package android.support.design.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.Collection;
import java.util.List;

public class b {
    public static void a(AnimatorSet arg10, List arg11) {
        int v0 = arg11.size();
        long v3 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v5 = arg11.get(v2);
            v3 = Math.max(v3, ((Animator)v5).getStartDelay() + ((Animator)v5).getDuration());
        }

        ValueAnimator v0_1 = ValueAnimator.ofInt(new int[]{0, 0});
        ((Animator)v0_1).setDuration(v3);
        arg11.add(0, v0_1);
        arg10.playTogether(((Collection)arg11));
    }
}

