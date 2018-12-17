package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout$Alignment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;

public class BotHelpCell extends View {
    public interface BotHelpCellDelegate {
        void didPressUrl(String arg1);
    }

    private BotHelpCellDelegate delegate;
    private int height;
    private String oldText;
    private ClickableSpan pressedLink;
    private StaticLayout textLayout;
    private int textX;
    private int textY;
    private LinkPath urlPath;
    private int width;

    public BotHelpCell(Context arg1) {
        super(arg1);
        this.urlPath = new LinkPath();
    }

    protected void onDraw(Canvas arg6) {
        int v0 = (arg6.getWidth() - this.width) / 2;
        int v1 = AndroidUtilities.dp(4f);
        Theme.chat_msgInMediaShadowDrawable.setBounds(v0, v1, this.width + v0, this.height + v1);
        Theme.chat_msgInMediaShadowDrawable.draw(arg6);
        Theme.chat_msgInMediaDrawable.setBounds(v0, v1, this.width + v0, this.height + v1);
        Theme.chat_msgInMediaDrawable.draw(arg6);
        Theme.chat_msgTextPaint.setColor(Theme.getColor("chat_messageTextIn"));
        Theme.chat_msgTextPaint.linkColor = Theme.getColor("chat_messageLinkIn");
        arg6.save();
        int v3 = AndroidUtilities.dp(11f) + v0;
        this.textX = v3;
        int v2 = AndroidUtilities.dp(11f) + v1;
        this.textY = v2;
        arg6.translate(((float)v3), ((float)v2));
        if(this.pressedLink != null) {
            arg6.drawPath(this.urlPath, Theme.chat_urlPaint);
        }

        if(this.textLayout != null) {
            this.textLayout.draw(arg6);
        }

        arg6.restore();
    }

    protected void onMeasure(int arg2, int arg3) {
        this.setMeasuredDimension(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), this.height + AndroidUtilities.dp(8f));
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

    public void setDelegate(BotHelpCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setText(String arg12) {
        if(arg12 != null) {
            if(arg12.length() == 0) {
            }
            else {
                if(arg12 != null && this.oldText != null && (arg12.equals(this.oldText))) {
                    return;
                }

                this.oldText = arg12;
                int v0 = 0;
                this.setVisibility(0);
                float v2 = 0.7f;
                int v1 = AndroidUtilities.isTablet() ? AndroidUtilities.getMinTabletSide() : Math.min(AndroidUtilities.displaySize.x, AndroidUtilities.displaySize.y);
                v1 = ((int)((((float)v1)) * v2));
                String[] v12 = arg12.split("\n");
                SpannableStringBuilder v3 = new SpannableStringBuilder();
                String v2_1 = LocaleController.getString("BotInfoTitle", 2131624215);
                v3.append(((CharSequence)v2_1));
                v3.append("\n\n");
                int v4;
                for(v4 = 0; v4 < v12.length; ++v4) {
                    v3.append(v12[v4].trim());
                    if(v4 != v12.length - 1) {
                        v3.append("\n");
                    }
                }

                MessageObject.addLinks(false, ((CharSequence)v3));
                v3.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")), 0, v2_1.length(), 33);
                Emoji.replaceEmoji(((CharSequence)v3), Theme.chat_msgTextPaint.getFontMetricsInt(), AndroidUtilities.dp(20f), false);
                float v12_1 = 22f;
                try {
                    this.textLayout = new StaticLayout(((CharSequence)v3), Theme.chat_msgTextPaint, v1, Layout$Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    this.width = 0;
                    this.height = this.textLayout.getHeight() + AndroidUtilities.dp(v12_1);
                    int v2_2 = this.textLayout.getLineCount();
                    while(v0 < v2_2) {
                        this.width = ((int)Math.ceil(((double)Math.max(((float)this.width), this.textLayout.getLineWidth(v0) + this.textLayout.getLineLeft(v0)))));
                        ++v0;
                    }

                    if(this.width <= v1) {
                        goto label_105;
                    }

                    this.width = v1;
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }

            label_105:
                this.width += AndroidUtilities.dp(v12_1);
                return;
            }
        }

        this.setVisibility(8);
    }
}

