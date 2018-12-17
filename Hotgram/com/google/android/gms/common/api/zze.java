package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        int v2 = 0;
        String v3 = null;
        PendingIntent v4 = ((PendingIntent)v3);
        int v1 = 0;
        while(arg9.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg9);
            int v6 = SafeParcelReader.getFieldId(v5);
            if(v6 != 1000) {
                switch(v6) {
                    case 1: {
                        goto label_20;
                    }
                    case 2: {
                        goto label_18;
                    }
                    case 3: {
                        goto label_15;
                    }
                }

                SafeParcelReader.skipUnknownField(arg9, v5);
                continue;
            label_18:
                v3 = SafeParcelReader.createString(arg9, v5);
                continue;
            label_20:
                v1 = SafeParcelReader.readInt(arg9, v5);
                continue;
            label_15:
                v4_1 = SafeParcelReader.createParcelable(arg9, v5, PendingIntent.CREATOR);
                continue;
            }

            v2 = SafeParcelReader.readInt(arg9, v5);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new Status(v2, v1, v3, ((PendingIntent)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new Status[arg1];
    }
}

