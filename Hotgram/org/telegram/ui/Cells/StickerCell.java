package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class StickerCell extends FrameLayout {
    private BackupImageView imageView;
    private static AccelerateInterpolator interpolator;
    private long lastUpdateTime;
    private float scale;
    private boolean scaled;
    private Document sticker;
    private long time;

    static {
        StickerCell.interpolator = new AccelerateInterpolator(0.5f);
    }

    public StickerCell(Context arg8) {
        super(arg8);
        this.time = 0;
        this.imageView = new BackupImageView(arg8);
        this.imageView.setAspectFit(true);
        this.addView(this.imageView, LayoutHelper.createFrame(66, 66f, 1, 0f, 5f, 0f, 0f));
    }

    protected boolean drawChild(Canvas arg5, View arg6, long arg7) {
        boolean v5 = super.drawChild(arg5, arg6, arg7);
        if(arg6 == this.imageView) {
            float v7 = 1f;
            float v8 = 0.8f;
            if(!this.scaled || this.scale == v8) {
                if(this.scaled) {
                }
                else if(this.scale != v7) {
                    goto label_13;
                }

                return v5;
            }

        label_13:
            long v0 = System.currentTimeMillis();
            long v2 = v0 - this.lastUpdateTime;
            this.lastUpdateTime = v0;
            float v0_1 = 400f;
            if(!this.scaled || this.scale == v8) {
                this.scale += (((float)v2)) / v0_1;
                if(this.scale > v7) {
                    this.scale = v7;
                }
            }
            else {
                this.scale -= (((float)v2)) / v0_1;
                if(this.scale < v8) {
                    this.scale = v8;
                }
            }

            this.imageView.setScaleX(this.scale);
            this.imageView.setScaleY(this.scale);
            this.imageView.invalidate();
            this.invalidate();
        }

        return v5;
    }

    public Document getSticker() {
        return this.sticker;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(76f) + this.getPaddingLeft() + this.getPaddingRight(), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(78f), 1073741824));
    }

    public void setPressed(boolean arg2) {
        if(this.imageView.getImageReceiver().getPressed() != arg2) {
            this.imageView.getImageReceiver().setPressed(((int)arg2));
            this.imageView.invalidate();
        }

        super.setPressed(arg2);
    }

    public void setScaled(boolean arg3) {
        this.scaled = arg3;
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public void setSticker(Document arg5, int arg6) {
        if(arg5 != null && arg5.thumb != null) {
            this.imageView.setImage(arg5.thumb.location, null, "webp", null);
        }

        this.sticker = arg5;
        float v0 = 7f;
        if(arg6 == -1) {
            this.setBackgroundResource(2131231578);
            this.setPadding(AndroidUtilities.dp(v0), 0, 0, 0);
        }
        else if(arg6 == 0) {
            this.setBackgroundResource(2131231577);
            this.setPadding(0, 0, 0, 0);
        }
        else if(arg6 == 1) {
            this.setBackgroundResource(2131231579);
            this.setPadding(0, 0, AndroidUtilities.dp(v0), 0);
        }
        else if(arg6 == 2) {
            this.setBackgroundResource(2131231575);
            this.setPadding(AndroidUtilities.dp(3f), 0, AndroidUtilities.dp(3f), 0);
        }

        Drawable v5 = this.getBackground();
        if(v5 != null) {
            v5.setAlpha(230);
            v5.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_stickersHintPanel"), PorterDuff$Mode.MULTIPLY));
        }
    }

    public boolean showingBitmap() {
        boolean v0 = this.imageView.getImageReceiver().getBitmap() != null ? true : false;
        return v0;
    }
}

