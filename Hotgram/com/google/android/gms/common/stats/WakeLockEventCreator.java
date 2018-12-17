package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public class WakeLockEventCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public WakeLockEventCreator() {
        super();
    }

    public WakeLockEvent createFromParcel(Parcel arg26) {
        ArrayList v14_1;
        Parcel v0 = arg26;
        int v1 = SafeParcelReader.validateObjectHeader(arg26);
        long v9 = 0;
        long v16 = v9;
        long v22 = v16;
        String v12 = null;
        List v14 = ((List)v12);
        String v15 = ((String)v14);
        String v19 = v15;
        String v20 = v19;
        String v24 = v20;
        int v8 = 0;
        int v11 = 0;
        int v13 = 0;
        int v18 = 0;
        float v21;
        for(v21 = 0f; arg26.dataPosition() < v1; v21 = SafeParcelReader.readFloat(v0, v2)) {
            int v2 = SafeParcelReader.readHeader(arg26);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_53;
                }
                case 2: {
                    goto label_51;
                }
                case 4: {
                    goto label_49;
                }
                case 5: {
                    goto label_47;
                }
                case 6: {
                    goto label_45;
                }
                case 8: {
                    goto label_43;
                }
                case 10: {
                    goto label_41;
                }
                case 11: {
                    goto label_39;
                }
                case 12: {
                    goto label_37;
                }
                case 13: {
                    goto label_35;
                }
                case 14: {
                    goto label_33;
                }
                case 15: {
                    goto label_31;
                }
                case 16: {
                    goto label_29;
                }
                case 17: {
                    goto label_27;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v18 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_35:
            v20 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_39:
            v11 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_41:
            v19 = SafeParcelReader.createString(v0, v2);
            continue;
        label_43:
            v16 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_45:
            v14_1 = SafeParcelReader.createStringList(v0, v2);
            continue;
        label_47:
            v13 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_49:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_51:
            v9 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_53:
            v8 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_27:
            v24 = SafeParcelReader.createString(v0, v2);
            continue;
        label_29:
            v22 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_31:
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new WakeLockEvent(v8, v9, v11, v12, v13, ((List)v14_1), v15, v16, v18, v19, v20, v21, v22, v24);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public WakeLockEvent[] newArray(int arg1) {
        return new WakeLockEvent[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

