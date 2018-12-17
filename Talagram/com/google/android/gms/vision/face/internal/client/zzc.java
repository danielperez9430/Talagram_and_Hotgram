package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="FaceSettingsParcelCreator") @Reserved(value={1}) public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public int mode;
    @Field(id=3) public int zzby;
    @Field(id=5) public boolean zzbz;
    @Field(id=4) public int zzca;
    @Field(id=6) public boolean zzcb;
    @Field(defaultValue="-1", id=7) public float zzcc;

    static {
        zzc.CREATOR = new zzd();
    }

    @Constructor public zzc(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) int arg3, @Param(id=5) boolean arg4, @Param(id=6) boolean arg5, @Param(id=7) float arg6) {
        super();
        this.mode = arg1;
        this.zzby = arg2;
        this.zzca = arg3;
        this.zzbz = arg4;
        this.zzcb = arg5;
        this.zzcc = arg6;
    }

    public zzc() {
        super();
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 2, this.mode);
        SafeParcelWriter.writeInt(arg3, 3, this.zzby);
        SafeParcelWriter.writeInt(arg3, 4, this.zzca);
        SafeParcelWriter.writeBoolean(arg3, 5, this.zzbz);
        SafeParcelWriter.writeBoolean(arg3, 6, this.zzcb);
        SafeParcelWriter.writeFloat(arg3, 7, this.zzcc);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

