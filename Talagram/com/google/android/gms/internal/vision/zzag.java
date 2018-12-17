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

@Class(creator="WordBoxParcelCreator") @Reserved(value={1}) public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=6) private final float zzco;
    @Field(id=7) public final String zzdd;
    @Field(id=3) public final zzr zzdj;
    @Field(id=4) private final zzr zzdk;
    @Field(id=5) public final String zzdm;
    @Field(id=2) private final zzab[] zzds;
    @Field(id=8) private final boolean zzdt;

    static {
        zzag.CREATOR = new zzah();
    }

    @Constructor public zzag(@Param(id=2) zzab[] arg1, @Param(id=3) zzr arg2, @Param(id=4) zzr arg3, @Param(id=5) String arg4, @Param(id=6) float arg5, @Param(id=7) String arg6, @Param(id=8) boolean arg7) {
        super();
        this.zzds = arg1;
        this.zzdj = arg2;
        this.zzdk = arg3;
        this.zzdm = arg4;
        this.zzco = arg5;
        this.zzdd = arg6;
        this.zzdt = arg7;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeTypedArray(arg5, 2, this.zzds, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdj, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzdk, arg6, false);
        SafeParcelWriter.writeString(arg5, 5, this.zzdm, false);
        SafeParcelWriter.writeFloat(arg5, 6, this.zzco);
        SafeParcelWriter.writeString(arg5, 7, this.zzdd, false);
        SafeParcelWriter.writeBoolean(arg5, 8, this.zzdt);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

