package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public class CheckServerAuthResultCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public CheckServerAuthResultCreator() {
        super();
    }

    public CheckServerAuthResult createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        int v1 = 0;
        List v3 = null;
        boolean v2;
        for(v2 = false; arg7.dataPosition() < v0; v2 = SafeParcelReader.readBoolean(arg7, v4)) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_15;
                }
                case 3: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_17:
            v1 = SafeParcelReader.readInt(arg7, v4);
            continue;
        label_12:
            ArrayList v3_1 = SafeParcelReader.createTypedList(arg7, v4, Scope.CREATOR);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new CheckServerAuthResult(v1, v2, v3);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public CheckServerAuthResult[] newArray(int arg1) {
        return new CheckServerAuthResult[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

