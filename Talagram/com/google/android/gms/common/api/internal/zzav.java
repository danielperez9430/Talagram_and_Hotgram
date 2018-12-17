package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.h;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager$GmsClientEventState;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzav extends GoogleApiClient implements zzbq {
    private final Context mContext;
    private final Looper zzcn;
    private final int zzde;
    private final GoogleApiAvailability zzdg;
    private final AbstractClientBuilder zzdh;
    private boolean zzdk;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map zzgi;
    @VisibleForTesting final Queue zzgo;
    private final GmsClientEventManager zzie;
    private zzbp zzif;
    private volatile boolean zzig;
    private long zzih;
    private long zzii;
    private final zzba zzij;
    @VisibleForTesting private GooglePlayServicesUpdatedReceiver zzik;
    final Map zzil;
    Set zzim;
    private final ListenerHolders zzin;
    private final ArrayList zzio;
    private Integer zzip;
    Set zziq;
    final zzck zzir;
    private final GmsClientEventState zzis;

    public zzav(Context arg6, Lock arg7, Looper arg8, ClientSettings arg9, GoogleApiAvailability arg10, AbstractClientBuilder arg11, Map arg12, List arg13, List arg14, Map arg15, int arg16, int arg17, ArrayList arg18, boolean arg19) {
        zzav v0 = this;
        super();
        v0.zzif = null;
        v0.zzgo = new LinkedList();
        v0.zzih = 120000;
        v0.zzii = 5000;
        v0.zzim = new HashSet();
        v0.zzin = new ListenerHolders();
        v0.zzip = null;
        v0.zziq = null;
        v0.zzis = new zzaw(this);
        v0.mContext = arg6;
        v0.zzga = arg7;
        v0.zzdk = false;
        v0.zzie = new GmsClientEventManager(arg8, v0.zzis);
        v0.zzcn = arg8;
        v0.zzij = new zzba(this, arg8);
        v0.zzdg = arg10;
        v0.zzde = arg16;
        if(v0.zzde >= 0) {
            v0.zzip = Integer.valueOf(arg17);
        }

        v0.zzgi = arg12;
        v0.zzil = arg15;
        v0.zzio = arg18;
        v0.zzir = new zzck(v0.zzil);
        Iterator v1 = arg13.iterator();
        while(v1.hasNext()) {
            v0.zzie.registerConnectionCallbacks(v1.next());
        }

        v1 = arg14.iterator();
        while(v1.hasNext()) {
            v0.zzie.registerConnectionFailedListener(v1.next());
        }

        v0.zzgf = arg9;
        v0.zzdh = arg11;
    }

    public final ConnectionResult blockingConnect() {
        ConnectionResult v0_2;
        boolean v2 = true;
        boolean v0 = Looper.myLooper() != Looper.getMainLooper() ? true : false;
        Preconditions.checkState(v0, "blockingConnect must not be called on the UI thread");
        this.zzga.lock();
        try {
            if(this.zzde >= 0) {
                if(this.zzip != null) {
                }
                else {
                    v2 = false;
                }

                Preconditions.checkState(v2, "Sign-in mode should have been set explicitly by auto-manage.");
            }
            else {
                if(this.zzip == null) {
                    this.zzip = Integer.valueOf(zzav.zza(this.zzil.values(), false));
                    goto label_33;
                }

                if(this.zzip.intValue() == 2) {
                    goto label_43;
                }
            }

        label_33:
            this.zzg(this.zzip.intValue());
            this.zzie.enableCallbacks();
            v0_2 = this.zzif.blockingConnect();
        }
        catch(Throwable v0_1) {
            goto label_49;
        }

        this.zzga.unlock();
        return v0_2;
        try {
        label_43:
            throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        }
        catch(Throwable v0_1) {
        label_49:
            this.zzga.unlock();
            throw v0_1;
        }
    }

    public final ConnectionResult blockingConnect(long arg4, TimeUnit arg6) {
        ConnectionResult v4_1;
        boolean v0 = Looper.myLooper() != Looper.getMainLooper() ? true : false;
        Preconditions.checkState(v0, "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(arg6, "TimeUnit must not be null");
        this.zzga.lock();
        try {
            if(this.zzip == null) {
                this.zzip = Integer.valueOf(zzav.zza(this.zzil.values(), false));
            }
            else {
                if(this.zzip.intValue() != 2) {
                    goto label_25;
                }

                goto label_35;
            }

        label_25:
            this.zzg(this.zzip.intValue());
            this.zzie.enableCallbacks();
            v4_1 = this.zzif.blockingConnect(arg4, arg6);
        }
        catch(Throwable v4) {
            goto label_41;
        }

        this.zzga.unlock();
        return v4_1;
        try {
        label_35:
            throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        }
        catch(Throwable v4) {
        label_41:
            this.zzga.unlock();
            throw v4;
        }
    }

    public final PendingResult clearDefaultAccountAndReconnect() {
        Preconditions.checkState(((GoogleApiClient)this).isConnected(), "GoogleApiClient is not connected yet.");
        boolean v0 = this.zzip.intValue() != 2 ? true : false;
        Preconditions.checkState(v0, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult v0_1 = new StatusPendingResult(((GoogleApiClient)this));
        if(this.zzil.containsKey(Common.CLIENT_KEY)) {
            this.zza(((GoogleApiClient)this), v0_1, false);
        }
        else {
            AtomicReference v1 = new AtomicReference();
            GoogleApiClient v2 = new Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zzax(this, v1, v0_1)).addOnConnectionFailedListener(new zzay(this, v0_1)).setHandler(this.zzij).build();
            v1.set(v2);
            v2.connect();
        }

        return ((PendingResult)v0_1);
    }

    public final void connect() {
        this.zzga.lock();
        try {
            boolean v1 = false;
            if(this.zzde >= 0) {
                if(this.zzip != null) {
                    v1 = true;
                }

                Preconditions.checkState(v1, "Sign-in mode should have been set explicitly by auto-manage.");
            }
            else {
                if(this.zzip == null) {
                    this.zzip = Integer.valueOf(zzav.zza(this.zzil.values(), false));
                    goto label_23;
                }

                if(this.zzip.intValue() == 2) {
                    goto label_29;
                }
            }

        label_23:
            ((GoogleApiClient)this).connect(this.zzip.intValue());
        }
        catch(Throwable v0) {
            goto label_35;
        }

        this.zzga.unlock();
        return;
        try {
        label_29:
            throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        }
        catch(Throwable v0) {
        label_35:
            this.zzga.unlock();
            throw v0;
        }
    }

    public final void connect(int arg4) {
        this.zzga.lock();
        boolean v1 = true;
        if(arg4 != 3 && arg4 != 1) {
            if(arg4 == 2) {
            }
            else {
                v1 = false;
            }
        }

        int v0 = 33;
        try {
            StringBuilder v2 = new StringBuilder(v0);
            v2.append("Illegal sign-in mode: ");
            v2.append(arg4);
            Preconditions.checkArgument(v1, v2.toString());
            this.zzg(arg4);
            this.zzax();
        }
        catch(Throwable v4) {
            this.zzga.unlock();
            throw v4;
        }

        this.zzga.unlock();
    }

    public final void disconnect() {
        this.zzga.lock();
        try {
            this.zzir.release();
            if(this.zzif != null) {
                this.zzif.disconnect();
            }

            this.zzin.release();
            Iterator v0_1 = this.zzgo.iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                ((BasePendingResult)v1).zza(null);
                ((PendingResult)v1).cancel();
            }

            this.zzgo.clear();
            if(this.zzif != null) {
                this.zzaz();
                this.zzie.disableCallbacks();
            }
        }
        catch(Throwable v0) {
            goto label_32;
        }

        this.zzga.unlock();
        return;
    label_32:
        this.zzga.unlock();
        throw v0;
    }

    public final void dump(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        arg5.append(((CharSequence)arg3)).append("mContext=").println(this.mContext);
        arg5.append(((CharSequence)arg3)).append("mResuming=").print(this.zzig);
        arg5.append(" mWorkQueue.size()=").print(this.zzgo.size());
        arg5.append(" mUnconsumedApiCalls.size()=").println(this.zzir.zzmo.size());
        if(this.zzif != null) {
            this.zzif.dump(arg3, arg4, arg5, arg6);
        }
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg5) {
        boolean v0 = arg5.getClientKey() != null ? true : false;
        Preconditions.checkArgument(v0, "This task can not be enqueued (it\'s probably a Batch or malformed)");
        v0 = this.zzil.containsKey(arg5.getClientKey());
        String v1 = arg5.getApi() != null ? arg5.getApi().getName() : "the API";
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 65);
        v3.append("GoogleApiClient is not configured to use ");
        v3.append(v1);
        v3.append(" required for this call.");
        Preconditions.checkArgument(v0, v3.toString());
        this.zzga.lock();
        try {
            if(this.zzif == null) {
                this.zzgo.add(arg5);
            }
            else {
                arg5 = this.zzif.enqueue(arg5);
            }
        }
        catch(Throwable v5) {
            this.zzga.unlock();
            throw v5;
        }

        this.zzga.unlock();
        return arg5;
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg5) {
        boolean v0 = arg5.getClientKey() != null ? true : false;
        Preconditions.checkArgument(v0, "This task can not be executed (it\'s probably a Batch or malformed)");
        v0 = this.zzil.containsKey(arg5.getClientKey());
        String v1 = arg5.getApi() != null ? arg5.getApi().getName() : "the API";
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 65);
        v3.append("GoogleApiClient is not configured to use ");
        v3.append(v1);
        v3.append(" required for this call.");
        Preconditions.checkArgument(v0, v3.toString());
        this.zzga.lock();
        try {
            if(this.zzif == null) {
                goto label_52;
            }

            if(this.zzig) {
                this.zzgo.add(arg5);
                while(!this.zzgo.isEmpty()) {
                    Object v0_1 = this.zzgo.remove();
                    this.zzir.zzb(((BasePendingResult)v0_1));
                    ((ApiMethodImpl)v0_1).setFailedResult(Status.RESULT_INTERNAL_ERROR);
                }
            }
            else {
                arg5 = this.zzif.execute(arg5);
            }
        }
        catch(Throwable v5) {
            goto label_58;
        }

        this.zzga.unlock();
        return arg5;
        try {
        label_52:
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        }
        catch(Throwable v5) {
        label_58:
            this.zzga.unlock();
            throw v5;
        }
    }

    public final Client getClient(AnyClientKey arg2) {
        Object v2 = this.zzil.get(arg2);
        Preconditions.checkNotNull(v2, "Appropriate Api was not requested.");
        return ((Client)v2);
    }

    public final ConnectionResult getConnectionResult(Api arg3) {
        ConnectionResult v3_1;
        ConnectionResult v0;
        this.zzga.lock();
        try {
            if(!((GoogleApiClient)this).isConnected()) {
                if(this.zzig) {
                }
                else {
                    throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
                }
            }

            if(!this.zzil.containsKey(arg3.getClientKey())) {
                goto label_43;
            }

            v0 = this.zzif.getConnectionResult(arg3);
            if(v0 != null) {
                goto label_40;
            }

            if(this.zzig) {
                v3_1 = ConnectionResult.RESULT_SUCCESS;
            }
            else {
                Log.w("GoogleApiClientImpl", this.zzbb());
                Log.wtf("GoogleApiClientImpl", String.valueOf(arg3.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                v3_1 = new ConnectionResult(8, null);
            }
        }
        catch(Throwable v3) {
            goto label_52;
        }

        this.zzga.unlock();
        return v3_1;
    label_40:
        this.zzga.unlock();
        return v0;
        try {
        label_43:
            throw new IllegalArgumentException(String.valueOf(arg3.getName()).concat(" was never registered with GoogleApiClient"));
        }
        catch(Throwable v3) {
        label_52:
            this.zzga.unlock();
            throw v3;
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzcn;
    }

    public final boolean hasApi(Api arg2) {
        return this.zzil.containsKey(arg2.getClientKey());
    }

    public final boolean hasConnectedApi(Api arg3) {
        if(!((GoogleApiClient)this).isConnected()) {
            return 0;
        }

        Object v3 = this.zzil.get(arg3.getClientKey());
        if(v3 != null && (((Client)v3).isConnected())) {
            return 1;
        }

        return 0;
    }

    public final boolean isConnected() {
        if(this.zzif != null && (this.zzif.isConnected())) {
            return 1;
        }

        return 0;
    }

    public final boolean isConnecting() {
        if(this.zzif != null && (this.zzif.isConnecting())) {
            return 1;
        }

        return 0;
    }

    public final boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg2) {
        return this.zzie.isConnectionCallbacksRegistered(arg2);
    }

    public final boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener arg2) {
        return this.zzie.isConnectionFailedListenerRegistered(arg2);
    }

    public final boolean maybeSignIn(SignInConnectionListener arg2) {
        if(this.zzif != null && (this.zzif.maybeSignIn(arg2))) {
            return 1;
        }

        return 0;
    }

    public final void maybeSignOut() {
        if(this.zzif != null) {
            this.zzif.maybeSignOut();
        }
    }

    public final void reconnect() {
        ((GoogleApiClient)this).disconnect();
        ((GoogleApiClient)this).connect();
    }

    public final void registerConnectionCallbacks(ConnectionCallbacks arg2) {
        this.zzie.registerConnectionCallbacks(arg2);
    }

    public final void registerConnectionFailedListener(OnConnectionFailedListener arg2) {
        this.zzie.registerConnectionFailedListener(arg2);
    }

    public final ListenerHolder registerListener(Object arg4) {
        ListenerHolder v4_1;
        this.zzga.lock();
        try {
            v4_1 = this.zzin.zza(arg4, this.zzcn, "NO_TYPE");
        }
        catch(Throwable v4) {
            this.zzga.unlock();
            throw v4;
        }

        this.zzga.unlock();
        return v4_1;
    }

    private final void resume() {
        this.zzga.lock();
        try {
            if(this.zzig) {
                this.zzax();
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    public final void stopAutoManage(h arg2) {
        LifecycleActivity v0 = new LifecycleActivity(((Activity)arg2));
        if(this.zzde >= 0) {
            zzi.zza(v0).zzc(this.zzde);
            return;
        }

        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public final void unregisterConnectionCallbacks(ConnectionCallbacks arg2) {
        this.zzie.unregisterConnectionCallbacks(arg2);
    }

    public final void unregisterConnectionFailedListener(OnConnectionFailedListener arg2) {
        this.zzie.unregisterConnectionFailedListener(arg2);
    }

    public static int zza(Iterable arg5, boolean arg6) {
        Iterator v5 = arg5.iterator();
        int v0 = 0;
        int v1;
        for(v1 = 0; v5.hasNext(); v1 = 1) {
            Object v2 = v5.next();
            if(((Client)v2).requiresSignIn()) {
                v0 = 1;
            }

            if(!((Client)v2).providesSignIn()) {
                continue;
            }
        }

        if(v0 != 0) {
            if(v1 != 0 && (arg6)) {
                return 2;
            }

            return 1;
        }

        return 3;
    }

    static void zza(zzav arg0) {
        arg0.resume();
    }

    private final void zza(GoogleApiClient arg3, StatusPendingResult arg4, boolean arg5) {
        Common.CommonApi.clearDefaultAccount(arg3).setResultCallback(new zzaz(this, arg4, arg5, arg3));
    }

    static void zza(zzav arg0, GoogleApiClient arg1, StatusPendingResult arg2, boolean arg3) {
        arg0.zza(arg1, arg2, true);
    }

    public final void zza(zzch arg2) {
        this.zzga.lock();
        try {
            if(this.zziq == null) {
                this.zziq = new HashSet();
            }

            this.zziq.add(arg2);
        }
        catch(Throwable v2) {
            this.zzga.unlock();
            throw v2;
        }

        this.zzga.unlock();
    }

    @GuardedBy(value="mLock") private final void zzax() {
        this.zzie.enableCallbacks();
        this.zzif.connect();
    }

    private final void zzay() {
        this.zzga.lock();
        try {
            if(this.zzaz()) {
                this.zzax();
            }
        }
        catch(Throwable v0) {
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
    }

    @GuardedBy(value="mLock") final boolean zzaz() {
        if(!this.zzig) {
            return 0;
        }

        this.zzig = false;
        this.zzij.removeMessages(2);
        this.zzij.removeMessages(1);
        if(this.zzik != null) {
            this.zzik.unregister();
            this.zzik = null;
        }

        return 1;
    }

    static void zzb(zzav arg0) {
        arg0.zzay();
    }

    @GuardedBy(value="mLock") public final void zzb(int arg5, boolean arg6) {
        int v0 = 2;
        if(arg5 == 1 && !arg6 && !this.zzig) {
            this.zzig = true;
            if(this.zzik == null) {
                this.zzik = this.zzdg.registerCallbackOnUpdate(this.mContext.getApplicationContext(), new zzbb(this));
            }

            this.zzij.sendMessageDelayed(this.zzij.obtainMessage(1), this.zzih);
            this.zzij.sendMessageDelayed(this.zzij.obtainMessage(v0), this.zzii);
        }

        this.zzir.zzce();
        this.zzie.onUnintentionalDisconnection(arg5);
        this.zzie.disableCallbacks();
        if(arg5 == v0) {
            this.zzax();
        }
    }

    @GuardedBy(value="mLock") public final void zzb(Bundle arg2) {
        while(!this.zzgo.isEmpty()) {
            ((GoogleApiClient)this).execute(this.zzgo.remove());
        }

        this.zzie.onConnectionSuccess(arg2);
    }

    public final void zzb(zzch arg3) {
        Exception v1;
        String v0;
        String v3_1;
        this.zzga.lock();
        try {
            if(this.zziq == null) {
                v3_1 = "GoogleApiClientImpl";
                v0 = "Attempted to remove pending transform when no transforms are registered.";
                v1 = new Exception();
                goto label_8;
            }
            else if(!this.zziq.remove(arg3)) {
                v3_1 = "GoogleApiClientImpl";
                v0 = "Failed to remove pending transform - this may lead to memory leaks!";
                v1 = new Exception();
            label_8:
                Log.wtf(v3_1, v0, ((Throwable)v1));
            }
            else if(!this.zzba()) {
                this.zzif.zzz();
            }
        }
        catch(Throwable v3) {
            this.zzga.unlock();
            throw v3;
        }

        this.zzga.unlock();
    }

    final boolean zzba() {
        boolean v0_1;
        this.zzga.lock();
        try {
            if(this.zziq != null) {
                goto label_8;
            }
        }
        catch(Throwable v0) {
            goto label_16;
        }

        this.zzga.unlock();
        return 0;
        try {
        label_8:
            v0_1 = this.zziq.isEmpty();
        }
        catch(Throwable v0) {
        label_16:
            this.zzga.unlock();
            throw v0;
        }

        this.zzga.unlock();
        return (((int)v0_1)) ^ 1;
    }

    final String zzbb() {
        StringWriter v0 = new StringWriter();
        ((GoogleApiClient)this).dump("", null, new PrintWriter(((Writer)v0)), null);
        return v0.toString();
    }

    static Context zzc(zzav arg0) {
        return arg0.mContext;
    }

    @GuardedBy(value="mLock") public final void zzc(ConnectionResult arg4) {
        if(!this.zzdg.isPlayServicesPossiblyUpdating(this.mContext, arg4.getErrorCode())) {
            this.zzaz();
        }

        if(!this.zzig) {
            this.zzie.onConnectionFailure(arg4);
            this.zzie.disableCallbacks();
        }
    }

    private final void zzg(int arg14) {
        if(this.zzip == null) {
            this.zzip = Integer.valueOf(arg14);
        }
        else {
            if(this.zzip.intValue() == arg14) {
                goto label_8;
            }

            goto label_112;
        }

    label_8:
        if(this.zzif != null) {
            return;
        }

        Iterator v0 = this.zzil.values().iterator();
        int v1 = 0;
        int v2;
        for(v2 = 0; v0.hasNext(); v2 = 1) {
            Object v3 = v0.next();
            if(((Client)v3).requiresSignIn()) {
                v1 = 1;
            }

            if(!((Client)v3).providesSignIn()) {
                continue;
            }
        }

        switch(this.zzip.intValue()) {
            case 1: {
                if(v1 == 0) {
                    goto label_71;
                }

                if(v2 != 0) {
                    goto label_67;
                }

                break;
            }
            case 2: {
                if(v1 == 0) {
                    goto label_75;
                }

                if(this.zzdk) {
                    this.zzif = new zzw(this.mContext, this.zzga, this.zzcn, this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this, true);
                    return;
                }

                this.zzif = zzr.zza(this.mContext, this, this.zzga, this.zzcn, this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio);
                return;
            }
            default: {
                break;
            }
        }

    label_75:
        if((this.zzdk) && v2 == 0) {
            this.zzif = new zzw(this.mContext, this.zzga, this.zzcn, this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this, false);
            return;
        }

        this.zzif = new zzbd(this.mContext, this, this.zzga, this.zzcn, this.zzdg, this.zzil, this.zzgf, this.zzgi, this.zzdh, this.zzio, this);
        return;
    label_67:
        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
    label_71:
        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
    label_112:
        String v1_1 = zzav.zzh(arg14);
        String v2_1 = zzav.zzh(this.zzip.intValue());
        StringBuilder v4 = new StringBuilder(String.valueOf(v1_1).length() + 51 + String.valueOf(v2_1).length());
        v4.append("Cannot use sign-in mode: ");
        v4.append(v1_1);
        v4.append(". Mode was already set to ");
        v4.append(v2_1);
        throw new IllegalStateException(v4.toString());
    }

    private static String zzh(int arg0) {
        switch(arg0) {
            case 1: {
                return "SIGN_IN_MODE_REQUIRED";
            }
            case 2: {
                return "SIGN_IN_MODE_OPTIONAL";
            }
            case 3: {
                return "SIGN_IN_MODE_NONE";
            }
        }

        return "UNKNOWN";
    }
}

