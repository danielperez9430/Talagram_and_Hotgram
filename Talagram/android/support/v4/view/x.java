package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class x {
    class a implements y {
        x a;
        boolean b;

        a(x arg1) {
            super();
            this.a = arg1;
        }

        public void a(View arg4) {
            Object v1_1;
            this.b = false;
            Paint v1 = null;
            if(this.a.c > -1) {
                arg4.setLayerType(2, v1);
            }

            if(this.a.a != null) {
                Runnable v0 = this.a.a;
                this.a.a = ((Runnable)v1);
                v0.run();
            }

            Object v0_1 = arg4.getTag(2113929216);
            if((v0_1 instanceof y)) {
                v1_1 = v0_1;
            }

            if(v1_1 != null) {
                ((y)v1_1).a(arg4);
            }
        }

        public void b(View arg4) {
            int v1 = -1;
            Paint v2 = null;
            if(this.a.c > v1) {
                arg4.setLayerType(this.a.c, v2);
                this.a.c = v1;
            }

            if(Build$VERSION.SDK_INT >= 16 || !this.b) {
                if(this.a.b != null) {
                    Runnable v0 = this.a.b;
                    this.a.b = ((Runnable)v2);
                    v0.run();
                }

                Object v0_1 = arg4.getTag(2113929216);
                if((v0_1 instanceof y)) {
                    Object v2_1 = v0_1;
                }

                if(v2 != null) {
                    ((y)v2).b(arg4);
                }

                this.b = true;
            }
        }

        public void c(View arg3) {
            Object v0 = arg3.getTag(2113929216);
            if((v0 instanceof y)) {
            }
            else {
                v0 = null;
            }

            if(v0 != null) {
                ((y)v0).c(arg3);
            }
        }
    }

    Runnable a;
    Runnable b;
    int c;
    private WeakReference d;

    x(View arg2) {
        super();
        this.a = null;
        this.b = null;
        this.c = -1;
        this.d = new WeakReference(arg2);
    }

    private void a(View arg3, y arg4) {
        if(arg4 != null) {
            arg3.animate().setListener(new AnimatorListenerAdapter(arg4, arg3) {
                public void onAnimationCancel(Animator arg2) {
                    this.a.c(this.b);
                }

                public void onAnimationEnd(Animator arg2) {
                    this.a.b(this.b);
                }

                public void onAnimationStart(Animator arg2) {
                    this.a.a(this.b);
                }
            });
        }
        else {
            arg3.animate().setListener(null);
        }
    }

    public long a() {
        Object v0 = this.d.get();
        if(v0 != null) {
            return ((View)v0).animate().getDuration();
        }

        return 0;
    }

    public x a(float arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().alpha(arg2);
        }

        return this;
    }

    public x a(long arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().setDuration(arg2);
        }

        return this;
    }

    public x a(aa arg4) {
        Object v0 = this.d.get();
        if(v0 != null && Build$VERSION.SDK_INT >= 19) {
            ValueAnimator$AnimatorUpdateListener v1 = null;
            if(arg4 != null) {
                android.support.v4.view.x$2 v1_1 = new ValueAnimator$AnimatorUpdateListener(arg4, ((View)v0)) {
                    public void onAnimationUpdate(ValueAnimator arg2) {
                        this.a.a(this.b);
                    }
                };
            }

            ((View)v0).animate().setUpdateListener(v1);
        }

        return this;
    }

    public x a(y arg4) {
        a v4;
        Object v0 = this.d.get();
        if(v0 != null) {
            if(Build$VERSION.SDK_INT < 16) {
                ((View)v0).setTag(2113929216, arg4);
                v4 = new a(this);
            }

            this.a(((View)v0), ((y)v4));
        }

        return this;
    }

    public x a(Interpolator arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().setInterpolator(((TimeInterpolator)arg2));
        }

        return this;
    }

    public x b(float arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().translationY(arg2);
        }

        return this;
    }

    public x b(long arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().setStartDelay(arg2);
        }

        return this;
    }

    public void b() {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().cancel();
        }
    }

    public x c(float arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().scaleX(arg2);
        }

        return this;
    }

    public void c() {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().start();
        }
    }

    public x d(float arg2) {
        Object v0 = this.d.get();
        if(v0 != null) {
            ((View)v0).animate().scaleY(arg2);
        }

        return this;
    }
}

