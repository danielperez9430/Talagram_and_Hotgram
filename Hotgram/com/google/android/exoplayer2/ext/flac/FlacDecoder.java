package com.google.android.exoplayer2.ext.flac;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.util.FlacStreamInfo;
import java.nio.ByteBuffer;
import java.util.List;

final class FlacDecoder extends SimpleDecoder {
    private final FlacDecoderJni decoderJni;
    private final int maxOutputBufferSize;

    public FlacDecoder(int arg1, int arg2, int arg3, List arg4) {
        FlacStreamInfo v1_1;
        super(new DecoderInputBuffer[arg1], new SimpleOutputBuffer[arg2]);
        if(arg4.size() != 1) {
            goto label_33;
        }

        this.decoderJni = new FlacDecoderJni();
        this.decoderJni.setData(ByteBuffer.wrap(arg4.get(0)));
        try {
            v1_1 = this.decoderJni.decodeMetadata();
            if(v1_1 == null) {
                goto label_25;
            }
        }
        catch(InterruptedException v1) {
            throw new IllegalStateException(((Throwable)v1));
        }

        if(arg3 != -1) {
        }
        else {
            arg3 = v1_1.maxFrameSize;
        }

        this.setInitialInputBufferSize(arg3);
        this.maxOutputBufferSize = v1_1.maxDecodedFrameSize();
        return;
    label_25:
        throw new FlacDecoderException("Metadata decoding failed");
    label_33:
        throw new FlacDecoderException("Initialization data must be of length 1");
    }

    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(1);
    }

    protected OutputBuffer createOutputBuffer() {
        return this.createOutputBuffer();
    }

    protected SimpleOutputBuffer createOutputBuffer() {
        return new SimpleOutputBuffer(((SimpleDecoder)this));
    }

    protected FlacDecoderException createUnexpectedDecodeException(Throwable arg3) {
        return new FlacDecoderException("Unexpected decode error", arg3);
    }

    protected Exception createUnexpectedDecodeException(Throwable arg1) {
        return this.createUnexpectedDecodeException(arg1);
    }

    protected FlacDecoderException decode(DecoderInputBuffer arg3, SimpleOutputBuffer arg4, boolean arg5) {
        if(arg5) {
            this.decoderJni.flush();
        }

        this.decoderJni.setData(arg3.data);
        ByteBuffer v3 = arg4.init(arg3.timeUs, this.maxOutputBufferSize);
        try {
            this.decoderJni.decodeSample(v3);
            return null;
        }
        catch(InterruptedException v3_1) {
            throw new IllegalStateException(((Throwable)v3_1));
        }
        catch(FlacFrameDecodeException v3_2) {
            return new FlacDecoderException("Frame decoding failed", ((Throwable)v3_2));
        }
    }

    protected Exception decode(DecoderInputBuffer arg1, OutputBuffer arg2, boolean arg3) {
        return this.decode(arg1, ((SimpleOutputBuffer)arg2), arg3);
    }

    public String getName() {
        return "libflac";
    }

    public void release() {
        super.release();
        this.decoderJni.release();
    }
}

