package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzap implements Parcelable$Creator {
    public zzap() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        List v1 = null;
        List v2 = v1;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 1: {
                    goto label_13;
                }
                case 2: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            ArrayList v2_1 = SafeParcelReader.createTypedList(arg6, v3, zzao.CREATOR);
            continue;
        label_13:
            ArrayList v1_1 = SafeParcelReader.createTypedList(arg6, v3, zzan.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzam(v1, v2);
    }

    public final Object[] newArray(int arg1) {
        return new zzam[arg1];
    }
}

