package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

public class BrightnessControlCell extends FrameLayout {
    private ImageView leftImageView;
    private ImageView rightImageView;
    private SeekBarView seekBarView;

    public BrightnessControlCell(Context arg9) {
        super(arg9);
        this.leftImageView = new ImageView(arg9);
        this.leftImageView.setImageResource(2131230958);
        this.addView(this.leftImageView, LayoutHelper.createFrame(24, 24f, 51, 17f, 12f, 0f, 0f));
        this.seekBarView = new SeekBarView(arg9) {
            public boolean onTouchEvent(MotionEvent arg3) {
                if(arg3.getAction() == 0) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                }

                return super.onTouchEvent(arg3);
            }
        };
        this.seekBarView.setReportChanges(true);
        this.seekBarView.setDelegate(new -$$Lambda$GN4ZDAm3ZJLTBxjR6_2pFIyDFuo(this));
        this.addView(this.seekBarView, LayoutHelper.createFrame(-1, 30f, 51, 58f, 9f, 58f, 0f));
        this.rightImageView = new ImageView(arg9);
        this.rightImageView.setImageResource(2131230957);
        this.addView(this.rightImageView, LayoutHelper.createFrame(24, 24f, 53, 0f, 12f, 17f, 0f));
    }

    protected void didChangedValue(float arg1) {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.leftImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("profile_actionIcon"), PorterDuff$Mode.MULTIPLY));
        this.rightImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("profile_actionIcon"), PorterDuff$Mode.MULTIPLY));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
    }

    public void setProgress(float arg2) {
        this.seekBarView.setProgress(arg2);
    }
}

