package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class ResolveAccountResponseCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public ResolveAccountResponseCreator() {
        super();
    }

    public ResolveAccountResponse createFromParcel(Parcel arg10) {
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        IBinder v5 = null;
        ConnectionResult v6 = ((ConnectionResult)v5);
        int v4 = 0;
        boolean v7 = false;
        boolean v8;
        for(v8 = false; arg10.dataPosition() < v0; v8 = SafeParcelReader.readBoolean(arg10, v1)) {
            int v1 = SafeParcelReader.readHeader(arg10);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_19;
                }
                case 4: {
                    goto label_17;
                }
                case 5: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg10, v1);
            continue;
        label_17:
            v7 = SafeParcelReader.readBoolean(arg10, v1);
            continue;
        label_19:
            Parcelable v6_1 = SafeParcelReader.createParcelable(arg10, v1, ConnectionResult.CREATOR);
            continue;
        label_23:
            v5 = SafeParcelReader.readIBinder(arg10, v1);
            continue;
        label_25:
            v4 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        return new ResolveAccountResponse(v4, v5, v6, v7, v8);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public ResolveAccountResponse[] newArray(int arg1) {
        return new ResolveAccountResponse[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

