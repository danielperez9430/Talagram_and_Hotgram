package com.mohamadamin.persianmaterialdatetimepicker.a;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class b extends GregorianCalendar {
    private int a;
    private int b;
    private int c;
    private String d;

    public b() {
        super();
        this.d = "/";
        this.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public b(long arg2) {
        super();
        this.d = "/";
        this.setTimeInMillis(arg2);
    }

    private long a(long arg5) {
        return arg5 * 86400000 - 210866803200000L + d.a(((double)(this.getTimeInMillis() + 210866803200000L)), 86400000);
    }

    private String a(int arg3) {
        String v3 = arg3 < 9 ? "0" + arg3 : String.valueOf(arg3);
        return v3;
    }

    protected void a() {
        long v0 = d.a((((long)Math.floor(((double)(this.getTimeInMillis() + 210866803200000L))))) / 86400000);
        long v2 = v0 >> 16;
        int v4 = (((int)(65280 & v0))) >> 8;
        int v0_1 = ((int)(v0 & 255));
        if(v2 > 0) {
        }
        else {
            --v2;
        }

        this.a = ((int)v2);
        this.b = v4;
        this.c = v0_1;
    }

    public void a(int arg2, int arg3, int arg4) {
        this.a = arg2;
        this.b = arg3 + 1;
        this.c = arg4;
        arg2 = this.a > 0 ? this.a : this.a + 1;
        long v2 = ((long)arg2);
        this.setTimeInMillis(this.a(d.a(v2, this.b - 1, this.c)));
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public String d() {
        return c.a[this.b];
    }

    public int e() {
        return this.c;
    }

    public boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    public String f() {
        int v1 = this.get(7);
        if(v1 == 7) {
            goto label_28;
        }

        switch(v1) {
            case 1: {
                goto label_24;
            }
            case 2: {
                goto label_20;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_12;
            }
            case 5: {
                goto label_8;
            }
        }

        return c.b[6];
    label_20:
        return c.b[2];
    label_24:
        return c.b[1];
    label_8:
        return c.b[5];
    label_12:
        return c.b[4];
    label_16:
        return c.b[3];
    label_28:
        return c.b[0];
    }

    public String g() {
        return this.f() + "  " + this.c + "  " + this.d() + "  " + this.a;
    }

    public String h() {
        return "" + this.a(this.a) + this.d + this.a(this.c() + 1) + this.d + this.a(this.c);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void set(int arg1, int arg2) {
        super.set(arg1, arg2);
        this.a();
    }

    public void setTimeInMillis(long arg1) {
        super.setTimeInMillis(arg1);
        this.a();
    }

    public void setTimeZone(TimeZone arg1) {
        super.setTimeZone(arg1);
        this.a();
    }

    public String toString() {
        String v0 = super.toString();
        return v0.substring(0, v0.length() - 1) + ",PersianDate=" + this.h() + "]";
    }
}

