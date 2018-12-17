package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzfi implements Parcelable$Creator {
    public zzfi() {
        super();
    }

    public final Object createFromParcel(Parcel arg17) {
        Parcel v0 = arg17;
        int v1 = SafeParcelReader.validateObjectHeader(arg17);
        String v8 = null;
        Long v11 = ((Long)v8);
        Float v12 = ((Float)v11);
        String v13 = ((String)v12);
        String v14 = v13;
        Double v15 = ((Double)v14);
        long v9 = 0;
        int v7 = 0;
        while(arg17.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg17);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_34;
                }
                case 2: {
                    goto label_32;
                }
                case 3: {
                    goto label_30;
                }
                case 4: {
                    goto label_28;
                }
                case 5: {
                    goto label_26;
                }
                case 6: {
                    goto label_24;
                }
                case 7: {
                    goto label_22;
                }
                case 8: {
                    goto label_20;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v7 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_20:
            v15 = SafeParcelReader.readDoubleObject(v0, v2);
            continue;
        label_22:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_24:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_26:
            v12 = SafeParcelReader.readFloatObject(v0, v2);
            continue;
        label_28:
            v11 = SafeParcelReader.readLongObject(v0, v2);
            continue;
        label_30:
            v9 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_32:
            v8 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzfh(v7, v8, v9, v11, v12, v13, v14, v15);
    }

    public final Object[] newArray(int arg1) {
        return new zzfh[arg1];
    }
}

