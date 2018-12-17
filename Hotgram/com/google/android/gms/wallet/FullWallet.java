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
import com.google.android.gms.identity.intents.model.UserAddress;

@Class(creator="FullWalletCreator") @Reserved(value={1}) public final class FullWallet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private String zzax;
    @Field(id=3) private String zzay;
    @Field(id=4) private ProxyCard zzaz;
    @Field(id=5) private String zzba;
    @Field(id=6) private zza zzbb;
    @Field(id=7) private zza zzbc;
    @Field(id=8) private String[] zzbd;
    @Field(id=9) private UserAddress zzbe;
    @Field(id=10) private UserAddress zzbf;
    @Field(id=11) private InstrumentInfo[] zzbg;
    @Field(id=12) private PaymentMethodToken zzbh;

    static {
        FullWallet.CREATOR = new zzk();
    }

    @Constructor FullWallet(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) ProxyCard arg3, @Param(id=5) String arg4, @Param(id=6) zza arg5, @Param(id=7) zza arg6, @Param(id=8) String[] arg7, @Param(id=9) UserAddress arg8, @Param(id=10) UserAddress arg9, @Param(id=11) InstrumentInfo[] arg10, @Param(id=12) PaymentMethodToken arg11) {
        super();
        this.zzax = arg1;
        this.zzay = arg2;
        this.zzaz = arg3;
        this.zzba = arg4;
        this.zzbb = arg5;
        this.zzbc = arg6;
        this.zzbd = arg7;
        this.zzbe = arg8;
        this.zzbf = arg9;
        this.zzbg = arg10;
        this.zzbh = arg11;
    }

    private FullWallet() {
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

    public final PaymentMethodToken getPaymentMethodToken() {
        return this.zzbh;
    }

    public final ProxyCard getProxyCard() {
        return this.zzaz;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzax, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzay, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzaz, arg6, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzba, false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.zzbb, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 7, this.zzbc, arg6, false);
        SafeParcelWriter.writeStringArray(arg5, 8, this.zzbd, false);
        SafeParcelWriter.writeParcelable(arg5, 9, this.zzbe, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 10, this.zzbf, arg6, false);
        SafeParcelWriter.writeTypedArray(arg5, 11, this.zzbg, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 12, this.zzbh, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

