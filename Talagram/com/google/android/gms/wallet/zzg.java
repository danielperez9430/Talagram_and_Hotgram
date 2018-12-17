package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

public final class zzg implements Parcelable$Creator {
    public zzg() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        String v2 = null;
        ArrayList v3 = new ArrayList();
        String v1;
        for(v1 = v2; arg7.dataPosition() < v0; v1 = SafeParcelReader.createString(arg7, v4)) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_18;
                }
                case 3: {
                    goto label_16;
                }
                case 4: {
                    goto label_13;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_18:
            v2 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_13:
            v3 = SafeParcelReader.createTypedList(arg7, v4, LineItem.CREATOR);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new Cart(v2, v1, v3);
    }

    public final Object[] newArray(int arg1) {
        return new Cart[arg1];
    }
}

