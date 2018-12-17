package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.e;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ad extends Service {
    private final Object lock;
    final ExecutorService zzh;
    private Binder zzi;
    private int zzj;
    private int zzk;

    public ad() {
        super();
        String v1 = "Firebase-";
        String v2 = String.valueOf(this.getClass().getSimpleName());
        v1 = v2.length() != 0 ? v1.concat(v2) : new String(v1);
        this.zzh = Executors.newSingleThreadExecutor(new NamedThreadFactory(v1));
        this.lock = new Object();
        this.zzk = 0;
    }

    public final IBinder onBind(Intent arg2) {
        Binder v2_1;
        __monitor_enter(this);
        try {
            if(Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "Service received bind request");
            }

            if(this.zzi == null) {
                this.zzi = new ah(this);
            }

            v2_1 = this.zzi;
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return ((IBinder)v2_1);
    }

    public final int onStartCommand(Intent arg2, int arg3, int arg4) {
        Object v3 = this.lock;
        __monitor_enter(v3);
        try {
            this.zzj = arg4;
            ++this.zzk;
            __monitor_exit(v3);
        }
        catch(Throwable v2) {
            try {
            label_23:
                __monitor_exit(v3);
            }
            catch(Throwable v2) {
                goto label_23;
            }

            throw v2;
        }

        Intent v3_1 = this.zzb(arg2);
        arg4 = 2;
        if(v3_1 == null) {
            this.zza(arg2);
            return arg4;
        }

        if(this.zzc(v3_1)) {
            this.zza(arg2);
            return arg4;
        }

        this.zzh.execute(new ae(this, v3_1, arg2));
        return 3;
    }

    private final void zza(Intent arg2) {
        if(arg2 != null) {
            e.completeWakefulIntent(arg2);
        }

        Object v2 = this.lock;
        __monitor_enter(v2);
        try {
            --this.zzk;
            if(this.zzk == 0) {
                this.stopSelfResult(this.zzj);
            }

            __monitor_exit(v2);
            return;
        label_14:
            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            goto label_14;
        }

        throw v0;
    }

    static void zza(ad arg0, Intent arg1) {
        arg0.zza(arg1);
    }

    protected Intent zzb(Intent arg1) {
        return arg1;
    }

    public boolean zzc(Intent arg1) {
        return 0;
    }

    public abstract void zzd(Intent arg1);
}

