package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzds implements Parcelable$Creator {
    public zzds() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        int v1 = 0;
        boolean v2 = false;
        boolean v3 = false;
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
            v3 = SafeParcelReader.readBoolean(arg7, v4);
            continue;
        label_13:
            v2 = SafeParcelReader.readBoolean(arg7, v4);
            continue;
        label_15:
            v1 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new zzdt(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzdt[arg1];
    }
}

