package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.widget.ImageView;

class c extends ImageView {
    class a extends OvalShape {
        private RadialGradient b;
        private Paint c;

        a(c arg2, int arg3) {
            this.a = arg2;
            super();
            this.c = new Paint();
            arg2.a = arg3;
            this.a(((int)this.rect().width()));
        }

        private void a(int arg9) {
            float v2 = ((float)(arg9 / 2));
            this.b = new RadialGradient(v2, v2, ((float)this.a.a), new int[]{1023410176, 0}, null, Shader$TileMode.CLAMP);
            this.c.setShader(this.b);
        }

        public void draw(Canvas arg5, Paint arg6) {
            int v0 = this.a.getWidth() / 2;
            float v2 = ((float)v0);
            float v1 = ((float)(this.a.getHeight() / 2));
            arg5.drawCircle(v2, v1, v2, this.c);
            arg5.drawCircle(v2, v1, ((float)(v0 - this.a.a)), arg6);
        }

        protected void onResize(float arg1, float arg2) {
            super.onResize(arg1, arg2);
            this.a(((int)arg1));
        }
    }

    int a;
    private Animation$AnimationListener b;

    c(Context arg6, int arg7) {
        ShapeDrawable v0_1;
        super(arg6);
        float v6 = this.getContext().getResources().getDisplayMetrics().density;
        int v0 = ((int)(1.75f * v6));
        int v1 = ((int)(0f * v6));
        this.a = ((int)(3.5f * v6));
        if(this.a()) {
            v0_1 = new ShapeDrawable(new OvalShape());
            t.k(((View)this), v6 * 4f);
        }
        else {
            ShapeDrawable v2 = new ShapeDrawable(new a(this, this.a));
            this.setLayerType(1, v2.getPaint());
            v2.getPaint().setShadowLayer(((float)this.a), ((float)v1), ((float)v0), 503316480);
            this.setPadding(this.a, this.a, this.a, this.a);
            v0_1 = v2;
        }

        v0_1.getPaint().setColor(arg7);
        t.a(((View)this), ((Drawable)v0_1));
    }

    private boolean a() {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        return v0;
    }

    public void a(Animation$AnimationListener arg1) {
        this.b = arg1;
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if(this.b != null) {
            this.b.onAnimationEnd(this.getAnimation());
        }
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if(this.b != null) {
            this.b.onAnimationStart(this.getAnimation());
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(arg2, arg3);
        if(!this.a()) {
            this.setMeasuredDimension(this.getMeasuredWidth() + this.a * 2, this.getMeasuredHeight() + this.a * 2);
        }
    }

    public void setBackgroundColor(int arg2) {
        if((this.getBackground() instanceof ShapeDrawable)) {
            this.getBackground().getPaint().setColor(arg2);
        }
    }
}

