package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable$Creator {
    public zzh() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        int v2 = 0;
        String v3 = v1;
        String v4 = v3;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_14;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_16:
            v1 = SafeParcelReader.createString(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new Email(v2, v1, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new Email[arg1];
    }
}

