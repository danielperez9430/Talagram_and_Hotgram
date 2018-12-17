package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class i extends t {
    class a implements Runnable {
        private final CoordinatorLayout b;
        private final View c;

        a(i arg1, CoordinatorLayout arg2, View arg3) {
            this.a = arg1;
            super();
            this.b = arg2;
            this.c = arg3;
        }

        public void run() {
            if(this.c != null && this.a.a != null) {
                if(this.a.a.computeScrollOffset()) {
                    this.a.a_(this.b, this.c, this.a.a.getCurrY());
                    android.support.v4.view.t.a(this.c, ((Runnable)this));
                }
                else {
                    this.a.a(this.b, this.c);
                }
            }
        }
    }

    OverScroller a;
    private Runnable b;
    private boolean c;
    private int d;
    private int e;
    private int f;
    private VelocityTracker g;

    public i() {
        super();
        this.d = -1;
        this.f = -1;
    }

    public i(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.d = -1;
        this.f = -1;
    }

    int a() {
        return this.b();
    }

    int a(CoordinatorLayout arg1, View arg2, int arg3, int arg4, int arg5) {
        int v1 = this.b();
        if(arg4 == 0 || v1 < arg4 || v1 > arg5) {
        label_9:
            v1 = 0;
        }
        else {
            int v2 = android.support.v4.b.a.a(arg3, arg4, arg5);
            if(v1 != v2) {
                this.a(v2);
                v1 -= v2;
            }
            else {
                goto label_9;
            }
        }

        return v1;
    }

    int a(View arg1) {
        return arg1.getHeight();
    }

    void a(CoordinatorLayout arg1, View arg2) {
    }

    final boolean a(CoordinatorLayout arg14, View arg15, int arg16, int arg17, float arg18) {
        i v0 = this;
        if(v0.b != null) {
            arg15.removeCallbacks(v0.b);
            v0.b = null;
        }

        if(v0.a == null) {
            v0.a = new OverScroller(arg15.getContext());
        }

        v0.a.fling(0, this.b(), 0, Math.round(arg18), 0, 0, arg16, arg17);
        if(v0.a.computeScrollOffset()) {
            v0.b = new a(this, arg14, arg15);
            android.support.v4.view.t.a(arg15, v0.b);
            return 1;
        }

        this.a(arg14, arg15);
        return 0;
    }

    public boolean a(CoordinatorLayout arg12, View arg13, MotionEvent arg14) {
        int v0;
        if(this.f < 0) {
            this.f = ViewConfiguration.get(arg12.getContext()).getScaledTouchSlop();
        }

        int v2 = -1;
        switch(arg14.getActionMasked()) {
            case 0: {
                v0 = ((int)arg14.getX());
                v2 = ((int)arg14.getY());
                if((arg12.a(arg13, v0, v2)) && (this.c(arg13))) {
                    this.e = v2;
                    this.d = arg14.getPointerId(0);
                    this.d();
                    goto label_83;
                }

                return 0;
            }
            case 1: {
                if(this.g == null) {
                    goto label_60;
                }

                this.g.addMovement(arg14);
                this.g.computeCurrentVelocity(1000);
                this.a(arg12, arg13, -this.a(arg13), 0, this.g.getYVelocity(this.d));
                goto label_60;
            }
            case 2: {
                v0 = arg14.findPointerIndex(this.d);
                if(v0 == v2) {
                    return 0;
                }

                v0 = ((int)arg14.getY(v0));
                v2 = this.e - v0;
                if(!this.c && Math.abs(v2) > this.f) {
                    this.c = true;
                    if(v2 > 0) {
                        v2 -= this.f;
                    }
                    else {
                        v2 += this.f;
                    }
                }

                int v6 = v2;
                if(!this.c) {
                    goto label_83;
                }

                this.e = v0;
                this.b(arg12, arg13, v6, this.b(arg13), 0);
                break;
            }
            case 3: {
            label_60:
                this.c = false;
                this.d = v2;
                if(this.g == null) {
                    goto label_83;
                }

                this.g.recycle();
                this.g = null;
                break;
            }
            default: {
                break;
            }
        }

    label_83:
        if(this.g != null) {
            this.g.addMovement(arg14);
        }

        return 1;
    }

    int a_(CoordinatorLayout arg7, View arg8, int arg9) {
        return this.a(arg7, arg8, arg9, -2147483648, 2147483647);
    }

    final int b(CoordinatorLayout arg8, View arg9, int arg10, int arg11, int arg12) {
        return this.a(arg8, arg9, this.a() - arg10, arg11, arg12);
    }

    int b(View arg1) {
        return -arg1.getHeight();
    }

    public boolean b(CoordinatorLayout arg5, View arg6, MotionEvent arg7) {
        if(this.f < 0) {
            this.f = ViewConfiguration.get(arg5.getContext()).getScaledTouchSlop();
        }

        if(arg7.getAction() == 2 && (this.c)) {
            return 1;
        }

        int v1 = -1;
        switch(arg7.getActionMasked()) {
            case 0: {
                this.c = false;
                int v0 = ((int)arg7.getX());
                v1 = ((int)arg7.getY());
                if(!this.c(arg6)) {
                    goto label_56;
                }

                if(!arg5.a(arg6, v0, v1)) {
                    goto label_56;
                }

                this.e = v1;
                this.d = arg7.getPointerId(0);
                this.d();
                break;
            }
            case 2: {
                int v5 = this.d;
                if(v5 == v1) {
                    goto label_56;
                }

                v5 = arg7.findPointerIndex(v5);
                if(v5 == v1) {
                    goto label_56;
                }

                v5 = ((int)arg7.getY(v5));
                if(Math.abs(v5 - this.e) <= this.f) {
                    goto label_56;
                }

                this.c = true;
                this.e = v5;
                break;
            }
            case 1: 
            case 3: {
                this.c = false;
                this.d = v1;
                if(this.g == null) {
                    goto label_56;
                }

                this.g.recycle();
                this.g = null;
                break;
            }
            default: {
                break;
            }
        }

    label_56:
        if(this.g != null) {
            this.g.addMovement(arg7);
        }

        return this.c;
    }

    boolean c(View arg1) {
        return 0;
    }

    private void d() {
        if(this.g == null) {
            this.g = VelocityTracker.obtain();
        }
    }
}

