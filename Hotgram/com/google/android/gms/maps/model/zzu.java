package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzu implements Parcelable$Creator {
    public zzu() {
        super();
    }

    public final Object createFromParcel(Parcel arg12) {
        int v0 = SafeParcelReader.validateObjectHeader(arg12);
        IBinder v6 = null;
        boolean v7 = false;
        float v8 = 0f;
        boolean v9 = true;
        float v10 = 0f;
        while(arg12.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg12);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_25;
                }
                case 3: {
                    goto label_23;
                }
                case 4: {
                    goto label_21;
                }
                case 5: {
                    goto label_19;
                }
                case 6: {
                    goto label_17;
                }
            }

            SafeParcelReader.skipUnknownField(arg12, v1);
            continue;
        label_17:
            v10 = SafeParcelReader.readFloat(arg12, v1);
            continue;
        label_19:
            v9 = SafeParcelReader.readBoolean(arg12, v1);
            continue;
        label_21:
            v8 = SafeParcelReader.readFloat(arg12, v1);
            continue;
        label_23:
            v7 = SafeParcelReader.readBoolean(arg12, v1);
            continue;
        label_25:
            v6 = SafeParcelReader.readIBinder(arg12, v1);
        }

        SafeParcelReader.ensureAtEnd(arg12, v0);
        return new TileOverlayOptions(v6, v7, v8, v9, v10);
    }

    public final Object[] newArray(int arg1) {
        return new TileOverlayOptions[arg1];
    }
}

