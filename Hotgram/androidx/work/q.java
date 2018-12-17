package androidx.work;

import android.content.Context;
import androidx.work.impl.g;
import java.util.Arrays;
import java.util.List;

public abstract class q {
    protected q() {
        super();
    }

    public static void a(Context arg0, b arg1) {
        g.b(arg0, arg1);
    }

    public static q a() {
        g v0 = g.b();
        if(v0 != null) {
            return ((q)v0);
        }

        throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
    }

    public abstract void a(String arg1);

    public abstract void a(String arg1, androidx.work.g arg2, m arg3);

    public abstract void a(List arg1);

    public final void a(r[] arg1) {
        this.a(Arrays.asList(((Object[])arg1)));
    }
}

