package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzf implements Parcelable$Creator {
    public zzf() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        int v1 = 1;
        int v2 = 0;
        WalletFragmentStyle v3 = null;
        int v4 = 1;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_19;
                }
                case 3: {
                    goto label_17;
                }
                case 4: {
                    goto label_14;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_17:
            v2 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_19:
            v1 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_14:
            Parcelable v3_1 = SafeParcelReader.createParcelable(arg8, v5, WalletFragmentStyle.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new WalletFragmentOptions(v1, v2, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new WalletFragmentOptions[arg1];
    }
}

