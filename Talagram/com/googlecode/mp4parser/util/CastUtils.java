package com.googlecode.mp4parser.util;

public class CastUtils {
    public CastUtils() {
        super();
    }

    public static int l2i(long arg3) {
        if(arg3 <= 2147483647 && arg3 >= -2147483648) {
            return ((int)arg3);
        }

        StringBuilder v1 = new StringBuilder("A cast to int has gone wrong. Please contact the mp4parser discussion group (");
        v1.append(arg3);
        v1.append(")");
        throw new RuntimeException(v1.toString());
    }
}

