package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class LanguageCell extends FrameLayout {
    private ImageView checkImage;
    private LocaleInfo currentLocale;
    private boolean isDialog;
    private boolean needDivider;
    private TextView textView;
    private TextView textView2;

    public LanguageCell(Context arg19, boolean arg20) {
        float v13;
        float v11;
        LanguageCell v0 = this;
        Context v1 = arg19;
        boolean v2 = arg20;
        super(arg19);
        v0.setWillNotDraw(false);
        v0.isDialog = v2;
        v0.textView = new TextView(v1);
        TextView v3 = v0.textView;
        String v4 = v2 ? "dialogTextBlack" : "windowBackgroundWhiteBlackText";
        v3.setTextColor(Theme.getColor(v4));
        v0.textView.setTextSize(1, 16f);
        v0.textView.setLines(1);
        v0.textView.setMaxLines(1);
        v0.textView.setSingleLine(true);
        v0.textView.setEllipsize(TextUtils$TruncateAt.END);
        v3 = v0.textView;
        int v6 = 3;
        int v4_1 = LocaleController.isRTL ? 5 : 3;
        v3.setGravity(v4_1 | 48);
        v3 = v0.textView;
        int v8 = -1;
        float v9 = -1f;
        v4_1 = LocaleController.isRTL ? 5 : 3;
        int v10 = v4_1 | 48;
        int v15 = 23;
        if(LocaleController.isRTL) {
            v11 = 71f;
        }
        else {
            v4_1 = v2 ? 23 : 16;
            v11 = ((float)v4_1);
        }

        v4_1 = v0.isDialog ? 4 : 6;
        float v12 = ((float)v4_1);
        if(LocaleController.isRTL) {
            v4_1 = v2 ? 23 : 16;
            v13 = ((float)v4_1);
        }
        else {
            v13 = 71f;
        }

        v0.addView(((View)v3), LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f));
        v0.textView2 = new TextView(v1);
        v3 = v0.textView2;
        v4 = v2 ? "dialogTextGray3" : "windowBackgroundWhiteGrayText3";
        v3.setTextColor(Theme.getColor(v4));
        v0.textView2.setTextSize(1, 13f);
        v0.textView2.setLines(1);
        v0.textView2.setMaxLines(1);
        v0.textView2.setSingleLine(true);
        v0.textView2.setEllipsize(TextUtils$TruncateAt.END);
        v3 = v0.textView2;
        v4_1 = LocaleController.isRTL ? 5 : 3;
        v3.setGravity(v4_1 | 48);
        v3 = v0.textView2;
        v8 = -1;
        v9 = -1f;
        v4_1 = LocaleController.isRTL ? 5 : 3;
        v10 = v4_1 | 48;
        if(LocaleController.isRTL) {
            v11 = 71f;
        }
        else {
            v4_1 = v2 ? 23 : 16;
            v11 = ((float)v4_1);
        }

        v4_1 = v0.isDialog ? 25 : 28;
        v12 = ((float)v4_1);
        if(LocaleController.isRTL) {
            if(v2) {
            }
            else {
                v15 = 16;
            }

            v13 = ((float)v15);
        }
        else {
            v13 = 71f;
        }

        v0.addView(((View)v3), LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f));
        v0.checkImage = new ImageView(v1);
        v0.checkImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
        v0.checkImage.setImageResource(2131231573);
        ImageView v1_1 = v0.checkImage;
        v8 = 19;
        v9 = 14f;
        if(LocaleController.isRTL) {
        }
        else {
            v6 = 5;
        }

        v0.addView(((View)v1_1), LayoutHelper.createFrame(v8, v9, v6 | 16, 23f, 0f, 23f, 0f));
    }

    public LocaleInfo getCurrentLocale() {
        return this.currentLocale;
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        arg4 = 1073741824;
        arg3 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), arg4);
        float v0 = this.isDialog ? 48f : 54f;
        super.onMeasure(arg3, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(v0) + this.needDivider, arg4));
    }

    public void setLanguage(LocaleInfo arg2, String arg3, boolean arg4) {
        TextView v0 = this.textView;
        if(arg3 != null) {
        }
        else {
            arg3 = arg2.name;
        }

        v0.setText(((CharSequence)arg3));
        this.textView2.setText(arg2.nameEnglish);
        this.currentLocale = arg2;
        this.needDivider = arg4;
    }

    public void setLanguageSelected(boolean arg2) {
        ImageView v0 = this.checkImage;
        int v2 = arg2 ? 0 : 4;
        v0.setVisibility(v2);
    }

    public void setValue(String arg2, String arg3) {
        this.textView.setText(((CharSequence)arg2));
        this.textView2.setText(((CharSequence)arg3));
        this.checkImage.setVisibility(4);
        this.currentLocale = null;
        this.needDivider = false;
    }
}

