package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzaf implements Parcelable$Creator {
    public zzaf() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = "";
        String v2 = "";
        String v3 = "";
        while(arg8.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg8);
            int v5 = SafeParcelReader.getFieldId(v4);
            if(v5 != 5) {
                switch(v5) {
                    case 1: {
                        goto label_15;
                    }
                    case 2: {
                        goto label_13;
                    }
                }

                SafeParcelReader.skipUnknownField(arg8, v4);
                continue;
            label_13:
                v3 = SafeParcelReader.createString(arg8, v4);
                continue;
            label_15:
                v2 = SafeParcelReader.createString(arg8, v4);
                continue;
            }

            v1 = SafeParcelReader.createString(arg8, v4);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzae(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzae[arg1];
    }
}

