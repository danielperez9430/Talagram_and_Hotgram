package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.DataItem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

@Class(creator="DataItemParcelableCreator") @Reserved(value={1}) @VisibleForTesting public final class zzdd extends AbstractSafeParcelable implements DataItem {
    public static final Parcelable$Creator CREATOR;
    @Field(getter="getData", id=5) private byte[] data;
    @Field(getter="getUri", id=2) private final Uri uri;
    @Field(getter="getAssetsInternal", id=4, type="android.os.Bundle") private final Map zzdo;

    static {
        zzdd.CREATOR = new zzde();
    }

    @Constructor zzdd(@Param(id=2) Uri arg4, @Param(id=4) Bundle arg5, @Param(id=5) byte[] arg6) {
        super();
        this.uri = arg4;
        HashMap v4 = new HashMap();
        arg5.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        Iterator v0 = arg5.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ((Map)v4).put(v1, arg5.getParcelable(((String)v1)));
        }

        this.zzdo = ((Map)v4);
        this.data = arg6;
    }

    public final Object freeze() {
        return this;
    }

    public final Map getAssets() {
        return this.zzdo;
    }

    @VisibleForTesting public final byte[] getData() {
        return this.data;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final DataItem setData(byte[] arg1) {
        this.data = arg1;
        return this;
    }

    public final String toString() {
        String v0_1;
        Integer v2_1;
        String v2;
        boolean v0 = Log.isLoggable("DataItem", 3);
        StringBuilder v1 = new StringBuilder("DataItemParcelable[");
        v1.append("@");
        v1.append(Integer.toHexString(this.hashCode()));
        if(this.data == null) {
            v2 = "null";
        }
        else {
            v2_1 = Integer.valueOf(this.data.length);
        }

        v2 = String.valueOf(v2_1);
        StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 8);
        v4.append(",dataSz=");
        v4.append(v2);
        v1.append(v4.toString());
        int v2_2 = this.zzdo.size();
        v4 = new StringBuilder(23);
        v4.append(", numAssets=");
        v4.append(v2_2);
        v1.append(v4.toString());
        v2 = String.valueOf(this.uri);
        v4 = new StringBuilder(String.valueOf(v2).length() + 6);
        v4.append(", uri=");
        v4.append(v2);
        v1.append(v4.toString());
        if(!v0) {
            v0_1 = "]";
        }
        else {
            v1.append("]\n  assets: ");
            Iterator v0_2 = this.zzdo.keySet().iterator();
            while(v0_2.hasNext()) {
                Object v2_3 = v0_2.next();
                String v3 = String.valueOf(this.zzdo.get(v2_3));
                StringBuilder v5 = new StringBuilder(String.valueOf(v2_3).length() + 7 + String.valueOf(v3).length());
                v5.append("\n    ");
                v5.append(((String)v2_3));
                v5.append(": ");
                v5.append(v3);
                v1.append(v5.toString());
            }

            v0_1 = "\n  ]";
        }

        v1.append(v0_1);
        return v1.toString();
    }

    public final void writeToParcel(Parcel arg7, int arg8) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg7);
        SafeParcelWriter.writeParcelable(arg7, 2, this.getUri(), arg8, false);
        Bundle v8 = new Bundle();
        v8.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        Iterator v1 = this.zzdo.entrySet().iterator();
        while(v1.hasNext()) {
            Object v3 = v1.next();
            v8.putParcelable(((Map$Entry)v3).getKey(), new DataItemAssetParcelable(((Map$Entry)v3).getValue()));
        }

        SafeParcelWriter.writeBundle(arg7, 4, v8, false);
        SafeParcelWriter.writeByteArray(arg7, 5, this.getData(), false);
        SafeParcelWriter.finishObjectHeader(arg7, v0);
    }
}

