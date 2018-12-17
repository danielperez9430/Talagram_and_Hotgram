package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public class GoogleSignInAccountCreator implements Parcelable$Creator {
    public static final int CONTENT_DESCRIPTION;

    public GoogleSignInAccountCreator() {
        super();
    }

    public GoogleSignInAccount createFromParcel(Parcel arg21) {
        ArrayList v17_1;
        Parcel v0 = arg21;
        int v1 = SafeParcelReader.validateObjectHeader(arg21);
        String v8 = null;
        String v9 = v8;
        String v10 = v9;
        String v11 = v10;
        Uri v12 = ((Uri)v11);
        String v13 = ((String)v12);
        String v16 = v13;
        List v17 = ((List)v16);
        String v18 = ((String)v17);
        String v19 = v18;
        long v14 = 0;
        int v7 = 0;
        while(arg21.dataPosition() < v1) {
            int v2 = SafeParcelReader.readHeader(arg21);
            switch(SafeParcelReader.getFieldId(v2)) {
                case 1: {
                    goto label_49;
                }
                case 2: {
                    goto label_47;
                }
                case 3: {
                    goto label_45;
                }
                case 4: {
                    goto label_43;
                }
                case 5: {
                    goto label_41;
                }
                case 6: {
                    goto label_37;
                }
                case 7: {
                    goto label_35;
                }
                case 8: {
                    goto label_33;
                }
                case 9: {
                    goto label_31;
                }
                case 10: {
                    goto label_28;
                }
                case 11: {
                    goto label_26;
                }
                case 12: {
                    goto label_24;
                }
            }

            SafeParcelReader.skipUnknownField(v0, v2);
            continue;
        label_33:
            v14 = SafeParcelReader.readLong(v0, v2);
            continue;
        label_35:
            v13 = SafeParcelReader.createString(v0, v2);
            continue;
        label_37:
            Parcelable v12_1 = SafeParcelReader.createParcelable(v0, v2, Uri.CREATOR);
            continue;
        label_41:
            v11 = SafeParcelReader.createString(v0, v2);
            continue;
        label_43:
            v10 = SafeParcelReader.createString(v0, v2);
            continue;
        label_45:
            v9 = SafeParcelReader.createString(v0, v2);
            continue;
        label_47:
            v8 = SafeParcelReader.createString(v0, v2);
            continue;
        label_49:
            v7 = SafeParcelReader.readInt(v0, v2);
            continue;
        label_24:
            v19 = SafeParcelReader.createString(v0, v2);
            continue;
        label_26:
            v18 = SafeParcelReader.createString(v0, v2);
            continue;
        label_28:
            v17_1 = SafeParcelReader.createTypedList(v0, v2, Scope.CREATOR);
            continue;
        label_31:
            v16 = SafeParcelReader.createString(v0, v2);
        }

        SafeParcelReader.ensureAtEnd(v0, v1);
        return new GoogleSignInAccount(v7, v8, v9, v10, v11, v12, v13, v14, v16, ((List)v17_1), v18, v19);
    }

    public Object createFromParcel(Parcel arg1) {
        return this.createFromParcel(arg1);
    }

    public GoogleSignInAccount[] newArray(int arg1) {
        return new GoogleSignInAccount[arg1];
    }

    public Object[] newArray(int arg1) {
        return this.newArray(arg1);
    }
}

