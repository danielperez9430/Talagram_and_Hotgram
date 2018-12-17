package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzv implements Parcelable$Creator {
    public zzv() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        Parcelable v7_1;
        Parcelable v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        LatLng v3 = null;
        LatLng v4 = v3;
        LatLng v5 = v4;
        LatLng v6 = v5;
        LatLngBounds v7 = ((LatLngBounds)v6);
        while(arg9.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg9);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_30;
                }
                case 3: {
                    goto label_26;
                }
                case 4: {
                    goto label_22;
                }
                case 5: {
                    goto label_18;
                }
                case 6: {
                    goto label_14;
                }
            }

            SafeParcelReader.skipUnknownField(arg9, v1);
            continue;
        label_18:
            Parcelable v6_1 = SafeParcelReader.createParcelable(arg9, v1, LatLng.CREATOR);
            continue;
        label_22:
            Parcelable v5_1 = SafeParcelReader.createParcelable(arg9, v1, LatLng.CREATOR);
            continue;
        label_26:
            Parcelable v4_1 = SafeParcelReader.createParcelable(arg9, v1, LatLng.CREATOR);
            continue;
        label_30:
            v3_1 = SafeParcelReader.createParcelable(arg9, v1, LatLng.CREATOR);
            continue;
        label_14:
            v7_1 = SafeParcelReader.createParcelable(arg9, v1, LatLngBounds.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new VisibleRegion(((LatLng)v3_1), v4, v5, v6, ((LatLngBounds)v7_1));
    }

    public final Object[] newArray(int arg1) {
        return new VisibleRegion[arg1];
    }
}

