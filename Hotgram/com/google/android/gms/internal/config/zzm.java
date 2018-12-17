package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable$Creator {
    public zzm() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        String v1 = null;
        String v2 = v1;
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
            v2 = SafeParcelReader.createString(arg6, v3);
            continue;
        label_12:
            v1 = SafeParcelReader.createString(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzl(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new zzl[arg1];
    }
}

