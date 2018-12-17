package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzi implements Parcelable$Creator {
    public zzi() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v3_1;
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        LoyaltyPointsBalance v2 = ((LoyaltyPointsBalance)v1);
        TimeInterval v3 = ((TimeInterval)v2);
        while(arg8.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg8);
            int v5 = SafeParcelReader.getFieldId(v4);
            if(v5 != 5) {
                switch(v5) {
                    case 2: {
                        goto label_16;
                    }
                    case 3: {
                        goto label_13;
                    }
                }

                SafeParcelReader.skipUnknownField(arg8, v4);
                continue;
            label_13:
                v2_1 = SafeParcelReader.createParcelable(arg8, v4, LoyaltyPointsBalance.CREATOR);
                continue;
            label_16:
                v1 = SafeParcelReader.createString(arg8, v4);
                continue;
            }

            v3_1 = SafeParcelReader.createParcelable(arg8, v4, TimeInterval.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new LoyaltyPoints(v1, ((LoyaltyPointsBalance)v2_1), ((TimeInterval)v3_1));
    }

    public final Object[] newArray(int arg1) {
        return new LoyaltyPoints[arg1];
    }
}

