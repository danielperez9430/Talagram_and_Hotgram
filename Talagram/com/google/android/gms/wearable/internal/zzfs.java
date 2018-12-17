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

@Class(creator="PackageStorageInfoCreator") @Reserved(value={1}) public final class zzfs extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private final String label;
    @Field(id=2) private final String packageName;
    @Field(id=4) private final long zzep;

    static {
        zzfs.CREATOR = new zzft();
    }

    @Constructor public zzfs(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) long arg3) {
        super();
        this.packageName = arg1;
        this.label = arg2;
        this.zzep = arg3;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.packageName, false);
        SafeParcelWriter.writeString(arg4, 3, this.label, false);
        SafeParcelWriter.writeLong(arg4, 4, this.zzep);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

