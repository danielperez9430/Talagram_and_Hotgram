package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

public final class zzo implements Parcelable$Creator {
    public zzo() {
        super();
    }

    public final Object createFromParcel(Parcel arg17) {
        Parcelable v6_1;
        Parcel v0 = arg17;
        int v1 = SafeParcelReader.validateObjectHeader(arg17);
        long v10 = 0;
        long v13 = v10;
        CommonWalletObject v6 = null;
        String v7 = ((String)v6);
        String v8 = v7;
        String v9 = v8;
        String v12 = v9;
        String v15 = v12;
        while(arg17.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg17);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_33;
                }
                case 3: {
                    goto label_31;
                }
                case 4: {
                    goto label_29;
                }
                case 5: {
                    goto label_27;
                }
                case 6: {
                    goto label_25;
                }
                case 7: {
                    goto label_23;
                }
                case 8: {
                    goto label_21;
                }
                case 9: {
                    goto label_19;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v6_1 = SafeParcelReader.createParcelable(v0, v2, CommonWalletObject.CREATOR);
            continue;
        label_19:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_21:
            v13 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_23:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_25:
            v10 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_27:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_29:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_31:
            v7 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new GiftCardWalletObject(((CommonWalletObject)v6_1), v7, v8, v9, v10, v12, v13, v15);
    }

    public final Object[] newArray(int arg1) {
        return new GiftCardWalletObject[arg1];
    }
}

