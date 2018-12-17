package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint(value={"NewApi"}) public class PhotoAttachCameraCell extends FrameLayout {
    private ImageView imageView;

    public PhotoAttachCameraCell(Context arg3) {
        super(arg3);
        this.imageView = new ImageView(arg3);
        this.imageView.setScaleType(ImageView$ScaleType.CENTER);
        this.imageView.setImageResource(2131231271);
        this.imageView.setBackgroundColor(-16777216);
        this.addView(this.imageView, LayoutHelper.createFrame(80, 80f));
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("dialogCameraIcon"), PorterDuff$Mode.MULTIPLY));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(86f), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80f), 1073741824));
    }
}

