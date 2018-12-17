package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzp implements Parcelable$Creator {
    public zzp() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        zzm v1 = null;
        int v2 = 1;
        IBinder v3 = ((IBinder)v1);
        IBinder v4 = v3;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_19;
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
        label_19:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.readIBinder(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.readIBinder(arg8, v5);
            continue;
        label_16:
            Parcelable v1_1 = SafeParcelReader.createParcelable(arg8, v5, zzm.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzo(v2, v1, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new zzo[arg1];
    }
}

