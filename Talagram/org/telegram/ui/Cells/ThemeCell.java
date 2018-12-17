package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme$ThemeInfo;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

public class ThemeCell extends FrameLayout {
    private static byte[] bytes;
    private ImageView checkImage;
    private static ThemeInfo currentThemeInfo;
    private boolean isNightTheme;
    private boolean needDivider;
    private ImageView optionsButton;
    private Paint paint;
    private TextView textView;

    static {
        ThemeCell.bytes = new byte[1024];
    }

    public ThemeCell(Context arg14, boolean arg15) {
        FrameLayout$LayoutParams v15_2;
        ImageView v14;
        super(arg14);
        this.setWillNotDraw(false);
        this.isNightTheme = arg15;
        this.paint = new Paint(1);
        this.textView = new TextView(arg14);
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setPadding(0, 0, 0, AndroidUtilities.dp(1f));
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v15 = this.textView;
        int v2 = 3;
        int v1 = LocaleController.isRTL ? 5 : 3;
        v15.setGravity(v1 | 16);
        v15 = this.textView;
        int v4 = -1;
        float v5 = -1f;
        v1 = LocaleController.isRTL ? 5 : 3;
        int v11 = 48;
        int v6 = v1 | 48;
        float v1_1 = LocaleController.isRTL ? 101f : 60f;
        float v10 = LocaleController.isRTL ? 60f : 101f;
        this.addView(((View)v15), LayoutHelper.createFrame(v4, v5, v6, v1_1, 0f, v10, 0f));
        this.checkImage = new ImageView(arg14);
        this.checkImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
        this.checkImage.setImageResource(2131231573);
        if(!this.isNightTheme) {
            ImageView v15_1 = this.checkImage;
            v4 = 19;
            v5 = 14f;
            v1 = LocaleController.isRTL ? 3 : 5;
            this.addView(((View)v15_1), LayoutHelper.createFrame(v4, v5, v1 | 16, 55f, 0f, 55f, 0f));
            this.optionsButton = new ImageView(arg14);
            this.optionsButton.setFocusable(false);
            this.optionsButton.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor("stickers_menuSelector")));
            this.optionsButton.setImageResource(2131231112);
            this.optionsButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor("stickers_menu"), PorterDuff$Mode.MULTIPLY));
            this.optionsButton.setScaleType(ImageView$ScaleType.CENTER);
            v14 = this.optionsButton;
            if(LocaleController.isRTL) {
            }
            else {
                v2 = 5;
            }

            v15_2 = LayoutHelper.createFrame(v11, v11, v2 | 48);
        }
        else {
            v14 = this.checkImage;
            v4 = 19;
            v5 = 14f;
            if(LocaleController.isRTL) {
            }
            else {
                v2 = 5;
            }

            v15_2 = LayoutHelper.createFrame(v4, v5, v2 | 16, 17f, 0f, 17f, 0f);
        }

        this.addView(((View)v14), ((ViewGroup$LayoutParams)v15_2));
    }

    public static ThemeInfo getCurrentThemeInfo() {
        return ThemeCell.currentThemeInfo;
    }

    public TextView getTextView() {
        return this.textView;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.checkImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(((float)this.getPaddingLeft()), ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }

        int v0 = AndroidUtilities.dp(27f);
        if(LocaleController.isRTL) {
            v0 = this.getWidth() - v0;
        }

        arg8.drawCircle(((float)v0), ((float)AndroidUtilities.dp(24f)), ((float)AndroidUtilities.dp(11f)), this.paint);
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f) + this.needDivider, 1073741824));
    }

    public void setOnOptionsClick(View$OnClickListener arg2) {
        this.optionsButton.setOnClickListener(arg2);
    }

    public void setTextColor(int arg2) {
        this.textView.setTextColor(arg2);
    }

    public void setTheme(ThemeInfo arg17, boolean arg18) {
        Integer v2_3;
        int v12;
        int v10;
        int v9;
        int v11;
        int v6;
        int v2_2;
        int v0_3;
        FileInputStream v5;
        ThemeCell v1 = this;
        ThemeInfo v0 = arg17;
        ThemeCell.currentThemeInfo = v0;
        String v2 = arg17.getName();
        int v4 = 0;
        if(v2.endsWith(".attheme")) {
            v2 = v2.substring(0, v2.lastIndexOf(46));
        }

        v1.textView.setText(((CharSequence)v2));
        v1.needDivider = arg18;
        this.updateCurrentThemeCheck();
        if(v0.pathToFile == null && v0.assetName == null) {
            goto label_130;
        }

        FileInputStream v2_1 = null;
        int v3 = 1;
        try {
            File v0_2 = v0.assetName != null ? Theme.getAssetFile(v0.assetName) : new File(v0.pathToFile);
            v5 = new FileInputStream(v0_2);
            v0_3 = 0;
            v2_2 = 0;
            v6 = 0;
            goto label_36;
        }
        catch(Throwable v0_1) {
        }
        catch(Throwable v0_1) {
            goto label_127;
            try {
                while(true) {
                label_36:
                    int v7 = v5.read(ThemeCell.bytes);
                    int v8 = -1;
                    if(v7 == v8) {
                        goto label_109;
                    }

                    v11 = v0_3;
                    v9 = v2_2;
                    v2_2 = 0;
                    v10 = 0;
                    while(true) {
                    label_44:
                        if(v2_2 < v7) {
                            if(ThemeCell.bytes[v2_2] == 10) {
                                ++v9;
                                v12 = v2_2 - v10 + v3;
                                String v13 = new String(ThemeCell.bytes, v10, v12 - 1, "UTF-8");
                                if(v13.startsWith("WPS")) {
                                    goto label_96;
                                }
                                else {
                                    v3 = v13.indexOf(61);
                                    if(v3 != v8 && (v13.substring(0, v3).equals("actionBarDefault"))) {
                                        v2 = v13.substring(v3 + 1);
                                        if(v2.length() > 0 && v2.charAt(0) == 35) {
                                            try {
                                                v3 = Color.parseColor(v2);
                                                break;
                                            }
                                            catch(Exception ) {
                                                v2_3 = Utilities.parseInt(v2);
                                                goto label_78;
                                            }
                                        }

                                        v2_3 = Utilities.parseInt(v2);
                                    label_78:
                                        v3 = v2_3.intValue();
                                        break;
                                    }

                                    goto label_91;
                                }
                            }

                            goto label_93;
                        }

                        goto label_96;
                    }
                }
            }
            catch(Throwable v0_1) {
                goto label_119;
            }

            try {
                v1.paint.setColor(v3);
                v2_2 = v9;
                v6 = 1;
                goto label_97;
            }
            catch(Throwable v0_1) {
                v2_1 = v5;
                v4 = 1;
                goto label_127;
            }

        label_91:
            v10 += v12;
            v11 += v12;
        label_93:
            ++v2_2;
            v3 = 1;
            goto label_44;
        label_96:
            v2_2 = v9;
        label_97:
            if(v0_3 == v11) {
                goto label_109;
            }

            if(v2_2 >= 500) {
                goto label_109;
            }

            try {
                v5.getChannel().position(((long)v11));
                if(v6 == 0) {
                    goto label_106;
                }
            }
            catch(Throwable v0_1) {
                goto label_117;
            }
            catch(Throwable v0_1) {
                goto label_119;
            }

            goto label_109;
        label_106:
            v0_3 = v11;
            v3 = 1;
            goto label_36;
        label_119:
            v2_1 = v5;
            v4 = v6;
            goto label_127;
        label_117:
            goto label_124;
        label_109:
            v4 = v6;
            try {
                v5.close();
                goto label_130;
            }
            catch(Exception v0_4) {
                goto label_114;
            }

            try {
            label_127:
                FileLog.e(v0_1);
                if((((FileInputStream)v2_2)) == null) {
                    goto label_130;
                }

                goto label_129;
            }
            catch(Throwable v0_1) {
                v5 = ((FileInputStream)v2_2);
            }
        }

    label_124:
        Throwable v2_4 = v0_1;
        if(v5 != null) {
            try {
                v5.close();
            }
            catch(Exception v0_4) {
                FileLog.e(v0_4);
            }
        }

        throw v2_4;
        try {
        label_129:
            ((FileInputStream)v2_2).close();
        }
        catch(Exception v0_4) {
        label_114:
            FileLog.e(v0_4);
        }

    label_130:
        if(v4 == 0) {
            v1.paint.setColor(Theme.getDefaultColor("actionBarDefault"));
        }
    }

    public void updateCurrentThemeCheck() {
        ThemeInfo v0 = this.isNightTheme ? Theme.getCurrentNightTheme() : Theme.getCurrentTheme();
        int v0_1 = ThemeCell.currentThemeInfo == v0 ? 0 : 4;
        if(this.checkImage.getVisibility() != v0_1) {
            this.checkImage.setVisibility(v0_1);
        }
    }
}

