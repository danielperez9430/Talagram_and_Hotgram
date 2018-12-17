package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.b.d;
import androidx.work.impl.b.e;
import androidx.work.impl.g;
import androidx.work.impl.utils.c;
import androidx.work.j;

class a {
    public static void a(Context arg3, g arg4, String arg5) {
        e v4 = arg4.d().p();
        d v0 = v4.a(arg5);
        if(v0 != null) {
            a.a(arg3, arg5, v0.b);
            j.b("Alarms", String.format("Removing SystemIdInfo for workSpecId (%s)", arg5), new Throwable[0]);
            v4.b(arg5);
        }
    }

    private static void a(Context arg5, String arg6, int arg7) {
        Object v0 = arg5.getSystemService("alarm");
        PendingIntent v5 = PendingIntent.getService(arg5, arg7, b.b(arg5, arg6), 536870912);
        if(v5 != null && v0 != null) {
            j.b("Alarms", String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", arg6, Integer.valueOf(arg7)), new Throwable[0]);
            ((AlarmManager)v0).cancel(v5);
        }
    }

    public static void a(Context arg2, g arg3, String arg4, long arg5) {
        e v3 = arg3.d().p();
        d v0 = v3.a(arg4);
        if(v0 != null) {
            a.a(arg2, arg4, v0.b);
            a.a(arg2, arg4, v0.b, arg5);
        }
        else {
            int v0_1 = new c(arg2).a();
            v3.a(new d(arg4, v0_1));
            a.a(arg2, arg4, v0_1, arg5);
        }
    }

    private static void a(Context arg2, String arg3, int arg4, long arg5) {
        Object v0 = arg2.getSystemService("alarm");
        PendingIntent v2 = PendingIntent.getService(arg2, arg4, b.b(arg2, arg3), 1073741824);
        if(v0 != null) {
            if(Build$VERSION.SDK_INT >= 19) {
                ((AlarmManager)v0).setExact(0, arg5, v2);
            }
            else {
                ((AlarmManager)v0).set(0, arg5, v2);
            }
        }
    }
}

