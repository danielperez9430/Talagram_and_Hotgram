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

@Class(creator="FullWalletRequestCreator") @Reserved(value={1}) public final class FullWalletRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(FullWalletRequest arg1, zzl arg2) {
            this(arg1);
        }

        private Builder(FullWalletRequest arg1) {
            this.zzbj = arg1;
            super();
        }

        public final FullWalletRequest build() {
            return this.zzbj;
        }

        public final Builder setCart(Cart arg2) {
            this.zzbj.zzbi = arg2;
            return this;
        }

        public final Builder setGoogleTransactionId(String arg2) {
            this.zzbj.zzax = arg2;
            return this;
        }

        public final Builder setMerchantTransactionId(String arg2) {
            this.zzbj.zzay = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String zzax;
    @Field(id=3) String zzay;
    @Field(id=4) Cart zzbi;

    static {
        FullWalletRequest.CREATOR = new zzm();
    }

    @Constructor FullWalletRequest(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) Cart arg3) {
        super();
        this.zzax = arg1;
        this.zzay = arg2;
        this.zzbi = arg3;
    }

    FullWalletRequest() {
        super();
    }

    public final Cart getCart() {
        return this.zzbi;
    }

    public final String getGoogleTransactionId() {
        return this.zzax;
    }

    public final String getMerchantTransactionId() {
        return this.zzay;
    }

    public static Builder newBuilder() {
        return new Builder(new FullWalletRequest(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzax, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzay, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzbi, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

