package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public final class s {
    public final class a {
        final List a;

        public a() {
            super();
            this.a = new ArrayList(20);
        }

        public a a(String arg1, String arg2) {
            this.d(arg1, arg2);
            return this.b(arg1, arg2);
        }

        public s a() {
            return new s(this);
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
            int v7;
            int v4;
            int v3;
            if(arg10 == null) {
                goto label_69;
            }

            if(arg10.isEmpty()) {
                goto label_65;
            }

            int v0 = arg10.length();
            int v2 = 0;
            while(true) {
                v3 = 2;
                v4 = 3;
                int v5 = 127;
                if(v2 >= v0) {
                    goto label_28;
                }

                v7 = arg10.charAt(v2);
                if(v7 > 32 && v7 < v5) {
                    ++v2;
                    continue;
                }

                break;
            }

            Object[] v0_1 = new Object[v4];
            v0_1[0] = Integer.valueOf(v7);
            v0_1[1] = Integer.valueOf(v2);
            v0_1[v3] = arg10;
            throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in header name: %s", v0_1));
        label_28:
            if(arg11 != null) {
                v0 = arg11.length();
                v2 = 0;
                goto label_31;
            }

            StringBuilder v0_2 = new StringBuilder();
            v0_2.append("value for name ");
            v0_2.append(arg10);
            v0_2.append(" == null");
            throw new NullPointerException(v0_2.toString());
            while(true) {
            label_31:
                if(v2 >= v0) {
                    return;
                }

                v7 = arg11.charAt(v2);
                if((v7 > 31 || v7 == 9) && v7 < v5) {
                    ++v2;
                    continue;
                }

                break;
            }

            Object[] v5_1 = new Object[4];
            v5_1[0] = Integer.valueOf(v7);
            v5_1[1] = Integer.valueOf(v2);
            v5_1[v3] = arg10;
            v5_1[v4] = arg11;
            throw new IllegalArgumentException(c.a("Unexpected char %#04x at %d in %s value: %s", v5_1));
            return;
        label_65:
            throw new IllegalArgumentException("name is empty");
        label_69:
            throw new NullPointerException("name == null");
        }
    }

    private final String[] a;

    s(a arg2) {
        super();
        this.a = arg2.a.toArray(new String[arg2.a.size()]);
    }

    private s(String[] arg1) {
        super();
        this.a = arg1;
    }

    public static s a(String[] arg6) {
        // Method was not decompiled
    }

    @Nullable public String a(String arg2) {
        return s.a(this.a, arg2);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int arg2) {
        return this.a[arg2 * 2];
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

    public a b() {
        a v0 = new a();
        Collections.addAll(v0.a, this.a);
        return v0;
    }

    public String b(int arg2) {
        return this.a[arg2 * 2 + 1];
    }

    public List b(String arg5) {
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

    public boolean equals(@Nullable Object arg2) {
        boolean v2 = !(arg2 instanceof s) || !Arrays.equals(((s)arg2).a, this.a) ? false : true;
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

