package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable$Creator {
    public zzb() {
        super();
    }

    public final Object createFromParcel(Parcel arg20) {
        Parcelable v17_1;
        Parcelable v10_1;
        Parcelable v13_1;
        Parcelable v14_1;
        Parcel v0 = arg20;
        int v1 = SafeParcelReader.validateObjectHeader(arg20);
        String v6 = null;
        String v7 = v6;
        Point[] v9 = ((Point[])v7);
        Email v10 = ((Email)v9);
        Phone v11 = ((Phone)v10);
        Sms v12 = ((Sms)v11);
        WiFi v13 = ((WiFi)v12);
        UrlBookmark v14 = ((UrlBookmark)v13);
        GeoPoint v15 = ((GeoPoint)v14);
        CalendarEvent v16 = ((CalendarEvent)v15);
        ContactInfo v17 = ((ContactInfo)v16);
        DriverLicense v18 = ((DriverLicense)v17);
        int v5 = 0;
        int v8 = 0;
        while(arg20.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg20);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 2: {
                    goto label_71;
                }
                case 3: {
                    goto label_69;
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
                    goto label_57;
                }
                case 8: {
                    goto label_53;
                }
                case 9: {
                    goto label_49;
                }
                case 10: {
                    goto label_45;
                }
                case 11: {
                    goto label_41;
                }
                case 12: {
                    goto label_37;
                }
                case 13: {
                    goto label_33;
                }
                case 14: {
                    goto label_29;
                }
                case 15: {
                    goto label_25;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_65:
            v8 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_33:
            Parcelable v16_1 = SafeParcelReader.createParcelable(v0, v2, CalendarEvent.CREATOR);
            continue;
        label_67:
            v7 = SafeParcelReader.createString(v0, v2);
            continue;
        label_69:
            v6 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            Parcelable v15_1 = SafeParcelReader.createParcelable(v0, v2, GeoPoint.CREATOR);
            continue;
        label_71:
            v5 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_41:
            v14_1 = SafeParcelReader.createParcelable(v0, v2, UrlBookmark.CREATOR);
            continue;
        label_45:
            v13_1 = SafeParcelReader.createParcelable(v0, v2, WiFi.CREATOR);
            continue;
        label_49:
            Parcelable v12_1 = SafeParcelReader.createParcelable(v0, v2, Sms.CREATOR);
            continue;
        label_53:
            Parcelable v11_1 = SafeParcelReader.createParcelable(v0, v2, Phone.CREATOR);
            continue;
        label_57:
            v10_1 = SafeParcelReader.createParcelable(v0, v2, Email.CREATOR);
            continue;
        label_25:
            Parcelable v18_1 = SafeParcelReader.createParcelable(v0, v2, DriverLicense.CREATOR);
            continue;
        label_61:
            Object[] v9_1 = SafeParcelReader.createTypedArray(v0, v2, Point.CREATOR);
            continue;
        label_29:
            v17_1 = SafeParcelReader.createParcelable(v0, v2, ContactInfo.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new Barcode(v5, v6, v7, v8, v9, ((Email)v10_1), v11, v12, ((WiFi)v13_1), ((UrlBookmark)v14_1), v15, v16, ((ContactInfo)v17_1), v18);
    }

    public final Object[] newArray(int arg1) {
        return new Barcode[arg1];
    }
}

