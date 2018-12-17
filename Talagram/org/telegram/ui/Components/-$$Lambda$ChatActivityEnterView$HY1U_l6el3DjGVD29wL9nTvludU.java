package org.telegram.ui.Components;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;

public final class -$$Lambda$ChatActivityEnterView$HY1U_l6el3DjGVD29wL9nTvludU implements ValueAnimator$AnimatorUpdateListener {
    public -$$Lambda$ChatActivityEnterView$HY1U_l6el3DjGVD29wL9nTvludU(ChatActivityEnterView arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onAnimationUpdate(ValueAnimator arg3) {
        ChatActivityEnterView.lambda$setStickersExpanded$18(this.f$0, this.f$1, arg3);
    }
}

