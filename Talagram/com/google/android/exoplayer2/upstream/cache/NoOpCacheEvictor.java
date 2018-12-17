package com.google.android.exoplayer2.upstream.cache;

public final class NoOpCacheEvictor implements CacheEvictor {
    public NoOpCacheEvictor() {
        super();
    }

    public void onCacheInitialized() {
    }

    public void onSpanAdded(Cache arg1, CacheSpan arg2) {
    }

    public void onSpanRemoved(Cache arg1, CacheSpan arg2) {
    }

    public void onSpanTouched(Cache arg1, CacheSpan arg2, CacheSpan arg3) {
    }

    public void onStartFile(Cache arg1, String arg2, long arg3, long arg5) {
    }
}

