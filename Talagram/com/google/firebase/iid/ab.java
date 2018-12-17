package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;

@VisibleForTesting final class ab extends BroadcastReceiver {
    @Nullable private aa a;

    public ab(aa arg1) {
        super();
        this.a = arg1;
    }

    public final void a() {
        if(FirebaseInstanceId.g()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }

        this.a.a().registerReceiver(((BroadcastReceiver)this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public final void onReceive(Context arg3, Intent arg4) {
        if(this.a == null) {
            return;
        }

        if(!this.a.b()) {
            return;
        }

        if(FirebaseInstanceId.g()) {
            Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
        }

        FirebaseInstanceId.a(this.a, 0);
        this.a.a().unregisterReceiver(((BroadcastReceiver)this));
        this.a = null;
    }
}

