package com.google.android.gms.internal.vision;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzaa implements Parcelable$Creator {
    public zzaa() {
        super();
    }

    public final Object createFromParcel(Parcel arg6) {
        Parcelable v1_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg6);
        Rect v1 = null;
        while(arg6.dataPosition() < v0) {
            int v2 = SafeParcelReader.readHeader(arg6);
            if(SafeParcelReader.getFieldId(v2) != 2) {
                SafeParcelReader.skipUnknownField(arg6, v2);
                continue;
            }

            v1_1 = SafeParcelReader.createParcelable(arg6, v2, Rect.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg6, v0);
        return new zzz(((Rect)v1_1));
    }

    public final Object[] newArray(int arg1) {
        return new zzz[arg1];
    }
}

