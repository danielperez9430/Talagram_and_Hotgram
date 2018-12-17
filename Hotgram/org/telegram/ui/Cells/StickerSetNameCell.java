package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils$TruncateAt;
import android.text.style.ForegroundColorSpan;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColorSpanUnderline;
import org.telegram.ui.Components.LayoutHelper;

public class StickerSetNameCell extends FrameLayout {
    private ImageView buttonView;
    private boolean empty;
    private TextView textView;
    private TextView urlTextView;

    public StickerSetNameCell(Context arg10) {
        super(arg10);
        this.textView = new TextView(arg10);
        this.textView.setTextColor(Theme.getColor("chat_emojiPanelStickerSetName"));
        this.textView.setTextSize(1, 14f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        this.textView.setSingleLine(true);
        this.addView(this.textView, LayoutHelper.createFrame(-2, -2f, 51, 17f, 4f, 57f, 0f));
        this.urlTextView = new TextView(arg10);
        this.urlTextView.setTextColor(Theme.getColor("chat_emojiPanelStickerSetName"));
        this.urlTextView.setTextSize(1, 12f);
        this.urlTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.urlTextView.setSingleLine(true);
        this.urlTextView.setVisibility(4);
        this.addView(this.urlTextView, LayoutHelper.createFrame(-2, -2f, 53, 17f, 6f, 17f, 0f));
        this.buttonView = new ImageView(arg10);
        this.buttonView.setScaleType(ImageView$ScaleType.CENTER);
        this.buttonView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_emojiPanelStickerSetNameIcon"), PorterDuff$Mode.MULTIPLY));
        this.addView(this.buttonView, LayoutHelper.createFrame(24, 24f, 53, 0f, 0f, 16f, 0f));
    }

    public void invalidate() {
        this.textView.invalidate();
        super.invalidate();
    }

    protected void onMeasure(int arg2, int arg3) {
        int v0 = 1073741824;
        if(this.empty) {
            arg2 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), v0);
            arg3 = 1;
        }
        else {
            arg2 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), v0);
            arg3 = AndroidUtilities.dp(24f);
        }

        super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(arg3, v0));
    }

    public void setOnIconClickListener(View$OnClickListener arg2) {
        this.buttonView.setOnClickListener(arg2);
    }

    public void setText(CharSequence arg2, int arg3) {
        this.setText(arg2, arg3, 0, 0);
    }

    public void setText(CharSequence arg5, int arg6, int arg7, int arg8) {
        int v0 = 4;
        if(arg5 == null) {
            this.empty = true;
            this.textView.setText("");
            goto label_7;
        }
        else {
            if(arg8 != 0) {
                SpannableStringBuilder v2 = new SpannableStringBuilder(arg5);
                try {
                    v2.setSpan(new ForegroundColorSpan(Theme.getColor("windowBackgroundWhiteBlueText4")), arg7, arg8 + arg7, 33);
                    goto label_21;
                }
                catch(Exception ) {
                label_21:
                    this.textView.setText(((CharSequence)v2));
                }
            }
            else {
                this.textView.setText(Emoji.replaceEmoji(arg5, this.textView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(14f), false));
            }

            if(arg6 != 0) {
                this.buttonView.setImageResource(arg6);
                this.buttonView.setVisibility(0);
                return;
            }

        label_7:
            this.buttonView.setVisibility(v0);
        }
    }

    public void setUrl(CharSequence arg6, int arg7) {
        if(arg6 != null) {
            SpannableStringBuilder v0 = new SpannableStringBuilder(arg6);
            try {
                v0.setSpan(new ColorSpanUnderline(Theme.getColor("windowBackgroundWhiteBlueText4")), 0, arg7, 33);
                v0.setSpan(new ColorSpanUnderline(Theme.getColor("chat_emojiPanelStickerSetName")), arg7, arg6.length(), 33);
                goto label_16;
            }
            catch(Exception ) {
            label_16:
                this.urlTextView.setText(((CharSequence)v0));
                this.urlTextView.setVisibility(0);
            }
        }
        else {
            this.urlTextView.setVisibility(8);
        }
    }
}

