package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        boolean v1 = false;
        ArrayList v2 = null;
        boolean v3 = true;
        int v4 = 0;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_18;
                }
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
        label_18:
            v2 = SafeParcelReader.createIntegerList(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_14:
            v1 = SafeParcelReader.readBoolean(arg8, v5);
            continue;
        label_16:
            v3 = SafeParcelReader.readBoolean(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new CardRequirements(v2, v3, v1, v4);
    }

    public final Object[] newArray(int arg1) {
        return new CardRequirements[arg1];
    }
}

