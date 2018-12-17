package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class zzab implements Parcelable$Creator {
    public zzab() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        int v2 = 0;
        String v3 = v1;
        CommonWalletObject v4 = ((CommonWalletObject)v3);
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_19;
                }
                case 2: {
                    goto label_17;
                }
                case 3: {
                    goto label_15;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_17:
            v1 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_19:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4_1 = SafeParcelReader.createParcelable(arg8, v5, CommonWalletObject.CREATOR);
            continue;
        label_15:
            v3 = SafeParcelReader.createString(arg8, v5);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new OfferWalletObject(v2, v1, v3, ((CommonWalletObject)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new OfferWalletObject[arg1];
    }
}

