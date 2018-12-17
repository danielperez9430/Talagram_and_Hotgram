package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="BusinessHoursIntervalCreator") @Reserved(value={1000}) public final class zzan extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=1) private final int zzgw;
    @Field(id=2) private final int zzgx;

    static {
        zzan.CREATOR = new zzf();
    }

    @Constructor zzan(@Param(id=1) int arg1, @Param(id=2) int arg2) {
        super();
        this.zzgw = arg1;
        this.zzgx = arg2;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 1, this.zzgw);
        SafeParcelWriter.writeInt(arg3, 2, this.zzgx);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

