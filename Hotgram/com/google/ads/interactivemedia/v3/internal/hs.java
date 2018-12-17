package com.google.ads.interactivemedia.v3.internal;

public final class hs extends gp {
    class com.google.ads.interactivemedia.v3.internal.hs$1 {
    }

    final class a implements gd, gl {
        a(hs arg1, com.google.ads.interactivemedia.v3.internal.hs$1 arg2) {
            this(arg1);
        }

        private a(hs arg1) {
            this.a = arg1;
            super();
        }
    }

    final class b implements gq {
        private final hw a;
        private final boolean b;
        private final Class c;
        private final gm d;
        private final ge e;

        b(Object arg3, hw arg4, boolean arg5, Class arg6) {
            Object v0;
            super();
            ge v1 = null;
            if((arg3 instanceof gm)) {
                v0 = arg3;
            }
            else {
                gm v0_1 = ((gm)v1);
            }

            this.d = ((gm)v0);
            if((arg3 instanceof ge)) {
                Object v1_1 = arg3;
            }

            this.e = v1;
            boolean v3 = this.d != null || this.e != null ? true : false;
            gw.a(v3);
            this.a = arg4;
            this.b = arg5;
            this.c = arg6;
        }

        public gp a(fz arg8, hw arg9) {
            hs v0_1;
            boolean v0;
            if(this.a != null) {
                if(!this.a.equals(arg9) && (!this.b || this.a.b() != arg9.a())) {
                    v0 = false;
                    goto label_19;
                }

                v0 = true;
            }
            else {
                v0 = this.c.isAssignableFrom(arg9.a());
            }

        label_19:
            if(v0) {
                v0_1 = new hs(this.d, this.e, arg8, arg9, this);
            }
            else {
                gp v0_2 = null;
            }

            return ((gp)v0_1);
        }
    }

    private final gm a;
    private final ge b;
    private final fz c;
    private final hw d;
    private final gq e;
    private final a f;
    private gp g;

    public hs(gm arg3, ge arg4, fz arg5, hw arg6, gq arg7) {
        super();
        this.f = new a(this, null);
        this.a = arg3;
        this.b = arg4;
        this.c = arg5;
        this.d = arg6;
        this.e = arg7;
    }

    public static gq a(hw arg3, Object arg4) {
        return new b(arg4, arg3, false, null);
    }

    private gp a() {
        gp v0 = this.g;
        if(v0 != null) {
        }
        else {
            v0 = this.c.a(this.e, this.d);
            this.g = v0;
        }

        return v0;
    }

    public static gq b(hw arg3, Object arg4) {
        boolean v0 = arg3.b() == arg3.a() ? true : false;
        return new b(arg4, arg3, v0, null);
    }

    public Object read(hx arg4) {
        if(this.b == null) {
            return this.a().read(arg4);
        }

        gf v4 = hf.a(arg4);
        if(v4.j()) {
            return null;
        }

        try {
            return this.b.b(v4, this.d.b(), this.f);
        }
        catch(Exception v4_1) {
            throw new gj(((Throwable)v4_1));
        }
        catch(gj v4_2) {
            throw v4_2;
        }
    }

    public void write(hz arg4, Object arg5) {
        if(this.a == null) {
            this.a().write(arg4, arg5);
            return;
        }

        if(arg5 == null) {
            arg4.f();
            return;
        }

        hf.a(this.a.a(arg5, this.d.b(), this.f), arg4);
    }
}

