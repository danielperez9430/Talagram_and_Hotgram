package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class StickerSetGroupInfoCell extends LinearLayout {
    private TextView addButton;
    private boolean isLast;

    public StickerSetGroupInfoCell(Context arg12) {
        super(arg12);
        this.setOrientation(1);
        TextView v1 = new TextView(arg12);
        v1.setTextColor(Theme.getColor("chat_emojiPanelTrendingDescription"));
        v1.setTextSize(1, 14f);
        v1.setText(LocaleController.getString("GroupStickersInfo", 2131624915));
        this.addView(((View)v1), LayoutHelper.createLinear(-1, -2, 51, 17, 4, 17, 0));
        this.addButton = new TextView(arg12);
        this.addButton.setPadding(AndroidUtilities.dp(17f), 0, AndroidUtilities.dp(17f), 0);
        this.addButton.setGravity(17);
        this.addButton.setTextColor(Theme.getColor("featuredStickers_buttonText"));
        this.addButton.setTextSize(1, 14f);
        this.addButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addButton.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4f), Theme.getColor("featuredStickers_addButton"), Theme.getColor("featuredStickers_addButtonPressed")));
        this.addButton.setText(LocaleController.getString("ChooseStickerSet", 2131624416).toUpperCase());
        this.addView(this.addButton, LayoutHelper.createLinear(-2, 28, 51, 17, 10, 14, 8));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), arg3);
        if(this.isLast) {
            ViewParent v2 = this.getParent();
            if(v2 != null) {
                arg3 = ((View)v2).getMeasuredHeight() - ((View)v2).getPaddingBottom() - ((View)v2).getPaddingTop() - AndroidUtilities.dp(24f);
                if(this.getMeasuredHeight() < arg3) {
                    this.setMeasuredDimension(this.getMeasuredWidth(), arg3);
                }
            }
        }
    }

    public void setAddOnClickListener(View$OnClickListener arg2) {
        this.addButton.setOnClickListener(arg2);
    }

    public void setIsLast(boolean arg1) {
        this.isLast = arg1;
        this.requestLayout();
    }
}

