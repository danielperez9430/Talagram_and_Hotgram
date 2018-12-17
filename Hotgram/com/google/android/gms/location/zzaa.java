package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzaa implements Parcelable$Creator {
    public zzaa() {
        super();
    }

    public final Object createFromParcel(Parcel arg14) {
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        long v10 = 0;
        zzaj[] v12 = null;
        int v7 = 1000;
        int v8 = 1;
        int v9 = 1;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
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
                    goto label_17;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_17:
            Object[] v12_1 = SafeParcelReader.createTypedArray(arg14, v1, zzaj.CREATOR);
            continue;
        label_21:
            v7 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_23:
            v10 = SafeParcelReader.readLong(arg14, v1);
            continue;
        label_25:
            v9 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_27:
            v8 = SafeParcelReader.readInt(arg14, v1);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new LocationAvailability(v7, v8, v9, v10, v12);
    }

    public final Object[] newArray(int arg1) {
        return new LocationAvailability[arg1];
    }
}

