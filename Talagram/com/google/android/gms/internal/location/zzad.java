package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="FusedLocationProviderResultCreator") @Reserved(value={1000}) public final class zzad extends AbstractSafeParcelable implements Result {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getStatus", id=1) private final Status zzbl;
    private static final zzad zzcr;

    static {
        zzad.zzcr = new zzad(Status.RESULT_SUCCESS);
        zzad.CREATOR = new zzae();
    }

    @Constructor public zzad(@Param(id=1) Status arg1) {
        super();
        this.zzbl = arg1;
    }

    public final Status getStatus() {
        return this.zzbl;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.getStatus(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

