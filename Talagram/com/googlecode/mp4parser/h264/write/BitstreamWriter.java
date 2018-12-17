package com.googlecode.mp4parser.h264.write;

import com.googlecode.mp4parser.h264.Debug;
import java.io.OutputStream;

public class BitstreamWriter {
    private int curBit;
    private int[] curByte;
    private final OutputStream os;

    public BitstreamWriter(OutputStream arg2) {
        super();
        this.curByte = new int[8];
        this.os = arg2;
    }

    public void flush() {
        int v0;
        for(v0 = this.curBit; v0 < 8; ++v0) {
            this.curByte[v0] = 0;
        }

        this.curBit = 0;
        this.writeCurByte();
    }

    public void write1Bit(int arg4) {
        Debug.print(arg4);
        if(this.curBit == 8) {
            this.curBit = 0;
            this.writeCurByte();
        }

        int[] v0 = this.curByte;
        int v1 = this.curBit;
        this.curBit = v1 + 1;
        v0[v1] = arg4;
    }

    public void writeByte(int arg2) {
        this.os.write(arg2);
    }

    private void writeCurByte() {
        this.os.write(this.curByte[0] << 7 | this.curByte[1] << 6 | this.curByte[2] << 5 | this.curByte[3] << 4 | this.curByte[4] << 3 | this.curByte[5] << 2 | this.curByte[6] << 1 | this.curByte[7]);
    }

    public void writeNBit(long arg4, int arg6) {
        int v0;
        for(v0 = 0; v0 < arg6; ++v0) {
            this.write1Bit((((int)(arg4 >> arg6 - v0 - 1))) & 1);
        }
    }

    public void writeRemainingZero() {
        this.writeNBit(0, 8 - this.curBit);
    }
}

