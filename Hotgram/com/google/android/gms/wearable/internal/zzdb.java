package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemAsset;

public final class zzdb extends DataBufferRef implements DataItemAsset {
    public zzdb(DataHolder arg1, int arg2) {
        super(arg1, arg2);
    }

    public final Object freeze() {
        return new zzcz(((DataItemAsset)this));
    }

    public final String getDataItemKey() {
        return this.getString("asset_key");
    }

    public final String getId() {
        return this.getString("asset_id");
    }
}

