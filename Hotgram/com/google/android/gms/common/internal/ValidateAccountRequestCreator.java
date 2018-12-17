package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ValidateAccountRequestCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ValidateAccountRequestCreator() {
        super();
    }

    public ValidateAccountRequest createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1;
        for(v1 = 0; arg6.dataPosition() < v0; v1 = SafeParcelReader.readInt(arg6, v2)) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 1) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new ValidateAccountRequest(v1);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ValidateAccountRequest[] newArray(int arg1) {
        return new ValidateAccountRequest[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

