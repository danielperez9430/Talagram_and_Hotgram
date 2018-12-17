package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.GoogleApiManager$zza;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zzad;
import com.google.android.gms.common.api.internal.zzbo;
import com.google.android.gms.common.api.internal.zzby;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@KeepForSdk public class GoogleApi {
    @KeepForSdk public class Settings {
        @KeepForSdk public class Builder {
            private Looper zzcn;
            private StatusExceptionMapper zzcp;

            @KeepForSdk public Builder() {
                super();
            }

            @KeepForSdk public Settings build() {
                if(this.zzcp == null) {
                    this.zzcp = new ApiExceptionMapper();
                }

                if(this.zzcn == null) {
                    this.zzcn = Looper.getMainLooper();
                }

                return new Settings(this.zzcp, null, this.zzcn, null);
            }

            @KeepForSdk public Builder setLooper(Looper arg2) {
                Preconditions.checkNotNull(arg2, "Looper must not be null.");
                this.zzcn = arg2;
                return this;
            }

            @KeepForSdk public Builder setMapper(StatusExceptionMapper arg2) {
                Preconditions.checkNotNull(arg2, "StatusExceptionMapper must not be null.");
                this.zzcp = arg2;
                return this;
            }
        }

        @KeepForSdk public static final Settings DEFAULT_SETTINGS;
        public final StatusExceptionMapper zzcr;
        public final Looper zzcs;

        static {
            Settings.DEFAULT_SETTINGS = new Builder().build();
        }

        @KeepForSdk private Settings(StatusExceptionMapper arg1, Account arg2, Looper arg3) {
            super();
            this.zzcr = arg1;
            this.zzcs = arg3;
        }

        Settings(StatusExceptionMapper arg1, Account arg2, Looper arg3, zzb arg4) {
            this(arg1, null, arg3);
        }
    }

    private final Api mApi;
    private final Context mContext;
    private final int mId;
    private final ApiOptions zzcl;
    private final zzh zzcm;
    private final Looper zzcn;
    private final GoogleApiClient zzco;
    private final StatusExceptionMapper zzcp;
    protected final GoogleApiManager zzcq;

    @KeepForSdk public GoogleApi(Activity arg2, Api arg3, ApiOptions arg4, Settings arg5) {
        super();
        Preconditions.checkNotNull(arg2, "Null activity is not permitted.");
        Preconditions.checkNotNull(arg3, "Api must not be null.");
        Preconditions.checkNotNull(arg5, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = arg2.getApplicationContext();
        this.mApi = arg3;
        this.zzcl = arg4;
        this.zzcn = arg5.zzcs;
        this.zzcm = zzh.zza(this.mApi, this.zzcl);
        this.zzco = new zzbo(this);
        this.zzcq = GoogleApiManager.zzb(this.mContext);
        this.mId = this.zzcq.zzbg();
        this.zzcp = arg5.zzcr;
        zzad.zza(arg2, this.zzcq, this.zzcm);
        this.zzcq.zza(this);
    }

    @KeepForSdk @Deprecated public GoogleApi(Activity arg2, Api arg3, ApiOptions arg4, StatusExceptionMapper arg5) {
        this(arg2, arg3, arg4, new Builder().setMapper(arg5).setLooper(arg2.getMainLooper()).build());
    }

    @KeepForSdk protected GoogleApi(Context arg2, Api arg3, Looper arg4) {
        super();
        Preconditions.checkNotNull(arg2, "Null context is not permitted.");
        Preconditions.checkNotNull(arg3, "Api must not be null.");
        Preconditions.checkNotNull(arg4, "Looper must not be null.");
        this.mContext = arg2.getApplicationContext();
        this.mApi = arg3;
        this.zzcl = null;
        this.zzcn = arg4;
        this.zzcm = zzh.zza(arg3);
        this.zzco = new zzbo(this);
        this.zzcq = GoogleApiManager.zzb(this.mContext);
        this.mId = this.zzcq.zzbg();
        this.zzcp = new ApiExceptionMapper();
    }

    @KeepForSdk @Deprecated public GoogleApi(Context arg2, Api arg3, ApiOptions arg4, Looper arg5, StatusExceptionMapper arg6) {
        this(arg2, arg3, arg4, new Builder().setLooper(arg5).setMapper(arg6).build());
    }

    @KeepForSdk public GoogleApi(Context arg2, Api arg3, ApiOptions arg4, Settings arg5) {
        super();
        Preconditions.checkNotNull(arg2, "Null context is not permitted.");
        Preconditions.checkNotNull(arg3, "Api must not be null.");
        Preconditions.checkNotNull(arg5, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = arg2.getApplicationContext();
        this.mApi = arg3;
        this.zzcl = arg4;
        this.zzcn = arg5.zzcs;
        this.zzcm = zzh.zza(this.mApi, this.zzcl);
        this.zzco = new zzbo(this);
        this.zzcq = GoogleApiManager.zzb(this.mContext);
        this.mId = this.zzcq.zzbg();
        this.zzcp = arg5.zzcr;
        this.zzcq.zza(this);
    }

    @KeepForSdk @Deprecated public GoogleApi(Context arg2, Api arg3, ApiOptions arg4, StatusExceptionMapper arg5) {
        this(arg2, arg3, arg4, new Builder().setMapper(arg5).build());
    }

    @KeepForSdk public GoogleApiClient asGoogleApiClient() {
        return this.zzco;
    }

    @KeepForSdk protected com.google.android.gms.common.internal.ClientSettings$Builder createClientSettingsBuilder() {
        Set v1_2;
        Account v1_1;
        GoogleSignInAccount v1;
        com.google.android.gms.common.internal.ClientSettings$Builder v0 = new com.google.android.gms.common.internal.ClientSettings$Builder();
        if((this.zzcl instanceof HasGoogleSignInAccountOptions)) {
            v1 = this.zzcl.getGoogleSignInAccount();
            if(v1 != null) {
                v1_1 = v1.getAccount();
            }
            else {
                goto label_10;
            }
        }
        else {
        label_10:
            v1_1 = (this.zzcl instanceof HasAccountOptions) ? this.zzcl.getAccount() : null;
        }

        v0 = v0.setAccount(v1_1);
        if((this.zzcl instanceof HasGoogleSignInAccountOptions)) {
            v1 = this.zzcl.getGoogleSignInAccount();
            if(v1 != null) {
                v1_2 = v1.getRequestedScopes();
            }
            else {
                goto label_26;
            }
        }
        else {
        label_26:
            v1_2 = Collections.emptySet();
        }

        return v0.addAllRequiredScopes(((Collection)v1_2)).setRealClientClassName(this.mContext.getClass().getName()).setRealClientPackageName(this.mContext.getPackageName());
    }

    @KeepForSdk protected Task disconnectService() {
        return this.zzcq.zzc(this);
    }

    @KeepForSdk public ApiMethodImpl doBestEffortWrite(ApiMethodImpl arg2) {
        return this.zza(2, arg2);
    }

    @KeepForSdk public Task doBestEffortWrite(TaskApiCall arg2) {
        return this.zza(2, arg2);
    }

    @KeepForSdk public ApiMethodImpl doRead(ApiMethodImpl arg2) {
        return this.zza(0, arg2);
    }

    @KeepForSdk public Task doRead(TaskApiCall arg2) {
        return this.zza(0, arg2);
    }

    @KeepForSdk public Task doRegisterEventListener(RegisterListenerMethod arg3, UnregisterListenerMethod arg4) {
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg4);
        Preconditions.checkNotNull(arg3.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(arg4.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(arg3.getListenerKey().equals(arg4.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zzcq.zza(this, arg3, arg4);
    }

    @KeepForSdk public Task doUnregisterEventListener(ListenerKey arg2) {
        Preconditions.checkNotNull(arg2, "Listener key cannot be null.");
        return this.zzcq.zza(this, arg2);
    }

    @KeepForSdk public ApiMethodImpl doWrite(ApiMethodImpl arg2) {
        return this.zza(1, arg2);
    }

    @KeepForSdk public Task doWrite(TaskApiCall arg2) {
        return this.zza(1, arg2);
    }

    public final Api getApi() {
        return this.mApi;
    }

    @KeepForSdk public ApiOptions getApiOptions() {
        return this.zzcl;
    }

    @KeepForSdk public Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    @KeepForSdk public Looper getLooper() {
        return this.zzcn;
    }

    @KeepForSdk public ListenerHolder registerListener(Object arg2, String arg3) {
        return ListenerHolders.createListenerHolder(arg2, this.zzcn, arg3);
    }

    private final ApiMethodImpl zza(int arg2, ApiMethodImpl arg3) {
        ((BasePendingResult)arg3).zzx();
        this.zzcq.zza(this, arg2, arg3);
        return arg3;
    }

    private final Task zza(int arg8, TaskApiCall arg9) {
        TaskCompletionSource v6 = new TaskCompletionSource();
        this.zzcq.zza(this, arg8, arg9, v6, this.zzcp);
        return v6.getTask();
    }

    public Client zza(Looper arg9, zza arg10) {
        return this.mApi.zzk().buildClient(this.mContext, arg9, this.createClientSettingsBuilder().build(), this.zzcl, arg10, arg10);
    }

    public zzby zza(Context arg3, Handler arg4) {
        return new zzby(arg3, arg4, this.createClientSettingsBuilder().build());
    }

    public final zzh zzm() {
        return this.zzcm;
    }
}

