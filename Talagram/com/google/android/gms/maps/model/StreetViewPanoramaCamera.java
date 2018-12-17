package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="StreetViewPanoramaCameraCreator") @Reserved(value={1}) public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        public float bearing;
        public float tilt;
        public float zoom;

        public Builder() {
            super();
        }

        public Builder(StreetViewPanoramaCamera arg2) {
            super();
            Preconditions.checkNotNull(arg2, "StreetViewPanoramaCamera");
            this.zoom = arg2.zoom;
            this.bearing = arg2.bearing;
            this.tilt = arg2.tilt;
        }

        public final Builder bearing(float arg1) {
            this.bearing = arg1;
            return this;
        }

        public final StreetViewPanoramaCamera build() {
            return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
        }

        public final Builder orientation(StreetViewPanoramaOrientation arg2) {
            Preconditions.checkNotNull(arg2, "StreetViewPanoramaOrientation");
            this.tilt = arg2.tilt;
            this.bearing = arg2.bearing;
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
    @Field(id=4) public final float bearing;
    @Field(id=3) public final float tilt;
    @Field(id=2) public final float zoom;
    private final StreetViewPanoramaOrientation zzef;

    static {
        StreetViewPanoramaCamera.CREATOR = new zzm();
    }

    public StreetViewPanoramaCamera(float arg6, float arg7, float arg8) {
        // Method was not decompiled
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(StreetViewPanoramaCamera arg1) {
        return new Builder(arg1);
    }

    public boolean equals(Object arg5) {
        if(this == (((StreetViewPanoramaCamera)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof StreetViewPanoramaCamera)) {
            return 0;
        }

        if(Float.floatToIntBits(this.zoom) == Float.floatToIntBits(((StreetViewPanoramaCamera)arg5).zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaCamera)arg5).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaCamera)arg5).bearing)) {
            return 1;
        }

        return 0;
    }

    public StreetViewPanoramaOrientation getOrientation() {
        return this.zzef;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing)});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeFloat(arg3, 2, this.zoom);
        SafeParcelWriter.writeFloat(arg3, 3, this.tilt);
        SafeParcelWriter.writeFloat(arg3, 4, this.bearing);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

