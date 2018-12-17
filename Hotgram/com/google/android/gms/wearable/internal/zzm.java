package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable$Creator {
    public zzm() {
        super();
    }

    public final Object createFromParcel(Parcel arg18) {
        Parcel v0 = arg18;
        int v1 = SafeParcelReader.validateObjectHeader(arg18);
        String v6 = null;
        String v7 = v6;
        String v8 = v7;
        String v9 = v8;
        String v10 = v9;
        String v11 = v10;
        String v16 = v11;
        int v5 = 0;
        byte v12 = 0;
        byte v13 = 0;
        byte v14 = 0;
        byte v15 = 0;
        while(arg18.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg18);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_45;
                }
                case 3: {
                    goto label_43;
                }
                case 4: {
                    goto label_41;
                }
                case 5: {
                    goto label_39;
                }
                case 6: {
                    goto label_37;
                }
                case 7: {
                    goto label_35;
                }
                case 8: {
                    goto label_33;
                }
                case 9: {
                    goto label_31;
                }
                case 10: {
                    goto label_29;
                }
                case 11: {
                    goto label_27;
                }
                case 12: {
                    goto label_25;
                }
                case 13: {
                    goto label_23;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_35:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_39:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_41:
            v7 = SafeParcelReader.createString(v0, v2);
            continue;
        label_43:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_45:
            v5 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_23:
            v16 = SafeParcelReader.createString(v0, v2);
            continue;
        label_25:
            v15 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_27:
            v14 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_29:
            v13 = SafeParcelReader.readByte(v0, v2);
            continue;
        label_31:
            v12 = SafeParcelReader.readByte(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzl(v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16);
    }

    public final Object[] newArray(int arg1) {
        return new zzl[arg1];
    }
}

