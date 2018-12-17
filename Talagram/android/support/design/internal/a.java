package android.support.design.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.support.design.a$k;
import android.support.v7.widget.ap;
import android.util.AttributeSet;
import android.view.Gravity;

public class a extends ap {
    protected boolean a;
    boolean b;
    private Drawable c;
    private final Rect d;
    private final Rect e;
    private int f;

    public a(Context arg2) {
        this(arg2, null);
    }

    public a(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public a(Context arg9, AttributeSet arg10, int arg11) {
        super(arg9, arg10, arg11);
        this.d = new Rect();
        this.e = new Rect();
        this.f = 119;
        this.a = true;
        this.b = false;
        TypedArray v9 = b.a(arg9, arg10, k.ForegroundLinearLayout, arg11, 0, new int[0]);
        this.f = v9.getInt(k.ForegroundLinearLayout_android_foregroundGravity, this.f);
        Drawable v10 = v9.getDrawable(k.ForegroundLinearLayout_android_foreground);
        if(v10 != null) {
            this.setForeground(v10);
        }

        this.a = v9.getBoolean(k.ForegroundLinearLayout_foregroundInsidePadding, true);
        v9.recycle();
    }

    public void draw(Canvas arg9) {
        super.draw(arg9);
        if(this.c != null) {
            Drawable v0 = this.c;
            if(this.b) {
                this.b = false;
                Rect v2 = this.d;
                Rect v3 = this.e;
                int v4 = this.getRight() - this.getLeft();
                int v5 = this.getBottom() - this.getTop();
                if(this.a) {
                    v2.set(0, 0, v4, v5);
                }
                else {
                    v2.set(this.getPaddingLeft(), this.getPaddingTop(), v4 - this.getPaddingRight(), v5 - this.getPaddingBottom());
                }

                Gravity.apply(this.f, v0.getIntrinsicWidth(), v0.getIntrinsicHeight(), v2, v3);
                v0.setBounds(v3);
            }

            v0.draw(arg9);
        }
    }

    @TargetApi(value=21) public void drawableHotspotChanged(float arg2, float arg3) {
        super.drawableHotspotChanged(arg2, arg3);
        if(this.c != null) {
            this.c.setHotspot(arg2, arg3);
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.c != null && (this.c.isStateful())) {
            this.c.setState(this.getDrawableState());
        }
    }

    public Drawable getForeground() {
        return this.c;
    }

    public int getForegroundGravity() {
        return this.f;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if(this.c != null) {
            this.c.jumpToCurrentState();
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.b |= ((int)arg1);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        this.b = true;
    }

    public void setForeground(Drawable arg3) {
        if(this.c != arg3) {
            if(this.c != null) {
                this.c.setCallback(null);
                this.unscheduleDrawable(this.c);
            }

            this.c = arg3;
            if(arg3 != null) {
                this.setWillNotDraw(false);
                arg3.setCallback(((Drawable$Callback)this));
                if(arg3.isStateful()) {
                    arg3.setState(this.getDrawableState());
                }

                if(this.f != 119) {
                    goto label_27;
                }

                arg3.getPadding(new Rect());
            }
            else {
                this.setWillNotDraw(true);
            }

        label_27:
            this.requestLayout();
            this.invalidate();
        }
    }

    public void setForegroundGravity(int arg2) {
        if(this.f != arg2) {
            if((8388615 & arg2) == 0) {
                arg2 |= 8388611;
            }

            if((arg2 & 112) == 0) {
                arg2 |= 48;
            }

            this.f = arg2;
            if(this.f == 119 && this.c != null) {
                this.c.getPadding(new Rect());
            }

            this.requestLayout();
        }
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2 = (super.verifyDrawable(arg2)) || arg2 == this.c ? true : false;
        return v2;
    }
}

