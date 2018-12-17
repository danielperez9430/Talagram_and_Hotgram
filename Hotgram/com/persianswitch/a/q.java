package com.persianswitch.a;

import com.persianswitch.a.a.b.g;
import com.persianswitch.a.a.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public final class q {
    class com.persianswitch.a.q$1 {
    }

    public final class a {
        private final List a;

        public a() {
            super();
            this.a = new ArrayList(20);
        }

        public q a() {
            return new q(this, null);
        }

        static List a(a arg0) {
            return arg0.a;
        }

        a a(String arg4) {
            int v0 = arg4.indexOf(":", 1);
            if(v0 != -1) {
                return this.b(arg4.substring(0, v0), arg4.substring(v0 + 1));
            }

            if(arg4.startsWith(":")) {
                return this.b("", arg4.substring(1));
            }

            return this.b("", arg4);
        }

        public a a(String arg1, String arg2) {
            this.d(arg1, arg2);
            return this.b(arg1, arg2);
        }

        public a b(String arg3) {
            int v0;
            for(v0 = 0; v0 < this.a.size(); v0 += 2) {
                if(arg3.equalsIgnoreCase(this.a.get(v0))) {
                    this.a.remove(v0);
                    this.a.remove(v0);
                    v0 += -2;
                }
            }

            return this;
        }

        a b(String arg2, String arg3) {
            this.a.add(arg2);
            this.a.add(arg3.trim());
            return this;
        }

        public a c(String arg1, String arg2) {
            this.d(arg1, arg2);
            this.b(arg1);
            this.b(arg1, arg2);
            return this;
        }

        private void d(String arg10, String arg11) {
            int v8;
            int v4;
            int v3;
            if(arg10 == null) {
                goto label_59;
            }

            if(arg10.isEmpty()) {
                goto label_55;
            }

            int v0 = arg10.length();
            int v2 = 0;
            while(true) {
                v3 = 2;
                v4 = 3;
                int v5 = 127;
                int v6 = 31;
                if(v2 >= v0) {
                    goto label_28;
                }

                v8 = arg10.charAt(v2);
                if(v8 > v6 && v8 < v5) {
                    ++v2;
                    continue;
                }

                break;
            }

            Object[] v0_1 = new Object[v4];
            v0_1[0] = Integer.valueOf(v8);
            v0_1[1] = Integer.valueOf(v2);
            v0_1[v3] = arg10;
            throw new IllegalArgumentException(l.a("Unexpected char %#04x at %d in header name: %s", v0_1));
        label_28:
            if(arg11 != null) {
                v0 = arg11.length();
                v2 = 0;
                goto label_31;
            }

            throw new NullPointerException("value == null");
            while(true) {
            label_31:
                if(v2 >= v0) {
                    return;
                }

                v8 = arg11.charAt(v2);
                if(v8 > v6 && v8 < v5) {
                    ++v2;
                    continue;
                }

                break;
            }

            Object[] v5_1 = new Object[4];
            v5_1[0] = Integer.valueOf(v8);
            v5_1[1] = Integer.valueOf(v2);
            v5_1[v3] = arg10;
            v5_1[v4] = arg11;
            throw new IllegalArgumentException(l.a("Unexpected char %#04x at %d in %s value: %s", v5_1));
            return;
        label_55:
            throw new IllegalArgumentException("name is empty");
        label_59:
            throw new NullPointerException("name == null");
        }
    }

    private final String[] a;

    private q(a arg2) {
        super();
        this.a = a.a(arg2).toArray(new String[a.a(arg2).size()]);
    }

    q(a arg1, com.persianswitch.a.q$1 arg2) {
        this(arg1);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int arg2) {
        return this.a[arg2 * 2];
    }

    public String a(String arg2) {
        return q.a(this.a, arg2);
    }

    private static String a(String[] arg2, String arg3) {
        int v0;
        for(v0 = arg2.length - 2; v0 >= 0; v0 += -2) {
            if(arg3.equalsIgnoreCase(arg2[v0])) {
                return arg2[v0 + 1];
            }
        }

        return null;
    }

    public String b(int arg2) {
        return this.a[arg2 * 2 + 1];
    }

    public Date b(String arg1) {
        arg1 = this.a(arg1);
        Date v1 = arg1 != null ? g.a(arg1) : null;
        return v1;
    }

    public a b() {
        a v0 = new a();
        Collections.addAll(a.a(v0), this.a);
        return v0;
    }

    public List c(String arg5) {
        int v0 = this.a();
        ArrayList v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg5.equalsIgnoreCase(this.a(v2))) {
                if(v1 == null) {
                    v1 = new ArrayList(2);
                }

                ((List)v1).add(this.b(v2));
            }
        }

        List v5 = v1 != null ? Collections.unmodifiableList(((List)v1)) : Collections.emptyList();
        return v5;
    }

    public boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof q) || !Arrays.equals(((q)arg2).a, this.a) ? false : true;
        return v2;
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        int v1 = this.a();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0.append(this.a(v2));
            v0.append(": ");
            v0.append(this.b(v2));
            v0.append("\n");
        }

        return v0.toString();
    }
}

