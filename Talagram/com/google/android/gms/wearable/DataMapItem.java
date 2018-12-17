package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.wearable.zze;
import com.google.android.gms.internal.wearable.zzf;
import com.google.android.gms.internal.wearable.zzg;
import java.util.ArrayList;
import java.util.List;

@VisibleForTesting public class DataMapItem {
    private final Uri uri;
    private final DataMap zzr;

    private DataMapItem(DataItem arg2) {
        super();
        this.uri = arg2.getUri();
        this.zzr = DataMapItem.zza(arg2.freeze());
    }

    public static DataMapItem fromDataItem(DataItem arg1) {
        Asserts.checkNotNull(arg1, "dataItem must not be null");
        return new DataMapItem(arg1);
    }

    public DataMap getDataMap() {
        return this.zzr;
    }

    public Uri getUri() {
        return this.uri;
    }

    private static DataMap zza(DataItem arg6) {
        String v2_1;
        if(arg6.getData() == null) {
            if(arg6.getAssets().size() <= 0) {
            }
            else {
                throw new IllegalArgumentException("Cannot create DataMapItem from a DataItem  that wasn\'t made with DataMapItem.");
            }
        }

        if(arg6.getData() == null) {
            return new DataMap();
        }

        try {
            ArrayList v1_1 = new ArrayList();
            int v2 = arg6.getAssets().size();
            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= v2) {
                    goto label_47;
                }

                Object v4 = arg6.getAssets().get(Integer.toString(v3));
                if(v4 == null) {
                    break;
                }

                ((List)v1_1).add(Asset.createFromRef(((DataItemAsset)v4).getId()));
            }

            v2_1 = String.valueOf(arg6);
            StringBuilder v5 = new StringBuilder(String.valueOf(v2_1).length() + 64);
            v5.append("Cannot find DataItemAsset referenced in data at ");
            v5.append(v3);
            v5.append(" for ");
            v5.append(v2_1);
            throw new IllegalStateException(v5.toString());
        label_47:
            return zze.zza(new zzf(zzg.zza(arg6.getData()), ((List)v1_1)));
        }
        catch(NullPointerException v1) {
            v2_1 = String.valueOf(arg6.getUri());
            String v0 = Base64.encodeToString(arg6.getData(), 0);
            StringBuilder v4_1 = new StringBuilder(String.valueOf(v2_1).length() + 50 + String.valueOf(v0).length());
            v4_1.append("Unable to parse datamap from dataItem. uri=");
            v4_1.append(v2_1);
            v4_1.append(", data=");
            v4_1.append(v0);
            Log.w("DataItem", v4_1.toString());
            String v6 = String.valueOf(arg6.getUri());
            StringBuilder v3_1 = new StringBuilder(String.valueOf(v6).length() + 44);
            v3_1.append("Unable to parse datamap from dataItem.  uri=");
            v3_1.append(v6);
            throw new IllegalStateException(v3_1.toString(), ((Throwable)v1));
        }
    }
}

