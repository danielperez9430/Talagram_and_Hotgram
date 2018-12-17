package org.telegram.ui.ActionBar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities$LinkMovementMethodMy;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.RadialProgressView;

public class AlertDialog extends Dialog implements Drawable$Callback {
    public class AlertDialogCell extends FrameLayout {
        private ImageView imageView;
        private TextView textView;

        public AlertDialogCell(Context arg6) {
            super(arg6);
            this.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor("dialogButtonSelector"), 2));
            this.setPadding(AndroidUtilities.dp(23f), 0, AndroidUtilities.dp(23f), 0);
            this.imageView = new ImageView(arg6);
            this.imageView.setScaleType(ImageView$ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor("dialogIcon"), PorterDuff$Mode.MULTIPLY));
            ImageView v0 = this.imageView;
            int v2 = 3;
            int v1 = LocaleController.isRTL ? 5 : 3;
            this.addView(((View)v0), LayoutHelper.createFrame(24, 24, v1 | 16));
            this.textView = new TextView(arg6);
            this.textView.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils$TruncateAt.END);
            this.textView.setTextColor(Theme.getColor("dialogTextBlack"));
            this.textView.setTextSize(1, 16f);
            TextView v6 = this.textView;
            if(LocaleController.isRTL) {
                v2 = 5;
            }

            this.addView(((View)v6), LayoutHelper.createFrame(-2, -2, v2 | 16));
        }

        protected void onMeasure(int arg2, int arg3) {
            super.onMeasure(arg2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48f), 1073741824));
        }

        public void setGravity(int arg2) {
            this.textView.setGravity(arg2);
        }

        public void setTextAndIcon(CharSequence arg4, int arg5) {
            this.textView.setText(arg4);
            if(arg5 != 0) {
                this.imageView.setImageResource(arg5);
                this.imageView.setVisibility(0);
                TextView v5 = this.textView;
                float v1 = 56f;
                int v0 = LocaleController.isRTL ? 0 : AndroidUtilities.dp(v1);
                int v1_1 = LocaleController.isRTL ? AndroidUtilities.dp(v1) : 0;
                v5.setPadding(v0, 0, v1_1, 0);
            }
            else {
                this.imageView.setVisibility(4);
                this.textView.setPadding(0, 0, 0, 0);
            }
        }

        public void setTextColor(int arg2) {
            this.textView.setTextColor(arg2);
        }
    }

    public class Builder {
        private AlertDialog alertDialog;

        public Builder(Context arg3) {
            super();
            this.alertDialog = new AlertDialog(arg3, 0);
        }

        public Builder(Context arg2, int arg3) {
            super();
            this.alertDialog = new AlertDialog(arg2, arg3);
        }

        protected Builder(AlertDialog arg1) {
            super();
            this.alertDialog = arg1;
        }

        public AlertDialog create() {
            return this.alertDialog;
        }

        public Context getContext() {
            return this.alertDialog.getContext();
        }

        public Runnable getDismissRunnable() {
            return this.alertDialog.dismissRunnable;
        }

        public Builder setCustomViewOffset(int arg2) {
            this.alertDialog.customViewOffset = arg2;
            return this;
        }

        public Builder setItems(CharSequence[] arg2, DialogInterface$OnClickListener arg3) {
            this.alertDialog.items = arg2;
            this.alertDialog.onClickListener = arg3;
            return this;
        }

        public Builder setItems(CharSequence[] arg2, int[] arg3, DialogInterface$OnClickListener arg4) {
            this.alertDialog.items = arg2;
            this.alertDialog.itemIcons = arg3;
            this.alertDialog.onClickListener = arg4;
            return this;
        }

        public Builder setMessage(CharSequence arg2) {
            this.alertDialog.message = arg2;
            return this;
        }

        public Builder setNegativeButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.alertDialog.negativeButtonText = arg2;
            this.alertDialog.negativeButtonListener = arg3;
            return this;
        }

        public Builder setNeutralButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.alertDialog.neutralButtonText = arg2;
            this.alertDialog.neutralButtonListener = arg3;
            return this;
        }

        public Builder setOnBackButtonListener(DialogInterface$OnClickListener arg2) {
            this.alertDialog.onBackButtonListener = arg2;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface$OnDismissListener arg2) {
            this.alertDialog.setOnDismissListener(arg2);
            return this;
        }

        public Builder setPositiveButton(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.alertDialog.positiveButtonText = arg2;
            this.alertDialog.positiveButtonListener = arg3;
            return this;
        }

        public Builder setSubtitle(CharSequence arg2) {
            this.alertDialog.subtitle = arg2;
            return this;
        }

        public Builder setTitle(CharSequence arg2) {
            this.alertDialog.title = arg2;
            return this;
        }

        public Builder setTopImage(int arg2, int arg3) {
            this.alertDialog.topResId = arg2;
            this.alertDialog.topBackgroundColor = arg3;
            return this;
        }

        public Builder setTopImage(Drawable arg2, int arg3) {
            this.alertDialog.topDrawable = arg2;
            this.alertDialog.topBackgroundColor = arg3;
            return this;
        }

        public Builder setView(View arg2) {
            this.alertDialog.customView = arg2;
            return this;
        }

        public AlertDialog show() {
            this.alertDialog.show();
            return this.alertDialog;
        }
    }

    private Rect backgroundPaddings;
    protected FrameLayout buttonsLayout;
    private ScrollView contentScrollView;
    private int currentProgress;
    private View customView;
    private int customViewOffset;
    private boolean dismissDialogByButtons;
    private Runnable dismissRunnable;
    private int[] itemIcons;
    private CharSequence[] items;
    private int lastScreenWidth;
    private LineProgressView lineProgressView;
    private TextView lineProgressViewPercent;
    private CharSequence message;
    private TextView messageTextView;
    private DialogInterface$OnClickListener negativeButtonListener;
    private CharSequence negativeButtonText;
    private DialogInterface$OnClickListener neutralButtonListener;
    private CharSequence neutralButtonText;
    private DialogInterface$OnClickListener onBackButtonListener;
    private DialogInterface$OnClickListener onClickListener;
    private DialogInterface$OnDismissListener onDismissListener;
    private ViewTreeObserver$OnScrollChangedListener onScrollChangedListener;
    private DialogInterface$OnClickListener positiveButtonListener;
    private CharSequence positiveButtonText;
    private FrameLayout progressViewContainer;
    private int progressViewStyle;
    private TextView progressViewTextView;
    private LinearLayout scrollContainer;
    private CharSequence secondTitle;
    private TextView secondTitleTextView;
    private BitmapDrawable[] shadow;
    private AnimatorSet[] shadowAnimation;
    private Drawable shadowDrawable;
    private boolean[] shadowVisibility;
    private CharSequence subtitle;
    private TextView subtitleTextView;
    private CharSequence title;
    private FrameLayout titleContainer;
    private TextView titleTextView;
    private int topBackgroundColor;
    private Drawable topDrawable;
    private int topHeight;
    private ImageView topImageView;
    private int topResId;

    public AlertDialog(Context arg4, int arg5) {
        super(arg4, 2131689885);
        this.shadow = new BitmapDrawable[2];
        this.shadowVisibility = new boolean[2];
        this.shadowAnimation = new AnimatorSet[2];
        this.customViewOffset = 20;
        this.topHeight = 132;
        this.dismissDialogByButtons = true;
        this.dismissRunnable = new -$$Lambda$H9iyBEO4Zihg11d8XSg-qvJnAGk(this);
        this.backgroundPaddings = new Rect();
        this.shadowDrawable = arg4.getResources().getDrawable(2131231501).mutate();
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(this.getThemeColor("dialogBackground"), PorterDuff$Mode.MULTIPLY));
        this.shadowDrawable.getPadding(this.backgroundPaddings);
        this.progressViewStyle = arg5;
    }

    static TextView access$000(AlertDialog arg0) {
        return arg0.secondTitleTextView;
    }

    static TextView access$100(AlertDialog arg0) {
        return arg0.titleTextView;
    }

    static CharSequence[] access$1000(AlertDialog arg0) {
        return arg0.items;
    }

    static CharSequence[] access$1002(AlertDialog arg0, CharSequence[] arg1) {
        arg0.items = arg1;
        return arg1;
    }

    static FrameLayout access$1100(AlertDialog arg0) {
        return arg0.progressViewContainer;
    }

    static LineProgressView access$1200(AlertDialog arg0) {
        return arg0.lineProgressView;
    }

    static TextView access$1300(AlertDialog arg0) {
        return arg0.lineProgressViewPercent;
    }

    static int access$1400(AlertDialog arg0) {
        return arg0.lastScreenWidth;
    }

    static int access$1402(AlertDialog arg0, int arg1) {
        arg0.lastScreenWidth = arg1;
        return arg1;
    }

    static ViewTreeObserver$OnScrollChangedListener access$1500(AlertDialog arg0) {
        return arg0.onScrollChangedListener;
    }

    static ViewTreeObserver$OnScrollChangedListener access$1502(AlertDialog arg0, ViewTreeObserver$OnScrollChangedListener arg1) {
        arg0.onScrollChangedListener = arg1;
        return arg1;
    }

    static LinearLayout access$1600(AlertDialog arg0) {
        return arg0.scrollContainer;
    }

    static void access$1700(AlertDialog arg0, int arg1, boolean arg2) {
        arg0.runShadowAnimation(arg1, arg2);
    }

    static Rect access$1800(AlertDialog arg0) {
        return arg0.backgroundPaddings;
    }

    static BitmapDrawable[] access$1900(AlertDialog arg0) {
        return arg0.shadow;
    }

    static FrameLayout access$200(AlertDialog arg0) {
        return arg0.titleContainer;
    }

    static AnimatorSet[] access$2000(AlertDialog arg0) {
        return arg0.shadowAnimation;
    }

    static DialogInterface$OnClickListener access$2102(AlertDialog arg0, DialogInterface$OnClickListener arg1) {
        arg0.onClickListener = arg1;
        return arg1;
    }

    static int[] access$2202(AlertDialog arg0, int[] arg1) {
        arg0.itemIcons = arg1;
        return arg1;
    }

    static CharSequence access$2302(AlertDialog arg0, CharSequence arg1) {
        arg0.title = arg1;
        return arg1;
    }

    static CharSequence access$2402(AlertDialog arg0, CharSequence arg1) {
        arg0.subtitle = arg1;
        return arg1;
    }

    static int access$2502(AlertDialog arg0, int arg1) {
        arg0.topResId = arg1;
        return arg1;
    }

    static int access$2602(AlertDialog arg0, int arg1) {
        arg0.topBackgroundColor = arg1;
        return arg1;
    }

    static Drawable access$2702(AlertDialog arg0, Drawable arg1) {
        arg0.topDrawable = arg1;
        return arg1;
    }

    static CharSequence access$2802(AlertDialog arg0, CharSequence arg1) {
        arg0.message = arg1;
        return arg1;
    }

    static CharSequence access$2902(AlertDialog arg0, CharSequence arg1) {
        arg0.positiveButtonText = arg1;
        return arg1;
    }

    static TextView access$300(AlertDialog arg0) {
        return arg0.subtitleTextView;
    }

    static DialogInterface$OnClickListener access$3002(AlertDialog arg0, DialogInterface$OnClickListener arg1) {
        arg0.positiveButtonListener = arg1;
        return arg1;
    }

    static CharSequence access$3102(AlertDialog arg0, CharSequence arg1) {
        arg0.negativeButtonText = arg1;
        return arg1;
    }

    static DialogInterface$OnClickListener access$3202(AlertDialog arg0, DialogInterface$OnClickListener arg1) {
        arg0.negativeButtonListener = arg1;
        return arg1;
    }

    static CharSequence access$3302(AlertDialog arg0, CharSequence arg1) {
        arg0.neutralButtonText = arg1;
        return arg1;
    }

    static DialogInterface$OnClickListener access$3402(AlertDialog arg0, DialogInterface$OnClickListener arg1) {
        arg0.neutralButtonListener = arg1;
        return arg1;
    }

    static DialogInterface$OnClickListener access$3502(AlertDialog arg0, DialogInterface$OnClickListener arg1) {
        arg0.onBackButtonListener = arg1;
        return arg1;
    }

    static int access$3602(AlertDialog arg0, int arg1) {
        arg0.customViewOffset = arg1;
        return arg1;
    }

    static Runnable access$3700(AlertDialog arg0) {
        return arg0.dismissRunnable;
    }

    static ImageView access$400(AlertDialog arg0) {
        return arg0.topImageView;
    }

    static int access$500(AlertDialog arg0) {
        return arg0.topHeight;
    }

    static int access$600(AlertDialog arg0) {
        return arg0.progressViewStyle;
    }

    static ScrollView access$700(AlertDialog arg0) {
        return arg0.contentScrollView;
    }

    static View access$800(AlertDialog arg0) {
        return arg0.customView;
    }

    static View access$802(AlertDialog arg0, View arg1) {
        arg0.customView = arg1;
        return arg1;
    }

    static TextView access$900(AlertDialog arg0) {
        return arg0.messageTextView;
    }

    private boolean canTextInput(View arg5) {
        if(arg5.onCheckIsTextEditor()) {
            return 1;
        }

        if(!(arg5 instanceof ViewGroup)) {
            return 0;
        }

        int v0 = ((ViewGroup)arg5).getChildCount();
        do {
            if(v0 <= 0) {
                return 0;
            }

            --v0;
        }
        while(!this.canTextInput(((ViewGroup)arg5).getChildAt(v0)));

        return 1;
    }

    public void dismiss() {
        super.dismiss();
    }

    public View getButton(int arg2) {
        return this.buttonsLayout.findViewWithTag(Integer.valueOf(arg2));
    }

    protected int getThemeColor(String arg1) {
        return Theme.getColor(arg1);
    }

    public void invalidateDrawable(Drawable arg1) {
        this.contentScrollView.invalidate();
        this.scrollContainer.invalidate();
    }

    public static void lambda$onCreate$0(AlertDialog arg1, View arg2) {
        if(arg1.onClickListener != null) {
            arg1.onClickListener.onClick(((DialogInterface)arg1), arg2.getTag().intValue());
        }

        arg1.dismiss();
    }

    public static void lambda$onCreate$1(AlertDialog arg1, View arg2) {
        if(arg1.positiveButtonListener != null) {
            arg1.positiveButtonListener.onClick(((DialogInterface)arg1), -1);
        }

        if(arg1.dismissDialogByButtons) {
            arg1.dismiss();
        }
    }

    public static void lambda$onCreate$2(AlertDialog arg1, View arg2) {
        if(arg1.negativeButtonListener != null) {
            arg1.negativeButtonListener.onClick(((DialogInterface)arg1), -2);
        }

        if(arg1.dismissDialogByButtons) {
            arg1.cancel();
        }
    }

    public static void lambda$onCreate$3(AlertDialog arg1, View arg2) {
        if(arg1.neutralButtonListener != null) {
            arg1.neutralButtonListener.onClick(((DialogInterface)arg1), -2);
        }

        if(arg1.dismissDialogByButtons) {
            arg1.dismiss();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if(this.onBackButtonListener != null) {
            this.onBackButtonListener.onClick(((DialogInterface)this), -2);
        }
    }

    protected void onCreate(Bundle arg21) {
        float v2;
        int v19;
        int v15;
        int v17;
        int v16_1;
        int v13_1;
        int v7;
        int v14;
        float v13;
        int v12;
        TextView v11_1;
        FrameLayout v5_2;
        TextView v5_1;
        int v11;
        int v10;
        int v9;
        AlertDialog v0 = this;
        super.onCreate(arg21);
        org.telegram.ui.ActionBar.AlertDialog$1 v1 = new LinearLayout(this.getContext()) {
            private boolean inLayout;

            public boolean hasOverlappingRendering() {
                return 0;
            }

            public static void lambda$onLayout$1(org.telegram.ui.ActionBar.AlertDialog$1 arg5) {
                AlertDialog v0 = arg5.this$0;
                boolean v3 = false;
                boolean v1 = arg5.this$0.titleTextView == null || arg5.this$0.contentScrollView.getScrollY() <= arg5.this$0.scrollContainer.getTop() ? false : true;
                v0.runShadowAnimation(0, v1);
                v0 = arg5.this$0;
                if(arg5.this$0.buttonsLayout != null && arg5.this$0.contentScrollView.getScrollY() + arg5.this$0.contentScrollView.getHeight() < arg5.this$0.scrollContainer.getBottom()) {
                    v3 = true;
                }

                v0.runShadowAnimation(1, v3);
                arg5.this$0.contentScrollView.invalidate();
            }

            public static void lambda$onMeasure$0(org.telegram.ui.ActionBar.AlertDialog$1 arg5) {
                float v1;
                arg5.this$0.lastScreenWidth = AndroidUtilities.displaySize.x;
                int v0 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(56f);
                if(!AndroidUtilities.isTablet()) {
                    v1 = 356f;
                }
                else if(AndroidUtilities.isSmallTablet()) {
                    v1 = 446f;
                }
                else {
                    v1 = 496f;
                }

                int v1_1 = AndroidUtilities.dp(v1);
                Window v2 = arg5.this$0.getWindow();
                WindowManager$LayoutParams v3 = new WindowManager$LayoutParams();
                v3.copyFrom(v2.getAttributes());
                v3.width = Math.min(v1_1, v0) + arg5.this$0.backgroundPaddings.left + arg5.this$0.backgroundPaddings.right;
                v2.setAttributes(v3);
            }

            protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
                super.onLayout(arg1, arg2, arg3, arg4, arg5);
                if(AlertDialog.this.contentScrollView != null) {
                    if(AlertDialog.this.onScrollChangedListener == null) {
                        AlertDialog.this.onScrollChangedListener = new -$$Lambda$AlertDialog$1$vvKcenyzvRwmFgV39QOFVkx4krI(this);
                        AlertDialog.this.contentScrollView.getViewTreeObserver().addOnScrollChangedListener(AlertDialog.this.onScrollChangedListener);
                    }

                    AlertDialog.this.onScrollChangedListener.onScrollChanged();
                }
            }

            protected void onMeasure(int arg12, int arg13) {
                ViewGroup$LayoutParams v13;
                int v5;
                this.inLayout = true;
                arg12 = View$MeasureSpec.getSize(arg12);
                int v0 = View$MeasureSpec.getSize(arg13) - this.getPaddingTop() - this.getPaddingBottom();
                int v1 = arg12 - this.getPaddingLeft() - this.getPaddingRight();
                int v3 = 1073741824;
                int v2 = View$MeasureSpec.makeMeasureSpec(v1 - AndroidUtilities.dp(48f), v3);
                int v4 = View$MeasureSpec.makeMeasureSpec(v1, v3);
                if(AlertDialog.this.buttonsLayout != null) {
                    v5 = AlertDialog.this.buttonsLayout.getChildCount();
                    int v7;
                    for(v7 = 0; v7 < v5; ++v7) {
                        View v8 = AlertDialog.this.buttonsLayout.getChildAt(v7);
                        if((v8 instanceof TextView)) {
                            ((TextView)v8).setMaxWidth(AndroidUtilities.dp(((float)((v1 - AndroidUtilities.dp(24f)) / 2))));
                        }
                    }

                    AlertDialog.this.buttonsLayout.measure(v4, arg13);
                    ViewGroup$LayoutParams v1_1 = AlertDialog.this.buttonsLayout.getLayoutParams();
                    v1 = v0 - (AlertDialog.this.buttonsLayout.getMeasuredHeight() + ((LinearLayout$LayoutParams)v1_1).bottomMargin + ((LinearLayout$LayoutParams)v1_1).topMargin);
                }
                else {
                    v1 = v0;
                }

                v7 = -2147483648;
                if(AlertDialog.this.secondTitleTextView != null) {
                    AlertDialog.this.secondTitleTextView.measure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(v2), v7), arg13);
                }

                float v8_1 = 8f;
                if(AlertDialog.this.titleTextView != null) {
                    if(AlertDialog.this.secondTitleTextView != null) {
                        AlertDialog.this.titleTextView.measure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(v2) - AlertDialog.this.secondTitleTextView.getMeasuredWidth() - AndroidUtilities.dp(v8_1), v3), arg13);
                    }
                    else {
                        AlertDialog.this.titleTextView.measure(v2, arg13);
                    }
                }

                if(AlertDialog.this.titleContainer != null) {
                    AlertDialog.this.titleContainer.measure(v2, arg13);
                    ViewGroup$LayoutParams v5_1 = AlertDialog.this.titleContainer.getLayoutParams();
                    v1 -= AlertDialog.this.titleContainer.getMeasuredHeight() + ((LinearLayout$LayoutParams)v5_1).bottomMargin + ((LinearLayout$LayoutParams)v5_1).topMargin;
                }

                if(AlertDialog.this.subtitleTextView != null) {
                    AlertDialog.this.subtitleTextView.measure(v2, arg13);
                    v13 = AlertDialog.this.subtitleTextView.getLayoutParams();
                    v1 -= AlertDialog.this.subtitleTextView.getMeasuredHeight() + ((LinearLayout$LayoutParams)v13).bottomMargin + ((LinearLayout$LayoutParams)v13).topMargin;
                }

                if(AlertDialog.this.topImageView != null) {
                    AlertDialog.this.topImageView.measure(View$MeasureSpec.makeMeasureSpec(arg12, v3), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(((float)AlertDialog.this.topHeight)), v3));
                    v1 -= AlertDialog.this.topImageView.getMeasuredHeight() - AndroidUtilities.dp(v8_1);
                }

                v5 = 8;
                if(AlertDialog.this.progressViewStyle == 0) {
                    v13 = AlertDialog.this.contentScrollView.getLayoutParams();
                    if(AlertDialog.this.customView != null) {
                        v2 = AlertDialog.this.titleTextView != null || AlertDialog.this.messageTextView.getVisibility() != v5 || AlertDialog.this.items != null ? 0 : AndroidUtilities.dp(16f);
                        ((LinearLayout$LayoutParams)v13).topMargin = v2;
                        if(AlertDialog.this.buttonsLayout == null) {
                            goto label_185;
                        }

                        v2 = 0;
                        goto label_186;
                    }
                    else {
                        if(AlertDialog.this.items != null) {
                            v2 = AlertDialog.this.titleTextView != null || AlertDialog.this.messageTextView.getVisibility() != v5 ? 0 : AndroidUtilities.dp(v8_1);
                            ((LinearLayout$LayoutParams)v13).topMargin = v2;
                        label_185:
                            v2 = AndroidUtilities.dp(v8_1);
                            goto label_186;
                        }

                        if(AlertDialog.this.messageTextView.getVisibility() != 0) {
                            goto label_203;
                        }

                        v2 = AlertDialog.this.titleTextView == null ? AndroidUtilities.dp(19f) : 0;
                        ((LinearLayout$LayoutParams)v13).topMargin = v2;
                        v2 = AndroidUtilities.dp(20f);
                    label_186:
                        ((LinearLayout$LayoutParams)v13).bottomMargin = v2;
                    }

                label_203:
                    v1 -= ((LinearLayout$LayoutParams)v13).bottomMargin + ((LinearLayout$LayoutParams)v13).topMargin;
                    AlertDialog.this.contentScrollView.measure(v4, View$MeasureSpec.makeMeasureSpec(v1, v7));
                    v1 -= AlertDialog.this.contentScrollView.getMeasuredHeight();
                }
                else {
                    if(AlertDialog.this.progressViewContainer != null) {
                        AlertDialog.this.progressViewContainer.measure(v2, View$MeasureSpec.makeMeasureSpec(v1, v7));
                        v13 = AlertDialog.this.progressViewContainer.getLayoutParams();
                        v4 = AlertDialog.this.progressViewContainer.getMeasuredHeight();
                        goto label_229;
                    }
                    else if(AlertDialog.this.messageTextView != null) {
                        AlertDialog.this.messageTextView.measure(v2, View$MeasureSpec.makeMeasureSpec(v1, v7));
                        if(AlertDialog.this.messageTextView.getVisibility() != v5) {
                            v13 = AlertDialog.this.messageTextView.getLayoutParams();
                            v4 = AlertDialog.this.messageTextView.getMeasuredHeight();
                        label_229:
                            v1 -= v4 + ((LinearLayout$LayoutParams)v13).bottomMargin + ((LinearLayout$LayoutParams)v13).topMargin;
                        }
                    }

                    if(AlertDialog.this.lineProgressView == null) {
                        goto label_288;
                    }

                    AlertDialog.this.lineProgressView.measure(v2, View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(4f), v3));
                    v13 = AlertDialog.this.lineProgressView.getLayoutParams();
                    v1 -= AlertDialog.this.lineProgressView.getMeasuredHeight() + ((LinearLayout$LayoutParams)v13).bottomMargin + ((LinearLayout$LayoutParams)v13).topMargin;
                    AlertDialog.this.lineProgressViewPercent.measure(v2, View$MeasureSpec.makeMeasureSpec(v1, v7));
                    v13 = AlertDialog.this.lineProgressViewPercent.getLayoutParams();
                    v1 -= AlertDialog.this.lineProgressViewPercent.getMeasuredHeight() + ((LinearLayout$LayoutParams)v13).bottomMargin + ((LinearLayout$LayoutParams)v13).topMargin;
                }

            label_288:
                this.setMeasuredDimension(arg12, v0 - v1 + this.getPaddingTop() + this.getPaddingBottom());
                this.inLayout = false;
                if(AlertDialog.this.lastScreenWidth != AndroidUtilities.displaySize.x) {
                    AndroidUtilities.runOnUIThread(new -$$Lambda$AlertDialog$1$2il1lPevBw8X_3FhfSjXOpGmbaM(this));
                }
            }

            public void requestLayout() {
                if(this.inLayout) {
                    return;
                }

                super.requestLayout();
            }
        };
        ((LinearLayout)v1).setOrientation(1);
        ((LinearLayout)v1).setBackgroundDrawable(v0.shadowDrawable);
        boolean v3 = Build$VERSION.SDK_INT >= 21 ? true : false;
        ((LinearLayout)v1).setFitsSystemWindows(v3);
        v0.setContentView(((View)v1));
        int v3_1 = v0.positiveButtonText != null || v0.negativeButtonText != null || v0.neutralButtonText != null ? 1 : 0;
        int v6 = 48;
        if(v0.topResId != 0 || v0.topDrawable != null) {
            v0.topImageView = new ImageView(this.getContext());
            if(v0.topDrawable != null) {
                v0.topImageView.setImageDrawable(v0.topDrawable);
            }
            else {
                v0.topImageView.setImageResource(v0.topResId);
            }

            v0.topImageView.setScaleType(ImageView$ScaleType.CENTER);
            v0.topImageView.setBackgroundDrawable(this.getContext().getResources().getDrawable(2131231502));
            v0.topImageView.getBackground().setColorFilter(new PorterDuffColorFilter(v0.topBackgroundColor, PorterDuff$Mode.MULTIPLY));
            v0.topImageView.setPadding(0, 0, 0, 0);
            ImageView v5 = v0.topImageView;
            v9 = -1;
            v10 = v0.topHeight;
            v11 = LocaleController.isRTL ? 5 : 3;
            ((LinearLayout)v1).addView(((View)v5), LayoutHelper.createLinear(v9, v10, v11 | v6, -8, -8, 0, 0));
        }

        v10 = 2;
        if(v0.title != null) {
            v0.titleContainer = new FrameLayout(this.getContext());
            ((LinearLayout)v1).addView(v0.titleContainer, LayoutHelper.createLinear(-2, -2, 24f, 0f, 24f, 0f));
            v0.titleTextView = new TextView(this.getContext());
            v0.titleTextView.setText(v0.title);
            v0.titleTextView.setTextColor(v0.getThemeColor("dialogTextBlack"));
            v0.titleTextView.setTextSize(1, 20f);
            v0.titleTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            v5_1 = v0.titleTextView;
            v11 = LocaleController.isRTL ? 5 : 3;
            v5_1.setGravity(v11 | v6);
            v5_2 = v0.titleContainer;
            v11_1 = v0.titleTextView;
            v12 = -2;
            v13 = -2f;
            v14 = LocaleController.isRTL ? 5 : 3;
            v14 |= v6;
            float v16 = 19f;
            if(v0.subtitle != null) {
                v7 = 2;
            }
            else if(v0.items != null) {
                v7 = 14;
            }
            else {
                v7 = 10;
            }

            v5_2.addView(((View)v11_1), LayoutHelper.createFrame(v12, v13, v14, 0f, v16, 0f, ((float)v7)));
        }

        if(v0.secondTitle != null && v0.title != null) {
            v0.secondTitleTextView = new TextView(this.getContext());
            v0.secondTitleTextView.setText(v0.secondTitle);
            v0.secondTitleTextView.setTextColor(v0.getThemeColor("dialogTextGray3"));
            v0.secondTitleTextView.setTextSize(1, 18f);
            v5_1 = v0.secondTitleTextView;
            v7 = LocaleController.isRTL ? 3 : 5;
            v5_1.setGravity(v7 | v6);
            v5_2 = v0.titleContainer;
            TextView v7_1 = v0.secondTitleTextView;
            v11 = -2;
            float v12_1 = -2f;
            v13_1 = LocaleController.isRTL ? 3 : 5;
            v5_2.addView(((View)v7_1), LayoutHelper.createFrame(v11, v12_1, v13_1 | v6, 0f, 21f, 0f, 0f));
        }

        float v7_2 = 14f;
        if(v0.subtitle != null) {
            v0.subtitleTextView = new TextView(this.getContext());
            v0.subtitleTextView.setText(v0.subtitle);
            v0.subtitleTextView.setTextColor(v0.getThemeColor("dialogIcon"));
            v0.subtitleTextView.setTextSize(1, v7_2);
            v5_1 = v0.subtitleTextView;
            v11 = LocaleController.isRTL ? 5 : 3;
            v5_1.setGravity(v11 | v6);
            v5_1 = v0.subtitleTextView;
            v11 = -2;
            v12 = -2;
            v13_1 = LocaleController.isRTL ? 5 : 3;
            v13_1 |= v6;
            v14 = 24;
            v16_1 = 24;
            v17 = v0.items != null ? 14 : 10;
            ((LinearLayout)v1).addView(((View)v5_1), LayoutHelper.createLinear(v11, v12, v13_1, v14, 0, v16_1, v17));
        }

        int v8 = -1;
        v9 = -2;
        if(v0.progressViewStyle == 0) {
            v0.shadow[0] = this.getContext().getResources().getDrawable(2131231098).mutate();
            v0.shadow[1] = this.getContext().getResources().getDrawable(2131231099).mutate();
            v0.shadow[0].setAlpha(0);
            v0.shadow[1].setAlpha(0);
            v0.shadow[0].setCallback(((Drawable$Callback)v0));
            v0.shadow[1].setCallback(((Drawable$Callback)v0));
            v0.contentScrollView = new ScrollView(this.getContext()) {
                protected boolean drawChild(Canvas arg6, View arg7, long arg8) {
                    boolean v7 = super.drawChild(arg6, arg7, arg8);
                    float v0 = 3f;
                    if(AlertDialog.this.shadow[0].getPaint().getAlpha() != 0) {
                        AlertDialog.this.shadow[0].setBounds(0, this.getScrollY(), this.getMeasuredWidth(), this.getScrollY() + AndroidUtilities.dp(v0));
                        AlertDialog.this.shadow[0].draw(arg6);
                    }

                    if(AlertDialog.this.shadow[1].getPaint().getAlpha() != 0) {
                        AlertDialog.this.shadow[1].setBounds(0, this.getScrollY() + this.getMeasuredHeight() - AndroidUtilities.dp(v0), this.getMeasuredWidth(), this.getScrollY() + this.getMeasuredHeight());
                        AlertDialog.this.shadow[1].draw(arg6);
                    }

                    return v7;
                }
            };
            v0.contentScrollView.setVerticalScrollBarEnabled(false);
            AndroidUtilities.setScrollViewEdgeEffectColor(v0.contentScrollView, v0.getThemeColor("dialogScrollGlow"));
            ((LinearLayout)v1).addView(v0.contentScrollView, LayoutHelper.createLinear(-1, -2, 0f, 0f, 0f, 0f));
            v0.scrollContainer = new LinearLayout(this.getContext());
            v0.scrollContainer.setOrientation(1);
            v0.contentScrollView.addView(v0.scrollContainer, new FrameLayout$LayoutParams(v8, v9));
        }

        v0.messageTextView = new TextView(this.getContext());
        v0.messageTextView.setTextColor(v0.getThemeColor("dialogTextBlack"));
        v0.messageTextView.setTextSize(1, 16f);
        v0.messageTextView.setMovementMethod(new LinkMovementMethodMy());
        v0.messageTextView.setLinkTextColor(v0.getThemeColor("dialogTextLink"));
        v5_1 = v0.messageTextView;
        v11 = LocaleController.isRTL ? 5 : 3;
        v5_1.setGravity(v11 | v6);
        if(v0.progressViewStyle == 1) {
            v0.progressViewContainer = new FrameLayout(this.getContext());
            v5_2 = v0.progressViewContainer;
            v11 = -1;
            v12 = 44;
            v13_1 = 51;
            v14 = 23;
            v15 = v0.title == null ? 24 : 0;
            ((LinearLayout)v1).addView(((View)v5_2), LayoutHelper.createLinear(v11, v12, v13_1, v14, v15, 23, 24));
            RadialProgressView v5_3 = new RadialProgressView(this.getContext());
            v5_3.setProgressColor(v0.getThemeColor("dialogProgressCircle"));
            FrameLayout v11_2 = v0.progressViewContainer;
            v12 = 44;
            v13_1 = 44;
            v14 = LocaleController.isRTL ? 5 : 3;
            v11_2.addView(((View)v5_3), LayoutHelper.createFrame(v12, v13_1, v14 | v6));
            v0.messageTextView.setLines(1);
            v0.messageTextView.setEllipsize(TextUtils$TruncateAt.END);
            v5_2 = v0.progressViewContainer;
            v11_1 = v0.messageTextView;
            v12 = -2;
            v13 = -2f;
            v19 = LocaleController.isRTL ? 5 : 3;
            v14 = v19 | 16;
            v15 = LocaleController.isRTL ? 0 : 62;
            float v15_1 = ((float)v15);
            v9 = LocaleController.isRTL ? 62 : 0;
            v5_2.addView(((View)v11_1), LayoutHelper.createFrame(v12, v13, v14, v15_1, 0f, ((float)v9), 0f));
        }
        else {
            if(v0.progressViewStyle == v10) {
                v5_1 = v0.messageTextView;
                v11 = -2;
                v12 = -2;
                v9 = LocaleController.isRTL ? 5 : 3;
                v13_1 = v9 | 48;
                v14 = 24;
                v15 = v0.title == null ? 19 : 0;
                ((LinearLayout)v1).addView(((View)v5_1), LayoutHelper.createLinear(v11, v12, v13_1, v14, v15, 24, 20));
                v0.lineProgressView = new LineProgressView(this.getContext());
                v0.lineProgressView.setProgress((((float)v0.currentProgress)) / 100f, false);
                v0.lineProgressView.setProgressColor(v0.getThemeColor("dialogLineProgress"));
                v0.lineProgressView.setBackColor(v0.getThemeColor("dialogLineProgressBackground"));
                ((LinearLayout)v1).addView(v0.lineProgressView, LayoutHelper.createLinear(-1, 4, 19, 24, 0, 24, 0));
                v0.lineProgressViewPercent = new TextView(this.getContext());
                v0.lineProgressViewPercent.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                v5_1 = v0.lineProgressViewPercent;
                v9 = LocaleController.isRTL ? 5 : 3;
                v5_1.setGravity(v9 | v6);
                v0.lineProgressViewPercent.setTextColor(v0.getThemeColor("dialogTextGray2"));
                v0.lineProgressViewPercent.setTextSize(1, v7_2);
                v5_1 = v0.lineProgressViewPercent;
                v11 = -2;
                v12 = -2;
                v19 = LocaleController.isRTL ? 5 : 3;
                ((LinearLayout)v1).addView(((View)v5_1), LayoutHelper.createLinear(v11, v12, v19 | 48, 23, 4, 23, 24));
                this.updateLineProgressTextView();
                goto label_504;
            }

            LinearLayout v5_4 = v0.scrollContainer;
            TextView v9_1 = v0.messageTextView;
            v11 = -2;
            v12 = -2;
            v19 = LocaleController.isRTL ? 5 : 3;
            v13_1 = v19 | 48;
            v14 = 24;
            v16_1 = 24;
            v17 = v0.customView != null || v0.items != null ? v0.customViewOffset : 0;
            v5_4.addView(((View)v9_1), LayoutHelper.createLinear(v11, v12, v13_1, v14, 0, v16_1, v17));
        }

    label_504:
        if(!TextUtils.isEmpty(v0.message)) {
            v0.messageTextView.setText(v0.message);
            v0.messageTextView.setVisibility(0);
        }
        else {
            v0.messageTextView.setVisibility(8);
        }

        if(v0.items != null) {
            int v5_5;
            for(v5_5 = 0; v5_5 < v0.items.length; ++v5_5) {
                if(v0.items[v5_5] == null) {
                }
                else {
                    AlertDialogCell v9_2 = new AlertDialogCell(this.getContext());
                    CharSequence v10_1 = v0.items[v5_5];
                    v11 = v0.itemIcons != null ? v0.itemIcons[v5_5] : 0;
                    v9_2.setTextAndIcon(v10_1, v11);
                    v0.scrollContainer.addView(((View)v9_2), LayoutHelper.createLinear(v8, v6));
                    v9_2.setTag(Integer.valueOf(v5_5));
                    v9_2.setOnClickListener(new -$$Lambda$AlertDialog$iC26x2guh9hO2NrF8CCJRy6v4_w(v0));
                }
            }
        }

        if(v0.customView != null) {
            if(v0.customView.getParent() != null) {
                v0.customView.getParent().removeView(v0.customView);
            }

            v0.scrollContainer.addView(v0.customView, LayoutHelper.createLinear(v8, -2));
        }

        if(v3_1 != 0) {
            v0.buttonsLayout = new FrameLayout(this.getContext()) {
                protected void onLayout(boolean arg7, int arg8, int arg9, int arg10, int arg11) {
                    int v3;
                    int v2;
                    int v1_1;
                    int v4;
                    int v7 = this.getChildCount();
                    arg10 -= arg8;
                    View v11 = null;
                    for(arg9 = 0; arg9 < v7; ++arg9) {
                        View v0 = this.getChildAt(arg9);
                        Object v1 = v0.getTag();
                        if(v1 == null) {
                            v1_1 = v0.getMeasuredWidth();
                            v2 = v0.getMeasuredHeight();
                            if(v11 != null) {
                                v3 = v11.getLeft() + (v11.getMeasuredWidth() - v1_1) / 2;
                                v4 = v11.getTop() + (v11.getMeasuredHeight() - v2) / 2;
                            }
                            else {
                                v3 = 0;
                                v4 = 0;
                            }

                            v0.layout(v3, v4, v1_1 + v3, v2 + v4);
                        }
                        else if(((Integer)v1).intValue() == -1) {
                            if(LocaleController.isRTL) {
                                arg11 = this.getPaddingLeft();
                                v1_1 = this.getPaddingTop();
                                v2 = this.getPaddingLeft();
                            }
                            else {
                                arg11 = arg10 - this.getPaddingRight() - v0.getMeasuredWidth();
                                v1_1 = this.getPaddingTop();
                                v2 = arg10 - this.getPaddingRight();
                            }

                            v0.layout(arg11, v1_1, v2 + v0.getMeasuredWidth(), this.getPaddingTop() + v0.getMeasuredHeight());
                            v11 = v0;
                        }
                        else {
                            if(((Integer)v1).intValue() == -2) {
                                float v2_1 = 8f;
                                if(LocaleController.isRTL) {
                                    v1_1 = this.getPaddingLeft();
                                    if(v11 != null) {
                                        v1_1 += v11.getMeasuredWidth() + AndroidUtilities.dp(v2_1);
                                    }
                                }
                                else {
                                    v1_1 = arg10 - this.getPaddingRight() - v0.getMeasuredWidth();
                                    if(v11 != null) {
                                        v1_1 -= v11.getMeasuredWidth() + AndroidUtilities.dp(v2_1);
                                    }
                                }

                                v2 = this.getPaddingTop();
                                v3 = v0.getMeasuredWidth() + v1_1;
                            }
                            else {
                                if(((Integer)v1).intValue() != -3) {
                                    goto label_103;
                                }

                                if(LocaleController.isRTL) {
                                    v1_1 = arg10 - this.getPaddingRight() - v0.getMeasuredWidth();
                                    v2 = this.getPaddingTop();
                                    v3 = arg10 - this.getPaddingRight();
                                }
                                else {
                                    v1_1 = this.getPaddingLeft();
                                    v2 = this.getPaddingTop();
                                    v3 = this.getPaddingLeft();
                                }

                                v3 += v0.getMeasuredWidth();
                            }

                            v0.layout(v1_1, v2, v3, this.getPaddingTop() + v0.getMeasuredHeight());
                        }

                    label_103:
                    }
                }

                protected void onMeasure(int arg6, int arg7) {
                    super.onMeasure(arg6, arg7);
                    arg6 = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
                    arg7 = this.getChildCount();
                    int v0 = 0;
                    int v1 = 0;
                    while(v0 < arg7) {
                        View v2 = this.getChildAt(v0);
                        if(((v2 instanceof TextView)) && v2.getTag() != null) {
                            v1 += v2.getMeasuredWidth();
                        }

                        ++v0;
                    }

                    if(v1 > arg6) {
                        View v7 = this.findViewWithTag(Integer.valueOf(-2));
                        View v0_1 = this.findViewWithTag(Integer.valueOf(-3));
                        if(v7 != null && v0_1 != null) {
                            int v4 = 1073741824;
                            if(v7.getMeasuredWidth() < v0_1.getMeasuredWidth()) {
                                v0_1.measure(View$MeasureSpec.makeMeasureSpec(v0_1.getMeasuredWidth() - (v1 - arg6), v4), View$MeasureSpec.makeMeasureSpec(v0_1.getMeasuredHeight(), v4));
                            }
                            else {
                                v7.measure(View$MeasureSpec.makeMeasureSpec(v7.getMeasuredWidth() - (v1 - arg6), v4), View$MeasureSpec.makeMeasureSpec(v7.getMeasuredHeight(), v4));
                            }
                        }
                    }
                }
            };
            v0.buttonsLayout.setPadding(AndroidUtilities.dp(8f), AndroidUtilities.dp(8f), AndroidUtilities.dp(8f), AndroidUtilities.dp(8f));
            ((LinearLayout)v1).addView(v0.buttonsLayout, LayoutHelper.createLinear(v8, 52));
            v3_1 = 36;
            v5_5 = 17;
            float v6_1 = 64f;
            float v9_3 = 10f;
            if(v0.positiveButtonText != null) {
                org.telegram.ui.ActionBar.AlertDialog$4 v1_1 = new TextView(this.getContext()) {
                    public void setEnabled(boolean arg1) {
                        super.setEnabled(arg1);
                        float v1 = arg1 ? 1f : 0.5f;
                        this.setAlpha(v1);
                    }
                };
                ((TextView)v1_1).setMinWidth(AndroidUtilities.dp(v6_1));
                ((TextView)v1_1).setTag(Integer.valueOf(v8));
                ((TextView)v1_1).setTextSize(1, v7_2);
                ((TextView)v1_1).setTextColor(v0.getThemeColor("dialogButton"));
                ((TextView)v1_1).setGravity(v5_5);
                ((TextView)v1_1).setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                ((TextView)v1_1).setText(v0.positiveButtonText.toString().toUpperCase());
                ((TextView)v1_1).setBackgroundDrawable(Theme.getRoundRectSelectorDrawable());
                ((TextView)v1_1).setPadding(AndroidUtilities.dp(v9_3), 0, AndroidUtilities.dp(v9_3), 0);
                v0.buttonsLayout.addView(((View)v1_1), LayoutHelper.createFrame(-2, v3_1, 53));
                ((TextView)v1_1).setOnClickListener(new -$$Lambda$AlertDialog$rp49coWDdM6PFZnr9_LTptCU2Ag(v0));
            }

            if(v0.negativeButtonText != null) {
                org.telegram.ui.ActionBar.AlertDialog$5 v1_2 = new TextView(this.getContext()) {
                    public void setEnabled(boolean arg1) {
                        super.setEnabled(arg1);
                        float v1 = arg1 ? 1f : 0.5f;
                        this.setAlpha(v1);
                    }
                };
                ((TextView)v1_2).setMinWidth(AndroidUtilities.dp(v6_1));
                ((TextView)v1_2).setTag(Integer.valueOf(-2));
                ((TextView)v1_2).setTextSize(1, v7_2);
                ((TextView)v1_2).setTextColor(v0.getThemeColor("dialogButton"));
                ((TextView)v1_2).setGravity(v5_5);
                ((TextView)v1_2).setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
                ((TextView)v1_2).setEllipsize(TextUtils$TruncateAt.END);
                ((TextView)v1_2).setSingleLine(true);
                ((TextView)v1_2).setText(v0.negativeButtonText.toString().toUpperCase());
                ((TextView)v1_2).setBackgroundDrawable(Theme.getRoundRectSelectorDrawable());
                ((TextView)v1_2).setPadding(AndroidUtilities.dp(v9_3), 0, AndroidUtilities.dp(v9_3), 0);
                v0.buttonsLayout.addView(((View)v1_2), LayoutHelper.createFrame(-2, v3_1, 53));
                ((TextView)v1_2).setOnClickListener(new -$$Lambda$AlertDialog$35svlhUpH-M074FLkhJN8iyIwmw(v0));
            }

            if(v0.neutralButtonText == null) {
                goto label_692;
            }

            org.telegram.ui.ActionBar.AlertDialog$6 v1_3 = new TextView(this.getContext()) {
                public void setEnabled(boolean arg1) {
                    super.setEnabled(arg1);
                    float v1 = arg1 ? 1f : 0.5f;
                    this.setAlpha(v1);
                }
            };
            ((TextView)v1_3).setMinWidth(AndroidUtilities.dp(v6_1));
            ((TextView)v1_3).setTag(Integer.valueOf(-3));
            ((TextView)v1_3).setTextSize(1, v7_2);
            ((TextView)v1_3).setTextColor(v0.getThemeColor("dialogButton"));
            ((TextView)v1_3).setGravity(v5_5);
            ((TextView)v1_3).setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
            ((TextView)v1_3).setEllipsize(TextUtils$TruncateAt.END);
            ((TextView)v1_3).setSingleLine(true);
            ((TextView)v1_3).setText(v0.neutralButtonText.toString().toUpperCase());
            ((TextView)v1_3).setBackgroundDrawable(Theme.getRoundRectSelectorDrawable());
            ((TextView)v1_3).setPadding(AndroidUtilities.dp(v9_3), 0, AndroidUtilities.dp(v9_3), 0);
            v0.buttonsLayout.addView(((View)v1_3), LayoutHelper.createFrame(-2, v3_1, 51));
            ((TextView)v1_3).setOnClickListener(new -$$Lambda$AlertDialog$hCRmQxFHC-EIDauULvRdmfnSEuE(v0));
        }

    label_692:
        v0.lastScreenWidth = AndroidUtilities.displaySize.x;
        int v1_4 = AndroidUtilities.displaySize.x - AndroidUtilities.dp(48f);
        if(!AndroidUtilities.isTablet()) {
            v2 = 356f;
        }
        else if(AndroidUtilities.isSmallTablet()) {
            v2 = 446f;
        }
        else {
            v2 = 496f;
        }

        int v2_1 = AndroidUtilities.dp(v2);
        Window v3_2 = this.getWindow();
        WindowManager$LayoutParams v4 = new WindowManager$LayoutParams();
        v4.copyFrom(v3_2.getAttributes());
        v4.dimAmount = 0.6f;
        v4.width = Math.min(v2_1, v1_4) + v0.backgroundPaddings.left + v0.backgroundPaddings.right;
        v4.flags |= 2;
        if(v0.customView == null || !v0.canTextInput(v0.customView)) {
            v4.flags |= 131072;
        }
        else {
            v4.softInputMode = 4;
        }

        v3_2.setAttributes(v4);
    }

    private void runShadowAnimation(int arg7, boolean arg8) {
        if(!arg8 || (this.shadowVisibility[arg7])) {
            if(arg8) {
            }
            else if(this.shadowVisibility[arg7]) {
                goto label_8;
            }

            return;
        }

    label_8:
        this.shadowVisibility[arg7] = arg8;
        if(this.shadowAnimation[arg7] != null) {
            this.shadowAnimation[arg7].cancel();
        }

        this.shadowAnimation[arg7] = new AnimatorSet();
        if(this.shadow[arg7] != null) {
            AnimatorSet v0 = this.shadowAnimation[arg7];
            Animator[] v2 = new Animator[1];
            BitmapDrawable v3 = this.shadow[arg7];
            String v4 = "alpha";
            int[] v1 = new int[1];
            int v8 = arg8 ? 255 : 0;
            v1[0] = v8;
            v2[0] = ObjectAnimator.ofInt(v3, v4, v1);
            v0.playTogether(v2);
        }

        this.shadowAnimation[arg7].setDuration(150);
        this.shadowAnimation[arg7].addListener(new AnimatorListenerAdapter(arg7) {
            public void onAnimationCancel(Animator arg3) {
                if(AlertDialog.this.shadowAnimation[this.val$num] != null && (AlertDialog.this.shadowAnimation[this.val$num].equals(arg3))) {
                    AlertDialog.this.shadowAnimation[this.val$num] = null;
                }
            }

            public void onAnimationEnd(Animator arg3) {
                if(AlertDialog.this.shadowAnimation[this.val$num] != null && (AlertDialog.this.shadowAnimation[this.val$num].equals(arg3))) {
                    AlertDialog.this.shadowAnimation[this.val$num] = null;
                }
            }
        });
        try {
            this.shadowAnimation[arg7].start();
        }
        catch(Exception v7) {
            FileLog.e(((Throwable)v7));
        }
    }

    public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        if(this.contentScrollView != null) {
            this.contentScrollView.postDelayed(arg2, arg3);
        }
    }

    public void setButton(int arg1, CharSequence arg2, DialogInterface$OnClickListener arg3) {
        switch(arg1) {
            case -3: {
                this.neutralButtonText = arg2;
                this.neutralButtonListener = arg3;
                break;
            }
            case -2: {
                this.negativeButtonText = arg2;
                this.negativeButtonListener = arg3;
                break;
            }
            case -1: {
                this.positiveButtonText = arg2;
                this.positiveButtonListener = arg3;
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setCanceledOnTouchOutside(boolean arg1) {
        super.setCanceledOnTouchOutside(arg1);
    }

    public void setDismissDialogByButtons(boolean arg1) {
        this.dismissDialogByButtons = arg1;
    }

    public void setMessage(CharSequence arg2) {
        int v0;
        TextView v2;
        this.message = arg2;
        if(this.messageTextView != null) {
            if(!TextUtils.isEmpty(this.message)) {
                this.messageTextView.setText(this.message);
                v2 = this.messageTextView;
                v0 = 0;
            }
            else {
                v2 = this.messageTextView;
                v0 = 8;
            }

            v2.setVisibility(v0);
        }
    }

    public void setNegativeButton(CharSequence arg1, DialogInterface$OnClickListener arg2) {
        this.negativeButtonText = arg1;
        this.negativeButtonListener = arg2;
    }

    public void setNeutralButton(CharSequence arg1, DialogInterface$OnClickListener arg2) {
        this.neutralButtonText = arg1;
        this.neutralButtonListener = arg2;
    }

    public void setPositiveButton(CharSequence arg1, DialogInterface$OnClickListener arg2) {
        this.positiveButtonText = arg1;
        this.positiveButtonListener = arg2;
    }

    public void setPositiveButtonListener(DialogInterface$OnClickListener arg1) {
        this.positiveButtonListener = arg1;
    }

    public void setProgress(int arg3) {
        this.currentProgress = arg3;
        if(this.lineProgressView != null) {
            this.lineProgressView.setProgress((((float)arg3)) / 100f, true);
            this.updateLineProgressTextView();
        }
    }

    public void setProgressStyle(int arg1) {
        this.progressViewStyle = arg1;
    }

    public void setSecondTitle(CharSequence arg1) {
        this.secondTitle = arg1;
    }

    public void setTitle(CharSequence arg1) {
        this.title = arg1;
    }

    public void setTopHeight(int arg1) {
        this.topHeight = arg1;
    }

    public void setTopImage(int arg1, int arg2) {
        this.topResId = arg1;
        this.topBackgroundColor = arg2;
    }

    public void setTopImage(Drawable arg1, int arg2) {
        this.topDrawable = arg1;
        this.topBackgroundColor = arg2;
    }

    public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        if(this.contentScrollView != null) {
            this.contentScrollView.removeCallbacks(arg2);
        }
    }

    private void updateLineProgressTextView() {
        this.lineProgressViewPercent.setText(String.format("%d%%", Integer.valueOf(this.currentProgress)));
    }
}

