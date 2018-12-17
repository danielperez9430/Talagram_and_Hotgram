package com.google.android.exoplayer2.ext.opus;

import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.drm.DecryptionException;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

final class OpusDecoder extends SimpleDecoder {
    private static final int DECODE_ERROR = -1;
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int DRM_ERROR = -2;
    private static final int NO_ERROR = 0;
    private static final int SAMPLE_RATE = 48000;
    private final int channelCount;
    private final ExoMediaCrypto exoMediaCrypto;
    private final int headerSeekPreRollSamples;
    private final int headerSkipSamples;
    private final long nativeDecoderContext;
    private int skipSamples;

    public OpusDecoder(int arg11, int arg12, int arg13, List arg14, ExoMediaCrypto arg15) {
        int v6;
        int v7;
        super(new DecoderInputBuffer[arg11], new SimpleOutputBuffer[arg12]);
        this.exoMediaCrypto = arg15;
        if(arg15 != null) {
            goto label_119;
        }

        Object v12 = arg14.get(0);
        int v0 = 19;
        if(v12.length < v0) {
            goto label_115;
        }

        this.channelCount = v12[9] & 255;
        int v1 = 8;
        if(this.channelCount > v1) {
            goto label_105;
        }

        int v15 = OpusDecoder.readLittleEndian16(((byte[])v12), 10);
        int v8 = OpusDecoder.readLittleEndian16(((byte[])v12), 16);
        byte[] v9 = new byte[v1];
        int v3 = 2;
        if(v12[18] != 0) {
            v6 = 21;
            if(v12.length < this.channelCount + v6) {
                goto label_101;
            }

            v0 = v12[v0] & 255;
            int v2 = v12[20] & 255;
            System.arraycopy(v12, v6, v9, 0, this.channelCount);
            v6 = v0;
            v7 = v2;
        }
        else if(this.channelCount <= v3) {
            arg12 = this.channelCount == v3 ? 1 : 0;
            v9[0] = 0;
            v9[1] = 1;
            v7 = arg12;
            v6 = 1;
        }
        else {
            throw new OpusDecoderException("Invalid Header, missing stream map.");
        }

        if(arg14.size() == 3) {
            if(arg14.get(1).length == v1 && arg14.get(v3).length == v1) {
                long v11 = ByteBuffer.wrap(arg14.get(1)).order(ByteOrder.nativeOrder()).getLong();
                long v14 = ByteBuffer.wrap(arg14.get(v3)).order(ByteOrder.nativeOrder()).getLong();
                this.headerSkipSamples = OpusDecoder.nsToSamples(v11);
                arg11 = OpusDecoder.nsToSamples(v14);
                goto label_86;
            }

            throw new OpusDecoderException("Invalid Codec Delay or Seek Preroll");
        }
        else {
            this.headerSkipSamples = v15;
            arg11 = 3840;
        }

    label_86:
        this.headerSeekPreRollSamples = arg11;
        this.nativeDecoderContext = this.opusInit(48000, this.channelCount, v6, v7, v8, v9);
        if(this.nativeDecoderContext != 0) {
            this.setInitialInputBufferSize(arg13);
            return;
        }

        throw new OpusDecoderException("Failed to initialize decoder");
    label_101:
        throw new OpusDecoderException("Header size is too small.");
    label_105:
        StringBuilder v12_1 = new StringBuilder();
        v12_1.append("Invalid channel count: ");
        v12_1.append(this.channelCount);
        throw new OpusDecoderException(v12_1.toString());
    label_115:
        throw new OpusDecoderException("Header size is too small.");
    label_119:
        throw new OpusDecoderException("Opus decoder does not support secure decode.");
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

    protected OpusDecoderException createUnexpectedDecodeException(Throwable arg3) {
        return new OpusDecoderException("Unexpected decode error", arg3);
    }

    protected Exception createUnexpectedDecodeException(Throwable arg1) {
        return this.createUnexpectedDecodeException(arg1);
    }

    protected OpusDecoderException decode(DecoderInputBuffer arg20, SimpleOutputBuffer arg21, boolean arg22) {
        OpusDecoder v8;
        int v0_1;
        OpusDecoder v15 = this;
        DecoderInputBuffer v0 = arg20;
        if(arg22) {
            v15.opusReset(v15.nativeDecoderContext);
            int v1 = v0.timeUs == 0 ? v15.headerSkipSamples : v15.headerSeekPreRollSamples;
            v15.skipSamples = v1;
        }

        ByteBuffer v5 = v0.data;
        CryptoInfo v1_1 = v0.cryptoInfo;
        if(arg20.isEncrypted()) {
            v0_1 = this.opusSecureDecode(v15.nativeDecoderContext, v0.timeUs, v5, v5.limit(), arg21, 48000, v15.exoMediaCrypto, v1_1.mode, v1_1.key, v1_1.iv, v1_1.numSubSamples, v1_1.numBytesOfClearData, v1_1.numBytesOfEncryptedData);
            v8 = this;
        }
        else {
            v8 = v15;
            v0_1 = this.opusDecode(v8.nativeDecoderContext, v0.timeUs, v5, v5.limit(), arg21);
        }

        if(v0_1 < 0) {
            if(v0_1 == -2) {
                String v0_3 = "Drm error: " + v8.opusGetErrorMessage(v8.nativeDecoderContext);
                return new OpusDecoderException(v0_3, new DecryptionException(v8.opusGetErrorCode(v8.nativeDecoderContext), v0_3));
            }

            StringBuilder v2 = new StringBuilder();
            v2.append("Decode error: ");
            v2.append(v8.opusGetErrorMessage(((long)v0_1)));
            return new OpusDecoderException(v2.toString());
        }

        SimpleOutputBuffer v1_2 = arg21;
        ByteBuffer v2_1 = v1_2.data;
        v2_1.position(0);
        v2_1.limit(v0_1);
        if(v8.skipSamples > 0) {
            int v4 = v8.channelCount * 2;
            int v5_1 = v8.skipSamples * v4;
            if(v0_1 <= v5_1) {
                v8.skipSamples -= v0_1 / v4;
                v1_2.addFlag(-2147483648);
                v2_1.position(v0_1);
            }
            else {
                v8.skipSamples = 0;
                v2_1.position(v5_1);
            }
        }

        return null;
    }

    protected Exception decode(DecoderInputBuffer arg1, OutputBuffer arg2, boolean arg3) {
        return this.decode(arg1, ((SimpleOutputBuffer)arg2), arg3);
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public String getName() {
        return "libopus" + OpusLibrary.getVersion();
    }

    public int getSampleRate() {
        return 48000;
    }

    private static int nsToSamples(long arg2) {
        return ((int)(arg2 * 48000 / 1000000000));
    }

    private native void opusClose(long arg1) {
    }

    private native int opusDecode(long arg1, long arg2, ByteBuffer arg3, int arg4, SimpleOutputBuffer arg5) {
    }

    private native int opusGetErrorCode(long arg1) {
    }

    private native String opusGetErrorMessage(long arg1) {
    }

    private native long opusInit(int arg1, int arg2, int arg3, int arg4, int arg5, byte[] arg6) {
    }

    private native void opusReset(long arg1) {
    }

    private native int opusSecureDecode(long arg1, long arg2, ByteBuffer arg3, int arg4, SimpleOutputBuffer arg5, int arg6, ExoMediaCrypto arg7, int arg8, byte[] arg9, byte[] arg10, int arg11, int[] arg12, int[] arg13) {
    }

    private static int readLittleEndian16(byte[] arg1, int arg2) {
        return (arg1[arg2 + 1] & 255) << 8 | arg1[arg2] & 255;
    }

    public void release() {
        super.release();
        this.opusClose(this.nativeDecoderContext);
    }
}

