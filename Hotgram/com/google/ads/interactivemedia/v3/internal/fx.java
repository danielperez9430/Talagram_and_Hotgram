package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.util.Locale;

public abstract enum fx implements fy {
    enum com.google.ads.interactivemedia.v3.internal.fx$1 extends fx {
        com.google.ads.interactivemedia.v3.internal.fx$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg1) {
            return arg1.getName();
        }
    }

    enum com.google.ads.interactivemedia.v3.internal.fx$2 extends fx {
        com.google.ads.interactivemedia.v3.internal.fx$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg1) {
            return com.google.ads.interactivemedia.v3.internal.fx$2.a(arg1.getName());
        }
    }

    enum com.google.ads.interactivemedia.v3.internal.fx$3 extends fx {
        com.google.ads.interactivemedia.v3.internal.fx$3(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.ads.interactivemedia.v3.internal.fx$3.a(com.google.ads.interactivemedia.v3.internal.fx$3.a(arg2.getName(), " "));
        }
    }

    enum com.google.ads.interactivemedia.v3.internal.fx$4 extends fx {
        com.google.ads.interactivemedia.v3.internal.fx$4(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.ads.interactivemedia.v3.internal.fx$4.a(arg2.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    }

    enum com.google.ads.interactivemedia.v3.internal.fx$5 extends fx {
        com.google.ads.interactivemedia.v3.internal.fx$5(String arg2, int arg3) {
            super(arg2, arg3, null);
        }

        public String a(Field arg2) {
            return com.google.ads.interactivemedia.v3.internal.fx$5.a(arg2.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    }

    public static final enum fx a;
    public static final enum fx b;
    public static final enum fx c;
    public static final enum fx d;
    public static final enum fx e;

    static {
        fx.a = new com.google.ads.interactivemedia.v3.internal.fx$1("IDENTITY", 0);
        fx.b = new com.google.ads.interactivemedia.v3.internal.fx$2("UPPER_CAMEL_CASE", 1);
        fx.c = new com.google.ads.interactivemedia.v3.internal.fx$3("UPPER_CAMEL_CASE_WITH_SPACES", 2);
        fx.d = new com.google.ads.interactivemedia.v3.internal.fx$4("LOWER_CASE_WITH_UNDERSCORES", 3);
        fx.e = new com.google.ads.interactivemedia.v3.internal.fx$5("LOWER_CASE_WITH_DASHES", 4);
        fx.f = new fx[]{fx.a, fx.b, fx.c, fx.d, fx.e};
    }

    private fx(String arg1, int arg2) {
        super(arg1, arg2);
    }

    fx(String arg1, int arg2, com.google.ads.interactivemedia.v3.internal.fx$1 arg3) {
        this(arg1, arg2);
    }

    private static String a(char arg1, String arg2, int arg3) {
        String v1 = arg3 < arg2.length() ? arg1 + arg2.substring(arg3) : String.valueOf(arg1);
        return v1;
    }

    static String a(String arg4) {
        char v2;
        StringBuilder v0 = new StringBuilder();
        int v1 = 0;
        while(true) {
            v2 = arg4.charAt(v1);
            if(v1 < arg4.length() - 1) {
                if(Character.isLetter(v2)) {
                }
                else {
                    v0.append(v2);
                    ++v1;
                    continue;
                }
            }

            break;
        }

        if(v1 == arg4.length()) {
            return v0.toString();
        }

        if(!Character.isUpperCase(v2)) {
            v0.append(fx.a(Character.toUpperCase(v2), arg4, v1 + 1));
            arg4 = v0.toString();
        }

        return arg4;
    }

    static String a(String arg4, String arg5) {
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < arg4.length(); ++v1) {
            char v2 = arg4.charAt(v1);
            if((Character.isUpperCase(v2)) && v0.length() != 0) {
                v0.append(arg5);
            }

            v0.append(v2);
        }

        return v0.toString();
    }

    public static fx valueOf(String arg1) {
        return Enum.valueOf(fx.class, arg1);
    }

    public static fx[] values() {
        // Method was not decompiled
    }
}

