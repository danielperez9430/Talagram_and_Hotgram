package com.google.android.gms.common.data;

final class zzc implements StringFilter {
    zzc() {
        super();
    }

    public final boolean matches(String arg1, String arg2) {
        return arg1.contains(((CharSequence)arg2));
    }
}

