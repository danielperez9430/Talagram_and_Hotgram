package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzaf implements Parcelable$Creator {
    public zzaf() {
        super();
    }

    public final Object createFromParcel(Parcel arg17) {
        Parcelable v12_1;
        Parcelable v8_1;
        Parcelable v10_1;
        Parcel v0 = arg17;
        int v1 = SafeParcelReader.validateObjectHeader(arg17);
        CardRequirements v8 = null;
        ShippingAddressRequirements v10 = ((ShippingAddressRequirements)v8);
        ArrayList v11 = ((ArrayList)v10);
        PaymentMethodTokenizationParameters v12 = ((PaymentMethodTokenizationParameters)v11);
        TransactionInfo v13 = ((TransactionInfo)v12);
        String v15 = ((String)v13);
        boolean v6 = false;
        boolean v7 = false;
        boolean v9 = false;
        boolean v14 = true;
        while(arg17.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg17);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_48;
                }
                case 2: {
                    goto label_46;
                }
                case 3: {
                    goto label_42;
                }
                case 4: {
                    goto label_40;
                }
                case 5: {
                    goto label_36;
                }
                case 6: {
                    goto label_34;
                }
                case 7: {
                    goto label_30;
                }
                case 8: {
                    goto label_26;
                }
                case 9: {
                    goto label_24;
                }
                case 10: {
                    goto label_22;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v11 = SafeParcelReader.createIntegerList(v0, v2);
            continue;
        label_36:
            v10_1 = SafeParcelReader.createParcelable(v0, v2, ShippingAddressRequirements.CREATOR);
            continue;
        label_22:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_40:
            v9 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_24:
            v14 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_42:
            v8_1 = SafeParcelReader.createParcelable(v0, v2, CardRequirements.CREATOR);
            continue;
        label_26:
            Parcelable v13_1 = SafeParcelReader.createParcelable(v0, v2, TransactionInfo.CREATOR);
            continue;
        label_46:
            v7 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_30:
            v12_1 = SafeParcelReader.createParcelable(v0, v2, PaymentMethodTokenizationParameters.CREATOR);
            continue;
        label_48:
            v6 = SafeParcelReader.readBoolean(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new PaymentDataRequest(v6, v7, ((CardRequirements)v8_1), v9, ((ShippingAddressRequirements)v10_1), v11, ((PaymentMethodTokenizationParameters)v12_1), v13, v14, v15);
    }

    public final Object[] newArray(int arg1) {
        return new PaymentDataRequest[arg1];
    }
}

