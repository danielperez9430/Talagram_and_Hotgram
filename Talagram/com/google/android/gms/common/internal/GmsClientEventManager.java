package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Handler$Callback {
    @VisibleForTesting public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    private final Handler mHandler;
    private final Object mLock;
    private final GmsClientEventState zztf;
    private final ArrayList zztg;
    @VisibleForTesting private final ArrayList zzth;
    private final ArrayList zzti;
    private volatile boolean zztj;
    private final AtomicInteger zztk;
    private boolean zztl;

    public GmsClientEventManager(Looper arg3, GmsClientEventState arg4) {
        super();
        this.zztg = new ArrayList();
        this.zzth = new ArrayList();
        this.zzti = new ArrayList();
        this.zztj = false;
        this.zztk = new AtomicInteger(0);
        this.zztl = false;
        this.mLock = new Object();
        this.zztf = arg4;
        this.mHandler = new Handler(arg3, ((Handler$Callback)this));
    }

    public final boolean areCallbacksEnabled() {
        return this.zztj;
    }

    public final void disableCallbacks() {
        this.zztj = false;
        this.zztk.incrementAndGet();
    }

    public final void enableCallbacks() {
        this.zztj = true;
    }

    public final boolean handleMessage(Message arg4) {
        if(arg4.what == 1) {
            Object v4 = arg4.obj;
            Object v0 = this.mLock;
            __monitor_enter(v0);
            try {
                if((this.zztj) && (this.zztf.isConnected()) && (this.zztg.contains(v4))) {
                    ((ConnectionCallbacks)v4).onConnected(this.zztf.getConnectionHint());
                }

                __monitor_exit(v0);
                return 1;
            label_20:
                __monitor_exit(v0);
            }
            catch(Throwable v4_1) {
                goto label_20;
            }

            throw v4_1;
        }

        int v4_2 = arg4.what;
        StringBuilder v2 = new StringBuilder(45);
        v2.append("Don\'t know how to handle message: ");
        v2.append(v4_2);
        Log.wtf("GmsClientEvents", v2.toString(), new Exception());
        return 0;
    }

    public final boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg3) {
        Preconditions.checkNotNull(arg3);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zztg.contains(arg3);
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    public final boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener arg3) {
        Preconditions.checkNotNull(arg3);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.zzti.contains(arg3);
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_8;
        }

        throw v3;
    }

    @VisibleForTesting public final void onConnectionFailure(ConnectionResult arg8) {
        int v2 = 0;
        boolean v0 = Looper.myLooper() == this.mHandler.getLooper() ? true : false;
        Preconditions.checkState(v0, "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        Object v0_1 = this.mLock;
        __monitor_enter(v0_1);
        try {
            ArrayList v1 = new ArrayList(this.zzti);
            int v3 = this.zztk.get();
            int v4 = v1.size();
            while(true) {
                if(v2 >= v4) {
                    goto label_37;
                }

                Object v5 = v1.get(v2);
                ++v2;
                if(this.zztj) {
                    if(this.zztk.get() != v3) {
                    }
                    else {
                        if(!this.zzti.contains(v5)) {
                            continue;
                        }

                        ((OnConnectionFailedListener)v5).onConnectionFailed(arg8);
                        continue;
                    }
                }

                break;
            }

            __monitor_exit(v0_1);
            return;
        label_37:
            __monitor_exit(v0_1);
            return;
        label_40:
            __monitor_exit(v0_1);
        }
        catch(Throwable v8) {
            goto label_40;
        }

        throw v8;
    }

    @VisibleForTesting protected final void onConnectionSuccess() {
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.onConnectionSuccess(this.zztf.getConnectionHint());
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    @VisibleForTesting public final void onConnectionSuccess(Bundle arg9) {
        boolean v3 = true;
        boolean v0 = Looper.myLooper() == this.mHandler.getLooper() ? true : false;
        Preconditions.checkState(v0, "onConnectionSuccess must only be called on the Handler thread");
        Object v0_1 = this.mLock;
        __monitor_enter(v0_1);
        try {
            Preconditions.checkState(this.zztl ^ 1);
            this.mHandler.removeMessages(1);
            this.zztl = true;
            if(this.zzth.size() == 0) {
            }
            else {
                v3 = false;
            }

            Preconditions.checkState(v3);
            ArrayList v1 = new ArrayList(this.zztg);
            int v3_1 = this.zztk.get();
            int v4 = v1.size();
            int v5 = 0;
            while(v5 < v4) {
                Object v6 = v1.get(v5);
                ++v5;
                if(!this.zztj) {
                    break;
                }

                if(!this.zztf.isConnected()) {
                    break;
                }

                if(this.zztk.get() != v3_1) {
                    break;
                }

                if(this.zzth.contains(v6)) {
                    continue;
                }

                ((ConnectionCallbacks)v6).onConnected(arg9);
            }

            this.zzth.clear();
            this.zztl = false;
            __monitor_exit(v0_1);
            return;
        label_54:
            __monitor_exit(v0_1);
        }
        catch(Throwable v9) {
            goto label_54;
        }

        throw v9;
    }

    @VisibleForTesting public final void onUnintentionalDisconnection(int arg9) {
        boolean v0 = Looper.myLooper() == this.mHandler.getLooper() ? true : false;
        Preconditions.checkState(v0, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        Object v0_1 = this.mLock;
        __monitor_enter(v0_1);
        try {
            this.zztl = true;
            ArrayList v1 = new ArrayList(this.zztg);
            int v3 = this.zztk.get();
            int v4 = v1.size();
            int v5 = 0;
            while(v5 < v4) {
                Object v6 = v1.get(v5);
                ++v5;
                if(!this.zztj) {
                    break;
                }

                if(this.zztk.get() != v3) {
                    break;
                }

                if(!this.zztg.contains(v6)) {
                    continue;
                }

                ((ConnectionCallbacks)v6).onConnectionSuspended(arg9);
            }

            this.zzth.clear();
            this.zztl = false;
            __monitor_exit(v0_1);
            return;
        label_42:
            __monitor_exit(v0_1);
        }
        catch(Throwable v9) {
            goto label_42;
        }

        throw v9;
    }

    public final void registerConnectionCallbacks(ConnectionCallbacks arg6) {
        Preconditions.checkNotNull(arg6);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zztg.contains(arg6)) {
                String v2 = String.valueOf(arg6);
                StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 62);
                v4.append("registerConnectionCallbacks(): listener ");
                v4.append(v2);
                v4.append(" is already registered");
                Log.w("GmsClientEvents", v4.toString());
            }
            else {
                this.zztg.add(arg6);
            }

            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            try {
            label_34:
                __monitor_exit(v0);
            }
            catch(Throwable v6) {
                goto label_34;
            }

            throw v6;
        }

        if(this.zztf.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, arg6));
        }
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener arg5) {
        Preconditions.checkNotNull(arg5);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(this.zzti.contains(arg5)) {
                String v5_1 = String.valueOf(arg5);
                StringBuilder v3 = new StringBuilder(String.valueOf(v5_1).length() + 67);
                v3.append("registerConnectionFailedListener(): listener ");
                v3.append(v5_1);
                v3.append(" is already registered");
                Log.w("GmsClientEvents", v3.toString());
            }
            else {
                this.zzti.add(arg5);
            }

            __monitor_exit(v0);
            return;
        label_26:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_26;
        }

        throw v5;
    }

    public final void unregisterConnectionCallbacks(ConnectionCallbacks arg5) {
        Preconditions.checkNotNull(arg5);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.zztg.remove(arg5)) {
                String v5_1 = String.valueOf(arg5);
                StringBuilder v3 = new StringBuilder(String.valueOf(v5_1).length() + 52);
                v3.append("unregisterConnectionCallbacks(): listener ");
                v3.append(v5_1);
                v3.append(" not found");
                Log.w("GmsClientEvents", v3.toString());
            }
            else if(this.zztl) {
                this.zzth.add(arg5);
            }

            __monitor_exit(v0);
            return;
        label_28:
            __monitor_exit(v0);
        }
        catch(Throwable v5) {
            goto label_28;
        }

        throw v5;
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener arg5) {
        Preconditions.checkNotNull(arg5);
        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            if(!this.zzti.remove(arg5)) {
                String v5_1 = String.valueOf(arg5);
                StringBuilder v3 = new StringBuilder(String.valueOf(v5_1).length() + 57);
                v3.append("unregisterConnectionFailedListener(): listener ");
                v3.append(v5_1);
                v3.append(" not found");
                Log.w("GmsClientEvents", v3.toString());
            }

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
}

