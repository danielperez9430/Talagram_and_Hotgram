package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzj implements Parcelable$Creator {
    public zzj() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v3_1;
        Parcelable v2_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        LoyaltyWalletObject v1 = null;
        OfferWalletObject v2 = ((OfferWalletObject)v1);
        GiftCardWalletObject v3 = ((GiftCardWalletObject)v2);
        int v4 = 0;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_21;
                }
                case 3: {
                    goto label_18;
                }
                case 4: {
                    goto label_15;
                }
                case 5: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            v2_1 = SafeParcelReader.createParcelable(arg8, v5, OfferWalletObject.CREATOR);
            continue;
        label_21:
            Parcelable v1_1 = SafeParcelReader.createParcelable(arg8, v5, LoyaltyWalletObject.CREATOR);
            continue;
        label_13:
            v4 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_15:
            v3_1 = SafeParcelReader.createParcelable(arg8, v5, GiftCardWalletObject.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new CreateWalletObjectsRequest(v1, ((OfferWalletObject)v2_1), ((GiftCardWalletObject)v3_1), v4);
    }

    public final Object[] newArray(int arg1) {
        return new CreateWalletObjectsRequest[arg1];
    }
}

