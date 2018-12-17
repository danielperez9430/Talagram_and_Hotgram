package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.s;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class o extends MultiAutoCompleteTextView implements s {
    private static final int[] a;
    private final g b;
    private final w c;

    static {
        o.a = new int[]{16843126};
    }

    public o(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.autoCompleteTextViewStyle);
    }

    public o(Context arg3, AttributeSet arg4, int arg5) {
        super(bh.a(arg3), arg4, arg5);
        bk v3 = bk.a(this.getContext(), arg4, o.a, arg5, 0);
        if(v3.g(0)) {
            this.setDropDownBackgroundDrawable(v3.a(0));
        }

        v3.a();
        this.b = new g(((View)this));
        this.b.a(arg4, arg5);
        this.c = new w(((TextView)this));
        this.c.a(arg4, arg5);
        this.c.a();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.b != null) {
            this.b.c();
        }

        if(this.c != null) {
            this.c.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.b != null ? this.b.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.b != null ? this.b.b() : null;
        return v0;
    }

    public InputConnection onCreateInputConnection(EditorInfo arg2) {
        return m.a(super.onCreateInputConnection(arg2), arg2, ((View)this));
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setBackgroundResource(int arg2) {
        super.setBackgroundResource(arg2);
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setDropDownBackgroundResource(int arg2) {
        this.setDropDownBackgroundDrawable(android.support.v7.c.a.a.b(this.getContext(), arg2));
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setTextAppearance(Context arg2, int arg3) {
        super.setTextAppearance(arg2, arg3);
        if(this.c != null) {
            this.c.a(arg2, arg3);
        }
    }
}

