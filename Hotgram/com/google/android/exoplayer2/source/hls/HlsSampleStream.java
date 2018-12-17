package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.util.Assertions;

final class HlsSampleStream implements SampleStream {
    private int sampleQueueIndex;
    private final HlsSampleStreamWrapper sampleStreamWrapper;
    private final int trackGroupIndex;

    public HlsSampleStream(HlsSampleStreamWrapper arg1, int arg2) {
        super();
        this.sampleStreamWrapper = arg1;
        this.trackGroupIndex = arg2;
        this.sampleQueueIndex = -1;
    }

    public void bindSampleQueue() {
        boolean v0 = this.sampleQueueIndex == -1 ? true : false;
        Assertions.checkArgument(v0);
        this.sampleQueueIndex = this.sampleStreamWrapper.bindSampleQueueToSampleStream(this.trackGroupIndex);
    }

    private boolean hasValidSampleQueueIndex() {
        boolean v0 = this.sampleQueueIndex == -1 || this.sampleQueueIndex == -3 || this.sampleQueueIndex == -2 ? false : true;
        return v0;
    }

    public boolean isReady() {
        boolean v0;
        if(this.sampleQueueIndex != -3) {
            if((this.hasValidSampleQueueIndex()) && (this.sampleStreamWrapper.isReady(this.sampleQueueIndex))) {
                goto label_12;
            }

            v0 = false;
        }
        else {
        label_12:
            v0 = true;
        }

        return v0;
    }

    public void maybeThrowError() {
        if(this.sampleQueueIndex != -2) {
            this.sampleStreamWrapper.maybeThrowError();
            return;
        }

        throw new SampleQueueMappingException(this.sampleStreamWrapper.getTrackGroups().get(this.trackGroupIndex).getFormat(0).sampleMimeType);
    }

    public int readData(FormatHolder arg3, DecoderInputBuffer arg4, boolean arg5) {
        int v3 = this.hasValidSampleQueueIndex() ? this.sampleStreamWrapper.readData(this.sampleQueueIndex, arg3, arg4, arg5) : -3;
        return v3;
    }

    public int skipData(long arg3) {
        int v3 = this.hasValidSampleQueueIndex() ? this.sampleStreamWrapper.skipData(this.sampleQueueIndex, arg3) : 0;
        return v3;
    }

    public void unbindSampleQueue() {
        int v1 = -1;
        if(this.sampleQueueIndex != v1) {
            this.sampleStreamWrapper.unbindSampleQueue(this.trackGroupIndex);
            this.sampleQueueIndex = v1;
        }
    }
}

