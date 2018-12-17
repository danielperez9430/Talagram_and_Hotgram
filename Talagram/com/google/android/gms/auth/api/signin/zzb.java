package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class zzb implements Comparator {
    zzb() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return ((Scope)arg1).getScopeUri().compareTo(((Scope)arg2).getScopeUri());
    }
}

