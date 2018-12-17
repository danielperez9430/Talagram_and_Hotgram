package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.content.a.f$a;
import android.support.v4.widget.b;
import android.support.v4.widget.q;
import android.support.v7.a.a$j;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class w {
    private final TextView a;
    private bi b;
    private bi c;
    private bi d;
    private bi e;
    private bi f;
    private bi g;
    private final y h;
    private int i;
    private Typeface j;
    private boolean k;

    w(TextView arg2) {
        super();
        this.i = 0;
        this.a = arg2;
        this.h = new y(this.a);
    }

    @SuppressLint(value={"NewApi"}) void a(AttributeSet arg18, int arg19) {
        ColorStateList v14;
        ColorStateList v13;
        int v11;
        boolean v12;
        bk v7_1;
        w v0 = this;
        AttributeSet v1 = arg18;
        int v2 = arg19;
        Context v3 = v0.a.getContext();
        k v4 = k.a();
        bk v5 = bk.a(v3, v1, j.AppCompatTextHelper, v2, 0);
        int v8 = -1;
        int v7 = v5.g(j.AppCompatTextHelper_android_textAppearance, v8);
        if(v5.g(j.AppCompatTextHelper_android_drawableLeft)) {
            v0.b = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableLeft, 0));
        }

        if(v5.g(j.AppCompatTextHelper_android_drawableTop)) {
            v0.c = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableTop, 0));
        }

        if(v5.g(j.AppCompatTextHelper_android_drawableRight)) {
            v0.d = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableRight, 0));
        }

        if(v5.g(j.AppCompatTextHelper_android_drawableBottom)) {
            v0.e = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableBottom, 0));
        }

        if(Build$VERSION.SDK_INT >= 17) {
            if(v5.g(j.AppCompatTextHelper_android_drawableStart)) {
                v0.f = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableStart, 0));
            }

            if(!v5.g(j.AppCompatTextHelper_android_drawableEnd)) {
                goto label_57;
            }

            v0.g = w.a(v3, v4, v5.g(j.AppCompatTextHelper_android_drawableEnd, 0));
        }

    label_57:
        v5.a();
        boolean v4_1 = v0.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        int v5_1 = 1;
        int v9 = 23;
        ColorStateList v10 = null;
        if(v7 != v8) {
            v7_1 = bk.a(v3, v7, j.TextAppearance);
            if((v4_1) || !v7_1.g(j.TextAppearance_textAllCaps)) {
                v11 = 0;
                v12 = false;
            }
            else {
                v12 = v7_1.a(j.TextAppearance_textAllCaps, false);
                v11 = 1;
            }

            v0.a(v3, v7_1);
            if(Build$VERSION.SDK_INT < v9) {
                v13 = v7_1.g(j.TextAppearance_android_textColor) ? v7_1.e(j.TextAppearance_android_textColor) : v10;
                v14 = v7_1.g(j.TextAppearance_android_textColorHint) ? v7_1.e(j.TextAppearance_android_textColorHint) : v10;
                if(v7_1.g(j.TextAppearance_android_textColorLink)) {
                    v10 = v7_1.e(j.TextAppearance_android_textColorLink);
                }

                ColorStateList v16 = v13;
                v13 = v10;
                v10 = v16;
            }
            else {
                v13 = v10;
                v14 = v13;
            }

            v7_1.a();
        }
        else {
            v13 = v10;
            v14 = v13;
            v11 = 0;
            v12 = false;
        }

        v7_1 = bk.a(v3, v1, j.TextAppearance, v2, 0);
        if((v4_1) || !v7_1.g(j.TextAppearance_textAllCaps)) {
            v5_1 = v11;
        }
        else {
            v12 = v7_1.a(j.TextAppearance_textAllCaps, false);
        }

        if(Build$VERSION.SDK_INT < v9) {
            if(v7_1.g(j.TextAppearance_android_textColor)) {
                v10 = v7_1.e(j.TextAppearance_android_textColor);
            }

            if(v7_1.g(j.TextAppearance_android_textColorHint)) {
                v14 = v7_1.e(j.TextAppearance_android_textColorHint);
            }

            if(!v7_1.g(j.TextAppearance_android_textColorLink)) {
                goto label_141;
            }

            v13 = v7_1.e(j.TextAppearance_android_textColorLink);
        }

    label_141:
        if(Build$VERSION.SDK_INT >= 28 && (v7_1.g(j.TextAppearance_android_textSize)) && v7_1.e(j.TextAppearance_android_textSize, v8) == 0) {
            v0.a.setTextSize(0, 0f);
        }

        v0.a(v3, v7_1);
        v7_1.a();
        if(v10 != null) {
            v0.a.setTextColor(v10);
        }

        if(v14 != null) {
            v0.a.setHintTextColor(v14);
        }

        if(v13 != null) {
            v0.a.setLinkTextColor(v13);
        }

        if(!v4_1 && v5_1 != 0) {
            v0.a(v12);
        }

        if(v0.j != null) {
            v0.a.setTypeface(v0.j, v0.i);
        }

        v0.h.a(v1, v2);
        if((b.a) && v0.h.a() != 0) {
            int[] v2_1 = v0.h.e();
            if(v2_1.length > 0) {
                if((((float)v0.a.getAutoSizeStepGranularity())) != -1f) {
                    v0.a.setAutoSizeTextTypeUniformWithConfiguration(v0.h.c(), v0.h.d(), v0.h.b(), 0);
                }
                else {
                    v0.a.setAutoSizeTextTypeUniformWithPresetSizes(v2_1, 0);
                }
            }
        }

        bk v1_1 = bk.a(v3, v1, j.AppCompatTextView);
        v2 = v1_1.e(j.AppCompatTextView_firstBaselineToTopHeight, v8);
        int v3_1 = v1_1.e(j.AppCompatTextView_lastBaselineToBottomHeight, v8);
        int v4_2 = v1_1.e(j.AppCompatTextView_lineHeight, v8);
        v1_1.a();
        if(v2 != v8) {
            q.b(v0.a, v2);
        }

        if(v3_1 != v8) {
            q.c(v0.a, v3_1);
        }

        if(v4_2 != v8) {
            q.d(v0.a, v4_2);
        }
    }

    void a() {
        Drawable[] v0;
        int v1 = 2;
        if(this.b != null || this.c != null || this.d != null || this.e != null) {
            v0 = this.a.getCompoundDrawables();
            this.a(v0[0], this.b);
            this.a(v0[1], this.c);
            this.a(v0[v1], this.d);
            this.a(v0[3], this.e);
        }

        if(Build$VERSION.SDK_INT >= 17 && (this.f != null || this.g != null)) {
            v0 = this.a.getCompoundDrawablesRelative();
            this.a(v0[0], this.f);
            this.a(v0[v1], this.g);
        }
    }

    void a(Context arg4, int arg5) {
        bk v5 = bk.a(arg4, arg5, j.TextAppearance);
        if(v5.g(j.TextAppearance_textAllCaps)) {
            this.a(v5.a(j.TextAppearance_textAllCaps, false));
        }

        if(Build$VERSION.SDK_INT < 23 && (v5.g(j.TextAppearance_android_textColor))) {
            ColorStateList v0 = v5.e(j.TextAppearance_android_textColor);
            if(v0 != null) {
                this.a.setTextColor(v0);
            }
        }

        if((v5.g(j.TextAppearance_android_textSize)) && v5.e(j.TextAppearance_android_textSize, -1) == 0) {
            this.a.setTextSize(0, 0f);
        }

        this.a(arg4, v5);
        v5.a();
        if(this.j != null) {
            this.a.setTypeface(this.j, this.i);
        }
    }

    private static bi a(Context arg0, k arg1, int arg2) {
        ColorStateList v0 = arg1.b(arg0, arg2);
        if(v0 != null) {
            bi v1 = new bi();
            v1.d = true;
            v1.a = v0;
            return v1;
        }

        return null;
    }

    private void a(Context arg5, bk arg6) {
        this.i = arg6.a(j.TextAppearance_android_textStyle, this.i);
        boolean v1 = true;
        if(!arg6.g(j.TextAppearance_android_fontFamily)) {
            if(arg6.g(j.TextAppearance_fontFamily)) {
            }
            else {
                if(arg6.g(j.TextAppearance_android_typeface)) {
                    this.k = false;
                    switch(arg6.a(j.TextAppearance_android_typeface, 1)) {
                        case 1: {
                            goto label_25;
                        }
                        case 2: {
                            goto label_23;
                        }
                        case 3: {
                            goto label_21;
                        }
                    }

                    return;
                label_21:
                    Typeface v5 = Typeface.MONOSPACE;
                    goto label_26;
                label_23:
                    v5 = Typeface.SERIF;
                    goto label_26;
                label_25:
                    v5 = Typeface.SANS_SERIF;
                label_26:
                    this.j = v5;
                }

                return;
            }
        }

        this.j = null;
        int v0 = arg6.g(j.TextAppearance_fontFamily) ? j.TextAppearance_fontFamily : j.TextAppearance_android_fontFamily;
        if(!arg5.isRestricted()) {
            android.support.v7.widget.w$1 v3 = new a(new WeakReference(this.a)) {
                public void a(int arg1) {
                }

                public void a(Typeface arg3) {
                    this.b.a(this.a, arg3);
                }
            };
            try {
                this.j = arg6.a(v0, this.i, ((a)v3));
                if(this.j == null) {
                }
                else {
                    goto label_49;
                }

            label_50:
                this.k = v1;
                goto label_51;
            label_49:
                v1 = false;
                goto label_50;
            }
            catch(Resources$NotFoundException ) {
            label_51:
                if(this.j == null) {
                    String v5_1 = arg6.d(v0);
                    if(v5_1 != null) {
                        this.j = Typeface.create(v5_1, this.i);
                    }
                }

                return;
            }
        }

        goto label_51;
    }

    void a(WeakReference arg2, Typeface arg3) {
        if(this.k) {
            this.j = arg3;
            Object v2 = arg2.get();
            if(v2 != null) {
                ((TextView)v2).setTypeface(arg3, this.i);
            }
        }
    }

    private void a(Drawable arg2, bi arg3) {
        if(arg2 != null && arg3 != null) {
            k.a(arg2, arg3, this.a.getDrawableState());
        }
    }

    void a(int arg2) {
        this.h.a(arg2);
    }

    void a(int arg2, float arg3) {
        if(!b.a && !this.c()) {
            this.b(arg2, arg3);
        }
    }

    void a(int arg2, int arg3, int arg4, int arg5) {
        this.h.a(arg2, arg3, arg4, arg5);
    }

    void a(boolean arg2) {
        this.a.setAllCaps(arg2);
    }

    void a(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        if(!b.a) {
            this.b();
        }
    }

    void a(int[] arg2, int arg3) {
        this.h.a(arg2, arg3);
    }

    private void b(int arg2, float arg3) {
        this.h.a(arg2, arg3);
    }

    void b() {
        this.h.f();
    }

    boolean c() {
        return this.h.g();
    }

    int d() {
        return this.h.a();
    }

    int e() {
        return this.h.b();
    }

    int f() {
        return this.h.c();
    }

    int g() {
        return this.h.d();
    }

    int[] h() {
        return this.h.e();
    }
}

