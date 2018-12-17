package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzax implements Parcelable$Creator {
    public zzax() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        int v1 = 0;
        zzay v2 = null;
        int v3 = 0;
        int v4 = 0;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_14;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            v2_1 = SafeParcelReader.createParcelable(arg8, v5, zzay.CREATOR);
            continue;
        label_12:
            v4 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_16:
            v1 = SafeParcelReader.readInt(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzaw(((zzay)v2_1), v1, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new zzaw[arg1];
    }
}

