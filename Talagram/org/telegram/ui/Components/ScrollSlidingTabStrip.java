package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$FileLocation;
import org.telegram.ui.ActionBar.Theme;

public class ScrollSlidingTabStrip extends HorizontalScrollView {
    public interface ScrollSlidingTabStripDelegate {
        void onPageSelected(int arg1);
    }

    private int currentPosition;
    private LinearLayout$LayoutParams defaultTabLayoutParams;
    private ScrollSlidingTabStripDelegate delegate;
    private int dividerPadding;
    private int indicatorColor;
    private int indicatorHeight;
    private int lastScrollX;
    private Paint rectPaint;
    private int scrollOffset;
    private int tabCount;
    private int tabPadding;
    private LinearLayout tabsContainer;
    private int underlineColor;
    private int underlineHeight;

    public ScrollSlidingTabStrip(Context arg5) {
        super(arg5);
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.scrollOffset = AndroidUtilities.dp(52f);
        this.underlineHeight = AndroidUtilities.dp(2f);
        this.dividerPadding = AndroidUtilities.dp(12f);
        this.tabPadding = AndroidUtilities.dp(24f);
        this.lastScrollX = 0;
        this.setFillViewport(true);
        this.setWillNotDraw(false);
        this.setHorizontalScrollBarEnabled(false);
        this.tabsContainer = new LinearLayout(arg5);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        this.addView(this.tabsContainer);
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint$Style.FILL);
        this.defaultTabLayoutParams = new LinearLayout$LayoutParams(AndroidUtilities.dp(52f), -1);
    }

    static ScrollSlidingTabStripDelegate access$000(ScrollSlidingTabStrip arg0) {
        return arg0.delegate;
    }

    public void addIconTab(Drawable arg4) {
        int v0 = this.tabCount;
        this.tabCount = v0 + 1;
        ImageView v1 = new ImageView(this.getContext());
        boolean v2 = true;
        v1.setFocusable(true);
        v1.setImageDrawable(arg4);
        v1.setScaleType(ImageView$ScaleType.CENTER);
        v1.setOnClickListener(new View$OnClickListener(v0) {
            public void onClick(View arg2) {
                ScrollSlidingTabStrip.this.delegate.onPageSelected(this.val$position);
            }
        });
        this.tabsContainer.addView(((View)v1));
        if(v0 == this.currentPosition) {
        }
        else {
            v2 = false;
        }

        v1.setSelected(v2);
    }

    public TextView addIconTabWithCounter(Drawable arg13) {
        int v0 = this.tabCount;
        this.tabCount = v0 + 1;
        FrameLayout v1 = new FrameLayout(this.getContext());
        v1.setFocusable(true);
        this.tabsContainer.addView(((View)v1));
        ImageView v3 = new ImageView(this.getContext());
        v3.setImageDrawable(arg13);
        v3.setScaleType(ImageView$ScaleType.CENTER);
        v1.setOnClickListener(new View$OnClickListener(v0) {
            public void onClick(View arg2) {
                ScrollSlidingTabStrip.this.delegate.onPageSelected(this.val$position);
            }
        });
        v1.addView(((View)v3), LayoutHelper.createFrame(-1, -1f));
        boolean v13 = v0 == this.currentPosition ? true : false;
        v1.setSelected(v13);
        TextView v13_1 = new TextView(this.getContext());
        v13_1.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v13_1.setTextSize(1, 12f);
        v13_1.setTextColor(Theme.getColor("chat_emojiPanelBadgeText"));
        v13_1.setGravity(17);
        v13_1.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(9f), Theme.getColor("chat_emojiPanelBadgeBackground")));
        v13_1.setMinWidth(AndroidUtilities.dp(18f));
        v13_1.setPadding(AndroidUtilities.dp(5f), 0, AndroidUtilities.dp(5f), AndroidUtilities.dp(1f));
        v1.addView(((View)v13_1), LayoutHelper.createFrame(-2, 18f, 51, 26f, 6f, 0f, 0f));
        return v13_1;
    }

    public void addStickerTab(Chat arg7) {
        int v0 = this.tabCount;
        this.tabCount = v0 + 1;
        FrameLayout v1 = new FrameLayout(this.getContext());
        v1.setFocusable(true);
        v1.setOnClickListener(new View$OnClickListener(v0) {
            public void onClick(View arg2) {
                ScrollSlidingTabStrip.this.delegate.onPageSelected(this.val$position);
            }
        });
        this.tabsContainer.addView(((View)v1));
        boolean v0_1 = v0 == this.currentPosition ? true : false;
        v1.setSelected(v0_1);
        BackupImageView v0_2 = new BackupImageView(this.getContext());
        v0_2.setRoundRadius(AndroidUtilities.dp(15f));
        TLObject v3 = null;
        AvatarDrawable v4 = new AvatarDrawable();
        if(arg7.photo != null) {
            FileLocation v3_1 = arg7.photo.photo_small;
        }

        v4.setTextSize(AndroidUtilities.dp(14f));
        v4.setInfo(arg7);
        v0_2.setImage(v3, "50_50", ((Drawable)v4));
        v0_2.setAspectFit(true);
        v1.addView(((View)v0_2), LayoutHelper.createFrame(30, 30, 17));
    }

    public void addStickerTab(Document arg4) {
        int v0 = this.tabCount;
        this.tabCount = v0 + 1;
        FrameLayout v1 = new FrameLayout(this.getContext());
        v1.setTag(arg4);
        v1.setFocusable(true);
        v1.setOnClickListener(new View$OnClickListener(v0) {
            public void onClick(View arg2) {
                ScrollSlidingTabStrip.this.delegate.onPageSelected(this.val$position);
            }
        });
        this.tabsContainer.addView(((View)v1));
        boolean v0_1 = v0 == this.currentPosition ? true : false;
        v1.setSelected(v0_1);
        BackupImageView v0_2 = new BackupImageView(this.getContext());
        v0_2.setAspectFit(true);
        v1.addView(((View)v0_2), LayoutHelper.createFrame(30, 30, 17));
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    protected void onDraw(Canvas arg14) {
        float v8;
        float v10;
        super.onDraw(arg14);
        if(!this.isInEditMode()) {
            if(this.tabCount == 0) {
            }
            else {
                int v0 = this.getHeight();
                this.rectPaint.setColor(this.underlineColor);
                float v11 = ((float)v0);
                arg14.drawRect(0f, ((float)(v0 - this.underlineHeight)), ((float)this.tabsContainer.getWidth()), v11, this.rectPaint);
                View v1 = this.tabsContainer.getChildAt(this.currentPosition);
                if(v1 != null) {
                    float v2 = ((float)v1.getLeft());
                    v10 = ((float)v1.getRight());
                    v8 = v2;
                }
                else {
                    v8 = 0f;
                    v10 = 0f;
                }

                this.rectPaint.setColor(this.indicatorColor);
                float v9 = this.indicatorHeight == 0 ? 0f : ((float)(v0 - this.indicatorHeight));
                arg14.drawRect(v8, v9, v10, v11, this.rectPaint);
            }
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.setImages();
    }

    public void onPageScrolled(int arg5, int arg6) {
        if(this.currentPosition == arg5) {
            return;
        }

        this.currentPosition = arg5;
        if(arg5 >= this.tabsContainer.getChildCount()) {
            return;
        }

        int v1;
        for(v1 = 0; true; ++v1) {
            boolean v3 = true;
            if(v1 >= this.tabsContainer.getChildCount()) {
                break;
            }

            View v2 = this.tabsContainer.getChildAt(v1);
            if(v1 == arg5) {
            }
            else {
                v3 = false;
            }

            v2.setSelected(v3);
        }

        if(arg6 == arg5 && arg5 > 1) {
            --arg5;
        }

        this.scrollToChild(arg5);
        this.invalidate();
    }

    protected void onScrollChanged(int arg6, int arg7, int arg8, int arg9) {
        super.onScrollChanged(arg6, arg7, arg8, arg9);
        arg7 = AndroidUtilities.dp(52f);
        arg8 /= arg7;
        arg6 /= arg7;
        arg7 = (((int)Math.ceil(((double)((((float)this.getMeasuredWidth())) / (((float)arg7))))))) + 1;
        arg9 = Math.max(0, Math.min(arg8, arg6));
        arg8 = Math.min(this.tabsContainer.getChildCount(), Math.max(arg8, arg6) + arg7);
        while(arg9 < arg8) {
            View v1 = this.tabsContainer.getChildAt(arg9);
            if(v1 == null) {
            }
            else {
                Object v2 = v1.getTag();
                if(!(v2 instanceof Document)) {
                }
                else {
                    v1 = ((FrameLayout)v1).getChildAt(0);
                    String v3 = null;
                    if(arg9 >= arg6) {
                        if(arg9 >= arg6 + arg7) {
                        }
                        else {
                            if(((Document)v2).thumb != null) {
                                ((BackupImageView)v1).setImage(((Document)v2).thumb.location, v3, "webp", ((Drawable)v3));
                            }
                            else {
                            }

                            goto label_44;
                        }
                    }

                    ((BackupImageView)v1).setImageDrawable(((Drawable)v3));
                }
            }

        label_44:
            ++arg9;
        }
    }

    public void removeTabs() {
        this.tabsContainer.removeAllViews();
        this.tabCount = 0;
        this.currentPosition = 0;
    }

    private void scrollToChild(int arg5) {
        if(this.tabCount != 0) {
            if(this.tabsContainer.getChildAt(arg5) == null) {
            }
            else {
                int v0 = this.tabsContainer.getChildAt(arg5).getLeft();
                if(arg5 > 0) {
                    v0 -= this.scrollOffset;
                }

                arg5 = this.getScrollX();
                if(v0 == this.lastScrollX) {
                    return;
                }

                if(v0 >= arg5) {
                    if(this.scrollOffset + v0 > arg5 + this.getWidth() - this.scrollOffset * 2) {
                        v0 = v0 - this.getWidth() + this.scrollOffset * 3;
                    }
                    else {
                        return;
                    }
                }

                this.lastScrollX = v0;
                this.smoothScrollTo(this.lastScrollX, 0);
            }
        }
    }

    public void selectTab(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.tabCount) {
            }
            else {
                this.tabsContainer.getChildAt(arg2).performClick();
            }
        }
    }

    public void setDelegate(ScrollSlidingTabStripDelegate arg1) {
        this.delegate = arg1;
    }

    public void setImages() {
        int v0 = AndroidUtilities.dp(52f);
        int v1 = this.getScrollX() / v0;
        v0 = Math.min(this.tabsContainer.getChildCount(), (((int)Math.ceil(((double)((((float)this.getMeasuredWidth())) / (((float)v0))))))) + v1 + 1);
        while(v1 < v0) {
            View v2 = this.tabsContainer.getChildAt(v1);
            Object v3 = v2.getTag();
            if(!(v3 instanceof Document)) {
            }
            else {
                ((FrameLayout)v2).getChildAt(0).setImage(((Document)v3).thumb.location, null, "webp", null);
            }

            ++v1;
        }
    }

    public void setIndicatorColor(int arg1) {
        this.indicatorColor = arg1;
        this.invalidate();
    }

    public void setIndicatorHeight(int arg1) {
        this.indicatorHeight = arg1;
        this.invalidate();
    }

    public void setUnderlineColor(int arg1) {
        this.underlineColor = arg1;
        this.invalidate();
    }

    public void setUnderlineColorResource(int arg2) {
        this.underlineColor = this.getResources().getColor(arg2);
        this.invalidate();
    }

    public void setUnderlineHeight(int arg1) {
        this.underlineHeight = arg1;
        this.invalidate();
    }

    public void updateTabStyles() {
        int v0;
        for(v0 = 0; v0 < this.tabCount; ++v0) {
            this.tabsContainer.getChildAt(v0).setLayoutParams(this.defaultTabLayoutParams);
        }
    }
}

