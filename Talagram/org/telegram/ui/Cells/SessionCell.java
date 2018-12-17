package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.tgnet.TLRPC$TL_authorization;
import org.telegram.tgnet.TLRPC$TL_webAuthorization;
import org.telegram.tgnet.TLRPC$User;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class SessionCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private int currentAccount;
    private TextView detailExTextView;
    private TextView detailTextView;
    private BackupImageView imageView;
    private TextView nameTextView;
    private boolean needDivider;
    private TextView onlineTextView;

    public SessionCell(Context arg18, int arg19) {
        LinearLayout$LayoutParams v5_2;
        float v13_1;
        float v12_1;
        int v11;
        float v10;
        int v9;
        SessionCell v0 = this;
        Context v1 = arg18;
        super(arg18);
        v0.currentAccount = UserConfig.selectedAccount;
        LinearLayout v2 = new LinearLayout(v1);
        int v3 = 0;
        v2.setOrientation(0);
        v2.setWeightSum(1f);
        int v4 = 17;
        int v6 = 3;
        if(arg19 == 1) {
            v9 = -1;
            v10 = 30f;
            v11 = LocaleController.isRTL ? 5 : 3;
            v11 |= 48;
            int v13 = 45;
            int v12 = LocaleController.isRTL ? 11 : 45;
            v12_1 = ((float)v12);
            float v14 = 11f;
            if(LocaleController.isRTL) {
            }
            else {
                v13 = 11;
            }

            v0.addView(((View)v2), LayoutHelper.createFrame(v9, v10, v11, v12_1, v14, ((float)v13), 0f));
            v0.avatarDrawable = new AvatarDrawable();
            v0.avatarDrawable.setTextSize(AndroidUtilities.dp(10f));
            v0.imageView = new BackupImageView(v1);
            v0.imageView.setRoundRadius(AndroidUtilities.dp(10f));
            BackupImageView v5 = v0.imageView;
            v9 = 20;
            v10 = 20f;
            v11 = LocaleController.isRTL ? 5 : 3;
            v11 |= 48;
            v12 = LocaleController.isRTL ? 0 : 17;
            v12_1 = ((float)v12);
            v13_1 = 13f;
            if(LocaleController.isRTL) {
                v3 = 17;
            }

            v0.addView(((View)v5), LayoutHelper.createFrame(v9, v10, v11, v12_1, v13_1, ((float)v3), 0f));
        }
        else {
            v9 = -1;
            v10 = 30f;
            v3 = LocaleController.isRTL ? 5 : 3;
            v11 = v3 | 48;
            v3 = LocaleController.isRTL ? 11 : 17;
            v12_1 = ((float)v3);
            v13_1 = 11f;
            if(LocaleController.isRTL) {
            }
            else {
                v4 = 11;
            }

            v0.addView(((View)v2), LayoutHelper.createFrame(v9, v10, v11, v12_1, v13_1, ((float)v4), 0f));
        }

        v0.nameTextView = new TextView(v1);
        v0.nameTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.nameTextView.setTextSize(1, 16f);
        v0.nameTextView.setLines(1);
        v0.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v0.nameTextView.setMaxLines(1);
        v0.nameTextView.setSingleLine(true);
        v0.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v3_1 = v0.nameTextView;
        v4 = LocaleController.isRTL ? 5 : 3;
        v3_1.setGravity(v4 | 48);
        v0.onlineTextView = new TextView(v1);
        float v4_1 = 14f;
        v0.onlineTextView.setTextSize(1, v4_1);
        v3_1 = v0.onlineTextView;
        int v5_1 = LocaleController.isRTL ? 3 : 5;
        v3_1.setGravity(v5_1 | 48);
        if(LocaleController.isRTL) {
            v2.addView(v0.onlineTextView, LayoutHelper.createLinear(-2, -1, 51, 0, 2, 0, 0));
            v3_1 = v0.nameTextView;
            v5_2 = LayoutHelper.createLinear(0, -1, 1f, 53, 10, 0, 0, 0);
        }
        else {
            v2.addView(v0.nameTextView, LayoutHelper.createLinear(0, -1, 1f, 51, 0, 0, 10, 0));
            v3_1 = v0.onlineTextView;
            v5_2 = LayoutHelper.createLinear(-2, -1, 53, 0, 2, 0, 0);
        }

        v2.addView(((View)v3_1), ((ViewGroup$LayoutParams)v5_2));
        v0.detailTextView = new TextView(v1);
        v0.detailTextView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        v0.detailTextView.setTextSize(1, v4_1);
        v0.detailTextView.setLines(1);
        v0.detailTextView.setMaxLines(1);
        v0.detailTextView.setSingleLine(true);
        v0.detailTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v2_1 = v0.detailTextView;
        v3 = LocaleController.isRTL ? 5 : 3;
        v2_1.setGravity(v3 | 48);
        v2_1 = v0.detailTextView;
        v9 = -1;
        v10 = -2f;
        v3 = LocaleController.isRTL ? 5 : 3;
        v0.addView(((View)v2_1), LayoutHelper.createFrame(v9, v10, v3 | 48, 17f, 36f, 17f, 0f));
        v0.detailExTextView = new TextView(v1);
        v0.detailExTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
        v0.detailExTextView.setTextSize(1, v4_1);
        v0.detailExTextView.setLines(1);
        v0.detailExTextView.setMaxLines(1);
        v0.detailExTextView.setSingleLine(true);
        v0.detailExTextView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v1_1 = v0.detailExTextView;
        int v2_2 = LocaleController.isRTL ? 5 : 3;
        v1_1.setGravity(v2_2 | 48);
        v1_1 = v0.detailExTextView;
        int v8 = -1;
        float v9_1 = -2f;
        if(LocaleController.isRTL) {
            v6 = 5;
        }

        v0.addView(((View)v1_1), LayoutHelper.createFrame(v8, v9_1, v6 | 48, 17f, 59f, 17f, 0f));
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(90f) + this.needDivider, 1073741824));
    }

    public void setSession(TLObject arg7, boolean arg8) {
        TLObject v8_4;
        String v7;
        StringBuilder v8_1;
        String v0_1;
        this.needDivider = arg8;
        if((arg7 instanceof TL_authorization)) {
            TextView v8 = this.nameTextView;
            Locale v0 = Locale.US;
            int v2 = 2;
            v8.setText(String.format(v0, "%s %s", ((TL_authorization)arg7).app_name, ((TL_authorization)arg7).app_version));
            if((((TL_authorization)arg7).flags & 1) != 0) {
                this.setTag("windowBackgroundWhiteValueText");
                this.onlineTextView.setText(LocaleController.getString("Online", 2131625428));
                v8 = this.onlineTextView;
                v0_1 = "windowBackgroundWhiteValueText";
            }
            else {
                this.setTag("windowBackgroundWhiteGrayText3");
                this.onlineTextView.setText(LocaleController.stringForMessageListDate(((long)((TL_authorization)arg7).date_active)));
                v8 = this.onlineTextView;
                v0_1 = "windowBackgroundWhiteGrayText3";
            }

            v8.setTextColor(Theme.getColor(v0_1));
            v8_1 = new StringBuilder();
            if(((TL_authorization)arg7).ip.length() != 0) {
                v8_1.append(((TL_authorization)arg7).ip);
            }

            if(((TL_authorization)arg7).country.length() != 0) {
                if(v8_1.length() != 0) {
                    v8_1.append(" ");
                }

                v8_1.append("— ");
                v8_1.append(((TL_authorization)arg7).country);
            }

            this.detailExTextView.setText(((CharSequence)v8_1));
            v8_1 = new StringBuilder();
            if(((TL_authorization)arg7).device_model.length() != 0) {
                v8_1.append(((TL_authorization)arg7).device_model);
            }

            if(((TL_authorization)arg7).system_version.length() != 0 || ((TL_authorization)arg7).platform.length() != 0) {
                if(v8_1.length() != 0) {
                    v8_1.append(", ");
                }

                if(((TL_authorization)arg7).platform.length() != 0) {
                    v8_1.append(((TL_authorization)arg7).platform);
                }

                if(((TL_authorization)arg7).system_version.length() == 0) {
                    goto label_93;
                }

                if(((TL_authorization)arg7).platform.length() != 0) {
                    v8_1.append(" ");
                }

                v8_1.append(((TL_authorization)arg7).system_version);
            }

        label_93:
            if((((TL_authorization)arg7).flags & v2) != 0) {
                goto label_110;
            }

            if(v8_1.length() != 0) {
                v8_1.append(", ");
            }

            v8_1.append(LocaleController.getString("UnofficialApp", 2131626273));
            v8_1.append(" (ID: ");
            v8_1.append(((TL_authorization)arg7).api_id);
            v7 = ")";
            goto label_109;
        }
        else {
            if(!(arg7 instanceof TL_webAuthorization)) {
                return;
            }

            User v8_2 = MessagesController.getInstance(this.currentAccount).getUser(Integer.valueOf(((TL_webAuthorization)arg7).bot_id));
            this.nameTextView.setText(((TL_webAuthorization)arg7).domain);
            if(v8_2 != null) {
                this.avatarDrawable.setInfo(v8_2);
                v0_1 = UserObject.getFirstName(v8_2);
                if(v8_2.photo != null) {
                    FileLocation v8_3 = v8_2.photo.photo_small;
                }
                else {
                    v8_4 = null;
                }

                this.imageView.setImage(v8_4, "50_50", this.avatarDrawable);
            }
            else {
                v0_1 = "";
            }

            this.setTag("windowBackgroundWhiteGrayText3");
            this.onlineTextView.setText(LocaleController.stringForMessageListDate(((long)((TL_webAuthorization)arg7).date_active)));
            this.onlineTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText3"));
            v8_1 = new StringBuilder();
            if(((TL_webAuthorization)arg7).ip.length() != 0) {
                v8_1.append(((TL_webAuthorization)arg7).ip);
            }

            if(((TL_webAuthorization)arg7).region.length() != 0) {
                if(v8_1.length() != 0) {
                    v8_1.append(" ");
                }

                v8_1.append("— ");
                v8_1.append(((TL_webAuthorization)arg7).region);
            }

            this.detailExTextView.setText(((CharSequence)v8_1));
            v8_1 = new StringBuilder();
            if(!TextUtils.isEmpty(((CharSequence)v0_1))) {
                v8_1.append(v0_1);
            }

            if(((TL_webAuthorization)arg7).browser.length() != 0) {
                if(v8_1.length() != 0) {
                    v8_1.append(", ");
                }

                v8_1.append(((TL_webAuthorization)arg7).browser);
            }

            if(((TL_webAuthorization)arg7).platform.length() != 0) {
                if(v8_1.length() != 0) {
                    v8_1.append(", ");
                }

                v7 = ((TL_webAuthorization)arg7).platform;
            label_109:
                v8_1.append(v7);
            }

        label_110:
            this.detailTextView.setText(((CharSequence)v8_1));
        }
    }
}

