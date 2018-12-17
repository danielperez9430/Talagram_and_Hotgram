package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable$Creator {
    public zzh() {
        super();
    }

    public final Object createFromParcel(Parcel arg19) {
        Parcel v0 = arg19;
        int v1 = SafeParcelReader.validateObjectHeader(arg19);
        String v11 = null;
        String v14 = v11;
        double v12 = 0;
        long v15 = 0;
        int v10 = 0;
        int v17 = -1;
        while(arg19.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg19);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_30;
                }
                case 3: {
                    goto label_28;
                }
                case 4: {
                    goto label_26;
                }
                case 5: {
                    goto label_24;
                }
                case 6: {
                    goto label_22;
                }
                case 7: {
                    goto label_20;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_20:
            v17 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_22:
            v15 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_24:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_26:
            v12 = SafeParcelReader.readDouble(v0, v2);
            continue;
        label_28:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_30:
            v10 = SafeParcelReader.readInt(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new LoyaltyPointsBalance(v10, v11, v12, v14, v15, v17);
    }

    public final Object[] newArray(int arg1) {
        return new LoyaltyPointsBalance[arg1];
    }
}

