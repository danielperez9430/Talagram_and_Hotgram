package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import java.util.List;

@Class(creator="PlaceExtendedDetailsEntityCreator") public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getPlaceTypes", id=1) private final List zzdq;
    @Field(getter="getPhoneNumber", id=2) private final String zzdr;
    @Field(getter="getWebsiteUri", id=3) private final Uri zzds;
    @Field(getter="getRating", id=4) private final float zzgk;
    @Field(getter="getPriceLevel", id=5) private final int zzgl;

    static {
        zzah.CREATOR = new zzai();
    }

    @Constructor zzah(@Param(id=1) List arg1, @Param(id=2) String arg2, @Param(id=3) Uri arg3, @Param(id=4) float arg4, @Param(id=5) int arg5) {
        super();
        this.zzdq = Collections.unmodifiableList(arg1);
        this.zzdr = arg2;
        this.zzds = arg3;
        this.zzgk = arg4;
        this.zzgl = arg5;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeIntegerList(arg5, 1, this.zzdq, false);
        SafeParcelWriter.writeString(arg5, 2, this.zzdr, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzds, arg6, false);
        SafeParcelWriter.writeFloat(arg5, 4, this.zzgk);
        SafeParcelWriter.writeInt(arg5, 5, this.zzgl);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

