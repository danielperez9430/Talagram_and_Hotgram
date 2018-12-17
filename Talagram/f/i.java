package f;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.ab;
import okhttp3.s;

abstract class i {
    final class a extends i {
        private final e a;

        a(e arg1) {
            super();
            this.a = arg1;
        }

        void a(k arg4, @Nullable Object arg5) {
            if(arg5 != null) {
                try {
                    arg4.a(this.a.b(arg5));
                    return;
                }
                catch(IOException v4) {
                    StringBuilder v1 = new StringBuilder();
                    v1.append("Unable to convert ");
                    v1.append(arg5);
                    v1.append(" to RequestBody");
                    throw new RuntimeException(v1.toString(), ((Throwable)v4));
                }
            }

            throw new IllegalArgumentException("Body parameter value must not be null.");
        }
    }

    final class b extends i {
        private final String a;
        private final e b;
        private final boolean c;

        b(String arg2, e arg3, boolean arg4) {
            super();
            this.a = o.a(arg2, "name == null");
            this.b = arg3;
            this.c = arg4;
        }

        void a(k arg3, @Nullable Object arg4) {
            if(arg4 == null) {
                return;
            }

            arg4 = this.b.b(arg4);
            if(arg4 == null) {
                return;
            }

            arg3.c(this.a, ((String)arg4), this.c);
        }
    }

    final class c extends i {
        private final e a;
        private final boolean b;

        c(e arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        void a(k arg1, @Nullable Object arg2) {
            this.a(arg1, ((Map)arg2));
        }

        void a(k arg4, @Nullable Map arg5) {
            Object v1;
            Object v0;
            if(arg5 == null) {
                goto label_52;
            }

            Iterator v5 = arg5.entrySet().iterator();
            while(true) {
                if(!v5.hasNext()) {
                    return;
                }

                v0 = v5.next();
                v1 = ((Map$Entry)v0).getKey();
                if(v1 == null) {
                    goto label_47;
                }

                v0 = ((Map$Entry)v0).getValue();
                if(v0 == null) {
                    goto label_36;
                }

                Object v2 = this.a.b(v0);
                if(v2 == null) {
                    break;
                }

                arg4.c(((String)v1), ((String)v2), this.b);
            }

            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("Field map value \'");
            v5_1.append(v0);
            v5_1.append("\' converted to null by ");
            v5_1.append(this.a.getClass().getName());
            v5_1.append(" for key \'");
            v5_1.append(((String)v1));
            v5_1.append("\'.");
            throw new IllegalArgumentException(v5_1.toString());
        label_36:
            v5_1 = new StringBuilder();
            v5_1.append("Field map contained null value for key \'");
            v5_1.append(((String)v1));
            v5_1.append("\'.");
            throw new IllegalArgumentException(v5_1.toString());
        label_47:
            throw new IllegalArgumentException("Field map contained null key.");
            return;
        label_52:
            throw new IllegalArgumentException("Field map was null.");
        }
    }

    final class d extends i {
        private final String a;
        private final e b;

        d(String arg2, e arg3) {
            super();
            this.a = o.a(arg2, "name == null");
            this.b = arg3;
        }

        void a(k arg2, @Nullable Object arg3) {
            if(arg3 == null) {
                return;
            }

            arg3 = this.b.b(arg3);
            if(arg3 == null) {
                return;
            }

            arg2.a(this.a, ((String)arg3));
        }
    }

    final class f.i$e extends i {
        private final e a;

        f.i$e(e arg1) {
            super();
            this.a = arg1;
        }

        void a(k arg1, @Nullable Object arg2) {
            this.a(arg1, ((Map)arg2));
        }

        void a(k arg4, @Nullable Map arg5) {
            Object v1;
            if(arg5 == null) {
                goto label_30;
            }

            Iterator v5 = arg5.entrySet().iterator();
            while(true) {
                if(!v5.hasNext()) {
                    return;
                }

                Object v0 = v5.next();
                v1 = ((Map$Entry)v0).getKey();
                if(v1 == null) {
                    goto label_25;
                }

                v0 = ((Map$Entry)v0).getValue();
                if(v0 == null) {
                    break;
                }

                arg4.a(((String)v1), this.a.b(v0));
            }

            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("Header map contained null value for key \'");
            v5_1.append(((String)v1));
            v5_1.append("\'.");
            throw new IllegalArgumentException(v5_1.toString());
        label_25:
            throw new IllegalArgumentException("Header map contained null key.");
            return;
        label_30:
            throw new IllegalArgumentException("Header map was null.");
        }
    }

    final class f extends i {
        private final s a;
        private final e b;

        f(s arg1, e arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        void a(k arg4, @Nullable Object arg5) {
            s v5;
            Object v0;
            if(arg5 == null) {
                return;
            }

            try {
                v0 = this.b.b(arg5);
                v5 = this.a;
            }
            catch(IOException v4) {
                StringBuilder v1 = new StringBuilder();
                v1.append("Unable to convert ");
                v1.append(v5);
                v1.append(" to RequestBody");
                throw new RuntimeException(v1.toString(), ((Throwable)v4));
            }

            arg4.a(v5, ((ab)v0));
        }
    }

    final class g extends i {
        private final e a;
        private final String b;

        g(e arg1, String arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        void a(k arg1, @Nullable Object arg2) {
            this.a(arg1, ((Map)arg2));
        }

        void a(k arg7, @Nullable Map arg8) {
            Object v1;
            if(arg8 == null) {
                goto label_52;
            }

            Iterator v8 = arg8.entrySet().iterator();
            while(true) {
                if(!v8.hasNext()) {
                    return;
                }

                Object v0 = v8.next();
                v1 = ((Map$Entry)v0).getKey();
                if(v1 == null) {
                    goto label_47;
                }

                v0 = ((Map$Entry)v0).getValue();
                if(v0 == null) {
                    break;
                }

                String[] v2 = new String[4];
                v2[0] = "Content-Disposition";
                v2[1] = "form-data; name=\"" + (((String)v1)) + "\"";
                v2[2] = "Content-Transfer-Encoding";
                v2[3] = this.b;
                arg7.a(s.a(v2), this.a.b(v0));
            }

            StringBuilder v8_1 = new StringBuilder();
            v8_1.append("Part map contained null value for key \'");
            v8_1.append(((String)v1));
            v8_1.append("\'.");
            throw new IllegalArgumentException(v8_1.toString());
        label_47:
            throw new IllegalArgumentException("Part map contained null key.");
            return;
        label_52:
            throw new IllegalArgumentException("Part map was null.");
        }
    }

    final class h extends i {
        private final String a;
        private final e b;
        private final boolean c;

        h(String arg2, e arg3, boolean arg4) {
            super();
            this.a = o.a(arg2, "name == null");
            this.b = arg3;
            this.c = arg4;
        }

        void a(k arg3, @Nullable Object arg4) {
            if(arg4 != null) {
                arg3.a(this.a, this.b.b(arg4), this.c);
                return;
            }

            StringBuilder v4 = new StringBuilder();
            v4.append("Path parameter \"");
            v4.append(this.a);
            v4.append("\" value must not be null.");
            throw new IllegalArgumentException(v4.toString());
        }
    }

    final class f.i$i extends i {
        private final String a;
        private final e b;
        private final boolean c;

        f.i$i(String arg2, e arg3, boolean arg4) {
            super();
            this.a = o.a(arg2, "name == null");
            this.b = arg3;
            this.c = arg4;
        }

        void a(k arg3, @Nullable Object arg4) {
            if(arg4 == null) {
                return;
            }

            arg4 = this.b.b(arg4);
            if(arg4 == null) {
                return;
            }

            arg3.b(this.a, ((String)arg4), this.c);
        }
    }

    final class j extends i {
        private final e a;
        private final boolean b;

        j(e arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        void a(k arg1, @Nullable Object arg2) {
            this.a(arg1, ((Map)arg2));
        }

        void a(k arg4, @Nullable Map arg5) {
            Object v1;
            Object v0;
            if(arg5 == null) {
                goto label_52;
            }

            Iterator v5 = arg5.entrySet().iterator();
            while(true) {
                if(!v5.hasNext()) {
                    return;
                }

                v0 = v5.next();
                v1 = ((Map$Entry)v0).getKey();
                if(v1 == null) {
                    goto label_47;
                }

                v0 = ((Map$Entry)v0).getValue();
                if(v0 == null) {
                    goto label_36;
                }

                Object v2 = this.a.b(v0);
                if(v2 == null) {
                    break;
                }

                arg4.b(((String)v1), ((String)v2), this.b);
            }

            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("Query map value \'");
            v5_1.append(v0);
            v5_1.append("\' converted to null by ");
            v5_1.append(this.a.getClass().getName());
            v5_1.append(" for key \'");
            v5_1.append(((String)v1));
            v5_1.append("\'.");
            throw new IllegalArgumentException(v5_1.toString());
        label_36:
            v5_1 = new StringBuilder();
            v5_1.append("Query map contained null value for key \'");
            v5_1.append(((String)v1));
            v5_1.append("\'.");
            throw new IllegalArgumentException(v5_1.toString());
        label_47:
            throw new IllegalArgumentException("Query map contained null key.");
            return;
        label_52:
            throw new IllegalArgumentException("Query map was null.");
        }
    }

    final class f.i$k extends i {
        private final e a;
        private final boolean b;

        f.i$k(e arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        void a(k arg3, @Nullable Object arg4) {
            if(arg4 == null) {
                return;
            }

            arg3.b(this.a.b(arg4), null, this.b);
        }
    }

    final class l extends i {
        static final l a;

        static {
            l.a = new l();
        }

        private l() {
            super();
        }

        void a(k arg1, @Nullable Object arg2) {
            this.a(arg1, ((okhttp3.w$b)arg2));
        }

        void a(k arg1, @Nullable okhttp3.w$b arg2) {
            if(arg2 != null) {
                arg1.a(arg2);
            }
        }
    }

    final class m extends i {
        m() {
            super();
        }

        void a(k arg2, @Nullable Object arg3) {
            o.a(arg3, "@Url parameter is null.");
            arg2.a(arg3);
        }
    }

    i() {
        super();
    }

    final i a() {
        return new i() {
            void a(k arg3, @Nullable Iterable arg4) {
                if(arg4 == null) {
                    return;
                }

                Iterator v4 = arg4.iterator();
                while(v4.hasNext()) {
                    this.a.a(arg3, v4.next());
                }
            }

            void a(k arg1, @Nullable Object arg2) {
                this.a(arg1, ((Iterable)arg2));
            }
        };
    }

    abstract void a(k arg1, @Nullable Object arg2);

    final i b() {
        return new i() {
            void a(k arg5, @Nullable Object arg6) {
                if(arg6 == null) {
                    return;
                }

                int v0 = 0;
                int v1 = Array.getLength(arg6);
                while(v0 < v1) {
                    this.a.a(arg5, Array.get(arg6, v0));
                    ++v0;
                }
            }
        };
    }
}

