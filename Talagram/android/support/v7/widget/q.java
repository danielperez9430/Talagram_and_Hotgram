package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.c;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class q {
    private static final int[] a;
    private final ProgressBar b;
    private Bitmap c;

    static {
        q.a = new int[]{16843067, 16843068};
    }

    q(ProgressBar arg1) {
        super();
        this.b = arg1;
    }

    private Drawable a(Drawable arg7) {
        AnimationDrawable v7;
        int v3;
        if((arg7 instanceof AnimationDrawable)) {
            int v0 = ((AnimationDrawable)arg7).getNumberOfFrames();
            AnimationDrawable v1 = new AnimationDrawable();
            v1.setOneShot(((AnimationDrawable)arg7).isOneShot());
            int v2;
            for(v2 = 0; true; ++v2) {
                v3 = 10000;
                if(v2 >= v0) {
                    break;
                }

                Drawable v4 = this.a(((AnimationDrawable)arg7).getFrame(v2), true);
                v4.setLevel(v3);
                v1.addFrame(v4, ((AnimationDrawable)arg7).getDuration(v2));
            }

            v1.setLevel(v3);
            v7 = v1;
        }

        return ((Drawable)v7);
    }

    private Drawable a(Drawable arg8, boolean arg9) {
        ShapeDrawable v8_1;
        if((arg8 instanceof c)) {
            Drawable v0 = arg8;
            Drawable v1 = ((c)v0).a();
            if(v1 != null) {
                ((c)v0).a(this.a(v1, arg9));
            }
        }
        else if((arg8 instanceof LayerDrawable)) {
            int v9 = ((LayerDrawable)arg8).getNumberOfLayers();
            Drawable[] v0_1 = new Drawable[v9];
            int v2 = 0;
            int v3;
            for(v3 = 0; v3 < v9; ++v3) {
                int v4 = ((LayerDrawable)arg8).getId(v3);
                Drawable v5 = ((LayerDrawable)arg8).getDrawable(v3);
                boolean v4_1 = v4 == 16908301 || v4 == 16908303 ? true : false;
                v0_1[v3] = this.a(v5, v4_1);
            }

            LayerDrawable v1_1 = new LayerDrawable(v0_1);
            while(v2 < v9) {
                v1_1.setId(v2, ((LayerDrawable)arg8).getId(v2));
                ++v2;
            }

            return ((Drawable)v1_1);
        }
        else {
            if(!(arg8 instanceof BitmapDrawable)) {
                goto label_63;
            }

            Bitmap v0_2 = ((BitmapDrawable)arg8).getBitmap();
            if(this.c == null) {
                this.c = v0_2;
            }

            ShapeDrawable v2_1 = new ShapeDrawable(this.b());
            v2_1.getPaint().setShader(new BitmapShader(v0_2, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
            v2_1.getPaint().setColorFilter(((BitmapDrawable)arg8).getPaint().getColorFilter());
            if(arg9) {
                ClipDrawable v8 = new ClipDrawable(((Drawable)v2_1), 3, 1);
                goto label_63;
            }

            v8_1 = v2_1;
        }

    label_63:
        return ((Drawable)v8_1);
    }

    Bitmap a() {
        return this.c;
    }

    void a(AttributeSet arg4, int arg5) {
        bk v4 = bk.a(this.b.getContext(), arg4, q.a, arg5, 0);
        Drawable v5 = v4.b(0);
        if(v5 != null) {
            this.b.setIndeterminateDrawable(this.a(v5));
        }

        v5 = v4.b(1);
        if(v5 != null) {
            this.b.setProgressDrawable(this.a(v5, false));
        }

        v4.a();
    }

    private Shape b() {
        return new RoundRectShape(new float[]{5f, 5f, 5f, 5f, 5f, 5f, 5f, 5f}, null, null);
    }
}

