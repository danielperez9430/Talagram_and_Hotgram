package com.google.android.gms.common.data;

final class zzd implements StringFilter {
    zzd() {
        super();
    }

    public final boolean matches(String arg1, String arg2) {
        return arg1.startsWith(arg2);
    }
}

