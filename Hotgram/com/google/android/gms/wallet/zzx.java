package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class zzx implements Parcelable$Creator {
    public zzx() {
        super();
    }

    public final Object createFromParcel(Parcel arg15) {
        Object[] v10_1;
        Parcelable v7_1;
        Parcelable v8_1;
        Object[] v13_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg15);
        String v3 = null;
        String v4 = v3;
        String[] v5 = ((String[])v4);
        String v6 = ((String)v5);
        zza v7 = ((zza)v6);
        zza v8 = v7;
        LoyaltyWalletObject[] v9 = ((LoyaltyWalletObject[])v8);
        OfferWalletObject[] v10 = ((OfferWalletObject[])v9);
        UserAddress v11 = ((UserAddress)v10);
        UserAddress v12 = v11;
        InstrumentInfo[] v13 = ((InstrumentInfo[])v12);
        while(arg15.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg15);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_54;
                }
                case 3: {
                    goto label_52;
                }
                case 4: {
                    goto label_50;
                }
                case 5: {
                    goto label_48;
                }
                case 6: {
                    goto label_44;
                }
                case 7: {
                    goto label_40;
                }
                case 8: {
                    goto label_36;
                }
                case 9: {
                    goto label_32;
                }
                case 10: {
                    goto label_28;
                }
                case 11: {
                    goto label_24;
                }
                case 12: {
                    goto label_20;
                }
            }

            SafeParcelReader.skipUnknownField(arg15, v1);
            continue;
        label_50:
            v5 = SafeParcelReader.createStringArray(arg15, v1);
            continue;
        label_52:
            v4 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_36:
            Object[] v9_1 = SafeParcelReader.createTypedArray(arg15, v1, LoyaltyWalletObject.CREATOR);
            continue;
        label_20:
            v13_1 = SafeParcelReader.createTypedArray(arg15, v1, InstrumentInfo.CREATOR);
            continue;
        label_54:
            v3 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_40:
            v8_1 = SafeParcelReader.createParcelable(arg15, v1, zza.CREATOR);
            continue;
        label_24:
            Parcelable v12_1 = SafeParcelReader.createParcelable(arg15, v1, UserAddress.CREATOR);
            continue;
        label_44:
            v7_1 = SafeParcelReader.createParcelable(arg15, v1, zza.CREATOR);
            continue;
        label_28:
            Parcelable v11_1 = SafeParcelReader.createParcelable(arg15, v1, UserAddress.CREATOR);
            continue;
        label_48:
            v6 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_32:
            v10_1 = SafeParcelReader.createTypedArray(arg15, v1, OfferWalletObject.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg15, v0);
        return new MaskedWallet(v3, v4, v5, v6, ((zza)v7_1), ((zza)v8_1), v9, ((OfferWalletObject[])v10_1), v11, v12, ((InstrumentInfo[])v13_1));
    }

    public final Object[] newArray(int arg1) {
        return new MaskedWallet[arg1];
    }
}

