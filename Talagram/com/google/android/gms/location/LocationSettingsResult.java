package com.google.android.gms.location;

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

@Class(creator="LocationSettingsResultCreator") @Reserved(value={1000}) public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getStatus", id=1) private final Status zzbl;
    @Field(getter="getLocationSettingsStates", id=2) private final LocationSettingsStates zzbm;

    static {
        LocationSettingsResult.CREATOR = new zzah();
    }

    @Constructor public LocationSettingsResult(@Param(id=1) Status arg1, @Param(id=2) LocationSettingsStates arg2) {
        super();
        this.zzbl = arg1;
        this.zzbm = arg2;
    }

    public LocationSettingsResult(Status arg2) {
        this(arg2, null);
    }

    public final LocationSettingsStates getLocationSettingsStates() {
        return this.zzbm;
    }

    public final Status getStatus() {
        return this.zzbl;
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeParcelable(arg5, 1, this.getStatus(), arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 2, this.getLocationSettingsStates(), arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

