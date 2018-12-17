package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.j;

public abstract class c extends d {
    class androidx.work.impl.a.b.c$1 extends BroadcastReceiver {
        androidx.work.impl.a.b.c$1(c arg1) {
            this.a = arg1;
            super();
        }

        public void onReceive(Context arg2, Intent arg3) {
            if(arg3 != null) {
                this.a.a(arg2, arg3);
            }
        }
    }

    private final BroadcastReceiver b;

    public c(Context arg1) {
        super(arg1);
        this.b = new androidx.work.impl.a.b.c$1(this);
    }

    public abstract void a(Context arg1, Intent arg2);

    public abstract IntentFilter b();

    public void d() {
        j.b("BrdcstRcvrCnstrntTrckr", String.format("%s: registering receiver", this.getClass().getSimpleName()), new Throwable[0]);
        this.a.registerReceiver(this.b, this.b());
    }

    public void e() {
        j.b("BrdcstRcvrCnstrntTrckr", String.format("%s: unregistering receiver", this.getClass().getSimpleName()), new Throwable[0]);
        this.a.unregisterReceiver(this.b);
    }
}

