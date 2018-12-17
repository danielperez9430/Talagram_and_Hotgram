package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.location.zzj;
import java.util.ArrayList;
import java.util.List;

public final class zzn implements Parcelable$Creator {
    public zzn() {
        super();
    }

    public final Object createFromParcel(Parcel arg7) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg7);
        zzj v1 = zzm.zzce;
        List v2 = zzm.zzcd;
        String v3 = null;
        while(arg7.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg7);
            switch(SafeParcelReader.getFieldId(v4)) {
                case 1: {
                    goto label_16;
                }
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_11;
                }
            }

            SafeParcelReader.skipUnknownField(arg7, v4);
            continue;
        label_11:
            v3 = SafeParcelReader.createString(arg7, v4);
            continue;
        label_13:
            ArrayList v2_1 = SafeParcelReader.createTypedList(arg7, v4, ClientIdentity.CREATOR);
            continue;
        label_16:
            v1_1 = SafeParcelReader.createParcelable(arg7, v4, zzj.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg7, v0);
        return new zzm(((zzj)v1_1), v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzm[arg1];
    }
}

