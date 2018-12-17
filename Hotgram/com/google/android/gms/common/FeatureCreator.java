package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class FeatureCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public FeatureCreator() {
        super();
    }

    public Feature createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        int v2 = 0;
        long v3 = -1;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_15;
                }
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_11:
            v3 = SafeParcelReader.readLong(arg8, v5);
            continue;
        label_13:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_15:
            v1 = SafeParcelReader.createString(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new Feature(v1, v2, v3);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public Feature[] newArray(int arg1) {
        return new Feature[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

