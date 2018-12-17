package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DummyDataSource;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.PriorityDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;

public final class DownloaderConstructorHelper {
    private final Cache cache;
    private final Factory cacheReadDataSourceFactory;
    private final com.google.android.exoplayer2.upstream.DataSink$Factory cacheWriteDataSinkFactory;
    private final PriorityTaskManager priorityTaskManager;
    private final Factory upstreamDataSourceFactory;

    public DownloaderConstructorHelper(Cache arg7, Factory arg8) {
        this(arg7, arg8, null, null, null);
    }

    public DownloaderConstructorHelper(Cache arg1, Factory arg2, Factory arg3, com.google.android.exoplayer2.upstream.DataSink$Factory arg4, PriorityTaskManager arg5) {
        super();
        Assertions.checkNotNull(arg2);
        this.cache = arg1;
        this.upstreamDataSourceFactory = arg2;
        this.cacheReadDataSourceFactory = arg3;
        this.cacheWriteDataSinkFactory = arg4;
        this.priorityTaskManager = arg5;
    }

    public CacheDataSource buildCacheDataSource(boolean arg9) {
        PriorityDataSource v3_1;
        DataSink v9;
        DataSource v0;
        if(this.cacheReadDataSourceFactory != null) {
            v0 = this.cacheReadDataSourceFactory.createDataSource();
        }
        else {
            FileDataSource v0_1 = new FileDataSource();
        }

        DataSource v4 = v0;
        if(arg9) {
            return new CacheDataSource(this.cache, DummyDataSource.INSTANCE, v4, null, 1, null);
        }

        if(this.cacheWriteDataSinkFactory != null) {
            v9 = this.cacheWriteDataSinkFactory.createDataSink();
        }
        else {
            CacheDataSink v9_1 = new CacheDataSink(this.cache, 2097152);
        }

        DataSink v5 = v9;
        DataSource v9_2 = this.upstreamDataSourceFactory.createDataSource();
        if(this.priorityTaskManager == null) {
            DataSource v3 = v9_2;
        }
        else {
            v3_1 = new PriorityDataSource(v9_2, this.priorityTaskManager, -1000);
        }

        return new CacheDataSource(this.cache, ((DataSource)v3_1), v4, v5, 1, null);
    }

    public Cache getCache() {
        return this.cache;
    }

    public PriorityTaskManager getPriorityTaskManager() {
        PriorityTaskManager v0 = this.priorityTaskManager != null ? this.priorityTaskManager : new PriorityTaskManager();
        return v0;
    }
}

