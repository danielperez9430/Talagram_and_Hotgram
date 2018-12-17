package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.b.c;
import com.google.firebase.b.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class l implements b {
    private final List a;
    private final Map b;
    private final n c;

    public l(Executor arg6, Iterable arg7, a[] arg8) {
        super();
        this.b = new HashMap();
        this.c = new n(arg6);
        ArrayList v6 = new ArrayList();
        ((List)v6).add(a.a(this.c, n.class, new Class[]{d.class, c.class}));
        Iterator v7 = arg7.iterator();
        while(v7.hasNext()) {
            ((List)v6).addAll(v7.next().getComponents());
        }

        Collections.addAll(((Collection)v6), ((Object[])arg8));
        this.a = Collections.unmodifiableList(m.a(((List)v6)));
        Iterator v6_1 = this.a.iterator();
        while(v6_1.hasNext()) {
            this.a(v6_1.next());
        }

        this.a();
    }

    public final Object a(Class arg1) {
        return com.google.firebase.components.c.a(((b)this), arg1);
    }

    public final void a(boolean arg4) {
        Iterator v0 = this.a.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(!((a)v1).e()) {
                if(!((a)v1).f()) {
                    continue;
                }

                if(arg4) {
                    goto label_10;
                }

                continue;
            }

        label_10:
            this.a(((a)v1).a().iterator().next());
        }

        this.c.a();
    }

    private void a(a arg4) {
        p v0 = new p(arg4.c(), new r(arg4, ((b)this)));
        Iterator v4 = arg4.a().iterator();
        while(v4.hasNext()) {
            this.b.put(v4.next(), v0);
        }
    }

    private void a() {
        Object v3;
        Iterator v0 = this.a.iterator();
    label_2:
        if(v0.hasNext()) {
            Object v1 = v0.next();
            Iterator v2 = ((a)v1).b().iterator();
            while(true) {
                if(!v2.hasNext()) {
                    goto label_2;
                }

                v3 = v2.next();
                if(!((f)v3).b()) {
                    continue;
                }

                if(!this.b.containsKey(((f)v3).a())) {
                    break;
                }
            }

            throw new i(String.format("Unsatisfied dependency for component %s: %s", v1, ((f)v3).a()));
        }
    }

    public final com.google.firebase.c.a b(Class arg2) {
        Preconditions.checkNotNull(arg2, "Null interface requested.");
        return this.b.get(arg2);
    }
}

