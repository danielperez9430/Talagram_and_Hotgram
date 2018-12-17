package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzai implements Parcelable$Creator {
    public zzai() {
        super();
    }

    public final Object createFromParcel(Parcel arg15) {
        Parcelable v13_1;
        Parcelable v6_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg15);
        StreetViewPanoramaCamera v4 = null;
        String v5 = ((String)v4);
        LatLng v6 = ((LatLng)v5);
        Integer v7 = ((Integer)v6);
        StreetViewSource v13 = ((StreetViewSource)v7);
        byte v8 = 0;
        byte v9 = 0;
        byte v10 = 0;
        byte v11 = 0;
        byte v12 = 0;
        while(arg15.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg15);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_42;
                }
                case 3: {
                    goto label_40;
                }
                case 4: {
                    goto label_36;
                }
                case 5: {
                    goto label_34;
                }
                case 6: {
                    goto label_32;
                }
                case 7: {
                    goto label_30;
                }
                case 8: {
                    goto label_28;
                }
                case 9: {
                    goto label_26;
                }
                case 10: {
                    goto label_24;
                }
                case 11: {
                    goto label_20;
                }
            }

            SafeParcelReader.skipUnknownField(arg15, v1);
            continue;
        label_34:
            v7 = SafeParcelReader.readIntegerObject(arg15, v1);
            continue;
        label_36:
            v6_1 = SafeParcelReader.createParcelable(arg15, v1, LatLng.CREATOR);
            continue;
        label_20:
            v13_1 = SafeParcelReader.createParcelable(arg15, v1, StreetViewSource.CREATOR);
            continue;
        label_40:
            v5 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_24:
            v12 = SafeParcelReader.readByte(arg15, v1);
            continue;
        label_42:
            Parcelable v4_1 = SafeParcelReader.createParcelable(arg15, v1, StreetViewPanoramaCamera.CREATOR);
            continue;
        label_26:
            v11 = SafeParcelReader.readByte(arg15, v1);
            continue;
        label_28:
            v10 = SafeParcelReader.readByte(arg15, v1);
            continue;
        label_30:
            v9 = SafeParcelReader.readByte(arg15, v1);
            continue;
        label_32:
            v8 = SafeParcelReader.readByte(arg15, v1);
        }

        SafeParcelReader.ensureAtEnd(arg15, v0);
        return new StreetViewPanoramaOptions(v4, v5, ((LatLng)v6_1), v7, v8, v9, v10, v11, v12, ((StreetViewSource)v13_1));
    }

    public final Object[] newArray(int arg1) {
        return new StreetViewPanoramaOptions[arg1];
    }
}

