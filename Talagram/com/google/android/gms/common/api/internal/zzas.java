package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

final class zzas implements ConnectionCallbacks, OnConnectionFailedListener {
    zzas(zzaj arg1, zzak arg2) {
        this(arg1);
    }

    private zzas(zzaj arg1) {
        this.zzhv = arg1;
        super();
    }

    public final void onConnected(Bundle arg3) {
        zzaj.zzf(this.zzhv).signIn(new zzaq(this.zzhv));
    }

    public final void onConnectionFailed(ConnectionResult arg2) {
        zzaj.zzc(this.zzhv).lock();
        try {
            if(zzaj.zzb(this.zzhv, arg2)) {
                zzaj.zzi(this.zzhv);
                zzaj.zzj(this.zzhv);
            }
            else {
                zzaj.zza(this.zzhv, arg2);
            }
        }
        catch(Throwable v2) {
            zzaj.zzc(this.zzhv).unlock();
            throw v2;
        }

        zzaj.zzc(this.zzhv).unlock();
    }

    public final void onConnectionSuspended(int arg1) {
    }
}

