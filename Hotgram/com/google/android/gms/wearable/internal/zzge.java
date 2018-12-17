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
import java.util.List;

@Class(creator="StorageInfoResponseCreator") @Reserved(value={1}) public final class zzge extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final int statusCode;
    @Field(id=3) private final long zzep;
    @Field(id=4) private final List zzer;

    static {
        zzge.CREATOR = new zzgf();
    }

    @Constructor public zzge(@Param(id=2) int arg1, @Param(id=3) long arg2, @Param(id=4) List arg4) {
        super();
        this.statusCode = arg1;
        this.zzep = arg2;
        this.zzer = arg4;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeInt(arg4, 2, this.statusCode);
        SafeParcelWriter.writeLong(arg4, 3, this.zzep);
        SafeParcelWriter.writeTypedList(arg4, 4, this.zzer, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

