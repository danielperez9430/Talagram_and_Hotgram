package com.google.android.gms.internal.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.widget.RemoteViews;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable$Creator {
    public zzm() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        String[] v1 = null;
        int[] v2 = ((int[])v1);
        RemoteViews v3 = ((RemoteViews)v2);
        byte[] v4 = ((byte[])v3);
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_19;
                }
                case 2: {
                    goto label_17;
                }
                case 3: {
                    goto label_14;
                }
                case 4: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_17:
            v2 = SafeParcelReader.createIntArray(arg8, v5);
            continue;
        label_19:
            v1 = SafeParcelReader.createStringArray(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.createByteArray(arg8, v5);
            continue;
        label_14:
            Parcelable v3_1 = SafeParcelReader.createParcelable(arg8, v5, RemoteViews.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new zzl(v1, v2, v3, v4);
    }

    public final Object[] newArray(int arg1) {
        return new zzl[arg1];
    }
}

