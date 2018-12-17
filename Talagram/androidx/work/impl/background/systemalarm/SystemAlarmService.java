package androidx.work.impl.background.systemalarm;

import android.arch.lifecycle.j;
import android.content.Context;
import android.content.Intent;

public class SystemAlarmService extends j implements b {
    private e a;

    public SystemAlarmService() {
        super();
    }

    public void a() {
        androidx.work.j.b("SystemAlarmService", "All commands completed in dispatcher", new Throwable[0]);
        this.stopSelf();
    }

    public void onCreate() {
        super.onCreate();
        this.a = new e(((Context)this));
        this.a.a(((b)this));
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.a();
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        super.onStartCommand(arg1, arg2, arg3);
        if(arg1 != null) {
            this.a.a(arg1, arg3);
        }

        return 1;
    }
}

