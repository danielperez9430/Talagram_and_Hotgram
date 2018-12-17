package com.google.android.gms.internal.config;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

public final class zzar {
    private boolean zzap;
    private int zzaz;
    private long zzbd;
    private Map zzbe;

    public zzar() {
        this(-1);
    }

    @VisibleForTesting private zzar(long arg7) {
        this(0, -1, null, false);
    }

    private zzar(int arg1, long arg2, Map arg4, boolean arg5) {
        super();
        this.zzaz = 0;
        this.zzbd = -1;
        this.zzbe = new HashMap();
        this.zzap = false;
    }

    public final int getLastFetchStatus() {
        return this.zzaz;
    }

    public final boolean isDeveloperModeEnabled() {
        return this.zzap;
    }

    public final void zza(String arg2, zzal arg3) {
        this.zzbe.put(arg2, arg3);
    }

    public final void zza(Map arg1) {
        this.zzbe = arg1;
    }

    public final void zza(boolean arg1) {
        this.zzap = arg1;
    }

    public final void zzc(long arg1) {
        this.zzbd = arg1;
    }

    public final void zzc(String arg2) {
        if(this.zzbe.get(arg2) == null) {
            return;
        }

        this.zzbe.remove(arg2);
    }

    public final void zzf(int arg1) {
        this.zzaz = arg1;
    }

    public final Map zzr() {
        return this.zzbe;
    }

    public final long zzs() {
        return this.zzbd;
    }
}

