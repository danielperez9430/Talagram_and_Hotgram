package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="MaskedWalletRequestCreator") @Reserved(value={1}) public final class MaskedWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(MaskedWalletRequest arg1, zzy arg2) {
            this(arg1);
        }

        private Builder(MaskedWalletRequest arg1) {
            this.zzdq = arg1;
            super();
        }

        public final Builder addAllowedCardNetwork(int arg3) {
            if(this.zzdq.zzaj == null) {
                this.zzdq.zzaj = new ArrayList();
            }

            this.zzdq.zzaj.add(Integer.valueOf(arg3));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection arg3) {
            if(arg3 != null) {
                if(this.zzdq.zzaj == null) {
                    this.zzdq.zzaj = new ArrayList();
                }

                this.zzdq.zzaj.addAll(arg3);
            }

            return this;
        }

        public final Builder addAllowedCountrySpecificationForShipping(CountrySpecification arg3) {
            if(this.zzdq.zzdo == null) {
                this.zzdq.zzdo = new ArrayList();
            }

            this.zzdq.zzdo.add(arg3);
            return this;
        }

        public final Builder addAllowedCountrySpecificationsForShipping(Collection arg3) {
            if(arg3 != null) {
                if(this.zzdq.zzdo == null) {
                    this.zzdq.zzdo = new ArrayList();
                }

                this.zzdq.zzdo.addAll(arg3);
            }

            return this;
        }

        public final MaskedWalletRequest build() {
            return this.zzdq;
        }

        public final Builder setAllowDebitCard(boolean arg2) {
            this.zzdq.zzdn = arg2;
            return this;
        }

        public final Builder setAllowPrepaidCard(boolean arg2) {
            this.zzdq.zzdm = arg2;
            return this;
        }

        public final Builder setCart(Cart arg2) {
            this.zzdq.zzbi = arg2;
            return this;
        }

        public final Builder setCountryCode(String arg2) {
            this.zzdq.zzi = arg2;
            return this;
        }

        public final Builder setCurrencyCode(String arg2) {
            this.zzdq.zzap = arg2;
            return this;
        }

        public final Builder setEstimatedTotalPrice(String arg2) {
            this.zzdq.zzdh = arg2;
            return this;
        }

        @Deprecated public final Builder setIsBillingAgreement(boolean arg2) {
            this.zzdq.zzdk = arg2;
            return this;
        }

        public final Builder setMerchantName(String arg2) {
            this.zzdq.zzdi = arg2;
            return this;
        }

        public final Builder setMerchantTransactionId(String arg2) {
            this.zzdq.zzay = arg2;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters arg2) {
            this.zzdq.zzdp = arg2;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean arg2) {
            this.zzdq.zzde = arg2;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean arg2) {
            this.zzdq.zzdf = arg2;
            return this;
        }

        @Deprecated public final Builder setUseMinimalBillingAddress(boolean arg2) {
            this.zzdq.zzdg = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=17) ArrayList zzaj;
    @Field(id=7) String zzap;
    @Field(id=2) String zzay;
    @Field(id=9) Cart zzbi;
    @Field(id=3) boolean zzde;
    @Field(id=4) boolean zzdf;
    @Field(id=5) boolean zzdg;
    @Field(id=6) String zzdh;
    @Field(id=8) String zzdi;
    @Field(id=10) private boolean zzdj;
    @Field(id=11) boolean zzdk;
    @Field(id=12) private com.google.android.gms.wallet.CountrySpecification[] zzdl;
    @Field(defaultValue="true", id=13) boolean zzdm;
    @Field(defaultValue="true", id=14) boolean zzdn;
    @Field(id=15) ArrayList zzdo;
    @Field(id=16) PaymentMethodTokenizationParameters zzdp;
    @Field(id=18) String zzi;

    static {
        MaskedWalletRequest.CREATOR = new zzz();
    }

    @Constructor MaskedWalletRequest(@Param(id=2) String arg3, @Param(id=3) boolean arg4, @Param(id=4) boolean arg5, @Param(id=5) boolean arg6, @Param(id=6) String arg7, @Param(id=7) String arg8, @Param(id=8) String arg9, @Param(id=9) Cart arg10, @Param(id=10) boolean arg11, @Param(id=11) boolean arg12, @Param(id=12) com.google.android.gms.wallet.CountrySpecification[] arg13, @Param(id=13) boolean arg14, @Param(id=14) boolean arg15, @Param(id=15) ArrayList arg16, @Param(id=16) PaymentMethodTokenizationParameters arg17, @Param(id=17) ArrayList arg18, @Param(id=18) String arg19) {
        super();
        this.zzay = arg3;
        this.zzde = arg4;
        this.zzdf = arg5;
        this.zzdg = arg6;
        this.zzdh = arg7;
        this.zzap = arg8;
        this.zzdi = arg9;
        this.zzbi = arg10;
        this.zzdj = arg11;
        this.zzdk = arg12;
        this.zzdl = arg13;
        this.zzdm = arg14;
        this.zzdn = arg15;
        this.zzdo = arg16;
        this.zzdp = arg17;
        this.zzaj = arg18;
        this.zzi = arg19;
    }

    MaskedWalletRequest() {
        super();
        this.zzdm = true;
        this.zzdn = true;
    }

    public final boolean allowDebitCard() {
        return this.zzdn;
    }

    public final boolean allowPrepaidCard() {
        return this.zzdm;
    }

    public final ArrayList getAllowedCardNetworks() {
        return this.zzaj;
    }

    public final ArrayList getAllowedCountrySpecificationsForShipping() {
        return this.zzdo;
    }

    public final com.google.android.gms.wallet.CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.zzdl;
    }

    public final Cart getCart() {
        return this.zzbi;
    }

    public final String getCountryCode() {
        return this.zzi;
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final String getEstimatedTotalPrice() {
        return this.zzdh;
    }

    public final String getMerchantName() {
        return this.zzdi;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdp;
    }

    @Deprecated public final boolean isBillingAgreement() {
        return this.zzdk;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzde;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzdf;
    }

    public static Builder newBuilder() {
        return new Builder(new MaskedWalletRequest(), null);
    }

    @Deprecated public final boolean useMinimalBillingAddress() {
        return this.zzdg;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzay, false);
        SafeParcelWriter.writeBoolean(arg5, 3, this.zzde);
        SafeParcelWriter.writeBoolean(arg5, 4, this.zzdf);
        SafeParcelWriter.writeBoolean(arg5, 5, this.zzdg);
        SafeParcelWriter.writeString(arg5, 6, this.zzdh, false);
        SafeParcelWriter.writeString(arg5, 7, this.zzap, false);
        SafeParcelWriter.writeString(arg5, 8, this.zzdi, false);
        SafeParcelWriter.writeParcelable(arg5, 9, this.zzbi, arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 10, this.zzdj);
        SafeParcelWriter.writeBoolean(arg5, 11, this.zzdk);
        SafeParcelWriter.writeTypedArray(arg5, 12, this.zzdl, arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 13, this.zzdm);
        SafeParcelWriter.writeBoolean(arg5, 14, this.zzdn);
        SafeParcelWriter.writeTypedList(arg5, 15, this.zzdo, false);
        SafeParcelWriter.writeParcelable(arg5, 16, this.zzdp, arg6, false);
        SafeParcelWriter.writeIntegerList(arg5, 17, this.zzaj, false);
        SafeParcelWriter.writeString(arg5, 18, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

