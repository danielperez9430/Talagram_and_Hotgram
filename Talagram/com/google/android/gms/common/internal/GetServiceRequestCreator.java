package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class GetServiceRequestCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public GetServiceRequestCreator() {
        super();
    }

    public GetServiceRequest createFromParcel(Parcel arg17) {
        Object[] v14_1;
        Parcel v0 = arg17;
        int v1 = SafeParcelReader.validateObjectHeader(arg17);
        String v8 = null;
        IBinder v9 = ((IBinder)v8);
        Scope[] v10 = ((Scope[])v9);
        Bundle v11 = ((Bundle)v10);
        Account v12 = ((Account)v11);
        Feature[] v13 = ((Feature[])v12);
        Feature[] v14 = v13;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        boolean v15 = false;
        while(arg17.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg17);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_50;
                }
                case 2: {
                    goto label_48;
                }
                case 3: {
                    goto label_46;
                }
                case 4: {
                    goto label_44;
                }
                case 5: {
                    goto label_42;
                }
                case 6: {
                    goto label_38;
                }
                case 7: {
                    goto label_36;
                }
                case 8: {
                    goto label_32;
                }
                case 10: {
                    goto label_28;
                }
                case 11: {
                    goto label_24;
                }
                case 12: {
                    goto label_22;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_50:
            v5 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_36:
            v11 = SafeParcelReader.createBundle(v0, v2);
            continue;
        label_38:
            Object[] v10_1 = SafeParcelReader.createTypedArray(v0, v2, Scope.CREATOR);
            continue;
        label_22:
            v15 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_24:
            v14_1 = SafeParcelReader.createTypedArray(v0, v2, Feature.CREATOR);
            continue;
        label_42:
            v9 = SafeParcelReader.readIBinder(v0, v2);
            continue;
        label_44:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_28:
            Object[] v13_1 = SafeParcelReader.createTypedArray(v0, v2, Feature.CREATOR);
            continue;
        label_46:
            v7 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_48:
            v6 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_32:
            Parcelable v12_1 = SafeParcelReader.createParcelable(v0, v2, Account.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new GetServiceRequest(v5, v6, v7, v8, v9, v10, v11, v12, v13, ((Feature[])v14_1), v15);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public GetServiceRequest[] newArray(int arg1) {
        return new GetServiceRequest[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

