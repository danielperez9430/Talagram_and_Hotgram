package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;

final class zzh extends GmsClientSupervisor implements Handler$Callback {
    private final Handler mHandler;
    private final Context zzau;
    @GuardedBy(value="mConnectionStatus") private final HashMap zztr;
    private final ConnectionTracker zzts;
    private final long zztt;
    private final long zztu;

    zzh(Context arg3) {
        super();
        this.zztr = new HashMap();
        this.zzau = arg3.getApplicationContext();
        this.mHandler = new Handler(arg3.getMainLooper(), ((Handler$Callback)this));
        this.zzts = ConnectionTracker.getInstance();
        this.zztt = 5000;
        this.zztu = 300000;
    }

    protected final boolean bindService(ConnectionStatusConfig arg5, ServiceConnection arg6, String arg7) {
        zzi v1_1;
        Preconditions.checkNotNull(arg6, "ServiceConnection must not be null");
        HashMap v0 = this.zztr;
        __monitor_enter(v0);
        try {
            Object v1 = this.zztr.get(arg5);
            if(v1 == null) {
                v1_1 = new zzi(this, arg5);
                v1_1.zza(arg6, arg7);
                v1_1.zzj(arg7);
                this.zztr.put(arg5, v1_1);
            }
            else {
                this.mHandler.removeMessages(0, arg5);
                if(!((zzi)v1).zza(arg6)) {
                    ((zzi)v1).zza(arg6, arg7);
                    switch(((zzi)v1).getState()) {
                        case 1: {
                            goto label_25;
                        }
                        case 2: {
                            goto label_23;
                        }
                    }

                    goto label_28;
                label_23:
                    ((zzi)v1).zzj(arg7);
                    goto label_28;
                label_25:
                    arg6.onServiceConnected(((zzi)v1).getComponentName(), ((zzi)v1).getBinder());
                }
                else {
                    goto label_31;
                }
            }

        label_28:
            __monitor_exit(v0);
            return v1_1.isBound();
        label_31:
            String v5_1 = String.valueOf(arg5);
            StringBuilder v1_2 = new StringBuilder(String.valueOf(v5_1).length() + 81);
            v1_2.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
            v1_2.append(v5_1);
            throw new IllegalStateException(v1_2.toString());
        label_45:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_45;
        }

        throw v5;
    }

    public final boolean handleMessage(Message arg8) {
        Object v2;
        Object v8_1;
        switch(arg8.what) {
            case 0: {
                goto label_42;
            }
            case 1: {
                goto label_5;
            }
        }

        return 0;
    label_5:
        HashMap v0 = this.zztr;
        __monitor_enter(v0);
        try {
            v8_1 = arg8.obj;
            v2 = this.zztr.get(v8_1);
            if(v2 != null && ((zzi)v2).getState() == 3) {
                String v4 = String.valueOf(v8_1);
                StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 47);
                v6.append("Timeout waiting for ServiceConnection callback ");
                v6.append(v4);
                Log.wtf("GmsClientSupervisor", v6.toString(), new Exception());
                ComponentName v3 = ((zzi)v2).getComponentName();
                if(v3 == null) {
                    v3 = ((ConnectionStatusConfig)v8_1).getComponentName();
                }

                if(v3 == null) {
                    v3 = new ComponentName(((ConnectionStatusConfig)v8_1).getPackage(), "unknown");
                }

                ((zzi)v2).onServiceDisconnected(v3);
            }

            __monitor_exit(v0);
            return 1;
        label_40:
            __monitor_exit(v0);
        }
        catch(Throwable v8) {
            goto label_40;
        }

        throw v8;
    label_42:
        v0 = this.zztr;
        __monitor_enter(v0);
        try {
            v8_1 = arg8.obj;
            v2 = this.zztr.get(v8_1);
            if(v2 != null && (((zzi)v2).zzcv())) {
                if(((zzi)v2).isBound()) {
                    ((zzi)v2).zzk("GmsClientSupervisor");
                }

                this.zztr.remove(v8_1);
            }

            __monitor_exit(v0);
            return 1;
        label_59:
            __monitor_exit(v0);
        }
        catch(Throwable v8) {
            goto label_59;
        }

        throw v8;
    }

    @VisibleForTesting public final void resetForTesting() {
        HashMap v0 = this.zztr;
        __monitor_enter(v0);
        try {
            Iterator v1_1 = this.zztr.values().iterator();
            while(v1_1.hasNext()) {
                Object v2 = v1_1.next();
                this.mHandler.removeMessages(0, zzi.zza(((zzi)v2)));
                if(!((zzi)v2).isBound()) {
                    continue;
                }

                ((zzi)v2).zzk("GmsClientSupervisor");
            }

            this.zztr.clear();
            __monitor_exit(v0);
            return;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_22;
        }

        throw v1;
    }

    protected final void unbindService(ConnectionStatusConfig arg4, ServiceConnection arg5, String arg6) {
        StringBuilder v1_1;
        String v4_1;
        Preconditions.checkNotNull(arg5, "ServiceConnection must not be null");
        HashMap v0 = this.zztr;
        __monitor_enter(v0);
        try {
            Object v1 = this.zztr.get(arg4);
            if(v1 != null) {
                if(((zzi)v1).zza(arg5)) {
                    ((zzi)v1).zzb(arg5, arg6);
                    if(((zzi)v1).zzcv()) {
                        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, arg4), this.zztt);
                    }

                    __monitor_exit(v0);
                    return;
                }

                v4_1 = String.valueOf(arg4);
                v1_1 = new StringBuilder(String.valueOf(v4_1).length() + 76);
                v1_1.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                v1_1.append(v4_1);
                throw new IllegalStateException(v1_1.toString());
            }

            v4_1 = String.valueOf(arg4);
            v1_1 = new StringBuilder(String.valueOf(v4_1).length() + 50);
            v1_1.append("Nonexistent connection status for service config: ");
            v1_1.append(v4_1);
            throw new IllegalStateException(v1_1.toString());
        label_47:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_47;
        }

        throw v4;
    }

    static HashMap zza(zzh arg0) {
        return arg0.zztr;
    }

    static Handler zzb(zzh arg0) {
        return arg0.mHandler;
    }

    static Context zzc(zzh arg0) {
        return arg0.zzau;
    }

    static ConnectionTracker zzd(zzh arg0) {
        return arg0.zzts;
    }

    static long zze(zzh arg2) {
        return arg2.zztu;
    }
}

