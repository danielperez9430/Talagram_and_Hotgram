package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public final class zzcy extends DataBufferRef implements DataEvent {
    private final int zzdl;

    public zzcy(DataHolder arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.zzdl = arg3;
    }

    public final Object freeze() {
        return new zzcx(((DataEvent)this));
    }

    public final DataItem getDataItem() {
        return new zzdf(this.mDataHolder, this.mDataRow, this.zzdl);
    }

    public final int getType() {
        return this.getInteger("event_type");
    }

    public final String toString() {
        String v0;
        if(this.getType() == 1) {
            v0 = "changed";
        }
        else if(this.getType() == 2) {
            v0 = "deleted";
        }
        else {
            v0 = "unknown";
        }

        String v1 = String.valueOf(this.getDataItem());
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 32 + String.valueOf(v1).length());
        v3.append("DataEventRef{ type=");
        v3.append(v0);
        v3.append(", dataitem=");
        v3.append(v1);
        v3.append(" }");
        return v3.toString();
    }
}

