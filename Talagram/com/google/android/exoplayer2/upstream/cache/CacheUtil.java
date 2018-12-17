package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager$PriorityTooLowException;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CacheUtil {
    final class com.google.android.exoplayer2.upstream.cache.CacheUtil$1 implements CacheKeyFactory {
        com.google.android.exoplayer2.upstream.cache.CacheUtil$1() {
            super();
        }

        public String buildCacheKey(DataSpec arg1) {
            return CacheUtil.getKey(arg1);
        }
    }

    public class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength;
        public volatile long newlyCachedBytes;

        public CachingCounters() {
            super();
            this.contentLength = -1;
        }

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;
    public static final CacheKeyFactory DEFAULT_CACHE_KEY_FACTORY;

    static {
        CacheUtil.DEFAULT_CACHE_KEY_FACTORY = new com.google.android.exoplayer2.upstream.cache.CacheUtil$1();
    }

    private CacheUtil() {
        super();
    }

    public static void cache(DataSpec arg9, Cache arg10, DataSource arg11, CachingCounters arg12, AtomicBoolean arg13) {
        CacheUtil.cache(arg9, arg10, new CacheDataSource(arg10, arg11), new byte[131072], null, 0, arg12, arg13, false);
    }

    public static void cache(DataSpec arg25, Cache arg26, CacheDataSource arg27, byte[] arg28, PriorityTaskManager arg29, int arg30, CachingCounters arg31, AtomicBoolean arg32, boolean arg33) {
        long v23;
        DataSpec v11 = arg25;
        Cache v12 = arg26;
        CachingCounters v0 = arg31;
        Assertions.checkNotNull(arg27);
        Assertions.checkNotNull(arg28);
        if(v0 != null) {
            CacheUtil.getCached(v11, v12, v0);
        }
        else {
            v0 = new CachingCounters();
        }

        CachingCounters v13 = v0;
        String v14 = CacheUtil.getKey(arg25);
        long v0_1 = v11.absoluteStreamPosition;
        long v15 = -1;
        long v2 = v11.length != v15 ? v11.length : v12.getContentLength(v14);
        long v19 = v0_1;
        long v17 = v2;
        while(true) {
            long v21 = 0;
            if(v17 != v21) {
                CacheUtil.throwExceptionIfInterruptedOrCancelled(arg32);
                long v4 = v17 != v15 ? v17 : 9223372036854775807L;
                v0_1 = arg26.getCachedLength(v14, v19, v4);
                if(v0_1 > v21) {
                    v23 = v0_1;
                }
                else {
                    long v9 = -v0_1;
                    v23 = v9;
                    if(CacheUtil.readAndDiscard(arg25, v19, v9, arg27, arg28, arg29, arg30, v13, arg32) < v23) {
                        if(!arg33) {
                            return;
                        }
                        else if(v17 == v15) {
                            return;
                        }
                        else {
                            throw new EOFException();
                        }
                    }
                }

                v19 += v23;
                if(v17 == v15) {
                    v23 = v21;
                }

                v17 -= v23;
                continue;
            }

            return;
        }
    }

    public static String generateKey(Uri arg0) {
        return arg0.toString();
    }

    public static void getCached(DataSpec arg19, Cache arg20, CachingCounters arg21) {
        DataSpec v0 = arg19;
        CachingCounters v1 = arg21;
        String v8 = CacheUtil.getKey(arg19);
        long v2 = v0.absoluteStreamPosition;
        long v9 = -1;
        long v4 = v0.length != v9 ? v0.length : arg20.getContentLength(v8);
        v1.contentLength = v4;
        long v11 = 0;
        v1.alreadyCachedBytes = v11;
        v1.newlyCachedBytes = v11;
        long v15 = v2;
        long v13;
        for(v13 = v4; v13 != v11; v13 -= v2) {
            long v17 = 9223372036854775807L;
            long v6 = Long.compare(v13, v9) != 0 ? v13 : v17;
            v2 = arg20.getCachedLength(v8, v15, v6);
            if(v2 > v11) {
                v1.alreadyCachedBytes += v2;
            }
            else {
                v2 = -v2;
                if(v2 == v17) {
                    return;
                }
            }

            v15 += v2;
            if(v13 == v9) {
                v2 = v11;
            }
        }
    }

    public static String getKey(DataSpec arg1) {
        String v1 = arg1.key != null ? arg1.key : CacheUtil.generateKey(arg1.uri);
        return v1;
    }

    private static long readAndDiscard(DataSpec arg18, long arg19, long arg21, DataSource arg23, byte[] arg24, PriorityTaskManager arg25, int arg26, CachingCounters arg27, AtomicBoolean arg28) {
        long v5_1;
        DataSpec v6;
        DataSource v1 = arg23;
        byte[] v2 = arg24;
        CachingCounters v3 = arg27;
        DataSpec v5 = arg18;
        while(true) {
            if(arg25 != null) {
                arg25.proceed(arg26);
            }

            try {
                CacheUtil.throwExceptionIfInterruptedOrCancelled(arg28);
                v6 = new DataSpec(v5.uri, v5.postBody, arg19, v5.position + arg19 - v5.absoluteStreamPosition, -1, v5.key, v5.flags | 2);
            }
            catch(Throwable v0) {
                break;
            }
            catch(PriorityTooLowException ) {
                goto label_74;
            }

            DataSpec v4 = v6;
            try {
                v5_1 = v1.open(v4);
                long v9 = -1;
                if(v3.contentLength == v9 && v5_1 != v9) {
                    v3.contentLength = v4.absoluteStreamPosition + v5_1;
                }

                v5_1 = 0;
                while(v5_1 != arg21) {
                    CacheUtil.throwExceptionIfInterruptedOrCancelled(arg28);
                    int v8 = arg21 != v9 ? ((int)Math.min(((long)v2.length), arg21 - v5_1)) : v2.length;
                    int v7 = v1.read(v2, 0, v8);
                    if(v7 == -1) {
                        if(v3.contentLength != v9) {
                            break;
                        }

                        v3.contentLength = v4.absoluteStreamPosition + v5_1;
                        break;
                    }

                    long v7_1 = ((long)v7);
                    v5_1 += v7_1;
                    v3.newlyCachedBytes += v7_1;
                }
            }
            catch(PriorityTooLowException ) {
                goto label_69;
            }
            catch(Throwable v0) {
                break;
            }

            Util.closeQuietly(arg23);
            return v5_1;
        label_69:
            v5 = v4;
        label_74:
            Util.closeQuietly(arg23);
        }

        Util.closeQuietly(arg23);
        throw v0;
    }

    public static void remove(Cache arg1, String arg2) {
        Iterator v2 = arg1.getCachedSpans(arg2).iterator();
        while(v2.hasNext()) {
            Object v0 = v2.next();
            try {
                arg1.removeSpan(((CacheSpan)v0));
            }
            catch(CacheException ) {
            }
        }
    }

    private static void throwExceptionIfInterruptedOrCancelled(AtomicBoolean arg1) {
        if(!Thread.interrupted() && (arg1 == null || !arg1.get())) {
            return;
        }

        throw new InterruptedException();
    }
}

