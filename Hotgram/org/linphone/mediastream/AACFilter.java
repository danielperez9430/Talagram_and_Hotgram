package org.linphone.mediastream;

import android.annotation.TargetApi;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import java.nio.ByteBuffer;

@TargetApi(value=16) public class AACFilter {
    int bitrate;
    int channelCount;
    MediaCodec decoder;
    MediaCodec$BufferInfo decoderBufferInfo;
    ByteBuffer[] decoderInputBuffers;
    ByteBuffer[] decoderOutputBuffers;
    MediaCodec encoder;
    MediaCodec$BufferInfo encoderBufferInfo;
    ByteBuffer[] encoderInputBuffers;
    ByteBuffer[] encoderOutputBuffers;
    boolean initialized;
    int sampleRate;
    private static AACFilter singleton;

    public AACFilter() {
        super();
        this.initialized = false;
    }

    private static int dequeueData(MediaCodec arg7, ByteBuffer[] arg8, MediaCodec$BufferInfo arg9, byte[] arg10) {
        int v1;
        for(v1 = 0; v1 < 1; ++v1) {
            int v3 = arg7.dequeueOutputBuffer(arg9, 100);
            if(v3 >= 0) {
                if(arg10.length < arg9.size) {
                    Object[] v1_1 = new Object[1];
                    v1_1[0] = "array is too small " + arg10.length + " < " + arg9.size;
                    Log.e(v1_1);
                }

                if(arg9.flags == 2) {
                    Log.i(new Object[]{"JUST READ MediaCodec.BUFFER_FLAG_CODEC_CONFIG buffer"});
                }

                arg8[v3].get(arg10, 0, arg9.size);
                arg8[v3].position(0);
                arg7.releaseOutputBuffer(v3, false);
                return arg9.size;
            }

            int v4_1 = -3;
            if(v3 == v4_1) {
                return v4_1;
            }

            if(v3 == -2) {
                Log.i(new Object[]{"MediaCodec.INFO_OUTPUT_FORMAT_CHANGED"});
                Object[] v3_1 = new Object[1];
                v3_1[0] = "CHANNEL_COUNT: " + arg7.getOutputFormat().getInteger("channel-count");
                Log.i(v3_1);
                Object[] v2 = new Object[1];
                v2[0] = "SAMPLE_RATE: " + arg7.getOutputFormat().getInteger("sample-rate");
                Log.i(v2);
            }
        }

        return 0;
    }

    public static AACFilter instance() {
        if(AACFilter.singleton == null) {
            AACFilter.singleton = new AACFilter();
        }

        return AACFilter.singleton;
    }

    public boolean postprocess() {
        if(this.initialized) {
            this.encoder.flush();
            Log.i(new Object[]{"Stopping encoder"});
            this.encoder.stop();
            Log.i(new Object[]{"Stopping decoder"});
            this.decoder.flush();
            this.decoder.stop();
            Log.i(new Object[]{"Release encoder"});
            this.encoder.release();
            Log.i(new Object[]{"Release decoder"});
            this.decoder.release();
            this.encoder = null;
            this.decoder = null;
            this.initialized = false;
        }

        return 1;
    }

    public boolean preprocess(int arg10, int arg11, int arg12, boolean arg13) {
        byte[] v2_1;
        Surface v4;
        if(this.initialized) {
            return 1;
        }

        this.sampleRate = arg10;
        this.channelCount = arg11;
        this.bitrate = arg12;
        int v13 = 2;
        try {
            MediaFormat v2 = MediaFormat.createAudioFormat("audio/mp4a-latm", arg10, arg11);
            v2.setInteger("aac-profile", 39);
            v2.setInteger("bitrate", arg12);
            this.encoder = MediaCodec.createByCodecName("OMX.google.aac.encoder");
            v4 = null;
            this.encoder.configure(v2, v4, ((MediaCrypto)v4), 1);
            this.encoder.start();
            this.encoderBufferInfo = new MediaCodec$BufferInfo();
            v2_1 = ((byte[])v4);
            int v3;
            for(v3 = 0; v2_1 == null; ++v3) {
                if(v3 >= 1000) {
                    break;
                }

                int v5 = this.encoder.dequeueOutputBuffer(this.encoderBufferInfo, 0);
                if(v5 >= 0 && this.encoderBufferInfo.flags == v13) {
                    v2_1 = new byte[this.encoderBufferInfo.size];
                    this.encoder.getOutputBuffers()[v5].get(v2_1, 0, this.encoderBufferInfo.size);
                    this.encoder.getOutputBuffers()[v5].position(0);
                    this.encoder.releaseOutputBuffer(v5, false);
                }
            }

            this.encoderOutputBuffers = this.encoder.getOutputBuffers();
            this.encoderInputBuffers = this.encoder.getInputBuffers();
            if(v2_1 == null) {
                Log.e(new Object[]{"Sigh, failed to read asc from encoder"});
            }
        }
        catch(Exception v10) {
            goto label_114;
        }

        Log.i(new Object[]{"AAC encoder initialized"});
        if(v2_1 != null) {
            try {
                MediaFormat v10_1 = MediaFormat.createAudioFormat("audio/mp4a-latm", 0, 0);
                v10_1.setByteBuffer("csd-0", ByteBuffer.wrap(v2_1));
                goto label_85;
            label_81:
                v10_1 = MediaFormat.createAudioFormat("audio/mp4a-latm", arg10, arg11);
                v10_1.setInteger("bitrate", arg12);
            label_85:
                this.decoder = MediaCodec.createByCodecName("OMX.google.aac.decoder");
                this.decoder.configure(v10_1, v4, ((MediaCrypto)v4), 0);
                this.decoder.start();
                this.decoderOutputBuffers = this.decoder.getOutputBuffers();
                this.decoderInputBuffers = this.decoder.getInputBuffers();
                this.decoderBufferInfo = new MediaCodec$BufferInfo();
                goto label_101;
            }
            catch(Exception v10) {
                goto label_107;
            }
        }
        else {
            goto label_81;
        }

        goto label_85;
    label_101:
        Log.i(new Object[]{"AAC decoder initialized"});
        this.initialized = true;
        return 1;
    label_107:
        Object[] v11 = new Object[v13];
        v11[0] = v10;
        v11[1] = "Unable to create AAC Decoder";
        goto label_111;
    label_114:
        v11 = new Object[v13];
        v11[0] = v10;
        v11[1] = "Unable to create AAC Encoder";
    label_111:
        Log.e(v11);
        return 0;
    }

    public int pullFromDecoder(byte[] arg4) {
        int v0;
        try {
            v0 = AACFilter.dequeueData(this.decoder, this.decoderOutputBuffers, this.decoderBufferInfo, arg4);
            if(v0 != -3) {
                return v0;
            }

            this.decoderOutputBuffers = this.decoder.getOutputBuffers();
            return this.pullFromDecoder(arg4);
        }
        catch(Exception ) {
            return 0;
        }

        return v0;
    }

    public int pullFromEncoder(byte[] arg4) {
        int v0;
        try {
            v0 = AACFilter.dequeueData(this.encoder, this.encoderOutputBuffers, this.encoderBufferInfo, arg4);
            if(v0 != -3) {
                return v0;
            }

            this.encoderOutputBuffers = this.encoder.getOutputBuffers();
            return this.pullFromDecoder(arg4);
        }
        catch(Exception ) {
            return 0;
        }

        return v0;
    }

    public boolean pushToDecoder(byte[] arg4, int arg5) {
        if(arg4 != null) {
            try {
                if(this.decoder == null) {
                    return 0;
                }

                return AACFilter.queueData(this.decoder, this.decoderInputBuffers, arg4, arg5);
            }
            catch(Exception v4) {
                Log.e(new Object[]{v4, "Push to decoder failed"});
            }
        }

        return 0;
    }

    public boolean pushToEncoder(byte[] arg4, int arg5) {
        if(arg4 != null) {
            try {
                if(this.encoder == null) {
                    return 0;
                }

                return AACFilter.queueData(this.encoder, this.encoderInputBuffers, arg4, arg5);
            }
            catch(Exception v4) {
                Log.e(new Object[]{v4, "Push to encoder failed"});
            }
        }

        return 0;
    }

    private static boolean queueData(MediaCodec arg9, ByteBuffer[] arg10, byte[] arg11, int arg12) {
        int v3 = arg9.dequeueInputBuffer(0);
        if(v3 >= 0) {
            arg10[v3].position(0);
            arg10[v3].put(arg11, 0, arg12);
            arg9.queueInputBuffer(v3, 0, arg12, 0, 0);
            return 1;
        }

        return 0;
    }
}

