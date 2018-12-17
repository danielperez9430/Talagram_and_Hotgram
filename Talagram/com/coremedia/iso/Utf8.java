package com.coremedia.iso;

import java.io.UnsupportedEncodingException;

public final class Utf8 {
    public Utf8() {
        super();
    }

    public static String convert(byte[] arg2) {
        if(arg2 != null) {
            try {
                return new String(arg2, "UTF-8");
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
                return arg1.getBytes("UTF-8");
            }
            catch(UnsupportedEncodingException v1) {
                throw new Error(((Throwable)v1));
            }
        }

        return null;
    }

    public static int utf8StringLengthInBytes(String arg1) {
        if(arg1 != null) {
            try {
                return arg1.getBytes("UTF-8").length;
            }
            catch(UnsupportedEncodingException ) {
                throw new RuntimeException();
            }
        }

        return 0;
    }
}

