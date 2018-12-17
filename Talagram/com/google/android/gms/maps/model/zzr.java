package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzr implements Parcelable$Creator {
    public zzr() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        int v1 = 0;
        byte[] v3 = null;
        int v2 = 0;
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
            v3 = SafeParcelReader.createByteArray(arg7, v4);
            continue;
        label_14:
            v2 = SafeParcelReader.readInt(arg7, v4);
            continue;
        label_16:
            v1 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new Tile(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new Tile[arg1];
    }
}

