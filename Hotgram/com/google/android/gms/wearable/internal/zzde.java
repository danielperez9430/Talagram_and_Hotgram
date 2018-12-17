package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzde implements Parcelable$Creator {
    public zzde() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        Uri v1 = null;
        Bundle v2 = ((Bundle)v1);
        byte[] v3 = ((byte[])v2);
        while(arg8.dataPosition() < v0) {
            int v4 = SafeParcelReader.readHeader(arg8);
            int v5 = SafeParcelReader.getFieldId(v4);
            if(v5 != 2) {
                switch(v5) {
                    case 4: {
                        goto label_15;
                    }
                    case 5: {
                        goto label_13;
                    }
                }

                SafeParcelReader.skipUnknownField(arg8, v4);
                continue;
            label_13:
                v3 = SafeParcelReader.createByteArray(arg8, v4);
                continue;
            label_15:
                v2 = SafeParcelReader.createBundle(arg8, v4);
                continue;
            }

            v1_1 = SafeParcelReader.createParcelable(arg8, v4, Uri.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzdd(((Uri)v1_1), v2, v3);
    }

    public final Object[] newArray(int arg1) {
        return new zzdd[arg1];
    }
}

