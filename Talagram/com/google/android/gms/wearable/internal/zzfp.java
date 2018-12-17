package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzfp implements Parcelable$Creator {
    public zzfp() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v2 = null;
        String v1 = v2;
        int v3 = 0;
        boolean v4 = false;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_19;
                }
                case 3: {
                    goto label_17;
                }
                case 4: {
                    goto label_15;
                }
                case 5: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_17:
            v1 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_19:
            v2 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_13:
            v4 = SafeParcelReader.readBoolean(arg8, v5);
            continue;
        label_15:
            v3 = SafeParcelReader.readInt(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzfo(v2, v1, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new zzfo[arg1];
    }
}

