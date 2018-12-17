package android.support.design.widget;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.design.a$b;
import android.support.design.a$d;
import android.support.design.a$h;
import android.support.design.a$i;
import android.support.design.a$j;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.a.c;
import android.support.v4.view.t;
import android.support.v4.widget.q;
import android.support.v7.widget.ai;
import android.support.v7.widget.bk;
import android.support.v7.widget.x;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextInputLayout extends LinearLayout {
    class SavedState extends AbsSavedState {
        final class android.support.design.widget.TextInputLayout$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.design.widget.TextInputLayout$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState a(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.a(arg1, arg2);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        CharSequence a;
        boolean b;

        static {
            SavedState.CREATOR = new android.support.design.widget.TextInputLayout$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.a = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(arg1);
            boolean v2 = true;
            if(arg1.readInt() == 1) {
            }
            else {
                v2 = false;
            }

            this.b = v2;
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.a + "}";
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            super.writeToParcel(arg2, arg3);
            TextUtils.writeToParcel(this.a, arg2, arg3);
            arg2.writeInt(this.b);
        }
    }

    public class a extends android.support.v4.view.a {
        private final TextInputLayout a;

        public a(TextInputLayout arg1) {
            super();
            this.a = arg1;
        }

        public void onInitializeAccessibilityNodeInfo(View arg10, c arg11) {
            CharSequence v10_2;
            super.onInitializeAccessibilityNodeInfo(arg10, arg11);
            EditText v10 = this.a.getEditText();
            if(v10 != null) {
                Editable v10_1 = v10.getText();
            }
            else {
                v10_2 = null;
            }

            CharSequence v0 = this.a.getHint();
            CharSequence v1 = this.a.getError();
            CharSequence v2 = this.a.getCounterOverflowDescription();
            int v3 = TextUtils.isEmpty(v10_2) ^ 1;
            int v5 = TextUtils.isEmpty(v0) ^ 1;
            int v6 = TextUtils.isEmpty(v1) ^ 1;
            boolean v7 = false;
            int v8 = v6 != 0 || !TextUtils.isEmpty(v2) ? 1 : 0;
            if(v3 != 0) {
                arg11.c(v10_2);
            }
            else if(v5 != 0) {
                arg11.c(v0);
            }

            if(v5 != 0) {
                arg11.e(v0);
                if(v3 == 0 && v5 != 0) {
                    v7 = true;
                }

                arg11.k(v7);
            }

            if(v8 != 0) {
                if(v6 != 0) {
                }
                else {
                    v1 = v2;
                }

                arg11.f(v1);
                arg11.j(true);
            }
        }

        public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            CharSequence v2_2;
            Editable v2_1;
            super.onPopulateAccessibilityEvent(arg2, arg3);
            EditText v2 = this.a.getEditText();
            if(v2 != null) {
                v2_1 = v2.getText();
            }
            else {
                v2_2 = null;
            }

            if(TextUtils.isEmpty(((CharSequence)v2_1))) {
                v2_2 = this.a.getHint();
            }

            if(!TextUtils.isEmpty(((CharSequence)v2_1))) {
                arg3.getText().add(v2_1);
            }
        }
    }

    private int A;
    private int B;
    private Drawable C;
    private final Rect D;
    private final RectF E;
    private Typeface F;
    private boolean G;
    private Drawable H;
    private CharSequence I;
    private CheckableImageButton J;
    private boolean K;
    private Drawable L;
    private Drawable M;
    private ColorStateList N;
    private boolean O;
    private PorterDuff$Mode P;
    private boolean Q;
    private ColorStateList R;
    private ColorStateList S;
    private final int T;
    private final int U;
    private int V;
    private final int W;
    EditText a;
    private boolean aa;
    private boolean ab;
    private ValueAnimator ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    boolean b;
    final android.support.design.widget.c c;
    private final FrameLayout d;
    private CharSequence e;
    private final k f;
    private int g;
    private boolean h;
    private TextView i;
    private final int j;
    private final int k;
    private boolean l;
    private CharSequence m;
    private boolean n;
    private GradientDrawable o;
    private final int p;
    private final int q;
    private int r;
    private final int s;
    private float t;
    private float u;
    private float v;
    private float w;
    private int x;
    private final int y;
    private final int z;

    public TextInputLayout(Context arg2) {
        this(arg2, null);
    }

    public TextInputLayout(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, b.textInputStyle);
    }

    public TextInputLayout(Context arg11, AttributeSet arg12, int arg13) {
        super(arg11, arg12, arg13);
        this.f = new k(this);
        this.D = new Rect();
        this.E = new RectF();
        this.c = new android.support.design.widget.c(((View)this));
        this.setOrientation(1);
        this.setWillNotDraw(false);
        this.setAddStatesFromChildren(true);
        this.d = new FrameLayout(arg11);
        this.d.setAddStatesFromChildren(true);
        this.addView(this.d);
        this.c.a(android.support.design.a.a.a);
        this.c.b(android.support.design.a.a.a);
        this.c.b(8388659);
        bk v12 = android.support.design.internal.b.b(arg11, arg12, android.support.design.a$k.TextInputLayout, arg13, j.Widget_Design_TextInputLayout, new int[0]);
        this.l = v12.a(android.support.design.a$k.TextInputLayout_hintEnabled, true);
        this.setHint(v12.c(android.support.design.a$k.TextInputLayout_android_hint));
        this.ab = v12.a(android.support.design.a$k.TextInputLayout_hintAnimationEnabled, true);
        this.p = arg11.getResources().getDimensionPixelOffset(d.mtrl_textinput_box_bottom_offset);
        this.q = arg11.getResources().getDimensionPixelOffset(d.mtrl_textinput_box_label_cutout_padding);
        this.s = v12.d(android.support.design.a$k.TextInputLayout_boxCollapsedPaddingTop, 0);
        this.t = v12.b(android.support.design.a$k.TextInputLayout_boxCornerRadiusTopStart, 0f);
        this.u = v12.b(android.support.design.a$k.TextInputLayout_boxCornerRadiusTopEnd, 0f);
        this.v = v12.b(android.support.design.a$k.TextInputLayout_boxCornerRadiusBottomEnd, 0f);
        this.w = v12.b(android.support.design.a$k.TextInputLayout_boxCornerRadiusBottomStart, 0f);
        this.B = v12.b(android.support.design.a$k.TextInputLayout_boxBackgroundColor, 0);
        this.V = v12.b(android.support.design.a$k.TextInputLayout_boxStrokeColor, 0);
        this.y = arg11.getResources().getDimensionPixelSize(d.mtrl_textinput_box_stroke_width_default);
        this.z = arg11.getResources().getDimensionPixelSize(d.mtrl_textinput_box_stroke_width_focused);
        this.x = this.y;
        this.setBoxBackgroundMode(v12.a(android.support.design.a$k.TextInputLayout_boxBackgroundMode, 0));
        if(v12.g(android.support.design.a$k.TextInputLayout_android_textColorHint)) {
            ColorStateList v13 = v12.e(android.support.design.a$k.TextInputLayout_android_textColorHint);
            this.S = v13;
            this.R = v13;
        }

        this.T = android.support.v4.content.a.c(arg11, android.support.design.a$c.mtrl_textinput_default_box_stroke_color);
        this.W = android.support.v4.content.a.c(arg11, android.support.design.a$c.mtrl_textinput_disabled_color);
        this.U = android.support.v4.content.a.c(arg11, android.support.design.a$c.mtrl_textinput_hovered_box_stroke_color);
        arg13 = -1;
        if(v12.g(android.support.design.a$k.TextInputLayout_hintTextAppearance, arg13) != arg13) {
            this.setHintTextAppearance(v12.g(android.support.design.a$k.TextInputLayout_hintTextAppearance, 0));
        }

        int v11 = v12.g(android.support.design.a$k.TextInputLayout_errorTextAppearance, 0);
        boolean v2 = v12.a(android.support.design.a$k.TextInputLayout_errorEnabled, false);
        int v3 = v12.g(android.support.design.a$k.TextInputLayout_helperTextTextAppearance, 0);
        boolean v4 = v12.a(android.support.design.a$k.TextInputLayout_helperTextEnabled, false);
        CharSequence v5 = v12.c(android.support.design.a$k.TextInputLayout_helperText);
        boolean v6 = v12.a(android.support.design.a$k.TextInputLayout_counterEnabled, false);
        this.setCounterMaxLength(v12.a(android.support.design.a$k.TextInputLayout_counterMaxLength, arg13));
        this.k = v12.g(android.support.design.a$k.TextInputLayout_counterTextAppearance, 0);
        this.j = v12.g(android.support.design.a$k.TextInputLayout_counterOverflowTextAppearance, 0);
        this.G = v12.a(android.support.design.a$k.TextInputLayout_passwordToggleEnabled, false);
        this.H = v12.a(android.support.design.a$k.TextInputLayout_passwordToggleDrawable);
        this.I = v12.c(android.support.design.a$k.TextInputLayout_passwordToggleContentDescription);
        if(v12.g(android.support.design.a$k.TextInputLayout_passwordToggleTint)) {
            this.O = true;
            this.N = v12.e(android.support.design.a$k.TextInputLayout_passwordToggleTint);
        }

        if(v12.g(android.support.design.a$k.TextInputLayout_passwordToggleTintMode)) {
            this.Q = true;
            this.P = android.support.design.internal.c.a(v12.a(android.support.design.a$k.TextInputLayout_passwordToggleTintMode, arg13), null);
        }

        v12.a();
        this.setHelperTextEnabled(v4);
        this.setHelperText(v5);
        this.setHelperTextTextAppearance(v3);
        this.setErrorEnabled(v2);
        this.setErrorTextAppearance(v11);
        this.setCounterEnabled(v6);
        this.s();
        t.b(((View)this), 2);
    }

    private void a(RectF arg3) {
        arg3.left -= ((float)this.q);
        arg3.top -= ((float)this.q);
        arg3.right += ((float)this.q);
        arg3.bottom += ((float)this.q);
    }

    private static void a(ViewGroup arg4, boolean arg5) {
        int v0 = arg4.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            View v2 = arg4.getChildAt(v1);
            v2.setEnabled(arg5);
            if((v2 instanceof ViewGroup)) {
                TextInputLayout.a(((ViewGroup)v2), arg5);
            }
        }
    }

    private void a(boolean arg7, boolean arg8) {
        ColorStateList v4;
        android.support.design.widget.c v0_1;
        boolean v0 = this.isEnabled();
        int v2 = 1;
        int v1 = this.a == null || (TextUtils.isEmpty(this.a.getText())) ? 0 : 1;
        if(this.a == null || !this.a.hasFocus()) {
            v2 = 0;
        }
        else {
        }

        boolean v3 = this.f.g();
        if(this.R != null) {
            this.c.a(this.R);
            this.c.b(this.R);
        }

        if(!v0) {
            this.c.a(ColorStateList.valueOf(this.W));
            this.c.b(ColorStateList.valueOf(this.W));
        }
        else {
            if(v3) {
                v0_1 = this.c;
                v4 = this.f.k();
            }
            else {
                if((this.h) && this.i != null) {
                    v0_1 = this.c;
                    v4 = this.i.getTextColors();
                    goto label_43;
                }

                if(v2 == 0) {
                    goto label_59;
                }

                if(this.S == null) {
                    goto label_59;
                }

                v0_1 = this.c;
                v4 = this.S;
            }

        label_43:
            v0_1.a(v4);
        }

    label_59:
        if(v1 == 0) {
            if(this.isEnabled()) {
                if(v2 != 0) {
                    goto label_70;
                }
                else if(v3) {
                    goto label_70;
                }
            }

            if(!arg8 && (this.aa)) {
                return;
            }

            this.d(arg7);
        }
        else {
        label_70:
            if(!arg8 && !this.aa) {
                return;
            }

            this.c(arg7);
        }
    }

    static boolean a(TextInputLayout arg0) {
        return arg0.af;
    }

    void a(float arg5) {
        if(this.c.e() == arg5) {
            return;
        }

        if(this.ac == null) {
            this.ac = new ValueAnimator();
            this.ac.setInterpolator(android.support.design.a.a.b);
            this.ac.setDuration(167);
            this.ac.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg2) {
                    this.a.c.b(arg2.getAnimatedValue().floatValue());
                }
            });
        }

        this.ac.setFloatValues(new float[]{this.c.e(), arg5});
        this.ac.start();
    }

    void a(int arg10) {
        boolean v0 = this.h;
        if(this.g == -1) {
            this.i.setText(String.valueOf(arg10));
            this.i.setContentDescription(null);
            this.h = false;
        }
        else {
            if(t.g(this.i) == 1) {
                t.c(this.i, 0);
            }

            boolean v1 = arg10 > this.g ? true : false;
            this.h = v1;
            if(v0 != this.h) {
                TextView v1_1 = this.i;
                int v4 = this.h ? this.j : this.k;
                this.a(v1_1, v4);
                if(!this.h) {
                    goto label_38;
                }

                t.c(this.i, 1);
            }

        label_38:
            this.i.setText(this.getContext().getString(i.character_counter_pattern, new Object[]{Integer.valueOf(arg10), Integer.valueOf(this.g)}));
            this.i.setContentDescription(this.getContext().getString(i.character_counter_content_description, new Object[]{Integer.valueOf(arg10), Integer.valueOf(this.g)}));
        }

        if(this.a != null && v0 != this.h) {
            this.a(false);
            this.d();
            this.c();
        }
    }

    void a(boolean arg2) {
        this.a(arg2, false);
    }

    void a(TextView arg3, int arg4) {
        int v0 = 1;
        try {
            q.a(arg3, arg4);
            if(Build$VERSION.SDK_INT < 23) {
                goto label_10;
            }
            else if(arg3.getTextColors().getDefaultColor() == -65281) {
                goto label_9;
            }
            else {
                goto label_10;
            }
        }
        catch(Exception ) {
        }

        goto label_12;
    label_9:
        goto label_12;
    label_10:
        v0 = 0;
    label_12:
        if(v0 != 0) {
            q.a(arg3, j.TextAppearance_AppCompat_Caption);
            arg3.setTextColor(android.support.v4.content.a.c(this.getContext(), android.support.design.a$c.design_error));
        }
    }

    boolean a() {
        return this.n;
    }

    public void addView(View arg2, int arg3, ViewGroup$LayoutParams arg4) {
        if((arg2 instanceof EditText)) {
            FrameLayout$LayoutParams v3 = new FrameLayout$LayoutParams(arg4);
            v3.gravity = v3.gravity & -113 | 16;
            this.d.addView(arg2, ((ViewGroup$LayoutParams)v3));
            this.d.setLayoutParams(arg4);
            this.g();
            this.setEditText(((EditText)arg2));
        }
        else {
            super.addView(arg2, arg3, arg4);
        }
    }

    public void b(boolean arg4) {
        boolean v1;
        if(this.G) {
            int v0 = this.a.getSelectionEnd();
            if(this.q()) {
                this.a.setTransformationMethod(null);
                v1 = true;
            }
            else {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
                v1 = false;
            }

            this.K = v1;
            this.J.setChecked(this.K);
            if(arg4) {
                this.J.jumpDrawablesToCurrentState();
            }

            this.a.setSelection(v0);
        }
    }

    public boolean b() {
        return this.f.f();
    }

    private void c(boolean arg2) {
        if(this.ac != null && (this.ac.isRunning())) {
            this.ac.cancel();
        }

        float v0 = 1f;
        if(!arg2 || !this.ab) {
            this.c.b(v0);
        }
        else {
            this.a(v0);
        }

        this.aa = false;
        if(this.t()) {
            this.u();
        }
    }

    void c() {
        int v1;
        if(this.a == null) {
            return;
        }

        Drawable v0 = this.a.getBackground();
        if(v0 == null) {
            return;
        }

        this.o();
        if(ai.b(v0)) {
            v0 = v0.mutate();
        }

        if(this.f.g()) {
            v1 = this.f.j();
            goto label_16;
        }
        else {
            if((this.h) && this.i != null) {
                v1 = this.i.getCurrentTextColor();
            label_16:
                v0.setColorFilter(android.support.v7.widget.k.a(v1, PorterDuff$Mode.SRC_IN));
                return;
            }

            android.support.v4.graphics.drawable.a.f(v0);
            this.a.refreshDrawableState();
        }
    }

    private void d(boolean arg2) {
        if(this.ac != null && (this.ac.isRunning())) {
            this.ac.cancel();
        }

        if(!arg2 || !this.ab) {
            this.c.b(0f);
        }
        else {
            this.a(0f);
        }

        if((this.t()) && (this.o.a())) {
            this.v();
        }

        this.aa = true;
    }

    void d() {
        int v2;
        if(this.o != null) {
            if(this.r == 0) {
            }
            else {
                int v1 = 1;
                int v0 = this.a == null || !this.a.hasFocus() ? 0 : 1;
                if(this.a == null || !this.a.isHovered()) {
                    v1 = 0;
                }
                else {
                }

                if(this.r != 2) {
                    return;
                }

                if(!this.isEnabled()) {
                    v2 = this.W;
                }
                else if(this.f.g()) {
                    v2 = this.f.j();
                }
                else {
                    if((this.h) && this.i != null) {
                        v2 = this.i.getCurrentTextColor();
                        goto label_28;
                    }

                    if(v0 != 0) {
                        v2 = this.V;
                        goto label_28;
                    }

                    if(v1 != 0) {
                        v2 = this.U;
                        goto label_28;
                    }

                    v2 = this.T;
                }

            label_28:
                this.A = v2;
                if(v1 == 0 && v0 == 0) {
                    goto label_57;
                }
                else if(this.isEnabled()) {
                    v0 = this.z;
                }
                else {
                label_57:
                    v0 = this.y;
                }

                this.x = v0;
                this.n();
            }
        }
    }

    public void dispatchProvideAutofillStructure(ViewStructure arg5, int arg6) {
        if(this.e != null) {
            if(this.a == null) {
            }
            else {
                boolean v0 = this.n;
                this.n = false;
                CharSequence v1 = this.a.getHint();
                this.a.setHint(this.e);
                try {
                    super.dispatchProvideAutofillStructure(arg5, arg6);
                }
                catch(Throwable v5) {
                    this.a.setHint(v1);
                    this.n = v0;
                    throw v5;
                }

                this.a.setHint(v1);
                this.n = v0;
                return;
            }
        }

        super.dispatchProvideAutofillStructure(arg5, arg6);
    }

    protected void dispatchRestoreInstanceState(SparseArray arg2) {
        this.af = true;
        super.dispatchRestoreInstanceState(arg2);
        this.af = false;
    }

    public void draw(Canvas arg2) {
        if(this.o != null) {
            this.o.draw(arg2);
        }

        super.draw(arg2);
        if(this.l) {
            this.c.a(arg2);
        }
    }

    protected void drawableStateChanged() {
        if(this.ae) {
            return;
        }

        boolean v0 = true;
        this.ae = true;
        super.drawableStateChanged();
        int[] v1 = this.getDrawableState();
        if(!t.A(((View)this)) || !this.isEnabled()) {
            v0 = false;
        }
        else {
        }

        this.a(v0);
        this.c();
        this.h();
        this.d();
        int v0_1 = this.c != null ? this.c.a(v1) | 0 : 0;
        if(v0_1 != 0) {
            this.invalidate();
        }

        this.ae = false;
    }

    private void e() {
        this.f();
        if(this.r != 0) {
            this.g();
        }

        this.h();
    }

    private void f() {
        GradientDrawable v0;
        if(this.r == 0) {
            v0 = null;
            goto label_3;
        }
        else {
            if(this.r == 2 && (this.l) && !(this.o instanceof android.support.design.widget.d)) {
                android.support.design.widget.d v0_1 = new android.support.design.widget.d();
                goto label_3;
            }

            if((this.o instanceof GradientDrawable)) {
                return;
            }

            v0 = new GradientDrawable();
        label_3:
            this.o = v0;
        }
    }

    private void g() {
        ViewGroup$LayoutParams v0 = this.d.getLayoutParams();
        int v1 = this.j();
        if(v1 != ((LinearLayout$LayoutParams)v0).topMargin) {
            ((LinearLayout$LayoutParams)v0).topMargin = v1;
            this.d.requestLayout();
        }
    }

    private Drawable getBoxBackground() {
        if(this.r != 1) {
            if(this.r == 2) {
            }
            else {
                throw new IllegalStateException();
            }
        }

        return this.o;
    }

    public int getBoxBackgroundColor() {
        return this.B;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return this.v;
    }

    public float getBoxCornerRadiusBottomStart() {
        return this.w;
    }

    public float getBoxCornerRadiusTopEnd() {
        return this.u;
    }

    public float getBoxCornerRadiusTopStart() {
        return this.t;
    }

    public int getBoxStrokeColor() {
        return this.V;
    }

    private float[] getCornerRadiiAsArray() {
        float[] v0;
        int v1 = 7;
        int v2 = 6;
        int v3 = 5;
        int v4 = 4;
        int v5 = 3;
        int v6 = 2;
        int v9 = 8;
        if(!android.support.design.internal.c.a(((View)this))) {
            v0 = new float[v9];
            v0[0] = this.t;
            v0[1] = this.t;
            v0[v6] = this.u;
            v0[v5] = this.u;
            v0[v4] = this.v;
            v0[v3] = this.v;
            v0[v2] = this.w;
            v0[v1] = this.w;
            return v0;
        }

        v0 = new float[v9];
        v0[0] = this.u;
        v0[1] = this.u;
        v0[v6] = this.t;
        v0[v5] = this.t;
        v0[v4] = this.w;
        v0[v3] = this.w;
        v0[v2] = this.v;
        v0[v1] = this.v;
        return v0;
    }

    public int getCounterMaxLength() {
        return this.g;
    }

    CharSequence getCounterOverflowDescription() {
        if((this.b) && (this.h) && this.i != null) {
            return this.i.getContentDescription();
        }

        return null;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.R;
    }

    public EditText getEditText() {
        return this.a;
    }

    public CharSequence getError() {
        CharSequence v0 = this.f.e() ? this.f.h() : null;
        return v0;
    }

    public int getErrorCurrentTextColors() {
        return this.f.j();
    }

    final int getErrorTextCurrentColor() {
        return this.f.j();
    }

    public CharSequence getHelperText() {
        CharSequence v0 = this.f.f() ? this.f.i() : null;
        return v0;
    }

    public int getHelperTextCurrentTextColor() {
        return this.f.l();
    }

    public CharSequence getHint() {
        CharSequence v0 = this.l ? this.m : null;
        return v0;
    }

    final float getHintCollapsedTextHeight() {
        return this.c.b();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.c.f();
    }

    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.I;
    }

    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.H;
    }

    public Typeface getTypeface() {
        return this.F;
    }

    private void h() {
        if(this.r != 0 && this.o != null && this.a != null) {
            if(this.getRight() == 0) {
            }
            else {
                int v0 = this.a.getLeft();
                int v1 = this.i();
                int v2 = this.a.getRight();
                int v3 = this.a.getBottom() + this.p;
                int v5 = 2;
                if(this.r == v5) {
                    v0 += this.z / v5;
                    v1 -= this.z / v5;
                    v2 -= this.z / v5;
                    v3 += this.z / v5;
                }

                this.o.setBounds(v0, v1, v2, v3);
                this.n();
                this.l();
            }
        }
    }

    private int i() {
        if(this.a == null) {
            return 0;
        }

        switch(this.r) {
            case 1: {
                goto label_12;
            }
            case 2: {
                goto label_7;
            }
        }

        return 0;
    label_7:
        return this.a.getTop() + this.j();
    label_12:
        return this.a.getTop();
    }

    private int j() {
        if(!this.l) {
            return 0;
        }

        switch(this.r) {
            case 0: 
            case 1: {
                goto label_13;
            }
            case 2: {
                goto label_7;
            }
        }

        return 0;
    label_7:
        float v0 = this.c.b() / 2f;
        goto label_11;
    label_13:
        v0 = this.c.b();
    label_11:
        return ((int)v0);
    }

    private int k() {
        switch(this.r) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_4;
            }
        }

        return this.getPaddingTop();
    label_4:
        return this.getBoxBackground().getBounds().top - this.j();
    label_10:
        return this.getBoxBackground().getBounds().top + this.s;
    }

    private void l() {
        if(this.a == null) {
            return;
        }

        Drawable v0 = this.a.getBackground();
        if(v0 == null) {
            return;
        }

        if(ai.b(v0)) {
            v0 = v0.mutate();
        }

        e.b(((ViewGroup)this), this.a, new Rect());
        Rect v1 = v0.getBounds();
        if(v1.left != v1.right) {
            Rect v2 = new Rect();
            v0.getPadding(v2);
            v0.setBounds(v1.left - v2.left, v1.top, v1.right + v2.right * 2, this.a.getBottom());
        }
    }

    private void m() {
        switch(this.r) {
            case 1: {
                this.x = 0;
                break;
            }
            case 2: {
                if(this.V != 0) {
                    return;
                }

                this.V = this.S.getColorForState(this.getDrawableState(), this.S.getDefaultColor());
                break;
            }
            default: {
                break;
            }
        }
    }

    private void n() {
        if(this.o == null) {
            return;
        }

        this.m();
        if(this.a != null && this.r == 2) {
            if(this.a.getBackground() != null) {
                this.C = this.a.getBackground();
            }

            t.a(this.a, null);
        }

        if(this.a != null && this.r == 1 && this.C != null) {
            t.a(this.a, this.C);
        }

        if(this.x > -1 && this.A != 0) {
            this.o.setStroke(this.x, this.A);
        }

        this.o.setCornerRadii(this.getCornerRadiiAsArray());
        this.o.setColor(this.B);
        this.invalidate();
    }

    private void o() {
        int v0 = Build$VERSION.SDK_INT;
        if(v0 != 21 && v0 != 22) {
            return;
        }

        Drawable v0_1 = this.a.getBackground();
        if(v0_1 == null) {
            return;
        }

        if(!this.ad) {
            Drawable v1 = v0_1.getConstantState().newDrawable();
            if((v0_1 instanceof DrawableContainer)) {
                this.ad = f.a(((DrawableContainer)v0_1), v1.getConstantState());
            }

            if(this.ad) {
                return;
            }

            t.a(this.a, v1);
            this.ad = true;
            this.e();
        }
    }

    protected void onLayout(boolean arg5, int arg6, int arg7, int arg8, int arg9) {
        super.onLayout(arg5, arg6, arg7, arg8, arg9);
        if(this.o != null) {
            this.h();
        }

        if((this.l) && this.a != null) {
            Rect v5 = this.D;
            e.b(((ViewGroup)this), this.a, v5);
            arg6 = v5.left + this.a.getCompoundPaddingLeft();
            arg8 = v5.right - this.a.getCompoundPaddingRight();
            int v0 = this.k();
            this.c.a(arg6, v5.top + this.a.getCompoundPaddingTop(), arg8, v5.bottom - this.a.getCompoundPaddingBottom());
            this.c.b(arg6, v0, arg8, arg9 - arg7 - this.getPaddingBottom());
            this.c.g();
            if((this.t()) && !this.aa) {
                this.u();
            }
        }
    }

    protected void onMeasure(int arg1, int arg2) {
        this.p();
        super.onMeasure(arg1, arg2);
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.setError(((SavedState)arg2).a);
        if(((SavedState)arg2).b) {
            this.b(true);
        }

        this.requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        if(this.f.g()) {
            v1.a = this.getError();
        }

        v1.b = this.K;
        return ((Parcelable)v1);
    }

    private void p() {
        Drawable[] v0;
        if(this.a == null) {
            return;
        }

        int v1 = 3;
        int v3 = 2;
        if(this.r()) {
            if(this.J == null) {
                this.J = LayoutInflater.from(this.getContext()).inflate(h.design_text_input_password_icon, this.d, false);
                this.J.setImageDrawable(this.H);
                this.J.setContentDescription(this.I);
                this.d.addView(this.J);
                this.J.setOnClickListener(new View$OnClickListener() {
                    public void onClick(View arg2) {
                        this.a.b(false);
                    }
                });
            }

            if(this.a != null && t.l(this.a) <= 0) {
                this.a.setMinimumHeight(t.l(this.J));
            }

            this.J.setVisibility(0);
            this.J.setChecked(this.K);
            if(this.L == null) {
                this.L = new ColorDrawable();
            }

            this.L.setBounds(0, 0, this.J.getMeasuredWidth(), 1);
            v0 = q.b(this.a);
            if(v0[v3] != this.L) {
                this.M = v0[v3];
            }

            q.a(this.a, v0[0], v0[1], this.L, v0[v1]);
            this.J.setPadding(this.a.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
        }
        else {
            if(this.J != null && this.J.getVisibility() == 0) {
                this.J.setVisibility(8);
            }

            if(this.L == null) {
                return;
            }

            v0 = q.b(this.a);
            if(v0[v3] != this.L) {
                return;
            }

            q.a(this.a, v0[0], v0[1], this.M, v0[v1]);
            this.L = null;
        }
    }

    private boolean q() {
        boolean v0 = this.a == null || !(this.a.getTransformationMethod() instanceof PasswordTransformationMethod) ? false : true;
        return v0;
    }

    private boolean r() {
        boolean v0;
        if(this.G) {
            if(!this.q() && !this.K) {
                goto label_8;
            }

            v0 = true;
        }
        else {
        label_8:
            v0 = false;
        }

        return v0;
    }

    private void s() {
        if(this.H != null && ((this.O) || (this.Q))) {
            this.H = android.support.v4.graphics.drawable.a.g(this.H).mutate();
            if(this.O) {
                android.support.v4.graphics.drawable.a.a(this.H, this.N);
            }

            if(this.Q) {
                android.support.v4.graphics.drawable.a.a(this.H, this.P);
            }

            if(this.J == null) {
                return;
            }

            if(this.J.getDrawable() == this.H) {
                return;
            }

            this.J.setImageDrawable(this.H);
        }
    }

    public void setBoxBackgroundColor(int arg2) {
        if(this.B != arg2) {
            this.B = arg2;
            this.n();
        }
    }

    public void setBoxBackgroundColorResource(int arg2) {
        this.setBoxBackgroundColor(android.support.v4.content.a.c(this.getContext(), arg2));
    }

    public void setBoxBackgroundMode(int arg2) {
        if(arg2 == this.r) {
            return;
        }

        this.r = arg2;
        this.e();
    }

    public void setBoxStrokeColor(int arg2) {
        if(this.V != arg2) {
            this.V = arg2;
            this.d();
        }
    }

    public void setCounterEnabled(boolean arg4) {
        if(this.b != arg4) {
            int v0 = 2;
            if(arg4) {
                this.i = new x(this.getContext());
                this.i.setId(android.support.design.a$f.textinput_counter);
                if(this.F != null) {
                    this.i.setTypeface(this.F);
                }

                this.i.setMaxLines(1);
                this.a(this.i, this.k);
                this.f.a(this.i, v0);
                v0 = this.a == null ? 0 : this.a.getText().length();
                this.a(v0);
            }
            else {
                this.f.b(this.i, v0);
                this.i = null;
            }

            this.b = arg4;
        }
    }

    public void setCounterMaxLength(int arg2) {
        if(this.g != arg2) {
            if(arg2 <= 0) {
                arg2 = -1;
            }

            this.g = arg2;
            if(!this.b) {
                return;
            }

            arg2 = this.a == null ? 0 : this.a.getText().length();
            this.a(arg2);
        }
    }

    public void setDefaultHintTextColor(ColorStateList arg1) {
        this.R = arg1;
        this.S = arg1;
        if(this.a != null) {
            this.a(false);
        }
    }

    private void setEditText(EditText arg3) {
        if(this.a == null) {
            if(!(arg3 instanceof s)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }

            this.a = arg3;
            this.e();
            this.setTextInputAccessibilityDelegate(new a(this));
            if(!this.q()) {
                this.c.a(this.a.getTypeface());
            }

            this.c.a(this.a.getTextSize());
            int v3 = this.a.getGravity();
            this.c.b(v3 & -113 | 48);
            this.c.a(v3);
            this.a.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable arg3) {
                    this.a.a(TextInputLayout.a(this.a) ^ 1);
                    if(this.a.b) {
                        this.a.a(arg3.length());
                    }
                }

                public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                }

                public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
                }
            });
            if(this.R == null) {
                this.R = this.a.getHintTextColors();
            }

            if(this.l) {
                if(TextUtils.isEmpty(this.m)) {
                    this.e = this.a.getHint();
                    this.setHint(this.e);
                    this.a.setHint(null);
                }

                this.n = true;
            }

            if(this.i != null) {
                this.a(this.a.getText().length());
            }

            this.f.d();
            this.p();
            this.a(false, true);
            return;
        }

        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    public void setEnabled(boolean arg1) {
        TextInputLayout.a(((ViewGroup)this), arg1);
        super.setEnabled(arg1);
    }

    public void setError(CharSequence arg2) {
        if(!this.f.e()) {
            if(TextUtils.isEmpty(arg2)) {
                return;
            }
            else {
                this.setErrorEnabled(true);
            }
        }

        if(!TextUtils.isEmpty(arg2)) {
            this.f.b(arg2);
        }
        else {
            this.f.b();
        }
    }

    public void setErrorEnabled(boolean arg2) {
        this.f.a(arg2);
    }

    public void setErrorTextAppearance(int arg2) {
        this.f.b(arg2);
    }

    public void setErrorTextColor(ColorStateList arg2) {
        this.f.a(arg2);
    }

    public void setHelperText(CharSequence arg2) {
        if(!TextUtils.isEmpty(arg2)) {
            if(!this.b()) {
                this.setHelperTextEnabled(true);
            }

            this.f.a(arg2);
        }
        else if(this.b()) {
            this.setHelperTextEnabled(false);
        }
    }

    public void setHelperTextColor(ColorStateList arg2) {
        this.f.b(arg2);
    }

    public void setHelperTextEnabled(boolean arg2) {
        this.f.b(arg2);
    }

    public void setHelperTextTextAppearance(int arg2) {
        this.f.c(arg2);
    }

    public void setHint(CharSequence arg2) {
        if(this.l) {
            this.setHintInternal(arg2);
            this.sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean arg1) {
        this.ab = arg1;
    }

    public void setHintEnabled(boolean arg3) {
        if(arg3 != this.l) {
            this.l = arg3;
            CharSequence v0 = null;
            if(!this.l) {
                this.n = false;
                if(!TextUtils.isEmpty(this.m) && (TextUtils.isEmpty(this.a.getHint()))) {
                    this.a.setHint(this.m);
                }

                this.setHintInternal(v0);
            }
            else {
                CharSequence v3 = this.a.getHint();
                if(!TextUtils.isEmpty(v3)) {
                    if(TextUtils.isEmpty(this.m)) {
                        this.setHint(v3);
                    }

                    this.a.setHint(v0);
                }

                this.n = true;
            }

            if(this.a == null) {
                return;
            }

            this.g();
        }
    }

    private void setHintInternal(CharSequence arg2) {
        if(!TextUtils.equals(arg2, this.m)) {
            this.m = arg2;
            this.c.a(arg2);
            if(!this.aa) {
                this.u();
            }
        }
    }

    public void setHintTextAppearance(int arg2) {
        this.c.c(arg2);
        this.S = this.c.h();
        if(this.a != null) {
            this.a(false);
            this.g();
        }
    }

    public void setPasswordVisibilityToggleContentDescription(int arg2) {
        CharSequence v2 = arg2 != 0 ? this.getResources().getText(arg2) : null;
        this.setPasswordVisibilityToggleContentDescription(v2);
    }

    public void setPasswordVisibilityToggleContentDescription(CharSequence arg2) {
        this.I = arg2;
        if(this.J != null) {
            this.J.setContentDescription(arg2);
        }
    }

    public void setPasswordVisibilityToggleDrawable(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.getContext(), arg2) : null;
        this.setPasswordVisibilityToggleDrawable(v2);
    }

    public void setPasswordVisibilityToggleDrawable(Drawable arg2) {
        this.H = arg2;
        if(this.J != null) {
            this.J.setImageDrawable(arg2);
        }
    }

    public void setPasswordVisibilityToggleEnabled(boolean arg2) {
        if(this.G != arg2) {
            this.G = arg2;
            if(!arg2 && (this.K) && this.a != null) {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            this.K = false;
            this.p();
        }
    }

    public void setPasswordVisibilityToggleTintList(ColorStateList arg1) {
        this.N = arg1;
        this.O = true;
        this.s();
    }

    public void setPasswordVisibilityToggleTintMode(PorterDuff$Mode arg1) {
        this.P = arg1;
        this.Q = true;
        this.s();
    }

    public void setTextInputAccessibilityDelegate(a arg2) {
        if(this.a != null) {
            t.a(this.a, ((android.support.v4.view.a)arg2));
        }
    }

    public void setTypeface(Typeface arg2) {
        if(arg2 != this.F) {
            this.F = arg2;
            this.c.a(arg2);
            this.f.a(arg2);
            if(this.i != null) {
                this.i.setTypeface(arg2);
            }
        }
    }

    private boolean t() {
        boolean v0 = !this.l || (TextUtils.isEmpty(this.m)) || !(this.o instanceof android.support.design.widget.d) ? false : true;
        return v0;
    }

    private void u() {
        if(!this.t()) {
            return;
        }

        RectF v0 = this.E;
        this.c.a(v0);
        this.a(v0);
        this.o.a(v0);
    }

    private void v() {
        if(this.t()) {
            this.o.b();
        }
    }
}

