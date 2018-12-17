package com.google.android.gms.internal.places;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

final class zzjx implements PrivilegedExceptionAction {
    zzjx() {
        super();
    }

    public final Object run() {
        Object v4;
        Class v0 = Unsafe.class;
        Field[] v1 = v0.getDeclaredFields();
        int v2 = v1.length;
        int v3;
        for(v3 = 0; true; ++v3) {
            v4 = null;
            if(v3 >= v2) {
                return v4;
            }

            Field v5 = v1[v3];
            v5.setAccessible(true);
            v4 = v5.get(v4);
            if(v0.isInstance(v4)) {
                return v0.cast(v4);
            }
        }

        return v4;
    }
}

