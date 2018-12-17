package org.telegram.ui.Cells;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils$TruncateAt;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$StickerSetCovered;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class FeaturedStickerSetCell extends FrameLayout {
    private TextView addButton;
    private int angle;
    private ImageView checkImage;
    private int currentAccount;
    private AnimatorSet currentAnimation;
    private boolean drawProgress;
    private BackupImageView imageView;
    private boolean isInstalled;
    private long lastUpdateTime;
    private boolean needDivider;
    private float progressAlpha;
    private Paint progressPaint;
    private RectF progressRect;
    private Rect rect;
    private StickerSetCovered stickersSet;
    private TextView textView;
    private TextView valueTextView;
    private boolean wasLayout;

    public FeaturedStickerSetCell(Context arg15) {
        super(arg15);
        this.rect = new Rect();
        this.currentAccount = UserConfig.selectedAccount;
        this.progressRect = new RectF();
        this.progressPaint = new Paint(1);
        this.progressPaint.setColor(Theme.getColor("featuredStickers_buttonProgress"));
        this.progressPaint.setStrokeCap(Paint$Cap.ROUND);
        this.progressPaint.setStyle(Paint$Style.STROKE);
        this.progressPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.textView = new TextView(arg15);
        this.textView.setTextColor(Theme.getColor("windowBackgroundWhiteBlackText"));
        this.textView.setTextSize(1, 16f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        this.textView.setEllipsize(TextUtils$TruncateAt.END);
        TextView v0 = this.textView;
        int v3 = 3;
        int v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        v0 = this.textView;
        int v5 = -2;
        float v6 = -2f;
        int v7 = LocaleController.isRTL ? 5 : 3;
        float v8 = LocaleController.isRTL ? 100f : 71f;
        float v9 = 10f;
        float v10 = LocaleController.isRTL ? 71f : 100f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.valueTextView = new TextView(arg15);
        this.valueTextView.setTextColor(Theme.getColor("windowBackgroundWhiteGrayText2"));
        this.valueTextView.setTextSize(1, 13f);
        this.valueTextView.setLines(1);
        this.valueTextView.setMaxLines(1);
        this.valueTextView.setSingleLine(true);
        this.valueTextView.setEllipsize(TextUtils$TruncateAt.END);
        v0 = this.valueTextView;
        v2 = LocaleController.isRTL ? 5 : 3;
        v0.setGravity(v2);
        v0 = this.valueTextView;
        v5 = -2;
        v6 = -2f;
        v7 = LocaleController.isRTL ? 5 : 3;
        v8 = LocaleController.isRTL ? 100f : 71f;
        v9 = 35f;
        v10 = LocaleController.isRTL ? 71f : 100f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.imageView = new BackupImageView(arg15);
        this.imageView.setAspectFit(true);
        BackupImageView v0_1 = this.imageView;
        v5 = 48;
        v6 = 48f;
        v2 = LocaleController.isRTL ? 5 : 3;
        v7 = v2 | 48;
        float v2_1 = LocaleController.isRTL ? 0f : 12f;
        v9 = 8f;
        v10 = LocaleController.isRTL ? 12f : 0f;
        this.addView(((View)v0_1), LayoutHelper.createFrame(v5, v6, v7, v2_1, v9, v10, 0f));
        this.addButton = new TextView(arg15) {
            protected void onDraw(Canvas arg9) {
                super.onDraw(arg9);
                float v1 = 0f;
                if((FeaturedStickerSetCell.access$000(FeaturedStickerSetCell.this)) || !FeaturedStickerSetCell.access$000(FeaturedStickerSetCell.this) && FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) != 0f) {
                    FeaturedStickerSetCell.access$200(FeaturedStickerSetCell.this).setAlpha(Math.min(255, ((int)(FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) * 255f))));
                    int v0 = this.getMeasuredWidth() - AndroidUtilities.dp(11f);
                    FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this).set(((float)v0), ((float)AndroidUtilities.dp(3f)), ((float)(v0 + AndroidUtilities.dp(8f))), ((float)AndroidUtilities.dp(11f)));
                    arg9.drawArc(FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this), ((float)FeaturedStickerSetCell.access$400(FeaturedStickerSetCell.this)), 220f, false, FeaturedStickerSetCell.access$200(FeaturedStickerSetCell.this));
                    this.invalidate((((int)FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this).left)) - AndroidUtilities.dp(2f), (((int)FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this).top)) - AndroidUtilities.dp(2f), (((int)FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this).right)) + AndroidUtilities.dp(2f), (((int)FeaturedStickerSetCell.access$300(FeaturedStickerSetCell.this).bottom)) + AndroidUtilities.dp(2f));
                    long v2 = System.currentTimeMillis();
                    if(Math.abs(FeaturedStickerSetCell.access$500(FeaturedStickerSetCell.this) - System.currentTimeMillis()) < 1000) {
                        long v4 = v2 - FeaturedStickerSetCell.access$500(FeaturedStickerSetCell.this);
                        FeaturedStickerSetCell.access$402(FeaturedStickerSetCell.this, ((int)((((float)FeaturedStickerSetCell.access$400(FeaturedStickerSetCell.this))) + (((float)(360 * v4))) / 2000f)));
                        FeaturedStickerSetCell.access$402(FeaturedStickerSetCell.this, FeaturedStickerSetCell.access$400(FeaturedStickerSetCell.this) - FeaturedStickerSetCell.access$400(FeaturedStickerSetCell.this) / 360 * 360);
                        float v0_1 = 200f;
                        if(FeaturedStickerSetCell.access$000(FeaturedStickerSetCell.this)) {
                            v1 = 1f;
                            if(FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) < v1) {
                                FeaturedStickerSetCell.access$102(FeaturedStickerSetCell.this, FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) + (((float)v4)) / v0_1);
                                if(FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) <= v1) {
                                    goto label_142;
                                }
                            }
                            else {
                                goto label_142;
                            }
                        }
                        else if(FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) > 0f) {
                            FeaturedStickerSetCell.access$102(FeaturedStickerSetCell.this, FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) - (((float)v4)) / v0_1);
                            if(FeaturedStickerSetCell.access$100(FeaturedStickerSetCell.this) < 0f) {
                            }
                            else {
                                goto label_142;
                            }
                        }
                        else {
                            goto label_142;
                        }

                        FeaturedStickerSetCell.access$102(FeaturedStickerSetCell.this, v1);
                    }

                label_142:
                    FeaturedStickerSetCell.access$502(FeaturedStickerSetCell.this, v2);
                    this.invalidate();
                }
            }
        };
        this.addButton.setGravity(17);
        this.addButton.setTextColor(Theme.getColor("featuredStickers_buttonText"));
        v2_1 = 14f;
        this.addButton.setTextSize(1, v2_1);
        this.addButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addButton.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4f), Theme.getColor("featuredStickers_addButton"), Theme.getColor("featuredStickers_addButtonPressed")));
        this.addButton.setText(LocaleController.getString("Add", 2131624010).toUpperCase());
        this.addButton.setPadding(AndroidUtilities.dp(17f), 0, AndroidUtilities.dp(17f), 0);
        v0 = this.addButton;
        v5 = -2;
        v6 = 28f;
        if(LocaleController.isRTL) {
        }
        else {
            v3 = 5;
        }

        v7 = v3 | 48;
        v8 = LocaleController.isRTL ? 14f : 0f;
        v9 = 18f;
        v10 = LocaleController.isRTL ? 0f : 14f;
        this.addView(((View)v0), LayoutHelper.createFrame(v5, v6, v7, v8, v9, v10, 0f));
        this.checkImage = new ImageView(arg15);
        this.checkImage.setColorFilter(new PorterDuffColorFilter(Theme.getColor("featuredStickers_addedIcon"), PorterDuff$Mode.MULTIPLY));
        this.checkImage.setImageResource(2131231573);
        this.addView(this.checkImage, LayoutHelper.createFrame(19, v2_1));
    }

    static boolean access$000(FeaturedStickerSetCell arg0) {
        return arg0.drawProgress;
    }

    static float access$100(FeaturedStickerSetCell arg0) {
        return arg0.progressAlpha;
    }

    static float access$102(FeaturedStickerSetCell arg0, float arg1) {
        arg0.progressAlpha = arg1;
        return arg1;
    }

    static Paint access$200(FeaturedStickerSetCell arg0) {
        return arg0.progressPaint;
    }

    static RectF access$300(FeaturedStickerSetCell arg0) {
        return arg0.progressRect;
    }

    static int access$400(FeaturedStickerSetCell arg0) {
        return arg0.angle;
    }

    static int access$402(FeaturedStickerSetCell arg0, int arg1) {
        arg0.angle = arg1;
        return arg1;
    }

    static long access$500(FeaturedStickerSetCell arg2) {
        return arg2.lastUpdateTime;
    }

    static long access$502(FeaturedStickerSetCell arg0, long arg1) {
        arg0.lastUpdateTime = arg1;
        return arg1;
    }

    static AnimatorSet access$600(FeaturedStickerSetCell arg0) {
        return arg0.currentAnimation;
    }

    static AnimatorSet access$602(FeaturedStickerSetCell arg0, AnimatorSet arg1) {
        arg0.currentAnimation = arg1;
        return arg1;
    }

    static TextView access$700(FeaturedStickerSetCell arg0) {
        return arg0.addButton;
    }

    static ImageView access$800(FeaturedStickerSetCell arg0) {
        return arg0.checkImage;
    }

    public StickerSetCovered getStickerSet() {
        return this.stickersSet;
    }

    public boolean isInstalled() {
        return this.isInstalled;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.wasLayout = false;
    }

    protected void onDraw(Canvas arg8) {
        if(this.needDivider) {
            arg8.drawLine(0f, ((float)(this.getHeight() - 1)), ((float)(this.getWidth() - this.getPaddingRight())), ((float)(this.getHeight() - 1)), Theme.dividerPaint);
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        int v1 = this.addButton.getLeft() + this.addButton.getMeasuredWidth() / 2 - this.checkImage.getMeasuredWidth() / 2;
        arg2 = this.addButton.getTop() + this.addButton.getMeasuredHeight() / 2 - this.checkImage.getMeasuredHeight() / 2;
        this.checkImage.layout(v1, arg2, this.checkImage.getMeasuredWidth() + v1, this.checkImage.getMeasuredHeight() + arg2);
        this.wasLayout = true;
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg3), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64f) + this.needDivider, 1073741824));
    }

    public void setAddOnClickListener(View$OnClickListener arg2) {
        this.addButton.setOnClickListener(arg2);
    }

    public void setDrawProgress(boolean arg3) {
        this.drawProgress = arg3;
        this.lastUpdateTime = System.currentTimeMillis();
        this.addButton.invalidate();
    }

    public void setStickersSet(StickerSetCovered arg9, boolean arg10, boolean arg11) {
        org.telegram.ui.Cells.FeaturedStickerSetCell$3 v10_4;
        Animator[] v11_3;
        AnimatorSet v9_1;
        boolean v9;
        BackupImageView v10_2;
        Drawable v10_1;
        org.telegram.ui.Cells.FeaturedStickerSetCell$2 v4_1;
        int v0 = arg9 != this.stickersSet || !this.wasLayout ? 0 : 1;
        this.needDivider = arg10;
        this.stickersSet = arg9;
        this.lastUpdateTime = System.currentTimeMillis();
        this.setWillNotDraw(this.needDivider ^ 1);
        AnimatorSet v3 = null;
        if(this.currentAnimation != null) {
            this.currentAnimation.cancel();
            this.currentAnimation = v3;
        }

        this.textView.setText(this.stickersSet.set.title);
        if(arg11) {
            org.telegram.ui.Cells.FeaturedStickerSetCell$2 v10 = new Drawable() {
                Paint paint;

                public void draw(Canvas arg5) {
                    this.paint.setColor(-12277526);
                    arg5.drawCircle(((float)AndroidUtilities.dp(4f)), ((float)AndroidUtilities.dp(5f)), ((float)AndroidUtilities.dp(3f)), this.paint);
                }

                public int getIntrinsicHeight() {
                    return AndroidUtilities.dp(8f);
                }

                public int getIntrinsicWidth() {
                    return AndroidUtilities.dp(12f);
                }

                public int getOpacity() {
                    return -2;
                }

                public void setAlpha(int arg1) {
                }

                public void setColorFilter(ColorFilter arg1) {
                }
            };
            TextView v11 = this.textView;
            if(LocaleController.isRTL) {
                Drawable v4 = ((Drawable)v3);
            }
            else {
                v4_1 = v10;
            }

            if(LocaleController.isRTL) {
            }
            else {
                v10_1 = ((Drawable)v3);
            }

            v11.setCompoundDrawablesWithIntrinsicBounds(((Drawable)v4_1), ((Drawable)v3), v10_1, ((Drawable)v3));
        }
        else {
            this.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        this.valueTextView.setText(LocaleController.formatPluralString("Stickers", arg9.set.count));
        if(arg9.cover != null && arg9.cover.thumb != null && arg9.cover.thumb.location != null) {
            v10_2 = this.imageView;
            Document v11_1 = arg9.cover;
            goto label_61;
        }
        else if(!arg9.covers.isEmpty() && arg9.covers.get(0).thumb != null) {
            v10_2 = this.imageView;
            Object v11_2 = arg9.covers.get(0);
        label_61:
            v10_2.setImage(((Document)v11_2).thumb.location, ((String)v3), "webp", ((Drawable)v3));
        }

        int v10_3 = 4;
        if(v0 != 0) {
            arg11 = this.isInstalled;
            v9 = DataQuery.getInstance(this.currentAccount).isStickerPackInstalled(arg9.set.id);
            this.isInstalled = v9;
            v0 = 5;
            int v3_1 = 3;
            int v4_2 = 6;
            long v5 = 200;
            int v7 = 2;
            if(v9) {
                if(!arg11) {
                    this.checkImage.setVisibility(0);
                    this.addButton.setClickable(false);
                    this.currentAnimation = new AnimatorSet();
                    this.currentAnimation.setDuration(v5);
                    v9_1 = this.currentAnimation;
                    v11_3 = new Animator[v4_2];
                    v11_3[0] = ObjectAnimator.ofFloat(this.addButton, "alpha", new float[]{1f, 0f});
                    v11_3[1] = ObjectAnimator.ofFloat(this.addButton, "scaleX", new float[]{1f, 0.01f});
                    v11_3[v7] = ObjectAnimator.ofFloat(this.addButton, "scaleY", new float[]{1f, 0.01f});
                    v11_3[v3_1] = ObjectAnimator.ofFloat(this.checkImage, "alpha", new float[]{0f, 1f});
                    v11_3[v10_3] = ObjectAnimator.ofFloat(this.checkImage, "scaleX", new float[]{0.01f, 1f});
                    v11_3[v0] = ObjectAnimator.ofFloat(this.checkImage, "scaleY", new float[]{0.01f, 1f});
                    v9_1.playTogether(v11_3);
                    v9_1 = this.currentAnimation;
                    v10_4 = new AnimatorListenerAdapter() {
                        public void onAnimationCancel(Animator arg2) {
                            if(FeaturedStickerSetCell.this.currentAnimation != null && (FeaturedStickerSetCell.this.currentAnimation.equals(arg2))) {
                                FeaturedStickerSetCell.this.currentAnimation = null;
                            }
                        }

                        public void onAnimationEnd(Animator arg2) {
                            if(FeaturedStickerSetCell.this.currentAnimation != null && (FeaturedStickerSetCell.this.currentAnimation.equals(arg2))) {
                                FeaturedStickerSetCell.this.addButton.setVisibility(4);
                            }
                        }
                    };
                }
                else {
                    return;
                }
            }
            else if(arg11) {
                this.addButton.setVisibility(0);
                this.addButton.setClickable(true);
                this.currentAnimation = new AnimatorSet();
                this.currentAnimation.setDuration(v5);
                v9_1 = this.currentAnimation;
                v11_3 = new Animator[v4_2];
                v11_3[0] = ObjectAnimator.ofFloat(this.checkImage, "alpha", new float[]{1f, 0f});
                v11_3[1] = ObjectAnimator.ofFloat(this.checkImage, "scaleX", new float[]{1f, 0.01f});
                v11_3[v7] = ObjectAnimator.ofFloat(this.checkImage, "scaleY", new float[]{1f, 0.01f});
                v11_3[v3_1] = ObjectAnimator.ofFloat(this.addButton, "alpha", new float[]{0f, 1f});
                v11_3[v10_3] = ObjectAnimator.ofFloat(this.addButton, "scaleX", new float[]{0.01f, 1f});
                v11_3[v0] = ObjectAnimator.ofFloat(this.addButton, "scaleY", new float[]{0.01f, 1f});
                v9_1.playTogether(v11_3);
                v9_1 = this.currentAnimation;
                org.telegram.ui.Cells.FeaturedStickerSetCell$4 v10_5 = new AnimatorListenerAdapter() {
                    public void onAnimationCancel(Animator arg2) {
                        if(FeaturedStickerSetCell.this.currentAnimation != null && (FeaturedStickerSetCell.this.currentAnimation.equals(arg2))) {
                            FeaturedStickerSetCell.this.currentAnimation = null;
                        }
                    }

                    public void onAnimationEnd(Animator arg2) {
                        if(FeaturedStickerSetCell.this.currentAnimation != null && (FeaturedStickerSetCell.this.currentAnimation.equals(arg2))) {
                            FeaturedStickerSetCell.this.checkImage.setVisibility(4);
                        }
                    }
                };
            }
            else {
                return;
            }

            v9_1.addListener(((Animator$AnimatorListener)v10_4));
            this.currentAnimation.start();
        }
        else {
            v9 = DataQuery.getInstance(this.currentAccount).isStickerPackInstalled(arg9.set.id);
            this.isInstalled = v9;
            float v11_4 = 1f;
            if(v9) {
                this.addButton.setVisibility(v10_3);
                this.addButton.setClickable(false);
                this.checkImage.setVisibility(0);
                this.checkImage.setScaleX(v11_4);
                this.checkImage.setScaleY(v11_4);
                this.checkImage.setAlpha(v11_4);
                return;
            }

            this.addButton.setVisibility(0);
            this.addButton.setClickable(true);
            this.checkImage.setVisibility(v10_3);
            this.addButton.setScaleX(v11_4);
            this.addButton.setScaleY(v11_4);
            this.addButton.setAlpha(v11_4);
        }
    }
}

