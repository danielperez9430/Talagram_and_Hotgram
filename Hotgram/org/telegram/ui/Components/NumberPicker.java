package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint$Align;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View$MeasureSpec;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import org.telegram.ui.ActionBar.Theme;

public class NumberPicker extends LinearLayout {
    class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;

        ChangeCurrentByOneFromLongPressCommand(NumberPicker arg1) {
            NumberPicker.this = arg1;
            super();
        }

        static void access$000(ChangeCurrentByOneFromLongPressCommand arg0, boolean arg1) {
            arg0.setStep(arg1);
        }

        public void run() {
            NumberPicker.this.changeValueByOne(this.mIncrement);
            NumberPicker.this.postDelayed(((Runnable)this), NumberPicker.this.mLongPressUpdateInterval);
        }

        private void setStep(boolean arg1) {
            this.mIncrement = arg1;
        }
    }

    public interface Formatter {
        String format(int arg1);
    }

    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScrollStateChange(NumberPicker arg1, int arg2);
    }

    public interface OnValueChangeListener {
        void onValueChange(NumberPicker arg1, int arg2, int arg3);
    }

    class PressedStateHelper implements Runnable {
        public static final int BUTTON_DECREMENT = 2;
        public static final int BUTTON_INCREMENT = 1;
        private final int MODE_PRESS;
        private final int MODE_TAPPED;
        private int mManagedButton;
        private int mMode;

        PressedStateHelper(NumberPicker arg1) {
            NumberPicker.this = arg1;
            super();
            this.MODE_PRESS = 1;
            this.MODE_TAPPED = 2;
        }

        public void buttonPressDelayed(int arg3) {
            this.cancel();
            this.mMode = 1;
            this.mManagedButton = arg3;
            NumberPicker.this.postDelayed(((Runnable)this), ((long)ViewConfiguration.getTapTimeout()));
        }

        public void buttonTapped(int arg2) {
            this.cancel();
            this.mMode = 2;
            this.mManagedButton = arg2;
            NumberPicker.this.post(((Runnable)this));
        }

        public void cancel() {
            this.mMode = 0;
            this.mManagedButton = 0;
            NumberPicker.this.removeCallbacks(((Runnable)this));
            if(NumberPicker.this.mIncrementVirtualButtonPressed) {
                NumberPicker.this.mIncrementVirtualButtonPressed = false;
                NumberPicker.this.invalidate(0, NumberPicker.this.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
            }

            NumberPicker.this.mDecrementVirtualButtonPressed = false;
            if(NumberPicker.this.mDecrementVirtualButtonPressed) {
                NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.mTopSelectionDividerTop);
            }
        }

        public void run() {
            int v1_1;
            NumberPicker v0;
            boolean v1 = true;
            switch(this.mMode) {
                case 1: {
                    goto label_48;
                }
                case 2: {
                    switch(this.mManagedButton) {
                        case 1: {
                            if(!NumberPicker.this.mIncrementVirtualButtonPressed) {
                                NumberPicker.this.postDelayed(((Runnable)this), ((long)ViewConfiguration.getPressedStateDuration()));
                            }

                            v0 = NumberPicker.this;
                            v1_1 = 1 ^ NumberPicker.this.mIncrementVirtualButtonPressed;
                            goto label_38;
                        label_48:
                            switch(this.mManagedButton) {
                                case 1: {
                                    v0 = NumberPicker.this;
                                label_38:
                                    v0.mIncrementVirtualButtonPressed = v1;
                                    NumberPicker.this.invalidate(0, NumberPicker.this.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                                    return;
                                }
                                case 2: {
                                    v0 = NumberPicker.this;
                                label_19:
                                    v0.mDecrementVirtualButtonPressed = v1;
                                    NumberPicker.this.invalidate(0, 0, NumberPicker.this.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                                    return;
                                }
                                default: {
                                    return;
                                }
                            }
                        }
                        case 2: {
                            if(!NumberPicker.this.mDecrementVirtualButtonPressed) {
                                NumberPicker.this.postDelayed(((Runnable)this), ((long)ViewConfiguration.getPressedStateDuration()));
                            }

                            v0 = NumberPicker.this;
                            v1_1 = 1 ^ NumberPicker.this.mDecrementVirtualButtonPressed;
                            goto label_19;
                        }
                        default: {
                            return;
                        }
                    }
                }
                default: {
                    break;
                }
            }
        }
    }

    private static final int DEFAULT_LAYOUT_RESOURCE_ID = 0;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300;
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 8;
    private static final int SELECTOR_MIDDLE_ITEM_INDEX = 1;
    private static final int SELECTOR_WHEEL_ITEM_COUNT = 3;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final float TOP_AND_BOTTOM_FADING_EDGE_STRENGTH = 0.9f;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDERS_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDER_HEIGHT = 2;
    private Scroller mAdjustScroller;
    private int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private boolean mDecrementVirtualButtonPressed;
    private String[] mDisplayedValues;
    private Scroller mFlingScroller;
    private Formatter mFormatter;
    private boolean mIncrementVirtualButtonPressed;
    private boolean mIngonreMoveEvents;
    private int mInitialScrollOffset;
    private TextView mInputText;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private int mLastHoveredChildVirtualViewId;
    private long mLongPressUpdateInterval;
    private int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private int mMinHeight;
    private int mMinValue;
    private int mMinWidth;
    private int mMinimumFlingVelocity;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private PressedStateHelper mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private Paint mSelectionDivider;
    private int mSelectionDividerHeight;
    private int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private final SparseArray mSelectorIndexToStringCache;
    private final int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private Paint mSelectorWheelPaint;
    private int mSolidColor;
    private int mTextSize;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private boolean mWrapSelectorWheel;

    public NumberPicker(Context arg3) {
        super(arg3);
        this.mLongPressUpdateInterval = 300;
        this.mSelectorIndexToStringCache = new SparseArray();
        this.mSelectorIndices = new int[3];
        this.mInitialScrollOffset = -2147483648;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.init();
    }

    public NumberPicker(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.mLongPressUpdateInterval = 300;
        this.mSelectorIndexToStringCache = new SparseArray();
        this.mSelectorIndices = new int[3];
        this.mInitialScrollOffset = -2147483648;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.init();
    }

    public NumberPicker(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
        this.mLongPressUpdateInterval = 300;
        this.mSelectorIndexToStringCache = new SparseArray();
        this.mSelectorIndices = new int[3];
        this.mInitialScrollOffset = -2147483648;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.init();
    }

    static boolean access$100(NumberPicker arg0) {
        return arg0.mIncrementVirtualButtonPressed;
    }

    static boolean access$102(NumberPicker arg0, boolean arg1) {
        arg0.mIncrementVirtualButtonPressed = arg1;
        return arg1;
    }

    static int access$200(NumberPicker arg0) {
        return arg0.mBottomSelectionDividerBottom;
    }

    static boolean access$300(NumberPicker arg0) {
        return arg0.mDecrementVirtualButtonPressed;
    }

    static boolean access$302(NumberPicker arg0, boolean arg1) {
        arg0.mDecrementVirtualButtonPressed = arg1;
        return arg1;
    }

    static int access$400(NumberPicker arg0) {
        return arg0.mTopSelectionDividerTop;
    }

    static void access$500(NumberPicker arg0, boolean arg1) {
        arg0.changeValueByOne(arg1);
    }

    static long access$600(NumberPicker arg2) {
        return arg2.mLongPressUpdateInterval;
    }

    private void changeValueByOne(boolean arg14) {
        this.mInputText.setVisibility(4);
        if(!this.moveToFinalScrollerPosition(this.mFlingScroller)) {
            this.moveToFinalScrollerPosition(this.mAdjustScroller);
        }

        this.mPreviousScrollerY = 0;
        if(arg14) {
            this.mFlingScroller.startScroll(0, 0, 0, -this.mSelectorElementHeight, 300);
        }
        else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight, 300);
        }

        this.invalidate();
    }

    public void computeScroll() {
        Scroller v0 = this.mFlingScroller;
        if(v0.isFinished()) {
            v0 = this.mAdjustScroller;
            if(v0.isFinished()) {
                return;
            }
        }

        v0.computeScrollOffset();
        int v1 = v0.getCurrY();
        if(this.mPreviousScrollerY == 0) {
            this.mPreviousScrollerY = v0.getStartY();
        }

        this.scrollBy(0, v1 - this.mPreviousScrollerY);
        this.mPreviousScrollerY = v1;
        if(v0.isFinished()) {
            this.onScrollerFinished(v0);
        }
        else {
            this.invalidate();
        }
    }

    protected int computeVerticalScrollExtent() {
        return this.getHeight();
    }

    protected int computeVerticalScrollOffset() {
        return this.mCurrentScrollOffset;
    }

    protected int computeVerticalScrollRange() {
        return (this.mMaxValue - this.mMinValue + 1) * this.mSelectorElementHeight;
    }

    private void decrementSelectorIndices(int[] arg4) {
        System.arraycopy(arg4, 0, arg4, 1, arg4.length - 1);
        int v0 = arg4[1] - 1;
        if((this.mWrapSelectorWheel) && v0 < this.mMinValue) {
            v0 = this.mMaxValue;
        }

        arg4[0] = v0;
        this.ensureCachedScrollSelectorValue(v0);
    }

    public boolean dispatchKeyEvent(KeyEvent arg6) {
        int v0 = arg6.getKeyCode();
        if(v0 == 23) {
            goto label_40;
        }
        else if(v0 != 66) {
            switch(v0) {
                case 19: 
                case 20: {
                    switch(arg6.getAction()) {
                        case 0: {
                            goto label_16;
                        }
                        case 1: {
                            goto label_11;
                        }
                    }

                    goto label_41;
                label_11:
                    if(this.mLastHandledDownDpadKeyCode == v0) {
                        this.mLastHandledDownDpadKeyCode = -1;
                        return 1;
                    label_16:
                        int v3 = 20;
                        if((this.mWrapSelectorWheel) || v0 == v3) {
                            if(this.getValue() < this.getMaxValue()) {
                                goto label_28;
                            }

                            goto label_41;
                        }
                        else if(this.getValue() > this.getMinValue()) {
                        }
                        else {
                            goto label_41;
                        }

                    label_28:
                        this.requestFocus();
                        this.mLastHandledDownDpadKeyCode = v0;
                        this.removeAllCallbacks();
                        if(this.mFlingScroller.isFinished()) {
                            boolean v6 = v0 == v3 ? true : false;
                            this.changeValueByOne(v6);
                        }

                        return 1;
                    label_40:
                        this.removeAllCallbacks();
                    }

                    goto label_41;
                }
                default: {
                    goto label_41;
                }
            }
        }
        else {
            goto label_40;
        }

    label_41:
        return super.dispatchKeyEvent(arg6);
    }

    public boolean dispatchTouchEvent(MotionEvent arg3) {
        int v0 = arg3.getActionMasked();
        if(v0 == 1 || v0 == 3) {
            this.removeAllCallbacks();
        }
        else {
        }

        return super.dispatchTouchEvent(arg3);
    }

    public boolean dispatchTrackballEvent(MotionEvent arg3) {
        int v0 = arg3.getActionMasked();
        if(v0 == 1 || v0 == 3) {
            this.removeAllCallbacks();
        }
        else {
        }

        return super.dispatchTrackballEvent(arg3);
    }

    private void ensureCachedScrollSelectorValue(int arg4) {
        String v1;
        SparseArray v0 = this.mSelectorIndexToStringCache;
        if(v0.get(arg4) != null) {
            return;
        }

        if(arg4 < this.mMinValue || arg4 > this.mMaxValue) {
            v1 = "";
        }
        else if(this.mDisplayedValues != null) {
            v1 = this.mDisplayedValues[arg4 - this.mMinValue];
        }
        else {
            v1 = this.formatNumber(arg4);
        }

        v0.put(arg4, v1);
    }

    private boolean ensureScrollWheelAdjusted() {
        int v0 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if(v0 != 0) {
            this.mPreviousScrollerY = 0;
            if(Math.abs(v0) > this.mSelectorElementHeight / 2) {
                int v1 = v0 > 0 ? -this.mSelectorElementHeight : this.mSelectorElementHeight;
                v0 += v1;
            }

            this.mAdjustScroller.startScroll(0, 0, 0, v0, 800);
            this.invalidate();
            return 1;
        }

        return 0;
    }

    public void finishScroll() {
        if(!this.mFlingScroller.isFinished() || !this.mAdjustScroller.isFinished()) {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            this.invalidate();
        }
    }

    private void fling(int arg11) {
        int v3;
        int v2;
        Scroller v1;
        this.mPreviousScrollerY = 0;
        if(arg11 > 0) {
            v1 = this.mFlingScroller;
            v2 = 0;
            v3 = 0;
        }
        else {
            v1 = this.mFlingScroller;
            v2 = 0;
            v3 = 2147483647;
        }

        v1.fling(v2, v3, 0, arg11, 0, 0, 0, 2147483647);
        this.invalidate();
    }

    private String formatNumber(int arg2) {
        String v2 = this.mFormatter != null ? this.mFormatter.format(arg2) : NumberPicker.formatNumberWithLocale(arg2);
        return v2;
    }

    private static String formatNumberWithLocale(int arg4) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(arg4));
    }

    protected float getBottomFadingEdgeStrength() {
        return 0.9f;
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    private int getSelectedPos(String arg3) {
        if(this.mDisplayedValues == null) {
            try {
                return Integer.parseInt(arg3);
            }
            catch(NumberFormatException ) {
                goto label_21;
            }
        }

        int v0;
        for(v0 = 0; v0 < this.mDisplayedValues.length; ++v0) {
            arg3 = arg3.toLowerCase();
            if(this.mDisplayedValues[v0].toLowerCase().startsWith(arg3)) {
                return this.mMinValue + v0;
            }
        }

        try {
            return Integer.parseInt(arg3);
        }
        catch(NumberFormatException ) {
        label_21:
            return this.mMinValue;
        }
    }

    public int getSolidColor() {
        return this.mSolidColor;
    }

    protected float getTopFadingEdgeStrength() {
        return 0.9f;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    private int getWrappedSelectorIndex(int arg4) {
        if(arg4 > this.mMaxValue) {
            return this.mMinValue + (arg4 - this.mMaxValue) % (this.mMaxValue - this.mMinValue) - 1;
        }

        if(arg4 < this.mMinValue) {
            return this.mMaxValue - (this.mMinValue - arg4) % (this.mMaxValue - this.mMinValue) + 1;
        }

        return arg4;
    }

    private void incrementSelectorIndices(int[] arg4) {
        System.arraycopy(arg4, 1, arg4, 0, arg4.length - 1);
        int v0 = arg4[arg4.length - 2] + 1;
        if((this.mWrapSelectorWheel) && v0 > this.mMaxValue) {
            v0 = this.mMinValue;
        }

        arg4[arg4.length - 1] = v0;
        this.ensureCachedScrollSelectorValue(v0);
    }

    private void init() {
        this.mSolidColor = 0;
        this.mSelectionDivider = new Paint();
        this.mSelectionDivider.setColor(Theme.getColor("dialogButton"));
        this.mSelectionDividerHeight = ((int)TypedValue.applyDimension(1, 2f, this.getResources().getDisplayMetrics()));
        this.mSelectionDividersDistance = ((int)TypedValue.applyDimension(1, 48f, this.getResources().getDisplayMetrics()));
        int v1 = -1;
        this.mMinHeight = v1;
        this.mMaxHeight = ((int)TypedValue.applyDimension(1, 180f, this.getResources().getDisplayMetrics()));
        if(this.mMinHeight != v1 && this.mMaxHeight != v1) {
            if(this.mMinHeight <= this.mMaxHeight) {
            }
            else {
                throw new IllegalArgumentException("minHeight > maxHeight");
            }
        }

        this.mMinWidth = ((int)TypedValue.applyDimension(1, 64f, this.getResources().getDisplayMetrics()));
        this.mMaxWidth = v1;
        if(this.mMinWidth != v1 && this.mMaxWidth != v1) {
            if(this.mMinWidth <= this.mMaxWidth) {
            }
            else {
                throw new IllegalArgumentException("minWidth > maxWidth");
            }
        }

        boolean v3 = this.mMaxWidth == v1 ? true : false;
        this.mComputeMaxWidth = v3;
        this.mPressedStateHelper = new PressedStateHelper(this);
        this.setWillNotDraw(false);
        this.mInputText = new TextView(this.getContext());
        this.mInputText.setGravity(17);
        this.mInputText.setSingleLine(true);
        this.mInputText.setTextColor(Theme.getColor("dialogTextBlack"));
        this.mInputText.setBackgroundResource(0);
        this.mInputText.setTextSize(1, 18f);
        this.addView(this.mInputText, new LinearLayout$LayoutParams(v1, -2));
        ViewConfiguration v0 = ViewConfiguration.get(this.getContext());
        this.mTouchSlop = v0.getScaledTouchSlop();
        this.mMinimumFlingVelocity = v0.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = v0.getScaledMaximumFlingVelocity() / 8;
        this.mTextSize = ((int)this.mInputText.getTextSize());
        Paint v0_1 = new Paint();
        v0_1.setAntiAlias(true);
        v0_1.setTextAlign(Paint$Align.CENTER);
        v0_1.setTextSize(((float)this.mTextSize));
        v0_1.setTypeface(this.mInputText.getTypeface());
        v0_1.setColor(this.mInputText.getTextColors().getColorForState(NumberPicker.ENABLED_STATE_SET, v1));
        this.mSelectorWheelPaint = v0_1;
        this.mFlingScroller = new Scroller(this.getContext(), null, true);
        this.mAdjustScroller = new Scroller(this.getContext(), new DecelerateInterpolator(2.5f));
        this.updateInputTextView();
    }

    private void initializeFadingEdges() {
        this.setVerticalFadingEdgeEnabled(true);
        this.setFadingEdgeLength((this.getBottom() - this.getTop() - this.mTextSize) / 2);
    }

    private void initializeSelectorWheel() {
        this.initializeSelectorWheelIndices();
        this.mSelectorTextGapHeight = ((int)((((float)(this.getBottom() - this.getTop() - this.mSelectorIndices.length * this.mTextSize))) / (((float)this.mSelectorIndices.length)) + 0.5f));
        this.mSelectorElementHeight = this.mTextSize + this.mSelectorTextGapHeight;
        this.mInitialScrollOffset = this.mInputText.getBaseline() + this.mInputText.getTop() - this.mSelectorElementHeight;
        this.mCurrentScrollOffset = this.mInitialScrollOffset;
        this.updateInputTextView();
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        int[] v0 = this.mSelectorIndices;
        int v1 = this.getValue();
        int v2;
        for(v2 = 0; v2 < this.mSelectorIndices.length; ++v2) {
            int v3 = v2 - 1 + v1;
            if(this.mWrapSelectorWheel) {
                v3 = this.getWrappedSelectorIndex(v3);
            }

            v0[v2] = v3;
            this.ensureCachedScrollSelectorValue(v0[v2]);
        }
    }

    private int makeMeasureSpec(int arg5, int arg6) {
        if(arg6 == -1) {
            return arg5;
        }

        int v0 = View$MeasureSpec.getSize(arg5);
        int v1 = View$MeasureSpec.getMode(arg5);
        int v3 = 1073741824;
        if(v1 != -2147483648) {
            if(v1 != 0) {
                if(v1 == v3) {
                    return arg5;
                }

                StringBuilder v6 = new StringBuilder();
                v6.append("Unknown measure mode: ");
                v6.append(v1);
                throw new IllegalArgumentException(v6.toString());
            }

            return View$MeasureSpec.makeMeasureSpec(arg6, v3);
        }

        return View$MeasureSpec.makeMeasureSpec(Math.min(v0, arg6), v3);
    }

    private boolean moveToFinalScrollerPosition(Scroller arg6) {
        arg6.forceFinished(true);
        int v1 = arg6.getFinalY() - arg6.getCurrY();
        int v2 = this.mInitialScrollOffset - (this.mCurrentScrollOffset + v1) % this.mSelectorElementHeight;
        if(v2 != 0) {
            if(Math.abs(v2) > this.mSelectorElementHeight / 2) {
                if(v2 > 0) {
                    v2 -= this.mSelectorElementHeight;
                }
                else {
                    v2 += this.mSelectorElementHeight;
                }
            }

            this.scrollBy(0, v1 + v2);
            return 1;
        }

        return 0;
    }

    private void notifyChange(int arg2, int arg3) {
        if(this.mOnValueChangeListener != null) {
            this.mOnValueChangeListener.onValueChange(this, arg2, this.mValue);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeAllCallbacks();
    }

    protected void onDraw(Canvas arg9) {
        float v0 = ((float)((this.getRight() - this.getLeft()) / 2));
        float v1 = ((float)this.mCurrentScrollOffset);
        int[] v2 = this.mSelectorIndices;
        int v3;
        for(v3 = 0; v3 < v2.length; ++v3) {
            Object v4 = this.mSelectorIndexToStringCache.get(v2[v3]);
            if(v3 != 1 || this.mInputText.getVisibility() != 0) {
                arg9.drawText(((String)v4), v0, v1, this.mSelectorWheelPaint);
            }

            v1 += ((float)this.mSelectorElementHeight);
        }

        arg9.drawRect(0f, ((float)this.mTopSelectionDividerTop), ((float)this.getRight()), ((float)(this.mSelectionDividerHeight + this.mTopSelectionDividerTop)), this.mSelectionDivider);
        arg9.drawRect(0f, ((float)(this.mBottomSelectionDividerBottom - this.mSelectionDividerHeight)), ((float)this.getRight()), ((float)this.mBottomSelectionDividerBottom), this.mSelectionDivider);
    }

    public boolean onInterceptTouchEvent(MotionEvent arg5) {
        // Method was not decompiled
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        arg3 = this.getMeasuredWidth();
        arg4 = this.getMeasuredHeight();
        arg5 = this.mInputText.getMeasuredWidth();
        arg6 = this.mInputText.getMeasuredHeight();
        arg3 = (arg3 - arg5) / 2;
        arg4 = (arg4 - arg6) / 2;
        this.mInputText.layout(arg3, arg4, arg5 + arg3, arg6 + arg4);
        if(arg2) {
            this.initializeSelectorWheel();
            this.initializeFadingEdges();
            this.mTopSelectionDividerTop = (this.getHeight() - this.mSelectionDividersDistance) / 2 - this.mSelectionDividerHeight;
            this.mBottomSelectionDividerBottom = this.mTopSelectionDividerTop + this.mSelectionDividerHeight * 2 + this.mSelectionDividersDistance;
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(this.makeMeasureSpec(arg3, this.mMaxWidth), this.makeMeasureSpec(arg4, this.mMaxHeight));
        this.setMeasuredDimension(this.resolveSizeAndStateRespectingMinSize(this.mMinWidth, this.getMeasuredWidth(), arg3), this.resolveSizeAndStateRespectingMinSize(this.mMinHeight, this.getMeasuredHeight(), arg4));
    }

    private void onScrollStateChange(int arg2) {
        if(this.mScrollState == arg2) {
            return;
        }

        this.mScrollState = arg2;
        if(this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChange(this, arg2);
        }
    }

    private void onScrollerFinished(Scroller arg2) {
        if(arg2 == this.mFlingScroller) {
            if(!this.ensureScrollWheelAdjusted()) {
                this.updateInputTextView();
            }

            this.onScrollStateChange(0);
        }
        else {
            if(this.mScrollState == 1) {
                return;
            }

            this.updateInputTextView();
        }
    }

    public boolean onTouchEvent(MotionEvent arg11) {
        if(!this.isEnabled()) {
            return 0;
        }

        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }

        this.mVelocityTracker.addMovement(arg11);
        switch(arg11.getActionMasked()) {
            case 1: {
                this.removeChangeCurrentByOneFromLongPress();
                this.mPressedStateHelper.cancel();
                VelocityTracker v0 = this.mVelocityTracker;
                v0.computeCurrentVelocity(1000, ((float)this.mMaximumFlingVelocity));
                int v0_1 = ((int)v0.getYVelocity());
                int v5 = 2;
                if(Math.abs(v0_1) > this.mMinimumFlingVelocity) {
                    this.fling(v0_1);
                    this.onScrollStateChange(v5);
                }
                else {
                    v0_1 = ((int)arg11.getY());
                    int v3 = ((int)Math.abs((((float)v0_1)) - this.mLastDownEventY));
                    long v6 = arg11.getEventTime() - this.mLastDownEventTime;
                    if(v3 > this.mTouchSlop || v6 >= (((long)ViewConfiguration.getTapTimeout()))) {
                        this.ensureScrollWheelAdjusted();
                    }
                    else {
                        v0_1 = v0_1 / this.mSelectorElementHeight - 1;
                        if(v0_1 > 0) {
                            this.changeValueByOne(true);
                            this.mPressedStateHelper.buttonTapped(1);
                        }
                        else if(v0_1 < 0) {
                            this.changeValueByOne(false);
                            this.mPressedStateHelper.buttonTapped(v5);
                        }
                    }

                    this.onScrollStateChange(0);
                }

                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
                break;
            }
            case 2: {
                if(this.mIngonreMoveEvents) {
                    return 1;
                }

                float v11 = arg11.getY();
                if(this.mScrollState == 1) {
                    this.scrollBy(0, ((int)(v11 - this.mLastDownOrMoveEventY)));
                    this.invalidate();
                }
                else if((((int)Math.abs(v11 - this.mLastDownEventY))) > this.mTouchSlop) {
                    this.removeAllCallbacks();
                    this.onScrollStateChange(1);
                }

                this.mLastDownOrMoveEventY = v11;
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }

    private void postChangeCurrentByOneFromLongPress(boolean arg2, long arg3) {
        if(this.mChangeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand(this);
        }
        else {
            this.removeCallbacks(this.mChangeCurrentByOneFromLongPressCommand);
        }

        ChangeCurrentByOneFromLongPressCommand.access$000(this.mChangeCurrentByOneFromLongPressCommand, arg2);
        this.postDelayed(this.mChangeCurrentByOneFromLongPressCommand, arg3);
    }

    private void removeAllCallbacks() {
        if(this.mChangeCurrentByOneFromLongPressCommand != null) {
            this.removeCallbacks(this.mChangeCurrentByOneFromLongPressCommand);
        }

        this.mPressedStateHelper.cancel();
    }

    private void removeChangeCurrentByOneFromLongPress() {
        if(this.mChangeCurrentByOneFromLongPressCommand != null) {
            this.removeCallbacks(this.mChangeCurrentByOneFromLongPressCommand);
        }
    }

    public static int resolveSizeAndState(int arg2, int arg3, int arg4) {
        int v0 = View$MeasureSpec.getMode(arg3);
        arg3 = View$MeasureSpec.getSize(arg3);
        if(v0 != -2147483648) {
            if(v0 != 0) {
                if(v0 != 1073741824) {
                }
                else {
                    arg2 = arg3;
                }
            }
        }
        else if(arg3 < arg2) {
            arg2 = 16777216 | arg3;
        }

        return arg2 | -16777216 & arg4;
    }

    private int resolveSizeAndStateRespectingMinSize(int arg2, int arg3, int arg4) {
        if(arg2 != -1) {
            return NumberPicker.resolveSizeAndState(Math.max(arg2, arg3), arg4, 0);
        }

        return arg3;
    }

    public void scrollBy(int arg4, int arg5) {
        int[] v4 = this.mSelectorIndices;
        if(!this.mWrapSelectorWheel && arg5 > 0 && v4[1] <= this.mMinValue || !this.mWrapSelectorWheel && arg5 < 0 && v4[1] >= this.mMaxValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }

        this.mCurrentScrollOffset += arg5;
        while(this.mCurrentScrollOffset - this.mInitialScrollOffset > this.mSelectorTextGapHeight) {
            this.mCurrentScrollOffset -= this.mSelectorElementHeight;
            this.decrementSelectorIndices(v4);
            this.setValueInternal(v4[1], true);
            if(this.mWrapSelectorWheel) {
                continue;
            }

            if(v4[1] > this.mMinValue) {
                continue;
            }

            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        }

        while(this.mCurrentScrollOffset - this.mInitialScrollOffset < -this.mSelectorTextGapHeight) {
            this.mCurrentScrollOffset += this.mSelectorElementHeight;
            this.incrementSelectorIndices(v4);
            this.setValueInternal(v4[1], true);
            if(this.mWrapSelectorWheel) {
                continue;
            }

            if(v4[1] < this.mMaxValue) {
                continue;
            }

            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        }
    }

    public void setDisplayedValues(String[] arg2) {
        if(this.mDisplayedValues == arg2) {
            return;
        }

        this.mDisplayedValues = arg2;
        this.updateInputTextView();
        this.initializeSelectorWheelIndices();
        this.tryComputeMaxWidth();
    }

    public void setEnabled(boolean arg2) {
        super.setEnabled(arg2);
        this.mInputText.setEnabled(arg2);
    }

    public void setFormatter(Formatter arg2) {
        if(arg2 == this.mFormatter) {
            return;
        }

        this.mFormatter = arg2;
        this.initializeSelectorWheelIndices();
        this.updateInputTextView();
    }

    public void setMaxValue(int arg2) {
        if(this.mMaxValue == arg2) {
            return;
        }

        if(arg2 >= 0) {
            this.mMaxValue = arg2;
            if(this.mMaxValue < this.mValue) {
                this.mValue = this.mMaxValue;
            }

            boolean v2 = this.mMaxValue - this.mMinValue > this.mSelectorIndices.length ? true : false;
            this.setWrapSelectorWheel(v2);
            this.initializeSelectorWheelIndices();
            this.updateInputTextView();
            this.tryComputeMaxWidth();
            this.invalidate();
            return;
        }

        throw new IllegalArgumentException("maxValue must be >= 0");
    }

    public void setMinValue(int arg2) {
        if(this.mMinValue == arg2) {
            return;
        }

        if(arg2 >= 0) {
            this.mMinValue = arg2;
            if(this.mMinValue > this.mValue) {
                this.mValue = this.mMinValue;
            }

            boolean v2 = this.mMaxValue - this.mMinValue > this.mSelectorIndices.length ? true : false;
            this.setWrapSelectorWheel(v2);
            this.initializeSelectorWheelIndices();
            this.updateInputTextView();
            this.tryComputeMaxWidth();
            this.invalidate();
            return;
        }

        throw new IllegalArgumentException("minValue must be >= 0");
    }

    public void setOnLongPressUpdateInterval(long arg1) {
        this.mLongPressUpdateInterval = arg1;
    }

    public void setOnScrollListener(OnScrollListener arg1) {
        this.mOnScrollListener = arg1;
    }

    public void setOnValueChangedListener(OnValueChangeListener arg1) {
        this.mOnValueChangeListener = arg1;
    }

    public void setSelectorColor(int arg2) {
        this.mSelectionDivider.setColor(arg2);
    }

    public void setTextColor(int arg2) {
        this.mInputText.setTextColor(arg2);
        this.mSelectorWheelPaint.setColor(arg2);
    }

    public void setValue(int arg2) {
        this.setValueInternal(arg2, false);
    }

    private void setValueInternal(int arg2, boolean arg3) {
        if(this.mValue == arg2) {
            return;
        }

        arg2 = this.mWrapSelectorWheel ? this.getWrappedSelectorIndex(arg2) : Math.min(Math.max(arg2, this.mMinValue), this.mMaxValue);
        int v0 = this.mValue;
        this.mValue = arg2;
        this.updateInputTextView();
        if(arg3) {
            this.notifyChange(v0, arg2);
        }

        this.initializeSelectorWheelIndices();
        this.invalidate();
    }

    public void setWrapSelectorWheel(boolean arg3) {
        int v0 = this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length ? 1 : 0;
        if((!arg3 || v0 != 0) && arg3 != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = arg3;
        }
    }

    private void tryComputeMaxWidth() {
        int v0;
        if(!this.mComputeMaxWidth) {
            return;
        }

        int v1 = 0;
        if(this.mDisplayedValues == null) {
            v0 = 0;
            float v2 = 0f;
            while(v0 <= 9) {
                float v3 = this.mSelectorWheelPaint.measureText(NumberPicker.formatNumberWithLocale(v0));
                if(v3 > v2) {
                    v2 = v3;
                }

                ++v0;
            }

            for(v0 = this.mMaxValue; v0 > 0; v0 /= 10) {
                ++v1;
            }

            v0 = ((int)((((float)v1)) * v2));
        }
        else {
            String[] v0_1 = this.mDisplayedValues;
            int v2_1 = v0_1.length;
            int v3_1 = 0;
            while(v1 < v2_1) {
                float v4 = this.mSelectorWheelPaint.measureText(v0_1[v1]);
                if(v4 > (((float)v3_1))) {
                    v3_1 = ((int)v4);
                }

                ++v1;
            }

            v0 = v3_1;
        }

        v0 += this.mInputText.getPaddingLeft() + this.mInputText.getPaddingRight();
        if(this.mMaxWidth != v0) {
            if(v0 <= this.mMinWidth) {
                v0 = this.mMinWidth;
            }

            this.mMaxWidth = v0;
            this.invalidate();
        }
    }

    private boolean updateInputTextView() {
        String v0 = this.mDisplayedValues == null ? this.formatNumber(this.mValue) : this.mDisplayedValues[this.mValue - this.mMinValue];
        if(!TextUtils.isEmpty(((CharSequence)v0)) && !v0.equals(this.mInputText.getText().toString())) {
            this.mInputText.setText(((CharSequence)v0));
            return 1;
        }

        return 0;
    }
}

