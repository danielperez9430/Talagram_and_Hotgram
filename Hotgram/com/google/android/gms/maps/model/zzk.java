package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzk implements Parcelable$Creator {
    public zzk() {
        super();
    }

    public final Object createFromParcel(Parcel arg15) {
        ArrayList v12_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg15);
        ArrayList v3 = new ArrayList();
        List v2 = null;
        List v12 = v2;
        float v4 = 0f;
        int v5 = 0;
        int v6 = 0;
        float v7 = 0f;
        boolean v8 = false;
        boolean v9 = false;
        boolean v10 = false;
        int v11 = 0;
        while(arg15.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg15);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_45;
                }
                case 3: {
                    goto label_41;
                }
                case 4: {
                    goto label_39;
                }
                case 5: {
                    goto label_37;
                }
                case 6: {
                    goto label_35;
                }
                case 7: {
                    goto label_33;
                }
                case 8: {
                    goto label_31;
                }
                case 9: {
                    goto label_29;
                }
                case 10: {
                    goto label_27;
                }
                case 11: {
                    goto label_25;
                }
                case 12: {
                    goto label_22;
                }
            }

            SafeParcelReader.skipUnknownField(arg15, v1);
            continue;
        label_33:
            v7 = SafeParcelReader.readFloat(arg15, v1);
            continue;
        label_35:
            v6 = SafeParcelReader.readInt(arg15, v1);
            continue;
        label_37:
            v5 = SafeParcelReader.readInt(arg15, v1);
            continue;
        label_22:
            v12_1 = SafeParcelReader.createTypedList(arg15, v1, PatternItem.CREATOR);
            continue;
        label_39:
            v4 = SafeParcelReader.readFloat(arg15, v1);
            continue;
        label_41:
            SafeParcelReader.readList(arg15, v1, ((List)v3), this.getClass().getClassLoader());
            continue;
        label_25:
            v11 = SafeParcelReader.readInt(arg15, v1);
            continue;
        label_27:
            v10 = SafeParcelReader.readBoolean(arg15, v1);
            continue;
        label_45:
            ArrayList v2_1 = SafeParcelReader.createTypedList(arg15, v1, LatLng.CREATOR);
            continue;
        label_29:
            v9 = SafeParcelReader.readBoolean(arg15, v1);
            continue;
        label_31:
            v8 = SafeParcelReader.readBoolean(arg15, v1);
        }

        SafeParcelReader.ensureAtEnd(arg15, v0);
        return new PolygonOptions(v2, ((List)v3), v4, v5, v6, v7, v8, v9, v10, v11, ((List)v12_1));
    }

    public final Object[] newArray(int arg1) {
        return new PolygonOptions[arg1];
    }
}

