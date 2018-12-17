package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Parcelable$Creator {
    public zzk() {
        super();
    }

    public final Object createFromParcel(Parcel arg18) {
        Parcel v0 = arg18;
        int v1 = SafeParcelReader.validateObjectHeader(arg18);
        long v11 = 50;
        long v14 = 9223372036854775807L;
        boolean v10 = true;
        float v13 = 0f;
        int v16 = 2147483647;
        while(arg18.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg18);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_27;
                }
                case 2: {
                    goto label_25;
                }
                case 3: {
                    goto label_23;
                }
                case 4: {
                    goto label_21;
                }
                case 5: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_19:
            v16 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_21:
            v14 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_23:
            v13 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_25:
            v11 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_27:
            v10 = SafeParcelReader.readBoolean(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzj(v10, v11, v13, v14, v16);
    }

    public final Object[] newArray(int arg1) {
        return new zzj[arg1];
    }
}

