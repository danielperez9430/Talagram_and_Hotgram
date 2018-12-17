package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

@Deprecated public class FirebaseInstanceIdService extends ad {
    public FirebaseInstanceIdService() {
        super();
    }

    @Deprecated public void onTokenRefresh() {
    }

    protected final Intent zzb(Intent arg1) {
        return x.a().a.poll();
    }

    public final void zzd(Intent arg5) {
        if("com.google.firebase.iid.TOKEN_REFRESH".equals(arg5.getAction())) {
            this.onTokenRefresh();
            return;
        }

        String v0 = arg5.getStringExtra("CMD");
        if(v0 != null) {
            if(Log.isLoggable("FirebaseInstanceId", 3)) {
                String v5 = String.valueOf(arg5.getExtras());
                StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 21 + String.valueOf(v5).length());
                v3.append("Received command: ");
                v3.append(v0);
                v3.append(" - ");
                v3.append(v5);
                Log.d("FirebaseInstanceId", v3.toString());
            }

            if(!"RST".equals(v0)) {
                if("RST_FULL".equals(v0)) {
                }
                else {
                    if("SYNC".equals(v0)) {
                        FirebaseInstanceId.a().l();
                    }
                    else {
                    }

                    return;
                }
            }

            FirebaseInstanceId.a().h();
        }
    }
}

