package org.telegram.ui.ActionBar;

import android.view.KeyEvent;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;

public final class -$$Lambda$ActionBarMenuItem$DSACM5xoXBBb-9TAnJG5eS-F3HQ implements TextView$OnEditorActionListener {
    public -$$Lambda$ActionBarMenuItem$DSACM5xoXBBb-9TAnJG5eS-F3HQ(ActionBarMenuItem arg1) {
        super();
        this.f$0 = arg1;
    }

    public final boolean onEditorAction(TextView arg2, int arg3, KeyEvent arg4) {
        return ActionBarMenuItem.lambda$setIsSearchField$7(this.f$0, arg2, arg3, arg4);
    }
}

