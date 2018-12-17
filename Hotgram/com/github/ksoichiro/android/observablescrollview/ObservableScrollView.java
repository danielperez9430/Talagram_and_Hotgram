package com.github.ksoichiro.android.observablescrollview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View$BaseSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    class SavedState extends View$BaseSavedState {
        final class com.github.ksoichiro.android.observablescrollview.ObservableScrollView$SavedState$1 implements Parcelable$Creator {
            com.github.ksoichiro.android.observablescrollview.ObservableScrollView$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        int a;
        int b;

        static {
            SavedState.CREATOR = new com.github.ksoichiro.android.observablescrollview.ObservableScrollView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        private SavedState(Parcel arg2) {
            super(arg2);
            this.a = arg2.readInt();
            this.b = arg2.readInt();
        }

        SavedState(Parcel arg1, com.github.ksoichiro.android.observablescrollview.ObservableScrollView$1 arg2) {
            this(arg1);
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeInt(this.a);
            arg1.writeInt(this.b);
        }
    }

    private int a;
    private int b;
    private a c;
    private b d;
    private boolean e;
    private boolean f;
    private boolean g;
    private MotionEvent h;
    private ViewGroup i;

    public ObservableScrollView(Context arg1) {
        super(arg1);
    }

    public ObservableScrollView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public ObservableScrollView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    public int getCurrentScrollY() {
        return this.b;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg2) {
        if(this.c != null) {
            if(arg2.getActionMasked() != 0) {
            }
            else {
                this.f = true;
                this.e = true;
                this.c.a();
            }
        }

        return super.onInterceptTouchEvent(arg2);
    }

    public void onRestoreInstanceState(Parcelable arg2) {
        this.a = ((SavedState)arg2).a;
        this.b = ((SavedState)arg2).b;
        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.a = this.a;
        v1.b = this.b;
        return ((Parcelable)v1);
    }

    protected void onScrollChanged(int arg1, int arg2, int arg3, int arg4) {
        b v1;
        super.onScrollChanged(arg1, arg2, arg3, arg4);
        if(this.c != null) {
            this.b = arg2;
            this.c.a(arg2, this.e, this.f);
            if(this.e) {
                this.e = false;
            }

            if(this.a < arg2) {
                v1 = b.b;
                goto label_15;
            }
            else if(arg2 < this.a) {
                v1 = b.c;
            label_15:
                this.d = v1;
            }

            this.a = arg2;
        }
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        ViewParent v2;
        if(this.c != null) {
            switch(arg8.getActionMasked()) {
                case 2: {
                    goto label_6;
                }
                case 1: 
                case 3: {
                    goto label_58;
                }
            }

            goto label_63;
        label_6:
            if(this.h == null) {
                this.h = arg8;
            }

            float v0 = arg8.getY() - this.h.getY();
            this.h = MotionEvent.obtainNoHistory(arg8);
            if((((float)this.getCurrentScrollY())) - v0 > 0f) {
                goto label_63;
            }

            if(this.g) {
                return 0;
            }

            if(this.i == null) {
                v2 = this.getParent();
            }
            else {
                ViewGroup v2_1 = this.i;
            }

            float v3 = 0f;
            float v4 = 0f;
            ObservableScrollView v0_1 = this;
            while(v0_1 != null) {
                if((((ViewParent)v0_1)) == v2) {
                    break;
                }

                v3 += ((float)(((View)v0_1).getLeft() - ((View)v0_1).getScrollX()));
                v4 += ((float)(((View)v0_1).getTop() - ((View)v0_1).getScrollY()));
                ViewParent v0_2 = ((View)v0_2).getParent();
            }

            MotionEvent v0_3 = MotionEvent.obtainNoHistory(arg8);
            v0_3.offsetLocation(v3, v4);
            if(((ViewGroup)v2).onInterceptTouchEvent(v0_3)) {
                this.g = true;
                v0_3.setAction(0);
                this.post(new Runnable(((ViewGroup)v2), v0_3) {
                    public void run() {
                        this.a.dispatchTouchEvent(this.b);
                    }
                });
                return 0;
            }

            return super.onTouchEvent(arg8);
        label_58:
            this.g = false;
            this.f = false;
            this.c.a(this.d);
        }

    label_63:
        return super.onTouchEvent(arg8);
    }

    public void setScrollViewCallbacks(a arg1) {
        this.c = arg1;
    }

    public void setTouchInterceptionViewGroup(ViewGroup arg1) {
        this.i = arg1;
    }
}

