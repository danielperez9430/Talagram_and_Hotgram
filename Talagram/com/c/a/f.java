package com.c.a;

import android.view.animation.Interpolator;

public abstract class f implements Cloneable {
    class a extends f {
        float d;

        a(float arg1, float arg2) {
            super();
            this.a = arg1;
            this.d = arg2;
            this.b = Float.TYPE;
            this.c = true;
        }

        a(float arg1) {
            super();
            this.a = arg1;
            this.b = Float.TYPE;
        }

        public void a(Object arg3) {
            if(arg3 != null && arg3.getClass() == Float.class) {
                this.d = ((Float)arg3).floatValue();
                this.c = true;
            }
        }

        public Object b() {
            return Float.valueOf(this.d);
        }

        public Object clone() {
            return this.g();
        }

        public f e() {
            return this.g();
        }

        public float f() {
            return this.d;
        }

        public a g() {
            a v0 = new a(this.c(), this.d);
            v0.a(this.d());
            return v0;
        }
    }

    class b extends f {
        int d;

        b(float arg1) {
            super();
            this.a = arg1;
            this.b = Integer.TYPE;
        }

        b(float arg1, int arg2) {
            super();
            this.a = arg1;
            this.d = arg2;
            this.b = Integer.TYPE;
            this.c = true;
        }

        public void a(Object arg3) {
            if(arg3 != null && arg3.getClass() == Integer.class) {
                this.d = ((Integer)arg3).intValue();
                this.c = true;
            }
        }

        public Object b() {
            return Integer.valueOf(this.d);
        }

        public Object clone() {
            return this.g();
        }

        public f e() {
            return this.g();
        }

        public int f() {
            return this.d;
        }

        public b g() {
            b v0 = new b(this.c(), this.d);
            v0.a(this.d());
            return v0;
        }
    }

    float a;
    Class b;
    boolean c;
    private Interpolator d;

    public f() {
        super();
        this.d = null;
        this.c = false;
    }

    public static f a(float arg1) {
        return new b(arg1);
    }

    public static f a(float arg1, float arg2) {
        return new a(arg1, arg2);
    }

    public static f a(float arg1, int arg2) {
        return new b(arg1, arg2);
    }

    public void a(Interpolator arg1) {
        this.d = arg1;
    }

    public abstract void a(Object arg1);

    public boolean a() {
        return this.c;
    }

    public static f b(float arg1) {
        return new a(arg1);
    }

    public abstract Object b();

    public float c() {
        return this.a;
    }

    public Object clone() {
        return this.e();
    }

    public Interpolator d() {
        return this.d;
    }

    public abstract f e();
}

