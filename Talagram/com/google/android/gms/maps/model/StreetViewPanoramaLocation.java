package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="StreetViewPanoramaLocationCreator") @Reserved(value={1}) public class StreetViewPanoramaLocation extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final StreetViewPanoramaLink[] links;
    @Field(id=4) public final String panoId;
    @Field(id=3) public final LatLng position;

    static {
        StreetViewPanoramaLocation.CREATOR = new zzo();
    }

    @Constructor public StreetViewPanoramaLocation(@Param(id=2) StreetViewPanoramaLink[] arg1, @Param(id=3) LatLng arg2, @Param(id=4) String arg3) {
        super();
        this.links = arg1;
        this.position = arg2;
        this.panoId = arg3;
    }

    public boolean equals(Object arg5) {
        if(this == (((StreetViewPanoramaLocation)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof StreetViewPanoramaLocation)) {
            return 0;
        }

        if((this.panoId.equals(((StreetViewPanoramaLocation)arg5).panoId)) && (this.position.equals(((StreetViewPanoramaLocation)arg5).position))) {
            return 1;
        }

        return 0;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.position, this.panoId});
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("position", this.position.toString()).toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedArray(arg5, 2, this.links, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.position, arg6, false);
        SafeParcelWriter.writeString(arg5, 4, this.panoId, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

