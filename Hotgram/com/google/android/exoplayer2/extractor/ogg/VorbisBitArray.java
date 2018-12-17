package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.util.Assertions;

final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] arg1) {
        super();
        this.data = arg1;
        this.byteLimit = arg1.length;
    }

    private void assertValidOffset() {
        boolean v0;
        if(this.byteOffset >= 0) {
            if(this.byteOffset >= this.byteLimit) {
                if(this.byteOffset != this.byteLimit) {
                }
                else if(this.bitOffset == 0) {
                    goto label_10;
                }

                goto label_12;
            }

        label_10:
            v0 = true;
        }
        else {
        label_12:
            v0 = false;
        }

        Assertions.checkState(v0);
    }

    public int bitsLeft() {
        return (this.byteLimit - this.byteOffset) * 8 - this.bitOffset;
    }

    public int getPosition() {
        return this.byteOffset * 8 + this.bitOffset;
    }

    public boolean readBit() {
        boolean v0 = ((this.data[this.byteOffset] & 255) >> this.bitOffset & 1) == 1 ? true : false;
        this.skipBits(1);
        return v0;
    }

    public int readBits(int arg7) {
        int v0 = this.byteOffset;
        int v1 = Math.min(arg7, 8 - this.bitOffset);
        int v3 = v0 + 1;
        int v2 = 255;
        v0 = (this.data[v0] & v2) >> this.bitOffset & v2 >> 8 - v1;
        while(v1 < arg7) {
            v0 |= (this.data[v3] & v2) << v1;
            v1 += 8;
            ++v3;
        }

        this.skipBits(arg7);
        return v0 & -1 >>> 32 - arg7;
    }

    public void reset() {
        this.byteOffset = 0;
        this.bitOffset = 0;
    }

    public void setPosition(int arg2) {
        this.byteOffset = arg2 / 8;
        this.bitOffset = arg2 - this.byteOffset * 8;
        this.assertValidOffset();
    }

    public void skipBits(int arg3) {
        int v0 = arg3 / 8;
        this.byteOffset += v0;
        this.bitOffset += arg3 - v0 * 8;
        if(this.bitOffset > 7) {
            ++this.byteOffset;
            this.bitOffset += -8;
        }

        this.assertValidOffset();
    }
}

