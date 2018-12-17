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

@Class(creator="LineBoxParcelCreator") @Reserved(value={1}) public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=7) private final float zzco;
    @Field(id=8) public final String zzdd;
    @Field(id=2) public final zzag[] zzdi;
    @Field(id=3) public final zzr zzdj;
    @Field(id=4) private final zzr zzdk;
    @Field(id=5) private final zzr zzdl;
    @Field(id=6) public final String zzdm;
    @Field(id=9) private final int zzdn;
    @Field(id=10) public final boolean zzdo;
    @Field(id=11) public final int zzdp;
    @Field(id=12) public final int zzdq;

    static {
        zzx.CREATOR = new zzy();
    }

    @Constructor public zzx(@Param(id=2) zzag[] arg1, @Param(id=3) zzr arg2, @Param(id=4) zzr arg3, @Param(id=5) zzr arg4, @Param(id=6) String arg5, @Param(id=7) float arg6, @Param(id=8) String arg7, @Param(id=9) int arg8, @Param(id=10) boolean arg9, @Param(id=11) int arg10, @Param(id=12) int arg11) {
        super();
        this.zzdi = arg1;
        this.zzdj = arg2;
        this.zzdk = arg3;
        this.zzdl = arg4;
        this.zzdm = arg5;
        this.zzco = arg6;
        this.zzdd = arg7;
        this.zzdn = arg8;
        this.zzdo = arg9;
        this.zzdp = arg10;
        this.zzdq = arg11;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedArray(arg5, 2, this.zzdi, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdj, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzdk, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzdl, arg6, false);
        SafeParcelWriter.writeString(arg5, 6, this.zzdm, false);
        SafeParcelWriter.writeFloat(arg5, 7, this.zzco);
        SafeParcelWriter.writeString(arg5, 8, this.zzdd, false);
        SafeParcelWriter.writeInt(arg5, 9, this.zzdn);
        SafeParcelWriter.writeBoolean(arg5, 10, this.zzdo);
        SafeParcelWriter.writeInt(arg5, 11, this.zzdp);
        SafeParcelWriter.writeInt(arg5, 12, this.zzdq);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

