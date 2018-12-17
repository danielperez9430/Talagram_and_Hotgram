package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzn implements Parcelable$Creator {
    public zzn() {
        super();
    }

    public final Object createFromParcel(Parcel arg9) {
        int v0 = SafeParcelReader.validateObjectHeader(arg9);
        String v3 = null;
        String v4 = v3;
        TimeInterval v5 = ((TimeInterval)v4);
        UriData v6 = ((UriData)v5);
        UriData v7 = v6;
        while(arg9.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg9);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_28;
                }
                case 3: {
                    goto label_26;
                }
                case 4: {
                    goto label_22;
                }
                case 5: {
                    goto label_18;
                }
                case 6: {
                    goto label_14;
                }
            }

            SafeParcelReader.skipUnknownField(arg9, v1);
            continue;
        label_18:
            Parcelable v6_1 = SafeParcelReader.createParcelable(arg9, v1, UriData.CREATOR);
            continue;
        label_22:
            Parcelable v5_1 = SafeParcelReader.createParcelable(arg9, v1, TimeInterval.CREATOR);
            continue;
        label_26:
            v4 = SafeParcelReader.createString(arg9, v1);
            continue;
        label_28:
            v3 = SafeParcelReader.createString(arg9, v1);
            continue;
        label_14:
            Parcelable v7_1 = SafeParcelReader.createParcelable(arg9, v1, UriData.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg9, v0);
        return new WalletObjectMessage(v3, v4, v5, v6, v7);
    }

    public final Object[] newArray(int arg1) {
        return new WalletObjectMessage[arg1];
    }
}

