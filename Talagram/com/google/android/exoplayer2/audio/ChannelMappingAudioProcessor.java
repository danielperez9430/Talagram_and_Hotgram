package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

final class ChannelMappingAudioProcessor implements AudioProcessor {
    private boolean active;
    private ByteBuffer buffer;
    private int channelCount;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int[] outputChannels;
    private int[] pendingOutputChannels;
    private int sampleRateHz;

    public ChannelMappingAudioProcessor() {
        super();
        this.buffer = ChannelMappingAudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = ChannelMappingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
    }

    public boolean configure(int arg6, int arg7, int arg8) {
        int v0 = Arrays.equals(this.pendingOutputChannels, this.outputChannels) ^ 1;
        this.outputChannels = this.pendingOutputChannels;
        if(this.outputChannels == null) {
            this.active = false;
            return ((boolean)v0);
        }

        if(arg8 != 2) {
            goto label_49;
        }

        if(v0 == 0 && this.sampleRateHz == arg6 && this.channelCount == arg7) {
            return 0;
        }

        this.sampleRateHz = arg6;
        this.channelCount = arg7;
        boolean v0_1 = arg7 != this.outputChannels.length ? true : false;
        this.active = v0_1;
        for(v0 = 0; true; ++v0) {
            if(v0 >= this.outputChannels.length) {
                return 1;
            }

            int v2 = this.outputChannels[v0];
            if(v2 >= arg7) {
                break;
            }

            boolean v4 = this.active;
            v2 = v2 != v0 ? 1 : 0;
            this.active = v2 | (((int)v4));
        }

        throw new UnhandledFormatException(arg6, arg7, arg8);
        return 1;
    label_49:
        throw new UnhandledFormatException(arg6, arg7, arg8);
    }

    public void flush() {
        this.outputBuffer = ChannelMappingAudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = ChannelMappingAudioProcessor.EMPTY_BUFFER;
        return v0;
    }

    public int getOutputChannelCount() {
        int v0 = this.outputChannels == null ? this.channelCount : this.outputChannels.length;
        return v0;
    }

    public int getOutputEncoding() {
        return 2;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isEnded() {
        boolean v0 = !this.inputEnded || this.outputBuffer != ChannelMappingAudioProcessor.EMPTY_BUFFER ? false : true;
        return v0;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer arg9) {
        boolean v0 = this.outputChannels != null ? true : false;
        Assertions.checkState(v0);
        int v0_1 = arg9.position();
        int v2 = arg9.limit();
        int v3 = (v2 - v0_1) / (this.channelCount * 2) * this.outputChannels.length * 2;
        if(this.buffer.capacity() < v3) {
            this.buffer = ByteBuffer.allocateDirect(v3).order(ByteOrder.nativeOrder());
        }
        else {
            this.buffer.clear();
        }

        while(v0_1 < v2) {
            int[] v3_1 = this.outputChannels;
            int v4 = v3_1.length;
            int v5;
            for(v5 = 0; v5 < v4; ++v5) {
                this.buffer.putShort(arg9.getShort(v3_1[v5] * 2 + v0_1));
            }

            v0_1 += this.channelCount * 2;
        }

        arg9.position(v2);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    public void reset() {
        this.flush();
        this.buffer = ChannelMappingAudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputChannels = null;
        this.pendingOutputChannels = null;
        this.active = false;
    }

    public void setChannelMap(int[] arg1) {
        this.pendingOutputChannels = arg1;
    }
}

