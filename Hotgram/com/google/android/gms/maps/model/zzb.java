package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        IBinder v1 = null;
        int v2 = 0;
        Float v3 = ((Float)v1);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_15;
                }
                case 3: {
                    goto label_13;
                }
                case 4: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3 = SafeParcelReader.readFloatObject(arg7, v4);
            continue;
        label_13:
            v1 = SafeParcelReader.readIBinder(arg7, v4);
            continue;
        label_15:
            v2 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new Cap(v2, v1, v3);
    }

    public final Object[] newArray(int arg1) {
        return new Cap[arg1];
    }
}

