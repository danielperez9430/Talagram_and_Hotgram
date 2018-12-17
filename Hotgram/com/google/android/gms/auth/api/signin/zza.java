package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class zza implements Comparator {
    static final Comparator zzq;

    static {
        zza.zzq = new zza();
    }

    private zza() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return GoogleSignInAccount.zza(((Scope)arg1), ((Scope)arg2));
    }
}

