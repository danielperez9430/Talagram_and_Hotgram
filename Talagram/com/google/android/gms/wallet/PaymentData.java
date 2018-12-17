package com.google.android.gms.wallet;

import android.content.Intent;
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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator="PaymentDataCreator") public final class PaymentData extends AbstractSafeParcelable implements AutoResolvableResult {
    public final class zza {
        zza(PaymentData arg1, zzac arg2) {
            this(arg1);
        }

        private zza(PaymentData arg1) {
            this.zzdw = arg1;
            super();
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=5) private String zzax;
    @Field(id=1) private String zzba;
    @Field(id=4) private PaymentMethodToken zzbh;
    @Field(id=7) private String zzbz;
    @Field(id=2) private CardInfo zzdt;
    @Field(id=3) private UserAddress zzdu;
    @Field(id=6) private Bundle zzdv;

    static {
        PaymentData.CREATOR = new zzad();
    }

    @Constructor PaymentData(@Param(id=1) String arg1, @Param(id=2) CardInfo arg2, @Param(id=3) UserAddress arg3, @Param(id=4) PaymentMethodToken arg4, @Param(id=5) String arg5, @Param(id=6) Bundle arg6, @Param(id=7) String arg7) {
        super();
        this.zzba = arg1;
        this.zzdt = arg2;
        this.zzdu = arg3;
        this.zzbh = arg4;
        this.zzax = arg5;
        this.zzdv = arg6;
        this.zzbz = arg7;
    }

    private PaymentData() {
        super();
    }

    public static PaymentData fromJson(String arg3) {
        zza v0 = new zza(new PaymentData(), null);
        v0.zzdw.zzbz = Preconditions.checkNotNull(arg3, "paymentDataJson cannot be null!");
        return v0.zzdw;
    }

    public final CardInfo getCardInfo() {
        return this.zzdt;
    }

    public final String getEmail() {
        return this.zzba;
    }

    public final Bundle getExtraData() {
        return this.zzdv;
    }

    public static PaymentData getFromIntent(Intent arg2) {
        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "com.google.android.gms.wallet.PaymentData", PaymentData.CREATOR);
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbh;
    }

    public final UserAddress getShippingAddress() {
        return this.zzdu;
    }

    public final void putIntoIntent(Intent arg2) {
        SafeParcelableSerializer.serializeToIntentExtra(((SafeParcelable)this), arg2, "com.google.android.gms.wallet.PaymentData");
    }

    public final String toJson() {
        return this.zzbz;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 1, this.zzba, false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzdt, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdu, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzbh, arg6, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzax, false);
        SafeParcelWriter.writeBundle(arg5, 6, this.zzdv, false);
        SafeParcelWriter.writeString(arg5, 7, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

