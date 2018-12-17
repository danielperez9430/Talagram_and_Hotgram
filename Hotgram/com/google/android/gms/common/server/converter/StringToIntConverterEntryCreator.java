package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class StringToIntConverterEntryCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public StringToIntConverterEntryCreator() {
        super();
    }

    public Entry createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        int v1 = 0;
        String v2 = null;
        int v3 = 0;
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_15;
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
            v3 = SafeParcelReader.readInt(arg7, v4);
            continue;
        label_13:
            v2 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_15:
            v1 = SafeParcelReader.readInt(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new Entry(v1, v2, v3);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public Entry[] newArray(int arg1) {
        return new Entry[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

