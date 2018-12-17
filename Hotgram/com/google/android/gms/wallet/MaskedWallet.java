package com.google.android.gms.wallet;

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
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator="MaskedWalletCreator") @Reserved(value={1}) public final class MaskedWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(MaskedWallet arg1, zzw arg2) {
            this(arg1);
        }

        private Builder(MaskedWallet arg1) {
            this.zzdd = arg1;
            super();
        }

        public final MaskedWallet build() {
            return this.zzdd;
        }

        public final Builder setBuyerBillingAddress(UserAddress arg2) {
            this.zzdd.zzbe = arg2;
            return this;
        }

        public final Builder setBuyerShippingAddress(UserAddress arg2) {
            this.zzdd.zzbf = arg2;
            return this;
        }

        public final Builder setEmail(String arg2) {
            this.zzdd.zzba = arg2;
            return this;
        }

        public final Builder setGoogleTransactionId(String arg2) {
            this.zzdd.zzax = arg2;
            return this;
        }

        public final Builder setInstrumentInfos(InstrumentInfo[] arg2) {
            this.zzdd.zzbg = arg2;
            return this;
        }

        public final Builder setMerchantTransactionId(String arg2) {
            this.zzdd.zzay = arg2;
            return this;
        }

        public final Builder setPaymentDescriptions(String[] arg2) {
            this.zzdd.zzbd = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String zzax;
    @Field(id=3) String zzay;
    @Field(id=5) String zzba;
    @Field(id=6) private zza zzbb;
    @Field(id=7) private zza zzbc;
    @Field(id=4) String[] zzbd;
    @Field(id=10) UserAddress zzbe;
    @Field(id=11) UserAddress zzbf;
    @Field(id=12) InstrumentInfo[] zzbg;
    @Field(id=8) private LoyaltyWalletObject[] zzdb;
    @Field(id=9) private OfferWalletObject[] zzdc;

    static {
        MaskedWallet.CREATOR = new zzx();
    }

    @Constructor MaskedWallet(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String[] arg3, @Param(id=5) String arg4, @Param(id=6) zza arg5, @Param(id=7) zza arg6, @Param(id=8) LoyaltyWalletObject[] arg7, @Param(id=9) OfferWalletObject[] arg8, @Param(id=10) UserAddress arg9, @Param(id=11) UserAddress arg10, @Param(id=12) InstrumentInfo[] arg11) {
        super();
        this.zzax = arg1;
        this.zzay = arg2;
        this.zzbd = arg3;
        this.zzba = arg4;
        this.zzbb = arg5;
        this.zzbc = arg6;
        this.zzdb = arg7;
        this.zzdc = arg8;
        this.zzbe = arg9;
        this.zzbf = arg10;
        this.zzbg = arg11;
    }

    private MaskedWallet() {
        super();
    }

    public final UserAddress getBuyerBillingAddress() {
        return this.zzbe;
    }

    public final UserAddress getBuyerShippingAddress() {
        return this.zzbf;
    }

    public final String getEmail() {
        return this.zzba;
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final InstrumentInfo[] getInstrumentInfos() {
        return this.zzbg;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final String[] getPaymentDescriptions() {
        return this.zzbd;
    }

    public static Builder newBuilderFrom(MaskedWallet arg3) {
        Preconditions.checkNotNull(arg3);
        Builder v0 = new Builder(new MaskedWallet(), null).setGoogleTransactionId(arg3.getGoogleTransactionId()).setMerchantTransactionId(arg3.getMerchantTransactionId()).setPaymentDescriptions(arg3.getPaymentDescriptions()).setInstrumentInfos(arg3.getInstrumentInfos()).setEmail(arg3.getEmail());
        v0.zzdd.zzdb = arg3.zzdb;
        v0.zzdd.zzdc = arg3.zzdc;
        return v0.setBuyerBillingAddress(arg3.getBuyerBillingAddress()).setBuyerShippingAddress(arg3.getBuyerShippingAddress());
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzax, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzay, false);
        SafeParcelWriter.writeStringArray(arg5, 4, this.zzbd, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzba, false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.zzbb, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 7, this.zzbc, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 8, this.zzdb, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 9, this.zzdc, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 10, this.zzbe, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 11, this.zzbf, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 12, this.zzbg, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

