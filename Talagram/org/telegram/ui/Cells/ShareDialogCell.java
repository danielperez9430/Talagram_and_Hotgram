package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;

public class ShareDialogCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private CheckBox checkBox;
    private int currentAccount;
    private BackupImageView imageView;
    private TextView nameTextView;

    public ShareDialogCell(Context arg9) {
        super(arg9);
        this.avatarDrawable = new AvatarDrawable();
        this.currentAccount = UserConfig.selectedAccount;
        this.imageView = new BackupImageView(arg9);
        this.imageView.setRoundRadius(AndroidUtilities.dp(27f));
        this.addView(this.imageView, LayoutHelper.createFrame(54, 54f, 49, 0f, 7f, 0f, 0f));
        this.nameTextView = new TextView(arg9);
        this.nameTextView.setTextColor(Theme.getColor("dialogTextBlack"));
        this.nameTextView.setTextSize(1, 12f);
        this.nameTextView.setMaxLines(2);
        this.nameTextView.setGravity(49);
        this.nameTextView.setLines(2);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createFrame(-1, -2f, 51, 6f, 64f, 6f, 0f));
        this.checkBox = new CheckBox(arg9, 2131231523);
        this.checkBox.setSize(24);
        this.checkBox.setCheckOffset(AndroidUtilities.dp(1f));
        this.checkBox.setVisibility(0);
        this.checkBox.setColor(Theme.getColor("dialogRoundCheckBox"), Theme.getColor("dialogRoundCheckBoxCheck"));
        this.addView(this.checkBox, LayoutHelper.createFrame(24, 24f, 49, 17f, 39f, 0f, 0f));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100f), 1073741824));
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.checkBox.setChecked(arg2, arg3);
    }

    public void setDialog(int arg4, boolean arg5, CharSequence arg6) {
        FileLocation v0_1;
        String v1;
        TextView v6;
        TLObject v0 = null;
        if(arg4 > 0) {
            User v4 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(arg4));
            this.avatarDrawable.setInfo(v4);
            if(UserObject.isUserSelf(v4)) {
                this.nameTextView.setText(LocaleController.getString("SavedMessages", 2131625950));
                this.avatarDrawable.setSavedMessages(1);
            }
            else {
                if(arg6 != null) {
                    this.nameTextView.setText(arg6);
                }
                else {
                    if(v4 != null) {
                        v6 = this.nameTextView;
                        v1 = ContactsController.formatName(v4.first_name, v4.last_name);
                    }
                    else {
                        v6 = this.nameTextView;
                        v1 = "";
                    }

                    v6.setText(((CharSequence)v1));
                }

                if(v4 == null) {
                    goto label_64;
                }

                if(v4.photo == null) {
                    goto label_64;
                }

                v0_1 = v4.photo.photo_small;
            }
        }
        else {
            Chat v4_1 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-arg4));
            if(arg6 != null) {
                this.nameTextView.setText(arg6);
            }
            else {
                if(v4_1 != null) {
                    v6 = this.nameTextView;
                    v1 = v4_1.title;
                }
                else {
                    v6 = this.nameTextView;
                    v1 = "";
                }

                v6.setText(((CharSequence)v1));
            }

            this.avatarDrawable.setInfo(v4_1);
            if(v4_1 == null) {
                goto label_64;
            }

            if(v4_1.photo == null) {
                goto label_64;
            }

            v0_1 = v4_1.photo.photo_small;
        }

    label_64:
        this.imageView.setImage(((TLObject)v0_1), "50_50", this.avatarDrawable);
        this.checkBox.setChecked(arg5, false);
    }
}

