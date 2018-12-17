package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences$Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbd {
    private long value;
    private boolean zzanx;
    private final long zzanz;
    private final String zzoj;

    public zzbd(zzba arg1, String arg2, long arg3) {
        this.zzany = arg1;
        super();
        Preconditions.checkNotEmpty(arg2);
        this.zzoj = arg2;
        this.zzanz = arg3;
    }

    public final long get() {
        if(!this.zzanx) {
            this.zzanx = true;
            this.value = zzba.zza(this.zzany).getLong(this.zzoj, this.zzanz);
        }

        return this.value;
    }

    public final void set(long arg3) {
        SharedPreferences$Editor v0 = zzba.zza(this.zzany).edit();
        v0.putLong(this.zzoj, arg3);
        v0.apply();
        this.value = arg3;
    }
}

