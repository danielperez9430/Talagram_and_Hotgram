package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class GooglePlayServicesUpdatedReceiver extends BroadcastReceiver {
    public abstract class Callback {
        public Callback() {
            super();
        }

        public abstract void zzv();
    }

    private Context mContext;
    private final Callback zzkt;

    public GooglePlayServicesUpdatedReceiver(Callback arg1) {
        super();
        this.zzkt = arg1;
    }

    public final void onReceive(Context arg1, Intent arg2) {
        Uri v1 = arg2.getData();
        if(v1 != null) {
            String v1_1 = v1.getSchemeSpecificPart();
        }
        else {
            Object v1_2 = null;
        }

        if("com.google.android.gms".equals(v1_1)) {
            this.zzkt.zzv();
            this.unregister();
        }
    }

    public final void unregister() {
        __monitor_enter(this);
        try {
            if(this.mContext != null) {
                this.mContext.unregisterReceiver(((BroadcastReceiver)this));
            }

            this.mContext = null;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public final void zzc(Context arg1) {
        this.mContext = arg1;
    }
}

