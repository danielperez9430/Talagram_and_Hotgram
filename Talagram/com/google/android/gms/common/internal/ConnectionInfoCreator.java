package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ConnectionInfoCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ConnectionInfoCreator() {
        super();
    }

    public ConnectionInfo createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        Bundle v1 = null;
        Feature[] v2 = ((Feature[])v1);
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
            Object[] v2_1 = SafeParcelReader.createTypedArray(arg6, v3, Feature.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.createBundle(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new ConnectionInfo(v1, v2);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ConnectionInfo[] newArray(int arg1) {
        return new ConnectionInfo[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

