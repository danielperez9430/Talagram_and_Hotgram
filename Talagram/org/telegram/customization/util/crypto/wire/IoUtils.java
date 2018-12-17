package org.telegram.customization.util.crypto.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class IoUtils {
    public IoUtils() {
        super();
    }

    public static int copy(InputStream arg4, OutputStream arg5) {
        int v1;
        byte[] v0 = new byte[4096];
        while(true) {
            v1 = arg4.read(v0, 0, v0.length);
            if(v1 == -1) {
                return v1;
            }

            arg5.write(v0, 0, v1);
        }

        return v1;
    }

    public static byte[] gunzip(byte[] arg1) {
        try {
            GZIPInputStream v1_1 = new GZIPInputStream(new ByteArrayInputStream(arg1));
            ByteArrayOutputStream v0 = new ByteArrayOutputStream();
            IoUtils.copy(((InputStream)v1_1), ((OutputStream)v0));
            v0.close();
            return v0.toByteArray();
        }
        catch(IOException v1) {
            throw new RuntimeException(((Throwable)v1));
        }
    }

    public static byte[] gzip(byte[] arg2) {
        try {
            ByteArrayOutputStream v0 = new ByteArrayOutputStream(arg2.length);
            GZIPOutputStream v1 = new GZIPOutputStream(((OutputStream)v0));
            v1.write(arg2);
            v1.close();
            v0.close();
            return v0.toByteArray();
        }
        catch(IOException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
    }
}

