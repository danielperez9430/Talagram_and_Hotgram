package android.support.design.c;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path$Direction;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.design.widget.l;
import android.view.View;

public class c {
    interface a {
        void a(Canvas arg1);

        boolean c();
    }

    public static final int a;
    private final a b;
    private final View c;
    private final Path d;
    private final Paint e;
    private final Paint f;
    private d g;
    private Drawable h;
    private boolean i;
    private boolean j;

    static {
        int v0;
        if(Build$VERSION.SDK_INT >= 21) {
            v0 = 2;
        }
        else if(Build$VERSION.SDK_INT >= 18) {
            v0 = 1;
        }
        else {
            v0 = 0;
        }

        c.a = v0;
    }

    public void a() {
        if(c.a == 0) {
            this.i = true;
            this.j = false;
            this.c.buildDrawingCache();
            Bitmap v2 = this.c.getDrawingCache();
            if(v2 == null && this.c.getWidth() != 0 && this.c.getHeight() != 0) {
                v2 = Bitmap.createBitmap(this.c.getWidth(), this.c.getHeight(), Bitmap$Config.ARGB_8888);
                this.c.draw(new Canvas(v2));
            }

            if(v2 != null) {
                this.e.setShader(new BitmapShader(v2, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP));
            }

            this.i = false;
            this.j = true;
        }
    }

    public void a(int arg2) {
        this.f.setColor(arg2);
        this.c.invalidate();
    }

    public void a(Canvas arg9) {
        if(this.h()) {
            switch(c.a) {
                case 0: {
                    goto label_39;
                }
                case 1: {
                    goto label_19;
                }
                case 2: {
                    goto label_14;
                }
            }

            StringBuilder v0 = new StringBuilder();
            v0.append("Unsupported strategy ");
            v0.append(c.a);
            throw new IllegalStateException(v0.toString());
        label_19:
            int v0_1 = arg9.save();
            arg9.clipPath(this.d);
            this.b.a(arg9);
            if(this.i()) {
                arg9.drawRect(0f, 0f, ((float)this.c.getWidth()), ((float)this.c.getHeight()), this.f);
            }

            arg9.restoreToCount(v0_1);
            goto label_73;
        label_39:
            arg9.drawCircle(this.g.a, this.g.b, this.g.c, this.e);
            if(!this.i()) {
                goto label_73;
            }

            arg9.drawCircle(this.g.a, this.g.b, this.g.c, this.f);
            goto label_73;
        label_14:
            this.b.a(arg9);
            if(!this.i()) {
                goto label_73;
            }

            goto label_62;
        }
        else {
            this.b.a(arg9);
            if(!this.i()) {
                goto label_73;
            }

        label_62:
            arg9.drawRect(0f, 0f, ((float)this.c.getWidth()), ((float)this.c.getHeight()), this.f);
        }

    label_73:
        this.b(arg9);
    }

    public void a(Drawable arg1) {
        this.h = arg1;
        this.c.invalidate();
    }

    public void a(d arg3) {
        if(arg3 == null) {
            this.g = null;
        }
        else {
            if(this.g == null) {
                this.g = new d(arg3);
            }
            else {
                this.g.a(arg3);
            }

            if(!l.b(arg3.c, this.b(arg3), 0.0001f)) {
                goto label_20;
            }

            this.g.c = 340282346638528860000000000000000000000f;
        }

    label_20:
        this.g();
    }

    private float b(d arg7) {
        return l.a(arg7.a, arg7.b, 0f, 0f, ((float)this.c.getWidth()), ((float)this.c.getHeight()));
    }

    private void b(Canvas arg5) {
        if(this.j()) {
            Rect v0 = this.h.getBounds();
            float v1 = this.g.a - (((float)v0.width())) / 2f;
            float v2 = this.g.b - (((float)v0.height())) / 2f;
            arg5.translate(v1, v2);
            this.h.draw(arg5);
            arg5.translate(-v1, -v2);
        }
    }

    public void b() {
        if(c.a == 0) {
            this.j = false;
            this.c.destroyDrawingCache();
            this.e.setShader(null);
            this.c.invalidate();
        }
    }

    public d c() {
        if(this.g == null) {
            return null;
        }

        d v0 = new d(this.g);
        if(v0.a()) {
            v0.c = this.b(v0);
        }

        return v0;
    }

    public int d() {
        return this.f.getColor();
    }

    public Drawable e() {
        return this.h;
    }

    public boolean f() {
        boolean v0 = !this.b.c() || (this.h()) ? false : true;
        return v0;
    }

    private void g() {
        if(c.a == 1) {
            this.d.rewind();
            if(this.g != null) {
                this.d.addCircle(this.g.a, this.g.b, this.g.c, Path$Direction.CW);
            }
        }

        this.c.invalidate();
    }

    private boolean h() {
        boolean v1 = false;
        int v0 = this.g == null || (this.g.a()) ? 1 : 0;
        if(c.a == 0) {
            if(v0 == 0 && (this.j)) {
                v1 = true;
            }

            return v1;
        }

        return v0 ^ 1;
    }

    private boolean i() {
        boolean v0 = (this.i) || Color.alpha(this.f.getColor()) == 0 ? false : true;
        return v0;
    }

    private boolean j() {
        boolean v0 = (this.i) || this.h == null || this.g == null ? false : true;
        return v0;
    }
}

