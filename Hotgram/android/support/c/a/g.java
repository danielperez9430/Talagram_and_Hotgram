package android.support.c.a;

import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v4.graphics.b;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

public class g implements Interpolator {
    private float[] a;
    private float[] b;

    public g(Context arg2, AttributeSet arg3, XmlPullParser arg4) {
        this(arg2.getResources(), arg2.getTheme(), arg3, arg4);
    }

    public g(Resources arg2, Resources$Theme arg3, AttributeSet arg4, XmlPullParser arg5) {
        super();
        TypedArray v2 = android.support.v4.content.a.g.a(arg2, arg3, arg4, a.l);
        this.a(v2, arg5);
        v2.recycle();
    }

    private void a(TypedArray arg7, XmlPullParser arg8) {
        if(android.support.v4.content.a.g.a(arg8, "pathData")) {
            String v7 = android.support.v4.content.a.g.a(arg7, arg8, "pathData", 4);
            Path v8 = b.a(v7);
            if(v8 != null) {
                this.a(v8);
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append("The path is null, which is created from ");
                v0.append(v7);
                throw new InflateException(v0.toString());
            }
        }
        else if(!android.support.v4.content.a.g.a(arg8, "controlX1")) {
            goto label_56;
        }
        else if(android.support.v4.content.a.g.a(arg8, "controlY1")) {
            float v0_1 = android.support.v4.content.a.g.a(arg7, arg8, "controlX1", 0, 0f);
            float v1 = android.support.v4.content.a.g.a(arg7, arg8, "controlY1", 1, 0f);
            boolean v3 = android.support.v4.content.a.g.a(arg8, "controlX2");
            if(v3 != android.support.v4.content.a.g.a(arg8, "controlY2")) {
                goto label_48;
            }
            else if(!v3) {
                this.a(v0_1, v1);
            }
            else {
                this.a(v0_1, v1, android.support.v4.content.a.g.a(arg7, arg8, "controlX2", 2, 0f), android.support.v4.content.a.g.a(arg7, arg8, "controlY2", 3, 0f));
            }
        }
        else {
            goto label_52;
        }

        return;
    label_48:
        throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
    label_52:
        throw new InflateException("pathInterpolator requires the controlY1 attribute");
    label_56:
        throw new InflateException("pathInterpolator requires the controlX1 attribute");
    }

    private void a(float arg3, float arg4) {
        Path v0 = new Path();
        v0.moveTo(0f, 0f);
        v0.quadTo(arg3, arg4, 1f, 1f);
        this.a(v0);
    }

    private void a(Path arg11) {
        float v3;
        int v11_1;
        int v1 = 0;
        PathMeasure v0 = new PathMeasure(arg11, false);
        float v11 = v0.getLength();
        int v2 = Math.min(3000, (((int)(v11 / 0.002f))) + 1);
        if(v2 <= 0) {
            goto label_117;
        }

        this.a = new float[v2];
        this.b = new float[v2];
        float[] v4 = new float[2];
        int v5;
        for(v5 = 0; v5 < v2; ++v5) {
            v0.getPosTan((((float)v5)) * v11 / (((float)(v2 - 1))), v4, null);
            this.a[v5] = v4[0];
            this.b[v5] = v4[1];
        }

        double v6 = 0.00001;
        if((((double)Math.abs(this.a[0]))) <= v6 && (((double)Math.abs(this.b[0]))) <= v6) {
            int v4_1 = v2 - 1;
            float v5_1 = 1f;
            if((((double)Math.abs(this.a[v4_1] - v5_1))) <= v6 && (((double)Math.abs(this.b[v4_1] - v5_1))) <= v6) {
                v11_1 = 0;
                v3 = 0f;
                goto label_63;
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("The Path must start at (0,0) and end at (1,1) start: ");
        v0_1.append(this.a[0]);
        v0_1.append(",");
        v0_1.append(this.b[0]);
        v0_1.append(" end:");
        --v2;
        v0_1.append(this.a[v2]);
        v0_1.append(",");
        v0_1.append(this.b[v2]);
        throw new IllegalArgumentException(v0_1.toString());
        while(true) {
        label_63:
            if(v1 >= v2) {
                goto label_83;
            }

            v5 = v11_1 + 1;
            v11 = this.a[v11_1];
            if(v11 < v3) {
                break;
            }

            this.a[v1] = v11;
            ++v1;
            v3 = v11;
            v11_1 = v5;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("The Path cannot loop back on itself, x :");
        v1_1.append(v11);
        throw new IllegalArgumentException(v1_1.toString());
    label_83:
        if(!v0.nextContour()) {
            return;
        }

        throw new IllegalArgumentException("The Path should be continuous, can\'t have 2+ contours");
    label_117:
        v1_1 = new StringBuilder();
        v1_1.append("The Path has a invalid length ");
        v1_1.append(v11);
        throw new IllegalArgumentException(v1_1.toString());
    }

    private void a(float arg9, float arg10, float arg11, float arg12) {
        Path v7 = new Path();
        v7.moveTo(0f, 0f);
        v7.cubicTo(arg9, arg10, arg11, arg12, 1f, 1f);
        this.a(v7);
    }

    public float getInterpolation(float arg7) {
        if(arg7 <= 0f) {
            return 0;
        }

        float v1 = 1f;
        if(arg7 >= v1) {
            return v1;
        }

        int v1_1 = 0;
        int v2 = this.a.length - 1;
        while(v2 - v1_1 > 1) {
            int v4 = (v1_1 + v2) / 2;
            if(arg7 < this.a[v4]) {
                v2 = v4;
                continue;
            }

            v1_1 = v4;
        }

        float v3 = this.a[v2] - this.a[v1_1];
        if(v3 == 0f) {
            return this.b[v1_1];
        }

        return this.b[v1_1] + (arg7 - this.a[v1_1]) / v3 * (this.b[v2] - this.b[v1_1]);
    }
}

