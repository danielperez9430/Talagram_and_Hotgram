package android.support.design.g;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region$Op;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.b;

public class c extends Drawable implements b {
    private final Paint a;
    private final Matrix[] b;
    private final Matrix[] c;
    private final d[] d;
    private final Matrix e;
    private final Path f;
    private final PointF g;
    private final d h;
    private final Region i;
    private final Region j;
    private final float[] k;
    private final float[] l;
    private e m;
    private boolean n;
    private boolean o;
    private float p;
    private int q;
    private int r;
    private int s;
    private int t;
    private float u;
    private float v;
    private Paint$Style w;
    private PorterDuffColorFilter x;
    private PorterDuff$Mode y;
    private ColorStateList z;

    public void a(float arg1) {
        this.p = arg1;
        this.invalidateSelf();
    }

    public ColorStateList a() {
        return this.z;
    }

    private static int a(int arg1, int arg2) {
        return arg1 * (arg2 + (arg2 >>> 7)) >>> 8;
    }

    private a a(int arg1) {
        switch(arg1) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_4;
            }
        }

        return this.m.a();
    label_4:
        return this.m.d();
    label_7:
        return this.m.c();
    label_10:
        return this.m.b();
    }

    private void a(int arg5, int arg6, int arg7) {
        this.a(arg5, arg6, arg7, this.g);
        this.a(arg5).a(this.c(arg5, arg6, arg7), this.p, this.d[arg5]);
        float v6 = this.d((arg5 + 3) % 4, arg6, arg7);
        this.b[arg5].reset();
        this.b[arg5].setTranslate(this.g.x, this.g.y);
        this.b[arg5].preRotate(((float)Math.toDegrees(((double)(v6 + 1.570796f)))));
    }

    private void a(int arg2, int arg3, int arg4, PointF arg5) {
        switch(arg2) {
            case 1: {
                arg5.set(((float)arg3), 0f);
                break;
            }
            case 2: {
                arg5.set(((float)arg3), ((float)arg4));
                break;
            }
            case 3: {
                arg5.set(0f, ((float)arg4));
                break;
            }
            default: {
                arg5.set(0f, 0f);
                break;
            }
        }
    }

    private void a(int arg5, Path arg6) {
        this.k[0] = this.d[arg5].a;
        this.k[1] = this.d[arg5].b;
        this.b[arg5].mapPoints(this.k);
        if(arg5 == 0) {
            arg6.moveTo(this.k[0], this.k[1]);
        }
        else {
            arg6.lineTo(this.k[0], this.k[1]);
        }

        this.d[arg5].a(this.b[arg5], arg6);
    }

    public void a(int arg4, int arg5, Path arg6) {
        arg6.rewind();
        if(this.m == null) {
            return;
        }

        int v0 = 0;
        int v1 = 0;
        while(true) {
            int v2 = 4;
            if(v1 < v2) {
                this.a(v1, arg4, arg5);
                this.b(v1, arg4, arg5);
                ++v1;
                continue;
            }

            break;
        }

        while(v0 < v2) {
            this.a(v0, arg6);
            this.b(v0, arg6);
            ++v0;
        }

        arg6.close();
    }

    private android.support.design.g.b b(int arg1) {
        switch(arg1) {
            case 1: {
                goto label_10;
            }
            case 2: {
                goto label_7;
            }
            case 3: {
                goto label_4;
            }
        }

        return this.m.e();
    label_4:
        return this.m.h();
    label_7:
        return this.m.g();
    label_10:
        return this.m.f();
    }

    private void b() {
        if(this.z != null) {
            if(this.y == null) {
            }
            else {
                int v0 = this.z.getColorForState(this.getState(), 0);
                this.x = new PorterDuffColorFilter(v0, this.y);
                if(this.o) {
                    this.q = v0;
                }

                return;
            }
        }

        this.x = null;
    }

    private void b(int arg5, int arg6, int arg7) {
        this.k[0] = this.d[arg5].c;
        this.k[1] = this.d[arg5].d;
        this.b[arg5].mapPoints(this.k);
        float v6 = this.d(arg5, arg6, arg7);
        this.c[arg5].reset();
        this.c[arg5].setTranslate(this.k[0], this.k[1]);
        this.c[arg5].preRotate(((float)Math.toDegrees(((double)v6))));
    }

    private void b(int arg4, int arg5, Path arg6) {
        this.a(arg4, arg5, arg6);
        if(this.u == 1f) {
            return;
        }

        this.e.reset();
        this.e.setScale(this.u, this.u, ((float)(arg4 / 2)), ((float)(arg5 / 2)));
        arg6.transform(this.e);
    }

    private void b(int arg6, Path arg7) {
        int v0 = (arg6 + 1) % 4;
        this.k[0] = this.d[arg6].c;
        this.k[1] = this.d[arg6].d;
        this.b[arg6].mapPoints(this.k);
        this.l[0] = this.d[v0].a;
        this.l[1] = this.d[v0].b;
        this.b[v0].mapPoints(this.l);
        float v0_1 = ((float)Math.hypot(((double)(this.k[0] - this.l[0])), ((double)(this.k[1] - this.l[1]))));
        this.h.a(0f, 0f);
        this.b(arg6).a(v0_1, this.p, this.h);
        this.h.a(this.c[arg6], arg7);
    }

    private float c(int arg6, int arg7, int arg8) {
        this.a((arg6 + 3) % 4, arg7, arg8, this.g);
        float v0 = this.g.x;
        float v1 = this.g.y;
        this.a((arg6 + 1) % 4, arg7, arg8, this.g);
        float v2 = this.g.x;
        float v3 = this.g.y;
        this.a(arg6, arg7, arg8, this.g);
        float v6 = (((float)Math.atan2(((double)(v1 - this.g.y)), ((double)(v0 - this.g.x))))) - (((float)Math.atan2(((double)(v3 - this.g.y)), ((double)(v2 - this.g.x)))));
        if(v6 < 0f) {
            double v6_1 = ((double)v6);
            Double.isNaN(v6_1);
            v6 = ((float)(v6_1 + 6.283185));
        }

        return v6;
    }

    private float d(int arg4, int arg5, int arg6) {
        int v0 = (arg4 + 1) % 4;
        this.a(arg4, arg5, arg6, this.g);
        float v4 = this.g.x;
        float v1 = this.g.y;
        this.a(v0, arg5, arg6, this.g);
        return ((float)Math.atan2(((double)(this.g.y - v1)), ((double)(this.g.x - v4))));
    }

    public void draw(Canvas arg10) {
        this.a.setColorFilter(this.x);
        int v0 = this.a.getAlpha();
        this.a.setAlpha(c.a(v0, this.t));
        this.a.setStrokeWidth(this.v);
        this.a.setStyle(this.w);
        if(this.r > 0 && (this.n)) {
            this.a.setShadowLayer(((float)this.s), 0f, ((float)this.r), this.q);
        }

        if(this.m != null) {
            this.b(arg10.getWidth(), arg10.getHeight(), this.f);
            arg10.drawPath(this.f, this.a);
        }
        else {
            arg10.drawRect(0f, 0f, ((float)arg10.getWidth()), ((float)arg10.getHeight()), this.a);
        }

        this.a.setAlpha(v0);
    }

    public int getOpacity() {
        return -3;
    }

    public Region getTransparentRegion() {
        Rect v0 = this.getBounds();
        this.i.set(v0);
        this.b(v0.width(), v0.height(), this.f);
        this.j.setPath(this.f, this.i);
        this.i.op(this.j, Region$Op.DIFFERENCE);
        return this.i;
    }

    public void setAlpha(int arg1) {
        this.t = arg1;
        this.invalidateSelf();
    }

    public void setColorFilter(ColorFilter arg2) {
        this.a.setColorFilter(arg2);
        this.invalidateSelf();
    }

    public void setTint(int arg1) {
        this.setTintList(ColorStateList.valueOf(arg1));
    }

    public void setTintList(ColorStateList arg1) {
        this.z = arg1;
        this.b();
        this.invalidateSelf();
    }

    public void setTintMode(PorterDuff$Mode arg1) {
        this.y = arg1;
        this.b();
        this.invalidateSelf();
    }
}

