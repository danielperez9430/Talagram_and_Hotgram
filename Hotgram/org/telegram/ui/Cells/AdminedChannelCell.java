package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.URLSpanNoUnderline;

public class AdminedChannelCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private int currentAccount;
    private Chat currentChannel;
    private ImageView deleteButton;
    private boolean isLast;
    private SimpleTextView nameTextView;
    private SimpleTextView statusTextView;

    public AdminedChannelCell(Context arg17, View$OnClickListener arg18) {
        AdminedChannelCell v0 = this;
        Context v1 = arg17;
        super(arg17);
        v0.currentAccount = UserConfig.selectedAccount;
        v0.avatarDrawable = new AvatarDrawable();
        v0.avatarImageView = new BackupImageView(v1);
        v0.avatarImageView.setRoundRadius(AndroidUtilities.dp(24f));
        BackupImageView v2 = v0.avatarImageView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 48;
        float v9 = LocaleController.isRTL ? 0f : 12f;
        float v10 = 12f;
        float v11 = LocaleController.isRTL ? 12f : 0f;
        v0.addView(((View)v2), LayoutHelper.createFrame(48, 48f, v8, v9, v10, v11, 0f));
        v0.nameTextView = new SimpleTextView(v1);
        v0.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.nameTextView.setTextSize(17);
        SimpleTextView v2_1 = v0.nameTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.nameTextView;
        int v6 = -1;
        float v7 = 20f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v8 = v3 | 48;
        v9 = LocaleController.isRTL ? 62f : 73f;
        v10 = 15.5f;
        v11 = LocaleController.isRTL ? 73f : 62f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        v0.statusTextView = new SimpleTextView(v1);
        v0.statusTextView.setTextSize(14);
        v0.statusTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText"));
        v0.statusTextView.setLinkTextColor(Theme.getColor("windowBackgroundWhiteLinkText"));
        v2_1 = v0.statusTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.statusTextView;
        v6 = -1;
        v7 = 20f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v8 = v3 | 48;
        v9 = LocaleController.isRTL ? 62f : 73f;
        v10 = 38.5f;
        v11 = LocaleController.isRTL ? 73f : 62f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        v0.deleteButton = new ImageView(v1);
        v0.deleteButton.setScaleType(ImageView$ScaleType.CENTER);
        v0.deleteButton.setImageResource(2131231384);
        v0.deleteButton.setOnClickListener(arg18);
        v0.deleteButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayText"), PorterDuff$Mode.MULTIPLY));
        ImageView v1_1 = v0.deleteButton;
        v6 = 48;
        v7 = 48f;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 5;
        }

        v8 = v4 | 48;
        v9 = LocaleController.isRTL ? 7f : 0f;
        v10 = 12f;
        v11 = LocaleController.isRTL ? 0f : 7f;
        v0.addView(((View)v1_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
    }

    public Chat getCurrentChannel() {
        return this.currentChannel;
    }

    public ImageView getDeleteButton() {
        return this.deleteButton;
    }

    public SimpleTextView getNameTextView() {
        return this.nameTextView;
    }

    public SimpleTextView getStatusTextView() {
        return this.statusTextView;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    protected void onMeasure(int arg2, int arg3) {
        arg3 = 1073741824;
        arg2 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), arg3);
        int v0 = this.isLast ? 12 : 0;
        super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(((float)(v0 + 60))), arg3));
    }

    public void setChannel(Chat arg6, boolean arg7) {
        FileLocation v0;
        if(arg6.photo != null) {
            v0 = arg6.photo.photo_small;
        }
        else {
            TLObject v0_1 = null;
        }

        String v1_1 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/";
        this.currentChannel = arg6;
        this.avatarDrawable.setInfo(arg6);
        this.nameTextView.setText(arg6.title);
        StringBuilder v3 = new StringBuilder();
        v3.append(v1_1);
        v3.append(arg6.username);
        SpannableStringBuilder v2 = new SpannableStringBuilder(v3.toString());
        v2.setSpan(new URLSpanNoUnderline(""), v1_1.length(), v2.length(), 33);
        this.statusTextView.setText(((CharSequence)v2));
        this.avatarImageView.setImage(((TLObject)v0), "50_50", this.avatarDrawable);
        this.isLast = arg7;
    }

    public void update() {
        this.avatarDrawable.setInfo(this.currentChannel);
        this.avatarImageView.invalidate();
    }
}

