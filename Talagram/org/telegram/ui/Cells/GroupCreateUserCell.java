package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.LayoutHelper;

public class GroupCreateUserCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private GroupCreateCheckBox checkBox;
    private int currentAccount;
    private CharSequence currentName;
    private CharSequence currentStatus;
    private User currentUser;
    private FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private SimpleTextView nameTextView;
    private SimpleTextView statusTextView;

    public GroupCreateUserCell(Context arg17, boolean arg18) {
        GroupCreateUserCell v0 = this;
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

    public User getUser() {
        return this.currentUser;
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

    public void setUser(User arg1, CharSequence arg2, CharSequence arg3) {
        this.currentUser = arg1;
        this.currentStatus = arg3;
        this.currentName = arg2;
        this.update(0);
    }

    public void update(int arg10) {
        SimpleTextView v10_1;
        String v10;
        int v4;
        if(this.currentUser == null) {
            return;
        }

        String v1 = null;
        FileLocation v0 = this.currentUser.photo != null ? this.currentUser.photo.photo_small : ((FileLocation)v1);
        int v2 = 0;
        if(arg10 != 0) {
            if((arg10 & 2) != 0) {
                if(this.lastAvatar == null || v0 != null) {
                    if(this.lastAvatar != null) {
                        goto label_36;
                    }
                    else if(v0 == null) {
                        goto label_36;
                    }
                    else if(this.lastAvatar == null) {
                        goto label_36;
                    }
                    else if(v0 == null) {
                        goto label_36;
                    }
                    else if(this.lastAvatar.volume_id == v0.volume_id) {
                        if(this.lastAvatar.local_id != v0.local_id) {
                            goto label_34;
                        }

                        goto label_36;
                    }
                }

            label_34:
                v4 = 1;
            }
            else {
            label_36:
                v4 = 0;
            }

            if(this.currentUser != null && this.currentStatus == null && v4 == 0 && (arg10 & 4) != 0) {
                int v5 = this.currentUser.status != null ? this.currentUser.status.expires : 0;
                if(v5 == this.lastStatus) {
                    goto label_55;
                }

                v4 = 1;
            }

        label_55:
            if(v4 != 0 || this.currentName != null || this.lastName == null || (arg10 & 1) == 0) {
                v10 = v1;
            }
            else {
                v10 = UserObject.getUserName(this.currentUser);
                if(!v10.equals(this.lastName)) {
                    v4 = 1;
                }
            }

            if(v4 != 0) {
                goto label_73;
            }

            return;
        }
        else {
            v10 = v1;
        }

    label_73:
        this.avatarDrawable.setInfo(this.currentUser);
        if(this.currentUser.status != null) {
            v2 = this.currentUser.status.expires;
        }

        this.lastStatus = v2;
        if(this.currentName != null) {
            this.lastName = v1;
            this.nameTextView.setText(this.currentName, true);
        }
        else {
            if(v10 == null) {
                v10 = UserObject.getUserName(this.currentUser);
            }

            this.lastName = v10;
            this.nameTextView.setText(this.lastName);
        }

        if(this.currentStatus != null) {
            this.statusTextView.setText(this.currentStatus, true);
            this.statusTextView.setTag("groupcreate_offlineText");
            this.statusTextView.setTextColor(Theme.getColor("groupcreate_offlineText"));
        }
        else {
            if(this.currentUser.bot) {
                this.statusTextView.setTag("groupcreate_offlineText");
                this.statusTextView.setTextColor(Theme.getColor("groupcreate_offlineText"));
                v10_1 = this.statusTextView;
                v1 = "Bot";
                v2 = 2131624212;
                goto label_123;
            }
            else {
                if(this.currentUser.id != UserConfig.getInstance(this.currentAccount).getClientUserId() && (this.currentUser.status == null || this.currentUser.status.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) && !MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Integer.valueOf(this.currentUser.id))) {
                    this.statusTextView.setTag("groupcreate_offlineText");
                    this.statusTextView.setTextColor(Theme.getColor("groupcreate_offlineText"));
                    v10_1 = this.statusTextView;
                    v1 = LocaleController.formatUserStatus(this.currentAccount, this.currentUser);
                    goto label_124;
                }

                this.statusTextView.setTag("groupcreate_offlineText");
                this.statusTextView.setTextColor(Theme.getColor("groupcreate_onlineText"));
                v10_1 = this.statusTextView;
                v1 = "Online";
                v2 = 2131625428;
            label_123:
                v1 = LocaleController.getString(v1, v2);
            }

        label_124:
            v10_1.setText(((CharSequence)v1));
        }

        this.avatarImageView.setImage(((TLObject)v0), "50_50", this.avatarDrawable);
    }
}

