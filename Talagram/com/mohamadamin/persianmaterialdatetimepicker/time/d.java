package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint$Align;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;

public class d extends View {
    class com.mohamadamin.persianmaterialdatetimepicker.time.d$1 {
    }

    class a implements ValueAnimator$AnimatorUpdateListener {
        a(d arg1, com.mohamadamin.persianmaterialdatetimepicker.time.d$1 arg2) {
            this(arg1);
        }

        private a(d arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationUpdate(ValueAnimator arg1) {
            this.a.invalidate();
        }
    }

    private float[] A;
    private float[] B;
    private float[] C;
    private float D;
    private float E;
    private float F;
    private a G;
    ObjectAnimator a;
    ObjectAnimator b;
    private final Paint c;
    private final Paint d;
    private boolean e;
    private boolean f;
    private int g;
    private Typeface h;
    private Typeface i;
    private String[] j;
    private String[] k;
    private boolean l;
    private boolean m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private int t;
    private int u;
    private float v;
    private boolean w;
    private float x;
    private float y;
    private float[] z;

    public d(Context arg1) {
        super(arg1);
        this.c = new Paint();
        this.d = new Paint();
        this.g = -1;
        this.f = false;
    }

    public void a(Resources arg5, String[] arg6, String[] arg7, boolean arg8, boolean arg9) {
        if(this.f) {
            Log.e("RadialTextsView", "This RadialTextsView may only be initialized once.");
            return;
        }

        this.c.setColor(arg5.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_numbers_text_color));
        boolean v1 = false;
        this.h = Typeface.create(arg5.getString(f.mdtp_radial_numbers_typeface), 0);
        this.i = Typeface.create(arg5.getString(f.mdtp_sans_serif), 0);
        this.c.setAntiAlias(true);
        this.c.setTextAlign(Paint$Align.CENTER);
        this.d.setColor(arg5.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white));
        this.d.setAntiAlias(true);
        this.d.setTextAlign(Paint$Align.CENTER);
        this.j = arg6;
        this.k = arg7;
        this.l = arg8;
        if(arg7 != null) {
            v1 = true;
        }

        this.m = v1;
        if(arg8) {
            this.n = Float.parseFloat(arg5.getString(f.mdtp_circle_radius_multiplier_24HourMode));
        }
        else {
            this.n = Float.parseFloat(arg5.getString(f.mdtp_circle_radius_multiplier));
            this.o = Float.parseFloat(arg5.getString(f.mdtp_ampm_circle_radius_multiplier));
        }

        int v6 = 7;
        this.z = new float[v6];
        this.A = new float[v6];
        if(this.m) {
            this.p = Float.parseFloat(arg5.getString(f.mdtp_numbers_radius_multiplier_outer));
            this.r = Float.parseFloat(arg5.getString(f.mdtp_text_size_multiplier_outer));
            this.q = Float.parseFloat(arg5.getString(f.mdtp_numbers_radius_multiplier_inner));
            this.s = Float.parseFloat(arg5.getString(f.mdtp_text_size_multiplier_inner));
            this.B = new float[v6];
            this.C = new float[v6];
        }
        else {
            this.p = Float.parseFloat(arg5.getString(f.mdtp_numbers_radius_multiplier_normal));
            this.r = Float.parseFloat(arg5.getString(f.mdtp_text_size_multiplier_normal));
        }

        float v5 = 1f;
        this.D = v5;
        float v6_1 = 0.05f;
        int v7 = -1;
        int v8 = arg9 ? -1 : 1;
        this.E = (((float)v8)) * v6_1 + v5;
        v6_1 = 0.3f;
        if(arg9) {
            v7 = 1;
        }

        this.F = (((float)v7)) * v6_1 + v5;
        this.G = new a(this, null);
        this.w = true;
        this.f = true;
    }

    void a(Context arg1, boolean arg2) {
        Resources v1 = arg1.getResources();
        int v2 = arg2 ? com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white : com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_numbers_text_color;
        int v1_1 = v1.getColor(v2);
        this.c.setColor(v1_1);
    }

    private void a() {
        this.a = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(0.2f, this.E), Keyframe.ofFloat(1f, this.F)}), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(1f, 0f)})}).setDuration(((long)500));
        this.a.addUpdateListener(this.G);
        float v5 = ((float)500);
        int v6 = ((int)(1.25f * v5));
        v5 = v5 * 0.25f / (((float)v6));
        this.b = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{Keyframe.ofFloat(0f, this.F), Keyframe.ofFloat(v5, this.F), Keyframe.ofFloat(1f - (1f - v5) * 0.2f, this.E), Keyframe.ofFloat(1f, 1f)}), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(v5, 0f), Keyframe.ofFloat(1f, 1f)})}).setDuration(((long)v6));
        this.b.addUpdateListener(this.G);
    }

    private void a(float arg5, float arg6, float arg7, float arg8, float[] arg9, float[] arg10) {
        float v0 = (((float)Math.sqrt(3))) * arg5 / 2f;
        float v2 = arg5 / 2f;
        this.c.setTextSize(arg8);
        this.d.setTextSize(arg8);
        arg7 -= (this.c.descent() + this.c.ascent()) / 2f;
        arg9[0] = arg7 - arg5;
        arg10[0] = arg6 - arg5;
        arg9[1] = arg7 - v0;
        arg10[1] = arg6 - v0;
        arg9[2] = arg7 - v2;
        arg10[2] = arg6 - v2;
        arg9[3] = arg7;
        arg10[3] = arg6;
        arg9[4] = arg7 + v2;
        arg10[4] = v2 + arg6;
        arg9[5] = arg7 + v0;
        arg10[5] = v0 + arg6;
        arg9[6] = arg7 + arg5;
        arg10[6] = arg6 + arg5;
    }

    private void a(Canvas arg17, float arg18, Typeface arg19, String[] arg20, float[] arg21, float[] arg22) {
        d v0 = this;
        Canvas v1 = arg17;
        v0.c.setTextSize(arg18);
        v0.c.setTypeface(arg19);
        com.mohamadamin.persianmaterialdatetimepicker.a.a.a(arg20);
        String v6 = arg20[0];
        int v7 = 3;
        float v8 = arg21[v7];
        float v9 = arg22[0];
        Paint v10 = Integer.parseInt(arg20[0]) == v0.g ? v0.d : v0.c;
        v1.drawText(v6, v8, v9, v10);
        String v8_1 = arg20[1];
        int v9_1 = 4;
        float v10_1 = arg21[v9_1];
        float v11 = arg22[1];
        Paint v12 = Integer.parseInt(arg20[1]) == v0.g ? v0.d : v0.c;
        v1.drawText(v8_1, v10_1, v11, v12);
        int v8_2 = 2;
        String v10_2 = arg20[v8_2];
        int v11_1 = 5;
        float v12_1 = arg21[v11_1];
        float v13 = arg22[v8_2];
        Paint v14 = Integer.parseInt(arg20[v8_2]) == v0.g ? v0.d : v0.c;
        v1.drawText(v10_2, v12_1, v13, v14);
        v10_2 = arg20[v7];
        int v12_2 = 6;
        v13 = arg21[v12_2];
        float v14_1 = arg22[v7];
        Paint v5 = Integer.parseInt(arg20[v7]) == v0.g ? v0.d : v0.c;
        v1.drawText(v10_2, v13, v14_1, v5);
        String v5_1 = arg20[v9_1];
        v10_1 = arg21[v11_1];
        v13 = arg22[v9_1];
        v14 = Integer.parseInt(arg20[v9_1]) == v0.g ? v0.d : v0.c;
        v1.drawText(v5_1, v10_1, v13, v14);
        v5_1 = arg20[v11_1];
        v10_1 = arg21[v9_1];
        v13 = arg22[v11_1];
        v14 = Integer.parseInt(arg20[v11_1]) == v0.g ? v0.d : v0.c;
        v1.drawText(v5_1, v10_1, v13, v14);
        v5_1 = arg20[v12_2];
        v10_1 = arg21[v7];
        v13 = arg22[v12_2];
        v12 = Integer.parseInt(arg20[v12_2]) == v0.g ? v0.d : v0.c;
        v1.drawText(v5_1, v10_1, v13, v12);
        v10_2 = arg20[7];
        v12_1 = arg21[v8_2];
        v11 = arg22[v11_1];
        v5 = Integer.parseInt(arg20[7]) == v0.g ? v0.d : v0.c;
        v1.drawText(v10_2, v12_1, v11, v5);
        v10_2 = arg20[8];
        v11 = arg21[1];
        v9 = arg22[v9_1];
        v5 = Integer.parseInt(arg20[8]) == v0.g ? v0.d : v0.c;
        v1.drawText(v10_2, v11, v9, v5);
        String v9_2 = arg20[9];
        v10_1 = arg21[0];
        float v7_1 = arg22[v7];
        v5 = Integer.parseInt(arg20[9]) == v0.g ? v0.d : v0.c;
        v1.drawText(v9_2, v10_1, v7_1, v5);
        String v7_2 = arg20[10];
        v9 = arg21[1];
        v10_1 = arg22[v8_2];
        v5 = Integer.parseInt(arg20[10]) == v0.g ? v0.d : v0.c;
        v1.drawText(v7_2, v9, v10_1, v5);
        v7_2 = arg20[11];
        float v3 = arg21[v8_2];
        float v4 = arg22[1];
        Paint v2 = Integer.parseInt(arg20[11]) == v0.g ? v0.d : v0.c;
        v1.drawText(v7_2, v3, v4, v2);
    }

    public ObjectAnimator getDisappearAnimator() {
        if((this.f) && (this.e)) {
            if(this.a == null) {
            }
            else {
                return this.a;
            }
        }

        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        if((this.f) && (this.e)) {
            if(this.b == null) {
            }
            else {
                return this.b;
            }
        }

        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void onDraw(Canvas arg10) {
        if(this.getWidth() != 0) {
            if(!this.f) {
            }
            else {
                if(!this.e) {
                    this.t = this.getWidth() / 2;
                    this.u = this.getHeight() / 2;
                    this.v = (((float)Math.min(this.t, this.u))) * this.n;
                    if(!this.l) {
                        float v0 = this.v * this.o;
                        double v1 = ((double)this.u);
                        double v3 = ((double)v0);
                        Double.isNaN(v3);
                        Double.isNaN(v1);
                        this.u = ((int)(v1 - v3 * 0.75));
                    }

                    this.x = this.v * this.r;
                    if(this.m) {
                        this.y = this.v * this.s;
                    }

                    this.a();
                    this.w = true;
                    this.e = true;
                }

                if(this.w) {
                    this.a(this.v * this.p * this.D, ((float)this.t), ((float)this.u), this.x, this.z, this.A);
                    if(this.m) {
                        this.a(this.v * this.q * this.D, ((float)this.t), ((float)this.u), this.y, this.B, this.C);
                    }

                    this.w = false;
                }

                this.a(arg10, this.x, this.h, this.j, this.A, this.z);
                if(!this.m) {
                    return;
                }

                this.a(arg10, this.y, this.i, this.k, this.C, this.B);
            }
        }
    }

    public void setAnimationRadiusMultiplier(float arg1) {
        this.D = arg1;
        this.w = true;
    }

    protected void setSelection(int arg1) {
        this.g = arg1;
    }
}

