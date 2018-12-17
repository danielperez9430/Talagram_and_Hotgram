package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import androidx.work.impl.g;
import androidx.work.j;

public class RescheduleReceiver extends BroadcastReceiver {
    public RescheduleReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            g v2 = g.b();
            if(v2 == null) {
                j.e("RescheduleReceiver", "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", new Throwable[0]);
            }
            else {
                v2.a(this.goAsync());
            }
        }
        else {
            arg2.startService(b.b(arg2));
        }
    }
}

