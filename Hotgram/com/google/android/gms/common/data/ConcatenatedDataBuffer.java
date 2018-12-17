package com.google.android.gms.common.data;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

@VisibleForTesting public final class ConcatenatedDataBuffer implements DataBuffer, ExclusionFilterable, TextFilterable {
    private Bundle mBundle;
    private final ArrayList zznf;
    private final ArrayList zzng;
    private int zznh;

    public ConcatenatedDataBuffer() {
        super();
        this.zznf = new ArrayList();
        this.zzng = new ArrayList();
    }

    public ConcatenatedDataBuffer(DataBuffer arg2) {
        super();
        this.zznf = new ArrayList();
        this.zzng = new ArrayList();
        this.append(arg2);
    }

    public final void append(DataBuffer arg5) {
        if(arg5 == null) {
            return;
        }

        __monitor_enter(this);
        try {
            if(this.zznf.isEmpty()) {
                this.mBundle = new Bundle();
                Bundle v0 = arg5.getMetadata();
                if(v0 != null) {
                    this.mBundle.putString("prev_page_token", v0.getString("prev_page_token"));
                }
            }

            this.zznf.add(arg5);
            this.computeCounts();
            Bundle v5_1 = arg5.getMetadata();
            if(v5_1 != null) {
                this.mBundle.putString("next_page_token", v5_1.getString("next_page_token"));
            }
            else {
                this.mBundle.remove("next_page_token");
            }

            __monitor_exit(this);
            return;
        label_33:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_33;
        }

        throw v5;
    }

    public final void clearFilters() {
        int v0 = this.zznf.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.zznf.get(v1);
            if((v2 instanceof ExclusionFilterable)) {
                ((ExclusionFilterable)v2).clearFilters();
            }
        }

        this.computeCounts();
    }

    @Deprecated public final void close() {
        this.release();
    }

    public final void computeCounts() {
        this.zzng.clear();
        int v0 = this.zznf.size();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            Object v3 = this.zznf.get(v1);
            if(v3 != null) {
                v2 += ((DataBuffer)v3).getCount();
            }

            this.zzng.add(Integer.valueOf(v2));
            ++v1;
        }

        this.zznh = v2;
    }

    public static ConcatenatedDataBuffer extend(ConcatenatedDataBuffer arg4, DataBuffer arg5) {
        ConcatenatedDataBuffer v0 = new ConcatenatedDataBuffer();
        ArrayList v4 = arg4.zznf;
        int v1 = v4.size();
        int v2 = 0;
        while(v2 < v1) {
            Object v3 = v4.get(v2);
            ++v2;
            v0.append(((DataBuffer)v3));
        }

        v0.append(arg5);
        return v0;
    }

    public final void filterOut(String arg5) {
        int v0 = this.zznf.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.zznf.get(v1);
            if((v2 instanceof ExclusionFilterable)) {
                ((ExclusionFilterable)v2).filterOut(arg5);
            }
        }

        this.computeCounts();
    }

    public final Object get(int arg5) {
        __monitor_enter(this);
        int v0 = 0;
        try {
            int v1 = this.zznf.size();
            while(v0 < v1) {
                int v2 = this.zzng.get(v0).intValue();
                if(arg5 < v2) {
                    Object v3 = this.zznf.get(v0);
                    if(v3 != null) {
                        __monitor_exit(this);
                        return ((DataBuffer)v3).get(arg5 - v2 + ((DataBuffer)v3).getCount());
                    }
                }

                ++v0;
            }

            __monitor_exit(this);
            return null;
        label_24:
            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_24;
        }

        throw v5;
    }

    public final int getCount() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.zznh;
        label_5:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_5;
        }

        throw v0;
    }

    public final Bundle getMetadata() {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.mBundle;
        label_5:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_5;
        }

        throw v0;
    }

    @Deprecated public final boolean isClosed() {
        return 0;
    }

    public final Iterator iterator() {
        return new DataBufferIterator(((DataBuffer)this));
    }

    public final void release() {
        __monitor_enter(this);
        int v0 = 0;
        try {
            int v1 = this.zznf.size();
            while(v0 < v1) {
                Object v2 = this.zznf.get(v0);
                if(v2 != null) {
                    ((DataBuffer)v2).release();
                }

                ++v0;
            }

            this.zznf.clear();
            this.zzng.clear();
            this.mBundle = null;
            __monitor_exit(this);
            return;
        label_20:
            __monitor_exit(this);
        }
        catch(Throwable v0_1) {
            goto label_20;
        }

        throw v0_1;
    }

    public final void setFilterTerm(Context arg5, StringFilter arg6, String arg7) {
        int v0 = this.zznf.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.zznf.get(v1);
            if((v2 instanceof TextFilterable)) {
                ((TextFilterable)v2).setFilterTerm(arg5, arg6, arg7);
            }
        }

        this.computeCounts();
    }

    public final void setFilterTerm(Context arg5, String arg6) {
        int v0 = this.zznf.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.zznf.get(v1);
            if((v2 instanceof TextFilterable)) {
                ((TextFilterable)v2).setFilterTerm(arg5, arg6);
            }
        }

        this.computeCounts();
    }

    public final Iterator singleRefIterator() {
        return this.iterator();
    }

    public final void unfilter(String arg5) {
        int v0 = this.zznf.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.zznf.get(v1);
            if((v2 instanceof ExclusionFilterable)) {
                ((ExclusionFilterable)v2).unfilter(arg5);
            }
        }

        this.computeCounts();
    }
}

