package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public final class zzdf extends DataBufferRef implements DataItem {
    private final int zzdl;

    public zzdf(DataHolder arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.zzdl = arg3;
    }

    public final Object freeze() {
        return new zzdc(((DataItem)this));
    }

    public final Map getAssets() {
        HashMap v0 = new HashMap(this.zzdl);
        int v1;
        for(v1 = 0; v1 < this.zzdl; ++v1) {
            zzdb v2 = new zzdb(this.mDataHolder, this.mDataRow + v1);
            if(v2.getDataItemKey() != null) {
                ((Map)v0).put(v2.getDataItemKey(), v2);
            }
        }

        return ((Map)v0);
    }

    public final byte[] getData() {
        return this.getByteArray("data");
    }

    public final Uri getUri() {
        return Uri.parse(this.getString("path"));
    }

    public final DataItem setData(byte[] arg1) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        String v1_1;
        boolean v0 = Log.isLoggable("DataItem", 3);
        byte[] v1 = this.getData();
        Map v2 = this.getAssets();
        StringBuilder v3 = new StringBuilder("DataItemRef{ ");
        String v4 = String.valueOf(this.getUri());
        StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 4);
        v6.append("uri=");
        v6.append(v4);
        v3.append(v6.toString());
        if(v1 == null) {
            v1_1 = "null";
        }
        else {
            Integer v1_2 = Integer.valueOf(v1.length);
        }

        v1_1 = String.valueOf(v1_1);
        StringBuilder v5 = new StringBuilder(String.valueOf(v1_1).length() + 9);
        v5.append(", dataSz=");
        v5.append(v1_1);
        v3.append(v5.toString());
        int v1_3 = v2.size();
        v5 = new StringBuilder(23);
        v5.append(", numAssets=");
        v5.append(v1_3);
        v3.append(v5.toString());
        if((v0) && !v2.isEmpty()) {
            v3.append(", assets=[");
            String v0_1 = "";
            Iterator v1_4 = v2.entrySet().iterator();
            while(v1_4.hasNext()) {
                Object v2_1 = v1_4.next();
                Object v4_1 = ((Map$Entry)v2_1).getKey();
                String v2_2 = ((Map$Entry)v2_1).getValue().getId();
                v6 = new StringBuilder(String.valueOf(v0_1).length() + 2 + String.valueOf(v4_1).length() + String.valueOf(v2_2).length());
                v6.append(v0_1);
                v6.append(((String)v4_1));
                v6.append(": ");
                v6.append(v2_2);
                v3.append(v6.toString());
                v0_1 = ", ";
            }

            v3.append("]");
        }

        v3.append(" }");
        return v3.toString();
    }
}

