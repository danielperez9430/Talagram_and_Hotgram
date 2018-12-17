package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzq;

public final class IndoorLevel {
    private final zzq zzdf;

    public IndoorLevel(zzq arg1) {
        super();
        this.zzdf = Preconditions.checkNotNull(arg1);
    }

    public final void activate() {
        try {
            this.zzdf.activate();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof IndoorLevel)) {
            return 0;
        }

        try {
            return this.zzdf.zzb(((IndoorLevel)arg2).zzdf);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final String getName() {
        try {
            return this.zzdf.getName();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getShortName() {
        try {
            return this.zzdf.getShortName();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdf.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }
}

