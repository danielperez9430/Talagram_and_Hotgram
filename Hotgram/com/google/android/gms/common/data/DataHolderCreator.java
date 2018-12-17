package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class DataHolderCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public DataHolderCreator() {
        super();
    }

    public DataHolder createFromParcel(Parcel arg10) {
        int v0 = SafeParcelReader.validateObjectHeader(arg10);
        String[] v5 = null;
        CursorWindow[] v6 = ((CursorWindow[])v5);
        Bundle v8 = ((Bundle)v6);
        int v4 = 0;
        int v7 = 0;
        while(arg10.dataPosition() < v0) {
            int v1 = SafeParcelReader.readHeader(arg10);
            int v2 = SafeParcelReader.getFieldId(v1);
            if(v2 != 1000) {
                switch(v2) {
                    case 1: {
                        goto label_25;
                    }
                    case 2: {
                        goto label_21;
                    }
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_17;
                    }
                }

                SafeParcelReader.skipUnknownField(arg10, v1);
                continue;
            label_17:
                v8 = SafeParcelReader.createBundle(arg10, v1);
                continue;
            label_19:
                v7 = SafeParcelReader.readInt(arg10, v1);
                continue;
            label_21:
                Object[] v6_1 = SafeParcelReader.createTypedArray(arg10, v1, CursorWindow.CREATOR);
                continue;
            label_25:
                v5 = SafeParcelReader.createStringArray(arg10, v1);
                continue;
            }

            v4 = SafeParcelReader.readInt(arg10, v1);
        }

        SafeParcelReader.ensureAtEnd(arg10, v0);
        DataHolder v10 = new DataHolder(v4, v5, v6, v7, v8);
        v10.validateContents();
        return v10;
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public DataHolder[] newArray(int arg1) {
        return new DataHolder[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

