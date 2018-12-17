package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PointOfInterestCreator") @Reserved(value={1}) public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final LatLng latLng;
    @Field(id=4) public final String name;
    @Field(id=3) public final String placeId;

    static {
        PointOfInterest.CREATOR = new zzj();
    }

    @Constructor public PointOfInterest(@Param(id=2) LatLng arg1, @Param(id=3) String arg2, @Param(id=4) String arg3) {
        super();
        this.latLng = arg1;
        this.placeId = arg2;
        this.name = arg3;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 2, this.latLng, arg6, false);
        SafeParcelWriter.writeString(arg5, 3, this.placeId, false);
        SafeParcelWriter.writeString(arg5, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

