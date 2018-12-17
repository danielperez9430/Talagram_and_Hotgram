package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Parcelable$Creator {
    public zzk() {
        super();
    }

    public final Object createFromParcel(Parcel arg20) {
        Parcel v0 = arg20;
        int v1 = SafeParcelReader.validateObjectHeader(arg20);
        String v9 = null;
        String v15 = v9;
        byte[] v16 = ((byte[])v15);
        long v10 = 0;
        double v13 = 0;
        boolean v12 = false;
        int v17 = 0;
        int v18 = 0;
        while(arg20.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg20);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_35;
                }
                case 3: {
                    goto label_33;
                }
                case 4: {
                    goto label_31;
                }
                case 5: {
                    goto label_29;
                }
                case 6: {
                    goto label_27;
                }
                case 7: {
                    goto label_25;
                }
                case 8: {
                    goto label_23;
                }
                case 9: {
                    goto label_21;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v10 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_35:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_21:
            v18 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_23:
            v17 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_25:
            v16 = SafeParcelReader.createByteArray(v0, v2);
            continue;
        label_27:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_29:
            v13 = SafeParcelReader.readDouble(v0, v2);
            continue;
        label_31:
            v12 = SafeParcelReader.readBoolean(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzi(v9, v10, v12, v13, v15, v16, v17, v18);
    }

    public final Object[] newArray(int arg1) {
        return new zzi[arg1];
    }
}

