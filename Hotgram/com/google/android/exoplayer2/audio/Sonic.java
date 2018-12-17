package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int arg1, int arg2, float arg3, float arg4, int arg5) {
        super();
        this.inputSampleRateHz = arg1;
        this.channelCount = arg2;
        this.speed = arg3;
        this.pitch = arg4;
        this.rate = (((float)arg1)) / (((float)arg5));
        this.minPeriod = arg1 / 400;
        this.maxPeriod = arg1 / 65;
        this.maxRequiredFrameCount = this.maxPeriod * 2;
        this.downSampleBuffer = new short[this.maxRequiredFrameCount];
        this.inputBuffer = new short[this.maxRequiredFrameCount * arg2];
        this.outputBuffer = new short[this.maxRequiredFrameCount * arg2];
        this.pitchBuffer = new short[this.maxRequiredFrameCount * arg2];
    }

    private void adjustRate(float arg9, int arg10) {
        int v1;
        if(this.outputFrameCount == arg10) {
            return;
        }

        int v9 = ((int)((((float)this.inputSampleRateHz)) / arg9));
        int v0;
        for(v0 = this.inputSampleRateHz; true; v0 /= 2) {
            v1 = 16384;
            if(v9 <= v1) {
                if(v0 > v1) {
                }
                else {
                    this.moveNewSamplesToPitchBuffer(arg10);
                    v1 = 0;
                    break;
                }
            }

            v9 /= 2;
        }

        while(true) {
            boolean v3 = true;
            if(v1 >= this.pitchFrameCount - 1) {
                break;
            }

            while((this.oldRatePosition + 1) * v9 > this.newRatePosition * v0) {
                this.outputBuffer = this.ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                int v2;
                for(v2 = 0; v2 < this.channelCount; ++v2) {
                    this.outputBuffer[this.outputFrameCount * this.channelCount + v2] = this.interpolate(this.pitchBuffer, this.channelCount * v1 + v2, v0, v9);
                }

                ++this.newRatePosition;
                ++this.outputFrameCount;
            }

            ++this.oldRatePosition;
            if(this.oldRatePosition == v0) {
                this.oldRatePosition = 0;
                if(this.newRatePosition == v9) {
                }
                else {
                    v3 = false;
                }

                Assertions.checkState(v3);
                this.newRatePosition = 0;
            }

            ++v1;
        }

        this.removePitchFrames(this.pitchFrameCount - 1);
    }

    private void changeSpeed(float arg9) {
        int v2;
        if(this.inputFrameCount < this.maxRequiredFrameCount) {
            return;
        }

        int v0 = this.inputFrameCount;
        int v1 = 0;
        do {
            if(this.remainingInputToCopyFrameCount > 0) {
                v2 = this.copyInputToOutput(v1);
            }
            else {
                v2 = this.findPitchPeriod(this.inputBuffer, v1);
                if((((double)arg9)) > 1) {
                    v2 += this.skipPitchPeriod(this.inputBuffer, v1, arg9, v2);
                }
                else {
                    v2 = this.insertPitchPeriod(this.inputBuffer, v1, arg9, v2);
                }
            }

            v1 += v2;
        }
        while(this.maxRequiredFrameCount + v1 <= v0);

        this.removeProcessedInputFrames(v1);
    }

    private int copyInputToOutput(int arg3) {
        int v0 = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        this.copyToOutput(this.inputBuffer, arg3, v0);
        this.remainingInputToCopyFrameCount -= v0;
        return v0;
    }

    private void copyToOutput(short[] arg4, int arg5, int arg6) {
        this.outputBuffer = this.ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, arg6);
        System.arraycopy(arg4, arg5 * this.channelCount, this.outputBuffer, this.outputFrameCount * this.channelCount, this.channelCount * arg6);
        this.outputFrameCount += arg6;
    }

    private void downSampleInput(short[] arg7, int arg8, int arg9) {
        int v0 = this.maxRequiredFrameCount / arg9;
        int v1 = this.channelCount * arg9;
        arg8 *= this.channelCount;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            int v3 = 0;
            int v4 = 0;
            while(v3 < v1) {
                v4 += arg7[v2 * v1 + arg8 + v3];
                ++v3;
            }

            this.downSampleBuffer[v2] = ((short)(v4 / v1));
        }
    }

    private short[] ensureSpaceForAdditionalFrames(short[] arg3, int arg4, int arg5) {
        int v0 = arg3.length / this.channelCount;
        if(arg4 + arg5 <= v0) {
            return arg3;
        }

        return Arrays.copyOf(arg3, (v0 * 3 / 2 + arg5) * this.channelCount);
    }

    private int findPitchPeriod(short[] arg7, int arg8) {
        int v7;
        int v1 = 4000;
        int v0 = this.inputSampleRateHz > v1 ? this.inputSampleRateHz / v1 : 1;
        if(this.channelCount != 1 || v0 != 1) {
            this.downSampleInput(arg7, arg8, v0);
            v1 = this.findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / v0, this.maxPeriod / v0);
            if(v0 != 1) {
                v1 *= v0;
                v0 *= 4;
                int v3 = v1 - v0;
                v1 += v0;
                if(v3 < this.minPeriod) {
                    v3 = this.minPeriod;
                }

                if(v1 > this.maxPeriod) {
                    v1 = this.maxPeriod;
                }

                if(this.channelCount == 1) {
                    v7 = this.findPitchPeriodInRange(arg7, arg8, v3, v1);
                    goto label_43;
                }

                this.downSampleInput(arg7, arg8, 1);
                v7 = this.findPitchPeriodInRange(this.downSampleBuffer, 0, v3, v1);
            }
            else {
                v7 = v1;
            }
        }
        else {
            v7 = this.findPitchPeriodInRange(arg7, arg8, this.minPeriod, this.maxPeriod);
        }

    label_43:
        arg8 = this.previousPeriodBetter(this.minDiff, this.maxDiff) ? this.prevPeriod : v7;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = v7;
        return arg8;
    }

    private int findPitchPeriodInRange(short[] arg10, int arg11, int arg12, int arg13) {
        arg11 *= this.channelCount;
        int v0 = 1;
        int v2 = 0;
        int v3 = 0;
        int v4 = 255;
        while(arg12 <= arg13) {
            int v5 = 0;
            int v6 = 0;
            while(v5 < arg12) {
                v6 += Math.abs(arg10[arg11 + v5] - arg10[arg11 + arg12 + v5]);
                ++v5;
            }

            if(v6 * v2 < v0 * arg12) {
                v2 = arg12;
                v0 = v6;
            }

            if(v6 * v4 > v3 * arg12) {
                v4 = arg12;
                v3 = v6;
            }

            ++arg12;
        }

        this.minDiff = v0 / v2;
        this.maxDiff = v3 / v4;
        return v2;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
    }

    public int getFramesAvailable() {
        return this.outputFrameCount;
    }

    public void getOutput(ShortBuffer arg6) {
        int v0 = Math.min(arg6.remaining() / this.channelCount, this.outputFrameCount);
        arg6.put(this.outputBuffer, 0, this.channelCount * v0);
        this.outputFrameCount -= v0;
        System.arraycopy(this.outputBuffer, v0 * this.channelCount, this.outputBuffer, 0, this.outputFrameCount * this.channelCount);
    }

    private int insertPitchPeriod(short[] arg10, int arg11, float arg12, int arg13) {
        // Method was not decompiled
    }

    private short interpolate(short[] arg3, int arg4, int arg5, int arg6) {
        int v0 = arg3[arg4];
        int v3 = arg3[arg4 + this.channelCount];
        arg4 = this.newRatePosition * arg5;
        arg5 = this.oldRatePosition * arg6;
        int v1 = (this.oldRatePosition + 1) * arg6;
        arg4 = v1 - arg4;
        v1 -= arg5;
        return ((short)((v0 * arg4 + (v1 - arg4) * v3) / v1));
    }

    private void moveNewSamplesToPitchBuffer(int arg7) {
        int v0 = this.outputFrameCount - arg7;
        this.pitchBuffer = this.ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, v0);
        System.arraycopy(this.outputBuffer, this.channelCount * arg7, this.pitchBuffer, this.pitchFrameCount * this.channelCount, this.channelCount * v0);
        this.outputFrameCount = arg7;
        this.pitchFrameCount += v0;
    }

    private static void overlapAdd(int arg8, int arg9, short[] arg10, int arg11, short[] arg12, int arg13, short[] arg14, int arg15) {
        int v1;
        for(v1 = 0; v1 < arg9; ++v1) {
            int v4 = arg13 * arg9 + v1;
            int v5 = arg15 * arg9 + v1;
            int v3 = arg11 * arg9 + v1;
            int v2;
            for(v2 = 0; v2 < arg8; ++v2) {
                arg10[v3] = ((short)((arg12[v4] * (arg8 - v2) + arg14[v5] * v2) / arg8));
                v3 += arg9;
                v4 += arg9;
                v5 += arg9;
            }
        }
    }

    private boolean previousPeriodBetter(int arg3, int arg4) {
        if(arg3 != 0) {
            if(this.prevPeriod == 0) {
            }
            else if(arg4 > arg3 * 3) {
                return 0;
            }
            else if(arg3 * 2 <= this.prevMinDiff * 3) {
                return 0;
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    private void processStreamInput() {
        int v0 = this.outputFrameCount;
        float v1 = this.speed / this.pitch;
        float v2 = this.rate * this.pitch;
        double v3 = ((double)v1);
        if(v3 > 1.00001 || v3 < 0.99999) {
            this.changeSpeed(v1);
        }
        else {
            this.copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }

        if(v2 != 1f) {
            this.adjustRate(v2, v0);
        }
    }

    public void queueEndOfStream() {
        int v0 = this.inputFrameCount;
        int v3 = this.outputFrameCount + (((int)(((((float)v0)) / (this.speed / this.pitch) + (((float)this.pitchFrameCount))) / (this.rate * this.pitch) + 0.5f)));
        this.inputBuffer = this.ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, this.maxRequiredFrameCount * 2 + v0);
        int v2;
        for(v2 = 0; v2 < this.maxRequiredFrameCount * 2 * this.channelCount; ++v2) {
            this.inputBuffer[this.channelCount * v0 + v2] = 0;
        }

        this.inputFrameCount += this.maxRequiredFrameCount * 2;
        this.processStreamInput();
        if(this.outputFrameCount > v3) {
            this.outputFrameCount = v3;
        }

        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void queueInput(ShortBuffer arg6) {
        int v0 = arg6.remaining() / this.channelCount;
        int v1 = this.channelCount * v0 * 2;
        this.inputBuffer = this.ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, v0);
        arg6.get(this.inputBuffer, this.inputFrameCount * this.channelCount, v1 / 2);
        this.inputFrameCount += v0;
        this.processStreamInput();
    }

    private void removePitchFrames(int arg7) {
        if(arg7 == 0) {
            return;
        }

        System.arraycopy(this.pitchBuffer, this.channelCount * arg7, this.pitchBuffer, 0, (this.pitchFrameCount - arg7) * this.channelCount);
        this.pitchFrameCount -= arg7;
    }

    private void removeProcessedInputFrames(int arg6) {
        int v0 = this.inputFrameCount - arg6;
        System.arraycopy(this.inputBuffer, arg6 * this.channelCount, this.inputBuffer, 0, this.channelCount * v0);
        this.inputFrameCount = v0;
    }

    private int skipPitchPeriod(short[] arg9, int arg10, float arg11, int arg12) {
        int v11;
        float v0 = 2f;
        float v2 = 1f;
        if(Float.compare(arg11, v0) >= 0) {
            v11 = ((int)((((float)arg12)) / (arg11 - v2)));
        }
        else {
            this.remainingInputToCopyFrameCount = ((int)((((float)arg12)) * (v0 - arg11) / (arg11 - v2)));
            v11 = arg12;
        }

        this.outputBuffer = this.ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, v11);
        Sonic.overlapAdd(v11, this.channelCount, this.outputBuffer, this.outputFrameCount, arg9, arg10, arg9, arg10 + arg12);
        this.outputFrameCount += v11;
        return v11;
    }
}

