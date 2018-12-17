package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class zzad implements Parcelable$Creator {
    public zzad() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        Parcelable v6_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        String v3 = null;
        CardInfo v4 = ((CardInfo)v3);
        UserAddress v5 = ((UserAddress)v4);
        PaymentMethodToken v6 = ((PaymentMethodToken)v5);
        String v7 = ((String)v6);
        Bundle v8 = ((Bundle)v7);
        String v9;
        for(v9 = ((String)v8); arg11.dataPosition() < v0; v9 = SafeParcelReader.createString(arg11, v1)) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_34;
                }
                case 2: {
                    goto label_30;
                }
                case 3: {
                    goto label_26;
                }
                case 4: {
                    goto label_22;
                }
                case 5: {
                    goto label_20;
                }
                case 6: {
                    goto label_18;
                }
                case 7: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_34:
            v3 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_18:
            v8 = SafeParcelReader.createBundle(arg11, v1);
            continue;
        label_20:
            v7 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_22:
            v6_1 = SafeParcelReader.createParcelable(arg11, v1, PaymentMethodToken.CREATOR);
            continue;
        label_26:
            Parcelable v5_1 = SafeParcelReader.createParcelable(arg11, v1, UserAddress.CREATOR);
            continue;
        label_30:
            Parcelable v4_1 = SafeParcelReader.createParcelable(arg11, v1, CardInfo.CREATOR);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new PaymentData(v3, v4, v5, ((PaymentMethodToken)v6_1), v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new PaymentData[arg1];
    }
}

