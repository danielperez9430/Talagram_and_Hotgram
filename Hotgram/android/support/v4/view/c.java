package android.support.v4.view;

import android.content.Context;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector$OnDoubleTapListener;
import android.view.GestureDetector$OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public final class c {
    interface a {
        boolean a(MotionEvent arg1);
    }

    class b implements a {
        class android.support.v4.view.c$b$a extends Handler {
            android.support.v4.view.c$b$a(b arg1, Handler arg2) {
                this.a = arg1;
                super(arg2.getLooper());
            }

            android.support.v4.view.c$b$a(b arg1) {
                this.a = arg1;
                super();
            }

            public void handleMessage(Message arg4) {
                switch(arg4.what) {
                    case 1: {
                        this.a.a.onShowPress(this.a.e);
                        break;
                    }
                    case 2: {
                        this.a.a();
                        break;
                    }
                    case 3: {
                        if(this.a.b == null) {
                            return;
                        }

                        if(!this.a.c) {
                            this.a.b.onSingleTapConfirmed(this.a.e);
                            return;
                        }

                        this.a.d = true;
                        break;
                    }
                    default: {
                        StringBuilder v1 = new StringBuilder();
                        v1.append("Unknown message ");
                        v1.append(arg4);
                        throw new RuntimeException(v1.toString());
                    }
                }
            }
        }

        final GestureDetector$OnGestureListener a;
        GestureDetector$OnDoubleTapListener b;
        boolean c;
        boolean d;
        MotionEvent e;
        private int f;
        private int g;
        private int h;
        private int i;
        private static final int j;
        private static final int k;
        private static final int l;
        private final Handler m;
        private boolean n;
        private boolean o;
        private boolean p;
        private MotionEvent q;
        private boolean r;
        private float s;
        private float t;
        private float u;
        private float v;
        private boolean w;
        private VelocityTracker x;

        static {
            b.j = ViewConfiguration.getLongPressTimeout();
            b.k = ViewConfiguration.getTapTimeout();
            b.l = ViewConfiguration.getDoubleTapTimeout();
        }

        b(Context arg2, GestureDetector$OnGestureListener arg3, Handler arg4) {
            super();
            this.m = arg4 != null ? new android.support.v4.view.c$b$a(this, arg4) : new android.support.v4.view.c$b$a(this);
            this.a = arg3;
            if((arg3 instanceof GestureDetector$OnDoubleTapListener)) {
                this.a(((GestureDetector$OnDoubleTapListener)arg3));
            }

            this.a(arg2);
        }

        public void a(GestureDetector$OnDoubleTapListener arg1) {
            this.b = arg1;
        }

        private void a(Context arg4) {
            if(arg4 != null) {
                if(this.a != null) {
                    this.w = true;
                    ViewConfiguration v4 = ViewConfiguration.get(arg4);
                    int v0 = v4.getScaledTouchSlop();
                    int v1 = v4.getScaledDoubleTapSlop();
                    this.h = v4.getScaledMinimumFlingVelocity();
                    this.i = v4.getScaledMaximumFlingVelocity();
                    this.f = v0 * v0;
                    this.g = v1 * v1;
                    return;
                }

                throw new IllegalArgumentException("OnGestureListener must not be null");
            }

            throw new IllegalArgumentException("Context must not be null");
        }

        private boolean a(MotionEvent arg7, MotionEvent arg8, MotionEvent arg9) {
            boolean v1 = false;
            if(!this.p) {
                return 0;
            }

            if(arg9.getEventTime() - arg8.getEventTime() > (((long)b.l))) {
                return 0;
            }

            int v8 = (((int)arg7.getX())) - (((int)arg9.getX()));
            int v7 = (((int)arg7.getY())) - (((int)arg9.getY()));
            if(v8 * v8 + v7 * v7 < this.g) {
                v1 = true;
            }

            return v1;
        }

        void a() {
            this.m.removeMessages(3);
            this.d = false;
            this.n = true;
            this.a.onLongPress(this.e);
        }

        public boolean a(MotionEvent arg12) {
            float v5_2;
            boolean v12;
            int v6;
            int v3_1;
            int v0 = arg12.getAction();
            if(this.x == null) {
                this.x = VelocityTracker.obtain();
            }

            this.x.addMovement(arg12);
            v0 &= 255;
            boolean v3 = false;
            int v1 = v0 == 6 ? 1 : 0;
            int v4 = v1 != 0 ? arg12.getActionIndex() : -1;
            int v5 = arg12.getPointerCount();
            int v7 = 0;
            float v8 = 0f;
            float v9 = 0f;
            while(v7 < v5) {
                if(v4 == v7) {
                }
                else {
                    v8 += arg12.getX(v7);
                    v9 += arg12.getY(v7);
                }

                ++v7;
            }

            v1 = v1 != 0 ? v5 - 1 : v5;
            float v1_1 = ((float)v1);
            v8 /= v1_1;
            v9 /= v1_1;
            v1 = 1000;
            v4 = 2;
            v7 = 3;
            switch(v0) {
                case 0: {
                    if(this.b != null) {
                        boolean v0_3 = this.m.hasMessages(v7);
                        if(v0_3) {
                            this.m.removeMessages(v7);
                        }

                        if(this.e != null && this.q != null && (v0_3) && (this.a(this.e, this.q, arg12))) {
                            this.r = true;
                            v0 = this.b.onDoubleTap(this.e) | 0 | this.b.onDoubleTapEvent(arg12);
                            goto label_236;
                        }

                        this.m.sendEmptyMessageDelayed(v7, ((long)b.l));
                        goto label_235;
                    }
                    else {
                    label_235:
                        v0 = 0;
                    }

                label_236:
                    this.s = v8;
                    this.u = v8;
                    this.t = v9;
                    this.v = v9;
                    if(this.e != null) {
                        this.e.recycle();
                    }

                    this.e = MotionEvent.obtain(arg12);
                    this.o = true;
                    this.p = true;
                    this.c = true;
                    this.n = false;
                    this.d = false;
                    if(this.w) {
                        this.m.removeMessages(v4);
                        this.m.sendEmptyMessageAtTime(v4, this.e.getDownTime() + (((long)b.k)) + (((long)b.j)));
                    }

                    this.m.sendEmptyMessageAtTime(1, this.e.getDownTime() + (((long)b.k)));
                    v3_1 = v0 | this.a.onDown(arg12);
                    goto label_275;
                label_60:
                    while(v4 < v5) {
                        if(v4 == v0) {
                        }
                        else {
                            v7 = arg12.getPointerId(v4);
                            if(this.x.getXVelocity(v7) * v2 + this.x.getYVelocity(v7) * v1_1 < 0f) {
                                this.x.clear();
                                break;
                            }
                        }

                        ++v4;
                    }
                }
                case 1: {
                    this.c = false;
                    MotionEvent v0_2 = MotionEvent.obtain(arg12);
                    if(this.r) {
                        int v12_1 = this.b.onDoubleTapEvent(arg12) | 0;
                    }
                    else {
                        if(this.n) {
                            this.m.removeMessages(v7);
                            this.n = false;
                        }
                        else if(this.o) {
                            boolean v1_2 = this.a.onSingleTapUp(arg12);
                            if((this.d) && this.b != null) {
                                this.b.onSingleTapConfirmed(arg12);
                            }

                            v12 = v1_2;
                            goto label_187;
                        }
                        else {
                            VelocityTracker v5_1 = this.x;
                            v6 = arg12.getPointerId(0);
                            v5_1.computeCurrentVelocity(v1, ((float)this.i));
                            v1_1 = v5_1.getYVelocity(v6);
                            v5_2 = v5_1.getXVelocity(v6);
                            if(Math.abs(v1_1) <= (((float)this.h))) {
                                if(Math.abs(v5_2) > (((float)this.h))) {
                                }
                                else {
                                    goto label_182;
                                }
                            }

                            goto label_184;
                        }

                    label_182:
                        v12 = false;
                        goto label_187;
                    label_184:
                        v12 = this.a.onFling(this.e, arg12, v5_2, v1_1);
                    }

                label_187:
                    if(this.q != null) {
                        this.q.recycle();
                    }

                    this.q = v0_2;
                    if(this.x != null) {
                        this.x.recycle();
                        this.x = null;
                    }

                    this.r = false;
                    this.d = false;
                    this.m.removeMessages(1);
                    this.m.removeMessages(v4);
                label_204:
                    v3 = v12;
                    break;
                }
                case 2: {
                    if(this.n) {
                        goto label_275;
                    }

                    float v0_1 = this.s - v8;
                    v1_1 = this.t - v9;
                    if(this.r) {
                        v3_1 = 0 | this.b.onDoubleTapEvent(arg12);
                        goto label_275;
                    }

                    if(this.o) {
                        v5 = ((int)(v8 - this.u));
                        v6 = ((int)(v9 - this.v));
                        v5 = v5 * v5 + v6 * v6;
                        if(v5 > this.f) {
                            v12 = this.a.onScroll(this.e, arg12, v0_1, v1_1);
                            this.s = v8;
                            this.t = v9;
                            this.o = false;
                            this.m.removeMessages(v7);
                            this.m.removeMessages(1);
                            this.m.removeMessages(v4);
                        }
                        else {
                            v12 = false;
                        }

                        if(v5 <= this.f) {
                            goto label_204;
                        }

                        this.p = false;
                        goto label_204;
                    }

                    float v4_1 = 1f;
                    if(Math.abs(v0_1) < v4_1 && Math.abs(v1_1) < v4_1) {
                        goto label_275;
                    }

                    v3 = this.a.onScroll(this.e, arg12, v0_1, v1_1);
                    this.s = v8;
                    this.t = v9;
                    break;
                }
                case 3: {
                    this.b();
                    break;
                }
                case 5: {
                    this.s = v8;
                    this.u = v8;
                    this.t = v9;
                    this.v = v9;
                    this.c();
                    break;
                }
                case 6: {
                    this.s = v8;
                    this.u = v8;
                    this.t = v9;
                    this.v = v9;
                    this.x.computeCurrentVelocity(v1, ((float)this.i));
                    v0 = arg12.getActionIndex();
                    v1 = arg12.getPointerId(v0);
                    float v2 = this.x.getXVelocity(v1);
                    v1_1 = this.x.getYVelocity(v1);
                    v4 = 0;
                    goto label_60;
                }
                default: {
                    break;
                }
            }

        label_275:
            return ((boolean)v3_1);
        }

        private void b() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.x.recycle();
            this.x = null;
            this.r = false;
            this.c = false;
            this.o = false;
            this.p = false;
            this.d = false;
            if(this.n) {
                this.n = false;
            }
        }

        private void c() {
            this.m.removeMessages(1);
            this.m.removeMessages(2);
            this.m.removeMessages(3);
            this.r = false;
            this.o = false;
            this.p = false;
            this.d = false;
            if(this.n) {
                this.n = false;
            }
        }
    }

    class android.support.v4.view.c$c implements a {
        private final GestureDetector a;

        android.support.v4.view.c$c(Context arg2, GestureDetector$OnGestureListener arg3, Handler arg4) {
            super();
            this.a = new GestureDetector(arg2, arg3, arg4);
        }

        public boolean a(MotionEvent arg2) {
            return this.a.onTouchEvent(arg2);
        }
    }

    private final a a;

    public c(Context arg2, GestureDetector$OnGestureListener arg3) {
        this(arg2, arg3, null);
    }

    public c(Context arg3, GestureDetector$OnGestureListener arg4, Handler arg5) {
        b v0_1;
        super();
        if(Build$VERSION.SDK_INT > 17) {
            android.support.v4.view.c$c v0 = new android.support.v4.view.c$c(arg3, arg4, arg5);
        }
        else {
            v0_1 = new b(arg3, arg4, arg5);
        }

        this.a = ((a)v0_1);
    }

    public boolean a(MotionEvent arg2) {
        return this.a.a(arg2);
    }
}

