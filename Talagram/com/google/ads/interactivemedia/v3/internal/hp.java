package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class hp implements gq {
    public final class a extends gp {
        private final hd a;
        private final Map b;

        a(hd arg1, Map arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public Object read(hx arg4) {
            if(arg4.f() == hy.i) {
                arg4.j();
                return null;
            }

            Object v0 = this.a.a();
            try {
                arg4.c();
                while(arg4.e()) {
                    Object v1 = this.b.get(arg4.g());
                    if(v1 != null) {
                        if(!((b)v1).j) {
                        }
                        else {
                            ((b)v1).a(arg4, v0);
                            continue;
                        }
                    }

                    arg4.n();
                }
            }
            catch(IllegalAccessException v4) {
                goto label_27;
            }
            catch(IllegalStateException v4_1) {
                goto label_31;
            }

            arg4.d();
            return v0;
        label_31:
            throw new gn(((Throwable)v4_1));
        label_27:
            throw new AssertionError(v4);
        }

        public void write(hz arg4, Object arg5) {
            if(arg5 == null) {
                arg4.f();
                return;
            }

            arg4.d();
            try {
                Iterator v0 = this.b.values().iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    if(!((b)v1).a(arg5)) {
                        continue;
                    }

                    arg4.a(((b)v1).h);
                    ((b)v1).a(arg4, arg5);
                }
            }
            catch(IllegalAccessException v4) {
                goto label_21;
            }

            arg4.e();
            return;
        label_21:
            throw new AssertionError(v4);
        }
    }

    abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        protected b(String arg1, boolean arg2, boolean arg3) {
            super();
            this.h = arg1;
            this.i = arg2;
            this.j = arg3;
        }

        abstract void a(hx arg1, Object arg2);

        abstract void a(hz arg1, Object arg2);

        abstract boolean a(Object arg1);
    }

    private final gy a;
    private final fy b;
    private final gz c;
    private final hk d;

    public hp(gy arg1, fy arg2, gz arg3, hk arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    private b a(fz arg14, Field arg15, String arg16, hw arg17, boolean arg18, boolean arg19) {
        hp v11 = this;
        hw v9 = arg17;
        boolean v10 = he.a(arg17.a());
        Annotation v0 = arg15.getAnnotation(gs.class);
        gp v0_1 = v0 != null ? v11.d.a(v11.a, arg14, v9, ((gs)v0)) : null;
        boolean v6 = v0_1 != null ? true : false;
        if(v0_1 == null) {
            v0_1 = arg14.a(v9);
        }

        return new b(arg16, arg18, arg19, arg15, v6, v0_1, arg14, arg17, v10) {
            void a(hx arg2, Object arg3) {
                Object v2 = this.c.read(arg2);
                if(v2 != null || !this.f) {
                    this.a.set(arg3, v2);
                }
            }

            void a(hz arg5, Object arg6) {
                ht v0_1;
                arg6 = this.a.get(arg6);
                if(this.b) {
                    gp v0 = this.c;
                }
                else {
                    v0_1 = new ht(this.d, this.c, this.e.b());
                }

                ((gp)v0_1).write(arg5, arg6);
            }

            public boolean a(Object arg3) {
                boolean v1 = false;
                if(!this.i) {
                    return 0;
                }

                if(this.a.get(arg3) != arg3) {
                    v1 = true;
                }

                return v1;
            }
        };
    }

    private List a(Field arg5) {
        Annotation v0 = arg5.getAnnotation(gt.class);
        if(v0 == null) {
            return Collections.singletonList(this.b.a(arg5));
        }

        String v5 = ((gt)v0).a();
        String[] v0_1 = ((gt)v0).b();
        if(v0_1.length == 0) {
            return Collections.singletonList(v5);
        }

        ArrayList v1 = new ArrayList(v0_1.length + 1);
        ((List)v1).add(v5);
        int v5_1 = v0_1.length;
        int v2;
        for(v2 = 0; v2 < v5_1; ++v2) {
            ((List)v1).add(v0_1[v2]);
        }

        return ((List)v1);
    }

    private Map a(fz arg24, hw arg25, Class arg26) {
        Object v14_1;
        hp v7 = this;
        LinkedHashMap v8 = new LinkedHashMap();
        if(arg26.isInterface()) {
            return ((Map)v8);
        }

        Type v9 = arg25.b();
        hw v11 = arg25;
        Class v10;
        for(v10 = arg26; v10 != Object.class; v10 = v11.a()) {
            Field[] v12 = v10.getDeclaredFields();
            int v13 = v12.length;
            boolean v14 = false;
            int v15 = 0;
            while(v15 < v13) {
                Field v6 = v12[v15];
                boolean v1 = v7.a(v6, true);
                boolean v16 = v7.a(v6, v14);
                if((v1) || (v16)) {
                    v6.setAccessible(true);
                    Type v17 = gx.a(v11.b(), v10, v6.getGenericType());
                    List v5 = v7.a(v6);
                    Object v3 = null;
                    int v4 = 0;
                    while(v4 < v5.size()) {
                        Object v2 = v5.get(v4);
                        boolean v18 = v4 != 0 ? false : v1;
                        v14_1 = v3;
                        int v21 = v4;
                        List v19 = v5;
                        Field v22 = v6;
                        Object v0 = ((Map)v8).put(v2, this.a(arg24, v6, v2, hw.a(v17), v18, v16));
                        v3 = v14_1 == null ? v0 : v14_1;
                        v4 = v21 + 1;
                        v1 = v18;
                        v5 = v19;
                        v6 = v22;
                    }

                    v14_1 = v3;
                    if(v14_1 == null) {
                        goto label_67;
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append(v9);
                    v1_1.append(" declares multiple JSON fields named ");
                    v1_1.append(((b)v14_1).h);
                    throw new IllegalArgumentException(v1_1.toString());
                }
                else {
                }

            label_67:
                ++v15;
                v14 = false;
            }

            v11 = hw.a(gx.a(v11.b(), v10, v10.getGenericSuperclass()));
        }

        return ((Map)v8);
    }

    public boolean a(Field arg2, boolean arg3) {
        return hp.a(arg2, arg3, this.c);
    }

    static boolean a(Field arg1, boolean arg2, gz arg3) {
        boolean v1 = (arg3.a(arg1.getType(), arg2)) || (arg3.a(arg1, arg2)) ? false : true;
        return v1;
    }

    public gp a(fz arg4, hw arg5) {
        Class v0 = arg5.a();
        if(!Object.class.isAssignableFrom(v0)) {
            return null;
        }

        return new a(this.a.a(arg5), this.a(arg4, arg5, v0));
    }
}

