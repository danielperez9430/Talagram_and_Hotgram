package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DataQuery;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$TL_documentAttributeSticker;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class StickerEmojiCell extends FrameLayout {
    private float alpha;
    private boolean changingAlpha;
    private int currentAccount;
    private TextView emojiTextView;
    private BackupImageView imageView;
    private static AccelerateInterpolator interpolator;
    private long lastUpdateTime;
    private boolean recent;
    private float scale;
    private boolean scaled;
    private Document sticker;
    private long time;

    static {
        StickerEmojiCell.interpolator = new AccelerateInterpolator(0.5f);
    }

    public StickerEmojiCell(Context arg5) {
        super(arg5);
        this.alpha = 1f;
        this.currentAccount = UserConfig.selectedAccount;
        this.imageView = new BackupImageView(arg5);
        this.imageView.setAspectFit(true);
        this.addView(this.imageView, LayoutHelper.createFrame(66, 66, 17));
        this.emojiTextView = new TextView(arg5);
        this.emojiTextView.setTextSize(1, 16f);
        this.addView(this.emojiTextView, LayoutHelper.createFrame(28, 28, 85));
    }

    public void disable() {
        this.changingAlpha = true;
        this.alpha = 0.5f;
        this.time = 0;
        this.imageView.getImageReceiver().setAlpha(this.alpha);
        this.imageView.invalidate();
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    protected boolean drawChild(Canvas arg5, View arg6, long arg7) {
        boolean v5 = super.drawChild(arg5, arg6, arg7);
        if(arg6 == this.imageView) {
            float v7 = 0.8f;
            float v8 = 1f;
            if(!this.changingAlpha && (!this.scaled || this.scale == v7)) {
                if(this.scaled) {
                    return v5;
                }

                if(this.scale == v8) {
                    return v5;
                }
            }

            long v0 = System.currentTimeMillis();
            long v2 = v0 - this.lastUpdateTime;
            this.lastUpdateTime = v0;
            if(this.changingAlpha) {
                this.time += v2;
                v0 = 1050;
                if(this.time > v0) {
                    this.time = v0;
                }

                this.alpha = StickerEmojiCell.interpolator.getInterpolation((((float)this.time)) / 1050f) * 0.5f + 0.5f;
                if(this.alpha >= v8) {
                    this.changingAlpha = false;
                    this.alpha = v8;
                }

                this.imageView.getImageReceiver().setAlpha(this.alpha);
            }
            else {
                float v0_1 = 400f;
                if((this.scaled) && this.scale != v7) {
                    this.scale -= (((float)v2)) / v0_1;
                    if(this.scale < v7) {
                        this.scale = v7;
                    }
                    else {
                    }

                    goto label_70;
                }

                this.scale += (((float)v2)) / v0_1;
                if(this.scale <= v8) {
                    goto label_70;
                }

                this.scale = v8;
            }

        label_70:
            this.imageView.setScaleX(this.scale);
            this.imageView.setScaleY(this.scale);
            this.imageView.invalidate();
            this.invalidate();
        }

        return v5;
    }

    public Document getSticker() {
        return this.sticker;
    }

    public void invalidate() {
        this.emojiTextView.invalidate();
        super.invalidate();
    }

    public boolean isDisabled() {
        return this.changingAlpha;
    }

    public boolean isRecent() {
        return this.recent;
    }

    public void setRecent(boolean arg1) {
        this.recent = arg1;
    }

    public void setScaled(boolean arg3) {
        this.scaled = arg3;
        this.lastUpdateTime = System.currentTimeMillis();
        this.invalidate();
    }

    public void setSticker(Document arg6, boolean arg7) {
        TextView v6_1;
        int v6;
        float v2;
        int v7;
        if(arg6 != null) {
            this.sticker = arg6;
            if(arg6.thumb != null) {
                this.imageView.setImage(arg6.thumb.location, null, "webp", null);
            }

            if(arg7) {
                v7 = 0;
                int v0 = 0;
                while(true) {
                    v2 = 16f;
                    if(v0 < arg6.attributes.size()) {
                        Object v1 = arg6.attributes.get(v0);
                        if(!(v1 instanceof TL_documentAttributeSticker)) {
                            ++v0;
                            continue;
                        }
                        else if(((DocumentAttribute)v1).alt == null) {
                            break;
                        }
                        else if(((DocumentAttribute)v1).alt.length() > 0) {
                            this.emojiTextView.setText(Emoji.replaceEmoji(((DocumentAttribute)v1).alt, this.emojiTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(v2), false));
                            v6 = 1;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        break;
                    }

                    goto label_39;
                }

                v6 = 0;
            label_39:
                if(v6 == 0) {
                    this.emojiTextView.setText(Emoji.replaceEmoji(DataQuery.getInstance(this.currentAccount).getEmojiForSticker(this.sticker.id), this.emojiTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(v2), false));
                }

                v6_1 = this.emojiTextView;
            }
            else {
                v6_1 = this.emojiTextView;
                v7 = 4;
            }

            v6_1.setVisibility(v7);
        }
    }

    public boolean showingBitmap() {
        boolean v0 = this.imageView.getImageReceiver().getBitmap() != null ? true : false;
        return v0;
    }
}

