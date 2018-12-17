package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.am;
import android.support.v7.widget.bm;
import android.support.v7.widget.x;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;

public class ActionMenuItemView extends x implements a, android.support.v7.widget.ActionMenuView$a, View$OnClickListener {
    class android.support.v7.view.menu.ActionMenuItemView$a extends am {
        public android.support.v7.view.menu.ActionMenuItemView$a(ActionMenuItemView arg1) {
            this.a = arg1;
            super(((View)arg1));
        }

        public s a() {
            if(this.a.d != null) {
                return this.a.d.a();
            }

            return null;
        }

        protected boolean b() {
            boolean v1 = false;
            if(this.a.c != null && (this.a.c.a(this.a.b))) {
                s v0 = this.a();
                if(v0 != null && (v0.d())) {
                    v1 = true;
                }
            }

            return v1;
        }
    }

    public abstract class b {
        public b() {
            super();
        }

        public abstract s a();
    }

    j b;
    android.support.v7.view.menu.h$b c;
    b d;
    private CharSequence e;
    private Drawable f;
    private am g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    public ActionMenuItemView(Context arg2) {
        this(arg2, null);
    }

    public ActionMenuItemView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ActionMenuItemView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        Resources v0 = arg4.getResources();
        this.h = this.e();
        TypedArray v4 = arg4.obtainStyledAttributes(arg5, android.support.v7.a.a$j.ActionMenuItemView, arg6, 0);
        this.j = v4.getDimensionPixelSize(android.support.v7.a.a$j.ActionMenuItemView_android_minWidth, 0);
        v4.recycle();
        this.l = ((int)(v0.getDisplayMetrics().density * 32f + 0.5f));
        this.setOnClickListener(((View$OnClickListener)this));
        this.k = -1;
        this.setSaveEnabled(false);
    }

    public void a(j arg1, int arg2) {
        this.b = arg1;
        this.setIcon(arg1.getIcon());
        this.setTitle(arg1.a(((a)this)));
        this.setId(arg1.getItemId());
        arg2 = arg1.isVisible() ? 0 : 8;
        this.setVisibility(arg2);
        this.setEnabled(arg1.isEnabled());
        if((arg1.hasSubMenu()) && this.g == null) {
            this.g = new android.support.v7.view.menu.ActionMenuItemView$a(this);
        }
    }

    public boolean a() {
        return 1;
    }

    public boolean b() {
        return TextUtils.isEmpty(this.getText()) ^ 1;
    }

    public boolean c() {
        boolean v0 = !this.b() || this.b.getIcon() != null ? false : true;
        return v0;
    }

    public boolean d() {
        return this.b();
    }

    private boolean e() {
        boolean v0_1;
        Configuration v0 = this.getContext().getResources().getConfiguration();
        int v1 = v0.screenWidthDp;
        int v2 = v0.screenHeightDp;
        int v3 = 480;
        if(v1 < v3) {
            if(v1 >= 640 && v2 >= v3) {
                goto label_16;
            }

            if(v0.orientation == 2) {
                goto label_16;
            }

            v0_1 = false;
        }
        else {
        label_16:
            v0_1 = true;
        }

        return v0_1;
    }

    private void f() {
        int v1 = 1;
        int v0 = TextUtils.isEmpty(this.e) ^ 1;
        if(this.f != null) {
            if(this.b.m()) {
                if(this.h) {
                    goto label_15;
                }
                else if(this.i) {
                    goto label_15;
                }
            }

            v1 = 0;
        }

    label_15:
        v0 &= v1;
        CharSequence v1_1 = null;
        CharSequence v2 = v0 != 0 ? this.e : v1_1;
        this.setText(v2);
        v2 = this.b.getContentDescription();
        if(TextUtils.isEmpty(v2)) {
            v2 = v0 != 0 ? v1_1 : this.b.getTitle();
        }

        this.setContentDescription(v2);
        v2 = this.b.getTooltipText();
        if(TextUtils.isEmpty(v2)) {
            if(v0 != 0) {
            }
            else {
                v1_1 = this.b.getTitle();
            }

            bm.a(((View)this), v1_1);
        }
        else {
            bm.a(((View)this), v2);
        }
    }

    public j getItemData() {
        return this.b;
    }

    public void onClick(View arg2) {
        if(this.c != null) {
            this.c.a(this.b);
        }
    }

    public void onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
        this.h = this.e();
        this.f();
    }

    protected void onMeasure(int arg6, int arg7) {
        boolean v0 = this.b();
        if((v0) && this.k >= 0) {
            super.setPadding(this.k, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }

        super.onMeasure(arg6, arg7);
        int v1 = View$MeasureSpec.getMode(arg6);
        arg6 = View$MeasureSpec.getSize(arg6);
        int v2 = this.getMeasuredWidth();
        arg6 = v1 == -2147483648 ? Math.min(arg6, this.j) : this.j;
        int v3 = 1073741824;
        if(v1 != v3 && this.j > 0 && v2 < arg6) {
            super.onMeasure(View$MeasureSpec.makeMeasureSpec(arg6, v3), arg7);
        }

        if(!v0 && this.f != null) {
            super.setPadding((this.getMeasuredWidth() - this.f.getBounds().width()) / 2, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable arg1) {
        super.onRestoreInstanceState(null);
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        if((this.b.hasSubMenu()) && this.g != null && (this.g.onTouch(((View)this), arg2))) {
            return 1;
        }

        return super.onTouchEvent(arg2);
    }

    public void setCheckable(boolean arg1) {
    }

    public void setChecked(boolean arg1) {
    }

    public void setExpandedFormat(boolean arg2) {
        if(this.i != arg2) {
            this.i = arg2;
            if(this.b != null) {
                this.b.h();
            }
        }
    }

    public void setIcon(Drawable arg4) {
        float v2;
        this.f = arg4;
        if(arg4 != null) {
            int v0 = arg4.getIntrinsicWidth();
            int v1 = arg4.getIntrinsicHeight();
            if(v0 > this.l) {
                v2 = (((float)this.l)) / (((float)v0));
                v0 = this.l;
                v1 = ((int)((((float)v1)) * v2));
            }

            if(v1 > this.l) {
                v2 = (((float)this.l)) / (((float)v1));
                v1 = this.l;
                v0 = ((int)((((float)v0)) * v2));
            }

            arg4.setBounds(0, 0, v0, v1);
        }

        this.setCompoundDrawables(arg4, null, null, null);
        this.f();
    }

    public void setItemInvoker(android.support.v7.view.menu.h$b arg1) {
        this.c = arg1;
    }

    public void setPadding(int arg1, int arg2, int arg3, int arg4) {
        this.k = arg1;
        super.setPadding(arg1, arg2, arg3, arg4);
    }

    public void setPopupCallback(b arg1) {
        this.d = arg1;
    }

    public void setTitle(CharSequence arg1) {
        this.e = arg1;
        this.f();
    }
}

