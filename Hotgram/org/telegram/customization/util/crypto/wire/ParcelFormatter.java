package org.telegram.customization.util.crypto.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class ParcelFormatter {
    protected static final int MAX_BYTES_SIZE = 4194304;
    private static final Random random;

    static {
        ParcelFormatter.random = new Random();
    }

    public ParcelFormatter() {
        super();
    }

    public static byte[] decode(byte[] arg4) {
        try {
            ByteArrayInputStream v0 = new ByteArrayInputStream(arg4);
            ByteArrayOutputStream v4_1 = new ByteArrayOutputStream();
            ParcelHeader v1 = ParcelHeader.from(new DataInputStream(((InputStream)v0)));
            IoUtils.copy(((InputStream)v0), ((OutputStream)v4_1));
            arg4 = v4_1.toByteArray();
            if(Crc32.hash(arg4) == v1.crc32) {
                if(v1.isEncrypted()) {
                    arg4 = AesCrypto.getSingleCrypto(Long.toHexString(v1.key)).decrypt(arg4);
                }

                if(v1.isCompressed()) {
                    arg4 = IoUtils.gunzip(arg4);
                }

                return arg4;
            }

            throw new IllegalArgumentException("CRC does not match.");
        }
        catch(IOException v4) {
            throw new IllegalStateException(((Throwable)v4));
        }
    }

    public static byte[] encode(byte[] arg3) {
        boolean v0 = arg3.length > 64 ? true : false;
        return ParcelFormatter.encode(arg3, true, v0);
    }

    public static byte[] encode(byte[] arg6, boolean arg7, boolean arg8) {
        long v0 = ParcelFormatter.random.nextLong();
        long v2 = 0;
        if(v0 == v2) {
            ++v0;
        }

        if(arg8) {
            arg6 = IoUtils.gzip(arg6);
        }

        if(arg7) {
            arg6 = AesCrypto.getSingleCrypto(Long.toHexString(v0)).encrypt(arg6);
        }

        if(arg7) {
        }
        else {
            v0 = v2;
        }

        byte v7 = arg8 ? 103 : 110;
        ParcelHeader v4 = new ParcelHeader(arg6, v0, v7);
        try {
            ByteArrayOutputStream v7_1 = new ByteArrayOutputStream();
            v4.writeTo(((OutputStream)v7_1));
            v7_1.write(arg6);
            return v7_1.toByteArray();
        }
        catch(IOException v6) {
            throw new RuntimeException(((Throwable)v6));
        }
    }
}

