package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.ISignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.Set;

public final class zzby extends BaseSignInCallbacks implements ConnectionCallbacks, OnConnectionFailedListener {
    private final Context mContext;
    private final Handler mHandler;
    private Set mScopes;
    private final AbstractClientBuilder zzby;
    private ClientSettings zzgf;
    private SignInClient zzhn;
    private static AbstractClientBuilder zzlv;
    private zzcb zzlw;

    static {
        zzby.zzlv = SignIn.CLIENT_BUILDER;
    }

    public zzby(Context arg2, Handler arg3, ClientSettings arg4) {
        this(arg2, arg3, arg4, zzby.zzlv);
    }

    public zzby(Context arg1, Handler arg2, ClientSettings arg3, AbstractClientBuilder arg4) {
        super();
        this.mContext = arg1;
        this.mHandler = arg2;
        this.zzgf = Preconditions.checkNotNull(arg3, "ClientSettings must not be null");
        this.mScopes = arg3.getRequiredScopes();
        this.zzby = arg4;
    }

    public final void onConnected(Bundle arg1) {
        this.zzhn.signIn(((ISignInCallbacks)this));
    }

    public final void onConnectionFailed(ConnectionResult arg2) {
        this.zzlw.zzg(arg2);
    }

    public final void onConnectionSuspended(int arg1) {
        this.zzhn.disconnect();
    }

    public final void onSignInComplete(SignInResponse arg3) {
        this.mHandler.post(new zzca(this, arg3));
    }

    public final void zza(zzcb arg10) {
        if(this.zzhn != null) {
            this.zzhn.disconnect();
        }

        this.zzgf.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
        this.zzhn = this.zzby.buildClient(this.mContext, this.mHandler.getLooper(), this.zzgf, this.zzgf.getSignInOptions(), this, this);
        this.zzlw = arg10;
        if(this.mScopes != null) {
            if(this.mScopes.isEmpty()) {
            }
            else {
                this.zzhn.connect();
                return;
            }
        }

        this.mHandler.post(new zzbz(this));
    }

    static zzcb zza(zzby arg0) {
        return arg0.zzlw;
    }

    static void zza(zzby arg0, SignInResponse arg1) {
        arg0.zzb(arg1);
    }

    private final void zzb(SignInResponse arg5) {
        ConnectionResult v0 = arg5.getConnectionResult();
        if(v0.isSuccess()) {
            ResolveAccountResponse v5 = arg5.getResolveAccountResponse();
            v0 = v5.getConnectionResult();
            if(!v0.isSuccess()) {
                String v1 = String.valueOf(v0);
                StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 48);
                v3.append("Sign-in succeeded with resolve account failure: ");
                v3.append(v1);
                Log.wtf("SignInCoordinator", v3.toString(), new Exception());
                goto label_21;
            }
            else {
                this.zzlw.zza(v5.getAccountAccessor(), this.mScopes);
            }
        }
        else {
        label_21:
            this.zzlw.zzg(v0);
        }

        this.zzhn.disconnect();
    }

    public final SignInClient zzbt() {
        return this.zzhn;
    }

    public final void zzbz() {
        if(this.zzhn != null) {
            this.zzhn.disconnect();
        }
    }
}

