package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.TimeUnit;

final class i implements Runnable {
    private final f a;

    i(f arg1) {
        super();
        this.a = arg1;
    }

    public final void run() {
        Object v1_1;
        int v2;
        f v0 = this.a;
        while(true) {
            __monitor_enter(v0);
            try {
                v2 = 2;
                if(v0.a != v2) {
                    __monitor_exit(v0);
                    return;
                }

                if(v0.d.isEmpty()) {
                    v0.a();
                    __monitor_exit(v0);
                    return;
                }

                v1_1 = v0.d.poll();
                v0.e.put(((m)v1_1).a, v1_1);
                d.b(v0.f).schedule(new j(v0, ((m)v1_1)), 30, TimeUnit.SECONDS);
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                break;
            }

            if(Log.isLoggable("MessengerIpcClient", 3)) {
                String v4 = String.valueOf(v1_1);
                StringBuilder v6 = new StringBuilder(String.valueOf(v4).length() + 8);
                v6.append("Sending ");
                v6.append(v4);
                Log.d("MessengerIpcClient", v6.toString());
            }

            Context v3 = d.a(v0.f);
            Messenger v4_1 = v0.b;
            Message v5 = Message.obtain();
            v5.what = ((m)v1_1).c;
            v5.arg1 = ((m)v1_1).a;
            v5.replyTo = v4_1;
            Bundle v4_2 = new Bundle();
            v4_2.putBoolean("oneWay", ((m)v1_1).a());
            v4_2.putString("pkg", v3.getPackageName());
            v4_2.putBundle("data", ((m)v1_1).d);
            v5.setData(v4_2);
            try {
                v0.c.a(v5);
            }
            catch(RemoteException v1_2) {
                v0.a(v2, v1_2.getMessage());
            }
        }

        try {
        label_71:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_71;
        }

        throw v1;
    }
}

