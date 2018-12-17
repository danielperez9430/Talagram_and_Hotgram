package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC$TL_messageMediaVenue;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class LocationCell extends FrameLayout {
    private TextView addressTextView;
    private BackupImageView imageView;
    private TextView nameTextView;
    private boolean needDivider;

    public LocationCell(Context arg15) {
        super(arg15);
        this.imageView = new BackupImageView(arg15);
        this.imageView.setBackgroundResource(2131231525);
        this.imageView.setSize(AndroidUtilities.dp(30f), AndroidUtilities.dp(30f));
        this.imageView.getImageReceiver().setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayText3"), PorterDuff$Mode.MULTIPLY));
        BackupImageView v0 = this.imageView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        int v6 = v1 | 48;
        float v7 = LocaleController.isRTL ? 0f : 17f;
        float v8 = 8f;
        float v9 = LocaleController.isRTL ? 17f : 0f;
        this.addView(((View)v0), LayoutHelper.createFrame(40, 40f, v6, v7, v8, v9, 0f));
        this.nameTextView = new TextView(arg15);
        this.nameTextView.setTextSize(1, 16f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        TextView v0_1 = this.nameTextView;
        v1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v1);
        v0_1 = this.nameTextView;
        int v5 = -2;
        float v6_1 = -2f;
        v1 = LocaleController.isRTL ? 5 : 3;
        int v7_1 = v1 | 48;
        int v12 = 72;
        v1 = LocaleController.isRTL ? 16 : 72;
        v8 = ((float)v1);
        v9 = 5f;
        v1 = LocaleController.isRTL ? 72 : 16;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6_1, v7_1, v8, v9, ((float)v1), 0f));
        this.addressTextView = new TextView(arg15);
        this.addressTextView.setTextSize(1, 14f);
        this.addressTextView.setMaxLines(1);
        this.addressTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addressTextView.setSingleLine(true);
        this.addressTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        TextView v15 = this.addressTextView;
        int v0_2 = LocaleController.isRTL ? 5 : 3;
        v15.setGravity(v0_2);
        v15 = this.addressTextView;
        int v4 = -2;
        float v5_1 = -2f;
        if(LocaleController.isRTL) {
            v2 = 5;
        }

        v6 = v2 | 48;
        v0_2 = LocaleController.isRTL ? 16 : 72;
        v7 = ((float)v0_2);
        v8 = 30f;
        if(LocaleController.isRTL) {
        }
        else {
            v12 = 16;
        }

        this.addView(((View)v15), LayoutHelper.createFrame(v4, v5_1, v6, v7, v8, ((float)v12), 0f));
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)AndroidUtilities.dp(72f)), ((float)(this.getHeight() - 1)), ((float)this.getWidth()), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56f) + this.needDivider, 1073741824));
    }

    public void setLocation(TL_messageMediaVenue arg3, String arg4, boolean arg5) {
        this.needDivider = arg5;
        this.nameTextView.setText(arg3.title);
        this.addressTextView.setText(arg3.address);
        this.imageView.setImage(arg4, null, null);
        this.setWillNotDraw((((int)arg5)) ^ 1);
    }
}

