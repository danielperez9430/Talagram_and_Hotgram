package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;

public final class zzf implements Parcelable$Creator {
    public zzf() {
        super();
    }

    public final Object createFromParcel(Parcel arg13) {
        Object[] v11_1;
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg13);
        zzr v4 = null;
        byte[] v5 = ((byte[])v4);
        int[] v6 = ((int[])v5);
        String[] v7 = ((String[])v6);
        int[] v8 = ((int[])v7);
        byte[][] v9 = ((byte[][])v8);
        ExperimentTokens[] v11 = ((ExperimentTokens[])v9);
        boolean v10 = true;
        while(arg13.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg13);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_34;
                }
                case 3: {
                    goto label_32;
                }
                case 4: {
                    goto label_30;
                }
                case 5: {
                    goto label_28;
                }
                case 6: {
                    goto label_26;
                }
                case 7: {
                    goto label_24;
                }
                case 8: {
                    goto label_22;
                }
                case 9: {
                    goto label_18;
                }
            }

            SafeParcelReader.skipUnknownField(arg13, v1);
            continue;
        label_34:
            v4_1 = SafeParcelReader.createParcelable(arg13, v1, zzr.CREATOR);
            continue;
        label_18:
            v11_1 = SafeParcelReader.createTypedArray(arg13, v1, ExperimentTokens.CREATOR);
            continue;
        label_22:
            v10 = SafeParcelReader.readBoolean(arg13, v1);
            continue;
        label_24:
            v9 = SafeParcelReader.createByteArrayArray(arg13, v1);
            continue;
        label_26:
            v8 = SafeParcelReader.createIntArray(arg13, v1);
            continue;
        label_28:
            v7 = SafeParcelReader.createStringArray(arg13, v1);
            continue;
        label_30:
            v6 = SafeParcelReader.createIntArray(arg13, v1);
            continue;
        label_32:
            v5 = SafeParcelReader.createByteArray(arg13, v1);
        }

        SafeParcelReader.ensureAtEnd(arg13, v0);
        return new zze(((zzr)v4_1), v5, v6, v7, v8, v9, v10, ((ExperimentTokens[])v11_1));
    }

    public final Object[] newArray(int arg1) {
        return new zze[arg1];
    }
}

