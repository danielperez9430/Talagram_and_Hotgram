package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzam implements Parcelable$Creator {
    public zzam() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        List v2 = null;
        String v3 = "";
        PendingIntent v1 = ((PendingIntent)v2);
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_17;
                }
                case 2: {
                    goto label_14;
                }
                case 3: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_17:
            ArrayList v2_1 = SafeParcelReader.createStringList(arg7, v4);
            continue;
        label_12:
            v3 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_14:
            v1_1 = SafeParcelReader.createParcelable(arg7, v4, PendingIntent.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new zzal(v2, ((PendingIntent)v1_1), v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzal[arg1];
    }
}

