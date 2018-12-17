package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class ObjectWrapper extends Stub {
    private final Object zzabn;

    private ObjectWrapper(Object arg1) {
        super();
        this.zzabn = arg1;
    }

    public static Object unwrap(IObjectWrapper arg7) {
        if((arg7 instanceof ObjectWrapper)) {
            return ((ObjectWrapper)arg7).zzabn;
        }

        IBinder v7 = arg7.asBinder();
        Field[] v0 = v7.getClass().getDeclaredFields();
        int v2 = v0.length;
        int v3 = 0;
        Field v4 = null;
        int v1 = 0;
        while(v3 < v2) {
            Field v5 = v0[v3];
            if(!v5.isSynthetic()) {
                ++v1;
                v4 = v5;
            }

            ++v3;
        }

        if(v1 == 1) {
            if(!v4.isAccessible()) {
                v4.setAccessible(true);
                try {
                    return v4.get(v7);
                }
                catch(IllegalAccessException v7_1) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", ((Throwable)v7_1));
                }
                catch(NullPointerException v7_2) {
                    throw new IllegalArgumentException("Binder object is null.", ((Throwable)v7_2));
                }
            }

            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }

        int v0_1 = v0.length;
        StringBuilder v2_1 = new StringBuilder(64);
        v2_1.append("Unexpected number of IObjectWrapper declared fields: ");
        v2_1.append(v0_1);
        throw new IllegalArgumentException(v2_1.toString());
    }

    public static IObjectWrapper wrap(Object arg1) {
        return new ObjectWrapper(arg1);
    }
}

