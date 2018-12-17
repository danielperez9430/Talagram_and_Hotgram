package com.google.android.exoplayer2.upstream.cache;

import java.util.Comparator;
import java.util.TreeSet;

public final class LeastRecentlyUsedCacheEvictor implements CacheEvictor, Comparator {
    private long currentSize;
    private final TreeSet leastRecentlyUsed;
    private final long maxBytes;

    public LeastRecentlyUsedCacheEvictor(long arg1) {
        super();
        this.maxBytes = arg1;
        this.leastRecentlyUsed = new TreeSet(((Comparator)this));
    }

    public int compare(CacheSpan arg6, CacheSpan arg7) {
        if(arg6.lastAccessTimestamp - arg7.lastAccessTimestamp == 0) {
            return arg6.compareTo(arg7);
        }

        int v6 = arg6.lastAccessTimestamp < arg7.lastAccessTimestamp ? -1 : 1;
        return v6;
    }

    public int compare(Object arg1, Object arg2) {
        return this.compare(((CacheSpan)arg1), ((CacheSpan)arg2));
    }

    private void evictCache(Cache arg6, long arg7) {
        while(this.currentSize + arg7 > this.maxBytes) {
            if(this.leastRecentlyUsed.isEmpty()) {
                return;
            }

            try {
                arg6.removeSpan(this.leastRecentlyUsed.first());
            }
            catch(CacheException ) {
            }
        }
    }

    public void onCacheInitialized() {
    }

    public void onSpanAdded(Cache arg5, CacheSpan arg6) {
        this.leastRecentlyUsed.add(arg6);
        this.currentSize += arg6.length;
        this.evictCache(arg5, 0);
    }

    public void onSpanRemoved(Cache arg3, CacheSpan arg4) {
        this.leastRecentlyUsed.remove(arg4);
        this.currentSize -= arg4.length;
    }

    public void onSpanTouched(Cache arg1, CacheSpan arg2, CacheSpan arg3) {
        this.onSpanRemoved(arg1, arg2);
        this.onSpanAdded(arg1, arg3);
    }

    public void onStartFile(Cache arg1, String arg2, long arg3, long arg5) {
        this.evictCache(arg1, arg5);
    }
}

