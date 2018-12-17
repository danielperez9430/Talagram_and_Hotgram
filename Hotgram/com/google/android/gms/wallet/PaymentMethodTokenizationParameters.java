package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PaymentMethodTokenizationParametersCreator") @Reserved(value={1}) public final class PaymentMethodTokenizationParameters extends AbstractSafeParcelable {
    public final class Builder {
        Builder(PaymentMethodTokenizationParameters arg1, zzah arg2) {
            this(arg1);
        }

        private Builder(PaymentMethodTokenizationParameters arg1) {
            this.zzeg = arg1;
            super();
        }

        public final Builder addParameter(String arg2, String arg3) {
            Preconditions.checkNotEmpty(arg2, "Tokenization parameter name must not be empty");
            Preconditions.checkNotEmpty(arg3, "Tokenization parameter value must not be empty");
            this.zzeg.zzef.putString(arg2, arg3);
            return this;
        }

        public final PaymentMethodTokenizationParameters build() {
            return this.zzeg;
        }

        public final Builder setPaymentMethodTokenizationType(int arg2) {
            this.zzeg.zzed = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) int zzed;
    @Field(id=3) Bundle zzef;

    static {
        PaymentMethodTokenizationParameters.CREATOR = new zzai();
    }

    @Constructor PaymentMethodTokenizationParameters(@Param(id=2) int arg2, @Param(id=3) Bundle arg3) {
        super();
        this.zzef = new Bundle();
        this.zzed = arg2;
        this.zzef = arg3;
    }

    private PaymentMethodTokenizationParameters() {
        super();
        this.zzef = new Bundle();
    }

    public final Bundle getParameters() {
        return new Bundle(this.zzef);
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzed;
    }

    public static Builder newBuilder() {
        return new Builder(new PaymentMethodTokenizationParameters(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.zzed);
        SafeParcelWriter.writeBundle(arg4, 3, this.zzef, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

