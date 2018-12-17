package com.google.android.exoplayer2.upstream.cache;

public interface CacheEvictor extends Listener {
    void onCacheInitialized();

    void onStartFile(Cache arg1, String arg2, long arg3, long arg4);
}

