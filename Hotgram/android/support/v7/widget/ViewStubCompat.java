package android.support.v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;

public final class ViewStubCompat extends View {
    public interface a {
        void a(ViewStubCompat arg1, View arg2);
    }

    private int a;
    private int b;
    private WeakReference c;
    private LayoutInflater d;
    private a e;

    public ViewStubCompat(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public ViewStubCompat(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        this.a = 0;
        TypedArray v3 = arg3.obtainStyledAttributes(arg4, j.ViewStubCompat, arg5, 0);
        this.b = v3.getResourceId(j.ViewStubCompat_android_inflatedId, -1);
        this.a = v3.getResourceId(j.ViewStubCompat_android_layout, 0);
        this.setId(v3.getResourceId(j.ViewStubCompat_android_id, -1));
        v3.recycle();
        this.setVisibility(8);
        this.setWillNotDraw(true);
    }

    public View a() {
        ViewParent v0 = this.getParent();
        if(v0 != null && ((v0 instanceof ViewGroup))) {
            if(this.a != 0) {
                LayoutInflater v1 = this.d != null ? this.d : LayoutInflater.from(this.getContext());
                View v1_1 = v1.inflate(this.a, ((ViewGroup)v0), false);
                if(this.b != -1) {
                    v1_1.setId(this.b);
                }

                int v2 = ((ViewGroup)v0).indexOfChild(((View)this));
                ((ViewGroup)v0).removeViewInLayout(((View)this));
                ViewGroup$LayoutParams v3 = this.getLayoutParams();
                if(v3 != null) {
                    ((ViewGroup)v0).addView(v1_1, v2, v3);
                }
                else {
                    ((ViewGroup)v0).addView(v1_1, v2);
                }

                this.c = new WeakReference(v1_1);
                if(this.e != null) {
                    this.e.a(this, v1_1);
                }

                return v1_1;
            }
            else {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
        }

        throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }

    protected void dispatchDraw(Canvas arg1) {
    }

    @SuppressLint(value={"MissingSuperCall"}) public void draw(Canvas arg1) {
    }

    public int getInflatedId() {
        return this.b;
    }

    public LayoutInflater getLayoutInflater() {
        return this.d;
    }

    public int getLayoutResource() {
        return this.a;
    }

    protected void onMeasure(int arg1, int arg2) {
        this.setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int arg1) {
        this.b = arg1;
    }

    public void setLayoutInflater(LayoutInflater arg1) {
        this.d = arg1;
    }

    public void setLayoutResource(int arg1) {
        this.a = arg1;
    }

    public void setOnInflateListener(a arg1) {
        this.e = arg1;
    }

    public void setVisibility(int arg2) {
        if(this.c != null) {
            Object v0 = this.c.get();
            if(v0 != null) {
                ((View)v0).setVisibility(arg2);
            }
            else {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
        }
        else {
            super.setVisibility(arg2);
            if(arg2 != 0 && arg2 != 4) {
                return;
            }

            this.a();
        }
    }
}

