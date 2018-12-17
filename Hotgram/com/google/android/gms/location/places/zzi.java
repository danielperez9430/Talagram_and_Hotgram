package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzi implements Parcelable$Creator {
    public zzi() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        ArrayList v1_1;
        ArrayList v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        List v1 = null;
        boolean v2 = false;
        List v3 = v1;
        List v4 = v3;
        while(arg9.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg9);
            int v6 = SafeParcelReader.getFieldId(v5);
            if(v6 != 1) {
                if(v6 != 6) {
                    switch(v6) {
                        case 3: {
                            goto label_19;
                        }
                        case 4: {
                            goto label_16;
                        }
                    }

                    SafeParcelReader.skipUnknownField(arg9, v5);
                    continue;
                label_19:
                    v2 = SafeParcelReader.readBoolean(arg9, v5);
                    continue;
                label_16:
                    ArrayList v4_1 = SafeParcelReader.createTypedList(arg9, v5, zzo.CREATOR);
                    continue;
                }

                v3_1 = SafeParcelReader.createStringList(arg9, v5);
                continue;
            }

            v1_1 = SafeParcelReader.createIntegerList(arg9, v5);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new PlaceFilter(((List)v1_1), v2, ((List)v3_1), v4);
    }

    public final Object[] newArray(int arg1) {
        return new PlaceFilter[arg1];
    }
}

