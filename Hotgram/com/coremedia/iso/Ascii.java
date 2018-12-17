package com.coremedia.iso;

import java.io.UnsupportedEncodingException;

public final class Ascii {
    public Ascii() {
        super();
    }

    public static String convert(byte[] arg2) {
        if(arg2 != null) {
            try {
                return new String(arg2, "us-ascii");
            }
            catch(UnsupportedEncodingException v2) {
                throw new Error(((Throwable)v2));
            }
        }

        return null;
    }

    public static byte[] convert(String arg1) {
        if(arg1 != null) {
            try {
                return arg1.getBytes("us-ascii");
            }
            catch(UnsupportedEncodingException v1) {
                throw new Error(((Throwable)v1));
            }
        }

        return null;
    }
}

