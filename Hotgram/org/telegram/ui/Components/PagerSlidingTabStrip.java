package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager$f;
import android.support.v4.view.ViewPager;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.FrameLayout$LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    public interface IconTabProvider {
        boolean canScrollToTab(int arg1);

        void customOnDraw(Canvas arg1, int arg2);

        Drawable getPageIconDrawable(int arg1);
    }

    class PageListener implements f {
        PageListener(PagerSlidingTabStrip arg1, org.telegram.ui.Components.PagerSlidingTabStrip$1 arg2) {
            this(arg1);
        }

        private PageListener(PagerSlidingTabStrip arg1) {
            PagerSlidingTabStrip.this = arg1;
            super();
        }

        public void onPageScrollStateChanged(int arg4) {
            if(arg4 == 0) {
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.pager.getCurrentItem(), 0);
            }

            if(PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrollStateChanged(arg4);
            }
        }

        public void onPageScrolled(int arg3, float arg4, int arg5) {
            PagerSlidingTabStrip.this.currentPosition = arg3;
            PagerSlidingTabStrip.this.currentPositionOffset = arg4;
            PagerSlidingTabStrip.this.scrollToChild(arg3, ((int)((((float)PagerSlidingTabStrip.this.tabsContainer.getChildAt(arg3).getWidth())) * arg4)));
            PagerSlidingTabStrip.this.invalidate();
            if(PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageScrolled(arg3, arg4, arg5);
            }
        }

        public void onPageSelected(int arg5) {
            if(PagerSlidingTabStrip.this.delegatePageListener != null) {
                PagerSlidingTabStrip.this.delegatePageListener.onPageSelected(arg5);
            }

            int v1;
            for(v1 = 0; v1 < PagerSlidingTabStrip.this.tabsContainer.getChildCount(); ++v1) {
                View v2 = PagerSlidingTabStrip.this.tabsContainer.getChildAt(v1);
                boolean v3 = v1 == arg5 ? true : false;
                v2.setSelected(v3);
            }
        }
    }

    private int currentPosition;
    private float currentPositionOffset;
    private LinearLayout$LayoutParams defaultTabLayoutParams;
    public f delegatePageListener;
    private int dividerPadding;
    private int indicatorColor;
    private int indicatorHeight;
    private int lastScrollX;
    private final PageListener pageListener;
    private ViewPager pager;
    private Paint rectPaint;
    private int scrollOffset;
    private boolean shouldExpand;
    private int tabCount;
    private int tabPadding;
    private LinearLayout tabsContainer;
    private int underlineColor;
    private int underlineHeight;

    public PagerSlidingTabStrip(Context arg4) {
        super(arg4);
        this.pageListener = new PageListener(this, null);
        this.currentPosition = 0;
        this.currentPositionOffset = 0f;
        this.indicatorColor = -10066330;
        this.underlineColor = 436207616;
        this.shouldExpand = false;
        this.scrollOffset = AndroidUtilities.dp(52f);
        this.indicatorHeight = AndroidUtilities.dp(8f);
        this.underlineHeight = AndroidUtilities.dp(2f);
        this.dividerPadding = AndroidUtilities.dp(12f);
        this.tabPadding = AndroidUtilities.dp(24f);
        this.lastScrollX = 0;
        this.setFillViewport(true);
        this.setWillNotDraw(false);
        this.tabsContainer = new LinearLayout(arg4);
        this.tabsContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        this.addView(this.tabsContainer);
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint$Style.FILL);
        this.defaultTabLayoutParams = new LinearLayout$LayoutParams(-2, -1);
    }

    static int access$100(PagerSlidingTabStrip arg0) {
        return arg0.currentPosition;
    }

    static int access$102(PagerSlidingTabStrip arg0, int arg1) {
        arg0.currentPosition = arg1;
        return arg1;
    }

    static ViewPager access$200(PagerSlidingTabStrip arg0) {
        return arg0.pager;
    }

    static void access$300(PagerSlidingTabStrip arg0, int arg1, int arg2) {
        arg0.scrollToChild(arg1, arg2);
    }

    static float access$402(PagerSlidingTabStrip arg0, float arg1) {
        arg0.currentPositionOffset = arg1;
        return arg1;
    }

    static LinearLayout access$500(PagerSlidingTabStrip arg0) {
        return arg0.tabsContainer;
    }

    private void addIconTab(int arg3, Drawable arg4) {
        org.telegram.ui.Components.PagerSlidingTabStrip$2 v0 = new ImageView(this.getContext(), arg3) {
            protected void onDraw(Canvas arg3) {
                super.onDraw(arg3);
                if((PagerSlidingTabStrip.this.pager.getAdapter() instanceof IconTabProvider)) {
                    PagerSlidingTabStrip.this.pager.getAdapter().customOnDraw(arg3, this.val$position);
                }
            }
        };
        boolean v1 = true;
        ((ImageView)v0).setFocusable(true);
        ((ImageView)v0).setImageDrawable(arg4);
        ((ImageView)v0).setScaleType(ImageView$ScaleType.CENTER);
        ((ImageView)v0).setOnClickListener(new View$OnClickListener(arg3) {
            public void onClick(View arg2) {
                if(((PagerSlidingTabStrip.this.pager.getAdapter() instanceof IconTabProvider)) && !PagerSlidingTabStrip.this.pager.getAdapter().canScrollToTab(this.val$position)) {
                    return;
                }

                PagerSlidingTabStrip.this.pager.setCurrentItem(this.val$position);
            }
        });
        this.tabsContainer.addView(((View)v0));
        if(arg3 == this.currentPosition) {
        }
        else {
            v1 = false;
        }

        ((ImageView)v0).setSelected(v1);
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public View getTab(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.tabsContainer.getChildCount()) {
            }
            else {
                return this.tabsContainer.getChildAt(arg2);
            }
        }

        return null;
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public int getUnderlineColor() {
        return this.underlineColor;
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    public void notifyDataSetChanged() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().getCount();
        int v0;
        for(v0 = 0; v0 < this.tabCount; ++v0) {
            if((this.pager.getAdapter() instanceof IconTabProvider)) {
                this.addIconTab(v0, this.pager.getAdapter().getPageIconDrawable(v0));
            }
        }

        this.updateTabStyles();
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)this));
                PagerSlidingTabStrip.this.currentPosition = PagerSlidingTabStrip.this.pager.getCurrentItem();
                PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.currentPosition, 0);
            }
        });
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
                float v2 = ((float)v1.getLeft());
                float v1_1 = ((float)v1.getRight());
                if(this.currentPositionOffset <= 0f || this.currentPosition >= this.tabCount - 1) {
                    v10 = v1_1;
                    v8 = v2;
                }
                else {
                    View v3 = this.tabsContainer.getChildAt(this.currentPosition + 1);
                    float v4 = ((float)v3.getLeft());
                    float v3_1 = ((float)v3.getRight());
                    float v5 = this.currentPositionOffset * v4 + (1f - this.currentPositionOffset) * v2;
                    v10 = this.currentPositionOffset * v3_1 + (1f - this.currentPositionOffset) * v1_1;
                    v8 = v5;
                }

                this.rectPaint.setColor(this.indicatorColor);
                arg14.drawRect(v8, ((float)(v0 - this.indicatorHeight)), v10, v11, this.rectPaint);
            }
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(arg3, arg4);
        if(this.shouldExpand) {
            if(View$MeasureSpec.getMode(arg3) == 0) {
            }
            else {
                this.tabsContainer.measure(this.getMeasuredWidth() | 1073741824, arg4);
            }
        }
    }

    public void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        if(!this.shouldExpand) {
            this.post(new Runnable() {
                public void run() {
                    PagerSlidingTabStrip.this.notifyDataSetChanged();
                }
            });
        }
    }

    private void scrollToChild(int arg2, int arg3) {
        if(this.tabCount == 0) {
            return;
        }

        int v0 = this.tabsContainer.getChildAt(arg2).getLeft() + arg3;
        if(arg2 > 0 || arg3 > 0) {
            v0 -= this.scrollOffset;
        }

        if(v0 != this.lastScrollX) {
            this.lastScrollX = v0;
            this.scrollTo(v0, 0);
        }
    }

    public void setDividerPadding(int arg1) {
        this.dividerPadding = arg1;
        this.invalidate();
    }

    public void setIndicatorColor(int arg1) {
        this.indicatorColor = arg1;
        this.invalidate();
    }

    public void setIndicatorColorResource(int arg2) {
        this.indicatorColor = this.getResources().getColor(arg2);
        this.invalidate();
    }

    public void setIndicatorHeight(int arg1) {
        this.indicatorHeight = arg1;
        this.invalidate();
    }

    public void setOnPageChangeListener(f arg1) {
        this.delegatePageListener = arg1;
    }

    public void setScrollOffset(int arg1) {
        this.scrollOffset = arg1;
        this.invalidate();
    }

    public void setShouldExpand(boolean arg3) {
        this.shouldExpand = arg3;
        this.tabsContainer.setLayoutParams(new FrameLayout$LayoutParams(-1, -1));
        this.updateTabStyles();
        this.requestLayout();
    }

    public void setTabPaddingLeftRight(int arg1) {
        this.tabPadding = arg1;
        this.updateTabStyles();
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

    public void setViewPager(ViewPager arg2) {
        this.pager = arg2;
        if(arg2.getAdapter() != null) {
            arg2.setOnPageChangeListener(this.pageListener);
            this.notifyDataSetChanged();
            return;
        }

        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    private void updateTabStyles() {
        int v1;
        for(v1 = 0; v1 < this.tabCount; ++v1) {
            View v2 = this.tabsContainer.getChildAt(v1);
            v2.setLayoutParams(this.defaultTabLayoutParams);
            if(this.shouldExpand) {
                v2.setPadding(0, 0, 0, 0);
                v2.setLayoutParams(new LinearLayout$LayoutParams(-1, -1, 1f));
            }
            else {
                v2.setPadding(this.tabPadding, 0, this.tabPadding, 0);
            }
        }
    }
}

