package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable$Creator {
    public zzm() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        Parcelable v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        String v1 = null;
        String v2 = v1;
        Cart v3 = ((Cart)v2);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_16;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3_1 = SafeParcelReader.createParcelable(arg7, v4, Cart.CREATOR);
            continue;
        label_14:
            v2 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_16:
            v1 = SafeParcelReader.createString(arg7, v4);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new FullWalletRequest(v1, v2, ((Cart)v3_1));
    }

    public final Object[] newArray(int arg1) {
        return new FullWalletRequest[arg1];
    }
}

