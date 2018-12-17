package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ClientIdentityCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ClientIdentityCreator() {
        super();
    }

    public ClientIdentity createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        String v2 = null;
        while(arg6.dataPosition() < v0) {
            int v3 = SafeParcelReader.readHeader(arg6);
            switch(SafeParcelReader.getFieldId(v3)) {
                case 1: {
                    goto label_12;
                }
                case 2: {
                    goto label_10;
                }
            }

            SafeParcelReader.skipUnknownField(arg6, v3);
            continue;
        label_10:
            v2 = SafeParcelReader.createString(arg6, v3);
            continue;
        label_12:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new ClientIdentity(v1, v2);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ClientIdentity[] newArray(int arg1) {
        return new ClientIdentity[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

