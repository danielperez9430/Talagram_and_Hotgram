package android.support.design.widget;

import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.GradientDrawable;
import android.os.Build$VERSION;
import android.view.View;

class d extends GradientDrawable {
    private final Paint a;
    private final RectF b;
    private int c;

    d() {
        super();
        this.a = new Paint(1);
        this.c();
        this.b = new RectF();
    }

    boolean a() {
        return this.b.isEmpty() ^ 1;
    }

    void a(RectF arg4) {
        this.a(arg4.left, arg4.top, arg4.right, arg4.bottom);
    }

    private void a(Canvas arg3) {
        Drawable$Callback v0 = this.getCallback();
        if(this.a(v0)) {
            ((View)v0).setLayerType(2, null);
        }
        else {
            this.b(arg3);
        }
    }

    private boolean a(Drawable$Callback arg1) {
        return arg1 instanceof View;
    }

    void a(float arg2, float arg3, float arg4, float arg5) {
        if(arg2 != this.b.left || arg3 != this.b.top || arg4 != this.b.right || arg5 != this.b.bottom) {
            this.b.set(arg2, arg3, arg4, arg5);
            this.invalidateSelf();
        }
    }

    void b() {
        this.a(0f, 0f, 0f, 0f);
    }

    private void b(Canvas arg9) {
        int v9 = Build$VERSION.SDK_INT >= 21 ? arg9.saveLayer(0f, 0f, ((float)arg9.getWidth()), ((float)arg9.getHeight()), null) : arg9.saveLayer(0f, 0f, ((float)arg9.getWidth()), ((float)arg9.getHeight()), null, 31);
        this.c = v9;
    }

    private void c() {
        this.a.setStyle(Paint$Style.FILL_AND_STROKE);
        this.a.setColor(-1);
        this.a.setXfermode(new PorterDuffXfermode(PorterDuff$Mode.DST_OUT));
    }

    private void c(Canvas arg2) {
        if(!this.a(this.getCallback())) {
            arg2.restoreToCount(this.c);
        }
    }

    public void draw(Canvas arg3) {
        this.a(arg3);
        super.draw(arg3);
        arg3.drawRect(this.b, this.a);
        this.c(arg3);
    }
}

