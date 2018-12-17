package com.google.android.exoplayer2;

import android.util.Log;
import com.google.android.exoplayer2.source.ClippingMediaPeriod;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;

final class MediaPeriodHolder {
    private static final String TAG = "MediaPeriodHolder";
    public boolean hasEnabledTracks;
    public MediaPeriodInfo info;
    public final boolean[] mayRetainStreamFlags;
    public final MediaPeriod mediaPeriod;
    private final MediaSource mediaSource;
    public MediaPeriodHolder next;
    private TrackSelectorResult periodTrackSelectorResult;
    public boolean prepared;
    private final RendererCapabilities[] rendererCapabilities;
    public long rendererPositionOffsetUs;
    public final SampleStream[] sampleStreams;
    public TrackGroupArray trackGroups;
    private final TrackSelector trackSelector;
    public TrackSelectorResult trackSelectorResult;
    public final Object uid;

    public MediaPeriodHolder(RendererCapabilities[] arg3, long arg4, TrackSelector arg6, Allocator arg7, MediaSource arg8, Object arg9, MediaPeriodInfo arg10) {
        ClippingMediaPeriod v3;
        super();
        this.rendererCapabilities = arg3;
        this.rendererPositionOffsetUs = arg4 - arg10.startPositionUs;
        this.trackSelector = arg6;
        this.mediaSource = arg8;
        this.uid = Assertions.checkNotNull(arg9);
        this.info = arg10;
        this.sampleStreams = new SampleStream[arg3.length];
        this.mayRetainStreamFlags = new boolean[arg3.length];
        MediaPeriod v5 = arg8.createPeriod(arg10.id, arg7);
        if(arg10.id.endPositionUs != -9223372036854775808L) {
            v3 = new ClippingMediaPeriod(v5, true, 0, arg10.id.endPositionUs);
        }
        else {
            MediaPeriod v3_1 = v5;
        }

        this.mediaPeriod = ((MediaPeriod)v3);
    }

    public long applyTrackSelection(long arg2, boolean arg4) {
        return this.applyTrackSelection(arg2, arg4, new boolean[this.rendererCapabilities.length]);
    }

    public long applyTrackSelection(long arg12, boolean arg14, boolean[] arg15) {
        int v1;
        for(v1 = 0; true; ++v1) {
            boolean v3 = true;
            if(v1 >= this.trackSelectorResult.length) {
                break;
            }

            boolean[] v2 = this.mayRetainStreamFlags;
            if((arg14) || !this.trackSelectorResult.isEquivalent(this.periodTrackSelectorResult, v1)) {
                v3 = false;
            }
            else {
            }

            v2[v1] = v3;
        }

        this.disassociateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.updatePeriodTrackSelectorResult(this.trackSelectorResult);
        TrackSelectionArray v14 = this.trackSelectorResult.selections;
        arg12 = this.mediaPeriod.selectTracks(v14.getAll(), this.mayRetainStreamFlags, this.sampleStreams, arg15, arg12);
        this.associateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.hasEnabledTracks = false;
        int v15;
        for(v15 = 0; v15 < this.sampleStreams.length; ++v15) {
            if(this.sampleStreams[v15] != null) {
                Assertions.checkState(this.trackSelectorResult.isRendererEnabled(v15));
                if(this.rendererCapabilities[v15].getTrackType() != 5) {
                    this.hasEnabledTracks = true;
                }
            }
            else {
                boolean v1_1 = v14.get(v15) == null ? true : false;
                Assertions.checkState(v1_1);
            }
        }

        return arg12;
    }

    private void associateNoSampleRenderersWithEmptySampleStream(SampleStream[] arg4) {
        int v0;
        for(v0 = 0; v0 < this.rendererCapabilities.length; ++v0) {
            if(this.rendererCapabilities[v0].getTrackType() == 5 && (this.trackSelectorResult.isRendererEnabled(v0))) {
                arg4[v0] = new EmptySampleStream();
            }
        }
    }

    public void continueLoading(long arg2) {
        this.mediaPeriod.continueLoading(this.toPeriodTime(arg2));
    }

    private void disableTrackSelectionsInResult(TrackSelectorResult arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            boolean v1 = arg4.isRendererEnabled(v0);
            TrackSelection v2 = arg4.selections.get(v0);
            if((v1) && v2 != null) {
                v2.disable();
            }
        }
    }

    private void disassociateNoSampleRenderersWithEmptySampleStream(SampleStream[] arg4) {
        int v0;
        for(v0 = 0; v0 < this.rendererCapabilities.length; ++v0) {
            if(this.rendererCapabilities[v0].getTrackType() == 5) {
                arg4[v0] = null;
            }
        }
    }

    private void enableTrackSelectionsInResult(TrackSelectorResult arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            boolean v1 = arg4.isRendererEnabled(v0);
            TrackSelection v2 = arg4.selections.get(v0);
            if((v1) && v2 != null) {
                v2.enable();
            }
        }
    }

    public long getBufferedPositionUs(boolean arg6) {
        if(!this.prepared) {
            return this.info.startPositionUs;
        }

        long v1 = -9223372036854775808L;
        long v3 = this.hasEnabledTracks ? this.mediaPeriod.getBufferedPositionUs() : v1;
        if(v3 == v1 && (arg6)) {
            v3 = this.info.durationUs;
        }

        return v3;
    }

    public long getDurationUs() {
        return this.info.durationUs;
    }

    public long getNextLoadPositionUs() {
        long v0 = !this.prepared ? 0 : this.mediaPeriod.getNextLoadPositionUs();
        return v0;
    }

    public long getRendererOffset() {
        return this.rendererPositionOffsetUs;
    }

    public void handlePrepared(float arg7) {
        this.prepared = true;
        this.trackGroups = this.mediaPeriod.getTrackGroups();
        this.selectTracks(arg7);
        long v0 = this.applyTrackSelection(this.info.startPositionUs, false);
        this.rendererPositionOffsetUs += this.info.startPositionUs - v0;
        this.info = this.info.copyWithStartPositionUs(v0);
    }

    public boolean isFullyBuffered() {
        boolean v0;
        if(this.prepared) {
            if((this.hasEnabledTracks) && this.mediaPeriod.getBufferedPositionUs() != -9223372036854775808L) {
                goto label_10;
            }

            v0 = true;
        }
        else {
        label_10:
            v0 = false;
        }

        return v0;
    }

    public void reevaluateBuffer(long arg2) {
        if(this.prepared) {
            this.mediaPeriod.reevaluateBuffer(this.toPeriodTime(arg2));
        }
    }

    public void release() {
        MediaPeriod v1;
        MediaSource v0_1;
        this.updatePeriodTrackSelectorResult(null);
        try {
            if(this.info.id.endPositionUs != -9223372036854775808L) {
                v0_1 = this.mediaSource;
                v1 = this.mediaPeriod.mediaPeriod;
            }
            else {
                v0_1 = this.mediaSource;
                v1 = this.mediaPeriod;
            }

            v0_1.releasePeriod(v1);
        }
        catch(RuntimeException v0) {
            Log.e("MediaPeriodHolder", "Period release failed.", ((Throwable)v0));
        }
    }

    public boolean selectTracks(float arg5) {
        TrackSelectorResult v0 = this.trackSelector.selectTracks(this.rendererCapabilities, this.trackGroups);
        int v2 = 0;
        if(v0.isEquivalent(this.periodTrackSelectorResult)) {
            return 0;
        }

        this.trackSelectorResult = v0;
        TrackSelection[] v0_1 = this.trackSelectorResult.selections.getAll();
        int v1 = v0_1.length;
        while(v2 < v1) {
            TrackSelection v3 = v0_1[v2];
            if(v3 != null) {
                v3.onPlaybackSpeed(arg5);
            }

            ++v2;
        }

        return 1;
    }

    public long toPeriodTime(long arg3) {
        return arg3 - this.getRendererOffset();
    }

    public long toRendererTime(long arg3) {
        return arg3 + this.getRendererOffset();
    }

    private void updatePeriodTrackSelectorResult(TrackSelectorResult arg2) {
        if(this.periodTrackSelectorResult != null) {
            this.disableTrackSelectionsInResult(this.periodTrackSelectorResult);
        }

        this.periodTrackSelectorResult = arg2;
        if(this.periodTrackSelectorResult != null) {
            this.enableTrackSelectionsInResult(this.periodTrackSelectorResult);
        }
    }
}

