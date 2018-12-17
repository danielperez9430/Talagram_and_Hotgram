package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Build$VERSION;
import android.text.Layout$Alignment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.URLSpanNoUnderline;

public class AboutLinkCell extends FrameLayout {
    public interface AboutLinkCellDelegate {
        void didPressUrl(String arg1);
    }

    private AboutLinkCellDelegate delegate;
    private ImageView imageView;
    private String oldText;
    private ClickableSpan pressedLink;
    private SpannableStringBuilder stringBuilder;
    private StaticLayout textLayout;
    private int textX;
    private int textY;
    private LinkPath urlPath;

    public AboutLinkCell(Context arg9) {
        super(arg9);
        this.urlPath = new LinkPath();
        this.imageView = new ImageView(arg9);
        this.imageView.setScaleType(ImageView$ScaleType.CENTER);
        this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("windowBackgroundWhiteGrayIcon"), PorterDuff$Mode.MULTIPLY));
        ImageView v9 = this.imageView;
        int v0 = LocaleController.isRTL ? 5 : 3;
        int v3 = v0 | 48;
        float v4 = LocaleController.isRTL ? 0f : 16f;
        float v5 = 5f;
        float v6 = LocaleController.isRTL ? 16f : 0f;
        this.addView(((View)v9), LayoutHelper.createFrame(-2, -2f, v3, v4, v5, v6, 0f));
        this.setWillNotDraw(false);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    protected void onDraw(Canvas arg3) {
        arg3.save();
        float v0 = LocaleController.isRTL ? 16f : 71f;
        int v0_1 = AndroidUtilities.dp(v0);
        this.textX = v0_1;
        int v1 = AndroidUtilities.dp(8f);
        this.textY = v1;
        arg3.translate(((float)v0_1), ((float)v1));
        if(this.pressedLink != null) {
            arg3.drawPath(this.urlPath, Theme.linkSelectionPaint);
        }

        try {
            if(this.textLayout == null) {
                goto label_26;
            }

            this.textLayout.draw(arg3);
        }
        catch(Exception v0_2) {
            FileLog.e(((Throwable)v0_2));
        }

    label_26:
        arg3.restore();
    }

    @SuppressLint(value={"DrawAllocation"}) protected void onMeasure(int arg10, int arg11) {
        if(this.stringBuilder != null) {
            int v4 = View$MeasureSpec.getSize(arg10) - AndroidUtilities.dp(87f);
            StaticLayout v11 = Build$VERSION.SDK_INT >= 24 ? StaticLayout$Builder.obtain(this.stringBuilder, 0, this.stringBuilder.length(), Theme.profile_aboutTextPaint, v4).setBreakStrategy(1).setHyphenationFrequency(0).setAlignment(Layout$Alignment.ALIGN_NORMAL).build() : new StaticLayout(this.stringBuilder, Theme.profile_aboutTextPaint, v4, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
            this.textLayout = v11;
        }

        arg11 = 1073741824;
        arg10 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg10), arg11);
        int v0 = this.textLayout != null ? this.textLayout.getHeight() : AndroidUtilities.dp(20f);
        super.onMeasure(arg10, View$MeasureSpec.makeMeasureSpec(v0 + AndroidUtilities.dp(16f), arg11));
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        CharSequence v0_3;
        int v1_1;
        int v0_2;
        float v0 = arg8.getX();
        float v1 = arg8.getY();
        boolean v3 = false;
        if(this.textLayout != null) {
            if(arg8.getAction() != 0 && (this.pressedLink == null || arg8.getAction() != 1)) {
                if(arg8.getAction() != 3) {
                    goto label_113;
                }

                this.resetPressedLink();
                goto label_113;
            }

            if(arg8.getAction() == 0) {
                this.resetPressedLink();
                try {
                    v0_2 = ((int)(v0 - (((float)this.textX))));
                    v1_1 = this.textLayout.getLineForVertical(((int)(v1 - (((float)this.textY)))));
                    v0 = ((float)v0_2);
                    int v2 = this.textLayout.getOffsetForHorizontal(v1_1, v0);
                    float v5 = this.textLayout.getLineLeft(v1_1);
                    if(v5 <= v0 && v5 + this.textLayout.getLineWidth(v1_1) >= v0) {
                        v0_3 = this.textLayout.getText();
                        Object[] v1_2 = ((Spannable)v0_3).getSpans(v2, v2, ClickableSpan.class);
                        if(v1_2.length != 0) {
                            this.resetPressedLink();
                            this.pressedLink = v1_2[0];
                            goto label_50;
                        }
                    }

                    goto label_68;
                }
                catch(Exception v0_1) {
                    goto label_71;
                }

                try {
                label_50:
                    v1_1 = ((Spannable)v0_3).getSpanStart(this.pressedLink);
                    this.urlPath.setCurrentLayout(this.textLayout, v1_1, 0f);
                    this.textLayout.getSelectionPath(v1_1, ((Spannable)v0_3).getSpanEnd(this.pressedLink), this.urlPath);
                    goto label_111;
                }
                catch(Exception v0_1) {
                    try {
                        FileLog.e(((Throwable)v0_1));
                        goto label_111;
                    }
                    catch(Exception v0_1) {
                        v1_1 = 1;
                        goto label_72;
                    }
                }

                try {
                label_68:
                    this.resetPressedLink();
                    goto label_113;
                }
                catch(Exception v0_1) {
                label_71:
                    v1_1 = 0;
                }

            label_72:
                this.resetPressedLink();
                FileLog.e(((Throwable)v0_1));
                v0_2 = v1_1;
                goto label_114;
            }

            if(this.pressedLink == null) {
                goto label_113;
            }

            try {
                if((this.pressedLink instanceof URLSpanNoUnderline)) {
                    String v0_4 = this.pressedLink.getURL();
                    if(!v0_4.startsWith("@") && !v0_4.startsWith("#") && !v0_4.startsWith("/")) {
                        goto label_110;
                    }

                    if(this.delegate == null) {
                        goto label_110;
                    }

                    this.delegate.didPressUrl(v0_4);
                    goto label_110;
                }

                if((this.pressedLink instanceof URLSpan)) {
                    Browser.openUrl(this.getContext(), this.pressedLink.getURL());
                    goto label_110;
                }

                this.pressedLink.onClick(((View)this));
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

        label_110:
            this.resetPressedLink();
        label_111:
            v0_2 = 1;
        }
        else {
        label_113:
            v0_2 = 0;
        }

    label_114:
        if(v0_2 != 0 || (super.onTouchEvent(arg8))) {
            v3 = true;
        }

        return v3;
    }

    private void resetPressedLink() {
        if(this.pressedLink != null) {
            this.pressedLink = null;
        }

        this.invalidate();
    }

    public void setDelegate(AboutLinkCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setTextAndIcon(String arg3, int arg4, boolean arg5) {
        if(!TextUtils.isEmpty(((CharSequence)arg3)) && (arg3 == null || this.oldText == null || !arg3.equals(this.oldText))) {
            this.oldText = arg3;
            this.stringBuilder = new SpannableStringBuilder(this.oldText);
            if(arg5) {
                MessageObject.addLinks(false, this.stringBuilder, false);
            }

            Emoji.replaceEmoji(this.stringBuilder, Theme.profile_aboutTextPaint.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
            this.requestLayout();
            if(arg4 == 0) {
                this.imageView.setImageDrawable(null);
                return;
            }

            this.imageView.setImageResource(arg4);
        }
    }
}

