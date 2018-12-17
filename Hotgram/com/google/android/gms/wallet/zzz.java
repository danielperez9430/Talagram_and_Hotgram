package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzz implements Parcelable$Creator {
    public zzz() {
        super();
    }

    public final Object createFromParcel(Parcel arg24) {
        Parcelable v13_1;
        Parcelable v20_1;
        Parcel v0 = arg24;
        int v1 = SafeParcelReader.validateObjectHeader(arg24);
        String v6 = null;
        String v10 = v6;
        String v11 = v10;
        String v12 = v11;
        Cart v13 = ((Cart)v12);
        CountrySpecification[] v16 = ((CountrySpecification[])v13);
        ArrayList v19 = ((ArrayList)v16);
        PaymentMethodTokenizationParameters v20 = ((PaymentMethodTokenizationParameters)v19);
        ArrayList v21 = ((ArrayList)v20);
        String v22 = ((String)v21);
        boolean v7 = false;
        boolean v8 = false;
        boolean v9 = false;
        boolean v14 = false;
        boolean v15 = false;
        boolean v17 = true;
        boolean v18 = true;
        while(arg24.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg24);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_68;
                }
                case 3: {
                    goto label_66;
                }
                case 4: {
                    goto label_64;
                }
                case 5: {
                    goto label_62;
                }
                case 6: {
                    goto label_60;
                }
                case 7: {
                    goto label_58;
                }
                case 8: {
                    goto label_56;
                }
                case 9: {
                    goto label_52;
                }
                case 10: {
                    goto label_50;
                }
                case 11: {
                    goto label_48;
                }
                case 12: {
                    goto label_44;
                }
                case 13: {
                    goto label_42;
                }
                case 14: {
                    goto label_40;
                }
                case 15: {
                    goto label_37;
                }
                case 16: {
                    goto label_33;
                }
                case 17: {
                    goto label_31;
                }
                case 18: {
                    goto label_29;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v20_1 = SafeParcelReader.createParcelable(v0, v2, PaymentMethodTokenizationParameters.CREATOR);
            continue;
        label_66:
            v7 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_68:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            v19 = SafeParcelReader.createTypedList(v0, v2, com.google.android.gms.identity.intents.model.CountrySpecification.CREATOR);
            continue;
        label_40:
            v18 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_42:
            v17 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_44:
            Object[] v16_1 = SafeParcelReader.createTypedArray(v0, v2, CountrySpecification.CREATOR);
            continue;
        label_48:
            v15 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_50:
            v14 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_52:
            v13_1 = SafeParcelReader.createParcelable(v0, v2, Cart.CREATOR);
            continue;
        label_56:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_58:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_60:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_29:
            v22 = SafeParcelReader.createString(v0, v2);
            continue;
        label_62:
            v9 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_31:
            v21 = SafeParcelReader.createIntegerList(v0, v2);
            continue;
        label_64:
            v8 = SafeParcelReader.readBoolean(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new MaskedWalletRequest(v6, v7, v8, v9, v10, v11, v12, ((Cart)v13_1), v14, v15, v16, v17, v18, v19, ((PaymentMethodTokenizationParameters)v20_1), v21, v22);
    }

    public final Object[] newArray(int arg1) {
        return new MaskedWalletRequest[arg1];
    }
}

