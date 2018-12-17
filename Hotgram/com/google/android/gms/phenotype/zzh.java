package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable$Creator {
    public zzh() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        String v3 = null;
        byte[] v4 = ((byte[])v3);
        byte[][] v5 = ((byte[][])v4);
        byte[][] v6 = v5;
        byte[][] v7 = v6;
        byte[][] v8 = v7;
        int[] v9 = ((int[])v8);
        byte[][] v10 = ((byte[][])v9);
        while(arg12.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_31;
                }
                case 3: {
                    goto label_29;
                }
                case 4: {
                    goto label_27;
                }
                case 5: {
                    goto label_25;
                }
                case 6: {
                    goto label_23;
                }
                case 7: {
                    goto label_21;
                }
                case 8: {
                    goto label_19;
                }
                case 9: {
                    goto label_17;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_17:
            v10 = SafeParcelReader.createByteArrayArray(arg12, v1);
            continue;
        label_19:
            v9 = SafeParcelReader.createIntArray(arg12, v1);
            continue;
        label_21:
            v8 = SafeParcelReader.createByteArrayArray(arg12, v1);
            continue;
        label_23:
            v7 = SafeParcelReader.createByteArrayArray(arg12, v1);
            continue;
        label_25:
            v6 = SafeParcelReader.createByteArrayArray(arg12, v1);
            continue;
        label_27:
            v5 = SafeParcelReader.createByteArrayArray(arg12, v1);
            continue;
        label_29:
            v4 = SafeParcelReader.createByteArray(arg12, v1);
            continue;
        label_31:
            v3 = SafeParcelReader.createString(arg12, v1);
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new ExperimentTokens(v3, v4, v5, v6, v7, v8, v9, v10);
    }

    public final Object[] newArray(int arg1) {
        return new ExperimentTokens[arg1];
    }
}

