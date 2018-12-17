package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences$Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbe {
    private final long zzabv;
    @VisibleForTesting private final String zzaoa;
    private final String zzaob;
    private final String zzaoc;

    private zzbe(zzba arg3, String arg4, long arg5) {
        this.zzany = arg3;
        super();
        Preconditions.checkNotEmpty(arg4);
        boolean v3 = arg5 > 0 ? true : false;
        Preconditions.checkArgument(v3);
        this.zzaoa = String.valueOf(arg4).concat(":start");
        this.zzaob = String.valueOf(arg4).concat(":count");
        this.zzaoc = String.valueOf(arg4).concat(":value");
        this.zzabv = arg5;
    }

    zzbe(zzba arg1, String arg2, long arg3, zzbb arg5) {
        this(arg1, arg2, arg3);
    }

    public final void zzc(String arg7, long arg8) {
        this.zzany.zzaf();
        long v0 = 0;
        if(this.zzfn() == v0) {
            this.zzfl();
        }

        if(arg7 == null) {
            arg7 = "";
        }

        arg8 = zzba.zza(this.zzany).getLong(this.zzaob, v0);
        int v2 = Long.compare(arg8, v0);
        v0 = 1;
        if(v2 <= 0) {
            SharedPreferences$Editor v8 = zzba.zza(this.zzany).edit();
            v8.putString(this.zzaoc, arg7);
            v8.putLong(this.zzaob, v0);
            v8.apply();
            return;
        }

        arg8 += v0;
        int v0_1 = (this.zzany.zzgm().zzmd().nextLong() & 9223372036854775807L) < 9223372036854775807L / arg8 ? 1 : 0;
        SharedPreferences$Editor v1 = zzba.zza(this.zzany).edit();
        if(v0_1 != 0) {
            v1.putString(this.zzaoc, arg7);
        }

        v1.putLong(this.zzaob, arg8);
        v1.apply();
    }

    private final void zzfl() {
        this.zzany.zzaf();
        long v0 = this.zzany.zzbx().currentTimeMillis();
        SharedPreferences$Editor v2 = zzba.zza(this.zzany).edit();
        v2.remove(this.zzaob);
        v2.remove(this.zzaoc);
        v2.putLong(this.zzaoa, v0);
        v2.apply();
    }

    public final Pair zzfm() {
        // Method was not decompiled
    }

    private final long zzfn() {
        return zzba.zza(this.zzany).getLong(this.zzaoa, 0);
    }
}

