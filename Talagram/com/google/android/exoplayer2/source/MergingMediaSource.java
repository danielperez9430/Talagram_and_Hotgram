package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class MergingMediaSource extends CompositeMediaSource {
    public final class IllegalMergeException extends IOException {
        @Retention(value=RetentionPolicy.SOURCE) @public interface Reason {
        }

        public static final int REASON_PERIOD_COUNT_MISMATCH;
        public final int reason;

        public IllegalMergeException(int arg1) {
            super();
            this.reason = arg1;
        }
    }

    private static final int PERIOD_COUNT_UNSET = -1;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList pendingTimelineSources;
    private int periodCount;
    private Object primaryManifest;
    private Timeline primaryTimeline;

    public MergingMediaSource(CompositeSequenceableLoaderFactory arg1, MediaSource[] arg2) {
        super();
        this.mediaSources = arg2;
        this.compositeSequenceableLoaderFactory = arg1;
        this.pendingTimelineSources = new ArrayList(Arrays.asList(((Object[])arg2)));
        this.periodCount = -1;
    }

    public MergingMediaSource(MediaSource[] arg2) {
        this(new DefaultCompositeSequenceableLoaderFactory(), arg2);
    }

    private IllegalMergeException checkTimelineMerges(Timeline arg3) {
        if(this.periodCount == -1) {
            this.periodCount = arg3.getPeriodCount();
        }
        else if(arg3.getPeriodCount() != this.periodCount) {
            return new IllegalMergeException(0);
        }

        return null;
    }

    public MediaPeriod createPeriod(MediaPeriodId arg4, Allocator arg5) {
        MediaPeriod[] v0 = new MediaPeriod[this.mediaSources.length];
        int v1;
        for(v1 = 0; v1 < v0.length; ++v1) {
            v0[v1] = this.mediaSources[v1].createPeriod(arg4, arg5);
        }

        return new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, v0);
    }

    public void maybeThrowSourceInfoRefreshError() {
        if(this.mergeError == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }

        throw this.mergeError;
    }

    protected void onChildSourceInfoRefreshed(Integer arg2, MediaSource arg3, Timeline arg4, Object arg5) {
        if(this.mergeError == null) {
            this.mergeError = this.checkTimelineMerges(arg4);
        }

        if(this.mergeError != null) {
            return;
        }

        this.pendingTimelineSources.remove(arg3);
        if(arg3 == this.mediaSources[0]) {
            this.primaryTimeline = arg4;
            this.primaryManifest = arg5;
        }

        if(this.pendingTimelineSources.isEmpty()) {
            this.refreshSourceInfo(this.primaryTimeline, this.primaryManifest);
        }
    }

    protected void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.onChildSourceInfoRefreshed(((Integer)arg1), arg2, arg3, arg4);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        super.prepareSourceInternal(arg1, arg2, arg3);
        int v1;
        for(v1 = 0; v1 < this.mediaSources.length; ++v1) {
            this.prepareChildSource(Integer.valueOf(v1), this.mediaSources[v1]);
        }
    }

    public void releasePeriod(MediaPeriod arg4) {
        int v0;
        for(v0 = 0; v0 < this.mediaSources.length; ++v0) {
            this.mediaSources[v0].releasePeriod(((MergingMediaPeriod)arg4).periods[v0]);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.primaryTimeline = null;
        this.primaryManifest = null;
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }
}

