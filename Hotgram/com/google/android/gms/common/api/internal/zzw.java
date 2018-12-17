package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import android.support.v4.f.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzw implements zzbp {
    private final Looper zzcn;
    private final GoogleApiManager zzcq;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map zzgg;
    private final Map zzgh;
    private final Map zzgi;
    private final zzav zzgj;
    private final GoogleApiAvailabilityLight zzgk;
    private final Condition zzgl;
    private final boolean zzgm;
    private final boolean zzgn;
    private final Queue zzgo;
    @GuardedBy(value="mLock") private boolean zzgp;
    @GuardedBy(value="mLock") private Map zzgq;
    @GuardedBy(value="mLock") private Map zzgr;
    @GuardedBy(value="mLock") private zzz zzgs;
    @GuardedBy(value="mLock") private ConnectionResult zzgt;

    public zzw(Context arg22, Lock arg23, Looper arg24, GoogleApiAvailabilityLight arg25, Map arg26, ClientSettings arg27, Map arg28, AbstractClientBuilder arg29, ArrayList arg30, zzav arg31, boolean arg32) {
        int v20;
        int v18;
        int v19;
        Object v4;
        zzw v0 = this;
        super();
        v0.zzgg = new HashMap();
        v0.zzgh = new HashMap();
        v0.zzgo = new LinkedList();
        v0.zzga = arg23;
        v0.zzcn = arg24;
        v0.zzgl = arg23.newCondition();
        v0.zzgk = arg25;
        v0.zzgj = arg31;
        v0.zzgi = arg28;
        v0.zzgf = arg27;
        v0.zzgm = arg32;
        HashMap v11 = new HashMap();
        Iterator v1 = arg28.keySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            ((Map)v11).put(((Api)v2).getClientKey(), v2);
        }

        HashMap v12 = new HashMap();
        ArrayList v1_1 = arg30;
        int v2_1 = v1_1.size();
        int v3 = 0;
        while(v3 < v2_1) {
            v4 = v1_1.get(v3);
            ++v3;
            ((Map)v12).put(((zzp)v4).mApi, v4);
        }

        Iterator v14 = arg26.entrySet().iterator();
        boolean v15 = true;
        int v1_2 = 0;
        v2_1 = 1;
        v3 = 0;
        while(v14.hasNext()) {
            Object v16 = v14.next();
            v4 = ((Map)v11).get(((Map$Entry)v16).getKey());
            Object v17 = ((Map$Entry)v16).getValue();
            if(((Client)v17).requiresGooglePlayServices()) {
                if(!v0.zzgi.get(v4).booleanValue()) {
                    v19 = v2_1;
                    v18 = 1;
                }
                else {
                    v19 = v2_1;
                    v18 = v3;
                }

                v20 = 1;
            }
            else {
                v20 = v1_2;
                v18 = v3;
                v19 = 0;
            }

            zzv v13 = new zzv(arg22, v4, arg24, v17, ((Map)v12).get(v4), arg27, arg29);
            v0.zzgg.put(((Map$Entry)v16).getKey(), v13);
            if(((Client)v17).requiresSignIn()) {
                v0.zzgh.put(((Map$Entry)v16).getKey(), v13);
            }

            v3 = v18;
            v2_1 = v19;
            v1_2 = v20;
        }

        if(v1_2 == 0 || v2_1 != 0 || v3 != 0) {
            v15 = false;
        }
        else {
        }

        v0.zzgn = v15;
        v0.zzcq = GoogleApiManager.zzbf();
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
                this.zzgl.await();
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

        if(this.zzgt != null) {
            return this.zzgt;
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
                arg4 = this.zzgl.awaitNanos(arg4);
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

        if(this.zzgt != null) {
            return this.zzgt;
        }

        return new ConnectionResult(13, v0);
    }

    public final void connect() {
        this.zzga.lock();
        try {
            if(!this.zzgp) {
                this.zzgp = true;
                this.zzgq = null;
                this.zzgr = null;
                this.zzgs = null;
                this.zzgt = null;
                this.zzcq.zzr();
                this.zzcq.zza(this.zzgg.values()).addOnCompleteListener(new HandlerExecutor(this.zzcn), new zzy(this, null));
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    public final void disconnect() {
        this.zzga.lock();
        try {
            this.zzgp = false;
            Map v0_1 = null;
            this.zzgq = v0_1;
            this.zzgr = v0_1;
            if(this.zzgs != null) {
                this.zzgs.cancel();
                this.zzgs = ((zzz)v0_1);
            }

            this.zzgt = ((ConnectionResult)v0_1);
            while(!this.zzgo.isEmpty()) {
                Object v1 = this.zzgo.remove();
                ((BasePendingResult)v1).zza(((zzcn)v0_1));
                ((PendingResult)v1).cancel();
            }

            this.zzgl.signalAll();
        }
        catch(Throwable v0) {
            goto label_28;
        }

        this.zzga.unlock();
        return;
    label_28:
        this.zzga.unlock();
        throw v0;
    }

    public final void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4) {
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg3) {
        if((this.zzgm) && (this.zzb(arg3))) {
            return arg3;
        }

        if(!this.isConnected()) {
            this.zzgo.add(arg3);
            return arg3;
        }

        this.zzgj.zzir.zzb(((BasePendingResult)arg3));
        return this.zzgg.get(arg3.getClientKey()).doRead(arg3);
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg3) {
        AnyClientKey v0 = arg3.getClientKey();
        if((this.zzgm) && (this.zzb(arg3))) {
            return arg3;
        }

        this.zzgj.zzir.zzb(((BasePendingResult)arg3));
        return this.zzgg.get(v0).doWrite(arg3);
    }

    public final ConnectionResult getConnectionResult(Api arg1) {
        return this.zza(arg1.getClientKey());
    }

    public final boolean isConnected() {
        this.zzga.lock();
        try {
            if(this.zzgq == null) {
                goto label_8;
            }
            else if(this.zzgt != null) {
                goto label_8;
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        boolean v0_1 = true;
        goto label_9;
    label_8:
        v0_1 = false;
    label_9:
        this.zzga.unlock();
        return v0_1;
    }

    public final boolean isConnecting() {
        this.zzga.lock();
        try {
            if(this.zzgq != null) {
                goto label_8;
            }
            else if(!this.zzgp) {
                goto label_8;
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        boolean v0_1 = true;
        goto label_9;
    label_8:
        v0_1 = false;
    label_9:
        this.zzga.unlock();
        return v0_1;
    }

    public final boolean maybeSignIn(SignInConnectionListener arg3) {
        this.zzga.lock();
        try {
            if((this.zzgp) && !this.zzaf()) {
                this.zzcq.zzr();
                this.zzgs = new zzz(this, arg3);
                this.zzcq.zza(this.zzgh.values()).addOnCompleteListener(new HandlerExecutor(this.zzcn), this.zzgs);
                goto label_20;
            }

            goto label_24;
        }
        catch(Throwable v3) {
            this.zzga.unlock();
            throw v3;
        }

    label_20:
        this.zzga.unlock();
        return 1;
    label_24:
        this.zzga.unlock();
        return 0;
    }

    public final void maybeSignOut() {
        this.zzga.lock();
        try {
            this.zzcq.maybeSignOut();
            if(this.zzgs != null) {
                this.zzgs.cancel();
                this.zzgs = null;
            }

            if(this.zzgr == null) {
                this.zzgr = new a(this.zzgh.size());
            }

            ConnectionResult v0_1 = new ConnectionResult(4);
            Iterator v1 = this.zzgh.values().iterator();
            while(v1.hasNext()) {
                this.zzgr.put(v1.next().zzm(), v0_1);
            }

            if(this.zzgq != null) {
                this.zzgq.putAll(this.zzgr);
            }
        }
        catch(Throwable v0) {
            goto label_40;
        }

        this.zzga.unlock();
        return;
    label_40:
        this.zzga.unlock();
        throw v0;
    }

    private final ConnectionResult zza(AnyClientKey arg2) {
        Lock v0;
        Object v2_1;
        this.zzga.lock();
        try {
            v2_1 = this.zzgg.get(arg2);
            if(this.zzgq != null && v2_1 != null) {
                v2_1 = this.zzgq.get(((GoogleApi)v2_1).zzm());
                v0 = this.zzga;
                goto label_11;
            }

            goto label_13;
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

    label_11:
        v0.unlock();
        return ((ConnectionResult)v2_1);
    label_13:
        this.zzga.unlock();
        return null;
    }

    static ConnectionResult zza(zzw arg0, ConnectionResult arg1) {
        arg0.zzgt = arg1;
        return arg1;
    }

    static Map zza(zzw arg0, Map arg1) {
        arg0.zzgq = arg1;
        return arg1;
    }

    static Lock zza(zzw arg0) {
        return arg0.zzga;
    }

    private final boolean zza(zzv arg3, ConnectionResult arg4) {
        if(!arg4.isSuccess() && !arg4.hasResolution() && (this.zzgi.get(((GoogleApi)arg3).getApi()).booleanValue()) && (arg3.zzae().requiresGooglePlayServices()) && (this.zzgk.isUserResolvableError(arg4.getErrorCode()))) {
            return 1;
        }

        return 0;
    }

    static boolean zza(zzw arg0, zzv arg1, ConnectionResult arg2) {
        return arg0.zza(arg1, arg2);
    }

    static boolean zza(zzw arg0, boolean arg1) {
        arg0.zzgp = false;
        return 0;
    }

    private final boolean zzaf() {
        this.zzga.lock();
        try {
            if(this.zzgp) {
                if(!this.zzgm) {
                }
                else {
                    Iterator v0_1 = this.zzgh.keySet().iterator();
                    do {
                        if(v0_1.hasNext()) {
                            ConnectionResult v2 = this.zza(v0_1.next());
                            if(v2 != null && (v2.isSuccess())) {
                                continue;
                            }
                        }
                        else {
                            goto label_21;
                        }

                        goto label_18;
                    }
                    while(true);
                }
            }

            goto label_18;
        }
        catch(Throwable v0) {
            goto label_27;
        }

    label_21:
        this.zzga.unlock();
        return 1;
    label_27:
        this.zzga.unlock();
        throw v0;
    label_18:
        this.zzga.unlock();
        return 0;
    }

    @GuardedBy(value="mLock") private final void zzag() {
        if(this.zzgf == null) {
            this.zzgj.zzim = Collections.emptySet();
            return;
        }

        HashSet v0 = new HashSet(this.zzgf.getRequiredScopes());
        Map v1 = this.zzgf.getOptionalApiSettings();
        Iterator v2 = v1.keySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            ConnectionResult v4 = this.getConnectionResult(((Api)v3));
            if(v4 == null) {
                continue;
            }

            if(!v4.isSuccess()) {
                continue;
            }

            ((Set)v0).addAll(v1.get(v3).mScopes);
        }

        this.zzgj.zzim = ((Set)v0);
    }

    @GuardedBy(value="mLock") private final void zzah() {
        while(!this.zzgo.isEmpty()) {
            this.execute(this.zzgo.remove());
        }

        this.zzgj.zzb(null);
    }

    @GuardedBy(value="mLock") private final ConnectionResult zzai() {
        int v6_1;
        Iterator v0 = this.zzgg.values().iterator();
        Object v2 = null;
        Object v1 = v2;
        int v3 = 0;
        int v4 = 0;
        while(v0.hasNext()) {
            Object v5 = v0.next();
            Api v6 = ((GoogleApi)v5).getApi();
            v5 = this.zzgq.get(((GoogleApi)v5).zzm());
            if(((ConnectionResult)v5).isSuccess()) {
                continue;
            }

            if((this.zzgi.get(v6).booleanValue()) && !((ConnectionResult)v5).hasResolution() && !this.zzgk.isUserResolvableError(((ConnectionResult)v5).getErrorCode())) {
                continue;
            }

            if(((ConnectionResult)v5).getErrorCode() == 4 && (this.zzgm)) {
                v6_1 = v6.zzj().getPriority();
                if(v1 != null && v4 <= v6_1) {
                    continue;
                }

                v1 = v5;
                v4 = v6_1;
                continue;
            }

            v6_1 = v6.zzj().getPriority();
            if(v2 != null && v3 <= v6_1) {
                continue;
            }

            v2 = v5;
            v3 = v6_1;
        }

        if(v2 != null && v1 != null && v3 > v4) {
            return ((ConnectionResult)v1);
        }

        return ((ConnectionResult)v2);
    }

    static Map zzb(zzw arg0, Map arg1) {
        arg0.zzgr = arg1;
        return arg1;
    }

    private final boolean zzb(ApiMethodImpl arg7) {
        AnyClientKey v0 = arg7.getClientKey();
        ConnectionResult v1 = this.zza(v0);
        if(v1 != null) {
            int v2 = 4;
            if(v1.getErrorCode() == v2) {
                arg7.setFailedResult(new Status(v2, null, this.zzcq.zza(this.zzgg.get(v0).zzm(), System.identityHashCode(this.zzgj))));
                return 1;
            }
        }

        return 0;
    }

    static boolean zzb(zzw arg0) {
        return arg0.zzgp;
    }

    static Map zzc(zzw arg0) {
        return arg0.zzgg;
    }

    static Map zzd(zzw arg0) {
        return arg0.zzgq;
    }

    static boolean zze(zzw arg0) {
        return arg0.zzgn;
    }

    static ConnectionResult zzf(zzw arg0) {
        return arg0.zzai();
    }

    static Map zzg(zzw arg0) {
        return arg0.zzgr;
    }

    static ConnectionResult zzh(zzw arg0) {
        return arg0.zzgt;
    }

    static void zzi(zzw arg0) {
        arg0.zzag();
    }

    static void zzj(zzw arg0) {
        arg0.zzah();
    }

    static zzav zzk(zzw arg0) {
        return arg0.zzgj;
    }

    static Condition zzl(zzw arg0) {
        return arg0.zzgl;
    }

    static Map zzm(zzw arg0) {
        return arg0.zzgh;
    }

    public final void zzz() {
    }
}

