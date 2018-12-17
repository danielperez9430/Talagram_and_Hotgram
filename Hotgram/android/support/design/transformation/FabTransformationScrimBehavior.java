package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.a.b;
import android.support.design.a.i;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    private final i a;
    private final i b;

    public FabTransformationScrimBehavior() {
        super();
        this.a = new i(75, 150);
        this.b = new i(0, 150);
    }

    public FabTransformationScrimBehavior(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.a = new i(75, 150);
        this.b = new i(0, 150);
    }

    private void a(View arg4, boolean arg5, boolean arg6, List arg7, List arg8) {
        float[] v6;
        Property v5;
        i v8 = arg5 ? this.a : this.b;
        if(arg5) {
            if(!arg6) {
                arg4.setAlpha(0f);
            }

            v5 = View.ALPHA;
            v6 = new float[]{1f};
        }
        else {
            v5 = View.ALPHA;
            v6 = new float[]{0f};
        }

        ObjectAnimator v4 = ObjectAnimator.ofFloat(arg4, v5, v6);
        v8.a(((Animator)v4));
        arg7.add(v4);
    }

    public boolean a(CoordinatorLayout arg1, View arg2, MotionEvent arg3) {
        return super.a(arg1, arg2, arg3);
    }

    public boolean a(CoordinatorLayout arg1, View arg2, View arg3) {
        return arg3 instanceof FloatingActionButton;
    }

    protected AnimatorSet b(View arg7, View arg8, boolean arg9, boolean arg10) {
        ArrayList v7 = new ArrayList();
        this.a(arg8, arg9, arg10, v7, new ArrayList());
        AnimatorSet v10 = new AnimatorSet();
        b.a(v10, ((List)v7));
        v10.addListener(new AnimatorListenerAdapter(arg9, arg8) {
            public void onAnimationEnd(Animator arg2) {
                if(!this.a) {
                    this.b.setVisibility(4);
                }
            }

            public void onAnimationStart(Animator arg2) {
                if(this.a) {
                    this.b.setVisibility(0);
                }
            }
        });
        return v10;
    }
}

