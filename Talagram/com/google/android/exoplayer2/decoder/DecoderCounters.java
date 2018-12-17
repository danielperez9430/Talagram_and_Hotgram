package com.google.android.exoplayer2.decoder;

public final class DecoderCounters {
    public int decoderInitCount;
    public int decoderReleaseCount;
    public int droppedBufferCount;
    public int droppedToKeyframeCount;
    public int inputBufferCount;
    public int maxConsecutiveDroppedBufferCount;
    public int renderedOutputBufferCount;
    public int skippedInputBufferCount;
    public int skippedOutputBufferCount;

    public DecoderCounters() {
        super();
    }

    public void ensureUpdated() {
        __monitor_enter(this);
        __monitor_exit(this);
    }

    public void merge(DecoderCounters arg3) {
        this.decoderInitCount += arg3.decoderInitCount;
        this.decoderReleaseCount += arg3.decoderReleaseCount;
        this.inputBufferCount += arg3.inputBufferCount;
        this.skippedInputBufferCount += arg3.skippedInputBufferCount;
        this.renderedOutputBufferCount += arg3.renderedOutputBufferCount;
        this.skippedOutputBufferCount += arg3.skippedOutputBufferCount;
        this.droppedBufferCount += arg3.droppedBufferCount;
        this.maxConsecutiveDroppedBufferCount = Math.max(this.maxConsecutiveDroppedBufferCount, arg3.maxConsecutiveDroppedBufferCount);
        this.droppedToKeyframeCount += arg3.droppedToKeyframeCount;
    }
}

