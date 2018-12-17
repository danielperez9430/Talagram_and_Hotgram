package f;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.t;
import okhttp3.x;

public final class m {
    public final class a {
        private final j a;
        @Nullable private okhttp3.e$a b;
        private t c;
        private final List d;
        private final List e;
        @Nullable private Executor f;
        private boolean g;

        public a() {
            this(j.a());
        }

        a(j arg2) {
            super();
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.a = arg2;
        }

        public a a(f.e$a arg3) {
            this.d.add(o.a(arg3, "factory == null"));
            return this;
        }

        public a a(String arg4) {
            o.a(arg4, "baseUrl == null");
            t v0 = t.e(arg4);
            if(v0 != null) {
                return this.a(v0);
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Illegal URL: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        public a a(t arg4) {
            o.a(arg4, "baseUrl == null");
            List v0 = arg4.j();
            if("".equals(v0.get(v0.size() - 1))) {
                this.c = arg4;
                return this;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("baseUrl must end in /: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        public a a(okhttp3.e$a arg2) {
            this.b = o.a(arg2, "factory == null");
            return this;
        }

        public a a(x arg2) {
            return this.a(o.a(arg2, "client == null"));
        }

        public m a() {
            x v0_1;
            if(this.c != null) {
                okhttp3.e$a v0 = this.b;
                if(v0 == null) {
                    v0_1 = new x();
                }

                okhttp3.e$a v2 = ((okhttp3.e$a)v0_1);
                Executor v0_2 = this.f;
                if(v0_2 == null) {
                    v0_2 = this.a.b();
                }

                Executor v6 = v0_2;
                ArrayList v0_3 = new ArrayList(this.e);
                ((List)v0_3).add(this.a.a(v6));
                ArrayList v1 = new ArrayList(this.d.size() + 1);
                ((List)v1).add(new f.a());
                ((List)v1).addAll(this.d);
                return new m(v2, this.c, Collections.unmodifiableList(((List)v1)), Collections.unmodifiableList(((List)v0_3)), v6, this.g);
            }

            throw new IllegalStateException("Base URL required.");
        }
    }

    final okhttp3.e$a a;
    final t b;
    final List c;
    final List d;
    @Nullable final Executor e;
    final boolean f;
    private final Map g;

    m(okhttp3.e$a arg2, t arg3, List arg4, List arg5, @Nullable Executor arg6, boolean arg7) {
        super();
        this.g = new ConcurrentHashMap();
        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
        this.d = arg5;
        this.e = arg6;
        this.f = arg7;
    }

    n a(Method arg4) {
        Object v0 = this.g.get(arg4);
        if(v0 != null) {
            return ((n)v0);
        }

        Map v0_1 = this.g;
        __monitor_enter(v0_1);
        try {
            Object v1 = this.g.get(arg4);
            if(v1 == null) {
                n v1_1 = new f.n$a(this, arg4).a();
                this.g.put(arg4, v1_1);
            }

            __monitor_exit(v0_1);
            return ((n)v1);
        label_17:
            __monitor_exit(v0_1);
        }
        catch(Throwable v4) {
            goto label_17;
        }

        throw v4;
    }

    public c a(@Nullable f.c$a arg5, Type arg6, Annotation[] arg7) {
        o.a(arg6, "returnType == null");
        o.a(arg7, "annotations == null");
        int v0 = this.d.indexOf(arg5) + 1;
        int v1 = this.d.size();
        int v2;
        for(v2 = v0; v2 < v1; ++v2) {
            c v3 = this.d.get(v2).a(arg6, arg7, this);
            if(v3 != null) {
                return v3;
            }
        }

        StringBuilder v7 = new StringBuilder("Could not locate call adapter for ");
        v7.append(arg6);
        v7.append(".\n");
        if(arg5 != null) {
            v7.append("  Skipped:");
            int v5;
            for(v5 = 0; v5 < v0; ++v5) {
                v7.append("\n   * ");
                v7.append(this.d.get(v5).getClass().getName());
            }

            v7.append('\n');
        }

        v7.append("  Tried:");
        v5 = this.d.size();
        while(v0 < v5) {
            v7.append("\n   * ");
            v7.append(this.d.get(v0).getClass().getName());
            ++v0;
        }

        throw new IllegalArgumentException(v7.toString());
    }

    public c a(Type arg2, Annotation[] arg3) {
        return this.a(null, arg2, arg3);
    }

    public e a(@Nullable f.e$a arg5, Type arg6, Annotation[] arg7) {
        o.a(arg6, "type == null");
        o.a(arg7, "annotations == null");
        int v0 = this.c.indexOf(arg5) + 1;
        int v1 = this.c.size();
        int v2;
        for(v2 = v0; v2 < v1; ++v2) {
            e v3 = this.c.get(v2).a(arg6, arg7, this);
            if(v3 != null) {
                return v3;
            }
        }

        StringBuilder v7 = new StringBuilder("Could not locate ResponseBody converter for ");
        v7.append(arg6);
        v7.append(".\n");
        if(arg5 != null) {
            v7.append("  Skipped:");
            int v5;
            for(v5 = 0; v5 < v0; ++v5) {
                v7.append("\n   * ");
                v7.append(this.c.get(v5).getClass().getName());
            }

            v7.append('\n');
        }

        v7.append("  Tried:");
        v5 = this.c.size();
        while(v0 < v5) {
            v7.append("\n   * ");
            v7.append(this.c.get(v0).getClass().getName());
            ++v0;
        }

        throw new IllegalArgumentException(v7.toString());
    }

    public e a(@Nullable f.e$a arg5, Type arg6, Annotation[] arg7, Annotation[] arg8) {
        o.a(arg6, "type == null");
        o.a(arg7, "parameterAnnotations == null");
        o.a(arg8, "methodAnnotations == null");
        int v0 = this.c.indexOf(arg5) + 1;
        int v1 = this.c.size();
        int v2;
        for(v2 = v0; v2 < v1; ++v2) {
            e v3 = this.c.get(v2).a(arg6, arg7, arg8, this);
            if(v3 != null) {
                return v3;
            }
        }

        StringBuilder v7 = new StringBuilder("Could not locate RequestBody converter for ");
        v7.append(arg6);
        v7.append(".\n");
        if(arg5 != null) {
            v7.append("  Skipped:");
            int v5;
            for(v5 = 0; v5 < v0; ++v5) {
                v7.append("\n   * ");
                v7.append(this.c.get(v5).getClass().getName());
            }

            v7.append('\n');
        }

        v7.append("  Tried:");
        v5 = this.c.size();
        while(v0 < v5) {
            v7.append("\n   * ");
            v7.append(this.c.get(v0).getClass().getName());
            ++v0;
        }

        throw new IllegalArgumentException(v7.toString());
    }

    public e a(Type arg2, Annotation[] arg3, Annotation[] arg4) {
        return this.a(null, arg2, arg3, arg4);
    }

    public Object a(Class arg4) {
        o.a(arg4);
        if(this.f) {
            this.b(arg4);
        }

        return Proxy.newProxyInstance(arg4.getClassLoader(), new Class[]{arg4}, new InvocationHandler(arg4) {
            private final j c;

            public Object invoke(Object arg3, Method arg4, @Nullable Object[] arg5) {
                if(arg4.getDeclaringClass() == Object.class) {
                    return arg4.invoke(this, arg5);
                }

                if(this.c.a(arg4)) {
                    return this.c.a(arg4, this.a, arg3, arg5);
                }

                n v3 = this.b.a(arg4);
                return v3.a(new h(v3, arg5));
            }
        });
    }

    public okhttp3.e$a a() {
        return this.a;
    }

    private void b(Class arg6) {
        j v0 = j.a();
        Method[] v6 = arg6.getDeclaredMethods();
        int v1 = v6.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Method v3 = v6[v2];
            if(!v0.a(v3)) {
                this.a(v3);
            }
        }
    }

    public e b(Type arg2, Annotation[] arg3) {
        return this.a(null, arg2, arg3);
    }

    public t b() {
        return this.b;
    }

    public e c(Type arg4, Annotation[] arg5) {
        o.a(arg4, "type == null");
        o.a(arg5, "annotations == null");
        int v0 = this.c.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            e v2 = this.c.get(v1).b(arg4, arg5, this);
            if(v2 != null) {
                return v2;
            }
        }

        return d.a;
    }
}

