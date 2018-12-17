package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class ThemeTypeCell extends FrameLayout {
    private ImageView checkImage;
    private boolean needDivider;
    private TextView textView;

    public ThemeTypeCell(Context arg12) {
        super(arg12);
        this.setWillNotDraw(false);
        this.textView = new TextView(arg12);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0 = this.textView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1 | 16);
        v0 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        v1 = LocaleController.isRTL ? 5 : 3;
        int v6 = v1 | 48;
        float v1_1 = LocaleController.isRTL ? 71f : 17f;
        float v9 = LocaleController.isRTL ? 17f : 23f;
        this.addView(((View)v0), LayoutHelper.createFrame(v4, v5, v6, v1_1, 0f, v9, 0f));
        this.checkImage = new ImageView(arg12);
        this.checkImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
        this.checkImage.setImageResource(2131231573);
        ImageView v12 = this.checkImage;
        v4 = 19;
        v5 = 14f;
        if(LocaleController.isRTL) {
        }
        else {
            v2 = 5;
        }

        this.addView(((View)v12), LayoutHelper.createFrame(v4, v5, v2 | 16, 23f, 0f, 23f, 0f));
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setTypeChecked(boolean arg2) {
        ImageView v0 = this.checkImage;
        int v2 = arg2 ? 0 : 4;
        v0.setVisibility(v2);
    }

    public void setValue(String arg2, boolean arg3, boolean arg4) {
        this.textView.setText(((CharSequence)arg2));
        ImageView v2 = this.checkImage;
        int v3 = arg3 ? 0 : 4;
        v2.setVisibility(v3);
        this.needDivider = arg4;
    }
}

