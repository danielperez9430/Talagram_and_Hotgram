package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        long v6 = 0;
        long v8 = v6;
        boolean v5 = false;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_15;
                }
                case 3: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_17:
            v5 = SafeParcelReader.readBoolean(arg11, v1);
            continue;
        label_13:
            v6 = SafeParcelReader.readLong(arg11, v1);
            continue;
        label_15:
            v8 = SafeParcelReader.readLong(arg11, v1);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzc(v5, v6, v8);
    }

    public final Object[] newArray(int arg1) {
        return new zzc[arg1];
    }
}

