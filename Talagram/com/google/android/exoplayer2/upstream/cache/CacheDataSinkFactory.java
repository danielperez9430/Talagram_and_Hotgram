package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink$Factory;
import com.google.android.exoplayer2.upstream.DataSink;

public final class CacheDataSinkFactory implements Factory {
    private final int bufferSize;
    private final Cache cache;
    private final long maxCacheFileSize;

    public CacheDataSinkFactory(Cache arg2, long arg3) {
        this(arg2, arg3, 20480);
    }

    public CacheDataSinkFactory(Cache arg1, long arg2, int arg4) {
        super();
        this.cache = arg1;
        this.maxCacheFileSize = arg2;
        this.bufferSize = arg4;
    }

    public DataSink createDataSink() {
        return new CacheDataSink(this.cache, this.maxCacheFileSize, this.bufferSize);
    }
}

