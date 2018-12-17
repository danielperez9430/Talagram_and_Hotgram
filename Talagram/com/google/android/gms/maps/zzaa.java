package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzaa implements Parcelable$Creator {
    public zzaa() {
        super();
    }

    public final Object createFromParcel(Parcel arg23) {
        Parcel v0 = arg23;
        int v1 = SafeParcelReader.validateObjectHeader(arg23);
        CameraPosition v9 = null;
        Float v19 = ((Float)v9);
        Float v20 = v19;
        LatLngBounds v21 = ((LatLngBounds)v20);
        byte v6 = -1;
        byte v7 = -1;
        int v8 = 0;
        byte v10 = -1;
        byte v11 = -1;
        byte v12 = -1;
        byte v13 = -1;
        byte v14 = -1;
        byte v15 = -1;
        byte v16 = -1;
        byte v17 = -1;
        byte v18 = -1;
        while(arg23.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg23);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_62;
                }
                case 3: {
                    goto label_60;
                }
                case 4: {
                    goto label_58;
                }
                case 5: {
                    goto label_54;
                }
                case 6: {
                    goto label_52;
                }
                case 7: {
                    goto label_50;
                }
                case 8: {
                    goto label_48;
                }
                case 9: {
                    goto label_46;
                }
                case 10: {
                    goto label_44;
                }
                case 11: {
                    goto label_42;
                }
                case 12: {
                    goto label_40;
                }
                case 14: {
                    goto label_38;
                }
                case 15: {
                    goto label_36;
                }
                case 16: {
                    goto label_34;
                }
                case 17: {
                    goto label_32;
                }
                case 18: {
                    goto label_28;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v19 = SafeParcelReader.readFloatObject(v0, v2);
            continue;
        label_36:
            v18 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_38:
            v17 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_40:
            v16 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_42:
            v15 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_44:
            v14 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_46:
            v13 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_48:
            v12 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_50:
            v11 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_52:
            v10 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_54:
            Parcelable v9_1 = SafeParcelReader.createParcelable(v0, v2, CameraPosition.CREATOR);
            continue;
        label_58:
            v8 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_60:
            v7 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_28:
            Parcelable v21_1 = SafeParcelReader.createParcelable(v0, v2, LatLngBounds.CREATOR);
            continue;
        label_62:
            v6 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_32:
            v20 = SafeParcelReader.readFloatObject(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new GoogleMapOptions(v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21);
    }

    public final Object[] newArray(int arg1) {
        return new GoogleMapOptions[arg1];
    }
}

