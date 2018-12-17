package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdl extends zzbi implements zzcw, zzej, RandomAccess {
    private int size;
    private static final zzdl zzmr;
    private long[] zzms;

    static {
        zzdl v0 = new zzdl();
        zzdl.zzmr = v0;
        ((zzbi)v0).zzao();
    }

    zzdl() {
        this(new long[10], 0);
    }

    private zzdl(long[] arg1, int arg2) {
        super();
        this.zzms = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzk(arg3, ((Long)arg4).longValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzbi)this).zzap();
        zzct.checkNotNull(arg6);
        if(!(arg6 instanceof zzdl)) {
            return super.addAll(arg6);
        }

        if(((zzdl)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzdl)arg6).size) {
            int v0 = this.size + ((zzdl)arg6).size;
            if(v0 > this.zzms.length) {
                this.zzms = Arrays.copyOf(this.zzms, v0);
            }

            System.arraycopy(((zzdl)arg6).zzms, 0, this.zzms, this.size, ((zzdl)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzdl)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzdl)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzdl)arg9).size) {
            return 0;
        }

        long[] v9 = ((zzdl)arg9).zzms;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzms[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg3) {
        return Long.valueOf(this.getLong(arg3));
    }

    public final long getLong(int arg4) {
        this.zzi(arg4);
        return this.zzms[arg4];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzct.zzk(this.zzms[v1]);
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzbi)this).zzap();
        this.zzi(arg7);
        long v1 = this.zzms[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzms, arg7 + 1, this.zzms, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Long.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzbi)this).zzap();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Long.valueOf(this.zzms[v1]))) {
                System.arraycopy(this.zzms, v1 + 1, this.zzms, v1, this.size - v1);
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
            System.arraycopy(this.zzms, arg5, this.zzms, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        long v0 = ((Long)arg6).longValue();
        ((zzbi)this).zzap();
        this.zzi(arg5);
        long v2 = this.zzms[arg5];
        this.zzms[arg5] = v0;
        return Long.valueOf(v2);
    }

    public final int size() {
        return this.size;
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

    private final void zzk(int arg5, long arg6) {
        ((zzbi)this).zzap();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzms.length) {
                System.arraycopy(this.zzms, arg5, this.zzms, arg5 + 1, this.size - arg5);
            }
            else {
                long[] v0 = new long[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzms, 0, v0, 0, arg5);
                System.arraycopy(this.zzms, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzms = v0;
            }

            this.zzms[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzj(arg5));
    }

    public final zzcw zzk(int arg3) {
        if(arg3 >= this.size) {
            return new zzdl(Arrays.copyOf(this.zzms, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzl(long arg2) {
        this.zzk(this.size, arg2);
    }
}

