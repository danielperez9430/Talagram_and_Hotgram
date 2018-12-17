package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg14) {
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        String v4 = null;
        List v5 = ((List)v4);
        String v7 = ((String)v5);
        List v8 = ((List)v7);
        String v9 = ((String)v8);
        List v10 = ((List)v9);
        String v11 = ((String)v10);
        List v12 = ((List)v11);
        int v6 = 0;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_38;
                }
                case 2: {
                    goto label_36;
                }
                case 3: {
                    goto label_34;
                }
                case 4: {
                    goto label_31;
                }
                case 5: {
                    goto label_29;
                }
                case 6: {
                    goto label_27;
                }
                case 7: {
                    goto label_24;
                }
                case 8: {
                    goto label_22;
                }
                case 9: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_34:
            ArrayList v5_1 = SafeParcelReader.createIntegerList(arg14, v1);
            continue;
        label_19:
            ArrayList v12_1 = SafeParcelReader.createTypedList(arg14, v1, zzc.CREATOR);
            continue;
        label_36:
            v4 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_38:
            v7 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_22:
            v11 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_24:
            ArrayList v10_1 = SafeParcelReader.createTypedList(arg14, v1, zzc.CREATOR);
            continue;
        label_27:
            v9 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_29:
            v6 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_31:
            ArrayList v8_1 = SafeParcelReader.createTypedList(arg14, v1, zzc.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new zzb(v4, v5, v6, v7, v8, v9, v10, v11, v12);
    }

    public final Object[] newArray(int arg1) {
        return new zzb[arg1];
    }
}

