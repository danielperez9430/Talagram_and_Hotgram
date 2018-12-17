package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class RecordConsentRequestCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public RecordConsentRequestCreator() {
        super();
    }

    public RecordConsentRequest createFromParcel(Parcel arg8) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        Account v1 = null;
        int v2 = 0;
        Scope[] v3 = ((Scope[])v1);
        String v4 = ((String)v3);
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_20;
                }
                case 2: {
                    goto label_17;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_17:
            v1_1 = SafeParcelReader.createParcelable(arg8, v5, Account.CREATOR);
            continue;
        label_20:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_14:
            Object[] v3_1 = SafeParcelReader.createTypedArray(arg8, v5, Scope.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new RecordConsentRequest(v2, ((Account)v1_1), v3, v4);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public RecordConsentRequest[] newArray(int arg1) {
        return new RecordConsentRequest[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

