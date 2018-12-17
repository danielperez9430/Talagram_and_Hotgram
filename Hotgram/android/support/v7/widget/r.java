package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class r extends RadioButton implements android.support.v4.widget.r {
    private final j a;
    private final w b;

    public r(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.radioButtonStyle);
    }

    public r(Context arg1, AttributeSet arg2, int arg3) {
        super(bh.a(arg1), arg2, arg3);
        this.a = new j(((CompoundButton)this));
        this.a.a(arg2, arg3);
        this.b = new w(((TextView)this));
        this.b.a(arg2, arg3);
    }

    public int getCompoundPaddingLeft() {
        int v0 = super.getCompoundPaddingLeft();
        if(this.a != null) {
            v0 = this.a.a(v0);
        }

        return v0;
    }

    public ColorStateList getSupportButtonTintList() {
        ColorStateList v0 = this.a != null ? this.a.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportButtonTintMode() {
        PorterDuff$Mode v0 = this.a != null ? this.a.b() : null;
        return v0;
    }

    public void setButtonDrawable(int arg2) {
        this.setButtonDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setButtonDrawable(Drawable arg1) {
        super.setButtonDrawable(arg1);
        if(this.a != null) {
            this.a.c();
        }
    }

    public void setSupportButtonTintList(ColorStateList arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setSupportButtonTintMode(PorterDuff$Mode arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }
}

