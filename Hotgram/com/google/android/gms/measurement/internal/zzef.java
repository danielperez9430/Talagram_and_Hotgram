package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting public final class zzef implements ServiceConnection, BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private volatile boolean zzasm;
    private volatile zzao zzasn;

    protected zzef(zzdr arg1) {
        this.zzasg = arg1;
        super();
    }

    public final void onConnected(Bundle arg4) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        __monitor_enter(this);
        zzao v4 = null;
        try {
            IInterface v0 = this.zzasn.getService();
            if(!zzn.zzia()) {
                this.zzasn = v4;
            }

            this.zzasg.zzgn().zzc(new zzei(this, ((zzag)v0)));
            goto label_20;
        }
        catch(Throwable v4_1) {
        }
        catch(IllegalStateException ) {
            try {
                this.zzasn = v4;
                this.zzasm = false;
            label_20:
                __monitor_exit(this);
                return;
            }
            catch(Throwable v4_1) {
            label_16:
                try {
                    __monitor_exit(this);
                }
                catch(Throwable v4_1) {
                    goto label_16;
                }

                throw v4_1;
            }
        }
    }

    public final void onConnectionFailed(ConnectionResult arg3) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzap v0 = this.zzasg.zzadj.zzkf();
        if(v0 != null) {
            v0.zzjg().zzg("Service connection failed", arg3);
        }

        __monitor_enter(this);
        try {
            this.zzasm = false;
            this.zzasn = null;
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            try {
            label_22:
                __monitor_exit(this);
            }
            catch(Throwable v3) {
                goto label_22;
            }

            throw v3;
        }

        this.zzasg.zzgn().zzc(new zzek(this));
    }

    public final void onConnectionSuspended(int arg2) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzasg.zzgo().zzjk().zzbx("Service connection suspended");
        this.zzasg.zzgn().zzc(new zzej(this));
    }

    public final void onServiceConnected(ComponentName arg4, IBinder arg5) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        __monitor_enter(this);
        if(arg5 == null) {
            try {
                this.zzasm = false;
                this.zzasg.zzgo().zzjd().zzbx("Service connected with null binder");
                __monitor_exit(this);
                return;
            }
            catch(Throwable v4) {
                goto label_14;
            }
        }

        IInterface v0 = null;
        try {
            String v1 = arg5.getInterfaceDescriptor();
            if("com.google.android.gms.measurement.internal.IMeasurementService".equals(v1)) {
                if(arg5 == null) {
                }
                else {
                    IInterface v1_1 = arg5.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                    if(!(v1_1 instanceof zzag)) {
                        zzai v1_2 = new zzai(arg5);
                    }

                    v0 = v1_1;
                }

                this.zzasg.zzgo().zzjl().zzbx("Bound to IMeasurementService interface");
                goto label_48;
            }

            this.zzasg.zzgo().zzjd().zzg("Got binder with a wrong descriptor", v1);
            goto label_48;
        }
        catch(Throwable v4) {
        }
        catch(RemoteException ) {
            try {
                this.zzasg.zzgo().zzjd().zzbx("Service connect failed to get IMeasurementService");
            label_48:
                if(v0 == null) {
                    this.zzasm = false;
                }
                else {
                    goto label_57;
                }
            }
            catch(Throwable v4) {
                goto label_14;
            }

            try {
                ConnectionTracker.getInstance().unbindService(this.zzasg.getContext(), zzdr.zza(this.zzasg));
                goto label_62;
            }
            catch(IllegalArgumentException ) {
                goto label_62;
            }
            catch(Throwable v4) {
                goto label_14;
            }

            try {
            label_57:
                this.zzasg.zzgn().zzc(new zzeg(this, ((zzag)v0)));
            label_62:
                __monitor_exit(this);
                return;
            }
            catch(Throwable v4) {
            label_14:
                try {
                    __monitor_exit(this);
                }
                catch(Throwable v4) {
                    goto label_14;
                }

                throw v4;
            }
        }
    }

    public final void onServiceDisconnected(ComponentName arg3) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzasg.zzgo().zzjk().zzbx("Service disconnected");
        this.zzasg.zzgn().zzc(new zzeh(this, arg3));
    }

    static boolean zza(zzef arg0, boolean arg1) {
        arg0.zzasm = false;
        return 0;
    }

    public final void zzc(Intent arg5) {
        this.zzasg.zzaf();
        Context v0 = this.zzasg.getContext();
        ConnectionTracker v1 = ConnectionTracker.getInstance();
        __monitor_enter(this);
        try {
            if(this.zzasm) {
                this.zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
                __monitor_exit(this);
                return;
            }

            this.zzasg.zzgo().zzjl().zzbx("Using local app measurement service");
            this.zzasm = true;
            v1.bindService(v0, arg5, zzdr.zza(this.zzasg), 129);
            __monitor_exit(this);
            return;
        label_29:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_29;
        }

        throw v5;
    }

    public final void zzlg() {
        if(this.zzasn != null && ((this.zzasn.isConnected()) || (this.zzasn.isConnecting()))) {
            this.zzasn.disconnect();
        }

        this.zzasn = null;
    }

    public final void zzlh() {
        this.zzasg.zzaf();
        Context v0 = this.zzasg.getContext();
        __monitor_enter(this);
        try {
            if(this.zzasm) {
                this.zzasg.zzgo().zzjl().zzbx("Connection attempt already in progress");
                __monitor_exit(this);
                return;
            }

            if(this.zzasn != null && (!zzn.zzia() || (this.zzasn.isConnecting()) || (this.zzasn.isConnected()))) {
                this.zzasg.zzgo().zzjl().zzbx("Already awaiting connection attempt");
                __monitor_exit(this);
                return;
            }

            this.zzasn = new zzao(v0, Looper.getMainLooper(), ((BaseConnectionCallbacks)this), ((BaseOnConnectionFailedListener)this));
            this.zzasg.zzgo().zzjl().zzbx("Connecting to remote service");
            this.zzasm = true;
            this.zzasn.checkAvailabilityAndConnect();
            __monitor_exit(this);
            return;
        label_47:
            __monitor_exit(this);
        }
        catch(Throwable v0_1) {
            goto label_47;
        }

        throw v0_1;
    }
}

