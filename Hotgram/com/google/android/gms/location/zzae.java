package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="LocationSettingsConfigurationCreator") @Reserved(value={3, 4, 1000}) public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(defaultValue="", getter="getJustificationText", id=1) private final String zzbd;
    @Field(defaultValue="", getter="getExperimentId", id=2) private final String zzbe;
    @Field(defaultValue="", getter="getTitleText", id=5) private final String zzbf;

    static {
        zzae.CREATOR = new zzaf();
    }

    @Constructor zzae(@Param(id=5) String arg1, @Param(id=1) String arg2, @Param(id=2) String arg3) {
        super();
        this.zzbf = arg1;
        this.zzbd = arg2;
        this.zzbe = arg3;
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 1, this.zzbd, false);
        SafeParcelWriter.writeString(arg4, 2, this.zzbe, false);
        SafeParcelWriter.writeString(arg4, 5, this.zzbf, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

