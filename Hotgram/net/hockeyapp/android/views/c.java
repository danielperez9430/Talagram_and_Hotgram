package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.g;

@SuppressLint(value={"ViewConstructor"}) public class c extends ImageView {
    private Path a;
    private Stack b;
    private Paint c;
    private float d;
    private float e;

    @SuppressLint(value={"StaticFieldLeak"}) public c(Context arg5, Uri arg6, int arg7, int arg8) {
        super(arg5);
        this.a = new Path();
        this.b = new Stack();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setDither(true);
        this.c.setColor(-65536);
        this.c.setStyle(Paint$Style.STROKE);
        this.c.setStrokeJoin(Paint$Join.ROUND);
        this.c.setStrokeCap(Paint$Cap.ROUND);
        this.c.setStrokeWidth(12f);
        new AsyncTask() {
            protected Bitmap a(Object[] arg5) {
                Object v0 = arg5[0];
                Object v1 = arg5[1];
                Object v2 = arg5[2];
                Object v5 = arg5[3];
                try {
                    return g.a(((Context)v0), ((Uri)v1), ((Integer)v2).intValue(), ((Integer)v5).intValue());
                }
                catch(IOException v5_1) {
                    e.b("Could not load image into ImageView.", ((Throwable)v5_1));
                    return null;
                }
            }

            protected void a(Bitmap arg2) {
                if(arg2 == null) {
                    return;
                }

                this.a.setImageBitmap(arg2);
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(arg1);
            }

            protected void onPostExecute(Object arg1) {
                this.a(((Bitmap)arg1));
            }

            protected void onPreExecute() {
                this.a.setAdjustViewBounds(true);
            }
        }.execute(new Object[]{arg5, arg6, Integer.valueOf(arg7), Integer.valueOf(arg8)});
    }

    public void a() {
        this.b.clear();
        this.invalidate();
    }

    private void a(float arg2, float arg3) {
        this.a.reset();
        this.a.moveTo(arg2, arg3);
        this.d = arg2;
        this.e = arg3;
    }

    public void b() {
        if(!this.b.empty()) {
            this.b.pop();
            this.invalidate();
        }
    }

    private void b(float arg7, float arg8) {
        float v0 = Math.abs(arg7 - this.d);
        float v1 = Math.abs(arg8 - this.e);
        float v2 = 4f;
        if(v0 >= v2 || v1 >= v2) {
            this.a.quadTo(this.d, this.e, (this.d + arg7) / 2f, (this.e + arg8) / 2f);
            this.d = arg7;
            this.e = arg8;
        }
    }

    public boolean c() {
        return this.b.empty();
    }

    private void d() {
        this.a.lineTo(this.d, this.e);
        this.b.push(this.a);
        this.a = new Path();
    }

    protected void onDraw(Canvas arg4) {
        super.onDraw(arg4);
        Iterator v0 = this.b.iterator();
        while(v0.hasNext()) {
            arg4.drawPath(v0.next(), this.c);
        }

        arg4.drawPath(this.a, this.c);
    }

    @SuppressLint(value={"ClickableViewAccessibility"}) public boolean onTouchEvent(MotionEvent arg3) {
        float v0 = arg3.getX();
        float v1 = arg3.getY();
        switch(arg3.getAction()) {
            case 0: {
                goto label_9;
            }
            case 1: {
                goto label_7;
            }
            case 2: {
                goto label_5;
            }
        }

        return 1;
    label_5:
        this.b(v0, v1);
        goto label_10;
    label_7:
        this.d();
        goto label_10;
    label_9:
        this.a(v0, v1);
    label_10:
        this.invalidate();
        return 1;
    }
}

