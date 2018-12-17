package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzai implements Parcelable$Creator {
    public zzai() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        ArrayList v5_1;
        Parcelable v7_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        List v5 = null;
        String v6 = ((String)v5);
        Uri v7 = ((Uri)v6);
        float v8 = 0f;
        int v9;
        for(v9 = 0; arg11.dataPosition() < v0; v9 = SafeParcelReader.readInt(arg11, v1)) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_26;
                }
                case 2: {
                    goto label_24;
                }
                case 3: {
                    goto label_20;
                }
                case 4: {
                    goto label_18;
                }
                case 5: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_18:
            v8 = SafeParcelReader.readFloat(arg11, v1);
            continue;
        label_20:
            v7_1 = SafeParcelReader.createParcelable(arg11, v1, Uri.CREATOR);
            continue;
        label_24:
            v6 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_26:
            v5_1 = SafeParcelReader.createIntegerList(arg11, v1);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzah(((List)v5_1), v6, ((Uri)v7_1), v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new zzah[arg1];
    }
}

