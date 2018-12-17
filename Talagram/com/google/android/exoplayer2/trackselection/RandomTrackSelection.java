package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import java.util.Random;

public final class RandomTrackSelection extends BaseTrackSelection {
    public final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection$Factory {
        private final Random random;

        public Factory() {
            super();
            this.random = new Random();
        }

        public Factory(int arg4) {
            super();
            this.random = new Random(((long)arg4));
        }

        public RandomTrackSelection createTrackSelection(TrackGroup arg2, BandwidthMeter arg3, int[] arg4) {
            return new RandomTrackSelection(arg2, arg4, this.random);
        }

        public TrackSelection createTrackSelection(TrackGroup arg1, BandwidthMeter arg2, int[] arg3) {
            return this.createTrackSelection(arg1, arg2, arg3);
        }
    }

    private final Random random;
    private int selectedIndex;

    public RandomTrackSelection(TrackGroup arg1, int[] arg2) {
        super(arg1, arg2);
        this.random = new Random();
        this.selectedIndex = this.random.nextInt(this.length);
    }

    public RandomTrackSelection(TrackGroup arg2, int[] arg3, long arg4) {
        this(arg2, arg3, new Random(arg4));
    }

    public RandomTrackSelection(TrackGroup arg1, int[] arg2, Random arg3) {
        super(arg1, arg2);
        this.random = arg3;
        this.selectedIndex = arg3.nextInt(this.length);
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public Object getSelectionData() {
        return null;
    }

    public int getSelectionReason() {
        return 3;
    }

    public void updateSelectedTrack(long arg1, long arg3, long arg5) {
        arg1 = SystemClock.elapsedRealtime();
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        while(v4 < this.length) {
            if(!this.isBlacklisted(v4, arg1)) {
                ++v5;
            }

            ++v4;
        }

        this.selectedIndex = this.random.nextInt(v5);
        if(v5 != this.length) {
            v4 = 0;
            while(v3 < this.length) {
                if(!this.isBlacklisted(v3, arg1)) {
                    int v6 = v4 + 1;
                    if(this.selectedIndex == v4) {
                        this.selectedIndex = v3;
                        return;
                    }
                    else {
                        v4 = v6;
                    }
                }

                ++v3;
            }
        }
    }
}

