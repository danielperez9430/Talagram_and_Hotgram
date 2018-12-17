package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheUtil$CachingCounters;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SegmentDownloader implements Downloader {
    public class Segment implements Comparable {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long arg1, DataSpec arg3) {
            super();
            this.startTimeUs = arg1;
            this.dataSpec = arg3;
        }

        public int compareTo(Segment arg5) {
            int v5;
            long v0 = this.startTimeUs - arg5.startTimeUs;
            long v2 = 0;
            if(v0 == v2) {
                v5 = 0;
            }
            else if(v0 < v2) {
                v5 = -1;
            }
            else {
                v5 = 1;
            }

            return v5;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((Segment)arg1));
        }
    }

    private static final int BUFFER_SIZE_BYTES = 131072;
    private final Cache cache;
    private final CacheDataSource dataSource;
    private volatile long downloadedBytes;
    private volatile int downloadedSegments;
    private final AtomicBoolean isCanceled;
    private final Uri manifestUri;
    private final CacheDataSource offlineDataSource;
    private final PriorityTaskManager priorityTaskManager;
    private final ArrayList streamKeys;
    private volatile int totalSegments;

    public SegmentDownloader(Uri arg1, List arg2, DownloaderConstructorHelper arg3) {
        super();
        this.manifestUri = arg1;
        this.streamKeys = new ArrayList(((Collection)arg2));
        this.cache = arg3.getCache();
        this.dataSource = arg3.buildCacheDataSource(false);
        this.offlineDataSource = arg3.buildCacheDataSource(true);
        this.priorityTaskManager = arg3.getPriorityTaskManager();
        this.totalSegments = -1;
        this.isCanceled = new AtomicBoolean();
    }

    public void cancel() {
        this.isCanceled.set(true);
    }

    public final void download() {
        int v13;
        CachingCounters v12;
        byte[] v2;
        List v0_1;
        int v1 = -1000;
        this.priorityTaskManager.add(v1);
        try {
            v0_1 = this.initDownload();
            Collections.sort(v0_1);
            v2 = new byte[131072];
            v12 = new CachingCounters();
            v13 = 0;
        label_11:
            while(v13 >= v0_1.size()) {
                goto label_41;
            }
        }
        catch(Throwable v0) {
            goto label_46;
        }

        try {
            CacheUtil.cache(v0_1.get(v13).dataSpec, this.cache, this.dataSource, v2, this.priorityTaskManager, -1000, v12, this.isCanceled, true);
            ++this.downloadedSegments;
            goto label_27;
        }
        catch(Throwable v0) {
            try {
                this.downloadedBytes += v12.newlyCachedBytes;
                throw v0;
            label_27:
                this.downloadedBytes += v12.newlyCachedBytes;
                ++v13;
                goto label_11;
            }
            catch(Throwable v0) {
                goto label_46;
            }
        }

    label_41:
        this.priorityTaskManager.remove(v1);
        return;
    label_46:
        this.priorityTaskManager.remove(v1);
        throw v0;
    }

    public final float getDownloadPercentage() {
        int v0 = this.totalSegments;
        int v1 = this.downloadedSegments;
        int v2 = -1;
        if(v0 != v2) {
            if(v1 == v2) {
            }
            else {
                float v2_1 = 100f;
                if(v0 == 0) {
                }
                else {
                    v2_1 = (((float)v1)) * v2_1 / (((float)v0));
                }

                return v2_1;
            }
        }

        return -1f;
    }

    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    protected abstract FilterableManifest getManifest(DataSource arg1, Uri arg2);

    protected abstract List getSegments(DataSource arg1, FilterableManifest arg2, boolean arg3);

    private List initDownload() {
        FilterableManifest v0 = this.getManifest(this.dataSource, this.manifestUri);
        if(!this.streamKeys.isEmpty()) {
            Object v0_1 = v0.copy(this.streamKeys);
        }

        List v0_2 = this.getSegments(this.dataSource, v0, false);
        CachingCounters v1 = new CachingCounters();
        this.totalSegments = v0_2.size();
        this.downloadedSegments = 0;
        this.downloadedBytes = 0;
        int v2;
        for(v2 = v0_2.size() - 1; v2 >= 0; --v2) {
            CacheUtil.getCached(v0_2.get(v2).dataSpec, this.cache, v1);
            this.downloadedBytes += v1.alreadyCachedBytes;
            if(v1.alreadyCachedBytes == v1.contentLength) {
                ++this.downloadedSegments;
                v0_2.remove(v2);
            }
        }

        return v0_2;
    }

    public final void remove() {
        try {
            List v0_1 = this.getSegments(this.offlineDataSource, this.getManifest(this.offlineDataSource, this.manifestUri), true);
            int v1;
            for(v1 = 0; true; ++v1) {
                if(v1 >= v0_1.size()) {
                    goto label_19;
                }

                this.removeUri(v0_1.get(v1).dataSpec.uri);
            }
        }
        catch(IOException ) {
        label_19:
            this.removeUri(this.manifestUri);
            return;
        }
        catch(Throwable v0) {
            this.removeUri(this.manifestUri);
            throw v0;
        }
    }

    private void removeUri(Uri arg2) {
        CacheUtil.remove(this.cache, CacheUtil.generateKey(arg2));
    }
}

