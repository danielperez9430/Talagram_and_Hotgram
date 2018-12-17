package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.customization.util.c;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

public class CustomCell extends FrameLayout {
    private ImageView adminImage;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private CheckBox checkBox;
    private CheckBoxSquare checkBoxBig;
    private int currentDrawable;
    private CharSequence currentName;
    private TLObject currentObject;
    private CharSequence currrntStatus;
    private ImageView imageView;
    private ImageView ivMutual;
    private FileLocation lastAvatar;
    private String lastName;
    private int lastStatus;
    private SimpleTextView nameTextView;
    private final boolean showMatual;
    private int statusColor;
    private int statusOnlineColor;
    private SimpleTextView statusTextView;

    public CustomCell(Context arg26, int arg27, int arg28, boolean arg29) {
        CustomCell v0 = this;
        Context v1 = arg26;
        int v3 = arg28;
        super(arg26);
        v0.statusColor = Theme.getColor("windowBackgroundWhiteGrayText");
        v0.statusOnlineColor = Theme.getColor("windowBackgroundWhiteBlueText");
        v0.showMatual = ApplicationLoader.applicationContext.getSharedPreferences("plusconfig", 0).getBoolean("mutualShow", false);
        v0.avatarDrawable = new AvatarDrawable();
        v0.avatarImageView = new BackupImageView(v1);
        v0.avatarImageView.setRoundRadius(AndroidUtilities.dp(24f));
        BackupImageView v4 = v0.avatarImageView;
        int v8 = 3;
        int v7 = LocaleController.isRTL ? 5 : 3;
        int v12 = v7 | 48;
        float v13 = LocaleController.isRTL ? 0f : ((float)(arg27 + 7));
        float v14 = 8f;
        float v15 = LocaleController.isRTL ? ((float)(arg27 + 7)) : 0f;
        v0.addView(((View)v4), LayoutHelper.createFrame(48, 48f, v12, v13, v14, v15, 0f));
        v0.nameTextView = new SimpleTextView(v1);
        v0.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.nameTextView.setTypeface(c.b(arg26));
        v0.nameTextView.setTextSize(17);
        SimpleTextView v4_1 = v0.nameTextView;
        v7 = LocaleController.isRTL ? 5 : 3;
        v4_1.setGravity(v7 | 48);
        v4_1 = v0.nameTextView;
        int v10 = -1;
        float v11 = 20f;
        v7 = LocaleController.isRTL ? 5 : 3;
        v12 = v7 | 48;
        int v13_1 = 18;
        int v15_1 = 2;
        if(LocaleController.isRTL) {
            v7 = v3 == v15_1 ? 18 : 0;
            v7 += 28;
        }
        else {
            v7 = arg27 + 68;
        }

        float v7_1 = ((float)v7);
        v14 = 11.5f;
        if(LocaleController.isRTL) {
            v13_1 = arg27 + 68;
        }
        else {
            if(v3 == v15_1) {
            }
            else {
                v13_1 = 0;
            }

            v13_1 += 28;
        }

        float v16 = ((float)v13_1);
        v13 = v7_1;
        v7 = 2;
        v0.addView(((View)v4_1), LayoutHelper.createFrame(v10, v11, v12, v13, v14, v16, 0f));
        v0.statusTextView = new SimpleTextView(v1);
        v0.statusTextView.setTypeface(c.b(arg26));
        v0.statusTextView.setTextSize(14);
        v4_1 = v0.statusTextView;
        v10 = LocaleController.isRTL ? 5 : 3;
        v4_1.setGravity(v10 | 48);
        v4_1 = v0.statusTextView;
        v10 = -1;
        v11 = 20f;
        v12 = LocaleController.isRTL ? 5 : 3;
        v12 |= 48;
        v13 = LocaleController.isRTL ? 28f : ((float)(arg27 + 68));
        v15 = 34.5f;
        v16 = LocaleController.isRTL ? ((float)(arg27 + 68)) : 28f;
        v0.addView(((View)v4_1), LayoutHelper.createFrame(v10, v11, v12, v13, v15, v16, 0f));
        v0.imageView = new ImageView(v1);
        v0.imageView.setScaleType(ImageView$ScaleType.CENTER);
        v10 = 8;
        v0.imageView.setVisibility(v10);
        ImageView v4_2 = v0.imageView;
        int v18 = -2;
        float v19 = -2f;
        int v11_1 = LocaleController.isRTL ? 5 : 3;
        int v20 = v11_1 | 16;
        float v21 = LocaleController.isRTL ? 0f : 16f;
        float v23 = LocaleController.isRTL ? 16f : 0f;
        v0.addView(((View)v4_2), LayoutHelper.createFrame(v18, v19, v20, v21, 0f, v23, 0f));
        v0.ivMutual = new ImageView(v1);
        v0.ivMutual.setScaleType(ImageView$ScaleType.CENTER);
        v0.ivMutual.setVisibility(v10);
        v0.ivMutual.setBackgroundResource(2131230813);
        v4_2 = v0.ivMutual;
        v10 = -2;
        v11 = -2f;
        v12 = LocaleController.isRTL ? 3 : 5;
        v0.addView(((View)v4_2), LayoutHelper.createFrame(v10, v11, v12 | 16, 16f, 0f, 16f, 0f));
        if(v3 == v7) {
            v0.checkBoxBig = new CheckBoxSquare(v1, false);
            CheckBoxSquare v2 = v0.checkBoxBig;
            v10 = 18;
            v11 = 18f;
            v3 = LocaleController.isRTL ? 3 : 5;
            v12 = v3 | 16;
            v13 = LocaleController.isRTL ? 19f : 0f;
            v15 = LocaleController.isRTL ? 0f : 19f;
            v0.addView(((View)v2), LayoutHelper.createFrame(v10, v11, v12, v13, 0f, v15, 0f));
        }
        else {
            if(v3 != 1) {
                goto label_298;
            }

            v0.checkBox = new CheckBox(v1, 2131231523);
            v0.checkBox.setVisibility(4);
            v0.checkBox.setColor(Theme.getColor("checkbox"), Theme.getColor("checkboxCheck"));
            CheckBox v3_1 = v0.checkBox;
            v10 = 22;
            v11 = 22f;
            int v4_3 = LocaleController.isRTL ? 5 : 3;
            v12 = v4_3 | 48;
            v13 = LocaleController.isRTL ? 0f : ((float)(arg27 + 37));
            v14 = 38f;
            v15 = LocaleController.isRTL ? ((float)(arg27 + 37)) : 0f;
            v0.addView(((View)v3_1), LayoutHelper.createFrame(v10, v11, v12, v13, v14, v15, 0f));
        }

    label_298:
        if(arg29) {
            v0.adminImage = new ImageView(v1);
            v0.adminImage.setImageResource(2131230815);
            ImageView v1_1 = v0.adminImage;
            v10 = 16;
            v11 = 16f;
            if(LocaleController.isRTL) {
            }
            else {
                v8 = 5;
            }

            v12 = v8 | 48;
            v13 = LocaleController.isRTL ? 24f : 0f;
            v14 = 13.5f;
            v15 = LocaleController.isRTL ? 0f : 24f;
            v0.addView(((View)v1_1), LayoutHelper.createFrame(v10, v11, v12, v13, v14, v15, 0f));
        }
    }

    public boolean hasOverlappingRendering() {
        return 0;
    }

    public void invalidate() {
        super.invalidate();
        if(this.checkBoxBig != null) {
            this.checkBoxBig.invalidate();
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64f), 1073741824));
    }

    public void setCheckDisabled(boolean arg2) {
        if(this.checkBoxBig != null) {
            this.checkBoxBig.setDisabled(arg2);
        }
    }

    public void setChecked(boolean arg3, boolean arg4) {
        if(this.checkBox != null) {
            if(this.checkBox.getVisibility() != 0) {
                this.checkBox.setVisibility(0);
            }

            this.checkBox.setChecked(arg3, arg4);
        }
        else {
            if(this.checkBoxBig == null) {
                return;
            }

            if(this.checkBoxBig.getVisibility() != 0) {
                this.checkBoxBig.setVisibility(0);
            }

            this.checkBoxBig.setChecked(arg3, arg4);
        }
    }

    public void setData(TLObject arg1, CharSequence arg2, CharSequence arg3, int arg4) {
        if(arg1 == null) {
            this.currrntStatus = null;
            this.currentName = null;
            this.currentObject = null;
            this.nameTextView.setText("");
            this.statusTextView.setText("");
            this.avatarImageView.setImageDrawable(null);
            return;
        }

        this.currrntStatus = arg3;
        this.currentName = arg2;
        this.currentObject = arg1;
        this.currentDrawable = arg4;
        this.update(0);
    }

    public void setIsAdmin(int arg6) {
        PorterDuffColorFilter v0_2;
        ImageView v6;
        if(this.adminImage == null) {
            return;
        }

        ImageView v0 = this.adminImage;
        int v2 = arg6 != 0 ? 0 : 8;
        v0.setVisibility(v2);
        SimpleTextView v0_1 = this.nameTextView;
        float v3 = 16f;
        v2 = !LocaleController.isRTL || arg6 == 0 ? 0 : AndroidUtilities.dp(v3);
        int v3_1 = (LocaleController.isRTL) || arg6 == 0 ? 0 : AndroidUtilities.dp(v3);
        v0_1.setPadding(v2, 0, v3_1, 0);
        if(arg6 == 1) {
            this.setTag("profile_creatorIcon");
            v6 = this.adminImage;
            v0_2 = new PorterDuffColorFilter(Theme.getColor("profile_creatorIcon"), PorterDuff$Mode.MULTIPLY);
            goto label_35;
        }
        else if(arg6 == 2) {
            this.setTag("profile_adminIcon");
            v6 = this.adminImage;
            v0_2 = new PorterDuffColorFilter(Theme.getColor("profile_adminIcon"), PorterDuff$Mode.MULTIPLY);
        label_35:
            v6.setColorFilter(((ColorFilter)v0_2));
        }
    }

    public void setStatusColors(int arg1, int arg2) {
        this.statusColor = arg1;
        this.statusOnlineColor = arg2;
    }

    public void update(int arg12) {
        int v1_2;
        String v0_2;
        CharSequence v0_1;
        CharSequence v1_1;
        SimpleTextView v12_1;
        String v12;
        int v2_1;
        TLObject v3_1;
        Chat v3;
        FileLocation v2;
        TLObject v0;
        if(this.currentObject == null) {
            return;
        }

        String v1 = null;
        if((this.currentObject instanceof User)) {
            v0 = this.currentObject;
            if(((User)v0).photo != null) {
                v2 = ((User)v0).photo.photo_small;
                v3 = ((Chat)v1);
            }
            else {
                v2 = ((FileLocation)v1);
                v3 = ((Chat)v2);
            }
        }
        else {
            v0 = this.currentObject;
            if(((Chat)v0).photo != null) {
                v2 = ((Chat)v0).photo.photo_small;
                v3_1 = v0;
                v0 = ((TLObject)v1);
            }
            else {
                v3_1 = v0;
                v0 = ((TLObject)v1);
                v2 = ((FileLocation)v0);
            }
        }

        if(arg12 != 0) {
            if((arg12 & 2) != 0) {
                if(this.lastAvatar == null || v2 != null) {
                    if(this.lastAvatar != null) {
                        goto label_52;
                    }
                    else if(v2 == null) {
                        goto label_52;
                    }
                    else if(this.lastAvatar == null) {
                        goto label_52;
                    }
                    else if(v2 == null) {
                        goto label_52;
                    }
                    else if(this.lastAvatar.volume_id == v2.volume_id) {
                        if(this.lastAvatar.local_id != v2.local_id) {
                            goto label_50;
                        }

                        goto label_52;
                    }
                }

            label_50:
                v2_1 = 1;
            }
            else {
            label_52:
                v2_1 = 0;
            }

            if(v0 != null && v2_1 == 0 && (arg12 & 4) != 0) {
                int v5 = ((User)v0).status != null ? ((User)v0).status.expires : 0;
                if(v5 == this.lastStatus) {
                    goto label_66;
                }

                v2_1 = 1;
            }

        label_66:
            if(v2_1 != 0 || this.currentName != null || this.lastName == null || (arg12 & 1) == 0) {
                v12 = v1;
            }
            else {
                v12 = v0 != null ? UserObject.getUserName(((User)v0)) : v3.title;
                if(v12.equals(this.lastName)) {
                    goto label_83;
                }

                v2_1 = 1;
            }

        label_83:
            if(v2_1 != 0) {
                goto label_86;
            }

            return;
        }
        else {
            v12 = v1;
        }

    label_86:
        if(v0 != null) {
            this.avatarDrawable.setInfo(((User)v0));
            this.lastStatus = ((User)v0).status != null ? ((User)v0).status.expires : 0;
        }
        else {
            this.avatarDrawable.setInfo(v3);
        }

        if(this.currentName != null) {
            this.lastName = v1;
            v12_1 = this.nameTextView;
            v1_1 = this.currentName;
        }
        else {
            if(v0 != null) {
                if(v12 == null) {
                    v12 = UserObject.getUserName(((User)v0));
                }
            }
            else if(v12 == null) {
                v12 = v3.title;
            }

            this.lastName = v12;
            v12_1 = this.nameTextView;
            v1 = this.lastName;
        }

        v12_1.setText(v1_1);
        if(this.currrntStatus != null) {
            this.statusTextView.setTextColor(this.statusColor);
            v12_1 = this.statusTextView;
            v0_1 = this.currrntStatus;
            goto label_124;
        }
        else if(v0 != null) {
            if(((User)v0).bot) {
                this.statusTextView.setTextColor(this.statusColor);
                if(!((User)v0).bot_chat_history && (this.adminImage == null || this.adminImage.getVisibility() != 0)) {
                    v12_1 = this.statusTextView;
                    v0_2 = "BotStatusCantRead";
                    v1_2 = 2131624222;
                    goto label_147;
                }

                v12_1 = this.statusTextView;
                v0_2 = "BotStatusRead";
                v1_2 = 2131624223;
            }
            else {
                if(((User)v0).id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId() && (((User)v0).status == null || ((User)v0).status.expires <= ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime()) && !MessagesController.getInstance(UserConfig.selectedAccount).onlinePrivacy.containsKey(Integer.valueOf(((User)v0).id))) {
                    this.statusTextView.setTextColor(this.statusColor);
                    v12_1 = this.statusTextView;
                    v0_2 = LocaleController.formatUserStatus(UserConfig.selectedAccount, ((User)v0));
                    goto label_124;
                }

                this.statusTextView.setTextColor(this.statusOnlineColor);
                v12_1 = this.statusTextView;
                v0_2 = "Online";
                v1_2 = 2131625428;
            }

        label_147:
            v0_2 = LocaleController.getString(v0_2, v1_2);
        label_124:
            v12_1.setText(v0_1);
        }

        int v0_3 = 8;
        if(this.imageView.getVisibility() == 0 && this.currentDrawable == 0 || this.imageView.getVisibility() == v0_3 && this.currentDrawable != 0) {
            ImageView v12_2 = this.imageView;
            if(this.currentDrawable == 0) {
            }
            else {
                v0_3 = 0;
            }

            v12_2.setVisibility(v0_3);
            this.imageView.setImageResource(this.currentDrawable);
        }
    }
}

