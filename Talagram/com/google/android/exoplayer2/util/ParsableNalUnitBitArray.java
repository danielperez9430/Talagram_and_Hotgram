package com.google.android.exoplayer2.util;

public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] arg1, int arg2, int arg3) {
        super();
        this.reset(arg1, arg2, arg3);
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

    public boolean canReadBits(int arg5) {
        int v0 = this.byteOffset;
        int v1 = arg5 / 8;
        int v2 = this.byteOffset + v1;
        int v3 = this.bitOffset + arg5 - v1 * 8;
        if(v3 > 7) {
            ++v2;
            v3 += -8;
        }

        boolean v5 = true;
        while(true) {
            ++v0;
            if(v0 <= v2 && v2 < this.byteLimit) {
                if(!this.shouldSkipByte(v0)) {
                    continue;
                }

                ++v2;
                v0 += 2;
                continue;
            }

            break;
        }

        if(v2 >= this.byteLimit && (v2 != this.byteLimit || v3 != 0)) {
            v5 = false;
        }

        return v5;
    }

    public boolean canReadExpGolombCodedNum() {
        int v0 = this.byteOffset;
        int v1 = this.bitOffset;
        boolean v2 = false;
        int v3;
        for(v3 = 0; this.byteOffset < this.byteLimit; ++v3) {
            if(this.readBit()) {
                break;
            }
        }

        int v4 = this.byteOffset == this.byteLimit ? 1 : 0;
        this.byteOffset = v0;
        this.bitOffset = v1;
        if(v4 == 0 && (this.canReadBits(v3 * 2 + 1))) {
            v2 = true;
        }

        return v2;
    }

    public boolean readBit() {
        boolean v0 = (this.data[this.byteOffset] & 128 >> this.bitOffset) != 0 ? true : false;
        this.skipBit();
        return v0;
    }

    public int readBits(int arg8) {
        int v3;
        this.bitOffset += arg8;
        int v1 = 0;
        while(true) {
            v3 = 2;
            int v5 = 8;
            if(this.bitOffset <= v5) {
                break;
            }

            this.bitOffset -= v5;
            v1 |= (this.data[this.byteOffset] & 255) << this.bitOffset;
            int v2 = this.byteOffset;
            if(this.shouldSkipByte(this.byteOffset + 1)) {
            }
            else {
                v3 = 1;
            }

            this.byteOffset = v2 + v3;
        }

        arg8 = -1 >>> 32 - arg8 & (v1 | (this.data[this.byteOffset] & 255) >> 8 - this.bitOffset);
        if(this.bitOffset == v5) {
            this.bitOffset = 0;
            int v0 = this.byteOffset;
            if(this.shouldSkipByte(this.byteOffset + 1)) {
            }
            else {
                v3 = 1;
            }

            this.byteOffset = v0 + v3;
        }

        this.assertValidOffset();
        return arg8;
    }

    private int readExpGolombCodeNum() {
        int v0 = 0;
        int v1;
        for(v1 = 0; !this.readBit(); ++v1) {
        }

        int v3 = (1 << v1) - 1;
        if(v1 > 0) {
            v0 = this.readBits(v1);
        }

        return v3 + v0;
    }

    public int readSignedExpGolombCodedInt() {
        int v0 = this.readExpGolombCodeNum();
        int v1 = v0 % 2 == 0 ? -1 : 1;
        return v1 * ((v0 + 1) / 2);
    }

    public int readUnsignedExpGolombCodedInt() {
        return this.readExpGolombCodeNum();
    }

    public void reset(byte[] arg1, int arg2, int arg3) {
        this.data = arg1;
        this.byteOffset = arg2;
        this.byteLimit = arg3;
        this.bitOffset = 0;
        this.assertValidOffset();
    }

    private boolean shouldSkipByte(int arg4) {
        boolean v0 = true;
        if(2 > arg4 || arg4 >= this.byteLimit || this.data[arg4] != 3 || this.data[arg4 - 2] != 0 || this.data[arg4 - 1] != 0) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public void skipBit() {
        int v1 = 1;
        int v0 = this.bitOffset + 1;
        this.bitOffset = v0;
        if(v0 == 8) {
            this.bitOffset = 0;
            v0 = this.byteOffset;
            if(this.shouldSkipByte(this.byteOffset + 1)) {
                v1 = 2;
            }

            this.byteOffset = v0 + v1;
        }

        this.assertValidOffset();
    }

    public void skipBits(int arg4) {
        int v0 = this.byteOffset;
        int v1 = arg4 / 8;
        this.byteOffset += v1;
        this.bitOffset += arg4 - v1 * 8;
        if(this.bitOffset > 7) {
            ++this.byteOffset;
            this.bitOffset += -8;
        }

        while(true) {
            ++v0;
            if(v0 > this.byteOffset) {
                break;
            }

            if(!this.shouldSkipByte(v0)) {
                continue;
            }

            ++this.byteOffset;
            v0 += 2;
        }

        this.assertValidOffset();
    }
}

