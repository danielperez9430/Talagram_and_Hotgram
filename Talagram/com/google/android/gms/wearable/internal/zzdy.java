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
import com.google.android.gms.wearable.ConnectionConfiguration;

@Class(creator="GetConfigsResponseCreator") @Reserved(value={1}) public final class zzdy extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final int statusCode;
    @Field(id=3) private final ConnectionConfiguration[] zzdw;

    static {
        zzdy.CREATOR = new zzdz();
    }

    @Constructor public zzdy(@Param(id=2) int arg1, @Param(id=3) ConnectionConfiguration[] arg2) {
        super();
        this.statusCode = arg1;
        this.zzdw = arg2;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.statusCode);
        SafeParcelWriter.writeTypedArray(arg5, 3, this.zzdw, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

