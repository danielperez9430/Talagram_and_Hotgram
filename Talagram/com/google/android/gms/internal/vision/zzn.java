package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzn implements Parcelable$Creator {
    public zzn() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        long v8 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        int v10;
        for(v10 = 0; arg12.dataPosition() < v0; v10 = SafeParcelReader.readInt(arg12, v1)) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_19;
                }
                case 5: {
                    goto label_17;
                }
                case 6: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_17:
            v8 = SafeParcelReader.readLong(arg12, v1);
            continue;
        label_19:
            v7 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_21:
            v6 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_23:
            v5 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new zzm(v5, v6, v7, v8, v10);
    }

    public final Object[] newArray(int arg1) {
        return new zzm[arg1];
    }
}

