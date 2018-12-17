package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.wearable.DataItem;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public final class zzdc implements DataItem {
    private byte[] data;
    private Uri uri;
    private Map zzdo;

    public zzdc(DataItem arg4) {
        super();
        this.uri = arg4.getUri();
        this.data = arg4.getData();
        HashMap v0 = new HashMap();
        Iterator v4 = arg4.getAssets().entrySet().iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            if(((Map$Entry)v1).getKey() == null) {
                continue;
            }

            v0.put(((Map$Entry)v1).getKey(), ((Map$Entry)v1).getValue().freeze());
        }

        this.zzdo = Collections.unmodifiableMap(((Map)v0));
    }

    public final Object freeze() {
        return this;
    }

    public final Map getAssets() {
        return this.zzdo;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final DataItem setData(byte[] arg1) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        boolean v0 = Log.isLoggable("DataItem", 3);
        StringBuilder v1 = new StringBuilder("DataItemEntity{ ");
        String v2 = String.valueOf(this.uri);
        StringBuilder v4 = new StringBuilder(String.valueOf(v2).length() + 4);
        v4.append("uri=");
        v4.append(v2);
        v1.append(v4.toString());
        if(this.data == null) {
            v2 = "null";
        }
        else {
            Integer v2_1 = Integer.valueOf(this.data.length);
        }

        v2 = String.valueOf(v2);
        v4 = new StringBuilder(String.valueOf(v2).length() + 9);
        v4.append(", dataSz=");
        v4.append(v2);
        v1.append(v4.toString());
        int v2_2 = this.zzdo.size();
        v4 = new StringBuilder(23);
        v4.append(", numAssets=");
        v4.append(v2_2);
        v1.append(v4.toString());
        if((v0) && !this.zzdo.isEmpty()) {
            v1.append(", assets=[");
            String v0_1 = "";
            Iterator v2_3 = this.zzdo.entrySet().iterator();
            while(v2_3.hasNext()) {
                Object v3 = v2_3.next();
                Object v4_1 = ((Map$Entry)v3).getKey();
                String v3_1 = ((Map$Entry)v3).getValue().getId();
                StringBuilder v6 = new StringBuilder(String.valueOf(v0_1).length() + 2 + String.valueOf(v4_1).length() + String.valueOf(v3_1).length());
                v6.append(v0_1);
                v6.append(((String)v4_1));
                v6.append(": ");
                v6.append(v3_1);
                v1.append(v6.toString());
                v0_1 = ", ";
            }

            v1.append("]");
        }

        v1.append(" }");
        return v1.toString();
    }
}

