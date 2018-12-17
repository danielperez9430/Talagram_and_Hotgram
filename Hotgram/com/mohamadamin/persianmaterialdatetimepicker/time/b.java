package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.b$a;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;

public class b extends View {
    private final Paint a;
    private boolean b;
    private int c;
    private int d;
    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;

    public b(Context arg2) {
        super(arg2);
        this.a = new Paint();
        Resources v2 = arg2.getResources();
        this.c = v2.getColor(a.mdtp_circle_color);
        this.d = v2.getColor(a.mdtp_numbers_text_color);
        this.a.setAntiAlias(true);
        this.g = false;
    }

    public void a(Context arg2, boolean arg3) {
        if(this.g) {
            Log.e("CircleView", "CircleView may only be initialized once.");
            return;
        }

        Resources v2 = arg2.getResources();
        this.b = arg3;
        if(arg3) {
            this.e = Float.parseFloat(v2.getString(f.mdtp_circle_radius_multiplier_24HourMode));
        }
        else {
            this.e = Float.parseFloat(v2.getString(f.mdtp_circle_radius_multiplier));
            this.f = Float.parseFloat(v2.getString(f.mdtp_ampm_circle_radius_multiplier));
        }

        this.g = true;
    }

    void b(Context arg1, boolean arg2) {
        int v2;
        Resources v1 = arg1.getResources();
        if(arg2) {
            this.c = v1.getColor(a.mdtp_circle_background_dark_theme);
            v2 = a.mdtp_white;
        }
        else {
            this.c = v1.getColor(a.mdtp_circle_color);
            v2 = a.mdtp_numbers_text_color;
        }

        this.d = v1.getColor(v2);
    }

    public void onDraw(Canvas arg8) {
        if(this.getWidth() != 0) {
            if(!this.g) {
            }
            else {
                if(!this.h) {
                    this.i = this.getWidth() / 2;
                    this.j = this.getHeight() / 2;
                    this.k = ((int)((((float)Math.min(this.i, this.j))) * this.e));
                    if(!this.b) {
                        int v0 = ((int)((((float)this.k)) * this.f));
                        double v1 = ((double)this.j);
                        double v3 = ((double)v0);
                        Double.isNaN(v3);
                        Double.isNaN(v1);
                        this.j = ((int)(v1 - v3 * 0.75));
                    }

                    this.h = true;
                }

                this.a.setColor(this.c);
                arg8.drawCircle(((float)this.i), ((float)this.j), ((float)this.k), this.a);
                this.a.setColor(this.d);
                arg8.drawCircle(((float)this.i), ((float)this.j), 4f, this.a);
            }
        }
    }
}

