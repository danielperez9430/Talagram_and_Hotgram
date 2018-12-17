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

@Class(creator="TransactionInfoCreator") public final class TransactionInfo extends AbstractSafeParcelable {
    public final class Builder {
        Builder(TransactionInfo arg1, zzao arg2) {
            this(arg1);
        }

        private Builder(TransactionInfo arg1) {
            this.zzeq = arg1;
            super();
        }

        public final TransactionInfo build() {
            Preconditions.checkNotEmpty(this.zzeq.zzap, "currencyCode must be set!");
            int v1 = 3;
            int v2 = 2;
            int v3 = 1;
            if(this.zzeq.zzep != 1 && this.zzeq.zzep != v2) {
                if(this.zzeq.zzep == v1) {
                }
                else {
                    v3 = 0;
                }
            }

            if(v3 != 0) {
                if(this.zzeq.zzep == v2) {
                    Preconditions.checkNotEmpty(this.zzeq.zzao, "An estimated total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_ESTIMATED!");
                }

                if(this.zzeq.zzep == v1) {
                    Preconditions.checkNotEmpty(this.zzeq.zzao, "An final total price must be set if totalPriceStatus is set to WalletConstants.TOTAL_PRICE_STATUS_FINAL!");
                }

                return this.zzeq;
            }

            throw new IllegalArgumentException("totalPriceStatus must be set to one of WalletConstants.TotalPriceStatus!");
        }

        public final Builder setCurrencyCode(String arg2) {
            this.zzeq.zzap = arg2;
            return this;
        }

        public final Builder setTotalPrice(String arg2) {
            this.zzeq.zzao = arg2;
            return this;
        }

        public final Builder setTotalPriceStatus(int arg2) {
            this.zzeq.zzep = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String zzao;
    @Field(id=3) String zzap;
    @Field(id=1) int zzep;

    static {
        TransactionInfo.CREATOR = new zzap();
    }

    @Constructor public TransactionInfo(@Param(id=1) int arg1, @Param(id=2) String arg2, @Param(id=3) String arg3) {
        super();
        this.zzep = arg1;
        this.zzao = arg2;
        this.zzap = arg3;
    }

    private TransactionInfo() {
        super();
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final String getTotalPrice() {
        return this.zzao;
    }

    public final int getTotalPriceStatus() {
        return this.zzep;
    }

    public static Builder newBuilder() {
        return new Builder(new TransactionInfo(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 1, this.zzep);
        SafeParcelWriter.writeString(arg4, 2, this.zzao, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzap, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

