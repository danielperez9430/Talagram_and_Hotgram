package android.support.design.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.design.a$b;
import android.support.design.a$j;
import android.support.design.a$k;
import android.support.v4.view.t;
import android.support.v4.widget.q;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class a extends AppCompatButton {
    private final c b;
    private int c;
    private PorterDuff$Mode d;
    private ColorStateList e;
    private Drawable f;
    private int g;
    private int h;
    private int i;

    public a(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, b.materialButtonStyle);
    }

    public a(Context arg8, AttributeSet arg9, int arg10) {
        super(arg8, arg9, arg10);
        TypedArray v8 = android.support.design.internal.b.a(arg8, arg9, k.MaterialButton, arg10, j.Widget_MaterialComponents_Button, new int[0]);
        this.c = v8.getDimensionPixelSize(k.MaterialButton_iconPadding, 0);
        this.d = android.support.design.internal.c.a(v8.getInt(k.MaterialButton_iconTintMode, -1), PorterDuff$Mode.SRC_IN);
        this.e = android.support.design.e.a.a(this.getContext(), v8, k.MaterialButton_iconTint);
        this.f = android.support.design.e.a.b(this.getContext(), v8, k.MaterialButton_icon);
        this.i = v8.getInteger(k.MaterialButton_iconGravity, 1);
        this.g = v8.getDimensionPixelSize(k.MaterialButton_iconSize, 0);
        this.b = new c(this);
        this.b.a(v8);
        v8.recycle();
        this.setCompoundDrawablePadding(this.c);
        this.b();
    }

    private boolean a() {
        boolean v1 = true;
        if(t.f(((View)this)) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private void b() {
        if(this.f != null) {
            this.f = this.f.mutate();
            android.support.v4.graphics.drawable.a.a(this.f, this.e);
            if(this.d != null) {
                android.support.v4.graphics.drawable.a.a(this.f, this.d);
            }

            int v0 = this.g != 0 ? this.g : this.f.getIntrinsicWidth();
            int v1 = this.g != 0 ? this.g : this.f.getIntrinsicHeight();
            this.f.setBounds(this.h, 0, this.h + v0, v1);
        }

        q.a(((TextView)this), this.f, null, null, null);
    }

    private boolean c() {
        boolean v0 = this.b == null || (this.b.b()) ? false : true;
        return v0;
    }

    public ColorStateList getBackgroundTintList() {
        return this.getSupportBackgroundTintList();
    }

    public PorterDuff$Mode getBackgroundTintMode() {
        return this.getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        int v0 = this.c() ? this.b.h() : 0;
        return v0;
    }

    public Drawable getIcon() {
        return this.f;
    }

    public int getIconGravity() {
        return this.i;
    }

    public int getIconPadding() {
        return this.c;
    }

    public int getIconSize() {
        return this.g;
    }

    public ColorStateList getIconTint() {
        return this.e;
    }

    public PorterDuff$Mode getIconTintMode() {
        return this.d;
    }

    public ColorStateList getRippleColor() {
        ColorStateList v0 = this.c() ? this.b.e() : null;
        return v0;
    }

    public ColorStateList getStrokeColor() {
        ColorStateList v0 = this.c() ? this.b.f() : null;
        return v0;
    }

    public int getStrokeWidth() {
        int v0 = this.c() ? this.b.g() : 0;
        return v0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        if(this.c()) {
            return this.b.c();
        }

        return super.getSupportBackgroundTintList();
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        if(this.c()) {
            return this.b.d();
        }

        return super.getSupportBackgroundTintMode();
    }

    protected void onDraw(Canvas arg3) {
        super.onDraw(arg3);
        if(Build$VERSION.SDK_INT < 21 && (this.c())) {
            this.b.a(arg3);
        }
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        super.onLayout(arg2, arg3, arg4, arg5, arg6);
        if(Build$VERSION.SDK_INT == 21 && this.b != null) {
            this.b.a(arg6 - arg4, arg5 - arg3);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(arg3, arg4);
        if(this.f != null) {
            arg4 = 2;
            if(this.i != arg4) {
            }
            else {
                arg3 = ((int)this.getPaint().measureText(this.getText().toString()));
                int v0 = this.g == 0 ? this.f.getIntrinsicWidth() : this.g;
                int v1 = (this.getMeasuredWidth() - arg3 - t.i(((View)this)) - v0 - this.c - t.h(((View)this))) / arg4;
                if(this.a()) {
                    v1 = -v1;
                }

                if(this.h == v1) {
                    return;
                }

                this.h = v1;
                this.b();
            }
        }
    }

    public void setBackground(Drawable arg1) {
        this.setBackgroundDrawable(arg1);
    }

    public void setBackgroundColor(int arg2) {
        if(this.c()) {
            this.b.a(arg2);
        }
        else {
            super.setBackgroundColor(arg2);
        }
    }

    public void setBackgroundDrawable(Drawable arg3) {
        if(!this.c()) {
        label_14:
            super.setBackgroundDrawable(arg3);
        }
        else if(arg3 != this.getBackground()) {
            Log.i("MaterialButton", "Setting a custom background is not supported.");
            this.b.a();
            goto label_14;
        }
        else {
            this.getBackground().setState(arg3.getState());
        }
    }

    public void setBackgroundResource(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.getContext(), arg2) : null;
        this.setBackgroundDrawable(v2);
    }

    public void setBackgroundTintList(ColorStateList arg1) {
        this.setSupportBackgroundTintList(arg1);
    }

    public void setBackgroundTintMode(PorterDuff$Mode arg1) {
        this.setSupportBackgroundTintMode(arg1);
    }

    public void setCornerRadius(int arg2) {
        if(this.c()) {
            this.b.c(arg2);
        }
    }

    public void setCornerRadiusResource(int arg2) {
        if(this.c()) {
            this.setCornerRadius(this.getResources().getDimensionPixelSize(arg2));
        }
    }

    public void setIcon(Drawable arg2) {
        if(this.f != arg2) {
            this.f = arg2;
            this.b();
        }
    }

    public void setIconGravity(int arg1) {
        this.i = arg1;
    }

    public void setIconPadding(int arg2) {
        if(this.c != arg2) {
            this.c = arg2;
            this.setCompoundDrawablePadding(arg2);
        }
    }

    public void setIconResource(int arg2) {
        Drawable v2 = arg2 != 0 ? android.support.v7.c.a.a.b(this.getContext(), arg2) : null;
        this.setIcon(v2);
    }

    public void setIconSize(int arg2) {
        if(arg2 >= 0) {
            if(this.g != arg2) {
                this.g = arg2;
                this.b();
            }

            return;
        }

        throw new IllegalArgumentException("iconSize cannot be less than 0");
    }

    public void setIconTint(ColorStateList arg2) {
        if(this.e != arg2) {
            this.e = arg2;
            this.b();
        }
    }

    public void setIconTintMode(PorterDuff$Mode arg2) {
        if(this.d != arg2) {
            this.d = arg2;
            this.b();
        }
    }

    public void setIconTintResource(int arg2) {
        this.setIconTint(android.support.v7.c.a.a.a(this.getContext(), arg2));
    }

    void setInternalBackground(Drawable arg1) {
        super.setBackgroundDrawable(arg1);
    }

    public void setRippleColor(ColorStateList arg2) {
        if(this.c()) {
            this.b.b(arg2);
        }
    }

    public void setRippleColorResource(int arg2) {
        if(this.c()) {
            this.setRippleColor(android.support.v7.c.a.a.a(this.getContext(), arg2));
        }
    }

    public void setStrokeColor(ColorStateList arg2) {
        if(this.c()) {
            this.b.c(arg2);
        }
    }

    public void setStrokeColorResource(int arg2) {
        if(this.c()) {
            this.setStrokeColor(android.support.v7.c.a.a.a(this.getContext(), arg2));
        }
    }

    public void setStrokeWidth(int arg2) {
        if(this.c()) {
            this.b.b(arg2);
        }
    }

    public void setStrokeWidthResource(int arg2) {
        if(this.c()) {
            this.setStrokeWidth(this.getResources().getDimensionPixelSize(arg2));
        }
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.c()) {
            this.b.a(arg2);
        }
        else if(this.b != null) {
            super.setSupportBackgroundTintList(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.c()) {
            this.b.a(arg2);
        }
        else if(this.b != null) {
            super.setSupportBackgroundTintMode(arg2);
        }
    }
}

