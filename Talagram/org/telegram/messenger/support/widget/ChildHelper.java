package org.telegram.messenger.support.widget;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ChildHelper {
    class Bucket {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = -9223372036854775808L;
        long mData;
        Bucket mNext;

        Bucket() {
            super();
            this.mData = 0;
        }

        void clear(int arg7) {
            int v0 = 64;
            if(arg7 < v0) {
                this.mData &= 1 << arg7 ^ -1;
            }
            else if(this.mNext != null) {
                this.mNext.clear(arg7 - v0);
            }
        }

        int countOnesBefore(int arg7) {
            int v1 = 64;
            long v2 = 1;
            if(this.mNext == null) {
                if(arg7 >= v1) {
                    return Long.bitCount(this.mData);
                }

                return Long.bitCount(this.mData & (v2 << arg7) - v2);
            }

            if(arg7 < v1) {
                return Long.bitCount(this.mData & (v2 << arg7) - v2);
            }

            return this.mNext.countOnesBefore(arg7 - v1) + Long.bitCount(this.mData);
        }

        private void ensureNext() {
            if(this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        boolean get(int arg5) {
            int v0 = 64;
            if(arg5 >= v0) {
                this.ensureNext();
                return this.mNext.get(arg5 - v0);
            }

            boolean v5 = (this.mData & 1 << arg5) != 0 ? true : false;
            return v5;
        }

        void insert(int arg12, boolean arg13) {
            // Method was not decompiled
        }

        boolean remove(int arg13) {
            // Method was not decompiled
        }

        void reset() {
            this.mData = 0;
            if(this.mNext != null) {
                this.mNext.reset();
            }
        }

        void set(int arg5) {
            int v0 = 64;
            if(arg5 >= v0) {
                this.ensureNext();
                this.mNext.set(arg5 - v0);
            }
            else {
                this.mData |= 1 << arg5;
            }
        }

        public String toString() {
            String v0 = this.mNext == null ? Long.toBinaryString(this.mData) : this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
            return v0;
        }
    }

    interface Callback {
        void addView(View arg1, int arg2);

        void attachViewToParent(View arg1, int arg2, ViewGroup$LayoutParams arg3);

        void detachViewFromParent(int arg1);

        View getChildAt(int arg1);

        int getChildCount();

        ViewHolder getChildViewHolder(View arg1);

        int indexOfChild(View arg1);

        void onEnteredHiddenState(View arg1);

        void onLeftHiddenState(View arg1);

        void removeAllViews();

        void removeViewAt(int arg1);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket;
    final Callback mCallback;
    final List mHiddenViews;

    ChildHelper(Callback arg1) {
        super();
        this.mCallback = arg1;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList();
    }

    void addView(View arg2, int arg3, boolean arg4) {
        arg3 = arg3 < 0 ? this.mCallback.getChildCount() : this.getOffset(arg3);
        this.mBucket.insert(arg3, arg4);
        if(arg4) {
            this.hideViewInternal(arg2);
        }

        this.mCallback.addView(arg2, arg3);
    }

    void addView(View arg2, boolean arg3) {
        this.addView(arg2, -1, arg3);
    }

    void attachViewToParent(View arg2, int arg3, ViewGroup$LayoutParams arg4, boolean arg5) {
        arg3 = arg3 < 0 ? this.mCallback.getChildCount() : this.getOffset(arg3);
        this.mBucket.insert(arg3, arg5);
        if(arg5) {
            this.hideViewInternal(arg2);
        }

        this.mCallback.attachViewToParent(arg2, arg3, arg4);
    }

    void detachViewFromParent(int arg2) {
        arg2 = this.getOffset(arg2);
        this.mBucket.remove(arg2);
        this.mCallback.detachViewFromParent(arg2);
    }

    View findHiddenNonRemovedView(int arg6) {
        int v0 = this.mHiddenViews.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.mHiddenViews.get(v1);
            ViewHolder v3 = this.mCallback.getChildViewHolder(((View)v2));
            if(v3.getLayoutPosition() == arg6 && !v3.isInvalid() && !v3.isRemoved()) {
                return ((View)v2);
            }
        }

        return null;
    }

    View getChildAt(int arg2) {
        return this.mCallback.getChildAt(this.getOffset(arg2));
    }

    int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    public View getHiddenChildAt(int arg2) {
        if(arg2 >= 0) {
            if(arg2 >= this.mHiddenViews.size()) {
            }
            else {
                return this.mHiddenViews.get(arg2);
            }
        }

        return null;
    }

    protected int getHiddenChildCount() {
        return this.mHiddenViews.size();
    }

    private int getOffset(int arg5) {
        int v0 = -1;
        if(arg5 < 0) {
            return v0;
        }

        int v1 = this.mCallback.getChildCount();
        int v2 = arg5;
        while(v2 < v1) {
            int v3 = arg5 - (v2 - this.mBucket.countOnesBefore(v2));
            if(v3 != 0) {
                v2 += v3;
                continue;
            }

            goto label_12;
        }

        return v0;
    label_12:
        while(this.mBucket.get(v2)) {
            ++v2;
        }

        return v2;
    }

    View getUnfilteredChildAt(int arg2) {
        return this.mCallback.getChildAt(arg2);
    }

    int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    void hide(View arg4) {
        int v0 = this.mCallback.indexOfChild(arg4);
        if(v0 >= 0) {
            this.mBucket.set(v0);
            this.hideViewInternal(arg4);
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("view is not a child, cannot hide ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    private void hideViewInternal(View arg2) {
        this.mHiddenViews.add(arg2);
        this.mCallback.onEnteredHiddenState(arg2);
    }

    int indexOfChild(View arg3) {
        int v3 = this.mCallback.indexOfChild(arg3);
        int v0 = -1;
        if(v3 == v0) {
            return v0;
        }

        if(this.mBucket.get(v3)) {
            return v0;
        }

        return v3 - this.mBucket.countOnesBefore(v3);
    }

    boolean isHidden(View arg2) {
        return this.mHiddenViews.contains(arg2);
    }

    void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        int v0;
        for(v0 = this.mHiddenViews.size() - 1; v0 >= 0; --v0) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(v0));
            this.mHiddenViews.remove(v0);
        }

        this.mCallback.removeAllViews();
    }

    void removeView(View arg3) {
        int v0 = this.mCallback.indexOfChild(arg3);
        if(v0 < 0) {
            return;
        }

        if(this.mBucket.remove(v0)) {
            this.unhideViewInternal(arg3);
        }

        this.mCallback.removeViewAt(v0);
    }

    void removeViewAt(int arg3) {
        arg3 = this.getOffset(arg3);
        View v0 = this.mCallback.getChildAt(arg3);
        if(v0 == null) {
            return;
        }

        if(this.mBucket.remove(arg3)) {
            this.unhideViewInternal(v0);
        }

        this.mCallback.removeViewAt(arg3);
    }

    boolean removeViewIfHidden(View arg4) {
        int v0 = this.mCallback.indexOfChild(arg4);
        if(v0 == -1) {
            this.unhideViewInternal(arg4);
            return 1;
        }

        if(this.mBucket.get(v0)) {
            this.mBucket.remove(v0);
            this.unhideViewInternal(arg4);
            this.mCallback.removeViewAt(v0);
            return 1;
        }

        return 0;
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    void unhide(View arg4) {
        StringBuilder v1;
        int v0 = this.mCallback.indexOfChild(arg4);
        if(v0 >= 0) {
            if(this.mBucket.get(v0)) {
                this.mBucket.clear(v0);
                this.unhideViewInternal(arg4);
                return;
            }

            v1 = new StringBuilder();
            v1.append("trying to unhide a view that was not hidden");
            v1.append(arg4);
            throw new RuntimeException(v1.toString());
        }

        v1 = new StringBuilder();
        v1.append("view is not a child, cannot hide ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    private boolean unhideViewInternal(View arg2) {
        if(this.mHiddenViews.remove(arg2)) {
            this.mCallback.onLeftHiddenState(arg2);
            return 1;
        }

        return 0;
    }
}

