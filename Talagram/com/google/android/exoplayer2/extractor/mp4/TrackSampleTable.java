package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class TrackSampleTable {
    public final long durationUs;
    public final int[] flags;
    public final int maximumSize;
    public final long[] offsets;
    public final int sampleCount;
    public final int[] sizes;
    public final long[] timestampsUs;
    public final Track track;

    public TrackSampleTable(Track arg5, long[] arg6, int[] arg7, int arg8, long[] arg9, int[] arg10, long arg11) {
        super();
        boolean v2 = false;
        boolean v0 = arg7.length == arg9.length ? true : false;
        Assertions.checkArgument(v0);
        v0 = arg6.length == arg9.length ? true : false;
        Assertions.checkArgument(v0);
        if(arg10.length == arg9.length) {
            v2 = true;
        }

        Assertions.checkArgument(v2);
        this.track = arg5;
        this.offsets = arg6;
        this.sizes = arg7;
        this.maximumSize = arg8;
        this.timestampsUs = arg9;
        this.flags = arg10;
        this.durationUs = arg11;
        this.sampleCount = arg6.length;
    }

    public int getIndexOfEarlierOrEqualSynchronizationSample(long arg4) {
        int v4;
        for(v4 = Util.binarySearchFloor(this.timestampsUs, arg4, true, false); v4 >= 0; --v4) {
            if((this.flags[v4] & 1) != 0) {
                return v4;
            }
        }

        return -1;
    }

    public int getIndexOfLaterOrEqualSynchronizationSample(long arg4) {
        int v4;
        for(v4 = Util.binarySearchCeil(this.timestampsUs, arg4, true, false); v4 < this.timestampsUs.length; ++v4) {
            if((this.flags[v4] & 1) != 0) {
                return v4;
            }
        }

        return -1;
    }
}

