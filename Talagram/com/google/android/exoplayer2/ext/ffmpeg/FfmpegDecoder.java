package com.google.android.exoplayer2.ext.ffmpeg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.List;

final class FfmpegDecoder extends SimpleDecoder {
    private static final int OUTPUT_BUFFER_SIZE_16BIT = 65536;
    private static final int OUTPUT_BUFFER_SIZE_32BIT = 131072;
    private volatile int channelCount;
    private final String codecName;
    private final int encoding;
    private final byte[] extraData;
    private boolean hasOutputFormat;
    private long nativeContext;
    private final int outputBufferSize;
    private volatile int sampleRate;

    public FfmpegDecoder(int arg7, int arg8, int arg9, Format arg10, boolean arg11) {
        super(new DecoderInputBuffer[arg7], new SimpleOutputBuffer[arg8]);
        Assertions.checkNotNull(arg10.sampleMimeType);
        this.codecName = Assertions.checkNotNull(FfmpegLibrary.getCodecName(arg10.sampleMimeType, arg10.pcmEncoding));
        this.extraData = FfmpegDecoder.getExtraData(arg10.sampleMimeType, arg10.initializationData);
        arg7 = arg11 ? 4 : 2;
        this.encoding = arg7;
        arg7 = arg11 ? 131072 : 65536;
        this.outputBufferSize = arg7;
        this.nativeContext = this.ffmpegInitialize(this.codecName, this.extraData, arg11, arg10.sampleRate, arg10.channelCount);
        if(this.nativeContext != 0) {
            this.setInitialInputBufferSize(arg9);
            return;
        }

        throw new FfmpegDecoderException("Initialization failed.");
    }

    protected DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(2);
    }

    protected OutputBuffer createOutputBuffer() {
        return this.createOutputBuffer();
    }

    protected SimpleOutputBuffer createOutputBuffer() {
        return new SimpleOutputBuffer(((SimpleDecoder)this));
    }

    protected FfmpegDecoderException createUnexpectedDecodeException(Throwable arg3) {
        return new FfmpegDecoderException("Unexpected decode error", arg3);
    }

    protected Exception createUnexpectedDecodeException(Throwable arg1) {
        return this.createUnexpectedDecodeException(arg1);
    }

    protected FfmpegDecoderException decode(DecoderInputBuffer arg8, SimpleOutputBuffer arg9, boolean arg10) {
        if(arg10) {
            this.nativeContext = this.ffmpegReset(this.nativeContext, this.extraData);
            if(this.nativeContext == 0) {
                return new FfmpegDecoderException("Error resetting (see logcat).");
            }
        }

        int v8 = this.ffmpegDecode(this.nativeContext, arg8.data, arg8.data.limit(), arg9.init(arg8.timeUs, this.outputBufferSize), this.outputBufferSize);
        if(v8 < 0) {
            StringBuilder v10 = new StringBuilder();
            v10.append("Error decoding (see logcat). Code: ");
            v10.append(v8);
            return new FfmpegDecoderException(v10.toString());
        }

        if(!this.hasOutputFormat) {
            this.channelCount = this.ffmpegGetChannelCount(this.nativeContext);
            this.sampleRate = this.ffmpegGetSampleRate(this.nativeContext);
            if(this.sampleRate == 0 && ("alac".equals(this.codecName))) {
                Assertions.checkNotNull(this.extraData);
                ParsableByteArray v10_1 = new ParsableByteArray(this.extraData);
                v10_1.setPosition(this.extraData.length - 4);
                this.sampleRate = v10_1.readUnsignedIntToInt();
            }

            this.hasOutputFormat = true;
        }

        arg9.data.position(0);
        arg9.data.limit(v8);
        return null;
    }

    protected Exception decode(DecoderInputBuffer arg1, OutputBuffer arg2, boolean arg3) {
        return this.decode(arg1, ((SimpleOutputBuffer)arg2), arg3);
    }

    private native int ffmpegDecode(long arg1, ByteBuffer arg2, int arg3, ByteBuffer arg4, int arg5) {
    }

    private native int ffmpegGetChannelCount(long arg1) {
    }

    private native int ffmpegGetSampleRate(long arg1) {
    }

    private native long ffmpegInitialize(String arg1, byte[] arg2, boolean arg3, int arg4, int arg5) {
    }

    private native void ffmpegRelease(long arg1) {
    }

    private native long ffmpegReset(long arg1, byte[] arg2) {
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getEncoding() {
        return this.encoding;
    }

    private static byte[] getExtraData(String arg6, List arg7) {
        int v6;
        int v0 = arg6.hashCode();
        int v2 = 3;
        int v3 = 2;
        if(v0 != -1003765268) {
            if(v0 != -53558318) {
                if(v0 != 1504470054) {
                    if(v0 != 1504891608) {
                        goto label_34;
                    }
                    else if(arg6.equals("audio/opus")) {
                        v6 = 2;
                    }
                    else {
                        goto label_34;
                    }
                }
                else if(arg6.equals("audio/alac")) {
                    v6 = 1;
                }
                else {
                    goto label_34;
                }
            }
            else if(arg6.equals("audio/mp4a-latm")) {
                v6 = 0;
            }
            else {
                goto label_34;
            }
        }
        else if(arg6.equals("audio/vorbis")) {
            v6 = 3;
        }
        else {
        label_34:
            v6 = -1;
        }

        switch(v6) {
            case 0: 
            case 1: 
            case 2: {
                goto label_78;
            }
            case 3: {
                goto label_38;
            }
        }

        return null;
    label_38:
        Object v6_1 = arg7.get(0);
        Object v7 = arg7.get(1);
        byte[] v0_1 = new byte[v6_1.length + v7.length + 6];
        v0_1[0] = ((byte)(v6_1.length >> 8));
        v0_1[1] = ((byte)(v6_1.length & 255));
        System.arraycopy(v6_1, 0, v0_1, v3, v6_1.length);
        v0_1[v6_1.length + v3] = 0;
        v0_1[v6_1.length + v2] = 0;
        v0_1[v6_1.length + 4] = ((byte)(v7.length >> 8));
        v0_1[v6_1.length + 5] = ((byte)(v7.length & 255));
        System.arraycopy(v7, 0, v0_1, v6_1.length + 6, v7.length);
        return v0_1;
    label_78:
        return arg7.get(0);
    }

    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void release() {
        super.release();
        this.ffmpegRelease(this.nativeContext);
        this.nativeContext = 0;
    }
}

