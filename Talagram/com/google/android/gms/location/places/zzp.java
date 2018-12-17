package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzp implements Parcelable$Creator {
    public zzp() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        String v1 = null;
        int v2 = 0;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 1: {
                    goto label_12;
                }
                case 2: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2 = SafeParcelReader.readInt(arg6, v3);
            continue;
        label_12:
            v1 = SafeParcelReader.createString(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzo(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new zzo[arg1];
    }
}

