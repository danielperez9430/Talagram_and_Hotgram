package com.google.android.exoplayer2.text;

final class SimpleSubtitleOutputBuffer extends SubtitleOutputBuffer {
    private final SimpleSubtitleDecoder owner;

    public SimpleSubtitleOutputBuffer(SimpleSubtitleDecoder arg1) {
        super();
        this.owner = arg1;
    }

    public final void release() {
        this.owner.releaseOutputBuffer(((SubtitleOutputBuffer)this));
    }
}

