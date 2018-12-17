package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzv;

public final class zzbh implements ServiceConnection {
    private final String packageName;

    zzbh(zzbg arg1, String arg2) {
        this.zzaoe = arg1;
        super();
        this.packageName = arg2;
    }

    public final void onServiceConnected(ComponentName arg2, IBinder arg3) {
        if(arg3 == null) {
            this.zzaoe.zzadj.zzgo().zzjg().zzbx("Install Referrer connection returned with null binder");
            return;
        }

        try {
            zzu v2_1 = zzv.zza(arg3);
            if(v2_1 == null) {
                this.zzaoe.zzadj.zzgo().zzjg().zzbx("Install Referrer Service implementation was not found");
                return;
            }

            this.zzaoe.zzadj.zzgo().zzjj().zzbx("Install Referrer Service connected");
            this.zzaoe.zzadj.zzgn().zzc(new zzbi(this, v2_1, ((ServiceConnection)this)));
            return;
        }
        catch(Exception v2) {
            this.zzaoe.zzadj.zzgo().zzjg().zzg("Exception occurred while calling Install Referrer API", v2);
            return;
        }
    }

    public final void onServiceDisconnected(ComponentName arg2) {
        this.zzaoe.zzadj.zzgo().zzjj().zzbx("Install Referrer Service disconnected");
    }

    static String zza(zzbh arg0) {
        return arg0.packageName;
    }
}

