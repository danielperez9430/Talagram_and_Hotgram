package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable$Creator {
    public zzh() {
        super();
    }

    public final Object createFromParcel(Parcel arg23) {
        Parcelable v8_1;
        Parcel v0 = arg23;
        int v1 = SafeParcelReader.validateObjectHeader(arg23);
        LatLng v8 = null;
        String v9 = ((String)v8);
        String v10 = v9;
        IBinder v11 = ((IBinder)v10);
        float v12 = 0f;
        float v13 = 0f;
        boolean v14 = false;
        boolean v15 = false;
        boolean v16 = false;
        float v17 = 0f;
        float v18 = 0.5f;
        float v19 = 0f;
        float v20 = 1f;
        float v21 = 0f;
        while(arg23.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg23);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_54;
                }
                case 3: {
                    goto label_52;
                }
                case 4: {
                    goto label_50;
                }
                case 5: {
                    goto label_48;
                }
                case 6: {
                    goto label_46;
                }
                case 7: {
                    goto label_44;
                }
                case 8: {
                    goto label_42;
                }
                case 9: {
                    goto label_40;
                }
                case 10: {
                    goto label_38;
                }
                case 11: {
                    goto label_36;
                }
                case 12: {
                    goto label_34;
                }
                case 13: {
                    goto label_32;
                }
                case 14: {
                    goto label_30;
                }
                case 15: {
                    goto label_28;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v18 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_36:
            v17 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_38:
            v16 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_40:
            v15 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_42:
            v14 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_44:
            v13 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_46:
            v12 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_48:
            v11 = SafeParcelReader.readIBinder(v0, v2);
            continue;
        label_50:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_52:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_54:
            v8_1 = SafeParcelReader.createParcelable(v0, v2, LatLng.CREATOR);
            continue;
        label_28:
            v21 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_30:
            v20 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_32:
            v19 = SafeParcelReader.readFloat(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new MarkerOptions(((LatLng)v8_1), v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21);
    }

    public final Object[] newArray(int arg1) {
        return new MarkerOptions[arg1];
    }
}

