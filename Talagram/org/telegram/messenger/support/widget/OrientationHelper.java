package org.telegram.messenger.support.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup$LayoutParams;

public abstract class OrientationHelper {
    public static final int HORIZONTAL = 0;
    private static final int INVALID_SIZE = -2147483648;
    public static final int VERTICAL = 1;
    private int mLastTotalSpace;
    protected final LayoutManager mLayoutManager;
    final Rect mTmpRect;

    private OrientationHelper(LayoutManager arg2) {
        super();
        this.mLastTotalSpace = -2147483648;
        this.mTmpRect = new Rect();
        this.mLayoutManager = arg2;
    }

    OrientationHelper(LayoutManager arg1, org.telegram.messenger.support.widget.OrientationHelper$1 arg2) {
        this(arg1);
    }

    public static OrientationHelper createHorizontalHelper(LayoutManager arg1) {
        return new OrientationHelper(arg1) {
            public int getDecoratedEnd(View arg3) {
                return this.mLayoutManager.getDecoratedRight(arg3) + arg3.getLayoutParams().rightMargin;
            }

            public int getDecoratedMeasurement(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(arg3) + ((LayoutParams)v0).leftMargin + ((LayoutParams)v0).rightMargin;
            }

            public int getDecoratedMeasurementInOther(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(arg3) + ((LayoutParams)v0).topMargin + ((LayoutParams)v0).bottomMargin;
            }

            public int getDecoratedStart(View arg3) {
                return this.mLayoutManager.getDecoratedLeft(arg3) - arg3.getLayoutParams().leftMargin;
            }

            public int getEnd() {
                return this.mLayoutManager.getWidth();
            }

            public int getEndAfterPadding() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
            }

            public int getEndPadding() {
                return this.mLayoutManager.getPaddingRight();
            }

            public int getMode() {
                return this.mLayoutManager.getWidthMode();
            }

            public int getModeInOther() {
                return this.mLayoutManager.getHeightMode();
            }

            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingLeft();
            }

            public int getTotalSpace() {
                return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft() - this.mLayoutManager.getPaddingRight();
            }

            public int getTransformedEndWithDecoration(View arg4) {
                this.mLayoutManager.getTransformedBoundingBox(arg4, true, this.mTmpRect);
                return this.mTmpRect.right;
            }

            public int getTransformedStartWithDecoration(View arg4) {
                this.mLayoutManager.getTransformedBoundingBox(arg4, true, this.mTmpRect);
                return this.mTmpRect.left;
            }

            public void offsetChild(View arg1, int arg2) {
                arg1.offsetLeftAndRight(arg2);
            }

            public void offsetChildren(int arg2) {
                this.mLayoutManager.offsetChildrenHorizontal(arg2);
            }
        };
    }

    public static OrientationHelper createOrientationHelper(LayoutManager arg0, int arg1) {
        switch(arg1) {
            case 0: {
                goto label_7;
            }
            case 1: {
                goto label_5;
            }
        }

        throw new IllegalArgumentException("invalid orientation");
    label_5:
        return OrientationHelper.createVerticalHelper(arg0);
    label_7:
        return OrientationHelper.createHorizontalHelper(arg0);
    }

    public static OrientationHelper createVerticalHelper(LayoutManager arg1) {
        return new OrientationHelper(arg1) {
            public int getDecoratedEnd(View arg3) {
                return this.mLayoutManager.getDecoratedBottom(arg3) + arg3.getLayoutParams().bottomMargin;
            }

            public int getDecoratedMeasurement(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredHeight(arg3) + ((LayoutParams)v0).topMargin + ((LayoutParams)v0).bottomMargin;
            }

            public int getDecoratedMeasurementInOther(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.mLayoutManager.getDecoratedMeasuredWidth(arg3) + ((LayoutParams)v0).leftMargin + ((LayoutParams)v0).rightMargin;
            }

            public int getDecoratedStart(View arg3) {
                return this.mLayoutManager.getDecoratedTop(arg3) - arg3.getLayoutParams().topMargin;
            }

            public int getEnd() {
                return this.mLayoutManager.getHeight();
            }

            public int getEndAfterPadding() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
            }

            public int getEndPadding() {
                return this.mLayoutManager.getPaddingBottom();
            }

            public int getMode() {
                return this.mLayoutManager.getHeightMode();
            }

            public int getModeInOther() {
                return this.mLayoutManager.getWidthMode();
            }

            public int getStartAfterPadding() {
                return this.mLayoutManager.getPaddingTop();
            }

            public int getTotalSpace() {
                return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop() - this.mLayoutManager.getPaddingBottom();
            }

            public int getTransformedEndWithDecoration(View arg4) {
                this.mLayoutManager.getTransformedBoundingBox(arg4, true, this.mTmpRect);
                return this.mTmpRect.bottom;
            }

            public int getTransformedStartWithDecoration(View arg4) {
                this.mLayoutManager.getTransformedBoundingBox(arg4, true, this.mTmpRect);
                return this.mTmpRect.top;
            }

            public void offsetChild(View arg1, int arg2) {
                arg1.offsetTopAndBottom(arg2);
            }

            public void offsetChildren(int arg2) {
                this.mLayoutManager.offsetChildrenVertical(arg2);
            }
        };
    }

    public abstract int getDecoratedEnd(View arg1);

    public abstract int getDecoratedMeasurement(View arg1);

    public abstract int getDecoratedMeasurementInOther(View arg1);

    public abstract int getDecoratedStart(View arg1);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public int getTotalSpaceChange() {
        int v0 = -2147483648 == this.mLastTotalSpace ? 0 : this.getTotalSpace() - this.mLastTotalSpace;
        return v0;
    }

    public abstract int getTransformedEndWithDecoration(View arg1);

    public abstract int getTransformedStartWithDecoration(View arg1);

    public abstract void offsetChild(View arg1, int arg2);

    public abstract void offsetChildren(int arg1);

    public void onLayoutComplete() {
        this.mLastTotalSpace = this.getTotalSpace();
    }
}

