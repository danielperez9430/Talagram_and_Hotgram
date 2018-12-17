package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzj implements Parcelable$Creator {
    public zzj() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        byte v1 = 0;
        String v3 = null;
        byte v2 = 0;
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_16;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_12:
            v3 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_14:
            v2 = SafeParcelReader.readByte(arg7, v4);
            continue;
        label_16:
            v1 = SafeParcelReader.readByte(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new zzi(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzi[arg1];
    }
}

