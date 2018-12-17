package okhttp3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.c.d;
import okhttp3.internal.c;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

public final class l {
    private static final Pattern a;
    private static final Pattern b;
    private static final Pattern c;
    private static final Pattern d;
    private final String e;
    private final String f;
    private final long g;
    private final String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    static {
        l.a = Pattern.compile("(\\d{2,4})[^\\d]*");
        l.b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
        l.c = Pattern.compile("(\\d{1,2})[^\\d]*");
        l.d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    }

    private l(String arg1, String arg2, long arg3, String arg5, String arg6, boolean arg7, boolean arg8, boolean arg9, boolean arg10) {
        super();
        this.e = arg1;
        this.f = arg2;
        this.g = arg3;
        this.h = arg5;
        this.i = arg6;
        this.j = arg7;
        this.k = arg8;
        this.m = arg9;
        this.l = arg10;
    }

    private static int a(String arg3, int arg4, int arg5, boolean arg6) {
        while(arg4 < arg5) {
            int v0 = arg3.charAt(arg4);
            if(v0 < 32 && v0 != 9) {
                goto label_26;
            }
            else if(v0 < 127) {
                if(v0 >= 48 && v0 <= 57) {
                    goto label_26;
                }

                if(v0 >= 97 && v0 <= 122) {
                    goto label_26;
                }

                if(v0 >= 65 && v0 <= 90) {
                    goto label_26;
                }

                if(v0 == 58) {
                    goto label_26;
                }

                v0 = 0;
            }
            else {
            label_26:
                v0 = 1;
            }

            if(v0 == ((((int)arg6)) ^ 1)) {
                return arg4;
            }

            ++arg4;
        }

        return arg5;
    }

    private static long a(String arg6) {
        long v0 = -9223372036854775808L;
        try {
            long v2_1 = Long.parseLong(arg6);
            if(v2_1 > 0) {
                return v2_1;
            }
        }
        catch(NumberFormatException v2) {
            if(arg6.matches("-?\\d+")) {
                if(arg6.startsWith("-")) {
                }
                else {
                    v0 = 9223372036854775807L;
                }

                return v0;
            }
            else {
                throw v2;
            }

            return v0;
        }

        return v0;
    }

    private static long a(String arg12, int arg13, int arg14) {
        int v9;
        arg13 = l.a(arg12, arg13, arg14, false);
        Matcher v1 = l.d.matcher(((CharSequence)arg12));
        int v2 = -1;
        int v3 = -1;
        int v4 = -1;
        int v5 = -1;
        int v6 = -1;
        int v7 = -1;
        int v8 = -1;
        while(true) {
            v9 = 2;
            if(arg13 >= arg14) {
                break;
            }

            int v11 = l.a(arg12, arg13 + 1, arg14, true);
            v1.region(arg13, v11);
            if(v3 != v2 || !v1.usePattern(l.d).matches()) {
                if(v5 == v2 && (v1.usePattern(l.c).matches())) {
                    v5 = Integer.parseInt(v1.group(1));
                    goto label_64;
                }

                if(v6 == v2 && (v1.usePattern(l.b).matches())) {
                    v6 = l.b.pattern().indexOf(v1.group(1).toLowerCase(Locale.US)) / 4;
                    goto label_64;
                }

                if(v4 != v2) {
                    goto label_64;
                }

                if(!v1.usePattern(l.a).matches()) {
                    goto label_64;
                }

                v4 = Integer.parseInt(v1.group(1));
            }
            else {
                arg13 = Integer.parseInt(v1.group(1));
                v3 = Integer.parseInt(v1.group(v9));
                v8 = Integer.parseInt(v1.group(3));
                v7 = v3;
                v3 = arg13;
            }

        label_64:
            arg13 = l.a(arg12, v11 + 1, arg14, false);
        }

        if(v4 >= 70 && v4 <= 99) {
            v4 += 1900;
        }

        if(v4 >= 0 && v4 <= 69) {
            v4 += 2000;
        }

        if(v4 >= 1601) {
            if(v6 != v2) {
                if(v5 >= 1 && v5 <= 31) {
                    if(v3 >= 0 && v3 <= 23) {
                        if(v7 >= 0) {
                            int v12 = 59;
                            if(v7 <= v12) {
                                if(v8 >= 0 && v8 <= v12) {
                                    GregorianCalendar v12_1 = new GregorianCalendar(c.g);
                                    ((Calendar)v12_1).setLenient(false);
                                    ((Calendar)v12_1).set(1, v4);
                                    ((Calendar)v12_1).set(v9, v6 - 1);
                                    ((Calendar)v12_1).set(5, v5);
                                    ((Calendar)v12_1).set(11, v3);
                                    ((Calendar)v12_1).set(12, v7);
                                    ((Calendar)v12_1).set(13, v8);
                                    ((Calendar)v12_1).set(14, 0);
                                    return ((Calendar)v12_1).getTimeInMillis();
                                }

                                throw new IllegalArgumentException();
                            }
                        }

                        throw new IllegalArgumentException();
                    }

                    throw new IllegalArgumentException();
                }

                throw new IllegalArgumentException();
            }

            throw new IllegalArgumentException();
        }

        throw new IllegalArgumentException();
    }

    public static List a(t arg4, s arg5) {
        List v5 = arg5.b("Set-Cookie");
        int v0 = v5.size();
        ArrayList v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            l v3 = l.a(arg4, v5.get(v2));
            if(v3 == null) {
            }
            else {
                if(v1 == null) {
                    v1 = new ArrayList();
                }

                ((List)v1).add(v3);
            }
        }

        List v4 = v1 != null ? Collections.unmodifiableList(((List)v1)) : Collections.emptyList();
        return v4;
    }

    @Nullable public static l a(t arg2, String arg3) {
        return l.a(System.currentTimeMillis(), arg2, arg3);
    }

    @Nullable static l a(long arg25, t arg27, String arg28) {
        String v16_1;
        l v1;
        String v15;
        long v13;
        String v2 = arg28;
        int v3 = arg28.length();
        char v4 = ';';
        int v6 = c.a(v2, 0, v3, v4);
        char v7 = '=';
        int v8 = c.a(v2, 0, v6, v7);
        l v9 = null;
        if(v8 == v6) {
            return v9;
        }

        String v11 = c.c(v2, 0, v8);
        if(!v11.isEmpty()) {
            int v12 = -1;
            if(c.b(v11) != v12) {
            }
            else {
                String v8_1 = c.c(v2, v8 + 1, v6);
                if(c.b(v8_1) != v12) {
                    return v9;
                }
                else {
                    ++v6;
                    long v12_1 = -1;
                    long v14 = 253402300799999L;
                    String v10 = ((String)v9);
                    String v21 = v10;
                    long v16 = v12_1;
                    long v22 = v14;
                    boolean v18 = false;
                    boolean v19 = false;
                    boolean v20 = true;
                    boolean v24 = false;
                    while(v6 < v3) {
                        int v9_1 = c.a(v2, v6, v3, v4);
                        int v4_1 = c.a(v2, v6, v9_1, v7);
                        String v6_1 = c.c(v2, v6, v4_1);
                        String v4_2 = v4_1 < v9_1 ? c.c(v2, v4_1 + 1, v9_1) : "";
                        if(v6_1.equalsIgnoreCase("expires")) {
                            try {
                                v22 = l.a(v4_2, 0, v4_2.length());
                                goto label_49;
                            }
                            catch(IllegalArgumentException ) {
                            }
                        }
                        else if(v6_1.equalsIgnoreCase("max-age")) {
                            try {
                                v16 = l.a(v4_2);
                            }
                            catch(IllegalArgumentException ) {
                                goto label_78;
                            }

                        label_49:
                            v24 = true;
                        }
                        else if(v6_1.equalsIgnoreCase("domain")) {
                            try {
                                v10 = l.b(v4_2);
                                v20 = false;
                            }
                            catch(IllegalArgumentException ) {
                            }
                        }
                        else if(v6_1.equalsIgnoreCase("path")) {
                            v21 = v4_2;
                        }
                        else if(v6_1.equalsIgnoreCase("secure")) {
                            v18 = true;
                        }
                        else if(v6_1.equalsIgnoreCase("httponly")) {
                            v19 = true;
                        }

                    label_78:
                        v6 = v9_1 + 1;
                        v4 = ';';
                        v7 = '=';
                    }

                    long v2_1 = -9223372036854775808L;
                    if(v16 == v2_1) {
                    label_85:
                        v13 = v2_1;
                        goto label_101;
                    label_98:
                        v13 = v14;
                        goto label_101;
                    label_100:
                        v13 = v22;
                    }
                    else if(v16 != v12_1) {
                        if(v16 <= 9223372036854775L) {
                            v16 *= 1000;
                        }
                        else {
                            v16 = 9223372036854775807L;
                        }

                        v2_1 = arg25 + v16;
                        if(v2_1 >= arg25 && v2_1 <= v14) {
                            goto label_85;
                        }

                        goto label_98;
                    }
                    else {
                        goto label_100;
                    }

                label_101:
                    String v0 = arg27.f();
                    if(v10 == null) {
                        v15 = v0;
                        v1 = null;
                    }
                    else if(!l.a(v0, v10)) {
                        return null;
                    }
                    else {
                        v1 = null;
                        v15 = v10;
                    }

                    if(v0.length() != v15.length() && PublicSuffixDatabase.a().a(v15) == null) {
                        return v1;
                    }

                    String v9_2 = v21;
                    if(v9_2 == null || !v9_2.startsWith("/")) {
                        v0 = arg27.h();
                        int v1_1 = v0.lastIndexOf(47);
                        v0 = v1_1 != 0 ? v0.substring(0, v1_1) : "/";
                        v16_1 = v0;
                    }
                    else {
                        v16_1 = v9_2;
                    }

                    return new l(v11, v8_1, v13, v15, v16_1, v18, v19, v20, v24);
                }
            }
        }

        return v9;
    }

    private static boolean a(String arg2, String arg3) {
        if(arg2.equals(arg3)) {
            return 1;
        }

        if((arg2.endsWith(arg3)) && arg2.charAt(arg2.length() - arg3.length() - 1) == 46 && !c.c(arg2)) {
            return 1;
        }

        return 0;
    }

    public String a() {
        return this.e;
    }

    String a(boolean arg7) {
        String v1;
        StringBuilder v0 = new StringBuilder();
        v0.append(this.e);
        v0.append('=');
        v0.append(this.f);
        if(this.l) {
            if(this.g == -9223372036854775808L) {
                v1 = "; max-age=0";
            }
            else {
                v0.append("; expires=");
                v1 = d.a(new Date(this.g));
            }

            v0.append(v1);
        }

        if(!this.m) {
            v0.append("; domain=");
            if(arg7) {
                v0.append(".");
            }

            v0.append(this.h);
        }

        v0.append("; path=");
        v0.append(this.i);
        if(this.j) {
            v0.append("; secure");
        }

        if(this.k) {
            v0.append("; httponly");
        }

        return v0.toString();
    }

    private static String b(String arg1) {
        if(!arg1.endsWith(".")) {
            if(arg1.startsWith(".")) {
                arg1 = arg1.substring(1);
            }

            arg1 = c.a(arg1);
            if(arg1 != null) {
                return arg1;
            }

            throw new IllegalArgumentException();
        }

        throw new IllegalArgumentException();
    }

    public String b() {
        return this.f;
    }

    public boolean equals(@Nullable Object arg7) {
        boolean v1 = false;
        if(!(arg7 instanceof l)) {
            return 0;
        }

        if((((l)arg7).e.equals(this.e)) && (((l)arg7).f.equals(this.f)) && (((l)arg7).h.equals(this.h)) && (((l)arg7).i.equals(this.i)) && ((l)arg7).g == this.g && ((l)arg7).j == this.j && ((l)arg7).k == this.k && ((l)arg7).l == this.l && ((l)arg7).m == this.m) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return ((((((((527 + this.e.hashCode()) * 31 + this.f.hashCode()) * 31 + this.h.hashCode()) * 31 + this.i.hashCode()) * 31 + (((int)(this.g ^ this.g >>> 32)))) * 31 + (this.j ^ 1)) * 31 + (this.k ^ 1)) * 31 + (this.l ^ 1)) * 31 + (this.m ^ 1);
    }

    public String toString() {
        return this.a(false);
    }
}

