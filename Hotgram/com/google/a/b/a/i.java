package com.google.a.b.a;

import com.google.a.b.d;
import com.google.a.b.h;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.e;
import com.google.a.f;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class i implements w {
    public final class a extends v {
        private final h a;
        private final Map b;

        a(h arg1, Map arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public Object read(com.google.a.d.a arg4) {
            if(arg4.f() == b.i) {
                arg4.j();
                return null;
            }

            Object v0 = this.a.a();
            try {
                arg4.c();
                while(arg4.e()) {
                    Object v1 = this.b.get(arg4.g());
                    if(v1 != null) {
                        if(!((com.google.a.b.a.i$b)v1).j) {
                        }
                        else {
                            ((com.google.a.b.a.i$b)v1).a(arg4, v0);
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
            throw new t(((Throwable)v4_1));
        label_27:
            throw new AssertionError(v4);
        }

        public void write(c arg4, Object arg5) {
            if(arg5 == null) {
                arg4.f();
                return;
            }

            arg4.d();
            try {
                Iterator v0 = this.b.values().iterator();
                while(v0.hasNext()) {
                    Object v1 = v0.next();
                    if(!((com.google.a.b.a.i$b)v1).a(arg5)) {
                        continue;
                    }

                    arg4.a(((com.google.a.b.a.i$b)v1).h);
                    ((com.google.a.b.a.i$b)v1).a(arg4, arg5);
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

    abstract class com.google.a.b.a.i$b {
        final String h;
        final boolean i;
        final boolean j;

        protected com.google.a.b.a.i$b(String arg1, boolean arg2, boolean arg3) {
            super();
            this.h = arg1;
            this.i = arg2;
            this.j = arg3;
        }

        abstract void a(com.google.a.d.a arg1, Object arg2);

        abstract void a(c arg1, Object arg2);

        abstract boolean a(Object arg1);
    }

    private final com.google.a.b.c a;
    private final e b;
    private final d c;
    private final com.google.a.b.a.d d;

    public i(com.google.a.b.c arg1, e arg2, d arg3, com.google.a.b.a.d arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    private com.google.a.b.a.i$b a(f arg14, Field arg15, String arg16, com.google.a.c.a arg17, boolean arg18, boolean arg19) {
        i v11 = this;
        com.google.a.c.a v9 = arg17;
        boolean v10 = com.google.a.b.i.a(arg17.a());
        Annotation v0 = arg15.getAnnotation(com.google.a.a.b.class);
        v v0_1 = v0 != null ? v11.d.a(v11.a, arg14, v9, ((com.google.a.a.b)v0)) : null;
        boolean v6 = v0_1 != null ? true : false;
        if(v0_1 == null) {
            v0_1 = arg14.a(v9);
        }

        return new com.google.a.b.a.i$b(arg16, arg18, arg19, arg15, v6, v0_1, arg14, arg17, v10) {
            void a(com.google.a.d.a arg2, Object arg3) {
                Object v2 = this.c.read(arg2);
                if(v2 != null || !this.f) {
                    this.a.set(arg3, v2);
                }
            }

            void a(c arg5, Object arg6) {
                m v0_1;
                arg6 = this.a.get(arg6);
                if(this.b) {
                    v v0 = this.c;
                }
                else {
                    v0_1 = new m(this.d, this.c, this.e.b());
                }

                ((v)v0_1).write(arg5, arg6);
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
        Annotation v0 = arg5.getAnnotation(com.google.a.a.c.class);
        if(v0 == null) {
            return Collections.singletonList(this.b.a(arg5));
        }

        String v5 = ((com.google.a.a.c)v0).a();
        String[] v0_1 = ((com.google.a.a.c)v0).b();
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

    private Map a(f arg25, com.google.a.c.a arg26, Class arg27) {
        Object v14_1;
        i v7 = this;
        LinkedHashMap v8 = new LinkedHashMap();
        if(arg27.isInterface()) {
            return ((Map)v8);
        }

        Type v9 = arg26.b();
        com.google.a.c.a v11 = arg26;
        Class v10;
        for(v10 = arg27; v10 != Object.class; v10 = v11.a()) {
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
                    Type v17 = com.google.a.b.b.a(v11.b(), v10, v6.getGenericType());
                    List v5 = v7.a(v6);
                    int v4 = v5.size();
                    Object v2 = null;
                    int v3 = 0;
                    while(v3 < v4) {
                        Object v0 = v5.get(v3);
                        boolean v18 = v3 != 0 ? false : v1;
                        v14_1 = v2;
                        int v21 = v3;
                        int v22 = v4;
                        List v19 = v5;
                        Field v23 = v6;
                        v0 = ((Map)v8).put(v0, this.a(arg25, v6, v0, com.google.a.c.a.a(v17), v18, v16));
                        v2 = v14_1 == null ? v0 : v14_1;
                        v3 = v21 + 1;
                        v1 = v18;
                        v5 = v19;
                        v4 = v22;
                        v6 = v23;
                    }

                    v14_1 = v2;
                    if(v14_1 == null) {
                        goto label_69;
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append(v9);
                    v1_1.append(" declares multiple JSON fields named ");
                    v1_1.append(((com.google.a.b.a.i$b)v14_1).h);
                    throw new IllegalArgumentException(v1_1.toString());
                }
                else {
                }

            label_69:
                ++v15;
                v14 = false;
            }

            v11 = com.google.a.c.a.a(com.google.a.b.b.a(v11.b(), v10, v10.getGenericSuperclass()));
        }

        return ((Map)v8);
    }

    public boolean a(Field arg2, boolean arg3) {
        return i.a(arg2, arg3, this.c);
    }

    static boolean a(Field arg1, boolean arg2, d arg3) {
        boolean v1 = (arg3.a(arg1.getType(), arg2)) || (arg3.a(arg1, arg2)) ? false : true;
        return v1;
    }

    public v create(f arg4, com.google.a.c.a arg5) {
        Class v0 = arg5.a();
        if(!Object.class.isAssignableFrom(v0)) {
            return null;
        }

        return new a(this.a.a(arg5), this.a(arg4, arg5, v0));
    }
}

