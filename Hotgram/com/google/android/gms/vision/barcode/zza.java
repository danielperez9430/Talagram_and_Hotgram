package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zza implements Parcelable$Creator {
    public zza() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        String[] v2 = null;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 2: {
                    goto label_12;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2 = SafeParcelReader.createStringArray(arg6, v3);
            continue;
        label_12:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new Address(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new Address[arg1];
    }
}

