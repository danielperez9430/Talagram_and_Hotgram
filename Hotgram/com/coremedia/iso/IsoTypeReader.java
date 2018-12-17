package com.coremedia.iso;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public final class IsoTypeReader {
    public IsoTypeReader() {
        super();
    }

    public static int byte2int(byte arg0) {
        int v0;
        if(arg0 < 0) {
            v0 = arg0 + 256;
        }

        return v0;
    }

    public static String read4cc(ByteBuffer arg2) {
        byte[] v0 = new byte[4];
        arg2.get(v0);
        try {
            return new String(v0, "ISO-8859-1");
        }
        catch(UnsupportedEncodingException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }

    public static double readFixedPoint0230(ByteBuffer arg4) {
        byte[] v0 = new byte[4];
        arg4.get(v0);
        double v0_1 = ((double)(0 | v0[0] << 24 & -16777216 | v0[1] << 16 & 16711680 | v0[2] << 8 & 65280 | v0[3] & 255));
        Double.isNaN(v0_1);
        return v0_1 / 1073741824;
    }

    public static double readFixedPoint1616(ByteBuffer arg4) {
        byte[] v0 = new byte[4];
        arg4.get(v0);
        double v0_1 = ((double)(0 | v0[0] << 24 & -16777216 | v0[1] << 16 & 16711680 | v0[2] << 8 & 65280 | v0[3] & 255));
        Double.isNaN(v0_1);
        return v0_1 / 65536;
    }

    public static float readFixedPoint88(ByteBuffer arg3) {
        byte[] v0 = new byte[2];
        arg3.get(v0);
        return (((float)(((short)((((short)(0 | v0[0] << 8 & 65280))) | v0[1] & 255))))) / 256f;
    }

    public static String readIso639(ByteBuffer arg3) {
        int v3 = IsoTypeReader.readUInt16(arg3);
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < 3; ++v1) {
            v0.append(((char)((v3 >> (2 - v1) * 5 & 31) + 96)));
        }

        return v0.toString();
    }

    public static String readString(ByteBuffer arg2) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        while(true) {
            int v1 = arg2.get();
            if(v1 == 0) {
                break;
            }

            v0.write(v1);
        }

        return Utf8.convert(v0.toByteArray());
    }

    public static String readString(ByteBuffer arg0, int arg1) {
        byte[] v1 = new byte[arg1];
        arg0.get(v1);
        return Utf8.convert(v1);
    }

    public static int readUInt16(ByteBuffer arg1) {
        return (IsoTypeReader.byte2int(arg1.get()) << 8) + IsoTypeReader.byte2int(arg1.get());
    }

    public static int readUInt16BE(ByteBuffer arg1) {
        return IsoTypeReader.byte2int(arg1.get()) + (IsoTypeReader.byte2int(arg1.get()) << 8);
    }

    public static int readUInt24(ByteBuffer arg1) {
        return (IsoTypeReader.readUInt16(arg1) << 8) + IsoTypeReader.byte2int(arg1.get());
    }

    public static long readUInt32(ByteBuffer arg4) {
        long v0 = ((long)arg4.getInt());
        if(v0 < 0) {
            v0 += 4294967296L;
        }

        return v0;
    }

    public static long readUInt32BE(ByteBuffer arg8) {
        return ((((long)IsoTypeReader.readUInt8(arg8))) << 24) + ((((long)IsoTypeReader.readUInt8(arg8))) << 16) + ((((long)IsoTypeReader.readUInt8(arg8))) << 8) + ((((long)IsoTypeReader.readUInt8(arg8))) << 0);
    }

    public static long readUInt48(ByteBuffer arg5) {
        long v0 = (((long)IsoTypeReader.readUInt16(arg5))) << 32;
        if(v0 >= 0) {
            return v0 + IsoTypeReader.readUInt32(arg5);
        }

        throw new RuntimeException("I don\'t know how to deal with UInt64! long is not sufficient and I don\'t want to use BigInt");
    }

    public static long readUInt64(ByteBuffer arg5) {
        long v0 = IsoTypeReader.readUInt32(arg5) << 32;
        if(v0 >= 0) {
            return v0 + IsoTypeReader.readUInt32(arg5);
        }

        throw new RuntimeException("I don\'t know how to deal with UInt64! long is not sufficient and I don\'t want to use BigInt");
    }

    public static int readUInt8(ByteBuffer arg0) {
        return IsoTypeReader.byte2int(arg0.get());
    }
}

