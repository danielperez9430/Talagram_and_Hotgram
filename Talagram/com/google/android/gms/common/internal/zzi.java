package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class zzi implements ServiceConnection {
    private ComponentName mComponentName;
    private int mState;
    private IBinder zzry;
    private final Set zztv;
    private boolean zztw;
    private final ConnectionStatusConfig zztx;

    public zzi(zzh arg1, ConnectionStatusConfig arg2) {
        this.zzty = arg1;
        super();
        this.zztx = arg2;
        this.zztv = new HashSet();
        this.mState = 2;
    }

    public final IBinder getBinder() {
        return this.zzry;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zztw;
    }

    public final void onServiceConnected(ComponentName arg5, IBinder arg6) {
        HashMap v0 = zzh.zza(this.zzty);
        __monitor_enter(v0);
        try {
            zzh.zzb(this.zzty).removeMessages(1, this.zztx);
            this.zzry = arg6;
            this.mComponentName = arg5;
            Iterator v1 = this.zztv.iterator();
            while(v1.hasNext()) {
                v1.next().onServiceConnected(arg5, arg6);
            }

            this.mState = 1;
            __monitor_exit(v0);
            return;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_21;
        }

        throw v5;
    }

    public final void onServiceDisconnected(ComponentName arg5) {
        HashMap v0 = zzh.zza(this.zzty);
        __monitor_enter(v0);
        try {
            zzh.zzb(this.zzty).removeMessages(1, this.zztx);
            this.zzry = null;
            this.mComponentName = arg5;
            Iterator v1 = this.zztv.iterator();
            while(v1.hasNext()) {
                v1.next().onServiceDisconnected(arg5);
            }

            this.mState = 2;
            __monitor_exit(v0);
            return;
        label_23:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_23;
        }

        throw v5;
    }

    static ConnectionStatusConfig zza(zzi arg0) {
        return arg0.zztx;
    }

    public final void zza(ServiceConnection arg5, String arg6) {
        zzh.zzd(this.zzty).logConnectService(zzh.zzc(this.zzty), arg5, arg6, this.zztx.getStartServiceIntent(zzh.zzc(this.zzty)));
        this.zztv.add(arg5);
    }

    public final boolean zza(ServiceConnection arg2) {
        return this.zztv.contains(arg2);
    }

    public final void zzb(ServiceConnection arg2, String arg3) {
        zzh.zzd(this.zzty).logDisconnectService(zzh.zzc(this.zzty), arg2);
        this.zztv.remove(arg2);
    }

    public final boolean zzcv() {
        return this.zztv.isEmpty();
    }

    public final void zzj(String arg8) {
        this.mState = 3;
        this.zztw = zzh.zzd(this.zzty).bindService(zzh.zzc(this.zzty), arg8, this.zztx.getStartServiceIntent(zzh.zzc(this.zzty)), this, this.zztx.getBindFlags());
        if(this.zztw) {
            zzh.zzb(this.zzty).sendMessageDelayed(zzh.zzb(this.zzty).obtainMessage(1, this.zztx), zzh.zze(this.zzty));
            return;
        }

        this.mState = 2;
        try {
            zzh.zzd(this.zzty).unbindService(zzh.zzc(this.zzty), ((ServiceConnection)this));
            return;
        }
        catch(IllegalArgumentException ) {
            return;
        }
    }

    public final void zzk(String arg3) {
        zzh.zzb(this.zzty).removeMessages(1, this.zztx);
        zzh.zzd(this.zzty).unbindService(zzh.zzc(this.zzty), ((ServiceConnection)this));
        this.zztw = false;
        this.mState = 2;
    }
}

