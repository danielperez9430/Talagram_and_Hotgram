package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb implements VersionPolicy {
    zzb() {
        super();
    }

    public final SelectionResult selectModule(Context arg4, String arg5, IVersions arg6) {
        SelectionResult v0 = new SelectionResult();
        v0.remoteVersion = arg6.getRemoteVersion(arg4, arg5, true);
        if(v0.remoteVersion != 0) {
            v0.selection = 1;
        }
        else {
            v0.localVersion = arg6.getLocalVersion(arg4, arg5);
            if(v0.localVersion != 0) {
                v0.selection = -1;
            }
        }

        return v0;
    }
}

