package org.telegram.messenger.support.util;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil {
    class org.telegram.messenger.support.util.AsyncListUtil$1 implements MainThreadCallback {
        org.telegram.messenger.support.util.AsyncListUtil$1(AsyncListUtil arg1) {
            AsyncListUtil.this = arg1;
            super();
        }

        public void addTile(int arg4, Tile arg5) {
            if(!this.isRequestedGeneration(arg4)) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(arg5);
                return;
            }

            Tile v4 = AsyncListUtil.this.mTileList.addOrReplace(arg5);
            if(v4 != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + v4.mStartPosition);
                AsyncListUtil.this.mBackgroundProxy.recycleTile(v4);
            }

            arg4 = arg5.mStartPosition + arg5.mItemCount;
            int v0;
            for(v0 = 0; v0 < AsyncListUtil.this.mMissingPositions.size(); ++v0) {
                int v1_1 = AsyncListUtil.this.mMissingPositions.keyAt(v0);
                if(arg5.mStartPosition <= v1_1 && v1_1 < arg4) {
                    AsyncListUtil.this.mMissingPositions.removeAt(v0);
                    AsyncListUtil.this.mViewCallback.onItemLoaded(v1_1);
                    continue;
                }
            }
        }

        private boolean isRequestedGeneration(int arg2) {
            boolean v2 = arg2 == AsyncListUtil.this.mRequestedGeneration ? true : false;
            return v2;
        }

        private void recycleAllTiles() {
            int v0;
            for(v0 = 0; v0 < AsyncListUtil.this.mTileList.size(); ++v0) {
                AsyncListUtil.this.mBackgroundProxy.recycleTile(AsyncListUtil.this.mTileList.getAtIndex(v0));
            }

            AsyncListUtil.this.mTileList.clear();
        }

        public void removeTile(int arg3, int arg4) {
            if(!this.isRequestedGeneration(arg3)) {
                return;
            }

            Tile v3 = AsyncListUtil.this.mTileList.removeAtPos(arg4);
            if(v3 == null) {
                Log.e("AsyncListUtil", "tile not found @" + arg4);
                return;
            }

            AsyncListUtil.this.mBackgroundProxy.recycleTile(v3);
        }

        public void updateItemCount(int arg1, int arg2) {
            if(!this.isRequestedGeneration(arg1)) {
                return;
            }

            AsyncListUtil.this.mItemCount = arg2;
            AsyncListUtil.this.mViewCallback.onDataRefresh();
            AsyncListUtil.this.mDisplayedGeneration = AsyncListUtil.this.mRequestedGeneration;
            this.recycleAllTiles();
            AsyncListUtil.this.mAllowScrollHints = false;
            AsyncListUtil.this.updateRange();
        }
    }

    class org.telegram.messenger.support.util.AsyncListUtil$2 implements BackgroundCallback {
        private int mFirstRequiredTileStart;
        private int mGeneration;
        private int mItemCount;
        private int mLastRequiredTileStart;
        final SparseBooleanArray mLoadedTiles;
        private Tile mRecycledRoot;

        org.telegram.messenger.support.util.AsyncListUtil$2(AsyncListUtil arg1) {
            AsyncListUtil.this = arg1;
            super();
            this.mLoadedTiles = new SparseBooleanArray();
        }

        private Tile acquireTile() {
            if(this.mRecycledRoot != null) {
                Tile v0 = this.mRecycledRoot;
                this.mRecycledRoot = this.mRecycledRoot.mNext;
                return v0;
            }

            return new Tile(AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
        }

        private void addTile(Tile arg4) {
            this.mLoadedTiles.put(arg4.mStartPosition, true);
            AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, arg4);
        }

        private void flushTileCache(int arg8) {
            int v0 = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
            while(this.mLoadedTiles.size() >= v0) {
                int v1 = this.mLoadedTiles.keyAt(0);
                int v2 = this.mLoadedTiles.keyAt(this.mLoadedTiles.size() - 1);
                int v3 = this.mFirstRequiredTileStart - v1;
                int v5 = v2 - this.mLastRequiredTileStart;
                if(v3 > 0 && (v3 >= v5 || arg8 == 2)) {
                    this.removeTile(v1);
                    continue;
                }

                if(v5 <= 0) {
                    return;
                }

                if(v3 >= v5 && arg8 != 1) {
                    return;
                }

                this.removeTile(v2);
            }
        }

        private int getTileStart(int arg2) {
            return arg2 - arg2 % AsyncListUtil.this.mTileSize;
        }

        private boolean isTileLoaded(int arg2) {
            return this.mLoadedTiles.get(arg2);
        }

        public void loadTile(int arg5, int arg6) {
            if(this.isTileLoaded(arg5)) {
                return;
            }

            Tile v0 = this.acquireTile();
            v0.mStartPosition = arg5;
            v0.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - v0.mStartPosition);
            AsyncListUtil.this.mDataCallback.fillData(v0.mItems, v0.mStartPosition, v0.mItemCount);
            this.flushTileCache(arg6);
            this.addTile(v0);
        }

        private void log(String arg4, Object[] arg5) {
            Log.d("AsyncListUtil", "[BKGR] " + String.format(arg4, arg5));
        }

        public void recycleTile(Tile arg4) {
            AsyncListUtil.this.mDataCallback.recycleData(arg4.mItems, arg4.mItemCount);
            arg4.mNext = this.mRecycledRoot;
            this.mRecycledRoot = arg4;
        }

        public void refresh(int arg3) {
            this.mGeneration = arg3;
            this.mLoadedTiles.clear();
            this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
            AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
        }

        private void removeTile(int arg3) {
            this.mLoadedTiles.delete(arg3);
            AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, arg3);
        }

        private void requestTiles(int arg4, int arg5, int arg6, boolean arg7) {
            int v0;
            for(v0 = arg4; v0 <= arg5; v0 += AsyncListUtil.this.mTileSize) {
                int v1 = arg7 ? arg5 + arg4 - v0 : v0;
                AsyncListUtil.this.mBackgroundProxy.loadTile(v1, arg6);
            }
        }

        public void updateRange(int arg1, int arg2, int arg3, int arg4, int arg5) {
            if(arg1 > arg2) {
                return;
            }

            arg1 = this.getTileStart(arg1);
            arg2 = this.getTileStart(arg2);
            this.mFirstRequiredTileStart = this.getTileStart(arg3);
            this.mLastRequiredTileStart = this.getTileStart(arg4);
            if(arg5 == 1) {
                this.requestTiles(this.mFirstRequiredTileStart, arg2, arg5, true);
                this.requestTiles(arg2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, arg5, false);
            }
            else {
                this.requestTiles(arg1, this.mLastRequiredTileStart, arg5, false);
                this.requestTiles(this.mFirstRequiredTileStart, arg1 - AsyncListUtil.this.mTileSize, arg5, true);
            }
        }
    }

    public abstract class DataCallback {
        public DataCallback() {
            super();
        }

        public abstract void fillData(Object[] arg1, int arg2, int arg3);

        public int getMaxCachedTiles() {
            return 10;
        }

        public void recycleData(Object[] arg1, int arg2) {
        }

        public abstract int refreshData();
    }

    public abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE;

        public ViewCallback() {
            super();
        }

        public void extendRangeInto(int[] arg7, int[] arg8, int arg9) {
            int v1 = arg7[1] - arg7[0] + 1;
            int v3 = v1 / 2;
            int v4 = arg7[0];
            int v5 = arg9 == 1 ? v1 : v3;
            arg8[0] = v4 - v5;
            int v7 = arg7[1];
            if(arg9 == 2) {
            }
            else {
                v1 = v3;
            }

            arg8[1] = v7 + v1;
        }

        public abstract void getItemRangeInto(int[] arg1);

        public abstract void onDataRefresh();

        public abstract void onItemLoaded(int arg1);
    }

    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    private final BackgroundCallback mBackgroundCallback;
    final BackgroundCallback mBackgroundProxy;
    final DataCallback mDataCallback;
    int mDisplayedGeneration;
    int mItemCount;
    private final MainThreadCallback mMainThreadCallback;
    final MainThreadCallback mMainThreadProxy;
    final SparseIntArray mMissingPositions;
    final int[] mPrevRange;
    int mRequestedGeneration;
    private int mScrollHint;
    final Class mTClass;
    final TileList mTileList;
    final int mTileSize;
    final int[] mTmpRange;
    final int[] mTmpRangeExtended;
    final ViewCallback mViewCallback;

    public AsyncListUtil(Class arg3, int arg4, DataCallback arg5, ViewCallback arg6) {
        super();
        this.mTmpRange = new int[2];
        this.mPrevRange = new int[2];
        this.mTmpRangeExtended = new int[2];
        this.mScrollHint = 0;
        this.mItemCount = 0;
        this.mDisplayedGeneration = 0;
        this.mRequestedGeneration = this.mDisplayedGeneration;
        this.mMissingPositions = new SparseIntArray();
        this.mMainThreadCallback = new org.telegram.messenger.support.util.AsyncListUtil$1(this);
        this.mBackgroundCallback = new org.telegram.messenger.support.util.AsyncListUtil$2(this);
        this.mTClass = arg3;
        this.mTileSize = arg4;
        this.mDataCallback = arg5;
        this.mViewCallback = arg6;
        this.mTileList = new TileList(this.mTileSize);
        MessageThreadUtil v3 = new MessageThreadUtil();
        this.mMainThreadProxy = ((ThreadUtil)v3).getMainThreadProxy(this.mMainThreadCallback);
        this.mBackgroundProxy = ((ThreadUtil)v3).getBackgroundProxy(this.mBackgroundCallback);
        this.refresh();
    }

    public Object getItem(int arg4) {
        if(arg4 >= 0 && arg4 < this.mItemCount) {
            Object v0 = this.mTileList.getItemAt(arg4);
            if(v0 == null && !this.isRefreshPending()) {
                this.mMissingPositions.put(arg4, 0);
            }

            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append(arg4);
        v1.append(" is not within 0 and ");
        v1.append(this.mItemCount);
        throw new IndexOutOfBoundsException(v1.toString());
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    private boolean isRefreshPending() {
        boolean v0 = this.mRequestedGeneration != this.mDisplayedGeneration ? true : false;
        return v0;
    }

    void log(String arg4, Object[] arg5) {
        Log.d("AsyncListUtil", "[MAIN] " + String.format(arg4, arg5));
    }

    public void onRangeChanged() {
        if(this.isRefreshPending()) {
            return;
        }

        this.updateRange();
        this.mAllowScrollHints = true;
    }

    public void refresh() {
        this.mMissingPositions.clear();
        BackgroundCallback v0 = this.mBackgroundProxy;
        int v1 = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = v1;
        v0.refresh(v1);
    }

    void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        if(this.mTmpRange[0] <= this.mTmpRange[1]) {
            if(this.mTmpRange[0] < 0) {
            }
            else if(this.mTmpRange[1] >= this.mItemCount) {
                return;
            }
            else {
                if(!this.mAllowScrollHints || this.mTmpRange[0] > this.mPrevRange[1] || this.mPrevRange[0] > this.mTmpRange[1]) {
                    this.mScrollHint = 0;
                }
                else if(this.mTmpRange[0] < this.mPrevRange[0]) {
                    this.mScrollHint = 1;
                }
                else if(this.mTmpRange[0] > this.mPrevRange[0]) {
                    this.mScrollHint = 2;
                }

                this.mPrevRange[0] = this.mTmpRange[0];
                this.mPrevRange[1] = this.mTmpRange[1];
                this.mViewCallback.extendRangeInto(this.mTmpRange, this.mTmpRangeExtended, this.mScrollHint);
                this.mTmpRangeExtended[0] = Math.min(this.mTmpRange[0], Math.max(this.mTmpRangeExtended[0], 0));
                this.mTmpRangeExtended[1] = Math.max(this.mTmpRange[1], Math.min(this.mTmpRangeExtended[1], this.mItemCount - 1));
                this.mBackgroundProxy.updateRange(this.mTmpRange[0], this.mTmpRange[1], this.mTmpRangeExtended[0], this.mTmpRangeExtended[1], this.mScrollHint);
            }
        }
    }
}

