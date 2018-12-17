package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.ConverterWrapper;

public class FieldCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public FieldCreator() {
        super();
    }

    public Field createFromParcel(Parcel arg14) {
        Parcelable v12_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        String v9 = null;
        String v11 = v9;
        ConverterWrapper v12 = ((ConverterWrapper)v11);
        int v4 = 0;
        int v5 = 0;
        boolean v6 = false;
        int v7 = 0;
        boolean v8 = false;
        int v10 = 0;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_37;
                }
                case 2: {
                    goto label_35;
                }
                case 3: {
                    goto label_33;
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
                    goto label_25;
                }
                case 8: {
                    goto label_23;
                }
                case 9: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_33:
            v6 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_35:
            v5 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_19:
            v12_1 = SafeParcelReader.createParcelable(arg14, v1, ConverterWrapper.CREATOR);
            continue;
        label_37:
            v4 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_23:
            v11 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_25:
            v10 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_27:
            v9 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_29:
            v8 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_31:
            v7 = SafeParcelReader.readInt(arg14, v1);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new Field(v4, v5, v6, v7, v8, v9, v10, v11, ((ConverterWrapper)v12_1));
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public Field[] newArray(int arg1) {
        return new Field[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

