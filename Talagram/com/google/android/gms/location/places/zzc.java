package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public final class zzc implements Parcelable$Creator {
    public zzc() {
        super();
    }

    public final Object createFromParcel(Parcel arg10) {
        Parcelable v4_1;
        ArrayList v6_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        String v3 = null;
        LatLng v4 = ((LatLng)v3);
        String v5 = ((String)v4);
        List v6 = ((List)v5);
        String v7 = ((String)v6);
        Uri v8 = ((Uri)v7);
        while(arg10.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg10);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 1: {
                    goto label_29;
                }
                case 2: {
                    goto label_25;
                }
                case 3: {
                    goto label_23;
                }
                case 4: {
                    goto label_21;
                }
                case 5: {
                    goto label_19;
                }
                case 6: {
                    goto label_15;
                }
            }

            SafeParcelReader.skipUnknownField(arg10, v1);
            continue;
        label_19:
            v7 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_21:
            v6_1 = SafeParcelReader.createIntegerList(arg10, v1);
            continue;
        label_23:
            v5 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_25:
            v4_1 = SafeParcelReader.createParcelable(arg10, v1, LatLng.CREATOR);
            continue;
        label_29:
            v3 = SafeParcelReader.createString(arg10, v1);
            continue;
        label_15:
            Parcelable v8_1 = SafeParcelReader.createParcelable(arg10, v1, Uri.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        return new AddPlaceRequest(v3, ((LatLng)v4_1), v5, ((List)v6_1), v7, v8);
    }

    public final Object[] newArray(int arg1) {
        return new AddPlaceRequest[arg1];
    }
}

