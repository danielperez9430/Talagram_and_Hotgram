package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.ActionBar.Theme;

public class ChatBigEmptyView extends LinearLayout {
    private ArrayList imageViews;
    private TextView secretViewStatusTextView;
    private ArrayList textViews;

    public ChatBigEmptyView(Context arg25, boolean arg26) {
        float v20;
        float v19_1;
        String v11_1;
        int v10;
        ImageView v6_1;
        LinearLayout$LayoutParams v7;
        TextView v6;
        ChatBigEmptyView v0 = this;
        Context v1 = arg25;
        super(arg25);
        v0.textViews = new ArrayList();
        v0.imageViews = new ArrayList();
        v0.setBackgroundResource(2131231591);
        this.getBackground().setColorFilter(Theme.colorFilter);
        float v2 = 16f;
        v0.setPadding(AndroidUtilities.dp(v2), AndroidUtilities.dp(12f), AndroidUtilities.dp(v2), AndroidUtilities.dp(12f));
        v0.setOrientation(1);
        float v4 = 15f;
        int v5 = -2;
        if(arg26) {
            v0.secretViewStatusTextView = new TextView(v1);
            v0.secretViewStatusTextView.setTextSize(1, v4);
            v0.secretViewStatusTextView.setTextColor(Theme.getColor("chat_serviceText"));
            v0.secretViewStatusTextView.setGravity(1);
            v0.secretViewStatusTextView.setMaxWidth(AndroidUtilities.dp(210f));
            v0.textViews.add(v0.secretViewStatusTextView);
            v6 = v0.secretViewStatusTextView;
            v7 = LayoutHelper.createLinear(v5, v5, 49);
        }
        else {
            v6_1 = new ImageView(v1);
            v6_1.setImageResource(2131230996);
            v7 = LayoutHelper.createLinear(-2, -2, 49, 0, 2, 0, 0);
        }

        v0.addView(((View)v6_1), ((ViewGroup$LayoutParams)v7));
        v6 = new TextView(v1);
        if(arg26) {
            v6.setText(LocaleController.getString("EncryptedDescriptionTitle", 2131624676));
            v6.setTextSize(1, v4);
        }
        else {
            v6.setText(LocaleController.getString("ChatYourSelfTitle", 2131624402));
            v6.setTextSize(1, v2);
            v6.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            v6.setGravity(1);
        }

        v6.setTextColor(Theme.getColor("chat_serviceText"));
        v0.textViews.add(v6);
        v2 = 260f;
        v6.setMaxWidth(AndroidUtilities.dp(v2));
        int v8 = -2;
        int v9 = -2;
        if(!arg26) {
            v10 = 1;
        }
        else if(LocaleController.isRTL) {
            v10 = 5;
        }
        else {
            v10 = 3;
        }

        v10 |= 48;
        int v12 = 8;
        int v16 = arg26 ? 0 : 8;
        v0.addView(((View)v6), LayoutHelper.createLinear(v8, v9, v10, 0, v12, 0, v16));
        int v6_2;
        for(v6_2 = 0; v6_2 < 4; ++v6_2) {
            LinearLayout v8_1 = new LinearLayout(v1);
            v8_1.setOrientation(0);
            int v17 = -2;
            int v18 = -2;
            int v19 = LocaleController.isRTL ? 5 : 3;
            v0.addView(((View)v8_1), LayoutHelper.createLinear(v17, v18, v19, 0, 8, 0, 0));
            ImageView v9_1 = new ImageView(v1);
            v9_1.setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_serviceText"), PorterDuff$Mode.MULTIPLY));
            v10 = arg26 ? 2131231187 : 2131231308;
            v9_1.setImageResource(v10);
            v0.imageViews.add(v9_1);
            TextView v10_1 = new TextView(v1);
            v10_1.setTextSize(1, v4);
            v10_1.setTextColor(Theme.getColor("chat_serviceText"));
            v0.textViews.add(v10_1);
            int v11 = LocaleController.isRTL ? 5 : 3;
            v10_1.setGravity(v11 | 16);
            v10_1.setMaxWidth(AndroidUtilities.dp(v2));
            switch(v6_2) {
                case 0: {
                    if(arg26) {
                        v11_1 = "EncryptedDescription1";
                        v12 = 2131624672;
                        goto label_189;
                    }

                    v11_1 = "ChatYourSelfDescription1";
                    v12 = 2131624397;
                    break;
                }
                case 1: {
                    if(arg26) {
                        v11_1 = "EncryptedDescription2";
                        v12 = 2131624673;
                        goto label_189;
                    }

                    v11_1 = "ChatYourSelfDescription2";
                    v12 = 2131624398;
                    break;
                }
                case 2: {
                    if(arg26) {
                        v11_1 = "EncryptedDescription3";
                        v12 = 2131624674;
                        goto label_189;
                    }

                    v11_1 = "ChatYourSelfDescription3";
                    v12 = 2131624399;
                    break;
                }
                case 3: {
                    if(arg26) {
                        v11_1 = "EncryptedDescription4";
                        v12 = 2131624675;
                        goto label_189;
                    }

                    v11_1 = "ChatYourSelfDescription4";
                    v12 = 2131624400;
                    break;
                }
                default: {
                    goto label_195;
                }
            }

        label_189:
            v10_1.setText(LocaleController.getString(v11_1, v12));
        label_195:
            if(LocaleController.isRTL) {
                v8_1.addView(((View)v10_1), LayoutHelper.createLinear(v5, v5));
                if(arg26) {
                    v17 = -2;
                    v18 = -2;
                    v19_1 = 8f;
                    v20 = 3f;
                }
                else {
                    v17 = -2;
                    v18 = -2;
                    v19_1 = 8f;
                    v20 = 7f;
                }

                v8_1.addView(((View)v9_1), LayoutHelper.createLinear(v17, v18, v19_1, v20, 0f, 0f));
            }
            else {
                if(arg26) {
                    v17 = -2;
                    v18 = -2;
                    v19_1 = 0f;
                    v20 = 4f;
                }
                else {
                    v17 = -2;
                    v18 = -2;
                    v19_1 = 0f;
                    v20 = 8f;
                }

                v8_1.addView(((View)v9_1), LayoutHelper.createLinear(v17, v18, v19_1, v20, 8f, 0f));
                v8_1.addView(((View)v10_1), LayoutHelper.createLinear(v5, v5));
            }
        }
    }

    public void setSecretText(String arg2) {
        this.secretViewStatusTextView.setText(((CharSequence)arg2));
    }

    public void setTextColor(int arg5) {
        int v0 = 0;
        int v1;
        for(v1 = 0; v1 < this.textViews.size(); ++v1) {
            this.textViews.get(v1).setTextColor(arg5);
        }

        while(v0 < this.imageViews.size()) {
            this.imageViews.get(v0).setColorFilter(new PorterDuffColorFilter(Theme.getColor("chat_serviceText"), PorterDuff$Mode.MULTIPLY));
            ++v0;
        }
    }
}

