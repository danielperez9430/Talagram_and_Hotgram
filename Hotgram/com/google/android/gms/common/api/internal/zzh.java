package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

public final class zzh {
    private final Api mApi;
    private final ApiOptions zzcl;
    private final boolean zzeb;
    private final int zzec;

    private zzh(Api arg2) {
        super();
        this.zzeb = true;
        this.mApi = arg2;
        this.zzcl = null;
        this.zzec = System.identityHashCode(this);
    }

    private zzh(Api arg2, ApiOptions arg3) {
        super();
        this.zzeb = false;
        this.mApi = arg2;
        this.zzcl = arg3;
        this.zzec = Objects.hashCode(new Object[]{this.mApi, this.zzcl});
    }

    public final boolean equals(Object arg5) {
        if((((zzh)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof zzh)) {
            return 0;
        }

        if(!this.zzeb && !((zzh)arg5).zzeb && (Objects.equal(this.mApi, ((zzh)arg5).mApi)) && (Objects.equal(this.zzcl, ((zzh)arg5).zzcl))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return this.zzec;
    }

    public static zzh zza(Api arg1, ApiOptions arg2) {
        return new zzh(arg1, arg2);
    }

    public static zzh zza(Api arg1) {
        return new zzh(arg1);
    }

    public final String zzq() {
        return this.mApi.getName();
    }
}

