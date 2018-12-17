package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.design.a$d;
import android.support.design.a$f;
import android.support.design.a.a;
import android.support.design.a.b;
import android.support.v4.view.t;
import android.support.v4.widget.Space;
import android.support.v4.widget.q;
import android.support.v7.widget.x;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

final class k {
    private final Context a;
    private final TextInputLayout b;
    private LinearLayout c;
    private int d;
    private FrameLayout e;
    private int f;
    private Animator g;
    private final float h;
    private int i;
    private int j;
    private CharSequence k;
    private boolean l;
    private TextView m;
    private int n;
    private CharSequence o;
    private boolean p;
    private TextView q;
    private int r;
    private Typeface s;

    public k(TextInputLayout arg2) {
        super();
        this.a = arg2.getContext();
        this.b = arg2;
        this.h = ((float)this.a.getResources().getDimensionPixelSize(d.design_textinput_caption_translate_y));
    }

    void a(TextView arg7, int arg8) {
        if(this.c == null && this.e == null) {
            this.c = new LinearLayout(this.a);
            this.c.setOrientation(0);
            this.b.addView(this.c, -1, -2);
            this.e = new FrameLayout(this.a);
            this.c.addView(this.e, -1, new FrameLayout$LayoutParams(-2, -2));
            this.c.addView(new Space(this.a), new LinearLayout$LayoutParams(0, 0, 1f));
            if(this.b.getEditText() != null) {
                this.d();
            }
        }

        if(this.a(arg8)) {
            this.e.setVisibility(0);
            this.e.addView(((View)arg7));
            ++this.f;
        }
        else {
            this.c.addView(((View)arg7), arg8);
        }

        this.c.setVisibility(0);
        ++this.d;
    }

    void a(boolean arg4) {
        if(this.l == arg4) {
            return;
        }

        this.c();
        if(arg4) {
            this.m = new x(this.a);
            this.m.setId(f.textinput_error);
            if(this.s != null) {
                this.m.setTypeface(this.s);
            }

            this.b(this.n);
            this.m.setVisibility(4);
            t.c(this.m, 1);
            this.a(this.m, 0);
        }
        else {
            this.b();
            this.b(this.m, 0);
            this.m = null;
            this.b.c();
            this.b.d();
        }

        this.l = arg4;
    }

    void a(ColorStateList arg2) {
        if(this.m != null) {
            this.m.setTextColor(arg2);
        }
    }

    void a(CharSequence arg4) {
        this.c();
        this.o = arg4;
        this.q.setText(arg4);
        int v1 = 2;
        if(this.i != v1) {
            this.j = v1;
        }

        this.a(this.i, this.j, this.a(this.q, arg4));
    }

    void a(Typeface arg2) {
        if(arg2 != this.s) {
            this.s = arg2;
            this.a(this.m, arg2);
            this.a(this.q, arg2);
        }
    }

    static int a(k arg0, int arg1) {
        arg0.i = arg1;
        return arg1;
    }

    static Animator a(k arg0, Animator arg1) {
        arg0.g = arg1;
        return arg1;
    }

    private ObjectAnimator a(TextView arg5) {
        ObjectAnimator v5 = ObjectAnimator.ofFloat(arg5, View.TRANSLATION_Y, new float[]{-this.h, 0f});
        v5.setDuration(217);
        v5.setInterpolator(a.d);
        return v5;
    }

    private ObjectAnimator a(TextView arg4, boolean arg5) {
        float v5 = arg5 ? 1f : 0f;
        ObjectAnimator v4 = ObjectAnimator.ofFloat(arg4, View.ALPHA, new float[]{v5});
        v4.setDuration(167);
        v4.setInterpolator(a.a);
        return v4;
    }

    static TextView a(k arg0) {
        return arg0.m;
    }

    private void a(int arg3, int arg4) {
        TextView v0;
        if(arg3 == arg4) {
            return;
        }

        if(arg4 != 0) {
            v0 = this.d(arg4);
            if(v0 != null) {
                v0.setVisibility(0);
                v0.setAlpha(1f);
            }
        }

        if(arg3 != 0) {
            v0 = this.d(arg3);
            if(v0 != null) {
                v0.setVisibility(4);
                if(arg3 == 1) {
                    v0.setText(null);
                }
            }
        }

        this.i = arg4;
    }

    private void a(int arg10, int arg11, boolean arg12) {
        if(arg12) {
            AnimatorSet v7 = new AnimatorSet();
            this.g = ((Animator)v7);
            ArrayList v8 = new ArrayList();
            this.a(v8, this.p, this.q, 2, arg10, arg11);
            this.a(v8, this.l, this.m, 1, arg10, arg11);
            b.a(v7, ((List)v8));
            v7.addListener(new AnimatorListenerAdapter(arg11, this.d(arg10), arg10, this.d(arg11)) {
                public void onAnimationEnd(Animator arg3) {
                    k.a(this.e, this.a);
                    Animator v0 = null;
                    k.a(this.e, v0);
                    if(this.b != null) {
                        this.b.setVisibility(4);
                        if(this.c == 1 && k.a(this.e) != null) {
                            k.a(this.e).setText(((CharSequence)v0));
                        }
                    }
                }

                public void onAnimationStart(Animator arg2) {
                    if(this.d != null) {
                        this.d.setVisibility(0);
                    }
                }
            });
            v7.start();
        }
        else {
            this.a(arg10, arg11);
        }

        this.b.c();
        this.b.a(arg12);
        this.b.d();
    }

    private void a(List arg1, boolean arg2, TextView arg3, int arg4, int arg5, int arg6) {
        if(arg3 != null) {
            if(!arg2) {
            }
            else {
                if(arg4 != arg6 && arg4 != arg5) {
                    return;
                }

                arg2 = arg6 == arg4 ? true : false;
                arg1.add(this.a(arg3, arg2));
                if(arg6 != arg4) {
                    return;
                }

                arg1.add(this.a(arg3));
            }
        }
    }

    private void a(ViewGroup arg1, int arg2) {
        if(arg2 == 0) {
            arg1.setVisibility(8);
        }
    }

    private void a(TextView arg1, Typeface arg2) {
        if(arg1 != null) {
            arg1.setTypeface(arg2);
        }
    }

    private boolean a(TextView arg3, CharSequence arg4) {
        boolean v3;
        if(!t.A(this.b) || !this.b.isEnabled()) {
        label_15:
            v3 = false;
        }
        else {
            if(this.j == this.i && arg3 != null && (TextUtils.equals(arg3.getText(), arg4))) {
                goto label_15;
            }

            v3 = true;
        }

        return v3;
    }

    void a() {
        this.c();
        if(this.i == 2) {
            this.j = 0;
        }

        this.a(this.i, this.j, this.a(this.q, null));
    }

    boolean a(int arg2) {
        boolean v0 = true;
        if(arg2 != 0) {
            if(arg2 == 1) {
            }
            else {
                v0 = false;
            }
        }

        return v0;
    }

    void b(TextView arg2, int arg3) {
        if(this.c == null) {
            return;
        }

        if(!this.a(arg3) || this.e == null) {
            this.c.removeView(((View)arg2));
        }
        else {
            --this.f;
            this.a(this.e, this.f);
            this.e.removeView(((View)arg2));
        }

        --this.d;
        this.a(this.c, this.d);
    }

    void b(CharSequence arg4) {
        this.c();
        this.k = arg4;
        this.m.setText(arg4);
        if(this.i != 1) {
            this.j = 1;
        }

        this.a(this.i, this.j, this.a(this.m, arg4));
    }

    void b() {
        CharSequence v0 = null;
        this.k = v0;
        this.c();
        if(this.i == 1) {
            int v1 = !this.p || (TextUtils.isEmpty(this.o)) ? 0 : 2;
            this.j = v1;
        }

        this.a(this.i, this.j, this.a(this.m, v0));
    }

    void b(int arg3) {
        this.n = arg3;
        if(this.m != null) {
            this.b.a(this.m, arg3);
        }
    }

    void b(ColorStateList arg2) {
        if(this.q != null) {
            this.q.setTextColor(arg2);
        }
    }

    void b(boolean arg4) {
        if(this.p == arg4) {
            return;
        }

        this.c();
        if(arg4) {
            this.q = new x(this.a);
            this.q.setId(f.textinput_helper_text);
            if(this.s != null) {
                this.q.setTypeface(this.s);
            }

            this.q.setVisibility(4);
            t.c(this.q, 1);
            this.c(this.r);
            this.a(this.q, 1);
        }
        else {
            this.a();
            this.b(this.q, 1);
            this.q = null;
            this.b.c();
            this.b.d();
        }

        this.p = arg4;
    }

    void c(int arg2) {
        this.r = arg2;
        if(this.q != null) {
            q.a(this.q, arg2);
        }
    }

    void c() {
        if(this.g != null) {
            this.g.cancel();
        }
    }

    void d() {
        if(this.m()) {
            t.a(this.c, t.h(this.b.getEditText()), 0, t.i(this.b.getEditText()), 0);
        }
    }

    private TextView d(int arg1) {
        switch(arg1) {
            case 1: {
                goto label_5;
            }
            case 2: {
                goto label_3;
            }
        }

        return null;
    label_3:
        return this.q;
    label_5:
        return this.m;
    }

    boolean e() {
        return this.l;
    }

    private boolean e(int arg2) {
        boolean v0 = true;
        if(arg2 != 1 || this.m == null || (TextUtils.isEmpty(this.k))) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    boolean f() {
        return this.p;
    }

    boolean g() {
        return this.e(this.j);
    }

    CharSequence h() {
        return this.k;
    }

    CharSequence i() {
        return this.o;
    }

    int j() {
        int v0 = this.m != null ? this.m.getCurrentTextColor() : -1;
        return v0;
    }

    ColorStateList k() {
        ColorStateList v0 = this.m != null ? this.m.getTextColors() : null;
        return v0;
    }

    int l() {
        int v0 = this.q != null ? this.q.getCurrentTextColor() : -1;
        return v0;
    }

    private boolean m() {
        boolean v0 = this.c == null || this.b.getEditText() == null ? false : true;
        return v0;
    }
}

