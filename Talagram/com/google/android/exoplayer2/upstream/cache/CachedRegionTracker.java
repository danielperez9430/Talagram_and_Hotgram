package com.google.android.exoplayer2.upstream.cache;

import android.util.Log;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public final class CachedRegionTracker implements Listener {
    class Region implements Comparable {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long arg1, long arg3) {
            super();
            this.startOffset = arg1;
            this.endOffset = arg3;
        }

        public int compareTo(Region arg6) {
            int v6;
            if(this.startOffset < arg6.startOffset) {
                v6 = -1;
            }
            else if(this.startOffset == arg6.startOffset) {
                v6 = 0;
            }
            else {
                v6 = 1;
            }

            return v6;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((Region)arg1));
        }
    }

    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final Region lookupRegion;
    private final TreeSet regions;

    public CachedRegionTracker(Cache arg3, String arg4, ChunkIndex arg5) {
        super();
        this.cache = arg3;
        this.cacheKey = arg4;
        this.chunkIndex = arg5;
        this.regions = new TreeSet();
        this.lookupRegion = new Region(0, 0);
        __monitor_enter(this);
        try {
            Iterator v3_1 = arg3.addListener(arg4, ((Listener)this)).descendingIterator();
            while(v3_1.hasNext()) {
                this.mergeSpan(v3_1.next());
            }

            __monitor_exit(this);
            return;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            goto label_22;
        }

        throw v3;
    }

    public int getRegionEndTimeMs(long arg8) {
        int v8_1;
        int v1;
        Object v0;
        __monitor_enter(this);
        try {
            this.lookupRegion.startOffset = arg8;
            v0 = this.regions.floor(this.lookupRegion);
            v1 = -1;
            if(v0 != null && arg8 <= ((Region)v0).endOffset) {
                if(((Region)v0).endOffsetIndex == v1) {
                }
                else {
                    v8_1 = ((Region)v0).endOffsetIndex;
                    if(v8_1 != this.chunkIndex.length - 1) {
                        goto label_31;
                    }
                    else if(((Region)v0).endOffset == this.chunkIndex.offsets[v8_1] + (((long)this.chunkIndex.sizes[v8_1]))) {
                        goto label_28;
                    }
                    else {
                        goto label_31;
                    }
                }
            }

            goto label_55;
        }
        catch(Throwable v8) {
            goto label_58;
        }

    label_28:
        v8_1 = -2;
        goto label_29;
        try {
        label_31:
            v8_1 = ((int)((this.chunkIndex.timesUs[v8_1] + this.chunkIndex.durationsUs[v8_1] * (((Region)v0).endOffset - this.chunkIndex.offsets[v8_1]) / (((long)this.chunkIndex.sizes[v8_1]))) / 1000));
        }
        catch(Throwable v8) {
        label_58:
            __monitor_exit(this);
            throw v8;
        }

    label_29:
        __monitor_exit(this);
        return v8_1;
    label_55:
        __monitor_exit(this);
        return v1;
    }

    private void mergeSpan(CacheSpan arg8) {
        Region v0 = new Region(arg8.position, arg8.position + arg8.length);
        Object v8 = this.regions.floor(v0);
        Object v1 = this.regions.ceiling(v0);
        boolean v2 = this.regionsConnect(((Region)v8), v0);
        if(this.regionsConnect(v0, ((Region)v1))) {
            if(v2) {
                ((Region)v8).endOffset = ((Region)v1).endOffset;
                ((Region)v8).endOffsetIndex = ((Region)v1).endOffsetIndex;
            }
            else {
                v0.endOffset = ((Region)v1).endOffset;
                v0.endOffsetIndex = ((Region)v1).endOffsetIndex;
                this.regions.add(v0);
            }

            this.regions.remove(v1);
        }
        else {
            if(v2) {
                ((Region)v8).endOffset = v0.endOffset;
                int v0_1;
                for(v0_1 = ((Region)v8).endOffsetIndex; v0_1 < this.chunkIndex.length - 1; v0_1 = v2_1) {
                    int v2_1 = v0_1 + 1;
                    if(this.chunkIndex.offsets[v2_1] > ((Region)v8).endOffset) {
                        break;
                    }
                }

                ((Region)v8).endOffsetIndex = v0_1;
                return;
            }

            int v8_1 = Arrays.binarySearch(this.chunkIndex.offsets, v0.endOffset);
            if(v8_1 < 0) {
                v8_1 = -v8_1 - 2;
            }

            v0.endOffsetIndex = v8_1;
            this.regions.add(v0);
        }
    }

    public void onSpanAdded(Cache arg1, CacheSpan arg2) {
        __monitor_enter(this);
        try {
            this.mergeSpan(arg2);
        }
        catch(Throwable v1) {
            __monitor_exit(this);
            throw v1;
        }

        __monitor_exit(this);
    }

    public void onSpanRemoved(Cache arg7, CacheSpan arg8) {
        Region v0;
        Object v8;
        Region v7_1;
        __monitor_enter(this);
        try {
            v7_1 = new Region(arg8.position, arg8.position + arg8.length);
            v8 = this.regions.floor(v7_1);
            if(v8 != null) {
                goto label_16;
            }

            Log.e("CachedRegionTracker", "Removed a span we were not aware of");
        }
        catch(Throwable v7) {
            goto label_51;
        }

        __monitor_exit(this);
        return;
        try {
        label_16:
            this.regions.remove(v8);
            if(((Region)v8).startOffset < v7_1.startOffset) {
                v0 = new Region(((Region)v8).startOffset, v7_1.startOffset);
                int v1 = Arrays.binarySearch(this.chunkIndex.offsets, v0.endOffset);
                if(v1 < 0) {
                    v1 = -v1 - 2;
                }

                v0.endOffsetIndex = v1;
                this.regions.add(v0);
            }

            if(((Region)v8).endOffset > v7_1.endOffset) {
                v0 = new Region(v7_1.endOffset + 1, ((Region)v8).endOffset);
                v0.endOffsetIndex = ((Region)v8).endOffsetIndex;
                this.regions.add(v0);
            }
        }
        catch(Throwable v7) {
        label_51:
            __monitor_exit(this);
            throw v7;
        }

        __monitor_exit(this);
    }

    public void onSpanTouched(Cache arg1, CacheSpan arg2, CacheSpan arg3) {
    }

    private boolean regionsConnect(Region arg4, Region arg5) {
        boolean v4 = arg4 == null || arg5 == null || arg4.endOffset != arg5.startOffset ? false : true;
        return v4;
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, ((Listener)this));
    }
}

