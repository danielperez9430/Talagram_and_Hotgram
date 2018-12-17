package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzf implements Parcelable$Creator {
    public zzf() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        double v1 = 0;
        double v3 = v1;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_12;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_10:
            v3 = SafeParcelReader.readDouble(arg8, v5);
            continue;
        label_12:
            v1 = SafeParcelReader.readDouble(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new LatLng(v1, v3);
    }

    public final Object[] newArray(int arg1) {
        return new LatLng[arg1];
    }
}

