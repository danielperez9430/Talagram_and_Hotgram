package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzah;
import com.google.android.gms.maps.model.RuntimeRemoteException;

final class zzm implements OnLocationChangedListener {
    zzm(zzl arg1, zzah arg2) {
        this.zzu = arg2;
        super();
    }

    public final void onLocationChanged(Location arg2) {
        try {
            this.zzu.zza(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

