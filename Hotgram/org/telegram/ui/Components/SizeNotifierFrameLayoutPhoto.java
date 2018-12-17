package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;

public class SizeNotifierFrameLayoutPhoto extends FrameLayout {
    public interface SizeNotifierFrameLayoutPhotoDelegate {
        void onSizeChanged(int arg1, boolean arg2);
    }

    private SizeNotifierFrameLayoutPhotoDelegate delegate;
    private int keyboardHeight;
    private Rect rect;
    private WindowManager windowManager;
    private boolean withoutWindow;

    public SizeNotifierFrameLayoutPhoto(Context arg1) {
        super(arg1);
        this.rect = new Rect();
    }

    static SizeNotifierFrameLayoutPhotoDelegate access$000(SizeNotifierFrameLayoutPhoto arg0) {
        return arg0.delegate;
    }

    static int access$100(SizeNotifierFrameLayoutPhoto arg0) {
        return arg0.keyboardHeight;
    }

    public int getKeyboardHeight() {
        View v0 = this.getRootView();
        this.getWindowVisibleDisplayFrame(this.rect);
        int v2 = 0;
        if(this.withoutWindow) {
            int v1 = v0.getHeight();
            if(this.rect.top != 0) {
                v2 = AndroidUtilities.statusBarHeight;
            }

            return v1 - v2 - AndroidUtilities.getViewInset(v0) - (this.rect.bottom - this.rect.top);
        }

        int v0_1 = AndroidUtilities.displaySize.y - this.rect.top - (v0.getHeight() - AndroidUtilities.getViewInset(v0));
        if(v0_1 <= Math.max(AndroidUtilities.dp(10f), AndroidUtilities.statusBarHeight)) {
            v0_1 = 0;
        }

        return v0_1;
    }

    public void notifyHeightChanged() {
        if(this.delegate != null) {
            this.keyboardHeight = this.getKeyboardHeight();
            boolean v0 = AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? true : false;
            this.post(new Runnable(v0) {
                public void run() {
                    if(SizeNotifierFrameLayoutPhoto.this.delegate != null) {
                        SizeNotifierFrameLayoutPhoto.this.delegate.onSizeChanged(SizeNotifierFrameLayoutPhoto.this.keyboardHeight, this.val$isWidthGreater);
                    }
                }
            });
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.notifyHeightChanged();
    }

    public void setDelegate(SizeNotifierFrameLayoutPhotoDelegate arg1) {
        this.delegate = arg1;
    }

    public void setWithoutWindow(boolean arg1) {
        this.withoutWindow = arg1;
    }
}

