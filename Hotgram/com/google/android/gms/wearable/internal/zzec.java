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

@Class(creator="GetDataItemResponseCreator") @Reserved(value={1}) public final class zzec extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) public final int statusCode;
    @Field(id=3) public final zzdd zzdy;

    static {
        zzec.CREATOR = new zzed();
    }

    @Constructor public zzec(@Param(id=2) int arg1, @Param(id=3) zzdd arg2) {
        super();
        this.statusCode = arg1;
        this.zzdy = arg2;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.statusCode);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdy, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

