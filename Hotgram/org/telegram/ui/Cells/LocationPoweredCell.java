package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class LocationPoweredCell extends FrameLayout {
    private ImageView imageView;
    private TextView textView;
    private TextView textView2;

    public LocationPoweredCell(Context arg9) {
        super(arg9);
        LinearLayout v0 = new LinearLayout(arg9);
        this.addView(((View)v0), LayoutHelper.createFrame(-2, -2, 17));
        this.textView = new TextView(arg9);
        this.textView.setTextSize(1, 16f);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        this.textView.setText("Powered by");
        v0.addView(this.textView, LayoutHelper.createLinear(-2, -2));
        this.imageView = new ImageView(arg9);
        this.imageView.setImageResource(2131231077);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayText3"), PorterDuff$Mode.MULTIPLY));
        this.imageView.setPadding(0, AndroidUtilities.dp(2f), 0, 0);
        v0.addView(this.imageView, LayoutHelper.createLinear(35, -2));
        this.textView2 = new TextView(arg9);
        this.textView2.setTextSize(1, 16f);
        this.textView2.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        this.textView2.setText("Foursquare");
        v0.addView(this.textView2, LayoutHelper.createLinear(-2, -2));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56f), 1073741824));
    }
}

