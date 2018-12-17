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

@Class(creator="GetConfigResponseCreator") @Reserved(value={1}) @Deprecated public final class zzdw extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=2) private final int statusCode;
    @Field(id=3) private final ConnectionConfiguration zzdv;

    static {
        zzdw.CREATOR = new zzdx();
    }

    @Constructor public zzdw(@Param(id=2) int arg1, @Param(id=3) ConnectionConfiguration arg2) {
        super();
        this.statusCode = arg1;
        this.zzdv = arg2;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.statusCode);
        SafeParcelWriter.writeParcelable(arg5, 3, this.zzdv, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

