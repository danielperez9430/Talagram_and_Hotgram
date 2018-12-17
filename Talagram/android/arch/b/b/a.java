package android.arch.b.b;

import android.arch.b.a.c$c;
import android.content.Context;
import java.util.List;
import java.util.Set;

public class a {
    public final c a;
    public final Context b;
    public final String c;
    public final d d;
    public final List e;
    public final boolean f;
    public final android.arch.b.b.e$c g;
    public final boolean h;
    private final Set i;

    public a(Context arg1, String arg2, c arg3, d arg4, List arg5, boolean arg6, android.arch.b.b.e$c arg7, boolean arg8, Set arg9) {
        super();
        this.a = arg3;
        this.b = arg1;
        this.c = arg2;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
        this.h = arg8;
        this.i = arg9;
    }

    public boolean a(int arg2) {
        boolean v2;
        if(this.h) {
            if(this.i != null && (this.i.contains(Integer.valueOf(arg2)))) {
                goto label_10;
            }

            v2 = true;
        }
        else {
        label_10:
            v2 = false;
        }

        return v2;
    }
}

