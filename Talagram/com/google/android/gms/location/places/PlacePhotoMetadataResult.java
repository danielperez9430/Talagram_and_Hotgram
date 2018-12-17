package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PlacePhotoMetadataResultCreator") @Reserved(value={1000}) public class PlacePhotoMetadataResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getStatus", id=1) private final Status zzdz;
    @Field(id=2) private final DataHolder zzep;
    private final PlacePhotoMetadataBuffer zzeq;

    static {
        PlacePhotoMetadataResult.CREATOR = new zzk();
    }

    @Constructor public PlacePhotoMetadataResult(@Param(id=1) Status arg1, @Param(id=2) DataHolder arg2) {
        super();
        this.zzdz = arg1;
        this.zzep = arg2;
        PlacePhotoMetadataBuffer v1 = arg2 == null ? null : new PlacePhotoMetadataBuffer(this.zzep);
        this.zzeq = v1;
    }

    public PlacePhotoMetadataBuffer getPhotoMetadata() {
        return this.zzeq;
    }

    public Status getStatus() {
        return this.zzdz;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.getStatus(), arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzep, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

