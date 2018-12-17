package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzf implements Parcelable$Creator {
    public zzf() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        ArrayList v1_1;
        ArrayList v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        List v1 = null;
        String v2 = ((String)v1);
        List v3 = ((List)v2);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_16;
                }
                case 2: {
                    goto label_14;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3_1 = SafeParcelReader.createTypedList(arg7, v4, ClientIdentity.CREATOR);
            continue;
        label_14:
            v2 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_16:
            v1_1 = SafeParcelReader.createTypedList(arg7, v4, ActivityTransition.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new ActivityTransitionRequest(((List)v1_1), v2, ((List)v3_1));
    }

    public final Object[] newArray(int arg1) {
        return new ActivityTransitionRequest[arg1];
    }
}

