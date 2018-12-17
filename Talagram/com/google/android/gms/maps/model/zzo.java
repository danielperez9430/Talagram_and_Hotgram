package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzo implements Parcelable$Creator {
    public zzo() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        StreetViewPanoramaLink[] v1 = null;
        LatLng v2 = ((LatLng)v1);
        String v3 = ((String)v2);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_16;
                }
                case 3: {
                    goto label_13;
                }
                case 4: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_13:
            v2_1 = SafeParcelReader.createParcelable(arg7, v4, LatLng.CREATOR);
            continue;
        label_16:
            Object[] v1_1 = SafeParcelReader.createTypedArray(arg7, v4, StreetViewPanoramaLink.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new StreetViewPanoramaLocation(v1, ((LatLng)v2_1), v3);
    }

    public final Object[] newArray(int arg1) {
        return new StreetViewPanoramaLocation[arg1];
    }
}

