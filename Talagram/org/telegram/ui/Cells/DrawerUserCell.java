package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.GroupCreateCheckBox;
import org.telegram.ui.Components.LayoutHelper;

public class DrawerUserCell extends FrameLayout {
    private int accountNumber;
    private AvatarDrawable avatarDrawable;
    private GroupCreateCheckBox checkBox;
    private BackupImageView imageView;
    private RectF rect;
    private TextView textView;

    public DrawerUserCell(Context arg22) {
        DrawerUserCell v0 = this;
        Context v1 = arg22;
        super(arg22);
        v0.rect = new RectF();
        v0.avatarDrawable = new AvatarDrawable();
        v0.avatarDrawable.setTextSize(AndroidUtilities.dp(12f));
        v0.imageView = new BackupImageView(v1);
        v0.imageView.setRoundRadius(AndroidUtilities.dp(18f));
        BackupImageView v2 = v0.imageView;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 48;
        float v9 = LocaleController.isRTL ? 0f : 14f;
        float v10 = 6f;
        float v11 = LocaleController.isRTL ? 14f : 0f;
        v0.addView(((View)v2), LayoutHelper.createFrame(36, 36f, v8, v9, v10, v11, 0f));
        v0.textView = new TextView(v1);
        v0.textView.setTextColor(Theme.getColor("chats_menuItemText"));
        v0.textView.setTextSize(1, 15f);
        v0.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.textView.setLines(1);
        v0.textView.setMaxLines(1);
        v0.textView.setSingleLine(true);
        TextView v2_1 = v0.textView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 16);
        v0.textView.setEllipsize(TextUtils$TruncateAt.END);
        v2_1 = v0.textView;
        int v14 = -1;
        float v15 = -1f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v14, v15, v3 | 48, 72f, 0f, 60f, 0f));
        v0.checkBox = new GroupCreateCheckBox(v1);
        v0.checkBox.setChecked(true, false);
        v0.checkBox.setCheckScale(0.9f);
        v0.checkBox.setInnerRadDiff(AndroidUtilities.dp(1.5f));
        v0.checkBox.setColorKeysOverrides("chats_unreadCounterText", "chats_unreadCounter", "chats_menuBackground");
        GroupCreateCheckBox v1_1 = v0.checkBox;
        int v6 = 18;
        float v7 = 18f;
        if(LocaleController.isRTL) {
            v4 = 5;
        }

        v8 = v4 | 48;
        v9 = LocaleController.isRTL ? 0f : 37f;
        v10 = 27f;
        v11 = LocaleController.isRTL ? 37f : 0f;
        v0.addView(((View)v1_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        v0.setWillNotDraw(false);
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
    }

    protected void onDraw(Canvas arg9) {
        if(UserConfig.getActivatedAccountsCount() > 1) {
            if(!NotificationsController.getInstance(this.accountNumber).showBadgeNumber) {
            }
            else {
                int v0 = NotificationsController.getInstance(this.accountNumber).getTotalUnreadCount();
                if(v0 <= 0) {
                    return;
                }
                else {
                    String v0_1 = String.format("%d", Integer.valueOf(v0));
                    int v1 = AndroidUtilities.dp(12.5f);
                    int v2 = ((int)Math.ceil(((double)Theme.chat_livePaint.measureText(v0_1))));
                    int v3 = Math.max(AndroidUtilities.dp(12f), v2);
                    int v4 = this.getMeasuredWidth() - v3 - AndroidUtilities.dp(25f) - AndroidUtilities.dp(5.5f);
                    this.rect.set(((float)v4), ((float)v1), ((float)(v4 + v3 + AndroidUtilities.dp(11f))), ((float)(AndroidUtilities.dp(23f) + v1)));
                    arg9.drawRoundRect(this.rect, AndroidUtilities.density * 11.5f, AndroidUtilities.density * 11.5f, Theme.dialogs_countPaint);
                    arg9.drawText(v0_1, this.rect.left + (this.rect.width() - (((float)v2))) / 2f - (((float)AndroidUtilities.dp(0.5f))), ((float)(v1 + AndroidUtilities.dp(16f))), Theme.dialogs_countTextPaint);
                }
            }
        }
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
    }

    public void setAccount(int arg7) {
        FileLocation v0_1;
        this.accountNumber = arg7;
        User v0 = UserConfig.getInstance(this.accountNumber).getCurrentUser();
        if(v0 == null) {
            return;
        }

        this.avatarDrawable.setInfo(v0);
        this.textView.setText(ContactsController.formatName(v0.first_name, v0.last_name));
        if(v0.photo == null || v0.photo.photo_small == null || v0.photo.photo_small.volume_id == 0 || v0.photo.photo_small.local_id == 0) {
            TLObject v0_2 = null;
        }
        else {
            v0_1 = v0.photo.photo_small;
        }

        this.imageView.getImageReceiver().setCurrentAccount(arg7);
        this.imageView.setImage(((TLObject)v0_1), "50_50", this.avatarDrawable);
        GroupCreateCheckBox v0_3 = this.checkBox;
        arg7 = arg7 == UserConfig.selectedAccount ? 0 : 4;
        v0_3.setVisibility(arg7);
    }
}

