package com.google.android.exoplayer2.util;

public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray(byte[] arg2) {
        this(arg2, arg2.length);
    }

    public ParsableBitArray(byte[] arg1, int arg2) {
        super();
        this.data = arg1;
        this.byteLimit = arg2;
    }

    public ParsableBitArray() {
        super();
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

    public void byteAlign() {
        if(this.bitOffset == 0) {
            return;
        }

        this.bitOffset = 0;
        ++this.byteOffset;
        this.assertValidOffset();
    }

    public int getBytePosition() {
        boolean v0 = this.bitOffset == 0 ? true : false;
        Assertions.checkState(v0);
        return this.byteOffset;
    }

    public int getPosition() {
        return this.byteOffset * 8 + this.bitOffset;
    }

    public void putInt(int arg10, int arg11) {
        if(arg11 < 32) {
            arg10 &= (1 << arg11) - 1;
        }

        int v2 = 8;
        int v1 = Math.min(8 - this.bitOffset, arg11);
        int v3 = 8 - this.bitOffset - v1;
        this.data[this.byteOffset] = ((byte)((65280 >> this.bitOffset | (1 << v3) - 1) & this.data[this.byteOffset]));
        v1 = arg11 - v1;
        this.data[this.byteOffset] = ((byte)(arg10 >>> v1 << v3 | this.data[this.byteOffset]));
        for(v3 = this.byteOffset + 1; v1 > v2; ++v3) {
            this.data[v3] = ((byte)(arg10 >>> v1 - 8));
            v1 += -8;
        }

        v2 -= v1;
        this.data[v3] = ((byte)(this.data[v3] & (1 << v2) - 1));
        this.data[v3] = ((byte)((arg10 & (1 << v1) - 1) << v2 | this.data[v3]));
        this.skipBits(arg11);
        this.assertValidOffset();
    }

    public boolean readBit() {
        boolean v0 = (this.data[this.byteOffset] & 128 >> this.bitOffset) != 0 ? true : false;
        this.skipBit();
        return v0;
    }

    public int readBits(int arg6) {
        if(arg6 == 0) {
            return 0;
        }

        this.bitOffset += arg6;
        int v1;
        for(v1 = 0; true; v1 |= (v2[v3] & 255) << this.bitOffset) {
            int v3 = 8;
            if(this.bitOffset <= v3) {
                break;
            }

            this.bitOffset -= v3;
            byte[] v2 = this.data;
            v3 = this.byteOffset;
            this.byteOffset = v3 + 1;
        }

        arg6 = -1 >>> 32 - arg6 & (v1 | (this.data[this.byteOffset] & 255) >> 8 - this.bitOffset);
        if(this.bitOffset == v3) {
            this.bitOffset = 0;
            ++this.byteOffset;
        }

        this.assertValidOffset();
        return arg6;
    }

    public void readBits(byte[] arg7, int arg8, int arg9) {
        int v4;
        byte[] v3;
        int v2;
        int v1;
        int v0 = (arg9 >> 3) + arg8;
        while(true) {
            v1 = 255;
            v2 = 8;
            if(arg8 >= v0) {
                break;
            }

            v3 = this.data;
            v4 = this.byteOffset;
            this.byteOffset = v4 + 1;
            arg7[arg8] = ((byte)(v3[v4] << this.bitOffset));
            arg7[arg8] = ((byte)((v1 & this.data[this.byteOffset]) >> v2 - this.bitOffset | arg7[arg8]));
            ++arg8;
        }

        arg8 = arg9 & 7;
        if(arg8 == 0) {
            return;
        }

        arg7[v0] = ((byte)(arg7[v0] & v1 >> arg8));
        if(this.bitOffset + arg8 > v2) {
            arg9 = arg7[v0];
            v3 = this.data;
            v4 = this.byteOffset;
            this.byteOffset = v4 + 1;
            arg7[v0] = ((byte)(arg9 | (v3[v4] & v1) << this.bitOffset));
            this.bitOffset -= v2;
        }

        this.bitOffset += arg8;
        arg7[v0] = ((byte)((((byte)((this.data[this.byteOffset] & v1) >> 8 - this.bitOffset << 8 - arg8))) | arg7[v0]));
        if(this.bitOffset == v2) {
            this.bitOffset = 0;
            ++this.byteOffset;
        }

        this.assertValidOffset();
    }

    public void readBytes(byte[] arg3, int arg4, int arg5) {
        boolean v0 = this.bitOffset == 0 ? true : false;
        Assertions.checkState(v0);
        System.arraycopy(this.data, this.byteOffset, arg3, arg4, arg5);
        this.byteOffset += arg5;
        this.assertValidOffset();
    }

    public void reset(byte[] arg2) {
        this.reset(arg2, arg2.length);
    }

    public void reset(ParsableByteArray arg3) {
        this.reset(arg3.data, arg3.limit());
        this.setPosition(arg3.getPosition() * 8);
    }

    public void reset(byte[] arg1, int arg2) {
        this.data = arg1;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = arg2;
    }

    public void setPosition(int arg2) {
        this.byteOffset = arg2 / 8;
        this.bitOffset = arg2 - this.byteOffset * 8;
        this.assertValidOffset();
    }

    public void skipBit() {
        int v0 = this.bitOffset + 1;
        this.bitOffset = v0;
        if(v0 == 8) {
            this.bitOffset = 0;
            ++this.byteOffset;
        }

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

    public void skipBytes(int arg2) {
        boolean v0 = this.bitOffset == 0 ? true : false;
        Assertions.checkState(v0);
        this.byteOffset += arg2;
        this.assertValidOffset();
    }
}

