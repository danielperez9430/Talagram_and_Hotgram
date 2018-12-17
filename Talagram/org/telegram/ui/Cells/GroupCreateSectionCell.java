package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class GroupCreateSectionCell extends FrameLayout {
    private Drawable drawable;
    private TextView textView;

    public GroupCreateSectionCell(Context arg11) {
        super(arg11);
        this.setBackgroundColor(Theme.getColor("graySection"));
        this.drawable = this.getResources().getDrawable(2131231553);
        this.drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor("groupcreate_sectionShadow"), PorterDuff$Mode.MULTIPLY));
        this.textView = new TextView(this.getContext());
        this.textView.setTextSize(1, 14f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setTextColor(Theme.getColor("groupcreate_sectionText"));
        TextView v11 = this.textView;
        int v1 = 3;
        int v0 = LocaleController.isRTL ? 5 : 3;
        v11.setGravity(v0 | 16);
        v11 = this.textView;
        int v3 = -1;
        float v4 = -1f;
        if(LocaleController.isRTL) {
            v1 = 5;
        }

        this.addView(((View)v11), LayoutHelper.createFrame(v3, v4, v1 | 48, 16f, 0f, 16f, 0f));
    }

    protected void onDraw(Canvas arg6) {
        this.drawable.setBounds(0, this.getMeasuredHeight() - AndroidUtilities.dp(3f), this.getMeasuredWidth(), this.getMeasuredHeight());
        this.drawable.draw(arg6);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40f), 1073741824));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }
}

