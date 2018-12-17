package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable$Creator {
    public zzd() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        boolean v7 = false;
        boolean v8 = false;
        float v9;
        for(v9 = -1f; arg11.dataPosition() < v0; v9 = SafeParcelReader.readFloat(arg11, v1)) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_26;
                }
                case 3: {
                    goto label_24;
                }
                case 4: {
                    goto label_22;
                }
                case 5: {
                    goto label_20;
                }
                case 6: {
                    goto label_18;
                }
                case 7: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_18:
            v8 = SafeParcelReader.readBoolean(arg11, v1);
            continue;
        label_20:
            v7 = SafeParcelReader.readBoolean(arg11, v1);
            continue;
        label_22:
            v6 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_24:
            v5 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_26:
            v4 = SafeParcelReader.readInt(arg11, v1);
            continue;
        label_16:
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new zzc(v4, v5, v6, v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new zzc[arg1];
    }
}

