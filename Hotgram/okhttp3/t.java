package okhttp3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.c;

public final class t {
    public final class a {
        enum okhttp3.t$a$a {
            public static final enum okhttp3.t$a$a a;
            public static final enum okhttp3.t$a$a b;
            public static final enum okhttp3.t$a$a c;
            public static final enum okhttp3.t$a$a d;
            public static final enum okhttp3.t$a$a e;

            static {
                okhttp3.t$a$a.a = new okhttp3.t$a$a("SUCCESS", 0);
                okhttp3.t$a$a.b = new okhttp3.t$a$a("MISSING_SCHEME", 1);
                okhttp3.t$a$a.c = new okhttp3.t$a$a("UNSUPPORTED_SCHEME", 2);
                okhttp3.t$a$a.d = new okhttp3.t$a$a("INVALID_PORT", 3);
                okhttp3.t$a$a.e = new okhttp3.t$a$a("INVALID_HOST", 4);
                okhttp3.t$a$a.f = new okhttp3.t$a$a[]{okhttp3.t$a$a.a, okhttp3.t$a$a.b, okhttp3.t$a$a.c, okhttp3.t$a$a.d, okhttp3.t$a$a.e};
            }

            private okhttp3.t$a$a(String arg1, int arg2) {
                super(arg1, arg2);
            }

            public static okhttp3.t$a$a valueOf(String arg1) {
                return Enum.valueOf(okhttp3.t$a$a.class, arg1);
            }

            public static okhttp3.t$a$a[] values() {
                // Method was not decompiled
            }
        }

        @Nullable String a;
        String b;
        String c;
        @Nullable String d;
        int e;
        final List f;
        @Nullable List g;
        @Nullable String h;

        public a() {
            super();
            this.b = "";
            this.c = "";
            this.e = -1;
            this.f = new ArrayList();
            this.f.add("");
        }

        public a a(String arg8, @Nullable String arg9) {
            if(arg8 != null) {
                if(this.g == null) {
                    this.g = new ArrayList();
                }

                this.g.add(t.a(arg8, " !\"#$&\'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
                List v8 = this.g;
                if(arg9 != null) {
                    arg9 = t.a(arg9, " !\"#$&\'(),/:;<=>?@[]\\^`{|}~", false, false, true, true);
                }
                else {
                    Object v9 = null;
                }

                v8.add(arg9);
                return this;
            }

            throw new NullPointerException("name == null");
        }

        public a a(int arg4) {
            if(arg4 > 0 && arg4 <= 65535) {
                this.e = arg4;
                return this;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("unexpected port: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        public a a(String arg4) {
            if(arg4 == null) {
                goto label_22;
            }

            if(arg4.equalsIgnoreCase("http")) {
                arg4 = "http";
            }
            else if(arg4.equalsIgnoreCase("https")) {
                arg4 = "https";
            }
            else {
                goto label_13;
            }

            this.a = arg4;
            return this;
        label_13:
            StringBuilder v1 = new StringBuilder();
            v1.append("unexpected scheme: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        label_22:
            throw new NullPointerException("scheme == null");
        }

        int a() {
            int v0 = this.e != -1 ? this.e : t.a(this.a);
            return v0;
        }

        okhttp3.t$a$a a(@Nullable t arg20, String arg21) {
            int v1_1;
            String v2;
            a v0 = this;
            t v1 = arg20;
            String v10 = arg21;
            int v9 = c.a(v10, 0, arg21.length());
            int v11 = c.b(v10, v9, arg21.length());
            int v12 = -1;
            if(a.b(v10, v9, v11) != v12) {
                if(arg21.regionMatches(true, v9, "https:", 0, 6)) {
                    v0.a = "https";
                    v2 = "https:";
                }
                else if(arg21.regionMatches(true, v9, "http:", 0, 5)) {
                    v0.a = "http";
                    v2 = "http:";
                }
                else {
                    goto label_37;
                }

                v9 += v2.length();
                goto label_42;
            label_37:
                return okhttp3.t$a$a.c;
            }
            else {
                if(v1 == null) {
                    goto label_214;
                }

                v0.a = v1.a;
            }

        label_42:
            int v2_1 = a.c(v10, v9, v11);
            int v13 = 35;
            if(v2_1 >= 2 || v1 == null || !v1.a.equals(v0.a)) {
                v2_1 = v9 + v2_1;
                int v15 = 0;
                int v16 = 0;
                while(true) {
                    v9 = c.a(v10, v2_1, v11, "@/\\?#");
                    v1_1 = v9 != v11 ? v10.charAt(v9) : -1;
                    if(v1_1 != v12 && v1_1 != v13 && v1_1 != 47 && v1_1 != 92) {
                        switch(v1_1) {
                            case 63: {
                                goto label_153;
                            }
                            case 64: {
                                goto label_90;
                            }
                        }

                        goto label_151;
                    label_90:
                        if(v15 == 0) {
                            int v8 = c.a(v10, v2_1, v9, ':');
                            int v14 = v8;
                            v13 = v9;
                            String v1_2 = t.a(arg21, v2_1, v8, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if(v16 != 0) {
                                v1_2 = v0.b + "%40" + v1_2;
                            }

                            v0.b = v1_2;
                            if(v14 != v13) {
                                v0.c = t.a(arg21, v14 + 1, v13, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                v15 = 1;
                            }

                            v16 = 1;
                        }
                        else {
                            v13 = v9;
                            v0.c = v0.c + "%40" + t.a(arg21, v2_1, v13, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        }

                        v2_1 = v13 + 1;
                    label_151:
                        v13 = 35;
                        continue;
                    }

                    break;
                }

            label_153:
                v13 = v9;
                v1_1 = a.d(v10, v2_1, v13);
                int v3 = v1_1 + 1;
                if(v3 < v13) {
                    v0.d = a.e(v10, v2_1, v1_1);
                    v0.e = a.f(v10, v3, v13);
                    if(v0.e == v12) {
                        return okhttp3.t$a$a.d;
                    }
                }
                else {
                    v0.d = a.e(v10, v2_1, v1_1);
                    v0.e = t.a(v0.a);
                }

                if(v0.d != null) {
                    goto label_174;
                }

                return okhttp3.t$a$a.e;
            }
            else {
                v0.b = arg20.d();
                v0.c = arg20.e();
                v0.d = v1.b;
                v0.e = v1.c;
                v0.f.clear();
                v0.f.addAll(arg20.i());
                if(v9 == v11 || v10.charAt(v9) == v13) {
                    v0.e(arg20.k());
                }

                v13 = v9;
            }

        label_174:
            v1_1 = c.a(v10, v13, v11, "?#");
            v0.a(v10, v13, v1_1);
            if(v1_1 < v11 && v10.charAt(v1_1) == 63) {
                v12 = c.a(v10, v1_1, v11, '#');
                v0.g = t.b(t.a(arg21, v1_1 + 1, v12, " \"\'<>#", true, false, true, true, null));
                v1_1 = v12;
            }

            if(v1_1 < v11 && v10.charAt(v1_1) == 35) {
                v0.h = t.a(arg21, 1 + v1_1, v11, "", true, false, false, false, null);
            }

            return okhttp3.t$a$a.a;
        label_214:
            return okhttp3.t$a$a.b;
        }

        private void a(String arg10, int arg11, int arg12) {
            if(arg11 == arg12) {
                return;
            }

            int v0 = arg10.charAt(arg11);
            if(v0 == 47 || v0 == 92) {
                this.f.clear();
                this.f.add("");
                goto label_37;
                do {
                label_22:
                    int v5 = arg11;
                    if(v5 < arg12) {
                        arg11 = c.a(arg10, v5, arg12, "/\\");
                        boolean v0_1 = arg11 < arg12 ? true : false;
                        this.a(arg10, v5, arg11, v0_1, true);
                        if(!v0_1) {
                            continue;
                        }
                    }
                    else {
                        return;
                    }

                    goto label_37;
                }
                while(true);

                return;
            }
            else {
                this.f.set(this.f.size() - 1, "");
                goto label_22;
            }

        label_37:
            ++arg11;
            goto label_22;
        }

        private void a(String arg10, int arg11, int arg12, boolean arg13, boolean arg14) {
            arg10 = t.a(arg10, arg11, arg12, " \"<>^`{}|/\\?#", arg14, false, false, true, null);
            if(this.f(arg10)) {
                return;
            }

            if(this.g(arg10)) {
                this.d();
                return;
            }

            if(this.f.get(this.f.size() - 1).isEmpty()) {
                this.f.set(this.f.size() - 1, arg10);
            }
            else {
                this.f.add(arg10);
            }

            if(arg13) {
                this.f.add("");
            }
        }

        public a b(String arg8, @Nullable String arg9) {
            if(arg8 != null) {
                if(this.g == null) {
                    this.g = new ArrayList();
                }

                this.g.add(t.a(arg8, " \"\'<>#&=", true, false, true, true));
                List v8 = this.g;
                if(arg9 != null) {
                    arg9 = t.a(arg9, " \"\'<>#&=", true, false, true, true);
                }
                else {
                    Object v9 = null;
                }

                v8.add(arg9);
                return this;
            }

            throw new NullPointerException("encodedName == null");
        }

        a b() {
            int v0 = this.f.size();
            int v1 = 0;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                this.f.set(v2, t.a(this.f.get(v2), "[]", true, true, false, true));
            }

            if(this.g != null) {
                v0 = this.g.size();
                while(v1 < v0) {
                    Object v3 = this.g.get(v1);
                    if(v3 != null) {
                        this.g.set(v1, t.a(((String)v3), "\\^`{|}", true, true, true, true));
                    }

                    ++v1;
                }
            }

            if(this.h != null) {
                this.h = t.a(this.h, " \"#<>\\^`{|}", true, true, false, false);
            }

            return this;
        }

        public a b(String arg7) {
            if(arg7 != null) {
                this.b = t.a(arg7, " \"\':;<=>@[]^`{}|/\\?#", false, false, false, true);
                return this;
            }

            throw new NullPointerException("username == null");
        }

        private static int b(String arg7, int arg8, int arg9) {
            int v1 = -1;
            if(arg9 - arg8 < 2) {
                return v1;
            }

            int v0 = arg7.charAt(arg8);
            int v2 = 90;
            int v3 = 65;
            int v4 = 122;
            int v5 = 97;
            if(v0 >= v5 && v0 <= v4) {
                goto label_15;
            }
            else if(v0 >= v3) {
                if(v0 > v2) {
                }
                else {
                    while(true) {
                    label_15:
                        ++arg8;
                        if(arg8 < arg9) {
                            v0 = arg7.charAt(arg8);
                            if(v0 >= v5 && v0 <= v4) {
                                continue;
                            }

                            if(v0 >= v3 && v0 <= v2) {
                                continue;
                            }

                            if(v0 >= 48 && v0 <= 57) {
                                continue;
                            }

                            if(v0 == 43) {
                                continue;
                            }

                            if(v0 == 45) {
                                continue;
                            }

                            if(v0 != 46) {
                                break;
                            }

                            continue;
                        }

                        return v1;
                    }

                    if(v0 != 58) {
                        return v1;
                    }

                    return arg8;
                }
            }

            return v1;
        }

        public t c() {
            if(this.a != null) {
                if(this.d != null) {
                    return new t(this);
                }

                throw new IllegalStateException("host == null");
            }

            throw new IllegalStateException("scheme == null");
        }

        public a c(String arg7) {
            if(arg7 != null) {
                this.c = t.a(arg7, " \"\':;<=>@[]^`{}|/\\?#", false, false, false, true);
                return this;
            }

            throw new NullPointerException("password == null");
        }

        private static int c(String arg3, int arg4, int arg5) {
            int v0 = 0;
            while(arg4 < arg5) {
                int v1 = arg3.charAt(arg4);
                if(v1 != 92 && v1 != 47) {
                    return v0;
                }

                ++v0;
                ++arg4;
            }

            return v0;
        }

        public a d(String arg4) {
            if(arg4 != null) {
                String v0 = a.e(arg4, 0, arg4.length());
                if(v0 != null) {
                    this.d = v0;
                    return this;
                }

                StringBuilder v1 = new StringBuilder();
                v1.append("unexpected host: ");
                v1.append(arg4);
                throw new IllegalArgumentException(v1.toString());
            }

            throw new NullPointerException("host == null");
        }

        private void d() {
            if(!this.f.remove(this.f.size() - 1).isEmpty() || (this.f.isEmpty())) {
                this.f.add("");
            }
            else {
                this.f.set(this.f.size() - 1, "");
            }
        }

        private static int d(String arg2, int arg3, int arg4) {
            while(true) {
                if(arg3 >= arg4) {
                    return arg4;
                }

                int v0 = arg2.charAt(arg3);
                if(v0 == 58) {
                    return arg3;
                }

                if(v0 != 91) {
                }
                else {
                    do {
                        ++arg3;
                        if(arg3 < arg4 && arg2.charAt(arg3) != 93) {
                            continue;
                        }

                        break;
                    }
                    while(true);
                }

                ++arg3;
            }

            return arg3;
        }

        public a e(@Nullable String arg7) {
            List v7 = arg7 != null ? t.b(t.a(arg7, " \"\'<>#", true, false, true, true)) : null;
            this.g = v7;
            return this;
        }

        private static String e(String arg1, int arg2, int arg3) {
            return c.a(t.a(arg1, arg2, arg3, false));
        }

        private boolean f(String arg2) {
            boolean v2 = (arg2.equals(".")) || (arg2.equalsIgnoreCase("%2e")) ? true : false;
            return v2;
        }

        private static int f(String arg10, int arg11, int arg12) {
            int v10;
            int v0 = -1;
            try {
                v10 = Integer.parseInt(t.a(arg10, arg11, arg12, "", false, false, false, true, null));
                if(v10 > 0) {
                    goto label_13;
                }
            }
            catch(NumberFormatException ) {
            }

            return v0;
        label_13:
            if(v10 <= 65535) {
                return v10;
            }

            return v0;
        }

        private boolean g(String arg2) {
            boolean v2 = (arg2.equals("..")) || (arg2.equalsIgnoreCase("%2e.")) || (arg2.equalsIgnoreCase(".%2e")) || (arg2.equalsIgnoreCase("%2e%2e")) ? true : false;
            return v2;
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder();
            v0.append(this.a);
            v0.append("://");
            char v2 = ':';
            if(!this.b.isEmpty() || !this.c.isEmpty()) {
                v0.append(this.b);
                if(!this.c.isEmpty()) {
                    v0.append(v2);
                    v0.append(this.c);
                }

                v0.append('@');
            }

            if(this.d.indexOf(v2) != -1) {
                v0.append('[');
                v0.append(this.d);
                v0.append(']');
            }
            else {
                v0.append(this.d);
            }

            int v1 = this.a();
            if(v1 != t.a(this.a)) {
                v0.append(v2);
                v0.append(v1);
            }

            t.a(v0, this.f);
            if(this.g != null) {
                v0.append('?');
                t.b(v0, this.g);
            }

            if(this.h != null) {
                v0.append('#');
                v0.append(this.h);
            }

            return v0.toString();
        }
    }

    final String a;
    final String b;
    final int c;
    private static final char[] d;
    private final String e;
    private final String f;
    private final List g;
    @Nullable private final List h;
    @Nullable private final String i;
    private final String j;

    static {
        t.d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    t(a arg5) {
        super();
        this.a = arg5.a;
        this.e = t.a(arg5.b, false);
        this.f = t.a(arg5.c, false);
        this.b = arg5.d;
        this.c = arg5.a();
        this.g = this.a(arg5.f, false);
        String v2 = null;
        List v0 = arg5.g != null ? this.a(arg5.g, true) : ((List)v2);
        this.h = v0;
        if(arg5.h != null) {
            v2 = t.a(arg5.h, false);
        }

        this.i = v2;
        this.j = arg5.toString();
    }

    public static int a(String arg1) {
        if(arg1.equals("http")) {
            return 80;
        }

        if(arg1.equals("https")) {
            return 443;
        }

        return -1;
    }

    static String a(String arg2, boolean arg3) {
        return t.a(arg2, 0, arg2.length(), arg3);
    }

    private List a(List arg5, boolean arg6) {
        int v0 = arg5.size();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = arg5.get(v2);
            if(v3 != null) {
                String v3_1 = t.a(((String)v3), arg6);
            }
            else {
                v3 = null;
            }

            ((List)v1).add(v3);
        }

        return Collections.unmodifiableList(((List)v1));
    }

    static String a(String arg11, int arg12, int arg13, String arg14, boolean arg15, boolean arg16, boolean arg17, boolean arg18, Charset arg19) {
        int v3 = arg13;
        int v2 = arg12;
        while(true) {
            if(v2 >= v3) {
                goto label_46;
            }

            int v0 = arg11.codePointAt(v2);
            if(v0 >= 32 && v0 != 127) {
                if(v0 >= 128 && (arg18)) {
                    break;
                }

                if(arg14.indexOf(v0) != -1) {
                    break;
                }

                if(v0 == 37) {
                    if(!arg15) {
                        break;
                    }
                    else if(arg16) {
                        if(t.a(arg11, v2, arg13)) {
                            goto label_22;
                        }

                        break;
                    }
                }

            label_22:
                if(v0 == 43 && (arg17)) {
                    break;
                }

                v2 += Character.charCount(v0);
                continue;
            }

            break;
        }

        e.c v10 = new e.c();
        v10.a(arg11, arg12, v2);
        t.a(v10, arg11, v2, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
        return v10.p();
    label_46:
        return arg11.substring(arg12, arg13);
    }

    static boolean a(String arg3, int arg4, int arg5) {
        int v0 = arg4 + 2;
        boolean v1 = true;
        if(v0 >= arg5 || arg3.charAt(arg4) != 37) {
        label_15:
            v1 = false;
        }
        else {
            arg5 = -1;
            if(c.a(arg3.charAt(arg4 + 1)) == arg5) {
                goto label_15;
            }
            else if(c.a(arg3.charAt(v0)) != arg5) {
            }
            else {
                goto label_15;
            }
        }

        return v1;
    }

    static void a(e.c arg6, String arg7, int arg8, int arg9, String arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14, Charset arg15) {
        e.c v0 = null;
        while(arg8 < arg9) {
            int v1 = arg7.codePointAt(arg8);
            if(!arg11) {
            label_13:
                if(v1 == 43 && (arg13)) {
                    String v2 = arg11 ? "+" : "%2B";
                    arg6.a(v2);
                    goto label_69;
                }

                int v3 = 37;
                if(v1 >= 32 && v1 != 127 && (v1 < 128 || !arg14) && arg10.indexOf(v1) == -1) {
                    if(v1 == v3) {
                        if(!arg11) {
                            goto label_41;
                        }
                        else if((arg12) && !t.a(arg7, arg8, arg9)) {
                            goto label_41;
                        }
                    }

                    arg6.a(v1);
                    goto label_69;
                }

            label_41:
                if(v0 == null) {
                    v0 = new e.c();
                }

                if(arg15 == null || (arg15.equals(c.e))) {
                    v0.a(v1);
                }
                else {
                    v0.a(arg7, arg8, Character.charCount(v1) + arg8, arg15);
                }

                while(!v0.f()) {
                    int v2_1 = v0.i() & 255;
                    arg6.b(v3);
                    arg6.b(t.d[v2_1 >> 4 & 15]);
                    arg6.b(t.d[v2_1 & 15]);
                }
            }
            else if(v1 != 9 && v1 != 10 && v1 != 12) {
                if(v1 == 13) {
                }
                else {
                    goto label_13;
                }
            }

        label_69:
            arg8 += Character.charCount(v1);
        }
    }

    static String a(String arg3, int arg4, int arg5, boolean arg6) {
        int v0 = arg4;
        while(true) {
            if(v0 >= arg5) {
                goto label_17;
            }

            int v1 = arg3.charAt(v0);
            if(v1 != 37) {
                if(v1 == 43 && (arg6)) {
                    break;
                }

                ++v0;
                continue;
            }

            break;
        }

        e.c v1_1 = new e.c();
        v1_1.a(arg3, arg4, v0);
        t.a(v1_1, arg3, v0, arg5, arg6);
        return v1_1.p();
    label_17:
        return arg3.substring(arg4, arg5);
    }

    static void a(e.c arg5, String arg6, int arg7, int arg8, boolean arg9) {
        while(arg7 < arg8) {
            int v0 = arg6.codePointAt(arg7);
            if(v0 == 37) {
                int v1 = arg7 + 2;
                if(v1 < arg8) {
                    int v2 = c.a(arg6.charAt(arg7 + 1));
                    int v3 = c.a(arg6.charAt(v1));
                    int v4 = -1;
                    if(v2 == v4) {
                        goto label_25;
                    }
                    else if(v3 != v4) {
                        arg5.b((v2 << 4) + v3);
                        arg7 = v1;
                    }
                    else {
                        goto label_25;
                    }
                }
                else {
                    goto label_19;
                }
            }
            else {
            label_19:
                if(v0 != 43 || !arg9) {
                label_25:
                    arg5.a(v0);
                }
                else {
                    arg5.b(32);
                }
            }

            arg7 += Character.charCount(v0);
        }
    }

    static String a(String arg9, String arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14) {
        return t.a(arg9, 0, arg9.length(), arg10, arg11, arg12, arg13, arg14, null);
    }

    static String a(String arg9, String arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14, Charset arg15) {
        return t.a(arg9, 0, arg9.length(), arg10, arg11, arg12, arg13, arg14, arg15);
    }

    static void a(StringBuilder arg3, List arg4) {
        int v0 = arg4.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg3.append('/');
            arg3.append(arg4.get(v1));
        }
    }

    public URI a() {
        String v0 = this.o().b().toString();
        try {
            return new URI(v0);
        }
        catch(URISyntaxException v1) {
            try {
                return URI.create(v0.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            }
            catch(Exception ) {
                throw new RuntimeException(((Throwable)v1));
            }
        }
    }

    public String b() {
        return this.a;
    }

    static List b(String arg5) {
        String v1_1;
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 <= arg5.length(); v1 = v2 + 1) {
            int v2 = arg5.indexOf(38, v1);
            int v3 = -1;
            if(v2 == v3) {
                v2 = arg5.length();
            }

            int v4 = arg5.indexOf(61, v1);
            if(v4 == v3 || v4 > v2) {
                ((List)v0).add(arg5.substring(v1, v2));
                Object v1_2 = null;
            }
            else {
                ((List)v0).add(arg5.substring(v1, v4));
                v1_1 = arg5.substring(v4 + 1, v2);
            }

            ((List)v0).add(v1_1);
        }

        return ((List)v0);
    }

    static void b(StringBuilder arg5, List arg6) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; v1 += 2) {
            Object v2 = arg6.get(v1);
            Object v3 = arg6.get(v1 + 1);
            if(v1 > 0) {
                arg5.append('&');
            }

            arg5.append(((String)v2));
            if(v3 != null) {
                arg5.append('=');
                arg5.append(((String)v3));
            }
        }
    }

    @Nullable public t c(String arg1) {
        a v1 = this.d(arg1);
        t v1_1 = v1 != null ? v1.c() : null;
        return v1_1;
    }

    public boolean c() {
        return this.a.equals("https");
    }

    @Nullable public a d(String arg3) {
        a v0 = new a();
        if(v0.a(this, arg3) == okhttp3.t$a$a.a) {
        }
        else {
            v0 = null;
        }

        return v0;
    }

    public String d() {
        if(this.e.isEmpty()) {
            return "";
        }

        int v0 = this.a.length() + 3;
        return this.j.substring(v0, c.a(this.j, v0, this.j.length(), ":@"));
    }

    @Nullable public static t e(String arg3) {
        a v0 = new a();
        t v1 = null;
        if(v0.a(v1, arg3) == okhttp3.t$a$a.a) {
            v1 = v0.c();
        }

        return v1;
    }

    public String e() {
        if(this.f.isEmpty()) {
            return "";
        }

        return this.j.substring(this.j.indexOf(58, this.a.length() + 3) + 1, this.j.indexOf(64));
    }

    public boolean equals(@Nullable Object arg2) {
        boolean v2 = !(arg2 instanceof t) || !((t)arg2).j.equals(this.j) ? false : true;
        return v2;
    }

    public String f() {
        return this.b;
    }

    public int g() {
        return this.c;
    }

    public String h() {
        int v0 = this.j.indexOf(47, this.a.length() + 3);
        return this.j.substring(v0, c.a(this.j, v0, this.j.length(), "?#"));
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public List i() {
        char v2 = '/';
        int v0 = this.j.indexOf(v2, this.a.length() + 3);
        int v1 = c.a(this.j, v0, this.j.length(), "?#");
        ArrayList v3 = new ArrayList();
        while(v0 < v1) {
            ++v0;
            int v4 = c.a(this.j, v0, v1, v2);
            ((List)v3).add(this.j.substring(v0, v4));
            v0 = v4;
        }

        return ((List)v3);
    }

    public List j() {
        return this.g;
    }

    @Nullable public String k() {
        if(this.h == null) {
            return null;
        }

        int v0 = this.j.indexOf(63) + 1;
        return this.j.substring(v0, c.a(this.j, v0, this.j.length(), '#'));
    }

    @Nullable public String l() {
        if(this.h == null) {
            return null;
        }

        StringBuilder v0 = new StringBuilder();
        t.b(v0, this.h);
        return v0.toString();
    }

    @Nullable public String m() {
        if(this.i == null) {
            return null;
        }

        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public String n() {
        return this.d("/...").b("").c("").c().toString();
    }

    public a o() {
        a v0 = new a();
        v0.a = this.a;
        v0.b = this.d();
        v0.c = this.e();
        v0.d = this.b;
        int v1 = this.c != t.a(this.a) ? this.c : -1;
        v0.e = v1;
        v0.f.clear();
        v0.f.addAll(this.i());
        v0.e(this.k());
        v0.h = this.m();
        return v0;
    }

    public String toString() {
        return this.j;
    }
}

