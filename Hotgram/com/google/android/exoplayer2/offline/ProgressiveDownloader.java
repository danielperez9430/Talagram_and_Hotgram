package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheUtil$CachingCounters;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ProgressiveDownloader implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CachingCounters cachingCounters;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private final AtomicBoolean isCanceled;
    private final PriorityTaskManager priorityTaskManager;

    public ProgressiveDownloader(Uri arg10, String arg11, DownloaderConstructorHelper arg12) {
        super();
        this.dataSpec = new DataSpec(arg10, 0, -1, arg11, 0);
        this.cache = arg12.getCache();
        this.dataSource = arg12.buildCacheDataSource(false);
        this.priorityTaskManager = arg12.getPriorityTaskManager();
        this.cachingCounters = new CachingCounters();
        this.isCanceled = new AtomicBoolean();
    }

    public void cancel() {
        this.isCanceled.set(true);
    }

    public void download() {
        int v1 = -1000;
        this.priorityTaskManager.add(v1);
        try {
            CacheUtil.cache(this.dataSpec, this.cache, this.dataSource, new byte[131072], this.priorityTaskManager, -1000, this.cachingCounters, this.isCanceled, true);
        }
        catch(Throwable v0) {
            this.priorityTaskManager.remove(v1);
            throw v0;
        }

        this.priorityTaskManager.remove(v1);
    }

    public float getDownloadPercentage() {
        long v0 = this.cachingCounters.contentLength;
        float v0_1 = v0 == -1 ? -1f : (((float)this.cachingCounters.totalCachedBytes())) * 100f / (((float)v0));
        return v0_1;
    }

    public long getDownloadedBytes() {
        return this.cachingCounters.totalCachedBytes();
    }

    public void remove() {
        CacheUtil.remove(this.cache, CacheUtil.getKey(this.dataSpec));
    }
}

