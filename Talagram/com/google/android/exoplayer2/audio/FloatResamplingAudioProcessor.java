package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class FloatResamplingAudioProcessor implements AudioProcessor {
    private static final int FLOAT_NAN_AS_INT = 0;
    private static final double PCM_32_BIT_INT_TO_PCM_32_BIT_FLOAT_FACTOR = 0;
    private ByteBuffer buffer;
    private int channelCount;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int sampleRateHz;
    private int sourceEncoding;

    static {
        FloatResamplingAudioProcessor.FLOAT_NAN_AS_INT = Float.floatToIntBits(NaNf);
    }

    public FloatResamplingAudioProcessor() {
        super();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.sourceEncoding = 0;
        this.buffer = FloatResamplingAudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = FloatResamplingAudioProcessor.EMPTY_BUFFER;
    }

    public boolean configure(int arg2, int arg3, int arg4) {
        if(Util.isEncodingHighResolutionIntegerPcm(arg4)) {
            if(this.sampleRateHz == arg2 && this.channelCount == arg3 && this.sourceEncoding == arg4) {
                return 0;
            }

            this.sampleRateHz = arg2;
            this.channelCount = arg3;
            this.sourceEncoding = arg4;
            return 1;
        }

        throw new UnhandledFormatException(arg2, arg3, arg4);
    }

    public void flush() {
        this.outputBuffer = FloatResamplingAudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = FloatResamplingAudioProcessor.EMPTY_BUFFER;
        return v0;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputEncoding() {
        return 4;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public boolean isActive() {
        return Util.isEncodingHighResolutionIntegerPcm(this.sourceEncoding);
    }

    public boolean isEnded() {
        boolean v0 = !this.inputEnded || this.outputBuffer != FloatResamplingAudioProcessor.EMPTY_BUFFER ? false : true;
        return v0;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer arg6) {
        int v0 = this.sourceEncoding == 1073741824 ? 1 : 0;
        int v1 = arg6.position();
        int v2 = arg6.limit();
        int v3 = v2 - v1;
        if(v0 != 0) {
        }
        else {
            v3 = v3 / 3 * 4;
        }

        if(this.buffer.capacity() < v3) {
            this.buffer = ByteBuffer.allocateDirect(v3).order(ByteOrder.nativeOrder());
        }
        else {
            this.buffer.clear();
        }

        if(v0 != 0) {
            while(v1 < v2) {
                FloatResamplingAudioProcessor.writePcm32BitFloat(arg6.get(v1) & 255 | (arg6.get(v1 + 1) & 255) << 8 | (arg6.get(v1 + 2) & 255) << 16 | (arg6.get(v1 + 3) & 255) << 24, this.buffer);
                v1 += 4;
            }
        }
        else {
            while(v1 < v2) {
                FloatResamplingAudioProcessor.writePcm32BitFloat((arg6.get(v1) & 255) << 8 | (arg6.get(v1 + 1) & 255) << 16 | (arg6.get(v1 + 2) & 255) << 24, this.buffer);
                v1 += 3;
            }
        }

        arg6.position(arg6.limit());
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    public void reset() {
        this.flush();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.sourceEncoding = 0;
        this.buffer = FloatResamplingAudioProcessor.EMPTY_BUFFER;
    }

    private static void writePcm32BitFloat(int arg4, ByteBuffer arg5) {
        double v0 = ((double)arg4);
        Double.isNaN(v0);
        arg4 = Float.floatToIntBits(((float)(v0 * 0)));
        if(arg4 == FloatResamplingAudioProcessor.FLOAT_NAN_AS_INT) {
            arg4 = Float.floatToIntBits(0f);
        }

        arg5.putInt(arg4);
    }
}

