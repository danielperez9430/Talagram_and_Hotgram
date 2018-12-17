package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="StreetViewPanoramaLinkCreator") @Reserved(value={1}) public class StreetViewPanoramaLink extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) public final float bearing;
    @Field(id=2) public final String panoId;

    static {
        StreetViewPanoramaLink.CREATOR = new zzn();
    }

    public StreetViewPanoramaLink(String arg5, float arg6) {
        // Method was not decompiled
    }

    public boolean equals(Object arg5) {
        if(this == (((StreetViewPanoramaLink)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof StreetViewPanoramaLink)) {
            return 0;
        }

        if((this.panoId.equals(((StreetViewPanoramaLink)arg5).panoId)) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(((StreetViewPanoramaLink)arg5).bearing)) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.panoId, Float.valueOf(this.bearing)});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.panoId, false);
        SafeParcelWriter.writeFloat(arg4, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

