package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.f.a;
import android.support.v4.f.b;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api$SimpleClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk public class GoogleApiManager implements Handler$Callback {
    public final class zza implements ConnectionCallbacks, OnConnectionFailedListener, zzq {
        private final zzh zzhc;
        private final Queue zzjz;
        private final Client zzka;
        private final AnyClient zzkb;
        private final zzaa zzkc;
        private final Set zzkd;
        private final Map zzke;
        private final int zzkf;
        private final zzby zzkg;
        private boolean zzkh;
        private final List zzki;
        private ConnectionResult zzkj;

        public zza(GoogleApiManager arg3, GoogleApi arg4) {
            Client v1_1;
            this.zzjy = arg3;
            super();
            this.zzjz = new LinkedList();
            this.zzkd = new HashSet();
            this.zzke = new HashMap();
            this.zzki = new ArrayList();
            ConnectionResult v0 = null;
            this.zzkj = v0;
            this.zzka = arg4.zza(GoogleApiManager.zza(arg3).getLooper(), this);
            if((this.zzka instanceof SimpleClientAdapter)) {
                SimpleClient v1 = this.zzka.getClient();
            }
            else {
                v1_1 = this.zzka;
            }

            this.zzkb = ((AnyClient)v1_1);
            this.zzhc = arg4.zzm();
            this.zzkc = new zzaa();
            this.zzkf = arg4.getInstanceId();
            if(this.zzka.requiresSignIn()) {
                this.zzkg = arg4.zza(GoogleApiManager.zzb(arg3), GoogleApiManager.zza(arg3));
                return;
            }

            this.zzkg = ((zzby)v0);
        }

        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if(!this.zzka.isConnected()) {
                if(this.zzka.isConnecting()) {
                }
                else {
                    int v0 = GoogleApiManager.zze(this.zzjy).getClientAvailability(GoogleApiManager.zzb(this.zzjy), this.zzka);
                    if(v0 != 0) {
                        this.onConnectionFailed(new ConnectionResult(v0, null));
                        return;
                    }
                    else {
                        zzc v0_1 = new zzc(this.zzjy, this.zzka, this.zzhc);
                        if(this.zzka.requiresSignIn()) {
                            this.zzkg.zza(((zzcb)v0_1));
                        }

                        this.zzka.connect(((ConnectionProgressReportCallbacks)v0_1));
                    }
                }
            }
        }

        public final int getInstanceId() {
            return this.zzkf;
        }

        final boolean isConnected() {
            return this.zzka.isConnected();
        }

        public final void onConnected(Bundle arg2) {
            if(Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
                this.zzbj();
                return;
            }

            GoogleApiManager.zza(this.zzjy).post(new zzbi(this));
        }

        public final void onConnectionFailed(ConnectionResult arg5) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if(this.zzkg != null) {
                this.zzkg.zzbz();
            }

            this.zzbo();
            GoogleApiManager.zze(this.zzjy).flush();
            this.zzi(arg5);
            if(arg5.getErrorCode() == 4) {
                this.zzc(GoogleApiManager.zzbi());
                return;
            }

            if(this.zzjz.isEmpty()) {
                this.zzkj = arg5;
                return;
            }

            if(this.zzh(arg5)) {
                return;
            }

            if(!this.zzjy.zzc(arg5, this.zzkf)) {
                if(arg5.getErrorCode() == 18) {
                    this.zzkh = true;
                }

                if(this.zzkh) {
                    GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 9, this.zzhc), GoogleApiManager.zzc(this.zzjy));
                    return;
                }

                String v1 = this.zzhc.zzq();
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 38);
                v3.append("API: ");
                v3.append(v1);
                v3.append(" is not available on this device.");
                this.zzc(new Status(17, v3.toString()));
            }
        }

        public final void onConnectionSuspended(int arg2) {
            if(Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
                this.zzbk();
                return;
            }

            GoogleApiManager.zza(this.zzjy).post(new zzbj(this));
        }

        public final boolean requiresSignIn() {
            return this.zzka.requiresSignIn();
        }

        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if(this.zzkh) {
                this.connect();
            }
        }

        public final void zza(zzb arg2) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if(this.zzka.isConnected()) {
                if(this.zzb(arg2)) {
                    this.zzbr();
                    return;
                }

                this.zzjz.add(arg2);
                return;
            }

            this.zzjz.add(arg2);
            if(this.zzkj != null && (this.zzkj.hasResolution())) {
                this.onConnectionFailed(this.zzkj);
                return;
            }

            this.connect();
        }

        static boolean zza(zza arg0, boolean arg1) {
            return arg0.zzb(false);
        }

        public final void zza(zzj arg2) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            this.zzkd.add(arg2);
        }

        static void zza(zza arg0, com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg1) {
            arg0.zza(arg1);
        }

        private final void zza(com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg2) {
            if(!this.zzki.contains(arg2)) {
                return;
            }

            if(!this.zzkh) {
                if(!this.zzka.isConnected()) {
                    this.connect();
                    return;
                }
                else {
                    this.zzbl();
                }
            }
        }

        public final void zza(ConnectionResult arg1, Api arg2, boolean arg3) {
            if(Looper.myLooper() == GoogleApiManager.zza(this.zzjy).getLooper()) {
                this.onConnectionFailed(arg1);
                return;
            }

            GoogleApiManager.zza(this.zzjy).post(new zzbk(this, arg1));
        }

        public final Client zzae() {
            return this.zzka;
        }

        public final void zzay() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if(this.zzkh) {
                this.zzbq();
                int v2 = 8;
                Status v0 = GoogleApiManager.zzh(this.zzjy).isGooglePlayServicesAvailable(GoogleApiManager.zzb(this.zzjy)) == 18 ? new Status(v2, "Connection timed out while waiting for Google Play services update to complete.") : new Status(v2, "API failed to connect while resuming due to an unknown error.");
                this.zzc(v0);
                this.zzka.disconnect();
            }
        }

        static void zzb(zza arg0, com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg1) {
            arg0.zzb(arg1);
        }

        private final boolean zzb(boolean arg3) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            if((this.zzka.isConnected()) && this.zzke.size() == 0) {
                if(this.zzkc.zzaj()) {
                    if(arg3) {
                        this.zzbr();
                    }

                    return 0;
                }
                else {
                    this.zzka.disconnect();
                    return 1;
                }
            }

            return 0;
        }

        private final void zzb(com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg6) {
            if(this.zzki.remove(arg6)) {
                GoogleApiManager.zza(this.zzjy).removeMessages(15, arg6);
                GoogleApiManager.zza(this.zzjy).removeMessages(16, arg6);
                Feature v6 = com.google.android.gms.common.api.internal.GoogleApiManager$zzb.zzd(arg6);
                ArrayList v0 = new ArrayList(this.zzjz.size());
                Iterator v1 = this.zzjz.iterator();
                while(v1.hasNext()) {
                    Object v2 = v1.next();
                    if(!(v2 instanceof zzf)) {
                        continue;
                    }

                    Feature[] v3 = v2.getRequiredFeatures();
                    if(v3 == null) {
                        continue;
                    }

                    if(!ArrayUtils.contains(((Object[])v3), v6)) {
                        continue;
                    }

                    v0.add(v2);
                }

                int v1_1 = v0.size();
                int v2_1 = 0;
                while(v2_1 < v1_1) {
                    Object v3_1 = v0.get(v2_1);
                    ++v2_1;
                    this.zzjz.remove(v3_1);
                    ((zzb)v3_1).zza(new UnsupportedApiCallException(v6));
                }
            }
        }

        private final boolean zzb(zzb arg15) {
            zzbh v9;
            Feature v7_1;
            if(!(arg15 instanceof zzf)) {
                this.zzc(arg15);
                return 1;
            }

            zzb v0 = arg15;
            Feature[] v2 = ((zzf)v0).getRequiredFeatures();
            if(v2 != null) {
                if(v2.length == 0) {
                }
                else {
                    Feature[] v3 = this.zzka.getAvailableFeatures();
                    if(v3 == null) {
                        v3 = new Feature[0];
                    }

                    a v5 = new a(v3.length);
                    int v6 = v3.length;
                    int v7;
                    for(v7 = 0; v7 < v6; ++v7) {
                        ((Map)v5).put(v3[v7].getName(), Long.valueOf(v3[v7].getVersion()));
                    }

                    int v3_1 = v2.length;
                    v6 = 0;
                    while(true) {
                        if(v6 >= v3_1) {
                            goto label_105;
                        }

                        v7_1 = v2[v6];
                        v9 = null;
                        if(((Map)v5).containsKey(v7_1.getName())) {
                            if(((Map)v5).get(v7_1.getName()).longValue() < v7_1.getVersion()) {
                            }
                            else {
                                this.zzki.remove(new com.google.android.gms.common.api.internal.GoogleApiManager$zzb(this.zzhc, v7_1, v9));
                                ++v6;
                                continue;
                            }
                        }

                        break;
                    }

                    if(((zzf)v0).shouldAutoResolveMissingFeatures()) {
                        com.google.android.gms.common.api.internal.GoogleApiManager$zzb v15 = new com.google.android.gms.common.api.internal.GoogleApiManager$zzb(this.zzhc, v7_1, v9);
                        int v0_1 = this.zzki.indexOf(v15);
                        int v1 = 15;
                        if(v0_1 >= 0) {
                            Object v15_1 = this.zzki.get(v0_1);
                            GoogleApiManager.zza(this.zzjy).removeMessages(v1, v15_1);
                            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), v1, v15_1), GoogleApiManager.zzc(this.zzjy));
                        }
                        else {
                            this.zzki.add(v15);
                            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), v1, v15), GoogleApiManager.zzc(this.zzjy));
                            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 16, v15), GoogleApiManager.zzd(this.zzjy));
                            ConnectionResult v15_2 = new ConnectionResult(2, ((PendingIntent)v9));
                            if(!this.zzh(v15_2)) {
                                this.zzjy.zzc(v15_2, this.zzkf);
                            }
                        }
                    }
                    else {
                        v0.zza(new UnsupportedApiCallException(v7_1));
                    }

                    return 0;
                label_105:
                    this.zzc(arg15);
                    return 1;
                }
            }

            this.zzc(arg15);
            return 1;
        }

        private final void zzbj() {
            this.zzbo();
            this.zzi(ConnectionResult.RESULT_SUCCESS);
            this.zzbq();
            Iterator v0 = this.zzke.values().iterator();
            while(true) {
                if(!v0.hasNext()) {
                    goto label_20;
                }

                Object v1 = v0.next();
                try {
                    ((zzbv)v1).zzlt.registerListener(this.zzkb, new TaskCompletionSource());
                    continue;
                }
                catch(DeadObjectException ) {
                    break;
                }
                catch(RemoteException ) {
                    continue;
                }
            }

            this.onConnectionSuspended(1);
            this.zzka.disconnect();
        label_20:
            this.zzbl();
            this.zzbr();
        }

        private final void zzbk() {
            this.zzbo();
            this.zzkh = true;
            this.zzkc.zzal();
            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 9, this.zzhc), GoogleApiManager.zzc(this.zzjy));
            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(Message.obtain(GoogleApiManager.zza(this.zzjy), 11, this.zzhc), GoogleApiManager.zzd(this.zzjy));
            GoogleApiManager.zze(this.zzjy).flush();
        }

        private final void zzbl() {
            ArrayList v0 = new ArrayList(this.zzjz);
            int v1 = v0.size();
            int v2 = 0;
            while(v2 < v1) {
                Object v3 = v0.get(v2);
                ++v2;
                if(!this.zzka.isConnected()) {
                    return;
                }

                if(!this.zzb(((zzb)v3))) {
                    continue;
                }

                this.zzjz.remove(v3);
            }
        }

        public final void zzbm() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            this.zzc(GoogleApiManager.zzjj);
            this.zzkc.zzak();
            Object[] v0 = this.zzke.keySet().toArray(new ListenerKey[this.zzke.size()]);
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                this.zza(new zzg(v0[v2], new TaskCompletionSource()));
            }

            this.zzi(new ConnectionResult(4));
            if(this.zzka.isConnected()) {
                this.zzka.onUserSignOut(new zzbl(this));
            }
        }

        public final Map zzbn() {
            return this.zzke;
        }

        public final void zzbo() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            this.zzkj = null;
        }

        public final ConnectionResult zzbp() {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            return this.zzkj;
        }

        private final void zzbq() {
            if(this.zzkh) {
                GoogleApiManager.zza(this.zzjy).removeMessages(11, this.zzhc);
                GoogleApiManager.zza(this.zzjy).removeMessages(9, this.zzhc);
                this.zzkh = false;
            }
        }

        private final void zzbr() {
            GoogleApiManager.zza(this.zzjy).removeMessages(12, this.zzhc);
            GoogleApiManager.zza(this.zzjy).sendMessageDelayed(GoogleApiManager.zza(this.zzjy).obtainMessage(12, this.zzhc), GoogleApiManager.zzi(this.zzjy));
        }

        public final boolean zzbs() {
            return this.zzb(true);
        }

        final SignInClient zzbt() {
            if(this.zzkg == null) {
                return null;
            }

            return this.zzkg.zzbt();
        }

        public final void zzc(Status arg3) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            Iterator v0 = this.zzjz.iterator();
            while(v0.hasNext()) {
                v0.next().zza(arg3);
            }

            this.zzjz.clear();
        }

        private final void zzc(zzb arg3) {
            arg3.zza(this.zzkc, this.requiresSignIn());
            try {
                arg3.zza(this);
                return;
            }
            catch(DeadObjectException ) {
                this.onConnectionSuspended(1);
                this.zzka.disconnect();
                return;
            }
        }

        static void zzc(zza arg0) {
            arg0.zzbj();
        }

        static void zzd(zza arg0) {
            arg0.zzbk();
        }

        static Client zze(zza arg0) {
            return arg0.zzka;
        }

        public final void zzg(ConnectionResult arg2) {
            Preconditions.checkHandlerThread(GoogleApiManager.zza(this.zzjy));
            this.zzka.disconnect();
            this.onConnectionFailed(arg2);
        }

        private final boolean zzh(ConnectionResult arg4) {
            Object v0 = GoogleApiManager.zzbh();
            __monitor_enter(v0);
            try {
                if(GoogleApiManager.zzf(this.zzjy) != null && (GoogleApiManager.zzg(this.zzjy).contains(this.zzhc))) {
                    GoogleApiManager.zzf(this.zzjy).zzb(arg4, this.zzkf);
                    __monitor_exit(v0);
                    return 1;
                }

                __monitor_exit(v0);
                return 0;
            label_21:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_21;
            }

            throw v4;
        }

        private final void zzi(ConnectionResult arg5) {
            Iterator v0 = this.zzkd.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                String v2 = null;
                if(Objects.equal(arg5, ConnectionResult.RESULT_SUCCESS)) {
                    v2 = this.zzka.getEndpointPackageName();
                }

                ((zzj)v1).zza(this.zzhc, arg5, v2);
            }

            this.zzkd.clear();
        }
    }

    final class com.google.android.gms.common.api.internal.GoogleApiManager$zzb {
        private final Feature zzdr;
        private final zzh zzkn;

        private com.google.android.gms.common.api.internal.GoogleApiManager$zzb(zzh arg1, Feature arg2) {
            super();
            this.zzkn = arg1;
            this.zzdr = arg2;
        }

        com.google.android.gms.common.api.internal.GoogleApiManager$zzb(zzh arg1, Feature arg2, zzbh arg3) {
            this(arg1, arg2);
        }

        public final boolean equals(Object arg4) {
            if(arg4 != null && ((arg4 instanceof com.google.android.gms.common.api.internal.GoogleApiManager$zzb)) && (Objects.equal(this.zzkn, ((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)arg4).zzkn)) && (Objects.equal(this.zzdr, ((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)arg4).zzdr))) {
                return 1;
            }

            return 0;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.zzkn, this.zzdr});
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("key", this.zzkn).add("feature", this.zzdr).toString();
        }

        static zzh zzc(com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg0) {
            return arg0.zzkn;
        }

        static Feature zzd(com.google.android.gms.common.api.internal.GoogleApiManager$zzb arg0) {
            return arg0.zzdr;
        }
    }

    final class zzc implements zzcb, ConnectionProgressReportCallbacks {
        private final zzh zzhc;
        private final Client zzka;
        private IAccountAccessor zzko;
        private Set zzkp;
        private boolean zzkq;

        public zzc(GoogleApiManager arg1, Client arg2, zzh arg3) {
            this.zzjy = arg1;
            super();
            this.zzko = null;
            this.zzkp = null;
            this.zzkq = false;
            this.zzka = arg2;
            this.zzhc = arg3;
        }

        public final void onReportServiceBinding(ConnectionResult arg3) {
            GoogleApiManager.zza(this.zzjy).post(new zzbn(this, arg3));
        }

        static Client zza(zzc arg0) {
            return arg0.zzka;
        }

        static boolean zza(zzc arg0, boolean arg1) {
            arg0.zzkq = true;
            return 1;
        }

        public final void zza(IAccountAccessor arg2, Set arg3) {
            if(arg2 != null) {
                if(arg3 == null) {
                }
                else {
                    this.zzko = arg2;
                    this.zzkp = arg3;
                    this.zzbu();
                    return;
                }
            }

            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            this.zzg(new ConnectionResult(4));
        }

        static void zzb(zzc arg0) {
            arg0.zzbu();
        }

        private final void zzbu() {
            if((this.zzkq) && this.zzko != null) {
                this.zzka.getRemoteService(this.zzko, this.zzkp);
            }
        }

        static zzh zzc(zzc arg0) {
            return arg0.zzhc;
        }

        public final void zzg(ConnectionResult arg3) {
            GoogleApiManager.zzj(this.zzjy).get(this.zzhc).zzg(arg3);
        }
    }

    private final Handler handler;
    private static final Object lock;
    public static final Status zzjj;
    private static final Status zzjk;
    private long zzjl;
    private long zzjm;
    private long zzjn;
    @GuardedBy(value="lock") private static GoogleApiManager zzjo;
    private final Context zzjp;
    private final GoogleApiAvailability zzjq;
    private final GoogleApiAvailabilityCache zzjr;
    private final AtomicInteger zzjs;
    private final AtomicInteger zzjt;
    private final Map zzju;
    @GuardedBy(value="lock") private zzad zzjv;
    @GuardedBy(value="lock") private final Set zzjw;
    private final Set zzjx;

    static {
        GoogleApiManager.zzjj = new Status(4, "Sign-out occurred while this API call was in progress.");
        GoogleApiManager.zzjk = new Status(4, "The user must be signed in to make this API call.");
        GoogleApiManager.lock = new Object();
    }

    @KeepForSdk private GoogleApiManager(Context arg5, Looper arg6, GoogleApiAvailability arg7) {
        super();
        this.zzjl = 5000;
        this.zzjm = 120000;
        this.zzjn = 10000;
        this.zzjs = new AtomicInteger(1);
        this.zzjt = new AtomicInteger(0);
        this.zzju = new ConcurrentHashMap(5, 0.75f, 1);
        this.zzjv = null;
        this.zzjw = new b();
        this.zzjx = new b();
        this.zzjp = arg5;
        this.handler = new Handler(arg6, ((Handler$Callback)this));
        this.zzjq = arg7;
        this.zzjr = new GoogleApiAvailabilityCache(((GoogleApiAvailabilityLight)arg7));
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    public boolean handleMessage(Message arg8) {
        Object v3;
        Boolean v0_3;
        TaskCompletionSource v8_3;
        Object v0_1;
        Object v8_2;
        Iterator v8_1;
        int v0;
        long v2 = 300000;
        String v4 = null;
        switch(arg8.what) {
            case 1: {
                if(arg8.obj.booleanValue()) {
                    v2 = 10000;
                }

                this.zzjn = v2;
                v0 = 12;
                this.handler.removeMessages(v0);
                v8_1 = this.zzju.keySet().iterator();
                while(v8_1.hasNext()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(v0, v8_1.next()), this.zzjn);
                }
            }
            case 2: {
                v8_2 = arg8.obj;
                Iterator v0_5 = ((zzj)v8_2).zzs().iterator();
                while(v0_5.hasNext()) {
                    Object v2_2 = v0_5.next();
                    v3 = this.zzju.get(v2_2);
                    if(v3 == null) {
                        ((zzj)v8_2).zza(((zzh)v2_2), new ConnectionResult(13), v4);
                        return 1;
                    }

                    if(((zza)v3).isConnected()) {
                        ((zzj)v8_2).zza(((zzh)v2_2), ConnectionResult.RESULT_SUCCESS, ((zza)v3).zzae().getEndpointPackageName());
                        continue;
                    }

                    if(((zza)v3).zzbp() != null) {
                        ((zzj)v8_2).zza(((zzh)v2_2), ((zza)v3).zzbp(), v4);
                        continue;
                    }

                    ((zza)v3).zza(((zzj)v8_2));
                }
            }
            case 3: {
                v8_1 = this.zzju.values().iterator();
                while(v8_1.hasNext()) {
                    v0_1 = v8_1.next();
                    ((zza)v0_1).zzbo();
                    ((zza)v0_1).connect();
                }
            }
            case 5: {
                v0 = arg8.arg1;
                v8_2 = arg8.obj;
                Iterator v2_3 = this.zzju.values().iterator();
                do {
                    if(v2_3.hasNext()) {
                        v3 = v2_3.next();
                        if(((zza)v3).getInstanceId() != v0) {
                            continue;
                        }

                        break;
                    }
                    else {
                        goto label_123;
                    }
                }
                while(true);

                goto label_124;
            label_123:
                v3 = v4;
            label_124:
                if(v3 != null) {
                    v4 = this.zzjq.getErrorString(((ConnectionResult)v8_2).getErrorCode());
                    String v8_4 = ((ConnectionResult)v8_2).getErrorMessage();
                    StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 69 + String.valueOf(v8_4).length());
                    v6.append("Error resolution was canceled by the user, original error message: ");
                    v6.append(v4);
                    v6.append(": ");
                    v6.append(v8_4);
                    ((zza)v3).zzc(new Status(17, v6.toString()));
                    return 1;
                }

                StringBuilder v3_1 = new StringBuilder(76);
                v3_1.append("Could not find API instance ");
                v3_1.append(v0);
                v3_1.append(" while trying to fail enqueued calls.");
                Log.wtf("GoogleApiManager", v3_1.toString(), new Exception());
                break;
            }
            case 6: {
                if(!PlatformVersion.isAtLeastIceCreamSandwich()) {
                    return 1;
                }

                if(!(this.zzjp.getApplicationContext() instanceof Application)) {
                    return 1;
                }

                BackgroundDetector.initialize(this.zzjp.getApplicationContext());
                BackgroundDetector.getInstance().addListener(new zzbh(this));
                if(BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                    return 1;
                }

                this.zzjn = v2;
                break;
            }
            case 7: {
                this.zzb(arg8.obj);
                break;
            }
            case 9: {
                if(!this.zzju.containsKey(arg8.obj)) {
                    return 1;
                }

                this.zzju.get(arg8.obj).resume();
                break;
            }
            case 10: {
                v8_1 = this.zzjx.iterator();
                while(v8_1.hasNext()) {
                    this.zzju.remove(v8_1.next()).zzbm();
                }

                this.zzjx.clear();
                break;
            }
            case 11: {
                if(!this.zzju.containsKey(arg8.obj)) {
                    return 1;
                }

                this.zzju.get(arg8.obj).zzay();
                break;
            }
            case 12: {
                if(!this.zzju.containsKey(arg8.obj)) {
                    return 1;
                }

                this.zzju.get(arg8.obj).zzbs();
                break;
            }
            case 4: 
            case 8: 
            case 13: {
                v8_2 = arg8.obj;
                v0_1 = this.zzju.get(((zzbu)v8_2).zzlr.zzm());
                if(v0_1 == null) {
                    this.zzb(((zzbu)v8_2).zzlr);
                    v0_1 = this.zzju.get(((zzbu)v8_2).zzlr.zzm());
                }

                if((((zza)v0_1).requiresSignIn()) && this.zzjt.get() != ((zzbu)v8_2).zzlq) {
                    ((zzbu)v8_2).zzlp.zza(GoogleApiManager.zzjj);
                    ((zza)v0_1).zzbm();
                    return 1;
                }

                ((zza)v0_1).zza(((zzbu)v8_2).zzlp);
                break;
            }
            case 14: {
                v8_2 = arg8.obj;
                zzh v0_2 = ((zzae)v8_2).zzm();
                if(!this.zzju.containsKey(v0_2)) {
                    v8_3 = ((zzae)v8_2).zzao();
                    v0_3 = Boolean.valueOf(false);
                }
                else {
                    boolean v0_4 = zza.zza(this.zzju.get(v0_2), false);
                    v8_3 = ((zzae)v8_2).zzao();
                    v0_3 = Boolean.valueOf(v0_4);
                }

                v8_3.setResult(v0_3);
                break;
            }
            case 15: {
                v8_2 = arg8.obj;
                if(!this.zzju.containsKey(com.google.android.gms.common.api.internal.GoogleApiManager$zzb.zzc(((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2)))) {
                    return 1;
                }

                zza.zza(this.zzju.get(com.google.android.gms.common.api.internal.GoogleApiManager$zzb.zzc(((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2))), ((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2));
                break;
            }
            case 16: {
                v8_2 = arg8.obj;
                if(!this.zzju.containsKey(com.google.android.gms.common.api.internal.GoogleApiManager$zzb.zzc(((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2)))) {
                    return 1;
                }

                zza.zzb(this.zzju.get(com.google.android.gms.common.api.internal.GoogleApiManager$zzb.zzc(((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2))), ((com.google.android.gms.common.api.internal.GoogleApiManager$zzb)v8_2));
                break;
            }
            default: {
                int v8 = arg8.what;
                StringBuilder v2_1 = new StringBuilder(31);
                v2_1.append("Unknown message id: ");
                v2_1.append(v8);
                Log.w("GoogleApiManager", v2_1.toString());
                return 0;
            }
        }

        return 1;
    }

    final void maybeSignOut() {
        this.zzjt.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    @KeepForSdk public static void reportSignOut() {
        Object v0 = GoogleApiManager.lock;
        __monitor_enter(v0);
        try {
            if(GoogleApiManager.zzjo != null) {
                GoogleApiManager v1_1 = GoogleApiManager.zzjo;
                v1_1.zzjt.incrementAndGet();
                v1_1.handler.sendMessageAtFrontOfQueue(v1_1.handler.obtainMessage(10));
            }

            __monitor_exit(v0);
            return;
        label_15:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_15;
        }

        throw v1;
    }

    public final void zza(GoogleApi arg4) {
        this.handler.sendMessage(this.handler.obtainMessage(7, arg4));
    }

    public final void zza(GoogleApi arg4, int arg5, ApiMethodImpl arg6) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzd(arg5, arg6), this.zzjt.get(), arg4)));
    }

    public final void zza(GoogleApi arg2, int arg3, TaskApiCall arg4, TaskCompletionSource arg5, StatusExceptionMapper arg6) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzf(arg3, arg4, arg5, arg6), this.zzjt.get(), arg2)));
    }

    public final Task zza(GoogleApi arg5, RegisterListenerMethod arg6, UnregisterListenerMethod arg7) {
        TaskCompletionSource v0 = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zzbu(new zze(new zzbv(arg6, arg7), v0), this.zzjt.get(), arg5)));
        return v0.getTask();
    }

    public final Task zza(GoogleApi arg6, ListenerKey arg7) {
        TaskCompletionSource v0 = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zzbu(new zzg(arg7, v0), this.zzjt.get(), arg6)));
        return v0.getTask();
    }

    public final void zza(ConnectionResult arg5, int arg6) {
        if(!this.zzc(arg5, arg6)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, arg6, 0, arg5));
        }
    }

    static Handler zza(GoogleApiManager arg0) {
        return arg0.handler;
    }

    final PendingIntent zza(zzh arg3, int arg4) {
        Object v3 = this.zzju.get(arg3);
        PendingIntent v0 = null;
        if(v3 == null) {
            return v0;
        }

        SignInClient v3_1 = ((zza)v3).zzbt();
        if(v3_1 == null) {
            return v0;
        }

        return PendingIntent.getActivity(this.zzjp, arg4, v3_1.getSignInIntent(), 134217728);
    }

    public final Task zza(Iterable arg4) {
        zzj v0 = new zzj(arg4);
        this.handler.sendMessage(this.handler.obtainMessage(2, v0));
        return v0.getTask();
    }

    public final void zza(zzad arg3) {
        Object v0 = GoogleApiManager.lock;
        __monitor_enter(v0);
        try {
            if(this.zzjv != arg3) {
                this.zzjv = arg3;
                this.zzjw.clear();
            }

            this.zzjw.addAll(arg3.zzam());
            __monitor_exit(v0);
            return;
        label_13:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_13;
        }

        throw v3;
    }

    public static GoogleApiManager zzb(Context arg4) {
        Object v0 = GoogleApiManager.lock;
        __monitor_enter(v0);
        try {
            if(GoogleApiManager.zzjo == null) {
                HandlerThread v1 = new HandlerThread("GoogleApiHandler", 9);
                v1.start();
                GoogleApiManager.zzjo = new GoogleApiManager(arg4.getApplicationContext(), v1.getLooper(), GoogleApiAvailability.getInstance());
            }

            __monitor_exit(v0);
            return GoogleApiManager.zzjo;
        label_19:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_19;
        }

        throw v4;
    }

    static Context zzb(GoogleApiManager arg0) {
        return arg0.zzjp;
    }

    private final void zzb(GoogleApi arg3) {
        zzh v0 = arg3.zzm();
        Object v1 = this.zzju.get(v0);
        if(v1 == null) {
            zza v1_1 = new zza(this, arg3);
            this.zzju.put(v0, v1_1);
        }

        if(((zza)v1).requiresSignIn()) {
            this.zzjx.add(v0);
        }

        ((zza)v1).connect();
    }

    final void zzb(zzad arg3) {
        Object v0 = GoogleApiManager.lock;
        __monitor_enter(v0);
        try {
            if(this.zzjv == arg3) {
                this.zzjv = null;
                this.zzjw.clear();
            }

            __monitor_exit(v0);
            return;
        label_11:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_11;
        }

        throw v3;
    }

    public static GoogleApiManager zzbf() {
        Object v0 = GoogleApiManager.lock;
        __monitor_enter(v0);
        try {
            Preconditions.checkNotNull(GoogleApiManager.zzjo, "Must guarantee manager is non-null before using getInstance");
            __monitor_exit(v0);
            return GoogleApiManager.zzjo;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_9;
        }

        throw v1;
    }

    public final int zzbg() {
        return this.zzjs.getAndIncrement();
    }

    static Object zzbh() {
        return GoogleApiManager.lock;
    }

    static Status zzbi() {
        return GoogleApiManager.zzjk;
    }

    public final Task zzc(GoogleApi arg4) {
        zzae v0 = new zzae(arg4.zzm());
        this.handler.sendMessage(this.handler.obtainMessage(14, v0));
        return v0.zzao().getTask();
    }

    static long zzc(GoogleApiManager arg2) {
        return arg2.zzjl;
    }

    final boolean zzc(ConnectionResult arg3, int arg4) {
        return this.zzjq.showWrappedErrorNotification(this.zzjp, arg3, arg4);
    }

    static long zzd(GoogleApiManager arg2) {
        return arg2.zzjm;
    }

    static GoogleApiAvailabilityCache zze(GoogleApiManager arg0) {
        return arg0.zzjr;
    }

    static zzad zzf(GoogleApiManager arg0) {
        return arg0.zzjv;
    }

    static Set zzg(GoogleApiManager arg0) {
        return arg0.zzjw;
    }

    static GoogleApiAvailability zzh(GoogleApiManager arg0) {
        return arg0.zzjq;
    }

    static long zzi(GoogleApiManager arg2) {
        return arg2.zzjn;
    }

    static Map zzj(GoogleApiManager arg0) {
        return arg0.zzju;
    }

    public final void zzr() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }
}

