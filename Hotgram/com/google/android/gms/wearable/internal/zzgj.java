package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;

public final class zzgj {
    public static IntentFilter zza(String arg2, Uri arg3, int arg4) {
        IntentFilter v0 = new IntentFilter(arg2);
        if(arg3.getScheme() != null) {
            v0.addDataScheme(arg3.getScheme());
        }

        if(arg3.getAuthority() != null) {
            v0.addDataAuthority(arg3.getAuthority(), Integer.toString(arg3.getPort()));
        }

        if(arg3.getPath() != null) {
            v0.addDataPath(arg3.getPath(), arg4);
        }

        return v0;
    }

    public static IntentFilter zzc(String arg2) {
        IntentFilter v0 = new IntentFilter(arg2);
        v0.addDataScheme("wear");
        v0.addDataAuthority("*", null);
        return v0;
    }
}

