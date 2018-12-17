package org.telegram.customization.util.view.Poll;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class a extends BaseAdapter implements AdapterView$OnItemClickListener, AdapterView$OnItemSelectedListener {
    class org.telegram.customization.util.view.Poll.a$a extends Animation {
        private final float b;
        private final float c;
        private ProgressBar d;

        public org.telegram.customization.util.view.Poll.a$a(a arg1, ProgressBar arg2, float arg3, float arg4) {
            this.a = arg1;
            super();
            this.b = arg3;
            this.c = arg4 - arg3;
            this.d = arg2;
        }

        protected void applyTransformation(float arg3, Transformation arg4) {
            this.d.setProgress(((int)(this.b + this.c * arg3)));
        }

        public boolean willChangeBounds() {
            return 0;
        }
    }

    volatile List a;
    volatile ArrayList b;
    int c;
    int d;
    int e;
    int f;
    int g;
    private Context h;

    public a(Context arg2, List arg3, int arg4, int arg5, int arg6) {
        super();
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.g = 0;
        this.h = arg2;
        this.a = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
        this.c = -1;
    }

    public int a() {
        return this.f;
    }

    public void a(int arg12, View arg13) {
        __monitor_enter(this);
        try {
            this.d().get(arg12);
            if(this.c < 0) {
                this.c = arg12;
                this.a.get(arg12).a(1);
                ScaleAnimation v12_1 = new ScaleAnimation(1f, 1f, 1f, 1f, 1, 0.5f, 1, 0.5f);
                ((Animation)v12_1).setInterpolator(new LinearInterpolator());
                ((Animation)v12_1).setRepeatMode(2);
                ((Animation)v12_1).setRepeatCount(1);
                ((Animation)v12_1).setDuration(200);
                ((Animation)v12_1).setAnimationListener(new Animation$AnimationListener() {
                    public void onAnimationEnd(Animation arg4) {
                        this.a.notifyDataSetChanged();
                        this.a.b = new ArrayList();
                        int v0;
                        for(v0 = 0; v0 < this.a.getCount(); ++v0) {
                            this.a.b.add(Boolean.valueOf(false));
                        }
                    }

                    public void onAnimationRepeat(Animation arg1) {
                    }

                    public void onAnimationStart(Animation arg1) {
                    }
                });
                arg13.findViewById(2131296839).startAnimation(((Animation)v12_1));
            }
            else {
                int v0 = -1;
                if(this.c == arg12) {
                    this.a.get(arg12).a(v0);
                    this.c = v0;
                }
                else {
                    this.a.get(this.c).a(v0);
                    this.a.get(arg12).a(1);
                    this.c = arg12;
                }

                this.notifyDataSetChanged();
            }
        }
        catch(Throwable v12) {
            __monitor_exit(this);
            throw v12;
        }
        catch(Exception ) {
            __monitor_exit(this);
            return;
        }

        __monitor_exit(this);
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public List d() {
        return this.a;
    }

    public int getCount() {
        int v0_1;
        __monitor_enter(this);
        try {
            if(this.d() == null) {
                goto label_7;
            }

            v0_1 = this.d().size();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    label_7:
        __monitor_exit(this);
        return 0;
    }

    public Object getItem(int arg1) {
        return Integer.valueOf(arg1);
    }

    public long getItemId(int arg3) {
        return ((long)arg3);
    }

    public View getView(int arg12, View arg13, ViewGroup arg14) {
        ClipDrawable v1_1;
        int v0;
        Object v14;
        if(arg13 == null) {
            arg13 = View.inflate(this.h, 2131493056, null);
        }

        try {
            v14 = this.d().get(arg12);
            v0 = 2131296560;
        }
        catch(Exception v12) {
            v12.printStackTrace();
            return arg13;
        }

        View v0_1 = arg13.findViewById(v0);
        arg13.findViewById(2131296839);
        View v6 = arg13.findViewById(2131296813);
        View v1 = arg13.findViewById(2131296575);
        View v2 = arg13.findViewById(2131296968);
        View v3 = arg13.findViewById(2131296781);
        ((TextView)v2).setText(((b)v14).b());
        ((TextView)v3).setText(String.format(this.h.getResources().getString(2131626988), Integer.valueOf(((b)v14).c())));
        org.telegram.customization.util.b.a(((ImageView)v0_1), ((b)v14).a());
        try {
            Drawable v0_2 = this.h.getResources().getDrawable(2131231499);
            ((LayerDrawable)v0_2).setDrawableByLayerId(16908288, new ColorDrawable(this.a()));
            int v5 = 3;
            int v7 = 8;
            int v9 = 16908301;
            if(this.c == arg12) {
                v1_1 = new ClipDrawable(new ColorDrawable(this.c()), v5, 1);
            }
            else {
                v1.setVisibility(v7);
                v1_1 = new ClipDrawable(new ColorDrawable(this.b()), v5, 1);
            }

            ((LayerDrawable)v0_2).setDrawableByLayerId(v9, ((Drawable)v1_1));
            ((ProgressBar)v6).setProgressDrawable(v0_2);
            if(this.c < 0) {
                ((ProgressBar)v6).setProgress(100);
                ((TextView)v3).setVisibility(v7);
                return arg13;
            }

            ((TextView)v3).setVisibility(0);
        }
        catch(Exception v12) {
            goto label_103;
        }

        try {
            if(!this.b.get(arg12).booleanValue()) {
                ((ProgressBar)v6).post(new Runnable(arg12, ((ProgressBar)v6), ((b)v14)) {
                    public void run() {
                        this.d.b.set(this.a, Boolean.valueOf(true));
                        org.telegram.customization.util.view.Poll.a$a v0 = new org.telegram.customization.util.view.Poll.a$a(this.d, this.b, 0f, ((float)this.c.c()));
                        v0.setDuration(500);
                        v0.setInterpolator(new LinearInterpolator());
                        this.b.startAnimation(((Animation)v0));
                    }
                });
                return arg13;
            }

            if(((ProgressBar)v6).getProgress() != ((b)v14).c()) {
                ((ProgressBar)v6).post(new Runnable(arg12, v6, ((ProgressBar)v6).getProgress(), v14) {
                    public void run() {
                        this.e.b.set(this.a, Boolean.valueOf(true));
                        org.telegram.customization.util.view.Poll.a$a v0 = new org.telegram.customization.util.view.Poll.a$a(this.e, this.b, ((float)this.c), ((float)this.d.c()));
                        v0.setDuration(500);
                        v0.setInterpolator(new LinearInterpolator());
                        this.b.startAnimation(((Animation)v0));
                    }
                });
                return arg13;
            }

            ((ProgressBar)v6).setProgress(((b)v14).c());
        }
        catch(Exception v12) {
            try {
                ((ProgressBar)v6).setProgress(((b)v14).c());
                v12.printStackTrace();
            }
            catch(Exception v12) {
            label_103:
                v12.printStackTrace();
            }
        }

        return arg13;
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        __monitor_enter(this);
        try {
            this.onItemSelected(arg1, arg2, arg3, arg4);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
        __monitor_enter(this);
        try {
            this.a(arg3, arg2);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public void onNothingSelected(AdapterView arg1) {
    }
}

