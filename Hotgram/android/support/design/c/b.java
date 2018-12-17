package android.support.design.c;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

public class b extends FrameLayout implements d {
    private final c a;

    public void a() {
        this.a.a();
    }

    public void a(Canvas arg1) {
        super.draw(arg1);
    }

    public void b() {
        this.a.b();
    }

    public boolean c() {
        return super.isOpaque();
    }

    @SuppressLint(value={"MissingSuperCall"}) public void draw(Canvas arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
        else {
            super.draw(arg2);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.a.e();
    }

    public int getCircularRevealScrimColor() {
        return this.a.d();
    }

    public android.support.design.c.d$d getRevealInfo() {
        return this.a.c();
    }

    public boolean isOpaque() {
        if(this.a != null) {
            return this.a.f();
        }

        return super.isOpaque();
    }

    public void setCircularRevealOverlayDrawable(Drawable arg2) {
        this.a.a(arg2);
    }

    public void setCircularRevealScrimColor(int arg2) {
        this.a.a(arg2);
    }

    public void setRevealInfo(android.support.design.c.d$d arg2) {
        this.a.a(arg2);
    }
}

