package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.f.a;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

final class zzr implements zzbp {
    private final Context mContext;
    private final Looper zzcn;
    private final zzav zzfq;
    private final zzbd zzfr;
    private final zzbd zzfs;
    private final Map zzft;
    private final Set zzfu;
    private final Client zzfv;
    private Bundle zzfw;
    private ConnectionResult zzfx;
    private ConnectionResult zzfy;
    private boolean zzfz;
    private final Lock zzga;
    @GuardedBy(value="mLock") private int zzgb;

    private zzr(Context arg19, zzav arg20, Lock arg21, Looper arg22, GoogleApiAvailabilityLight arg23, Map arg24, Map arg25, ClientSettings arg26, AbstractClientBuilder arg27, Client arg28, ArrayList arg29, ArrayList arg30, Map arg31, Map arg32) {
        zzr v0 = this;
        super();
        v0.zzfu = Collections.newSetFromMap(new WeakHashMap());
        v0.zzfx = null;
        v0.zzfy = null;
        v0.zzfz = false;
        v0.zzgb = 0;
        v0.mContext = arg19;
        v0.zzfq = arg20;
        v0.zzga = arg21;
        v0.zzcn = arg22;
        v0.zzfv = arg28;
        v0.zzfr = new zzbd(arg19, v0.zzfq, arg21, arg22, arg23, arg25, null, arg32, null, arg30, new zzt(v0, null));
        v0.zzfs = new zzbd(arg19, v0.zzfq, arg21, arg22, arg23, arg24, arg26, arg31, arg27, arg29, new zzu(v0, null));
        a v1 = new a();
        Iterator v2 = arg25.keySet().iterator();
        while(v2.hasNext()) {
            v1.put(v2.next(), v0.zzfr);
        }

        v2 = arg24.keySet().iterator();
        while(v2.hasNext()) {
            v1.put(v2.next(), v0.zzfs);
        }

        v0.zzft = Collections.unmodifiableMap(((Map)v1));
    }

    @GuardedBy(value="mLock") public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @GuardedBy(value="mLock") public final ConnectionResult blockingConnect(long arg1, TimeUnit arg3) {
        throw new UnsupportedOperationException();
    }

    @GuardedBy(value="mLock") public final void connect() {
        this.zzgb = 2;
        this.zzfz = false;
        this.zzfy = null;
        this.zzfx = null;
        this.zzfr.connect();
        this.zzfs.connect();
    }

    @GuardedBy(value="mLock") public final void disconnect() {
        this.zzfy = null;
        this.zzfx = null;
        this.zzgb = 0;
        this.zzfr.disconnect();
        this.zzfs.disconnect();
        this.zzab();
    }

    public final void dump(String arg4, FileDescriptor arg5, PrintWriter arg6, String[] arg7) {
        arg6.append(((CharSequence)arg4)).append("authClient").println(":");
        this.zzfs.dump(String.valueOf(arg4).concat("  "), arg5, arg6, arg7);
        arg6.append(((CharSequence)arg4)).append("anonClient").println(":");
        this.zzfr.dump(String.valueOf(arg4).concat("  "), arg5, arg6, arg7);
    }

    @GuardedBy(value="mLock") public final ApiMethodImpl enqueue(ApiMethodImpl arg5) {
        if(this.zza(arg5)) {
            if(this.zzac()) {
                arg5.setFailedResult(new Status(4, null, this.zzad()));
                return arg5;
            }

            return this.zzfs.enqueue(arg5);
        }

        return this.zzfr.enqueue(arg5);
    }

    @GuardedBy(value="mLock") public final ApiMethodImpl execute(ApiMethodImpl arg5) {
        if(this.zza(arg5)) {
            if(this.zzac()) {
                arg5.setFailedResult(new Status(4, null, this.zzad()));
                return arg5;
            }

            return this.zzfs.execute(arg5);
        }

        return this.zzfr.execute(arg5);
    }

    @GuardedBy(value="mLock") public final ConnectionResult getConnectionResult(Api arg3) {
        if(this.zzft.get(arg3.getClientKey()).equals(this.zzfs)) {
            if(this.zzac()) {
                return new ConnectionResult(4, this.zzad());
            }

            return this.zzfs.getConnectionResult(arg3);
        }

        return this.zzfr.getConnectionResult(arg3);
    }

    public final boolean isConnected() {
        boolean v1;
        this.zzga.lock();
        try {
            v1 = true;
            if(!this.zzfr.isConnected()) {
                goto label_14;
            }
            else if(!this.zzfs.isConnected() && !this.zzac()) {
                if(this.zzgb != 1) {
                    goto label_14;
                }

                goto label_13;
            }

            goto label_15;
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

    label_13:
        goto label_15;
    label_14:
        v1 = false;
    label_15:
        this.zzga.unlock();
        return v1;
    }

    public final boolean isConnecting() {
        this.zzga.lock();
        try {
            if(this.zzgb != 2) {
                goto label_7;
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        boolean v0_1 = true;
        goto label_8;
    label_7:
        v0_1 = false;
    label_8:
        this.zzga.unlock();
        return v0_1;
    }

    public final boolean maybeSignIn(SignInConnectionListener arg2) {
        this.zzga.lock();
        try {
            if(((this.isConnecting()) || (this.isConnected())) && !this.zzfs.isConnected()) {
                this.zzfu.add(arg2);
                if(this.zzgb == 0) {
                    this.zzgb = 1;
                }

                this.zzfy = null;
                this.zzfs.connect();
                goto label_19;
            }

            goto label_22;
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

    label_19:
        this.zzga.unlock();
        return 1;
    label_22:
        this.zzga.unlock();
        return 0;
    }

    public final void maybeSignOut() {
        this.zzga.lock();
        try {
            boolean v0_1 = this.isConnecting();
            this.zzfs.disconnect();
            this.zzfy = new ConnectionResult(4);
            if(v0_1) {
                new Handler(this.zzcn).post(new zzs(this));
            }
            else {
                this.zzab();
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    static Lock zza(zzr arg0) {
        return arg0.zzga;
    }

    public static zzr zza(Context arg16, zzav arg17, Lock arg18, Looper arg19, GoogleApiAvailabilityLight arg20, Map arg21, ClientSettings arg22, Map arg23, AbstractClientBuilder arg24, ArrayList arg25) {
        Object v10_1;
        Object v3;
        Object v2;
        Map v0 = arg23;
        a v6 = new a();
        a v7 = new a();
        Iterator v1 = arg21.entrySet().iterator();
        Client v10 = null;
        while(v1.hasNext()) {
            v2 = v1.next();
            v3 = ((Map$Entry)v2).getValue();
            if(((Client)v3).providesSignIn()) {
                v10_1 = v3;
            }

            if(((Client)v3).requiresSignIn()) {
                ((Map)v6).put(((Map$Entry)v2).getKey(), v3);
                continue;
            }

            ((Map)v7).put(((Map$Entry)v2).getKey(), v3);
        }

        Preconditions.checkState(((Map)v6).isEmpty() ^ 1, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        a v13 = new a();
        a v14 = new a();
        v1 = arg23.keySet().iterator();
        while(true) {
            if(!v1.hasNext()) {
                goto label_52;
            }

            v2 = v1.next();
            AnyClientKey v3_1 = ((Api)v2).getClientKey();
            if(((Map)v6).containsKey(v3_1)) {
                ((Map)v13).put(v2, v0.get(v2));
                continue;
            }

            if(!((Map)v7).containsKey(v3_1)) {
                break;
            }

            ((Map)v14).put(v2, v0.get(v2));
        }

        throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
    label_52:
        ArrayList v11 = new ArrayList();
        ArrayList v12 = new ArrayList();
        ArrayList v0_1 = arg25;
        int v1_1 = v0_1.size();
        int v2_1 = 0;
        while(true) {
            if(v2_1 >= v1_1) {
                goto label_76;
            }

            v3 = v0_1.get(v2_1);
            ++v2_1;
            if(((Map)v13).containsKey(((zzp)v3).mApi)) {
                v11.add(v3);
                continue;
            }

            if(!((Map)v14).containsKey(((zzp)v3).mApi)) {
                break;
            }

            v12.add(v3);
        }

        throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
    label_76:
        return new zzr(arg16, arg17, arg18, arg19, arg20, ((Map)v6), ((Map)v7), arg22, arg24, ((Client)v10_1), v11, v12, ((Map)v13), ((Map)v14));
    }

    static ConnectionResult zza(zzr arg0, ConnectionResult arg1) {
        arg0.zzfx = arg1;
        return arg1;
    }

    @GuardedBy(value="mLock") private final void zza(int arg2, boolean arg3) {
        this.zzfq.zzb(arg2, arg3);
        this.zzfy = null;
        this.zzfx = null;
    }

    private final void zza(Bundle arg2) {
        if(this.zzfw == null) {
            this.zzfw = arg2;
            return;
        }

        if(arg2 != null) {
            this.zzfw.putAll(arg2);
        }
    }

    @GuardedBy(value="mLock") private final void zza(ConnectionResult arg3) {
        switch(this.zzgb) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_8;
            }
        }

        Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        goto label_11;
    label_8:
        this.zzfq.zzc(arg3);
    label_10:
        this.zzab();
    label_11:
        this.zzgb = 0;
    }

    static void zza(zzr arg0, int arg1, boolean arg2) {
        arg0.zza(arg1, arg2);
    }

    static void zza(zzr arg0, Bundle arg1) {
        arg0.zza(arg1);
    }

    private final boolean zza(ApiMethodImpl arg3) {
        AnyClientKey v3 = arg3.getClientKey();
        Preconditions.checkArgument(this.zzft.containsKey(v3), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zzft.get(v3).equals(this.zzfs);
    }

    static boolean zza(zzr arg0, boolean arg1) {
        arg0.zzfz = arg1;
        return arg1;
    }

    @GuardedBy(value="mLock") private final void zzaa() {
        if(zzr.zzb(this.zzfx)) {
            if(!zzr.zzb(this.zzfy)) {
                if(this.zzac()) {
                }
                else if(this.zzfy == null) {
                    return;
                }
                else if(this.zzgb == 1) {
                    this.zzab();
                    return;
                }
                else {
                    this.zza(this.zzfy);
                    this.zzfr.disconnect();
                    return;
                }
            }

            switch(this.zzgb) {
                case 1: {
                    goto label_32;
                }
                case 2: {
                    goto label_29;
                }
            }

            Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            goto label_33;
        label_29:
            this.zzfq.zzb(this.zzfw);
        label_32:
            this.zzab();
        label_33:
            this.zzgb = 0;
            return;
        }
        else {
            if(this.zzfx != null && (zzr.zzb(this.zzfy))) {
                this.zzfs.disconnect();
                this.zza(this.zzfx);
                return;
            }

            if(this.zzfx == null) {
                return;
            }

            if(this.zzfy == null) {
                return;
            }

            ConnectionResult v0 = this.zzfx;
            if(this.zzfs.zzje < this.zzfr.zzje) {
                v0 = this.zzfy;
            }

            this.zza(v0);
        }
    }

    @GuardedBy(value="mLock") private final void zzab() {
        Iterator v0 = this.zzfu.iterator();
        while(v0.hasNext()) {
            v0.next().onComplete();
        }

        this.zzfu.clear();
    }

    @GuardedBy(value="mLock") private final boolean zzac() {
        if(this.zzfy != null && this.zzfy.getErrorCode() == 4) {
            return 1;
        }

        return 0;
    }

    private final PendingIntent zzad() {
        if(this.zzfv == null) {
            return null;
        }

        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfq), this.zzfv.getSignInIntent(), 134217728);
    }

    static void zzb(zzr arg0) {
        arg0.zzaa();
    }

    private static boolean zzb(ConnectionResult arg0) {
        if(arg0 != null && (arg0.isSuccess())) {
            return 1;
        }

        return 0;
    }

    static ConnectionResult zzb(zzr arg0, ConnectionResult arg1) {
        arg0.zzfy = arg1;
        return arg1;
    }

    static boolean zzc(zzr arg0) {
        return arg0.zzfz;
    }

    static ConnectionResult zzd(zzr arg0) {
        return arg0.zzfy;
    }

    static zzbd zze(zzr arg0) {
        return arg0.zzfs;
    }

    static zzbd zzf(zzr arg0) {
        return arg0.zzfr;
    }

    @GuardedBy(value="mLock") public final void zzz() {
        this.zzfr.zzz();
        this.zzfs.zzz();
    }
}

