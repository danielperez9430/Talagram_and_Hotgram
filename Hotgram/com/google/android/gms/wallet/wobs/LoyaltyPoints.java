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

@Class(creator="LoyaltyPointsCreator") @Reserved(value={1, 4}) public final class LoyaltyPoints extends AbstractSafeParcelable {
    public final class Builder {
        Builder(LoyaltyPoints arg1, zzf arg2) {
            this(arg1);
        }

        private Builder(LoyaltyPoints arg1) {
            this.zzgu = arg1;
            super();
        }

        public final LoyaltyPoints build() {
            return this.zzgu;
        }

        public final Builder setBalance(LoyaltyPointsBalance arg2) {
            this.zzgu.zzgt = arg2;
            return this;
        }

        public final Builder setLabel(String arg2) {
            this.zzgu.label = arg2;
            return this;
        }

        @Deprecated public final Builder setType(String arg1) {
            return this;
        }

        @Deprecated public final Builder setValidTimeInterval(TimeInterval arg2) {
            this.zzgu.zzcq = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String label;
    @Field(id=5) @Deprecated TimeInterval zzcq;
    @Field(id=3) LoyaltyPointsBalance zzgt;

    static {
        LoyaltyPoints.CREATOR = new zzi();
    }

    @Constructor LoyaltyPoints(@Param(id=2) String arg1, @Param(id=3) LoyaltyPointsBalance arg2, @Param(id=5) TimeInterval arg3) {
        super();
        this.label = arg1;
        this.zzgt = arg2;
        this.zzcq = arg3;
    }

    LoyaltyPoints() {
        super();
    }

    public final LoyaltyPointsBalance getBalance() {
        return this.zzgt;
    }

    public final String getLabel() {
        return this.label;
    }

    @Deprecated public final String getType() {
        return null;
    }

    @Deprecated public final TimeInterval getValidTimeInterval() {
        return this.zzcq;
    }

    public static Builder newBuilder() {
        return new Builder(new LoyaltyPoints(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.label, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzgt, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzcq, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

