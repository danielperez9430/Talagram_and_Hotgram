package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Array;
import java.util.Arrays;

final class li {
    static Object[] a(Object[] arg0, int arg1, int arg2, Object[] arg3) {
        return Arrays.copyOfRange(arg0, arg1, arg2, arg3.getClass());
    }

    static Object[] a(Object[] arg0, int arg1) {
        return Array.newInstance(arg0.getClass().getComponentType(), arg1);
    }
}

