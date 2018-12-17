package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class j extends Service implements g {
    private final r a;

    public j() {
        super();
        this.a = new r(((g)this));
    }

    public d getLifecycle() {
        return this.a.e();
    }

    public IBinder onBind(Intent arg1) {
        this.a.b();
        return null;
    }

    public void onCreate() {
        this.a.a();
        super.onCreate();
    }

    public void onDestroy() {
        this.a.d();
        super.onDestroy();
    }

    public void onStart(Intent arg2, int arg3) {
        this.a.c();
        super.onStart(arg2, arg3);
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        return super.onStartCommand(arg1, arg2, arg3);
    }
}

