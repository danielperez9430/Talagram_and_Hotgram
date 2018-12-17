package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class SonicAudioProcessor implements AudioProcessor {
    private static final float CLOSE_THRESHOLD = 0.01f;
    public static final float MAXIMUM_PITCH = 0f;
    public static final float MAXIMUM_SPEED = 0f;
    public static final float MINIMUM_PITCH = 0.1f;
    public static final float MINIMUM_SPEED = 0.1f;
    private static final int MIN_BYTES_FOR_SPEEDUP_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    private ByteBuffer buffer;
    private int channelCount;
    private long inputBytes;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private long outputBytes;
    private int outputSampleRateHz;
    private int pendingOutputSampleRateHz;
    private float pitch;
    private int sampleRateHz;
    private ShortBuffer shortBuffer;
    private Sonic sonic;
    private float speed;

    public SonicAudioProcessor() {
        super();
        this.speed = 1f;
        this.pitch = 1f;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.buffer = SonicAudioProcessor.EMPTY_BUFFER;
        this.shortBuffer = this.buffer.asShortBuffer();
        this.outputBuffer = SonicAudioProcessor.EMPTY_BUFFER;
        this.pendingOutputSampleRateHz = -1;
    }

    public boolean configure(int arg2, int arg3, int arg4) {
        if(arg4 == 2) {
            arg4 = this.pendingOutputSampleRateHz == -1 ? arg2 : this.pendingOutputSampleRateHz;
            if(this.sampleRateHz == arg2 && this.channelCount == arg3 && this.outputSampleRateHz == arg4) {
                return 0;
            }

            this.sampleRateHz = arg2;
            this.channelCount = arg3;
            this.outputSampleRateHz = arg4;
            this.sonic = null;
            return 1;
        }

        throw new UnhandledFormatException(arg2, arg3, arg4);
    }

    public void flush() {
        if(this.isActive()) {
            if(this.sonic == null) {
                this.sonic = new Sonic(this.sampleRateHz, this.channelCount, this.speed, this.pitch, this.outputSampleRateHz);
            }
            else {
                this.sonic.flush();
            }
        }

        this.outputBuffer = SonicAudioProcessor.EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = SonicAudioProcessor.EMPTY_BUFFER;
        return v0;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputEncoding() {
        return 2;
    }

    public int getOutputSampleRateHz() {
        return this.outputSampleRateHz;
    }

    public boolean isActive() {
        boolean v0;
        if(this.sampleRateHz != -1) {
            float v1 = 1f;
            float v2 = 0.01f;
            if(Math.abs(this.speed - v1) < v2 && Math.abs(this.pitch - v1) < v2 && this.outputSampleRateHz == this.sampleRateHz) {
                goto label_18;
            }

            v0 = true;
        }
        else {
        label_18:
            v0 = false;
        }

        return v0;
    }

    public boolean isEnded() {
        boolean v0;
        if(this.inputEnded) {
            if(this.sonic != null && this.sonic.getFramesAvailable() != 0) {
                goto label_9;
            }

            v0 = true;
        }
        else {
        label_9:
            v0 = false;
        }

        return v0;
    }

    public void queueEndOfStream() {
        boolean v0 = this.sonic != null ? true : false;
        Assertions.checkState(v0);
        this.sonic.queueEndOfStream();
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer arg7) {
        boolean v0 = this.sonic != null ? true : false;
        Assertions.checkState(v0);
        if(arg7.hasRemaining()) {
            ShortBuffer v0_1 = arg7.asShortBuffer();
            int v1 = arg7.remaining();
            this.inputBytes += ((long)v1);
            this.sonic.queueInput(v0_1);
            arg7.position(arg7.position() + v1);
        }

        int v7 = this.sonic.getFramesAvailable() * this.channelCount * 2;
        if(v7 > 0) {
            if(this.buffer.capacity() < v7) {
                this.buffer = ByteBuffer.allocateDirect(v7).order(ByteOrder.nativeOrder());
                this.shortBuffer = this.buffer.asShortBuffer();
            }
            else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }

            this.sonic.getOutput(this.shortBuffer);
            this.outputBytes += ((long)v7);
            this.buffer.limit(v7);
            this.outputBuffer = this.buffer;
        }
    }

    public void reset() {
        this.speed = 1f;
        this.pitch = 1f;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.buffer = SonicAudioProcessor.EMPTY_BUFFER;
        this.shortBuffer = this.buffer.asShortBuffer();
        this.outputBuffer = SonicAudioProcessor.EMPTY_BUFFER;
        this.pendingOutputSampleRateHz = -1;
        this.sonic = null;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public long scaleDurationForSpeedup(long arg11) {
        if(this.outputBytes >= 1024) {
            return this.outputSampleRateHz == this.sampleRateHz ? Util.scaleLargeTimestamp(arg11, this.inputBytes, this.outputBytes) : Util.scaleLargeTimestamp(arg11, this.inputBytes * (((long)this.outputSampleRateHz)), this.outputBytes * (((long)this.sampleRateHz)));
        }

        double v0 = ((double)this.speed);
        double v11 = ((double)arg11);
        Double.isNaN(v0);
        Double.isNaN(v11);
        return ((long)(v0 * v11));
    }

    public void setOutputSampleRateHz(int arg1) {
        this.pendingOutputSampleRateHz = arg1;
    }

    public float setPitch(float arg3) {
        arg3 = Util.constrainValue(arg3, 0.1f, 8f);
        if(this.pitch != arg3) {
            this.pitch = arg3;
            this.sonic = null;
        }

        this.flush();
        return arg3;
    }

    public float setSpeed(float arg3) {
        arg3 = Util.constrainValue(arg3, 0.1f, 8f);
        if(this.speed != arg3) {
            this.speed = arg3;
            this.sonic = null;
        }

        this.flush();
        return arg3;
    }
}

