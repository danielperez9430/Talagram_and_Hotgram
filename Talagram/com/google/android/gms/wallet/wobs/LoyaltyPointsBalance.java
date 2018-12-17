package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="LoyaltyPointsBalanceCreator") @Reserved(value={1}) public final class LoyaltyPointsBalance extends AbstractSafeParcelable {
    public final class Builder {
        Builder(LoyaltyPointsBalance arg1, zzg arg2) {
            this(arg1);
        }

        private Builder(LoyaltyPointsBalance arg1) {
            this.zzha = arg1;
            super();
        }

        public final LoyaltyPointsBalance build() {
            return this.zzha;
        }

        public final Builder setDouble(double arg2) {
            this.zzha.zzgx = arg2;
            this.zzha.zzgz = 2;
            return this;
        }

        public final Builder setInt(int arg2) {
            this.zzha.zzgv = arg2;
            this.zzha.zzgz = 0;
            return this;
        }

        public final Builder setMoney(String arg2, long arg3) {
            this.zzha.zzbo = arg2;
            this.zzha.zzgy = arg3;
            this.zzha.zzgz = 3;
            return this;
        }

        public final Builder setString(String arg2) {
            this.zzha.zzgw = arg2;
            this.zzha.zzgz = 1;
            return this;
        }
    }

    public interface Type {
        public static final int DOUBLE = 2;
        public static final int INT = 0;
        public static final int MONEY = 3;
        public static final int STRING = 1;
        public static final int UNDEFINED = -1;

    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=5) String zzbo;
    @Field(id=2) int zzgv;
    @Field(id=3) String zzgw;
    @Field(id=4) double zzgx;
    @Field(id=6) long zzgy;
    @Field(defaultValueUnchecked="com.google.android.gms.wallet.wobs.LoyaltyPointsBalance.Type.UNDEFINED", id=7) int zzgz;

    static {
        LoyaltyPointsBalance.CREATOR = new zzh();
    }

    @Constructor LoyaltyPointsBalance(@Param(id=2) int arg1, @Param(id=3) String arg2, @Param(id=4) double arg3, @Param(id=5) String arg5, @Param(id=6) long arg6, @Param(id=7) int arg8) {
        super();
        this.zzgv = arg1;
        this.zzgw = arg2;
        this.zzgx = arg3;
        this.zzbo = arg5;
        this.zzgy = arg6;
        this.zzgz = arg8;
    }

    LoyaltyPointsBalance() {
        super();
        this.zzgz = -1;
        this.zzgv = -1;
        this.zzgx = -1;
    }

    public final String getCurrencyCode() {
        return this.zzbo;
    }

    public final long getCurrencyMicros() {
        return this.zzgy;
    }

    public final double getDouble() {
        return this.zzgx;
    }

    public final int getInt() {
        return this.zzgv;
    }

    public final String getString() {
        return this.zzgw;
    }

    public final int getType() {
        return this.zzgz;
    }

    public static Builder newBuilder() {
        return new Builder(new LoyaltyPointsBalance(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        arg6 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.zzgv);
        SafeParcelWriter.writeString(arg5, 3, this.zzgw, false);
        SafeParcelWriter.writeDouble(arg5, 4, this.zzgx);
        SafeParcelWriter.writeString(arg5, 5, this.zzbo, false);
        SafeParcelWriter.writeLong(arg5, 6, this.zzgy);
        SafeParcelWriter.writeInt(arg5, 7, this.zzgz);
        SafeParcelWriter.finishObjectHeader(arg5, arg6);
    }
}

