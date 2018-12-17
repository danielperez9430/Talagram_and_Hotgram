package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.common.internal.Preconditions;

public final class SignInOptions implements Optional {
    public final class Builder {
        private boolean zzadf;
        private boolean zzadg;
        private String zzadh;
        private boolean zzadi;
        private String zzadj;
        private boolean zzadk;
        private Long zzadl;
        private Long zzadm;

        public Builder() {
            super();
        }

        public final SignInOptions build() {
            return new SignInOptions(this.zzadf, this.zzadg, this.zzadh, this.zzadi, this.zzadj, this.zzadk, this.zzadl, this.zzadm, null);
        }

        public final Builder requestIdToken(String arg2) {
            this.zzadg = true;
            this.zzadh = this.zza(arg2);
            return this;
        }

        public final Builder requestServerAuthCode(String arg1, boolean arg2) {
            this.zzadi = arg2;
            this.zzadf = true;
            this.zzadh = this.zza(arg1);
            return this;
        }

        public final Builder setAuthApiSignInModuleVersion(long arg1) {
            this.zzadl = Long.valueOf(arg1);
            return this;
        }

        public final Builder setHostedDomain(String arg1) {
            this.zzadj = arg1;
            return this;
        }

        public final Builder setRealClientLibraryVersion(long arg1) {
            this.zzadm = Long.valueOf(arg1);
            return this;
        }

        public final Builder setWaitForAccessTokenRefresh(boolean arg1) {
            this.zzadk = arg1;
            return this;
        }

        private final String zza(String arg3) {
            Preconditions.checkNotNull(arg3);
            boolean v0 = this.zzadh == null || (this.zzadh.equals(arg3)) ? true : false;
            Preconditions.checkArgument(v0, "two different server client ids provided");
            return arg3;
        }
    }

    public static final SignInOptions DEFAULT;
    private final boolean zzadb;
    private final boolean zzadc;
    private final Long zzadd;
    private final Long zzade;
    private final boolean zzt;
    private final boolean zzv;
    private final String zzw;
    private final String zzx;

    static {
        SignInOptions.DEFAULT = new Builder().build();
    }

    private SignInOptions(boolean arg1, boolean arg2, String arg3, boolean arg4, String arg5, boolean arg6, Long arg7, Long arg8) {
        super();
        this.zzadb = arg1;
        this.zzt = arg2;
        this.zzw = arg3;
        this.zzv = arg4;
        this.zzadc = arg6;
        this.zzx = arg5;
        this.zzadd = arg7;
        this.zzade = arg8;
    }

    SignInOptions(boolean arg1, boolean arg2, String arg3, boolean arg4, String arg5, boolean arg6, Long arg7, Long arg8, zzc arg9) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    public final Long getAuthApiSignInModuleVersion() {
        return this.zzadd;
    }

    public final String getHostedDomain() {
        return this.zzx;
    }

    public final Long getRealClientLibraryVersion() {
        return this.zzade;
    }

    public final String getServerClientId() {
        return this.zzw;
    }

    public final boolean isForceCodeForRefreshToken() {
        return this.zzv;
    }

    public final boolean isIdTokenRequested() {
        return this.zzt;
    }

    public final boolean isOfflineAccessRequested() {
        return this.zzadb;
    }

    public final boolean waitForAccessTokenRefresh() {
        return this.zzadc;
    }
}

