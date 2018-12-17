package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import java.util.ArrayList;
import java.util.List;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        List v1 = null;
        while(arg6.dataPosition() < v0) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 2) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }

            ArrayList v1_1 = SafeParcelReader.createTypedList(arg6, v2, CountrySpecification.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new UserAddressRequest(v1);
    }

    public final Object[] newArray(int arg1) {
        return new UserAddressRequest[arg1];
    }
}

