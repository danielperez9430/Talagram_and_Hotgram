package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;

public final class FixedTrackSelection extends BaseTrackSelection {
    public final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection$Factory {
        private final Object data;
        private final int reason;

        public Factory() {
            super();
            this.reason = 0;
            this.data = null;
        }

        public Factory(int arg1, Object arg2) {
            super();
            this.reason = arg1;
            this.data = arg2;
        }

        public FixedTrackSelection createTrackSelection(TrackGroup arg3, BandwidthMeter arg4, int[] arg5) {
            boolean v1 = true;
            if(arg5.length == 1) {
            }
            else {
                v1 = false;
            }

            Assertions.checkArgument(v1);
            return new FixedTrackSelection(arg3, arg5[0], this.reason, this.data);
        }

        public TrackSelection createTrackSelection(TrackGroup arg1, BandwidthMeter arg2, int[] arg3) {
            return this.createTrackSelection(arg1, arg2, arg3);
        }
    }

    private final Object data;
    private final int reason;

    public FixedTrackSelection(TrackGroup arg3, int arg4) {
        this(arg3, arg4, 0, null);
    }

    public FixedTrackSelection(TrackGroup arg3, int arg4, int arg5, Object arg6) {
        super(arg3, new int[]{arg4});
        this.reason = arg5;
        this.data = arg6;
    }

    public int getSelectedIndex() {
        return 0;
    }

    public Object getSelectionData() {
        return this.data;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    public void updateSelectedTrack(long arg1, long arg3, long arg5) {
    }
}

