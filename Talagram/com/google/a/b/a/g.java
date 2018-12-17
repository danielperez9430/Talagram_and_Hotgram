package com.google.a.b.a;

import com.google.a.b.e;
import com.google.a.b.h;
import com.google.a.b.j;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.l;
import com.google.a.q;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public final class g implements w {
    final class a extends v {
        private final v b;
        private final v c;
        private final h d;

        public a(g arg1, f arg2, Type arg3, v arg4, Type arg5, v arg6, h arg7) {
            this.a = arg1;
            super();
            this.b = new m(arg2, arg4, arg3);
            this.c = new m(arg2, arg6, arg5);
            this.d = arg7;
        }

        private String a(l arg2) {
            if(arg2.i()) {
                q v2 = arg2.m();
                if(v2.p()) {
                    return String.valueOf(v2.a());
                }

                if(v2.o()) {
                    return Boolean.toString(v2.f());
                }

                if(v2.q()) {
                    return v2.b();
                }

                throw new AssertionError();
            }

            if(arg2.j()) {
                return "null";
            }

            throw new AssertionError();
        }

        public Map a(com.google.a.d.a arg4) {
            StringBuilder v1_1;
            Object v0_1;
            b v0 = arg4.f();
            if(v0 == b.i) {
                arg4.j();
                return null;
            }

            Object v1 = this.d.a();
            if(v0 == b.a) {
                arg4.a();
                while(true) {
                    if(arg4.e()) {
                        arg4.a();
                        v0_1 = this.b.read(arg4);
                        if(((Map)v1).put(v0_1, this.c.read(arg4)) == null) {
                            arg4.b();
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        goto label_31;
                    }

                    goto label_55;
                }

                v1_1 = new StringBuilder();
                v1_1.append("duplicate key: ");
                v1_1.append(v0_1);
                throw new t(v1_1.toString());
            label_31:
                arg4.b();
            }
            else {
                arg4.c();
                while(true) {
                    if(arg4.e()) {
                        e.a.a(arg4);
                        v0_1 = this.b.read(arg4);
                        if(((Map)v1).put(v0_1, this.c.read(arg4)) == null) {
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        goto label_54;
                    }

                    goto label_55;
                }

                v1_1 = new StringBuilder();
                v1_1.append("duplicate key: ");
                v1_1.append(v0_1);
                throw new t(v1_1.toString());
            label_54:
                arg4.d();
            }

        label_55:
            return ((Map)v1);
        }

        public void a(c arg8, Map arg9) {
            int v9_1;
            Iterator v9;
            if(arg9 == null) {
                arg8.f();
                return;
            }

            if(!this.a.a) {
                arg8.d();
                v9 = arg9.entrySet().iterator();
                while(v9.hasNext()) {
                    Object v0 = v9.next();
                    arg8.a(String.valueOf(((Map$Entry)v0).getKey()));
                    this.c.write(arg8, ((Map$Entry)v0).getValue());
                }

                arg8.e();
                return;
            }

            ArrayList v0_1 = new ArrayList(arg9.size());
            ArrayList v1 = new ArrayList(arg9.size());
            v9 = arg9.entrySet().iterator();
            int v2 = 0;
            int v3;
            for(v3 = 0; v9.hasNext(); v3 |= v4_1) {
                Object v4 = v9.next();
                l v5 = this.b.toJsonTree(((Map$Entry)v4).getKey());
                ((List)v0_1).add(v5);
                ((List)v1).add(((Map$Entry)v4).getValue());
                int v4_1 = (v5.g()) || (v5.h()) ? 1 : 0;
            }

            if(v3 != 0) {
                arg8.b();
                v9_1 = ((List)v0_1).size();
                goto label_53;
            }
            else {
                arg8.d();
                v9_1 = ((List)v0_1).size();
                while(v2 < v9_1) {
                    arg8.a(this.a(((List)v0_1).get(v2)));
                    this.c.write(arg8, ((List)v1).get(v2));
                    ++v2;
                }

                arg8.e();
                return;
            label_53:
                while(v2 < v9_1) {
                    arg8.b();
                    j.a(((List)v0_1).get(v2), arg8);
                    this.c.write(arg8, ((List)v1).get(v2));
                    arg8.c();
                    ++v2;
                }

                arg8.c();
            }
        }

        public Object read(com.google.a.d.a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Map)arg2));
        }
    }

    final boolean a;
    private final com.google.a.b.c b;

    public g(com.google.a.b.c arg1, boolean arg2) {
        super();
        this.b = arg1;
        this.a = arg2;
    }

    private v a(f arg2, Type arg3) {
        v v2 = arg3 == Boolean.TYPE || arg3 == Boolean.class ? n.f : arg2.a(com.google.a.c.a.a(arg3));
        return v2;
    }

    public v create(f arg12, com.google.a.c.a arg13) {
        Type v0 = arg13.b();
        if(!Map.class.isAssignableFrom(arg13.a())) {
            return null;
        }

        Type[] v0_1 = com.google.a.b.b.b(v0, com.google.a.b.b.e(v0));
        return new a(this, arg12, v0_1[0], this.a(arg12, v0_1[0]), v0_1[1], arg12.a(com.google.a.c.a.a(v0_1[1])), this.b.a(arg13));
    }
}

