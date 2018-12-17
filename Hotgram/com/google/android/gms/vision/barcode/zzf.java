package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzf implements Parcelable$Creator {
    public zzf() {
        super();
    }

    public final Object createFromParcel(Parcel arg11) {
        Object[] v9_1;
        Parcelable v3_1;
        int v0 = SafeParcelReader.validateObjectHeader(arg11);
        PersonName v3 = null;
        String v4 = ((String)v3);
        String v5 = v4;
        Phone[] v6 = ((Phone[])v5);
        Email[] v7 = ((Email[])v6);
        String[] v8 = ((String[])v7);
        Address[] v9 = ((Address[])v8);
        while(arg11.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg11);
            switch(SafeParcelReader.getFieldId(v1)) {
                case 2: {
                    goto label_34;
                }
                case 3: {
                    goto label_32;
                }
                case 4: {
                    goto label_30;
                }
                case 5: {
                    goto label_26;
                }
                case 6: {
                    goto label_22;
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
        label_34:
            v3_1 = SafeParcelReader.createParcelable(arg11, v1, PersonName.CREATOR);
            continue;
        label_20:
            v8 = SafeParcelReader.createStringArray(arg11, v1);
            continue;
        label_22:
            Object[] v7_1 = SafeParcelReader.createTypedArray(arg11, v1, Email.CREATOR);
            continue;
        label_26:
            Object[] v6_1 = SafeParcelReader.createTypedArray(arg11, v1, Phone.CREATOR);
            continue;
        label_30:
            v5 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_32:
            v4 = SafeParcelReader.createString(arg11, v1);
            continue;
        label_16:
            v9_1 = SafeParcelReader.createTypedArray(arg11, v1, Address.CREATOR);
        }

        SafeParcelReader.ensureAtEnd(arg11, v0);
        return new ContactInfo(((PersonName)v3_1), v4, v5, v6, v7, v8, ((Address[])v9_1));
    }

    public final Object[] newArray(int arg1) {
        return new ContactInfo[arg1];
    }
}

