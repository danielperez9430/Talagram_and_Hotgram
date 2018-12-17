package com.google.android.exoplayer2.audio;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class SilenceSkippingAudioProcessor implements AudioProcessor {
    @Retention(value=RetentionPolicy.SOURCE) @interface State {
    }

    private static final long MINIMUM_SILENCE_DURATION_US = 100000;
    private static final long PADDING_SILENCE_US = 10000;
    private static final short SILENCE_THRESHOLD_LEVEL = 1024;
    private static final byte SILENCE_THRESHOLD_LEVEL_MSB = 4;
    private static final int STATE_MAYBE_SILENT = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SILENT = 2;
    private ByteBuffer buffer;
    private int bytesPerFrame;
    private int channelCount;
    private boolean enabled;
    private boolean hasOutputNoise;
    private boolean inputEnded;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferSize;
    private ByteBuffer outputBuffer;
    private byte[] paddingBuffer;
    private int paddingSize;
    private int sampleRateHz;
    private long skippedFrames;
    private int state;

    public SilenceSkippingAudioProcessor() {
        super();
        this.buffer = SilenceSkippingAudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = SilenceSkippingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.maybeSilenceBuffer = new byte[0];
        this.paddingBuffer = new byte[0];
    }

    public boolean configure(int arg2, int arg3, int arg4) {
        if(arg4 == 2) {
            if(this.sampleRateHz == arg2 && this.channelCount == arg3) {
                return 0;
            }

            this.sampleRateHz = arg2;
            this.channelCount = arg3;
            this.bytesPerFrame = arg3 * 2;
            return 1;
        }

        throw new UnhandledFormatException(arg2, arg3, arg4);
    }

    private int durationUsToFrames(long arg3) {
        return ((int)(arg3 * (((long)this.sampleRateHz)) / 1000000));
    }

    private int findNoiseLimit(ByteBuffer arg4) {
        int v0;
        for(v0 = arg4.limit() - 1; v0 >= arg4.position(); v0 += -2) {
            if(Math.abs(arg4.get(v0)) > 4) {
                return this.bytesPerFrame * (v0 / this.bytesPerFrame) + this.bytesPerFrame;
            }
        }

        return arg4.position();
    }

    private int findNoisePosition(ByteBuffer arg4) {
        int v0;
        for(v0 = arg4.position() + 1; v0 < arg4.limit(); v0 += 2) {
            if(Math.abs(arg4.get(v0)) > 4) {
                return this.bytesPerFrame * (v0 / this.bytesPerFrame);
            }
        }

        return arg4.limit();
    }

    public void flush() {
        if(this.isActive()) {
            int v0 = this.durationUsToFrames(100000) * this.bytesPerFrame;
            if(this.maybeSilenceBuffer.length != v0) {
                this.maybeSilenceBuffer = new byte[v0];
            }

            this.paddingSize = this.durationUsToFrames(10000) * this.bytesPerFrame;
            if(this.paddingBuffer.length == this.paddingSize) {
                goto label_23;
            }

            this.paddingBuffer = new byte[this.paddingSize];
        }

    label_23:
        this.state = 0;
        this.outputBuffer = SilenceSkippingAudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
        this.skippedFrames = 0;
        this.maybeSilenceBufferSize = 0;
        this.hasOutputNoise = false;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = SilenceSkippingAudioProcessor.EMPTY_BUFFER;
        return v0;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputEncoding() {
        return 2;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    public boolean isActive() {
        boolean v0 = this.sampleRateHz == -1 || !this.enabled ? false : true;
        return v0;
    }

    public boolean isEnded() {
        boolean v0 = !this.inputEnded || this.outputBuffer != SilenceSkippingAudioProcessor.EMPTY_BUFFER ? false : true;
        return v0;
    }

    private void output(ByteBuffer arg2) {
        this.prepareForOutput(arg2.remaining());
        this.buffer.put(arg2);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    private void output(byte[] arg3, int arg4) {
        this.prepareForOutput(arg4);
        this.buffer.put(arg3, 0, arg4);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    private void prepareForOutput(int arg3) {
        if(this.buffer.capacity() < arg3) {
            this.buffer = ByteBuffer.allocateDirect(arg3).order(ByteOrder.nativeOrder());
        }
        else {
            this.buffer.clear();
        }

        if(arg3 > 0) {
            this.hasOutputNoise = true;
        }
    }

    private void processMaybeSilence(ByteBuffer arg10) {
        long v5;
        int v0 = arg10.limit();
        int v1 = this.findNoisePosition(arg10);
        int v2 = v1 - arg10.position();
        int v3 = this.maybeSilenceBuffer.length - this.maybeSilenceBufferSize;
        if(v1 >= v0 || v2 >= v3) {
            v1 = Math.min(v2, v3);
            arg10.limit(arg10.position() + v1);
            arg10.get(this.maybeSilenceBuffer, this.maybeSilenceBufferSize, v1);
            this.maybeSilenceBufferSize += v1;
            if(this.maybeSilenceBufferSize == this.maybeSilenceBuffer.length) {
                v2 = 2;
                if(this.hasOutputNoise) {
                    this.output(this.maybeSilenceBuffer, this.paddingSize);
                    v5 = this.skippedFrames;
                    v1 = this.maybeSilenceBufferSize;
                    v3 = this.paddingSize * 2;
                }
                else {
                    v5 = this.skippedFrames;
                    v1 = this.maybeSilenceBufferSize;
                    v3 = this.paddingSize;
                }

                this.skippedFrames = v5 + (((long)((v1 - v3) / this.bytesPerFrame)));
                this.updatePaddingBuffer(arg10, this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
                this.maybeSilenceBufferSize = 0;
                this.state = v2;
            }

            arg10.limit(v0);
        }
        else {
            this.output(this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
            this.maybeSilenceBufferSize = 0;
            this.state = 0;
        }
    }

    private void processNoisy(ByteBuffer arg4) {
        int v0 = arg4.limit();
        arg4.limit(Math.min(v0, arg4.position() + this.maybeSilenceBuffer.length));
        int v1 = this.findNoiseLimit(arg4);
        if(v1 == arg4.position()) {
            this.state = 1;
        }
        else {
            arg4.limit(v1);
            this.output(arg4);
        }

        arg4.limit(v0);
    }

    private void processSilence(ByteBuffer arg7) {
        int v0 = arg7.limit();
        int v1 = this.findNoisePosition(arg7);
        arg7.limit(v1);
        this.skippedFrames += ((long)(arg7.remaining() / this.bytesPerFrame));
        this.updatePaddingBuffer(arg7, this.paddingBuffer, this.paddingSize);
        if(v1 < v0) {
            this.output(this.paddingBuffer, this.paddingSize);
            this.state = 0;
            arg7.limit(v0);
        }
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
        if(this.maybeSilenceBufferSize > 0) {
            this.output(this.maybeSilenceBuffer, this.maybeSilenceBufferSize);
        }

        if(!this.hasOutputNoise) {
            this.skippedFrames += ((long)(this.paddingSize / this.bytesPerFrame));
        }
    }

    public void queueInput(ByteBuffer arg2) {
        while(arg2.hasRemaining()) {
            if(this.outputBuffer.hasRemaining()) {
                return;
            }

            switch(this.state) {
                case 0: {
                    goto label_14;
                }
                case 1: {
                    goto label_12;
                }
                case 2: {
                    goto label_10;
                }
            }

            throw new IllegalStateException();
        label_10:
            this.processSilence(arg2);
            continue;
        label_12:
            this.processMaybeSilence(arg2);
            continue;
        label_14:
            this.processNoisy(arg2);
        }
    }

    public void reset() {
        this.enabled = false;
        this.flush();
        this.buffer = SilenceSkippingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.paddingSize = 0;
        this.maybeSilenceBuffer = new byte[0];
        this.paddingBuffer = new byte[0];
    }

    public void setEnabled(boolean arg1) {
        this.enabled = arg1;
        this.flush();
    }

    private void updatePaddingBuffer(ByteBuffer arg5, byte[] arg6, int arg7) {
        int v0 = Math.min(arg5.remaining(), this.paddingSize);
        int v1 = this.paddingSize - v0;
        System.arraycopy(arg6, arg7 - v1, this.paddingBuffer, 0, v1);
        arg5.position(arg5.limit() - v0);
        arg5.get(this.paddingBuffer, v1, v0);
    }
}

