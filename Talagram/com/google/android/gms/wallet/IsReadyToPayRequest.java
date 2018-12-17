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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="IsReadyToPayRequestCreator") @Reserved(value={1}) public final class IsReadyToPayRequest extends AbstractSafeParcelable {
    public final class Builder {
        Builder(IsReadyToPayRequest arg1, zzq arg2) {
            this(arg1);
        }

        private Builder(IsReadyToPayRequest arg1) {
            this.zzca = arg1;
            super();
        }

        public final Builder addAllowedCardNetwork(int arg3) {
            if(this.zzca.zzaj == null) {
                this.zzca.zzaj = new ArrayList();
            }

            this.zzca.zzaj.add(Integer.valueOf(arg3));
            return this;
        }

        public final Builder addAllowedCardNetworks(Collection arg3) {
            boolean v0 = arg3 == null || (arg3.isEmpty()) ? false : true;
            Preconditions.checkArgument(v0, "allowedCardNetworks can\'t be null or empty. If you want the defaults, leave it unset.");
            if(this.zzca.zzaj == null) {
                this.zzca.zzaj = new ArrayList();
            }

            this.zzca.zzaj.addAll(arg3);
            return this;
        }

        public final Builder addAllowedPaymentMethod(int arg3) {
            if(this.zzca.zzbx == null) {
                this.zzca.zzbx = new ArrayList();
            }

            this.zzca.zzbx.add(Integer.valueOf(arg3));
            return this;
        }

        public final Builder addAllowedPaymentMethods(Collection arg3) {
            boolean v0 = arg3 == null || (arg3.isEmpty()) ? false : true;
            Preconditions.checkArgument(v0, "allowedPaymentMethods can\'t be null or empty. If you want the default, leave it unset.");
            if(this.zzca.zzbx == null) {
                this.zzca.zzbx = new ArrayList();
            }

            this.zzca.zzbx.addAll(arg3);
            return this;
        }

        public final IsReadyToPayRequest build() {
            return this.zzca;
        }

        public final Builder setExistingPaymentMethodRequired(boolean arg2) {
            this.zzca.zzby = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) ArrayList zzaj;
    @Field(id=4) private String zzbv;
    @Field(id=5) private String zzbw;
    @Field(id=6) ArrayList zzbx;
    @Field(id=7) boolean zzby;
    @Field(id=8) private String zzbz;

    static {
        IsReadyToPayRequest.CREATOR = new zzr();
    }

    @Constructor IsReadyToPayRequest(@Param(id=2) ArrayList arg1, @Param(id=4) String arg2, @Param(id=5) String arg3, @Param(id=6) ArrayList arg4, @Param(id=7) boolean arg5, @Param(id=8) String arg6) {
        super();
        this.zzaj = arg1;
        this.zzbv = arg2;
        this.zzbw = arg3;
        this.zzbx = arg4;
        this.zzby = arg5;
        this.zzbz = arg6;
    }

    IsReadyToPayRequest() {
        super();
    }

    public static IsReadyToPayRequest fromJson(String arg2) {
        Builder v0 = IsReadyToPayRequest.newBuilder();
        v0.zzca.zzbz = Preconditions.checkNotNull(arg2, "isReadyToPayRequestJson cannot be null!");
        return v0.build();
    }

    public final ArrayList getAllowedCardNetworks() {
        return this.zzaj;
    }

    public final ArrayList getAllowedPaymentMethods() {
        return this.zzbx;
    }

    public final boolean isExistingPaymentMethodRequired() {
        return this.zzby;
    }

    public static Builder newBuilder() {
        return new Builder(new IsReadyToPayRequest(), null);
    }

    public final String toJson() {
        return this.zzbz;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeIntegerList(arg4, 2, this.zzaj, false);
        SafeParcelWriter.writeString(arg4, 4, this.zzbv, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzbw, false);
        SafeParcelWriter.writeIntegerList(arg4, 6, this.zzbx, false);
        SafeParcelWriter.writeBoolean(arg4, 7, this.zzby);
        SafeParcelWriter.writeString(arg4, 8, this.zzbz, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

