package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg19) {
        Parcelable v7_1;
        Parcel v0 = arg19;
        int v1 = SafeParcelReader.validateObjectHeader(arg19);
        IBinder v6 = null;
        LatLng v7 = ((LatLng)v6);
        LatLngBounds v10 = ((LatLngBounds)v7);
        float v8 = 0f;
        float v9 = 0f;
        float v11 = 0f;
        float v12 = 0f;
        boolean v13 = false;
        float v14 = 0f;
        float v15 = 0f;
        float v16 = 0f;
        boolean v17 = false;
        while(arg19.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg19);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_50;
                }
                case 3: {
                    goto label_46;
                }
                case 4: {
                    goto label_44;
                }
                case 5: {
                    goto label_42;
                }
                case 6: {
                    goto label_38;
                }
                case 7: {
                    goto label_36;
                }
                case 8: {
                    goto label_34;
                }
                case 9: {
                    goto label_32;
                }
                case 10: {
                    goto label_30;
                }
                case 11: {
                    goto label_28;
                }
                case 12: {
                    goto label_26;
                }
                case 13: {
                    goto label_24;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v12 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_36:
            v11 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_38:
            Parcelable v10_1 = SafeParcelReader.createParcelable(v0, v2, LatLngBounds.CREATOR);
            continue;
        label_42:
            v9 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_44:
            v8 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_46:
            v7_1 = SafeParcelReader.createParcelable(v0, v2, LatLng.CREATOR);
            continue;
        label_50:
            v6 = SafeParcelReader.readIBinder(v0, v2);
            continue;
        label_24:
            v17 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_26:
            v16 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_28:
            v15 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_30:
            v14 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_32:
            v13 = SafeParcelReader.readBoolean(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new GroundOverlayOptions(v6, ((LatLng)v7_1), v8, v9, v10, v11, v12, v13, v14, v15, v16, v17);
    }

    public final Object[] newArray(int arg1) {
        return new GroundOverlayOptions[arg1];
    }
}

