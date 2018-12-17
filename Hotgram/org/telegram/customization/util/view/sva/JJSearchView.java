package org.telegram.customization.util.view.sva;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import org.telegram.customization.util.view.sva.a.a;
import org.telegram.messenger.R$styleable;

public class JJSearchView extends View {
    int a;
    int b;
    float c;
    float d;
    private Paint e;
    private Path f;
    private a g;

    public JJSearchView(Context arg2) {
        this(arg2, null);
    }

    public JJSearchView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
        this.a(arg2, arg3);
    }

    public JJSearchView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.a = 0;
        this.b = 0;
        this.c = 7f;
        this.d = 2f;
        this.g = new org.telegram.customization.util.view.sva.a.a.a();
        this.a(arg1, arg2);
        this.c();
    }

    void a(Context arg2, AttributeSet arg3) {
        TypedArray v2 = arg2.obtainStyledAttributes(arg3, styleable.JJSearchView);
        this.a = v2.getColor(1, this.a);
        this.b = v2.getColor(1, this.b);
        this.c = v2.getFloat(3, this.c);
        this.d = v2.getFloat(2, this.d);
        v2.recycle();
    }

    public void a() {
        if(this.g != null) {
            this.g.d();
        }
    }

    public void b() {
        if(this.g != null) {
            this.g.e();
        }
    }

    private void c() {
        this.e = new Paint(1);
        this.e.setStrokeWidth(4f);
        this.f = new Path();
    }

    protected void onDraw(Canvas arg3) {
        super.onDraw(arg3);
        this.g.a(this.a);
        this.g.b(this.b);
        this.g.a(this.c);
        this.g.b(this.d);
        this.g.a(arg3, this.e);
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
    }

    public void setController(a arg1) {
        this.g = arg1;
        this.g.a(((View)this));
        this.invalidate();
    }
}

