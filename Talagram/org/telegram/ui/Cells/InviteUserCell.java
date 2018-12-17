package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController$Contact;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.LayoutHelper;

public class InviteUserCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private GroupCreateCheckBox checkBox;
    private Contact currentContact;
    private CharSequence currentName;
    private SimpleTextView nameTextView;
    private SimpleTextView statusTextView;

    public InviteUserCell(Context arg17, boolean arg18) {
        InviteUserCell v0 = this;
        Context v1 = arg17;
        super(arg17);
        v0.avatarDrawable = new AvatarDrawable();
        v0.avatarImageView = new BackupImageView(v1);
        v0.avatarImageView.setRoundRadius(AndroidUtilities.dp(24f));
        BackupImageView v2 = v0.avatarImageView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 48;
        float v9 = LocaleController.isRTL ? 0f : 11f;
        float v10 = 11f;
        float v11 = LocaleController.isRTL ? 11f : 0f;
        v0.addView(((View)v2), LayoutHelper.createFrame(50, 50f, v8, v9, v10, v11, 0f));
        v0.nameTextView = new SimpleTextView(v1);
        v0.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.nameTextView.setTextSize(17);
        SimpleTextView v2_1 = v0.nameTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.nameTextView;
        int v6 = -1;
        float v7 = 20f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v8 = v3 | 48;
        v9 = LocaleController.isRTL ? 28f : 72f;
        v10 = 14f;
        v11 = LocaleController.isRTL ? 72f : 28f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        v0.statusTextView = new SimpleTextView(v1);
        v0.statusTextView.setTextSize(16);
        v2_1 = v0.statusTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.statusTextView;
        v6 = -1;
        v7 = 20f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v8 = v3 | 48;
        v9 = LocaleController.isRTL ? 28f : 72f;
        v10 = 39f;
        v11 = LocaleController.isRTL ? 72f : 28f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        if(arg18) {
            v0.checkBox = new GroupCreateCheckBox(v1);
            v0.checkBox.setVisibility(0);
            GroupCreateCheckBox v1_1 = v0.checkBox;
            v6 = 24;
            v7 = 24f;
            if(LocaleController.isRTL) {
                v4 = 5;
            }

            v8 = v4 | 48;
            v9 = LocaleController.isRTL ? 0f : 41f;
            v10 = 41f;
            v11 = LocaleController.isRTL ? 41f : 0f;
            v0.addView(((View)v1_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        }
    }

    public Contact getContact() {
        return this.currentContact;
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(72f), 1073741824));
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.checkBox.setChecked(arg2, arg3);
    }

    public void setUser(Contact arg1, CharSequence arg2) {
        this.currentContact = arg1;
        this.currentName = arg2;
        this.update(0);
    }

    public void update(int arg5) {
        String v0;
        SimpleTextView v5;
        if(this.currentContact == null) {
            return;
        }

        this.avatarDrawable.setInfo(this.currentContact.contact_id, this.currentContact.first_name, this.currentContact.last_name, false);
        if(this.currentName != null) {
            this.nameTextView.setText(this.currentName, true);
        }
        else {
            this.nameTextView.setText(ContactsController.formatName(this.currentContact.first_name, this.currentContact.last_name));
        }

        this.statusTextView.setTag("groupcreate_offlineText");
        this.statusTextView.setTextColor(Theme.getColor("groupcreate_offlineText"));
        if(this.currentContact.imported > 0) {
            v5 = this.statusTextView;
            v0 = LocaleController.formatPluralString("TelegramContacts", this.currentContact.imported);
        }
        else {
            v5 = this.statusTextView;
            Object v0_1 = this.currentContact.phones.get(0);
        }

        v5.setText(((CharSequence)v0));
        this.avatarImageView.setImageDrawable(this.avatarDrawable);
    }
}

