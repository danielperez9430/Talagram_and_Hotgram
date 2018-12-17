package com.google.android.exoplayer2.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class ParsableByteArray {
    public byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray(int arg2) {
        super();
        this.data = new byte[arg2];
        this.limit = arg2;
    }

    public ParsableByteArray(byte[] arg1) {
        super();
        this.data = arg1;
        this.limit = arg1.length;
    }

    public ParsableByteArray(byte[] arg1, int arg2) {
        super();
        this.data = arg1;
        this.limit = arg2;
    }

    public ParsableByteArray() {
        super();
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int capacity() {
        int v0 = this.data == null ? 0 : this.data.length;
        return v0;
    }

    public int getPosition() {
        return this.position;
    }

    public int limit() {
        return this.limit;
    }

    public char peekChar() {
        return ((char)((this.data[this.position] & 255) << 8 | this.data[this.position + 1] & 255));
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & 255;
    }

    public void readBytes(byte[] arg3, int arg4, int arg5) {
        System.arraycopy(this.data, this.position, arg3, arg4, arg5);
        this.position += arg5;
    }

    public void readBytes(ParsableBitArray arg3, int arg4) {
        this.readBytes(arg3.data, 0, arg4);
        arg3.setPosition(0);
    }

    public void readBytes(ByteBuffer arg3, int arg4) {
        arg3.put(this.data, this.position, arg4);
        this.position += arg4;
    }

    public double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }

    public float readFloat() {
        return Float.intBitsToFloat(this.readInt());
    }

    public int readInt() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 24;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 16;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public int readInt24() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 24 >> 8;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public String readLine() {
        if(this.bytesLeft() == 0) {
            return null;
        }

        int v0;
        for(v0 = this.position; v0 < this.limit; ++v0) {
            if(Util.isLinebreak(this.data[v0])) {
                break;
            }
        }

        int v2 = 3;
        if(v0 - this.position >= v2 && this.data[this.position] == -17 && this.data[this.position + 1] == -69 && this.data[this.position + 2] == -65) {
            this.position += v2;
        }

        String v1 = Util.fromUtf8Bytes(this.data, this.position, v0 - this.position);
        this.position = v0;
        if(this.position == this.limit) {
            return v1;
        }

        if(this.data[this.position] == 13) {
            ++this.position;
            if(this.position == this.limit) {
                return v1;
            }
        }

        if(this.data[this.position] == 10) {
            ++this.position;
        }

        return v1;
    }

    public int readLittleEndianInt() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 16;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 24;
    }

    public int readLittleEndianInt24() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 16;
    }

    public long readLittleEndianLong() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        long v0_1 = (((long)v0[v1])) & 255;
        byte[] v4 = this.data;
        int v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 24;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 32;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 40;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 48;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        return v0_1 | (255 & (((long)v4[v5]))) << 56;
    }

    public short readLittleEndianShort() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        return ((short)(v0_1 | (v1_1[v2] & 255) << 8));
    }

    public long readLittleEndianUnsignedInt() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        long v0_1 = (((long)v0[v1])) & 255;
        byte[] v4 = this.data;
        int v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        return v0_1 | (255 & (((long)v4[v5]))) << 24;
    }

    public int readLittleEndianUnsignedInt24() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 16;
    }

    public int readLittleEndianUnsignedIntToInt() {
        int v0 = this.readLittleEndianInt();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("Top bit not zero: ");
        v2.append(v0);
        throw new IllegalStateException(v2.toString());
    }

    public int readLittleEndianUnsignedShort() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0[v1] & 255;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | (v1_1[v2] & 255) << 8;
    }

    public long readLong() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        long v0_1 = ((((long)v0[v1])) & 255) << 56;
        byte[] v4 = this.data;
        int v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 48;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 40;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 32;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 24;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        return v0_1 | 255 & (((long)v4[v5]));
    }

    public String readNullTerminatedString(int arg4) {
        if(arg4 == 0) {
            return "";
        }

        int v0 = this.position + arg4 - 1;
        v0 = v0 >= this.limit || this.data[v0] != 0 ? arg4 : arg4 - 1;
        String v0_1 = Util.fromUtf8Bytes(this.data, this.position, v0);
        this.position += arg4;
        return v0_1;
    }

    public String readNullTerminatedString() {
        if(this.bytesLeft() == 0) {
            return null;
        }

        int v0;
        for(v0 = this.position; v0 < this.limit; ++v0) {
            if(this.data[v0] == 0) {
                break;
            }
        }

        String v1 = Util.fromUtf8Bytes(this.data, this.position, v0 - this.position);
        this.position = v0;
        if(this.position < this.limit) {
            ++this.position;
        }

        return v1;
    }

    public short readShort() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        return ((short)(v0_1 | v1_1[v2] & 255));
    }

    public String readString(int arg2) {
        return this.readString(arg2, Charset.forName("UTF-8"));
    }

    public String readString(int arg4, Charset arg5) {
        String v0 = new String(this.data, this.position, arg4, arg5);
        this.position += arg4;
        return v0;
    }

    public int readSynchSafeInt() {
        return this.readUnsignedByte() << 21 | this.readUnsignedByte() << 14 | this.readUnsignedByte() << 7 | this.readUnsignedByte();
    }

    public int readUnsignedByte() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        return v0[v1] & 255;
    }

    public int readUnsignedFixedPoint1616() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= v1_1[v2] & 255;
        this.position += 2;
        return v0_1;
    }

    public long readUnsignedInt() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        long v0_1 = ((((long)v0[v1])) & 255) << 24;
        byte[] v4 = this.data;
        int v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 16;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        v0_1 |= ((((long)v4[v5])) & 255) << 8;
        v4 = this.data;
        v5 = this.position;
        this.position = v5 + 1;
        return v0_1 | 255 & (((long)v4[v5]));
    }

    public int readUnsignedInt24() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 16;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        v0_1 |= (v1_1[v2] & 255) << 8;
        v1_1 = this.data;
        v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public int readUnsignedIntToInt() {
        int v0 = this.readInt();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("Top bit not zero: ");
        v2.append(v0);
        throw new IllegalStateException(v2.toString());
    }

    public long readUnsignedLongToLong() {
        long v0 = this.readLong();
        if(v0 >= 0) {
            return v0;
        }

        StringBuilder v3 = new StringBuilder();
        v3.append("Top bit not zero: ");
        v3.append(v0);
        throw new IllegalStateException(v3.toString());
    }

    public int readUnsignedShort() {
        byte[] v0 = this.data;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = (v0[v1] & 255) << 8;
        byte[] v1_1 = this.data;
        int v2 = this.position;
        this.position = v2 + 1;
        return v0_1 | v1_1[v2] & 255;
    }

    public long readUtf8EncodedLong() {
        StringBuilder v3_1;
        int v5;
        int v4;
        long v0 = ((long)this.data[this.position]);
        int v2 = 7;
        int v3 = 7;
        while(true) {
            v4 = 6;
            v5 = 1;
            if(v3 >= 0) {
                int v6 = 1 << v3;
                if(((((long)v6)) & v0) != 0) {
                    --v3;
                    continue;
                }
                else if(v3 < v4) {
                    v0 &= ((long)(v6 - 1));
                    v2 -= v3;
                }
                else if(v3 == v2) {
                    v2 = 1;
                }
                else {
                    break;
                }
            }
            else {
                break;
            }

            goto label_26;
        }

        v2 = 0;
    label_26:
        if(v2 == 0) {
            v3_1 = new StringBuilder();
            v3_1.append("Invalid UTF-8 sequence first byte: ");
            v3_1.append(v0);
            throw new NumberFormatException(v3_1.toString());
        }

        while(true) {
            if(v5 >= v2) {
                goto label_50;
            }

            v3 = this.data[this.position + v5];
            if((v3 & 192) != 128) {
                break;
            }

            v0 = v0 << v4 | (((long)(v3 & 63)));
            ++v5;
        }

        v3_1 = new StringBuilder();
        v3_1.append("Invalid UTF-8 sequence continuation byte: ");
        v3_1.append(v0);
        throw new NumberFormatException(v3_1.toString());
    label_50:
        this.position += v2;
        return v0;
    }

    public void reset() {
        this.position = 0;
        this.limit = 0;
    }

    public void reset(byte[] arg1, int arg2) {
        this.data = arg1;
        this.limit = arg2;
        this.position = 0;
    }

    public void reset(int arg2) {
        byte[] v0 = this.capacity() < arg2 ? new byte[arg2] : this.data;
        this.reset(v0, arg2);
    }

    public void setLimit(int arg2) {
        boolean v0 = arg2 < 0 || arg2 > this.data.length ? false : true;
        Assertions.checkArgument(v0);
        this.limit = arg2;
    }

    public void setPosition(int arg2) {
        boolean v0 = arg2 < 0 || arg2 > this.limit ? false : true;
        Assertions.checkArgument(v0);
        this.position = arg2;
    }

    public void skipBytes(int arg2) {
        this.setPosition(this.position + arg2);
    }
}

