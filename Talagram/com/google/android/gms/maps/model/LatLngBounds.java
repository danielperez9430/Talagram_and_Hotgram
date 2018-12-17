package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.AttributeSet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.maps.GoogleMapOptions;

@Class(creator="LatLngBoundsCreator") @Reserved(value={1}) public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        private double zzdg;
        private double zzdh;
        private double zzdi;
        private double zzdj;

        public Builder() {
            super();
            this.zzdg = Infinity;
            this.zzdh = -Infinity;
            this.zzdi = NaN;
            this.zzdj = NaN;
        }

        public final LatLngBounds build() {
            Preconditions.checkState(Double.isNaN(this.zzdi) ^ 1, "no included points");
            return new LatLngBounds(new LatLng(this.zzdg, this.zzdi), new LatLng(this.zzdh, this.zzdj));
        }

        public final Builder include(LatLng arg7) {
            // Method was not decompiled
        }
    }

    @KeepForSdk public static final Parcelable$Creator CREATOR;
    @Field(id=3) public final LatLng northeast;
    @Field(id=2) public final LatLng southwest;

    static {
        LatLngBounds.CREATOR = new zze();
    }

    public LatLngBounds(LatLng arg8, LatLng arg9) {
        // Method was not decompiled
    }

    public static Builder builder() {
        return new Builder();
    }

    public final boolean contains(LatLng arg8) {
        // Method was not decompiled
    }

    public static LatLngBounds createFromAttributes(Context arg0, AttributeSet arg1) {
        return GoogleMapOptions.zza(arg0, arg1);
    }

    public final boolean equals(Object arg5) {
        if(this == (((LatLngBounds)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof LatLngBounds)) {
            return 0;
        }

        if((this.southwest.equals(((LatLngBounds)arg5).southwest)) && (this.northeast.equals(((LatLngBounds)arg5).northeast))) {
            return 1;
        }

        return 0;
    }

    public final LatLng getCenter() {
        double v2 = 2;
        double v0 = (this.southwest.latitude + this.northeast.latitude) / v2;
        double v4 = this.northeast.longitude;
        double v6 = this.southwest.longitude;
        if(v6 > v4) {
            v4 += 360;
        }

        v4 = (v4 + v6) / v2;
        return new LatLng(v0, v4);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.southwest, this.northeast});
    }

    public final LatLngBounds including(LatLng arg15) {
        double v0 = Math.min(this.southwest.latitude, arg15.latitude);
        double v2 = Math.max(this.northeast.latitude, arg15.latitude);
        double v4 = this.northeast.longitude;
        double v6 = this.southwest.longitude;
        double v8 = arg15.longitude;
        if(!this.zza(v8)) {
            if(LatLngBounds.zza(v6, v8) < LatLngBounds.zzb(v4, v8)) {
                v6 = v8;
            }
            else {
                v4 = v8;
            }
        }

        return new LatLngBounds(new LatLng(v0, v6), new LatLng(v2, v4));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.southwest, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.northeast, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    private static double zza(double arg0, double arg2) {
        return (arg0 - arg2 + 360) % 360;
    }

    private final boolean zza(double arg6) {
        // Method was not decompiled
    }

    private static double zzb(double arg0, double arg2) {
        return (arg2 - arg0 + 360) % 360;
    }

    static double zzc(double arg0, double arg2) {
        return LatLngBounds.zza(arg0, arg2);
    }

    static double zzd(double arg0, double arg2) {
        return LatLngBounds.zzb(arg0, arg2);
    }
}

