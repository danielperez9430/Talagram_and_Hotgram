package com.google.android.gms.internal.config;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="PackageConfigTableCreator") @Reserved(value={1}) public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getConfigTable", id=2) private final Bundle zzae;

    static {
        zzaj.CREATOR = new zzak();
    }

    @Constructor public zzaj(@Param(id=2) Bundle arg1) {
        super();
        this.zzae = arg1;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeBundle(arg4, 2, this.zzae, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }

    public final Bundle zzm() {
        return this.zzae;
    }
}

