package android.support.c.a;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.content.a.g;
import android.support.v4.graphics.b;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class i extends h {
    class android.support.c.a.i$1 {
    }

    class a extends e {
        public a() {
            super();
        }

        public a(a arg1) {
            super(((e)arg1));
        }

        public void a(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            if(!g.a(arg5, "pathData")) {
                return;
            }

            TypedArray v2 = g.a(arg2, arg4, arg3, android.support.c.a.a.d);
            this.a(v2);
            v2.recycle();
        }

        private void a(TypedArray arg2) {
            String v0 = arg2.getString(0);
            if(v0 != null) {
                this.n = v0;
            }

            String v2 = arg2.getString(1);
            if(v2 != null) {
                this.m = b.b(v2);
            }
        }

        public boolean a() {
            return 1;
        }
    }

    class android.support.c.a.i$b extends e {
        android.support.v4.content.a.b a;
        float b;
        android.support.v4.content.a.b c;
        float d;
        int e;
        float f;
        float g;
        float h;
        float i;
        Paint$Cap j;
        Paint$Join k;
        float l;
        private int[] p;

        public android.support.c.a.i$b() {
            super();
            this.b = 0f;
            this.d = 1f;
            this.e = 0;
            this.f = 1f;
            this.g = 0f;
            this.h = 1f;
            this.i = 0f;
            this.j = Paint$Cap.BUTT;
            this.k = Paint$Join.MITER;
            this.l = 4f;
        }

        public android.support.c.a.i$b(android.support.c.a.i$b arg4) {
            super(((e)arg4));
            this.b = 0f;
            this.d = 1f;
            this.e = 0;
            this.f = 1f;
            this.g = 0f;
            this.h = 1f;
            this.i = 0f;
            this.j = Paint$Cap.BUTT;
            this.k = Paint$Join.MITER;
            this.l = 4f;
            this.p = arg4.p;
            this.a = arg4.a;
            this.b = arg4.b;
            this.d = arg4.d;
            this.c = arg4.c;
            this.e = arg4.e;
            this.f = arg4.f;
            this.g = arg4.g;
            this.h = arg4.h;
            this.i = arg4.i;
            this.j = arg4.j;
            this.k = arg4.k;
            this.l = arg4.l;
        }

        public void a(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            TypedArray v2 = g.a(arg2, arg4, arg3, android.support.c.a.a.c);
            this.a(v2, arg5, arg4);
            v2.recycle();
        }

        private Paint$Cap a(int arg1, Paint$Cap arg2) {
            switch(arg1) {
                case 0: {
                    goto label_6;
                }
                case 1: {
                    goto label_4;
                }
                case 2: {
                    goto label_2;
                }
            }

            return arg2;
        label_2:
            return Paint$Cap.SQUARE;
        label_4:
            return Paint$Cap.ROUND;
        label_6:
            return Paint$Cap.BUTT;
        }

        private Paint$Join a(int arg1, Paint$Join arg2) {
            switch(arg1) {
                case 0: {
                    goto label_6;
                }
                case 1: {
                    goto label_4;
                }
                case 2: {
                    goto label_2;
                }
            }

            return arg2;
        label_2:
            return Paint$Join.BEVEL;
        label_4:
            return Paint$Join.ROUND;
        label_6:
            return Paint$Join.MITER;
        }

        private void a(TypedArray arg8, XmlPullParser arg9, Resources$Theme arg10) {
            this.p = null;
            if(!g.a(arg9, "pathData")) {
                return;
            }

            String v0 = arg8.getString(0);
            if(v0 != null) {
                this.n = v0;
            }

            v0 = arg8.getString(2);
            if(v0 != null) {
                this.m = b.b(v0);
            }

            this.c = g.a(arg8, arg9, arg10, "fillColor", 1, 0);
            this.f = g.a(arg8, arg9, "fillAlpha", 12, this.f);
            this.j = this.a(g.a(arg8, arg9, "strokeLineCap", 8, -1), this.j);
            this.k = this.a(g.a(arg8, arg9, "strokeLineJoin", 9, -1), this.k);
            this.l = g.a(arg8, arg9, "strokeMiterLimit", 10, this.l);
            this.a = g.a(arg8, arg9, arg10, "strokeColor", 3, 0);
            this.d = g.a(arg8, arg9, "strokeAlpha", 11, this.d);
            this.b = g.a(arg8, arg9, "strokeWidth", 4, this.b);
            this.h = g.a(arg8, arg9, "trimPathEnd", 6, this.h);
            this.i = g.a(arg8, arg9, "trimPathOffset", 7, this.i);
            this.g = g.a(arg8, arg9, "trimPathStart", 5, this.g);
            this.e = g.a(arg8, arg9, "fillType", 13, this.e);
        }

        public boolean a(int[] arg3) {
            return this.a.a(arg3) | this.c.a(arg3);
        }

        public boolean b() {
            boolean v0 = (this.c.d()) || (this.a.d()) ? true : false;
            return v0;
        }

        float getFillAlpha() {
            return this.f;
        }

        int getFillColor() {
            return this.c.b();
        }

        float getStrokeAlpha() {
            return this.d;
        }

        int getStrokeColor() {
            return this.a.b();
        }

        float getStrokeWidth() {
            return this.b;
        }

        float getTrimPathEnd() {
            return this.h;
        }

        float getTrimPathOffset() {
            return this.i;
        }

        float getTrimPathStart() {
            return this.g;
        }

        void setFillAlpha(float arg1) {
            this.f = arg1;
        }

        void setFillColor(int arg2) {
            this.c.b(arg2);
        }

        void setStrokeAlpha(float arg1) {
            this.d = arg1;
        }

        void setStrokeColor(int arg2) {
            this.a.b(arg2);
        }

        void setStrokeWidth(float arg1) {
            this.b = arg1;
        }

        void setTrimPathEnd(float arg1) {
            this.h = arg1;
        }

        void setTrimPathOffset(float arg1) {
            this.i = arg1;
        }

        void setTrimPathStart(float arg1) {
            this.g = arg1;
        }
    }

    class c extends d {
        final Matrix a;
        final ArrayList b;
        float c;
        final Matrix d;
        int e;
        private float f;
        private float g;
        private float h;
        private float i;
        private float j;
        private float k;
        private int[] l;
        private String m;

        public c() {
            super(null);
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0f;
            this.f = 0f;
            this.g = 0f;
            this.h = 1f;
            this.i = 1f;
            this.j = 0f;
            this.k = 0f;
            this.d = new Matrix();
            this.m = null;
        }

        public c(c arg5, android.support.v4.f.a arg6) {
            android.support.c.a.i$b v2;
            super(null);
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0f;
            this.f = 0f;
            this.g = 0f;
            this.h = 1f;
            this.i = 1f;
            this.j = 0f;
            this.k = 0f;
            this.d = new Matrix();
            this.m = null;
            this.c = arg5.c;
            this.f = arg5.f;
            this.g = arg5.g;
            this.h = arg5.h;
            this.i = arg5.i;
            this.j = arg5.j;
            this.k = arg5.k;
            this.l = arg5.l;
            this.m = arg5.m;
            this.e = arg5.e;
            if(this.m != null) {
                arg6.put(this.m, this);
            }

            this.d.set(arg5.d);
            ArrayList v5 = arg5.b;
            int v0;
            for(v0 = 0; true; ++v0) {
                if(v0 >= v5.size()) {
                    return;
                }

                Object v1 = v5.get(v0);
                if((v1 instanceof c)) {
                    this.b.add(new c(((c)v1), arg6));
                }
                else {
                    if((v1 instanceof android.support.c.a.i$b)) {
                        v2 = new android.support.c.a.i$b(((android.support.c.a.i$b)v1));
                    }
                    else if((v1 instanceof a)) {
                        a v2_1 = new a(((a)v1));
                    }
                    else {
                        break;
                    }

                    this.b.add(v2);
                    if(((e)v2).n == null) {
                        goto label_75;
                    }

                    arg6.put(((e)v2).n, v2);
                }

            label_75:
            }

            throw new IllegalStateException("Unknown object in the tree!");
        }

        public void a(Resources arg2, AttributeSet arg3, Resources$Theme arg4, XmlPullParser arg5) {
            TypedArray v2 = g.a(arg2, arg4, arg3, android.support.c.a.a.b);
            this.a(v2, arg5);
            v2.recycle();
        }

        public boolean a(int[] arg4) {
            int v0 = 0;
            int v1 = 0;
            while(v0 < this.b.size()) {
                v1 |= this.b.get(v0).a(arg4);
                ++v0;
            }

            return ((boolean)v1);
        }

        private void a() {
            this.d.reset();
            this.d.postTranslate(-this.f, -this.g);
            this.d.postScale(this.h, this.i);
            this.d.postRotate(this.c, 0f, 0f);
            this.d.postTranslate(this.j + this.f, this.k + this.g);
        }

        private void a(TypedArray arg4, XmlPullParser arg5) {
            this.l = null;
            this.c = g.a(arg4, arg5, "rotation", 5, this.c);
            this.f = arg4.getFloat(1, this.f);
            this.g = arg4.getFloat(2, this.g);
            this.h = g.a(arg4, arg5, "scaleX", 3, this.h);
            this.i = g.a(arg4, arg5, "scaleY", 4, this.i);
            this.j = g.a(arg4, arg5, "translateX", 6, this.j);
            this.k = g.a(arg4, arg5, "translateY", 7, this.k);
            String v4 = arg4.getString(0);
            if(v4 != null) {
                this.m = v4;
            }

            this.a();
        }

        public boolean b() {
            int v1;
            for(v1 = 0; v1 < this.b.size(); ++v1) {
                if(this.b.get(v1).b()) {
                    return 1;
                }
            }

            return 0;
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.d;
        }

        public float getPivotX() {
            return this.f;
        }

        public float getPivotY() {
            return this.g;
        }

        public float getRotation() {
            return this.c;
        }

        public float getScaleX() {
            return this.h;
        }

        public float getScaleY() {
            return this.i;
        }

        public float getTranslateX() {
            return this.j;
        }

        public float getTranslateY() {
            return this.k;
        }

        public void setPivotX(float arg2) {
            if(arg2 != this.f) {
                this.f = arg2;
                this.a();
            }
        }

        public void setPivotY(float arg2) {
            if(arg2 != this.g) {
                this.g = arg2;
                this.a();
            }
        }

        public void setRotation(float arg2) {
            if(arg2 != this.c) {
                this.c = arg2;
                this.a();
            }
        }

        public void setScaleX(float arg2) {
            if(arg2 != this.h) {
                this.h = arg2;
                this.a();
            }
        }

        public void setScaleY(float arg2) {
            if(arg2 != this.i) {
                this.i = arg2;
                this.a();
            }
        }

        public void setTranslateX(float arg2) {
            if(arg2 != this.j) {
                this.j = arg2;
                this.a();
            }
        }

        public void setTranslateY(float arg2) {
            if(arg2 != this.k) {
                this.k = arg2;
                this.a();
            }
        }
    }

    abstract class d {
        private d() {
            super();
        }

        d(android.support.c.a.i$1 arg1) {
            this();
        }

        public boolean a(int[] arg1) {
            return 0;
        }

        public boolean b() {
            return 0;
        }
    }

    abstract class e extends d {
        protected android.support.v4.graphics.b$b[] m;
        String n;
        int o;

        public e() {
            super(null);
            this.m = null;
        }

        public e(e arg2) {
            super(null);
            this.m = null;
            this.n = arg2.n;
            this.o = arg2.o;
            this.m = b.a(arg2.m);
        }

        public void a(Path arg2) {
            arg2.reset();
            if(this.m != null) {
                android.support.v4.graphics.b$b.a(this.m, arg2);
            }
        }

        public boolean a() {
            return 0;
        }

        public android.support.v4.graphics.b$b[] getPathData() {
            return this.m;
        }

        public String getPathName() {
            return this.n;
        }

        public void setPathData(android.support.v4.graphics.b$b[] arg2) {
            if(!b.a(this.m, arg2)) {
                this.m = b.a(arg2);
            }
            else {
                b.b(this.m, arg2);
            }
        }
    }

    class f {
        Paint a;
        Paint b;
        final c c;
        float d;
        float e;
        float f;
        float g;
        int h;
        String i;
        Boolean j;
        final android.support.v4.f.a k;
        private final Path l;
        private final Path m;
        private static final Matrix n;
        private final Matrix o;
        private PathMeasure p;
        private int q;

        static {
            f.n = new Matrix();
        }

        public f() {
            super();
            this.o = new Matrix();
            this.d = 0f;
            this.e = 0f;
            this.f = 0f;
            this.g = 0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.f.a();
            this.c = new c();
            this.l = new Path();
            this.m = new Path();
        }

        public f(f arg4) {
            super();
            this.o = new Matrix();
            this.d = 0f;
            this.e = 0f;
            this.f = 0f;
            this.g = 0f;
            this.h = 255;
            this.i = null;
            this.j = null;
            this.k = new android.support.v4.f.a();
            this.c = new c(arg4.c, this.k);
            this.l = new Path(arg4.l);
            this.m = new Path(arg4.m);
            this.d = arg4.d;
            this.e = arg4.e;
            this.f = arg4.f;
            this.g = arg4.g;
            this.q = arg4.q;
            this.h = arg4.h;
            this.i = arg4.i;
            if(arg4.i != null) {
                this.k.put(arg4.i, this);
            }

            this.j = arg4.j;
        }

        private static float a(float arg0, float arg1, float arg2, float arg3) {
            return arg0 * arg3 - arg1 * arg2;
        }

        private float a(Matrix arg10) {
            float[] v0 = new float[]{0f, 1f, 1f, 0f};
            arg10.mapVectors(v0);
            float v1 = ((float)Math.hypot(((double)v0[0]), ((double)v0[1])));
            float v4 = ((float)Math.hypot(((double)v0[2]), ((double)v0[3])));
            float v10 = f.a(v0[0], v0[1], v0[2], v0[3]);
            float v0_1 = Math.max(v1, v4);
            v1 = 0f;
            if(v0_1 > 0f) {
                v1 = Math.abs(v10) / v0_1;
            }

            return v1;
        }

        private void a(c arg10, Matrix arg11, Canvas arg12, int arg13, int arg14, ColorFilter arg15) {
            arg10.a.set(arg11);
            arg10.a.preConcat(arg10.d);
            arg12.save();
            int v11;
            for(v11 = 0; v11 < arg10.b.size(); ++v11) {
                Object v0 = arg10.b.get(v11);
                if((v0 instanceof c)) {
                    this.a(v0, arg10.a, arg12, arg13, arg14, arg15);
                }
                else if((v0 instanceof e)) {
                    this.a(arg10, v0, arg12, arg13, arg14, arg15);
                }
            }

            arg12.restore();
        }

        private void a(c arg8, e arg9, Canvas arg10, int arg11, int arg12, ColorFilter arg13) {
            // Method was not decompiled
        }

        public void a(Canvas arg8, int arg9, int arg10, ColorFilter arg11) {
            this.a(this.c, f.n, arg8, arg9, arg10, arg11);
        }

        public boolean a() {
            if(this.j == null) {
                this.j = Boolean.valueOf(this.c.b());
            }

            return this.j.booleanValue();
        }

        public boolean a(int[] arg2) {
            return this.c.a(arg2);
        }

        public float getAlpha() {
            return (((float)this.getRootAlpha())) / 255f;
        }

        public int getRootAlpha() {
            return this.h;
        }

        public void setAlpha(float arg2) {
            this.setRootAlpha(((int)(arg2 * 255f)));
        }

        public void setRootAlpha(int arg1) {
            this.h = arg1;
        }
    }

    class android.support.c.a.i$g extends Drawable$ConstantState {
        int a;
        f b;
        ColorStateList c;
        PorterDuff$Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff$Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public android.support.c.a.i$g() {
            super();
            this.c = null;
            this.d = i.a;
            this.b = new f();
        }

        public android.support.c.a.i$g(android.support.c.a.i$g arg4) {
            super();
            this.c = null;
            this.d = i.a;
            if(arg4 != null) {
                this.a = arg4.a;
                this.b = new f(arg4.b);
                if(arg4.b.b != null) {
                    this.b.b = new Paint(arg4.b.b);
                }

                if(arg4.b.a != null) {
                    this.b.a = new Paint(arg4.b.a);
                }

                this.c = arg4.c;
                this.d = arg4.d;
                this.e = arg4.e;
            }
        }

        public boolean a(int[] arg2) {
            boolean v2 = this.b.a(arg2);
            this.k |= ((int)v2);
            return v2;
        }

        public Paint a(ColorFilter arg3) {
            if(!this.a() && arg3 == null) {
                return null;
            }

            if(this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }

            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(arg3);
            return this.l;
        }

        public boolean a() {
            boolean v0 = this.b.getRootAlpha() < 255 ? true : false;
            return v0;
        }

        public void a(int arg4, int arg5) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), arg4, arg5, null);
        }

        public void a(Canvas arg3, ColorFilter arg4, Rect arg5) {
            arg3.drawBitmap(this.f, null, arg5, this.a(arg4));
        }

        public void b(int arg2, int arg3) {
            if(this.f == null || !this.c(arg2, arg3)) {
                this.f = Bitmap.createBitmap(arg2, arg3, Bitmap$Config.ARGB_8888);
                this.k = true;
            }
        }

        public boolean b() {
            if(!this.k && this.g == this.c && this.h == this.d && this.j == this.e && this.i == this.b.getRootAlpha()) {
                return 1;
            }

            return 0;
        }

        public boolean c(int arg2, int arg3) {
            if(arg2 == this.f.getWidth() && arg3 == this.f.getHeight()) {
                return 1;
            }

            return 0;
        }

        public void c() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public boolean d() {
            return this.b.a();
        }

        public int getChangingConfigurations() {
            return this.a;
        }

        public Drawable newDrawable() {
            return new i(this);
        }

        public Drawable newDrawable(Resources arg1) {
            return new i(this);
        }
    }

    class android.support.c.a.i$h extends Drawable$ConstantState {
        private final Drawable$ConstantState a;

        public android.support.c.a.i$h(Drawable$ConstantState arg1) {
            super();
            this.a = arg1;
        }

        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            i v0 = new i();
            v0.c = this.a.newDrawable();
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3) {
            i v0 = new i();
            v0.c = this.a.newDrawable(arg3);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3, Resources$Theme arg4) {
            i v0 = new i();
            v0.c = this.a.newDrawable(arg3, arg4);
            return ((Drawable)v0);
        }
    }

    static final PorterDuff$Mode a;
    private android.support.c.a.i$g b;
    private PorterDuffColorFilter d;
    private ColorFilter e;
    private boolean f;
    private boolean g;
    private Drawable$ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    static {
        i.a = PorterDuff$Mode.SRC_IN;
    }

    i() {
        super();
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.b = new android.support.c.a.i$g();
    }

    i(android.support.c.a.i$g arg3) {
        super();
        this.g = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.b = arg3;
        this.d = this.a(this.d, arg3.c, arg3.d);
    }

    public static i a(Resources arg1, XmlPullParser arg2, AttributeSet arg3, Resources$Theme arg4) {
        i v0 = new i();
        v0.inflate(arg1, arg2, arg3, arg4);
        return v0;
    }

    Object a(String arg2) {
        return this.b.b.k.get(arg2);
    }

    public static i a(Resources arg4, int arg5, Resources$Theme arg6) {
        if(Build$VERSION.SDK_INT >= 24) {
            i v0 = new i();
            v0.c = android.support.v4.content.a.f.a(arg4, arg5, arg6);
            v0.h = new android.support.c.a.i$h(v0.c.getConstantState());
            return v0;
        }

        try {
            XmlResourceParser v5 = arg4.getXml(arg5);
            AttributeSet v0_1 = Xml.asAttributeSet(((XmlPullParser)v5));
            while(true) {
                int v1 = ((XmlPullParser)v5).next();
                int v2 = 2;
                if(v1 != v2 && v1 != 1) {
                    continue;
                }

                break;
            }

            if(v1 == v2) {
                return i.a(arg4, ((XmlPullParser)v5), v0_1, arg6);
            }

            throw new XmlPullParserException("No start tag found");
        }
        catch(IOException v4) {
            Log.e("VectorDrawableCompat", "parser error", ((Throwable)v4));
            return null;
        }
    }

    void a(boolean arg1) {
        this.g = arg1;
    }

    PorterDuffColorFilter a(PorterDuffColorFilter arg2, ColorStateList arg3, PorterDuff$Mode arg4) {
        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                return new PorterDuffColorFilter(arg3.getColorForState(this.getState(), 0), arg4);
            }
        }

        return null;
    }

    static int a(int arg2, float arg3) {
        return arg2 & 16777215 | (((int)((((float)Color.alpha(arg2))) * arg3))) << 24;
    }

    private static PorterDuff$Mode a(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_18;
        }

        if(arg1 == 5) {
            goto label_16;
        }

        if(arg1 == 9) {
            goto label_14;
        }

        switch(arg1) {
            case 14: {
                goto label_12;
            }
            case 15: {
                goto label_10;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        return PorterDuff$Mode.ADD;
    label_10:
        return PorterDuff$Mode.SCREEN;
    label_12:
        return PorterDuff$Mode.MULTIPLY;
    label_14:
        return PorterDuff$Mode.SRC_ATOP;
    label_16:
        return PorterDuff$Mode.SRC_IN;
    label_18:
        return PorterDuff$Mode.SRC_OVER;
    }

    private void a(TypedArray arg6, XmlPullParser arg7) {
        StringBuilder v0_1;
        android.support.c.a.i$g v0 = this.b;
        f v1 = v0.b;
        v0.d = i.a(g.a(arg6, arg7, "tintMode", 6, -1), PorterDuff$Mode.SRC_IN);
        ColorStateList v2 = arg6.getColorStateList(1);
        if(v2 != null) {
            v0.c = v2;
        }

        v0.e = g.a(arg6, arg7, "autoMirrored", 5, v0.e);
        v1.f = g.a(arg6, arg7, "viewportWidth", 7, v1.f);
        v1.g = g.a(arg6, arg7, "viewportHeight", 8, v1.g);
        if(v1.f > 0f) {
            if(v1.g > 0f) {
                v1.d = arg6.getDimension(3, v1.d);
                v1.e = arg6.getDimension(2, v1.e);
                if(v1.d > 0f) {
                    if(v1.e > 0f) {
                        v1.setAlpha(g.a(arg6, arg7, "alpha", 4, v1.getAlpha()));
                        String v6 = arg6.getString(0);
                        if(v6 != null) {
                            v1.i = v6;
                            v1.k.put(v6, v1);
                        }

                        return;
                    }

                    v0_1 = new StringBuilder();
                    v0_1.append(arg6.getPositionDescription());
                    v0_1.append("<vector> tag requires height > 0");
                    throw new XmlPullParserException(v0_1.toString());
                }

                v0_1 = new StringBuilder();
                v0_1.append(arg6.getPositionDescription());
                v0_1.append("<vector> tag requires width > 0");
                throw new XmlPullParserException(v0_1.toString());
            }

            v0_1 = new StringBuilder();
            v0_1.append(arg6.getPositionDescription());
            v0_1.append("<vector> tag requires viewportHeight > 0");
            throw new XmlPullParserException(v0_1.toString());
        }

        v0_1 = new StringBuilder();
        v0_1.append(arg6.getPositionDescription());
        v0_1.append("<vector> tag requires viewportWidth > 0");
        throw new XmlPullParserException(v0_1.toString());
    }

    private boolean a() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT >= 17 && (this.isAutoMirrored()) && android.support.v4.graphics.drawable.a.h(((Drawable)this)) == 1) {
            v1 = true;
        }

        return v1;
    }

    public void applyTheme(Resources$Theme arg1) {
        super.applyTheme(arg1);
    }

    private void b(Resources arg10, XmlPullParser arg11, AttributeSet arg12, Resources$Theme arg13) {
        int v7_1;
        android.support.c.a.i$g v0 = this.b;
        f v1 = v0.b;
        ArrayDeque v2 = new ArrayDeque();
        v2.push(v1.c);
        int v3 = arg11.getEventType();
        int v4 = arg11.getDepth() + 1;
        int v6 = 1;
        while(v3 != 1) {
            int v8 = 3;
            if(arg11.getDepth() < v4 && v3 == v8) {
                break;
            }

            if(v3 == 2) {
                String v3_1 = arg11.getName();
                Object v7 = v2.peek();
                if("path".equals(v3_1)) {
                    android.support.c.a.i$b v3_2 = new android.support.c.a.i$b();
                    v3_2.a(arg10, arg12, arg13, arg11);
                    ((c)v7).b.add(v3_2);
                    if(v3_2.getPathName() != null) {
                        v1.k.put(v3_2.getPathName(), v3_2);
                    }

                    v6 = 0;
                    v7_1 = v0.a;
                    v3 = v3_2.o;
                }
                else {
                    if("clip-path".equals(v3_1)) {
                        a v3_3 = new a();
                        v3_3.a(arg10, arg12, arg13, arg11);
                        ((c)v7).b.add(v3_3);
                        if(v3_3.getPathName() != null) {
                            v1.k.put(v3_3.getPathName(), v3_3);
                        }

                        v7_1 = v0.a;
                        v3 = v3_3.o;
                        goto label_52;
                    }

                    if(!"group".equals(v3_1)) {
                        goto label_78;
                    }

                    c v3_4 = new c();
                    v3_4.a(arg10, arg12, arg13, arg11);
                    ((c)v7).b.add(v3_4);
                    v2.push(v3_4);
                    if(v3_4.getGroupName() != null) {
                        v1.k.put(v3_4.getGroupName(), v3_4);
                    }

                    v7_1 = v0.a;
                    v3 = v3_4.e;
                }

            label_52:
                v0.a = v3 | v7_1;
            }
            else {
                if(v3 != v8) {
                    goto label_78;
                }

                if(!"group".equals(arg11.getName())) {
                    goto label_78;
                }

                v2.pop();
            }

        label_78:
            v3 = arg11.next();
        }

        if(v6 == 0) {
            return;
        }

        throw new XmlPullParserException("no path defined");
    }

    public boolean canApplyTheme() {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.d(this.c);
        }

        return 0;
    }

    public void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas arg10) {
        // Method was not decompiled
    }

    public int getAlpha() {
        if(this.c != null) {
            return android.support.v4.graphics.drawable.a.c(this.c);
        }

        return this.b.b.getRootAlpha();
    }

    public int getChangingConfigurations() {
        if(this.c != null) {
            return this.c.getChangingConfigurations();
        }

        return super.getChangingConfigurations() | this.b.getChangingConfigurations();
    }

    public ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.c != null && Build$VERSION.SDK_INT >= 24) {
            return new android.support.c.a.i$h(this.c.getConstantState());
        }

        this.b.a = this.getChangingConfigurations();
        return this.b;
    }

    public Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        if(this.c != null) {
            return this.c.getIntrinsicHeight();
        }

        return ((int)this.b.b.e);
    }

    public int getIntrinsicWidth() {
        if(this.c != null) {
            return this.c.getIntrinsicWidth();
        }

        return ((int)this.b.b.d);
    }

    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        if(this.c != null) {
            return this.c.getOpacity();
        }

        return -3;
    }

    public boolean getPadding(Rect arg1) {
        return super.getPadding(arg1);
    }

    public int[] getState() {
        return super.getState();
    }

    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources arg3, XmlPullParser arg4, AttributeSet arg5, Resources$Theme arg6) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg3, arg4, arg5, arg6);
            return;
        }

        android.support.c.a.i$g v0 = this.b;
        v0.b = new f();
        TypedArray v1 = g.a(arg3, arg6, arg5, android.support.c.a.a.a);
        this.a(v1, arg4);
        v1.recycle();
        v0.a = this.getChangingConfigurations();
        v0.k = true;
        this.b(arg3, arg4, arg5, arg6);
        this.d = this.a(this.d, v0.c, v0.d);
    }

    public void inflate(Resources arg2, XmlPullParser arg3, AttributeSet arg4) {
        if(this.c != null) {
            this.c.inflate(arg2, arg3, arg4);
            return;
        }

        this.inflate(arg2, arg3, arg4, null);
    }

    public void invalidateSelf() {
        if(this.c != null) {
            this.c.invalidateSelf();
            return;
        }

        super.invalidateSelf();
    }

    public boolean isAutoMirrored() {
        if(this.c != null) {
            return android.support.v4.graphics.drawable.a.b(this.c);
        }

        return this.b.e;
    }

    public boolean isStateful() {
        boolean v0;
        if(this.c != null) {
            return this.c.isStateful();
        }

        if(!super.isStateful()) {
            if(this.b != null) {
                if(this.b.d()) {
                    goto label_22;
                }
                else if(this.b.c != null && (this.b.c.isStateful())) {
                    goto label_22;
                }
            }

            v0 = false;
        }
        else {
        label_22:
            v0 = true;
        }

        return v0;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if(this.c != null) {
            this.c.mutate();
            return this;
        }

        if(!this.f && super.mutate() == this) {
            this.b = new android.support.c.a.i$g(this.b);
            this.f = true;
        }

        return this;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.c != null) {
            this.c.setBounds(arg2);
        }
    }

    protected boolean onStateChange(int[] arg6) {
        if(this.c != null) {
            return this.c.setState(arg6);
        }

        boolean v0 = false;
        android.support.c.a.i$g v1 = this.b;
        if(v1.c != null && v1.d != null) {
            this.d = this.a(this.d, v1.c, v1.d);
            this.invalidateSelf();
            v0 = true;
        }

        if((v1.d()) && (v1.a(arg6))) {
            this.invalidateSelf();
            v0 = true;
        }

        return v0;
    }

    public void scheduleSelf(Runnable arg2, long arg3) {
        if(this.c != null) {
            this.c.scheduleSelf(arg2, arg3);
            return;
        }

        super.scheduleSelf(arg2, arg3);
    }

    public void setAlpha(int arg2) {
        if(this.c != null) {
            this.c.setAlpha(arg2);
            return;
        }

        if(this.b.b.getRootAlpha() != arg2) {
            this.b.b.setRootAlpha(arg2);
            this.invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.b.e = arg2;
    }

    public void setChangingConfigurations(int arg1) {
        super.setChangingConfigurations(arg1);
    }

    public void setColorFilter(ColorFilter arg2) {
        if(this.c != null) {
            this.c.setColorFilter(arg2);
            return;
        }

        this.e = arg2;
        this.invalidateSelf();
    }

    public void setColorFilter(int arg1, PorterDuff$Mode arg2) {
        super.setColorFilter(arg1, arg2);
    }

    public void setFilterBitmap(boolean arg1) {
        super.setFilterBitmap(arg1);
    }

    public void setHotspot(float arg1, float arg2) {
        super.setHotspot(arg1, arg2);
    }

    public void setHotspotBounds(int arg1, int arg2, int arg3, int arg4) {
        super.setHotspotBounds(arg1, arg2, arg3, arg4);
    }

    public boolean setState(int[] arg1) {
        return super.setState(arg1);
    }

    public void setTint(int arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.setTintList(ColorStateList.valueOf(arg2));
    }

    public void setTintList(ColorStateList arg3) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg3);
            return;
        }

        android.support.c.a.i$g v0 = this.b;
        if(v0.c != arg3) {
            v0.c = arg3;
            this.d = this.a(this.d, arg3, v0.d);
            this.invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff$Mode arg3) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg3);
            return;
        }

        android.support.c.a.i$g v0 = this.b;
        if(v0.d != arg3) {
            v0.d = arg3;
            this.d = this.a(this.d, v0.c, arg3);
            this.invalidateSelf();
        }
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        if(this.c != null) {
            return this.c.setVisible(arg2, arg3);
        }

        return super.setVisible(arg2, arg3);
    }

    public void unscheduleSelf(Runnable arg2) {
        if(this.c != null) {
            this.c.unscheduleSelf(arg2);
            return;
        }

        super.unscheduleSelf(arg2);
    }
}

