package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
    final class android.support.v4.view.ViewPager$1 implements Comparator {
        android.support.v4.view.ViewPager$1() {
            super();
        }

        public int a(b arg1, b arg2) {
            return arg1.b - arg2.b;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((b)arg1), ((b)arg2));
        }
    }

    final class android.support.v4.view.ViewPager$2 implements Interpolator {
        android.support.v4.view.ViewPager$2() {
            super();
        }

        public float getInterpolation(float arg3) {
            --arg3;
            return arg3 * arg3 * arg3 * arg3 * arg3 + 1f;
        }
    }

    class android.support.v4.view.ViewPager$3 implements Runnable {
        android.support.v4.view.ViewPager$3(ViewPager arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.setScrollState(0);
            this.a.populate();
        }
    }

    public class SavedState extends AbsSavedState {
        final class android.support.v4.view.ViewPager$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v4.view.ViewPager$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState a(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.a(arg1, arg2);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        int a;
        Parcelable b;
        ClassLoader c;

        static {
            SavedState.CREATOR = new android.support.v4.view.ViewPager$SavedState$1();
        }

        public SavedState(Parcelable arg1) {
            super(arg1);
        }

        SavedState(Parcel arg2, ClassLoader arg3) {
            super(arg2, arg3);
            if(arg3 == null) {
                arg3 = this.getClass().getClassLoader();
            }

            this.a = arg2.readInt();
            this.b = arg2.readParcelable(arg3);
            this.c = arg3;
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        public void writeToParcel(Parcel arg2, int arg3) {
            super.writeToParcel(arg2, arg3);
            arg2.writeInt(this.a);
            arg2.writeParcelable(this.b, arg3);
        }
    }

    @Inherited @Retention(value=RetentionPolicy.RUNTIME) @Target(value={ElementType.TYPE}) @public interface a {
    }

    class b {
        Object a;
        int b;
        boolean c;
        float d;
        float e;

        b() {
            super();
        }
    }

    public class c extends ViewGroup$LayoutParams {
        public boolean a;
        public int b;
        float c;
        boolean d;
        int e;
        int f;

        public c() {
            super(-1, -1);
            this.c = 0f;
        }

        public c(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            this.c = 0f;
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, ViewPager.LAYOUT_ATTRS);
            this.b = v2.getInteger(0, 48);
            v2.recycle();
        }
    }

    class d extends android.support.v4.view.a {
        d(ViewPager arg1) {
            this.a = arg1;
            super();
        }

        private boolean a() {
            boolean v1 = true;
            if(this.a.mAdapter == null || this.a.mAdapter.getCount() <= 1) {
                v1 = false;
            }
            else {
            }

            return v1;
        }

        public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            super.onInitializeAccessibilityEvent(arg2, arg3);
            arg3.setClassName(ViewPager.class.getName());
            arg3.setScrollable(this.a());
            if(arg3.getEventType() == 4096 && this.a.mAdapter != null) {
                arg3.setItemCount(this.a.mAdapter.getCount());
                arg3.setFromIndex(this.a.mCurItem);
                arg3.setToIndex(this.a.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View arg2, android.support.v4.view.a.c arg3) {
            super.onInitializeAccessibilityNodeInfo(arg2, arg3);
            arg3.b(ViewPager.class.getName());
            arg3.i(this.a());
            if(this.a.canScrollHorizontally(1)) {
                arg3.a(4096);
            }

            if(this.a.canScrollHorizontally(-1)) {
                arg3.a(8192);
            }
        }

        public boolean performAccessibilityAction(View arg2, int arg3, Bundle arg4) {
            ViewPager v2;
            if(super.performAccessibilityAction(arg2, arg3, arg4)) {
                return 1;
            }

            if(arg3 != 4096) {
                if(arg3 != 8192) {
                    return 0;
                }
                else if(this.a.canScrollHorizontally(-1)) {
                    v2 = this.a;
                    arg3 = this.a.mCurItem - 1;
                }
                else {
                    return 0;
                }
            }
            else if(this.a.canScrollHorizontally(1)) {
                v2 = this.a;
                arg3 = this.a.mCurItem + 1;
            }
            else {
                return 0;
            }

            v2.setCurrentItem(arg3);
            return 1;
        }
    }

    public interface e {
        void a(ViewPager arg1, q arg2, q arg3);
    }

    public interface f {
        void onPageScrollStateChanged(int arg1);

        void onPageScrolled(int arg1, float arg2, int arg3);

        void onPageSelected(int arg1);
    }

    public interface g {
        void a(View arg1, float arg2);
    }

    class h extends DataSetObserver {
        h(ViewPager arg1) {
            this.a = arg1;
            super();
        }

        public void onChanged() {
            this.a.dataSetChanged();
        }

        public void onInvalidated() {
            this.a.dataSetChanged();
        }
    }

    class i implements Comparator {
        i() {
            super();
        }

        public int a(View arg3, View arg4) {
            ViewGroup$LayoutParams v3 = arg3.getLayoutParams();
            ViewGroup$LayoutParams v4 = arg4.getLayoutParams();
            if(((c)v3).a != ((c)v4).a) {
                int v3_1 = ((c)v3).a ? 1 : -1;
                return v3_1;
            }

            return ((c)v3).e - ((c)v4).e;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((View)arg1), ((View)arg2));
        }
    }

    private static final int CLOSE_ENOUGH = 2;
    private static final Comparator COMPARATOR = null;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    static final int[] LAYOUT_ATTRS = null;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private static final boolean USE_CACHE = false;
    private int mActivePointerId;
    q mAdapter;
    private List mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private f mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffect mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private h mObserver;
    private int mOffscreenPageLimit;
    private f mOnPageChangeListener;
    private List mOnPageChangeListeners;
    private int mPageMargin;
    private g mPageTransformer;
    private int mPageTransformerLayerType;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffect mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final b mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private static final Interpolator sInterpolator;
    private static final i sPositionComparator;

    static {
        ViewPager.LAYOUT_ATTRS = new int[]{16842931};
        ViewPager.COMPARATOR = new android.support.v4.view.ViewPager$1();
        ViewPager.sInterpolator = new android.support.v4.view.ViewPager$2();
        ViewPager.sPositionComparator = new i();
    }

    public ViewPager(Context arg2) {
        super(arg2);
        this.mItems = new ArrayList();
        this.mTempItem = new b();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -340282346638528860000000000000000000000f;
        this.mLastOffset = 340282346638528860000000000000000000000f;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new android.support.v4.view.ViewPager$3(this);
        this.mScrollState = 0;
        this.initViewPager();
    }

    public ViewPager(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.mItems = new ArrayList();
        this.mTempItem = new b();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -340282346638528860000000000000000000000f;
        this.mLastOffset = 340282346638528860000000000000000000000f;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new android.support.v4.view.ViewPager$3(this);
        this.mScrollState = 0;
        this.initViewPager();
    }

    public void addFocusables(ArrayList arg7, int arg8, int arg9) {
        int v0 = arg7.size();
        int v1 = this.getDescendantFocusability();
        if(v1 != 393216) {
            int v2;
            for(v2 = 0; v2 < this.getChildCount(); ++v2) {
                View v3 = this.getChildAt(v2);
                if(v3.getVisibility() == 0) {
                    b v4 = this.infoForChild(v3);
                    if(v4 != null && v4.b == this.mCurItem) {
                        v3.addFocusables(arg7, arg8, arg9);
                    }
                }
            }
        }

        if(v1 != 262144 || v0 == arg7.size()) {
            if(!this.isFocusable()) {
                return;
            }
            else {
                if((arg9 & 1) == 1 && (this.isInTouchMode()) && !this.isFocusableInTouchMode()) {
                    return;
                }

                if(arg7 == null) {
                    return;
                }

                arg7.add(this);
            }
        }
    }

    b addNewItem(int arg3, int arg4) {
        b v0 = new b();
        v0.b = arg3;
        v0.a = this.mAdapter.instantiateItem(((ViewGroup)this), arg3);
        v0.d = this.mAdapter.getPageWidth(arg3);
        if(arg4 < 0 || arg4 >= this.mItems.size()) {
            this.mItems.add(v0);
        }
        else {
            this.mItems.add(arg4, v0);
        }

        return v0;
    }

    public void addOnAdapterChangeListener(e arg2) {
        if(this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }

        this.mAdapterChangeListeners.add(arg2);
    }

    public void addOnPageChangeListener(f arg2) {
        if(this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }

        this.mOnPageChangeListeners.add(arg2);
    }

    public void addTouchables(ArrayList arg5) {
        int v0;
        for(v0 = 0; v0 < this.getChildCount(); ++v0) {
            View v1 = this.getChildAt(v0);
            if(v1.getVisibility() == 0) {
                b v2 = this.infoForChild(v1);
                if(v2 != null && v2.b == this.mCurItem) {
                    v1.addTouchables(arg5);
                }
            }
        }
    }

    public void addView(View arg4, int arg5, ViewGroup$LayoutParams arg6) {
        if(!this.checkLayoutParams(arg6)) {
            arg6 = this.generateLayoutParams(arg6);
        }

        ViewGroup$LayoutParams v0 = arg6;
        ((c)v0).a |= ViewPager.isDecorView(arg4);
        if(this.mInLayout) {
            if(v0 != null) {
                if(!((c)v0).a) {
                }
                else {
                    throw new IllegalStateException("Cannot add pager decor view during layout");
                }
            }

            ((c)v0).d = true;
            this.addViewInLayout(arg4, arg5, arg6);
        }
        else {
            super.addView(arg4, arg5, arg6);
        }
    }

    public boolean arrowScroll(int arg8) {
        boolean v0_2;
        int v2_1;
        int v1;
        int v4_1;
        View v0 = this.findFocus();
        boolean v2 = false;
        View v3 = null;
        if((((ViewPager)v0)) == this) {
        }
        else {
            if(v0 != null) {
                ViewParent v4 = v0.getParent();
                while(true) {
                    if(!(v4 instanceof ViewGroup)) {
                        break;
                    }
                    else if((((ViewPager)v4)) == this) {
                        v4_1 = 1;
                    }
                    else {
                        v4 = v4.getParent();
                        continue;
                    }

                    goto label_16;
                }

                v4_1 = 0;
            label_16:
                if(v4_1 != 0) {
                    goto label_42;
                }

                StringBuilder v4_2 = new StringBuilder();
                v4_2.append(v0.getClass().getSimpleName());
                ViewParent v0_1;
                for(v0_1 = v0.getParent(); (v0_1 instanceof ViewGroup); v0_1 = v0_1.getParent()) {
                    v4_2.append(" => ");
                    v4_2.append(v0_1.getClass().getSimpleName());
                }

                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + v4_2.toString());
                goto label_43;
            }

        label_42:
            v3 = v0;
        }

    label_43:
        v0 = FocusFinder.getInstance().findNextFocus(((ViewGroup)this), v3, arg8);
        v4_1 = 66;
        int v5_1 = 17;
        if(v0 == null || v0 == v3) {
            if(arg8 != v5_1) {
                if(arg8 == 1) {
                }
                else {
                    if(arg8 != v4_1 && arg8 != 2) {
                        goto label_83;
                    }

                    v2 = this.pageRight();
                    goto label_83;
                }
            }

            v2 = this.pageLeft();
        }
        else {
            if(arg8 == v5_1) {
                v1 = this.getChildRectInPagerCoordinates(this.mTempRect, v0).left;
                v2_1 = this.getChildRectInPagerCoordinates(this.mTempRect, v3).left;
                if(v3 == null) {
                    goto label_60;
                }
                else if(v1 >= v2_1) {
                    v0_2 = this.pageLeft();
                }
                else {
                    goto label_60;
                }
            }
            else if(arg8 == v4_1) {
                v1 = this.getChildRectInPagerCoordinates(this.mTempRect, v0).left;
                v2_1 = this.getChildRectInPagerCoordinates(this.mTempRect, v3).left;
                if(v3 == null || v1 > v2_1) {
                label_60:
                    v0_2 = v0.requestFocus();
                }
                else {
                    v0_2 = this.pageRight();
                }
            }
            else {
                goto label_83;
            }

            v2 = v0_2;
        }

    label_83:
        if(v2) {
            this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(arg8));
        }

        return v2;
    }

    public boolean beginFakeDrag() {
        if(this.mIsBeingDragged) {
            return 0;
        }

        this.mFakeDragging = true;
        this.setScrollState(1);
        this.mLastMotionX = 0f;
        this.mInitialMotionX = 0f;
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        else {
            this.mVelocityTracker.clear();
        }

        long v10 = SystemClock.uptimeMillis();
        MotionEvent v1 = MotionEvent.obtain(v10, v10, 0, 0f, 0f, 0);
        this.mVelocityTracker.addMovement(v1);
        v1.recycle();
        this.mFakeDragBeginTime = v10;
        return 1;
    }

    private void calculatePageOffsets(b arg11, int arg12, b arg13) {
        int v4_1;
        Object v5;
        int v13;
        int v0 = this.mAdapter.getCount();
        int v1 = this.getClientWidth();
        float v2 = v1 > 0 ? (((float)this.mPageMargin)) / (((float)v1)) : 0f;
        if(arg13 != null) {
            int v3 = arg13.b;
            if(v3 < arg11.b) {
                float v4 = arg13.e + arg13.d + v2;
                ++v3;
                v13 = 0;
                while(v3 <= arg11.b) {
                    if(v13 >= this.mItems.size()) {
                        break;
                    }

                    while(true) {
                        v5 = this.mItems.get(v13);
                        if(v3 > ((b)v5).b && v13 < this.mItems.size() - 1) {
                            ++v13;
                            continue;
                        }

                        break;
                    }

                    while(v3 < ((b)v5).b) {
                        v4 += this.mAdapter.getPageWidth(v3) + v2;
                        ++v3;
                    }

                    ((b)v5).e = v4;
                    v4 += ((b)v5).d + v2;
                    ++v3;
                }
            }
            else {
                if(v3 <= arg11.b) {
                    goto label_81;
                }

                v4_1 = this.mItems.size() - 1;
                float v13_1 = arg13.e;
                --v3;
                while(v3 >= arg11.b) {
                    if(v4_1 < 0) {
                        break;
                    }

                    while(true) {
                        v5 = this.mItems.get(v4_1);
                        if(v3 < ((b)v5).b && v4_1 > 0) {
                            --v4_1;
                            continue;
                        }

                        break;
                    }

                    while(v3 > ((b)v5).b) {
                        v13_1 -= this.mAdapter.getPageWidth(v3) + v2;
                        --v3;
                    }

                    v13_1 -= ((b)v5).d + v2;
                    ((b)v5).e = v13_1;
                    --v3;
                }
            }
        }

    label_81:
        v13 = this.mItems.size();
        float v3_1 = arg11.e;
        v4_1 = arg11.b - 1;
        float v5_1 = arg11.b == 0 ? arg11.e : -340282346638528860000000000000000000000f;
        this.mFirstOffset = v5_1;
        --v0;
        float v6 = 1f;
        v5_1 = arg11.b == v0 ? arg11.e + arg11.d - v6 : 340282346638528860000000000000000000000f;
        this.mLastOffset = v5_1;
        int v5_2 = arg12 - 1;
        while(v5_2 >= 0) {
            Object v7 = this.mItems.get(v5_2);
            while(v4_1 > ((b)v7).b) {
                v3_1 -= this.mAdapter.getPageWidth(v4_1) + v2;
                --v4_1;
            }

            v3_1 -= ((b)v7).d + v2;
            ((b)v7).e = v3_1;
            if(((b)v7).b == 0) {
                this.mFirstOffset = v3_1;
            }

            --v5_2;
            --v4_1;
        }

        v3_1 = arg11.e + arg11.d + v2;
        int v11 = arg11.b + 1;
        ++arg12;
        while(arg12 < v13) {
            Object v4_2 = this.mItems.get(arg12);
            while(v11 < ((b)v4_2).b) {
                v3_1 += this.mAdapter.getPageWidth(v11) + v2;
                ++v11;
            }

            if(((b)v4_2).b == v0) {
                this.mLastOffset = ((b)v4_2).d + v3_1 - v6;
            }

            ((b)v4_2).e = v3_1;
            v3_1 += ((b)v4_2).d + v2;
            ++arg12;
            ++v11;
        }

        this.mNeedCalculatePageOffsets = false;
    }

    protected boolean canScroll(View arg13, boolean arg14, int arg15, int arg16, int arg17) {
        View v0 = arg13;
        boolean v2 = true;
        if((v0 instanceof ViewGroup)) {
            View v1 = v0;
            int v3 = arg13.getScrollX();
            int v4 = arg13.getScrollY();
            int v5;
            for(v5 = ((ViewGroup)v1).getChildCount() - 1; v5 >= 0; --v5) {
                View v7 = ((ViewGroup)v1).getChildAt(v5);
                int v6 = arg16 + v3;
                if(v6 >= v7.getLeft() && v6 < v7.getRight()) {
                    int v8 = arg17 + v4;
                    if(v8 >= v7.getTop() && v8 < v7.getBottom() && (this.canScroll(v7, true, arg15, v6 - v7.getLeft(), v8 - v7.getTop()))) {
                        return 1;
                    }
                }
            }
        }

        if(!arg14 || !arg13.canScrollHorizontally(-arg15)) {
            v2 = false;
        }
        else {
        }

        return v2;
    }

    public boolean canScrollHorizontally(int arg5) {
        boolean v1 = false;
        if(this.mAdapter == null) {
            return 0;
        }

        int v0 = this.getClientWidth();
        int v2 = this.getScrollX();
        if(arg5 < 0) {
            if(v2 > (((int)((((float)v0)) * this.mFirstOffset)))) {
                v1 = true;
            }

            return v1;
        }

        if(arg5 > 0 && v2 < (((int)((((float)v0)) * this.mLastOffset)))) {
            v1 = true;
        }

        return v1;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg2) {
        boolean v2 = !(arg2 instanceof c) || !super.checkLayoutParams(arg2) ? false : true;
        return v2;
    }

    public void clearOnPageChangeListeners() {
        if(this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.clear();
        }
    }

    private void completeScroll(boolean arg8) {
        int v3;
        int v0 = this.mScrollState == 2 ? 1 : 0;
        if(v0 != 0) {
            this.setScrollingCacheEnabled(false);
            if((this.mScroller.isFinished() ^ 1) != 0) {
                this.mScroller.abortAnimation();
                v3 = this.getScrollX();
                int v4 = this.getScrollY();
                int v5 = this.mScroller.getCurrX();
                int v6 = this.mScroller.getCurrY();
                if(v3 == v5 && v4 == v6) {
                    goto label_27;
                }

                this.scrollTo(v5, v6);
                if(v5 == v3) {
                    goto label_27;
                }

                this.pageScrolled(v5);
            }
        }

    label_27:
        this.mPopulatePending = false;
        v3 = v0;
        for(v0 = 0; v0 < this.mItems.size(); ++v0) {
            Object v4_1 = this.mItems.get(v0);
            if(((b)v4_1).c) {
                ((b)v4_1).c = false;
                v3 = 1;
            }
        }

        if(v3 != 0) {
            if(arg8) {
                t.a(((View)this), this.mEndScrollRunnable);
            }
            else {
                this.mEndScrollRunnable.run();
            }
        }
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if(!this.mScroller.isFinished() && (this.mScroller.computeScrollOffset())) {
            int v0 = this.getScrollX();
            int v1 = this.getScrollY();
            int v2 = this.mScroller.getCurrX();
            int v3 = this.mScroller.getCurrY();
            if(v0 != v2 || v1 != v3) {
                this.scrollTo(v2, v3);
                if(!this.pageScrolled(v2)) {
                    this.mScroller.abortAnimation();
                    this.scrollTo(0, v3);
                }
            }

            t.d(((View)this));
            return;
        }

        this.completeScroll(true);
    }

    void dataSetChanged() {
        int v0 = this.mAdapter.getCount();
        this.mExpectedAdapterCount = v0;
        int v1 = this.mItems.size() >= this.mOffscreenPageLimit * 2 + 1 || this.mItems.size() >= v0 ? 0 : 1;
        int v5 = v1;
        int v6 = this.mCurItem;
        v1 = 0;
        int v2 = 0;
        while(v1 < this.mItems.size()) {
            Object v7 = this.mItems.get(v1);
            int v8 = this.mAdapter.getItemPosition(((b)v7).a);
            if(v8 == -1) {
            }
            else {
                if(v8 == -2) {
                    this.mItems.remove(v1);
                    --v1;
                    if(v2 == 0) {
                        this.mAdapter.startUpdate(((ViewGroup)this));
                        v2 = 1;
                    }

                    this.mAdapter.destroyItem(((ViewGroup)this), ((b)v7).b, ((b)v7).a);
                    if(this.mCurItem != ((b)v7).b) {
                        goto label_54;
                    }

                    v6 = Math.max(0, Math.min(this.mCurItem, v0 - 1));
                }
                else {
                    if(((b)v7).b == v8) {
                        goto label_64;
                    }

                    if(((b)v7).b == this.mCurItem) {
                        v6 = v8;
                    }

                    ((b)v7).b = v8;
                }

            label_54:
                v5 = 1;
            }

        label_64:
            ++v1;
        }

        if(v2 != 0) {
            this.mAdapter.finishUpdate(((ViewGroup)this));
        }

        Collections.sort(this.mItems, ViewPager.COMPARATOR);
        if(v5 != 0) {
            v0 = this.getChildCount();
            for(v1 = 0; v1 < v0; ++v1) {
                ViewGroup$LayoutParams v2_1 = this.getChildAt(v1).getLayoutParams();
                if(!((c)v2_1).a) {
                    ((c)v2_1).c = 0f;
                }
            }

            this.setCurrentItemInternal(v6, false, true);
            this.requestLayout();
        }
    }

    private int determineTargetPage(int arg2, float arg3, int arg4, int arg5) {
        if(Math.abs(arg5) <= this.mFlingDistance || Math.abs(arg4) <= this.mMinimumVelocity) {
            float v4 = arg2 >= this.mCurItem ? 0.4f : 0.6f;
            arg2 += ((int)(arg3 + v4));
        }
        else if(arg4 > 0) {
        }
        else {
            ++arg2;
        }

        if(this.mItems.size() > 0) {
            arg2 = Math.max(this.mItems.get(0).b, Math.min(arg2, this.mItems.get(this.mItems.size() - 1).b));
        }

        return arg2;
    }

    public boolean dispatchKeyEvent(KeyEvent arg2) {
        boolean v2 = (super.dispatchKeyEvent(arg2)) || (this.executeKeyEvent(arg2)) ? true : false;
        return v2;
    }

    private void dispatchOnPageScrolled(int arg4, float arg5, int arg6) {
        if(this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(arg4, arg5, arg6);
        }

        if(this.mOnPageChangeListeners != null) {
            int v0 = 0;
            int v1 = this.mOnPageChangeListeners.size();
            while(v0 < v1) {
                Object v2 = this.mOnPageChangeListeners.get(v0);
                if(v2 != null) {
                    ((f)v2).onPageScrolled(arg4, arg5, arg6);
                }

                ++v0;
            }
        }

        if(this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(arg4, arg5, arg6);
        }
    }

    private void dispatchOnPageSelected(int arg4) {
        if(this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(arg4);
        }

        if(this.mOnPageChangeListeners != null) {
            int v0 = 0;
            int v1 = this.mOnPageChangeListeners.size();
            while(v0 < v1) {
                Object v2 = this.mOnPageChangeListeners.get(v0);
                if(v2 != null) {
                    ((f)v2).onPageSelected(arg4);
                }

                ++v0;
            }
        }

        if(this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(arg4);
        }
    }

    private void dispatchOnScrollStateChanged(int arg4) {
        if(this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(arg4);
        }

        if(this.mOnPageChangeListeners != null) {
            int v0 = 0;
            int v1 = this.mOnPageChangeListeners.size();
            while(v0 < v1) {
                Object v2 = this.mOnPageChangeListeners.get(v0);
                if(v2 != null) {
                    ((f)v2).onPageScrollStateChanged(arg4);
                }

                ++v0;
            }
        }

        if(this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrollStateChanged(arg4);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg7) {
        if(arg7.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(arg7);
        }

        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = this.getChildAt(v2);
            if(v3.getVisibility() == 0) {
                b v4 = this.infoForChild(v3);
                if(v4 != null && v4.b == this.mCurItem && (v3.dispatchPopulateAccessibilityEvent(arg7))) {
                    return 1;
                }
            }
        }

        return 0;
    }

    float distanceInfluenceForSnapDuration(float arg3) {
        return ((float)Math.sin(((double)((arg3 - 0.5f) * 0.471239f))));
    }

    public void draw(Canvas arg8) {
        int v3;
        int v2;
        super.draw(arg8);
        int v0 = this.getOverScrollMode();
        int v1 = 0;
        if(v0 != 0) {
            if(v0 == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1) {
                goto label_17;
            }

            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        else {
        label_17:
            if(!this.mLeftEdge.isFinished()) {
                v0 = arg8.save();
                v2 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                v3 = this.getWidth();
                arg8.rotate(270f);
                arg8.translate(((float)(-v2 + this.getPaddingTop())), this.mFirstOffset * (((float)v3)));
                this.mLeftEdge.setSize(v2, v3);
                v1 = 0 | this.mLeftEdge.draw(arg8);
                arg8.restoreToCount(v0);
            }

            if(this.mRightEdge.isFinished()) {
                goto label_71;
            }

            v0 = arg8.save();
            v2 = this.getWidth();
            v3 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
            arg8.rotate(90f);
            arg8.translate(((float)(-this.getPaddingTop())), -(this.mLastOffset + 1f) * (((float)v2)));
            this.mRightEdge.setSize(v3, v2);
            v1 |= this.mRightEdge.draw(arg8);
            arg8.restoreToCount(v0);
        }

    label_71:
        if(v1 != 0) {
            t.d(((View)this));
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable v0 = this.mMarginDrawable;
        if(v0 != null && (v0.isStateful())) {
            v0.setState(this.getDrawableState());
        }
    }

    private void enableLayers(boolean arg7) {
        int v0 = this.getChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            int v3 = arg7 ? this.mPageTransformerLayerType : 0;
            this.getChildAt(v2).setLayerType(v3, null);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if(this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void endFakeDrag() {
        if(this.mFakeDragging) {
            if(this.mAdapter != null) {
                VelocityTracker v0 = this.mVelocityTracker;
                v0.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
                int v0_1 = ((int)v0.getXVelocity(this.mActivePointerId));
                this.mPopulatePending = true;
                int v2 = this.getClientWidth();
                int v3 = this.getScrollX();
                b v4 = this.infoForCurrentScrollPosition();
                this.setCurrentItemInternal(this.determineTargetPage(v4.b, ((((float)v3)) / (((float)v2)) - v4.e) / v4.d, v0_1, ((int)(this.mLastMotionX - this.mInitialMotionX))), true, true, v0_1);
            }

            this.endDrag();
            this.mFakeDragging = false;
            return;
        }

        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    public boolean executeKeyEvent(KeyEvent arg4) {
        boolean v4;
        if(arg4.getAction() == 0) {
            int v0 = arg4.getKeyCode();
            int v2 = 2;
            if(v0 != 61) {
                switch(v0) {
                    case 21: {
                        goto label_14;
                    }
                    case 22: {
                        goto label_8;
                    }
                }

                goto label_30;
            label_8:
                if(arg4.hasModifiers(v2)) {
                    v4 = this.pageRight();
                }
                else {
                    int v4_1 = 66;
                    goto label_19;
                label_14:
                    if(arg4.hasModifiers(v2)) {
                        v4 = this.pageLeft();
                    }
                    else {
                        v4_1 = 17;
                    label_19:
                        v4 = this.arrowScroll(v4_1);
                    }
                }
            }
            else if(arg4.hasNoModifiers()) {
                v4 = this.arrowScroll(v2);
            }
            else if(arg4.hasModifiers(1)) {
                v4 = this.arrowScroll(1);
            }
            else {
                goto label_30;
            }
        }
        else {
        label_30:
            v4 = false;
        }

        return v4;
    }

    public void fakeDragBy(float arg11) {
        if(this.mFakeDragging) {
            if(this.mAdapter == null) {
                return;
            }

            this.mLastMotionX += arg11;
            float v0 = (((float)this.getScrollX())) - arg11;
            arg11 = ((float)this.getClientWidth());
            float v1 = this.mFirstOffset * arg11;
            float v2 = this.mLastOffset * arg11;
            Object v3 = this.mItems.get(0);
            Object v4 = this.mItems.get(this.mItems.size() - 1);
            if(((b)v3).b != 0) {
                v1 = ((b)v3).e * arg11;
            }

            if(((b)v4).b != this.mAdapter.getCount() - 1) {
                v2 = ((b)v4).e * arg11;
            }

            if(v0 < v1) {
                v0 = v1;
            }
            else if(v0 > v2) {
                v0 = v2;
            }

            int v1_1 = ((int)v0);
            this.mLastMotionX += v0 - (((float)v1_1));
            this.scrollTo(v1_1, this.getScrollY());
            this.pageScrolled(v1_1);
            MotionEvent v11 = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0f, 0);
            this.mVelocityTracker.addMovement(v11);
            v11.recycle();
            return;
        }

        throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new c();
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.generateDefaultLayoutParams();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg3) {
        return new c(this.getContext(), arg3);
    }

    public q getAdapter() {
        return this.mAdapter;
    }

    protected int getChildDrawingOrder(int arg3, int arg4) {
        if(this.mDrawingOrder == 2) {
            arg4 = arg3 - 1 - arg4;
        }

        return this.mDrawingOrderedChildren.get(arg4).getLayoutParams().f;
    }

    private Rect getChildRectInPagerCoordinates(Rect arg3, View arg4) {
        if(arg3 == null) {
            arg3 = new Rect();
        }

        if(arg4 == null) {
            arg3.set(0, 0, 0, 0);
            return arg3;
        }

        arg3.left = arg4.getLeft();
        arg3.right = arg4.getRight();
        arg3.top = arg4.getTop();
        arg3.bottom = arg4.getBottom();
        ViewParent v4;
        for(v4 = arg4.getParent(); (v4 instanceof ViewGroup); v4 = ((ViewGroup)v4).getParent()) {
            if((((ViewPager)v4)) == this) {
                return arg3;
            }

            arg3.left += ((ViewGroup)v4).getLeft();
            arg3.right += ((ViewGroup)v4).getRight();
            arg3.top += ((ViewGroup)v4).getTop();
            arg3.bottom += ((ViewGroup)v4).getBottom();
        }

        return arg3;
    }

    private int getClientWidth() {
        return this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    b infoForAnyChild(View arg2) {
        ViewParent v2;
        while(true) {
            ViewParent v0 = arg2.getParent();
            if((((ViewPager)v0)) == this) {
                goto label_10;
            }

            if(v0 != null) {
                if(!(v0 instanceof View)) {
                }
                else {
                    v2 = v0;
                    continue;
                }
            }

            return null;
        }

        return null;
    label_10:
        return this.infoForChild(((View)v2));
    }

    b infoForChild(View arg5) {
        int v0;
        for(v0 = 0; v0 < this.mItems.size(); ++v0) {
            Object v1 = this.mItems.get(v0);
            if(this.mAdapter.isViewFromObject(arg5, ((b)v1).a)) {
                return ((b)v1);
            }
        }

        return null;
    }

    private b infoForCurrentScrollPosition() {
        Object v5_1;
        b v10_1;
        int v0 = this.getClientWidth();
        float v2 = v0 > 0 ? (((float)this.getScrollX())) / (((float)v0)) : 0f;
        float v0_1 = v0 > 0 ? (((float)this.mPageMargin)) / (((float)v0)) : 0f;
        b v5 = null;
        int v1 = 0;
        int v3 = 1;
        int v7 = -1;
        float v8 = 0f;
        float v9 = 0f;
        while(true) {
            if(v1 >= this.mItems.size()) {
                goto label_66;
            }

            Object v10 = this.mItems.get(v1);
            if(v3 == 0) {
                ++v7;
                if(((b)v10).b != v7) {
                    v10_1 = this.mTempItem;
                    v10_1.e = v8 + v9 + v0_1;
                    v10_1.b = v7;
                    v10_1.d = this.mAdapter.getPageWidth(v10_1.b);
                    --v1;
                }
            }

            v8 = v10_1.e;
            float v7_1 = v10_1.d + v8 + v0_1;
            if(v3 == 0) {
                if(v2 >= v8) {
                }
                else {
                    return v5;
                }
            }

            if(v2 >= v7_1) {
                if(v1 == this.mItems.size() - 1) {
                }
                else {
                    v7 = v10_1.b;
                    v9 = v10_1.d;
                    ++v1;
                    v5_1 = v10_1;
                    v3 = 0;
                    continue;
                }
            }

            return v10_1;
        }

        return v10_1;
    label_66:
        return ((b)v5_1);
    }

    b infoForPosition(int arg4) {
        int v0;
        for(v0 = 0; v0 < this.mItems.size(); ++v0) {
            Object v1 = this.mItems.get(v0);
            if(((b)v1).b == arg4) {
                return ((b)v1);
            }
        }

        return null;
    }

    void initViewPager() {
        this.setWillNotDraw(false);
        this.setDescendantFocusability(262144);
        this.setFocusable(true);
        Context v1 = this.getContext();
        this.mScroller = new Scroller(v1, ViewPager.sInterpolator);
        ViewConfiguration v2 = ViewConfiguration.get(v1);
        float v3 = v1.getResources().getDisplayMetrics().density;
        this.mTouchSlop = v2.getScaledPagingTouchSlop();
        this.mMinimumVelocity = ((int)(400f * v3));
        this.mMaximumVelocity = v2.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(v1);
        this.mRightEdge = new EdgeEffect(v1);
        this.mFlingDistance = ((int)(25f * v3));
        this.mCloseEnough = ((int)(2f * v3));
        this.mDefaultGutterSize = ((int)(v3 * 16f));
        t.a(((View)this), new d(this));
        if(t.e(((View)this)) == 0) {
            t.b(((View)this), 1);
        }

        t.a(((View)this), new p() {
            private final Rect b;

            public ab a(View arg6, ab arg7) {
                ab v6 = t.a(arg6, arg7);
                if(v6.e()) {
                    return v6;
                }

                Rect v7 = this.b;
                v7.left = v6.a();
                v7.top = v6.b();
                v7.right = v6.c();
                v7.bottom = v6.d();
                int v0 = 0;
                int v1 = this.a.getChildCount();
                while(v0 < v1) {
                    ab v2 = t.b(this.a.getChildAt(v0), v6);
                    v7.left = Math.min(v2.a(), v7.left);
                    v7.top = Math.min(v2.b(), v7.top);
                    v7.right = Math.min(v2.c(), v7.right);
                    v7.bottom = Math.min(v2.d(), v7.bottom);
                    ++v0;
                }

                return v6.a(v7.left, v7.top, v7.right, v7.bottom);
            }
        });
    }

    private static boolean isDecorView(View arg1) {
        boolean v1 = arg1.getClass().getAnnotation(a.class) != null ? true : false;
        return v1;
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isGutterDrag(float arg4, float arg5) {
        // Method was not decompiled
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mEndScrollRunnable);
        if(this.mScroller != null && !this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }

        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas arg18) {
        float v16;
        float v11;
        ViewPager v0 = this;
        super.onDraw(arg18);
        if(v0.mPageMargin > 0 && v0.mMarginDrawable != null && v0.mItems.size() > 0 && v0.mAdapter != null) {
            int v1 = this.getScrollX();
            int v2 = this.getWidth();
            float v4 = ((float)v2);
            float v3 = (((float)v0.mPageMargin)) / v4;
            int v6 = 0;
            Object v5 = v0.mItems.get(0);
            float v7 = ((b)v5).e;
            int v8 = v0.mItems.size();
            int v9 = ((b)v5).b;
            int v10 = v0.mItems.get(v8 - 1).b;
            while(v9 < v10) {
                while(v9 > ((b)v5).b) {
                    if(v6 >= v8) {
                        break;
                    }

                    ++v6;
                    v5 = v0.mItems.get(v6);
                }

                if(v9 == ((b)v5).b) {
                    v7 = (((b)v5).e + ((b)v5).d) * v4;
                    v11 = ((b)v5).e + ((b)v5).d + v3;
                }
                else {
                    v11 = v0.mAdapter.getPageWidth(v9);
                    float v12 = (v7 + v11) * v4;
                    v11 = v7 + (v11 + v3);
                    v7 = v12;
                }

                if((((float)v0.mPageMargin)) + v7 > (((float)v1))) {
                    v16 = v3;
                    v0.mMarginDrawable.setBounds(Math.round(v7), v0.mTopPageBounds, Math.round((((float)v0.mPageMargin)) + v7), v0.mBottomPageBounds);
                    v0.mMarginDrawable.draw(arg18);
                }
                else {
                    v16 = v3;
                }

                if(v7 > (((float)(v1 + v2)))) {
                    return;
                }

                ++v9;
                v7 = v11;
                v3 = v16;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg17) {
        float v0_1;
        ViewPager v6 = this;
        MotionEvent v7 = arg17;
        int v0 = arg17.getAction() & 255;
        if(v0 != 3) {
            if(v0 == 1) {
            }
            else {
                if(v0 != 0) {
                    if(v6.mIsBeingDragged) {
                        return 1;
                    }
                    else if(v6.mIsUnableToDrag) {
                        return 0;
                    }
                }

                int v1 = 2;
                if(v0 == 0) {
                    v0_1 = arg17.getX();
                    v6.mInitialMotionX = v0_1;
                    v6.mLastMotionX = v0_1;
                    v0_1 = arg17.getY();
                    v6.mInitialMotionY = v0_1;
                    v6.mLastMotionY = v0_1;
                    v6.mActivePointerId = v7.getPointerId(0);
                    v6.mIsUnableToDrag = false;
                    v6.mIsScrollStarted = true;
                    v6.mScroller.computeScrollOffset();
                    if(v6.mScrollState == v1 && Math.abs(v6.mScroller.getFinalX() - v6.mScroller.getCurrX()) > v6.mCloseEnough) {
                        v6.mScroller.abortAnimation();
                        v6.mPopulatePending = false;
                        this.populate();
                        v6.mIsBeingDragged = true;
                        v6.requestParentDisallowInterceptTouchEvent(true);
                        v6.setScrollState(1);
                        goto label_120;
                    }

                    v6.completeScroll(false);
                    v6.mIsBeingDragged = false;
                }
                else if(v0 == v1) {
                    v0 = v6.mActivePointerId;
                    if(v0 == -1) {
                    }
                    else {
                        v0 = v7.findPointerIndex(v0);
                        float v10 = v7.getX(v0);
                        float v11 = v10 - v6.mLastMotionX;
                        float v12 = Math.abs(v11);
                        float v13 = v7.getY(v0);
                        float v14 = Math.abs(v13 - v6.mInitialMotionY);
                        if(v11 != 0f && !v6.isGutterDrag(v6.mLastMotionX, v11) && (this.canScroll(this, false, ((int)v11), ((int)v10), ((int)v13)))) {
                            v6.mLastMotionX = v10;
                            v6.mLastMotionY = v13;
                            v6.mIsUnableToDrag = true;
                            return 0;
                        }

                        if(v12 <= (((float)v6.mTouchSlop)) || v12 * 0.5f <= v14) {
                            if(v14 <= (((float)v6.mTouchSlop))) {
                                goto label_82;
                            }

                            v6.mIsUnableToDrag = true;
                        }
                        else {
                            v6.mIsBeingDragged = true;
                            v6.requestParentDisallowInterceptTouchEvent(true);
                            v6.setScrollState(1);
                            v0_1 = v11 > 0f ? v6.mInitialMotionX + (((float)v6.mTouchSlop)) : v6.mInitialMotionX - (((float)v6.mTouchSlop));
                            v6.mLastMotionX = v0_1;
                            v6.mLastMotionY = v13;
                            v6.setScrollingCacheEnabled(true);
                        }

                    label_82:
                        if(!v6.mIsBeingDragged) {
                            goto label_120;
                        }

                        if(!v6.performDrag(v10)) {
                            goto label_120;
                        }

                        t.d(((View)this));
                    }
                }
                else if(v0 != 6) {
                }
                else {
                    this.onSecondaryPointerUp(arg17);
                }

            label_120:
                if(v6.mVelocityTracker == null) {
                    v6.mVelocityTracker = VelocityTracker.obtain();
                }

                v6.mVelocityTracker.addMovement(v7);
                return v6.mIsBeingDragged;
            }
        }

        this.resetTouch();
        return 0;
    }

    protected void onLayout(boolean arg19, int arg20, int arg21, int arg22, int arg23) {
        boolean v2_1;
        ViewPager v0 = this;
        int v1 = this.getChildCount();
        int v2 = arg22 - arg20;
        int v3 = arg23 - arg21;
        int v4 = this.getPaddingLeft();
        int v5 = this.getPaddingTop();
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingBottom();
        int v8 = this.getScrollX();
        int v10 = v7;
        int v11 = 0;
        v7 = v5;
        v5 = v4;
        for(v4 = 0; true; ++v4) {
            int v12 = 8;
            if(v4 >= v1) {
                break;
            }

            View v13 = v0.getChildAt(v4);
            if(v13.getVisibility() != v12) {
                ViewGroup$LayoutParams v12_1 = v13.getLayoutParams();
                if(((c)v12_1).a) {
                    int v14 = ((c)v12_1).b & 7;
                    v12 = ((c)v12_1).b & 112;
                    if(v14 == 1) {
                        v14 = Math.max((v2 - v13.getMeasuredWidth()) / 2, v5);
                    }
                    else if(v14 == 3) {
                        v14 = v5;
                        v5 = v13.getMeasuredWidth() + v5;
                    }
                    else if(v14 != 5) {
                        v14 = v5;
                    }
                    else {
                        v14 = v2 - v6 - v13.getMeasuredWidth();
                        v6 += v13.getMeasuredWidth();
                    }

                    if(v12 == 16) {
                        v12 = Math.max((v3 - v13.getMeasuredHeight()) / 2, v7);
                    }
                    else if(v12 == 48) {
                        v12 = v7;
                        v7 = v13.getMeasuredHeight() + v7;
                    }
                    else if(v12 != 80) {
                        v12 = v7;
                    }
                    else {
                        v12 = v3 - v10 - v13.getMeasuredHeight();
                        v10 += v13.getMeasuredHeight();
                    }

                    v14 += v8;
                    v13.layout(v14, v12, v13.getMeasuredWidth() + v14, v12 + v13.getMeasuredHeight());
                    ++v11;
                }
            }
        }

        v2 = v2 - v5 - v6;
        for(v4 = 0; v4 < v1; ++v4) {
            View v6_1 = v0.getChildAt(v4);
            if(v6_1.getVisibility() != v12) {
                ViewGroup$LayoutParams v8_1 = v6_1.getLayoutParams();
                if(!((c)v8_1).a) {
                    b v9 = v0.infoForChild(v6_1);
                    if(v9 != null) {
                        float v13_1 = ((float)v2);
                        int v9_1 = (((int)(v9.e * v13_1))) + v5;
                        if(((c)v8_1).d) {
                            ((c)v8_1).d = false;
                            v6_1.measure(View$MeasureSpec.makeMeasureSpec(((int)(v13_1 * ((c)v8_1).c)), 1073741824), View$MeasureSpec.makeMeasureSpec(v3 - v7 - v10, 1073741824));
                        }

                        v6_1.layout(v9_1, v7, v6_1.getMeasuredWidth() + v9_1, v6_1.getMeasuredHeight() + v7);
                    }
                }
            }
        }

        v0.mTopPageBounds = v7;
        v0.mBottomPageBounds = v3 - v10;
        v0.mDecorChildCount = v11;
        if(v0.mFirstLayout) {
            v2_1 = false;
            v0.scrollToItem(v0.mCurItem, false, 0, false);
        }
        else {
            v2_1 = false;
        }

        v0.mFirstLayout = v2_1;
    }

    protected void onMeasure(int arg17, int arg18) {
        int v1;
        int v13;
        int v12;
        int v8;
        ViewPager v0 = this;
        v0.setMeasuredDimension(ViewPager.getDefaultSize(0, arg17), ViewPager.getDefaultSize(0, arg18));
        int v2 = this.getMeasuredWidth();
        v0.mGutterSize = Math.min(v2 / 10, v0.mDefaultGutterSize);
        v2 = v2 - this.getPaddingLeft() - this.getPaddingRight();
        int v3 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int v4 = this.getChildCount();
        int v5 = v3;
        v3 = v2;
        for(v2 = 0; true; ++v2) {
            int v6 = 8;
            int v7 = 1;
            v8 = 1073741824;
            if(v2 >= v4) {
                break;
            }

            View v9 = v0.getChildAt(v2);
            if(v9.getVisibility() != v6) {
                ViewGroup$LayoutParams v6_1 = v9.getLayoutParams();
                if(v6_1 != null && (((c)v6_1).a)) {
                    int v10 = ((c)v6_1).b & 7;
                    int v11 = ((c)v6_1).b & 112;
                    v11 = v11 == 48 || v11 == 80 ? 1 : 0;
                    if(v10 != 3) {
                        if(v10 == 5) {
                        }
                        else {
                            v7 = 0;
                        }
                    }

                    v10 = -2147483648;
                    if(v11 != 0) {
                        v10 = 1073741824;
                        goto label_57;
                    }
                    else if(v7 != 0) {
                        v12 = 1073741824;
                    }
                    else {
                    label_57:
                        v12 = -2147483648;
                    }

                    int v14 = -1;
                    int v15 = -2;
                    if(((c)v6_1).width != v15) {
                        v13 = ((c)v6_1).width != v14 ? ((c)v6_1).width : v3;
                        v10 = 1073741824;
                    }
                    else {
                        v13 = v3;
                    }

                    if(((c)v6_1).height == v15) {
                        v1 = v5;
                        v8 = v12;
                    }
                    else if(((c)v6_1).height != v14) {
                        v1 = ((c)v6_1).height;
                    }
                    else {
                        v1 = v5;
                    }

                    v9.measure(View$MeasureSpec.makeMeasureSpec(v13, v10), View$MeasureSpec.makeMeasureSpec(v1, v8));
                    if(v11 != 0) {
                        v5 -= v9.getMeasuredHeight();
                        goto label_94;
                    }

                    if(v7 == 0) {
                        goto label_94;
                    }

                    v3 -= v9.getMeasuredWidth();
                }
            }

        label_94:
        }

        v0.mChildWidthMeasureSpec = View$MeasureSpec.makeMeasureSpec(v3, v8);
        v0.mChildHeightMeasureSpec = View$MeasureSpec.makeMeasureSpec(v5, v8);
        v0.mInLayout = true;
        this.populate();
        v1 = 0;
        v0.mInLayout = false;
        v2 = this.getChildCount();
        while(v1 < v2) {
            View v4_1 = v0.getChildAt(v1);
            if(v4_1.getVisibility() != v6) {
                ViewGroup$LayoutParams v5_1 = v4_1.getLayoutParams();
                if(v5_1 != null && (((c)v5_1).a)) {
                    goto label_121;
                }

                v4_1.measure(View$MeasureSpec.makeMeasureSpec(((int)((((float)v3)) * ((c)v5_1).c)), v8), v0.mChildHeightMeasureSpec);
            }

        label_121:
            ++v1;
        }
    }

    protected void onPageScrolled(int arg13, float arg14, int arg15) {
        int v1 = 0;
        if(this.mDecorChildCount > 0) {
            int v0 = this.getScrollX();
            int v3 = this.getPaddingLeft();
            int v4 = this.getPaddingRight();
            int v5 = this.getWidth();
            int v6 = this.getChildCount();
            int v7 = v4;
            v4 = v3;
            for(v3 = 0; v3 < v6; ++v3) {
                View v8 = this.getChildAt(v3);
                ViewGroup$LayoutParams v9 = v8.getLayoutParams();
                if(!((c)v9).a) {
                }
                else {
                    int v9_1 = ((c)v9).b & 7;
                    if(v9_1 == 1) {
                        v9_1 = Math.max((v5 - v8.getMeasuredWidth()) / 2, v4);
                    label_40:
                        int v11 = v9_1;
                        v9_1 = v4;
                        v4 = v11;
                    }
                    else if(v9_1 == 3) {
                        v9_1 = v8.getWidth() + v4;
                    }
                    else if(v9_1 != 5) {
                        v9_1 = v4;
                    }
                    else {
                        v9_1 = v5 - v7 - v8.getMeasuredWidth();
                        v7 += v8.getMeasuredWidth();
                        goto label_40;
                    }

                    v4 = v4 + v0 - v8.getLeft();
                    if(v4 != 0) {
                        v8.offsetLeftAndRight(v4);
                    }

                    v4 = v9_1;
                }
            }
        }

        this.dispatchOnPageScrolled(arg13, arg14, arg15);
        if(this.mPageTransformer != null) {
            arg13 = this.getScrollX();
            int v14 = this.getChildCount();
            while(v1 < v14) {
                View v15 = this.getChildAt(v1);
                if(v15.getLayoutParams().a) {
                }
                else {
                    this.mPageTransformer.a(v15, (((float)(v15.getLeft() - arg13))) / (((float)this.getClientWidth())));
                }

                ++v1;
            }
        }

        this.mCalledSuper = true;
    }

    protected boolean onRequestFocusInDescendants(int arg9, Rect arg10) {
        int v1;
        int v0 = this.getChildCount();
        int v2 = -1;
        if((arg9 & 2) != 0) {
            v2 = v0;
            v0 = 0;
            v1 = 1;
        }
        else {
            --v0;
            v1 = -1;
        }

        while(v0 != v2) {
            View v5 = this.getChildAt(v0);
            if(v5.getVisibility() == 0) {
                b v6 = this.infoForChild(v5);
                if(v6 != null && v6.b == this.mCurItem && (v5.requestFocus(arg9, arg10))) {
                    return 1;
                }
            }

            v0 += v1;
        }

        return 0;
    }

    public void onRestoreInstanceState(Parcelable arg4) {
        if(!(arg4 instanceof SavedState)) {
            super.onRestoreInstanceState(arg4);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg4).getSuperState());
        if(this.mAdapter != null) {
            this.mAdapter.restoreState(((SavedState)arg4).b, ((SavedState)arg4).c);
            this.setCurrentItemInternal(((SavedState)arg4).a, false, true);
        }
        else {
            this.mRestoredCurItem = ((SavedState)arg4).a;
            this.mRestoredAdapterState = ((SavedState)arg4).b;
            this.mRestoredClassLoader = ((SavedState)arg4).c;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.a = this.mCurItem;
        if(this.mAdapter != null) {
            v1.b = this.mAdapter.saveState();
        }

        return ((Parcelable)v1);
    }

    private void onSecondaryPointerUp(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.mActivePointerId) {
            v0 = v0 == 0 ? 1 : 0;
            this.mLastMotionX = arg4.getX(v0);
            this.mActivePointerId = arg4.getPointerId(v0);
            if(this.mVelocityTracker == null) {
                return;
            }

            this.mVelocityTracker.clear();
        }
    }

    protected void onSizeChanged(int arg1, int arg2, int arg3, int arg4) {
        super.onSizeChanged(arg1, arg2, arg3, arg4);
        if(arg1 != arg3) {
            this.recomputeScrollPosition(arg1, arg3, this.mPageMargin, this.mPageMargin);
        }
    }

    public boolean onTouchEvent(MotionEvent arg8) {
        int v8;
        float v0_1;
        int v0;
        if(this.mFakeDragging) {
            return 1;
        }

        int v2 = 0;
        if(arg8.getAction() == 0 && arg8.getEdgeFlags() != 0) {
            return 0;
        }

        if(this.mAdapter != null) {
            if(this.mAdapter.getCount() == 0) {
            }
            else {
                if(this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }

                this.mVelocityTracker.addMovement(arg8);
                switch(arg8.getAction() & 255) {
                    case 0: {
                        this.mScroller.abortAnimation();
                        this.mPopulatePending = false;
                        this.populate();
                        v0_1 = arg8.getX();
                        this.mInitialMotionX = v0_1;
                        this.mLastMotionX = v0_1;
                        v0_1 = arg8.getY();
                        this.mInitialMotionY = v0_1;
                        this.mLastMotionY = v0_1;
                        v8 = arg8.getPointerId(0);
                        goto label_138;
                    }
                    case 1: {
                        if(!this.mIsBeingDragged) {
                            goto label_139;
                        }

                        VelocityTracker v0_3 = this.mVelocityTracker;
                        v0_3.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
                        v0 = ((int)v0_3.getXVelocity(this.mActivePointerId));
                        this.mPopulatePending = true;
                        v2 = this.getClientWidth();
                        int v3_1 = this.getScrollX();
                        b v4_1 = this.infoForCurrentScrollPosition();
                        float v2_1 = ((float)v2);
                        this.setCurrentItemInternal(this.determineTargetPage(v4_1.b, ((((float)v3_1)) / v2_1 - v4_1.e) / (v4_1.d + (((float)this.mPageMargin)) / v2_1), v0, ((int)(arg8.getX(arg8.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX))), true, true, v0);
                    label_125:
                        boolean v2_2 = this.resetTouch();
                        break;
                    }
                    case 2: {
                        if(!this.mIsBeingDragged) {
                            v0 = arg8.findPointerIndex(this.mActivePointerId);
                            if(v0 == -1) {
                                goto label_125;
                            }
                            else {
                                float v3 = arg8.getX(v0);
                                float v4 = Math.abs(v3 - this.mLastMotionX);
                                v0_1 = arg8.getY(v0);
                                float v5 = Math.abs(v0_1 - this.mLastMotionY);
                                if(v4 > (((float)this.mTouchSlop)) && v4 > v5) {
                                    this.mIsBeingDragged = true;
                                    this.requestParentDisallowInterceptTouchEvent(true);
                                    v3 = v3 - this.mInitialMotionX > 0f ? this.mInitialMotionX + (((float)this.mTouchSlop)) : this.mInitialMotionX - (((float)this.mTouchSlop));
                                    this.mLastMotionX = v3;
                                    this.mLastMotionY = v0_1;
                                    this.setScrollState(1);
                                    this.setScrollingCacheEnabled(true);
                                    ViewParent v0_2 = this.getParent();
                                    if(v0_2 == null) {
                                        goto label_83;
                                    }

                                    v0_2.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }

                    label_83:
                        if(!this.mIsBeingDragged) {
                            goto label_139;
                        }

                        v2 = 0 | this.performDrag(arg8.getX(arg8.findPointerIndex(this.mActivePointerId)));
                        break;
                    }
                    case 3: {
                        if(!this.mIsBeingDragged) {
                            goto label_139;
                        }

                        this.scrollToItem(this.mCurItem, true, 0, false);
                        goto label_125;
                    }
                    case 5: {
                        v0 = arg8.getActionIndex();
                        this.mLastMotionX = arg8.getX(v0);
                        v8 = arg8.getPointerId(v0);
                    label_138:
                        this.mActivePointerId = v8;
                        break;
                    }
                    case 6: {
                        this.onSecondaryPointerUp(arg8);
                        this.mLastMotionX = arg8.getX(arg8.findPointerIndex(this.mActivePointerId));
                        break;
                    }
                    default: {
                        break;
                    }
                }

            label_139:
                if((((int)v2_2)) != 0) {
                    t.d(((View)this));
                }

                return 1;
            }
        }

        return 0;
    }

    boolean pageLeft() {
        if(this.mCurItem > 0) {
            this.setCurrentItem(this.mCurItem - 1, true);
            return 1;
        }

        return 0;
    }

    boolean pageRight() {
        if(this.mAdapter != null && this.mCurItem < this.mAdapter.getCount() - 1) {
            this.setCurrentItem(this.mCurItem + 1, true);
            return 1;
        }

        return 0;
    }

    private boolean pageScrolled(int arg7) {
        if(this.mItems.size() == 0) {
            if(this.mFirstLayout) {
                return 0;
            }

            this.mCalledSuper = false;
            this.onPageScrolled(0, 0f, 0);
            if(this.mCalledSuper) {
                return 0;
            }

            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }

        b v0 = this.infoForCurrentScrollPosition();
        int v2 = this.getClientWidth();
        int v3 = this.mPageMargin + v2;
        float v2_1 = ((float)v2);
        float v4 = (((float)this.mPageMargin)) / v2_1;
        int v5 = v0.b;
        float v7 = ((((float)arg7)) / v2_1 - v0.e) / (v0.d + v4);
        this.mCalledSuper = false;
        this.onPageScrolled(v5, v7, ((int)((((float)v3)) * v7)));
        if(this.mCalledSuper) {
            return 1;
        }

        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean performDrag(float arg10) {
        int v5_1;
        int v3_1;
        float v0 = this.mLastMotionX - arg10;
        this.mLastMotionX = arg10;
        arg10 = (((float)this.getScrollX())) + v0;
        v0 = ((float)this.getClientWidth());
        float v1 = this.mFirstOffset * v0;
        float v2 = this.mLastOffset * v0;
        boolean v4 = false;
        Object v3 = this.mItems.get(0);
        Object v5 = this.mItems.get(this.mItems.size() - 1);
        if(((b)v3).b != 0) {
            v1 = ((b)v3).e * v0;
            v3_1 = 0;
        }
        else {
            v3_1 = 1;
        }

        if(((b)v5).b != this.mAdapter.getCount() - 1) {
            v2 = ((b)v5).e * v0;
            v5_1 = 0;
        }
        else {
            v5_1 = 1;
        }

        if(arg10 < v1) {
            if(v3_1 != 0) {
                this.mLeftEdge.onPull(Math.abs(v1 - arg10) / v0);
                v4 = true;
            }

            arg10 = v1;
        }
        else {
            if(arg10 <= v2) {
                goto label_57;
            }

            if(v5_1 != 0) {
                this.mRightEdge.onPull(Math.abs(arg10 - v2) / v0);
                v4 = true;
            }

            arg10 = v2;
        }

    label_57:
        int v1_1 = ((int)arg10);
        this.mLastMotionX += arg10 - (((float)v1_1));
        this.scrollTo(v1_1, this.getScrollY());
        this.pageScrolled(v1_1);
        return v4;
    }

    void populate() {
        this.populate(this.mCurItem);
    }

    void populate(int arg18) {
        String v1_2;
        b v3_2;
        Object v5;
        Object v8;
        b v2;
        ViewPager v0 = this;
        int v1 = arg18;
        if(v0.mCurItem != v1) {
            v2 = v0.infoForPosition(v0.mCurItem);
            v0.mCurItem = v1;
        }
        else {
            v2 = null;
        }

        if(v0.mAdapter == null) {
            this.sortChildDrawingOrder();
            return;
        }

        if(v0.mPopulatePending) {
            this.sortChildDrawingOrder();
            return;
        }

        if(this.getWindowToken() == null) {
            return;
        }

        v0.mAdapter.startUpdate(((ViewGroup)v0));
        v1 = v0.mOffscreenPageLimit;
        int v4 = Math.max(0, v0.mCurItem - v1);
        int v6 = v0.mAdapter.getCount();
        v1 = Math.min(v6 - 1, v0.mCurItem + v1);
        if(v6 == v0.mExpectedAdapterCount) {
            int v7 = 0;
            while(true) {
                if(v7 < v0.mItems.size()) {
                    v8 = v0.mItems.get(v7);
                    if(((b)v8).b < v0.mCurItem) {
                        ++v7;
                        continue;
                    }
                    else if(((b)v8).b == v0.mCurItem) {
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }

                goto label_51;
            }

            v8 = null;
        label_51:
            if(v8 == null && v6 > 0) {
                b v8_1 = v0.addNewItem(v0.mCurItem, v7);
            }

            if(v8 != null) {
                int v10 = v7 - 1;
                Object v11 = v10 >= 0 ? v0.mItems.get(v10) : null;
                int v12 = this.getClientWidth();
                float v13 = 2f;
                float v3 = v12 <= 0 ? 0f : v13 - ((b)v8).d + (((float)this.getPaddingLeft())) / (((float)v12));
                int v14 = v0.mCurItem - 1;
                int v15 = v7;
                float v7_1 = 0f;
                while(v14 >= 0) {
                    if(v7_1 < v3 || v14 >= v4) {
                        if(v11 != null && v14 == ((b)v11).b) {
                            v7_1 += ((b)v11).d;
                            --v10;
                            if(v10 >= 0) {
                                goto label_111;
                            }
                            else {
                                goto label_114;
                            }
                        }

                        v7_1 += v0.addNewItem(v14, v10 + 1).d;
                        ++v15;
                        if(v10 >= 0) {
                        label_111:
                            v5 = v0.mItems.get(v10);
                        }
                        else {
                        label_114:
                            v5 = null;
                        }

                        v11 = v5;
                    }
                    else if(v11 == null) {
                        break;
                    }
                    else if(v14 == ((b)v11).b && !((b)v11).c) {
                        v0.mItems.remove(v10);
                        v0.mAdapter.destroyItem(((ViewGroup)v0), v14, ((b)v11).a);
                        --v10;
                        --v15;
                        if(v10 >= 0) {
                            goto label_111;
                        }
                        else {
                            goto label_114;
                        }
                    }

                    --v14;
                }

                v3 = ((b)v8).d;
                v4 = v15 + 1;
                if(v3 < v13) {
                    v5 = v4 < v0.mItems.size() ? v0.mItems.get(v4) : null;
                    v7_1 = v12 <= 0 ? 0f : (((float)this.getPaddingRight())) / (((float)v12)) + v13;
                    v10 = v0.mCurItem;
                    while(true) {
                        ++v10;
                        if(v10 >= v6) {
                            break;
                        }

                        if(v3 < v7_1 || v10 <= v1) {
                            if(v5 != null && v10 == ((b)v5).b) {
                                v3 += ((b)v5).d;
                                ++v4;
                                if(v4 < v0.mItems.size()) {
                                    goto label_156;
                                }
                                else {
                                    goto label_159;
                                }
                            }

                            b v5_1 = v0.addNewItem(v10, v4);
                            ++v4;
                            v3 += v5_1.d;
                            if(v4 >= v0.mItems.size()) {
                                goto label_159;
                            }

                        label_156:
                            v5 = v0.mItems.get(v4);
                            continue;
                        }
                        else if(v5 == null) {
                            break;
                        }
                        else {
                            if(v10 != ((b)v5).b) {
                                continue;
                            }

                            if(((b)v5).c) {
                                continue;
                            }

                            v0.mItems.remove(v4);
                            v0.mAdapter.destroyItem(((ViewGroup)v0), v10, ((b)v5).a);
                            if(v4 >= v0.mItems.size()) {
                                goto label_159;
                            }

                            goto label_156;
                        }

                    label_159:
                        v5 = null;
                    }
                }

                v0.calculatePageOffsets(((b)v8), v15, v2);
                v0.mAdapter.setPrimaryItem(((ViewGroup)v0), v0.mCurItem, ((b)v8).a);
            }

            v0.mAdapter.finishUpdate(((ViewGroup)v0));
            v1 = this.getChildCount();
            int v2_1;
            for(v2_1 = 0; v2_1 < v1; ++v2_1) {
                View v3_1 = v0.getChildAt(v2_1);
                ViewGroup$LayoutParams v4_1 = v3_1.getLayoutParams();
                ((c)v4_1).f = v2_1;
                if(!((c)v4_1).a && ((c)v4_1).c == 0f) {
                    v3_2 = v0.infoForChild(v3_1);
                    if(v3_2 != null) {
                        ((c)v4_1).c = v3_2.d;
                        ((c)v4_1).e = v3_2.b;
                    }
                }
            }

            this.sortChildDrawingOrder();
            if(this.hasFocus()) {
                View v1_1 = this.findFocus();
                v3_2 = v1_1 != null ? v0.infoForAnyChild(v1_1) : null;
                if(v3_2 != null && v3_2.b == v0.mCurItem) {
                    return;
                }

                for(v1 = 0; v1 < this.getChildCount(); ++v1) {
                    View v2_2 = v0.getChildAt(v1);
                    v3_2 = v0.infoForChild(v2_2);
                    if(v3_2 != null && v3_2.b == v0.mCurItem && (v2_2.requestFocus(2))) {
                        return;
                    }
                }
            }

            return;
        }

        try {
            v1_2 = this.getResources().getResourceName(this.getId());
        }
        catch(Resources$NotFoundException ) {
            v1_2 = Integer.toHexString(this.getId());
        }

        StringBuilder v3_3 = new StringBuilder();
        v3_3.append("The application\'s PagerAdapter changed the adapter\'s contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ");
        v3_3.append(v0.mExpectedAdapterCount);
        v3_3.append(", found: ");
        v3_3.append(v6);
        v3_3.append(" Pager id: ");
        v3_3.append(v1_2);
        v3_3.append(" Pager class: ");
        v3_3.append(this.getClass());
        v3_3.append(" Problematic adapter: ");
        v3_3.append(v0.mAdapter.getClass());
        throw new IllegalStateException(v3_3.toString());
    }

    private void recomputeScrollPosition(int arg2, int arg3, int arg4, int arg5) {
        if(arg3 <= 0 || (this.mItems.isEmpty())) {
            b v3 = this.infoForPosition(this.mCurItem);
            float v3_1 = v3 != null ? Math.min(v3.e, this.mLastOffset) : 0f;
            arg2 = ((int)(v3_1 * (((float)(arg2 - this.getPaddingLeft() - this.getPaddingRight())))));
            if(arg2 == this.getScrollX()) {
                return;
            }

            this.completeScroll(false);
        label_30:
            this.scrollTo(arg2, this.getScrollY());
        }
        else if(!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(this.getCurrentItem() * this.getClientWidth());
        }
        else {
            arg2 = ((int)((((float)this.getScrollX())) / (((float)(arg3 - this.getPaddingLeft() - this.getPaddingRight() + arg5))) * (((float)(arg2 - this.getPaddingLeft() - this.getPaddingRight() + arg4)))));
            goto label_30;
        }
    }

    private void removeNonDecorViews() {
        int v0;
        for(v0 = 0; v0 < this.getChildCount(); ++v0) {
            if(!this.getChildAt(v0).getLayoutParams().a) {
                this.removeViewAt(v0);
                --v0;
            }
        }
    }

    public void removeOnAdapterChangeListener(e arg2) {
        if(this.mAdapterChangeListeners != null) {
            this.mAdapterChangeListeners.remove(arg2);
        }
    }

    public void removeOnPageChangeListener(f arg2) {
        if(this.mOnPageChangeListeners != null) {
            this.mOnPageChangeListeners.remove(arg2);
        }
    }

    public void removeView(View arg2) {
        if(this.mInLayout) {
            this.removeViewInLayout(arg2);
        }
        else {
            super.removeView(arg2);
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean arg2) {
        ViewParent v0 = this.getParent();
        if(v0 != null) {
            v0.requestDisallowInterceptTouchEvent(arg2);
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        this.endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        boolean v0 = (this.mLeftEdge.isFinished()) || (this.mRightEdge.isFinished()) ? true : false;
        return v0;
    }

    private void scrollToItem(int arg6, boolean arg7, int arg8, boolean arg9) {
        b v0 = this.infoForPosition(arg6);
        int v0_1 = v0 != null ? ((int)((((float)this.getClientWidth())) * Math.max(this.mFirstOffset, Math.min(v0.e, this.mLastOffset)))) : 0;
        if(arg7) {
            this.smoothScrollTo(v0_1, 0, arg8);
            if(arg9) {
                this.dispatchOnPageSelected(arg6);
            }
        }
        else {
            if(arg9) {
                this.dispatchOnPageSelected(arg6);
            }

            this.completeScroll(false);
            this.scrollTo(v0_1, 0);
            this.pageScrolled(v0_1);
        }
    }

    public void setAdapter(q arg8) {
        DataSetObserver v1 = null;
        int v2 = 0;
        if(this.mAdapter != null) {
            this.mAdapter.setViewPagerObserver(v1);
            this.mAdapter.startUpdate(((ViewGroup)this));
            int v0;
            for(v0 = 0; v0 < this.mItems.size(); ++v0) {
                Object v3 = this.mItems.get(v0);
                this.mAdapter.destroyItem(((ViewGroup)this), ((b)v3).b, ((b)v3).a);
            }

            this.mAdapter.finishUpdate(((ViewGroup)this));
            this.mItems.clear();
            this.removeNonDecorViews();
            this.mCurItem = 0;
            this.scrollTo(0, 0);
        }

        q v0_1 = this.mAdapter;
        this.mAdapter = arg8;
        this.mExpectedAdapterCount = 0;
        if(this.mAdapter != null) {
            if(this.mObserver == null) {
                this.mObserver = new h(this);
            }

            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean v3_1 = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if(this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                this.setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = ((Parcelable)v1);
                this.mRestoredClassLoader = ((ClassLoader)v1);
                goto label_64;
            }

            if(!v3_1) {
                this.populate();
                goto label_64;
            }

            this.requestLayout();
        }

    label_64:
        if(this.mAdapterChangeListeners != null && !this.mAdapterChangeListeners.isEmpty()) {
            int v1_1 = this.mAdapterChangeListeners.size();
            while(v2 < v1_1) {
                this.mAdapterChangeListeners.get(v2).a(this, v0_1, arg8);
                ++v2;
            }
        }
    }

    public void setCurrentItem(int arg3) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(arg3, this.mFirstLayout ^ 1, false);
    }

    public void setCurrentItem(int arg2, boolean arg3) {
        this.mPopulatePending = false;
        this.setCurrentItemInternal(arg2, arg3, false);
    }

    void setCurrentItemInternal(int arg2, boolean arg3, boolean arg4) {
        this.setCurrentItemInternal(arg2, arg3, arg4, 0);
    }

    void setCurrentItemInternal(int arg4, boolean arg5, boolean arg6, int arg7) {
        if(this.mAdapter != null) {
            if(this.mAdapter.getCount() <= 0) {
            }
            else {
                if(!arg6 && this.mCurItem == arg4 && this.mItems.size() != 0) {
                    this.setScrollingCacheEnabled(false);
                    return;
                }

                arg6 = true;
                if(arg4 < 0) {
                    arg4 = 0;
                }
                else if(arg4 >= this.mAdapter.getCount()) {
                    arg4 = this.mAdapter.getCount() - 1;
                }

                int v0 = this.mOffscreenPageLimit;
                if(arg4 > this.mCurItem + v0 || arg4 < this.mCurItem - v0) {
                    for(v0 = 0; v0 < this.mItems.size(); ++v0) {
                        this.mItems.get(v0).c = true;
                    }
                }

                if(this.mCurItem != arg4) {
                }
                else {
                    arg6 = false;
                }

                if(this.mFirstLayout) {
                    this.mCurItem = arg4;
                    if(arg6) {
                        this.dispatchOnPageSelected(arg4);
                    }

                    this.requestLayout();
                }
                else {
                    this.populate(arg4);
                    this.scrollToItem(arg4, arg5, arg7, arg6);
                }

                return;
            }
        }

        this.setScrollingCacheEnabled(false);
    }

    f setInternalPageChangeListener(f arg2) {
        f v0 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = arg2;
        return v0;
    }

    public void setOffscreenPageLimit(int arg5) {
        if(arg5 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + arg5 + " too small; defaulting to " + 1);
        }

        if(1 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = 1;
            this.populate();
        }
    }

    @Deprecated public void setOnPageChangeListener(f arg1) {
        this.mOnPageChangeListener = arg1;
    }

    public void setPageMargin(int arg3) {
        int v0 = this.mPageMargin;
        this.mPageMargin = arg3;
        int v1 = this.getWidth();
        this.recomputeScrollPosition(v1, v1, arg3, v0);
        this.requestLayout();
    }

    public void setPageMarginDrawable(int arg2) {
        this.setPageMarginDrawable(android.support.v4.content.a.a(this.getContext(), arg2));
    }

    public void setPageMarginDrawable(Drawable arg1) {
        this.mMarginDrawable = arg1;
        if(arg1 != null) {
            this.refreshDrawableState();
        }

        boolean v1 = arg1 == null ? true : false;
        this.setWillNotDraw(v1);
        this.invalidate();
    }

    public void setPageTransformer(boolean arg2, g arg3) {
        this.setPageTransformer(arg2, arg3, 2);
    }

    public void setPageTransformer(boolean arg5, g arg6, int arg7) {
        int v1 = 1;
        boolean v2 = arg6 != null ? true : false;
        int v3 = this.mPageTransformer != null ? 1 : 0;
        v3 = v2 != (((boolean)v3)) ? 1 : 0;
        this.mPageTransformer = arg6;
        this.setChildrenDrawingOrderEnabled(v2);
        if(v2) {
            if(arg5) {
                v1 = 2;
            }

            this.mDrawingOrder = v1;
            this.mPageTransformerLayerType = arg7;
        }
        else {
            this.mDrawingOrder = 0;
        }

        if(v3 != 0) {
            this.populate();
        }
    }

    void setScrollState(int arg2) {
        if(this.mScrollState == arg2) {
            return;
        }

        this.mScrollState = arg2;
        if(this.mPageTransformer != null) {
            boolean v0 = arg2 != 0 ? true : false;
            this.enableLayers(v0);
        }

        this.dispatchOnScrollStateChanged(arg2);
    }

    private void setScrollingCacheEnabled(boolean arg2) {
        if(this.mScrollingCacheEnabled != arg2) {
            this.mScrollingCacheEnabled = arg2;
        }
    }

    void smoothScrollTo(int arg10, int arg11, int arg12) {
        if(this.getChildCount() == 0) {
            this.setScrollingCacheEnabled(false);
            return;
        }

        int v0 = this.mScroller == null || (this.mScroller.isFinished()) ? 0 : 1;
        if(v0 != 0) {
            v0 = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            this.setScrollingCacheEnabled(false);
        }
        else {
            v0 = this.getScrollX();
        }

        int v4 = v0;
        int v5 = this.getScrollY();
        int v6 = arg10 - v4;
        int v7 = arg11 - v5;
        if(v6 == 0 && v7 == 0) {
            this.completeScroll(false);
            this.populate();
            this.setScrollState(0);
            return;
        }

        this.setScrollingCacheEnabled(true);
        this.setScrollState(2);
        arg10 = this.getClientWidth();
        arg11 = arg10 / 2;
        float v2 = 1f;
        float v10 = ((float)arg10);
        float v11 = ((float)arg11);
        v11 += this.distanceInfluenceForSnapDuration(Math.min(v2, (((float)Math.abs(v6))) * v2 / v10)) * v11;
        arg12 = Math.abs(arg12);
        arg10 = arg12 > 0 ? Math.round(Math.abs(v11 / (((float)arg12))) * 1000f) * 4 : ((int)(((((float)Math.abs(v6))) / (v10 * this.mAdapter.getPageWidth(this.mCurItem) + (((float)this.mPageMargin))) + v2) * 100f));
        int v8 = Math.min(arg10, 600);
        this.mIsScrollStarted = false;
        this.mScroller.startScroll(v4, v5, v6, v7, v8);
        t.d(((View)this));
    }

    void smoothScrollTo(int arg2, int arg3) {
        this.smoothScrollTo(arg2, arg3, 0);
    }

    private void sortChildDrawingOrder() {
        if(this.mDrawingOrder != 0) {
            if(this.mDrawingOrderedChildren == null) {
                this.mDrawingOrderedChildren = new ArrayList();
            }
            else {
                this.mDrawingOrderedChildren.clear();
            }

            int v0 = this.getChildCount();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.mDrawingOrderedChildren.add(this.getChildAt(v1));
            }

            Collections.sort(this.mDrawingOrderedChildren, ViewPager.sPositionComparator);
        }
    }

    protected boolean verifyDrawable(Drawable arg2) {
        boolean v2 = (super.verifyDrawable(arg2)) || arg2 == this.mMarginDrawable ? true : false;
        return v2;
    }
}

