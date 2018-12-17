package okhttp3.internal;

import e.e;
import e.f;
import e.s;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.ab;
import okhttp3.ad;
import okhttp3.t;

public final class c {
    final class okhttp3.internal.c$1 implements Comparator {
        okhttp3.internal.c$1() {
            super();
        }

        public int a(String arg1, String arg2) {
            return arg1.compareTo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((String)arg1), ((String)arg2));
        }
    }

    public static final byte[] a;
    public static final String[] b;
    public static final ad c;
    public static final ab d;
    public static final Charset e;
    public static final Charset f;
    public static final TimeZone g;
    public static final Comparator h;
    private static final f i;
    private static final f j;
    private static final f k;
    private static final f l;
    private static final f m;
    private static final Charset n;
    private static final Charset o;
    private static final Charset p;
    private static final Charset q;
    private static final Pattern r;

    static {
        c.a = new byte[0];
        c.b = new String[0];
        c.c = ad.a(null, c.a);
        c.d = ab.a(null, c.a);
        c.i = f.b("efbbbf");
        c.j = f.b("feff");
        c.k = f.b("fffe");
        c.l = f.b("0000ffff");
        c.m = f.b("ffff0000");
        c.e = Charset.forName("UTF-8");
        c.f = Charset.forName("ISO-8859-1");
        c.n = Charset.forName("UTF-16BE");
        c.o = Charset.forName("UTF-16LE");
        c.p = Charset.forName("UTF-32BE");
        c.q = Charset.forName("UTF-32LE");
        c.g = TimeZone.getTimeZone("GMT");
        c.h = new okhttp3.internal.c$1();
        c.r = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
    }

    public static List a(List arg1) {
        return Collections.unmodifiableList(new ArrayList(((Collection)arg1)));
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

    public static void a(long arg5, long arg7, long arg9) {
        if((arg7 | arg9) >= 0 && arg7 <= arg5 && arg5 - arg7 >= arg9) {
            return;
        }

        throw new ArrayIndexOutOfBoundsException();
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

    public static Charset a(e arg3, Charset arg4) {
        long v1 = 0;
        if(arg3.a(v1, c.i)) {
            arg3.h(((long)c.i.g()));
            return c.e;
        }

        if(arg3.a(v1, c.j)) {
            arg3.h(((long)c.j.g()));
            return c.n;
        }

        if(arg3.a(v1, c.k)) {
            arg3.h(((long)c.k.g()));
            return c.o;
        }

        if(arg3.a(v1, c.l)) {
            arg3.h(((long)c.l.g()));
            return c.p;
        }

        if(arg3.a(v1, c.m)) {
            arg3.h(((long)c.m.g()));
            return c.q;
        }

        return arg4;
    }

    public static int a(char arg2) {
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

    public static int a(String arg6, long arg7, TimeUnit arg9) {
        StringBuilder v8;
        long v0 = 0;
        if(arg7 >= v0) {
            if(arg9 != null) {
                long v2 = arg9.toMillis(arg7);
                if(v2 <= 2147483647) {
                    if(v2 == v0) {
                        if(arg7 <= v0) {
                        }
                        else {
                            v8 = new StringBuilder();
                            v8.append(arg6);
                            v8.append(" too small.");
                            throw new IllegalArgumentException(v8.toString());
                        }
                    }

                    return ((int)v2);
                }

                v8 = new StringBuilder();
                v8.append(arg6);
                v8.append(" too large.");
                throw new IllegalArgumentException(v8.toString());
            }

            throw new NullPointerException("unit == null");
        }

        v8 = new StringBuilder();
        v8.append(arg6);
        v8.append(" < 0");
        throw new IllegalArgumentException(v8.toString());
    }

    public static int a(Comparator arg3, String[] arg4, String arg5) {
        int v0 = arg4.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg3.compare(arg4[v1], arg5) == 0) {
                return v1;
            }
        }

        return -1;
    }

    public static AssertionError a(String arg1, Exception arg2) {
        AssertionError v0 = new AssertionError(arg1);
        try {
            v0.initCause(((Throwable)arg2));
            return v0;
        }
        catch(IllegalStateException ) {
            return v0;
        }
    }

    public static String a(String arg3) {
        String v1 = null;
        if(arg3.contains(":")) {
            InetAddress v0 = !arg3.startsWith("[") || !arg3.endsWith("]") ? c.d(arg3, 0, arg3.length()) : c.d(arg3, 1, arg3.length() - 1);
            if(v0 == null) {
                return v1;
            }

            byte[] v0_1 = v0.getAddress();
            if(v0_1.length == 16) {
                return c.a(v0_1);
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Invalid IPv6 address: \'");
            v1_1.append(arg3);
            v1_1.append("\'");
            throw new AssertionError(v1_1.toString());
        }

        try {
            arg3 = IDN.toASCII(arg3).toLowerCase(Locale.US);
            if(arg3.isEmpty()) {
                return v1;
            }

            if(!c.d(arg3)) {
                return arg3;
            }
        }
        catch(IllegalArgumentException ) {
            return v1;
        }

        return v1;
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
            if(v5 > v2 && v5 >= 4) {
                v3 = v1;
                v2 = v5;
            }

            v1 = v4 + 2;
        }

        e.c v1_1 = new e.c();
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

            v1_1.j(((long)((arg8[v0] & 255) << 8 | arg8[v0 + 1] & 255)));
            v0 += 2;
        }

        return v1_1.p();
    }

    public static String a(String arg1, Object[] arg2) {
        return String.format(Locale.US, arg1, arg2);
    }

    public static String a(t arg2, boolean arg3) {
        String v0_1 = arg2.f().contains(":") ? "[" + arg2.f() + "]" : arg2.f();
        if((arg3) || arg2.g() != t.a(arg2.b())) {
            v0_1 = v0_1 + ":" + arg2.g();
        }

        return v0_1;
    }

    public static List a(Object[] arg0) {
        // Method was not decompiled
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
            if(c.a(v1_1)) {
                return;
            }

            throw v1_1;
        }
    }

    public static boolean a(AssertionError arg1) {
        boolean v1 = arg1.getCause() == null || arg1.getMessage() == null || !arg1.getMessage().contains("getsockname failed") ? false : true;
        return v1;
    }

    public static boolean a(s arg0, int arg1, TimeUnit arg2) {
        try {
            return c.b(arg0, arg1, arg2);
        }
        catch(IOException ) {
            return 0;
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

    public static String[] a(Comparator arg8, String[] arg9, String[] arg10) {
        ArrayList v0 = new ArrayList();
        int v1 = arg9.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            String v4 = arg9[v3];
            int v5 = arg10.length;
            int v6 = 0;
            while(v6 < v5) {
                if(arg8.compare(v4, arg10[v6]) == 0) {
                    ((List)v0).add(v4);
                }
                else {
                    ++v6;
                    continue;
                }

                break;
            }
        }

        return ((List)v0).toArray(new String[((List)v0).size()]);
    }

    public static String[] a(String[] arg3, String arg4) {
        String[] v0 = new String[arg3.length + 1];
        System.arraycopy(arg3, 0, v0, 0, arg3.length);
        v0[v0.length - 1] = arg4;
        return v0;
    }

    public static boolean b(s arg11, int arg12, TimeUnit arg13) {
        long v0 = System.nanoTime();
        long v3 = 9223372036854775807L;
        long v5 = arg11.a().l_() ? arg11.a().d() - v0 : v3;
        arg11.a().a(Math.min(v5, arg13.toNanos(((long)arg12))) + v0);
        try {
            e.c v12_1 = new e.c();
            while(arg11.a(v12_1, 8192) != -1) {
                v12_1.s();
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

    public static int b(String arg4) {
        int v0 = arg4.length();
        int v1 = 0;
        while(true) {
            if(v1 >= v0) {
                return -1;
            }

            int v2 = arg4.charAt(v1);
            if(v2 > 31) {
                if(v2 >= 127) {
                }
                else {
                    ++v1;
                    continue;
                }
            }

            return v1;
        }

        return v1;
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

    public static boolean b(Comparator arg7, String[] arg8, String[] arg9) {
        if(arg8 != null && arg9 != null && arg8.length != 0) {
            if(arg9.length == 0) {
            }
            else {
                int v1 = arg8.length;
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    String v3 = arg8[v2];
                    int v4 = arg9.length;
                    int v5 = 0;
                    while(true) {
                        if(v5 >= v4) {
                            break;
                        }
                        else if(arg7.compare(v3, arg9[v5]) == 0) {
                            return 1;
                        }
                        else {
                            ++v5;
                            continue;
                        }

                        return 0;
                    }
                }
            }
        }

        return 0;
    }

    public static String c(String arg0, int arg1, int arg2) {
        arg1 = c.a(arg0, arg1, arg2);
        return arg0.substring(arg1, c.b(arg0, arg1, arg2));
    }

    public static boolean c(String arg1) {
        return c.r.matcher(((CharSequence)arg1)).matches();
    }

    @Nullable private static InetAddress d(String arg10, int arg11, int arg12) {
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
                            else if(!c.a(arg10, v5, arg12, v0, v3 - 2)) {
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
                        v8 = c.a(arg10.charAt(arg11));
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

    private static boolean d(String arg5) {
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
}

