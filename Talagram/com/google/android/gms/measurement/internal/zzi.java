package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzi implements Parcelable$Creator {
    public zzi() {
        super();
    }

    public final Object createFromParcel(Parcel arg34) {
        Parcel v0 = arg34;
        int v1 = SafeParcelReader.validateObjectHeader(arg34);
        long v14 = 0;
        long v16 = v14;
        long v24 = v16;
        long v26 = v24;
        String v10 = null;
        String v11 = v10;
        String v12 = v11;
        String v13 = v12;
        String v18 = v13;
        String v23 = v18;
        String v32 = v23;
        long v21 = -2147483648;
        boolean v19 = true;
        boolean v20 = false;
        int v28 = 0;
        boolean v29 = true;
        boolean v30 = true;
        boolean v31 = false;
        while(arg34.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg34);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_66;
                }
                case 3: {
                    goto label_64;
                }
                case 4: {
                    goto label_62;
                }
                case 5: {
                    goto label_60;
                }
                case 6: {
                    goto label_58;
                }
                case 7: {
                    goto label_56;
                }
                case 8: {
                    goto label_54;
                }
                case 9: {
                    goto label_52;
                }
                case 10: {
                    goto label_50;
                }
                case 11: {
                    goto label_48;
                }
                case 12: {
                    goto label_46;
                }
                case 13: {
                    goto label_44;
                }
                case 14: {
                    goto label_42;
                }
                case 15: {
                    goto label_40;
                }
                case 16: {
                    goto label_38;
                }
                case 17: {
                    goto label_36;
                }
                case 18: {
                    goto label_34;
                }
                case 19: {
                    goto label_32;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_66:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_34:
            v31 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_36:
            v30 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_38:
            v29 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_40:
            v28 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_42:
            v26 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_44:
            v24 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_46:
            v23 = SafeParcelReader.createString(v0, v2);
            continue;
        label_48:
            v21 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_50:
            v20 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_52:
            v19 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_54:
            v18 = SafeParcelReader.createString(v0, v2);
            continue;
        label_56:
            v16 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_58:
            v14 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_60:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_62:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_64:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_32:
            v32 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzh(v10, v11, v12, v13, v14, v16, v18, v19, v20, v21, v23, v24, v26, v28, v29, v30, v31, v32);
    }

    public final Object[] newArray(int arg1) {
        return new zzh[arg1];
    }
}

