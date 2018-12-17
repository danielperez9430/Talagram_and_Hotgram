package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzbi extends AbstractList implements zzcw {
    private boolean zzgl;

    zzbi() {
        super();
        this.zzgl = true;
    }

    public void add(int arg1, Object arg2) {
        this.zzap();
        super.add(arg1, arg2);
    }

    public boolean add(Object arg1) {
        this.zzap();
        return super.add(arg1);
    }

    public boolean addAll(int arg1, Collection arg2) {
        this.zzap();
        return super.addAll(arg1, arg2);
    }

    public boolean addAll(Collection arg1) {
        this.zzap();
        return super.addAll(arg1);
    }

    public void clear() {
        this.zzap();
        super.clear();
    }

    public boolean equals(Object arg7) {
        if((((zzbi)arg7)) == this) {
            return 1;
        }

        if(!(arg7 instanceof List)) {
            return 0;
        }

        if(!(arg7 instanceof RandomAccess)) {
            return super.equals(arg7);
        }

        int v1 = this.size();
        if(v1 != ((List)arg7).size()) {
            return 0;
        }

        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            if(!this.get(v3).equals(((List)arg7).get(v3))) {
                return 0;
            }
        }

        return 1;
    }

    public int hashCode() {
        int v0 = this.size();
        int v1 = 1;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1 = v1 * 31 + this.get(v2).hashCode();
        }

        return v1;
    }

    public Object remove(int arg1) {
        this.zzap();
        return super.remove(arg1);
    }

    public boolean remove(Object arg1) {
        this.zzap();
        return super.remove(arg1);
    }

    public boolean removeAll(Collection arg1) {
        this.zzap();
        return super.removeAll(arg1);
    }

    public boolean retainAll(Collection arg1) {
        this.zzap();
        return super.retainAll(arg1);
    }

    public Object set(int arg1, Object arg2) {
        this.zzap();
        return super.set(arg1, arg2);
    }

    public boolean zzan() {
        return this.zzgl;
    }

    public final void zzao() {
        this.zzgl = false;
    }

    protected final void zzap() {
        if(this.zzgl) {
            return;
        }

        throw new UnsupportedOperationException();
    }
}

