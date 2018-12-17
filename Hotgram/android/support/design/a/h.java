package android.support.design.a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.f.m;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class h {
    private final m a;

    public h() {
        super();
        this.a = new m();
    }

    public static h a(Context arg4, int arg5) {
        h v0 = null;
        try {
            Animator v4_1 = AnimatorInflater.loadAnimator(arg4, arg5);
            if((v4_1 instanceof AnimatorSet)) {
                return h.a(((AnimatorSet)v4_1).getChildAnimations());
            }

            if(v4_1 == null) {
                return v0;
            }

            ArrayList v1 = new ArrayList();
            ((List)v1).add(v4_1);
            return h.a(((List)v1));
        }
        catch(Exception v4) {
            Log.w("MotionSpec", "Can\'t load animation resource ID #0x" + Integer.toHexString(arg5), ((Throwable)v4));
            return v0;
        }

        return v0;
    }

    private static h a(List arg4) {
        h v0 = new h();
        int v1 = arg4.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            h.a(v0, arg4.get(v2));
        }

        return v0;
    }

    public static h a(Context arg1, TypedArray arg2, int arg3) {
        if(arg2.hasValue(arg3)) {
            int v2 = arg2.getResourceId(arg3, 0);
            if(v2 != 0) {
                return h.a(arg1, v2);
            }
        }

        return null;
    }

    private static void a(h arg2, Animator arg3) {
        if((arg3 instanceof ObjectAnimator)) {
            arg2.a(((ObjectAnimator)arg3).getPropertyName(), i.a(((ValueAnimator)arg3)));
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Animator must be an ObjectAnimator: ");
        v0.append(arg3);
        throw new IllegalArgumentException(v0.toString());
    }

    public void a(String arg2, i arg3) {
        this.a.put(arg2, arg3);
    }

    public long a() {
        int v0 = this.a.size();
        long v1 = 0;
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            Object v4 = this.a.c(v3);
            v1 = Math.max(v1, ((i)v4).a() + ((i)v4).b());
        }

        return v1;
    }

    public boolean a(String arg2) {
        boolean v2 = this.a.get(arg2) != null ? true : false;
        return v2;
    }

    public i b(String arg2) {
        if(this.a(arg2)) {
            return this.a.get(arg2);
        }

        throw new IllegalArgumentException();
    }

    public boolean equals(Object arg3) {
        if(this == (((h)arg3))) {
            return 1;
        }

        if(arg3 != null) {
            if(this.getClass() != arg3.getClass()) {
            }
            else {
                return this.a.equals(((h)arg3).a);
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return '\n' + this.getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.a + "}\n";
    }
}

