package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzy implements Parcelable$Creator {
    public zzy() {
        super();
    }

    public final Object createFromParcel(Parcel arg18) {
        Parcelable v7_1;
        Parcelable v9_1;
        Parcel v0 = arg18;
        int v1 = SafeParcelReader.validateObjectHeader(arg18);
        zzag[] v6 = null;
        zzr v7 = ((zzr)v6);
        zzr v8 = v7;
        zzr v9 = v8;
        String v10 = ((String)v9);
        String v12 = v10;
        float v11 = 0f;
        int v13 = 0;
        boolean v14 = false;
        int v15 = 0;
        int v16 = 0;
        while(arg18.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg18);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_49;
                }
                case 3: {
                    goto label_45;
                }
                case 4: {
                    goto label_41;
                }
                case 5: {
                    goto label_37;
                }
                case 6: {
                    goto label_35;
                }
                case 7: {
                    goto label_33;
                }
                case 8: {
                    goto label_31;
                }
                case 9: {
                    goto label_29;
                }
                case 10: {
                    goto label_27;
                }
                case 11: {
                    goto label_25;
                }
                case 12: {
                    goto label_23;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_49:
            Object[] v6_1 = SafeParcelReader.createTypedArray(v0, v2, zzag.CREATOR);
            continue;
        label_33:
            v11 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_35:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            v9_1 = SafeParcelReader.createParcelable(v0, v2, zzr.CREATOR);
            continue;
        label_23:
            v16 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_41:
            Parcelable v8_1 = SafeParcelReader.createParcelable(v0, v2, zzr.CREATOR);
            continue;
        label_25:
            v15 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_27:
            v14 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_45:
            v7_1 = SafeParcelReader.createParcelable(v0, v2, zzr.CREATOR);
            continue;
        label_29:
            v13 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_31:
            v12 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzx(v6, ((zzr)v7_1), v8, ((zzr)v9_1), v10, v11, v12, v13, v14, v15, v16);
    }

    public final Object[] newArray(int arg1) {
        return new zzx[arg1];
    }
}

