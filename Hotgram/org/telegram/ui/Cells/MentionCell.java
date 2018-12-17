package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiSuggestion;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class MentionCell extends LinearLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView imageView;
    private TextView nameTextView;
    private TextView usernameTextView;

    public MentionCell(Context arg12) {
        super(arg12);
        this.setOrientation(0);
        this.avatarDrawable = new AvatarDrawable();
        this.avatarDrawable.setTextSize(AndroidUtilities.dp(12f));
        this.imageView = new BackupImageView(arg12);
        this.imageView.setRoundRadius(AndroidUtilities.dp(14f));
        this.addView(this.imageView, LayoutHelper.createLinear(28, 28, 12f, 4f, 0f, 0f));
        this.nameTextView = new TextView(arg12);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTextSize(1, 15f);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setGravity(3);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createLinear(-2, -2, 16, 12, 0, 0, 0));
        this.usernameTextView = new TextView(arg12);
        this.usernameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        this.usernameTextView.setTextSize(1, 15f);
        this.usernameTextView.setSingleLine(true);
        this.usernameTextView.setGravity(3);
        this.usernameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.usernameTextView, LayoutHelper.createLinear(-2, -2, 16, 12, 0, 8, 0));
    }

    public void invalidate() {
        super.invalidate();
        this.nameTextView.invalidate();
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36f), 1073741824));
    }

    public void setBotCommand(String arg5, String arg6, User arg7) {
        if(arg7 != null) {
            this.imageView.setVisibility(0);
            this.avatarDrawable.setInfo(arg7);
            if(arg7.photo != null && arg7.photo.photo_small != null) {
                this.imageView.setImage(arg7.photo.photo_small, "50_50", this.avatarDrawable);
                goto label_25;
            }

            this.imageView.setImageDrawable(this.avatarDrawable);
        }
        else {
            this.imageView.setVisibility(4);
        }

    label_25:
        this.usernameTextView.setVisibility(0);
        this.nameTextView.setText(((CharSequence)arg5));
        this.usernameTextView.setText(Emoji.replaceEmoji(((CharSequence)arg6), this.usernameTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(20f), false));
    }

    public void setEmojiSuggestion(EmojiSuggestion arg5) {
        this.imageView.setVisibility(4);
        this.usernameTextView.setVisibility(4);
        StringBuilder v0 = new StringBuilder(arg5.emoji.length() + arg5.label.length() + 3);
        v0.append(arg5.emoji);
        v0.append("   ");
        v0.append(arg5.label);
        this.nameTextView.setText(Emoji.replaceEmoji(((CharSequence)v0), this.nameTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(20f), false));
    }

    public void setIsDarkTheme(boolean arg2) {
        int v0;
        TextView v2;
        if(arg2) {
            this.nameTextView.setTextColor(-1);
            v2 = this.usernameTextView;
            v0 = -4473925;
        }
        else {
            this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            v2 = this.usernameTextView;
            v0 = Theme.getColor("windowBackgroundWhiteGrayText3");
        }

        v2.setTextColor(v0);
    }

    public void setText(String arg3) {
        this.imageView.setVisibility(4);
        this.usernameTextView.setVisibility(4);
        this.nameTextView.setText(((CharSequence)arg3));
    }

    public void setUser(User arg5) {
        if(arg5 == null) {
            this.nameTextView.setText("");
            this.usernameTextView.setText("");
            this.imageView.setImageDrawable(null);
            return;
        }

        this.avatarDrawable.setInfo(arg5);
        if(arg5.photo == null || arg5.photo.photo_small == null) {
            this.imageView.setImageDrawable(this.avatarDrawable);
        }
        else {
            this.imageView.setImage(arg5.photo.photo_small, "50_50", this.avatarDrawable);
        }

        this.nameTextView.setText(UserObject.getUserName(arg5));
        if(arg5.username != null) {
            TextView v0 = this.usernameTextView;
            v0.setText("@" + arg5.username);
        }
        else {
            this.usernameTextView.setText("");
        }

        this.imageView.setVisibility(0);
        this.usernameTextView.setVisibility(0);
    }
}

