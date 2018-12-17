package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class AccountSelectCell extends FrameLayout {
    private int accountNumber;
    private AvatarDrawable avatarDrawable;
    private ImageView checkImageView;
    private BackupImageView imageView;
    private TextView textView;

    public AccountSelectCell(Context arg9) {
        super(arg9);
        this.avatarDrawable = new AvatarDrawable();
        this.avatarDrawable.setTextSize(AndroidUtilities.dp(12f));
        this.imageView = new BackupImageView(arg9);
        this.imageView.setRoundRadius(AndroidUtilities.dp(18f));
        this.addView(this.imageView, LayoutHelper.createFrame(36, 36f, 51, 10f, 10f, 0f, 0f));
        this.textView = new TextView(arg9);
        this.textView.setTextColor(Theme.getColor("actionBarDefaultSubmenuItem"));
        this.textView.setTextSize(1, 15f);
        this.textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setGravity(19);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.textView, LayoutHelper.createFrame(-1, -1f, 51, 61f, 0f, 56f, 0f));
        this.checkImageView = new ImageView(arg9);
        this.checkImageView.setImageResource(2131230812);
        this.checkImageView.setScaleType(ImageView$ScaleType.CENTER);
        this.checkImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chats_menuItemCheck"), PorterDuff$Mode.MULTIPLY));
        this.addView(this.checkImageView, LayoutHelper.createFrame(40, -1f, 53, 0f, 0f, 6f, 0f));
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.textView.setTextColor(Theme.getColor("chats_menuItemText"));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56f), 1073741824));
    }

    public void setAccount(int arg7, boolean arg8) {
        FileLocation v0_1;
        this.accountNumber = arg7;
        User v0 = UserConfig.getInstance(this.accountNumber).getCurrentUser();
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
        ImageView v0_3 = this.checkImageView;
        arg7 = !arg8 || arg7 != UserConfig.selectedAccount ? 4 : 0;
        v0_3.setVisibility(arg7);
    }
}

