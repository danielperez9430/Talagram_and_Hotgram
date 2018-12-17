package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg17) {
        Parcel v0 = arg17;
        int v1 = SafeParcelReader.validateObjectHeader(arg17);
        String v5 = null;
        String v6 = v5;
        String v7 = v6;
        String v8 = v7;
        String v9 = v8;
        String v10 = v9;
        String v11 = v10;
        String v12 = v11;
        String v13 = v12;
        String v15 = v13;
        boolean v14 = false;
        while(arg17.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg17);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_42;
                }
                case 3: {
                    goto label_40;
                }
                case 4: {
                    goto label_38;
                }
                case 5: {
                    goto label_36;
                }
                case 6: {
                    goto label_34;
                }
                case 7: {
                    goto label_32;
                }
                case 8: {
                    goto label_30;
                }
                case 9: {
                    goto label_28;
                }
                case 10: {
                    goto label_26;
                }
                case 11: {
                    goto label_24;
                }
                case 12: {
                    goto label_22;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_36:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_38:
            v7 = SafeParcelReader.createString(v0, v2);
            continue;
        label_22:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_40:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_24:
            v14 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_42:
            v5 = SafeParcelReader.createString(v0, v2);
            continue;
        label_26:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_28:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_30:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_32:
            v10 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new zza(v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15);
    }

    public final Object[] newArray(int arg1) {
        return new zza[arg1];
    }
}

