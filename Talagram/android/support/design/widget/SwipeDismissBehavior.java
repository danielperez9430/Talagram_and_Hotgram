package android.support.design.widget;

import android.support.v4.view.t;
import android.support.v4.widget.t$a;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class SwipeDismissBehavior extends b {
    class android.support.design.widget.SwipeDismissBehavior$1 extends a {
        private int b;
        private int c;

        android.support.design.widget.SwipeDismissBehavior$1(SwipeDismissBehavior arg1) {
            this.a = arg1;
            super();
            this.c = -1;
        }

        private boolean a(View arg6, float arg7) {
            // Method was not decompiled
        }

        public int a(View arg1, int arg2, int arg3) {
            return arg1.getTop();
        }

        public void a(int arg2) {
            if(this.a.b != null) {
                this.a.b.a(arg2);
            }
        }

        public void a(View arg3, float arg4, float arg5) {
            boolean v5_1;
            int v4;
            this.c = -1;
            int v5 = arg3.getWidth();
            if(this.a(arg3, arg4)) {
                v4 = arg3.getLeft() < this.b ? this.b - v5 : this.b + v5;
                v5_1 = true;
            }
            else {
                v4 = this.b;
                v5_1 = false;
            }

            if(this.a.a.a(v4, arg3.getTop())) {
                t.a(arg3, new android.support.design.widget.SwipeDismissBehavior$b(this.a, arg3, v5_1));
            }
            else if((v5_1) && this.a.b != null) {
                this.a.b.a(arg3);
            }
        }

        public void a(View arg3, int arg4, int arg5, int arg6, int arg7) {
            float v5 = (((float)this.b)) + (((float)arg3.getWidth())) * this.a.e;
            float v6 = (((float)this.b)) + (((float)arg3.getWidth())) * this.a.f;
            float v4 = ((float)arg4);
            float v0 = 1f;
            if(Float.compare(v4, v5) <= 0) {
                arg3.setAlpha(v0);
            }
            else if(Float.compare(v4, v6) >= 0) {
                arg3.setAlpha(0f);
            }
            else {
                arg3.setAlpha(SwipeDismissBehavior.a(0f, v0 - SwipeDismissBehavior.b(v5, v6, v4), v0));
            }
        }

        public boolean a(View arg2, int arg3) {
            boolean v2 = this.c != -1 || !this.a.a(arg2) ? false : true;
            return v2;
        }

        public int b(View arg1) {
            return arg1.getWidth();
        }

        public int b(View arg3, int arg4, int arg5) {
            int v3;
            arg5 = t.f(arg3) == 1 ? 1 : 0;
            if(this.a.c == 0) {
                if(arg5 == 0) {
                    goto label_15;
                }

                goto label_10;
            }
            else if(this.a.c != 1) {
                arg5 = this.b - arg3.getWidth();
            label_16:
                v3 = arg3.getWidth() + this.b;
            }
            else if(arg5 != 0) {
            label_15:
                arg5 = this.b;
                goto label_16;
            }
            else {
            label_10:
                arg5 = this.b - arg3.getWidth();
                v3 = this.b;
            }

            return SwipeDismissBehavior.a(arg5, arg4, v3);
        }

        public void b(View arg1, int arg2) {
            this.c = arg2;
            this.b = arg1.getLeft();
            ViewParent v1 = arg1.getParent();
            if(v1 != null) {
                v1.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    public interface android.support.design.widget.SwipeDismissBehavior$a {
        void a(int arg1);

        void a(View arg1);
    }

    class android.support.design.widget.SwipeDismissBehavior$b implements Runnable {
        private final View b;
        private final boolean c;

        android.support.design.widget.SwipeDismissBehavior$b(SwipeDismissBehavior arg1, View arg2, boolean arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            if(this.a.a != null && (this.a.a.a(true))) {
                t.a(this.b, ((Runnable)this));
            }
            else if((this.c) && this.a.b != null) {
                this.a.b.a(this.b);
            }
        }
    }

    android.support.v4.widget.t a;
    android.support.design.widget.SwipeDismissBehavior$a b;
    int c;
    float d;
    float e;
    float f;
    private boolean g;
    private float h;
    private boolean i;
    private final a j;

    public SwipeDismissBehavior() {
        super();
        this.h = 0f;
        this.c = 2;
        this.d = 0.5f;
        this.e = 0f;
        this.f = 0.5f;
        this.j = new android.support.design.widget.SwipeDismissBehavior$1(this);
    }

    public void a(android.support.design.widget.SwipeDismissBehavior$a arg1) {
        this.b = arg1;
    }

    public void a(float arg3) {
        this.e = SwipeDismissBehavior.a(0f, arg3, 1f);
    }

    public void a(int arg1) {
        this.c = arg1;
    }

    static float a(float arg0, float arg1, float arg2) {
        return Math.min(Math.max(arg0, arg1), arg2);
    }

    static int a(int arg0, int arg1, int arg2) {
        return Math.min(Math.max(arg0, arg1), arg2);
    }

    private void a(ViewGroup arg3) {
        if(this.a == null) {
            android.support.v4.widget.t v3 = this.i ? android.support.v4.widget.t.a(arg3, this.h, this.j) : android.support.v4.widget.t.a(arg3, this.j);
            this.a = v3;
        }
    }

    public boolean a(CoordinatorLayout arg1, View arg2, MotionEvent arg3) {
        if(this.a != null) {
            this.a.b(arg3);
            return 1;
        }

        return 0;
    }

    public boolean a(View arg1) {
        return 1;
    }

    public void b(float arg3) {
        this.f = SwipeDismissBehavior.a(0f, arg3, 1f);
    }

    public boolean b(CoordinatorLayout arg5, View arg6, MotionEvent arg7) {
        boolean v0 = this.g;
        int v1 = arg7.getActionMasked();
        if(v1 != 3) {
            switch(v1) {
                case 0: {
                    goto label_7;
                }
                case 1: {
                    goto label_15;
                }
            }

            goto label_16;
        label_7:
            this.g = arg5.a(arg6, ((int)arg7.getX()), ((int)arg7.getY()));
            v0 = this.g;
        }
        else {
        label_15:
            this.g = false;
        }

    label_16:
        if(v0) {
            this.a(((ViewGroup)arg5));
            return this.a.a(arg7);
        }

        return 0;
    }

    static float b(float arg0, float arg1, float arg2) {
        return (arg2 - arg0) / (arg1 - arg0);
    }
}

