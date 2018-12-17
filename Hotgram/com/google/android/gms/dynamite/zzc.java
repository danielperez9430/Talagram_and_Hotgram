package com.google.android.gms.dynamite;

import android.content.Context;

final class zzc implements VersionPolicy {
    zzc() {
        super();
    }

    public final SelectionResult selectModule(Context arg3, String arg4, IVersions arg5) {
        SelectionResult v0 = new SelectionResult();
        v0.localVersion = arg5.getLocalVersion(arg3, arg4);
        if(v0.localVersion != 0) {
            v0.selection = -1;
        }
        else {
            v0.remoteVersion = arg5.getRemoteVersion(arg3, arg4, true);
            if(v0.remoteVersion != 0) {
                v0.selection = 1;
            }
        }

        return v0;
    }
}

