package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzak implements Parcelable$Creator {
    public zzak() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        long v7 = -1;
        long v9 = v7;
        int v5 = 1;
        int v6 = 1;
        while(arg12.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_20;
                }
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_14;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_18:
            v6 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_20:
            v5 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_14:
            v9 = SafeParcelReader.readLong(arg12, v1);
            continue;
        label_16:
            v7 = SafeParcelReader.readLong(arg12, v1);
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new zzaj(v5, v6, v7, v9);
    }

    public final Object[] newArray(int arg1) {
        return new zzaj[arg1];
    }
}

