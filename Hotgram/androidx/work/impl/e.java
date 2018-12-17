package androidx.work.impl;

import android.text.TextUtils;
import androidx.work.h;
import androidx.work.impl.utils.b;
import androidx.work.j;
import androidx.work.p;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class e extends p {
    private final g a;
    private final String b;
    private final h c;
    private final List d;
    private final List e;
    private final List f;
    private final List g;
    private boolean h;

    e(g arg7, String arg8, h arg9, List arg10) {
        this(arg7, arg8, arg9, arg10, null);
    }

    e(g arg1, String arg2, h arg3, List arg4, List arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.g = arg5;
        this.e = new ArrayList(this.d.size());
        this.f = new ArrayList();
        if(arg5 != null) {
            Iterator v1 = arg5.iterator();
            while(v1.hasNext()) {
                this.f.addAll(v1.next().f);
            }
        }

        int v1_1;
        for(v1_1 = 0; v1_1 < arg4.size(); ++v1_1) {
            arg2 = arg4.get(v1_1).a();
            this.e.add(arg2);
            this.f.add(arg2);
        }
    }

    e(g arg7, List arg8) {
        this(arg7, null, h.b, arg8, null);
    }

    public g a() {
        return this.a;
    }

    public static Set a(e arg2) {
        HashSet v0 = new HashSet();
        List v2 = arg2.h();
        if(v2 != null && !v2.isEmpty()) {
            Iterator v2_1 = v2.iterator();
            while(v2_1.hasNext()) {
                ((Set)v0).addAll(v2_1.next().e());
            }
        }

        return ((Set)v0);
    }

    private static boolean a(e arg4, Set arg5) {
        arg5.addAll(arg4.e());
        Set v0 = e.a(arg4);
        Iterator v1 = arg5.iterator();
        do {
            if(!v1.hasNext()) {
                goto label_11;
            }
        }
        while(!v0.contains(v1.next()));

        return 1;
    label_11:
        List v0_1 = arg4.h();
        if(v0_1 != null && !v0_1.isEmpty()) {
            Iterator v0_2 = v0_1.iterator();
            do {
                if(v0_2.hasNext()) {
                    if(!e.a(v0_2.next(), arg5)) {
                        continue;
                    }

                    return 1;
                }

                goto label_22;
            }
            while(true);

            return 1;
        }

    label_22:
        arg5.removeAll(arg4.e());
        return 0;
    }

    public String b() {
        return this.b;
    }

    public h c() {
        return this.c;
    }

    public List d() {
        return this.d;
    }

    public List e() {
        return this.e;
    }

    public boolean f() {
        return this.h;
    }

    public void g() {
        this.h = true;
    }

    public List h() {
        return this.g;
    }

    public void i() {
        if(!this.h) {
            this.a.h().a(new b(this));
        }
        else {
            j.d("WorkContinuationImpl", String.format("Already enqueued work ids (%s)", TextUtils.join(", ", this.e)), new Throwable[0]);
        }
    }

    public boolean j() {
        return e.a(this, new HashSet());
    }
}

