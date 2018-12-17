package android.support.design.widget;

import android.content.Context;
import android.support.design.a$b;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;

public final class Snackbar extends BaseTransientBottomBar {
    public final class SnackbarLayout extends e {
        public SnackbarLayout(Context arg1) {
            super(arg1);
        }

        public SnackbarLayout(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        protected void onMeasure(int arg6, int arg7) {
            super.onMeasure(arg6, arg7);
            arg6 = this.getChildCount();
            arg7 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
            int v0;
            for(v0 = 0; v0 < arg6; ++v0) {
                View v1 = this.getChildAt(v0);
                if(v1.getLayoutParams().width == -1) {
                    v1.measure(View$MeasureSpec.makeMeasureSpec(arg7, 1073741824), View$MeasureSpec.makeMeasureSpec(v1.getMeasuredHeight(), 1073741824));
                }
            }
        }
    }

    private static final int[] d;

    static {
        Snackbar.d = new int[]{b.snackbarButtonStyle};
    }
}

