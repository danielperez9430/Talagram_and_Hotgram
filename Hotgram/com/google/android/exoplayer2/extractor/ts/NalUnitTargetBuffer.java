package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

final class NalUnitTargetBuffer {
    private boolean isCompleted;
    private boolean isFilling;
    public byte[] nalData;
    public int nalLength;
    private final int targetType;

    public NalUnitTargetBuffer(int arg2, int arg3) {
        super();
        this.targetType = arg2;
        this.nalData = new byte[arg3 + 3];
        this.nalData[2] = 1;
    }

    public void appendToNalUnit(byte[] arg3, int arg4, int arg5) {
        if(!this.isFilling) {
            return;
        }

        arg5 -= arg4;
        if(this.nalData.length < this.nalLength + arg5) {
            this.nalData = Arrays.copyOf(this.nalData, (this.nalLength + arg5) * 2);
        }

        System.arraycopy(arg3, arg4, this.nalData, this.nalLength, arg5);
        this.nalLength += arg5;
    }

    public boolean endNalUnit(int arg3) {
        if(!this.isFilling) {
            return 0;
        }

        this.nalLength -= arg3;
        this.isFilling = false;
        this.isCompleted = true;
        return 1;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void reset() {
        this.isFilling = false;
        this.isCompleted = false;
    }

    public void startNalUnit(int arg4) {
        boolean v1 = true;
        Assertions.checkState(this.isFilling ^ 1);
        if(arg4 == this.targetType) {
        }
        else {
            v1 = false;
        }

        this.isFilling = v1;
        if(this.isFilling) {
            this.nalLength = 3;
            this.isCompleted = false;
        }
    }
}

