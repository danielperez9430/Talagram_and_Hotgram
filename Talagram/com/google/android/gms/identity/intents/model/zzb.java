package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg21) {
        Parcel v0 = arg21;
        int v1 = SafeParcelReader.validateObjectHeader(arg21);
        String v5 = null;
        String v6 = v5;
        String v7 = v6;
        String v8 = v7;
        String v9 = v8;
        String v10 = v9;
        String v11 = v10;
        String v12 = v11;
        String v13 = v12;
        String v14 = v13;
        String v15 = v14;
        String v16 = v15;
        String v18 = v16;
        String v19 = v18;
        boolean v17 = false;
        while(arg21.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg21);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_54;
                }
                case 3: {
                    goto label_52;
                }
                case 4: {
                    goto label_50;
                }
                case 5: {
                    goto label_48;
                }
                case 6: {
                    goto label_46;
                }
                case 7: {
                    goto label_44;
                }
                case 8: {
                    goto label_42;
                }
                case 9: {
                    goto label_40;
                }
                case 10: {
                    goto label_38;
                }
                case 11: {
                    goto label_36;
                }
                case 12: {
                    goto label_34;
                }
                case 13: {
                    goto label_32;
                }
                case 14: {
                    goto label_30;
                }
                case 15: {
                    goto label_28;
                }
                case 16: {
                    goto label_26;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_34:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_36:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_38:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_40:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_42:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_44:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_46:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_48:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_50:
            v7 = SafeParcelReader.createString(v0, v2);
            continue;
        label_52:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_54:
            v5 = SafeParcelReader.createString(v0, v2);
            continue;
        label_26:
            v19 = SafeParcelReader.createString(v0, v2);
            continue;
        label_28:
            v18 = SafeParcelReader.createString(v0, v2);
            continue;
        label_30:
            v17 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_32:
            v16 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new UserAddress(v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19);
    }

    public final Object[] newArray(int arg1) {
        return new UserAddress[arg1];
    }
}

