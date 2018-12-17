package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PaymentMethodTokenCreator") @Reserved(value={1}) public final class PaymentMethodToken extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private int zzed;
    @Field(id=3) private String zzee;

    static {
        PaymentMethodToken.CREATOR = new zzag();
    }

    @Constructor PaymentMethodToken(@Param(id=2) int arg1, @Param(id=3) String arg2) {
        super();
        this.zzed = arg1;
        this.zzee = arg2;
    }

    private PaymentMethodToken() {
        super();
    }

    public final int getPaymentMethodTokenizationType() {
        return this.zzed;
    }

    public final String getToken() {
        return this.zzee;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.zzed);
        SafeParcelWriter.writeString(arg4, 3, this.zzee, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

