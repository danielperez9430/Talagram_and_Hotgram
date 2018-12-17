package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzfv implements Parcelable$Creator {
    public zzfv() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        zzdd v2 = null;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2_1 = SafeParcelReader.createParcelable(arg6, v3, zzdd.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzfu(v1, ((zzdd)v2_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzfu[arg1];
    }
}

