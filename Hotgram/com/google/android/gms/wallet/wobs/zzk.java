package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Parcelable$Creator {
    public zzk() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        long v1 = 0;
        long v3 = v1;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_12;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_10:
            v3 = SafeParcelReader.readLong(arg8, v5);
            continue;
        label_12:
            v1 = SafeParcelReader.readLong(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new TimeInterval(v1, v3);
    }

    public final Object[] newArray(int arg1) {
        return new TimeInterval[arg1];
    }
}

