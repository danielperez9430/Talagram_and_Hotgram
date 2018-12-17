package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="StreetViewPanoramaOrientationCreator") @Reserved(value={1}) public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
    public final class Builder {
        public float bearing;
        public float tilt;

        public Builder() {
            super();
        }

        public Builder(StreetViewPanoramaOrientation arg2) {
            super();
            Preconditions.checkNotNull(arg2, "StreetViewPanoramaOrientation");
            this.bearing = arg2.bearing;
            this.tilt = arg2.tilt;
        }

        public final Builder bearing(float arg1) {
            this.bearing = arg1;
            return this;
        }

        public final StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
        }

        public final Builder tilt(float arg1) {
            this.tilt = arg1;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=3) public final float bearing;
    @Field(id=2) public final float tilt;

    static {
        StreetViewPanoramaOrientation.CREATOR = new zzp();
    }

    public StreetViewPanoramaOrientation(float arg5, float arg6) {
        // Method was not decompiled
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(StreetViewPanoramaOrientation arg1) {
        return new Builder(arg1);
    }

    public boolean equals(Object arg5) {
        if(this == (((StreetViewPanoramaOrientation)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof StreetViewPanoramaOrientation)) {
            return 0;
        }

        if(Float.floatToIntBits(this.tilt) == Float.floatToIntBits(((StreetViewPanoramaOrientation)arg5).tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaOrientation)arg5).bearing)) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Float.valueOf(this.tilt), Float.valueOf(this.bearing)});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeFloat(arg3, 2, this.tilt);
        SafeParcelWriter.writeFloat(arg3, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

