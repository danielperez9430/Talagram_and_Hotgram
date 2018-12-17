package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences$Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbf {
    private String value;
    private boolean zzanx;
    private final String zzaod;
    private final String zzoj;

    public zzbf(zzba arg1, String arg2, String arg3) {
        this.zzany = arg1;
        super();
        Preconditions.checkNotEmpty(arg2);
        this.zzoj = arg2;
        this.zzaod = null;
    }

    public final void zzcc(String arg3) {
        if(zzfk.zzu(arg3, this.value)) {
            return;
        }

        SharedPreferences$Editor v0 = zzba.zza(this.zzany).edit();
        v0.putString(this.zzoj, arg3);
        v0.apply();
        this.value = arg3;
    }

    public final String zzjz() {
        if(!this.zzanx) {
            this.zzanx = true;
            this.value = zzba.zza(this.zzany).getString(this.zzoj, null);
        }

        return this.value;
    }
}

