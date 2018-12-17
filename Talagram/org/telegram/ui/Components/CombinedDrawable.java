package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;

public class CombinedDrawable extends Drawable implements Drawable$Callback {
    private int backHeight;
    private int backWidth;
    private Drawable background;
    private boolean fullSize;
    private Drawable icon;
    private int iconHeight;
    private int iconWidth;
    private int left;
    private int offsetX;
    private int offsetY;
    private int top;

    public CombinedDrawable(Drawable arg1, Drawable arg2) {
        super();
        this.background = arg1;
        this.icon = arg2;
        if(arg2 != null) {
            arg2.setCallback(((Drawable$Callback)this));
        }
    }

    public CombinedDrawable(Drawable arg1, Drawable arg2, int arg3, int arg4) {
        super();
        this.background = arg1;
        this.icon = arg2;
        this.left = arg3;
        this.top = arg4;
        if(arg2 != null) {
            arg2.setCallback(((Drawable$Callback)this));
        }
    }

    public void draw(Canvas arg6) {
        int v4;
        int v3;
        Drawable v2;
        int v1;
        int v0;
        this.background.setBounds(this.getBounds());
        this.background.draw(arg6);
        if(this.icon != null) {
            if(this.fullSize) {
                this.icon.setBounds(this.getBounds());
            }
            else {
                if(this.iconWidth != 0) {
                    v0 = this.getBounds().centerX() - this.iconWidth / 2 + this.left + this.offsetX;
                    v1 = this.getBounds().centerY() - this.iconHeight / 2 + this.top + this.offsetY;
                    v2 = this.icon;
                    v3 = this.iconWidth + v0;
                    v4 = this.iconHeight;
                }
                else {
                    v0 = this.getBounds().centerX() - this.icon.getIntrinsicWidth() / 2 + this.left;
                    v1 = this.getBounds().centerY() - this.icon.getIntrinsicHeight() / 2 + this.top;
                    v2 = this.icon;
                    v3 = this.icon.getIntrinsicWidth() + v0;
                    v4 = this.icon.getIntrinsicHeight();
                }

                v2.setBounds(v0, v1, v3, v4 + v1);
            }

            this.icon.draw(arg6);
        }
    }

    public Drawable getBackground() {
        return this.background;
    }

    public Drawable$ConstantState getConstantState() {
        return this.icon.getConstantState();
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public int getIntrinsicHeight() {
        int v0 = this.backHeight != 0 ? this.backHeight : this.background.getIntrinsicHeight();
        return v0;
    }

    public int getIntrinsicWidth() {
        int v0 = this.backWidth != 0 ? this.backWidth : this.background.getIntrinsicWidth();
        return v0;
    }

    public int getMinimumHeight() {
        int v0 = this.backHeight != 0 ? this.backHeight : this.background.getMinimumHeight();
        return v0;
    }

    public int getMinimumWidth() {
        int v0 = this.backWidth != 0 ? this.backWidth : this.background.getMinimumWidth();
        return v0;
    }

    public int getOpacity() {
        return this.icon.getOpacity();
    }

    public int[] getState() {
        return this.icon.getState();
    }

    public void invalidateDrawable(Drawable arg1) {
        this.invalidateSelf();
    }

    public boolean isStateful() {
        return this.icon.isStateful();
    }

    public void jumpToCurrentState() {
        this.icon.jumpToCurrentState();
    }

    protected boolean onStateChange(int[] arg1) {
        return 1;
    }

    public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        this.scheduleSelf(arg2, arg3);
    }

    public void setAlpha(int arg2) {
        this.icon.setAlpha(arg2);
        this.background.setAlpha(arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        this.icon.setColorFilter(arg2);
    }

    public void setCustomSize(int arg1, int arg2) {
        this.backWidth = arg1;
        this.backHeight = arg2;
    }

    public void setFullsize(boolean arg1) {
        this.fullSize = arg1;
    }

    public void setIconOffset(int arg1, int arg2) {
        this.offsetX = arg1;
        this.offsetY = arg2;
    }

    public void setIconSize(int arg1, int arg2) {
        this.iconWidth = arg1;
        this.iconHeight = arg2;
    }

    public boolean setState(int[] arg2) {
        this.icon.setState(arg2);
        return 1;
    }

    public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        this.unscheduleSelf(arg2);
    }
}

