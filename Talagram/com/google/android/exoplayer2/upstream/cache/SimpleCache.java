package com.google.android.exoplayer2.upstream.cache;

import android.os.ConditionVariable;
import android.util.Log;
import com.google.android.exoplayer2.util.Assertions;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public final class SimpleCache implements Cache {
    private static final String TAG = "SimpleCache";
    private final File cacheDir;
    private static boolean cacheFolderLockingDisabled;
    private final CacheEvictor evictor;
    private final CachedContentIndex index;
    private final HashMap listeners;
    private static final HashSet lockedCacheDirs;
    private boolean released;
    private long totalSpace;

    static {
        SimpleCache.lockedCacheDirs = new HashSet();
    }

    public SimpleCache(File arg3, CacheEvictor arg4) {
        this(arg3, arg4, null, false);
    }

    public SimpleCache(File arg2, CacheEvictor arg3, byte[] arg4, boolean arg5) {
        this(arg2, arg3, new CachedContentIndex(arg2, arg4, arg5));
    }

    SimpleCache(File arg2, CacheEvictor arg3, CachedContentIndex arg4) {
        super();
        if(SimpleCache.lockFolder(arg2)) {
            this.cacheDir = arg2;
            this.evictor = arg3;
            this.index = arg4;
            this.listeners = new HashMap();
            ConditionVariable v2 = new ConditionVariable();
            new Thread("SimpleCache.initialize()", v2) {
                public void run() {
                    SimpleCache v0 = SimpleCache.this;
                    __monitor_enter(v0);
                    try {
                        this.val$conditionVariable.open();
                        SimpleCache.access$000(SimpleCache.this);
                        SimpleCache.access$100(SimpleCache.this).onCacheInitialized();
                        __monitor_exit(v0);
                        return;
                    label_12:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v1) {
                        goto label_12;
                    }

                    throw v1;
                }
            }.start();
            v2.block();
            return;
        }

        StringBuilder v4 = new StringBuilder();
        v4.append("Another SimpleCache instance uses the folder: ");
        v4.append(arg2);
        throw new IllegalStateException(v4.toString());
    }

    public SimpleCache(File arg2, CacheEvictor arg3, byte[] arg4) {
        boolean v0 = arg4 != null ? true : false;
        this(arg2, arg3, arg4, v0);
    }

    static void access$000(SimpleCache arg0) {
        arg0.initialize();
    }

    static CacheEvictor access$100(SimpleCache arg0) {
        return arg0.evictor;
    }

    public NavigableSet addListener(String arg3, Listener arg4) {
        NavigableSet v3_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            Object v0 = this.listeners.get(arg3);
            if(v0 == null) {
                ArrayList v0_1 = new ArrayList();
                this.listeners.put(arg3, v0_1);
            }

            ((ArrayList)v0).add(arg4);
            v3_1 = this.getCachedSpans(arg3);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return v3_1;
    }

    private void addSpan(SimpleCacheSpan arg5) {
        this.index.getOrAdd(arg5.key).addSpan(arg5);
        this.totalSpace += arg5.length;
        this.notifySpanAdded(arg5);
    }

    public void applyContentMetadataMutations(String arg2, ContentMetadataMutations arg3) {
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            this.index.applyContentMetadataMutations(arg2, arg3);
            this.index.store();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void commitFile(File arg10) {
        CachedContent v3_1;
        SimpleCacheSpan v0;
        boolean v1;
        __monitor_enter(this);
        try {
            v1 = true;
            Assertions.checkState(this.released ^ 1);
            v0 = SimpleCacheSpan.createCacheEntry(arg10, this.index);
            boolean v3 = v0 != null ? true : false;
            Assertions.checkState(v3);
            v3_1 = this.index.get(v0.key);
            Assertions.checkNotNull(v3_1);
            Assertions.checkState(v3_1.isLocked());
            if(arg10.exists()) {
                goto label_23;
            }
        }
        catch(Throwable v10) {
            goto label_48;
        }

        __monitor_exit(this);
        return;
        try {
        label_23:
            if(arg10.length() != 0) {
                goto label_29;
            }

            arg10.delete();
        }
        catch(Throwable v10) {
            goto label_48;
        }

        __monitor_exit(this);
        return;
        try {
        label_29:
            long v3_2 = ContentMetadataInternal.getContentLength(v3_1.getMetadata());
            if(v3_2 != -1) {
                if(v0.position + v0.length <= v3_2) {
                }
                else {
                    v1 = false;
                }

                Assertions.checkState(v1);
            }

            this.addSpan(v0);
            this.index.store();
            this.notifyAll();
        }
        catch(Throwable v10) {
        label_48:
            __monitor_exit(this);
            throw v10;
        }

        __monitor_exit(this);
    }

    @Deprecated public static void disableCacheFolderLocking() {
        Class v0 = SimpleCache.class;
        __monitor_enter(v0);
        try {
            SimpleCache.cacheFolderLockingDisabled = true;
            SimpleCache.lockedCacheDirs.clear();
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
    }

    public long getCacheSpace() {
        long v0_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            v0_1 = this.totalSpace;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public long getCachedLength(String arg2, long arg3, long arg5) {
        long v2_2;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            CachedContent v2_1 = this.index.get(arg2);
            if(v2_1 != null) {
                v2_2 = v2_1.getCachedBytesLength(arg3, arg5);
            }
            else {
                goto label_9;
            }

            goto label_10;
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

    label_9:
        v2_2 = -arg5;
    label_10:
        __monitor_exit(this);
        return v2_2;
    }

    public NavigableSet getCachedSpans(String arg2) {
        TreeSet v0;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            CachedContent v2_1 = this.index.get(arg2);
            v0 = v2_1 == null || (v2_1.isEmpty()) ? new TreeSet() : new TreeSet(v2_1.getSpans());
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return ((NavigableSet)v0);
    }

    public long getContentLength(String arg3) {
        long v0;
        __monitor_enter(this);
        try {
            v0 = ContentMetadataInternal.getContentLength(this.getContentMetadata(arg3));
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return v0;
    }

    public ContentMetadata getContentMetadata(String arg2) {
        ContentMetadata v2_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            v2_1 = this.index.getContentMetadata(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public Set getKeys() {
        HashSet v0_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            v0_1 = new HashSet(this.index.getKeys());
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return ((Set)v0_1);
    }

    private SimpleCacheSpan getSpan(String arg3, long arg4) {
        SimpleCacheSpan v3;
        CachedContent v0 = this.index.get(arg3);
        if(v0 == null) {
            return SimpleCacheSpan.createOpenHole(arg3, arg4);
        }

        while(true) {
            v3 = v0.getSpan(arg4);
            if((v3.isCached) && !v3.file.exists()) {
                this.removeStaleSpansAndCachedContents();
                continue;
            }

            return v3;
        }

        return v3;
    }

    private void initialize() {
        if(!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
            return;
        }

        this.index.load();
        File[] v0 = this.cacheDir.listFiles();
        if(v0 == null) {
            return;
        }

        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            File v3 = v0[v2];
            if(v3.getName().equals("cached_content_index.exi")) {
            }
            else {
                SimpleCacheSpan v4 = v3.length() > 0 ? SimpleCacheSpan.createCacheEntry(v3, this.index) : null;
                if(v4 != null) {
                    this.addSpan(v4);
                    goto label_32;
                }

                v3.delete();
            }

        label_32:
        }

        this.index.removeEmpty();
        try {
            this.index.store();
        }
        catch(CacheException v0_1) {
            Log.e("SimpleCache", "Storing index file failed", ((Throwable)v0_1));
        }
    }

    public static boolean isCacheFolderLocked(File arg2) {
        boolean v2_1;
        Class v0 = SimpleCache.class;
        __monitor_enter(v0);
        try {
            v2_1 = SimpleCache.lockedCacheDirs.contains(arg2.getAbsoluteFile());
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    public boolean isCached(String arg3, long arg4, long arg6) {
        boolean v1;
        __monitor_enter(this);
        try {
            v1 = true;
            Assertions.checkState(this.released ^ 1);
            CachedContent v3_1 = this.index.get(arg3);
            if(v3_1 == null) {
                goto label_11;
            }
            else if(v3_1.getCachedBytesLength(arg4, arg6) < arg6) {
                goto label_11;
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        goto label_12;
    label_11:
        v1 = false;
    label_12:
        __monitor_exit(this);
        return v1;
    }

    private static boolean lockFolder(File arg2) {
        boolean v2_1;
        Class v0 = SimpleCache.class;
        __monitor_enter(v0);
        try {
            if(!SimpleCache.cacheFolderLockingDisabled) {
                goto label_7;
            }
        }
        catch(Throwable v2) {
            goto label_13;
        }

        __monitor_exit(v0);
        return 1;
        try {
        label_7:
            v2_1 = SimpleCache.lockedCacheDirs.add(arg2.getAbsoluteFile());
        }
        catch(Throwable v2) {
        label_13:
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
        return v2_1;
    }

    private void notifySpanAdded(SimpleCacheSpan arg4) {
        Object v0 = this.listeners.get(arg4.key);
        if(v0 != null) {
            int v1;
            for(v1 = ((ArrayList)v0).size() - 1; v1 >= 0; --v1) {
                ((ArrayList)v0).get(v1).onSpanAdded(((Cache)this), ((CacheSpan)arg4));
            }
        }

        this.evictor.onSpanAdded(((Cache)this), ((CacheSpan)arg4));
    }

    private void notifySpanRemoved(CacheSpan arg4) {
        Object v0 = this.listeners.get(arg4.key);
        if(v0 != null) {
            int v1;
            for(v1 = ((ArrayList)v0).size() - 1; v1 >= 0; --v1) {
                ((ArrayList)v0).get(v1).onSpanRemoved(((Cache)this), arg4);
            }
        }

        this.evictor.onSpanRemoved(((Cache)this), arg4);
    }

    private void notifySpanTouched(SimpleCacheSpan arg4, CacheSpan arg5) {
        Object v0 = this.listeners.get(arg4.key);
        if(v0 != null) {
            int v1;
            for(v1 = ((ArrayList)v0).size() - 1; v1 >= 0; --v1) {
                ((ArrayList)v0).get(v1).onSpanTouched(((Cache)this), ((CacheSpan)arg4), arg5);
            }
        }

        this.evictor.onSpanTouched(((Cache)this), ((CacheSpan)arg4), arg5);
    }

    public void release() {
        __monitor_enter(this);
        try {
            if(!this.released) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_20;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            this.listeners.clear();
        }
        catch(Throwable v0) {
            goto label_20;
        }

        try {
            this.removeStaleSpansAndCachedContents();
            goto label_9;
        }
        catch(Throwable v1) {
            try {
                SimpleCache.unlockFolder(this.cacheDir);
                this.released = true;
                throw v1;
            label_9:
                SimpleCache.unlockFolder(this.cacheDir);
                this.released = true;
            }
            catch(Throwable v0) {
            label_20:
                __monitor_exit(this);
                throw v0;
            }
        }

        __monitor_exit(this);
    }

    public void releaseHoleSpan(CacheSpan arg2) {
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            CachedContent v2_1 = this.index.get(arg2.key);
            Assertions.checkNotNull(v2_1);
            Assertions.checkState(v2_1.isLocked());
            v2_1.setLocked(false);
            this.index.maybeRemove(v2_1.key);
            this.notifyAll();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void removeListener(String arg2, Listener arg3) {
        __monitor_enter(this);
        try {
            if(!this.released) {
                goto label_5;
            }
        }
        catch(Throwable v2) {
            goto label_16;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            Object v0 = this.listeners.get(arg2);
            if(v0 != null) {
                ((ArrayList)v0).remove(arg3);
                if(((ArrayList)v0).isEmpty()) {
                    this.listeners.remove(arg2);
                }
            }
        }
        catch(Throwable v2) {
        label_16:
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    private void removeSpan(CacheSpan arg6, boolean arg7) {
        CachedContent v0 = this.index.get(arg6.key);
        if(v0 != null) {
            if(!v0.removeSpan(arg6)) {
            }
            else {
                this.totalSpace -= arg6.length;
                if(arg7) {
                    try {
                        this.index.maybeRemove(v0.key);
                        this.index.store();
                    }
                    catch(Throwable v7) {
                        this.notifySpanRemoved(arg6);
                        throw v7;
                    }
                }

                this.notifySpanRemoved(arg6);
            }
        }
    }

    public void removeSpan(CacheSpan arg3) {
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            this.removeSpan(arg3, true);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private void removeStaleSpansAndCachedContents() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.index.getAll().iterator();
    label_5:
        if(v1.hasNext()) {
            Iterator v2 = v1.next().getSpans().iterator();
            while(true) {
                if(!v2.hasNext()) {
                    goto label_5;
                }

                Object v3 = v2.next();
                if(((CacheSpan)v3).file.exists()) {
                    continue;
                }

                v0.add(v3);
            }
        }

        int v2_1;
        for(v2_1 = 0; v2_1 < v0.size(); ++v2_1) {
            this.removeSpan(v0.get(v2_1), false);
        }

        this.index.removeEmpty();
        this.index.store();
    }

    public void setContentLength(String arg2, long arg3) {
        __monitor_enter(this);
        try {
            ContentMetadataMutations v0 = new ContentMetadataMutations();
            ContentMetadataInternal.setContentLength(v0, arg3);
            this.applyContentMetadataMutations(arg2, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public File startFile(String arg9, long arg10, long arg12) {
        File v9_1;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            CachedContent v0 = this.index.get(arg9);
            Assertions.checkNotNull(v0);
            Assertions.checkState(v0.isLocked());
            if(!this.cacheDir.exists()) {
                this.cacheDir.mkdirs();
                this.removeStaleSpansAndCachedContents();
            }

            this.evictor.onStartFile(this, arg9, arg10, arg12);
            v9_1 = SimpleCacheSpan.getCacheFile(this.cacheDir, v0.id, arg10, System.currentTimeMillis());
        }
        catch(Throwable v9) {
            __monitor_exit(this);
            throw v9;
        }

        __monitor_exit(this);
        return v9_1;
    }

    public CacheSpan startReadWrite(String arg1, long arg2) {
        return this.startReadWrite(arg1, arg2);
    }

    public SimpleCacheSpan startReadWrite(String arg2, long arg3) {
        SimpleCacheSpan v0;
        __monitor_enter(this);
        try {
            while(true) {
            label_1:
                v0 = this.startReadWriteNonBlocking(arg2, arg3);
                if(v0 == null) {
                    goto label_5;
                }

                break;
            }
        }
        catch(Throwable v2) {
            goto label_8;
        }

        __monitor_exit(this);
        return v0;
        try {
        label_5:
            this.wait();
            goto label_1;
        }
        catch(Throwable v2) {
        label_8:
            __monitor_exit(this);
            throw v2;
        }
    }

    public SimpleCacheSpan startReadWriteNonBlocking(String arg3, long arg4) {
        SimpleCacheSpan v3_1;
        SimpleCacheSpan v4;
        __monitor_enter(this);
        try {
            Assertions.checkState(this.released ^ 1);
            v4 = this.getSpan(arg3, arg4);
            if(!v4.isCached) {
                goto label_14;
            }

            v3_1 = this.index.get(arg3).touch(v4);
            this.notifySpanTouched(v4, ((CacheSpan)v3_1));
        }
        catch(Throwable v3) {
            goto label_25;
        }

        __monitor_exit(this);
        return v3_1;
        try {
        label_14:
            CachedContent v3_2 = this.index.getOrAdd(arg3);
            if(v3_2.isLocked()) {
                goto label_21;
            }

            v3_2.setLocked(true);
        }
        catch(Throwable v3) {
        label_25:
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return v4;
    label_21:
        __monitor_exit(this);
        return null;
    }

    public CacheSpan startReadWriteNonBlocking(String arg1, long arg2) {
        return this.startReadWriteNonBlocking(arg1, arg2);
    }

    private static void unlockFolder(File arg2) {
        Class v0 = SimpleCache.class;
        __monitor_enter(v0);
        try {
            if(!SimpleCache.cacheFolderLockingDisabled) {
                SimpleCache.lockedCacheDirs.remove(arg2.getAbsoluteFile());
            }
        }
        catch(Throwable v2) {
            __monitor_exit(v0);
            throw v2;
        }

        __monitor_exit(v0);
    }
}

