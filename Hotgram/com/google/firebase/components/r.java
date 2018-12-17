package com.google.firebase.components;

import com.google.firebase.b.c;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class r implements b {
    final class a implements c {
        private final Set a;
        private final c b;

        public a(Set arg1, c arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    private final Set a;
    private final Set b;
    private final Set c;
    private final b d;

    r(com.google.firebase.components.a arg6, b arg7) {
        super();
        HashSet v0 = new HashSet();
        HashSet v1 = new HashSet();
        Iterator v2 = arg6.b().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            if(((f)v3).c()) {
                ((Set)v0).add(((f)v3).a());
                continue;
            }

            ((Set)v1).add(((f)v3).a());
        }

        if(!arg6.d().isEmpty()) {
            ((Set)v0).add(c.class);
        }

        this.a = Collections.unmodifiableSet(((Set)v0));
        this.b = Collections.unmodifiableSet(((Set)v1));
        this.c = arg6.d();
        this.d = arg7;
    }

    public final Object a(Class arg4) {
        if(this.a.contains(arg4)) {
            Object v0 = this.d.a(arg4);
            if(!arg4.equals(c.class)) {
                return v0;
            }

            return new a(this.c, ((c)v0));
        }

        throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", arg4));
    }

    public final com.google.firebase.c.a b(Class arg4) {
        if(this.b.contains(arg4)) {
            return this.d.b(arg4);
        }

        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", arg4));
    }
}

