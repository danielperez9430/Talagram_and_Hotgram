package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzs implements Parcelable$Creator {
    public zzs() {
        super();
    }

    public final Object createFromParcel(Parcel arg15) {
        int v0 = SafeParcelReader.validateObjectHeader(arg15);
        String v5 = null;
        String v8 = v5;
        String v9 = v8;
        String v11 = v9;
        int v6 = 0;
        int v7 = 0;
        boolean v10 = true;
        boolean v12 = false;
        int v13 = 0;
        while(arg15.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg15);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_36;
                }
                case 3: {
                    goto label_34;
                }
                case 4: {
                    goto label_32;
                }
                case 5: {
                    goto label_30;
                }
                case 6: {
                    goto label_28;
                }
                case 7: {
                    goto label_26;
                }
                case 8: {
                    goto label_24;
                }
                case 9: {
                    goto label_22;
                }
                case 10: {
                    goto label_20;
                }
            }

            SafeParcelReader.skipUnknownField(arg15, v1);
            continue;
        label_34:
            v6 = SafeParcelReader.readInt(arg15, v1);
            continue;
        label_36:
            v5 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_20:
            v13 = SafeParcelReader.readInt(arg15, v1);
            continue;
        label_22:
            v12 = SafeParcelReader.readBoolean(arg15, v1);
            continue;
        label_24:
            v11 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_26:
            v10 = SafeParcelReader.readBoolean(arg15, v1);
            continue;
        label_28:
            v9 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_30:
            v8 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_32:
            v7 = SafeParcelReader.readInt(arg15, v1);
        }

        SafeParcelReader.ensureAtEnd(arg15, v0);
        return new zzr(v5, v6, v7, v8, v9, v10, v11, v12, v13);
    }

    public final Object[] newArray(int arg1) {
        return new zzr[arg1];
    }
}

