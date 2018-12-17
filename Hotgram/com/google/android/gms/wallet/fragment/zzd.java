package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String v1 = null;
        MaskedWalletRequest v2 = ((MaskedWalletRequest)v1);
        MaskedWallet v4 = ((MaskedWallet)v2);
        int v3;
        for(v3 = -1; arg8.dataPosition() < v0; v3 = SafeParcelReader.readInt(arg8, v5)) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_21;
                }
                case 3: {
                    goto label_18;
                }
                case 4: {
                    goto label_16;
                }
                case 5: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg8, v5, MaskedWalletRequest.CREATOR);
            continue;
        label_21:
            v1 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_13:
            v4_1 = SafeParcelReader.createParcelable(arg8, v5, MaskedWallet.CREATOR);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new WalletFragmentInitParams(v1, v2, v3, ((MaskedWallet)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new WalletFragmentInitParams[arg1];
    }
}

