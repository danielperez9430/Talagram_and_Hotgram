package org.telegram.customization.util.view.zoom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector$SimpleOnScaleGestureListener;
import android.view.ScaleGestureDetector;
import android.view.View$MeasureSpec;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.Scroller;

public class TouchImageView extends ImageView {
    class org.telegram.customization.util.view.zoom.TouchImageView$1 {
        static {
            org.telegram.customization.util.view.zoom.TouchImageView$1.a = new int[ImageView$ScaleType.values().length];
            try {
                org.telegram.customization.util.view.zoom.TouchImageView$1.a[ImageView$ScaleType.CENTER.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.telegram.customization.util.view.zoom.TouchImageView$1.a[ImageView$ScaleType.CENTER_CROP.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.telegram.customization.util.view.zoom.TouchImageView$1.a[ImageView$ScaleType.CENTER_INSIDE.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.telegram.customization.util.view.zoom.TouchImageView$1.a[ImageView$ScaleType.FIT_CENTER.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                org.telegram.customization.util.view.zoom.TouchImageView$1.a[ImageView$ScaleType.FIT_XY.ordinal()] = 5;
                                return;
                            }
                            catch(NoSuchFieldError ) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(value=9) class a {
        Scroller a;
        OverScroller b;
        boolean c;

        public a(TouchImageView arg2, Context arg3) {
            this.d = arg2;
            super();
            if(Build$VERSION.SDK_INT < 9) {
                this.c = true;
                this.a = new Scroller(arg3);
            }
            else {
                this.c = false;
                this.b = new OverScroller(arg3);
            }
        }

        public void a(int arg13, int arg14, int arg15, int arg16, int arg17, int arg18, int arg19, int arg20) {
            a v0 = this;
            if(v0.c) {
                v0.a.fling(arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
            }
            else {
                v0.b.fling(arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20);
            }
        }

        public void a(boolean arg2) {
            if(this.c) {
                this.a.forceFinished(arg2);
            }
            else {
                this.b.forceFinished(arg2);
            }
        }

        public boolean a() {
            if(this.c) {
                return this.a.isFinished();
            }

            return this.b.isFinished();
        }

        public boolean b() {
            if(this.c) {
                return this.a.computeScrollOffset();
            }

            this.b.computeScrollOffset();
            return this.b.computeScrollOffset();
        }

        public int c() {
            if(this.c) {
                return this.a.getCurrX();
            }

            return this.b.getCurrX();
        }

        public int d() {
            if(this.c) {
                return this.a.getCurrY();
            }

            return this.b.getCurrY();
        }
    }

    class b implements Runnable {
        private long b;
        private float c;
        private float d;
        private float e;
        private float f;
        private boolean g;
        private AccelerateDecelerateInterpolator h;
        private PointF i;
        private PointF j;

        b(TouchImageView arg3, float arg4, float arg5, float arg6, boolean arg7) {
            this.a = arg3;
            super();
            this.h = new AccelerateDecelerateInterpolator();
            TouchImageView.a(arg3, h.e);
            this.b = System.currentTimeMillis();
            this.c = TouchImageView.d(arg3);
            this.d = arg4;
            this.g = arg7;
            PointF v4 = TouchImageView.a(arg3, arg5, arg6, false);
            this.e = v4.x;
            this.f = v4.y;
            this.i = TouchImageView.a(arg3, this.e, this.f);
            this.j = new PointF(((float)(TouchImageView.i(arg3) / 2)), ((float)(TouchImageView.k(arg3) / 2)));
        }

        private float a() {
            return this.h.getInterpolation(Math.min(1f, (((float)(System.currentTimeMillis() - this.b))) / 500f));
        }

        private void a(float arg5) {
            float v0 = this.i.x + (this.j.x - this.i.x) * arg5;
            float v1 = this.i.y + arg5 * (this.j.y - this.i.y);
            PointF v5 = TouchImageView.a(this.a, this.e, this.f);
            TouchImageView.m(this.a).postTranslate(v0 - v5.x, v1 - v5.y);
        }

        private double b(float arg5) {
            double v0 = ((double)(this.c + arg5 * (this.d - this.c)));
            double v2 = ((double)TouchImageView.d(this.a));
            Double.isNaN(v0);
            Double.isNaN(v2);
            return v0 / v2;
        }

        public void run() {
            float v0 = this.a();
            TouchImageView.a(this.a, this.b(v0), this.e, this.f, this.g);
            this.a(v0);
            TouchImageView.q(this.a);
            this.a.setImageMatrix(TouchImageView.m(this.a));
            if(TouchImageView.p(this.a) != null) {
                TouchImageView.p(this.a).a();
            }

            if(v0 < 1f) {
                TouchImageView.a(this.a, ((Runnable)this));
            }
            else {
                TouchImageView.a(this.a, h.a);
            }
        }
    }

    class c implements Runnable {
        a a;
        int b;
        int c;

        c(TouchImageView arg12, int arg13, int arg14) {
            // Method was not decompiled
        }

        public void a() {
            if(this.a != null) {
                TouchImageView.a(this.d, h.a);
                this.a.a(true);
            }
        }

        public void run() {
            if(TouchImageView.p(this.d) != null) {
                TouchImageView.p(this.d).a();
            }

            if(this.a.a()) {
                this.a = null;
                return;
            }

            if(this.a.b()) {
                int v0 = this.a.c();
                int v1 = this.a.d();
                int v2 = v0 - this.b;
                int v3 = v1 - this.c;
                this.b = v0;
                this.c = v1;
                TouchImageView.m(this.d).postTranslate(((float)v2), ((float)v3));
                TouchImageView.n(this.d);
                this.d.setImageMatrix(TouchImageView.m(this.d));
                TouchImageView.a(this.d, ((Runnable)this));
            }
        }
    }

    class d extends GestureDetector$SimpleOnGestureListener {
        d(TouchImageView arg1, org.telegram.customization.util.view.zoom.TouchImageView$1 arg2) {
            this(arg1);
        }

        private d(TouchImageView arg1) {
            this.a = arg1;
            super();
        }

        public boolean onDoubleTap(MotionEvent arg8) {
            boolean v0 = TouchImageView.a(this.a) != null ? TouchImageView.a(this.a).onDoubleTap(arg8) : false;
            if(TouchImageView.c(this.a) == h.a) {
                float v0_1 = TouchImageView.d(this.a) == TouchImageView.e(this.a) ? TouchImageView.f(this.a) : TouchImageView.e(this.a);
                float v3 = v0_1;
                TouchImageView.a(this.a, new b(this.a, v3, arg8.getX(), arg8.getY(), false));
                v0 = true;
            }

            return v0;
        }

        public boolean onDoubleTapEvent(MotionEvent arg2) {
            if(TouchImageView.a(this.a) != null) {
                return TouchImageView.a(this.a).onDoubleTapEvent(arg2);
            }

            return 0;
        }

        public boolean onFling(MotionEvent arg6, MotionEvent arg7, float arg8, float arg9) {
            if(TouchImageView.b(this.a) != null) {
                TouchImageView.b(this.a).a();
            }

            TouchImageView.a(this.a, new c(this.a, ((int)arg8), ((int)arg9)));
            TouchImageView.a(this.a, TouchImageView.b(this.a));
            return super.onFling(arg6, arg7, arg8, arg9);
        }

        public void onLongPress(MotionEvent arg1) {
            this.a.performLongClick();
        }

        public boolean onSingleTapConfirmed(MotionEvent arg2) {
            if(TouchImageView.a(this.a) != null) {
                return TouchImageView.a(this.a).onSingleTapConfirmed(arg2);
            }

            return this.a.performClick();
        }
    }

    public interface e {
        void a();
    }

    class f implements View$OnTouchListener {
        private PointF b;

        f(TouchImageView arg1, org.telegram.customization.util.view.zoom.TouchImageView$1 arg2) {
            this(arg1);
        }

        private f(TouchImageView arg1) {
            this.a = arg1;
            super();
            this.b = new PointF();
        }

        public boolean onTouch(View arg7, MotionEvent arg8) {
            h v1_1;
            TouchImageView v0_1;
            TouchImageView.g(this.a).onTouchEvent(arg8);
            TouchImageView.h(this.a).onTouchEvent(arg8);
            PointF v0 = new PointF(arg8.getX(), arg8.getY());
            if(TouchImageView.c(this.a) == h.a || TouchImageView.c(this.a) == h.b || TouchImageView.c(this.a) == h.d) {
                int v1 = arg8.getAction();
                if(v1 != 6) {
                    switch(v1) {
                        case 0: {
                            goto label_63;
                        }
                        case 1: {
                            goto label_74;
                        }
                        case 2: {
                            goto label_27;
                        }
                    }

                    goto label_77;
                label_27:
                    if(TouchImageView.c(this.a) == h.b) {
                        TouchImageView.m(this.a).postTranslate(TouchImageView.a(this.a, v0.x - this.b.x, ((float)TouchImageView.i(this.a)), TouchImageView.j(this.a)), TouchImageView.a(this.a, v0.y - this.b.y, ((float)TouchImageView.k(this.a)), TouchImageView.l(this.a)));
                        TouchImageView.n(this.a);
                        this.b.set(v0.x, v0.y);
                        goto label_77;
                    label_63:
                        this.b.set(v0);
                        if(TouchImageView.b(this.a) != null) {
                            TouchImageView.b(this.a).a();
                        }

                        v0_1 = this.a;
                        v1_1 = h.b;
                    }
                    else {
                        goto label_77;
                    }
                }
                else {
                label_74:
                    v0_1 = this.a;
                    v1_1 = h.a;
                }

                TouchImageView.a(v0_1, v1_1);
            }

        label_77:
            this.a.setImageMatrix(TouchImageView.m(this.a));
            if(TouchImageView.o(this.a) != null) {
                TouchImageView.o(this.a).onTouch(arg7, arg8);
            }

            if(TouchImageView.p(this.a) != null) {
                TouchImageView.p(this.a).a();
            }

            return 1;
        }
    }

    class g extends ScaleGestureDetector$SimpleOnScaleGestureListener {
        g(TouchImageView arg1, org.telegram.customization.util.view.zoom.TouchImageView$1 arg2) {
            this(arg1);
        }

        private g(TouchImageView arg1) {
            this.a = arg1;
            super();
        }

        public boolean onScale(ScaleGestureDetector arg7) {
            TouchImageView.a(this.a, ((double)arg7.getScaleFactor()), arg7.getFocusX(), arg7.getFocusY(), true);
            if(TouchImageView.p(this.a) != null) {
                TouchImageView.p(this.a).a();
            }

            return 1;
        }

        public boolean onScaleBegin(ScaleGestureDetector arg2) {
            TouchImageView.a(this.a, h.c);
            return 1;
        }

        public void onScaleEnd(ScaleGestureDetector arg9) {
            // Method was not decompiled
        }
    }

    enum h {
        public static final enum h a;
        public static final enum h b;
        public static final enum h c;
        public static final enum h d;
        public static final enum h e;

        static {
            h.a = new h("NONE", 0);
            h.b = new h("DRAG", 1);
            h.c = new h("ZOOM", 2);
            h.d = new h("FLING", 3);
            h.e = new h("ANIMATE_ZOOM", 4);
            h.f = new h[]{h.a, h.b, h.c, h.d, h.e};
        }

        private h(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static h valueOf(String arg1) {
            return Enum.valueOf(h.class, arg1);
        }

        public static h[] values() {
            // Method was not decompiled
        }
    }

    class i {
        public float a;
        public float b;
        public float c;
        public ImageView$ScaleType d;

        public i(TouchImageView arg1, float arg2, float arg3, float arg4, ImageView$ScaleType arg5) {
            this.e = arg1;
            super();
            this.a = arg2;
            this.b = arg3;
            this.c = arg4;
            this.d = arg5;
        }
    }

    private View$OnTouchListener A;
    private e B;
    private float a;
    private Matrix b;
    private Matrix c;
    private h d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float[] i;
    private Context j;
    private c k;
    private ImageView$ScaleType l;
    private boolean m;
    private boolean n;
    private i o;
    private int p;
    private int q;
    private int r;
    private int s;
    private float t;
    private float u;
    private float v;
    private float w;
    private ScaleGestureDetector x;
    private GestureDetector y;
    private GestureDetector$OnDoubleTapListener z;

    public TouchImageView(Context arg2) {
        super(arg2);
        this.z = null;
        this.A = null;
        this.B = null;
        this.a(arg2);
    }

    public TouchImageView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.z = null;
        this.A = null;
        this.B = null;
        this.a(arg1);
    }

    public TouchImageView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.z = null;
        this.A = null;
        this.B = null;
        this.a(arg1);
    }

    private void a(Context arg4) {
        super.setClickable(true);
        this.j = arg4;
        org.telegram.customization.util.view.zoom.TouchImageView$1 v2 = null;
        this.x = new ScaleGestureDetector(arg4, new g(this, v2));
        this.y = new GestureDetector(arg4, new d(this, v2));
        this.b = new Matrix();
        this.c = new Matrix();
        this.i = new float[9];
        float v4 = 1f;
        this.a = v4;
        if(this.l == null) {
            this.l = ImageView$ScaleType.FIT_CENTER;
        }

        this.e = v4;
        this.f = 3f;
        this.g = this.e * 0.75f;
        this.h = this.f * 1.25f;
        this.setImageMatrix(this.b);
        this.setScaleType(ImageView$ScaleType.MATRIX);
        this.setState(h.a);
        this.n = false;
        super.setOnTouchListener(new f(this, v2));
    }

    static float a(TouchImageView arg0, float arg1, float arg2, float arg3) {
        return arg0.c(arg1, arg2, arg3);
    }

    private int a(int arg2, int arg3, int arg4) {
        if(arg2 == -2147483648) {
            arg3 = Math.min(arg4, arg3);
        }
        else if(arg2 != 0) {
        }
        else {
            arg3 = arg4;
        }

        return arg3;
    }

    private PointF a(float arg3, float arg4) {
        this.b.getValues(this.i);
        return new PointF(this.i[2] + this.getImageWidth() * (arg3 / (((float)this.getDrawable().getIntrinsicWidth()))), this.i[5] + this.getImageHeight() * (arg4 / (((float)this.getDrawable().getIntrinsicHeight()))));
    }

    private PointF a(float arg6, float arg7, boolean arg8) {
        this.b.getValues(this.i);
        float v0 = ((float)this.getDrawable().getIntrinsicWidth());
        float v1 = ((float)this.getDrawable().getIntrinsicHeight());
        float v2 = this.i[2];
        float v3 = this.i[5];
        arg6 = (arg6 - v2) * v0 / this.getImageWidth();
        arg7 = (arg7 - v3) * v1 / this.getImageHeight();
        if(arg8) {
            arg6 = Math.min(Math.max(arg6, 0f), v0);
            arg7 = Math.min(Math.max(arg7, 0f), v1);
        }

        return new PointF(arg6, arg7);
    }

    static PointF a(TouchImageView arg0, float arg1, float arg2) {
        return arg0.a(arg1, arg2);
    }

    static PointF a(TouchImageView arg0, float arg1, float arg2, boolean arg3) {
        return arg0.a(arg1, arg2, arg3);
    }

    static GestureDetector$OnDoubleTapListener a(TouchImageView arg0) {
        return arg0.z;
    }

    static c a(TouchImageView arg0, c arg1) {
        arg0.k = arg1;
        return arg1;
    }

    private void a(double arg5, float arg7, float arg8, boolean arg9) {
        float v0;
        float v9;
        if(arg9) {
            v9 = this.g;
            v0 = this.h;
        }
        else {
            v9 = this.e;
            v0 = this.f;
        }

        float v1 = this.a;
        double v2 = ((double)this.a);
        Double.isNaN(v2);
        this.a = ((float)(v2 * arg5));
        if(this.a > v0) {
            this.a = v0;
            arg5 = ((double)(v0 / v1));
        }
        else if(this.a < v9) {
            this.a = v9;
            arg5 = ((double)(v9 / v1));
        }

        float v5 = ((float)arg5);
        this.b.postScale(v5, v5, arg7, arg8);
        this.e();
    }

    private void a(int arg3, float arg4, float arg5, float arg6, int arg7, int arg8, int arg9) {
        float v8 = ((float)arg8);
        float v1 = 0.5f;
        if(Float.compare(arg6, v8) < 0) {
            this.i[arg3] = (v8 - (((float)arg9)) * this.i[0]) * v1;
        }
        else if(arg4 > 0f) {
            this.i[arg3] = -((arg6 - v8) * v1);
        }
        else {
            this.i[arg3] = -((Math.abs(arg4) + (((float)arg7)) * v1) / arg5 * arg6 - v8 * v1);
        }
    }

    @TargetApi(value=16) private void a(Runnable arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            this.postOnAnimation(arg3);
        }
        else {
            this.postDelayed(arg3, 16);
        }
    }

    static void a(TouchImageView arg0, double arg1, float arg3, float arg4, boolean arg5) {
        arg0.a(arg1, arg3, arg4, arg5);
    }

    static void a(TouchImageView arg0, Runnable arg1) {
        arg0.a(arg1);
    }

    static void a(TouchImageView arg0, h arg1) {
        arg0.setState(arg1);
    }

    public boolean a() {
        boolean v0 = this.a != 1f ? true : false;
        return v0;
    }

    public void a(float arg2, float arg3, float arg4) {
        this.a(arg2, arg3, arg4, this.l);
    }

    public void a(float arg8, float arg9, float arg10, ImageView$ScaleType arg11) {
        if(!this.n) {
            this.o = new i(this, arg8, arg9, arg10, arg11);
            return;
        }

        if(arg11 != this.l) {
            this.setScaleType(arg11);
        }

        this.b();
        this.a(((double)arg8), ((float)(this.p / 2)), ((float)(this.q / 2)), true);
        this.b.getValues(this.i);
        this.i[2] = -(arg9 * this.getImageWidth() - (((float)this.p)) * 0.5f);
        this.i[5] = -(arg10 * this.getImageHeight() - (((float)this.q)) * 0.5f);
        this.b.setValues(this.i);
        this.d();
        this.setImageMatrix(this.b);
    }

    public boolean a(int arg1) {
        return this.canScrollHorizontally(arg1);
    }

    private float b(float arg3, float arg4, float arg5) {
        if(Float.compare(arg5, arg4) <= 0) {
            arg5 = arg4 - arg5;
            arg4 = 0f;
        }
        else {
            arg4 -= arg5;
            arg5 = 0f;
        }

        if(arg3 < arg4) {
            return -arg3 + arg4;
        }

        if(arg3 > arg5) {
            return -arg3 + arg5;
        }

        return 0;
    }

    static c b(TouchImageView arg0) {
        return arg0.k;
    }

    public void b() {
        this.a = 1f;
        this.f();
    }

    private float c(float arg1, float arg2, float arg3) {
        return 0;
    }

    static h c(TouchImageView arg0) {
        return arg0.d;
    }

    private void c() {
        if(this.b != null && this.q != 0 && this.p != 0) {
            this.b.getValues(this.i);
            this.c.setValues(this.i);
            this.w = this.u;
            this.v = this.t;
            this.s = this.q;
            this.r = this.p;
        }
    }

    public boolean canScrollHorizontally(int arg4) {
        // Method was not decompiled
    }

    static float d(TouchImageView arg0) {
        return arg0.a;
    }

    private void d() {
        this.b.getValues(this.i);
        float v0 = this.i[2];
        float v1 = this.i[5];
        v0 = this.b(v0, ((float)this.p), this.getImageWidth());
        v1 = this.b(v1, ((float)this.q), this.getImageHeight());
        if(v0 != 0f || v1 != 0f) {
            this.b.postTranslate(v0, v1);
        }
    }

    private void e() {
        // Method was not decompiled
    }

    static float e(TouchImageView arg0) {
        return arg0.e;
    }

    static float f(TouchImageView arg0) {
        return arg0.f;
    }

    private void f() {
        Drawable v0 = this.getDrawable();
        if(v0 != null && v0.getIntrinsicWidth() != 0) {
            if(v0.getIntrinsicHeight() == 0) {
            }
            else if(this.b != null) {
                if(this.c == null) {
                }
                else {
                    int v9 = v0.getIntrinsicWidth();
                    int v0_1 = v0.getIntrinsicHeight();
                    float v2 = ((float)v9);
                    float v1 = (((float)this.p)) / v2;
                    float v4 = ((float)v0_1);
                    float v3 = (((float)this.q)) / v4;
                    float v6 = 1f;
                    switch(org.telegram.customization.util.view.zoom.TouchImageView$1.a[this.l.ordinal()]) {
                        case 1: {
                            goto label_40;
                        }
                        case 2: {
                            goto label_37;
                        }
                        case 3: {
                            goto label_32;
                        }
                        case 4: {
                            goto label_35;
                        }
                        case 5: {
                            goto label_42;
                        }
                    }

                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
                label_37:
                    v1 = Math.max(v1, v3);
                    goto label_38;
                label_40:
                    v1 = 1f;
                    v3 = 1f;
                    goto label_42;
                label_32:
                    v1 = Math.min(v6, Math.min(v1, v3));
                    v3 = v1;
                label_35:
                    v1 = Math.min(v1, v3);
                label_38:
                    v3 = v1;
                label_42:
                    float v5 = (((float)this.p)) - v1 * v2;
                    float v7 = (((float)this.q)) - v3 * v4;
                    this.t = (((float)this.p)) - v5;
                    this.u = (((float)this.q)) - v7;
                    if((this.a()) || (this.m)) {
                        if(this.v == 0f || this.w == 0f) {
                            this.c();
                        }

                        this.c.getValues(this.i);
                        this.i[0] = this.t / v2 * this.a;
                        this.i[4] = this.u / v4 * this.a;
                        v4 = this.i[2];
                        float v10 = this.i[5];
                        this.a(2, v4, this.v * this.a, this.getImageWidth(), this.r, this.p, v9);
                        this.a(5, v10, this.w * this.a, this.getImageHeight(), this.s, this.q, v0_1);
                        this.b.setValues(this.i);
                    }
                    else {
                        this.b.setScale(v1, v3);
                        this.b.postTranslate(v5 / 2f, v7 / 2f);
                        this.a = v6;
                    }

                    this.d();
                    this.setImageMatrix(this.b);
                }
            }
        }
    }

    static ScaleGestureDetector g(TouchImageView arg0) {
        return arg0.x;
    }

    public float getCurrentZoom() {
        return this.a;
    }

    private float getImageHeight() {
        return this.u * this.a;
    }

    private float getImageWidth() {
        return this.t * this.a;
    }

    public float getMaxZoom() {
        return this.f;
    }

    public float getMinZoom() {
        return this.e;
    }

    public ImageView$ScaleType getScaleType() {
        return this.l;
    }

    public PointF getScrollPosition() {
        Drawable v0 = this.getDrawable();
        if(v0 == null) {
            return null;
        }

        int v1 = v0.getIntrinsicWidth();
        int v0_1 = v0.getIntrinsicHeight();
        PointF v2 = this.a(((float)(this.p / 2)), ((float)(this.q / 2)), true);
        v2.x /= ((float)v1);
        v2.y /= ((float)v0_1);
        return v2;
    }

    public RectF getZoomedRect() {
        if(this.l != ImageView$ScaleType.FIT_XY) {
            PointF v1 = this.a(0f, 0f, true);
            PointF v0 = this.a(((float)this.p), ((float)this.q), true);
            float v2 = ((float)this.getDrawable().getIntrinsicWidth());
            float v3 = ((float)this.getDrawable().getIntrinsicHeight());
            return new RectF(v1.x / v2, v1.y / v3, v0.x / v2, v0.y / v3);
        }

        throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
    }

    static GestureDetector h(TouchImageView arg0) {
        return arg0.y;
    }

    static int i(TouchImageView arg0) {
        return arg0.p;
    }

    static float j(TouchImageView arg0) {
        return arg0.getImageWidth();
    }

    static int k(TouchImageView arg0) {
        return arg0.q;
    }

    static float l(TouchImageView arg0) {
        return arg0.getImageHeight();
    }

    static Matrix m(TouchImageView arg0) {
        return arg0.b;
    }

    static void n(TouchImageView arg0) {
        arg0.d();
    }

    static View$OnTouchListener o(TouchImageView arg0) {
        return arg0.A;
    }

    public void onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
        this.c();
    }

    protected void onDraw(Canvas arg5) {
        this.n = true;
        this.m = true;
        if(this.o != null) {
            this.a(this.o.a, this.o.b, this.o.c, this.o.d);
            this.o = null;
        }

        super.onDraw(arg5);
    }

    protected void onMeasure(int arg5, int arg6) {
        Drawable v0 = this.getDrawable();
        if(v0 != null && v0.getIntrinsicWidth() != 0) {
            if(v0.getIntrinsicHeight() == 0) {
            }
            else {
                int v1 = v0.getIntrinsicWidth();
                int v0_1 = v0.getIntrinsicHeight();
                int v2 = View$MeasureSpec.getSize(arg5);
                arg5 = View$MeasureSpec.getMode(arg5);
                int v3 = View$MeasureSpec.getSize(arg6);
                arg6 = View$MeasureSpec.getMode(arg6);
                this.p = this.a(arg5, v2, v1);
                this.q = this.a(arg6, v3, v0_1);
                this.setMeasuredDimension(this.p, this.q);
                this.f();
                return;
            }
        }

        this.setMeasuredDimension(0, 0);
    }

    public void onRestoreInstanceState(Parcelable arg3) {
        if((arg3 instanceof Bundle)) {
            this.a = ((Bundle)arg3).getFloat("saveScale");
            this.i = ((Bundle)arg3).getFloatArray("matrix");
            this.c.setValues(this.i);
            this.w = ((Bundle)arg3).getFloat("matchViewHeight");
            this.v = ((Bundle)arg3).getFloat("matchViewWidth");
            this.s = ((Bundle)arg3).getInt("viewHeight");
            this.r = ((Bundle)arg3).getInt("viewWidth");
            this.m = ((Bundle)arg3).getBoolean("imageRendered");
            super.onRestoreInstanceState(((Bundle)arg3).getParcelable("instanceState"));
            return;
        }

        super.onRestoreInstanceState(arg3);
    }

    public Parcelable onSaveInstanceState() {
        Bundle v0 = new Bundle();
        v0.putParcelable("instanceState", super.onSaveInstanceState());
        v0.putFloat("saveScale", this.a);
        v0.putFloat("matchViewHeight", this.u);
        v0.putFloat("matchViewWidth", this.t);
        v0.putInt("viewWidth", this.p);
        v0.putInt("viewHeight", this.q);
        this.b.getValues(this.i);
        v0.putFloatArray("matrix", this.i);
        v0.putBoolean("imageRendered", this.m);
        return ((Parcelable)v0);
    }

    static e p(TouchImageView arg0) {
        return arg0.B;
    }

    static void q(TouchImageView arg0) {
        arg0.e();
    }

    static Context r(TouchImageView arg0) {
        return arg0.j;
    }

    static float[] s(TouchImageView arg0) {
        return arg0.i;
    }

    public void setImageBitmap(Bitmap arg1) {
        super.setImageBitmap(arg1);
        this.c();
        this.f();
    }

    public void setImageDrawable(Drawable arg1) {
        super.setImageDrawable(arg1);
        this.c();
        this.f();
    }

    public void setImageResource(int arg1) {
        super.setImageResource(arg1);
        this.c();
        this.f();
    }

    public void setImageURI(Uri arg1) {
        super.setImageURI(arg1);
        this.c();
        this.f();
    }

    public void setMaxZoom(float arg2) {
        this.f = arg2;
        this.h = this.f * 1.25f;
    }

    public void setMinZoom(float arg2) {
        this.e = arg2;
        this.g = this.e * 0.75f;
    }

    public void setOnDoubleTapListener(GestureDetector$OnDoubleTapListener arg1) {
        this.z = arg1;
    }

    public void setOnTouchImageViewListener(e arg1) {
        this.B = arg1;
    }

    public void setOnTouchListener(View$OnTouchListener arg1) {
        this.A = arg1;
    }

    public void setScaleType(ImageView$ScaleType arg2) {
        if(arg2 != ImageView$ScaleType.FIT_START && arg2 != ImageView$ScaleType.FIT_END) {
            if(arg2 == ImageView$ScaleType.MATRIX) {
                super.setScaleType(ImageView$ScaleType.MATRIX);
            }
            else {
                this.l = arg2;
                if(this.n) {
                    this.setZoom(this);
                }
            }

            return;
        }

        throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
    }

    private void setState(h arg1) {
        this.d = arg1;
    }

    public void setZoom(TouchImageView arg4) {
        PointF v0 = arg4.getScrollPosition();
        this.a(arg4.getCurrentZoom(), v0.x, v0.y, arg4.getScaleType());
    }

    public void setZoom(float arg2) {
        this.a(arg2, 0.5f, 0.5f);
    }
}

