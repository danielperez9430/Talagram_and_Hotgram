package com.google.ads.interactivemedia.v3.internal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class hj extends gp {
    final class com.google.ads.interactivemedia.v3.internal.hj$1 implements gq {
        com.google.ads.interactivemedia.v3.internal.hj$1() {
            super();
        }

        public gp a(fz arg1, hw arg2) {
            gp v1_1;
            if(arg2.a() == Date.class) {
                hj v1 = new hj();
            }
            else {
                v1_1 = null;
            }

            return v1_1;
        }
    }

    public static final gq a;
    private final DateFormat b;
    private final DateFormat c;

    static {
        hj.a = new com.google.ads.interactivemedia.v3.internal.hj$1();
    }

    public hj() {
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
                    v0 = hv.a(arg3, new ParsePosition(0));
                    goto label_15;
                }
                catch(Throwable v3) {
                label_21:
                    __monitor_exit(this);
                    throw v3;
                }
                catch(ParseException v0_1) {
                    try {
                        throw new gn(arg3, ((Throwable)v0_1));
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

    public Date a(hx arg3) {
        if(arg3.f() == hy.i) {
            arg3.j();
            return null;
        }

        return this.a(arg3.h());
    }

    public void a(hz arg2, Date arg3) {
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

    public Object read(hx arg1) {
        return this.a(arg1);
    }

    public void write(hz arg1, Object arg2) {
        this.a(arg1, ((Date)arg2));
    }
}

