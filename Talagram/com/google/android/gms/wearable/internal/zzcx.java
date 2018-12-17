package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataItem;

public final class zzcx implements DataEvent {
    private int type;
    private DataItem zzdg;

    public zzcx(DataEvent arg2) {
        super();
        this.type = arg2.getType();
        this.zzdg = arg2.getDataItem().freeze();
    }

    public final Object freeze() {
        return this;
    }

    public final DataItem getDataItem() {
        return this.zzdg;
    }

    public final int getType() {
        return this.type;
    }

    public final boolean isDataValid() {
        return 1;
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
        StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 35 + String.valueOf(v1).length());
        v3.append("DataEventEntity{ type=");
        v3.append(v0);
        v3.append(", dataitem=");
        v3.append(v1);
        v3.append(" }");
        return v3.toString();
    }
}

