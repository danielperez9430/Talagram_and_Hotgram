package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class ClippingMediaPeriod implements Callback, MediaPeriod {
    final class ClippingSampleStream implements SampleStream {
        public final SampleStream childStream;
        private boolean sentEos;

        public ClippingSampleStream(ClippingMediaPeriod arg1, SampleStream arg2) {
            ClippingMediaPeriod.this = arg1;
            super();
            this.childStream = arg2;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        public boolean isReady() {
            boolean v0 = (ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) || !this.childStream.isReady() ? false : true;
            return v0;
        }

        public void maybeThrowError() {
            this.childStream.maybeThrowError();
        }

        public int readData(FormatHolder arg11, DecoderInputBuffer arg12, boolean arg13) {
            // Method was not decompiled
        }

        public int skipData(long arg2) {
            if(ClippingMediaPeriod.this.isPendingInitialDiscontinuity()) {
                return -3;
            }

            return this.childStream.skipData(arg2);
        }
    }

    private Callback callback;
    long endUs;
    public final MediaPeriod mediaPeriod;
    private long pendingInitialDiscontinuityPositionUs;
    private ClippingSampleStream[] sampleStreams;
    long startUs;

    public ClippingMediaPeriod(MediaPeriod arg1, boolean arg2, long arg3, long arg5) {
        super();
        this.mediaPeriod = arg1;
        this.sampleStreams = new ClippingSampleStream[0];
        long v1 = arg2 ? arg3 : -9223372036854775807L;
        this.pendingInitialDiscontinuityPositionUs = v1;
        this.startUs = arg3;
        this.endUs = arg5;
    }

    private SeekParameters clipSeekParameters(long arg10, SeekParameters arg12) {
        long v0 = Util.constrainValue(arg12.toleranceBeforeUs, 0, arg10 - this.startUs);
        long v2 = arg12.toleranceAfterUs;
        long v6 = this.endUs == -9223372036854775808L ? 9223372036854775807L : this.endUs - arg10;
        arg10 = Util.constrainValue(v2, 0, v6);
        if(v0 == arg12.toleranceBeforeUs && arg10 == arg12.toleranceAfterUs) {
            return arg12;
        }

        return new SeekParameters(v0, arg10);
    }

    public boolean continueLoading(long arg2) {
        return this.mediaPeriod.continueLoading(arg2);
    }

    public void discardBuffer(long arg2, boolean arg4) {
        this.mediaPeriod.discardBuffer(arg2, arg4);
    }

    public long getAdjustedSeekPositionUs(long arg4, SeekParameters arg6) {
        if(arg4 == this.startUs) {
            return this.startUs;
        }

        return this.mediaPeriod.getAdjustedSeekPositionUs(arg4, this.clipSeekParameters(arg4, arg6));
    }

    public long getBufferedPositionUs() {
        long v0 = this.mediaPeriod.getBufferedPositionUs();
        long v2 = -9223372036854775808L;
        if(v0 != v2 && (this.endUs == v2 || v0 < this.endUs)) {
            return v0;
        }

        return v2;
    }

    public long getNextLoadPositionUs() {
        long v0 = this.mediaPeriod.getNextLoadPositionUs();
        long v2 = -9223372036854775808L;
        if(v0 != v2 && (this.endUs == v2 || v0 < this.endUs)) {
            return v0;
        }

        return v2;
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    boolean isPendingInitialDiscontinuity() {
        boolean v0 = this.pendingInitialDiscontinuityPositionUs != -9223372036854775807L ? true : false;
        return v0;
    }

    public void maybeThrowPrepareError() {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public void onContinueLoadingRequested(MediaPeriod arg1) {
        this.callback.onContinueLoadingRequested(((SequenceableLoader)this));
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((MediaPeriod)arg1));
    }

    public void onPrepared(MediaPeriod arg1) {
        this.callback.onPrepared(((MediaPeriod)this));
    }

    public void prepare(Callback arg1, long arg2) {
        this.callback = arg1;
        this.mediaPeriod.prepare(((Callback)this), arg2);
    }

    public long readDiscontinuity() {
        // Method was not decompiled
    }

    public void reevaluateBuffer(long arg2) {
        this.mediaPeriod.reevaluateBuffer(arg2);
    }

    public long seekToUs(long arg7) {
        this.pendingInitialDiscontinuityPositionUs = -9223372036854775807L;
        ClippingSampleStream[] v0 = this.sampleStreams;
        int v1 = v0.length;
        boolean v2 = false;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            ClippingSampleStream v4 = v0[v3];
            if(v4 != null) {
                v4.clearSentEos();
            }
        }

        long v0_1 = this.mediaPeriod.seekToUs(arg7);
        if(v0_1 == arg7) {
        label_22:
            v2 = true;
        }
        else if(v0_1 >= this.startUs) {
            if(this.endUs == -9223372036854775808L) {
                goto label_22;
            }
            else if(v0_1 <= this.endUs) {
                goto label_22;
            }
        }

        Assertions.checkState(v2);
        return v0_1;
    }

    public long selectTracks(TrackSelection[] arg13, boolean[] arg14, SampleStream[] arg15, boolean[] arg16, long arg17) {
        boolean v4_1;
        SampleStream v11;
        ClippingMediaPeriod v0 = this;
        SampleStream[] v1 = arg15;
        v0.sampleStreams = new ClippingSampleStream[v1.length];
        SampleStream[] v9 = new SampleStream[v1.length];
        int v10 = 0;
        int v2;
        for(v2 = 0; true; ++v2) {
            v11 = null;
            if(v2 >= v1.length) {
                break;
            }

            v0.sampleStreams[v2] = v1[v2];
            if(v0.sampleStreams[v2] != null) {
                v11 = v0.sampleStreams[v2].childStream;
            }

            v9[v2] = v11;
        }

        long v2_1 = v0.mediaPeriod.selectTracks(arg13, arg14, v9, arg16, arg17);
        long v4 = !this.isPendingInitialDiscontinuity() || arg17 != v0.startUs || !ClippingMediaPeriod.shouldKeepInitialDiscontinuity(v0.startUs, arg13) ? -9223372036854775807L : v2_1;
        v0.pendingInitialDiscontinuityPositionUs = v4;
        if(v2_1 != arg17) {
            if(v2_1 >= v0.startUs) {
                if(v0.endUs == -9223372036854775808L) {
                    goto label_54;
                }
                else if(v2_1 <= v0.endUs) {
                    goto label_54;
                }
            }

            v4_1 = false;
        }
        else {
        label_54:
            v4_1 = true;
        }

        Assertions.checkState(v4_1);
        while(v10 < v1.length) {
            if(v9[v10] == null) {
                v0.sampleStreams[v10] = ((ClippingSampleStream)v11);
            }
            else {
                if(v1[v10] != null && v0.sampleStreams[v10].childStream == v9[v10]) {
                    goto label_75;
                }

                v0.sampleStreams[v10] = new ClippingSampleStream(this, v9[v10]);
            }

        label_75:
            v1[v10] = v0.sampleStreams[v10];
            ++v10;
        }

        return v2_1;
    }

    private static boolean shouldKeepInitialDiscontinuity(long arg3, TrackSelection[] arg5) {
        // Method was not decompiled
    }

    public void updateClipping(long arg1, long arg3) {
        this.startUs = arg1;
        this.endUs = arg3;
    }
}

