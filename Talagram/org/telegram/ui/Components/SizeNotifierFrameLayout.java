package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.ActionBar;

public class SizeNotifierFrameLayout extends FrameLayout {
    public interface SizeNotifierFrameLayoutDelegate {
        void onSizeChanged(int arg1, boolean arg2);
    }

    private Drawable backgroundDrawable;
    private int bottomClip;
    private SizeNotifierFrameLayoutDelegate delegate;
    private int keyboardHeight;
    private boolean occupyStatusBar;
    private Rect rect;

    public SizeNotifierFrameLayout(Context arg1) {
        super(arg1);
        this.rect = new Rect();
        this.occupyStatusBar = true;
        this.setWillNotDraw(false);
    }

    static SizeNotifierFrameLayoutDelegate access$000(SizeNotifierFrameLayout arg0) {
        return arg0.delegate;
    }

    static int access$100(SizeNotifierFrameLayout arg0) {
        return arg0.keyboardHeight;
    }

    public Drawable getBackgroundImage() {
        return this.backgroundDrawable;
    }

    public int getKeyboardHeight() {
        View v0 = this.getRootView();
        this.getWindowVisibleDisplayFrame(this.rect);
        int v1 = v0.getHeight();
        int v2 = this.rect.top != 0 ? AndroidUtilities.statusBarHeight : 0;
        return v1 - v2 - AndroidUtilities.getViewInset(v0) - (this.rect.bottom - this.rect.top);
    }

    protected boolean isActionBarVisible() {
        return 1;
    }

    public void notifyHeightChanged() {
        if(this.delegate != null) {
            this.keyboardHeight = this.getKeyboardHeight();
            boolean v0 = AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? true : false;
            this.post(new Runnable(v0) {
                public void run() {
                    if(SizeNotifierFrameLayout.this.delegate != null) {
                        SizeNotifierFrameLayout.this.delegate.onSizeChanged(SizeNotifierFrameLayout.this.keyboardHeight, this.val$isWidthGreater);
                    }
                }
            });
        }
    }

    protected void onDraw(Canvas arg9) {
        if(this.backgroundDrawable != null) {
            if((this.backgroundDrawable instanceof ColorDrawable)) {
                if(this.bottomClip != 0) {
                    arg9.save();
                    arg9.clipRect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight() - this.bottomClip);
                }

                this.backgroundDrawable.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                this.backgroundDrawable.draw(arg9);
                if(this.bottomClip == 0) {
                    return;
                }
            }
            else {
                if(!(this.backgroundDrawable instanceof BitmapDrawable)) {
                    return;
                }

                if(this.backgroundDrawable.getTileModeX() == Shader$TileMode.REPEAT) {
                    arg9.save();
                    float v0 = 2f / AndroidUtilities.density;
                    arg9.scale(v0, v0);
                    this.backgroundDrawable.setBounds(0, 0, ((int)Math.ceil(((double)((((float)this.getMeasuredWidth())) / v0)))), ((int)Math.ceil(((double)((((float)this.getMeasuredHeight())) / v0)))));
                }
                else {
                    int v0_1 = this.isActionBarVisible() ? ActionBar.getCurrentActionBarHeight() : 0;
                    int v2 = Build$VERSION.SDK_INT < 21 || !this.occupyStatusBar ? 0 : AndroidUtilities.statusBarHeight;
                    v0_1 += v2;
                    v2 = this.getMeasuredHeight() - v0_1;
                    float v3 = (((float)this.getMeasuredWidth())) / (((float)this.backgroundDrawable.getIntrinsicWidth()));
                    float v4 = (((float)(this.keyboardHeight + v2))) / (((float)this.backgroundDrawable.getIntrinsicHeight()));
                    if(v3 < v4) {
                        v3 = v4;
                    }

                    int v4_1 = ((int)Math.ceil(((double)((((float)this.backgroundDrawable.getIntrinsicWidth())) * v3))));
                    int v3_1 = ((int)Math.ceil(((double)((((float)this.backgroundDrawable.getIntrinsicHeight())) * v3))));
                    int v5 = (this.getMeasuredWidth() - v4_1) / 2;
                    v2 = (v2 - v3_1 + this.keyboardHeight) / 2 + v0_1;
                    arg9.save();
                    arg9.clipRect(0, v0_1, v4_1, this.getMeasuredHeight() - this.bottomClip);
                    this.backgroundDrawable.setBounds(v5, v2, v4_1 + v5, v3_1 + v2);
                }

                this.backgroundDrawable.draw(arg9);
            }

            arg9.restore();
        }
        else {
            super.onDraw(arg9);
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.notifyHeightChanged();
    }

    public void setBackgroundImage(Drawable arg1) {
        this.backgroundDrawable = arg1;
        this.invalidate();
    }

    public void setBottomClip(int arg1) {
        this.bottomClip = arg1;
    }

    public void setDelegate(SizeNotifierFrameLayoutDelegate arg1) {
        this.delegate = arg1;
    }

    public void setOccupyStatusBar(boolean arg1) {
        this.occupyStatusBar = arg1;
    }
}

