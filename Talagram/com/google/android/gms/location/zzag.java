package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzag implements Parcelable$Creator {
    public zzag() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        boolean v1 = false;
        List v2 = null;
        zzae v4 = ((zzae)v2);
        boolean v3 = false;
        while(arg9.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg9);
            int v6 = SafeParcelReader.getFieldId(v5);
            if(v6 != 5) {
                switch(v6) {
                    case 1: {
                        goto label_18;
                    }
                    case 2: {
                        goto label_16;
                    }
                    case 3: {
                        goto label_14;
                    }
                }

                SafeParcelReader.skipUnknownField(arg9, v5);
                continue;
            label_18:
                ArrayList v2_1 = SafeParcelReader.createTypedList(arg9, v5, LocationRequest.CREATOR);
                continue;
            label_14:
                v3 = SafeParcelReader.readBoolean(arg9, v5);
                continue;
            label_16:
                v1 = SafeParcelReader.readBoolean(arg9, v5);
                continue;
            }

            v4_1 = SafeParcelReader.createParcelable(arg9, v5, zzae.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new LocationSettingsRequest(v2, v1, v3, ((zzae)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new LocationSettingsRequest[arg1];
    }
}

