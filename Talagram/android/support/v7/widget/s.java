package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.a.a$a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;

public class s extends RatingBar {
    private final q a;

    public s(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.ratingBarStyle);
    }

    public s(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.a = new q(((ProgressBar)this));
        this.a.a(arg2, arg3);
    }

    protected void onMeasure(int arg2, int arg3) {
        __monitor_enter(this);
        try {
            super.onMeasure(arg2, arg3);
            Bitmap v3 = this.a.a();
            if(v3 != null) {
                this.setMeasuredDimension(View.resolveSizeAndState(v3.getWidth() * this.getNumStars(), arg2, 0), this.getMeasuredHeight());
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }
}

