package com.google.android.gms.internal.location;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbm {
    public static Looper zza(Looper arg0) {
        if(arg0 != null) {
            return arg0;
        }

        return zzbm.zzc();
    }

    public static Looper zzc() {
        boolean v0 = Looper.myLooper() != null ? true : false;
        Preconditions.checkState(v0, "Can\'t create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}

