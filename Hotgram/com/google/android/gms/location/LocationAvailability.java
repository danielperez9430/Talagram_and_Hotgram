package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Arrays;

@Class(creator="LocationAvailabilityCreator") @Reserved(value={1000}) public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="LocationAvailability.STATUS_UNKNOWN", id=1) @Deprecated private int zzar;
    @Field(defaultValueUnchecked="LocationAvailability.STATUS_UNKNOWN", id=2) @Deprecated private int zzas;
    @Field(defaultValueUnchecked="0", id=3) private long zzat;
    @Field(defaultValueUnchecked="LocationAvailability.STATUS_UNSUCCESSFUL", id=4) private int zzau;
    @Field(id=5) private zzaj[] zzav;

    static {
        LocationAvailability.CREATOR = new zzaa();
    }

    @Constructor LocationAvailability(@Param(id=4) int arg1, @Param(id=1) int arg2, @Param(id=2) int arg3, @Param(id=3) long arg4, @Param(id=5) zzaj[] arg6) {
        super();
        this.zzau = arg1;
        this.zzar = arg2;
        this.zzas = arg3;
        this.zzat = arg4;
        this.zzav = arg6;
    }

    public final boolean equals(Object arg8) {
        if(this == (((LocationAvailability)arg8))) {
            return 1;
        }

        if(arg8 != null) {
            if(this.getClass() != arg8.getClass()) {
            }
            else if(this.zzar == ((LocationAvailability)arg8).zzar && this.zzas == ((LocationAvailability)arg8).zzas && this.zzat == ((LocationAvailability)arg8).zzat && this.zzau == ((LocationAvailability)arg8).zzau && (Arrays.equals(this.zzav, ((LocationAvailability)arg8).zzav))) {
                return 1;
            }
        }

        return 0;
    }

    public static LocationAvailability extractLocationAvailability(Intent arg1) {
        if(!LocationAvailability.hasLocationAvailability(arg1)) {
            return null;
        }

        return arg1.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public static boolean hasLocationAvailability(Intent arg1) {
        if(arg1 == null) {
            return 0;
        }

        return arg1.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzau), Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), this.zzav});
    }

    public final boolean isLocationAvailable() {
        if(this.zzau < 1000) {
            return 1;
        }

        return 0;
    }

    public final String toString() {
        boolean v0 = this.isLocationAvailable();
        StringBuilder v1 = new StringBuilder(48);
        v1.append("LocationAvailability[isLocationAvailable: ");
        v1.append(v0);
        v1.append("]");
        return v1.toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzar);
        SafeParcelWriter.writeInt(arg5, 2, this.zzas);
        SafeParcelWriter.writeLong(arg5, 3, this.zzat);
        SafeParcelWriter.writeInt(arg5, 4, this.zzau);
        SafeParcelWriter.writeTypedArray(arg5, 5, this.zzav, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

