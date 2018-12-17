package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v7.view.menu.s;
import android.view.MotionEvent;
import android.view.View$OnAttachStateChangeListener;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.ListView;

public abstract class am implements View$OnAttachStateChangeListener, View$OnTouchListener {
    class a implements Runnable {
        a(am arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            ViewParent v0 = this.a.c.getParent();
            if(v0 != null) {
                v0.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    class b implements Runnable {
        b(am arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.d();
        }
    }

    private final float a;
    private final int b;
    final View c;
    private final int d;
    private Runnable e;
    private Runnable f;
    private boolean g;
    private int h;
    private final int[] i;

    public am(View arg3) {
        super();
        this.i = new int[2];
        this.c = arg3;
        arg3.setLongClickable(true);
        arg3.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        this.a = ((float)ViewConfiguration.get(arg3.getContext()).getScaledTouchSlop());
        this.b = ViewConfiguration.getTapTimeout();
        this.d = (this.b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private boolean a(MotionEvent arg6) {
        View v0 = this.c;
        if(!v0.isEnabled()) {
            return 0;
        }

        switch(arg6.getActionMasked()) {
            case 0: {
                this.h = arg6.getPointerId(0);
                if(this.e == null) {
                    this.e = new a(this);
                }

                v0.postDelayed(this.e, ((long)this.b));
                if(this.f == null) {
                    this.f = new b(this);
                }

                v0.postDelayed(this.f, ((long)this.d));
                break;
            }
            case 2: {
                int v1 = arg6.findPointerIndex(this.h);
                if(v1 < 0) {
                    return 0;
                }

                if(am.a(v0, arg6.getX(v1), arg6.getY(v1), this.a)) {
                    return 0;
                }

                this.e();
                v0.getParent().requestDisallowInterceptTouchEvent(true);
                return 1;
            }
            case 1: 
            case 3: {
                this.e();
                break;
            }
            default: {
                break;
            }
        }

        return 0;
    }

    private static boolean a(View arg2, float arg3, float arg4, float arg5) {
        float v0 = -arg5;
        boolean v2 = arg3 < v0 || arg4 < v0 || arg3 >= (((float)(arg2.getRight() - arg2.getLeft()))) + arg5 || arg4 >= (((float)(arg2.getBottom() - arg2.getTop()))) + arg5 ? false : true;
        return v2;
    }

    private boolean a(View arg3, MotionEvent arg4) {
        int[] v0 = this.i;
        arg3.getLocationOnScreen(v0);
        arg4.offsetLocation(((float)(-v0[0])), ((float)(-v0[1])));
        return 1;
    }

    public abstract s a();

    private boolean b(MotionEvent arg5) {
        View v0 = this.c;
        s v1 = this.a();
        if(v1 != null) {
            if(!v1.d()) {
            }
            else {
                ListView v1_1 = v1.e();
                if(v1_1 != null) {
                    if(!((aj)v1_1).isShown()) {
                    }
                    else {
                        MotionEvent v3 = MotionEvent.obtainNoHistory(arg5);
                        this.b(v0, v3);
                        this.a(((View)v1_1), v3);
                        boolean v0_1 = ((aj)v1_1).a(v3, this.h);
                        v3.recycle();
                        int v5 = arg5.getActionMasked();
                        boolean v1_2 = true;
                        v5 = v5 == 1 || v5 == 3 ? 0 : 1;
                        if(!v0_1 || v5 == 0) {
                            v1_2 = false;
                        }
                        else {
                        }

                        return v1_2;
                    }
                }
            }
        }

        return 0;
    }

    private boolean b(View arg3, MotionEvent arg4) {
        int[] v0 = this.i;
        arg3.getLocationOnScreen(v0);
        arg4.offsetLocation(((float)v0[0]), ((float)v0[1]));
        return 1;
    }

    protected boolean b() {
        s v0 = this.a();
        if(v0 != null && !v0.d()) {
            v0.a();
        }

        return 1;
    }

    protected boolean c() {
        s v0 = this.a();
        if(v0 != null && (v0.d())) {
            v0.c();
        }

        return 1;
    }

    void d() {
        this.e();
        View v0 = this.c;
        if(v0.isEnabled()) {
            if(v0.isLongClickable()) {
            }
            else if(!this.b()) {
                return;
            }
            else {
                v0.getParent().requestDisallowInterceptTouchEvent(true);
                long v5 = SystemClock.uptimeMillis();
                MotionEvent v1 = MotionEvent.obtain(v5, v5, 3, 0f, 0f, 0);
                v0.onTouchEvent(v1);
                v1.recycle();
                this.g = true;
            }
        }
    }

    private void e() {
        if(this.f != null) {
            this.c.removeCallbacks(this.f);
        }

        if(this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }

    public boolean onTouch(View arg11, MotionEvent arg12) {
        boolean v12;
        boolean v11 = this.g;
        boolean v0 = false;
        if(v11) {
            if(!this.b(arg12)) {
                if(!this.c()) {
                }
                else {
                    v12 = false;
                    goto label_31;
                }
            }

            v12 = true;
        }
        else {
            v12 = !this.a(arg12) || !this.b() ? false : true;
            if(!v12) {
                goto label_31;
            }

            long v4 = SystemClock.uptimeMillis();
            MotionEvent v2 = MotionEvent.obtain(v4, v4, 3, 0f, 0f, 0);
            this.c.onTouchEvent(v2);
            v2.recycle();
        }

    label_31:
        this.g = v12;
        if((v12) || (v11)) {
            v0 = true;
        }

        return v0;
    }

    public void onViewAttachedToWindow(View arg1) {
    }

    public void onViewDetachedFromWindow(View arg2) {
        this.g = false;
        this.h = -1;
        if(this.e != null) {
            this.c.removeCallbacks(this.e);
        }
    }
}

