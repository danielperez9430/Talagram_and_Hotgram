package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.c;
import androidx.work.j;
import androidx.work.k;
import java.util.Iterator;
import java.util.List;

abstract class ConstraintProxy extends BroadcastReceiver {
    public class BatteryChargingProxy extends ConstraintProxy {
        public BatteryChargingProxy() {
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            super.onReceive(arg1, arg2);
        }
    }

    public class BatteryNotLowProxy extends ConstraintProxy {
        public BatteryNotLowProxy() {
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            super.onReceive(arg1, arg2);
        }
    }

    public class NetworkStateProxy extends ConstraintProxy {
        public NetworkStateProxy() {
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            super.onReceive(arg1, arg2);
        }
    }

    public class StorageNotLowProxy extends ConstraintProxy {
        public StorageNotLowProxy() {
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            super.onReceive(arg1, arg2);
        }
    }

    ConstraintProxy() {
        super();
    }

    static void a(Context arg7, List arg8) {
        Iterator v8 = arg8.iterator();
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        do {
        label_6:
            if(!v8.hasNext()) {
                break;
            }

            c v5 = v8.next().j;
            v1 |= v5.d();
            v2 |= v5.b();
            v3 |= v5.e();
            int v5_1 = v5.a() != k.a ? 1 : 0;
            v4 |= v5_1;
            if(v1 == 0) {
                goto label_6;
            }

            if(v2 == 0) {
                goto label_6;
            }

            if(v3 == 0) {
                goto label_6;
            }
        }
        while(v4 == 0);

        arg7.sendBroadcast(ConstraintProxyUpdateReceiver.a(((boolean)v1), ((boolean)v2), ((boolean)v3), ((boolean)v4)));
    }

    public void onReceive(Context arg5, Intent arg6) {
        j.b("ConstraintProxy", String.format("onReceive : %s", arg6), new Throwable[0]);
        arg5.startService(b.a(arg5));
    }
}

