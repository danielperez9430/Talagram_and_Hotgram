package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController$SharingLocationInfo;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;

public class SendLocationCell extends FrameLayout {
    class org.telegram.ui.Cells.SendLocationCell$1 implements Runnable {
        org.telegram.ui.Cells.SendLocationCell$1(SendLocationCell arg1) {
            SendLocationCell.this = arg1;
            super();
        }

        public void run() {
            SendLocationCell.this.checkText();
            SendLocationCell.this.invalidate((((int)SendLocationCell.this.rect.left)) - 5, (((int)SendLocationCell.this.rect.top)) - 5, (((int)SendLocationCell.this.rect.right)) + 5, (((int)SendLocationCell.this.rect.bottom)) + 5);
            AndroidUtilities.runOnUIThread(SendLocationCell.this.invalidateRunnable, 1000);
        }
    }

    private SimpleTextView accurateTextView;
    private int currentAccount;
    private long dialogId;
    private ImageView imageView;
    private Runnable invalidateRunnable;
    private RectF rect;
    private SimpleTextView titleTextView;

    public SendLocationCell(Context arg12, boolean arg13) {
        CombinedDrawable v3_1;
        Drawable v2_2;
        super(arg12);
        this.currentAccount = UserConfig.selectedAccount;
        this.invalidateRunnable = new org.telegram.ui.Cells.SendLocationCell$1(this);
        this.imageView = new ImageView(arg12);
        ImageView v0 = this.imageView;
        String v1 = arg13 ? "location_sendLiveLocationBackgroundlocation_sendLiveLocationIcon" : "location_sendLocationBackgroundlocation_sendLocationIcon";
        v0.setTag(v1);
        float v0_1 = 40f;
        int v1_1 = AndroidUtilities.dp(v0_1);
        String v2 = arg13 ? "location_sendLiveLocationBackground" : "location_sendLocationBackground";
        int v2_1 = Theme.getColor(v2);
        String v3 = arg13 ? "location_sendLiveLocationBackground" : "location_sendLocationBackground";
        Drawable v1_2 = Theme.createSimpleSelectorCircleDrawable(v1_1, v2_1, Theme.getColor(v3));
        if(arg13) {
            this.rect = new RectF();
            v2_2 = this.getResources().getDrawable(2131231318);
            v2_2.setColorFilter(new PorterDuffColorFilter(Theme.getColor("location_sendLiveLocationIcon"), PorterDuff$Mode.MULTIPLY));
            v3_1 = new CombinedDrawable(v1_2, v2_2);
            v3_1.setCustomSize(AndroidUtilities.dp(v0_1), AndroidUtilities.dp(v0_1));
            this.imageView.setBackgroundDrawable(((Drawable)v3_1));
            AndroidUtilities.runOnUIThread(this.invalidateRunnable, 1000);
            this.setWillNotDraw(false);
        }
        else {
            v2_2 = this.getResources().getDrawable(2131231478);
            v2_2.setColorFilter(new PorterDuffColorFilter(Theme.getColor("location_sendLocationIcon"), PorterDuff$Mode.MULTIPLY));
            v3_1 = new CombinedDrawable(v1_2, v2_2);
            v3_1.setCustomSize(AndroidUtilities.dp(v0_1), AndroidUtilities.dp(v0_1));
            v3_1.setIconSize(AndroidUtilities.dp(24f), AndroidUtilities.dp(24f));
            this.imageView.setBackgroundDrawable(((Drawable)v3_1));
        }

        v0 = this.imageView;
        v1_1 = 40;
        float v2_3 = 40f;
        int v8 = 3;
        int v3_2 = LocaleController.isRTL ? 5 : 3;
        v3_2 |= 48;
        float v6 = 0f;
        float v4 = LocaleController.isRTL ? 0f : 17f;
        float v7 = 13f;
        if(LocaleController.isRTL) {
            v6 = 17f;
        }

        this.addView(((View)v0), LayoutHelper.createFrame(v1_1, v2_3, v3_2, v4, v7, v6, 0f));
        this.titleTextView = new SimpleTextView(arg12);
        this.titleTextView.setTextSize(16);
        SimpleTextView v0_2 = this.titleTextView;
        v1 = arg13 ? "windowBackgroundWhiteRedText2" : "windowBackgroundWhiteBlueText7";
        v0_2.setTag(v1);
        v0_2 = this.titleTextView;
        String v13 = arg13 ? "windowBackgroundWhiteRedText2" : "windowBackgroundWhiteBlueText7";
        v0_2.setTextColor(Theme.getColor(v13));
        SimpleTextView v13_1 = this.titleTextView;
        int v0_3 = LocaleController.isRTL ? 5 : 3;
        v13_1.setGravity(v0_3);
        this.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v13_1 = this.titleTextView;
        v0_3 = -1;
        float v1_3 = 20f;
        v2_1 = LocaleController.isRTL ? 5 : 3;
        v2_1 |= 48;
        float v3_3 = LocaleController.isRTL ? 16f : 73f;
        v4 = 12f;
        float v5 = LocaleController.isRTL ? 73f : 16f;
        this.addView(((View)v13_1), LayoutHelper.createFrame(v0_3, v1_3, v2_1, v3_3, v4, v5, 0f));
        this.accurateTextView = new SimpleTextView(arg12);
        this.accurateTextView.setTextSize(14);
        this.accurateTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        SimpleTextView v12 = this.accurateTextView;
        int v13_2 = LocaleController.isRTL ? 5 : 3;
        v12.setGravity(v13_2);
        v12 = this.accurateTextView;
        v0_3 = -1;
        v1_3 = 20f;
        if(LocaleController.isRTL) {
            v8 = 5;
        }

        v2_1 = v8 | 48;
        v3_3 = LocaleController.isRTL ? 16f : 73f;
        v4 = 37f;
        v5 = LocaleController.isRTL ? 73f : 16f;
        this.addView(((View)v12), LayoutHelper.createFrame(v0_3, v1_3, v2_1, v3_3, v4, v5, 0f));
    }

    static void access$000(SendLocationCell arg0) {
        arg0.checkText();
    }

    static RectF access$100(SendLocationCell arg0) {
        return arg0.rect;
    }

    static Runnable access$200(SendLocationCell arg0) {
        return arg0.invalidateRunnable;
    }

    private void checkText() {
        SharingLocationInfo v0 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        if(v0 != null) {
            String v1 = LocaleController.getString("StopLiveLocation", 2131626156);
            int v0_1 = v0.messageObject.messageOwner.edit_date != 0 ? v0.messageObject.messageOwner.edit_date : v0.messageObject.messageOwner.date;
            long v2 = ((long)v0_1);
            this.setText(v1, LocaleController.formatLocationUpdateDate(v2));
        }
        else {
            this.setText(LocaleController.getString("SendLiveLocation", 2131626007), LocaleController.getString("SendLiveLocationInfo", 2131626011));
        }
    }

    private ImageView getImageView() {
        return this.imageView;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.rect != null) {
            AndroidUtilities.runOnUIThread(this.invalidateRunnable, 1000);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
    }

    protected void onDraw(Canvas arg12) {
        SharingLocationInfo v0 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        if(v0 == null) {
            return;
        }

        int v1 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if(v0.stopTime < v1) {
            return;
        }

        float v2 = (((float)Math.abs(v0.stopTime - v1))) / (((float)v0.period));
        float v4 = 48f;
        float v5 = 43f;
        float v6 = 18f;
        float v7 = 13f;
        if(LocaleController.isRTL) {
            this.rect.set(((float)AndroidUtilities.dp(v7)), ((float)AndroidUtilities.dp(v6)), ((float)AndroidUtilities.dp(v5)), ((float)AndroidUtilities.dp(v4)));
        }
        else {
            this.rect.set(((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v5))), ((float)AndroidUtilities.dp(v6)), ((float)(this.getMeasuredWidth() - AndroidUtilities.dp(v7))), ((float)AndroidUtilities.dp(v4)));
        }

        int v3 = Theme.getColor("location_liveLocationProgress");
        Theme.chat_radialProgress2Paint.setColor(v3);
        Theme.chat_livePaint.setColor(v3);
        arg12.drawArc(this.rect, -90f, v2 * -360f, false, Theme.chat_radialProgress2Paint);
        String v0_1 = LocaleController.formatLocationLeftTime(Math.abs(v0.stopTime - v1));
        arg12.drawText(v0_1, this.rect.centerX() - Theme.chat_livePaint.measureText(v0_1) / 2f, ((float)AndroidUtilities.dp(37f)), Theme.chat_livePaint);
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(66f), 1073741824));
    }

    public void setDialogId(long arg1) {
        this.dialogId = arg1;
        this.checkText();
    }

    public void setHasLocation(boolean arg5) {
        if(LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId) == null) {
            SimpleTextView v0 = this.titleTextView;
            float v1 = 0.5f;
            float v3 = arg5 ? 1f : 0.5f;
            v0.setAlpha(v3);
            v0 = this.accurateTextView;
            v3 = arg5 ? 1f : 0.5f;
            v0.setAlpha(v3);
            ImageView v0_1 = this.imageView;
            if(arg5) {
                v1 = 1f;
            }

            v0_1.setAlpha(v1);
        }
    }

    public void setText(String arg2, String arg3) {
        this.titleTextView.setText(((CharSequence)arg2));
        this.accurateTextView.setText(((CharSequence)arg3));
    }
}

