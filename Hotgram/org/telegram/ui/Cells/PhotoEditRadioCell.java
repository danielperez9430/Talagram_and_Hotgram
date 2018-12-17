package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

public class PhotoEditRadioCell extends FrameLayout {
    private int currentColor;
    private int currentType;
    private TextView nameTextView;
    private View$OnClickListener onClickListener;
    private LinearLayout tintButtonsContainer;
    private final int[] tintHighlighsColors;
    private final int[] tintShadowColors;

    public PhotoEditRadioCell(Context arg10) {
        super(arg10);
        this.tintShadowColors = new int[]{0, -45747, -753630, -13056, -8269183, -9321002, -16747844, -10080879};
        this.tintHighlighsColors = new int[]{0, -1076602, -1388894, -859780, -5968466, -7742235, -13726776, -3303195};
        this.nameTextView = new TextView(arg10);
        this.nameTextView.setGravity(5);
        int v1 = -1;
        this.nameTextView.setTextColor(v1);
        this.nameTextView.setTextSize(1, 12f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.addView(this.nameTextView, LayoutHelper.createFrame(80, -2f, 19, 0f, 0f, 0f, 0f));
        this.tintButtonsContainer = new LinearLayout(arg10);
        this.tintButtonsContainer.setOrientation(0);
        int v0;
        for(v0 = 0; v0 < this.tintShadowColors.length; ++v0) {
            RadioButton v3 = new RadioButton(arg10);
            v3.setSize(AndroidUtilities.dp(20f));
            v3.setTag(Integer.valueOf(v0));
            this.tintButtonsContainer.addView(((View)v3), LayoutHelper.createLinear(0, v1, 1f / (((float)this.tintShadowColors.length))));
            v3.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg3) {
                    int v3;
                    PhotoEditRadioCell v0;
                    if(PhotoEditRadioCell.access$000(PhotoEditRadioCell.this) == 0) {
                        v0 = PhotoEditRadioCell.this;
                        v3 = PhotoEditRadioCell.access$200(PhotoEditRadioCell.this)[((RadioButton)arg3).getTag().intValue()];
                    }
                    else {
                        v0 = PhotoEditRadioCell.this;
                        v3 = PhotoEditRadioCell.access$300(PhotoEditRadioCell.this)[((RadioButton)arg3).getTag().intValue()];
                    }

                    PhotoEditRadioCell.access$102(v0, v3);
                    PhotoEditRadioCell.access$400(PhotoEditRadioCell.this, true);
                    PhotoEditRadioCell.access$500(PhotoEditRadioCell.this).onClick(PhotoEditRadioCell.this);
                }
            });
        }

        this.addView(this.tintButtonsContainer, LayoutHelper.createFrame(-1, 40f, 51, 96f, 0f, 24f, 0f));
    }

    static int access$000(PhotoEditRadioCell arg0) {
        return arg0.currentType;
    }

    static int access$102(PhotoEditRadioCell arg0, int arg1) {
        arg0.currentColor = arg1;
        return arg1;
    }

    static int[] access$200(PhotoEditRadioCell arg0) {
        return arg0.tintShadowColors;
    }

    static int[] access$300(PhotoEditRadioCell arg0) {
        return arg0.tintHighlighsColors;
    }

    static void access$400(PhotoEditRadioCell arg0, boolean arg1) {
        arg0.updateSelectedTintButton(arg1);
    }

    static View$OnClickListener access$500(PhotoEditRadioCell arg0) {
        return arg0.onClickListener;
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40f), 1073741824));
    }

    public void setIconAndTextAndValue(String arg4, int arg5, int arg6) {
        this.currentType = arg5;
        this.currentColor = arg6;
        TextView v5 = this.nameTextView;
        v5.setText(arg4.substring(0, 1).toUpperCase() + arg4.substring(1).toLowerCase());
        this.updateSelectedTintButton(false);
    }

    public void setOnClickListener(View$OnClickListener arg1) {
        this.onClickListener = arg1;
    }

    private void updateSelectedTintButton(boolean arg8) {
        int v6;
        int v0 = this.tintButtonsContainer.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.tintButtonsContainer.getChildAt(v2);
            if((v3 instanceof RadioButton)) {
                int v4 = ((RadioButton)v3).getTag().intValue();
                int v5 = this.currentType == 0 ? this.tintShadowColors[v4] : this.tintHighlighsColors[v4];
                boolean v5_1 = this.currentColor == v5 ? true : false;
                ((RadioButton)v3).setChecked(v5_1, arg8);
                v5 = -1;
                if(v4 == 0) {
                    v6 = -1;
                }
                else if(this.currentType == 0) {
                    v6 = this.tintShadowColors[v4];
                }
                else {
                    v6 = this.tintHighlighsColors[v4];
                }

                if(v4 == 0) {
                }
                else if(this.currentType == 0) {
                    v5 = this.tintShadowColors[v4];
                }
                else {
                    v5 = this.tintHighlighsColors[v4];
                }

                ((RadioButton)v3).setColor(v6, v5);
            }
        }
    }
}

