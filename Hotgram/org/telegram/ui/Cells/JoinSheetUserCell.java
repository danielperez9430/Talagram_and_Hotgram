package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class JoinSheetUserCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView imageView;
    private TextView nameTextView;
    private int[] result;

    public JoinSheetUserCell(Context arg10) {
        super(arg10);
        this.avatarDrawable = new AvatarDrawable();
        this.result = new int[1];
        this.imageView = new BackupImageView(arg10);
        this.imageView.setRoundRadius(AndroidUtilities.dp(27f));
        this.addView(this.imageView, LayoutHelper.createFrame(54, 54f, 49, 0f, 7f, 0f, 0f));
        this.nameTextView = new TextView(arg10);
        this.nameTextView.setTextColor(Theme.getColor("dialogTextBlack"));
        this.nameTextView.setTextSize(1, 12f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setGravity(49);
        this.nameTextView.setLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createFrame(-1, -2f, 51, 6f, 64f, 6f, 0f));
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100f), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(90f), 1073741824));
    }

    public void setCount(int arg9) {
        this.nameTextView.setText("");
        AvatarDrawable v2 = this.avatarDrawable;
        v2.setInfo(0, null, null, false, "+" + LocaleController.formatShortNumber(arg9, this.result));
        this.imageView.setImage(null, "50_50", this.avatarDrawable);
    }

    public void setUser(User arg4) {
        TLObject v4_1;
        this.nameTextView.setText(ContactsController.formatName(arg4.first_name, arg4.last_name));
        this.avatarDrawable.setInfo(arg4);
        if(arg4 == null || arg4.photo == null) {
            v4_1 = null;
        }
        else {
            FileLocation v4 = arg4.photo.photo_small;
        }

        this.imageView.setImage(v4_1, "50_50", this.avatarDrawable);
    }
}

