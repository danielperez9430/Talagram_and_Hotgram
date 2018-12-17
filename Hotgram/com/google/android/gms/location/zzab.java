package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzab implements Parcelable$Creator {
    public zzab() {
        super();
    }

    public final Object createFromParcel(Parcel arg28) {
        Parcel v0 = arg28;
        int v1 = SafeParcelReader.validateObjectHeader(arg28);
        long v16 = 3600000;
        long v18 = 600000;
        long v21 = 9223372036854775807L;
        long v25 = 0;
        int v15 = 102;
        boolean v20 = false;
        int v23 = 2147483647;
        float v24 = 0f;
        while(arg28.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg28);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_39;
                }
                case 2: {
                    goto label_37;
                }
                case 3: {
                    goto label_35;
                }
                case 4: {
                    goto label_33;
                }
                case 5: {
                    goto label_31;
                }
                case 6: {
                    goto label_29;
                }
                case 7: {
                    goto label_27;
                }
                case 8: {
                    goto label_25;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v20 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_35:
            v18 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_37:
            v16 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_39:
            v15 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_25:
            v25 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_27:
            v24 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_29:
            v23 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_31:
            v21 = SafeParcelReader.readLong(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new LocationRequest(v15, v16, v18, v20, v21, v23, v24, v25);
    }

    public final Object[] newArray(int arg1) {
        return new LocationRequest[arg1];
    }
}

