package org.apache.a.a;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class b {
    private static final Pattern a;
    private static boolean b;
    private static Method c;
    private static final Pattern d;
    private static boolean e;
    private static Method f;
    private static Object g;
    private static final Pattern h;

    static {
        Class v1_1;
        b.a = Pattern.compile("\\s+");
        b.b = false;
        Method v1 = null;
        b.c = v1;
        b.d = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        b.e = false;
        b.f = v1;
        b.g = v1;
        b.h = b.d;
        int v2 = 2;
        try {
            Class v4 = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer$Form");
            b.g = v4.getField("NFD").get(v1);
            v1_1 = Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");
            b.f = v1_1.getMethod("normalize", CharSequence.class, v4);
            b.e = true;
        }
        catch(NoSuchMethodException ) {
            b.e = false;
        }

        try {
            v1_1 = Thread.currentThread().getContextClassLoader().loadClass("sun.text.Normalizer");
            Class[] v5 = new Class[3];
            v5[0] = String.class;
            v5[1] = Boolean.TYPE;
            v5[v2] = Integer.TYPE;
            b.c = v1_1.getMethod("decompose", v5);
            b.b = true;
        }
        catch(NoSuchMethodException ) {
            b.b = false;
        }
    }

    public static int a(CharSequence arg3, CharSequence arg4) {
        int v1 = 0;
        if(!b.a(arg3)) {
            if(b.a(arg4)) {
            }
            else {
                int v0 = 0;
                while(true) {
                    v1 = a.a(arg3, arg4, v1);
                    if(v1 == -1) {
                        return v0;
                    }

                    ++v0;
                    v1 += arg4.length();
                }

                return v0;
            }
        }

        return 0;
    }

    public static boolean a(CharSequence arg0) {
        boolean v0 = arg0 == null || arg0.length() == 0 ? true : false;
        return v0;
    }

    public static String a(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return new StringBuilder(arg1).reverse().toString();
    }

    public static String a(String arg1, String arg2) {
        if(!b.a(((CharSequence)arg1))) {
            if(b.a(((CharSequence)arg2))) {
            }
            else if(arg1.startsWith(arg2)) {
                arg1 = arg1.substring(arg2.length());
            }
        }

        return arg1;
    }

    public static String a(String arg1, String arg2, String arg3) {
        return b.a(arg1, arg2, arg3, -1);
    }

    public static String a(String arg7, String arg8, String arg9, int arg10) {
        if(!b.a(((CharSequence)arg7)) && !b.a(((CharSequence)arg8)) && arg9 != null) {
            if(arg10 == 0) {
            }
            else {
                int v0 = 0;
                int v1 = arg7.indexOf(arg8, 0);
                int v2 = -1;
                if(v1 == v2) {
                    return arg7;
                }
                else {
                    int v3 = arg8.length();
                    int v4 = arg9.length() - v3;
                    if(v4 < 0) {
                        v4 = 0;
                    }

                    int v5 = 64;
                    if(arg10 < 0) {
                        v5 = 16;
                    }
                    else if(arg10 > v5) {
                    }
                    else {
                        v5 = arg10;
                    }

                    StringBuilder v5_1 = new StringBuilder(arg7.length() + v4 * v5);
                    while(v1 != v2) {
                        v5_1.append(arg7.substring(v0, v1));
                        v5_1.append(arg9);
                        v0 = v1 + v3;
                        arg10 += v2;
                        if(arg10 == 0) {
                        }
                        else {
                            v1 = arg7.indexOf(arg8, v0);
                            continue;
                        }

                        break;
                    }

                    v5_1.append(arg7.substring(v0));
                    arg7 = v5_1.toString();
                }
            }
        }

        return arg7;
    }
}

