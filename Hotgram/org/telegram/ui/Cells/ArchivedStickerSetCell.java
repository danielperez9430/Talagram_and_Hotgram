package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils$TruncateAt;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$StickerSetCovered;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Switch;

public class ArchivedStickerSetCell extends FrameLayout {
    private Switch checkBox;
    private BackupImageView imageView;
    private boolean needDivider;
    private CompoundButton$OnCheckedChangeListener onCheckedChangeListener;
    private Rect rect;
    private StickerSetCovered stickersSet;
    private TextView textView;
    private TextView valueTextView;

    public ArchivedStickerSetCell(Context arg14, boolean arg15) {
        super(arg14);
        this.rect = new Rect();
        this.textView = new TextView(arg14);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        v0 = this.textView;
        int v5 = -2;
        float v6 = -2f;
        int v7 = LocaleController.isRTL ? 5 : 3;
        float v8 = 71f;
        float v9 = 10f;
        float v10 = arg15 ? 71f : 21f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.valueTextView = new TextView(arg14);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.valueTextView.setTextSize(1, 13f);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        v0 = this.valueTextView;
        v5 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v5);
        v0 = this.valueTextView;
        v5 = -2;
        v6 = -2f;
        v7 = LocaleController.isRTL ? 5 : 3;
        v8 = 71f;
        v9 = 35f;
        v10 = arg15 ? 71f : 21f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.imageView = new BackupImageView(arg14);
        this.imageView.setAspectFit(true);
        BackupImageView v0_1 = this.imageView;
        v5 = 48;
        v6 = 48f;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v7 = v1 | 48;
        float v1_1 = LocaleController.isRTL ? 0f : 12f;
        v9 = 8f;
        v10 = LocaleController.isRTL ? 12f : 0f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6, v7, v1_1, v9, v10, 0f));
        if(arg15) {
            this.checkBox = new Switch(arg14);
            this.checkBox.setDuplicateParentStateEnabled(false);
            this.checkBox.setFocusable(false);
            this.checkBox.setFocusableInTouchMode(false);
            Switch v14 = this.checkBox;
            v5 = -2;
            v6 = -2f;
            if(LocaleController.isRTL) {
            }
            else {
                v3 = 5;
            }

            this.addView(((View)v14), LayoutHelper.createFrame(v5, v6, v3 | 16, 14f, 0f, 14f, 0f));
        }
    }

    public Switch getCheckBox() {
        return this.checkBox;
    }

    public StickerSetCovered getStickersSet() {
        return this.stickersSet;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public TextView getValueTextView() {
        return this.valueTextView;
    }

    public boolean isChecked() {
        boolean v0 = this.checkBox == null || !this.checkBox.isChecked() ? false : true;
        return v0;
    }

    static void lambda$setOnCheckClick$0(View arg0) {
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(0f, ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64f) + this.needDivider, 1073741824));
    }

    public boolean onTouchEvent(MotionEvent arg4) {
        if(this.checkBox != null) {
            this.checkBox.getHitRect(this.rect);
            if(this.rect.contains(((int)arg4.getX()), ((int)arg4.getY()))) {
                arg4.offsetLocation(-this.checkBox.getX(), -this.checkBox.getY());
                return this.checkBox.onTouchEvent(arg4);
            }
        }

        return super.onTouchEvent(arg4);
    }

    public void setChecked(boolean arg3) {
        this.checkBox.setOnCheckedChangeListener(null);
        this.checkBox.setChecked(arg3);
        this.checkBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
    }

    public void setOnCheckClick(CompoundButton$OnCheckedChangeListener arg2) {
        Switch v0 = this.checkBox;
        this.onCheckedChangeListener = arg2;
        v0.setOnCheckedChangeListener(arg2);
        this.checkBox.setOnClickListener(-$$Lambda$ArchivedStickerSetCell$mTQUFQWpIbO5-1lcMiyhXBviVR8.INSTANCE);
    }

    public void setStickersSet(StickerSetCovered arg3, boolean arg4) {
        BackupImageView v4;
        this.needDivider = arg4;
        this.stickersSet = arg3;
        this.setWillNotDraw(this.needDivider ^ 1);
        this.textView.setText(this.stickersSet.set.title);
        this.valueTextView.setText(LocaleController.formatPluralString("Stickers", arg3.set.count));
        String v0 = null;
        if(arg3.cover != null && arg3.cover.thumb != null && arg3.cover.thumb.location != null) {
            v4 = this.imageView;
            Document v3 = arg3.cover;
            goto label_36;
        }
        else if(!arg3.covers.isEmpty()) {
            v4 = this.imageView;
            Object v3_1 = arg3.covers.get(0);
        label_36:
            v4.setImage(((Document)v3_1).thumb.location, v0, "webp", ((Drawable)v0));
        }
    }
}

