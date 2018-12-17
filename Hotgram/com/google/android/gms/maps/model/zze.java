package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        LatLng v1 = null;
        LatLng v2 = v1;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg6, v3, LatLng.CREATOR);
            continue;
        label_13:
            Parcelable v1_1 = SafeParcelReader.createParcelable(arg6, v3, LatLng.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new LatLngBounds(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new LatLngBounds[arg1];
    }
}

