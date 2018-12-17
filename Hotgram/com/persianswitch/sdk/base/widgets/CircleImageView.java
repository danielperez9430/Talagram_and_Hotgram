package com.persianswitch.sdk.base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import com.persianswitch.sdk.R$styleable;

public class CircleImageView extends ImageView {
    private static final ImageView$ScaleType a;
    private static final Bitmap$Config b;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private final Paint g;
    private final Paint h;
    private int i;
    private int j;
    private int k;
    private Bitmap l;
    private BitmapShader m;
    private int n;
    private int o;
    private float p;
    private float q;
    private ColorFilter r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;

    static {
        CircleImageView.a = ImageView$ScaleType.CENTER_CROP;
        CircleImageView.b = Bitmap$Config.ARGB_8888;
    }

    public CircleImageView(Context arg1) {
        super(arg1);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = -16777216;
        this.j = 0;
        this.k = 0;
        this.a();
    }

    public CircleImageView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public CircleImageView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        this.i = -16777216;
        this.j = 0;
        this.k = 0;
        TypedArray v4 = arg4.obtainStyledAttributes(arg5, styleable.asanpardakht_CircleImageView, arg6, 0);
        this.j = v4.getDimensionPixelSize(styleable.asanpardakht_CircleImageView_asanpardakht_civ_border_width, 0);
        this.i = v4.getColor(styleable.asanpardakht_CircleImageView_asanpardakht_civ_border_color, -16777216);
        this.u = v4.getBoolean(styleable.asanpardakht_CircleImageView_asanpardakht_civ_border_overlay, false);
        this.k = v4.getColor(styleable.asanpardakht_CircleImageView_asanpardakht_civ_fill_color, 0);
        v4.recycle();
        this.a();
    }

    private void a() {
        super.setScaleType(CircleImageView.a);
        this.s = true;
        if(this.t) {
            this.d();
            this.t = false;
        }
    }

    private Bitmap a(Drawable arg7) {
        Bitmap v0 = null;
        if(arg7 == null) {
            return v0;
        }

        if((arg7 instanceof BitmapDrawable)) {
            return ((BitmapDrawable)arg7).getBitmap();
        }

        try {
            Bitmap v1 = (arg7 instanceof ColorDrawable) ? Bitmap.createBitmap(2, 2, CircleImageView.b) : Bitmap.createBitmap(arg7.getIntrinsicWidth(), arg7.getIntrinsicHeight(), CircleImageView.b);
            Canvas v2 = new Canvas(v1);
            arg7.setBounds(0, 0, v2.getWidth(), v2.getHeight());
            arg7.draw(v2);
            return v1;
        }
        catch(Exception v7) {
            v7.printStackTrace();
            return v0;
        }
    }

    private void b() {
        if(this.f != null) {
            this.f.setColorFilter(this.r);
        }
    }

    private void c() {
        Bitmap v0 = this.v ? null : this.a(this.getDrawable());
        this.l = v0;
        this.d();
    }

    private void d() {
        if(!this.s) {
            this.t = true;
            return;
        }

        if(this.getWidth() == 0 && this.getHeight() == 0) {
            return;
        }

        if(this.l == null) {
            this.invalidate();
            return;
        }

        this.m = new BitmapShader(this.l, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
        this.f.setAntiAlias(true);
        this.f.setShader(this.m);
        this.g.setStyle(Paint$Style.STROKE);
        this.g.setAntiAlias(true);
        this.g.setColor(this.i);
        this.g.setStrokeWidth(((float)this.j));
        this.h.setStyle(Paint$Style.FILL);
        this.h.setAntiAlias(true);
        this.h.setColor(this.k);
        this.o = this.l.getHeight();
        this.n = this.l.getWidth();
        this.d.set(this.e());
        float v1 = 2f;
        this.q = Math.min((this.d.height() - (((float)this.j))) / v1, (this.d.width() - (((float)this.j))) / v1);
        this.c.set(this.d);
        if(!this.u && this.j > 0) {
            this.c.inset((((float)this.j)) - 1f, (((float)this.j)) - 1f);
        }

        this.p = Math.min(this.c.height() / v1, this.c.width() / v1);
        this.b();
        this.f();
        this.invalidate();
    }

    private RectF e() {
        int v0 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
        int v1 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int v2 = Math.min(v0, v1);
        float v3 = (((float)this.getPaddingLeft())) + (((float)(v0 - v2))) / 2f;
        float v0_1 = (((float)this.getPaddingTop())) + (((float)(v1 - v2))) / 2f;
        float v2_1 = ((float)v2);
        return new RectF(v3, v0_1, v3 + v2_1, v2_1 + v0_1);
    }

    private void f() {
        // Method was not decompiled
    }

    public int getBorderColor() {
        return this.i;
    }

    public int getBorderWidth() {
        return this.j;
    }

    public ColorFilter getColorFilter() {
        return this.r;
    }

    @Deprecated public int getFillColor() {
        return this.k;
    }

    public ImageView$ScaleType getScaleType() {
        return CircleImageView.a;
    }

    protected void onDraw(Canvas arg5) {
        if(this.v) {
            super.onDraw(arg5);
            return;
        }

        if(this.l == null) {
            return;
        }

        if(this.k != 0) {
            arg5.drawCircle(this.c.centerX(), this.c.centerY(), this.p, this.h);
        }

        arg5.drawCircle(this.c.centerX(), this.c.centerY(), this.p, this.f);
        if(this.j > 0) {
            arg5.drawCircle(this.d.centerX(), this.d.centerY(), this.q, this.g);
        }
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        this.d();
    }

    public void setAdjustViewBounds(boolean arg2) {
        if(!arg2) {
            return;
        }

        throw new IllegalArgumentException("adjustViewBounds not supported.");
    }

    public void setBorderColor(int arg2) {
        if(arg2 == this.i) {
            return;
        }

        this.i = arg2;
        this.g.setColor(this.i);
        this.invalidate();
    }

    @Deprecated public void setBorderColorResource(int arg2) {
        this.setBorderColor(this.getContext().getResources().getColor(arg2));
    }

    public void setBorderOverlay(boolean arg2) {
        if(arg2 == this.u) {
            return;
        }

        this.u = arg2;
        this.d();
    }

    public void setBorderWidth(int arg2) {
        if(arg2 == this.j) {
            return;
        }

        this.j = arg2;
        this.d();
    }

    public void setColorFilter(ColorFilter arg2) {
        if(arg2 == this.r) {
            return;
        }

        this.r = arg2;
        this.b();
        this.invalidate();
    }

    public void setDisableCircularTransformation(boolean arg2) {
        if(this.v == arg2) {
            return;
        }

        this.v = arg2;
        this.c();
    }

    @Deprecated public void setFillColor(int arg2) {
        if(arg2 == this.k) {
            return;
        }

        this.k = arg2;
        this.h.setColor(arg2);
        this.invalidate();
    }

    @Deprecated public void setFillColorResource(int arg2) {
        this.setFillColor(this.getContext().getResources().getColor(arg2));
    }

    public void setImageBitmap(Bitmap arg1) {
        super.setImageBitmap(arg1);
        this.c();
    }

    public void setImageDrawable(Drawable arg1) {
        super.setImageDrawable(arg1);
        this.c();
    }

    public void setImageResource(int arg1) {
        super.setImageResource(arg1);
        this.c();
    }

    public void setImageURI(Uri arg1) {
        super.setImageURI(arg1);
        this.c();
    }

    public void setScaleType(ImageView$ScaleType arg4) {
        if(arg4 == CircleImageView.a) {
            return;
        }

        throw new IllegalArgumentException(String.format("ScaleType %s not supported.", arg4));
    }
}

