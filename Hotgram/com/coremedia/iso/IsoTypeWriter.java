package com.coremedia.iso;

import java.nio.ByteBuffer;

public final class IsoTypeWriter {
    static {
    }

    public IsoTypeWriter() {
        super();
    }

    public static void writeFixedPoint0230(ByteBuffer arg2, double arg3) {
        int v3 = ((int)(arg3 * 1073741824));
        arg2.put(((byte)((-16777216 & v3) >> 24)));
        arg2.put(((byte)((16711680 & v3) >> 16)));
        arg2.put(((byte)((65280 & v3) >> 8)));
        arg2.put(((byte)(v3 & 255)));
    }

    public static void writeFixedPoint1616(ByteBuffer arg2, double arg3) {
        int v3 = ((int)(arg3 * 65536));
        arg2.put(((byte)((-16777216 & v3) >> 24)));
        arg2.put(((byte)((16711680 & v3) >> 16)));
        arg2.put(((byte)((65280 & v3) >> 8)));
        arg2.put(((byte)(v3 & 255)));
    }

    public static void writeFixedPoint88(ByteBuffer arg2, double arg3) {
        short v3 = ((short)(((int)(arg3 * 256))));
        arg2.put(((byte)((65280 & v3) >> 8)));
        arg2.put(((byte)(v3 & 255)));
    }

    public static void writeIso639(ByteBuffer arg5, String arg6) {
        int v1 = 3;
        if(arg6.getBytes().length == v1) {
            int v0 = 0;
            int v2 = 0;
            while(v0 < v1) {
                v2 += arg6.getBytes()[v0] - 96 << (2 - v0) * 5;
                ++v0;
            }

            IsoTypeWriter.writeUInt16(arg5, v2);
            return;
        }

        StringBuilder v0_1 = new StringBuilder("\"");
        v0_1.append(arg6);
        v0_1.append("\" language string isn\'t exactly 3 characters long!");
        throw new IllegalArgumentException(v0_1.toString());
    }

    public static void writePascalUtfString(ByteBuffer arg1, String arg2) {
        byte[] v2 = Utf8.convert(arg2);
        IsoTypeWriter.writeUInt8(arg1, v2.length);
        arg1.put(v2);
    }

    public static void writeUInt16(ByteBuffer arg1, int arg2) {
        arg2 &= 65535;
        IsoTypeWriter.writeUInt8(arg1, arg2 >> 8);
        IsoTypeWriter.writeUInt8(arg1, arg2 & 255);
    }

    public static void writeUInt16BE(ByteBuffer arg1, int arg2) {
        arg2 &= 65535;
        IsoTypeWriter.writeUInt8(arg1, arg2 & 255);
        IsoTypeWriter.writeUInt8(arg1, arg2 >> 8);
    }

    public static void writeUInt24(ByteBuffer arg1, int arg2) {
        arg2 &= 16777215;
        IsoTypeWriter.writeUInt16(arg1, arg2 >> 8);
        IsoTypeWriter.writeUInt8(arg1, arg2);
    }

    public static void writeUInt32(ByteBuffer arg0, long arg1) {
        arg0.putInt(((int)arg1));
    }

    public static void writeUInt32BE(ByteBuffer arg2, long arg3) {
        IsoTypeWriter.writeUInt16BE(arg2, (((int)arg3)) & 65535);
        IsoTypeWriter.writeUInt16BE(arg2, ((int)(arg3 >> 16 & 65535)));
    }

    public static void writeUInt48(ByteBuffer arg2, long arg3) {
        arg3 &= 281474976710655L;
        IsoTypeWriter.writeUInt16(arg2, ((int)(arg3 >> 32)));
        IsoTypeWriter.writeUInt32(arg2, arg3 & 4294967295L);
    }

    public static void writeUInt64(ByteBuffer arg0, long arg1) {
        arg0.putLong(arg1);
    }

    public static void writeUInt8(ByteBuffer arg0, int arg1) {
        arg0.put(((byte)(arg1 & 255)));
    }

    public static void writeUtf8String(ByteBuffer arg0, String arg1) {
        arg0.put(Utf8.convert(arg1));
        IsoTypeWriter.writeUInt8(arg0, 0);
    }

    public static void writeZeroTermUtf8String(ByteBuffer arg0, String arg1) {
        arg0.put(Utf8.convert(arg1));
        IsoTypeWriter.writeUInt8(arg0, 0);
    }
}

