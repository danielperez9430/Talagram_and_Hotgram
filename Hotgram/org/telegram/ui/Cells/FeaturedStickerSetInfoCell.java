package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils$TruncateAt;
import android.text.style.ForegroundColorSpan;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$StickerSetCovered;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ColorSpanUnderline;
import org.telegram.ui.Components.LayoutHelper;

public class FeaturedStickerSetInfoCell extends FrameLayout {
    class org.telegram.ui.Cells.FeaturedStickerSetInfoCell$1 extends Drawable {
        Paint paint;

        org.telegram.ui.Cells.FeaturedStickerSetInfoCell$1(FeaturedStickerSetInfoCell arg2) {
            FeaturedStickerSetInfoCell.this = arg2;
            super();
            this.paint = new Paint(1);
        }

        public void draw(Canvas arg5) {
            this.paint.setColor(Theme.getColor("featuredStickers_unread"));
            arg5.drawCircle(((float)AndroidUtilities.dp(8f)), 0f, ((float)AndroidUtilities.dp(4f)), this.paint);
        }

        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(26f);
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
    }

    private TextView addButton;
    private Drawable addDrawable;
    private int angle;
    private Paint botProgressPaint;
    private int currentAccount;
    private Drawable delDrawable;
    private boolean drawProgress;
    Drawable drawable;
    private boolean hasOnClick;
    private TextView infoTextView;
    private boolean isInstalled;
    private long lastUpdateTime;
    private TextView nameTextView;
    private float progressAlpha;
    private RectF rect;
    private StickerSetCovered set;

    public FeaturedStickerSetInfoCell(Context arg10, int arg11) {
        super(arg10);
        this.rect = new RectF();
        this.currentAccount = UserConfig.selectedAccount;
        this.drawable = new org.telegram.ui.Cells.FeaturedStickerSetInfoCell$1(this);
        this.delDrawable = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4f), Theme.getColor("featuredStickers_delButton"), Theme.getColor("featuredStickers_delButtonPressed"));
        this.addDrawable = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4f), Theme.getColor("featuredStickers_addButton"), Theme.getColor("featuredStickers_addButtonPressed"));
        this.botProgressPaint = new Paint(1);
        this.botProgressPaint.setColor(Theme.getColor("featuredStickers_buttonProgress"));
        this.botProgressPaint.setStrokeCap(Paint$Cap.ROUND);
        this.botProgressPaint.setStyle(Paint$Style.STROKE);
        this.botProgressPaint.setStrokeWidth(((float)AndroidUtilities.dp(2f)));
        this.nameTextView = new TextView(arg10);
        this.nameTextView.setTextColor(Theme.getColor("chat_emojiPanelTrendingTitle"));
        this.nameTextView.setTextSize(1, 17f);
        this.nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.nameTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.nameTextView.setSingleLine(true);
        float v5 = ((float)arg11);
        this.addView(this.nameTextView, LayoutHelper.createFrame(-2, -1f, 51, v5, 8f, 100f, 0f));
        this.infoTextView = new TextView(arg10);
        this.infoTextView.setTextColor(Theme.getColor("chat_emojiPanelTrendingDescription"));
        this.infoTextView.setTextSize(1, 13f);
        this.infoTextView.setEllipsize(TextUtils$TruncateAt.END);
        this.infoTextView.setSingleLine(true);
        this.addView(this.infoTextView, LayoutHelper.createFrame(-2, -1f, 51, v5, 30f, 100f, 0f));
        this.addButton = new TextView(arg10) {
            protected void onDraw(Canvas arg9) {
                super.onDraw(arg9);
                float v1 = 0f;
                if((FeaturedStickerSetInfoCell.access$000(FeaturedStickerSetInfoCell.this)) || !FeaturedStickerSetInfoCell.access$000(FeaturedStickerSetInfoCell.this) && FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) != 0f) {
                    FeaturedStickerSetInfoCell.access$200(FeaturedStickerSetInfoCell.this).setAlpha(Math.min(255, ((int)(FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) * 255f))));
                    int v0 = this.getMeasuredWidth() - AndroidUtilities.dp(11f);
                    FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this).set(((float)v0), ((float)AndroidUtilities.dp(3f)), ((float)(v0 + AndroidUtilities.dp(8f))), ((float)AndroidUtilities.dp(11f)));
                    arg9.drawArc(FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this), ((float)FeaturedStickerSetInfoCell.access$400(FeaturedStickerSetInfoCell.this)), 220f, false, FeaturedStickerSetInfoCell.access$200(FeaturedStickerSetInfoCell.this));
                    this.invalidate((((int)FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this).left)) - AndroidUtilities.dp(2f), (((int)FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this).top)) - AndroidUtilities.dp(2f), (((int)FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this).right)) + AndroidUtilities.dp(2f), (((int)FeaturedStickerSetInfoCell.access$300(FeaturedStickerSetInfoCell.this).bottom)) + AndroidUtilities.dp(2f));
                    long v2 = System.currentTimeMillis();
                    if(Math.abs(FeaturedStickerSetInfoCell.access$500(FeaturedStickerSetInfoCell.this) - System.currentTimeMillis()) < 1000) {
                        long v4 = v2 - FeaturedStickerSetInfoCell.access$500(FeaturedStickerSetInfoCell.this);
                        FeaturedStickerSetInfoCell.access$402(FeaturedStickerSetInfoCell.this, ((int)((((float)FeaturedStickerSetInfoCell.access$400(FeaturedStickerSetInfoCell.this))) + (((float)(360 * v4))) / 2000f)));
                        FeaturedStickerSetInfoCell.access$402(FeaturedStickerSetInfoCell.this, FeaturedStickerSetInfoCell.access$400(FeaturedStickerSetInfoCell.this) - FeaturedStickerSetInfoCell.access$400(FeaturedStickerSetInfoCell.this) / 360 * 360);
                        float v0_1 = 200f;
                        if(FeaturedStickerSetInfoCell.access$000(FeaturedStickerSetInfoCell.this)) {
                            v1 = 1f;
                            if(FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) < v1) {
                                FeaturedStickerSetInfoCell.access$102(FeaturedStickerSetInfoCell.this, FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) + (((float)v4)) / v0_1);
                                if(FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) <= v1) {
                                    goto label_142;
                                }
                            }
                            else {
                                goto label_142;
                            }
                        }
                        else if(FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) > 0f) {
                            FeaturedStickerSetInfoCell.access$102(FeaturedStickerSetInfoCell.this, FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) - (((float)v4)) / v0_1);
                            if(FeaturedStickerSetInfoCell.access$100(FeaturedStickerSetInfoCell.this) < 0f) {
                            }
                            else {
                                goto label_142;
                            }
                        }
                        else {
                            goto label_142;
                        }

                        FeaturedStickerSetInfoCell.access$102(FeaturedStickerSetInfoCell.this, v1);
                    }

                label_142:
                    FeaturedStickerSetInfoCell.access$502(FeaturedStickerSetInfoCell.this, v2);
                    this.invalidate();
                }
            }
        };
        this.addButton.setGravity(17);
        this.addButton.setTextColor(Theme.getColor("featuredStickers_buttonText"));
        this.addButton.setTextSize(1, 14f);
        this.addButton.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.addView(this.addButton, LayoutHelper.createFrame(-2, 28f, 53, 0f, 16f, 14f, 0f));
    }

    static boolean access$000(FeaturedStickerSetInfoCell arg0) {
        return arg0.drawProgress;
    }

    static float access$100(FeaturedStickerSetInfoCell arg0) {
        return arg0.progressAlpha;
    }

    static float access$102(FeaturedStickerSetInfoCell arg0, float arg1) {
        arg0.progressAlpha = arg1;
        return arg1;
    }

    static Paint access$200(FeaturedStickerSetInfoCell arg0) {
        return arg0.botProgressPaint;
    }

    static RectF access$300(FeaturedStickerSetInfoCell arg0) {
        return arg0.rect;
    }

    static int access$400(FeaturedStickerSetInfoCell arg0) {
        return arg0.angle;
    }

    static int access$402(FeaturedStickerSetInfoCell arg0, int arg1) {
        arg0.angle = arg1;
        return arg1;
    }

    static long access$500(FeaturedStickerSetInfoCell arg2) {
        return arg2.lastUpdateTime;
    }

    static long access$502(FeaturedStickerSetInfoCell arg0, long arg1) {
        arg0.lastUpdateTime = arg1;
        return arg1;
    }

    public StickerSetCovered getStickerSet() {
        return this.set;
    }

    public boolean isInstalled() {
        return this.isInstalled;
    }

    protected void onMeasure(int arg2, int arg3) {
        super.onMeasure(View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg2), 1073741824), View$MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60f), 1073741824));
    }

    public void setAddOnClickListener(View$OnClickListener arg2) {
        this.hasOnClick = true;
        this.addButton.setOnClickListener(arg2);
    }

    public void setDrawProgress(boolean arg3) {
        this.drawProgress = arg3;
        this.lastUpdateTime = System.currentTimeMillis();
        this.addButton.invalidate();
    }

    public void setStickerSet(StickerSetCovered arg2, boolean arg3) {
        this.setStickerSet(arg2, arg3, 0, 0);
    }

    public void setStickerSet(StickerSetCovered arg4, boolean arg5, int arg6, int arg7) {
        int v0_1;
        String v7;
        TextView v5;
        this.lastUpdateTime = System.currentTimeMillis();
        if(arg7 != 0) {
            SpannableStringBuilder v0 = new SpannableStringBuilder(arg4.set.title);
            try {
                v0.setSpan(new ForegroundColorSpan(Theme.getColor("windowBackgroundWhiteBlueText4")), arg6, arg7 + arg6, 33);
                goto label_14;
            }
            catch(Exception ) {
            label_14:
                this.nameTextView.setText(((CharSequence)v0));
            }
        }
        else {
            this.nameTextView.setText(arg4.set.title);
        }

        this.infoTextView.setText(LocaleController.formatPluralString("Stickers", arg4.set.count));
        Drawable v6 = null;
        if(arg5) {
            this.nameTextView.setCompoundDrawablesWithIntrinsicBounds(v6, v6, this.drawable, v6);
        }
        else {
            this.nameTextView.setCompoundDrawablesWithIntrinsicBounds(v6, v6, v6, v6);
        }

        if(this.hasOnClick) {
            this.addButton.setVisibility(0);
            arg5 = DataQuery.getInstance(this.currentAccount).isStickerPackInstalled(arg4.set.id);
            this.isInstalled = arg5;
            if(arg5) {
                this.addButton.setBackgroundDrawable(this.delDrawable);
                v5 = this.addButton;
                v7 = "StickersRemove";
                v0_1 = 2131626143;
            }
            else {
                this.addButton.setBackgroundDrawable(this.addDrawable);
                v5 = this.addButton;
                v7 = "Add";
                v0_1 = 2131624010;
            }

            v5.setText(LocaleController.getString(v7, v0_1).toUpperCase());
            this.addButton.setPadding(AndroidUtilities.dp(17f), 0, AndroidUtilities.dp(17f), 0);
        }
        else {
            this.addButton.setVisibility(8);
        }

        this.set = arg4;
    }

    public void setUrl(CharSequence arg5, int arg6) {
        if(arg5 != null) {
            SpannableStringBuilder v0 = new SpannableStringBuilder(arg5);
            try {
                v0.setSpan(new ColorSpanUnderline(Theme.getColor("windowBackgroundWhiteBlueText4")), 0, arg6, 33);
                v0.setSpan(new ColorSpanUnderline(Theme.getColor("chat_emojiPanelTrendingDescription")), arg6, arg5.length(), 33);
                goto label_16;
            }
            catch(Exception ) {
            label_16:
                this.infoTextView.setText(((CharSequence)v0));
            }
        }
    }
}

