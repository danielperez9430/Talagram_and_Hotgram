package org.telegram.customization.util.view.slideshow;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.q;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.R$styleable;

public class SlideshowView extends FrameLayout {
    public abstract class a extends q implements b {
        public a() {
            super();
        }
    }

    View a;
    AutoScrollViewPager b;
    IconPageIndicator c;

    public SlideshowView(Context arg1) {
        super(arg1);
        this.b();
        this.a();
    }

    public SlideshowView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.b();
        this.a(arg1, arg2);
        this.a();
    }

    public SlideshowView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.b();
        this.a(arg1, arg2);
        this.a();
    }

    @TargetApi(value=21) public SlideshowView(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        super(arg1, arg2, arg3, arg4);
        this.b();
        this.a(arg1, arg2);
        this.a();
    }

    void a() {
    }

    void a(Context arg6, AttributeSet arg7) {
        TypedArray v6 = arg6.obtainStyledAttributes(arg7, styleable.SlideshowView);
        int v7 = v6.getIndexCount();
        int v0;
        for(v0 = 0; v0 < v7; ++v0) {
            int v1 = v6.getIndex(v0);
            switch(v1) {
                case 0: {
                    if(!v6.getBoolean(v1, true)) {
                        goto label_30;
                    }

                    this.b.a();
                    break;
                }
                case 1: {
                    this.b.setInterval(((long)v6.getInteger(v1, 2000)));
                    break;
                }
                case 2: {
                    this.b.setDirection(v6.getBoolean(v1, true) ^ 1);
                    break;
                }
                case 3: {
                    this.b.setAutoScrollDurationFactor(((double)v6.getFloat(v1, 5f)));
                    break;
                }
                default: {
                    break;
                }
            }

        label_30:
        }

        v6.recycle();
    }

    private void b() {
        this.a = SlideshowView.inflate(this.getContext(), 2131493070, null);
        this.b = this.a.findViewById(2131296318);
        this.c = this.a.findViewById(2131296568);
        this.addView(this.a);
    }

    public void setAdapter(a arg2) {
        this.b.setAdapter(((q)arg2));
        this.c.setViewPager(this.b);
    }
}

