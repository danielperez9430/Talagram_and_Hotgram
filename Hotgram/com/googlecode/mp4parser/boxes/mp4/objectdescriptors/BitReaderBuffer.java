package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

public class BitReaderBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    public BitReaderBuffer(ByteBuffer arg1) {
        super();
        this.buffer = arg1;
        this.initialPos = arg1.position();
    }

    public int byteSync() {
        int v0 = 8 - this.position % 8;
        if(v0 == 8) {
            v0 = 0;
        }

        this.readBits(v0);
        return v0;
    }

    public int getPosition() {
        return this.position;
    }

    public int readBits(int arg7) {
        int v0 = this.buffer.get(this.initialPos + this.position / 8);
        if(v0 < 0) {
            v0 += 256;
        }

        int v1 = 8 - this.position % 8;
        if(arg7 <= v1) {
            v0 = (v0 << this.position % 8 & 255) >> this.position % 8 + (v1 - arg7);
            this.position += arg7;
        }
        else {
            arg7 -= v1;
            v0 = (this.readBits(v1) << arg7) + this.readBits(arg7);
        }

        ByteBuffer v7 = this.buffer;
        v1 = this.initialPos;
        double v2 = ((double)this.position);
        Double.isNaN(v2);
        v7.position(v1 + (((int)Math.ceil(v2 / 8))));
        return v0;
    }

    public boolean readBool() {
        if(this.readBits(1) == 1) {
            return 1;
        }

        return 0;
    }

    public int remainingBits() {
        return this.buffer.limit() * 8 - this.position;
    }
}

