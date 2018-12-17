package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    private AnimatorSet a;

    public ExpandableTransformationBehavior() {
        super();
    }

    public ExpandableTransformationBehavior(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    static AnimatorSet a(ExpandableTransformationBehavior arg0, AnimatorSet arg1) {
        arg0.a = arg1;
        return arg1;
    }

    protected boolean a(View arg4, View arg5, boolean arg6, boolean arg7) {
        boolean v0 = this.a != null ? true : false;
        if(v0) {
            this.a.cancel();
        }

        this.a = this.b(arg4, arg5, arg6, v0);
        this.a.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                ExpandableTransformationBehavior.a(this.a, null);
            }
        });
        this.a.start();
        if(!arg7) {
            this.a.end();
        }

        return 1;
    }

    protected abstract AnimatorSet b(View arg1, View arg2, boolean arg3, boolean arg4);
}

