package android.support.design.a;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class i {
    private long a;
    private long b;
    private TimeInterpolator c;
    private int d;
    private int e;

    public i(long arg3, long arg5) {
        super();
        this.a = 0;
        this.b = 300;
        this.c = null;
        this.d = 0;
        this.e = 1;
        this.a = arg3;
        this.b = arg5;
    }

    public i(long arg3, long arg5, TimeInterpolator arg7) {
        super();
        this.a = 0;
        this.b = 300;
        this.c = null;
        this.d = 0;
        this.e = 1;
        this.a = arg3;
        this.b = arg5;
        this.c = arg7;
    }

    static i a(ValueAnimator arg7) {
        i v6 = new i(arg7.getStartDelay(), arg7.getDuration(), i.b(arg7));
        v6.d = arg7.getRepeatCount();
        v6.e = arg7.getRepeatMode();
        return v6;
    }

    public long a() {
        return this.a;
    }

    public void a(Animator arg3) {
        arg3.setStartDelay(this.a());
        arg3.setDuration(this.b());
        arg3.setInterpolator(this.c());
        if((arg3 instanceof ValueAnimator)) {
            ((ValueAnimator)arg3).setRepeatCount(this.d());
            ((ValueAnimator)arg3).setRepeatMode(this.e());
        }
    }

    public long b() {
        return this.b;
    }

    private static TimeInterpolator b(ValueAnimator arg1) {
        TimeInterpolator v1 = arg1.getInterpolator();
        if(!(v1 instanceof AccelerateDecelerateInterpolator)) {
            if(v1 == null) {
            }
            else if((v1 instanceof AccelerateInterpolator)) {
                return a.c;
            }
            else {
                if((v1 instanceof DecelerateInterpolator)) {
                    v1 = a.d;
                }

                return v1;
            }
        }

        return a.b;
    }

    public TimeInterpolator c() {
        TimeInterpolator v0 = this.c != null ? this.c : a.b;
        return v0;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public boolean equals(Object arg7) {
        if(this == (((i)arg7))) {
            return 1;
        }

        if(arg7 != null) {
            if(this.getClass() != arg7.getClass()) {
            }
            else if(this.a() != ((i)arg7).a()) {
                return 0;
            }
            else if(this.b() != ((i)arg7).b()) {
                return 0;
            }
            else if(this.d() != ((i)arg7).d()) {
                return 0;
            }
            else if(this.e() != ((i)arg7).e()) {
                return 0;
            }
            else {
                return this.c().getClass().equals(((i)arg7).c().getClass());
            }
        }

        return 0;
    }

    public int hashCode() {
        return ((((((int)(this.a() ^ this.a() >>> 32))) * 31 + (((int)(this.b() ^ this.b() >>> 32)))) * 31 + this.c().getClass().hashCode()) * 31 + this.d()) * 31 + this.e();
    }

    public String toString() {
        return '\n' + this.getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + this.a() + " duration: " + this.b() + " interpolator: " + this.c().getClass() + " repeatCount: " + this.d() + " repeatMode: " + this.e() + "}\n";
    }
}

