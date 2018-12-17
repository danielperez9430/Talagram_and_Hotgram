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

@Class(creator="GetCloudSyncSettingCreator") @Reserved(value={1}) public final class zzdv extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private final boolean enabled;
    @Field(id=2) private final int statusCode;

    static {
        zzdv.CREATOR = new zzdu();
    }

    @Constructor public zzdv(@Param(id=2) int arg1, @Param(id=3) boolean arg2) {
        super();
        this.statusCode = arg1;
        this.enabled = arg2;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeInt(arg3, 2, this.statusCode);
        SafeParcelWriter.writeBoolean(arg3, 3, this.enabled);
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

