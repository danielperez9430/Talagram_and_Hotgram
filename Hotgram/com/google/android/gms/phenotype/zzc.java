package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Parcelable$Creator {
    public zzc() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        Object[] v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        zzi[] v1 = null;
        int v2 = 0;
        String[] v3 = ((String[])v1);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_16;
                }
                case 3: {
                    goto label_13;
                }
                case 4: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3 = SafeParcelReader.createStringArray(arg7, v4);
            continue;
        label_13:
            v1_1 = SafeParcelReader.createTypedArray(arg7, v4, zzi.CREATOR);
            continue;
        label_16:
            v2 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new Configuration(v2, ((zzi[])v1_1), v3);
    }

    public final Object[] newArray(int arg1) {
        return new Configuration[arg1];
    }
}

