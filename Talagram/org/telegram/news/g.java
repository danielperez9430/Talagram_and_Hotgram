package org.telegram.news;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;

public class g implements Animation$AnimationListener {
    private final View a;

    public g(View arg1) {
        super();
        this.a = arg1;
    }

    public void onAnimationEnd(Animation arg2) {
        Log.d("sadegh)anim", "end");
        if(this.a != null) {
            this.a.setVisibility(0);
        }
    }

    public void onAnimationRepeat(Animation arg1) {
    }

    public void onAnimationStart(Animation arg1) {
    }
}

