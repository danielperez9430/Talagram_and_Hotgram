package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzft implements Parcelable$Creator {
    public zzft() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        long v3 = 0;
        String v2 = v1;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_16;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_12:
            v3 = SafeParcelReader.readLong(arg8, v5);
            continue;
        label_14:
            v2 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_16:
            v1 = SafeParcelReader.createString(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzfs(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzfs[arg1];
    }
}

