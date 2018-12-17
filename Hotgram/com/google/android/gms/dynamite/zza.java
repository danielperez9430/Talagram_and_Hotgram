package com.google.android.gms.dynamite;

import android.content.Context;

final class zza implements IVersions {
    zza() {
        super();
    }

    public final int getLocalVersion(Context arg1, String arg2) {
        return DynamiteModule.getLocalVersion(arg1, arg2);
    }

    public final int getRemoteVersion(Context arg1, String arg2, boolean arg3) {
        return DynamiteModule.getRemoteVersion(arg1, arg2, arg3);
    }
}

