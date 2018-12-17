package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wearable.ConnectionConfiguration;

public final class zzdz implements Parcelable$Creator {
    public zzdz() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        Object[] v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        ConnectionConfiguration[] v2 = null;
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
            v2_1 = SafeParcelReader.createTypedArray(arg6, v3, ConnectionConfiguration.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzdy(v1, ((ConnectionConfiguration[])v2_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzdy[arg1];
    }
}

