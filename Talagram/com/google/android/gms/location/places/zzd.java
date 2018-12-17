package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        ArrayList v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        int v2 = 0;
        List v3 = null;
        String v4 = ((String)v3);
        boolean v1 = false;
        while(arg9.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg9);
            int v6 = SafeParcelReader.getFieldId(v5);
            if(v6 != 1000) {
                switch(v6) {
                    case 1: {
                        goto label_19;
                    }
                    case 2: {
                        goto label_17;
                    }
                    case 3: {
                        goto label_15;
                    }
                }

                SafeParcelReader.skipUnknownField(arg9, v5);
                continue;
            label_17:
                v3_1 = SafeParcelReader.createIntegerList(arg9, v5);
                continue;
            label_19:
                v1 = SafeParcelReader.readBoolean(arg9, v5);
                continue;
            label_15:
                v4 = SafeParcelReader.createString(arg9, v5);
                continue;
            }

            v2 = SafeParcelReader.readInt(arg9, v5);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new AutocompleteFilter(v2, v1, ((List)v3_1), v4);
    }

    public final Object[] newArray(int arg1) {
        return new AutocompleteFilter[arg1];
    }
}

