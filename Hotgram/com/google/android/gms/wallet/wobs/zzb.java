package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg31) {
        Parcelable v21_1;
        Parcel v0 = arg31;
        int v1 = SafeParcelReader.validateObjectHeader(arg31);
        ArrayList v20 = ArrayUtils.newArrayList();
        ArrayList v22 = ArrayUtils.newArrayList();
        ArrayList v25 = ArrayUtils.newArrayList();
        ArrayList v27 = ArrayUtils.newArrayList();
        ArrayList v28 = ArrayUtils.newArrayList();
        ArrayList v29 = ArrayUtils.newArrayList();
        String v11 = null;
        String v12 = v11;
        String v13 = v12;
        String v14 = v13;
        String v15 = v14;
        String v16 = v15;
        String v17 = v16;
        String v18 = v17;
        TimeInterval v21 = ((TimeInterval)v18);
        String v23 = ((String)v21);
        String v24 = v23;
        int v19 = 0;
        boolean v26 = false;
        while(arg31.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg31);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_80;
                }
                case 3: {
                    goto label_78;
                }
                case 4: {
                    goto label_76;
                }
                case 5: {
                    goto label_74;
                }
                case 6: {
                    goto label_72;
                }
                case 7: {
                    goto label_70;
                }
                case 8: {
                    goto label_68;
                }
                case 9: {
                    goto label_66;
                }
                case 10: {
                    goto label_64;
                }
                case 11: {
                    goto label_61;
                }
                case 12: {
                    goto label_57;
                }
                case 13: {
                    goto label_54;
                }
                case 14: {
                    goto label_52;
                }
                case 15: {
                    goto label_50;
                }
                case 16: {
                    goto label_47;
                }
                case 17: {
                    goto label_45;
                }
                case 18: {
                    goto label_42;
                }
                case 19: {
                    goto label_39;
                }
                case 20: {
                    goto label_36;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_66:
            v18 = SafeParcelReader.createString(v0, v2);
            continue;
        label_68:
            v17 = SafeParcelReader.createString(v0, v2);
            continue;
        label_36:
            v29 = SafeParcelReader.createTypedList(v0, v2, UriData.CREATOR);
            continue;
        label_70:
            v16 = SafeParcelReader.createString(v0, v2);
            continue;
        label_39:
            v28 = SafeParcelReader.createTypedList(v0, v2, TextModuleData.CREATOR);
            continue;
        label_72:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_74:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_42:
            v27 = SafeParcelReader.createTypedList(v0, v2, UriData.CREATOR);
            continue;
        label_76:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_45:
            v26 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_78:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_47:
            v25 = SafeParcelReader.createTypedList(v0, v2, LabelValueRow.CREATOR);
            continue;
        label_80:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_50:
            v24 = SafeParcelReader.createString(v0, v2);
            continue;
        label_52:
            v23 = SafeParcelReader.createString(v0, v2);
            continue;
        label_54:
            v22 = SafeParcelReader.createTypedList(v0, v2, LatLng.CREATOR);
            continue;
        label_57:
            v21_1 = SafeParcelReader.createParcelable(v0, v2, TimeInterval.CREATOR);
            continue;
        label_61:
            v20 = SafeParcelReader.createTypedList(v0, v2, WalletObjectMessage.CREATOR);
            continue;
        label_64:
            v19 = SafeParcelReader.readInt(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new CommonWalletObject(v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, ((TimeInterval)v21_1), v22, v23, v24, v25, v26, v27, v28, v29);
    }

    public final Object[] newArray(int arg1) {
        return new CommonWalletObject[arg1];
    }
}

