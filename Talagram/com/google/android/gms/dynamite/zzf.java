package com.google.android.gms.dynamite;

import android.content.Context;

final class zzf implements VersionPolicy {
    zzf() {
        super();
    }

    public final SelectionResult selectModule(Context arg3, String arg4, IVersions arg5) {
        int v3;
        SelectionResult v0 = new SelectionResult();
        v0.localVersion = arg5.getLocalVersion(arg3, arg4);
        v0.remoteVersion = arg5.getRemoteVersion(arg3, arg4, true);
        if(v0.localVersion == 0 && v0.remoteVersion == 0) {
            v3 = 0;
            goto label_12;
        }
        else if(v0.remoteVersion >= v0.localVersion) {
            v0.selection = 1;
        }
        else {
            v3 = -1;
        label_12:
            v0.selection = v3;
        }

        return v0;
    }
}

