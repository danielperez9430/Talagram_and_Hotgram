package com.google.android.exoplayer2.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class ResamplingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer;
    private int channelCount;
    private int encoding;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int sampleRateHz;

    public ResamplingAudioProcessor() {
        super();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = 0;
        this.buffer = ResamplingAudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = ResamplingAudioProcessor.EMPTY_BUFFER;
    }

    public boolean configure(int arg2, int arg3, int arg4) {
        if(arg4 != 3 && arg4 != 2 && arg4 != -2147483648) {
            if(arg4 == 1073741824) {
            }
            else {
                throw new UnhandledFormatException(arg2, arg3, arg4);
            }
        }

        if(this.sampleRateHz == arg2 && this.channelCount == arg3 && this.encoding == arg4) {
            return 0;
        }

        this.sampleRateHz = arg2;
        this.channelCount = arg3;
        this.encoding = arg4;
        return 1;
    }

    public void flush() {
        this.outputBuffer = ResamplingAudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    public ByteBuffer getOutput() {
        ByteBuffer v0 = this.outputBuffer;
        this.outputBuffer = ResamplingAudioProcessor.EMPTY_BUFFER;
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

    public boolean isActive() {
        boolean v0 = this.encoding == 0 || this.encoding == 2 ? false : true;
        return v0;
    }

    public boolean isEnded() {
        boolean v0 = !this.inputEnded || this.outputBuffer != ResamplingAudioProcessor.EMPTY_BUFFER ? false : true;
        return v0;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public void queueInput(ByteBuffer arg8) {
        int v0 = arg8.position();
        int v1 = arg8.limit();
        int v2 = v1 - v0;
        int v3 = this.encoding;
        int v4 = 1073741824;
        int v5 = -2147483648;
        int v6 = 3;
        if(v3 == v5) {
            v2 /= v6;
        label_16:
            v2 *= 2;
        }
        else if(v3 == v6) {
            goto label_16;
        }
        else if(v3 == v4) {
            v2 /= 2;
        }
        else {
            throw new IllegalStateException();
        }

        if(this.buffer.capacity() < v2) {
            this.buffer = ByteBuffer.allocateDirect(v2).order(ByteOrder.nativeOrder());
        }
        else {
            this.buffer.clear();
        }

        v2 = this.encoding;
        if(v2 != v5) {
            if(v2 != v6) {
                if(v2 == v4) {
                    while(true) {
                        if(v0 >= v1) {
                            goto label_68;
                        }

                        this.buffer.put(arg8.get(v0 + 2));
                        this.buffer.put(arg8.get(v0 + 3));
                        v0 += 4;
                    }
                }

                throw new IllegalStateException();
            }

            while(v0 < v1) {
                this.buffer.put(0);
                this.buffer.put(((byte)((arg8.get(v0) & 255) - 128)));
                ++v0;
            }
        }
        else {
            while(v0 < v1) {
                this.buffer.put(arg8.get(v0 + 1));
                this.buffer.put(arg8.get(v0 + 2));
                v0 += 3;
            }
        }

    label_68:
        arg8.position(arg8.limit());
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    public void reset() {
        this.flush();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = 0;
        this.buffer = ResamplingAudioProcessor.EMPTY_BUFFER;
    }
}

