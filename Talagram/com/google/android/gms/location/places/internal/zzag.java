package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.ArrayList;
import java.util.List;

public final class zzag implements Parcelable$Creator {
    public zzag() {
        super();
    }

    public final Object createFromParcel(Parcel arg24) {
        Parcelable v21_1;
        Parcelable v16_1;
        ArrayList v7_1;
        Parcelable v20_1;
        Parcel v0 = arg24;
        int v1 = SafeParcelReader.validateObjectHeader(arg24);
        String v6 = null;
        List v7 = ((List)v6);
        String v8 = ((String)v7);
        String v9 = v8;
        String v10 = v9;
        List v11 = ((List)v10);
        LatLng v12 = ((LatLng)v11);
        LatLngBounds v14 = ((LatLngBounds)v12);
        String v15 = ((String)v14);
        Uri v16 = ((Uri)v15);
        zzam v20 = ((zzam)v16);
        zzah v21 = ((zzah)v20);
        String v22 = ((String)v21);
        float v13 = 0f;
        boolean v17 = false;
        float v18 = 0f;
        int v19 = 0;
        while(arg24.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg24);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_71;
                }
                case 4: {
                    goto label_67;
                }
                case 5: {
                    goto label_65;
                }
                case 6: {
                    goto label_61;
                }
                case 7: {
                    goto label_59;
                }
                case 8: {
                    goto label_55;
                }
                case 9: {
                    goto label_53;
                }
                case 10: {
                    goto label_51;
                }
                case 11: {
                    goto label_49;
                }
                case 14: {
                    goto label_47;
                }
                case 15: {
                    goto label_45;
                }
                case 17: {
                    goto label_43;
                }
                case 19: {
                    goto label_41;
                }
                case 20: {
                    goto label_39;
                }
                case 21: {
                    goto label_35;
                }
                case 22: {
                    goto label_31;
                }
                case 23: {
                    goto label_29;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_65:
            v13 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_67:
            Parcelable v12_1 = SafeParcelReader.createParcelable(v0, v2, LatLng.CREATOR);
            continue;
        label_35:
            v20_1 = SafeParcelReader.createParcelable(v0, v2, zzam.CREATOR);
            continue;
        label_71:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_39:
            v7_1 = SafeParcelReader.createIntegerList(v0, v2);
            continue;
        label_41:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_43:
            ArrayList v11_1 = SafeParcelReader.createStringList(v0, v2);
            continue;
        label_45:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_47:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_49:
            v19 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_51:
            v18 = SafeParcelReader.readFloat(v0, v2);
            continue;
        label_53:
            v17 = SafeParcelReader.readBoolean(v0, v2);
            continue;
        label_55:
            v16_1 = SafeParcelReader.createParcelable(v0, v2, Uri.CREATOR);
            continue;
        label_59:
            v15 = SafeParcelReader.createString(v0, v2);
            continue;
        label_61:
            Parcelable v14_1 = SafeParcelReader.createParcelable(v0, v2, LatLngBounds.CREATOR);
            continue;
        label_29:
            v22 = SafeParcelReader.createString(v0, v2);
            continue;
        label_31:
            v21_1 = SafeParcelReader.createParcelable(v0, v2, zzah.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new PlaceEntity(v6, ((List)v7_1), v8, v9, v10, v11, v12, v13, v14, v15, ((Uri)v16_1), v17, v18, v19, ((zzam)v20_1), ((zzah)v21_1), v22);
    }

    public final Object[] newArray(int arg1) {
        return new PlaceEntity[arg1];
    }
}

