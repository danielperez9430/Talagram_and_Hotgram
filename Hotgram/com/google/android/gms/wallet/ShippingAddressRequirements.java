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
import java.util.ArrayList;
import java.util.Collection;

@Class(creator="ShippingAddressRequirementsCreator") public final class ShippingAddressRequirements extends AbstractSafeParcelable {
    public final class Builder {
        Builder(ShippingAddressRequirements arg1, zzam arg2) {
            this(arg1);
        }

        private Builder(ShippingAddressRequirements arg1) {
            this.zzeo = arg1;
            super();
        }

        public final Builder addAllowedCountryCode(String arg3) {
            Preconditions.checkNotEmpty(arg3, "allowedCountryCode can\'t be null or empty! If you don\'t have restrictions, just leave it unset.");
            if(this.zzeo.zzen == null) {
                this.zzeo.zzen = new ArrayList();
            }

            this.zzeo.zzen.add(arg3);
            return this;
        }

        public final Builder addAllowedCountryCodes(Collection arg3) {
            if(arg3 != null && !arg3.isEmpty()) {
                if(this.zzeo.zzen == null) {
                    this.zzeo.zzen = new ArrayList();
                }

                this.zzeo.zzen.addAll(arg3);
                return this;
            }

            throw new IllegalArgumentException("allowedCountryCodes can\'t be null or empty! If you don\'t have restrictions, just leave it unset.");
        }

        public final ShippingAddressRequirements build() {
            return this.zzeo;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=1) ArrayList zzen;

    static {
        ShippingAddressRequirements.CREATOR = new zzan();
    }

    @Constructor ShippingAddressRequirements(@Param(id=1) ArrayList arg1) {
        super();
        this.zzen = arg1;
    }

    private ShippingAddressRequirements() {
        super();
    }

    public final ArrayList getAllowedCountryCodes() {
        return this.zzen;
    }

    public static Builder newBuilder() {
        return new Builder(new ShippingAddressRequirements(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeStringList(arg4, 1, this.zzen, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

