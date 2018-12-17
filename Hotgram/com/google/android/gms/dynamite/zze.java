package com.google.android.gms.dynamite;

import android.content.Context;

final class zze implements VersionPolicy {
    zze() {
        super();
    }

    public final SelectionResult selectModule(Context arg5, String arg6, IVersions arg7) {
        SelectionResult v0 = new SelectionResult();
        v0.localVersion = arg7.getLocalVersion(arg5, arg6);
        int v5 = v0.localVersion != 0 ? arg7.getRemoteVersion(arg5, arg6, false) : arg7.getRemoteVersion(arg5, arg6, true);
        v0.remoteVersion = v5;
        if(v0.localVersion == 0 && v0.remoteVersion == 0) {
            v0.selection = 0;
        }
        else if(v0.localVersion >= v0.remoteVersion) {
            v0.selection = -1;
        }
        else {
            v0.selection = 1;
        }

        return v0;
    }
}

