package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
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
import org.telegram.ui.Components.CheckBoxSquare;
import org.telegram.ui.Components.LayoutHelper;

public class CheckBoxUserCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private CheckBoxSquare checkBox;
    private User currentUser;
    private BackupImageView imageView;
    private boolean needDivider;
    private TextView textView;

    public CheckBoxUserCell(Context arg13, boolean arg14) {
        super(arg13);
        this.textView = new TextView(arg13);
        TextView v0 = this.textView;
        String v1 = arg14 ? "dialogTextBlack" : "windowBackgroundWhiteBlackText";
        v0.setTextColor(Theme.getColor(v1));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.textView;
        int v2 = 3;
        int v1_1 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v1_1 | 16);
        v0 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        v1_1 = LocaleController.isRTL ? 5 : 3;
        int v6 = v1_1 | 48;
        int v7 = 94;
        v1_1 = LocaleController.isRTL ? 17 : 94;
        float v1_2 = ((float)v1_1);
        if(LocaleController.isRTL) {
        }
        else {
            v7 = 17;
        }

        this.addView(((View)v0), LayoutHelper.createFrame(v4, v5, v6, v1_2, 0f, ((float)v7), 0f));
        this.avatarDrawable = new AvatarDrawable();
        this.imageView = new BackupImageView(arg13);
        this.imageView.setRoundRadius(AndroidUtilities.dp(36f));
        BackupImageView v0_1 = this.imageView;
        v4 = 36;
        v5 = 36f;
        v1_1 = LocaleController.isRTL ? 5 : 3;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v4, v5, v1_1 | 48, 48f, 6f, 48f, 0f));
        this.checkBox = new CheckBoxSquare(arg13, arg14);
        CheckBoxSquare v13 = this.checkBox;
        v4 = 18;
        v5 = 18f;
        if(LocaleController.isRTL) {
            v2 = 5;
        }

        v6 = v2 | 48;
        int v0_2 = 0;
        int v14 = LocaleController.isRTL ? 0 : 17;
        float v7_1 = ((float)v14);
        float v8 = 15f;
        if(LocaleController.isRTL) {
            v0_2 = 17;
        }

        this.addView(((View)v13), LayoutHelper.createFrame(v4, v5, v6, v7_1, v8, ((float)v0_2), 0f));
    }

    public CheckBoxSquare getCheckBox() {
        return this.checkBox;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public boolean isChecked() {
        return this.checkBox.isChecked();
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setChecked(boolean arg2, boolean arg3) {
        this.checkBox.setChecked(arg2, arg3);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }

    public void setUser(User arg4, boolean arg5, boolean arg6) {
        FileLocation v4;
        this.currentUser = arg4;
        this.textView.setText(ContactsController.formatName(arg4.first_name, arg4.last_name));
        this.checkBox.setChecked(arg5, false);
        this.avatarDrawable.setInfo(arg4);
        if(arg4 == null || arg4.photo == null) {
            TLObject v4_1 = null;
        }
        else {
            v4 = arg4.photo.photo_small;
        }

        this.imageView.setImage(((TLObject)v4), "50_50", this.avatarDrawable);
        this.needDivider = arg6;
        this.setWillNotDraw((((int)arg6)) ^ 1);
    }
}

