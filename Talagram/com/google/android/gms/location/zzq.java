package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

public final class zzq implements Parcelable$Creator {
    public zzq() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        ArrayList v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        String v1 = "";
        List v2 = null;
        int v3 = 0;
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_15;
                }
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v1 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_13:
            v3 = SafeParcelReader.readInt(arg7, v4);
            continue;
        label_15:
            v2_1 = SafeParcelReader.createTypedList(arg7, v4, zzbh.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new GeofencingRequest(((List)v2_1), v3, v1);
    }

    public final Object[] newArray(int arg1) {
        return new GeofencingRequest[arg1];
    }
}

