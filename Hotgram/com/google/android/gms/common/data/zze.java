package com.google.android.gms.common.data;

final class zze implements StringFilter {
    zze() {
        super();
    }

    public final boolean matches(String arg3, String arg4) {
        if(!arg3.startsWith(arg4)) {
            String v0 = " ";
            arg4 = String.valueOf(arg4);
            arg4 = arg4.length() != 0 ? v0.concat(arg4) : new String(v0);
            if(arg3.contains(((CharSequence)arg4))) {
                return 1;
            }

            return 0;
        }

        return 1;
    }
}

