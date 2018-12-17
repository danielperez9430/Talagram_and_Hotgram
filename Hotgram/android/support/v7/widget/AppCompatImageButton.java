package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.s;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AppCompatImageButton extends ImageButton implements s, android.support.v4.widget.s {
    private final g a;
    private final n b;

    public AppCompatImageButton(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.imageButtonStyle);
    }

    public AppCompatImageButton(Context arg1, AttributeSet arg2, int arg3) {
        super(bh.a(arg1), arg2, arg3);
        this.a = new g(((View)this));
        this.a.a(arg2, arg3);
        this.b = new n(((ImageView)this));
        this.b.a(arg2, arg3);
    }

    public AppCompatImageButton(Context arg2) {
        this(arg2, null);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.a != null) {
            this.a.c();
        }

        if(this.b != null) {
            this.b.d();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.a != null ? this.a.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.a != null ? this.a.b() : null;
        return v0;
    }

    public ColorStateList getSupportImageTintList() {
        ColorStateList v0 = this.b != null ? this.b.b() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportImageTintMode() {
        PorterDuff$Mode v0 = this.b != null ? this.b.c() : null;
        return v0;
    }

    public boolean hasOverlappingRendering() {
        boolean v0 = !this.b.a() || !super.hasOverlappingRendering() ? false : true;
        return v0;
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setBackgroundResource(int arg2) {
        super.setBackgroundResource(arg2);
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setImageBitmap(Bitmap arg1) {
        super.setImageBitmap(arg1);
        if(this.b != null) {
            this.b.d();
        }
    }

    public void setImageDrawable(Drawable arg1) {
        super.setImageDrawable(arg1);
        if(this.b != null) {
            this.b.d();
        }
    }

    public void setImageResource(int arg2) {
        this.b.a(arg2);
    }

    public void setImageURI(Uri arg1) {
        super.setImageURI(arg1);
        if(this.b != null) {
            this.b.d();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
    }

    public void setSupportImageTintList(ColorStateList arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }

    public void setSupportImageTintMode(PorterDuff$Mode arg2) {
        if(this.b != null) {
            this.b.a(arg2);
        }
    }
}

