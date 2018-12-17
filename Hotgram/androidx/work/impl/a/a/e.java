package androidx.work.impl.a.a;

import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.a.b.g;
import androidx.work.impl.a.b;
import androidx.work.j;
import androidx.work.k;

public class e extends c {
    public e(Context arg1) {
        super(g.a(arg1).c());
    }

    boolean a(b arg5) {
        boolean v1 = true;
        if(Build$VERSION.SDK_INT < 26) {
            j.b("NetworkMeteredCtrlr", "Metered network constraint is not supported before API 26, only checking for connected state.", new Throwable[0]);
            return arg5.a() ^ 1;
        }

        if(arg5.a()) {
            if(!arg5.c()) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    boolean a(androidx.work.impl.b.j arg2) {
        boolean v2 = arg2.j.a() == k.e ? true : false;
        return v2;
    }

    boolean b(Object arg1) {
        return this.a(((b)arg1));
    }
}

