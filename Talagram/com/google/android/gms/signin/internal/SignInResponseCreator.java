package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInResponseCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public SignInResponseCreator() {
        super();
    }

    public SignInResponse createFromParcel(Parcel arg7) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        ConnectionResult v1 = null;
        int v2 = 0;
        ResolveAccountResponse v3 = ((ResolveAccountResponse)v1);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_14;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_17:
            v2 = SafeParcelReader.readInt(arg7, v4);
            continue;
        label_11:
            Parcelable v3_1 = SafeParcelReader.createParcelable(arg7, v4, ResolveAccountResponse.CREATOR);
            continue;
        label_14:
            v1_1 = SafeParcelReader.createParcelable(arg7, v4, ConnectionResult.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new SignInResponse(v2, ((ConnectionResult)v1_1), v3);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public SignInResponse[] newArray(int arg1) {
        return new SignInResponse[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

