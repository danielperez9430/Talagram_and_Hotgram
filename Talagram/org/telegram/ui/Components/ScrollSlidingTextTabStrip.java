package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.annotation.Keep;
import android.util.SparseIntArray;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class ScrollSlidingTextTabStrip extends HorizontalScrollView {
    class org.telegram.ui.Components.ScrollSlidingTextTabStrip$1 implements Runnable {
        org.telegram.ui.Components.ScrollSlidingTextTabStrip$1(ScrollSlidingTextTabStrip arg1) {
            ScrollSlidingTextTabStrip.this = arg1;
            super();
        }

        public void run() {
            if(!ScrollSlidingTextTabStrip.this.animatingIndicator) {
                return;
            }

            long v0 = SystemClock.elapsedRealtime() - ScrollSlidingTextTabStrip.this.lastAnimationTime;
            long v2 = 17;
            if(v0 > v2) {
                v0 = v2;
            }

            ScrollSlidingTextTabStrip.this.animationTime += (((float)v0)) / 200f;
            ScrollSlidingTextTabStrip.this.setAnimationIdicatorProgress(ScrollSlidingTextTabStrip.this.interpolator.getInterpolation(ScrollSlidingTextTabStrip.this.animationTime));
            float v1 = 1f;
            if(ScrollSlidingTextTabStrip.this.animationTime > v1) {
                ScrollSlidingTextTabStrip.this.animationTime = v1;
            }

            if(ScrollSlidingTextTabStrip.this.animationTime < v1) {
                AndroidUtilities.runOnUIThread(ScrollSlidingTextTabStrip.this.animationRunnable);
            }
            else {
                ScrollSlidingTextTabStrip.this.animatingIndicator = false;
                ScrollSlidingTextTabStrip.this.setEnabled(true);
                if(ScrollSlidingTextTabStrip.this.delegate != null) {
                    ScrollSlidingTextTabStrip.this.delegate.onPageScrolled(v1);
                }
            }
        }
    }

    public interface ScrollSlidingTabStripDelegate {
        void onPageScrolled(float arg1);

        void onPageSelected(int arg1, boolean arg2);
    }

    private int allTextWidth;
    private int animateIndicatorStartWidth;
    private int animateIndicatorStartX;
    private int animateIndicatorToWidth;
    private int animateIndicatorToX;
    private boolean animatingIndicator;
    private float animationIdicatorProgress;
    private Runnable animationRunnable;
    private boolean animationRunning;
    private float animationTime;
    private int currentPosition;
    private ScrollSlidingTabStripDelegate delegate;
    private SparseIntArray idToPosition;
    private int indicatorWidth;
    private int indicatorX;
    private CubicBezierInterpolator interpolator;
    private long lastAnimationTime;
    private SparseIntArray positionToId;
    private SparseIntArray positionToWidth;
    private int prevLayoutWidth;
    private int previousPosition;
    private Paint rectPaint;
    private int selectedTabId;
    private int tabCount;
    private LinearLayout tabsContainer;

    public ScrollSlidingTextTabStrip(Context arg5) {
        super(arg5);
        this.selectedTabId = -1;
        this.interpolator = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.positionToId = new SparseIntArray(5);
        this.idToPosition = new SparseIntArray(5);
        this.positionToWidth = new SparseIntArray(5);
        this.animationRunnable = new org.telegram.ui.Components.ScrollSlidingTextTabStrip$1(this);
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
        this.rectPaint.setColor(Theme.getColor("actionBarDefaultTitle"));
    }

    static boolean access$000(ScrollSlidingTextTabStrip arg0) {
        return arg0.animatingIndicator;
    }

    static boolean access$002(ScrollSlidingTextTabStrip arg0, boolean arg1) {
        arg0.animatingIndicator = arg1;
        return arg1;
    }

    static long access$100(ScrollSlidingTextTabStrip arg2) {
        return arg2.lastAnimationTime;
    }

    static float access$200(ScrollSlidingTextTabStrip arg0) {
        return arg0.animationTime;
    }

    static float access$202(ScrollSlidingTextTabStrip arg0, float arg1) {
        arg0.animationTime = arg1;
        return arg1;
    }

    static CubicBezierInterpolator access$300(ScrollSlidingTextTabStrip arg0) {
        return arg0.interpolator;
    }

    static Runnable access$400(ScrollSlidingTextTabStrip arg0) {
        return arg0.animationRunnable;
    }

    static ScrollSlidingTabStripDelegate access$500(ScrollSlidingTextTabStrip arg0) {
        return arg0.delegate;
    }

    public void addTextTab(int arg7, CharSequence arg8) {
        int v0 = this.tabCount;
        this.tabCount = v0 + 1;
        int v1 = -1;
        if(v0 == 0 && this.selectedTabId == v1) {
            this.selectedTabId = arg7;
        }

        this.positionToId.put(v0, arg7);
        this.idToPosition.put(arg7, v0);
        if(this.selectedTabId != v1 && this.selectedTabId == arg7) {
            this.currentPosition = v0;
            this.prevLayoutWidth = 0;
        }

        TextView v2 = new TextView(this.getContext());
        v2.setGravity(17);
        v2.setText(arg8);
        v2.setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor("actionBarDefaultSelector"), 2));
        String v4 = this.selectedTabId == arg7 ? "actionBarDefaultTitle" : "actionBarDefaultSubtitle";
        v2.setTag(v4);
        v4 = this.currentPosition == v0 ? "actionBarDefaultTitle" : "actionBarDefaultSubtitle";
        v2.setTextColor(Theme.getColor(v4));
        v2.setTextSize(1, 14f);
        v2.setSingleLine(true);
        v2.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v2.setPadding(AndroidUtilities.dp(8f), 0, AndroidUtilities.dp(8f), 0);
        v2.setOnClickListener(new -$$Lambda$ScrollSlidingTextTabStrip$9m5R2Lhhk-z3JuL73ha6pd-2NRk(this, arg7));
        arg7 = (((int)Math.ceil(((double)v2.getPaint().measureText(arg8, 0, arg8.length()))))) + AndroidUtilities.dp(16f);
        this.allTextWidth += arg7;
        this.positionToWidth.put(v0, arg7);
        this.tabsContainer.addView(((View)v2), LayoutHelper.createLinear(0, v1));
    }

    protected boolean drawChild(Canvas arg7, View arg8, long arg9) {
        boolean v9 = super.drawChild(arg7, arg8, arg9);
        if(arg8 == this.tabsContainer) {
            int v8 = this.getMeasuredHeight();
            arg7.drawRect(((float)this.indicatorX), ((float)(v8 - AndroidUtilities.dp(2f))), ((float)(this.indicatorX + this.indicatorWidth)), ((float)v8), this.rectPaint);
        }

        return v9;
    }

    public float getAnimationIdicatorProgress() {
        return this.animationIdicatorProgress;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public int getCurrentTabId() {
        return this.selectedTabId;
    }

    public int getFirstTabId() {
        return this.positionToId.get(0, 0);
    }

    public int getNextPageId(boolean arg4) {
        SparseIntArray v0 = this.positionToId;
        int v1 = this.currentPosition;
        int v2 = -1;
        int v4 = arg4 ? 1 : -1;
        return v0.get(v1 + v4, v2);
    }

    public Paint getRectPaint() {
        return this.rectPaint;
    }

    public View getTabsContainer() {
        return this.tabsContainer;
    }

    public int getTabsCount() {
        return this.tabCount;
    }

    public boolean hasTab(int arg3) {
        boolean v3 = this.idToPosition.get(arg3, -1) != -1 ? true : false;
        return v3;
    }

    public boolean isAnimatingIndicator() {
        return this.animatingIndicator;
    }

    public static void lambda$addTextTab$0(ScrollSlidingTextTabStrip arg5, int arg6, View arg7) {
        int v0 = arg5.tabsContainer.indexOfChild(arg7);
        if(v0 >= 0) {
            if(v0 == arg5.currentPosition) {
            }
            else {
                boolean v1 = arg5.currentPosition < v0 ? true : false;
                arg5.previousPosition = arg5.currentPosition;
                arg5.currentPosition = v0;
                arg5.selectedTabId = arg6;
                if(arg5.animatingIndicator) {
                    AndroidUtilities.cancelRunOnUIThread(arg5.animationRunnable);
                    arg5.animatingIndicator = false;
                }

                arg5.animationTime = 0f;
                arg5.animatingIndicator = true;
                arg5.animateIndicatorStartX = arg5.indicatorX;
                arg5.animateIndicatorStartWidth = arg5.indicatorWidth;
                arg5.animateIndicatorToX = arg7.getLeft();
                arg5.animateIndicatorToWidth = arg7.getMeasuredWidth();
                arg5.setEnabled(false);
                AndroidUtilities.runOnUIThread(arg5.animationRunnable, 16);
                if(arg5.delegate != null) {
                    arg5.delegate.onPageSelected(arg6, v1);
                }

                arg5.scrollToChild(v0);
            }
        }
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        arg4 -= arg2;
        if(this.prevLayoutWidth != arg4) {
            this.prevLayoutWidth = arg4;
            if(this.animatingIndicator) {
                AndroidUtilities.cancelRunOnUIThread(this.animationRunnable);
                this.animatingIndicator = false;
                this.setEnabled(true);
                if(this.delegate != null) {
                    this.delegate.onPageScrolled(1f);
                }
            }

            View v1 = this.tabsContainer.getChildAt(this.currentPosition);
            if(v1 == null) {
                return;
            }

            this.indicatorX = v1.getLeft();
            this.indicatorWidth = v1.getMeasuredWidth();
        }
    }

    protected void onMeasure(int arg9, int arg10) {
        float v4;
        int v0 = View$MeasureSpec.getSize(arg9);
        int v1 = this.tabsContainer.getChildCount();
        int v3;
        for(v3 = 0; true; ++v3) {
            v4 = 1f;
            if(v3 >= v1) {
                break;
            }

            ViewGroup$LayoutParams v6 = this.tabsContainer.getChildAt(v3).getLayoutParams();
            if(this.allTextWidth > v0) {
                ((LinearLayout$LayoutParams)v6).weight = 0f;
                ((LinearLayout$LayoutParams)v6).width = -2;
            }
            else {
                ((LinearLayout$LayoutParams)v6).weight = v4 / (((float)this.allTextWidth)) * (((float)this.positionToWidth.get(v3)));
                ((LinearLayout$LayoutParams)v6).width = 0;
            }
        }

        if(this.allTextWidth > v0) {
            this.tabsContainer.setWeightSum(0f);
        }
        else {
            this.tabsContainer.setWeightSum(v4);
        }

        super.onMeasure(arg9, arg10);
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

    public void removeTabs() {
        this.positionToId.clear();
        this.idToPosition.clear();
        this.positionToWidth.clear();
        this.tabsContainer.removeAllViews();
        this.allTextWidth = 0;
        this.tabCount = 0;
    }

    private void scrollToChild(int arg4) {
        if(this.tabCount == 0) {
            return;
        }

        View v4 = this.tabsContainer.getChildAt(arg4);
        if(v4 == null) {
            return;
        }

        int v0 = this.getScrollX();
        int v1 = v4.getLeft();
        arg4 = v4.getMeasuredWidth();
        if(v1 >= v0) {
            v1 += arg4;
            if(v1 > v0 + this.getWidth()) {
                goto label_12;
            }
        }
        else {
        label_12:
            this.smoothScrollTo(v1, 0);
        }
    }

    public void selectTabWithId(int arg6, float arg7) {
        // Method was not decompiled
    }

    @Keep public void setAnimationIdicatorProgress(float arg4) {
        this.animationIdicatorProgress = arg4;
        this.setAnimationProgressInernal(this.tabsContainer.getChildAt(this.currentPosition), this.tabsContainer.getChildAt(this.previousPosition), arg4);
        if(this.delegate != null) {
            this.delegate.onPageScrolled(arg4);
        }
    }

    private void setAnimationProgressInernal(TextView arg16, TextView arg17, float arg18) {
        int v2 = Theme.getColor("actionBarDefaultTitle");
        int v3 = Theme.getColor("actionBarDefaultSubtitle");
        int v4 = Color.red(v2);
        int v5 = Color.green(v2);
        int v6 = Color.blue(v2);
        v2 = Color.alpha(v2);
        int v7 = Color.red(v3);
        int v8 = Color.green(v3);
        int v9 = Color.blue(v3);
        v3 = Color.alpha(v3);
        arg17.setTextColor(Color.argb(((int)((((float)v2)) + (((float)(v3 - v2))) * arg18)), ((int)((((float)v4)) + (((float)(v7 - v4))) * arg18)), ((int)((((float)v5)) + (((float)(v8 - v5))) * arg18)), ((int)((((float)v6)) + (((float)(v9 - v6))) * arg18))));
        arg16.setTextColor(Color.argb(((int)((((float)v3)) + (((float)(v2 - v3))) * arg18)), ((int)((((float)v7)) + (((float)(v4 - v7))) * arg18)), ((int)((((float)v8)) + (((float)(v5 - v8))) * arg18)), ((int)((((float)v9)) + (((float)(v6 - v9))) * arg18))));
        this.indicatorX = ((int)((((float)this.animateIndicatorStartX)) + (((float)(this.animateIndicatorToX - this.animateIndicatorStartX))) * arg18));
        this.indicatorWidth = ((int)((((float)this.animateIndicatorStartWidth)) + (((float)(this.animateIndicatorToWidth - this.animateIndicatorStartWidth))) * arg18));
        this.invalidate();
    }

    public void setDelegate(ScrollSlidingTabStripDelegate arg1) {
        this.delegate = arg1;
    }

    public void setEnabled(boolean arg4) {
        super.setEnabled(arg4);
        int v0 = this.tabsContainer.getChildCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.tabsContainer.getChildAt(v1).setEnabled(arg4);
        }
    }
}

