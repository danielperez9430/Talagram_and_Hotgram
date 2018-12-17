package android.support.design.c.a;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.design.c.c;
import android.support.design.c.d;
import android.support.v7.widget.CardView;

public class a extends CardView implements d {
    private final c e;

    public void a() {
        this.e.a();
    }

    public void a(Canvas arg1) {
        super.draw(arg1);
    }

    public void b() {
        this.e.b();
    }

    public boolean c() {
        return super.isOpaque();
    }

    public void draw(Canvas arg2) {
        if(this.e != null) {
            this.e.a(arg2);
        }
        else {
            super.draw(arg2);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.e.e();
    }

    public int getCircularRevealScrimColor() {
        return this.e.d();
    }

    public android.support.design.c.d$d getRevealInfo() {
        return this.e.c();
    }

    public boolean isOpaque() {
        if(this.e != null) {
            return this.e.f();
        }

        return super.isOpaque();
    }

    public void setCircularRevealOverlayDrawable(Drawable arg2) {
        this.e.a(arg2);
    }

    public void setCircularRevealScrimColor(int arg2) {
        this.e.a(arg2);
    }

    public void setRevealInfo(android.support.design.c.d$d arg2) {
        this.e.a(arg2);
    }
}

