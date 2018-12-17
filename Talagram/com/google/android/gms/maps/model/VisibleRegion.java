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

@Class(creator="VisibleRegionCreator") @Reserved(value={1}) public final class VisibleRegion extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=4) public final LatLng farLeft;
    @Field(id=5) public final LatLng farRight;
    @Field(id=6) public final LatLngBounds latLngBounds;
    @Field(id=2) public final LatLng nearLeft;
    @Field(id=3) public final LatLng nearRight;

    static {
        VisibleRegion.CREATOR = new zzv();
    }

    @Constructor public VisibleRegion(@Param(id=2) LatLng arg1, @Param(id=3) LatLng arg2, @Param(id=4) LatLng arg3, @Param(id=5) LatLng arg4, @Param(id=6) LatLngBounds arg5) {
        super();
        this.nearLeft = arg1;
        this.nearRight = arg2;
        this.farLeft = arg3;
        this.farRight = arg4;
        this.latLngBounds = arg5;
    }

    public final boolean equals(Object arg5) {
        if(this == (((VisibleRegion)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof VisibleRegion)) {
            return 0;
        }

        if((this.nearLeft.equals(((VisibleRegion)arg5).nearLeft)) && (this.nearRight.equals(((VisibleRegion)arg5).nearRight)) && (this.farLeft.equals(((VisibleRegion)arg5).farLeft)) && (this.farRight.equals(((VisibleRegion)arg5).farRight)) && (this.latLngBounds.equals(((VisibleRegion)arg5).latLngBounds))) {
            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("nearLeft", this.nearLeft).add("nearRight", this.nearRight).add("farLeft", this.farLeft).add("farRight", this.farRight).add("latLngBounds", this.latLngBounds).toString();
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.nearLeft, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.nearRight, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.farLeft, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 5, this.farRight, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.latLngBounds, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

