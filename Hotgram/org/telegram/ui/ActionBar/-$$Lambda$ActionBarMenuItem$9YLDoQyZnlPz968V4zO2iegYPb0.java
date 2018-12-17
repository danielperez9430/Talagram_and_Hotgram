package org.telegram.ui.ActionBar;

import android.view.KeyEvent;
import android.view.View$OnKeyListener;
import android.view.View;

public final class -$$Lambda$ActionBarMenuItem$9YLDoQyZnlPz968V4zO2iegYPb0 implements View$OnKeyListener {
    public -$$Lambda$ActionBarMenuItem$9YLDoQyZnlPz968V4zO2iegYPb0(ActionBarMenuItem arg1) {
        super();
        this.f$0 = arg1;
    }

    public final boolean onKey(View arg2, int arg3, KeyEvent arg4) {
        return ActionBarMenuItem.lambda$toggleSubMenu$6(this.f$0, arg2, arg3, arg4);
    }
}

