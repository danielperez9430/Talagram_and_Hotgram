package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        String v2 = null;
        ArrayList v3 = ArrayUtils.newArrayList();
        String v1;
        for(v1 = v2; arg7.dataPosition() < v0; v1 = SafeParcelReader.createString(arg7, v4)) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 2: {
                    goto label_17;
                }
                case 3: {
                    goto label_15;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_17:
            v2 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_12:
            v3 = SafeParcelReader.createTypedList(arg7, v4, LabelValue.CREATOR);
            continue;
        label_15:
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new LabelValueRow(v2, v1, v3);
    }

    public final Object[] newArray(int arg1) {
        return new LabelValueRow[arg1];
    }
}

