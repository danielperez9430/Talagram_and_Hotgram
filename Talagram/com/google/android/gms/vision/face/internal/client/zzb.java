package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg19) {
        Object[] v14_1;
        Parcel v0 = arg19;
        int v1 = SafeParcelReader.validateObjectHeader(arg19);
        LandmarkParcel[] v14 = null;
        int v6 = 0;
        int v7 = 0;
        float v8 = 0f;
        float v9 = 0f;
        float v10 = 0f;
        float v11 = 0f;
        float v12 = 0f;
        float v13 = 0f;
        float v15 = 0f;
        float v16 = 0f;
        float v17 = 0f;
        while(arg19.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg19);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_48;
                }
                case 2: {
                    goto label_46;
                }
                case 3: {
                    goto label_44;
                }
                case 4: {
                    goto label_42;
                }
                case 5: {
                    goto label_40;
                }
                case 6: {
                    goto label_38;
                }
                case 7: {
                    goto label_36;
                }
                case 8: {
                    goto label_34;
                }
                case 9: {
                    goto label_30;
                }
                case 10: {
                    goto label_28;
                }
                case 11: {
                    goto label_26;
                }
                case 12: {
                    goto label_24;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v13 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_36:
            v12 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_38:
            v11 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_40:
            v10 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_42:
            v9 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_44:
            v8 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_46:
            v7 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_48:
            v6 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_24:
            v17 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_26:
            v16 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_28:
            v15 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_30:
            v14_1 = SafeParcelReader.createTypedArray(v0, v2, LandmarkParcel.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new FaceParcel(v6, v7, v8, v9, v10, v11, v12, v13, ((LandmarkParcel[])v14_1), v15, v16, v17);
    }

    public final Object[] newArray(int arg1) {
        return new FaceParcel[arg1];
    }
}

