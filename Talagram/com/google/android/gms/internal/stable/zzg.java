package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.net.Uri;

public final class zzg extends zza {
    private static final Uri CONTENT_URI;

    static {
        zzg.CONTENT_URI = Uri.parse("content://com.google.settings/partner");
    }

    public static int getInt(ContentResolver arg0, String arg1, int arg2) {
        String v0 = zzg.getString(arg0, arg1);
        int v1 = -1;
        if(v0 != null) {
            try {
                return Integer.parseInt(v0);
            }
            catch(NumberFormatException ) {
                return v1;
            }
        }

        return v1;
    }

    private static String getString(ContentResolver arg1, String arg2) {
        return zzg.zza(arg1, zzg.CONTENT_URI, arg2);
    }

    public static String zza(ContentResolver arg0, String arg1, String arg2) {
        String v0 = zzg.getString(arg0, arg1);
        if(v0 == null) {
            v0 = arg2;
        }

        return v0;
    }
}

