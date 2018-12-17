package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

public class SlidingTabView extends LinearLayout {
    public interface SlidingTabViewDelegate {
        void didSelectTab(int arg1);
    }

    private float animateTabXTo;
    private SlidingTabViewDelegate delegate;
    private DecelerateInterpolator interpolator;
    private Paint paint;
    private int selectedTab;
    private long startAnimationTime;
    private float startAnimationX;
    private int tabCount;
    private float tabWidth;
    private float tabX;
    private long totalAnimationDiff;

    public SlidingTabView(Context arg4) {
        super(arg4);
        this.selectedTab = 0;
        this.tabCount = 0;
        this.tabWidth = 0f;
        this.tabX = 0f;
        this.animateTabXTo = 0f;
        this.paint = new Paint();
        this.startAnimationTime = 0;
        this.totalAnimationDiff = 0;
        this.startAnimationX = 0f;
        this.setOrientation(0);
        this.setWeightSum(100f);
        this.paint.setColor(-1);
        this.setWillNotDraw(false);
        this.interpolator = new DecelerateInterpolator();
    }

    static void access$000(SlidingTabView arg0, int arg1) {
        arg0.didSelectTab(arg1);
    }

    public void addTextTab(int arg5, String arg6) {
        TextView v0 = new TextView(this.getContext());
        v0.setText(((CharSequence)arg6));
        v0.setFocusable(true);
        v0.setGravity(17);
        v0.setSingleLine();
        v0.setTextColor(-1);
        v0.setTextSize(1, 14f);
        v0.setTypeface(Typeface.DEFAULT_BOLD);
        v0.setBackgroundDrawable(Theme.createSelectorDrawable(-12763843, 0));
        v0.setOnClickListener(new View$OnClickListener(arg5) {
            public void onClick(View arg2) {
                SlidingTabView.this.didSelectTab(this.val$position);
            }
        });
        this.addView(((View)v0));
        ViewGroup$LayoutParams v5 = v0.getLayoutParams();
        ((LinearLayout$LayoutParams)v5).height = -1;
        ((LinearLayout$LayoutParams)v5).width = 0;
        ((LinearLayout$LayoutParams)v5).weight = 50f;
        v0.setLayoutParams(v5);
        ++this.tabCount;
    }

    private void animateToTab(int arg3) {
        this.animateTabXTo = (((float)arg3)) * this.tabWidth;
        this.startAnimationX = this.tabX;
        this.totalAnimationDiff = 0;
        this.startAnimationTime = System.currentTimeMillis();
        this.invalidate();
    }

    private void didSelectTab(int arg2) {
        if(this.selectedTab == arg2) {
            return;
        }

        this.selectedTab = arg2;
        this.animateToTab(arg2);
        if(this.delegate != null) {
            this.delegate.didSelectTab(arg2);
        }
    }

    public int getSeletedTab() {
        return this.selectedTab;
    }

    protected void onDraw(Canvas arg8) {
        if(this.tabX != this.animateTabXTo) {
            long v0 = System.currentTimeMillis() - this.startAnimationTime;
            this.startAnimationTime = System.currentTimeMillis();
            this.totalAnimationDiff += v0;
            long v2 = 200;
            if(this.totalAnimationDiff > v2) {
                this.totalAnimationDiff = v2;
                this.tabX = this.animateTabXTo;
            }
            else {
                this.tabX = this.startAnimationX + this.interpolator.getInterpolation((((float)this.totalAnimationDiff)) / 200f) * (this.animateTabXTo - this.startAnimationX);
                this.invalidate();
            }
        }

        arg8.drawRect(this.tabX, ((float)(this.getHeight() - AndroidUtilities.dp(2f))), this.tabX + this.tabWidth, ((float)this.getHeight()), this.paint);
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        super.onLayout(arg1, arg2, arg3, arg4, arg5);
        this.tabWidth = (((float)(arg4 - arg2))) / (((float)this.tabCount));
        float v1 = this.tabWidth * (((float)this.selectedTab));
        this.tabX = v1;
        this.animateTabXTo = v1;
    }

    public void setDelegate(SlidingTabViewDelegate arg1) {
        this.delegate = arg1;
    }
}

