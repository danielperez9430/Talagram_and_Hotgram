package com.google.android.gms.common.api.internal;

import android.os.IBinder$DeathRecipient;
import android.os.IBinder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.zzc;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zzcm implements IBinder$DeathRecipient, zzcn {
    private final WeakReference zzmr;
    private final WeakReference zzms;
    private final WeakReference zzmt;

    zzcm(BasePendingResult arg1, zzc arg2, IBinder arg3, zzcl arg4) {
        this(arg1, null, arg3);
    }

    private zzcm(BasePendingResult arg2, zzc arg3, IBinder arg4) {
        super();
        this.zzms = new WeakReference(arg3);
        this.zzmr = new WeakReference(arg2);
        this.zzmt = new WeakReference(arg4);
    }

    public final void binderDied() {
        this.zzcf();
    }

    public final void zzc(BasePendingResult arg1) {
        this.zzcf();
    }

    private final void zzcf() {
        Object v0 = this.zzmr.get();
        Object v1 = this.zzms.get();
        if(v1 != null && v0 != null) {
            ((zzc)v1).remove(((PendingResult)v0).zzo().intValue());
        }

        v0 = this.zzmt.get();
        if(v0 != null) {
            try {
                ((IBinder)v0).unlinkToDeath(((IBinder$DeathRecipient)this), 0);
                return;
            }
            catch(NoSuchElementException ) {
                return;
            }
        }
    }
}

