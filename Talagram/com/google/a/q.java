package com.google.a;

import com.google.a.b.a;
import com.google.a.b.f;
import java.math.BigInteger;

public final class q extends l {
    private static final Class[] a;
    private Object b;

    static {
        q.a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    }

    public q(Boolean arg1) {
        super();
        this.a(arg1);
    }

    public q(Number arg1) {
        super();
        this.a(arg1);
    }

    public q(String arg1) {
        super();
        this.a(arg1);
    }

    public Number a() {
        f v0;
        if((this.b instanceof String)) {
            v0 = new f(this.b);
        }
        else {
            Object v0_1 = this.b;
        }

        return ((Number)v0);
    }

    void a(Object arg2) {
        String v2;
        if((arg2 instanceof Character)) {
            v2 = String.valueOf(((Character)arg2).charValue());
        }
        else {
            boolean v0 = ((arg2 instanceof Number)) || (q.b(arg2)) ? true : false;
            a.a(v0);
        }

        this.b = v2;
    }

    private static boolean a(q arg2) {
        boolean v1 = false;
        if((arg2.b instanceof Number)) {
            Object v2 = arg2.b;
            if(!(v2 instanceof BigInteger) && !(v2 instanceof Long) && !(v2 instanceof Integer) && !(v2 instanceof Short) && !(v2 instanceof Byte)) {
                return v1;
            }

            v1 = true;
        }

        return v1;
    }

    public String b() {
        if(this.p()) {
            return this.a().toString();
        }

        if(this.o()) {
            return this.n().toString();
        }

        return this.b;
    }

    private static boolean b(Object arg6) {
        if((arg6 instanceof String)) {
            return 1;
        }

        Class v6 = arg6.getClass();
        Class[] v0 = q.a;
        int v2 = v0.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            if(v0[v4].isAssignableFrom(v6)) {
                return 1;
            }
        }

        return 0;
    }

    public double c() {
        double v0 = this.p() ? this.a().doubleValue() : Double.parseDouble(this.b());
        return v0;
    }

    public long d() {
        long v0 = this.p() ? this.a().longValue() : Long.parseLong(this.b());
        return v0;
    }

    public int e() {
        int v0 = this.p() ? this.a().intValue() : Integer.parseInt(this.b());
        return v0;
    }

    public boolean equals(Object arg7) {
        boolean v0 = true;
        if(this == (((q)arg7))) {
            return 1;
        }

        if(arg7 != null) {
            if(this.getClass() != arg7.getClass()) {
            }
            else if(this.b == null) {
                if(((q)arg7).b == null) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
            else {
                if((q.a(this)) && (q.a(((q)arg7)))) {
                    if(this.a().longValue() == ((q)arg7).a().longValue()) {
                    }
                    else {
                        v0 = false;
                    }

                    return v0;
                }

                if(((this.b instanceof Number)) && ((((q)arg7).b instanceof Number))) {
                    double v2 = this.a().doubleValue();
                    double v4 = ((q)arg7).a().doubleValue();
                    if(v2 != v4 && (!Double.isNaN(v2) || !Double.isNaN(v4))) {
                        v0 = false;
                    }

                    return v0;
                }

                return this.b.equals(((q)arg7).b);
            }
        }

        return 0;
    }

    public boolean f() {
        if(this.o()) {
            return this.n().booleanValue();
        }

        return Boolean.parseBoolean(this.b());
    }

    public int hashCode() {
        long v2;
        if(this.b == null) {
            return 31;
        }

        int v1 = 32;
        if(q.a(this)) {
            v2 = this.a().longValue();
            return ((int)(v2 >>> v1 ^ v2));
        }

        if((this.b instanceof Number)) {
            v2 = Double.doubleToLongBits(this.a().doubleValue());
            return ((int)(v2 >>> v1 ^ v2));
        }

        return this.b.hashCode();
    }

    Boolean n() {
        return this.b;
    }

    public boolean o() {
        return this.b instanceof Boolean;
    }

    public boolean p() {
        return this.b instanceof Number;
    }

    public boolean q() {
        return this.b instanceof String;
    }
}

