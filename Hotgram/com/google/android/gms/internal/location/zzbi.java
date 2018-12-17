package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzbi implements Parcelable$Creator {
    public zzbi() {
        super();
    }

    public final Object createFromParcel(Parcel arg24) {
        Parcel v0 = arg24;
        int v1 = SafeParcelReader.validateObjectHeader(arg24);
        double v14 = 0;
        double v16 = v14;
        String v11 = null;
        long v19 = 0;
        int v12 = 0;
        short v13 = 0;
        float v18 = 0f;
        int v21 = 0;
        int v22 = -1;
        while(arg24.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg24);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_40;
                }
                case 2: {
                    goto label_38;
                }
                case 3: {
                    goto label_36;
                }
                case 4: {
                    goto label_34;
                }
                case 5: {
                    goto label_32;
                }
                case 6: {
                    goto label_30;
                }
                case 7: {
                    goto label_28;
                }
                case 8: {
                    goto label_26;
                }
                case 9: {
                    goto label_24;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v14 = SafeParcelReader.readDouble(v0, v2);
            continue;
        label_36:
            v13 = SafeParcelReader.readShort(v0, v2);
            continue;
        label_38:
            v19 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_40:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_24:
            v22 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_26:
            v21 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_28:
            v12 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_30:
            v18 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_32:
            v16 = SafeParcelReader.readDouble(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzbh(v11, v12, v13, v14, v16, v18, v19, v21, v22);
    }

    public final Object[] newArray(int arg1) {
        return new zzbh[arg1];
    }
}

