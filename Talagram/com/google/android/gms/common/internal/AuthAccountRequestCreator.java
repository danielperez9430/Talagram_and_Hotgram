package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class AuthAccountRequestCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public AuthAccountRequestCreator() {
        super();
    }

    public AuthAccountRequest createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        IBinder v5 = null;
        Scope[] v6 = ((Scope[])v5);
        Integer v7 = ((Integer)v6);
        Integer v8 = v7;
        Account v9 = ((Account)v8);
        int v4 = 0;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_30;
                }
                case 2: {
                    goto label_28;
                }
                case 3: {
                    goto label_24;
                }
                case 4: {
                    goto label_22;
                }
                case 5: {
                    goto label_20;
                }
                case 6: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_20:
            v8 = SafeParcelReader.readIntegerObject(arg11, v1);
            continue;
        label_22:
            v7 = SafeParcelReader.readIntegerObject(arg11, v1);
            continue;
        label_24:
            Object[] v6_1 = SafeParcelReader.createTypedArray(arg11, v1, Scope.CREATOR);
            continue;
        label_28:
            v5 = SafeParcelReader.readIBinder(arg11, v1);
            continue;
        label_30:
            v4 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_16:
            Parcelable v9_1 = SafeParcelReader.createParcelable(arg11, v1, Account.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new AuthAccountRequest(v4, v5, v6, v7, v8, v9);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public AuthAccountRequest[] newArray(int arg1) {
        return new AuthAccountRequest[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

