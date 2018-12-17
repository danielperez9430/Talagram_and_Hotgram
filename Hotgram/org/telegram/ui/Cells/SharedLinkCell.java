package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.Layout$Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$MessageEntity;
import org.telegram.tgnet.TLRPC$PhotoSize;
import org.telegram.tgnet.TLRPC$TL_messageEntityEmail;
import org.telegram.tgnet.TLRPC$TL_messageEntityTextUrl;
import org.telegram.tgnet.TLRPC$TL_messageEntityUrl;
import org.telegram.tgnet.TLRPC$TL_messageMediaWebPage;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.tgnet.TLRPC$WebPage;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.LinkPath;

public class SharedLinkCell extends FrameLayout {
    class org.telegram.ui.Cells.SharedLinkCell$1 {
    }

    class CheckForLongPress implements Runnable {
        public int currentPressCount;

        CheckForLongPress(SharedLinkCell arg1) {
            SharedLinkCell.this = arg1;
            super();
        }

        public void run() {
            if((SharedLinkCell.this.checkingForLongPress) && SharedLinkCell.this.getParent() != null && this.currentPressCount == SharedLinkCell.this.pressCount) {
                SharedLinkCell.this.checkingForLongPress = false;
                SharedLinkCell.this.performHapticFeedback(0);
                if(SharedLinkCell.this.pressedLink >= 0) {
                    SharedLinkCell.this.delegate.onLinkLongPress(SharedLinkCell.this.links.get(SharedLinkCell.this.pressedLink));
                }

                MotionEvent v0 = MotionEvent.obtain(0, 0, 3, 0f, 0f, 0);
                SharedLinkCell.this.onTouchEvent(v0);
                v0.recycle();
            }
        }
    }

    final class CheckForTap implements Runnable {
        CheckForTap(SharedLinkCell arg1, org.telegram.ui.Cells.SharedLinkCell$1 arg2) {
            this(arg1);
        }

        private CheckForTap(SharedLinkCell arg1) {
            SharedLinkCell.this = arg1;
            super();
        }

        public void run() {
            if(SharedLinkCell.this.pendingCheckForLongPress == null) {
                SharedLinkCell.this.pendingCheckForLongPress = new CheckForLongPress(SharedLinkCell.this);
            }

            SharedLinkCell.this.pendingCheckForLongPress.currentPressCount = SharedLinkCell.access$104(SharedLinkCell.this);
            SharedLinkCell.this.postDelayed(SharedLinkCell.this.pendingCheckForLongPress, ((long)(ViewConfiguration.getLongPressTimeout() - ViewConfiguration.getTapTimeout())));
        }
    }

    public interface SharedLinkCellDelegate {
        boolean canPerformActions();

        void needOpenWebView(WebPage arg1);

        void onLinkLongPress(String arg1);
    }

    private CheckBox checkBox;
    private boolean checkingForLongPress;
    private SharedLinkCellDelegate delegate;
    private int description2Y;
    private StaticLayout descriptionLayout;
    private StaticLayout descriptionLayout2;
    private TextPaint descriptionTextPaint;
    private int descriptionY;
    private boolean drawLinkImageView;
    private LetterDrawable letterDrawable;
    private ImageReceiver linkImageView;
    private ArrayList linkLayout;
    private boolean linkPreviewPressed;
    private int linkY;
    ArrayList links;
    private MessageObject message;
    private boolean needDivider;
    private CheckForLongPress pendingCheckForLongPress;
    private CheckForTap pendingCheckForTap;
    private int pressCount;
    private int pressedLink;
    private StaticLayout titleLayout;
    private TextPaint titleTextPaint;
    private int titleY;
    private LinkPath urlPath;

    public SharedLinkCell(Context arg9) {
        super(arg9);
        this.checkingForLongPress = false;
        this.pendingCheckForLongPress = null;
        this.pressCount = 0;
        this.pendingCheckForTap = null;
        this.urlPath = new LinkPath();
        this.links = new ArrayList();
        this.linkLayout = new ArrayList();
        this.titleY = AndroidUtilities.dp(7f);
        this.descriptionY = AndroidUtilities.dp(27f);
        this.description2Y = AndroidUtilities.dp(27f);
        this.titleTextPaint = new TextPaint(1);
        this.titleTextPaint.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.titleTextPaint.setColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.descriptionTextPaint = new TextPaint(1);
        this.titleTextPaint.setTextSize(((float)AndroidUtilities.dp(16f)));
        this.descriptionTextPaint.setTextSize(((float)AndroidUtilities.dp(16f)));
        this.setWillNotDraw(false);
        this.linkImageView = new ImageReceiver(((View)this));
        this.letterDrawable = new LetterDrawable();
        this.checkBox = new CheckBox(arg9, 2131231523);
        this.checkBox.setVisibility(4);
        this.checkBox.setColor(Theme.getColor("checkbox"), Theme.getColor("checkboxCheck"));
        CheckBox v9 = this.checkBox;
        int v0 = LocaleController.isRTL ? 5 : 3;
        int v3 = v0 | 48;
        float v4 = LocaleController.isRTL ? 0f : 44f;
        float v5 = 44f;
        float v6 = LocaleController.isRTL ? 44f : 0f;
        this.addView(((View)v9), LayoutHelper.createFrame(22, 22f, v3, v4, v5, v6, 0f));
    }

    static CheckForLongPress access$000(SharedLinkCell arg0) {
        return arg0.pendingCheckForLongPress;
    }

    static CheckForLongPress access$002(SharedLinkCell arg0, CheckForLongPress arg1) {
        arg0.pendingCheckForLongPress = arg1;
        return arg1;
    }

    static int access$100(SharedLinkCell arg0) {
        return arg0.pressCount;
    }

    static int access$104(SharedLinkCell arg1) {
        int v0 = arg1.pressCount + 1;
        arg1.pressCount = v0;
        return v0;
    }

    static boolean access$200(SharedLinkCell arg0) {
        return arg0.checkingForLongPress;
    }

    static boolean access$202(SharedLinkCell arg0, boolean arg1) {
        arg0.checkingForLongPress = arg1;
        return arg1;
    }

    static int access$300(SharedLinkCell arg0) {
        return arg0.pressedLink;
    }

    static SharedLinkCellDelegate access$400(SharedLinkCell arg0) {
        return arg0.delegate;
    }

    protected void cancelCheckLongPress() {
        this.checkingForLongPress = false;
        if(this.pendingCheckForLongPress != null) {
            this.removeCallbacks(this.pendingCheckForLongPress);
        }

        if(this.pendingCheckForTap != null) {
            this.removeCallbacks(this.pendingCheckForTap);
        }
    }

    public String getLink(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.links.size()) {
            }
            else {
                return this.links.get(arg2);
            }
        }

        return null;
    }

    public MessageObject getMessage() {
        return this.message;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.drawLinkImageView) {
            this.linkImageView.onAttachedToWindow();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.drawLinkImageView) {
            this.linkImageView.onDetachedFromWindow();
        }
    }

    protected void onDraw(Canvas arg8) {
        float v3_1;
        float v2_1;
        int v0_1;
        float v0;
        if(this.titleLayout != null) {
            arg8.save();
            v0 = LocaleController.isRTL ? 8f : ((float)AndroidUtilities.leftBaseline);
            arg8.translate(((float)AndroidUtilities.dp(v0)), ((float)this.titleY));
            this.titleLayout.draw(arg8);
            arg8.restore();
        }

        if(this.descriptionLayout != null) {
            this.descriptionTextPaint.setColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            arg8.save();
            v0 = LocaleController.isRTL ? 8f : ((float)AndroidUtilities.leftBaseline);
            arg8.translate(((float)AndroidUtilities.dp(v0)), ((float)this.descriptionY));
            this.descriptionLayout.draw(arg8);
            arg8.restore();
        }

        if(this.descriptionLayout2 != null) {
            this.descriptionTextPaint.setColor(Theme.getColor("windowBackgroundWhiteBlackText"));
            arg8.save();
            v0 = LocaleController.isRTL ? 8f : ((float)AndroidUtilities.leftBaseline);
            arg8.translate(((float)AndroidUtilities.dp(v0)), ((float)this.description2Y));
            this.descriptionLayout2.draw(arg8);
            arg8.restore();
        }

        if(!this.linkLayout.isEmpty()) {
            this.descriptionTextPaint.setColor(Theme.getColor("windowBackgroundWhiteLinkText"));
            v0_1 = 0;
            int v2 = 0;
            while(v0_1 < this.linkLayout.size()) {
                Object v3 = this.linkLayout.get(v0_1);
                if(((StaticLayout)v3).getLineCount() > 0) {
                    arg8.save();
                    float v4 = LocaleController.isRTL ? 8f : ((float)AndroidUtilities.leftBaseline);
                    arg8.translate(((float)AndroidUtilities.dp(v4)), ((float)(this.linkY + v2)));
                    if(this.pressedLink == v0_1) {
                        arg8.drawPath(this.urlPath, Theme.linkSelectionPaint);
                    }

                    ((StaticLayout)v3).draw(arg8);
                    arg8.restore();
                    v2 += ((StaticLayout)v3).getLineBottom(((StaticLayout)v3).getLineCount() - 1);
                }

                ++v0_1;
            }
        }

        this.letterDrawable.draw(arg8);
        if(this.drawLinkImageView) {
            this.linkImageView.draw(arg8);
        }

        if(this.needDivider) {
            if(LocaleController.isRTL) {
                v2_1 = 0f;
                v3_1 = ((float)(this.getMeasuredHeight() - 1));
                v0_1 = this.getMeasuredWidth() - AndroidUtilities.dp(((float)AndroidUtilities.leftBaseline));
            }
            else {
                v2_1 = ((float)AndroidUtilities.dp(((float)AndroidUtilities.leftBaseline)));
                v3_1 = ((float)(this.getMeasuredHeight() - 1));
                v0_1 = this.getMeasuredWidth();
            }

            arg8.drawLine(v2_1, v3_1, ((float)v0_1), ((float)(this.getMeasuredHeight() - 1)), Theme.dividerPaint);
        }
    }

    @SuppressLint(value={"DrawAllocation"}) protected void onMeasure(int arg28, int arg29) {
        int v9_3;
        int v16_1;
        Object v0_7;
        char v2;
        int v8_1;
        StaticLayout v5_2;
        float v10_1;
        Layout$Alignment v9_2;
        TextPaint v7_1;
        StaticLayout v0_6;
        CharSequence v6_2;
        String v15;
        String v16;
        ArrayList v0_5;
        int v0_4;
        String v11;
        String v3_1;
        int v6_1;
        String v5_1;
        int v14;
        String v7;
        String v6;
        int v5;
        SharedLinkCell v1 = this;
        v1.drawLinkImageView = false;
        StaticLayout v3 = null;
        v1.descriptionLayout = v3;
        v1.titleLayout = v3;
        v1.descriptionLayout2 = v3;
        v1.description2Y = v1.descriptionY;
        v1.linkLayout.clear();
        v1.links.clear();
        int v4 = View$MeasureSpec.getSize(arg28) - AndroidUtilities.dp(((float)AndroidUtilities.leftBaseline)) - AndroidUtilities.dp(8f);
        if(!(v1.message.messageOwner.media instanceof TL_messageMediaWebPage) || !(v1.message.messageOwner.media.webpage instanceof TL_webPage)) {
            v5_1 = ((String)v3);
            v6 = v5_1;
            v7 = v6;
            v14 = 0;
        }
        else {
            WebPage v0 = v1.message.messageOwner.media.webpage;
            if(v1.message.photoThumbs == null && v0.photo != null) {
                v1.message.generateThumbs(true);
            }

            v5 = v0.photo == null || v1.message.photoThumbs == null ? 0 : 1;
            v6 = v0.title;
            if(v6 == null) {
                v6 = v0.site_name;
            }

            v7 = v0.description;
            v14 = v5;
            v5_1 = v0.url;
        }

        if(v1.message != null && !v1.message.messageOwner.entities.isEmpty()) {
            String v0_1 = ((String)v3);
            String v8 = v7;
            v7 = v6;
            v6_1 = 0;
            while(true) {
                if(v6_1 < v1.message.messageOwner.entities.size()) {
                    Object v9 = v1.message.messageOwner.entities.get(v6_1);
                    if(((MessageEntity)v9).length > 0) {
                        if(((MessageEntity)v9).offset < 0) {
                        }
                        else if(((MessageEntity)v9).offset >= v1.message.messageOwner.message.length()) {
                        }
                        else {
                            if(((MessageEntity)v9).offset + ((MessageEntity)v9).length > v1.message.messageOwner.message.length()) {
                                ((MessageEntity)v9).length = v1.message.messageOwner.message.length() - ((MessageEntity)v9).offset;
                            }

                            if(v6_1 == 0 && v5_1 != null && (((MessageEntity)v9).offset != 0 || ((MessageEntity)v9).length != v1.message.messageOwner.message.length()) && (v1.message.messageOwner.entities.size() != 1 || v8 == null)) {
                                v0_1 = v1.message.messageOwner.message;
                            }

                            String v10 = v0_1;
                            try {
                                if((v9 instanceof TL_messageEntityTextUrl)) {
                                    goto label_178;
                                }
                                else if((v9 instanceof TL_messageEntityUrl)) {
                                    goto label_178;
                                }
                                else {
                                    if((v9 instanceof TL_messageEntityEmail)) {
                                        if(v7 != null && v7.length() != 0) {
                                            goto label_176;
                                        }

                                        v3_1 = "mailto:" + v1.message.messageOwner.message.substring(((MessageEntity)v9).offset, ((MessageEntity)v9).offset + ((MessageEntity)v9).length);
                                        v11 = v1.message.messageOwner.message.substring(((MessageEntity)v9).offset, ((MessageEntity)v9).offset + ((MessageEntity)v9).length);
                                        goto label_162;
                                    }

                                    goto label_176;
                                }
                            }
                            catch(Exception v0_2) {
                                goto label_261;
                            }

                            try {
                            label_162:
                                if(((MessageEntity)v9).offset != 0 || ((MessageEntity)v9).length != v1.message.messageOwner.message.length()) {
                                    v8 = v1.message.messageOwner.message;
                                }
                            }
                            catch(Exception v0_2) {
                                goto label_220;
                            }

                            v7 = v11;
                            goto label_237;
                        label_176:
                            v3_1 = null;
                            goto label_237;
                            try {
                            label_178:
                                v0_1 = (v9 instanceof TL_messageEntityUrl) ? v1.message.messageOwner.message.substring(((MessageEntity)v9).offset, ((MessageEntity)v9).offset + ((MessageEntity)v9).length) : ((MessageEntity)v9).url;
                                v3_1 = v0_1;
                                if(v7 != null) {
                                    if(v7.length() == 0) {
                                        goto label_194;
                                    }

                                    goto label_237;
                                }
                            }
                            catch(Exception v0_2) {
                                goto label_261;
                            }

                            try {
                            label_194:
                                v0_1 = Uri.parse(v3_1).getHost();
                                if(v0_1 != null) {
                                    goto label_199;
                                }
                            }
                            catch(Exception v0_2) {
                                v7 = v3_1;
                                goto label_261;
                            }

                            v7 = v3_1;
                            goto label_200;
                        label_199:
                            v7 = v0_1;
                        label_200:
                            if(v7 != null) {
                                v0_4 = 46;
                                try {
                                    int v11_1 = v7.lastIndexOf(v0_4);
                                    if(v11_1 >= 0) {
                                        v11 = v7.substring(0, v11_1);
                                        goto label_205;
                                    }

                                    goto label_222;
                                }
                                catch(Exception v0_2) {
                                    goto label_261;
                                }

                                try {
                                label_205:
                                    v0_4 = v11.lastIndexOf(v0_4);
                                    if(v0_4 >= 0) {
                                        v11 = v11.substring(v0_4 + 1);
                                    }

                                    v7 = v11.substring(0, 1).toUpperCase() + v11.substring(1);
                                }
                                catch(Exception v0_2) {
                                label_220:
                                    v7 = v11;
                                    goto label_261;
                                }
                            }

                            try {
                            label_222:
                                v0_1 = ((MessageEntity)v9).offset != 0 || ((MessageEntity)v9).length != v1.message.messageOwner.message.length() ? v1.message.messageOwner.message : v8;
                                v8 = v0_1;
                            label_237:
                                if(v3_1 == null) {
                                    goto label_262;
                                }

                                if(v3_1.toLowerCase().indexOf("http") == 0 || v3_1.toLowerCase().indexOf("mailto") == 0) {
                                    v0_5 = v1.links;
                                }
                                else {
                                    v0_5 = v1.links;
                                    v3_1 = "http://" + v3_1;
                                }

                                v0_5.add(v3_1);
                                goto label_262;
                            }
                            catch(Exception v0_2) {
                            }

                        label_261:
                            FileLog.e(((Throwable)v0_2));
                        label_262:
                            v0_1 = v10;
                        }

                        goto label_263;
                    }
                    else {
                    label_263:
                        ++v6_1;
                        continue;
                    }
                }
                else {
                    break;
                }

                goto label_270;
            }

            v16 = v0_1;
            v3_1 = v7;
            v15 = v8;
        }
        else {
        label_270:
            v3_1 = v6;
            v15 = v7;
            v16 = null;
        }

        if(v5_1 != null && (v1.links.isEmpty())) {
            v1.links.add(v5_1);
        }

        char v12 = ' ';
        char v11_2 = '\n';
        if(v3_1 != null) {
            try {
                v6_2 = TextUtils.ellipsize(v3_1.replace(v11_2, v12), v1.titleTextPaint, ((float)Math.min(((int)Math.ceil(((double)v1.titleTextPaint.measureText(v3_1)))), v4)), TextUtils$TruncateAt.END);
                v0_6 = null;
                v7_1 = v1.titleTextPaint;
                v9_2 = Layout$Alignment.ALIGN_NORMAL;
                v10_1 = 1f;
                v5_2 = v0_6;
                v8_1 = v4;
                v2 = ' ';
            }
            catch(Exception v0_2) {
                v2 = ' ';
                goto label_312;
            }

            try {
                super(v6_2, v7_1, v8_1, v9_2, v10_1, 0f, false);
                v1.titleLayout = v0_6;
                goto label_313;
            }
            catch(Exception v0_2) {
            }

        label_312:
            FileLog.e(((Throwable)v0_2));
        label_313:
            v1.letterDrawable.setTitle(v3_1);
        }
        else {
            v2 = ' ';
        }

        float v3_2 = 1f;
        if(v15 != null) {
            try {
                v1.descriptionLayout = ChatMessageCell.generateStaticLayout(v15, v1.descriptionTextPaint, v4, v4, 0, 3);
                if(v1.descriptionLayout.getLineCount() <= 0) {
                    goto label_343;
                }

                v1.description2Y = v1.descriptionY + v1.descriptionLayout.getLineBottom(v1.descriptionLayout.getLineCount() - 1) + AndroidUtilities.dp(v3_2);
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }
        }

    label_343:
        float v15_1 = 10f;
        if(v16 != null) {
            try {
                v1.descriptionLayout2 = ChatMessageCell.generateStaticLayout(v16, v1.descriptionTextPaint, v4, v4, 0, 3);
                v1.descriptionLayout2.getLineBottom(v1.descriptionLayout2.getLineCount() - 1);
                if(v1.descriptionLayout == null) {
                    goto label_367;
                }

                v1.description2Y += AndroidUtilities.dp(v15_1);
            }
            catch(Exception v0_2) {
                FileLog.e(((Throwable)v0_2));
            }
        }

    label_367:
        if(!v1.links.isEmpty()) {
            int v12_1;
            for(v12_1 = 0; v12_1 < v1.links.size(); v12_1 = v16_1 + 1) {
                try {
                    v0_7 = v1.links.get(v12_1);
                    v6_2 = TextUtils.ellipsize(((String)v0_7).replace('\n', v2), v1.descriptionTextPaint, ((float)Math.min(((int)Math.ceil(((double)v1.descriptionTextPaint.measureText(((String)v0_7))))), v4)), TextUtils$TruncateAt.MIDDLE);
                    v0_7 = null;
                    v7_1 = v1.descriptionTextPaint;
                    v9_2 = Layout$Alignment.ALIGN_NORMAL;
                    v10_1 = 1f;
                    v5_2 = ((StaticLayout)v0_7);
                    v8_1 = v4;
                    v16_1 = v12_1;
                }
                catch(Exception v0_2) {
                    v16_1 = 0;
                    goto label_426;
                }

                try {
                    super(v6_2, v7_1, v8_1, v9_2, v10_1, 0f, false);
                    v1.linkY = v1.description2Y;
                    if(v1.descriptionLayout2 != null && v1.descriptionLayout2.getLineCount() != 0) {
                        v1.linkY += v1.descriptionLayout2.getLineBottom(v1.descriptionLayout2.getLineCount() - 1) + AndroidUtilities.dp(v3_2);
                    }

                    v1.linkLayout.add(v0_7);
                    goto label_427;
                }
                catch(Exception v0_2) {
                }

            label_426:
                FileLog.e(((Throwable)v0_2));
            label_427:
            }
        }

        v0_4 = AndroidUtilities.dp(52f);
        int v2_1 = LocaleController.isRTL ? View$MeasureSpec.getSize(arg28) - AndroidUtilities.dp(v15_1) - v0_4 : AndroidUtilities.dp(v15_1);
        v1.letterDrawable.setBounds(v2_1, AndroidUtilities.dp(v15_1), v2_1 + v0_4, AndroidUtilities.dp(62f));
        if(v14 != 0) {
            PhotoSize v3_3 = FileLoader.getClosestPhotoSizeWithSize(v1.message.photoThumbs, v0_4, true);
            PhotoSize v4_1 = FileLoader.getClosestPhotoSizeWithSize(v1.message.photoThumbs, 80);
            if(v4_1 == v3_3) {
                v4_1 = null;
            }

            v5 = -1;
            v3_3.size = v5;
            if(v4_1 != null) {
                v4_1.size = v5;
            }

            v1.linkImageView.setImageCoords(v2_1, AndroidUtilities.dp(v15_1), v0_4, v0_4);
            FileLoader.getAttachFileName(((TLObject)v3_3));
            Locale v2_2 = Locale.US;
            v6_1 = 2;
            String v21 = String.format(v2_2, "%d_%d", Integer.valueOf(v0_4), Integer.valueOf(v0_4));
            ImageReceiver v2_3 = v1.linkImageView;
            FileLocation v3_4 = v3_3.location;
            FileLocation v22 = v4_1 != null ? v4_1.location : null;
            Locale v4_2 = Locale.US;
            v2_3.setImage(v3_4, v21, v22, String.format(v4_2, "%d_%d_b", Integer.valueOf(v0_4), Integer.valueOf(v0_4)), 0, null, 0);
            v1.drawLinkImageView = true;
        }

        if(v1.titleLayout == null || v1.titleLayout.getLineCount() == 0) {
            v9_3 = 0;
            v2_1 = 0;
        }
        else {
            v9_3 = 0;
            v2_1 = v1.titleLayout.getLineBottom(v1.titleLayout.getLineCount() - 1);
        }

        if(v1.descriptionLayout != null && v1.descriptionLayout.getLineCount() != 0) {
            v2_1 += v1.descriptionLayout.getLineBottom(v1.descriptionLayout.getLineCount() - 1);
        }

        if(v1.descriptionLayout2 != null && v1.descriptionLayout2.getLineCount() != 0) {
            v2_1 += v1.descriptionLayout2.getLineBottom(v1.descriptionLayout2.getLineCount() - 1);
            if(v1.descriptionLayout != null) {
                v2_1 += AndroidUtilities.dp(v15_1);
            }
        }

        while(v9_3 < v1.linkLayout.size()) {
            v0_7 = v1.linkLayout.get(v9_3);
            if(((StaticLayout)v0_7).getLineCount() > 0) {
                v2_1 += ((StaticLayout)v0_7).getLineBottom(((StaticLayout)v0_7).getLineCount() - 1);
            }

            ++v9_3;
        }

        if(v14 != 0) {
            v2_1 = Math.max(AndroidUtilities.dp(48f), v2_1);
        }

        v1.checkBox.measure(View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22f), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(22f), 1073741824));
        v1.setMeasuredDimension(View$MeasureSpec.getSize(arg28), Math.max(AndroidUtilities.dp(72f), v2_1 + AndroidUtilities.dp(16f)) + v1.needDivider);
    }

    public boolean onTouchEvent(MotionEvent arg12) {
        int v3;
        boolean v1 = true;
        if(this.message == null || (this.linkLayout.isEmpty()) || this.delegate == null || !this.delegate.canPerformActions()) {
        label_123:
            this.resetPressedLink();
        }
        else {
            if(arg12.getAction() != 0 && (!this.linkPreviewPressed || arg12.getAction() != 1)) {
                if(arg12.getAction() != 3) {
                    goto label_124;
                }

                goto label_123;
            }

            int v0 = ((int)arg12.getX());
            v3 = ((int)arg12.getY());
            int v4 = 0;
            int v5 = 0;
            while(v4 < this.linkLayout.size()) {
                Object v6 = this.linkLayout.get(v4);
                if(((StaticLayout)v6).getLineCount() > 0) {
                    int v7 = ((StaticLayout)v6).getLineBottom(((StaticLayout)v6).getLineCount() - 1);
                    float v8 = LocaleController.isRTL ? 8f : ((float)AndroidUtilities.leftBaseline);
                    float v9 = ((float)v0);
                    v8 = ((float)AndroidUtilities.dp(v8));
                    if(v9 >= ((StaticLayout)v6).getLineLeft(0) + v8 && v9 <= v8 + ((StaticLayout)v6).getLineWidth(0) && v3 >= this.linkY + v5 && v3 <= this.linkY + v5 + v7) {
                        if(arg12.getAction() == 0) {
                            this.resetPressedLink();
                            this.pressedLink = v4;
                            this.linkPreviewPressed = true;
                            this.startCheckLongPress();
                            try {
                                this.urlPath.setCurrentLayout(((StaticLayout)v6), 0, 0f);
                                ((StaticLayout)v6).getSelectionPath(0, ((StaticLayout)v6).getText().length(), this.urlPath);
                            }
                            catch(Exception v0_1) {
                                FileLog.e(((Throwable)v0_1));
                            }

                            goto label_77;
                        }
                        else if(this.linkPreviewPressed) {
                            try {
                                WebPage v0_2 = this.pressedLink != 0 || this.message.messageOwner.media == null ? null : this.message.messageOwner.media.webpage;
                                if(v0_2 != null && v0_2.embed_url != null && v0_2.embed_url.length() != 0) {
                                    this.delegate.needOpenWebView(v0_2);
                                    goto label_111;
                                }

                                Browser.openUrl(this.getContext(), this.links.get(this.pressedLink));
                            }
                            catch(Exception v0_1) {
                                FileLog.e(((Throwable)v0_1));
                            }

                        label_111:
                            this.resetPressedLink();
                        label_77:
                            v0 = 1;
                            v3 = 1;
                            goto label_120;
                        }
                        else {
                            v0 = 1;
                            goto label_119;
                        }
                    }

                    v5 += v7;
                }

                ++v4;
            }

            v0 = 0;
        label_119:
            v3 = 0;
        label_120:
            if(v0 != 0) {
                goto label_125;
            }

            this.resetPressedLink();
            goto label_125;
        }

    label_124:
        v3 = 0;
    label_125:
        if(v3 == 0) {
            if(super.onTouchEvent(arg12)) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    protected void resetPressedLink() {
        this.pressedLink = -1;
        this.linkPreviewPressed = false;
        this.cancelCheckLongPress();
        this.invalidate();
    }

    public void setChecked(boolean arg3, boolean arg4) {
        if(this.checkBox.getVisibility() != 0) {
            this.checkBox.setVisibility(0);
        }

        this.checkBox.setChecked(arg3, arg4);
    }

    public void setDelegate(SharedLinkCellDelegate arg1) {
        this.delegate = arg1;
    }

    public void setLink(MessageObject arg1, boolean arg2) {
        this.needDivider = arg2;
        this.resetPressedLink();
        this.message = arg1;
        this.requestLayout();
    }

    protected void startCheckLongPress() {
        if(this.checkingForLongPress) {
            return;
        }

        this.checkingForLongPress = true;
        if(this.pendingCheckForTap == null) {
            this.pendingCheckForTap = new CheckForTap(this, null);
        }

        this.postDelayed(this.pendingCheckForTap, ((long)ViewConfiguration.getTapTimeout()));
    }
}

