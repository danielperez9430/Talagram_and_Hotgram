package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzak implements Parcelable$Creator {
    public zzak() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        PlaceEntity v1 = null;
        float v2 = 0f;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 1: {
                    goto label_12;
                }
                case 2: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2 = SafeParcelReader.readFloat(arg6, v3);
            continue;
        label_12:
            Parcelable v1_1 = SafeParcelReader.createParcelable(arg6, v3, PlaceEntity.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzaj(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new zzaj[arg1];
    }
}

