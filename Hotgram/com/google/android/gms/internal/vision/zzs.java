package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzs implements Parcelable$Creator {
    public zzs() {
        super();
    }

    public final Object createFromParcel(Parcel arg10) {
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        float v8;
        for(v8 = 0f; arg10.dataPosition() < v0; v8 = SafeParcelReader.readFloat(arg10, v1)) {
            int v1 = SafeParcelReader.readHeader(arg10);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_19;
                }
                case 5: {
                    goto label_17;
                }
                case 6: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg10, v1);
            continue;
        label_17:
            v7 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_19:
            v6 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_21:
            v5 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_23:
            v4 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        return new zzr(v4, v5, v6, v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new zzr[arg1];
    }
}

