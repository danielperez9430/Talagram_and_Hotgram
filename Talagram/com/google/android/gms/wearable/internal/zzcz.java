package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemAsset;

public final class zzcz implements DataItemAsset {
    private final String zzdm;
    private final String zzdn;

    public zzcz(DataItemAsset arg2) {
        super();
        this.zzdm = arg2.getId();
        this.zzdn = arg2.getDataItemKey();
    }

    public final Object freeze() {
        return this;
    }

    public final String getDataItemKey() {
        return this.zzdn;
    }

    public final String getId() {
        return this.zzdm;
    }

    public final boolean isDataValid() {
        return 1;
    }

    public final String toString() {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append("DataItemAssetEntity[");
        v0.append("@");
        v0.append(Integer.toHexString(this.hashCode()));
        if(this.zzdm == null) {
            v1 = ",noid";
        }
        else {
            v0.append(",");
            v1 = this.zzdm;
        }

        v0.append(v1);
        v0.append(", key=");
        v0.append(this.zzdn);
        v0.append("]");
        return v0.toString();
    }
}

