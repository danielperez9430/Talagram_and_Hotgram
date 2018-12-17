package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="ConfigExperimentPayloadCreator") @Reserved(value={1}) public final class zzx extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getPayload", id=2) private final byte[] zzt;

    static {
        zzx.CREATOR = new zzy();
    }

    @Constructor public zzx(@Param(id=2) byte[] arg1) {
        super();
        this.zzt = arg1;
    }

    public final byte[] getPayload() {
        return this.zzt;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeByteArray(arg4, 2, this.zzt, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

