package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Parcelable$Creator {
    public zzh() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        Parcelable v5_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        Uri v5 = null;
        Bundle v6 = ((Bundle)v5);
        byte[] v7 = ((byte[])v6);
        long v8 = 0;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            int v2 = SafeParcelReader.getFieldId(v1);
            if(v2 != 2) {
                switch(v2) {
                    case 4: {
                        goto label_20;
                    }
                    case 5: {
                        goto label_18;
                    }
                    case 6: {
                        goto label_16;
                    }
                }

                SafeParcelReader.skipUnknownField(arg11, v1);
                continue;
            label_18:
                v7 = SafeParcelReader.createByteArray(arg11, v1);
                continue;
            label_20:
                v6 = SafeParcelReader.createBundle(arg11, v1);
                continue;
            label_16:
                v8 = SafeParcelReader.readLong(arg11, v1);
                continue;
            }

            v5_1 = SafeParcelReader.createParcelable(arg11, v1, Uri.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new PutDataRequest(((Uri)v5_1), v6, v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new PutDataRequest[arg1];
    }
}

