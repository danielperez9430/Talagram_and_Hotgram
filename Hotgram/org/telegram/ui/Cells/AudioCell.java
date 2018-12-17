package org.telegram.ui.Cells;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController$AudioEntry;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;

public class AudioCell extends FrameLayout {
    public interface AudioCellDelegate {
        void startedPlayingAudio(MessageObject arg1);
    }

    private AudioEntry audioEntry;
    private TextView authorTextView;
    private CheckBox checkBox;
    private int currentAccount;
    private AudioCellDelegate delegate;
    private TextView genreTextView;
    private boolean needDivider;
    private ImageView playButton;
    private TextView timeTextView;
    private TextView titleTextView;

    public AudioCell(Context arg23) {
        AudioCell v0 = this;
        Context v1 = arg23;
        super(arg23);
        v0.currentAccount = UserConfig.selectedAccount;
        v0.playButton = new ImageView(v1);
        ImageView v2 = v0.playButton;
        int v4 = 3;
        int v3 = LocaleController.isRTL ? 5 : 3;
        int v8 = v3 | 48;
        float v13 = 13f;
        float v9 = LocaleController.isRTL ? 0f : 13f;
        float v10 = 13f;
        float v11 = LocaleController.isRTL ? 13f : 0f;
        v0.addView(((View)v2), LayoutHelper.createFrame(46, 46f, v8, v9, v10, v11, 0f));
        v0.playButton.setOnClickListener(new -$$Lambda$AudioCell$4mzKXwu8KpZzCgq5bTdFt-gHiq4(v0));
        v0.titleTextView = new TextView(v1);
        v0.titleTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.titleTextView.setTextSize(1, 16f);
        v0.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.titleTextView.setLines(1);
        v0.titleTextView.setMaxLines(1);
        v0.titleTextView.setSingleLine(true);
        v0.titleTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v2_1 = v0.titleTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.titleTextView;
        int v15 = -1;
        float v16 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        int v17 = v3 | 48;
        float v18 = LocaleController.isRTL ? 50f : 72f;
        float v19 = 7f;
        float v20 = LocaleController.isRTL ? 72f : 50f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v15, v16, v17, v18, v19, v20, 0f));
        v0.genreTextView = new TextView(v1);
        v0.genreTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        float v3_1 = 14f;
        v0.genreTextView.setTextSize(1, v3_1);
        v0.genreTextView.setLines(1);
        v0.genreTextView.setMaxLines(1);
        v0.genreTextView.setSingleLine(true);
        v0.genreTextView.setEllipsize(TextUtils$TruncateAt.END);
        v2_1 = v0.genreTextView;
        int v9_1 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v9_1 | 48);
        v2_1 = v0.genreTextView;
        v15 = -1;
        v16 = -2f;
        v9_1 = LocaleController.isRTL ? 5 : 3;
        v17 = v9_1 | 48;
        v18 = LocaleController.isRTL ? 50f : 72f;
        v19 = 28f;
        v20 = LocaleController.isRTL ? 72f : 50f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v15, v16, v17, v18, v19, v20, 0f));
        v0.authorTextView = new TextView(v1);
        v0.authorTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        v0.authorTextView.setTextSize(1, v3_1);
        v0.authorTextView.setLines(1);
        v0.authorTextView.setMaxLines(1);
        v0.authorTextView.setSingleLine(true);
        v0.authorTextView.setEllipsize(TextUtils$TruncateAt.END);
        v2_1 = v0.authorTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.authorTextView;
        v15 = -1;
        v16 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v17 = v3 | 48;
        v18 = LocaleController.isRTL ? 50f : 72f;
        v19 = 44f;
        v20 = LocaleController.isRTL ? 72f : 50f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v15, v16, v17, v18, v19, v20, 0f));
        v0.timeTextView = new TextView(v1);
        v0.timeTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        v0.timeTextView.setTextSize(1, v13);
        v0.timeTextView.setLines(1);
        v0.timeTextView.setMaxLines(1);
        v0.timeTextView.setSingleLine(true);
        v0.timeTextView.setEllipsize(TextUtils$TruncateAt.END);
        v2_1 = v0.timeTextView;
        v3 = LocaleController.isRTL ? 3 : 5;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.timeTextView;
        int v6 = -2;
        float v7 = -2f;
        v3 = LocaleController.isRTL ? 3 : 5;
        v8 = v3 | 48;
        v9 = LocaleController.isRTL ? 18f : 0f;
        v10 = 11f;
        v11 = LocaleController.isRTL ? 0f : 18f;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
        v0.checkBox = new CheckBox(v1, 2131231523);
        v0.checkBox.setVisibility(0);
        v0.checkBox.setColor(Theme.getColor("musicPicker_checkbox"), Theme.getColor("musicPicker_checkboxCheck"));
        CheckBox v1_1 = v0.checkBox;
        v6 = 22;
        v7 = 22f;
        if(LocaleController.isRTL) {
        }
        else {
            v4 = 5;
        }

        v8 = v4 | 48;
        v9 = LocaleController.isRTL ? 18f : 0f;
        v10 = 39f;
        v11 = LocaleController.isRTL ? 0f : 18f;
        v0.addView(((View)v1_1), LayoutHelper.createFrame(v6, v7, v8, v9, v10, v11, 0f));
    }

    public AudioEntry getAudioEntry() {
        return this.audioEntry;
    }

    public TextView getAuthorTextView() {
        return this.authorTextView;
    }

    public CheckBox getCheckBox() {
        return this.checkBox;
    }

    public TextView getGenreTextView() {
        return this.genreTextView;
    }

    public ImageView getPlayButton() {
        return this.playButton;
    }

    public TextView getTimeTextView() {
        return this.timeTextView;
    }

    public TextView getTitleTextView() {
        return this.titleTextView;
    }

    public static void lambda$new$0(AudioCell arg2, View arg3) {
        if(arg2.audioEntry != null) {
            if((MediaController.getInstance().isPlayingMessage(arg2.audioEntry.messageObject)) && !MediaController.getInstance().isMessagePaused()) {
                MediaController.getInstance().pauseMessage(arg2.audioEntry.messageObject);
                arg2.setPlayDrawable(false);
                return;
            }

            ArrayList v3 = new ArrayList();
            v3.add(arg2.audioEntry.messageObject);
            if(!MediaController.getInstance().setPlaylist(v3, arg2.audioEntry.messageObject)) {
                return;
            }

            arg2.setPlayDrawable(true);
            if(arg2.delegate == null) {
                return;
            }

            arg2.delegate.startedPlayingAudio(arg2.audioEntry.messageObject);
        }
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)AndroidUtilities.dp(72f)), ((float)(this.getHeight() - 1)), ((float)this.getWidth()), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(72f) + this.needDivider, 1073741824));
    }

    public void setAudio(AudioEntry arg6, boolean arg7, boolean arg8) {
        this.audioEntry = arg6;
        this.titleTextView.setText(this.audioEntry.title);
        this.genreTextView.setText(this.audioEntry.genre);
        this.authorTextView.setText(this.audioEntry.author);
        this.timeTextView.setText(String.format("%d:%02d", Integer.valueOf(this.audioEntry.duration / 60), Integer.valueOf(this.audioEntry.duration % 60)));
        boolean v6 = !MediaController.getInstance().isPlayingMessage(this.audioEntry.messageObject) || (MediaController.getInstance().isMessagePaused()) ? false : true;
        this.setPlayDrawable(v6);
        this.needDivider = arg7;
        this.setWillNotDraw((((int)arg7)) ^ 1);
        this.checkBox.setChecked(arg8, false);
    }

    public void setChecked(boolean arg3) {
        this.checkBox.setChecked(arg3, true);
    }

    public void setDelegate(AudioCellDelegate arg1) {
        this.delegate = arg1;
    }

    private void setPlayDrawable(boolean arg6) {
        float v0 = 46f;
        Drawable v1 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(v0), Theme.getColor("musicPicker_buttonBackground"), Theme.getColor("musicPicker_buttonBackground"));
        Resources v2 = this.getResources();
        int v6 = arg6 ? 2131230907 : 2131230908;
        Drawable v6_1 = v2.getDrawable(v6);
        v6_1.setColorFilter(new PorterDuffColorFilter(Theme.getColor("musicPicker_buttonIcon"), PorterDuff$Mode.MULTIPLY));
        CombinedDrawable v2_1 = new CombinedDrawable(v1, v6_1);
        v2_1.setCustomSize(AndroidUtilities.dp(v0), AndroidUtilities.dp(v0));
        this.playButton.setBackgroundDrawable(((Drawable)v2_1));
    }
}

