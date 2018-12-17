package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@Class(creator="LocationSettingsStatesCreator") @Reserved(value={1000}) public final class LocationSettingsStates extends AbstractSafeParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="isGpsUsable", id=1) private final boolean zzbn;
    @Field(getter="isNetworkLocationUsable", id=2) private final boolean zzbo;
    @Field(getter="isBleUsable", id=3) private final boolean zzbp;
    @Field(getter="isGpsPresent", id=4) private final boolean zzbq;
    @Field(getter="isNetworkLocationPresent", id=5) private final boolean zzbr;
    @Field(getter="isBlePresent", id=6) private final boolean zzbs;

    static {
        LocationSettingsStates.CREATOR = new zzai();
    }

    @Constructor public LocationSettingsStates(@Param(id=1) boolean arg1, @Param(id=2) boolean arg2, @Param(id=3) boolean arg3, @Param(id=4) boolean arg4, @Param(id=5) boolean arg5, @Param(id=6) boolean arg6) {
        super();
        this.zzbn = arg1;
        this.zzbo = arg2;
        this.zzbp = arg3;
        this.zzbq = arg4;
        this.zzbr = arg5;
        this.zzbs = arg6;
    }

    public static LocationSettingsStates fromIntent(Intent arg2) {
        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", LocationSettingsStates.CREATOR);
    }

    public final boolean isBlePresent() {
        return this.zzbs;
    }

    public final boolean isBleUsable() {
        return this.zzbp;
    }

    public final boolean isGpsPresent() {
        return this.zzbq;
    }

    public final boolean isGpsUsable() {
        return this.zzbn;
    }

    public final boolean isLocationPresent() {
        if(!this.zzbq) {
            if(this.zzbr) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final boolean isLocationUsable() {
        if(!this.zzbn) {
            if(this.zzbo) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public final boolean isNetworkLocationPresent() {
        return this.zzbr;
    }

    public final boolean isNetworkLocationUsable() {
        return this.zzbo;
    }

    public final void writeToParcel(Parcel arg3, int arg4) {
        arg4 = SafeParcelWriter.beginObjectHeader(arg3);
        SafeParcelWriter.writeBoolean(arg3, 1, this.isGpsUsable());
        SafeParcelWriter.writeBoolean(arg3, 2, this.isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(arg3, 3, this.isBleUsable());
        SafeParcelWriter.writeBoolean(arg3, 4, this.isGpsPresent());
        SafeParcelWriter.writeBoolean(arg3, 5, this.isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(arg3, 6, this.isBlePresent());
        SafeParcelWriter.finishObjectHeader(arg3, arg4);
    }
}

