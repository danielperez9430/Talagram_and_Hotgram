package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg14) {
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        long v7 = 0;
        long v9 = v7;
        List v6 = null;
        Bundle v12 = ((Bundle)v6);
        int v11 = 0;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_24;
                }
                case 2: {
                    goto label_22;
                }
                case 3: {
                    goto label_20;
                }
                case 4: {
                    goto label_18;
                }
                case 5: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_18:
            v11 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_20:
            v9 = SafeParcelReader.readLong(arg14, v1);
            continue;
        label_22:
            v7 = SafeParcelReader.readLong(arg14, v1);
            continue;
        label_24:
            ArrayList v6_1 = SafeParcelReader.createTypedList(arg14, v1, DetectedActivity.CREATOR);
            continue;
        label_16:
            v12 = SafeParcelReader.createBundle(arg14, v1);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new ActivityRecognitionResult(v6, v7, v9, v11, v12);
    }

    public final Object[] newArray(int arg1) {
        return new ActivityRecognitionResult[arg1];
    }
}

