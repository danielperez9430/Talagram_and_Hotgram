package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.apps.common.proguard.UsedByNative;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;

@UsedByNative(value="wrapper.cc") @Class(creator="LandmarkParcelCreator") public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=4) public final int type;
    @VersionField(id=1) private final int versionCode;
    @Field(id=2) public final float x;
    @Field(id=3) public final float y;

    static {
        LandmarkParcel.CREATOR = new zzi();
    }

    @Constructor public LandmarkParcel(@Param(id=1) int arg1, @Param(id=2) float arg2, @Param(id=3) float arg3, @Param(id=4) int arg4) {
        super();
        this.versionCode = arg1;
        this.x = arg2;
        this.y = arg3;
        this.type = arg4;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.versionCode);
        SafeParcelWriter.writeFloat(arg3, 2, this.x);
        SafeParcelWriter.writeFloat(arg3, 3, this.y);
        SafeParcelWriter.writeInt(arg3, 4, this.type);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

