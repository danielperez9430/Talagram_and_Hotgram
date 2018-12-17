package org.telegram.ui.ActionBar;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;

public final class -$$Lambda$ActionBarMenuItem$NEgOkBBuNcW0duabIaP5FYMgH5w implements View$OnTouchListener {
    public -$$Lambda$ActionBarMenuItem$NEgOkBBuNcW0duabIaP5FYMgH5w(ActionBarMenuItem arg1) {
        super();
        this.f$0 = arg1;
    }

    public final boolean onTouch(View arg2, MotionEvent arg3) {
        return ActionBarMenuItem.lambda$createPopupLayout$1(this.f$0, arg2, arg3);
    }
}

