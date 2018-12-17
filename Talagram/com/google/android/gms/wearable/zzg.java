package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzg implements Parcelable$Creator {
    public zzg() {
        super();
    }

    public final Object createFromParcel(Parcel arg14) {
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        String v4 = null;
        String v5 = v4;
        String v10 = v5;
        String v12 = v10;
        int v6 = 0;
        int v7 = 0;
        boolean v8 = false;
        boolean v9 = false;
        boolean v11 = false;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
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
                case 10: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_33:
            v5 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_35:
            v4 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_19:
            v12 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_21:
            v11 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_23:
            v10 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_25:
            v9 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_27:
            v8 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_29:
            v7 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_31:
            v6 = SafeParcelReader.readInt(arg14, v1);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new ConnectionConfiguration(v4, v5, v6, v7, v8, v9, v10, v11, v12);
    }

    public final Object[] newArray(int arg1) {
        return new ConnectionConfiguration[arg1];
    }
}

