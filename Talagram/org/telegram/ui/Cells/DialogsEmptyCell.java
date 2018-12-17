package org.telegram.ui.Cells;

import android.content.Context;
import android.os.Build$VERSION;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnTouchListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class DialogsEmptyCell extends LinearLayout {
    private int currentAccount;
    private int currentType;
    private TextView emptyTextView1;
    private TextView emptyTextView2;

    public DialogsEmptyCell(Context arg10) {
        super(arg10);
        this.currentAccount = UserConfig.selectedAccount;
        int v0 = 17;
        this.setGravity(v0);
        this.setOrientation(1);
        this.setOnTouchListener(new View$OnTouchListener() {
            public boolean onTouch(View arg1, MotionEvent arg2) {
                return 1;
            }
        });
        this.emptyTextView1 = new TextView(arg10);
        this.emptyTextView1.setText(LocaleController.getString("NoChats", 2131625256));
        this.emptyTextView1.setTextColor(Theme.getColor("emptyListPlaceholder"));
        this.emptyTextView1.setGravity(v0);
        this.emptyTextView1.setTextSize(1, 20f);
        this.addView(this.emptyTextView1, LayoutHelper.createLinear(-2, -2, 0f, 20f, 0f, 0f));
        this.emptyTextView2 = new TextView(arg10);
        String v10 = LocaleController.getString("NoChatsHelp", 2131625258);
        if((AndroidUtilities.isTablet()) && !AndroidUtilities.isSmallTablet()) {
            v10 = v10.replace('\n', ' ');
        }

        this.emptyTextView2.setText(((CharSequence)v10));
        this.emptyTextView2.setTextColor(Theme.getColor("emptyListPlaceholder"));
        this.emptyTextView2.setTextSize(1, 15f);
        this.emptyTextView2.setGravity(v0);
        this.emptyTextView2.setPadding(AndroidUtilities.dp(8f), AndroidUtilities.dp(6f), AndroidUtilities.dp(8f), 0);
        this.emptyTextView2.setLineSpacing(((float)AndroidUtilities.dp(2f)), 1f);
        this.addView(this.emptyTextView2, LayoutHelper.createLinear(-2, -2, 0f, 0f, 0f, 20f));
    }

    protected void onMeasure(int arg4, int arg5) {
        int v0;
        arg5 = View$MeasureSpec.getSize(arg5);
        if(arg5 == 0) {
            arg5 = AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight();
            v0 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
            arg5 -= v0;
        }

        if(this.currentType == 0) {
            ArrayList v0_1 = MessagesController.getInstance(this.currentAccount).hintDialogs;
            if(!v0_1.isEmpty()) {
                arg5 -= AndroidUtilities.dp(72f) * v0_1.size() + v0_1.size() - 1 + AndroidUtilities.dp(50f);
            }

            v0 = 1073741824;
            arg4 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg4), v0);
        }
        else {
            v0 = -2147483648;
        }

        super.onMeasure(arg4, View$MeasureSpec.makeMeasureSpec(arg5, v0));
    }

    public void setType(int arg4) {
        String v4;
        this.currentType = arg4;
        char v0 = ' ';
        char v1 = '\n';
        if(this.currentType == 0) {
            v4 = LocaleController.getString("NoChatsHelp", 2131625258);
            if(AndroidUtilities.isTablet()) {
                if(AndroidUtilities.isSmallTablet()) {
                    goto label_22;
                }

                goto label_12;
            }
        }
        else {
            v4 = LocaleController.getString("NoChatsContactsHelp", 2131625257);
            if((AndroidUtilities.isTablet()) && !AndroidUtilities.isSmallTablet()) {
            label_12:
                v4 = v4.replace(v1, v0);
            }
        }

    label_22:
        this.emptyTextView2.setText(((CharSequence)v4));
    }
}

