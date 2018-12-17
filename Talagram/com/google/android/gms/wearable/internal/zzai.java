package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzai implements Parcelable$Creator {
    public zzai() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        ArrayList v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        String v1 = null;
        List v2 = ((List)v1);
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2_1 = SafeParcelReader.createTypedList(arg6, v3, zzfo.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.createString(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzah(v1, ((List)v2_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzah[arg1];
    }
}

