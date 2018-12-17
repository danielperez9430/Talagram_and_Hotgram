package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class SignInButtonConfigCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public SignInButtonConfigCreator() {
        super();
    }

    public SignInButtonConfig createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        int v1 = 0;
        Scope[] v4 = null;
        int v2 = 0;
        int v3;
        for(v3 = 0; arg8.dataPosition() < v0; v3 = SafeParcelReader.readInt(arg8, v5)) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_20;
                }
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_20:
            v1 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_13:
            Object[] v4_1 = SafeParcelReader.createTypedArray(arg8, v5, Scope.CREATOR);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new SignInButtonConfig(v1, v2, v3, v4);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public SignInButtonConfig[] newArray(int arg1) {
        return new SignInButtonConfig[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

