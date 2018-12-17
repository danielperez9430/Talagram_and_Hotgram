package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class IndoorBuilding {
    @VisibleForTesting final class zza {
        public static final zza zzde;

        static {
            zza.zzde = new zza();
        }

        private zza() {
            super();
        }

        public static IndoorLevel zza(zzq arg1) {
            return new IndoorLevel(arg1);
        }

        public static zzq zza(IBinder arg0) {
            return zzr.zzf(arg0);
        }
    }

    private final zzn zzdc;
    private final zza zzdd;

    public IndoorBuilding(zzn arg2) {
        this(arg2, zza.zzde);
    }

    @VisibleForTesting private IndoorBuilding(zzn arg2, zza arg3) {
        super();
        this.zzdc = Preconditions.checkNotNull(arg2, "delegate");
        this.zzdd = Preconditions.checkNotNull(arg3, "shim");
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof IndoorBuilding)) {
            return 0;
        }

        try {
            return this.zzdc.zzb(((IndoorBuilding)arg2).zzdc);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final int getActiveLevelIndex() {
        try {
            return this.zzdc.getActiveLevelIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getDefaultLevelIndex() {
        try {
            return this.zzdc.getDefaultLevelIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getLevels() {
        ArrayList v1;
        try {
            List v0_1 = this.zzdc.getLevels();
            v1 = new ArrayList(v0_1.size());
            Iterator v0_2 = v0_1.iterator();
            while(v0_2.hasNext()) {
                ((List)v1).add(zza.zza(zza.zza(v0_2.next())));
            }
        }
        catch(RemoteException v0) {
            goto label_17;
        }

        return ((List)v1);
    label_17:
        throw new RuntimeRemoteException(v0);
    }

    public final int hashCode() {
        try {
            return this.zzdc.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isUnderground() {
        try {
            return this.zzdc.isUnderground();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }
}

