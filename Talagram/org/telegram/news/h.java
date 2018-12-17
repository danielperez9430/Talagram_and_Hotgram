package org.telegram.news;

import android.content.Context;
import android.util.Log;
import android.view.ScaleGestureDetector$SimpleOnScaleGestureListener;
import android.view.ScaleGestureDetector;
import utils.a.b;

class h extends ScaleGestureDetector$SimpleOnScaleGestureListener {
    private final Context a;
    private final c b;

    public h(Context arg1, c arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public boolean onScale(ScaleGestureDetector arg4) {
        float v0 = b.t(this.a);
        float v4 = arg4.getScaleFactor();
        Log.d("Factor1", String.valueOf(v4));
        Log.d("Factor2", String.valueOf(v0));
        v0 *= v4;
        int v1 = 2131361814;
        if(v0 > (((float)this.a.getResources().getInteger(v1)))) {
            v0 = ((float)this.a.getResources().getInteger(v1));
        }

        v1 = 2131361813;
        if(v0 < (((float)this.a.getResources().getInteger(v1)))) {
            v0 = ((float)this.a.getResources().getInteger(v1));
        }

        b.a(this.a, Float.valueOf(v0));
        this.b.a(v0);
        return 1;
    }

    public boolean onScaleBegin(ScaleGestureDetector arg4) {
        boolean v4 = super.onScaleBegin(arg4);
        Log.d("Factor1", "onScaleBegin " + v4);
        return v4;
    }

    public void onScaleEnd(ScaleGestureDetector arg3) {
        Log.d("Factor1", "onScaleEnd");
        super.onScaleEnd(arg3);
    }
}

