package com.google.a;

import java.lang.reflect.Field;
import java.util.Locale;

public abstract enum d implements e {
    enum com.google.a.d$1 extends d {
        com.google.a.d$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg1) {
            return arg1.getName();
        }
    }

    enum com.google.a.d$2 extends d {
        com.google.a.d$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg1) {
            return com.google.a.d$2.a(arg1.getName());
        }
    }

    enum com.google.a.d$3 extends d {
        com.google.a.d$3(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.a.d$3.a(com.google.a.d$3.a(arg2.getName(), " "));
        }
    }

    enum com.google.a.d$4 extends d {
        com.google.a.d$4(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.a.d$4.a(arg2.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    }

    enum com.google.a.d$5 extends d {
        com.google.a.d$5(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.a.d$5.a(arg2.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    }

    public static final enum d a;
    public static final enum d b;
    public static final enum d c;
    public static final enum d d;
    public static final enum d e;

    static {
        d.a = new com.google.a.d$1("IDENTITY", 0);
        d.b = new com.google.a.d$2("UPPER_CAMEL_CASE", 1);
        d.c = new com.google.a.d$3("UPPER_CAMEL_CASE_WITH_SPACES", 2);
        d.d = new com.google.a.d$4("LOWER_CASE_WITH_UNDERSCORES", 3);
        d.e = new com.google.a.d$5("LOWER_CASE_WITH_DASHES", 4);
        d.f = new d[]{d.a, d.b, d.c, d.d, d.e};
    }

    private d(String arg1, int arg2) {
        super(arg1, arg2);
    }

    d(String arg1, int arg2, com.google.a.d$1 arg3) {
        this(arg1, arg2);
    }

    private static String a(char arg1, String arg2, int arg3) {
        String v1 = arg3 < arg2.length() ? arg1 + arg2.substring(arg3) : String.valueOf(arg1);
        return v1;
    }

    static String a(String arg5) {
        StringBuilder v0 = new StringBuilder();
        int v1 = 0;
        char v2 = arg5.charAt(0);
        int v3 = arg5.length();
        while(v1 < v3 - 1) {
            if(Character.isLetter(v2)) {
            }
            else {
                v0.append(v2);
                ++v1;
                v2 = arg5.charAt(v1);
                continue;
            }

            break;
        }

        if(!Character.isUpperCase(v2)) {
            v0.append(d.a(Character.toUpperCase(v2), arg5, v1 + 1));
            arg5 = v0.toString();
        }

        return arg5;
    }

    static String a(String arg5, String arg6) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg5.length();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            char v3 = arg5.charAt(v2);
            if((Character.isUpperCase(v3)) && v0.length() != 0) {
                v0.append(arg6);
            }

            v0.append(v3);
        }

        return v0.toString();
    }

    public static d valueOf(String arg1) {
        return Enum.valueOf(d.class, arg1);
    }

    public static d[] values() {
        // Method was not decompiled
    }
}

