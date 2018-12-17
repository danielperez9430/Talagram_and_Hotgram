package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzbg implements Parcelable$Creator {
    public zzbg() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        Parcelable v5_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        zzbd v5 = null;
        IBinder v6 = ((IBinder)v5);
        PendingIntent v7 = ((PendingIntent)v6);
        IBinder v8 = ((IBinder)v7);
        IBinder v9 = v8;
        int v4 = 1;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_30;
                }
                case 2: {
                    goto label_26;
                }
                case 3: {
                    goto label_24;
                }
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
            v8 = SafeParcelReader.readIBinder(arg11, v1);
            continue;
        label_20:
            Parcelable v7_1 = SafeParcelReader.createParcelable(arg11, v1, PendingIntent.CREATOR);
            continue;
        label_24:
            v6 = SafeParcelReader.readIBinder(arg11, v1);
            continue;
        label_26:
            v5_1 = SafeParcelReader.createParcelable(arg11, v1, zzbd.CREATOR);
            continue;
        label_30:
            v4 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_16:
            v9 = SafeParcelReader.readIBinder(arg11, v1);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzbf(v4, ((zzbd)v5_1), v6, v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new zzbf[arg1];
    }
}

