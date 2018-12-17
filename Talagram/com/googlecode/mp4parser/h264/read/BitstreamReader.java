package com.googlecode.mp4parser.h264.read;

import com.googlecode.mp4parser.h264.CharCache;
import java.io.InputStream;

public class BitstreamReader {
    protected static int bitsRead;
    private int curByte;
    protected CharCache debugBits;
    private InputStream is;
    int nBit;
    private int nextByte;

    public BitstreamReader(InputStream arg3) {
        super();
        this.debugBits = new CharCache(50);
        this.is = arg3;
        this.curByte = arg3.read();
        this.nextByte = arg3.read();
    }

    private void advance() {
        this.curByte = this.nextByte;
        this.nextByte = this.is.read();
        this.nBit = 0;
    }

    public void close() {
    }

    public long getBitPosition() {
        return ((long)(BitstreamReader.bitsRead * 8 + this.nBit % 8));
    }

    public int getCurBit() {
        return this.nBit;
    }

    public boolean isByteAligned() {
        if(this.nBit % 8 == 0) {
            return 1;
        }

        return 0;
    }

    public boolean moreRBSPData() {
        int v1 = 8;
        if(this.nBit == v1) {
            this.advance();
        }

        boolean v0 = true;
        v1 = 1 << v1 - this.nBit - 1;
        v1 = ((v1 << 1) - 1 & this.curByte) == v1 ? 1 : 0;
        int v4 = -1;
        if(this.curByte == v4 || this.nextByte == v4 && v1 != 0) {
            v0 = false;
        }

        return v0;
    }

    public int peakNextBits(int arg9) {
        int v2;
        int v0 = 8;
        if(arg9 <= v0) {
            if(this.nBit == v0) {
                this.advance();
                v2 = -1;
                if(this.curByte == v2) {
                    return v2;
                }
            }

            int[] v1 = new int[16 - this.nBit];
            v2 = this.nBit;
            int v3 = 0;
            int v4;
            for(v4 = 0; v2 < v0; ++v4) {
                v1[v4] = this.curByte >> 7 - v2 & 1;
                ++v2;
            }

            v2 = 0;
            while(v2 < v0) {
                v1[v4] = this.nextByte >> 7 - v2 & 1;
                ++v2;
                ++v4;
            }

            v0 = 0;
            while(v3 < arg9) {
                v0 = v0 << 1 | v1[v3];
                ++v3;
            }

            return v0;
        }

        throw new IllegalArgumentException("N should be less then 8");
    }

    public int read1Bit() {
        if(this.nBit == 8) {
            this.advance();
            int v1 = -1;
            if(this.curByte == v1) {
                return v1;
            }
        }

        int v0 = this.curByte >> 7 - this.nBit & 1;
        ++this.nBit;
        CharCache v1_1 = this.debugBits;
        char v2 = v0 == 0 ? '0' : '1';
        v1_1.append(v2);
        ++BitstreamReader.bitsRead;
        return v0;
    }

    public boolean readBool() {
        if(this.read1Bit() == 1) {
            return 1;
        }

        return 0;
    }

    public int readByte() {
        if(this.nBit > 0) {
            this.advance();
        }

        int v0 = this.curByte;
        this.advance();
        return v0;
    }

    public long readNBit(int arg6) {
        if(arg6 <= 64) {
            long v0 = 0;
            int v2;
            for(v2 = 0; v2 < arg6; ++v2) {
                v0 = v0 << 1 | (((long)this.read1Bit()));
            }

            return v0;
        }

        throw new IllegalArgumentException("Can not readByte more then 64 bit");
    }

    public long readRemainingByte() {
        return this.readNBit(8 - this.nBit);
    }
}

