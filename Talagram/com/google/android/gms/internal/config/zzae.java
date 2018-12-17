package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzae implements Parcelable$Creator {
    public zzae() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        Parcelable v10_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        DataHolder v7 = null;
        DataHolder v10 = v7;
        long v8 = 0;
        int v6 = 0;
        while(arg12.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_25;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_19;
                }
                case 5: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_19:
            v8 = SafeParcelReader.readLong(arg12, v1);
            continue;
        label_21:
            Parcelable v7_1 = SafeParcelReader.createParcelable(arg12, v1, DataHolder.CREATOR);
            continue;
        label_25:
            v6 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_15:
            v10_1 = SafeParcelReader.createParcelable(arg12, v1, DataHolder.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new zzad(v6, v7, v8, ((DataHolder)v10_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzad[arg1];
    }
}

