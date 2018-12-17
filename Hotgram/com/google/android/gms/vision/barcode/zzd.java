package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg13) {
        int v0 = SafeParcelReader.validateObjectHeader(arg13);
        String v11 = null;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;
        boolean v10 = false;
        while(arg13.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg13);
            switch(SafeParcelReader.getFieldId(v1)) {
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
                case 9: {
                    goto label_18;
                }
            }

            SafeParcelReader.skipUnknownField(arg13, v1);
            continue;
        label_18:
            v11 = SafeParcelReader.createString(arg13, v1);
            continue;
        label_20:
            v10 = SafeParcelReader.readBoolean(arg13, v1);
            continue;
        label_22:
            v9 = SafeParcelReader.readInt(arg13, v1);
            continue;
        label_24:
            v8 = SafeParcelReader.readInt(arg13, v1);
            continue;
        label_26:
            v7 = SafeParcelReader.readInt(arg13, v1);
            continue;
        label_28:
            v6 = SafeParcelReader.readInt(arg13, v1);
            continue;
        label_30:
            v5 = SafeParcelReader.readInt(arg13, v1);
            continue;
        label_32:
            v4 = SafeParcelReader.readInt(arg13, v1);
        }

        SafeParcelReader.ensureAtEnd(arg13, v0);
        return new CalendarDateTime(v4, v5, v6, v7, v8, v9, v10, v11);
    }

    public final Object[] newArray(int arg1) {
        return new CalendarDateTime[arg1];
    }
}

