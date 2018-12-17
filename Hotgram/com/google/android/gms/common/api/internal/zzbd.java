package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzbd implements zzbp, zzq {
    private final Context mContext;
    private final AbstractClientBuilder zzdh;
    final zzav zzfq;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map zzgi;
    private final GoogleApiAvailabilityLight zzgk;
    final Map zzil;
    private final Condition zziz;
    private final zzbf zzja;
    final Map zzjb;
    private volatile zzbc zzjc;
    private ConnectionResult zzjd;
    int zzje;
    final zzbq zzjf;

    public zzbd(Context arg2, zzav arg3, Lock arg4, Looper arg5, GoogleApiAvailabilityLight arg6, Map arg7, ClientSettings arg8, Map arg9, AbstractClientBuilder arg10, ArrayList arg11, zzbq arg12) {
        super();
        this.zzjb = new HashMap();
        this.zzjd = null;
        this.mContext = arg2;
        this.zzga = arg4;
        this.zzgk = arg6;
        this.zzil = arg7;
        this.zzgf = arg8;
        this.zzgi = arg9;
        this.zzdh = arg10;
        this.zzfq = arg3;
        this.zzjf = arg12;
        int v2 = arg11.size();
        int v3 = 0;
        while(v3 < v2) {
            Object v6 = arg11.get(v3);
            ++v3;
            ((zzp)v6).zza(((zzq)this));
        }

        this.zzja = new zzbf(this, arg5);
        this.zziz = arg4.newCondition();
        this.zzjc = new zzau(this);
    }

    @GuardedBy(value="mLock") public final ConnectionResult blockingConnect() {
        PendingIntent v1;
        this.connect();
        while(true) {
            v1 = null;
            if(!this.isConnecting()) {
                break;
            }

            try {
                this.zziz.await();
                continue;
            }
            catch(InterruptedException ) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, v1);
            }
        }

        if(this.isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }

        if(this.zzjd != null) {
            return this.zzjd;
        }

        return new ConnectionResult(13, v1);
    }

    @GuardedBy(value="mLock") public final ConnectionResult blockingConnect(long arg4, TimeUnit arg6) {
        PendingIntent v0;
        this.connect();
        arg4 = arg6.toNanos(arg4);
        while(true) {
            v0 = null;
            if(!this.isConnecting()) {
                break;
            }

            if(arg4 > 0) {
                goto label_12;
            }

            try {
                this.disconnect();
                return new ConnectionResult(14, v0);
            label_12:
                arg4 = this.zziz.awaitNanos(arg4);
                continue;
            }
            catch(InterruptedException ) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, v0);
            }
        }

        if(this.isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }

        if(this.zzjd != null) {
            return this.zzjd;
        }

        return new ConnectionResult(13, v0);
    }

    @GuardedBy(value="mLock") public final void connect() {
        this.zzjc.connect();
    }

    @GuardedBy(value="mLock") public final void disconnect() {
        if(this.zzjc.disconnect()) {
            this.zzjb.clear();
        }
    }

    public final void dump(String arg6, FileDescriptor arg7, PrintWriter arg8, String[] arg9) {
        String v0 = String.valueOf(arg6).concat("  ");
        arg8.append(((CharSequence)arg6)).append("mState=").println(this.zzjc);
        Iterator v1 = this.zzgi.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            arg8.append(((CharSequence)arg6)).append(((Api)v2).getName()).println(":");
            this.zzil.get(((Api)v2).getClientKey()).dump(v0, arg7, arg8, arg9);
        }
    }

    @GuardedBy(value="mLock") public final ApiMethodImpl enqueue(ApiMethodImpl arg2) {
        ((BasePendingResult)arg2).zzx();
        return this.zzjc.enqueue(arg2);
    }

    @GuardedBy(value="mLock") public final ApiMethodImpl execute(ApiMethodImpl arg2) {
        ((BasePendingResult)arg2).zzx();
        return this.zzjc.execute(arg2);
    }

    @GuardedBy(value="mLock") public final ConnectionResult getConnectionResult(Api arg2) {
        AnyClientKey v2 = arg2.getClientKey();
        if(this.zzil.containsKey(v2)) {
            if(this.zzil.get(v2).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            else if(this.zzjb.containsKey(v2)) {
                return this.zzjb.get(v2);
            }
        }

        return null;
    }

    public final boolean isConnected() {
        return this.zzjc instanceof zzag;
    }

    public final boolean isConnecting() {
        return this.zzjc instanceof zzaj;
    }

    public final boolean maybeSignIn(SignInConnectionListener arg1) {
        return 0;
    }

    public final void maybeSignOut() {
    }

    public final void onConnected(Bundle arg2) {
        this.zzga.lock();
        try {
            this.zzjc.onConnected(arg2);
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

        this.zzga.unlock();
    }

    public final void onConnectionSuspended(int arg2) {
        this.zzga.lock();
        try {
            this.zzjc.onConnectionSuspended(arg2);
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

        this.zzga.unlock();
    }

    static Lock zza(zzbd arg0) {
        return arg0.zzga;
    }

    final void zza(zzbe arg3) {
        this.zzja.sendMessage(this.zzja.obtainMessage(1, arg3));
    }

    public final void zza(ConnectionResult arg2, Api arg3, boolean arg4) {
        this.zzga.lock();
        try {
            this.zzjc.zza(arg2, arg3, arg4);
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

        this.zzga.unlock();
    }

    final void zzb(RuntimeException arg3) {
        this.zzja.sendMessage(this.zzja.obtainMessage(2, arg3));
    }

    static zzbc zzb(zzbd arg0) {
        return arg0.zzjc;
    }

    final void zzbc() {
        this.zzga.lock();
        try {
            this.zzjc = new zzaj(this, this.zzgf, this.zzgi, this.zzgk, this.zzdh, this.zzga, this.mContext);
            this.zzjc.begin();
            this.zziz.signalAll();
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    final void zzbd() {
        this.zzga.lock();
        try {
            this.zzfq.zzaz();
            this.zzjc = new zzag(this);
            this.zzjc.begin();
            this.zziz.signalAll();
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    final void zzf(ConnectionResult arg2) {
        this.zzga.lock();
        try {
            this.zzjd = arg2;
            this.zzjc = new zzau(this);
            this.zzjc.begin();
            this.zziz.signalAll();
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

        this.zzga.unlock();
    }

    @GuardedBy(value="mLock") public final void zzz() {
        if(this.isConnected()) {
            this.zzjc.zzap();
        }
    }
}

