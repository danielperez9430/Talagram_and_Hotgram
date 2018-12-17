package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzgf implements Parcelable$Creator {
    public zzgf() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        ArrayList v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        int v1 = 0;
        long v2 = 0;
        List v4 = null;
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
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_11:
            v4_1 = SafeParcelReader.createTypedList(arg8, v5, zzfs.CREATOR);
            continue;
        label_14:
            v2 = SafeParcelReader.readLong(arg8, v5);
            continue;
        label_16:
            v1 = SafeParcelReader.readInt(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzge(v1, v2, ((List)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzge[arg1];
    }
}

