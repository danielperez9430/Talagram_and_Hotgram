package com.github.vivchar.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff$Mode;
import android.support.v4.view.ViewPager$f;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerIndicator extends ap {
    class com.github.vivchar.viewpagerindicator.ViewPagerIndicator$1 {
    }

    class a implements f {
        a(ViewPagerIndicator arg1, com.github.vivchar.viewpagerindicator.ViewPagerIndicator$1 arg2) {
            this(arg1);
        }

        private a(ViewPagerIndicator arg1) {
            this.a = arg1;
            super();
        }

        public void onPageScrollStateChanged(int arg2) {
            if(ViewPagerIndicator.a(this.a) != null) {
                ViewPagerIndicator.a(this.a).onPageScrollStateChanged(arg2);
            }
        }

        public void onPageScrolled(int arg2, float arg3, int arg4) {
            if(ViewPagerIndicator.a(this.a) != null) {
                ViewPagerIndicator.a(this.a).onPageScrolled(arg2, arg3, arg4);
            }
        }

        public void onPageSelected(int arg2) {
            ViewPagerIndicator.a(this.a, arg2);
            if(ViewPagerIndicator.a(this.a) != null) {
                ViewPagerIndicator.a(this.a).onPageSelected(arg2);
            }
        }
    }

    private static final int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private int h;
    private int i;
    private final List j;
    private f k;

    static {
        ViewPagerIndicator.a = com.github.vivchar.viewpagerindicator.a$a.white_circle;
    }

    public ViewPagerIndicator(Context arg2) {
        this(arg2, null);
    }

    public ViewPagerIndicator(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ViewPagerIndicator(Context arg5, AttributeSet arg6, int arg7) {
        super(arg5, arg6, arg7);
        arg7 = -1;
        this.b = arg7;
        this.c = arg7;
        float v0 = 1f;
        this.f = v0;
        int v1 = 10;
        this.g = v1;
        this.h = v1;
        this.i = v1;
        this.j = new ArrayList();
        this.setOrientation(0);
        TypedArray v5 = arg5.getTheme().obtainStyledAttributes(arg6, b.ViewPagerIndicator, 0, 0);
        try {
            this.g = v5.getDimensionPixelSize(b.ViewPagerIndicator_itemSize, v1);
            this.f = v5.getFloat(b.ViewPagerIndicator_itemScale, v0);
            this.c = v5.getColor(b.ViewPagerIndicator_itemSelectedTint, arg7);
            this.b = v5.getColor(b.ViewPagerIndicator_itemTint, arg7);
            this.h = v5.getDimensionPixelSize(b.ViewPagerIndicator_delimiterSize, v1);
            this.i = v5.getResourceId(b.ViewPagerIndicator_itemIcon, ViewPagerIndicator.a);
        }
        catch(Throwable v6) {
            v5.recycle();
            throw v6;
        }

        v5.recycle();
        if(this.isInEditMode()) {
            this.a();
        }
    }

    private void a() {
        int v1;
        for(v1 = 0; v1 < 5; ++v1) {
            FrameLayout v2 = this.a(v1);
            this.addView(((View)v2));
            if(v1 == 1) {
                View v2_1 = v2.getChildAt(0);
                ViewGroup$LayoutParams v3 = v2_1.getLayoutParams();
                v3.height = ((int)((((float)v3.height)) * this.f));
                v3.width = ((int)((((float)v3.width)) * this.f));
                v2_1.setLayoutParams(v3);
            }
        }
    }

    static f a(ViewPagerIndicator arg0) {
        return arg0.k;
    }

    private FrameLayout a(int arg6) {
        FrameLayout v0 = new FrameLayout(this.getContext());
        ImageView v1 = this.b();
        v0.addView(((View)v1));
        this.j.add(v1);
        android.support.v7.widget.ap$a v1_1 = new android.support.v7.widget.ap$a(((int)((((float)this.g)) * this.f)), ((int)((((float)this.g)) * this.f)));
        if(arg6 > 0) {
            v1_1.setMargins(this.h, 0, 0, 0);
        }

        v0.setLayoutParams(((ViewGroup$LayoutParams)v1_1));
        return v0;
    }

    static void a(ViewPagerIndicator arg0, int arg1) {
        arg0.setSelectedIndex(arg1);
    }

    private ImageView b() {
        ImageView v0 = new ImageView(this.getContext());
        FrameLayout$LayoutParams v1 = new FrameLayout$LayoutParams(this.g, this.g);
        v1.gravity = 17;
        v0.setLayoutParams(((ViewGroup$LayoutParams)v1));
        v0.setImageResource(this.i);
        v0.setScaleType(ImageView$ScaleType.FIT_CENTER);
        v0.setColorFilter(this.b, PorterDuff$Mode.SRC_IN);
        return v0;
    }

    private void setPageCount(int arg3) {
        this.d = arg3;
        int v0 = 0;
        this.e = 0;
        this.removeAllViews();
        this.j.clear();
        while(v0 < arg3) {
            this.addView(this.a(v0));
            ++v0;
        }

        this.setSelectedIndex(this.e);
    }

    private void setSelectedIndex(int arg6) {
        if(arg6 >= 0) {
            if(arg6 > this.d - 1) {
            }
            else {
                Object v0 = this.j.get(this.e);
                ((ImageView)v0).animate().scaleX(1f).scaleY(1f).setDuration(300).start();
                ((ImageView)v0).setColorFilter(this.b, PorterDuff$Mode.SRC_IN);
                v0 = this.j.get(arg6);
                ((ImageView)v0).animate().scaleX(this.f).scaleY(this.f).setDuration(300).start();
                ((ImageView)v0).setColorFilter(this.c, PorterDuff$Mode.SRC_IN);
                this.e = arg6;
            }
        }
    }

    public void setupWithViewPager(ViewPager arg3) {
        this.setPageCount(arg3.getAdapter().getCount());
        arg3.addOnPageChangeListener(new a(this, null));
    }
}

