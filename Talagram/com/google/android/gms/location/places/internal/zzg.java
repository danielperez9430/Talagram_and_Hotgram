package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzg implements Parcelable$Creator {
    public zzg() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        List v10 = null;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;
        while(arg12.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_30;
                }
                case 2: {
                    goto label_28;
                }
                case 3: {
                    goto label_26;
                }
                case 4: {
                    goto label_24;
                }
                case 5: {
                    goto label_22;
                }
                case 6: {
                    goto label_20;
                }
                case 7: {
                    goto label_17;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_17:
            ArrayList v10_1 = SafeParcelReader.createTypedList(arg12, v1, zzan.CREATOR);
            continue;
        label_20:
            v9 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_22:
            v8 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_24:
            v7 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_26:
            v6 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_28:
            v5 = SafeParcelReader.readInt(arg12, v1);
            continue;
        label_30:
            v4 = SafeParcelReader.readInt(arg12, v1);
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new zzao(v4, v5, v6, v7, v8, v9, v10);
    }

    public final Object[] newArray(int arg1) {
        return new zzao[arg1];
    }
}

