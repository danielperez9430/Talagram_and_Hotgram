package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzac implements Parcelable$Creator {
    public zzac() {
        super();
    }

    public final Object createFromParcel(Parcel arg3) {
        int v0 = SafeParcelReader.validateObjectHeader(arg3);
        while(arg3.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg3);
            SafeParcelReader.getFieldId(v1);
            SafeParcelReader.skipUnknownField(arg3, v1);
        }

        SafeParcelReader.ensureAtEnd(arg3, v0);
        return new zzab();
    }

    public final Object[] newArray(int arg1) {
        return new zzab[arg1];
    }
}

