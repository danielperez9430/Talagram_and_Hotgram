package com.google.firebase.iid;

import android.util.Log;

final class ai implements Runnable {
    ai(ah arg1, af arg2) {
        this.b = arg1;
        this.a = arg2;
        super();
    }

    public final void run() {
        if(Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }

        ah.a(this.b).zzd(this.a.a);
        this.a.a();
    }
}

