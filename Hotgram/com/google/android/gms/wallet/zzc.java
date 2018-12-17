package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class zzc implements Parcelable$Creator {
    public zzc() {
        super();
    }

    public final Object createFromParcel(Parcel arg10) {
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        String v4 = null;
        String v5 = v4;
        String v6 = v5;
        UserAddress v8 = ((UserAddress)v6);
        int v7 = 0;
        while(arg10.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg10);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_25;
                }
                case 2: {
                    goto label_23;
                }
                case 3: {
                    goto label_21;
                }
                case 4: {
                    goto label_19;
                }
                case 5: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg10, v1);
            continue;
        label_19:
            v7 = SafeParcelReader.readInt(arg10, v1);
            continue;
        label_21:
            v6 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_23:
            v5 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_25:
            v4 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_15:
            Parcelable v8_1 = SafeParcelReader.createParcelable(arg10, v1, UserAddress.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        return new CardInfo(v4, v5, v6, v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new CardInfo[arg1];
    }
}

