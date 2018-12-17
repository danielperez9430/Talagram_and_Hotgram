package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzah implements Parcelable$Creator {
    public zzah() {
        super();
    }

    public final Object createFromParcel(Parcel arg13) {
        Parcelable v6_1;
        Object[] v5_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg13);
        zzab[] v5 = null;
        zzr v6 = ((zzr)v5);
        zzr v7 = v6;
        String v8 = ((String)v7);
        String v10 = v8;
        float v9 = 0f;
        boolean v11 = false;
        while(arg13.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg13);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_34;
                }
                case 3: {
                    goto label_30;
                }
                case 4: {
                    goto label_26;
                }
                case 5: {
                    goto label_24;
                }
                case 6: {
                    goto label_22;
                }
                case 7: {
                    goto label_20;
                }
                case 8: {
                    goto label_18;
                }
            }

            SafeParcelReader.skipUnknownField(arg13, v1);
            continue;
        label_34:
            v5_1 = SafeParcelReader.createTypedArray(arg13, v1, zzab.CREATOR);
            continue;
        label_18:
            v11 = SafeParcelReader.readBoolean(arg13, v1);
            continue;
        label_20:
            v10 = SafeParcelReader.createString(arg13, v1);
            continue;
        label_22:
            v9 = SafeParcelReader.readFloat(arg13, v1);
            continue;
        label_24:
            v8 = SafeParcelReader.createString(arg13, v1);
            continue;
        label_26:
            Parcelable v7_1 = SafeParcelReader.createParcelable(arg13, v1, zzr.CREATOR);
            continue;
        label_30:
            v6_1 = SafeParcelReader.createParcelable(arg13, v1, zzr.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg13, v0);
        return new zzag(((zzab[])v5_1), ((zzr)v6_1), v7, v8, v9, v10, v11);
    }

    public final Object[] newArray(int arg1) {
        return new zzag[arg1];
    }
}

