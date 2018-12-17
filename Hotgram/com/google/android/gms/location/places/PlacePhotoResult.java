package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PlacePhotoResultCreator") @Reserved(value={1000}) public class PlacePhotoResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getStatus", id=1) private final Status zzdz;
    @Field(id=2) private final BitmapTeleporter zzer;
    private final Bitmap zzes;

    static {
        PlacePhotoResult.CREATOR = new zzl();
    }

    @Constructor public PlacePhotoResult(@Param(id=1) Status arg1, @Param(id=2) BitmapTeleporter arg2) {
        super();
        this.zzdz = arg1;
        this.zzer = arg2;
        Bitmap v1 = this.zzer != null ? arg2.get() : null;
        this.zzes = v1;
    }

    public Bitmap getBitmap() {
        return this.zzes;
    }

    public Status getStatus() {
        return this.zzdz;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzdz).add("bitmap", this.zzes).toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.getStatus(), arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zzer, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

