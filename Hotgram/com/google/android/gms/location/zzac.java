package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzac implements Parcelable$Creator {
    public zzac() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        ArrayList v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        List v1 = LocationResult.zzbb;
        while(arg6.dataPosition() < v0) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 1) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }

            v1_1 = SafeParcelReader.createTypedList(arg6, v2, Location.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new LocationResult(((List)v1_1));
    }

    public final Object[] newArray(int arg1) {
        return new LocationResult[arg1];
    }
}

