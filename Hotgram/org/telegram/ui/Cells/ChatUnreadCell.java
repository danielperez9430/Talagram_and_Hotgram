package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class ChatUnreadCell extends FrameLayout {
    private FrameLayout backgroundLayout;
    private ImageView imageView;
    private TextView textView;

    public ChatUnreadCell(Context arg11) {
        super(arg11);
        this.backgroundLayout = new FrameLayout(arg11);
        this.backgroundLayout.setBackgroundResource(2131231405);
        this.backgroundLayout.getBackground().setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_unreadMessagesStartBackground"), PorterDuff$Mode.MULTIPLY));
        this.addView(this.backgroundLayout, LayoutHelper.createFrame(-1, 27f, 51, 0f, 7f, 0f, 0f));
        this.imageView = new ImageView(arg11);
        this.imageView.setImageResource(2131231111);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_unreadMessagesStartArrowIcon"), PorterDuff$Mode.MULTIPLY));
        this.imageView.setPadding(0, AndroidUtilities.dp(2f), 0, 0);
        this.backgroundLayout.addView(this.imageView, LayoutHelper.createFrame(-2, -2f, 21, 0f, 0f, 10f, 0f));
        this.textView = new TextView(arg11);
        this.textView.setPadding(0, 0, 0, AndroidUtilities.dp(1f));
        this.textView.setTextSize(1, 14f);
        this.textView.setTextColor(Theme.getColor("chat_unreadMessagesStartText"));
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
    }

    public FrameLayout getBackgroundLayout() {
        return this.backgroundLayout;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public TextView getTextView() {
        return this.textView;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40f), 1073741824));
    }

    public void setText(String arg2) {
        this.textView.setText(((CharSequence)arg2));
    }
}

