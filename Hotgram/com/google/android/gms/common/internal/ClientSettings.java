package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.f.b;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@VisibleForTesting public final class ClientSettings {
    public final class Builder {
        private int zzcx;
        private View zzcy;
        private String zzcz;
        private String zzda;
        private Account zzs;
        private Map zzsa;
        private SignInOptions zzsb;
        private b zzsd;

        public Builder() {
            super();
            this.zzcx = 0;
            this.zzsb = SignInOptions.DEFAULT;
        }

        public final Builder addAllRequiredScopes(Collection arg2) {
            if(this.zzsd == null) {
                this.zzsd = new b();
            }

            this.zzsd.addAll(arg2);
            return this;
        }

        public final Builder addRequiredScope(Scope arg2) {
            if(this.zzsd == null) {
                this.zzsd = new b();
            }

            this.zzsd.add(arg2);
            return this;
        }

        public final ClientSettings build() {
            return new ClientSettings(this.zzs, this.zzsd, this.zzsa, this.zzcx, this.zzcy, this.zzcz, this.zzda, this.zzsb);
        }

        public final Builder setAccount(Account arg1) {
            this.zzs = arg1;
            return this;
        }

        public final Builder setGravityForPopups(int arg1) {
            this.zzcx = arg1;
            return this;
        }

        public final Builder setOptionalApiSettingsMap(Map arg1) {
            this.zzsa = arg1;
            return this;
        }

        public final Builder setRealClientClassName(String arg1) {
            this.zzda = arg1;
            return this;
        }

        public final Builder setRealClientPackageName(String arg1) {
            this.zzcz = arg1;
            return this;
        }

        public final Builder setSignInOptions(SignInOptions arg1) {
            this.zzsb = arg1;
            return this;
        }

        public final Builder setViewForPopups(View arg1) {
            this.zzcy = arg1;
            return this;
        }
    }

    public final class OptionalApiSettings {
        public final Set mScopes;

        public OptionalApiSettings(Set arg1) {
            super();
            Preconditions.checkNotNull(arg1);
            this.mScopes = Collections.unmodifiableSet(arg1);
        }
    }

    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    private final Set zzcv;
    private final int zzcx;
    private final View zzcy;
    private final String zzcz;
    private final String zzda;
    private final Set zzrz;
    private final Account zzs;
    private final Map zzsa;
    private final SignInOptions zzsb;
    private Integer zzsc;

    public ClientSettings(Account arg1, Set arg2, Map arg3, int arg4, View arg5, String arg6, String arg7, SignInOptions arg8) {
        super();
        this.zzs = arg1;
        Set v1 = arg2 == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(arg2);
        this.zzcv = v1;
        if(arg3 == null) {
            arg3 = Collections.EMPTY_MAP;
        }

        this.zzsa = arg3;
        this.zzcy = arg5;
        this.zzcx = arg4;
        this.zzcz = arg6;
        this.zzda = arg7;
        this.zzsb = arg8;
        HashSet v1_1 = new HashSet(this.zzcv);
        Iterator v2 = this.zzsa.values().iterator();
        while(v2.hasNext()) {
            ((Set)v1_1).addAll(v2.next().mScopes);
        }

        this.zzrz = Collections.unmodifiableSet(((Set)v1_1));
    }

    public static ClientSettings createDefault(Context arg1) {
        return new com.google.android.gms.common.api.GoogleApiClient$Builder(arg1).buildClientSettings();
    }

    @Nullable public final Account getAccount() {
        return this.zzs;
    }

    @Deprecated @Nullable public final String getAccountName() {
        if(this.zzs != null) {
            return this.zzs.name;
        }

        return null;
    }

    public final Account getAccountOrDefault() {
        if(this.zzs != null) {
            return this.zzs;
        }

        return new Account("<<default account>>", "com.google");
    }

    public final Set getAllRequestedScopes() {
        return this.zzrz;
    }

    public final Set getApplicableScopes(Api arg3) {
        Object v3 = this.zzsa.get(arg3);
        if(v3 != null) {
            if(((OptionalApiSettings)v3).mScopes.isEmpty()) {
            }
            else {
                HashSet v0 = new HashSet(this.zzcv);
                ((Set)v0).addAll(((OptionalApiSettings)v3).mScopes);
                return ((Set)v0);
            }
        }

        return this.zzcv;
    }

    @Nullable public final Integer getClientSessionId() {
        return this.zzsc;
    }

    public final int getGravityForPopups() {
        return this.zzcx;
    }

    public final Map getOptionalApiSettings() {
        return this.zzsa;
    }

    @Nullable public final String getRealClientClassName() {
        return this.zzda;
    }

    @Nullable public final String getRealClientPackageName() {
        return this.zzcz;
    }

    public final Set getRequiredScopes() {
        return this.zzcv;
    }

    @Nullable public final SignInOptions getSignInOptions() {
        return this.zzsb;
    }

    @Nullable public final View getViewForPopups() {
        return this.zzcy;
    }

    public final void setClientSessionId(Integer arg1) {
        this.zzsc = arg1;
    }
}

