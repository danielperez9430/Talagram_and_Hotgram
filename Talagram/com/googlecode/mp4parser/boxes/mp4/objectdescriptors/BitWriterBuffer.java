package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

public class BitWriterBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    static {
    }

    public BitWriterBuffer(ByteBuffer arg2) {
        super();
        this.position = 0;
        this.buffer = arg2;
        this.initialPos = arg2.position();
    }

    public void writeBits(int arg6, int arg7) {
        ByteBuffer v6;
        int v0 = 8 - this.position % 8;
        int v1 = 1;
        if(arg7 <= v0) {
            int v2 = this.buffer.get(this.initialPos + this.position / 8);
            if(v2 < 0) {
                v2 += 256;
            }

            v2 += arg6 << v0 - arg7;
            v6 = this.buffer;
            v0 = this.initialPos + this.position / 8;
            if(v2 > 127) {
                v2 += -256;
            }

            v6.put(v0, ((byte)v2));
            this.position += arg7;
        }
        else {
            arg7 -= v0;
            this.writeBits(arg6 >> arg7, v0);
            this.writeBits(arg6 & (1 << arg7) - 1, arg7);
        }

        v6 = this.buffer;
        arg7 = this.initialPos + this.position / 8;
        if(this.position % 8 > 0) {
        }
        else {
            v1 = 0;
        }

        v6.position(arg7 + v1);
    }
}

