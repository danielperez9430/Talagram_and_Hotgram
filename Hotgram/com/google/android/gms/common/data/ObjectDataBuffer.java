package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public final class ObjectDataBuffer extends AbstractDataBuffer implements Observable, ObjectExclusionFilterable {
    private final ArrayList zzob;
    private final HashSet zzoe;
    private DataBufferObserverSet zzof;
    private final ArrayList zzog;

    public ObjectDataBuffer() {
        super(null);
        this.zzoe = new HashSet();
        this.zzob = new ArrayList();
        this.zzog = new ArrayList();
        this.zzof = new DataBufferObserverSet();
        this.zzcl();
    }

    public ObjectDataBuffer(ArrayList arg2) {
        super(null);
        this.zzoe = new HashSet();
        this.zzob = new ArrayList();
        this.zzog = arg2;
        this.zzof = new DataBufferObserverSet();
        this.zzcl();
    }

    public ObjectDataBuffer(Object[] arg2) {
        super(null);
        this.zzoe = new HashSet();
        this.zzob = new ArrayList();
        this.zzog = new ArrayList(Arrays.asList(arg2));
        this.zzof = new DataBufferObserverSet();
        this.zzcl();
    }

    public final void add(Object arg5) {
        int v0 = this.zzog.size();
        this.zzog.add(arg5);
        this.zzcl();
        if(this.zzof.hasObservers()) {
            Asserts.checkState(this.zzoe.contains(Integer.valueOf(v0)) ^ 1);
            int v5 = this.zzob.size();
            boolean v2 = false;
            boolean v3 = v5 > 0 ? true : false;
            Asserts.checkState(v3);
            --v5;
            if(this.zzob.get(v5).intValue() == v0) {
                v2 = true;
            }

            Asserts.checkState(v2);
            this.zzof.onDataRangeInserted(v5, 1);
        }
    }

    public final void addObserver(DataBufferObserver arg2) {
        this.zzof.addObserver(arg2);
    }

    public final void filterOut(Object arg11) {
        int v0 = this.zzog.size();
        int v3 = 0;
        int v4 = 0;
        int v5 = -1;
        int v6 = -1;
        int v7 = -1;
        while(v3 < v0) {
            if(!this.zzoe.contains(Integer.valueOf(v3))) {
                ++v5;
                if(arg11.equals(this.zzog.get(v3))) {
                    this.zzoe.add(Integer.valueOf(v3));
                    if(this.zzof.hasObservers()) {
                        if(v6 < 0) {
                            v6 = v5;
                            v4 = 1;
                            v7 = 1;
                            goto label_42;
                        }
                        else {
                            ++v7;
                        }
                    }

                    v4 = 1;
                }
                else {
                    if(v6 < 0) {
                        goto label_42;
                    }

                    this.zzcl();
                    this.zzof.onDataRangeRemoved(v6, v7);
                    v5 -= v7;
                    v4 = 0;
                    v6 = -1;
                    v7 = -1;
                }
            }

        label_42:
            ++v3;
        }

        if(v4 != 0) {
            this.zzcl();
        }

        if(v6 >= 0) {
            this.zzof.onDataRangeRemoved(v6, v7);
        }
    }

    public final void filterOutRaw(int arg4) {
        int v0_1;
        boolean v0 = this.zzoe.add(Integer.valueOf(arg4));
        if(!this.zzof.hasObservers() || !v0) {
        label_18:
            v0_1 = -1;
        }
        else {
            v0_1 = 0;
            int v1 = this.zzob.size();
            while(true) {
                if(v0_1 >= v1) {
                    goto label_18;
                }
                else if(this.zzob.get(v0_1).intValue() == arg4) {
                }
                else {
                    ++v0_1;
                    continue;
                }

                break;
            }
        }

        this.zzcl();
        if(v0_1 >= 0) {
            this.zzof.onDataRangeRemoved(v0_1, 1);
        }
    }

    public final Object get(int arg2) {
        return this.zzog.get(this.getRawPosition(arg2));
    }

    public final int getCount() {
        return this.zzog.size() - this.zzoe.size();
    }

    public final Bundle getMetadata() {
        return null;
    }

    public final int getPositionFromRawPosition(int arg5) {
        int v0 = -1;
        int v1;
        for(v1 = 0; v1 <= arg5; ++v1) {
            if(!this.zzoe.contains(Integer.valueOf(v1))) {
                ++v0;
            }
        }

        return v0;
    }

    public final Object getRaw(int arg2) {
        return this.zzog.get(arg2);
    }

    public final int getRawCount() {
        return this.zzog.size();
    }

    public final int getRawPosition(int arg4) {
        if(arg4 >= 0 && arg4 < ((AbstractDataBuffer)this).getCount()) {
            return this.zzob.get(arg4).intValue();
        }

        StringBuilder v2 = new StringBuilder(53);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(v2.toString());
    }

    public final void insertRaw(int arg6, Object arg7) {
        this.zzog.add(arg6, arg7);
        HashSet v7 = new HashSet(this.zzoe.size());
        Iterator v0 = this.zzoe.iterator();
        int v1 = arg6;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            if(((Integer)v2).intValue() < arg6) {
                --v1;
                continue;
            }

            v7.add(Integer.valueOf(((Integer)v2).intValue() + 1));
            v0.remove();
        }

        Iterator v6 = v7.iterator();
        while(v6.hasNext()) {
            this.zzoe.add(v6.next());
        }

        this.zzcl();
        if(this.zzof.hasObservers()) {
            this.zzof.onDataRangeInserted(v1, 1);
        }
    }

    public final boolean isRawPositionFiltered(int arg2) {
        return this.zzoe.contains(Integer.valueOf(arg2));
    }

    public final void notifyChanged(Object arg5) {
        if(!this.zzof.hasObservers()) {
            return;
        }

        int v0 = 0;
        int v1 = this.zzob.size();
        while(v0 < v1) {
            if(arg5.equals(this.zzog.get(this.zzob.get(v0).intValue()))) {
                this.zzof.onDataRangeChanged(v0, 1);
            }

            ++v0;
        }
    }

    public final void release() {
        this.zzof.clear();
    }

    public final void removeObserver(DataBufferObserver arg2) {
        this.zzof.removeObserver(arg2);
    }

    public final void removeRaw(int arg8) {
        this.zzog.remove(arg8);
        boolean v0 = this.zzoe.remove(Integer.valueOf(arg8));
        HashSet v1 = new HashSet(this.zzoe.size());
        Iterator v2 = this.zzoe.iterator();
        int v3 = arg8;
        while(v2.hasNext()) {
            Object v4 = v2.next();
            if(((Integer)v4).intValue() < arg8) {
                --v3;
                continue;
            }

            v2.remove();
            v1.add(Integer.valueOf(((Integer)v4).intValue() - 1));
        }

        Iterator v8 = v1.iterator();
        while(v8.hasNext()) {
            this.zzoe.add(v8.next());
        }

        this.zzcl();
        if(!v0 && (this.zzof.hasObservers())) {
            this.zzof.onDataRangeRemoved(v3, 1);
        }
    }

    public final boolean setRaw(int arg5, Object arg6) {
        this.zzog.set(arg5, arg6);
        int v6 = this.zzoe.contains(Integer.valueOf(arg5)) ^ 1;
        if(v6 != 0 && (this.zzof.hasObservers())) {
            int v1 = 0;
            int v2 = this.zzob.size();
            while(v1 < v2) {
                if(this.zzob.get(v1).intValue() == arg5) {
                    this.zzof.onDataRangeChanged(v1, 1);
                }
                else {
                    ++v1;
                    continue;
                }

                break;
            }
        }

        return ((boolean)v6);
    }

    public final void unfilter(Object arg11) {
        int v0 = this.zzog.size();
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = -1;
        int v7 = -1;
        while(v3 < v0) {
            if(!this.zzoe.contains(Integer.valueOf(v3))) {
                ++v5;
                if(v6 < 0) {
                    goto label_48;
                }

                goto label_17;
            }
            else if(arg11.equals(this.zzog.get(v3))) {
                this.zzoe.remove(Integer.valueOf(v3));
                if(this.zzof.hasObservers()) {
                    if(v6 < 0) {
                        v6 = v5;
                        v4 = 1;
                        v7 = 1;
                        goto label_48;
                    }
                    else {
                        ++v7;
                    }
                }

                v4 = 1;
            }
            else {
                if(!this.zzof.hasObservers()) {
                    goto label_48;
                }

                if(v6 < 0) {
                    goto label_48;
                }

            label_17:
                this.zzcl();
                this.zzof.onDataRangeInserted(v6, v7);
                v5 += v7;
                v4 = 0;
                v6 = -1;
                v7 = -1;
            }

        label_48:
            ++v3;
        }

        if(v4 != 0) {
            this.zzcl();
        }

        if(v6 >= 0) {
            this.zzof.onDataRangeInserted(v6, v7);
        }
    }

    public final void unfilterRaw(int arg5) {
        boolean v0 = this.zzoe.remove(Integer.valueOf(arg5));
        this.zzcl();
        if(this.zzof.hasObservers()) {
            if(!v0) {
            }
            else {
                int v0_1 = -1;
                int v1 = 0;
                int v2 = this.zzob.size();
                while(v1 < v2) {
                    if(this.zzob.get(v1).intValue() == arg5) {
                        v0_1 = v1;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    break;
                }

                if(v0_1 < 0) {
                    return;
                }

                this.zzof.onDataRangeInserted(v0_1, 1);
            }
        }
    }

    private final void zzcl() {
        this.zzob.clear();
        int v0 = this.zzog.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(!this.zzoe.contains(Integer.valueOf(v1))) {
                this.zzob.add(Integer.valueOf(v1));
            }
        }
    }
}

