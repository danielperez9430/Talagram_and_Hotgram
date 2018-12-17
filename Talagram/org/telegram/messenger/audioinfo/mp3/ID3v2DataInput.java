package org.telegram.messenger.audioinfo.mp3;

import java.io.EOFException;
import java.io.InputStream;

public class ID3v2DataInput {
    private final InputStream input;

    public ID3v2DataInput(InputStream arg1) {
        super();
        this.input = arg1;
    }

    public byte readByte() {
        int v0 = this.input.read();
        if(v0 >= 0) {
            return ((byte)v0);
        }

        throw new EOFException();
    }

    public final void readFully(byte[] arg5, int arg6, int arg7) {
        int v0;
        for(v0 = 0; true; v0 += v1) {
            if(v0 >= arg7) {
                return;
            }

            int v1 = this.input.read(arg5, arg6 + v0, arg7 - v0);
            if(v1 <= 0) {
                break;
            }
        }

        throw new EOFException();
    }

    public byte[] readFully(int arg3) {
        byte[] v0 = new byte[arg3];
        this.readFully(v0, 0, arg3);
        return v0;
    }

    public int readInt() {
        return (this.readByte() & 255) << 24 | (this.readByte() & 255) << 16 | (this.readByte() & 255) << 8 | this.readByte() & 255;
    }

    public int readSyncsafeInt() {
        return (this.readByte() & 127) << 21 | (this.readByte() & 127) << 14 | (this.readByte() & 127) << 7 | this.readByte() & 127;
    }

    public void skipFully(long arg8) {
        long v0 = 0;
        long v2;
        for(v2 = v0; true; v2 += v4) {
            if(v2 >= arg8) {
                return;
            }

            long v4 = this.input.skip(arg8 - v2);
            if(v4 <= v0) {
                break;
            }
        }

        throw new EOFException();
    }
}

