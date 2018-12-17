package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

public final class zzv implements Parcelable$Creator {
    public zzv() {
        super();
    }

    public final Object createFromParcel(Parcel arg34) {
        Parcelable v23_1;
        Parcelable v32_1;
        Parcel v0 = arg34;
        int v1 = SafeParcelReader.validateObjectHeader(arg34);
        ArrayList v22 = ArrayUtils.newArrayList();
        ArrayList v24 = ArrayUtils.newArrayList();
        ArrayList v27 = ArrayUtils.newArrayList();
        ArrayList v29 = ArrayUtils.newArrayList();
        ArrayList v30 = ArrayUtils.newArrayList();
        ArrayList v31 = ArrayUtils.newArrayList();
        String v11 = null;
        String v12 = v11;
        String v13 = v12;
        String v14 = v13;
        String v15 = v14;
        String v16 = v15;
        String v17 = v16;
        String v18 = v17;
        String v19 = v18;
        String v20 = v19;
        TimeInterval v23 = ((TimeInterval)v20);
        String v25 = ((String)v23);
        String v26 = v25;
        LoyaltyPoints v32 = ((LoyaltyPoints)v26);
        int v21 = 0;
        boolean v28 = false;
        while(arg34.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg34);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_91;
                }
                case 3: {
                    goto label_89;
                }
                case 4: {
                    goto label_87;
                }
                case 5: {
                    goto label_85;
                }
                case 6: {
                    goto label_83;
                }
                case 7: {
                    goto label_81;
                }
                case 8: {
                    goto label_79;
                }
                case 9: {
                    goto label_77;
                }
                case 10: {
                    goto label_75;
                }
                case 11: {
                    goto label_73;
                }
                case 12: {
                    goto label_71;
                }
                case 13: {
                    goto label_68;
                }
                case 14: {
                    goto label_64;
                }
                case 15: {
                    goto label_61;
                }
                case 16: {
                    goto label_59;
                }
                case 17: {
                    goto label_57;
                }
                case 18: {
                    goto label_54;
                }
                case 19: {
                    goto label_52;
                }
                case 20: {
                    goto label_49;
                }
                case 21: {
                    goto label_46;
                }
                case 22: {
                    goto label_43;
                }
                case 23: {
                    goto label_39;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_68:
            v22 = SafeParcelReader.createTypedList(v0, v2, WalletObjectMessage.CREATOR);
            continue;
        label_71:
            v21 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_39:
            v32_1 = SafeParcelReader.createParcelable(v0, v2, LoyaltyPoints.CREATOR);
            continue;
        label_73:
            v20 = SafeParcelReader.createString(v0, v2);
            continue;
        label_75:
            v19 = SafeParcelReader.createString(v0, v2);
            continue;
        label_43:
            v31 = SafeParcelReader.createTypedList(v0, v2, UriData.CREATOR);
            continue;
        label_77:
            v18 = SafeParcelReader.createString(v0, v2);
            continue;
        label_46:
            v30 = SafeParcelReader.createTypedList(v0, v2, TextModuleData.CREATOR);
            continue;
        label_79:
            v17 = SafeParcelReader.createString(v0, v2);
            continue;
        label_81:
            v16 = SafeParcelReader.createString(v0, v2);
            continue;
        label_49:
            v29 = SafeParcelReader.createTypedList(v0, v2, UriData.CREATOR);
            continue;
        label_83:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_52:
            v28 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_85:
            v14 = SafeParcelReader.createString(v0, v2);
            continue;
        label_54:
            v27 = SafeParcelReader.createTypedList(v0, v2, LabelValueRow.CREATOR);
            continue;
        label_87:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_89:
            v12 = SafeParcelReader.createString(v0, v2);
            continue;
        label_57:
            v26 = SafeParcelReader.createString(v0, v2);
            continue;
        label_91:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_59:
            v25 = SafeParcelReader.createString(v0, v2);
            continue;
        label_61:
            v24 = SafeParcelReader.createTypedList(v0, v2, LatLng.CREATOR);
            continue;
        label_64:
            v23_1 = SafeParcelReader.createParcelable(v0, v2, TimeInterval.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new LoyaltyWalletObject(v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, ((TimeInterval)v23_1), v24, v25, v26, v27, v28, v29, v30, v31, ((LoyaltyPoints)v32_1));
    }

    public final Object[] newArray(int arg1) {
        return new LoyaltyWalletObject[arg1];
    }
}

