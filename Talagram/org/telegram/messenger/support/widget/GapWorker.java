package org.telegram.messenger.support.widget;

import android.support.v4.os.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker implements Runnable {
    final class org.telegram.messenger.support.widget.GapWorker$1 implements Comparator {
        org.telegram.messenger.support.widget.GapWorker$1() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Task)arg1), ((Task)arg2));
        }

        public int compare(Task arg6, Task arg7) {
            int v2 = 1;
            int v0 = arg6.view == null ? 1 : 0;
            int v3 = arg7.view == null ? 1 : 0;
            if(v0 != v3) {
                if(arg6.view == null) {
                }
                else {
                    v2 = -1;
                }

                return v2;
            }

            if(arg6.immediate != arg7.immediate) {
                if(arg6.immediate) {
                    v2 = -1;
                }

                return v2;
            }

            v0 = arg7.viewVelocity - arg6.viewVelocity;
            if(v0 != 0) {
                return v0;
            }

            int v6 = arg6.distanceToItem - arg7.distanceToItem;
            if(v6 != 0) {
                return v6;
            }

            return 0;
        }
    }

    class LayoutPrefetchRegistryImpl implements LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        LayoutPrefetchRegistryImpl() {
            super();
        }

        public void addPosition(int arg6, int arg7) {
            if(arg6 >= 0) {
                if(arg7 >= 0) {
                    int v0 = this.mCount * 2;
                    if(this.mPrefetchArray == null) {
                        this.mPrefetchArray = new int[4];
                        Arrays.fill(this.mPrefetchArray, -1);
                    }
                    else if(v0 >= this.mPrefetchArray.length) {
                        int[] v1 = this.mPrefetchArray;
                        this.mPrefetchArray = new int[v0 * 2];
                        System.arraycopy(v1, 0, this.mPrefetchArray, 0, v1.length);
                    }

                    this.mPrefetchArray[v0] = arg6;
                    this.mPrefetchArray[v0 + 1] = arg7;
                    ++this.mCount;
                    return;
                }

                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }

            throw new IllegalArgumentException("Layout positions must be non-negative");
        }

        void clearPrefetchPositions() {
            if(this.mPrefetchArray != null) {
                Arrays.fill(this.mPrefetchArray, -1);
            }

            this.mCount = 0;
        }

        void collectPrefetchPositionsFromView(RecyclerView arg5, boolean arg6) {
            this.mCount = 0;
            if(this.mPrefetchArray != null) {
                Arrays.fill(this.mPrefetchArray, -1);
            }

            LayoutManager v0 = arg5.mLayout;
            if(arg5.mAdapter != null && v0 != null && (v0.isItemPrefetchEnabled())) {
                if(arg6) {
                    if(!arg5.mAdapterHelper.hasPendingUpdates()) {
                        v0.collectInitialPrefetchPositions(arg5.mAdapter.getItemCount(), ((LayoutPrefetchRegistry)this));
                    }
                }
                else if(!arg5.hasPendingAdapterUpdates()) {
                    v0.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, arg5.mState, ((LayoutPrefetchRegistry)this));
                }

                if(this.mCount <= v0.mPrefetchMaxCountObserved) {
                    return;
                }

                v0.mPrefetchMaxCountObserved = this.mCount;
                v0.mPrefetchMaxObservedInInitialPrefetch = arg6;
                arg5.mRecycler.updateViewCacheSize();
            }
        }

        boolean lastPrefetchIncludedPosition(int arg5) {
            if(this.mPrefetchArray != null) {
                int v0 = this.mCount * 2;
                int v2 = 0;
                while(v2 < v0) {
                    if(this.mPrefetchArray[v2] == arg5) {
                        return 1;
                    }
                    else {
                        v2 += 2;
                        continue;
                    }

                    return 0;
                }
            }

            return 0;
        }

        void setPrefetchVector(int arg1, int arg2) {
            this.mPrefetchDx = arg1;
            this.mPrefetchDy = arg2;
        }
    }

    class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task() {
            super();
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList mRecyclerViews;
    private ArrayList mTasks;
    static final ThreadLocal sGapWorker;
    static Comparator sTaskComparator;

    static {
        GapWorker.sGapWorker = new ThreadLocal();
        GapWorker.sTaskComparator = new org.telegram.messenger.support.widget.GapWorker$1();
    }

    GapWorker() {
        super();
        this.mRecyclerViews = new ArrayList();
        this.mTasks = new ArrayList();
    }

    public void add(RecyclerView arg2) {
        this.mRecyclerViews.add(arg2);
    }

    private void buildTaskList() {
        Task v8;
        Object v4;
        int v0 = this.mRecyclerViews.size();
        int v2 = 0;
        int v3 = 0;
        while(v2 < v0) {
            v4 = this.mRecyclerViews.get(v2);
            if(((RecyclerView)v4).getWindowVisibility() == 0) {
                ((RecyclerView)v4).mPrefetchRegistry.collectPrefetchPositionsFromView(((RecyclerView)v4), false);
                v3 += ((RecyclerView)v4).mPrefetchRegistry.mCount;
            }

            ++v2;
        }

        this.mTasks.ensureCapacity(v3);
        v2 = 0;
        v3 = 0;
        while(v2 < v0) {
            v4 = this.mRecyclerViews.get(v2);
            if(((RecyclerView)v4).getWindowVisibility() != 0) {
            }
            else {
                LayoutPrefetchRegistryImpl v5 = ((RecyclerView)v4).mPrefetchRegistry;
                int v6 = Math.abs(v5.mPrefetchDx) + Math.abs(v5.mPrefetchDy);
                int v7 = v3;
                for(v3 = 0; v3 < v5.mCount * 2; v3 += 2) {
                    if(v7 >= this.mTasks.size()) {
                        v8 = new Task();
                        this.mTasks.add(v8);
                    }
                    else {
                        Object v8_1 = this.mTasks.get(v7);
                    }

                    int v9 = v5.mPrefetchArray[v3 + 1];
                    boolean v10 = v9 <= v6 ? true : false;
                    v8.immediate = v10;
                    v8.viewVelocity = v6;
                    v8.distanceToItem = v9;
                    v8.view = ((RecyclerView)v4);
                    v8.position = v5.mPrefetchArray[v3];
                    ++v7;
                }

                v3 = v7;
            }

            ++v2;
        }

        Collections.sort(this.mTasks, GapWorker.sTaskComparator);
    }

    private void flushTaskWithDeadline(Task arg4, long arg5) {
        long v0 = arg4.immediate ? 9223372036854775807L : arg5;
        ViewHolder v4 = this.prefetchPositionWithDeadline(arg4.view, arg4.position, v0);
        if(v4 != null && v4.mNestedRecyclerView != null && (v4.isBound()) && !v4.isInvalid()) {
            this.prefetchInnerRecyclerViewWithDeadline(v4.mNestedRecyclerView.get(), arg5);
        }
    }

    private void flushTasksWithDeadline(long arg4) {
        int v0 = 0;
        while(v0 < this.mTasks.size()) {
            Object v1 = this.mTasks.get(v0);
            if(((Task)v1).view == null) {
            }
            else {
                this.flushTaskWithDeadline(((Task)v1), arg4);
                ((Task)v1).clear();
                ++v0;
                continue;
            }

            return;
        }
    }

    static boolean isPrefetchPositionAttached(RecyclerView arg5, int arg6) {
        int v0 = arg5.mChildHelper.getUnfilteredChildCount();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            ViewHolder v3 = RecyclerView.getChildViewHolderInt(arg5.mChildHelper.getUnfilteredChildAt(v2));
            if(v3.mPosition == arg6 && !v3.isInvalid()) {
                return 1;
            }
        }

        return 0;
    }

    void postFromTraversal(RecyclerView arg6, int arg7, int arg8) {
        if((arg6.isAttachedToWindow()) && this.mPostTimeNs == 0) {
            this.mPostTimeNs = arg6.getNanoTime();
            arg6.post(((Runnable)this));
        }

        arg6.mPrefetchRegistry.setPrefetchVector(arg7, arg8);
    }

    void prefetch(long arg1) {
        this.buildTaskList();
        this.flushTasksWithDeadline(arg1);
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView arg4, long arg5) {
        if(arg4 == null) {
            return;
        }

        if((arg4.mDataSetHasChangedAfterLayout) && arg4.mChildHelper.getUnfilteredChildCount() != 0) {
            arg4.removeAndRecycleViews();
        }

        LayoutPrefetchRegistryImpl v0 = arg4.mPrefetchRegistry;
        v0.collectPrefetchPositionsFromView(arg4, true);
        if(v0.mCount != 0) {
            try {
                d.a("RV Nested Prefetch");
                arg4.mState.prepareForNestedPrefetch(arg4.mAdapter);
                int v1;
                for(v1 = 0; v1 < v0.mCount * 2; v1 += 2) {
                    this.prefetchPositionWithDeadline(arg4, v0.mPrefetchArray[v1], arg5);
                }
            }
            catch(Throwable v4) {
                goto label_30;
            }

            d.a();
            return;
        label_30:
            d.a();
            throw v4;
        }
    }

    private ViewHolder prefetchPositionWithDeadline(RecyclerView arg3, int arg4, long arg5) {
        ViewHolder v4_1;
        if(GapWorker.isPrefetchPositionAttached(arg3, arg4)) {
            return null;
        }

        Recycler v0 = arg3.mRecycler;
        try {
            arg3.onEnterLayoutOrScroll();
            v4_1 = v0.tryGetViewHolderForPositionByDeadline(arg4, false, arg5);
            if(v4_1 != null) {
                if((v4_1.isBound()) && !v4_1.isInvalid()) {
                    v0.recycleView(v4_1.itemView);
                    goto label_17;
                }

                v0.addViewHolderToRecycledViewPool(v4_1, false);
            }
        }
        catch(Throwable v4) {
            arg3.onExitLayoutOrScroll(false);
            throw v4;
        }

    label_17:
        arg3.onExitLayoutOrScroll(false);
        return v4_1;
    }

    public void remove(RecyclerView arg2) {
        this.mRecyclerViews.remove(arg2);
    }

    public void run() {
        long v0 = 0;
        try {
            d.a("RV Prefetch");
            if(!this.mRecyclerViews.isEmpty()) {
                int v2_1 = this.mRecyclerViews.size();
                int v3 = 0;
                long v4 = v0;
                while(v3 < v2_1) {
                    Object v6 = this.mRecyclerViews.get(v3);
                    if(((RecyclerView)v6).getWindowVisibility() == 0) {
                        v4 = Math.max(((RecyclerView)v6).getDrawingTime(), v4);
                    }

                    ++v3;
                }

                if(v4 == v0) {
                    goto label_6;
                }

                this.prefetch(TimeUnit.MILLISECONDS.toNanos(v4) + this.mFrameIntervalNs);
                goto label_30;
            }

            goto label_6;
        }
        catch(Throwable v2) {
            goto label_34;
        }

    label_30:
        this.mPostTimeNs = v0;
        d.a();
        return;
    label_6:
        this.mPostTimeNs = v0;
        d.a();
        return;
    label_34:
        this.mPostTimeNs = v0;
        d.a();
        throw v2;
    }
}

