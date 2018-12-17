package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;

public final class BaseMediaChunkOutput implements TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] arg1, SampleQueue[] arg2) {
        super();
        this.trackTypes = arg1;
        this.sampleQueues = arg2;
    }

    public int[] getWriteIndices() {
        int[] v0 = new int[this.sampleQueues.length];
        int v1;
        for(v1 = 0; v1 < this.sampleQueues.length; ++v1) {
            if(this.sampleQueues[v1] != null) {
                v0[v1] = this.sampleQueues[v1].getWriteIndex();
            }
        }

        return v0;
    }

    public void setSampleOffsetUs(long arg5) {
        SampleQueue[] v0 = this.sampleQueues;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            SampleQueue v3 = v0[v2];
            if(v3 != null) {
                v3.setSampleOffsetUs(arg5);
            }
        }
    }

    public TrackOutput track(int arg3, int arg4) {
        for(arg3 = 0; arg3 < this.trackTypes.length; ++arg3) {
            if(arg4 == this.trackTypes[arg3]) {
                return this.sampleQueues[arg3];
            }
        }

        Log.e("BaseMediaChunkOutput", "Unmatched track of type: " + arg4);
        return new DummyTrackOutput();
    }
}

