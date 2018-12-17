package com.google.android.gms.internal.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzdp implements Parcelable$Creator {
    public zzdp() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        String v2 = v1;
        List v3 = ((List)v2);
        while(arg8.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg8);
            int v5 = SafeParcelReader.getFieldId(v4);
            if(v5 != 6) {
                switch(v5) {
                    case 1: {
                        goto label_15;
                    }
                    case 2: {
                        goto label_13;
                    }
                }

                SafeParcelReader.skipUnknownField(arg8, v4);
                continue;
            label_13:
                v2 = SafeParcelReader.createString(arg8, v4);
                continue;
            label_15:
                v1 = SafeParcelReader.createString(arg8, v4);
                continue;
            }

            ArrayList v3_1 = SafeParcelReader.createTypedList(arg8, v4, zzdl.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzdn(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzdn[arg1];
    }
}

