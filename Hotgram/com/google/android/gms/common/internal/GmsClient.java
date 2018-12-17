package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.Set;

public abstract class GmsClient extends BaseGmsClient implements Client, GmsClientEventState {
    private final Set mScopes;
    private final ClientSettings zzgf;
    private final Account zzs;

    @VisibleForTesting protected GmsClient(Context arg10, Handler arg11, int arg12, ClientSettings arg13) {
        this(arg10, arg11, GmsClientSupervisor.getInstance(arg10), GoogleApiAvailability.getInstance(), arg12, arg13, null, null);
    }

    @VisibleForTesting protected GmsClient(Context arg10, Handler arg11, GmsClientSupervisor arg12, GoogleApiAvailability arg13, int arg14, ClientSettings arg15, ConnectionCallbacks arg16, OnConnectionFailedListener arg17) {
        super(arg10, arg11, arg12, arg13, arg14, GmsClient.zza(arg16), GmsClient.zza(arg17));
        this.zzgf = Preconditions.checkNotNull(arg15);
        this.zzs = arg15.getAccount();
        this.mScopes = this.zza(arg15.getAllRequestedScopes());
    }

    protected GmsClient(Context arg10, Looper arg11, int arg12, ClientSettings arg13) {
        this(arg10, arg11, GmsClientSupervisor.getInstance(arg10), GoogleApiAvailability.getInstance(), arg12, arg13, null, null);
    }

    @VisibleForTesting protected GmsClient(Context arg11, Looper arg12, GmsClientSupervisor arg13, GoogleApiAvailability arg14, int arg15, ClientSettings arg16, ConnectionCallbacks arg17, OnConnectionFailedListener arg18) {
        super(arg11, arg12, arg13, arg14, arg15, GmsClient.zza(arg17), GmsClient.zza(arg18), arg16.getRealClientClassName());
        this.zzgf = arg16;
        this.zzs = arg16.getAccount();
        this.mScopes = this.zza(arg16.getAllRequestedScopes());
    }

    protected GmsClient(Context arg10, Looper arg11, int arg12, ClientSettings arg13, ConnectionCallbacks arg14, OnConnectionFailedListener arg15) {
        this(arg10, arg11, GmsClientSupervisor.getInstance(arg10), GoogleApiAvailability.getInstance(), arg12, arg13, Preconditions.checkNotNull(arg14), Preconditions.checkNotNull(arg15));
    }

    public final Account getAccount() {
        return this.zzs;
    }

    protected final ClientSettings getClientSettings() {
        return this.zzgf;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    protected final Set getScopes() {
        return this.mScopes;
    }

    protected Set validateScopes(Set arg1) {
        return arg1;
    }

    private static BaseConnectionCallbacks zza(ConnectionCallbacks arg1) {
        if(arg1 == null) {
            return null;
        }

        return new zzf(arg1);
    }

    private static BaseOnConnectionFailedListener zza(OnConnectionFailedListener arg1) {
        if(arg1 == null) {
            return null;
        }

        return new zzg(arg1);
    }

    private final Set zza(Set arg4) {
        Set v0 = this.validateScopes(arg4);
        Iterator v1 = v0.iterator();
        while(true) {
            if(!v1.hasNext()) {
                return v0;
            }

            if(!arg4.contains(v1.next())) {
                break;
            }
        }

        throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
        return v0;
    }
}

