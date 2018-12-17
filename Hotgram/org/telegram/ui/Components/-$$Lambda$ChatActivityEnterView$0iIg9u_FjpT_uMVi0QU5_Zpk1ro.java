package org.telegram.ui.Components;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;

public final class -$$Lambda$ChatActivityEnterView$0iIg9u_FjpT_uMVi0QU5_Zpk1ro implements ValueAnimator$AnimatorUpdateListener {
    public -$$Lambda$ChatActivityEnterView$0iIg9u_FjpT_uMVi0QU5_Zpk1ro(ChatActivityEnterView arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void onAnimationUpdate(ValueAnimator arg3) {
        ChatActivityEnterView.lambda$setStickersExpanded$19(this.f$0, this.f$1, arg3);
    }
}

