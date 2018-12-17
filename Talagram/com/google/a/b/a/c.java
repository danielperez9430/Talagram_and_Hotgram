package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.f;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class c extends v {
    final class com.google.a.b.a.c$1 implements w {
        com.google.a.b.a.c$1() {
            super();
        }

        public v create(f arg1, a arg2) {
            v v1_1;
            if(arg2.a() == Date.class) {
                c v1 = new c();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final w a;
    private final DateFormat b;
    private final DateFormat c;

    static {
        c.a = new com.google.a.b.a.c$1();
    }

    public c() {
        super();
        this.b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.c = DateFormat.getDateTimeInstance(2, 2);
    }

    private Date a(String arg3) {
        Date v0;
        __monitor_enter(this);
        try {
            v0 = this.c.parse(arg3);
        }
        catch(Throwable v3) {
        }
        catch(ParseException ) {
            try {
                v0 = this.b.parse(arg3);
                goto label_9;
            }
            catch(Throwable v3) {
            }
            catch(ParseException ) {
                try {
                    v0 = com.google.a.b.a.a.a.a(arg3, new ParsePosition(0));
                    goto label_15;
                }
                catch(Throwable v3) {
                label_21:
                    __monitor_exit(this);
                    throw v3;
                }
                catch(ParseException v0_1) {
                    try {
                        throw new t(arg3, ((Throwable)v0_1));
                    }
                    catch(Throwable v3) {
                        goto label_21;
                    }

                label_15:
                    __monitor_exit(this);
                    return v0;
                label_9:
                    __monitor_exit(this);
                    return v0;
                }
            }
        }

        __monitor_exit(this);
        return v0;
    }

    public Date a(com.google.a.d.a arg3) {
        if(arg3.f() == b.i) {
            arg3.j();
            return null;
        }

        return this.a(arg3.h());
    }

    public void a(com.google.a.d.c arg2, Date arg3) {
        __monitor_enter(this);
        if(arg3 == null) {
            try {
                arg2.f();
            }
            catch(Throwable v2) {
                goto label_12;
            }

            __monitor_exit(this);
            return;
        }

        try {
            arg2.b(this.b.format(arg3));
        }
        catch(Throwable v2) {
        label_12:
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public Object read(com.google.a.d.a arg1) {
        return this.a(arg1);
    }

    public void write(com.google.a.d.c arg1, Object arg2) {
        this.a(arg1, ((Date)arg2));
    }
}

