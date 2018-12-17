package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;

public class c extends View {
    class com.mohamadamin.persianmaterialdatetimepicker.time.c$1 {
    }

    class a implements ValueAnimator$AnimatorUpdateListener {
        a(c arg1, com.mohamadamin.persianmaterialdatetimepicker.time.c$1 arg2) {
            this(arg1);
        }

        private a(c arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationUpdate(ValueAnimator arg1) {
            this.a.invalidate();
        }
    }

    private final Paint a;
    private boolean b;
    private boolean c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private int p;
    private float q;
    private float r;
    private int s;
    private int t;
    private a u;
    private int v;
    private double w;
    private boolean x;

    public c(Context arg1) {
        super(arg1);
        this.a = new Paint();
        this.b = false;
    }

    public int a(float arg10, float arg11, boolean arg12, Boolean[] arg13) {
        int v12;
        double v12_1;
        int v1 = -1;
        if(!this.c) {
            return v1;
        }

        double v2 = Math.sqrt(((double)((arg11 - (((float)this.o))) * (arg11 - (((float)this.o))) + (arg10 - (((float)this.n))) * (arg10 - (((float)this.n))))));
        int v4 = 1;
        if(!this.l) {
            if(arg12) {
                goto label_107;
            }

            v12_1 = ((double)this.s);
            Double.isNaN(v12_1);
            if((((int)Math.abs(v2 - v12_1))) <= (((int)((((float)this.p)) * (1f - this.h))))) {
                goto label_107;
            }

            return v1;
        }
        else if(arg12) {
            double v0 = ((double)(((int)((((float)this.p)) * this.f))));
            Double.isNaN(v0);
            v12 = ((int)Math.abs(v2 - v0));
            v0 = ((double)(((int)((((float)this.p)) * this.g))));
            Double.isNaN(v0);
            arg12 = v12 <= (((int)Math.abs(v2 - v0))) ? true : false;
            arg13[0] = Boolean.valueOf(arg12);
        }
        else {
            v12 = (((int)((((float)this.p)) * this.f))) - this.t;
            int v0_1 = (((int)((((float)this.p)) * this.g))) + this.t;
            int v6 = ((int)((((float)this.p)) * ((this.g + this.f) / 2f)));
            if(v2 >= (((double)v12)) && v2 <= (((double)v6))) {
                arg13[0] = Boolean.valueOf(true);
                goto label_107;
            }

            if(v2 <= (((double)v0_1)) && v2 >= (((double)v6))) {
                arg13[0] = Boolean.valueOf(false);
                goto label_107;
            }

            return v1;
        }

    label_107:
        v12_1 = ((double)Math.abs(arg11 - (((float)this.o))));
        Double.isNaN(v12_1);
        v12 = ((int)(Math.asin(v12_1 / v2) * 180 / 3.141593));
        int v10 = arg10 > (((float)this.n)) ? 1 : 0;
        if(arg11 < (((float)this.o))) {
        }
        else {
            v4 = 0;
        }

        if(v10 == 0 || v4 == 0) {
            if(v10 != 0 && v4 == 0) {
                return 90;
            }

            if(v10 == 0 && v4 == 0) {
                return 270 - v12;
            }

            if(v10 != 0) {
                return v12;
            }

            if(v4 == 0) {
                return v12;
            }

            v12 += 270;
        }
        else {
            v12 = 90 - v12;
        }

        return v12;
    }

    public void a(int arg5, boolean arg6, boolean arg7) {
        this.v = arg5;
        double v0 = ((double)arg5);
        Double.isNaN(v0);
        this.w = v0 * 3.141593 / 180;
        this.x = arg7;
        if(this.l) {
            float v5 = arg6 ? this.f : this.g;
            this.h = v5;
        }
    }

    public void a(Context arg3, boolean arg4, boolean arg5, boolean arg6, int arg7, boolean arg8) {
        if(this.b) {
            Log.e("RadialSelectorView", "This RadialSelectorView may only be initialized once.");
            return;
        }

        Resources v3 = arg3.getResources();
        this.a.setColor(v3.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color));
        this.a.setAntiAlias(true);
        this.m = 255;
        this.k = arg4;
        if(arg4) {
            this.d = Float.parseFloat(v3.getString(f.mdtp_circle_radius_multiplier_24HourMode));
        }
        else {
            this.d = Float.parseFloat(v3.getString(f.mdtp_circle_radius_multiplier));
            this.e = Float.parseFloat(v3.getString(f.mdtp_ampm_circle_radius_multiplier));
        }

        this.l = arg5;
        if(arg5) {
            this.f = Float.parseFloat(v3.getString(f.mdtp_numbers_radius_multiplier_inner));
            this.g = Float.parseFloat(v3.getString(f.mdtp_numbers_radius_multiplier_outer));
        }
        else {
            this.h = Float.parseFloat(v3.getString(f.mdtp_numbers_radius_multiplier_normal));
        }

        this.i = Float.parseFloat(v3.getString(f.mdtp_selection_radius_multiplier));
        float v3_1 = 1f;
        this.j = v3_1;
        float v4 = 0.05f;
        int v5 = -1;
        int v0 = arg6 ? -1 : 1;
        this.q = (((float)v0)) * v4 + v3_1;
        v4 = 0.3f;
        if(arg6) {
            v5 = 1;
        }

        this.r = (((float)v5)) * v4 + v3_1;
        this.u = new a(this, null);
        this.a(arg7, arg8, false);
        this.b = true;
    }

    void a(Context arg2, boolean arg3) {
        Resources v2 = arg2.getResources();
        int v0 = 255;
        int v3 = arg3 ? com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_red : com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color;
        int v2_1 = v2.getColor(v3);
        this.m = v0;
        this.a.setColor(v2_1);
    }

    public ObjectAnimator getDisappearAnimator() {
        if(this.b) {
            if(!this.c) {
            }
            else {
                ObjectAnimator v0 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(0.2f, this.q), Keyframe.ofFloat(1f, this.r)}), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(1f, 0f)})}).setDuration(((long)500));
                v0.addUpdateListener(this.u);
                return v0;
            }
        }

        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        if(this.b) {
            if(!this.c) {
            }
            else {
                float v1 = ((float)500);
                int v3 = ((int)(1.25f * v1));
                v1 = v1 * 0.25f / (((float)v3));
                ObjectAnimator v0 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{Keyframe.ofFloat(0f, this.r), Keyframe.ofFloat(v1, this.r), Keyframe.ofFloat(1f - (1f - v1) * 0.2f, this.q), Keyframe.ofFloat(1f, 1f)}), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(v1, 0f), Keyframe.ofFloat(1f, 1f)})}).setDuration(((long)v3));
                v0.addUpdateListener(this.u);
                return v0;
            }
        }

        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void onDraw(Canvas arg10) {
        double v4;
        double v2;
        int v0;
        if(this.getWidth() != 0) {
            if(!this.b) {
            }
            else {
                int v1 = 1;
                if(!this.c) {
                    this.n = this.getWidth() / 2;
                    this.o = this.getHeight() / 2;
                    this.p = ((int)((((float)Math.min(this.n, this.o))) * this.d));
                    if(!this.k) {
                        v0 = ((int)((((float)this.p)) * this.e));
                        v2 = ((double)this.o);
                        v4 = ((double)v0);
                        Double.isNaN(v4);
                        Double.isNaN(v2);
                        this.o = ((int)(v2 - v4 * 0.75));
                    }

                    this.t = ((int)((((float)this.p)) * this.i));
                    this.c = true;
                }

                this.s = ((int)((((float)this.p)) * this.h * this.j));
                v0 = this.n;
                v2 = ((double)this.s);
                v4 = Math.sin(this.w);
                Double.isNaN(v2);
                v0 += ((int)(v2 * v4));
                int v2_1 = this.o;
                double v3 = ((double)this.s);
                double v5 = Math.cos(this.w);
                Double.isNaN(v3);
                v2_1 -= ((int)(v3 * v5));
                this.a.setAlpha(this.m);
                float v3_1 = ((float)v0);
                float v4_1 = ((float)v2_1);
                arg10.drawCircle(v3_1, v4_1, ((float)this.t), this.a);
                boolean v5_1 = this.x;
                if(this.v % 30 != 0) {
                }
                else {
                    v1 = 0;
                }

                v1 |= ((int)v5_1);
                int v5_2 = 255;
                if(v1 != 0) {
                    this.a.setAlpha(v5_2);
                    arg10.drawCircle(v3_1, v4_1, ((float)(this.t * 2 / 7)), this.a);
                }
                else {
                    v0 = this.s - this.t;
                    v1 = this.n;
                    v2 = ((double)v0);
                    double v6 = Math.sin(this.w);
                    Double.isNaN(v2);
                    v0 = (((int)(v6 * v2))) + v1;
                    v1 = this.o;
                    v6 = Math.cos(this.w);
                    Double.isNaN(v2);
                    v2_1 = v1 - (((int)(v2 * v6)));
                }

                this.a.setAlpha(v5_2);
                this.a.setStrokeWidth(1f);
                arg10.drawLine(((float)this.n), ((float)this.o), ((float)v0), ((float)v2_1), this.a);
            }
        }
    }

    public void setAnimationRadiusMultiplier(float arg1) {
        this.j = arg1;
    }
}

