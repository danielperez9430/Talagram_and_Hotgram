package org.telegram.messenger.support.widget;

import android.view.View;

class LayoutState {
    static final int INVALID_LAYOUT = -2147483648;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final String TAG = "LayoutState";
    int mAvailable;
    int mCurrentPosition;
    int mEndLine;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle;
    int mStartLine;
    boolean mStopInFocusable;

    LayoutState() {
        super();
        this.mRecycle = true;
        this.mStartLine = 0;
        this.mEndLine = 0;
    }

    boolean hasMore(State arg2) {
        boolean v2 = this.mCurrentPosition < 0 || this.mCurrentPosition >= arg2.getItemCount() ? false : true;
        return v2;
    }

    View next(Recycler arg3) {
        View v3 = arg3.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return v3;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
    }
}

