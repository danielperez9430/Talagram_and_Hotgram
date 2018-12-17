package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

public abstract class ResultCallbacks implements ResultCallback {
    public ResultCallbacks() {
        super();
    }

    public abstract void onFailure(Status arg1);

    @KeepForSdk public final void onResult(Result arg5) {
        Status v0 = arg5.getStatus();
        if(v0.isSuccess()) {
            this.onSuccess(arg5);
            return;
        }

        this.onFailure(v0);
        if((arg5 instanceof Releasable)) {
            try {
                arg5.release();
                return;
            }
            catch(RuntimeException v0_1) {
                String v5 = String.valueOf(arg5);
                StringBuilder v3 = new StringBuilder(String.valueOf(v5).length() + 18);
                v3.append("Unable to release ");
                v3.append(v5);
                Log.w("ResultCallbacks", v3.toString(), ((Throwable)v0_1));
            }
        }
    }

    public abstract void onSuccess(Result arg1);
}

