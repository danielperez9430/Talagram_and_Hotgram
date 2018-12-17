package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzan implements Parcelable$Creator {
    public zzan() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        ArrayList v1;
        for(v1 = null; arg6.dataPosition() < v0; v1 = SafeParcelReader.createStringList(arg6, v2)) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 1) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new ShippingAddressRequirements(v1);
    }

    public final Object[] newArray(int arg1) {
        return new ShippingAddressRequirements[arg1];
    }
}

