package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzr implements Parcelable$Creator {
    public zzr() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        ArrayList v4 = null;
        String v5 = ((String)v4);
        String v6 = v5;
        ArrayList v7 = ((ArrayList)v6);
        String v9 = ((String)v7);
        boolean v8 = false;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            int v2 = SafeParcelReader.getFieldId(v1);
            if(v2 != 2) {
                switch(v2) {
                    case 4: {
                        goto label_26;
                    }
                    case 5: {
                        goto label_24;
                    }
                    case 6: {
                        goto label_22;
                    }
                    case 7: {
                        goto label_20;
                    }
                    case 8: {
                        goto label_18;
                    }
                }

                SafeParcelReader.skipUnknownField(arg11, v1);
                continue;
            label_18:
                v9 = SafeParcelReader.createString(arg11, v1);
                continue;
            label_20:
                v8 = SafeParcelReader.readBoolean(arg11, v1);
                continue;
            label_22:
                v7 = SafeParcelReader.createIntegerList(arg11, v1);
                continue;
            label_24:
                v6 = SafeParcelReader.createString(arg11, v1);
                continue;
            label_26:
                v5 = SafeParcelReader.createString(arg11, v1);
                continue;
            }

            v4 = SafeParcelReader.createIntegerList(arg11, v1);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new IsReadyToPayRequest(v4, v5, v6, v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new IsReadyToPayRequest[arg1];
    }
}

