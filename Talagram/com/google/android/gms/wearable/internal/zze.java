package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        IBinder v1 = null;
        IntentFilter[] v2 = ((IntentFilter[])v1);
        String v3 = ((String)v2);
        String v4 = v3;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_19;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_14;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_19:
            v1 = SafeParcelReader.readIBinder(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_16:
            Object[] v2_1 = SafeParcelReader.createTypedArray(arg8, v5, IntentFilter.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzd(v1, v2, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new zzd[arg1];
    }
}

