package com.persianswitch.sdk.base.utils.pdate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateTime {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final DateFormat g;

    private DateTime(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, DateFormat arg7) {
        super();
        this.a = arg1;
        this.c = arg3;
        this.b = arg2;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.g = arg7;
    }

    public static DateTime a(Date arg7) {
        Calendar v0 = GregorianCalendar.getInstance();
        v0.setTime(arg7);
        return DateTime.a(v0.get(1), v0.get(2) + 1, v0.get(5), v0.get(10), v0.get(12), v0.get(13)).a(DateFormat.c);
    }

    public int a() {
        return this.a;
    }

    public static DateTime a(int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
        return new DateTime(arg9, arg10, arg11, arg12, arg13, arg14, DateFormat.a);
    }

    public DateTime a(DateFormat arg10) {
        return new DateTime(this.a, this.b, this.c, this.d, this.e, this.f, arg10);
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((DateTime)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.a != ((DateTime)arg5).a) {
                return 0;
            }
            else if(this.b != ((DateTime)arg5).b) {
                return 0;
            }
            else if(this.c != ((DateTime)arg5).c) {
                return 0;
            }
            else if(this.d != ((DateTime)arg5).d) {
                return 0;
            }
            else if(this.e != ((DateTime)arg5).e) {
                return 0;
            }
            else {
                if(this.f == ((DateTime)arg5).f) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    public int f() {
        return this.f;
    }

    public int hashCode() {
        return ((((this.a * 31 + this.b) * 31 + this.c) * 31 + this.d) * 31 + this.e) * 31 + this.f;
    }

    public String toString() {
        return String.format(Locale.US, "DateTime{%04d/%02d/%02d %02d:%02d:%02d}", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f));
    }
}

