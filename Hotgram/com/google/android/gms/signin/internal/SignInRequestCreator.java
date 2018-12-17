package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInRequestCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public SignInRequestCreator() {
        super();
    }

    public SignInRequest createFromParcel(Parcel arg6) {
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        int v1 = 0;
        ResolveAccountRequest v2 = null;
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
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg6, v3, ResolveAccountRequest.CREATOR);
            continue;
        label_13:
            v1 = SafeParcelReader.readInt(arg6, v3);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new SignInRequest(v1, v2);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public SignInRequest[] newArray(int arg1) {
        return new SignInRequest[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

