package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class ah extends Binder {
    private final ad a;

    ah(ad arg1) {
        super();
        this.a = arg1;
    }

    static ad a(ah arg0) {
        return arg0.a;
    }

    public final void a(af arg4) {
        if(Binder.getCallingUid() == Process.myUid()) {
            int v1 = 3;
            if(Log.isLoggable("EnhancedIntentService", v1)) {
                Log.d("EnhancedIntentService", "service received new intent via bind strategy");
            }

            if(this.a.zzc(arg4.a)) {
                arg4.a();
                return;
            }

            if(Log.isLoggable("EnhancedIntentService", v1)) {
                Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }

            this.a.zzh.execute(new ai(this, arg4));
            return;
        }

        throw new SecurityException("Binding only allowed within app");
    }
}

