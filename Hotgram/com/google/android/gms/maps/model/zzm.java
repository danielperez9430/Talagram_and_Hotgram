package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable$Creator {
    public zzm() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        float v1 = 0f;
        float v2 = 0f;
        float v3 = 0f;
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
            v3 = SafeParcelReader.readFloat(arg7, v4);
            continue;
        label_13:
            v2 = SafeParcelReader.readFloat(arg7, v4);
            continue;
        label_15:
            v1 = SafeParcelReader.readFloat(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new StreetViewPanoramaCamera(v1, v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new StreetViewPanoramaCamera[arg1];
    }
}

