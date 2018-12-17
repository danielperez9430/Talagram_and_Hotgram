package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.h;
import android.support.v4.f.a;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zzav;
import com.google.android.gms.common.api.internal.zzch;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzp;
import com.google.android.gms.common.internal.ClientSettings$OptionalApiSettings;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk public abstract class GoogleApiClient {
    @KeepForSdk public final class Builder {
        private final Context mContext;
        private Looper zzcn;
        private final Set zzcv;
        private final Set zzcw;
        private int zzcx;
        private View zzcy;
        private String zzcz;
        private String zzda;
        private final Map zzdb;
        private final Map zzdc;
        private LifecycleActivity zzdd;
        private int zzde;
        private OnConnectionFailedListener zzdf;
        private GoogleApiAvailability zzdg;
        private AbstractClientBuilder zzdh;
        private final ArrayList zzdi;
        private final ArrayList zzdj;
        private boolean zzdk;
        private Account zzs;

        @KeepForSdk public Builder(Context arg2) {
            super();
            this.zzcv = new HashSet();
            this.zzcw = new HashSet();
            this.zzdb = new a();
            this.zzdc = new a();
            this.zzde = -1;
            this.zzdg = GoogleApiAvailability.getInstance();
            this.zzdh = SignIn.CLIENT_BUILDER;
            this.zzdi = new ArrayList();
            this.zzdj = new ArrayList();
            this.zzdk = false;
            this.mContext = arg2;
            this.zzcn = arg2.getMainLooper();
            this.zzcz = arg2.getPackageName();
            this.zzda = arg2.getClass().getName();
        }

        @KeepForSdk public Builder(Context arg1, ConnectionCallbacks arg2, OnConnectionFailedListener arg3) {
            this(arg1);
            Preconditions.checkNotNull(arg2, "Must provide a connected listener");
            this.zzdi.add(arg2);
            Preconditions.checkNotNull(arg3, "Must provide a connection failed listener");
            this.zzdj.add(arg3);
        }

        public final Builder addApi(Api arg3) {
            Preconditions.checkNotNull(arg3, "Api must not be null");
            this.zzdc.put(arg3, null);
            List v3 = arg3.zzj().getImpliedScopes(null);
            this.zzcw.addAll(((Collection)v3));
            this.zzcv.addAll(((Collection)v3));
            return this;
        }

        public final Builder addApi(Api arg2, HasOptions arg3) {
            Preconditions.checkNotNull(arg2, "Api must not be null");
            Preconditions.checkNotNull(arg3, "Null options are not permitted for this Api");
            this.zzdc.put(arg2, arg3);
            List v2 = arg2.zzj().getImpliedScopes(arg3);
            this.zzcw.addAll(((Collection)v2));
            this.zzcv.addAll(((Collection)v2));
            return this;
        }

        public final Builder addApiIfAvailable(Api arg2, HasOptions arg3, Scope[] arg4) {
            Preconditions.checkNotNull(arg2, "Api must not be null");
            Preconditions.checkNotNull(arg3, "Null options are not permitted for this Api");
            this.zzdc.put(arg2, arg3);
            this.zza(arg2, ((ApiOptions)arg3), arg4);
            return this;
        }

        public final Builder addApiIfAvailable(Api arg3, Scope[] arg4) {
            Preconditions.checkNotNull(arg3, "Api must not be null");
            this.zzdc.put(arg3, null);
            this.zza(arg3, null, arg4);
            return this;
        }

        public final Builder addConnectionCallbacks(ConnectionCallbacks arg2) {
            Preconditions.checkNotNull(arg2, "Listener must not be null");
            this.zzdi.add(arg2);
            return this;
        }

        public final Builder addOnConnectionFailedListener(OnConnectionFailedListener arg2) {
            Preconditions.checkNotNull(arg2, "Listener must not be null");
            this.zzdj.add(arg2);
            return this;
        }

        public final Builder addScope(Scope arg2) {
            Preconditions.checkNotNull(arg2, "Scope must not be null");
            this.zzcv.add(arg2);
            return this;
        }

        @KeepForSdk public final Builder addScopeNames(String[] arg5) {
            int v0;
            for(v0 = 0; v0 < arg5.length; ++v0) {
                this.zzcv.add(new Scope(arg5[v0]));
            }

            return this;
        }

        public final GoogleApiClient build() {
            Object v21;
            boolean v4;
            Builder v1 = this;
            Preconditions.checkArgument(v1.zzdc.isEmpty() ^ 1, "must call addApi() to add at least one API");
            ClientSettings v0 = this.buildClientSettings();
            Object v3 = null;
            Map v11 = v0.getOptionalApiSettings();
            a v12 = new a();
            a v14 = new a();
            ArrayList v15 = new ArrayList();
            Iterator v13 = v1.zzdc.keySet().iterator();
            int v17 = 0;
            while(true) {
                if(!v13.hasNext()) {
                    goto label_79;
                }

                Object v10 = v13.next();
                Object v18 = v1.zzdc.get(v10);
                v4 = v11.get(v10) != null ? true : false;
                ((Map)v12).put(v10, Boolean.valueOf(v4));
                zzp v9 = new zzp(((Api)v10), v4);
                v15.add(v9);
                AbstractClientBuilder v19 = ((Api)v10).zzk();
                v21 = v10;
                Client v4_1 = v19.buildClient(v1.mContext, v1.zzcn, v0, v18, ((ConnectionCallbacks)v9), v9);
                ((Map)v14).put(((Api)v21).getClientKey(), v4_1);
                if(((BaseClientBuilder)v19).getPriority() == 1) {
                    int v5 = v18 != null ? 1 : 0;
                    v17 = v5;
                }

                if(!v4_1.providesSignIn()) {
                    continue;
                }

                if(v3 != null) {
                    break;
                }

                v3 = v21;
            }

            String v2 = ((Api)v21).getName();
            String v3_1 = ((Api)v3).getName();
            StringBuilder v5_1 = new StringBuilder(String.valueOf(v2).length() + 21 + String.valueOf(v3_1).length());
            v5_1.append(v2);
            v5_1.append(" cannot be used with ");
            v5_1.append(v3_1);
            throw new IllegalStateException(v5_1.toString());
        label_79:
            if(v3 != null) {
                if(v17 == 0) {
                    v4 = v1.zzs == null ? true : false;
                    Preconditions.checkState(v4, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[]{((Api)v3).getName()});
                    Preconditions.checkState(v1.zzcv.equals(v1.zzcw), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[]{((Api)v3).getName()});
                }
                else {
                    v2 = ((Api)v3).getName();
                    StringBuilder v4_2 = new StringBuilder(String.valueOf(v2).length() + 82);
                    v4_2.append("With using ");
                    v4_2.append(v2);
                    v4_2.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
                    throw new IllegalStateException(v4_2.toString());
                }
            }

            zzav v2_1 = new zzav(v1.mContext, new ReentrantLock(), v1.zzcn, v0, v1.zzdg, v1.zzdh, v12, v1.zzdi, v1.zzdj, ((Map)v14), v1.zzde, zzav.zza(((Map)v14).values(), true), v15, false);
            Set v3_2 = GoogleApiClient.zzn();
            __monitor_enter(v3_2);
            try {
                GoogleApiClient.zzn().add(v2_1);
                __monitor_exit(v3_2);
            }
            catch(Throwable v0_1) {
                goto label_152;
            }

            if(v1.zzde >= 0) {
                zzi.zza(v1.zzdd).zza(v1.zzde, ((GoogleApiClient)v2_1), v1.zzdf);
            }

            return ((GoogleApiClient)v2_1);
            try {
            label_152:
                __monitor_exit(v3_2);
            }
            catch(Throwable v0_1) {
                goto label_152;
            }

            throw v0_1;
        }

        @KeepForSdk @VisibleForTesting public final ClientSettings buildClientSettings() {
            Object v0_1;
            SignInOptions v0 = SignInOptions.DEFAULT;
            if(this.zzdc.containsKey(SignIn.API)) {
                v0_1 = this.zzdc.get(SignIn.API);
            }

            return new ClientSettings(this.zzs, this.zzcv, this.zzdb, this.zzcx, this.zzcy, this.zzcz, this.zzda, ((SignInOptions)v0_1));
        }

        public final Builder enableAutoManage(h arg3, int arg4, OnConnectionFailedListener arg5) {
            LifecycleActivity v0 = new LifecycleActivity(((Activity)arg3));
            boolean v3 = arg4 >= 0 ? true : false;
            Preconditions.checkArgument(v3, "clientId must be non-negative");
            this.zzde = arg4;
            this.zzdf = arg5;
            this.zzdd = v0;
            return this;
        }

        public final Builder enableAutoManage(h arg2, OnConnectionFailedListener arg3) {
            return this.enableAutoManage(arg2, 0, arg3);
        }

        public final Builder setAccountName(String arg3) {
            Account v3 = arg3 == null ? null : new Account(arg3, "com.google");
            this.zzs = v3;
            return this;
        }

        public final Builder setGravityForPopups(int arg1) {
            this.zzcx = arg1;
            return this;
        }

        public final Builder setHandler(Handler arg2) {
            Preconditions.checkNotNull(arg2, "Handler must not be null");
            this.zzcn = arg2.getLooper();
            return this;
        }

        public final Builder setViewForPopups(View arg2) {
            Preconditions.checkNotNull(arg2, "View must not be null");
            this.zzcy = arg2;
            return this;
        }

        public final Builder useDefaultAccount() {
            return this.setAccountName("<<default account>>");
        }

        private final void zza(Api arg4, ApiOptions arg5, Scope[] arg6) {
            HashSet v0 = new HashSet(arg4.zzj().getImpliedScopes(arg5));
            int v5 = arg6.length;
            int v1;
            for(v1 = 0; v1 < v5; ++v1) {
                ((Set)v0).add(arg6[v1]);
            }

            this.zzdb.put(arg4, new OptionalApiSettings(((Set)v0)));
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle arg1);

        void onConnectionSuspended(int arg1);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult arg1);
    }

    @KeepForSdk public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    @GuardedBy(value="sAllClients") private static final Set zzcu;

    static {
        GoogleApiClient.zzcu = Collections.newSetFromMap(new WeakHashMap());
    }

    public GoogleApiClient() {
        super();
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long arg1, TimeUnit arg2);

    public abstract PendingResult clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int arg1) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

    public static void dumpAll(String arg7, FileDescriptor arg8, PrintWriter arg9, String[] arg10) {
        Set v0 = GoogleApiClient.zzcu;
        __monitor_enter(v0);
        int v1 = 0;
        try {
            String v2 = String.valueOf(arg7).concat("  ");
            Iterator v3 = GoogleApiClient.zzcu.iterator();
            while(v3.hasNext()) {
                Object v4 = v3.next();
                arg9.append(((CharSequence)arg7)).append("GoogleApiClient#").println(v1);
                ((GoogleApiClient)v4).dump(v2, arg8, arg9, arg10);
                ++v1;
            }

            __monitor_exit(v0);
            return;
        label_22:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_22;
        }

        throw v7;
    }

    @KeepForSdk public ApiMethodImpl enqueue(ApiMethodImpl arg1) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk public ApiMethodImpl execute(ApiMethodImpl arg1) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk public static Set getAllClients() {
        Set v0 = GoogleApiClient.zzcu;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return GoogleApiClient.zzcu;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    @KeepForSdk public Client getClient(AnyClientKey arg1) {
        throw new UnsupportedOperationException();
    }

    public abstract ConnectionResult getConnectionResult(Api arg1);

    @KeepForSdk public Context getContext() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk public boolean hasApi(Api arg1) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(Api arg1);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg1);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener arg1);

    @KeepForSdk public boolean maybeSignIn(SignInConnectionListener arg1) {
        throw new UnsupportedOperationException();
    }

    @KeepForSdk public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks arg1);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener arg1);

    @KeepForSdk public ListenerHolder registerListener(Object arg1) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(h arg1);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks arg1);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener arg1);

    public void zza(zzch arg1) {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzch arg1) {
        throw new UnsupportedOperationException();
    }

    static Set zzn() {
        return GoogleApiClient.zzcu;
    }
}

