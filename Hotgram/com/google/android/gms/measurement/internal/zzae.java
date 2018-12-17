package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzae implements Parcelable$Creator {
    public zzae() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        String v5 = null;
        zzaa v6 = ((zzaa)v5);
        String v7 = ((String)v6);
        long v8 = 0;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_22;
                }
                case 3: {
                    goto label_18;
                }
                case 4: {
                    goto label_16;
                }
                case 5: {
                    goto label_14;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_18:
            Parcelable v6_1 = SafeParcelReader.createParcelable(arg11, v1, zzaa.CREATOR);
            continue;
        label_22:
            v5 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_14:
            v8 = SafeParcelReader.readLong(arg11, v1);
            continue;
        label_16:
            v7 = SafeParcelReader.createString(arg11, v1);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzad(v5, v6, v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new zzad[arg1];
    }
}

