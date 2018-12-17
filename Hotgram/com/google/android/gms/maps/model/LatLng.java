package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="LatLngCreator") @Reserved(value={1}) public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final double latitude;
    @Field(id=3) public final double longitude;

    static {
        LatLng.CREATOR = new zzf();
    }

    public LatLng(double arg5, double arg7) {
        // Method was not decompiled
    }

    public final boolean equals(Object arg8) {
        if(this == (((LatLng)arg8))) {
            return 1;
        }

        if(!(arg8 instanceof LatLng)) {
            return 0;
        }

        if(Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(((LatLng)arg8).latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(((LatLng)arg8).longitude)) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        long v0 = Double.doubleToLongBits(this.latitude);
        long v3 = Double.doubleToLongBits(this.longitude);
        return ((((int)(v0 ^ v0 >>> 32))) + 31) * 31 + (((int)(v3 >>> 32 ^ v3)));
    }

    public final String toString() {
        double v0 = this.latitude;
        double v2 = this.longitude;
        StringBuilder v4 = new StringBuilder(60);
        v4.append("lat/lng: (");
        v4.append(v0);
        v4.append(",");
        v4.append(v2);
        v4.append(")");
        return v4.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeDouble(arg4, 2, this.latitude);
        SafeParcelWriter.writeDouble(arg4, 3, this.longitude);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

