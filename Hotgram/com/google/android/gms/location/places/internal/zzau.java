package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzau implements Parcelable$Creator {
    public zzau() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        String v4 = null;
        String v5 = v4;
        String v6 = v5;
        String v7 = v6;
        int v8 = 0;
        int v9;
        for(v9 = 0; arg11.dataPosition() < v0; v9 = SafeParcelReader.readInt(arg11, v1)) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_26;
                }
                case 2: {
                    goto label_24;
                }
                case 3: {
                    goto label_22;
                }
                case 4: {
                    goto label_20;
                }
                case 6: {
                    goto label_18;
                }
                case 7: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_18:
            v8 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_20:
            v7 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_22:
            v6 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_24:
            v5 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_26:
            v4 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzat(v4, v5, v6, v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new zzat[arg1];
    }
}

