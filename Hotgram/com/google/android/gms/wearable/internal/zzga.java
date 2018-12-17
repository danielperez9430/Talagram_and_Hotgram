package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="SendMessageResponseCreator") @Reserved(value={1}) public final class zzga extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final int statusCode;
    @Field(id=3) public final int zzeh;

    static {
        zzga.CREATOR = new zzgb();
    }

    @Constructor public zzga(@Param(id=2) int arg1, @Param(id=3) int arg2) {
        super();
        this.statusCode = arg1;
        this.zzeh = arg2;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 2, this.statusCode);
        SafeParcelWriter.writeInt(arg3, 3, this.zzeh);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

