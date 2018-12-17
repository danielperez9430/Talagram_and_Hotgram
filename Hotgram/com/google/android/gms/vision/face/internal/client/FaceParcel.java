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

@UsedByNative(value="wrapper.cc") @Class(creator="FaceParcelCreator") public class FaceParcel extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) public final float centerX;
    @Field(id=4) public final float centerY;
    @Field(id=6) public final float height;
    @Field(id=2) public final int id;
    @VersionField(id=1) private final int versionCode;
    @Field(id=5) public final float width;
    @Field(id=10) public final float zzbs;
    @Field(id=11) public final float zzbt;
    @Field(id=12) public final float zzbu;
    @Field(id=7) public final float zzcf;
    @Field(id=8) public final float zzcg;
    @Field(id=9) public final LandmarkParcel[] zzch;

    static {
        FaceParcel.CREATOR = new zzb();
    }

    @Constructor public FaceParcel(@Param(id=1) int arg1, @Param(id=2) int arg2, @Param(id=3) float arg3, @Param(id=4) float arg4, @Param(id=5) float arg5, @Param(id=6) float arg6, @Param(id=7) float arg7, @Param(id=8) float arg8, @Param(id=9) LandmarkParcel[] arg9, @Param(id=10) float arg10, @Param(id=11) float arg11, @Param(id=12) float arg12) {
        super();
        this.versionCode = arg1;
        this.id = arg2;
        this.centerX = arg3;
        this.centerY = arg4;
        this.width = arg5;
        this.height = arg6;
        this.zzcf = arg7;
        this.zzcg = arg8;
        this.zzch = arg9;
        this.zzbs = arg10;
        this.zzbt = arg11;
        this.zzbu = arg12;
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.versionCode);
        SafeParcelWriter.writeInt(arg5, 2, this.id);
        SafeParcelWriter.writeFloat(arg5, 3, this.centerX);
        SafeParcelWriter.writeFloat(arg5, 4, this.centerY);
        SafeParcelWriter.writeFloat(arg5, 5, this.width);
        SafeParcelWriter.writeFloat(arg5, 6, this.height);
        SafeParcelWriter.writeFloat(arg5, 7, this.zzcf);
        SafeParcelWriter.writeFloat(arg5, 8, this.zzcg);
        SafeParcelWriter.writeTypedArray(arg5, 9, this.zzch, arg6, false);
        SafeParcelWriter.writeFloat(arg5, 10, this.zzbs);
        SafeParcelWriter.writeFloat(arg5, 11, this.zzbt);
        SafeParcelWriter.writeFloat(arg5, 12, this.zzbu);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

