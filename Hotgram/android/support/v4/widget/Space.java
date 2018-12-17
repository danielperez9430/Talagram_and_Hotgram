package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;

@Deprecated public class Space extends View {
    @Deprecated public Space(Context arg2) {
        this(arg2, null);
    }

    @Deprecated public Space(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    @Deprecated public Space(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        if(this.getVisibility() == 0) {
            this.setVisibility(4);
        }
    }

    private static int a(int arg2, int arg3) {
        int v0 = View$MeasureSpec.getMode(arg3);
        arg3 = View$MeasureSpec.getSize(arg3);
        if(v0 == -2147483648) {
            arg2 = Math.min(arg2, arg3);
        }
        else if(v0 != 0) {
            if(v0 != 1073741824) {
            }
            else {
                arg2 = arg3;
            }
        }

        return arg2;
    }

    @SuppressLint(value={"MissingSuperCall"}) @Deprecated public void draw(Canvas arg1) {
    }

    @Deprecated protected void onMeasure(int arg2, int arg3) {
        this.setMeasuredDimension(Space.a(this.getSuggestedMinimumWidth(), arg2), Space.a(this.getSuggestedMinimumHeight(), arg3));
    }
}

