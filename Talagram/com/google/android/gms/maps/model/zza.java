package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zza implements Parcelable$Creator {
    public zza() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        float v1 = 0f;
        LatLng v2 = null;
        float v3 = 0f;
        float v4 = 0f;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_14;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg8, v5, LatLng.CREATOR);
            continue;
        label_12:
            v4 = SafeParcelReader.readFloat(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.readFloat(arg8, v5);
            continue;
        label_16:
            v1 = SafeParcelReader.readFloat(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new CameraPosition(v2, v1, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new CameraPosition[arg1];
    }
}

