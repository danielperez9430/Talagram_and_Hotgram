package androidx.work.impl.background.systemalarm;

import android.content.Context;
import androidx.work.impl.b.j;
import androidx.work.impl.c;

public class f implements c {
    private final Context a;

    public f(Context arg1) {
        super();
        this.a = arg1.getApplicationContext();
    }

    private void a(j arg6) {
        androidx.work.j.b("SystemAlarmScheduler", String.format("Scheduling work with workSpecId %s", arg6.a), new Throwable[0]);
        this.a.startService(b.a(this.a, arg6.a));
    }

    public void a(String arg2) {
        this.a.startService(b.c(this.a, arg2));
    }

    public void a(j[] arg4) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a(arg4[v1]);
        }
    }
}

