package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzc implements Parcelable$Creator {
    public zzc() {
        super();
    }

    public final Object createFromParcel(Parcel arg19) {
        Parcelable v8_1;
        Parcel v0 = arg19;
        int v1 = SafeParcelReader.validateObjectHeader(arg19);
        LatLng v8 = null;
        List v17 = ((List)v8);
        double v9 = 0;
        float v11 = 0f;
        int v12 = 0;
        int v13 = 0;
        float v14 = 0f;
        boolean v15 = false;
        boolean v16 = false;
        while(arg19.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg19);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_39;
                }
                case 3: {
                    goto label_37;
                }
                case 4: {
                    goto label_35;
                }
                case 5: {
                    goto label_33;
                }
                case 6: {
                    goto label_31;
                }
                case 7: {
                    goto label_29;
                }
                case 8: {
                    goto label_27;
                }
                case 9: {
                    goto label_25;
                }
                case 10: {
                    goto label_22;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v12 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_35:
            v11 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_37:
            v9 = SafeParcelReader.readDouble(v0, v2);
            continue;
        label_22:
            ArrayList v17_1 = SafeParcelReader.createTypedList(v0, v2, PatternItem.CREATOR);
            continue;
        label_39:
            v8_1 = SafeParcelReader.createParcelable(v0, v2, LatLng.CREATOR);
            continue;
        label_25:
            v16 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_27:
            v15 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_29:
            v14 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_31:
            v13 = SafeParcelReader.readInt(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new CircleOptions(((LatLng)v8_1), v9, v11, v12, v13, v14, v15, v16, v17);
    }

    public final Object[] newArray(int arg1) {
        return new CircleOptions[arg1];
    }
}

