package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.identity.intents.model.UserAddress;

public final class zzk implements Parcelable$Creator {
    public zzk() {
        super();
    }

    public final Object createFromParcel(Parcel arg15) {
        Parcelable v10_1;
        Parcelable v11_1;
        Object[] v12_1;
        Parcelable v8_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg15);
        String v3 = null;
        String v4 = v3;
        ProxyCard v5 = ((ProxyCard)v4);
        String v6 = ((String)v5);
        zza v7 = ((zza)v6);
        zza v8 = v7;
        String[] v9 = ((String[])v8);
        UserAddress v10 = ((UserAddress)v9);
        UserAddress v11 = v10;
        InstrumentInfo[] v12 = ((InstrumentInfo[])v11);
        PaymentMethodToken v13 = ((PaymentMethodToken)v12);
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
                    goto label_48;
                }
                case 5: {
                    goto label_46;
                }
                case 6: {
                    goto label_42;
                }
                case 7: {
                    goto label_38;
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
        label_52:
            v4 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_36:
            v9 = SafeParcelReader.createStringArray(arg15, v1);
            continue;
        label_20:
            Parcelable v13_1 = SafeParcelReader.createParcelable(arg15, v1, PaymentMethodToken.CREATOR);
            continue;
        label_54:
            v3 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_38:
            v8_1 = SafeParcelReader.createParcelable(arg15, v1, zza.CREATOR);
            continue;
        label_24:
            v12_1 = SafeParcelReader.createTypedArray(arg15, v1, InstrumentInfo.CREATOR);
            continue;
        label_42:
            Parcelable v7_1 = SafeParcelReader.createParcelable(arg15, v1, zza.CREATOR);
            continue;
        label_28:
            v11_1 = SafeParcelReader.createParcelable(arg15, v1, UserAddress.CREATOR);
            continue;
        label_46:
            v6 = SafeParcelReader.createString(arg15, v1);
            continue;
        label_48:
            Parcelable v5_1 = SafeParcelReader.createParcelable(arg15, v1, ProxyCard.CREATOR);
            continue;
        label_32:
            v10_1 = SafeParcelReader.createParcelable(arg15, v1, UserAddress.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg15, v0);
        return new FullWallet(v3, v4, v5, v6, v7, ((zza)v8_1), v9, ((UserAddress)v10_1), ((UserAddress)v11_1), ((InstrumentInfo[])v12_1), v13);
    }

    public final Object[] newArray(int arg1) {
        return new FullWallet[arg1];
    }
}

