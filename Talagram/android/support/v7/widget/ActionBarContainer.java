package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.support.v4.view.t;
import android.support.v7.a.a$f;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;

public class ActionBarContainer extends FrameLayout {
    Drawable a;
    Drawable b;
    Drawable c;
    boolean d;
    boolean e;
    private boolean f;
    private View g;
    private View h;
    private View i;
    private int j;

    public ActionBarContainer(Context arg2) {
        this(arg2, null);
    }

    public ActionBarContainer(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        t.a(((View)this), new b(this));
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, j.ActionBar);
        this.a = v3.getDrawable(j.ActionBar_background);
        this.b = v3.getDrawable(j.ActionBar_backgroundStacked);
        this.j = v3.getDimensionPixelSize(j.ActionBar_height, -1);
        if(this.getId() == f.split_action_bar) {
            this.d = true;
            this.c = v3.getDrawable(j.ActionBar_backgroundSplit);
        }

        v3.recycle();
        boolean v4 = false;
        if(this.d) {
            if(this.c != null) {
                goto label_37;
            }

            goto label_30;
        }
        else if(this.a == null && this.b == null) {
        label_30:
            v4 = true;
        }

    label_37:
        this.setWillNotDraw(v4);
    }

    private boolean a(View arg3) {
        boolean v3 = arg3 == null || arg3.getVisibility() == 8 || arg3.getMeasuredHeight() == 0 ? true : false;
        return v3;
    }

    private int b(View arg3) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        return arg3.getMeasuredHeight() + ((FrameLayout$LayoutParams)v0).topMargin + ((FrameLayout$LayoutParams)v0).bottomMargin;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.a != null && (this.a.isStateful())) {
            this.a.setState(this.getDrawableState());
        }

        if(this.b != null && (this.b.isStateful())) {
            this.b.setState(this.getDrawableState());
        }

        if(this.c != null && (this.c.isStateful())) {
            this.c.setState(this.getDrawableState());
        }
    }

    public View getTabContainer() {
        return this.g;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if(this.a != null) {
            this.a.jumpToCurrentState();
        }

        if(this.b != null) {
            this.b.jumpToCurrentState();
        }

        if(this.c != null) {
            this.c.jumpToCurrentState();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.h = this.findViewById(f.action_bar);
        this.i = this.findViewById(f.action_context_bar);
    }

    public boolean onHoverEvent(MotionEvent arg1) {
        super.onHoverEvent(arg1);
        return 1;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg2) {
        boolean v2 = (this.f) || (super.onInterceptTouchEvent(arg2)) ? true : false;
        return v2;
    }

    public void onLayout(boolean arg6, int arg7, int arg8, int arg9, int arg10) {
        View v2_1;
        Drawable v7;
        super.onLayout(arg6, arg7, arg8, arg9, arg10);
        View v6 = this.g;
        arg8 = 8;
        arg10 = 1;
        int v0 = 0;
        boolean v1 = v6 == null || v6.getVisibility() == arg8 ? false : true;
        if(v6 != null && v6.getVisibility() != arg8) {
            arg8 = this.getMeasuredHeight();
            ViewGroup$LayoutParams v2 = v6.getLayoutParams();
            v6.layout(arg7, arg8 - v6.getMeasuredHeight() - ((FrameLayout$LayoutParams)v2).bottomMargin, arg9, arg8 - ((FrameLayout$LayoutParams)v2).bottomMargin);
        }

        if(!this.d) {
            if(this.a != null) {
                if(this.h.getVisibility() == 0) {
                    v7 = this.a;
                    arg8 = this.h.getLeft();
                    arg9 = this.h.getTop();
                    v0 = this.h.getRight();
                    v2_1 = this.h;
                    goto label_47;
                }
                else {
                    if(this.i != null && this.i.getVisibility() == 0) {
                        v7 = this.a;
                        arg8 = this.i.getLeft();
                        arg9 = this.i.getTop();
                        v0 = this.i.getRight();
                        v2_1 = this.i;
                    label_47:
                        v7.setBounds(arg8, arg9, v0, v2_1.getBottom());
                        goto label_66;
                    }

                    this.a.setBounds(0, 0, 0, 0);
                }

            label_66:
                v0 = 1;
            }

            this.e = v1;
            if((v1) && this.b != null) {
                this.b.setBounds(v6.getLeft(), v6.getTop(), v6.getRight(), v6.getBottom());
                goto label_79;
            }

            arg10 = v0;
        }
        else if(this.c != null) {
            this.c.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        }
        else {
            arg10 = 0;
        }

    label_79:
        if(arg10 != 0) {
            this.invalidate();
        }
    }

    public void onMeasure(int arg4, int arg5) {
        int v0_1;
        View v0;
        int v1 = -2147483648;
        if(this.h == null && View$MeasureSpec.getMode(arg5) == v1 && this.j >= 0) {
            arg5 = View$MeasureSpec.makeMeasureSpec(Math.min(this.j, View$MeasureSpec.getSize(arg5)), v1);
        }

        super.onMeasure(arg4, arg5);
        if(this.h == null) {
            return;
        }

        arg4 = View$MeasureSpec.getMode(arg5);
        if(this.g != null && this.g.getVisibility() != 8 && arg4 != 1073741824) {
            if(!this.a(this.h)) {
                v0 = this.h;
                goto label_28;
            }
            else if(!this.a(this.i)) {
                v0 = this.i;
            label_28:
                v0_1 = this.b(v0);
            }
            else {
                v0_1 = 0;
            }

            arg4 = arg4 == v1 ? View$MeasureSpec.getSize(arg5) : 2147483647;
            this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(v0_1 + this.b(this.g), arg4));
        }
    }

    public boolean onTouchEvent(MotionEvent arg1) {
        super.onTouchEvent(arg1);
        return 1;
    }

    public void setPrimaryBackground(Drawable arg5) {
        if(this.a != null) {
            this.a.setCallback(null);
            this.unscheduleDrawable(this.a);
        }

        this.a = arg5;
        if(arg5 != null) {
            arg5.setCallback(((Drawable$Callback)this));
            if(this.h != null) {
                this.a.setBounds(this.h.getLeft(), this.h.getTop(), this.h.getRight(), this.h.getBottom());
            }
        }

        boolean v0 = false;
        if(this.d) {
            if(this.c != null) {
                goto label_35;
            }

            goto label_28;
        }
        else if(this.a == null && this.b == null) {
        label_28:
            v0 = true;
        }

    label_35:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setSplitBackground(Drawable arg4) {
        if(this.c != null) {
            this.c.setCallback(null);
            this.unscheduleDrawable(this.c);
        }

        this.c = arg4;
        boolean v0 = false;
        if(arg4 != null) {
            arg4.setCallback(((Drawable$Callback)this));
            if((this.d) && this.c != null) {
                this.c.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }

        if(this.d) {
            if(this.c != null) {
                goto label_31;
            }

            goto label_24;
        }
        else if(this.a == null && this.b == null) {
        label_24:
            v0 = true;
        }

    label_31:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setStackedBackground(Drawable arg5) {
        if(this.b != null) {
            this.b.setCallback(null);
            this.unscheduleDrawable(this.b);
        }

        this.b = arg5;
        if(arg5 != null) {
            arg5.setCallback(((Drawable$Callback)this));
            if((this.e) && this.b != null) {
                this.b.setBounds(this.g.getLeft(), this.g.getTop(), this.g.getRight(), this.g.getBottom());
            }
        }

        boolean v0 = false;
        if(this.d) {
            if(this.c != null) {
                goto label_37;
            }

            goto label_30;
        }
        else if(this.a == null && this.b == null) {
        label_30:
            v0 = true;
        }

    label_37:
        this.setWillNotDraw(v0);
        this.invalidate();
    }

    public void setTabContainer(bc arg3) {
        if(this.g != null) {
            this.removeView(this.g);
        }

        this.g = ((View)arg3);
        if(arg3 != null) {
            this.addView(((View)arg3));
            ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
            v0.width = -1;
            v0.height = -2;
            arg3.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean arg1) {
        this.f = arg1;
        int v1 = arg1 ? 393216 : 262144;
        this.setDescendantFocusability(v1);
    }

    public void setVisibility(int arg3) {
        super.setVisibility(arg3);
        boolean v3 = arg3 == 0 ? true : false;
        if(this.a != null) {
            this.a.setVisible(v3, false);
        }

        if(this.b != null) {
            this.b.setVisible(v3, false);
        }

        if(this.c != null) {
            this.c.setVisible(v3, false);
        }
    }

    public ActionMode startActionModeForChild(View arg1, ActionMode$Callback arg2) {
        return null;
    }

    public ActionMode startActionModeForChild(View arg1, ActionMode$Callback arg2, int arg3) {
        if(arg3 != 0) {
            return super.startActionModeForChild(arg1, arg2, arg3);
        }

        return null;
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2;
        if(arg2 != this.a || (this.d)) {
            if(arg2 == this.b && (this.e)) {
                goto label_14;
            }

            if(arg2 == this.c && (this.d)) {
                goto label_14;
            }

            if(super.verifyDrawable(arg2)) {
            label_14:
                v2 = true;
                return v2;
            }

            v2 = false;
        }
        else {
            goto label_14;
        }

        return v2;
    }
}

