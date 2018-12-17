package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzai implements Parcelable$Creator {
    public zzai() {
        super();
    }

    public final Object createFromParcel(Parcel arg10) {
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        boolean v3 = false;
        boolean v4 = false;
        boolean v5 = false;
        boolean v6 = false;
        boolean v7 = false;
        boolean v8;
        for(v8 = false; arg10.dataPosition() < v0; v8 = SafeParcelReader.readBoolean(arg10, v1)) {
            int v1 = SafeParcelReader.readHeader(arg10);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_19;
                }
                case 5: {
                    goto label_17;
                }
                case 6: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg10, v1);
            continue;
        label_17:
            v7 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_19:
            v6 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_21:
            v5 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_23:
            v4 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_25:
            v3 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        return new LocationSettingsStates(v3, v4, v5, v6, v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new LocationSettingsStates[arg1];
    }
}

