package com.google.android.gms.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api$ApiOptions$HasAccountOptions;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.wallet.zzaf;
import com.google.android.gms.internal.wallet.zzan;
import com.google.android.gms.internal.wallet.zzao;
import com.google.android.gms.internal.wallet.zzg;
import com.google.android.gms.internal.wallet.zzy;
import com.google.android.gms.wallet.wobs.WalletObjects;
import java.util.Locale;

public final class Wallet {
    public final class WalletOptions implements HasAccountOptions {
        public final class Builder {
            private int environment;
            private int theme;
            private boolean zzet;

            public Builder() {
                super();
                this.environment = 3;
                this.theme = 0;
                this.zzet = true;
            }

            public final WalletOptions build() {
                return new WalletOptions(this, null);
            }

            public final Builder setEnvironment(int arg4) {
                if(arg4 != 0 && arg4 != 0 && arg4 != 2 && arg4 != 1) {
                    if(arg4 == 3) {
                    }
                    else {
                        throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", Integer.valueOf(arg4)));
                    }
                }

                this.environment = arg4;
                return this;
            }

            public final Builder setTheme(int arg4) {
                if(arg4 != 0) {
                    if(arg4 == 1) {
                    }
                    else {
                        throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", Integer.valueOf(arg4)));
                    }
                }

                this.theme = arg4;
                return this;
            }

            @Deprecated public final Builder useGoogleWallet() {
                this.zzet = false;
                return this;
            }

            static int zza(Builder arg0) {
                return arg0.environment;
            }

            static int zzb(Builder arg0) {
                return arg0.theme;
            }

            static boolean zzc(Builder arg0) {
                return arg0.zzet;
            }
        }

        private final Account account;
        public final int environment;
        public final int theme;
        final boolean zzet;

        private WalletOptions() {
            this(new Builder());
        }

        private WalletOptions(Builder arg2) {
            super();
            this.environment = Builder.zza(arg2);
            this.theme = Builder.zzb(arg2);
            this.zzet = Builder.zzc(arg2);
            this.account = null;
        }

        WalletOptions(Builder arg1, zzaq arg2) {
            this(arg1);
        }

        WalletOptions(zzaq arg1) {
            this();
        }

        public final boolean equals(Object arg4) {
            boolean v1 = false;
            if(((arg4 instanceof WalletOptions)) && (Objects.equal(Integer.valueOf(this.environment), Integer.valueOf(((WalletOptions)arg4).environment))) && (Objects.equal(Integer.valueOf(this.theme), Integer.valueOf(((WalletOptions)arg4).theme))) && (Objects.equal(null, null)) && (Objects.equal(Boolean.valueOf(this.zzet), Boolean.valueOf(((WalletOptions)arg4).zzet)))) {
                v1 = true;
            }

            return v1;
        }

        public final Account getAccount() {
            return null;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{Integer.valueOf(this.environment), Integer.valueOf(this.theme), null, Boolean.valueOf(this.zzet)});
        }
    }

    public abstract class zza extends ApiMethodImpl {
        public zza(GoogleApiClient arg2) {
            super(Wallet.API, arg2);
        }

        protected void doExecute(AnyClient arg1) {
            this.zza(((zzaf)arg1));
        }

        protected abstract void zza(zzaf arg1);
    }

    public abstract class zzb extends zza {
        public zzb(GoogleApiClient arg1) {
            super(arg1);
        }

        protected Result createFailedResult(Status arg1) {
            return arg1;
        }
    }

    public static final Api API;
    private static final AbstractClientBuilder CLIENT_BUILDER;
    private static final ClientKey CLIENT_KEY;
    @Deprecated public static final Payments Payments;
    private static final WalletObjects zzer;
    private static final zzg zzes;

    static {
        Wallet.CLIENT_KEY = new ClientKey();
        Wallet.CLIENT_BUILDER = new zzaq();
        Wallet.API = new Api("Wallet.API", Wallet.CLIENT_BUILDER, Wallet.CLIENT_KEY);
        Wallet.Payments = new zzy();
        Wallet.zzer = new zzao();
        Wallet.zzes = new zzan();
    }

    private Wallet() {
        super();
    }

    public static PaymentsClient getPaymentsClient(Activity arg1, WalletOptions arg2) {
        return new PaymentsClient(arg1, arg2);
    }

    public static PaymentsClient getPaymentsClient(Context arg1, WalletOptions arg2) {
        return new PaymentsClient(arg1, arg2);
    }

    public static WalletObjectsClient getWalletObjectsClient(Activity arg1, WalletOptions arg2) {
        return new WalletObjectsClient(arg1, arg2);
    }
}

