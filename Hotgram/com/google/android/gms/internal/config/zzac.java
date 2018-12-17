package com.google.android.gms.internal.config;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzac implements Parcelable$Creator {
    public zzac() {
        super();
    }

    public final Object createFromParcel(Parcel arg20) {
        ArrayList v16_1;
        Parcelable v10_1;
        Parcel v0 = arg20;
        int v1 = SafeParcelReader.validateObjectHeader(arg20);
        String v7 = null;
        DataHolder v10 = ((DataHolder)v7);
        String v11 = ((String)v10);
        String v12 = v11;
        String v13 = v12;
        List v14 = ((List)v13);
        List v16 = v14;
        long v8 = 0;
        int v15 = 0;
        int v17 = 0;
        int v18 = 0;
        while(arg20.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg20);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_46;
                }
                case 3: {
                    goto label_44;
                }
                case 4: {
                    goto label_40;
                }
                case 5: {
                    goto label_38;
                }
                case 6: {
                    goto label_36;
                }
                case 7: {
                    goto label_34;
                }
                case 8: {
                    goto label_32;
                }
                case 9: {
                    goto label_30;
                }
                case 10: {
                    goto label_27;
                }
                case 11: {
                    goto label_25;
                }
                case 12: {
                    goto label_23;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_36:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_38:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_23:
            v18 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_40:
            v10_1 = SafeParcelReader.createParcelable(v0, v2, DataHolder.CREATOR);
            continue;
        label_25:
            v17 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_27:
            v16_1 = SafeParcelReader.createTypedList(v0, v2, zzl.CREATOR);
            continue;
        label_44:
            v8 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_46:
            v7 = SafeParcelReader.createString(v0, v2);
            continue;
        label_30:
            v15 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_32:
            ArrayList v14_1 = SafeParcelReader.createStringList(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zzab(v7, v8, ((DataHolder)v10_1), v11, v12, v13, v14, v15, ((List)v16_1), v17, v18);
    }

    public final Object[] newArray(int arg1) {
        return new zzab[arg1];
    }
}

