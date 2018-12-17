package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;

public abstract class SimpleSubtitleDecoder extends SimpleDecoder implements SubtitleDecoder {
    private final String name;

    protected SimpleSubtitleDecoder(String arg3) {
        super(new SubtitleInputBuffer[2], new SubtitleOutputBuffer[2]);
        this.name = arg3;
        this.setInitialInputBufferSize(1024);
    }

    protected DecoderInputBuffer createInputBuffer() {
        return this.createInputBuffer();
    }

    protected final SubtitleInputBuffer createInputBuffer() {
        return new SubtitleInputBuffer();
    }

    protected OutputBuffer createOutputBuffer() {
        return this.createOutputBuffer();
    }

    protected final SubtitleOutputBuffer createOutputBuffer() {
        return new SimpleSubtitleOutputBuffer(this);
    }

    protected final SubtitleDecoderException createUnexpectedDecodeException(Throwable arg3) {
        return new SubtitleDecoderException("Unexpected decode error", arg3);
    }

    protected Exception createUnexpectedDecodeException(Throwable arg1) {
        return this.createUnexpectedDecodeException(arg1);
    }

    protected abstract Subtitle decode(byte[] arg1, int arg2, boolean arg3);

    protected final SubtitleDecoderException decode(SubtitleInputBuffer arg9, SubtitleOutputBuffer arg10, boolean arg11) {
        try {
            arg10.setContent(arg9.timeUs, this.decode(arg9.data.array(), arg9.data.limit(), arg11), arg9.subsampleOffsetUs);
            arg10.clearFlag(-2147483648);
            return null;
        }
        catch(SubtitleDecoderException v9) {
            return v9;
        }
    }

    protected Exception decode(DecoderInputBuffer arg1, OutputBuffer arg2, boolean arg3) {
        return this.decode(((SubtitleInputBuffer)arg1), ((SubtitleOutputBuffer)arg2), arg3);
    }

    public final String getName() {
        return this.name;
    }

    protected void releaseOutputBuffer(OutputBuffer arg1) {
        this.releaseOutputBuffer(((SubtitleOutputBuffer)arg1));
    }

    protected final void releaseOutputBuffer(SubtitleOutputBuffer arg1) {
        super.releaseOutputBuffer(((OutputBuffer)arg1));
    }

    public void setPositionUs(long arg1) {
    }
}

