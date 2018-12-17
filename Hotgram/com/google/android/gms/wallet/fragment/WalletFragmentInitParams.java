package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

@Class(creator="WalletFragmentInitParamsCreator") @Reserved(value={1}) public final class WalletFragmentInitParams extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(WalletFragmentInitParams arg1, zzc arg2) {
            this(arg1);
        }

        private Builder(WalletFragmentInitParams arg1) {
            this.zzga = arg1;
            super();
        }

        public final WalletFragmentInitParams build() {
            boolean v0;
            boolean v1 = false;
            if(WalletFragmentInitParams.zza(this.zzga) == null || WalletFragmentInitParams.zzb(this.zzga) != null) {
                if(WalletFragmentInitParams.zza(this.zzga) == null && WalletFragmentInitParams.zzb(this.zzga) != null) {
                label_14:
                    v0 = true;
                    goto label_17;
                }

                v0 = false;
            }
            else {
                goto label_14;
            }

        label_17:
            Preconditions.checkState(v0, "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            if(WalletFragmentInitParams.zzc(this.zzga) >= 0) {
                v1 = true;
            }

            Preconditions.checkState(v1, "masked wallet request code is required and must be non-negative");
            return this.zzga;
        }

        public final Builder setAccountName(String arg2) {
            WalletFragmentInitParams.zza(this.zzga, arg2);
            return this;
        }

        public final Builder setMaskedWallet(MaskedWallet arg2) {
            WalletFragmentInitParams.zza(this.zzga, arg2);
            return this;
        }

        public final Builder setMaskedWalletRequest(MaskedWalletRequest arg2) {
            WalletFragmentInitParams.zza(this.zzga, arg2);
            return this;
        }

        public final Builder setMaskedWalletRequestCode(int arg2) {
            WalletFragmentInitParams.zza(this.zzga, arg2);
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(getter="getAccountName", id=2) private String zzcj;
    @Field(getter="getMaskedWalletRequest", id=3) private MaskedWalletRequest zzfk;
    @Field(getter="getMaskedWallet", id=5) private MaskedWallet zzfl;
    @Field(defaultValue="-1", getter="getMaskedWalletRequestCode", id=4) private int zzfz;

    static {
        WalletFragmentInitParams.CREATOR = new zzd();
    }

    @Constructor WalletFragmentInitParams(@Param(id=2) String arg1, @Param(id=3) MaskedWalletRequest arg2, @Param(id=4) int arg3, @Param(id=5) MaskedWallet arg4) {
        super();
        this.zzcj = arg1;
        this.zzfk = arg2;
        this.zzfz = arg3;
        this.zzfl = arg4;
    }

    private WalletFragmentInitParams() {
        super();
        this.zzfz = -1;
    }

    public final String getAccountName() {
        return this.zzcj;
    }

    public final MaskedWallet getMaskedWallet() {
        return this.zzfl;
    }

    public final MaskedWalletRequest getMaskedWalletRequest() {
        return this.zzfk;
    }

    public final int getMaskedWalletRequestCode() {
        return this.zzfz;
    }

    public static Builder newBuilder() {
        return new Builder(new WalletFragmentInitParams(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.getAccountName(), false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.getMaskedWalletRequest(), arg6, false);
        SafeParcelWriter.writeInt(arg5, 4, this.getMaskedWalletRequestCode());
        SafeParcelWriter.writeParcelable(arg5, 5, this.getMaskedWallet(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    static int zza(WalletFragmentInitParams arg0, int arg1) {
        arg0.zzfz = arg1;
        return arg1;
    }

    static MaskedWallet zza(WalletFragmentInitParams arg0) {
        return arg0.zzfl;
    }

    static MaskedWallet zza(WalletFragmentInitParams arg0, MaskedWallet arg1) {
        arg0.zzfl = arg1;
        return arg1;
    }

    static MaskedWalletRequest zza(WalletFragmentInitParams arg0, MaskedWalletRequest arg1) {
        arg0.zzfk = arg1;
        return arg1;
    }

    static String zza(WalletFragmentInitParams arg0, String arg1) {
        arg0.zzcj = arg1;
        return arg1;
    }

    static MaskedWalletRequest zzb(WalletFragmentInitParams arg0) {
        return arg0.zzfk;
    }

    static int zzc(WalletFragmentInitParams arg0) {
        return arg0.zzfz;
    }
}

