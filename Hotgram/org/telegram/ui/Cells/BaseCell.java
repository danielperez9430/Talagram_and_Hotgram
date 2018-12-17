package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public abstract class BaseCell extends ViewGroup {
    class org.telegram.ui.Cells.BaseCell$1 {
    }

    class CheckForLongPress implements Runnable {
        public int currentPressCount;

        CheckForLongPress(BaseCell arg1) {
            BaseCell.this = arg1;
            super();
        }

        public void run() {
            if((BaseCell.this.checkingForLongPress) && BaseCell.this.getParent() != null && this.currentPressCount == BaseCell.this.pressCount) {
                BaseCell.this.checkingForLongPress = false;
                BaseCell.this.performHapticFeedback(0);
                BaseCell.this.onLongPress();
                MotionEvent v0 = MotionEvent.obtain(0, 0, 3, 0f, 0f, 0);
                BaseCell.this.onTouchEvent(v0);
                v0.recycle();
            }
        }
    }

    final class CheckForTap implements Runnable {
        CheckForTap(BaseCell arg1, org.telegram.ui.Cells.BaseCell$1 arg2) {
            this(arg1);
        }

        private CheckForTap(BaseCell arg1) {
            BaseCell.this = arg1;
            super();
        }

        public void run() {
            if(BaseCell.this.pendingCheckForLongPress == null) {
                BaseCell.this.pendingCheckForLongPress = new CheckForLongPress(BaseCell.this);
            }

            BaseCell.this.pendingCheckForLongPress.currentPressCount = BaseCell.access$104(BaseCell.this);
            BaseCell.this.postDelayed(BaseCell.this.pendingCheckForLongPress, ((long)(ViewConfiguration.getLongPressTimeout() - ViewConfiguration.getTapTimeout())));
        }
    }

    private boolean checkingForLongPress;
    private CheckForLongPress pendingCheckForLongPress;
    private CheckForTap pendingCheckForTap;
    private int pressCount;

    public BaseCell(Context arg2) {
        super(arg2);
        this.checkingForLongPress = false;
        this.pendingCheckForLongPress = null;
        this.pressCount = 0;
        this.pendingCheckForTap = null;
        this.setWillNotDraw(false);
    }

    static CheckForLongPress access$000(BaseCell arg0) {
        return arg0.pendingCheckForLongPress;
    }

    static CheckForLongPress access$002(BaseCell arg0, CheckForLongPress arg1) {
        arg0.pendingCheckForLongPress = arg1;
        return arg1;
    }

    static int access$100(BaseCell arg0) {
        return arg0.pressCount;
    }

    static int access$104(BaseCell arg1) {
        int v0 = arg1.pressCount + 1;
        arg1.pressCount = v0;
        return v0;
    }

    static boolean access$200(BaseCell arg0) {
        return arg0.checkingForLongPress;
    }

    static boolean access$202(BaseCell arg0, boolean arg1) {
        arg0.checkingForLongPress = arg1;
        return arg1;
    }

    protected void cancelCheckLongPress() {
        this.checkingForLongPress = false;
        if(this.pendingCheckForLongPress != null) {
            this.removeCallbacks(this.pendingCheckForLongPress);
        }

        if(this.pendingCheckForTap != null) {
            this.removeCallbacks(this.pendingCheckForTap);
        }
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    protected void onLongPress() {
    }

    public static void setDrawableBounds(Drawable arg2, float arg3, float arg4) {
        BaseCell.setDrawableBounds(arg2, ((int)arg3), ((int)arg4), arg2.getIntrinsicWidth(), arg2.getIntrinsicHeight());
    }

    public static void setDrawableBounds(Drawable arg0, int arg1, int arg2, int arg3, int arg4) {
        if(arg0 != null) {
            arg0.setBounds(arg1, arg2, arg3 + arg1, arg4 + arg2);
        }
    }

    public static void setDrawableBounds(Drawable arg2, int arg3, int arg4) {
        BaseCell.setDrawableBounds(arg2, arg3, arg4, arg2.getIntrinsicWidth(), arg2.getIntrinsicHeight());
    }

    protected void startCheckLongPress() {
        if(this.checkingForLongPress) {
            return;
        }

        this.checkingForLongPress = true;
        if(this.pendingCheckForTap == null) {
            this.pendingCheckForTap = new CheckForTap(this, null);
        }

        this.postDelayed(this.pendingCheckForTap, ((long)ViewConfiguration.getTapTimeout()));
    }
}

