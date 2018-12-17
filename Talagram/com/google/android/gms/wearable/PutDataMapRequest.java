package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.wearable.zze;
import com.google.android.gms.internal.wearable.zzf;
import com.google.android.gms.internal.wearable.zzt;

@VisibleForTesting public class PutDataMapRequest {
    private final DataMap zzr;
    private final PutDataRequest zzs;

    private PutDataMapRequest(PutDataRequest arg1, DataMap arg2) {
        super();
        this.zzs = arg1;
        this.zzr = new DataMap();
        if(arg2 != null) {
            this.zzr.putAll(arg2);
        }
    }

    public PutDataRequest asPutDataRequest() {
        String v1_1;
        Object v4;
        zzf v0 = zze.zza(this.zzr);
        this.zzs.setData(zzt.zzb(v0.zzfw));
        int v1 = v0.zzfx.size();
        int v2;
        for(v2 = 0; true; ++v2) {
            if(v2 >= v1) {
                goto label_66;
            }

            String v3 = Integer.toString(v2);
            v4 = v0.zzfx.get(v2);
            if(v3 == null) {
                break;
            }

            if(v4 == null) {
                v1_1 = "asset cannot be null: key=";
                String v2_1 = String.valueOf(v3);
                v1_1 = v2_1.length() != 0 ? v1_1.concat(v2_1) : new String(v1_1);
                throw new IllegalStateException(v1_1);
            }

            if(Log.isLoggable("DataMap", 3)) {
                String v6 = String.valueOf(v4);
                StringBuilder v8 = new StringBuilder(String.valueOf(v3).length() + 33 + String.valueOf(v6).length());
                v8.append("asPutDataRequest: adding asset: ");
                v8.append(v3);
                v8.append(" ");
                v8.append(v6);
                Log.d("DataMap", v8.toString());
            }

            this.zzs.putAsset(v3, ((Asset)v4));
        }

        v1_1 = String.valueOf(v4);
        StringBuilder v3_1 = new StringBuilder(String.valueOf(v1_1).length() + 26);
        v3_1.append("asset key cannot be null: ");
        v3_1.append(v1_1);
        throw new IllegalStateException(v3_1.toString());
    label_66:
        return this.zzs;
    }

    public static PutDataMapRequest create(String arg2) {
        Asserts.checkNotNull(arg2, "path must not be null");
        return new PutDataMapRequest(PutDataRequest.create(arg2), null);
    }

    public static PutDataMapRequest createFromDataMapItem(DataMapItem arg2) {
        Asserts.checkNotNull(arg2, "source must not be null");
        return new PutDataMapRequest(PutDataRequest.zza(arg2.getUri()), arg2.getDataMap());
    }

    public static PutDataMapRequest createWithAutoAppendedId(String arg2) {
        Asserts.checkNotNull(arg2, "pathPrefix must not be null");
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(arg2), null);
    }

    public DataMap getDataMap() {
        return this.zzr;
    }

    public Uri getUri() {
        return this.zzs.getUri();
    }

    public boolean isUrgent() {
        return this.zzs.isUrgent();
    }

    public PutDataMapRequest setUrgent() {
        this.zzs.setUrgent();
        return this;
    }
}

