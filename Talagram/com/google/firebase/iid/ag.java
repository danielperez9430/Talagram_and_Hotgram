package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final class ag implements Runnable {
    ag(af arg1, Intent arg2) {
        this.b = arg1;
        this.a = arg2;
        super();
    }

    public final void run() {
        String v1 = this.a.getAction();
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 61);
        v3.append("Service took too long to process intent: ");
        v3.append(v1);
        v3.append(" App may get closed.");
        Log.w("EnhancedIntentService", v3.toString());
        this.b.a();
    }
}

