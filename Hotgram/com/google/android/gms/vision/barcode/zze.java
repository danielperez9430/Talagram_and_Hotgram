package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Parcelable$Creator {
    public zze() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        String v3 = null;
        String v4 = v3;
        String v5 = v4;
        String v6 = v5;
        String v7 = v6;
        CalendarDateTime v8 = ((CalendarDateTime)v7);
        CalendarDateTime v9 = v8;
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_32;
                }
                case 3: {
                    goto label_30;
                }
                case 4: {
                    goto label_28;
                }
                case 5: {
                    goto label_26;
                }
                case 6: {
                    goto label_24;
                }
                case 7: {
                    goto label_20;
                }
                case 8: {
                    goto label_16;
                }
            }

            SafeParcelReader.skipUnknownField(arg11, v1);
            continue;
        label_20:
            Parcelable v8_1 = SafeParcelReader.createParcelable(arg11, v1, CalendarDateTime.CREATOR);
            continue;
        label_24:
            v7 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_26:
            v6 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_28:
            v5 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_30:
            v4 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_32:
            v3 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_16:
            Parcelable v9_1 = SafeParcelReader.createParcelable(arg11, v1, CalendarDateTime.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new CalendarEvent(v3, v4, v5, v6, v7, v8, v9);
    }

    public final Object[] newArray(int arg1) {
        return new CalendarEvent[arg1];
    }
}

