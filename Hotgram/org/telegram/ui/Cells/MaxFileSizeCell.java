package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView$SeekBarViewDelegate;
import org.telegram.ui.Components.SeekBarView;

public class MaxFileSizeCell extends FrameLayout {
    private long maxSize;
    private SeekBarView seekBarView;
    private SimpleTextView sizeTextView;
    private TextView textView;

    public MaxFileSizeCell(Context arg15) {
        super(arg15);
        this.textView = new TextView(arg15);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setText(LocaleController.getString("AutodownloadSizeLimit", 2131624186));
        TextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2 | 48);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.textView;
        int v5 = -1;
        float v6 = -1f;
        v2 = LocaleController.isRTL ? 5 : 3;
        int v7 = v2 | 48;
        float v8 = LocaleController.isRTL ? 64f : 17f;
        float v9 = 13f;
        float v10 = LocaleController.isRTL ? 17f : 64f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.sizeTextView = new SimpleTextView(arg15);
        this.sizeTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlueText6"));
        this.sizeTextView.setTextSize(16);
        SimpleTextView v0_1 = this.sizeTextView;
        v2 = LocaleController.isRTL ? 3 : 5;
        v0_1.setGravity(v2 | 48);
        v0_1 = this.sizeTextView;
        v5 = -1;
        v6 = -1f;
        if(LocaleController.isRTL) {
            v3 = 5;
        }

        v7 = v3 | 48;
        v8 = LocaleController.isRTL ? 17f : 64f;
        v9 = 13f;
        v10 = LocaleController.isRTL ? 64f : 17f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.seekBarView = new SeekBarView(arg15) {
            public boolean onTouchEvent(MotionEvent arg3) {
                if(arg3.getAction() == 0) {
                    this.getParent().requestDisallowInterceptTouchEvent(true);
                }

                return super.onTouchEvent(arg3);
            }
        };
        this.seekBarView.setReportChanges(true);
        this.seekBarView.setDelegate(new SeekBarViewDelegate() {
            public void onSeekBarDrag(float arg8) {
                int v8;
                float v0_1;
                if(MaxFileSizeCell.access$000(MaxFileSizeCell.this) > 10485760) {
                    int v0 = 104857600;
                    float v1 = 0.8f;
                    if(arg8 <= v1) {
                        v0_1 = ((float)v0);
                        arg8 /= v1;
                        goto label_26;
                    }
                    else {
                        v8 = ((int)((((float)v0)) + (((float)(MaxFileSizeCell.access$000(MaxFileSizeCell.this) - (((long)v0))))) * (arg8 - v1) / 0.2f));
                    }
                }
                else {
                    v0_1 = ((float)MaxFileSizeCell.access$000(MaxFileSizeCell.this));
                label_26:
                    v8 = ((int)(v0_1 * arg8));
                }

                MaxFileSizeCell.access$100(MaxFileSizeCell.this).setText(LocaleController.formatString("AutodownloadSizeLimitUpTo", 2131624187, new Object[]{AndroidUtilities.formatFileSize(((long)v8))}));
                MaxFileSizeCell.this.didChangedSizeValue(v8);
            }
        });
        this.addView(this.seekBarView, LayoutHelper.createFrame(-1, 30f, 51, 4f, 40f, 4f, 0f));
    }

    static long access$000(MaxFileSizeCell arg2) {
        return arg2.maxSize;
    }

    static SimpleTextView access$100(MaxFileSizeCell arg0) {
        return arg0.sizeTextView;
    }

    protected void didChangedSizeValue(int arg1) {
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80f), 1073741824));
    }

    public void setSize(long arg6, long arg8) {
        float v9;
        this.maxSize = arg8;
        if(this.maxSize > 10485760) {
            int v8 = 104857600;
            long v0 = ((long)v8);
            float v2 = 0.8f;
            v9 = Long.compare(arg6, v0) <= 0 ? (((float)arg6)) / (((float)v8)) * v2 : (((float)(arg6 - v0))) / (((float)(this.maxSize - v0))) * 0.2f + v2;
        }
        else {
            v9 = (((float)arg6)) / (((float)this.maxSize));
        }

        this.seekBarView.setProgress(v9);
        this.sizeTextView.setText(LocaleController.formatString("AutodownloadSizeLimitUpTo", 2131624187, new Object[]{AndroidUtilities.formatFileSize(arg6)}));
    }
}

