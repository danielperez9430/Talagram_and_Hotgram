package com.google.android.gms.common.api.internal;

import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzck {
    private final Map zzil;
    public static final Status zzmm;
    private static final BasePendingResult[] zzmn;
    @VisibleForTesting final Set zzmo;
    private final zzcn zzmp;

    static {
        zzck.zzmm = new Status(8, "The connection to Google Play services was lost");
        zzck.zzmn = new BasePendingResult[0];
    }

    public zzck(Map arg2) {
        super();
        this.zzmo = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.zzmp = new zzcl(this);
        this.zzil = arg2;
    }

    public final void release() {
        Object[] v0 = this.zzmo.toArray(zzck.zzmn);
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = v0[v3];
            zzcn v5 = null;
            ((BasePendingResult)v4).zza(v5);
            if(((PendingResult)v4).zzo() == null) {
                if(!((BasePendingResult)v4).zzw()) {
                    goto label_43;
                }

                goto label_14;
            }
            else {
                ((PendingResult)v4).setResultCallback(((ResultCallback)v5));
                IBinder v6 = this.zzil.get(v4.getClientKey()).getServiceBrokerBinder();
                if(((BasePendingResult)v4).isReady()) {
                    ((BasePendingResult)v4).zza(new zzcm(((BasePendingResult)v4), ((zzc)v5), v6, ((zzcl)v5)));
                    goto label_14;
                }
                else {
                    if(v6 != null && (v6.isBinderAlive())) {
                        zzcm v7 = new zzcm(((BasePendingResult)v4), ((zzc)v5), v6, ((zzcl)v5));
                        ((BasePendingResult)v4).zza(((zzcn)v7));
                        try {
                            v6.linkToDeath(((IBinder$DeathRecipient)v7), 0);
                            goto label_14;
                        }
                        catch(RemoteException ) {
                            goto label_38;
                        }
                    }

                    ((BasePendingResult)v4).zza(v5);
                label_38:
                    ((PendingResult)v4).cancel();
                    ((zzc)v5).remove(((PendingResult)v4).zzo().intValue());
                label_14:
                    this.zzmo.remove(v4);
                }
            }

        label_43:
        }
    }

    final void zzb(BasePendingResult arg2) {
        this.zzmo.add(arg2);
        arg2.zza(this.zzmp);
    }

    public final void zzce() {
        Object[] v0 = this.zzmo.toArray(zzck.zzmn);
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].zzb(zzck.zzmm);
        }
    }
}

