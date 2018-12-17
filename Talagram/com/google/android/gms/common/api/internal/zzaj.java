package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zzaj implements zzbc {
    private final Context mContext;
    private final AbstractClientBuilder zzdh;
    private final Lock zzga;
    private final ClientSettings zzgf;
    private final Map zzgi;
    private final GoogleApiAvailabilityLight zzgk;
    private ConnectionResult zzgt;
    private final zzbd zzhf;
    private int zzhi;
    private int zzhj;
    private int zzhk;
    private final Bundle zzhl;
    private final Set zzhm;
    private SignInClient zzhn;
    private boolean zzho;
    private boolean zzhp;
    private boolean zzhq;
    private IAccountAccessor zzhr;
    private boolean zzhs;
    private boolean zzht;
    private ArrayList zzhu;

    public zzaj(zzbd arg2, ClientSettings arg3, Map arg4, GoogleApiAvailabilityLight arg5, AbstractClientBuilder arg6, Lock arg7, Context arg8) {
        super();
        this.zzhj = 0;
        this.zzhl = new Bundle();
        this.zzhm = new HashSet();
        this.zzhu = new ArrayList();
        this.zzhf = arg2;
        this.zzgf = arg3;
        this.zzgi = arg4;
        this.zzgk = arg5;
        this.zzdh = arg6;
        this.zzga = arg7;
        this.mContext = arg8;
    }

    public final void begin() {
        this.zzhf.zzjb.clear();
        this.zzhp = false;
        ConnectionResult v1 = null;
        this.zzgt = v1;
        this.zzhj = 0;
        this.zzho = true;
        this.zzhq = false;
        this.zzhs = false;
        HashMap v3 = new HashMap();
        Iterator v4 = this.zzgi.keySet().iterator();
        int v5 = 0;
        while(v4.hasNext()) {
            Object v6 = v4.next();
            Object v7 = this.zzhf.zzil.get(((Api)v6).getClientKey());
            int v8 = ((Api)v6).zzj().getPriority() == 1 ? 1 : 0;
            v5 |= v8;
            boolean v8_1 = this.zzgi.get(v6).booleanValue();
            if(((Client)v7).requiresSignIn()) {
                this.zzhp = true;
                if(v8_1) {
                    this.zzhm.add(((Api)v6).getClientKey());
                }
                else {
                    this.zzho = false;
                }
            }

            ((Map)v3).put(v7, new zzal(this, ((Api)v6), v8_1));
        }

        if(v5 != 0) {
            this.zzhp = false;
        }

        if(this.zzhp) {
            this.zzgf.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zzhf.zzfq)));
            zzas v10 = new zzas(this, ((zzak)v1));
            this.zzhn = this.zzdh.buildClient(this.mContext, this.zzhf.zzfq.getLooper(), this.zzgf, this.zzgf.getSignInOptions(), v10, ((OnConnectionFailedListener)v10));
        }

        this.zzhk = this.zzhf.zzil.size();
        this.zzhu.add(zzbg.zzbe().submit(new zzam(this, ((Map)v3))));
    }

    public final void connect() {
    }

    public final boolean disconnect() {
        this.zzav();
        this.zza(true);
        this.zzhf.zzf(null);
        return 1;
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg2) {
        this.zzhf.zzfq.zzgo.add(arg2);
        return arg2;
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg2) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @GuardedBy(value="mLock") public final void onConnected(Bundle arg2) {
        if(!this.zze(1)) {
            return;
        }

        if(arg2 != null) {
            this.zzhl.putAll(arg2);
        }

        if(this.zzar()) {
            this.zzat();
        }
    }

    @GuardedBy(value="mLock") public final void onConnectionSuspended(int arg3) {
        this.zze(new ConnectionResult(8, null));
    }

    static Context zza(zzaj arg0) {
        return arg0.mContext;
    }

    static void zza(zzaj arg0, ConnectionResult arg1) {
        arg0.zze(arg1);
    }

    static void zza(zzaj arg0, ConnectionResult arg1, Api arg2, boolean arg3) {
        arg0.zzb(arg1, arg2, arg3);
    }

    static void zza(zzaj arg0, SignInResponse arg1) {
        arg0.zza(arg1);
    }

    @GuardedBy(value="mLock") private final void zza(SignInResponse arg5) {
        if(!this.zze(0)) {
            return;
        }

        ConnectionResult v0 = arg5.getConnectionResult();
        if(v0.isSuccess()) {
            ResolveAccountResponse v5 = arg5.getResolveAccountResponse();
            v0 = v5.getConnectionResult();
            if(!v0.isSuccess()) {
                String v1 = String.valueOf(v0);
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 48);
                v3.append("Sign-in succeeded with resolve account failure: ");
                v3.append(v1);
                Log.wtf("GoogleApiClientConnecting", v3.toString(), new Exception());
                this.zze(v0);
                return;
            }

            this.zzhq = true;
            this.zzhr = v5.getAccountAccessor();
            this.zzhs = v5.getSaveDefaultAccount();
            this.zzht = v5.isFromCrossClientAuth();
            this.zzas();
            return;
        }

        if(this.zzd(v0)) {
            this.zzau();
            this.zzas();
            return;
        }

        this.zze(v0);
    }

    private final void zza(boolean arg2) {
        if(this.zzhn != null) {
            if((this.zzhn.isConnected()) && (arg2)) {
                this.zzhn.clearAccountFromSessionStore();
            }

            this.zzhn.disconnect();
            this.zzhr = null;
        }
    }

    static boolean zza(zzaj arg0, int arg1) {
        return arg0.zze(0);
    }

    @GuardedBy(value="mLock") public final void zza(ConnectionResult arg2, Api arg3, boolean arg4) {
        if(!this.zze(1)) {
            return;
        }

        this.zzb(arg2, arg3, arg4);
        if(this.zzar()) {
            this.zzat();
        }
    }

    @GuardedBy(value="mLock") private final boolean zzar() {
        ConnectionResult v0;
        --this.zzhk;
        if(this.zzhk > 0) {
            return 0;
        }

        if(this.zzhk < 0) {
            Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            v0 = new ConnectionResult(8, null);
        }
        else if(this.zzgt != null) {
            this.zzhf.zzje = this.zzhi;
            v0 = this.zzgt;
        }
        else {
            return 1;
        }

        this.zze(v0);
        return 0;
    }

    @GuardedBy(value="mLock") private final void zzas() {
        if(this.zzhk != 0) {
            return;
        }

        if(!this.zzhp || (this.zzhq)) {
            ArrayList v0 = new ArrayList();
            this.zzhj = 1;
            this.zzhk = this.zzhf.zzil.size();
            Iterator v1 = this.zzhf.zzil.keySet().iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                if(this.zzhf.zzjb.containsKey(v2)) {
                    if(!this.zzar()) {
                        continue;
                    }

                    this.zzat();
                }
                else {
                    v0.add(this.zzhf.zzil.get(v2));
                }
            }

            if(!v0.isEmpty()) {
                this.zzhu.add(zzbg.zzbe().submit(new zzap(this, v0)));
            }
        }
    }

    @GuardedBy(value="mLock") private final void zzat() {
        this.zzhf.zzbd();
        zzbg.zzbe().execute(new zzak(this));
        if(this.zzhn != null) {
            if(this.zzhs) {
                this.zzhn.saveDefaultAccount(this.zzhr, this.zzht);
            }

            this.zza(false);
        }

        Iterator v0 = this.zzhf.zzjb.keySet().iterator();
        while(v0.hasNext()) {
            this.zzhf.zzil.get(v0.next()).disconnect();
        }

        Bundle v0_1 = this.zzhl.isEmpty() ? null : this.zzhl;
        this.zzhf.zzjf.zzb(v0_1);
    }

    @GuardedBy(value="mLock") private final void zzau() {
        this.zzhp = false;
        this.zzhf.zzfq.zzim = Collections.emptySet();
        Iterator v0 = this.zzhm.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(this.zzhf.zzjb.containsKey(v1)) {
                continue;
            }

            this.zzhf.zzjb.put(v1, new ConnectionResult(17, null));
        }
    }

    private final void zzav() {
        ArrayList v0 = this.zzhu;
        int v1 = v0.size();
        int v2 = 0;
        while(v2 < v1) {
            Object v3 = v0.get(v2);
            ++v2;
            ((Future)v3).cancel(true);
        }

        this.zzhu.clear();
    }

    private final Set zzaw() {
        if(this.zzgf == null) {
            return Collections.emptySet();
        }

        HashSet v0 = new HashSet(this.zzgf.getRequiredScopes());
        Map v1 = this.zzgf.getOptionalApiSettings();
        Iterator v2 = v1.keySet().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(this.zzhf.zzjb.containsKey(((Api)v3).getClientKey())) {
                continue;
            }

            ((Set)v0).addAll(v1.get(v3).mScopes);
        }

        return ((Set)v0);
    }

    static GoogleApiAvailabilityLight zzb(zzaj arg0) {
        return arg0.zzgk;
    }

    @GuardedBy(value="mLock") private final void zzb(ConnectionResult arg5, Api arg6, boolean arg7) {
        int v0 = arg6.zzj().getPriority();
        int v1 = 0;
        if(arg7) {
            if((arg5.hasResolution()) || this.zzgk.getErrorResolutionIntent(arg5.getErrorCode()) != null) {
                int v7 = 1;
                goto label_15;
            label_14:
                v7 = 0;
            }
            else {
                goto label_14;
            }

        label_15:
            if(v7 == 0) {
                goto label_21;
            }

            goto label_16;
        }
        else {
        label_16:
            if(this.zzgt != null && v0 >= this.zzhi) {
                goto label_21;
            }

            v1 = 1;
        }

    label_21:
        if(v1 != 0) {
            this.zzgt = arg5;
            this.zzhi = v0;
        }

        this.zzhf.zzjb.put(arg6.getClientKey(), arg5);
    }

    static boolean zzb(zzaj arg0, ConnectionResult arg1) {
        return arg0.zzd(arg1);
    }

    static Lock zzc(zzaj arg0) {
        return arg0.zzga;
    }

    static zzbd zzd(zzaj arg0) {
        return arg0.zzhf;
    }

    @GuardedBy(value="mLock") private final boolean zzd(ConnectionResult arg2) {
        if((this.zzho) && !arg2.hasResolution()) {
            return 1;
        }

        return 0;
    }

    @GuardedBy(value="mLock") private final void zze(ConnectionResult arg2) {
        this.zzav();
        this.zza(arg2.hasResolution() ^ 1);
        this.zzhf.zzf(arg2);
        this.zzhf.zzjf.zzc(arg2);
    }

    @GuardedBy(value="mLock") private final boolean zze(int arg5) {
        if(this.zzhj != arg5) {
            Log.w("GoogleApiClientConnecting", this.zzhf.zzfq.zzbb());
            String v1 = String.valueOf(this);
            StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 23);
            v3.append("Unexpected callback in ");
            v3.append(v1);
            Log.w("GoogleApiClientConnecting", v3.toString());
            int v1_1 = this.zzhk;
            v3 = new StringBuilder(33);
            v3.append("mRemainingConnections=");
            v3.append(v1_1);
            Log.w("GoogleApiClientConnecting", v3.toString());
            v1 = zzaj.zzf(this.zzhj);
            String v5 = zzaj.zzf(arg5);
            v3 = new StringBuilder(String.valueOf(v1).length() + 70 + String.valueOf(v5).length());
            v3.append("GoogleApiClient connecting is in step ");
            v3.append(v1);
            v3.append(" but received callback for step ");
            v3.append(v5);
            Log.wtf("GoogleApiClientConnecting", v3.toString(), new Exception());
            this.zze(new ConnectionResult(8, null));
            return 0;
        }

        return 1;
    }

    static boolean zze(zzaj arg0) {
        return arg0.zzhp;
    }

    private static String zzf(int arg0) {
        switch(arg0) {
            case 0: {
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            }
            case 1: {
                return "STEP_GETTING_REMOTE_SERVICE";
            }
        }

        return "UNKNOWN";
    }

    static SignInClient zzf(zzaj arg0) {
        return arg0.zzhn;
    }

    static Set zzg(zzaj arg0) {
        return arg0.zzaw();
    }

    static IAccountAccessor zzh(zzaj arg0) {
        return arg0.zzhr;
    }

    static void zzi(zzaj arg0) {
        arg0.zzau();
    }

    static void zzj(zzaj arg0) {
        arg0.zzas();
    }

    static boolean zzk(zzaj arg0) {
        return arg0.zzar();
    }
}

