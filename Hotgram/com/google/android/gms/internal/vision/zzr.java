package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="BoundingBoxParcelCreator") @Reserved(value={1}) public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=5) public final int height;
    @Field(id=2) public final int left;
    @Field(id=3) public final int top;
    @Field(id=4) public final int width;
    @Field(id=6) public final float zzdh;

    static {
        zzr.CREATOR = new zzs();
    }

    @Constructor public zzr(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) int arg4, @Param(id=6) float arg5) {
        super();
        this.left = arg1;
        this.top = arg2;
        this.width = arg3;
        this.height = arg4;
        this.zzdh = arg5;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 2, this.left);
        SafeParcelWriter.writeInt(arg3, 3, this.top);
        SafeParcelWriter.writeInt(arg3, 4, this.width);
        SafeParcelWriter.writeInt(arg3, 5, this.height);
        SafeParcelWriter.writeFloat(arg3, 6, this.zzdh);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

