package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class FieldMappingDictionaryCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public FieldMappingDictionaryCreator() {
        super();
    }

    public FieldMappingDictionary createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        ArrayList v1 = null;
        int v2 = 0;
        String v3 = ((String)v1);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_16;
                }
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_13:
            v1 = SafeParcelReader.createTypedList(arg7, v4, Entry.CREATOR);
            continue;
        label_16:
            v2 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new FieldMappingDictionary(v2, v1, v3);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public FieldMappingDictionary[] newArray(int arg1) {
        return new FieldMappingDictionary[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

