package org.telegram.messenger.support.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList {
    public class Tile {
        public int mItemCount;
        public final Object[] mItems;
        Tile mNext;
        public int mStartPosition;

        public Tile(Class arg1, int arg2) {
            super();
            this.mItems = Array.newInstance(arg1, arg2);
        }

        boolean containsPosition(int arg3) {
            boolean v3 = this.mStartPosition > arg3 || arg3 >= this.mStartPosition + this.mItemCount ? false : true;
            return v3;
        }

        Object getByPosition(int arg3) {
            return this.mItems[arg3 - this.mStartPosition];
        }
    }

    Tile mLastAccessedTile;
    final int mTileSize;
    private final SparseArray mTiles;

    public TileList(int arg3) {
        super();
        this.mTiles = new SparseArray(10);
        this.mTileSize = arg3;
    }

    public Tile addOrReplace(Tile arg4) {
        int v0 = this.mTiles.indexOfKey(arg4.mStartPosition);
        if(v0 < 0) {
            this.mTiles.put(arg4.mStartPosition, arg4);
            return null;
        }

        Object v1 = this.mTiles.valueAt(v0);
        this.mTiles.setValueAt(v0, arg4);
        if(this.mLastAccessedTile == v1) {
            this.mLastAccessedTile = arg4;
        }

        return ((Tile)v1);
    }

    public void clear() {
        this.mTiles.clear();
    }

    public Tile getAtIndex(int arg2) {
        return this.mTiles.valueAt(arg2);
    }

    public Object getItemAt(int arg3) {
        if(this.mLastAccessedTile == null || !this.mLastAccessedTile.containsPosition(arg3)) {
            int v0 = this.mTiles.indexOfKey(arg3 - arg3 % this.mTileSize);
            if(v0 < 0) {
                return null;
            }
            else {
                this.mLastAccessedTile = this.mTiles.valueAt(v0);
            }
        }

        return this.mLastAccessedTile.getByPosition(arg3);
    }

    public Tile removeAtPos(int arg3) {
        Object v0 = this.mTiles.get(arg3);
        if(this.mLastAccessedTile == v0) {
            this.mLastAccessedTile = null;
        }

        this.mTiles.delete(arg3);
        return ((Tile)v0);
    }

    public int size() {
        return this.mTiles.size();
    }
}

