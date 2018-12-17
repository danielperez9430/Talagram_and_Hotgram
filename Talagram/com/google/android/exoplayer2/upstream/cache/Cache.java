package com.google.android.exoplayer2.upstream.cache;

import java.io.File;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.Set;

public interface Cache {
    public class CacheException extends IOException {
        public CacheException(String arg1) {
            super(arg1);
        }

        public CacheException(Throwable arg1) {
            super(arg1);
        }
    }

    public interface Listener {
        void onSpanAdded(Cache arg1, CacheSpan arg2);

        void onSpanRemoved(Cache arg1, CacheSpan arg2);

        void onSpanTouched(Cache arg1, CacheSpan arg2, CacheSpan arg3);
    }

    NavigableSet addListener(String arg1, Listener arg2);

    void applyContentMetadataMutations(String arg1, ContentMetadataMutations arg2);

    void commitFile(File arg1);

    long getCacheSpace();

    long getCachedLength(String arg1, long arg2, long arg3);

    NavigableSet getCachedSpans(String arg1);

    long getContentLength(String arg1);

    ContentMetadata getContentMetadata(String arg1);

    Set getKeys();

    boolean isCached(String arg1, long arg2, long arg3);

    void release();

    void releaseHoleSpan(CacheSpan arg1);

    void removeListener(String arg1, Listener arg2);

    void removeSpan(CacheSpan arg1);

    void setContentLength(String arg1, long arg2);

    File startFile(String arg1, long arg2, long arg3);

    CacheSpan startReadWrite(String arg1, long arg2);

    CacheSpan startReadWriteNonBlocking(String arg1, long arg2);
}

