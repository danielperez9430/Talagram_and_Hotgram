package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.List;

public final class zzbe implements Parcelable$Creator {
    public zzbe() {
        super();
    }

    public final Object createFromParcel(Parcel arg13) {
        ArrayList v6_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg13);
        List v6 = zzbd.zzcd;
        LocationRequest v5 = null;
        String v7 = ((String)v5);
        String v11 = v7;
        boolean v8 = false;
        boolean v9 = false;
        boolean v10 = false;
        while(arg13.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg13);
            int v2 = SafeParcelReader.getFieldId(v1);
            if(v2 != 1) {
                switch(v2) {
                    case 5: {
                        goto label_30;
                    }
                    case 6: {
                        goto label_28;
                    }
                    case 7: {
                        goto label_26;
                    }
                    case 8: {
                        goto label_24;
                    }
                    case 9: {
                        goto label_22;
                    }
                    case 10: {
                        goto label_20;
                    }
                }

                SafeParcelReader.skipUnknownField(arg13, v1);
                continue;
            label_20:
                v11 = SafeParcelReader.createString(arg13, v1);
                continue;
            label_22:
                v10 = SafeParcelReader.readBoolean(arg13, v1);
                continue;
            label_24:
                v9 = SafeParcelReader.readBoolean(arg13, v1);
                continue;
            label_26:
                v8 = SafeParcelReader.readBoolean(arg13, v1);
                continue;
            label_28:
                v7 = SafeParcelReader.createString(arg13, v1);
                continue;
            label_30:
                v6_1 = SafeParcelReader.createTypedList(arg13, v1, ClientIdentity.CREATOR);
                continue;
            }

            Parcelable v5_1 = SafeParcelReader.createParcelable(arg13, v1, LocationRequest.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg13, v0);
        return new zzbd(v5, ((List)v6_1), v7, v8, v9, v10, v11);
    }

    public final Object[] newArray(int arg1) {
        return new zzbd[arg1];
    }
}

