package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class WebImageCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public WebImageCreator() {
        super();
    }

    public WebImage createFromParcel(Parcel arg8) {
        int v0 = SafeParcelReader.validateObjectHeader(arg8);
        int v1 = 0;
        Uri v2 = null;
        int v3 = 0;
        int v4 = 0;
        while(arg8.dataPosition() < v0) {
            int v5 = SafeParcelReader.readHeader(arg8);
            switch(SafeParcelReader.getFieldId(v5)) {
                case 1: {
                    goto label_19;
                }
                case 2: {
                    goto label_16;
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
        label_19:
            v1 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_12:
            v4 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_14:
            v3 = SafeParcelReader.readInt(arg8, v5);
            continue;
        label_16:
            Parcelable v2_1 = SafeParcelReader.createParcelable(arg8, v5, Uri.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg8, v0);
        return new WebImage(v1, v2, v3, v4);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public WebImage[] newArray(int arg1) {
        return new WebImage[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

