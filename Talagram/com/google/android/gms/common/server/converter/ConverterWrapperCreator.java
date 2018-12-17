package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConverterWrapperCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ConverterWrapperCreator() {
        super();
    }

    public ConverterWrapper createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        StringToIntConverter v2 = null;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 1: {
                    goto label_13;
                }
                case 2: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg6, v3, StringToIntConverter.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new ConverterWrapper(v1, v2);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ConverterWrapper[] newArray(int arg1) {
        return new ConverterWrapper[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

