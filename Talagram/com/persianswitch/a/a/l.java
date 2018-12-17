package com.persianswitch.a.a;

import com.persianswitch.a.r;
import com.persianswitch.b.c;
import com.persianswitch.b.f;
import com.persianswitch.b.s;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public final class l {
    public static final byte[] a;
    public static final String[] b;
    public static final Charset c;
    public static final TimeZone d;
    private static final Pattern e;

    static {
        l.a = new byte[0];
        l.b = new String[0];
        l.c = Charset.forName("UTF-8");
        l.d = TimeZone.getTimeZone("GMT");
        l.e = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static ThreadFactory a(String arg1, boolean arg2) {
        return new ThreadFactory(arg1, arg2) {
            public Thread newThread(Runnable arg3) {
                Thread v0 = new Thread(arg3, this.a);
                v0.setDaemon(this.b);
                return v0;
            }
        };
    }

    public static String a(String arg1, Object[] arg2) {
        return String.format(Locale.US, arg1, arg2);
    }

    public static void a(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
            catch(RuntimeException v0) {
                throw v0;
            }
        }
    }

    public static boolean a(s arg0, int arg1, TimeUnit arg2) {
        try {
            return l.b(arg0, arg1, arg2);
        }
        catch(IOException ) {
            return 0;
        }
    }

    public static String a(r arg2, boolean arg3) {
        String v0_1 = arg2.f().contains(":") ? "[" + arg2.f() + "]" : arg2.f();
        if((arg3) || arg2.g() != r.a(arg2.b())) {
            v0_1 = v0_1 + ":" + arg2.g();
        }

        return v0_1;
    }

    public static void a(Socket arg1) {
        if(arg1 == null) {
            return;
        }

        try {
            arg1.close();
            return;
        }
        catch(Exception ) {
            return;
        }
        catch(RuntimeException v1) {
            throw v1;
        }
        catch(AssertionError v1_1) {
            if(l.a(v1_1)) {
                return;
            }

            throw v1_1;
        }
    }

    public static int a(String arg1, int arg2, int arg3) {
        while(arg2 < arg3) {
            switch(arg1.charAt(arg2)) {
                case 9: 
                case 10: 
                case 12: 
                case 13: 
                case 32: {
                    goto label_4;
                }
            }

            return arg2;
        label_4:
            ++arg2;
        }

        return arg3;
    }

    public static int a(String arg1, int arg2, int arg3, char arg4) {
        while(arg2 < arg3) {
            if(arg1.charAt(arg2) == arg4) {
                return arg2;
            }

            ++arg2;
        }

        return arg3;
    }

    public static int a(String arg2, int arg3, int arg4, String arg5) {
        while(arg3 < arg4) {
            if(arg5.indexOf(arg2.charAt(arg3)) != -1) {
                return arg3;
            }

            ++arg3;
        }

        return arg4;
    }

    public static f a(f arg1) {
        try {
            return f.a(MessageDigest.getInstance("SHA-1").digest(arg1.f()));
        }
        catch(NoSuchAlgorithmException v1) {
            throw new AssertionError(v1);
        }
    }

    public static String a(String arg2) {
        String v0 = null;
        try {
            arg2 = IDN.toASCII(arg2).toLowerCase(Locale.US);
            if(arg2.isEmpty()) {
                return v0;
            }

            if(!l.c(arg2)) {
                return arg2;
            }
        }
        catch(IllegalArgumentException ) {
            return v0;
        }

        return v0;
    }

    public static List a(List arg1) {
        return Collections.unmodifiableList(new ArrayList(((Collection)arg1)));
    }

    public static List a(Object[] arg0) {
        // Method was not decompiled
    }

    private static List a(Object[] arg9, Object[] arg10) {
        ArrayList v0 = new ArrayList();
        int v1 = arg9.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = arg9[v3];
            int v5 = arg10.length;
            int v6 = 0;
            while(v6 < v5) {
                Object v7 = arg10[v6];
                if(v4.equals(v7)) {
                    ((List)v0).add(v7);
                }
                else {
                    ++v6;
                    continue;
                }

                break;
            }
        }

        return ((List)v0);
    }

    public static void a(long arg5, long arg7, long arg9) {
        if((arg7 | arg9) >= 0 && arg7 <= arg5 && arg5 - arg7 >= arg9) {
            return;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    public static void a(Closeable arg0, Closeable arg1) {
        try {
            arg0.close();
            v0 = null;
        }
        catch(Throwable v0) {
        }

        try {
            arg1.close();
        }
        catch(Throwable v1) {
            if(v0 != null) {
                goto label_9;
            }

            v0 = v1;
        }

    label_9:
        if(v0 == null) {
            return;
        }

        if(!(v0 instanceof IOException)) {
            if(!(v0 instanceof RuntimeException)) {
                if((v0 instanceof Error)) {
                    throw v0;
                }

                throw new AssertionError(v0);
            }

            throw v0;
        }

        throw v0;
    }

    public static boolean a(AssertionError arg1) {
        boolean v1 = arg1.getCause() == null || arg1.getMessage() == null || !arg1.getMessage().contains("getsockname failed") ? false : true;
        return v1;
    }

    public static boolean a(Object arg0, Object arg1) {
        boolean v0;
        if(arg0 != arg1) {
            if(arg0 != null && (arg0.equals(arg1))) {
                goto label_7;
            }

            v0 = false;
        }
        else {
        label_7:
            v0 = true;
        }

        return v0;
    }

    public static boolean a(String[] arg0, String arg1) {
        return Arrays.asList(((Object[])arg0)).contains(arg1);
    }

    public static Object[] a(Class arg0, Object[] arg1, Object[] arg2) {
        List v1 = l.a(arg1, arg2);
        return v1.toArray(Array.newInstance(arg0, v1.size()));
    }

    public static boolean b(String arg1) {
        return l.e.matcher(((CharSequence)arg1)).matches();
    }

    public static boolean b(s arg11, int arg12, TimeUnit arg13) {
        long v0 = System.nanoTime();
        long v3 = 9223372036854775807L;
        long v5 = arg11.a().e_() ? arg11.a().d() - v0 : v3;
        arg11.a().a(Math.min(v5, arg13.toNanos(((long)arg12))) + v0);
        try {
            c v12_1 = new c();
            while(arg11.a(v12_1, 8192) != -1) {
                v12_1.r();
            }
        }
        catch(Throwable v12) {
            goto label_34;
        }
        catch(InterruptedIOException ) {
            goto label_42;
        }

        if(v5 == v3) {
            arg11.a().f();
        }
        else {
            arg11.a().a(v0 + v5);
        }

        return 1;
    label_42:
        if(v5 == v3) {
            arg11.a().f();
        }
        else {
            arg11.a().a(v0 + v5);
        }

        return 0;
    label_34:
        if(v5 == v3) {
            arg11.a().f();
        }
        else {
            arg11.a().a(v0 + v5);
        }

        throw v12;
    }

    public static int b(String arg1, int arg2, int arg3) {
        --arg3;
        while(arg3 >= arg2) {
            switch(arg1.charAt(arg3)) {
                case 9: 
                case 10: 
                case 12: 
                case 13: 
                case 32: {
                    goto label_6;
                }
            }

            return arg3 + 1;
        label_6:
            --arg3;
        }

        return arg2;
    }

    public static f b(f arg1) {
        try {
            return f.a(MessageDigest.getInstance("SHA-256").digest(arg1.f()));
        }
        catch(NoSuchAlgorithmException v1) {
            throw new AssertionError(v1);
        }
    }

    public static String[] b(String[] arg3, String arg4) {
        String[] v0 = new String[arg3.length + 1];
        System.arraycopy(arg3, 0, v0, 0, arg3.length);
        v0[v0.length - 1] = arg4;
        return v0;
    }

    private static boolean c(String arg5) {
        int v1 = 0;
        while(true) {
            if(v1 >= arg5.length()) {
                return 0;
            }

            int v2 = arg5.charAt(v1);
            if(v2 > 31) {
                if(v2 >= 127) {
                }
                else if(" #%/:?@[\\]".indexOf(v2) != -1) {
                    return 1;
                }
                else {
                    ++v1;
                    continue;
                }
            }

            return 1;
        }

        return 1;
    }

    public static String c(String arg0, int arg1, int arg2) {
        arg1 = l.a(arg0, arg1, arg2);
        return arg0.substring(arg1, l.b(arg0, arg1, arg2));
    }
}

