package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$TL_dialog;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class HintDialogCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private StaticLayout countLayout;
    private int countWidth;
    private int currentAccount;
    private long dialog_id;
    private BackupImageView imageView;
    private int lastUnreadCount;
    private TextView nameTextView;
    private RectF rect;

    public HintDialogCell(Context arg9) {
        super(arg9);
        this.avatarDrawable = new AvatarDrawable();
        this.rect = new RectF();
        this.currentAccount = UserConfig.selectedAccount;
        this.imageView = new BackupImageView(arg9);
        this.imageView.setRoundRadius(AndroidUtilities.dp(27f));
        this.addView(this.imageView, LayoutHelper.createFrame(54, 54f, 49, 0f, 7f, 0f, 0f));
        this.nameTextView = new TextView(arg9);
        this.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.nameTextView.setTextSize(1, 12f);
        this.nameTextView.setMaxLines(2);
        this.nameTextView.setGravity(49);
        this.nameTextView.setLines(2);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createFrame(-1, -2f, 51, 6f, 64f, 6f, 0f));
    }

    public void checkUnreadCounter(int arg13) {
        if(arg13 != 0 && (arg13 & 256) == 0 && (arg13 & 2048) == 0) {
            return;
        }

        Object v0 = MessagesController.getInstance(this.currentAccount).dialogs_dict.get(this.dialog_id);
        if(v0 == null || ((TL_dialog)v0).unread_count == 0) {
            if(this.countLayout != null) {
                if(arg13 != 0) {
                    this.invalidate();
                }

                this.lastUnreadCount = 0;
                this.countLayout = null;
            }
        }
        else if(this.lastUnreadCount != ((TL_dialog)v0).unread_count) {
            this.lastUnreadCount = ((TL_dialog)v0).unread_count;
            String v5 = String.format("%d", Integer.valueOf(((TL_dialog)v0).unread_count));
            this.countWidth = Math.max(AndroidUtilities.dp(12f), ((int)Math.ceil(((double)Theme.dialogs_countTextPaint.measureText(v5)))));
            this.countLayout = new StaticLayout(((CharSequence)v5), Theme.dialogs_countTextPaint, this.countWidth, Layout$Alignment.ALIGN_CENTER, 1f, 0f, false);
            if(arg13 != 0) {
                this.invalidate();
            }
        }
    }

    protected boolean drawChild(Canvas arg7, View arg8, long arg9) {
        boolean v9 = super.drawChild(arg7, arg8, arg9);
        if(arg8 == this.imageView && this.countLayout != null) {
            int v8 = AndroidUtilities.dp(6f);
            int v10 = AndroidUtilities.dp(54f);
            int v0 = v10 - AndroidUtilities.dp(5.5f);
            this.rect.set(((float)v0), ((float)v8), ((float)(v0 + this.countWidth + AndroidUtilities.dp(11f))), ((float)(AndroidUtilities.dp(23f) + v8)));
            RectF v0_1 = this.rect;
            float v1 = AndroidUtilities.density * 11.5f;
            float v3 = AndroidUtilities.density * 11.5f;
            Paint v2 = MessagesController.getInstance(this.currentAccount).isDialogMuted(this.dialog_id) ? Theme.dialogs_countGrayPaint : Theme.dialogs_countPaint;
            arg7.drawRoundRect(v0_1, v1, v3, v2);
            arg7.save();
            arg7.translate(((float)v10), ((float)(v8 + AndroidUtilities.dp(4f))));
            this.countLayout.draw(arg7);
            arg7.restore();
        }

        return v9;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100f), 1073741824));
    }

    public void setDialog(int arg4, boolean arg5, CharSequence arg6) {
        TLObject v4_3;
        FileLocation v4_1;
        String v1;
        TextView v6;
        this.dialog_id = ((long)arg4);
        StaticLayout v0 = null;
        if(arg4 > 0) {
            User v4 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(arg4));
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

            this.avatarDrawable.setInfo(v4);
            if(v4 == null) {
                goto label_55;
            }

            if(v4.photo == null) {
                goto label_55;
            }

            v4_1 = v4.photo.photo_small;
        }
        else {
            Chat v4_2 = MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-arg4));
            if(arg6 != null) {
                this.nameTextView.setText(arg6);
            }
            else {
                if(v4_2 != null) {
                    v6 = this.nameTextView;
                    v1 = v4_2.title;
                }
                else {
                    v6 = this.nameTextView;
                    v1 = "";
                }

                v6.setText(((CharSequence)v1));
            }

            this.avatarDrawable.setInfo(v4_2);
            if(v4_2 != null && v4_2.photo != null) {
                v4_1 = v4_2.photo.photo_small;
                goto label_56;
            }

        label_55:
            v4_3 = ((TLObject)v0);
        }

    label_56:
        this.imageView.setImage(v4_3, "50_50", this.avatarDrawable);
        if(arg5) {
            this.checkUnreadCounter(0);
        }
        else {
            this.countLayout = v0;
        }
    }

    public void update() {
        int v0 = ((int)this.dialog_id);
        if(v0 > 0) {
            this.avatarDrawable.setInfo(MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(v0)));
        }
        else {
            this.avatarDrawable.setInfo(MessagesController.getInstance(this.currentAccount).getChat(Integer.valueOf(-v0)));
        }
    }
}

