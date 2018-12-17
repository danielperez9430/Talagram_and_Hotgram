package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
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
import org.telegram.ui.Components.LayoutHelper;

public class ManageChatUserCell extends FrameLayout {
    public interface ManageChatUserCellDelegate {
        boolean onOptionsButtonCheck(ManageChatUserCell arg1, boolean arg2);
    }

    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private int currentAccount;
    private CharSequence currentName;
    private User currentUser;
    private CharSequence currrntStatus;
    private ManageChatUserCellDelegate delegate;
    private boolean isAdmin;
    private FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private SimpleTextView nameTextView;
    private ImageView optionsButton;
    private int statusColor;
    private int statusOnlineColor;
    private SimpleTextView statusTextView;

    public ManageChatUserCell(Context arg13, int arg14, boolean arg15) {
        super(arg13);
        this.currentAccount = UserConfig.selectedAccount;
        this.statusColor = Theme.getColor("windowBackgroundWhiteGrayText");
        this.statusOnlineColor = Theme.getColor("windowBackgroundWhiteBlueText");
        this.avatarDrawable = new AvatarDrawable();
        this.avatarImageView = new BackupImageView(arg13);
        this.avatarImageView.setRoundRadius(AndroidUtilities.dp(24f));
        BackupImageView v0 = this.avatarImageView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        int v4 = 48;
        int v7 = v1 | 48;
        float v8 = LocaleController.isRTL ? 0f : ((float)(arg14 + 7));
        float v9 = 8f;
        float v10 = LocaleController.isRTL ? ((float)(arg14 + 7)) : 0f;
        this.addView(((View)v0), LayoutHelper.createFrame(48, 48f, v7, v8, v9, v10, 0f));
        this.nameTextView = new SimpleTextView(arg13);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTextSize(17);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        SimpleTextView v0_1 = this.nameTextView;
        v1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v1 | v4);
        v0_1 = this.nameTextView;
        int v5 = -1;
        float v6 = 20f;
        v1 = LocaleController.isRTL ? 5 : 3;
        v7 = v1 | 48;
        float v1_1 = LocaleController.isRTL ? 46f : ((float)(arg14 + 68));
        v9 = 11.5f;
        v10 = LocaleController.isRTL ? ((float)(arg14 + 68)) : 46f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6, v7, v1_1, v9, v10, 0f));
        this.statusTextView = new SimpleTextView(arg13);
        this.statusTextView.setTextSize(14);
        v0_1 = this.statusTextView;
        v1 = LocaleController.isRTL ? 5 : 3;
        v0_1.setGravity(v1 | v4);
        v0_1 = this.statusTextView;
        v5 = -1;
        v6 = 20f;
        v1 = LocaleController.isRTL ? 5 : 3;
        v7 = v1 | 48;
        v1_1 = LocaleController.isRTL ? 28f : ((float)(arg14 + 68));
        v9 = 34.5f;
        v10 = LocaleController.isRTL ? ((float)(arg14 + 68)) : 28f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6, v7, v1_1, v9, v10, 0f));
        if(arg15) {
            this.optionsButton = new ImageView(arg13);
            this.optionsButton.setFocusable(false);
            this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor("stickers_menuSelector")));
            this.optionsButton.setImageResource(2131231112);
            this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor("stickers_menu"), PorterDuff$Mode.MULTIPLY));
            this.optionsButton.setScaleType(ImageView$ScaleType.CENTER);
            ImageView v13 = this.optionsButton;
            arg14 = 64;
            if(LocaleController.isRTL) {
            }
            else {
                v2 = 5;
            }

            this.addView(((View)v13), LayoutHelper.createFrame(v4, arg14, v2 | 48));
            this.optionsButton.setOnClickListener(new -$$Lambda$ManageChatUserCell$oJTkyKgCBYt9FR4kYNNgwqbXXuY(this));
        }
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public static void lambda$new$0(ManageChatUserCell arg1, View arg2) {
        arg1.delegate.onOptionsButtonCheck(arg1, true);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64f), 1073741824));
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setData(User arg1, CharSequence arg2, CharSequence arg3) {
        if(arg1 == null) {
            this.currrntStatus = null;
            this.currentName = null;
            this.currentUser = null;
            this.nameTextView.setText("");
            this.statusTextView.setText("");
            this.avatarImageView.setImageDrawable(null);
            return;
        }

        this.currrntStatus = arg3;
        this.currentName = arg2;
        this.currentUser = arg1;
        if(this.optionsButton != null) {
            ImageView v1 = this.optionsButton;
            int v3 = this.delegate.onOptionsButtonCheck(this, false) ? 0 : 4;
            v1.setVisibility(v3);
        }

        this.update(0);
    }

    public void setDelegate(ManageChatUserCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setIsAdmin(boolean arg1) {
        this.isAdmin = arg1;
    }

    public void setStatusColors(int arg1, int arg2) {
        this.statusColor = arg1;
        this.statusOnlineColor = arg2;
    }

    public void update(int arg10) {
        CharSequence v1_1;
        SimpleTextView v10_1;
        String v10;
        int v3;
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
                v3 = 1;
            }
            else {
            label_36:
                v3 = 0;
            }

            if(this.currentUser != null && v3 == 0 && (arg10 & 4) != 0) {
                int v5 = this.currentUser.status != null ? this.currentUser.status.expires : 0;
                if(v5 == this.lastStatus) {
                    goto label_53;
                }

                v3 = 1;
            }

        label_53:
            if(v3 != 0 || this.currentName != null || this.lastName == null || (arg10 & 1) == 0) {
                v10 = v1;
            }
            else {
                v10 = UserObject.getUserName(this.currentUser);
                if(!v10.equals(this.lastName)) {
                    v3 = 1;
                }
            }

            if(v3 != 0) {
                goto label_71;
            }

            return;
        }
        else {
            v10 = v1;
        }

    label_71:
        this.avatarDrawable.setInfo(this.currentUser);
        if(this.currentUser.status != null) {
            v2 = this.currentUser.status.expires;
        }

        this.lastStatus = v2;
        if(this.currentName != null) {
            this.lastName = v1;
            v10_1 = this.nameTextView;
            v1_1 = this.currentName;
        }
        else {
            if(v10 == null) {
                v10 = UserObject.getUserName(this.currentUser);
            }

            this.lastName = v10;
            v10_1 = this.nameTextView;
            v1 = this.lastName;
        }

        v10_1.setText(((CharSequence)v1));
        if(this.currrntStatus != null) {
            this.statusTextView.setTextColor(this.statusColor);
            v10_1 = this.statusTextView;
            v1_1 = this.currrntStatus;
            goto label_102;
        }
        else if(this.currentUser != null) {
            if(this.currentUser.bot) {
                this.statusTextView.setTextColor(this.statusColor);
                if(!this.currentUser.bot_chat_history) {
                    if(this.isAdmin) {
                    }
                    else {
                        v10_1 = this.statusTextView;
                        v1 = "BotStatusCantRead";
                        v2 = 2131624222;
                        goto label_125;
                    }
                }

                v10_1 = this.statusTextView;
                v1 = "BotStatusRead";
                v2 = 2131624223;
            }
            else {
                if(this.currentUser.id != UserConfig.getInstance(this.currentAccount).getClientUserId() && (this.currentUser.status == null || this.currentUser.status.expires <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) && !MessagesController.getInstance(this.currentAccount).onlinePrivacy.containsKey(Integer.valueOf(this.currentUser.id))) {
                    this.statusTextView.setTextColor(this.statusColor);
                    v10_1 = this.statusTextView;
                    v1 = LocaleController.formatUserStatus(this.currentAccount, this.currentUser);
                    goto label_102;
                }

                this.statusTextView.setTextColor(this.statusOnlineColor);
                v10_1 = this.statusTextView;
                v1 = "Online";
                v2 = 2131625428;
            }

        label_125:
            v1 = LocaleController.getString(v1, v2);
        label_102:
            v10_1.setText(((CharSequence)v1));
        }

        this.avatarImageView.setImage(((TLObject)v0), "50_50", this.avatarDrawable);
    }
}

