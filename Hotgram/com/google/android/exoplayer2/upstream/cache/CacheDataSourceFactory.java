package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;

public final class CacheDataSourceFactory implements Factory {
    private final Cache cache;
    private final Factory cacheReadDataSourceFactory;
    private final com.google.android.exoplayer2.upstream.DataSink$Factory cacheWriteDataSinkFactory;
    private final EventListener eventListener;
    private final int flags;
    private final Factory upstreamFactory;

    public CacheDataSourceFactory(Cache arg2, Factory arg3) {
        this(arg2, arg3, 0);
    }

    public CacheDataSourceFactory(Cache arg7, Factory arg8, int arg9) {
        this(arg7, arg8, arg9, 2097152);
    }

    public CacheDataSourceFactory(Cache arg8, Factory arg9, int arg10, long arg11) {
        this(arg8, arg9, new FileDataSourceFactory(), new CacheDataSinkFactory(arg8, arg11), arg10, null);
    }

    public CacheDataSourceFactory(Cache arg1, Factory arg2, Factory arg3, com.google.android.exoplayer2.upstream.DataSink$Factory arg4, int arg5, EventListener arg6) {
        super();
        this.cache = arg1;
        this.upstreamFactory = arg2;
        this.cacheReadDataSourceFactory = arg3;
        this.cacheWriteDataSinkFactory = arg4;
        this.flags = arg5;
        this.eventListener = arg6;
    }

    public DataSource createDataSource() {
        return this.createDataSource();
    }

    public CacheDataSource createDataSource() {
        CacheDataSource v7 = null;
        Cache v1 = this.cache;
        DataSource v2 = this.upstreamFactory.createDataSource();
        DataSource v3 = this.cacheReadDataSourceFactory.createDataSource();
        DataSink v0 = this.cacheWriteDataSinkFactory != null ? this.cacheWriteDataSinkFactory.createDataSink() : null;
        DataSink v4 = v0;
        super(v1, v2, v3, v4, this.flags, this.eventListener);
        return v7;
    }
}

