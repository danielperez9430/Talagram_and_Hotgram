package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$TL_messages_stickerSet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

public class StickerSetCell extends FrameLayout {
    private BackupImageView imageView;
    private boolean needDivider;
    private ImageView optionsButton;
    private RadialProgressView progressView;
    private Rect rect;
    private TL_messages_stickerSet stickersSet;
    private TextView textView;
    private TextView valueTextView;

    public StickerSetCell(Context arg18, int arg19) {
        FrameLayout$LayoutParams v2_1;
        ImageView v1_2;
        RadialProgressView v1_1;
        StickerSetCell v0 = this;
        Context v1 = arg18;
        int v2 = arg19;
        super(arg18);
        v0.rect = new Rect();
        v0.textView = new TextView(v1);
        v0.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.textView.setTextSize(1, 16f);
        v0.textView.setLines(1);
        v0.textView.setMaxLines(1);
        v0.textView.setSingleLine(true);
        v0.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v3 = v0.textView;
        int v6 = 5;
        int v7 = 3;
        int v5 = LocaleController.isRTL ? 5 : 3;
        v3.setGravity(v5);
        v3 = v0.textView;
        int v8 = -2;
        float v9 = -2f;
        int v10 = LocaleController.isRTL ? 5 : 3;
        float v11 = LocaleController.isRTL ? 40f : 71f;
        float v12 = 10f;
        float v13 = LocaleController.isRTL ? 71f : 40f;
        v0.addView(((View)v3), LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f));
        v0.valueTextView = new TextView(v1);
        v0.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        v0.valueTextView.setTextSize(1, 13f);
        v0.valueTextView.setLines(1);
        v0.valueTextView.setMaxLines(1);
        v0.valueTextView.setSingleLine(true);
        v3 = v0.valueTextView;
        v5 = LocaleController.isRTL ? 5 : 3;
        v3.setGravity(v5);
        v3 = v0.valueTextView;
        v8 = -2;
        v9 = -2f;
        v10 = LocaleController.isRTL ? 5 : 3;
        v11 = LocaleController.isRTL ? 40f : 71f;
        v12 = 35f;
        v13 = LocaleController.isRTL ? 71f : 40f;
        v0.addView(((View)v3), LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f));
        v0.imageView = new BackupImageView(v1);
        v0.imageView.setAspectFit(true);
        BackupImageView v3_1 = v0.imageView;
        v8 = 48;
        v9 = 48f;
        v5 = LocaleController.isRTL ? 5 : 3;
        v10 = v5 | 48;
        v11 = LocaleController.isRTL ? 0f : 12f;
        v12 = 8f;
        v13 = LocaleController.isRTL ? 12f : 0f;
        v0.addView(((View)v3_1), LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f));
        if(v2 == 2) {
            v0.progressView = new RadialProgressView(this.getContext());
            v0.progressView.setProgressColor(Theme.getColor("dialogProgressCircle"));
            v0.progressView.setSize(AndroidUtilities.dp(30f));
            v1_1 = v0.progressView;
            v8 = 48;
            v9 = 48f;
            if(LocaleController.isRTL) {
            }
            else {
                v6 = 3;
            }

            v10 = v6 | 48;
            v11 = LocaleController.isRTL ? 0f : 12f;
            v12 = 8f;
            if(LocaleController.isRTL) {
                v13 = 12f;
                goto label_168;
            }

            v13 = 0f;
            goto label_168;
        }
        else {
            if(v2 == 0) {
                return;
            }

            v0.optionsButton = new ImageView(v1);
            int v3_2 = 0;
            v0.optionsButton.setFocusable(false);
            v0.optionsButton.setScaleType(ImageView$ScaleType.CENTER);
            v0.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor("stickers_menuSelector")));
            if(v2 == 1) {
                v0.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor("stickers_menu"), PorterDuff$Mode.MULTIPLY));
                v0.optionsButton.setImageResource(2131231372);
                v1_2 = v0.optionsButton;
                if(LocaleController.isRTL) {
                    v6 = 3;
                }

                v2_1 = LayoutHelper.createFrame(40, 40, v6 | 48);
                goto label_204;
            }

            if(v2 != v7) {
                return;
            }

            v0.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
            v0.optionsButton.setImageResource(2131231573);
            v1_2 = v0.optionsButton;
            v8 = 40;
            v9 = 40f;
            if(LocaleController.isRTL) {
                v6 = 3;
            }

            v10 = v6 | 48;
            v2 = LocaleController.isRTL ? 10 : 0;
            v11 = ((float)v2);
            v12 = 12f;
            if(LocaleController.isRTL) {
            }
            else {
                v3_2 = 10;
            }

            v13 = ((float)v3_2);
        label_168:
            v2_1 = LayoutHelper.createFrame(v8, v9, v10, v11, v12, v13, 0f);
        label_204:
            v0.addView(((View)v1_1), ((ViewGroup$LayoutParams)v2_1));
        }
    }

    public TL_messages_stickerSet getStickersSet() {
        return this.stickersSet;
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
        if(Build$VERSION.SDK_INT >= 21 && this.getBackground() != null && this.optionsButton != null) {
            this.optionsButton.getHitRect(this.rect);
            if(this.rect.contains(((int)arg4.getX()), ((int)arg4.getY()))) {
                return 1;
            }
        }

        return super.onTouchEvent(arg4);
    }

    public void setChecked(boolean arg2) {
        if(this.optionsButton == null) {
            return;
        }

        ImageView v0 = this.optionsButton;
        int v2 = arg2 ? 0 : 4;
        v0.setVisibility(v2);
    }

    public void setOnOptionsClick(View$OnClickListener arg2) {
        if(this.optionsButton == null) {
            return;
        }

        this.optionsButton.setOnClickListener(arg2);
    }

    public void setStickersSet(TL_messages_stickerSet arg4, boolean arg5) {
        float v1;
        TextView v5;
        this.needDivider = arg5;
        this.stickersSet = arg4;
        this.imageView.setVisibility(0);
        if(this.progressView != null) {
            this.progressView.setVisibility(4);
        }

        this.textView.setTranslationY(0f);
        this.textView.setText(this.stickersSet.set.title);
        if(this.stickersSet.set.archived) {
            v5 = this.textView;
            v1 = 0.5f;
        }
        else {
            v5 = this.textView;
            v1 = 1f;
        }

        v5.setAlpha(v1);
        this.valueTextView.setAlpha(v1);
        this.imageView.setAlpha(v1);
        ArrayList v4 = arg4.documents;
        if(v4 == null || (v4.isEmpty())) {
            this.valueTextView.setText(LocaleController.formatPluralString("Stickers", 0));
        }
        else {
            this.valueTextView.setText(LocaleController.formatPluralString("Stickers", v4.size()));
            Object v4_1 = v4.get(0);
            if(((Document)v4_1).thumb != null && ((Document)v4_1).thumb.location != null) {
                this.imageView.setImage(((Document)v4_1).thumb.location, null, "webp", null);
            }
        }
    }

    public void setText(String arg2, String arg3, int arg4, boolean arg5) {
        float v3;
        TextView v2;
        this.needDivider = arg5;
        this.stickersSet = null;
        this.textView.setText(((CharSequence)arg2));
        this.valueTextView.setText(((CharSequence)arg3));
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            v2 = this.textView;
            v3 = ((float)AndroidUtilities.dp(10f));
        }
        else {
            v2 = this.textView;
            v3 = 0f;
        }

        v2.setTranslationY(v3);
        int v2_1 = 4;
        if(arg4 != 0) {
            this.imageView.setImageResource(arg4, Theme.getColor("windowBackgroundWhiteGrayIcon"));
            this.imageView.setVisibility(0);
            if(this.progressView != null) {
                this.progressView.setVisibility(v2_1);
            }
        }
        else {
            this.imageView.setVisibility(v2_1);
            if(this.progressView != null) {
                this.progressView.setVisibility(0);
            }
        }
    }
}

