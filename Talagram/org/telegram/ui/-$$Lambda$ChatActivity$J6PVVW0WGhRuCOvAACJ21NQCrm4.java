package org.telegram.ui;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;

public final class -$$Lambda$ChatActivity$J6PVVW0WGhRuCOvAACJ21NQCrm4 implements View$OnTouchListener {
    public -$$Lambda$ChatActivity$J6PVVW0WGhRuCOvAACJ21NQCrm4(ChatActivity arg1, StickerPreviewViewerDelegate arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final boolean onTouch(View arg3, MotionEvent arg4) {
        return ChatActivity.lambda$createView2$23(this.f$0, this.f$1, arg3, arg4);
    }
}

