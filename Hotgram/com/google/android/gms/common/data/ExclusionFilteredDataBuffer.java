package com.google.android.gms.common.data;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@VisibleForTesting public final class ExclusionFilteredDataBuffer extends FilteredDataBuffer implements Observable, ExclusionFilterable {
    private AbstractDataBuffer zzoc;
    private final String zzod;
    private final HashSet zzoe;
    private DataBufferObserverSet zzof;

    public ExclusionFilteredDataBuffer(AbstractDataBuffer arg2, String arg3) {
        super(((DataBuffer)arg2));
        this.zzoe = new HashSet();
        this.zzoc = arg2;
        this.zzod = arg3;
        this.zzof = new DataBufferObserverSet();
    }

    public final void addObserver(DataBufferObserver arg2) {
        this.zzof.addObserver(arg2);
    }

    public final void clearFilters() {
        if(!this.zzof.hasObservers()) {
            this.zzoe.clear();
            return;
        }

        int v0 = this.zzoe.size();
        Object[] v1 = this.zzoe.toArray(new Integer[v0]);
        Arrays.sort(v1);
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        while(v2 < v0) {
            int v5 = v1[v2].intValue();
            this.zzoe.remove(Integer.valueOf(v5));
            if(v3 == 0) {
            label_23:
                v4 = v5;
                v3 = 1;
            }
            else if(v5 == v4 + v3) {
                ++v3;
            }
            else {
                this.zzof.onDataRangeRemoved(v4, v3);
                goto label_23;
            }

            ++v2;
        }

        if(v3 > 0) {
            this.zzof.onDataRangeRemoved(v4, v3);
        }
    }

    public final int computeRealPosition(int arg6) {
        if(this.zzoe.isEmpty()) {
            return arg6;
        }

        if(arg6 >= 0 && arg6 < this.getCount()) {
            int v0 = this.mDataBuffer.getCount();
            int v1 = 0;
            int v2 = 0;
            while(v1 < v0) {
                if(!this.zzoe.contains(Integer.valueOf(v1))) {
                    if(v2 == arg6) {
                        return v1;
                    }
                    else {
                        ++v2;
                    }
                }

                ++v1;
            }

            return -1;
        }

        StringBuilder v2_1 = new StringBuilder(53);
        v2_1.append("Position ");
        v2_1.append(arg6);
        v2_1.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2_1.toString());
    }

    public final void filterOut(String arg10) {
        if(TextUtils.isEmpty(((CharSequence)arg10))) {
            return;
        }

        ArrayList v0 = null;
        if(this.zzof.hasObservers()) {
            v0 = new ArrayList();
        }

        ArrayList v10 = this.zza(arg10, v0);
        if(this.zzof.hasObservers()) {
            int v1 = v10.size() - 1;
            int v4 = 0;
            int v5 = 0;
            while(v1 >= 0) {
                int v6 = v0.get(v1).intValue();
                int v7 = v6 < 0 ? 1 : 0;
                if(v7 == 0) {
                    this.zzoe.add(Integer.valueOf(v10.get(v1).intValue()));
                    if(v4 != 0) {
                        if(v6 == v5 - 1) {
                            --v5;
                            ++v4;
                            goto label_44;
                        }
                        else {
                            this.zzof.onDataRangeRemoved(v5, v4);
                        }
                    }

                    v5 = v6;
                    v4 = 1;
                }

            label_44:
                --v1;
            }

            if(v4 > 0) {
                this.zzof.onDataRangeRemoved(v5, v4);
            }

            return;
        }

        this.zzoe.addAll(((Collection)v10));
    }

    public final int getCount() {
        return this.mDataBuffer.getCount() - this.zzoe.size();
    }

    public final void release() {
        super.release();
        this.zzof.clear();
    }

    public final void removeObserver(DataBufferObserver arg2) {
        this.zzof.removeObserver(arg2);
    }

    public final void unfilter(String arg10) {
        if(TextUtils.isEmpty(((CharSequence)arg10))) {
            return;
        }

        ArrayList v0 = null;
        if(this.zzof.hasObservers()) {
            v0 = new ArrayList();
        }

        ArrayList v10 = this.zza(arg10, v0);
        if(this.zzof.hasObservers()) {
            int v1 = v10.size() - 1;
            int v4 = 0;
            int v5 = 0;
            while(v1 >= 0) {
                int v6 = v0.get(v1).intValue();
                int v7 = v6 < 0 ? 1 : 0;
                if(v7 != 0) {
                    this.zzoe.remove(Integer.valueOf(v10.get(v1).intValue()));
                    v6 = -v6 - 1;
                    if(v4 != 0) {
                        if(v6 == v5) {
                            ++v4;
                            goto label_44;
                        }
                        else {
                            this.zzof.onDataRangeInserted(v5, v4);
                        }
                    }

                    v5 = v6;
                    v4 = 1;
                }

            label_44:
                --v1;
            }

            if(v4 > 0) {
                this.zzof.onDataRangeInserted(v5, v4);
            }

            return;
        }

        this.zzoe.removeAll(((Collection)v10));
    }

    private final ArrayList zza(String arg12, ArrayList arg13) {
        int v8;
        ArrayList v0 = new ArrayList();
        if(arg13 != null) {
            arg13.clear();
        }

        DataHolder v1 = this.zzoc.mDataHolder;
        String v2 = this.zzod;
        boolean v3 = this.zzoc instanceof EntityBuffer;
        int v4 = this.zzoc.getCount();
        int v5 = 0;
        int v6;
        for(v6 = 0; v5 < v4; v6 = v8) {
            int v7 = v3 ? this.zzoc.zzi(v5) : v5;
            String v7_1 = v1.getString(v2, v7, v1.getWindowIndex(v7));
            if(arg13 == null) {
                v8 = v6;
            }
            else if(this.zzoe.contains(Integer.valueOf(v5))) {
                v8 = v6;
                v6 = -v6 - 1;
            }
            else {
                v8 = v6 + 1;
            }

            if(!TextUtils.isEmpty(((CharSequence)v7_1)) && (v7_1.equals(arg12))) {
                v0.add(Integer.valueOf(v5));
                if(arg13 != null) {
                    arg13.add(Integer.valueOf(v6));
                }
            }

            ++v5;
        }

        return v0;
    }
}

