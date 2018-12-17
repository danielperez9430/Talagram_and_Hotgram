package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Class(creator="LocationResultCreator") @Reserved(value={1000}) public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    static final List zzbb;
    @Field(defaultValueUnchecked="LocationResult.DEFAULT_LOCATIONS", getter="getLocations", id=1) private final List zzbc;

    static {
        LocationResult.zzbb = Collections.emptyList();
        LocationResult.CREATOR = new zzac();
    }

    @Constructor LocationResult(@Param(id=1) List arg1) {
        super();
        this.zzbc = arg1;
    }

    public static LocationResult create(List arg1) {
        if(arg1 == null) {
            arg1 = LocationResult.zzbb;
        }

        return new LocationResult(arg1);
    }

    public final boolean equals(Object arg8) {
        if(!(arg8 instanceof LocationResult)) {
            return 0;
        }

        if(((LocationResult)arg8).zzbc.size() != this.zzbc.size()) {
            return 0;
        }

        Iterator v8 = ((LocationResult)arg8).zzbc.iterator();
        Iterator v0 = this.zzbc.iterator();
        do {
            if(!v8.hasNext()) {
                return 1;
            }
        }
        while(v0.next().getTime() == v8.next().getTime());

        return 0;
    }

    public static LocationResult extractResult(Intent arg1) {
        if(!LocationResult.hasResult(arg1)) {
            return null;
        }

        return arg1.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public final Location getLastLocation() {
        int v0 = this.zzbc.size();
        if(v0 == 0) {
            return null;
        }

        return this.zzbc.get(v0 - 1);
    }

    public final List getLocations() {
        return this.zzbc;
    }

    public static boolean hasResult(Intent arg1) {
        if(arg1 == null) {
            return 0;
        }

        return arg1.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public final int hashCode() {
        Iterator v0 = this.zzbc.iterator();
        int v1;
        for(v1 = 17; v0.hasNext(); v1 = v1 * 31 + (((int)(v2 ^ v2 >>> 32)))) {
            long v2 = v0.next().getTime();
        }

        return v1;
    }

    public final String toString() {
        String v0 = String.valueOf(this.zzbc);
        StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 27);
        v2.append("LocationResult[locations: ");
        v2.append(v0);
        v2.append("]");
        return v2.toString();
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeTypedList(arg4, 1, this.getLocations(), false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

