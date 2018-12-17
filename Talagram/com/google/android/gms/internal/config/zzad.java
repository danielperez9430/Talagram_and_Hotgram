package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="FetchConfigIpcResponseCreator") @Reserved(value={1}) public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getStatusCode", id=2) private final int zzab;
    @Field(getter="getConfigsHolder", id=3) private final DataHolder zzac;
    @Field(getter="getExperimentPayloadsHolder", id=5) private final DataHolder zzad;
    @Field(getter="getThrottleEndTimeMillis", id=4) private final long zzr;

    static {
        zzad.CREATOR = new zzae();
    }

    @Constructor public zzad(@Param(id=2) int arg1, @Param(id=3) DataHolder arg2, @Param(id=4) long arg3, @Param(id=5) DataHolder arg5) {
        super();
        this.zzab = arg1;
        this.zzac = arg2;
        this.zzr = arg3;
        this.zzad = arg5;
    }

    public final int getStatusCode() {
        return this.zzab;
    }

    public final long getThrottleEndTimeMillis() {
        return this.zzr;
    }

    public final void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeInt(arg6, 2, this.zzab);
        SafeParcelWriter.writeParcelable(arg6, 3, this.zzac, arg7, false);
        SafeParcelWriter.writeLong(arg6, 4, this.zzr);
        SafeParcelWriter.writeParcelable(arg6, 5, this.zzad, arg7, false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }

    public final DataHolder zzi() {
        return this.zzac;
    }

    public final DataHolder zzj() {
        return this.zzad;
    }

    public final void zzk() {
        if(this.zzac != null && !this.zzac.isClosed()) {
            this.zzac.close();
        }
    }

    public final void zzl() {
        if(this.zzad != null && !this.zzad.isClosed()) {
            this.zzad.close();
        }
    }
}

