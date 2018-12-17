package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg8) {
        Parcelable v4_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        byte[] v1 = null;
        String v2 = ((String)v1);
        ParcelFileDescriptor v3 = ((ParcelFileDescriptor)v2);
        Uri v4 = ((Uri)v3);
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 2: {
                    goto label_20;
                }
                case 3: {
                    goto label_18;
                }
                case 4: {
                    goto label_15;
                }
                case 5: {
                    goto label_12;
                }
            }

            SafeParcelReader.skipUnknownField(arg8, v5);
            continue;
        label_18:
            v2 = SafeParcelReader.createString(arg8, v5);
            continue;
        label_20:
            v1 = SafeParcelReader.createByteArray(arg8, v5);
            continue;
        label_12:
            v4_1 = SafeParcelReader.createParcelable(arg8, v5, Uri.CREATOR);
            continue;
        label_15:
            Parcelable v3_1 = SafeParcelReader.createParcelable(arg8, v5, ParcelFileDescriptor.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new Asset(v1, v2, v3, ((Uri)v4_1));
    }

    public final Object[] newArray(int arg1) {
        return new Asset[arg1];
    }
}

