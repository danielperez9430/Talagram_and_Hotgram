package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.maps.GoogleMapOptions;

@Class(creator="CameraPositionCreator") @Reserved(value={1}) public final class CameraPosition extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        private float bearing;
        private LatLng target;
        private float tilt;
        private float zoom;

        public Builder() {
            super();
        }

        public Builder(CameraPosition arg2) {
            super();
            this.target = arg2.target;
            this.zoom = arg2.zoom;
            this.tilt = arg2.tilt;
            this.bearing = arg2.bearing;
        }

        public final Builder bearing(float arg1) {
            this.bearing = arg1;
            return this;
        }

        public final CameraPosition build() {
            return new CameraPosition(this.target, this.zoom, this.tilt, this.bearing);
        }

        public final Builder target(LatLng arg1) {
            this.target = arg1;
            return this;
        }

        public final Builder tilt(float arg1) {
            this.tilt = arg1;
            return this;
        }

        public final Builder zoom(float arg1) {
            this.zoom = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=5) public final float bearing;
    @Field(id=2) public final LatLng target;
    @Field(id=4) public final float tilt;
    @Field(id=3) public final float zoom;

    static {
        CameraPosition.CREATOR = new zza();
    }

    public CameraPosition(LatLng arg7, float arg8, float arg9, float arg10) {
        // Method was not decompiled
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition arg1) {
        return new Builder(arg1);
    }

    public static CameraPosition createFromAttributes(Context arg0, AttributeSet arg1) {
        return GoogleMapOptions.zzb(arg0, arg1);
    }

    public final boolean equals(Object arg5) {
        if(this == (((CameraPosition)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof CameraPosition)) {
            return 0;
        }

        if((this.target.equals(((CameraPosition)arg5).target)) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(((CameraPosition)arg5).zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((CameraPosition)arg5).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((CameraPosition)arg5).bearing)) {
            return 1;
        }

        return 0;
    }

    public static final CameraPosition fromLatLngZoom(LatLng arg2, float arg3) {
        return new CameraPosition(arg2, arg3, 0f, 0f);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing)});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("target", this.target).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.target, arg6, false);
        SafeParcelWriter.writeFloat(arg5, 3, this.zoom);
        SafeParcelWriter.writeFloat(arg5, 4, this.tilt);
        SafeParcelWriter.writeFloat(arg5, 5, this.bearing);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

