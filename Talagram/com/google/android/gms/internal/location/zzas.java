package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzas {
    private final zzbj zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv;
    private boolean zzcw;
    private final Map zzcx;
    private final Map zzcy;
    private final Map zzcz;

    public zzas(Context arg2, zzbj arg3) {
        super();
        this.zzcv = null;
        this.zzcw = false;
        this.zzcx = new HashMap();
        this.zzcy = new HashMap();
        this.zzcz = new HashMap();
        this.zzcu = arg2;
        this.zzcb = arg3;
    }

    public final Location getLastLocation() {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zza(this.zzcu.getPackageName());
    }

    public final void removeAllListeners() {
        Object v2;
        zzaj v3;
        Iterator v1_1;
        Map v0 = this.zzcx;
        __monitor_enter(v0);
        try {
            v1_1 = this.zzcx.values().iterator();
            while(true) {
                v3 = null;
                if(!v1_1.hasNext()) {
                    break;
                }

                v2 = v1_1.next();
                if(v2 == null) {
                    continue;
                }

                this.zzcb.getService().zza(zzbf.zza(((zzx)v2), v3));
            }

            this.zzcx.clear();
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_63;
        }

        Map v1_2 = this.zzcz;
        __monitor_enter(v1_2);
        try {
            Iterator v0_2 = this.zzcz.values().iterator();
            while(v0_2.hasNext()) {
                v2 = v0_2.next();
                if(v2 == null) {
                    continue;
                }

                this.zzcb.getService().zza(zzbf.zza(((zzu)v2), v3));
            }

            this.zzcz.clear();
            __monitor_exit(v1_2);
        }
        catch(Throwable v0_1) {
            goto label_60;
        }

        v0 = this.zzcy;
        __monitor_enter(v0);
        try {
            v1_1 = this.zzcy.values().iterator();
            while(v1_1.hasNext()) {
                v2 = v1_1.next();
                if(v2 == null) {
                    continue;
                }

                this.zzcb.getService().zza(new zzo(2, ((zzm)v3), ((zzr)v2).asBinder(), ((IBinder)v3)));
            }

            this.zzcy.clear();
            __monitor_exit(v0);
            return;
        label_57:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_57;
        }

        throw v1;
        try {
        label_60:
            __monitor_exit(v1_2);
        }
        catch(Throwable v0_1) {
            goto label_60;
        }

        throw v0_1;
        try {
        label_63:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_63;
        }

        throw v1;
    }

    private final zzax zza(ListenerHolder arg4) {
        zzax v1_1;
        Map v0 = this.zzcx;
        __monitor_enter(v0);
        try {
            Object v1 = this.zzcx.get(arg4.getListenerKey());
            if(v1 == null) {
                v1_1 = new zzax(arg4);
            }

            this.zzcx.put(arg4.getListenerKey(), v1_1);
            __monitor_exit(v0);
            return v1_1;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_14;
        }

        throw v4;
    }

    public final LocationAvailability zza() {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zzb(this.zzcu.getPackageName());
    }

    public final void zza(PendingIntent arg10, zzaj arg11) {
        this.zzcb.checkConnected();
        IInterface v0 = this.zzcb.getService();
        zzbf v8 = null;
        IBinder v11 = arg11 != null ? arg11.asBinder() : null;
        IBinder v7 = v11;
        super(2, null, null, arg10, null, v7);
        ((zzao)v0).zza(v8);
    }

    public final void zza(Location arg2) {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(arg2);
    }

    public final void zza(ListenerKey arg3, zzaj arg4) {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(arg3, "Invalid null listener key");
        Map v0 = this.zzcx;
        __monitor_enter(v0);
        try {
            Object v3_1 = this.zzcx.remove(arg3);
            if(v3_1 != null) {
                ((zzax)v3_1).release();
                this.zzcb.getService().zza(zzbf.zza(((zzx)v3_1), arg4));
            }

            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }

    public final void zza(zzaj arg2) {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(arg2);
    }

    public final void zza(zzbd arg10, ListenerHolder arg11, zzaj arg12) {
        this.zzcb.checkConnected();
        zzat v11 = this.zzb(arg11);
        IInterface v0 = this.zzcb.getService();
        zzbf v8 = null;
        IBinder v6 = ((zzu)v11).asBinder();
        IBinder v11_1 = arg12 != null ? arg12.asBinder() : null;
        IBinder v7 = v11_1;
        super(1, arg10, null, null, v6, v7);
        ((zzao)v0).zza(v8);
    }

    public final void zza(LocationRequest arg9, PendingIntent arg10, zzaj arg11) {
        this.zzcb.checkConnected();
        IInterface v0 = this.zzcb.getService();
        zzbd v3 = zzbd.zza(arg9);
        zzbf v9 = null;
        IBinder v11 = arg11 != null ? arg11.asBinder() : null;
        IBinder v7 = v11;
        super(1, v3, null, arg10, null, v7);
        ((zzao)v0).zza(v9);
    }

    public final void zza(LocationRequest arg9, ListenerHolder arg10, zzaj arg11) {
        this.zzcb.checkConnected();
        zzax v10 = this.zza(arg10);
        IInterface v0 = this.zzcb.getService();
        zzbd v3 = zzbd.zza(arg9);
        zzbf v9 = null;
        IBinder v4 = ((zzx)v10).asBinder();
        IBinder v10_1 = arg11 != null ? arg11.asBinder() : null;
        IBinder v7 = v10_1;
        super(1, v3, v4, null, null, v7);
        ((zzao)v0).zza(v9);
    }

    public final void zza(boolean arg2) {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(arg2);
        this.zzcw = arg2;
    }

    private final zzat zzb(ListenerHolder arg4) {
        zzat v1_1;
        Map v0 = this.zzcz;
        __monitor_enter(v0);
        try {
            Object v1 = this.zzcz.get(arg4.getListenerKey());
            if(v1 == null) {
                v1_1 = new zzat(arg4);
            }

            this.zzcz.put(arg4.getListenerKey(), v1_1);
            __monitor_exit(v0);
            return v1_1;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_14;
        }

        throw v4;
    }

    public final void zzb() {
        if(this.zzcw) {
            this.zza(false);
        }
    }

    public final void zzb(ListenerKey arg3, zzaj arg4) {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(arg3, "Invalid null listener key");
        Map v0 = this.zzcz;
        __monitor_enter(v0);
        try {
            Object v3_1 = this.zzcz.remove(arg3);
            if(v3_1 != null) {
                ((zzat)v3_1).release();
                this.zzcb.getService().zza(zzbf.zza(((zzu)v3_1), arg4));
            }

            __monitor_exit(v0);
            return;
        label_17:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        throw v3;
    }
}

