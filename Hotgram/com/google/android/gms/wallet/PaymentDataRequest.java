package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="PaymentDataRequestCreator") public final class PaymentDataRequest extends AbstractSafeParcelable {
    public final class Builder {
        Builder(PaymentDataRequest arg1, zzae arg2) {
            this(arg1);
        }

        private Builder(PaymentDataRequest arg1) {
            this.zzec = arg1;
            super();
        }

        public final Builder addAllowedPaymentMethod(int arg3) {
            if(this.zzec.zzbx == null) {
                this.zzec.zzbx = new ArrayList();
            }

            this.zzec.zzbx.add(Integer.valueOf(arg3));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection arg3) {
            boolean v0 = arg3 == null || (arg3.isEmpty()) ? false : true;
            Preconditions.checkArgument(v0, "allowedPaymentMethods can\'t be null or empty!");
            if(this.zzec.zzbx == null) {
                this.zzec.zzbx = new ArrayList();
            }

            this.zzec.zzbx.addAll(arg3);
            return this;
        }

        public final PaymentDataRequest build() {
            if(this.zzec.zzbz == null) {
                Preconditions.checkNotNull(this.zzec.zzbx, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
                Preconditions.checkNotNull(this.zzec.zzdy, "Card requirements must be set!");
                if(this.zzec.zzdp != null) {
                    Preconditions.checkNotNull(this.zzec.zzea, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
                }
            }

            return this.zzec;
        }

        public final Builder setCardRequirements(CardRequirements arg2) {
            this.zzec.zzdy = arg2;
            return this;
        }

        public final Builder setEmailRequired(boolean arg2) {
            this.zzec.zzdx = arg2;
            return this;
        }

        public final Builder setPaymentMethodTokenizationParameters(PaymentMethodTokenizationParameters arg2) {
            this.zzec.zzdp = arg2;
            return this;
        }

        public final Builder setPhoneNumberRequired(boolean arg2) {
            this.zzec.zzde = arg2;
            return this;
        }

        public final Builder setShippingAddressRequired(boolean arg2) {
            this.zzec.zzdf = arg2;
            return this;
        }

        public final Builder setShippingAddressRequirements(ShippingAddressRequirements arg2) {
            this.zzec.zzdz = arg2;
            return this;
        }

        public final Builder setTransactionInfo(TransactionInfo arg2) {
            this.zzec.zzea = arg2;
            return this;
        }

        public final Builder setUiRequired(boolean arg2) {
            this.zzec.zzeb = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=6) ArrayList zzbx;
    @Field(id=10) String zzbz;
    @Field(id=2) boolean zzde;
    @Field(id=4) boolean zzdf;
    @Field(id=7) PaymentMethodTokenizationParameters zzdp;
    @Field(id=1) boolean zzdx;
    @Field(id=3) CardRequirements zzdy;
    @Field(id=5) ShippingAddressRequirements zzdz;
    @Field(id=8) TransactionInfo zzea;
    @Field(defaultValue="true", id=9) boolean zzeb;

    static {
        PaymentDataRequest.CREATOR = new zzaf();
    }

    @Constructor PaymentDataRequest(@Param(id=1) boolean arg1, @Param(id=2) boolean arg2, @Param(id=3) CardRequirements arg3, @Param(id=4) boolean arg4, @Param(id=5) ShippingAddressRequirements arg5, @Param(id=6) ArrayList arg6, @Param(id=7) PaymentMethodTokenizationParameters arg7, @Param(id=8) TransactionInfo arg8, @Param(id=9) boolean arg9, @Param(id=10) String arg10) {
        super();
        this.zzdx = arg1;
        this.zzde = arg2;
        this.zzdy = arg3;
        this.zzdf = arg4;
        this.zzdz = arg5;
        this.zzbx = arg6;
        this.zzdp = arg7;
        this.zzea = arg8;
        this.zzeb = arg9;
        this.zzbz = arg10;
    }

    private PaymentDataRequest() {
        super();
        this.zzeb = true;
    }

    public static PaymentDataRequest fromJson(String arg2) {
        Builder v0 = PaymentDataRequest.newBuilder();
        v0.zzec.zzbz = Preconditions.checkNotNull(arg2, "paymentDataRequestJson cannot be null!");
        return v0.build();
    }

    public final ArrayList getAllowedPaymentMethods() {
        return this.zzbx;
    }

    public final CardRequirements getCardRequirements() {
        return this.zzdy;
    }

    public final PaymentMethodTokenizationParameters getPaymentMethodTokenizationParameters() {
        return this.zzdp;
    }

    public final ShippingAddressRequirements getShippingAddressRequirements() {
        return this.zzdz;
    }

    public final TransactionInfo getTransactionInfo() {
        return this.zzea;
    }

    public final boolean isEmailRequired() {
        return this.zzdx;
    }

    public final boolean isPhoneNumberRequired() {
        return this.zzde;
    }

    public final boolean isShippingAddressRequired() {
        return this.zzdf;
    }

    public final boolean isUiRequired() {
        return this.zzeb;
    }

    public static Builder newBuilder() {
        return new Builder(new PaymentDataRequest(), null);
    }

    public final String toJson() {
        return this.zzbz;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeBoolean(arg5, 1, this.zzdx);
        SafeParcelWriter.writeBoolean(arg5, 2, this.zzde);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdy, arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 4, this.zzdf);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzdz, arg6, false);
        SafeParcelWriter.writeIntegerList(arg5, 6, this.zzbx, false);
        SafeParcelWriter.writeParcelable(arg5, 7, this.zzdp, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 8, this.zzea, arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 9, this.zzeb);
        SafeParcelWriter.writeString(arg5, 10, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

