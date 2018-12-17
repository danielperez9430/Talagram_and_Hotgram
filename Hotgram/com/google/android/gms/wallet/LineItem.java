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

@Class(creator="LineItemCreator") @Reserved(value={1}) public final class LineItem extends AbstractSafeParcelable {
    public final class Builder {
        Builder(LineItem arg1, zzs arg2) {
            this(arg1);
        }

        private Builder(LineItem arg1) {
            this.zzce = arg1;
            super();
        }

        public final LineItem build() {
            return this.zzce;
        }

        public final Builder setCurrencyCode(String arg2) {
            this.zzce.zzap = arg2;
            return this;
        }

        public final Builder setDescription(String arg2) {
            this.zzce.description = arg2;
            return this;
        }

        public final Builder setQuantity(String arg2) {
            this.zzce.zzcb = arg2;
            return this;
        }

        public final Builder setRole(int arg2) {
            this.zzce.zzcd = arg2;
            return this;
        }

        public final Builder setTotalPrice(String arg2) {
            this.zzce.zzao = arg2;
            return this;
        }

        public final Builder setUnitPrice(String arg2) {
            this.zzce.zzcc = arg2;
            return this;
        }
    }

    public interface Role {
        public static final int REGULAR = 0;
        public static final int SHIPPING = 2;
        public static final int TAX = 1;

    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String description;
    @Field(id=5) String zzao;
    @Field(id=7) String zzap;
    @Field(id=3) String zzcb;
    @Field(id=4) String zzcc;
    @Field(defaultValueUnchecked="com.google.android.gms.wallet.LineItem.Role.REGULAR", id=6) int zzcd;

    static {
        LineItem.CREATOR = new zzt();
    }

    @Constructor LineItem(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) String arg3, @Param(id=5) String arg4, @Param(id=6) int arg5, @Param(id=7) String arg6) {
        super();
        this.description = arg1;
        this.zzcb = arg2;
        this.zzcc = arg3;
        this.zzao = arg4;
        this.zzcd = arg5;
        this.zzap = arg6;
    }

    LineItem() {
        super();
        this.zzcd = 0;
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getQuantity() {
        return this.zzcb;
    }

    public final int getRole() {
        return this.zzcd;
    }

    public final String getTotalPrice() {
        return this.zzao;
    }

    public final String getUnitPrice() {
        return this.zzcc;
    }

    public static Builder newBuilder() {
        return new Builder(new LineItem(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.description, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzcb, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzcc, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzao, false);
        SafeParcelWriter.writeInt(arg4, 6, this.zzcd);
        SafeParcelWriter.writeString(arg4, 7, this.zzap, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

