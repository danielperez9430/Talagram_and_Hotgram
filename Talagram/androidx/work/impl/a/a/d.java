package androidx.work.impl.a.a;

import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.a.b.g;
import androidx.work.impl.a.b;
import androidx.work.impl.b.j;
import androidx.work.k;

public class d extends c {
    public d(Context arg1) {
        super(g.a(arg1).c());
    }

    boolean a(b arg4) {
        boolean v1 = true;
        if(Build$VERSION.SDK_INT >= 26) {
            if(arg4.a()) {
                if(!arg4.b()) {
                }
                else {
                    v1 = false;
                }
            }

            return v1;
        }

        return arg4.a() ^ 1;
    }

    boolean a(j arg2) {
        boolean v2 = arg2.j.a() == k.b ? true : false;
        return v2;
    }

    boolean b(Object arg1) {
        return this.a(((b)arg1));
    }
}

