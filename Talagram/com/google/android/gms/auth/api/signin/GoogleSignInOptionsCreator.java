package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public class GoogleSignInOptionsCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public GoogleSignInOptionsCreator() {
        super();
    }

    public GoogleSignInOptions createFromParcel(Parcel arg14) {
        Parcelable v6_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg14);
        ArrayList v5 = null;
        Account v6 = ((Account)v5);
        String v10 = ((String)v6);
        String v11 = v10;
        ArrayList v12 = ((ArrayList)v11);
        int v4 = 0;
        boolean v7 = false;
        boolean v8 = false;
        boolean v9 = false;
        while(arg14.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg14);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_39;
                }
                case 2: {
                    goto label_36;
                }
                case 3: {
                    goto label_32;
                }
                case 4: {
                    goto label_30;
                }
                case 5: {
                    goto label_28;
                }
                case 6: {
                    goto label_26;
                }
                case 7: {
                    goto label_24;
                }
                case 8: {
                    goto label_22;
                }
                case 9: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(arg14, v1);
            continue;
        label_19:
            v12 = SafeParcelReader.createTypedList(arg14, v1, GoogleSignInOptionsExtensionParcelable.CREATOR);
            continue;
        label_36:
            v5 = SafeParcelReader.createTypedList(arg14, v1, Scope.CREATOR);
            continue;
        label_22:
            v11 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_39:
            v4 = SafeParcelReader.readInt(arg14, v1);
            continue;
        label_24:
            v10 = SafeParcelReader.createString(arg14, v1);
            continue;
        label_26:
            v9 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_28:
            v8 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_30:
            v7 = SafeParcelReader.readBoolean(arg14, v1);
            continue;
        label_32:
            v6_1 = SafeParcelReader.createParcelable(arg14, v1, Account.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg14, v0);
        return new GoogleSignInOptions(v4, v5, ((Account)v6_1), v7, v8, v9, v10, v11, v12);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public GoogleSignInOptions[] newArray(int arg1) {
        return new GoogleSignInOptions[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

