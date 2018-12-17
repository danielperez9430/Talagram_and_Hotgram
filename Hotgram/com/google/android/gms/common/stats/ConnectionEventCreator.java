package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConnectionEventCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ConnectionEventCreator() {
        super();
    }

    public ConnectionEvent createFromParcel(Parcel arg22) {
        Parcel v0 = arg22;
        int v1 = SafeParcelReader.validateObjectHeader(arg22);
        long v8 = 0;
        long v17 = v8;
        long v19 = v17;
        String v11 = null;
        String v12 = v11;
        String v13 = v12;
        String v14 = v13;
        String v15 = v14;
        String v16 = v15;
        int v7 = 0;
        int v10 = 0;
        while(arg22.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg22);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_43;
                }
                case 2: {
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
                case 10: {
                    goto label_29;
                }
                case 11: {
                    goto label_27;
                }
                case 12: {
                    goto label_25;
                }
                case 13: {
                    goto label_23;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_35:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_39:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_23:
            v16 = SafeParcelReader.createString(v0, v2);
            continue;
        label_41:
            v8 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_25:
            v10 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_43:
            v7 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_27:
            v19 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_29:
            v17 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_31:
            v15 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new ConnectionEvent(v7, v8, v10, v11, v12, v13, v14, v15, v16, v17, v19);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ConnectionEvent[] newArray(int arg1) {
        return new ConnectionEvent[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

