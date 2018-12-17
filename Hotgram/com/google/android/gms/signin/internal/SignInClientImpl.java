package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient$LegacyClientCallbackAdapter;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;

public class SignInClientImpl extends GmsClient implements SignInClient {
    public static final String ACTION_START_SERVICE = "com.google.android.gms.signin.service.START";
    public static final String INTERNAL_ACTION_START_SERVICE = "com.google.android.gms.signin.service.INTERNAL_START";
    public static final String KEY_AUTH_API_SIGN_IN_MODULE_VERSION = "com.google.android.gms.signin.internal.authApiSignInModuleVersion";
    public static final String KEY_CLIENT_REQUESTED_ACCOUNT = "com.google.android.gms.signin.internal.clientRequestedAccount";
    public static final String KEY_FORCE_CODE_FOR_REFRESH_TOKEN = "com.google.android.gms.signin.internal.forceCodeForRefreshToken";
    public static final String KEY_HOSTED_DOMAIN = "com.google.android.gms.signin.internal.hostedDomain";
    public static final String KEY_ID_TOKEN_REQUESTED = "com.google.android.gms.signin.internal.idTokenRequested";
    @Deprecated public static final String KEY_OFFLINE_ACCESS_CALLBACKS = "com.google.android.gms.signin.internal.signInCallbacks";
    public static final String KEY_OFFLINE_ACCESS_REQUESTED = "com.google.android.gms.signin.internal.offlineAccessRequested";
    public static final String KEY_REAL_CLIENT_LIBRARY_VERSION = "com.google.android.gms.signin.internal.realClientLibraryVersion";
    public static final String KEY_REAL_CLIENT_PACKAGE_NAME = "com.google.android.gms.signin.internal.realClientPackageName";
    public static final String KEY_SERVER_CLIENT_ID = "com.google.android.gms.signin.internal.serverClientId";
    public static final String KEY_USE_PROMPT_MODE_FOR_AUTH_CODE = "com.google.android.gms.signin.internal.usePromptModeForAuthCode";
    public static final String KEY_WAIT_FOR_ACCESS_TOKEN_REFRESH = "com.google.android.gms.signin.internal.waitForAccessTokenRefresh";
    private final Bundle zzada;
    private final boolean zzads;
    private final ClientSettings zzgf;
    private Integer zzsc;

    public SignInClientImpl(Context arg9, Looper arg10, boolean arg11, ClientSettings arg12, SignInOptions arg13, ConnectionCallbacks arg14, OnConnectionFailedListener arg15) {
        this(arg9, arg10, arg11, arg12, SignInClientImpl.createBundleFromClientSettings(arg12), arg14, arg15);
    }

    public SignInClientImpl(Context arg8, Looper arg9, boolean arg10, ClientSettings arg11, Bundle arg12, ConnectionCallbacks arg13, OnConnectionFailedListener arg14) {
        super(arg8, arg9, 44, arg11, arg13, arg14);
        this.zzads = arg10;
        this.zzgf = arg11;
        this.zzada = arg12;
        this.zzsc = arg11.getClientSessionId();
    }

    public void clearAccountFromSessionStore() {
        try {
            ((BaseGmsClient)this).getService().clearAccountFromSessionStore(this.zzsc.intValue());
            return;
        }
        catch(RemoteException ) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
            return;
        }
    }

    public void connect() {
        ((BaseGmsClient)this).connect(new LegacyClientCallbackAdapter(((BaseGmsClient)this)));
    }

    public static Bundle createBundleFromClientSettings(ClientSettings arg5) {
        SignInOptions v0 = arg5.getSignInOptions();
        Integer v1 = arg5.getClientSessionId();
        Bundle v2 = new Bundle();
        v2.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", arg5.getAccount());
        if(v1 != null) {
            v2.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", v1.intValue());
        }

        if(v0 != null) {
            v2.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", v0.isOfflineAccessRequested());
            v2.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", v0.isIdTokenRequested());
            v2.putString("com.google.android.gms.signin.internal.serverClientId", v0.getServerClientId());
            v2.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            v2.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", v0.isForceCodeForRefreshToken());
            v2.putString("com.google.android.gms.signin.internal.hostedDomain", v0.getHostedDomain());
            v2.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", v0.waitForAccessTokenRefresh());
            if(v0.getAuthApiSignInModuleVersion() != null) {
                v2.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", v0.getAuthApiSignInModuleVersion().longValue());
            }

            if(v0.getRealClientLibraryVersion() == null) {
                return v2;
            }

            v2.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", v0.getRealClientLibraryVersion().longValue());
        }

        return v2;
    }

    protected IInterface createServiceInterface(IBinder arg1) {
        return this.createServiceInterface(arg1);
    }

    protected ISignInService createServiceInterface(IBinder arg1) {
        return Stub.asInterface(arg1);
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        if(!((BaseGmsClient)this).getContext().getPackageName().equals(this.zzgf.getRealClientPackageName())) {
            this.zzada.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzgf.getRealClientPackageName());
        }

        return this.zzada;
    }

    public int getMinApkVersion() {
        return 12451000;
    }

    protected String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    protected String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    public boolean requiresSignIn() {
        return this.zzads;
    }

    public void saveDefaultAccount(IAccountAccessor arg3, boolean arg4) {
        try {
            ((BaseGmsClient)this).getService().saveDefaultAccountToSharedPref(arg3, this.zzsc.intValue(), arg4);
            return;
        }
        catch(RemoteException ) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
            return;
        }
    }

    public void signIn(ISignInCallbacks arg5) {
        Preconditions.checkNotNull(arg5, "Expecting a valid ISignInCallbacks");
        try {
            Account v0_1 = this.zzgf.getAccountOrDefault();
            GoogleSignInAccount v1 = null;
            if("<<default account>>".equals(v0_1.name)) {
                v1 = Storage.getInstance(((BaseGmsClient)this).getContext()).getSavedDefaultGoogleSignInAccount();
            }

            ((BaseGmsClient)this).getService().signIn(new SignInRequest(new ResolveAccountRequest(v0_1, this.zzsc.intValue(), v1)), arg5);
            return;
        }
        catch(RemoteException v0) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                arg5.onSignInComplete(new SignInResponse(8));
                return;
            }
            catch(RemoteException ) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", ((Throwable)v0));
                return;
            }
        }
    }
}

