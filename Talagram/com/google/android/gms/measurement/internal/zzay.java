package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

class zzay extends BroadcastReceiver {
    @VisibleForTesting private static final String zzabi = "com.google.android.gms.measurement.internal.zzay";
    private boolean zzabj;
    private boolean zzabk;
    private final zzfa zzamz;

    static {
    }

    zzay(zzfa arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzamz = arg1;
    }

    public void onReceive(Context arg2, Intent arg3) {
        this.zzamz.zzlr();
        String v2 = arg3.getAction();
        this.zzamz.zzgo().zzjl().zzg("NetworkBroadcastReceiver received action", v2);
        if("android.net.conn.CONNECTIVITY_CHANGE".equals(v2)) {
            boolean v2_1 = this.zzamz.zzlo().zzfb();
            if(this.zzabk != v2_1) {
                this.zzabk = v2_1;
                this.zzamz.zzgn().zzc(new zzaz(this, v2_1));
            }

            return;
        }

        this.zzamz.zzgo().zzjg().zzg("NetworkBroadcastReceiver received unknown action", v2);
    }

    public final void unregister() {
        this.zzamz.zzlr();
        this.zzamz.zzgn().zzaf();
        this.zzamz.zzgn().zzaf();
        if(!this.zzabj) {
            return;
        }

        this.zzamz.zzgo().zzjl().zzbx("Unregistering connectivity change receiver");
        this.zzabj = false;
        this.zzabk = false;
        Context v0 = this.zzamz.getContext();
        try {
            v0.unregisterReceiver(((BroadcastReceiver)this));
            return;
        }
        catch(IllegalArgumentException v0_1) {
            this.zzamz.zzgo().zzjd().zzg("Failed to unregister the network broadcast receiver", v0_1);
            return;
        }
    }

    static zzfa zza(zzay arg0) {
        return arg0.zzamz;
    }

    public final void zzey() {
        this.zzamz.zzlr();
        this.zzamz.zzgn().zzaf();
        if(this.zzabj) {
            return;
        }

        this.zzamz.getContext().registerReceiver(((BroadcastReceiver)this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzabk = this.zzamz.zzlo().zzfb();
        this.zzamz.zzgo().zzjl().zzg("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzabk));
        this.zzabj = true;
    }
}

