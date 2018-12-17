package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzah implements Parcelable$Creator {
    public zzah() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        Status v1 = null;
        LocationSettingsStates v2 = ((LocationSettingsStates)v1);
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
            v2_1 = SafeParcelReader.createParcelable(arg6, v3, LocationSettingsStates.CREATOR);
            continue;
        label_13:
            Parcelable v1_1 = SafeParcelReader.createParcelable(arg6, v3, Status.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new LocationSettingsResult(v1, ((LocationSettingsStates)v2_1));
    }

    public final Object[] newArray(int arg1) {
        return new LocationSettingsResult[arg1];
    }
}

