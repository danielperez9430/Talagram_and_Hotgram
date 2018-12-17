package android.support.f;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.support.v4.view.t;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.Method;
import java.util.ArrayList;

class aa implements ac {
    class a extends ViewGroup {
        static Method a;
        ViewGroup b;
        View c;
        ArrayList d;
        aa e;

        static {
            try {
                a.a = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", Integer.TYPE, Integer.TYPE, Rect.class);
                return;
            }
            catch(NoSuchMethodException ) {
                return;
            }
        }

        a(Context arg1, ViewGroup arg2, View arg3, aa arg4) {
            super(arg1);
            this.d = null;
            this.b = arg2;
            this.c = arg3;
            this.setRight(arg2.getWidth());
            this.setBottom(arg2.getHeight());
            arg2.addView(((View)this));
            this.e = arg4;
        }

        public void a(Drawable arg2) {
            if(this.d == null) {
                this.d = new ArrayList();
            }

            if(!this.d.contains(arg2)) {
                this.d.add(arg2);
                this.invalidate(arg2.getBounds());
                arg2.setCallback(((Drawable$Callback)this));
            }
        }

        private void a(int[] arg6) {
            int[] v1 = new int[2];
            int[] v0 = new int[2];
            this.b.getLocationOnScreen(v1);
            this.c.getLocationOnScreen(v0);
            arg6[0] = v0[0] - v1[0];
            arg6[1] = v0[1] - v1[1];
        }

        public void a(View arg7) {
            if((arg7.getParent() instanceof ViewGroup)) {
                ViewParent v0 = arg7.getParent();
                if(v0 != this.b && ((ViewGroup)v0).getParent() != null && (t.D(((View)v0)))) {
                    int[] v3 = new int[2];
                    int[] v2 = new int[2];
                    ((ViewGroup)v0).getLocationOnScreen(v3);
                    this.b.getLocationOnScreen(v2);
                    t.f(arg7, v3[0] - v2[0]);
                    t.e(arg7, v3[1] - v2[1]);
                }

                ((ViewGroup)v0).removeView(arg7);
                if(arg7.getParent() == null) {
                    goto label_30;
                }

                ((ViewGroup)v0).removeView(arg7);
            }

        label_30:
            super.addView(arg7, this.getChildCount() - 1);
        }

        boolean a() {
            boolean v0;
            if(this.getChildCount() == 0) {
                if(this.d != null && this.d.size() != 0) {
                    goto label_9;
                }

                v0 = true;
            }
            else {
            label_9:
                v0 = false;
            }

            return v0;
        }

        public void b(Drawable arg2) {
            if(this.d != null) {
                this.d.remove(arg2);
                this.invalidate(arg2.getBounds());
                arg2.setCallback(null);
            }
        }

        public void b(View arg1) {
            super.removeView(arg1);
            if(this.a()) {
                this.b.removeView(((View)this));
            }
        }

        protected void dispatchDraw(Canvas arg6) {
            int[] v1 = new int[2];
            int[] v0 = new int[2];
            this.b.getLocationOnScreen(v1);
            this.c.getLocationOnScreen(v0);
            int v2 = 0;
            arg6.translate(((float)(v0[0] - v1[0])), ((float)(v0[1] - v1[1])));
            arg6.clipRect(new Rect(0, 0, this.c.getWidth(), this.c.getHeight()));
            super.dispatchDraw(arg6);
            int v0_1 = this.d == null ? 0 : this.d.size();
            while(v2 < v0_1) {
                this.d.get(v2).draw(arg6);
                ++v2;
            }
        }

        public boolean dispatchTouchEvent(MotionEvent arg1) {
            return 0;
        }

        public ViewParent invalidateChildInParent(int[] arg5, Rect arg6) {
            if(this.b != null) {
                arg6.offset(arg5[0], arg5[1]);
                if((this.b instanceof ViewGroup)) {
                    arg5[0] = 0;
                    arg5[1] = 0;
                    int[] v1 = new int[2];
                    this.a(v1);
                    arg6.offset(v1[0], v1[1]);
                    return super.invalidateChildInParent(arg5, arg6);
                }
                else {
                    this.invalidate(arg6);
                }
            }

            return null;
        }

        public void invalidateDrawable(Drawable arg1) {
            this.invalidate(arg1.getBounds());
        }

        protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        }

        protected boolean verifyDrawable(Drawable arg2) {
            boolean v2;
            if(!super.verifyDrawable(arg2)) {
                if(this.d != null && (this.d.contains(arg2))) {
                    goto label_10;
                }

                v2 = false;
            }
            else {
            label_10:
                v2 = true;
            }

            return v2;
        }
    }

    protected a a;

    aa(Context arg2, ViewGroup arg3, View arg4) {
        super();
        this.a = new a(arg2, arg3, arg4, this);
    }

    public void a(Drawable arg2) {
        this.a.a(arg2);
    }

    public void b(Drawable arg2) {
        this.a.b(arg2);
    }

    static ViewGroup c(View arg2) {
        while(arg2 != null) {
            if(arg2.getId() == 16908290 && ((arg2 instanceof ViewGroup))) {
                return arg2;
            }

            if(!(arg2.getParent() instanceof ViewGroup)) {
                continue;
            }

            ViewParent v2 = ((View)v2).getParent();
        }

        return null;
    }

    static aa d(View arg5) {
        ViewGroup v0 = aa.c(arg5);
        if(v0 != null) {
            int v1 = v0.getChildCount();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                View v3 = v0.getChildAt(v2);
                if((v3 instanceof a)) {
                    return ((a)v3).e;
                }
            }

            return new u(v0.getContext(), v0, arg5);
        }

        return null;
    }
}

