package com.persianswitch.a;

import com.persianswitch.a.a.l;
import com.persianswitch.b.c;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class r {
    class com.persianswitch.a.r$1 {
        static {
            com.persianswitch.a.r$1.a = new int[a.values().length];
            try {
                com.persianswitch.a.r$1.a[a.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.persianswitch.a.r$1.a[a.e.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.persianswitch.a.r$1.a[a.c.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.persianswitch.a.r$1.a[a.b.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.persianswitch.a.r$1.a[a.d.ordinal()] = 5;
                                return;
                            }
                            catch(NoSuchFieldError ) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public final class com.persianswitch.a.r$a {
        enum a {
            public static final enum a a;
            public static final enum a b;
            public static final enum a c;
            public static final enum a d;
            public static final enum a e;

            static {
                a.a = new a("SUCCESS", 0);
                a.b = new a("MISSING_SCHEME", 1);
                a.c = new a("UNSUPPORTED_SCHEME", 2);
                a.d = new a("INVALID_PORT", 3);
                a.e = new a("INVALID_HOST", 4);
                a.f = new a[]{a.a, a.b, a.c, a.d, a.e};
            }

            private a(String arg1, int arg2) {
                super(arg1, arg2);
            }

            public static a valueOf(String arg1) {
                return Enum.valueOf(a.class, arg1);
            }

            public static a[] values() {
                // Method was not decompiled
            }
        }

        String a;
        String b;
        String c;
        String d;
        int e;
        final List f;
        List g;
        String h;

        public com.persianswitch.a.r$a() {
            super();
            this.b = "";
            this.c = "";
            this.e = -1;
            this.f = new ArrayList();
            this.f.add("");
        }

        public com.persianswitch.a.r$a a(int arg4) {
            if(arg4 > 0 && arg4 <= 65535) {
                this.e = arg4;
                return this;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("unexpected port: ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        public com.persianswitch.a.r$a a(String arg4) {
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
            int v0 = this.e != -1 ? this.e : r.a(this.a);
            return v0;
        }

        a a(r arg19, String arg20) {
            int v1;
            int v8;
            String v2;
            com.persianswitch.a.r$a v0 = this;
            String v9 = arg20;
            int v10 = l.a(v9, 0, arg20.length());
            int v11 = l.b(v9, v10, arg20.length());
            int v12 = -1;
            if(com.persianswitch.a.r$a.b(v9, v10, v11) != v12) {
                if(arg20.regionMatches(true, v10, "https:", 0, 6)) {
                    v0.a = "https";
                    v2 = "https:";
                }
                else if(arg20.regionMatches(true, v10, "http:", 0, 5)) {
                    v0.a = "http";
                    v2 = "http:";
                }
                else {
                    goto label_36;
                }

                v10 += v2.length();
                goto label_41;
            label_36:
                return a.c;
            }
            else {
                if(arg19 == null) {
                    goto label_208;
                }

                v0.a = r.a(arg19);
            }

        label_41:
            int v2_1 = com.persianswitch.a.r$a.c(v9, v10, v11);
            int v13 = 35;
            if(v2_1 >= 2 || arg19 == null || !r.a(arg19).equals(v0.a)) {
                v2_1 = v10 + v2_1;
                v10 = 0;
                int v15 = 0;
                while(true) {
                    v8 = l.a(v9, v2_1, v11, "@/\\?#");
                    v1 = v8 != v11 ? v9.charAt(v8) : -1;
                    if(v1 != v12 && v1 != v13 && v1 != 47 && v1 != 92) {
                        switch(v1) {
                            case 63: {
                                goto label_149;
                            }
                            case 64: {
                                goto label_89;
                            }
                        }

                        goto label_147;
                    label_89:
                        if(v10 == 0) {
                            int v7 = l.a(v9, v2_1, v8, ':');
                            int v14 = v7;
                            v13 = v8;
                            String v1_1 = r.a(arg20, v2_1, v7, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            if(v15 != 0) {
                                v1_1 = v0.b + "%40" + v1_1;
                            }

                            v0.b = v1_1;
                            if(v14 != v13) {
                                v0.c = r.a(arg20, v14 + 1, v13, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                v10 = 1;
                            }

                            v15 = 1;
                        }
                        else {
                            v13 = v8;
                            v0.c = v0.c + "%40" + r.a(arg20, v2_1, v13, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, true);
                        }

                        v2_1 = v13 + 1;
                    label_147:
                        v13 = 35;
                        continue;
                    }

                    break;
                }

            label_149:
                v13 = v8;
                v1 = com.persianswitch.a.r$a.d(v9, v2_1, v13);
                int v3 = v1 + 1;
                if(v3 < v13) {
                    v0.d = com.persianswitch.a.r$a.e(v9, v2_1, v1);
                    v0.e = com.persianswitch.a.r$a.g(v9, v3, v13);
                    if(v0.e == v12) {
                        return a.d;
                    }
                }
                else {
                    v0.d = com.persianswitch.a.r$a.e(v9, v2_1, v1);
                    v0.e = r.a(v0.a);
                }

                if(v0.d != null) {
                    goto label_170;
                }

                return a.e;
            }
            else {
                v0.b = arg19.d();
                v0.c = arg19.e();
                v0.d = r.b(arg19);
                v0.e = r.c(arg19);
                v0.f.clear();
                v0.f.addAll(arg19.i());
                if(v10 == v11 || v9.charAt(v10) == v13) {
                    v0.c(arg19.j());
                }

                v13 = v10;
            }

        label_170:
            v1 = l.a(v9, v13, v11, "?#");
            v0.a(v9, v13, v1);
            if(v1 < v11 && v9.charAt(v1) == 63) {
                v10 = l.a(v9, v1, v11, '#');
                v0.g = r.b(r.a(arg20, v1 + 1, v10, " \"\'<>#", true, false, true, true));
                v1 = v10;
            }

            if(v1 < v11 && v9.charAt(v1) == 35) {
                v0.h = r.a(arg20, 1 + v1, v11, "", true, false, false, false);
            }

            return a.a;
        label_208:
            return a.b;
        }

        private static String a(byte[] arg8) {
            int v0 = 0;
            int v1 = 0;
            int v2 = 0;
            int v3 = -1;
            while(true) {
                int v5 = 16;
                if(v1 >= arg8.length) {
                    break;
                }

                int v4;
                for(v4 = v1; v4 < v5; v4 += 2) {
                    if(arg8[v4] != 0) {
                        break;
                    }

                    if(arg8[v4 + 1] != 0) {
                        break;
                    }
                }

                v5 = v4 - v1;
                if(v5 > v2) {
                    v3 = v1;
                    v2 = v5;
                }

                v1 = v4 + 2;
            }

            c v1_1 = new c();
            while(v0 < arg8.length) {
                v4 = 58;
                if(v0 == v3) {
                    v1_1.b(v4);
                    v0 += v2;
                    if(v0 != v5) {
                        continue;
                    }

                    v1_1.b(v4);
                    continue;
                }

                if(v0 > 0) {
                    v1_1.b(v4);
                }

                v1_1.i(((long)((arg8[v0] & 255) << 8 | arg8[v0 + 1] & 255)));
                v0 += 2;
            }

            return v1_1.o();
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
                        arg11 = l.a(arg10, v5, arg12, "/\\");
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

        private void a(String arg9, int arg10, int arg11, boolean arg12, boolean arg13) {
            arg9 = r.a(arg9, arg10, arg11, " \"<>^`{}|/\\?#", arg13, false, false, true);
            if(this.d(arg9)) {
                return;
            }

            if(this.e(arg9)) {
                this.d();
                return;
            }

            if(this.f.get(this.f.size() - 1).isEmpty()) {
                this.f.set(this.f.size() - 1, arg9);
            }
            else {
                this.f.add(arg9);
            }

            if(arg12) {
                this.f.add("");
            }
        }

        private static boolean a(String arg7, int arg8, int arg9, byte[] arg10, int arg11) {
            int v0 = arg11;
            while(arg8 < arg9) {
                if(v0 == arg10.length) {
                    return 0;
                }

                if(v0 != arg11) {
                    if(arg7.charAt(arg8) != 46) {
                        return 0;
                    }
                    else {
                        ++arg8;
                    }
                }

                int v2 = arg8;
                int v3 = 0;
                while(v2 < arg9) {
                    int v4 = arg7.charAt(v2);
                    int v5 = 48;
                    if(v4 < v5) {
                        break;
                    }

                    if(v4 > 57) {
                    }
                    else {
                        if(v3 == 0 && arg8 != v2) {
                            return 0;
                        }

                        v3 = v3 * 10 + v4 - v5;
                        if(v3 > 255) {
                            return 0;
                        }

                        ++v2;
                        continue;
                    }

                    break;
                }

                if(v2 - arg8 == 0) {
                    return 0;
                }

                arg10[v0] = ((byte)v3);
                ++v0;
                arg8 = v2;
            }

            if(v0 != arg11 + 4) {
                return 0;
            }

            return 1;
        }

        public com.persianswitch.a.r$a b(String arg4) {
            if(arg4 != null) {
                String v0 = com.persianswitch.a.r$a.e(arg4, 0, arg4.length());
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

        com.persianswitch.a.r$a b() {
            int v0 = this.f.size();
            int v1 = 0;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                this.f.set(v2, r.a(this.f.get(v2), "[]", true, true, false, true));
            }

            if(this.g != null) {
                v0 = this.g.size();
                while(v1 < v0) {
                    Object v3 = this.g.get(v1);
                    if(v3 != null) {
                        this.g.set(v1, r.a(((String)v3), "\\^`{|}", true, true, true, true));
                    }

                    ++v1;
                }
            }

            if(this.h != null) {
                this.h = r.a(this.h, " \"#<>\\^`{|}", true, true, false, false);
            }

            return this;
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

        public r c() {
            if(this.a != null) {
                if(this.d != null) {
                    return new r(this, null);
                }

                throw new IllegalStateException("host == null");
            }

            throw new IllegalStateException("scheme == null");
        }

        public com.persianswitch.a.r$a c(String arg7) {
            List v7 = arg7 != null ? r.b(r.a(arg7, " \"\'<>#", true, false, true, true)) : null;
            this.g = v7;
            return this;
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

        private boolean d(String arg2) {
            boolean v2 = (arg2.equals(".")) || (arg2.equalsIgnoreCase("%2e")) ? true : false;
            return v2;
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

        private boolean e(String arg2) {
            boolean v2 = (arg2.equals("..")) || (arg2.equalsIgnoreCase("%2e.")) || (arg2.equalsIgnoreCase(".%2e")) || (arg2.equalsIgnoreCase("%2e%2e")) ? true : false;
            return v2;
        }

        private static String e(String arg1, int arg2, int arg3) {
            arg1 = r.a(arg1, arg2, arg3, false);
            if(arg1.contains(":")) {
                InetAddress v1 = !arg1.startsWith("[") || !arg1.endsWith("]") ? com.persianswitch.a.r$a.f(arg1, 0, arg1.length()) : com.persianswitch.a.r$a.f(arg1, 1, arg1.length() - 1);
                if(v1 == null) {
                    return null;
                }

                byte[] v1_1 = v1.getAddress();
                if(v1_1.length == 16) {
                    return com.persianswitch.a.r$a.a(v1_1);
                }

                throw new AssertionError();
            }

            return l.a(arg1);
        }

        private static InetAddress f(String arg10, int arg11, int arg12) {
            int v8;
            InetAddress v6;
            byte[] v0 = new byte[16];
            int v1 = -1;
            int v3 = 0;
            int v4 = -1;
            int v5 = -1;
            while(true) {
                v6 = null;
                if(arg11 < arg12) {
                    if(v3 == v0.length) {
                        return v6;
                    }
                    else {
                        int v7 = arg11 + 2;
                        if(v7 > arg12 || !arg10.regionMatches(arg11, "::", 0, 2)) {
                            if(v3 != 0) {
                                if(arg10.regionMatches(arg11, ":", 0, 1)) {
                                    ++arg11;
                                }
                                else if(!arg10.regionMatches(arg11, ".", 0, 1)) {
                                    return v6;
                                }
                                else if(!com.persianswitch.a.r$a.a(arg10, v5, arg12, v0, v3 - 2)) {
                                    return v6;
                                }
                                else {
                                    v3 += 2;
                                    goto label_72;
                                }
                            }

                            v5 = arg11;
                        }
                        else if(v4 != v1) {
                            return v6;
                        }
                        else {
                            v3 += 2;
                            if(v7 == arg12) {
                                v4 = v3;
                                goto label_72;
                            }
                            else {
                                v4 = v3;
                                v5 = v7;
                            }
                        }

                        arg11 = v5;
                        v7 = 0;
                        while(arg11 < arg12) {
                            v8 = r.a(arg10.charAt(arg11));
                            if(v8 == v1) {
                            }
                            else {
                                v7 = (v7 << 4) + v8;
                                ++arg11;
                                continue;
                            }

                            break;
                        }

                        v8 = arg11 - v5;
                        if(v8 != 0) {
                            if(v8 > 4) {
                            }
                            else {
                                int v6_1 = v3 + 1;
                                v0[v3] = ((byte)(v7 >>> 8 & 255));
                                v3 = v6_1 + 1;
                                v0[v6_1] = ((byte)(v7 & 255));
                                continue;
                            }
                        }

                        return v6;
                    }
                }

                goto label_72;
            }

            return v6;
        label_72:
            if(v3 != v0.length) {
                if(v4 == v1) {
                    return v6;
                }
                else {
                    arg11 = v3 - v4;
                    System.arraycopy(v0, v4, v0, v0.length - arg11, arg11);
                    Arrays.fill(v0, v4, v0.length - v3 + v4, 0);
                }
            }

            try {
                return InetAddress.getByAddress(v0);
            }
            catch(UnknownHostException ) {
                throw new AssertionError();
            }
        }

        private static int g(String arg9, int arg10, int arg11) {
            int v9;
            int v0 = -1;
            try {
                v9 = Integer.parseInt(r.a(arg9, arg10, arg11, "", false, false, false, true));
                if(v9 > 0) {
                    goto label_12;
                }
            }
            catch(NumberFormatException ) {
            }

            return v0;
        label_12:
            if(v9 <= 65535) {
                return v9;
            }

            return v0;
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
            if(v1 != r.a(this.a)) {
                v0.append(v2);
                v0.append(v1);
            }

            r.a(v0, this.f);
            if(this.g != null) {
                v0.append('?');
                r.b(v0, this.g);
            }

            if(this.h != null) {
                v0.append('#');
                v0.append(this.h);
            }

            return v0.toString();
        }
    }

    private static final char[] a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final int f;
    private final List g;
    private final List h;
    private final String i;
    private final String j;

    static {
        r.a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    private r(com.persianswitch.a.r$a arg5) {
        super();
        this.b = arg5.a;
        this.c = r.a(arg5.b, false);
        this.d = r.a(arg5.c, false);
        this.e = arg5.d;
        this.f = arg5.a();
        this.g = this.a(arg5.f, false);
        String v2 = null;
        List v0 = arg5.g != null ? this.a(arg5.g, true) : ((List)v2);
        this.h = v0;
        if(arg5.h != null) {
            v2 = r.a(arg5.h, false);
        }

        this.i = v2;
        this.j = arg5.toString();
    }

    r(com.persianswitch.a.r$a arg1, com.persianswitch.a.r$1 arg2) {
        this(arg1);
    }

    public URI a() {
        String v0 = this.m().b().toString();
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
        return r.a(arg2, 0, arg2.length(), arg3);
    }

    private List a(List arg3, boolean arg4) {
        String v1_1;
        ArrayList v0 = new ArrayList(arg3.size());
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v1 = v3.next();
            if(v1 != null) {
                v1_1 = r.a(((String)v1), arg4);
            }
            else {
                v1 = null;
            }

            ((List)v0).add(v1_1);
        }

        return Collections.unmodifiableList(((List)v0));
    }

    static int a(char arg2) {
        int v0 = 48;
        if(arg2 >= v0 && arg2 <= 57) {
            return arg2 - v0;
        }

        v0 = 97;
        if(arg2 < v0 || arg2 > 102) {
            v0 = 65;
            if(arg2 >= v0 && arg2 <= 70) {
                goto label_10;
            }
        }
        else {
        label_10:
            return arg2 - v0 + 10;
        }

        return -1;
    }

    static String a(r arg0) {
        return arg0.b;
    }

    static String a(String arg10, int arg11, int arg12, String arg13, boolean arg14, boolean arg15, boolean arg16, boolean arg17) {
        int v3 = arg12;
        int v2 = arg11;
        while(true) {
            if(v2 >= v3) {
                goto label_45;
            }

            int v0 = arg10.codePointAt(v2);
            if(v0 >= 32 && v0 != 127) {
                if(v0 >= 128 && (arg17)) {
                    break;
                }

                if(arg13.indexOf(v0) != -1) {
                    break;
                }

                if(v0 == 37) {
                    if(!arg14) {
                        break;
                    }
                    else if(arg15) {
                        if(r.a(arg10, v2, arg12)) {
                            goto label_22;
                        }

                        break;
                    }
                }

            label_22:
                if(v0 == 43 && (arg16)) {
                    break;
                }

                v2 += Character.charCount(v0);
                continue;
            }

            break;
        }

        c v9 = new c();
        v9.a(arg10, arg11, v2);
        r.a(v9, arg10, v2, arg12, arg13, arg14, arg15, arg16, arg17);
        return v9.o();
    label_45:
        return arg10.substring(arg11, arg12);
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
            if(r.a(arg3.charAt(arg4 + 1)) == arg5) {
                goto label_15;
            }
            else if(r.a(arg3.charAt(v0)) != arg5) {
            }
            else {
                goto label_15;
            }
        }

        return v1;
    }

    static void a(c arg6, String arg7, int arg8, int arg9, String arg10, boolean arg11, boolean arg12, boolean arg13, boolean arg14) {
        c v0 = null;
        while(arg8 < arg9) {
            int v1 = arg7.codePointAt(arg8);
            if(!arg11) {
            label_13:
                if(v1 == 43 && (arg13)) {
                    String v2 = arg11 ? "+" : "%2B";
                    arg6.a(v2);
                    goto label_60;
                }

                int v3 = 37;
                if(v1 >= 32 && v1 != 127 && (v1 < 128 || !arg14) && arg10.indexOf(v1) == -1) {
                    if(v1 == v3) {
                        if(!arg11) {
                            goto label_41;
                        }
                        else if((arg12) && !r.a(arg7, arg8, arg9)) {
                            goto label_41;
                        }
                    }

                    arg6.a(v1);
                    goto label_60;
                }

            label_41:
                if(v0 == null) {
                    v0 = new c();
                }

                v0.a(v1);
                while(!v0.e()) {
                    int v2_1 = v0.h() & 255;
                    arg6.b(v3);
                    arg6.b(r.a[v2_1 >> 4 & 15]);
                    arg6.b(r.a[v2_1 & 15]);
                }
            }
            else if(v1 != 9 && v1 != 10 && v1 != 12) {
                if(v1 == 13) {
                }
                else {
                    goto label_13;
                }
            }

        label_60:
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

        c v1_1 = new c();
        v1_1.a(arg3, arg4, v0);
        r.a(v1_1, arg3, v0, arg5, arg6);
        return v1_1.o();
    label_17:
        return arg3.substring(arg4, arg5);
    }

    static void a(c arg5, String arg6, int arg7, int arg8, boolean arg9) {
        while(arg7 < arg8) {
            int v0 = arg6.codePointAt(arg7);
            if(v0 == 37) {
                int v1 = arg7 + 2;
                if(v1 < arg8) {
                    int v2 = r.a(arg6.charAt(arg7 + 1));
                    int v3 = r.a(arg6.charAt(v1));
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

    static String a(String arg8, String arg9, boolean arg10, boolean arg11, boolean arg12, boolean arg13) {
        return r.a(arg8, 0, arg8.length(), arg9, arg10, arg11, arg12, arg13);
    }

    static void a(StringBuilder arg3, List arg4) {
        int v0 = arg4.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            arg3.append('/');
            arg3.append(arg4.get(v1));
        }
    }

    public String b() {
        return this.b;
    }

    static String b(r arg0) {
        return arg0.e;
    }

    static List b(String arg5) {
        Object v1_2;
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
                v1_2 = null;
            }
            else {
                ((List)v0).add(arg5.substring(v1, v4));
                String v1_1 = arg5.substring(v4 + 1, v2);
            }

            ((List)v0).add(v1_2);
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

    public boolean c() {
        return this.b.equals("https");
    }

    static int c(r arg0) {
        return arg0.f;
    }

    public r c(String arg1) {
        com.persianswitch.a.r$a v1 = this.d(arg1);
        r v1_1 = v1 != null ? v1.c() : null;
        return v1_1;
    }

    public com.persianswitch.a.r$a d(String arg3) {
        com.persianswitch.a.r$a v0 = new com.persianswitch.a.r$a();
        if(v0.a(this, arg3) == a.a) {
        }
        else {
            v0 = null;
        }

        return v0;
    }

    public String d() {
        if(this.c.isEmpty()) {
            return "";
        }

        int v0 = this.b.length() + 3;
        return this.j.substring(v0, l.a(this.j, v0, this.j.length(), ":@"));
    }

    public static r e(String arg3) {
        com.persianswitch.a.r$a v0 = new com.persianswitch.a.r$a();
        r v1 = null;
        if(v0.a(v1, arg3) == a.a) {
            v1 = v0.c();
        }

        return v1;
    }

    public String e() {
        if(this.d.isEmpty()) {
            return "";
        }

        return this.j.substring(this.j.indexOf(58, this.b.length() + 3) + 1, this.j.indexOf(64));
    }

    public boolean equals(Object arg2) {
        boolean v2 = !(arg2 instanceof r) || !((r)arg2).j.equals(this.j) ? false : true;
        return v2;
    }

    public String f() {
        return this.e;
    }

    public int g() {
        return this.f;
    }

    public String h() {
        int v0 = this.j.indexOf(47, this.b.length() + 3);
        return this.j.substring(v0, l.a(this.j, v0, this.j.length(), "?#"));
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public List i() {
        char v2 = '/';
        int v0 = this.j.indexOf(v2, this.b.length() + 3);
        int v1 = l.a(this.j, v0, this.j.length(), "?#");
        ArrayList v3 = new ArrayList();
        while(v0 < v1) {
            ++v0;
            int v4 = l.a(this.j, v0, v1, v2);
            ((List)v3).add(this.j.substring(v0, v4));
            v0 = v4;
        }

        return ((List)v3);
    }

    public String j() {
        if(this.h == null) {
            return null;
        }

        int v0 = this.j.indexOf(63) + 1;
        return this.j.substring(v0, l.a(this.j, v0 + 1, this.j.length(), '#'));
    }

    public String k() {
        if(this.h == null) {
            return null;
        }

        StringBuilder v0 = new StringBuilder();
        r.b(v0, this.h);
        return v0.toString();
    }

    public String l() {
        if(this.i == null) {
            return null;
        }

        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public com.persianswitch.a.r$a m() {
        com.persianswitch.a.r$a v0 = new com.persianswitch.a.r$a();
        v0.a = this.b;
        v0.b = this.d();
        v0.c = this.e();
        v0.d = this.e;
        int v1 = this.f != r.a(this.b) ? this.f : -1;
        v0.e = v1;
        v0.f.clear();
        v0.f.addAll(this.i());
        v0.c(this.j());
        v0.h = this.l();
        return v0;
    }

    public String toString() {
        return this.j;
    }
}

