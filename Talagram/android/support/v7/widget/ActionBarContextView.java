package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.t;
import android.support.v4.view.x;
import android.support.v7.a.a$f;
import android.support.v7.a.a$g;
import android.support.v7.a.a$j;
import android.support.v7.view.b;
import android.support.v7.view.menu.h;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView extends a {
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private boolean p;
    private int q;

    public ActionBarContextView(Context arg2) {
        this(arg2, null);
    }

    public ActionBarContextView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.v7.a.a$a.actionModeStyle);
    }

    public ActionBarContextView(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        bk v3 = bk.a(arg3, arg4, j.ActionMode, arg5, 0);
        t.a(((View)this), v3.a(j.ActionMode_background));
        this.n = v3.g(j.ActionMode_titleTextStyle, 0);
        this.o = v3.g(j.ActionMode_subtitleTextStyle, 0);
        this.e = v3.f(j.ActionMode_height, 0);
        this.q = v3.g(j.ActionMode_closeItemLayout, g.abc_action_mode_close_item_material);
        v3.a();
    }

    public void a(b arg4) {
        if(this.i == null) {
            this.i = LayoutInflater.from(this.getContext()).inflate(this.q, ((ViewGroup)this), false);
            goto label_8;
        }
        else if(this.i.getParent() == null) {
        label_8:
            this.addView(this.i);
        }

        this.i.findViewById(f.action_mode_close_button).setOnClickListener(new View$OnClickListener(arg4) {
            public void onClick(View arg1) {
                this.a.c();
            }
        });
        Menu v4 = arg4.b();
        if(this.d != null) {
            this.d.f();
        }

        this.d = new c(this.getContext());
        this.d.c(true);
        ViewGroup$LayoutParams v0 = new ViewGroup$LayoutParams(-2, -1);
        ((h)v4).a(this.d, this.b);
        this.c = this.d.a(((ViewGroup)this));
        t.a(this.c, null);
        this.addView(this.c, v0);
    }

    public x a(int arg1, long arg2) {
        return super.a(arg1, arg2);
    }

    public boolean a() {
        if(this.d != null) {
            return this.d.d();
        }

        return 0;
    }

    public void b() {
        if(this.i == null) {
            this.c();
        }
    }

    public void c() {
        this.removeAllViews();
        this.j = null;
        this.c = null;
    }

    public boolean d() {
        return this.p;
    }

    private void e() {
        if(this.k == null) {
            LayoutInflater.from(this.getContext()).inflate(g.abc_action_bar_title_item, ((ViewGroup)this));
            this.k = this.getChildAt(this.getChildCount() - 1);
            this.l = this.k.findViewById(f.action_bar_title);
            this.m = this.k.findViewById(f.action_bar_subtitle);
            if(this.n != 0) {
                this.l.setTextAppearance(this.getContext(), this.n);
            }

            if(this.o == 0) {
                goto label_30;
            }

            this.m.setTextAppearance(this.getContext(), this.o);
        }

    label_30:
        this.l.setText(this.g);
        this.m.setText(this.h);
        int v0 = TextUtils.isEmpty(this.g) ^ 1;
        int v1 = TextUtils.isEmpty(this.h) ^ 1;
        TextView v2 = this.m;
        int v3 = 8;
        int v5 = v1 != 0 ? 0 : 8;
        v2.setVisibility(v5);
        LinearLayout v2_1 = this.k;
        if(v0 != 0 || v1 != 0) {
            v3 = 0;
        }

        v2_1.setVisibility(v3);
        if(this.k.getParent() == null) {
            this.addView(this.k);
        }
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$MarginLayoutParams(-1, -2);
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new ViewGroup$MarginLayoutParams(this.getContext(), arg3);
    }

    public int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.h;
    }

    public CharSequence getTitle() {
        return this.g;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.d != null) {
            this.d.e();
            this.d.g();
        }
    }

    public boolean onHoverEvent(MotionEvent arg1) {
        return super.onHoverEvent(arg1);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent arg3) {
        if(arg3.getEventType() == 32) {
            arg3.setSource(((View)this));
            arg3.setClassName(this.getClass().getName());
            arg3.setPackageName(this.getContext().getPackageName());
            arg3.setContentDescription(this.g);
        }
        else {
            super.onInitializeAccessibilityEvent(arg3);
        }
    }

    protected void onLayout(boolean arg10, int arg11, int arg12, int arg13, int arg14) {
        arg10 = bs.a(((View)this));
        int v0 = arg10 ? arg13 - arg11 - this.getPaddingRight() : this.getPaddingLeft();
        int v6 = this.getPaddingTop();
        arg12 = arg14 - arg12 - this.getPaddingTop() - this.getPaddingBottom();
        int v7 = 8;
        if(this.i == null || this.i.getVisibility() == v7) {
            arg14 = v0;
        }
        else {
            ViewGroup$LayoutParams v14 = this.i.getLayoutParams();
            int v1 = arg10 ? ((ViewGroup$MarginLayoutParams)v14).rightMargin : ((ViewGroup$MarginLayoutParams)v14).leftMargin;
            arg14 = arg10 ? ((ViewGroup$MarginLayoutParams)v14).leftMargin : ((ViewGroup$MarginLayoutParams)v14).rightMargin;
            int v8 = ActionBarContextView.a(v0, v1, arg10);
            arg14 = ActionBarContextView.a(v8 + this.a(this.i, v8, v6, arg12, arg10), arg14, arg10);
        }

        if(this.k != null && this.j == null && this.k.getVisibility() != v7) {
            arg14 += this.a(this.k, arg14, v6, arg12, arg10);
        }

        int v2 = arg14;
        if(this.j != null) {
            this.a(this.j, v2, v6, arg12, arg10);
        }

        int v3 = arg10 ? this.getPaddingLeft() : arg13 - arg11 - this.getPaddingRight();
        if(this.c != null) {
            this.a(this.c, v3, v6, arg12, (((int)arg10)) ^ 1);
        }
    }

    protected void onMeasure(int arg11, int arg12) {
        StringBuilder v12;
        int v6_1;
        int v1 = 1073741824;
        if(View$MeasureSpec.getMode(arg11) == v1) {
            if(View$MeasureSpec.getMode(arg12) != 0) {
                arg11 = View$MeasureSpec.getSize(arg11);
                arg12 = this.e > 0 ? this.e : View$MeasureSpec.getSize(arg12);
                int v0 = this.getPaddingTop() + this.getPaddingBottom();
                int v2 = arg11 - this.getPaddingLeft() - this.getPaddingRight();
                int v3 = arg12 - v0;
                int v5 = View$MeasureSpec.makeMeasureSpec(v3, -2147483648);
                int v7 = 0;
                if(this.i != null) {
                    v2 = this.a(this.i, v2, v5, 0);
                    ViewGroup$LayoutParams v6 = this.i.getLayoutParams();
                    v2 -= ((ViewGroup$MarginLayoutParams)v6).leftMargin + ((ViewGroup$MarginLayoutParams)v6).rightMargin;
                }

                if(this.c != null && this.c.getParent() == this) {
                    v2 = this.a(this.c, v2, v5, 0);
                }

                if(this.k != null && this.j == null) {
                    if(this.p) {
                        this.k.measure(View$MeasureSpec.makeMeasureSpec(0, 0), v5);
                        v5 = this.k.getMeasuredWidth();
                        v6_1 = v5 <= v2 ? 1 : 0;
                        if(v6_1 != 0) {
                            v2 -= v5;
                        }

                        LinearLayout v5_1 = this.k;
                        v6_1 = v6_1 != 0 ? 0 : 8;
                        v5_1.setVisibility(v6_1);
                    }
                    else {
                        v2 = this.a(this.k, v2, v5, 0);
                    }
                }

                if(this.j != null) {
                    ViewGroup$LayoutParams v5_2 = this.j.getLayoutParams();
                    int v8 = -2;
                    v6_1 = v5_2.width != v8 ? 1073741824 : -2147483648;
                    if(v5_2.width >= 0) {
                        v2 = Math.min(v5_2.width, v2);
                    }

                    if(v5_2.height != v8) {
                    }
                    else {
                        v1 = -2147483648;
                    }

                    if(v5_2.height >= 0) {
                        v3 = Math.min(v5_2.height, v3);
                    }

                    this.j.measure(View$MeasureSpec.makeMeasureSpec(v2, v6_1), View$MeasureSpec.makeMeasureSpec(v3, v1));
                }

                if(this.e <= 0) {
                    arg12 = this.getChildCount();
                    v1 = 0;
                    while(v7 < arg12) {
                        v2 = this.getChildAt(v7).getMeasuredHeight() + v0;
                        if(v2 > v1) {
                            v1 = v2;
                        }

                        ++v7;
                    }

                    this.setMeasuredDimension(arg11, v1);
                }
                else {
                    this.setMeasuredDimension(arg11, arg12);
                }

                return;
            }

            v12 = new StringBuilder();
            v12.append(this.getClass().getSimpleName());
            v12.append(" can only be used ");
            v12.append("with android:layout_height=\"wrap_content\"");
            throw new IllegalStateException(v12.toString());
        }

        v12 = new StringBuilder();
        v12.append(this.getClass().getSimpleName());
        v12.append(" can only be used ");
        v12.append("with android:layout_width=\"match_parent\" (or fill_parent)");
        throw new IllegalStateException(v12.toString());
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        return super.onTouchEvent(arg1);
    }

    public void setContentHeight(int arg1) {
        this.e = arg1;
    }

    public void setCustomView(View arg2) {
        if(this.j != null) {
            this.removeView(this.j);
        }

        this.j = arg2;
        if(arg2 != null && this.k != null) {
            this.removeView(this.k);
            this.k = null;
        }

        if(arg2 != null) {
            this.addView(arg2);
        }

        this.requestLayout();
    }

    public void setSubtitle(CharSequence arg1) {
        this.h = arg1;
        this.e();
    }

    public void setTitle(CharSequence arg1) {
        this.g = arg1;
        this.e();
    }

    public void setTitleOptional(boolean arg2) {
        if(arg2 != this.p) {
            this.requestLayout();
        }

        this.p = arg2;
    }

    public void setVisibility(int arg1) {
        super.setVisibility(arg1);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }
}

