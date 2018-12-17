package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences$Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbc {
    private boolean value;
    private final boolean zzanw;
    private boolean zzanx;
    private final String zzoj;

    public zzbc(zzba arg1, String arg2, boolean arg3) {
        this.zzany = arg1;
        super();
        Preconditions.checkNotEmpty(arg2);
        this.zzoj = arg2;
        this.zzanw = true;
    }

    public final boolean get() {
        if(!this.zzanx) {
            this.zzanx = true;
            this.value = zzba.zza(this.zzany).getBoolean(this.zzoj, this.zzanw);
        }

        return this.value;
    }

    public final void set(boolean arg3) {
        SharedPreferences$Editor v0 = zzba.zza(this.zzany).edit();
        v0.putBoolean(this.zzoj, arg3);
        v0.apply();
        this.value = arg3;
    }
}

