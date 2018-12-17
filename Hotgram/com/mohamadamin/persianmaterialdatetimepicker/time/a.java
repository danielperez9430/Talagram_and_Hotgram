package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint$Align;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;

public class a extends View {
    private final Paint a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private float h;
    private float i;
    private String j;
    private String k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;

    public a(Context arg1) {
        super(arg1);
        this.a = new Paint();
        this.l = false;
    }

    public void a(Context arg4, int arg5) {
        if(this.l) {
            Log.e("AmPmCirclesView", "AmPmCirclesView may only be initialized once.");
            return;
        }

        Resources v4 = arg4.getResources();
        this.d = v4.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white);
        this.g = v4.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color);
        this.c = v4.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color_dark);
        this.e = v4.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_ampm_text_color);
        this.f = v4.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white);
        this.b = 255;
        this.a.setTypeface(Typeface.create(v4.getString(f.mdtp_sans_serif), 0));
        this.a.setAntiAlias(true);
        this.a.setTextAlign(Paint$Align.CENTER);
        this.h = Float.parseFloat(v4.getString(f.mdtp_circle_radius_multiplier));
        this.i = Float.parseFloat(v4.getString(f.mdtp_ampm_circle_radius_multiplier));
        this.j = "قبل‌ازظهر";
        this.k = "بعدازظهر";
        this.setAmOrPm(arg5);
        this.s = -1;
        this.l = true;
    }

    void a(Context arg2, boolean arg3) {
        int v3;
        Resources v2 = arg2.getResources();
        int v0 = 255;
        if(arg3) {
            this.d = v2.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_circle_background_dark_theme);
            this.g = v2.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_red);
            v3 = com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white;
        }
        else {
            this.d = v2.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_white);
            this.g = v2.getColor(com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_accent_color);
            v3 = com.mohamadamin.persianmaterialdatetimepicker.b$a.mdtp_ampm_text_color;
        }

        this.e = v2.getColor(v3);
        this.b = v0;
    }

    public int a(float arg5, float arg6) {
        int v1 = -1;
        if(!this.m) {
            return v1;
        }

        arg6 = ((float)(((int)((arg6 - (((float)this.q))) * (arg6 - (((float)this.q)))))));
        if((((int)Math.sqrt(((double)((arg5 - (((float)this.o))) * (arg5 - (((float)this.o))) + arg6))))) <= this.n) {
            return 0;
        }

        if((((int)Math.sqrt(((double)((arg5 - (((float)this.p))) * (arg5 - (((float)this.p))) + arg6))))) <= this.n) {
            return 1;
        }

        return v1;
    }

    public void onDraw(Canvas arg11) {
        int v5;
        int v3;
        int v2;
        int v0;
        if(this.getWidth() != 0) {
            if(!this.l) {
            }
            else {
                if(!this.m) {
                    v0 = this.getWidth() / 2;
                    v2 = this.getHeight() / 2;
                    v3 = ((int)((((float)Math.min(v0, v2))) * this.h));
                    this.n = ((int)((((float)v3)) * this.i));
                    double v4 = ((double)v2);
                    double v6 = ((double)this.n);
                    Double.isNaN(v6);
                    Double.isNaN(v4);
                    this.a.setTextSize(((float)(this.n * 3 / 4)));
                    this.q = (((int)(v4 + v6 * 0.75))) - this.n / 2 + v3;
                    this.o = v0 - v3 + this.n;
                    this.p = v0 + v3 - this.n;
                    this.m = true;
                }

                v0 = this.d;
                v2 = this.e;
                v3 = this.d;
                int v4_1 = this.e;
                int v6_1 = 255;
                if(this.r == 0) {
                    v0 = this.g;
                    v2 = this.b;
                    v5 = this.f;
                }
                else {
                    if(this.r == 1) {
                        v3 = this.g;
                        v6_1 = this.b;
                        v4_1 = this.f;
                    }

                    v5 = v2;
                    v2 = 255;
                }

                if(this.s == 0) {
                    v0 = this.c;
                    v2 = this.b;
                }
                else if(this.s == 1) {
                    v3 = this.c;
                    v6_1 = this.b;
                }

                this.a.setColor(v0);
                this.a.setAlpha(v2);
                arg11.drawCircle(((float)this.o), ((float)this.q), ((float)this.n), this.a);
                this.a.setColor(v3);
                this.a.setAlpha(v6_1);
                arg11.drawCircle(((float)this.p), ((float)this.q), ((float)this.n), this.a);
                this.a.setColor(v5);
                float v0_1 = ((float)(this.q - (((int)(this.a.descent() + this.a.ascent()))) / 2));
                arg11.drawText(this.j, ((float)this.o), v0_1, this.a);
                this.a.setColor(v4_1);
                arg11.drawText(this.k, ((float)this.p), v0_1, this.a);
            }
        }
    }

    public void setAmOrPm(int arg1) {
        this.r = arg1;
    }

    public void setAmOrPmPressed(int arg1) {
        this.s = arg1;
    }
}

