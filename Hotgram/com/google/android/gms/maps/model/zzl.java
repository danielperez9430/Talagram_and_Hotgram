package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzl implements Parcelable$Creator {
    public zzl() {
        super();
    }

    public final Object createFromParcel(Parcel arg18) {
        Parcelable v13_1;
        ArrayList v16_1;
        Parcel v0 = arg18;
        int v1 = SafeParcelReader.validateObjectHeader(arg18);
        List v6 = null;
        Cap v13 = ((Cap)v6);
        Cap v14 = v13;
        List v16 = ((List)v14);
        float v7 = 0f;
        int v8 = 0;
        float v9 = 0f;
        boolean v10 = false;
        boolean v11 = false;
        boolean v12 = false;
        int v15 = 0;
        while(arg18.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg18);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_48;
                }
                case 3: {
                    goto label_46;
                }
                case 4: {
                    goto label_44;
                }
                case 5: {
                    goto label_42;
                }
                case 6: {
                    goto label_40;
                }
                case 7: {
                    goto label_38;
                }
                case 8: {
                    goto label_36;
                }
                case 9: {
                    goto label_32;
                }
                case 10: {
                    goto label_28;
                }
                case 11: {
                    goto label_26;
                }
                case 12: {
                    goto label_23;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_36:
            v12 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_38:
            v11 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_23:
            v16_1 = SafeParcelReader.createTypedList(v0, v2, PatternItem.CREATOR);
            continue;
        label_40:
            v10 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_42:
            v9 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_26:
            v15 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_44:
            v8 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_28:
            Parcelable v14_1 = SafeParcelReader.createParcelable(v0, v2, Cap.CREATOR);
            continue;
        label_46:
            v7 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_48:
            ArrayList v6_1 = SafeParcelReader.createTypedList(v0, v2, LatLng.CREATOR);
            continue;
        label_32:
            v13_1 = SafeParcelReader.createParcelable(v0, v2, Cap.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new PolylineOptions(v6, v7, v8, v9, v10, v11, v12, ((Cap)v13_1), v14, v15, ((List)v16_1));
    }

    public final Object[] newArray(int arg1) {
        return new PolylineOptions[arg1];
    }
}

