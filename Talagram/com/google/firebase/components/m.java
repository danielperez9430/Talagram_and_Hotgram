package com.google.firebase.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class m {
    final class a {
        private final com.google.firebase.components.a a;
        private final Set b;
        private final Set c;

        a(com.google.firebase.components.a arg2) {
            super();
            this.b = new HashSet();
            this.c = new HashSet();
            this.a = arg2;
        }

        final void a(a arg2) {
            this.b.add(arg2);
        }

        final Set a() {
            return this.b;
        }

        final com.google.firebase.components.a b() {
            return this.a;
        }

        final void b(a arg2) {
            this.c.add(arg2);
        }

        final void c(a arg2) {
            this.c.remove(arg2);
        }

        final boolean c() {
            return this.c.isEmpty();
        }

        final boolean d() {
            return this.b.isEmpty();
        }
    }

    static List a(List arg7) {
        Object v4;
        Object v2;
        HashMap v0 = new HashMap(arg7.size());
        Iterator v1 = arg7.iterator();
    label_4:
        if(v1.hasNext()) {
            v2 = v1.next();
            a v3 = new a(((com.google.firebase.components.a)v2));
            Iterator v2_1 = ((com.google.firebase.components.a)v2).a().iterator();
            while(true) {
                if(!v2_1.hasNext()) {
                    goto label_4;
                }

                v4 = v2_1.next();
                if(((Map)v0).put(v4, v3) != null) {
                    break;
                }
            }

            throw new IllegalArgumentException(String.format("Multiple components provide %s.", v4));
        }

        v1 = ((Map)v0).values().iterator();
    label_28:
        if(v1.hasNext()) {
            v2 = v1.next();
            Iterator v3_1 = ((a)v2).b().b().iterator();
            while(true) {
                if(!v3_1.hasNext()) {
                    goto label_28;
                }

                v4 = v3_1.next();
                if(!((f)v4).c()) {
                    continue;
                }

                v4 = ((Map)v0).get(((f)v4).a());
                if(v4 == null) {
                    continue;
                }

                ((a)v2).a(((a)v4));
                ((a)v4).b(((a)v2));
            }
        }

        HashSet v1_1 = new HashSet(((Map)v0).values());
        Set v0_1 = m.a(((Set)v1_1));
        ArrayList v2_2 = new ArrayList();
    label_51:
        if(!v0_1.isEmpty()) {
            Object v3_2 = v0_1.iterator().next();
            v0_1.remove(v3_2);
            ((List)v2_2).add(((a)v3_2).b());
            Iterator v4_1 = ((a)v3_2).a().iterator();
            while(true) {
                if(!v4_1.hasNext()) {
                    goto label_51;
                }

                Object v5 = v4_1.next();
                ((a)v5).c(((a)v3_2));
                if(!((a)v5).c()) {
                    continue;
                }

                v0_1.add(v5);
            }
        }

        if(((List)v2_2).size() == arg7.size()) {
            Collections.reverse(((List)v2_2));
            return ((List)v2_2);
        }

        ArrayList v7 = new ArrayList();
        Iterator v0_2 = ((Set)v1_1).iterator();
        while(v0_2.hasNext()) {
            Object v1_2 = v0_2.next();
            if(((a)v1_2).c()) {
                continue;
            }

            if(((a)v1_2).d()) {
                continue;
            }

            ((List)v7).add(((a)v1_2).b());
        }

        throw new g(((List)v7));
    }

    private static Set a(Set arg3) {
        HashSet v0 = new HashSet();
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v1 = v3.next();
            if(!((a)v1).c()) {
                continue;
            }

            ((Set)v0).add(v1);
        }

        return ((Set)v0);
    }
}

