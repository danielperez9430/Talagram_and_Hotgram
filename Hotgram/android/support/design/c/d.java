package android.support.design.c;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.support.design.widget.l;
import android.util.Property;

public interface d extends a {
    class android.support.design.c.d$1 {
    }

    public class android.support.design.c.d$a implements TypeEvaluator {
        public static final TypeEvaluator a;
        private final android.support.design.c.d$d b;

        static {
            android.support.design.c.d$a.a = new android.support.design.c.d$a();
        }

        public android.support.design.c.d$a() {
            super();
            this.b = new android.support.design.c.d$d(null);
        }

        public android.support.design.c.d$d a(float arg5, android.support.design.c.d$d arg6, android.support.design.c.d$d arg7) {
            this.b.a(l.a(arg6.a, arg7.a, arg5), l.a(arg6.b, arg7.b, arg5), l.a(arg6.c, arg7.c, arg5));
            return this.b;
        }

        public Object evaluate(float arg1, Object arg2, Object arg3) {
            return this.a(arg1, ((android.support.design.c.d$d)arg2), ((android.support.design.c.d$d)arg3));
        }
    }

    public class b extends Property {
        public static final Property a;

        static {
            b.a = new b("circularReveal");
        }

        private b(String arg2) {
            super(android.support.design.c.d$d.class, arg2);
        }

        public android.support.design.c.d$d a(d arg1) {
            return arg1.getRevealInfo();
        }

        public void a(d arg1, android.support.design.c.d$d arg2) {
            arg1.setRevealInfo(arg2);
        }

        public Object get(Object arg1) {
            return this.a(((d)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((d)arg1), ((android.support.design.c.d$d)arg2));
        }
    }

    public class c extends Property {
        public static final Property a;

        static {
            c.a = new c("circularRevealScrimColor");
        }

        private c(String arg2) {
            super(Integer.class, arg2);
        }

        public Integer a(d arg1) {
            return Integer.valueOf(arg1.getCircularRevealScrimColor());
        }

        public void a(d arg1, Integer arg2) {
            arg1.setCircularRevealScrimColor(arg2.intValue());
        }

        public Object get(Object arg1) {
            return this.a(((d)arg1));
        }

        public void set(Object arg1, Object arg2) {
            this.a(((d)arg1), ((Integer)arg2));
        }
    }

    public class android.support.design.c.d$d {
        public float a;
        public float b;
        public float c;

        public android.support.design.c.d$d(float arg1, float arg2, float arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public android.support.design.c.d$d(android.support.design.c.d$d arg3) {
            this(arg3.a, arg3.b, arg3.c);
        }

        android.support.design.c.d$d(android.support.design.c.d$1 arg1) {
            this();
        }

        private android.support.design.c.d$d() {
            super();
        }

        public boolean a() {
            boolean v0 = this.c == 340282346638528860000000000000000000000f ? true : false;
            return v0;
        }

        public void a(android.support.design.c.d$d arg3) {
            this.a(arg3.a, arg3.b, arg3.c);
        }

        public void a(float arg1, float arg2, float arg3) {
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    android.support.design.c.d$d getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable arg1);

    void setCircularRevealScrimColor(int arg1);

    void setRevealInfo(android.support.design.c.d$d arg1);
}

