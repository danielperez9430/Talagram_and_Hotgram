package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.c.a;

public abstract class c {
    @KeepForSdk public static Object a(b arg0, Class arg1) {
        a v0 = arg0.b(arg1);
        if(v0 == null) {
            return null;
        }

        return v0.a();
    }
}

