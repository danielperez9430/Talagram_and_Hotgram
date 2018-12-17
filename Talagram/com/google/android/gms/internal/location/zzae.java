package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzae implements Parcelable$Creator {
    public zzae() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        Status v1 = null;
        while(arg6.dataPosition() < v0) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 1) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }

            v1_1 = SafeParcelReader.createParcelable(arg6, v2, Status.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzad(((Status)v1_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzad[arg1];
    }
}

