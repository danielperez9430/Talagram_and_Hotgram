package com.b.a.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.WindowManager$LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public class a extends FrameLayout {
    final class com.b.a.a.a$a extends AsyncTask {
        interface com.b.a.a.a$a$a {
            void a(Bitmap arg1);
        }

        private WeakReference a;
        private WeakReference b;
        private Bitmap c;
        private com.b.a.a.a$a$a d;

        com.b.a.a.a$a(View arg4, int arg5, int arg6, a arg7, com.b.a.a.a$a$a arg8) {
            super();
            this.a = new WeakReference(arg4.getContext());
            this.b = new WeakReference(arg7);
            this.d = arg8;
            int v8 = arg4.getHeight() - arg5 - arg6;
            if(v8 < 0) {
                v8 = arg4.getHeight();
            }

            Drawable v6 = arg4.getBackground();
            this.c = Bitmap.createBitmap(arg4.getWidth(), v8, Bitmap$Config.ARGB_8888);
            Canvas v8_1 = new Canvas(this.c);
            int v0 = 0;
            if(arg5 != 0) {
                v0 = v8_1.save();
                v8_1.translate(0f, ((float)(-arg5)));
            }

            if(arg7.getBlurRadius() > 0f) {
                if(v6 == null) {
                    v8_1.drawColor(-1);
                }

                arg4.draw(v8_1);
            }

            if(arg7.getTintColor() != 0) {
                v8_1.drawColor(arg7.getTintColor());
            }

            if(arg5 != 0 && v0 != 0) {
                v8_1.restoreToCount(v0);
            }
        }

        protected Bitmap a(Void[] arg6) {
            Object v6 = this.a.get();
            Object v0 = this.b.get();
            if(v6 != null) {
                if(v0 == null) {
                }
                else {
                    float v1 = ((a)v0).getScaleRatio();
                    if(((a)v0).getBlurRadius() == 0f) {
                        return this.c;
                    }
                    else {
                        return Bitmap.createScaledBitmap(b.a(((Context)v6), Bitmap.createScaledBitmap(this.c, ((int)((((float)this.c.getWidth())) * v1)), ((int)((((float)this.c.getHeight())) * v1)), false), ((a)v0).getBlurRadius()), this.c.getWidth(), this.c.getHeight(), true);
                    }
                }
            }

            return null;
        }

        protected void a(Bitmap arg6) {
            Object v0 = this.b.get();
            if(v0 != null && ((a)v0).getAnchorView() != null) {
                Canvas v1 = new Canvas(arg6);
                View v2 = ((a)v0).getAnchorView();
                int[] v3 = new int[2];
                v2.getLocationInWindow(v3);
                v1.save();
                v1.translate(((float)v3[0]), ((float)v3[1]));
                ((a)v0).getAnchorView().draw(v1);
                v1.restore();
            }

            if(this.d != null) {
                this.d.a(arg6);
            }
        }

        protected Object doInBackground(Object[] arg1) {
            return this.a(((Void[])arg1));
        }

        protected void onPostExecute(Object arg1) {
            this.a(((Bitmap)arg1));
        }
    }

    public class com.b.a.a.a$b {
        protected Context a;
        private View b;
        private int c;
        private float d;
        private float e;
        private long f;
        private boolean g;
        private boolean h;
        private int i;
        private c j;

        public com.b.a.a.a$b(Context arg3) {
            super();
            this.g = true;
            this.h = true;
            this.i = -1;
            this.a = arg3;
            this.d = 10f;
            this.e = 0.4f;
            this.f = 300;
        }

        public com.b.a.a.a$b a(float arg4) {
            if(arg4 > 0f) {
                if(arg4 > 1f) {
                }
                else {
                    this.e = arg4;
                    return this;
                }
            }

            Log.w("BlurPopupWindow.Builder", "scaleRatio invalid: " + arg4 + ". It can only be (0, 1]");
            return this;
        }

        public com.b.a.a.a$b a(int arg4) {
            this.b = LayoutInflater.from(this.a).inflate(arg4, new FrameLayout(this.a), false);
            return this;
        }

        public com.b.a.a.a$b a(long arg4) {
            if(arg4 < 0) {
                Log.w("BlurPopupWindow.Builder", "animatingDuration invalid: " + arg4 + ". It can only be (0, ..)");
                return this;
            }

            this.f = arg4;
            return this;
        }

        public com.b.a.a.a$b a(View$OnClickListener arg5, int[] arg6) {
            if(this.b != null) {
                int v0 = arg6.length;
                int v1;
                for(v1 = 0; v1 < v0; ++v1) {
                    View v2 = this.b.findViewById(arg6[v1]);
                    if(v2 != null) {
                        v2.setOnClickListener(arg5);
                    }
                }
            }

            return this;
        }

        protected a a() {
            return new a(this.a);
        }

        public com.b.a.a.a$b b(float arg4) {
            if(arg4 >= 0f) {
                if(arg4 > 25f) {
                }
                else {
                    this.d = arg4;
                    return this;
                }
            }

            Log.w("BlurPopupWindow.Builder", "blurRadius invalid: " + arg4 + ". It can only be [0, 25]");
            return this;
        }

        public com.b.a.a.a$b b(int arg1) {
            this.i = arg1;
            return this;
        }

        public a b() {
            FrameLayout$LayoutParams v1_1;
            a v0 = this.a();
            if(this.b != null) {
                ViewGroup$LayoutParams v1 = this.b.getLayoutParams();
                if(v1 == null || !(v1 instanceof FrameLayout$LayoutParams)) {
                    v1_1 = new FrameLayout$LayoutParams(v1.width, v1.height);
                }

                if(this.i != -1) {
                    ((ViewGroup$LayoutParams)v1_1).gravity = this.i;
                }

                this.b.setLayoutParams(((ViewGroup$LayoutParams)v1_1));
                v0.setContentView(this.b);
            }

            v0.setTintColor(this.c);
            v0.setAnimationDuration(this.f);
            v0.setBlurRadius(this.d);
            v0.setScaleRatio(this.e);
            v0.setDismissOnTouchBackground(this.g);
            v0.setDismissOnClickBack(this.h);
            v0.setOnDismissListener(this.j);
            return v0;
        }

        public com.b.a.a.a$b c(int arg1) {
            this.c = arg1;
            return this;
        }
    }

    public interface c {
        void a(a arg1);
    }

    protected ImageView a;
    protected FrameLayout b;
    private Activity c;
    private boolean d;
    private WindowManager e;
    private View f;
    private int g;
    private View h;
    private float i;
    private float j;
    private long k;
    private boolean l;
    private boolean m;
    private c n;

    public a(Context arg1) {
        super(arg1);
        this.g();
    }

    private static int a(Activity arg5) {
        int v0_1;
        if(arg5 == null) {
            return 0;
        }

        Display v1 = arg5.getWindowManager().getDefaultDisplay();
        int v5 = arg5.getResources().getDisplayMetrics().heightPixels;
        if(Build$VERSION.SDK_INT >= 17) {
            DisplayMetrics v0 = new DisplayMetrics();
            v1.getRealMetrics(v0);
            v0_1 = v0.heightPixels;
            goto label_27;
        }

        try {
            v0_1 = Display.class.getMethod("getRawHeight").invoke(v1).intValue();
        }
        catch(Exception v1_1) {
            v1_1.printStackTrace();
        }

    label_27:
        return v0_1 - v5;
    }

    static WindowManager a(a arg0) {
        return arg0.e;
    }

    static boolean a(a arg0, boolean arg1) {
        arg0.d = arg1;
        return arg1;
    }

    protected View a(ViewGroup arg1) {
        return null;
    }

    public void a() {
        int v9;
        int v10;
        if(this.d) {
            return;
        }

        WindowManager$LayoutParams v0 = new WindowManager$LayoutParams();
        v0.width = -1;
        v0.height = -1;
        v0.format = 1;
        int v2 = a.a(this.c);
        int v3 = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        v3 = v3 > 0 ? this.getResources().getDimensionPixelSize(v3) : 0;
        if(Build$VERSION.SDK_INT >= 19) {
            WindowManager$LayoutParams v3_1 = this.c.getWindow().getAttributes();
            int v6 = 134217728;
            int v7 = 21;
            int v5 = (v3_1.flags & v6) != 0 || Build$VERSION.SDK_INT < v7 ? 0 : v2;
            v0.flags = v3_1.flags;
            if(v5 > 0) {
                this.setPadding(this.getPaddingLeft(), this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom() + v2);
            }
            else {
                if(Build$VERSION.SDK_INT < v7) {
                    if(v2 > 0 && (v3_1.flags & v6) != 0) {
                        goto label_51;
                    }

                    goto label_58;
                }
                else {
                label_51:
                    v3 = 1;
                    goto label_59;
                label_58:
                    v3 = 0;
                }

            label_59:
                if(v2 <= 0) {
                    goto label_68;
                }

                if(v3 == 0) {
                    goto label_68;
                }

                if(this.f == null) {
                    goto label_68;
                }

                ViewGroup$LayoutParams v3_2 = this.f.getLayoutParams();
                ((ViewGroup$MarginLayoutParams)v3).bottomMargin += v2;
            }

        label_68:
            v10 = v5;
            v9 = 0;
        }
        else {
            v9 = v3;
            v10 = 0;
        }

        new com.b.a.a.a$a(this.c.getWindow().getDecorView(), v9, v10, this, new com.b.a.a.a$a$a() {
            public void a(Bitmap arg2) {
                this.a.a(arg2);
            }
        }).execute(new Void[0]);
        this.e.addView(((View)this), ((ViewGroup$LayoutParams)v0));
        ObjectAnimator v0_1 = this.e();
        if(v0_1 != null) {
            this.d = true;
            v0_1.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator arg2) {
                    a.a(this.a, false);
                    this.a.requestFocus();
                }

                public void onAnimationEnd(Animator arg2) {
                    a.a(this.a, false);
                    this.a.requestFocus();
                }
            });
            v0_1.start();
        }

        this.c();
    }

    protected void a(Bitmap arg3) {
        this.a.setImageBitmap(arg3);
        if(!this.d) {
            ObjectAnimator.ofFloat(this.a, "alpha", new float[]{0f, 1f}).setDuration(this.getAnimationDuration()).start();
        }
    }

    public void b() {
        if(this.d) {
            return;
        }

        this.d();
        ObjectAnimator v0 = this.f();
        if(v0 == null) {
            this.e.removeView(((View)this));
        }
        else {
            this.d = true;
            ObjectAnimator.ofFloat(this.a, "alpha", new float[]{this.a.getAlpha(), 0f}).setDuration(this.getAnimationDuration()).start();
            v0.addListener(new AnimatorListenerAdapter() {
                private void a() {
                    try {
                        a.a(this.a).removeView(this.a);
                    }
                    catch(Throwable v1) {
                    }
                    catch(Exception v1_1) {
                        try {
                            v1_1.printStackTrace();
                        }
                        catch(Throwable v1) {
                            a.a(this.a, false);
                            throw v1;
                        }
                    }

                    a.a(this.a, false);
                }

                public void onAnimationCancel(Animator arg1) {
                    this.a();
                }

                public void onAnimationEnd(Animator arg1) {
                    this.a();
                }
            });
            v0.start();
        }
    }

    protected void c() {
    }

    protected void d() {
        if(this.n != null) {
            this.n.a(this);
        }
    }

    protected ObjectAnimator e() {
        return ObjectAnimator.ofFloat(this.b, "alpha", new float[]{0f, 1f}).setDuration(this.getAnimationDuration());
    }

    protected ObjectAnimator f() {
        return ObjectAnimator.ofFloat(this.b, "alpha", new float[]{this.b.getAlpha(), 0f}).setDuration(this.getAnimationDuration());
    }

    private void g() {
        if((this.getContext() instanceof Activity)) {
            this.c = this.getContext();
            this.e = this.c.getWindowManager();
            this.i = 10f;
            this.j = 0.4f;
            this.k = 300;
            this.setFocusable(true);
            this.setFocusableInTouchMode(true);
            this.b = new FrameLayout(this.getContext());
            this.addView(this.b, new FrameLayout$LayoutParams(-1, -1));
            this.a = new ImageView(this.c);
            this.a.setScaleType(ImageView$ScaleType.FIT_XY);
            FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(-1, -1);
            v0.gravity = 80;
            this.a.setLayoutParams(((ViewGroup$LayoutParams)v0));
            this.b.addView(this.a);
            this.f = this.a(this.b);
            if(this.f != null) {
                this.b.addView(this.f);
            }

            return;
        }

        throw new IllegalArgumentException("Context must be Activity");
    }

    public View getAnchorView() {
        return this.h;
    }

    public long getAnimationDuration() {
        return this.k;
    }

    public float getBlurRadius() {
        return this.i;
    }

    public View getContentView() {
        return this.f;
    }

    public c getOnDismissListener() {
        return this.n;
    }

    public float getScaleRatio() {
        return this.j;
    }

    public int getTintColor() {
        return this.g;
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        if(!this.d) {
            if(!this.m) {
            }
            else if(arg2 == 4) {
                this.b();
                return 1;
            }
            else {
                return super.onKeyUp(arg2, arg3);
            }
        }

        return super.onKeyUp(arg2, arg3);
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        if(!this.d) {
            if(!this.l) {
            }
            else {
                if(arg2.getAction() == 1) {
                    this.b();
                }

                return 1;
            }
        }

        return super.onTouchEvent(arg2);
    }

    public void setAnchorView(View arg1) {
        this.h = arg1;
    }

    public void setAnimationDuration(long arg1) {
        this.k = arg1;
    }

    public void setBlurRadius(float arg1) {
        this.i = arg1;
    }

    public void setContentView(View arg3) {
        if(arg3 != null) {
            if(this.f != null) {
                if(this.f.getParent() != null) {
                    this.f.getParent().removeView(this.f);
                }

                this.f = null;
            }

            this.f = arg3;
            this.b.addView(this.f);
            return;
        }

        throw new IllegalArgumentException("contentView can not be null");
    }

    public void setDismissOnClickBack(boolean arg1) {
        this.m = arg1;
    }

    public void setDismissOnTouchBackground(boolean arg1) {
        this.l = arg1;
    }

    public void setOnDismissListener(c arg1) {
        this.n = arg1;
    }

    public void setScaleRatio(float arg1) {
        this.j = arg1;
    }

    public void setTintColor(int arg1) {
        this.g = arg1;
    }
}

