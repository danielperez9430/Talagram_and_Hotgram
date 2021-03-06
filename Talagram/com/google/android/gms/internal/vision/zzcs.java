package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcs extends zzbi implements zzcw, zzej, RandomAccess {
    private int size;
    private static final zzcs zzlm;
    private int[] zzln;

    static {
        zzcs v0 = new zzcs();
        zzcs.zzlm = v0;
        ((zzbi)v0).zzao();
    }

    zzcs() {
        this(new int[10], 0);
    }

    private zzcs(int[] arg1, int arg2) {
        super();
        this.zzln = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzq(arg1, ((Integer)arg2).intValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzbi)this).zzap();
        zzct.checkNotNull(arg6);
        if(!(arg6 instanceof zzcs)) {
            return super.addAll(arg6);
        }

        if(((zzcs)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzcs)arg6).size) {
            int v0 = this.size + ((zzcs)arg6).size;
            if(v0 > this.zzln.length) {
                this.zzln = Arrays.copyOf(this.zzln, v0);
            }

            System.arraycopy(((zzcs)arg6).zzln, 0, this.zzln, this.size, ((zzcs)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzcs)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzcs)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzcs)arg6).size) {
            return 0;
        }

        int[] v6 = ((zzcs)arg6).zzln;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzln[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg1) {
        return Integer.valueOf(this.getInt(arg1));
    }

    public final int getInt(int arg2) {
        this.zzi(arg2);
        return this.zzln[arg2];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + this.zzln[v1];
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzbi)this).zzap();
        this.zzi(arg6);
        int v0 = this.zzln[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzln, arg6 + 1, this.zzln, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Integer.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzbi)this).zzap();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Integer.valueOf(this.zzln[v1]))) {
                System.arraycopy(this.zzln, v1 + 1, this.zzln, v1, this.size - v1);
                --this.size;
                ++this.modCount;
                return 1;
            }
        }

        return 0;
    }

    protected final void removeRange(int arg4, int arg5) {
        ((zzbi)this).zzap();
        if(arg5 >= arg4) {
            System.arraycopy(this.zzln, arg5, this.zzln, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        int v4 = ((Integer)arg4).intValue();
        ((zzbi)this).zzap();
        this.zzi(arg3);
        int v0 = this.zzln[arg3];
        this.zzln[arg3] = v4;
        return Integer.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    public final void zzae(int arg2) {
        this.zzq(this.size, arg2);
    }

    private final void zzi(int arg2) {
        if(arg2 >= 0 && arg2 < this.size) {
            return;
        }

        throw new IndexOutOfBoundsException(this.zzj(arg2));
    }

    private final String zzj(int arg4) {
        int v0 = this.size;
        StringBuilder v1 = new StringBuilder(35);
        v1.append("Index:");
        v1.append(arg4);
        v1.append(", Size:");
        v1.append(v0);
        return v1.toString();
    }

    public final zzcw zzk(int arg3) {
        if(arg3 >= this.size) {
            return new zzcs(Arrays.copyOf(this.zzln, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zzq(int arg5, int arg6) {
        ((zzbi)this).zzap();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzln.length) {
                System.arraycopy(this.zzln, arg5, this.zzln, arg5 + 1, this.size - arg5);
            }
            else {
                int[] v0 = new int[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzln, 0, v0, 0, arg5);
                System.arraycopy(this.zzln, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzln = v0;
            }

            this.zzln[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzj(arg5));
    }
}

