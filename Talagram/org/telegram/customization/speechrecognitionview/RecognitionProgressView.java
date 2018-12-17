package org.telegram.customization.speechrecognitionview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.customization.speechrecognitionview.b.a;
import org.telegram.customization.speechrecognitionview.b.c;
import org.telegram.customization.speechrecognitionview.b.d;
import org.telegram.customization.speechrecognitionview.b.e;
import org.telegram.customization.speechrecognitionview.b.f;

public class RecognitionProgressView extends View implements RecognitionListener {
    private static final int[] a;
    private final List b;
    private Paint c;
    private a d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private boolean j;
    private boolean k;
    private SpeechRecognizer l;
    private RecognitionListener m;
    private int n;
    private int[] o;
    private int[] p;

    static {
        RecognitionProgressView.a = new int[]{60, 46, 70, 54, 64};
    }

    public RecognitionProgressView(Context arg1) {
        super(arg1);
        this.b = new ArrayList();
        this.n = -1;
        this.c();
    }

    public RecognitionProgressView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.b = new ArrayList();
        this.n = -1;
        this.c();
    }

    public RecognitionProgressView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.b = new ArrayList();
        this.n = -1;
        this.c();
    }

    static void a(RecognitionProgressView arg0) {
        arg0.j();
    }

    public void a() {
        this.g();
        this.k = true;
    }

    public void b() {
        if(this.d != null) {
            this.d.b();
            this.d = null;
        }

        this.k = false;
        this.f();
    }

    private void c() {
        this.c = new Paint();
        this.c.setFlags(1);
        this.c.setColor(-7829368);
        this.i = this.getResources().getDisplayMetrics().density;
        this.e = ((int)(this.i * 5f));
        this.f = ((int)(this.i * 11f));
        this.g = ((int)(this.i * 25f));
        this.h = ((int)(this.i * 3f));
        if(this.i <= 1.5f) {
            this.h *= 2;
        }
    }

    private void d() {
        List v0 = this.e();
        int v1 = this.getMeasuredWidth() / 2 - this.f * 2 - this.e * 4;
        int v2;
        for(v2 = 0; v2 < 5; ++v2) {
            this.b.add(new org.telegram.customization.speechrecognitionview.a(v1 + (this.e * 2 + this.f) * v2, this.getMeasuredHeight() / 2, this.e * 2, v0.get(v2).intValue(), this.e));
        }
    }

    private List e() {
        ArrayList v0 = new ArrayList();
        int v2 = 5;
        int v3 = 0;
        if(this.p == null) {
            while(v3 < v2) {
                ((List)v0).add(Integer.valueOf(((int)((((float)RecognitionProgressView.a[v3])) * this.i))));
                ++v3;
            }
        }
        else {
            while(v3 < v2) {
                ((List)v0).add(Integer.valueOf(((int)((((float)this.p[v3])) * this.i))));
                ++v3;
            }
        }

        return ((List)v0);
    }

    private void f() {
        Iterator v0 = this.b.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            ((org.telegram.customization.speechrecognitionview.a)v1).a(((org.telegram.customization.speechrecognitionview.a)v1).f());
            ((org.telegram.customization.speechrecognitionview.a)v1).b(((org.telegram.customization.speechrecognitionview.a)v1).g());
            ((org.telegram.customization.speechrecognitionview.a)v1).c(this.e * 2);
            ((org.telegram.customization.speechrecognitionview.a)v1).a();
        }
    }

    private void g() {
        this.d = new c(this.b, this.h);
        this.d.a();
    }

    private void h() {
        this.f();
        this.d = new d(this.b);
        this.d.a();
    }

    private void i() {
        this.f();
        this.d = new f(this.b, this.getWidth() / 2, this.getHeight() / 2, this.g);
        this.d.a();
        this.d.a(new org.telegram.customization.speechrecognitionview.b.f$a() {
            public void a() {
                RecognitionProgressView.a(this.a);
            }
        });
    }

    private void j() {
        this.d = new e(this.b, this.getWidth() / 2, this.getHeight() / 2);
        this.d.a();
    }

    public void onBeginningOfSpeech() {
        if(this.m != null) {
            this.m.onBeginningOfSpeech();
        }

        this.j = true;
    }

    public void onBufferReceived(byte[] arg2) {
        if(this.m != null) {
            this.m.onBufferReceived(arg2);
        }
    }

    protected void onDraw(Canvas arg6) {
        int v3;
        Paint v2;
        super.onDraw(arg6);
        if(this.b.isEmpty()) {
            return;
        }

        if(this.k) {
            this.d.c();
        }

        int v0;
        for(v0 = 0; v0 < this.b.size(); ++v0) {
            Object v1 = this.b.get(v0);
            if(this.o != null) {
                v2 = this.c;
                v3 = this.o[v0];
                goto label_20;
            }
            else if(this.n != -1) {
                v2 = this.c;
                v3 = this.n;
            label_20:
                v2.setColor(v3);
            }

            arg6.drawRoundRect(((org.telegram.customization.speechrecognitionview.a)v1).h(), ((float)this.e), ((float)this.e), this.c);
        }

        if(this.k) {
            this.invalidate();
        }
    }

    public void onEndOfSpeech() {
        if(this.m != null) {
            this.m.onEndOfSpeech();
        }

        this.j = false;
        this.i();
    }

    public void onError(int arg2) {
        if(this.m != null) {
            this.m.onError(arg2);
        }
    }

    public void onEvent(int arg2, Bundle arg3) {
        if(this.m != null) {
            this.m.onEvent(arg2, arg3);
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        if(this.b.isEmpty()) {
        label_4:
            this.d();
        }
        else if(arg1) {
            this.b.clear();
            goto label_4;
        }
    }

    public void onPartialResults(Bundle arg2) {
        if(this.m != null) {
            this.m.onPartialResults(arg2);
        }
    }

    public void onReadyForSpeech(Bundle arg2) {
        if(this.m != null) {
            this.m.onReadyForSpeech(arg2);
        }
    }

    public void onResults(Bundle arg2) {
        if(this.m != null) {
            this.m.onResults(arg2);
        }
    }

    public void onRmsChanged(float arg2) {
        if(this.m != null) {
            this.m.onRmsChanged(arg2);
        }

        if(this.d != null) {
            if(arg2 < 1f) {
            }
            else {
                if(!(this.d instanceof d) && (this.j)) {
                    this.h();
                }

                if(!(this.d instanceof d)) {
                    return;
                }

                this.d.a(arg2);
            }
        }
    }

    public void setBarMaxHeightsInDp(int[] arg6) {
        if(arg6 == null) {
            return;
        }

        int v0 = 5;
        this.p = new int[v0];
        if(arg6.length < v0) {
            System.arraycopy(arg6, 0, this.p, 0, arg6.length);
            int v1;
            for(v1 = arg6.length; v1 < v0; ++v1) {
                this.p[v1] = arg6[0];
            }
        }
        else {
            System.arraycopy(arg6, 0, this.p, 0, v0);
        }
    }

    public void setCircleRadiusInDp(int arg2) {
        this.e = ((int)((((float)arg2)) * this.i));
    }

    public void setColors(int[] arg6) {
        if(arg6 == null) {
            return;
        }

        int v0 = 5;
        this.o = new int[v0];
        if(arg6.length < v0) {
            System.arraycopy(arg6, 0, this.o, 0, arg6.length);
            int v1;
            for(v1 = arg6.length; v1 < v0; ++v1) {
                this.o[v1] = arg6[0];
            }
        }
        else {
            System.arraycopy(arg6, 0, this.o, 0, v0);
        }
    }

    public void setIdleStateAmplitudeInDp(int arg2) {
        this.h = ((int)((((float)arg2)) * this.i));
    }

    public void setRecognitionListener(RecognitionListener arg1) {
        this.m = arg1;
    }

    public void setRotationRadiusInDp(int arg2) {
        this.g = ((int)((((float)arg2)) * this.i));
    }

    public void setSingleColor(int arg1) {
        this.n = arg1;
    }

    public void setSpacingInDp(int arg2) {
        this.f = ((int)((((float)arg2)) * this.i));
    }

    public void setSpeechRecognizer(SpeechRecognizer arg1) {
        this.l = arg1;
        this.l.setRecognitionListener(((RecognitionListener)this));
    }
}

