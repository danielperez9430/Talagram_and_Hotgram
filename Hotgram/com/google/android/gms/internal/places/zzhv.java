package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzhv extends zzfk implements zzhg, RandomAccess {
    private int size;
    private static final zzhv zzuo;
    private long[] zzup;

    static {
        zzhv v0 = new zzhv();
        zzhv.zzuo = v0;
        ((zzfk)v0).zzbb();
    }

    zzhv() {
        this(new long[10], 0);
    }

    private zzhv(long[] arg1, int arg2) {
        super();
        this.zzup = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzl(arg3, ((Long)arg4).longValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzfk)this).zzbc();
        zzhb.checkNotNull(arg6);
        if(!(arg6 instanceof zzhv)) {
            return super.addAll(arg6);
        }

        if(((zzhv)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzhv)arg6).size) {
            int v0 = this.size + ((zzhv)arg6).size;
            if(v0 > this.zzup.length) {
                this.zzup = Arrays.copyOf(this.zzup, v0);
            }

            System.arraycopy(((zzhv)arg6).zzup, 0, this.zzup, this.size, ((zzhv)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzhv)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzhv)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzhv)arg9).size) {
            return 0;
        }

        long[] v9 = ((zzhv)arg9).zzup;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzup[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg3) {
        return Long.valueOf(this.getLong(arg3));
    }

    public final long getLong(int arg4) {
        this.zzac(arg4);
        return this.zzup[arg4];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzhb.zzo(this.zzup[v1]);
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzfk)this).zzbc();
        this.zzac(arg7);
        long v1 = this.zzup[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzup, arg7 + 1, this.zzup, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Long.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzfk)this).zzbc();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Long.valueOf(this.zzup[v1]))) {
                System.arraycopy(this.zzup, v1 + 1, this.zzup, v1, this.size - v1);
                --this.size;
                ++this.modCount;
                return 1;
            }
        }

        return 0;
    }

    protected final void removeRange(int arg4, int arg5) {
        ((zzfk)this).zzbc();
        if(arg5 >= arg4) {
            System.arraycopy(this.zzup, arg5, this.zzup, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        long v0 = ((Long)arg6).longValue();
        ((zzfk)this).zzbc();
        this.zzac(arg5);
        long v2 = this.zzup[arg5];
        this.zzup[arg5] = v0;
        return Long.valueOf(v2);
    }

    public final int size() {
        return this.size;
    }

    private final void zzac(int arg2) {
        if(arg2 >= 0 && arg2 < this.size) {
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg2));
    }

    private final String zzad(int arg4) {
        int v0 = this.size;
        StringBuilder v1 = new StringBuilder(35);
        v1.append("Index:");
        v1.append(arg4);
        v1.append(", Size:");
        v1.append(v0);
        return v1.toString();
    }

    public final zzhg zzae(int arg3) {
        if(arg3 >= this.size) {
            return new zzhv(Arrays.copyOf(this.zzup, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zzl(int arg5, long arg6) {
        ((zzfk)this).zzbc();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzup.length) {
                System.arraycopy(this.zzup, arg5, this.zzup, arg5 + 1, this.size - arg5);
            }
            else {
                long[] v0 = new long[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzup, 0, v0, 0, arg5);
                System.arraycopy(this.zzup, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzup = v0;
            }

            this.zzup[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg5));
    }

    public final void zzp(long arg2) {
        this.zzl(this.size, arg2);
    }
}

