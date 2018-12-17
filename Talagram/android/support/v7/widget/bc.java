package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView$LayoutParams;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class bc extends HorizontalScrollView implements AdapterView$OnItemSelectedListener {
    class a extends BaseAdapter {
        a(bc arg1) {
            this.a = arg1;
            super();
        }

        public int getCount() {
            return this.a.b.getChildCount();
        }

        public Object getItem(int arg2) {
            return this.a.b.getChildAt(arg2).b();
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public View getView(int arg1, View arg2, ViewGroup arg3) {
            c v2;
            if(arg2 == null) {
                v2 = this.a.a(this.getItem(arg1), true);
            }
            else {
                arg2.a(this.getItem(arg1));
            }

            return ((View)v2);
        }
    }

    class b implements View$OnClickListener {
        b(bc arg1) {
            this.a = arg1;
            super();
        }

        public void onClick(View arg6) {
            arg6.b().d();
            int v0 = this.a.b.getChildCount();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                View v3 = this.a.b.getChildAt(v2);
                boolean v4 = v3 == arg6 ? true : false;
                v3.setSelected(v4);
            }
        }
    }

    class c extends LinearLayout {
        private final int[] b;
        private android.support.v7.app.a$c c;
        private TextView d;
        private ImageView e;
        private View f;

        public c(bc arg4, Context arg5, android.support.v7.app.a$c arg6, boolean arg7) {
            this.a = arg4;
            super(arg5, null, android.support.v7.a.a$a.actionBarTabStyle);
            this.b = new int[]{16842964};
            this.c = arg6;
            bk v4 = bk.a(arg5, null, this.b, android.support.v7.a.a$a.actionBarTabStyle, 0);
            if(v4.g(0)) {
                this.setBackgroundDrawable(v4.a(0));
            }

            v4.a();
            if(arg7) {
                this.setGravity(8388627);
            }

            this.a();
        }

        public void a(android.support.v7.app.a$c arg1) {
            this.c = arg1;
            this.a();
        }

        public void a() {
            CharSequence v3_1;
            android.support.v7.app.a$c v0 = this.c;
            View v1 = v0.c();
            int v2 = 8;
            Drawable v3 = null;
            if(v1 != null) {
                ViewParent v0_1 = v1.getParent();
                if((((c)v0_1)) != this) {
                    if(v0_1 != null) {
                        ((ViewGroup)v0_1).removeView(v1);
                    }

                    this.addView(v1);
                }

                this.f = v1;
                if(this.d != null) {
                    this.d.setVisibility(v2);
                }

                if(this.e == null) {
                    return;
                }

                this.e.setVisibility(v2);
                this.e.setImageDrawable(v3);
            }
            else {
                if(this.f != null) {
                    this.removeView(this.f);
                    this.f = ((View)v3);
                }

                Drawable v1_1 = v0.a();
                CharSequence v4 = v0.b();
                int v5 = 16;
                int v7 = -2;
                if(v1_1 != null) {
                    if(this.e == null) {
                        AppCompatImageView v8 = new AppCompatImageView(this.getContext());
                        LinearLayout$LayoutParams v9 = new LinearLayout$LayoutParams(v7, v7);
                        v9.gravity = v5;
                        ((ImageView)v8).setLayoutParams(((ViewGroup$LayoutParams)v9));
                        this.addView(((View)v8), 0);
                        this.e = ((ImageView)v8);
                    }

                    this.e.setImageDrawable(v1_1);
                    this.e.setVisibility(0);
                }
                else {
                    if(this.e == null) {
                        goto label_55;
                    }

                    this.e.setVisibility(v2);
                    this.e.setImageDrawable(v3);
                }

            label_55:
                int v1_2 = TextUtils.isEmpty(v4) ^ 1;
                if(v1_2 != 0) {
                    if(this.d == null) {
                        x v2_1 = new x(this.getContext(), ((AttributeSet)v3), android.support.v7.a.a$a.actionBarTabTextStyle);
                        ((TextView)v2_1).setEllipsize(TextUtils$TruncateAt.END);
                        LinearLayout$LayoutParams v8_1 = new LinearLayout$LayoutParams(v7, v7);
                        v8_1.gravity = v5;
                        ((TextView)v2_1).setLayoutParams(((ViewGroup$LayoutParams)v8_1));
                        this.addView(((View)v2_1));
                        this.d = ((TextView)v2_1);
                    }

                    this.d.setText(v4);
                    this.d.setVisibility(0);
                }
                else {
                    if(this.d == null) {
                        goto label_83;
                    }

                    this.d.setVisibility(v2);
                    this.d.setText(((CharSequence)v3));
                }

            label_83:
                if(this.e != null) {
                    this.e.setContentDescription(v0.e());
                }

                if(v1_2 != 0) {
                }
                else {
                    v3_1 = v0.e();
                }

                bm.a(((View)this), v3_1);
            }
        }

        public android.support.v7.app.a$c b() {
            return this.c;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent arg2) {
            super.onInitializeAccessibilityEvent(arg2);
            arg2.setClassName(android.support.v7.app.a$c.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo arg2) {
            super.onInitializeAccessibilityNodeInfo(arg2);
            arg2.setClassName(android.support.v7.app.a$c.class.getName());
        }

        public void onMeasure(int arg2, int arg3) {
            super.onMeasure(arg2, arg3);
            if(this.a.c > 0 && this.getMeasuredWidth() > this.a.c) {
                super.onMeasure(View$MeasureSpec.makeMeasureSpec(this.a.c, 1073741824), arg3);
            }
        }

        public void setSelected(boolean arg2) {
            int v0 = this.isSelected() != arg2 ? 1 : 0;
            super.setSelected(arg2);
            if(v0 != 0 && (arg2)) {
                this.sendAccessibilityEvent(4);
            }
        }
    }

    Runnable a;
    ap b;
    int c;
    int d;
    private b e;
    private Spinner f;
    private boolean g;
    private int h;
    private int i;
    private static final Interpolator j;

    static {
        bc.j = new DecelerateInterpolator();
    }

    private boolean a() {
        boolean v0 = this.f == null || this.f.getParent() != this ? false : true;
        return v0;
    }

    c a(android.support.v7.app.a$c arg3, boolean arg4) {
        c v0 = new c(this, this.getContext(), arg3, arg4);
        if(arg4) {
            v0.setBackgroundDrawable(null);
            v0.setLayoutParams(new AbsListView$LayoutParams(-1, this.h));
        }
        else {
            v0.setFocusable(true);
            if(this.e == null) {
                this.e = new b(this);
            }

            v0.setOnClickListener(this.e);
        }

        return v0;
    }

    public void a(int arg2) {
        View v2 = this.b.getChildAt(arg2);
        if(this.a != null) {
            this.removeCallbacks(this.a);
        }

        this.a = new Runnable(v2) {
            public void run() {
                this.b.smoothScrollTo(this.a.getLeft() - (this.b.getWidth() - this.a.getWidth()) / 2, 0);
                this.b.a = null;
            }
        };
        this.post(this.a);
    }

    private void b() {
        if(this.a()) {
            return;
        }

        if(this.f == null) {
            this.f = this.d();
        }

        this.removeView(this.b);
        this.addView(this.f, new ViewGroup$LayoutParams(-2, -1));
        if(this.f.getAdapter() == null) {
            this.f.setAdapter(new a(this));
        }

        if(this.a != null) {
            this.removeCallbacks(this.a);
            this.a = null;
        }

        this.f.setSelection(this.i);
    }

    private boolean c() {
        if(!this.a()) {
            return 0;
        }

        this.removeView(this.f);
        this.addView(this.b, new ViewGroup$LayoutParams(-2, -1));
        this.setTabSelected(this.f.getSelectedItemPosition());
        return 0;
    }

    private Spinner d() {
        v v0 = new v(this.getContext(), null, android.support.v7.a.a$a.actionDropDownStyle);
        ((Spinner)v0).setLayoutParams(new android.support.v7.widget.ap$a(-2, -1));
        ((Spinner)v0).setOnItemSelectedListener(((AdapterView$OnItemSelectedListener)this));
        return ((Spinner)v0);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.a != null) {
            this.post(this.a);
        }
    }

    protected void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        android.support.v7.view.a v2 = android.support.v7.view.a.a(this.getContext());
        this.setContentHeight(v2.e());
        this.d = v2.g();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.a != null) {
            this.removeCallbacks(this.a);
        }
    }

    public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
        ((c)arg2).b().d();
    }

    public void onMeasure(int arg7, int arg8) {
        arg8 = View$MeasureSpec.getMode(arg7);
        int v1 = 1;
        int v2 = 1073741824;
        boolean v3 = arg8 == v2 ? true : false;
        this.setFillViewport(v3);
        int v4 = this.b.getChildCount();
        if(v4 > 1) {
            if(arg8 != v2 && arg8 != -2147483648) {
                goto label_31;
            }

            arg8 = 2;
            this.c = v4 > arg8 ? ((int)((((float)View$MeasureSpec.getSize(arg7))) * 0.4f)) : View$MeasureSpec.getSize(arg7) / arg8;
            arg8 = Math.min(this.c, this.d);
        }
        else {
        label_31:
            arg8 = -1;
        }

        this.c = arg8;
        arg8 = View$MeasureSpec.makeMeasureSpec(this.h, v2);
        if((v3) || !this.g) {
            v1 = 0;
        }
        else {
        }

        if(v1 != 0) {
            this.b.measure(0, arg8);
            if(this.b.getMeasuredWidth() > View$MeasureSpec.getSize(arg7)) {
                this.b();
            }
            else {
                goto label_49;
            }
        }
        else {
        label_49:
            this.c();
        }

        int v0 = this.getMeasuredWidth();
        super.onMeasure(arg7, arg8);
        arg7 = this.getMeasuredWidth();
        if((v3) && v0 != arg7) {
            this.setTabSelected(this.i);
        }
    }

    public void onNothingSelected(AdapterView arg1) {
    }

    public void setAllowCollapse(boolean arg1) {
        this.g = arg1;
    }

    public void setContentHeight(int arg1) {
        this.h = arg1;
        this.requestLayout();
    }

    public void setTabSelected(int arg6) {
        this.i = arg6;
        int v0 = this.b.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.b.getChildAt(v2);
            boolean v4 = v2 == arg6 ? true : false;
            v3.setSelected(v4);
            if(v4) {
                this.a(arg6);
            }
        }

        if(this.f != null && arg6 >= 0) {
            this.f.setSelection(arg6);
        }
    }
}

