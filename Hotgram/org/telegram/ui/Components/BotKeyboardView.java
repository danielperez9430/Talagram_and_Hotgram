package org.telegram.ui.Components;

import android.content.Context;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.tgnet.TLRPC$KeyboardButton;
import org.telegram.tgnet.TLRPC$TL_keyboardButtonRow;
import org.telegram.tgnet.TLRPC$TL_replyKeyboardMarkup;
import org.telegram.ui.ActionBar.Theme;

public class BotKeyboardView extends LinearLayout {
    public interface BotKeyboardViewDelegate {
        void didPressedButton(KeyboardButton arg1);
    }

    private TL_replyKeyboardMarkup botButtons;
    private int buttonHeight;
    private ArrayList buttonViews;
    private LinearLayout container;
    private BotKeyboardViewDelegate delegate;
    private boolean isFullSize;
    private int panelHeight;
    private ScrollView scrollView;

    public BotKeyboardView(Context arg3) {
        super(arg3);
        this.buttonViews = new ArrayList();
        this.setOrientation(1);
        this.scrollView = new ScrollView(arg3);
        this.addView(this.scrollView);
        this.container = new LinearLayout(arg3);
        this.container.setOrientation(1);
        this.scrollView.addView(this.container);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor("chat_emojiPanelBackground"));
        this.setBackgroundColor(Theme.getColor("chat_emojiPanelBackground"));
    }

    static BotKeyboardViewDelegate access$000(BotKeyboardView arg0) {
        return arg0.delegate;
    }

    public int getKeyboardHeight() {
        int v0 = this.isFullSize ? this.panelHeight : this.botButtons.rows.size() * AndroidUtilities.dp(((float)this.buttonHeight)) + AndroidUtilities.dp(30f) + (this.botButtons.rows.size() - 1) * AndroidUtilities.dp(10f);
        return v0;
    }

    public void invalidateViews() {
        int v0;
        for(v0 = 0; v0 < this.buttonViews.size(); ++v0) {
            this.buttonViews.get(v0).invalidate();
        }
    }

    public boolean isFullSize() {
        return this.isFullSize;
    }

    public void setButtons(TL_replyKeyboardMarkup arg18) {
        BotKeyboardView v0 = this;
        TL_replyKeyboardMarkup v1 = arg18;
        v0.botButtons = v1;
        v0.container.removeAllViews();
        v0.buttonViews.clear();
        int v3 = 0;
        v0.scrollView.scrollTo(0, 0);
        if(v1 != null && v0.botButtons.rows.size() != 0) {
            v0.isFullSize = v1.resize ^ 1;
            float v5 = 10f;
            int v2 = !v0.isFullSize ? 42 : ((int)Math.max(42f, (((float)((v0.panelHeight - AndroidUtilities.dp(30f) - (v0.botButtons.rows.size() - 1) * AndroidUtilities.dp(v5)) / v0.botButtons.rows.size()))) / AndroidUtilities.density));
            v0.buttonHeight = v2;
            v2 = 0;
            while(v2 < v1.rows.size()) {
                Object v6 = v1.rows.get(v2);
                LinearLayout v7 = new LinearLayout(this.getContext());
                v7.setOrientation(v3);
                LinearLayout v8 = v0.container;
                int v9 = -1;
                int v10 = v0.buttonHeight;
                float v11 = 15f;
                float v13 = v2 == 0 ? 15f : 10f;
                float v14 = 15f;
                float v15 = v2 == v1.rows.size() - 1 ? 15f : 0f;
                v8.addView(((View)v7), LayoutHelper.createLinear(v9, v10, v11, v13, v14, v15));
                float v8_1 = 1f / (((float)((TL_keyboardButtonRow)v6).buttons.size()));
                v9 = 0;
                while(v9 < ((TL_keyboardButtonRow)v6).buttons.size()) {
                    Object v10_1 = ((TL_keyboardButtonRow)v6).buttons.get(v9);
                    TextView v15_1 = new TextView(this.getContext());
                    v15_1.setTag(v10_1);
                    v15_1.setTextColor(Theme.getColor("chat_botKeyboardButtonText"));
                    v15_1.setTextSize(1, 16f);
                    v15_1.setGravity(17);
                    v15_1.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4f), Theme.getColor("chat_botKeyboardButtonBackground"), Theme.getColor("chat_botKeyboardButtonBackgroundPressed")));
                    v15_1.setPadding(AndroidUtilities.dp(4f), v3, AndroidUtilities.dp(4f), v3);
                    v15_1.setText(Emoji.replaceEmoji(((KeyboardButton)v10_1).text, v15_1.getPaint().getFontMetricsInt(), AndroidUtilities.dp(16f), ((boolean)v3)));
                    int v11_1 = -1;
                    int v5_1 = v9 != ((TL_keyboardButtonRow)v6).buttons.size() - 1 ? 10 : 0;
                    v7.addView(v15_1, LayoutHelper.createLinear(0, v11_1, v8_1, 0, 0, v5_1, 0));
                    v15_1.setOnClickListener(new View$OnClickListener() {
                        public void onClick(View arg2) {
                            BotKeyboardView.this.delegate.didPressedButton(arg2.getTag());
                        }
                    });
                    v0.buttonViews.add(v15_1);
                    ++v9;
                    v3 = 0;
                }

                ++v2;
                v3 = 0;
            }
        }
    }

    public void setDelegate(BotKeyboardViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setPanelHeight(int arg6) {
        this.panelHeight = arg6;
        if((this.isFullSize) && this.botButtons != null && this.botButtons.rows.size() != 0) {
            arg6 = !this.isFullSize ? 42 : ((int)Math.max(42f, (((float)((this.panelHeight - AndroidUtilities.dp(30f) - (this.botButtons.rows.size() - 1) * AndroidUtilities.dp(10f)) / this.botButtons.rows.size()))) / AndroidUtilities.density));
            this.buttonHeight = arg6;
            arg6 = this.container.getChildCount();
            int v0 = AndroidUtilities.dp(((float)this.buttonHeight));
            int v1;
            for(v1 = 0; v1 < arg6; ++v1) {
                View v2 = this.container.getChildAt(v1);
                ViewGroup$LayoutParams v3 = v2.getLayoutParams();
                if(((LinearLayout$LayoutParams)v3).height != v0) {
                    ((LinearLayout$LayoutParams)v3).height = v0;
                    v2.setLayoutParams(v3);
                }
            }
        }
    }
}

