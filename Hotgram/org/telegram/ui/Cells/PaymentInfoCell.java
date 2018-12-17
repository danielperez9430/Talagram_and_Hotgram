package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC$TL_messageMediaInvoice;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class PaymentInfoCell extends FrameLayout {
    private TextView detailExTextView;
    private TextView detailTextView;
    private BackupImageView imageView;
    private TextView nameTextView;

    public PaymentInfoCell(Context arg15) {
        super(arg15);
        this.imageView = new BackupImageView(arg15);
        BackupImageView v0 = this.imageView;
        int v2 = 5;
        int v3 = 3;
        int v6 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0), LayoutHelper.createFrame(100, 100f, v6, 10f, 10f, 10f, 0f));
        this.nameTextView = new TextView(arg15);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTextSize(1, 16f);
        this.nameTextView.setLines(1);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0_1 = this.nameTextView;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v1 | 48);
        v0_1 = this.nameTextView;
        int v5 = -1;
        float v6_1 = -2f;
        v1 = LocaleController.isRTL ? 5 : 3;
        int v7 = v1 | 48;
        float v8 = LocaleController.isRTL ? 10f : 123f;
        float v9 = 9f;
        float v10 = LocaleController.isRTL ? 123f : 10f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6_1, v7, v8, v9, v10, 0f));
        this.detailTextView = new TextView(arg15);
        this.detailTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.detailTextView.setTextSize(1, 14f);
        this.detailTextView.setMaxLines(v3);
        this.detailTextView.setEllipsize(TextUtils$TruncateAt.END);
        v0_1 = this.detailTextView;
        v1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v1 | 48);
        v0_1 = this.detailTextView;
        v5 = -1;
        v6_1 = -2f;
        v1 = LocaleController.isRTL ? 5 : 3;
        v7 = v1 | 48;
        v8 = LocaleController.isRTL ? 10f : 123f;
        v9 = 33f;
        v10 = LocaleController.isRTL ? 123f : 10f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6_1, v7, v8, v9, v10, 0f));
        this.detailExTextView = new TextView(arg15);
        this.detailExTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.detailExTextView.setTextSize(1, 13f);
        this.detailExTextView.setLines(1);
        this.detailExTextView.setMaxLines(1);
        this.detailExTextView.setSingleLine(true);
        this.detailExTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v15 = this.detailExTextView;
        int v0_2 = LocaleController.isRTL ? 5 : 3;
        v15.setGravity(v0_2 | 48);
        v15 = this.detailExTextView;
        int v4 = -1;
        float v5_1 = -2f;
        if(LocaleController.isRTL) {
        }
        else {
            v2 = 3;
        }

        v6 = v2 | 48;
        float v7_1 = LocaleController.isRTL ? 10f : 123f;
        v8 = 90f;
        v9 = LocaleController.isRTL ? 123f : 10f;
        this.addView(((View)v15), LayoutHelper.createFrame(v4, v5_1, v6, v7_1, v8, v9, 0f));
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        int v1 = this.detailTextView.getBottom() + AndroidUtilities.dp(3f);
        this.detailExTextView.layout(this.detailExTextView.getLeft(), v1, this.detailExTextView.getRight(), this.detailExTextView.getMeasuredHeight() + v1);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120f), 1073741824));
    }

    public void setInvoice(TL_messageMediaInvoice arg14, String arg15) {
        float v5;
        int v4;
        this.nameTextView.setText(arg14.title);
        this.detailTextView.setText(arg14.description);
        this.detailExTextView.setText(((CharSequence)arg15));
        float v0 = 0.7f;
        int v15 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
        v15 = ((int)((((float)v15)) * v0));
        v0 = ((float)640);
        float v15_1 = v0 / (((float)(v15 - AndroidUtilities.dp(2f))));
        int v0_1 = ((int)(v0 / v15_1));
        v15 = ((int)((((float)360)) / v15_1));
        int v2 = 3;
        if(arg14.photo == null || !arg14.photo.mime_type.startsWith("image/")) {
            TextView v14 = this.nameTextView;
            v4 = -1;
            v5 = -2f;
            v15 = LocaleController.isRTL ? 5 : 3;
            v14.setLayoutParams(LayoutHelper.createFrame(v4, v5, v15 | 48, 17f, 9f, 17f, 0f));
            v14 = this.detailTextView;
            v4 = -1;
            v5 = -2f;
            v15 = LocaleController.isRTL ? 5 : 3;
            v14.setLayoutParams(LayoutHelper.createFrame(v4, v5, v15 | 48, 17f, 33f, 17f, 0f));
            v14 = this.detailExTextView;
            v4 = -1;
            v5 = -2f;
            if(LocaleController.isRTL) {
                v2 = 5;
            }

            v14.setLayoutParams(LayoutHelper.createFrame(v4, v5, v2 | 48, 17f, 90f, 17f, 0f));
            this.imageView.setVisibility(8);
        }
        else {
            TextView v1 = this.nameTextView;
            v4 = -1;
            v5 = -2f;
            int v6 = LocaleController.isRTL ? 5 : 3;
            v6 |= 48;
            float v7 = LocaleController.isRTL ? 10f : 123f;
            float v8 = 9f;
            float v9 = LocaleController.isRTL ? 123f : 10f;
            v1.setLayoutParams(LayoutHelper.createFrame(v4, v5, v6, v7, v8, v9, 0f));
            v1 = this.detailTextView;
            v4 = -1;
            v5 = -2f;
            v6 = LocaleController.isRTL ? 5 : 3;
            v6 |= 48;
            v7 = LocaleController.isRTL ? 10f : 123f;
            v8 = 33f;
            v9 = LocaleController.isRTL ? 123f : 10f;
            v1.setLayoutParams(LayoutHelper.createFrame(v4, v5, v6, v7, v8, v9, 0f));
            v1 = this.detailExTextView;
            v4 = -1;
            v5 = -2f;
            if(LocaleController.isRTL) {
                v2 = 5;
            }

            v6 = v2 | 48;
            v7 = LocaleController.isRTL ? 10f : 123f;
            v8 = 90f;
            v9 = LocaleController.isRTL ? 123f : 10f;
            v1.setLayoutParams(LayoutHelper.createFrame(v4, v5, v6, v7, v8, v9, 0f));
            this.imageView.setVisibility(0);
            this.imageView.getImageReceiver().setImage(arg14.photo, null, String.format(Locale.US, "%d_%d", Integer.valueOf(v0_1), Integer.valueOf(v15)), null, null, null, -1, null, 1);
        }
    }
}

