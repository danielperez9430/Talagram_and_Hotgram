package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdc extends zzav implements zzcn, RandomAccess {
    private int size;
    private static final zzdc zzlw;
    private long[] zzlx;

    static {
        zzdc v0 = new zzdc();
        zzdc.zzlw = v0;
        ((zzav)v0).zzv();
    }

    zzdc() {
        this(new long[10], 0);
    }

    private zzdc(long[] arg1, int arg2) {
        super();
        this.zzlx = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzk(arg3, ((Long)arg4).longValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzav)this).zzw();
        zzci.checkNotNull(arg6);
        if(!(arg6 instanceof zzdc)) {
            return super.addAll(arg6);
        }

        if(((zzdc)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzdc)arg6).size) {
            int v0 = this.size + ((zzdc)arg6).size;
            if(v0 > this.zzlx.length) {
                this.zzlx = Arrays.copyOf(this.zzlx, v0);
            }

            System.arraycopy(((zzdc)arg6).zzlx, 0, this.zzlx, this.size, ((zzdc)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzdc)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzdc)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzdc)arg9).size) {
            return 0;
        }

        long[] v9 = ((zzdc)arg9).zzlx;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzlx[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg3) {
        return Long.valueOf(this.getLong(arg3));
    }

    public final long getLong(int arg4) {
        this.zzg(arg4);
        return this.zzlx[arg4];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzci.zzl(this.zzlx[v1]);
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzav)this).zzw();
        this.zzg(arg7);
        long v1 = this.zzlx[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzlx, arg7 + 1, this.zzlx, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Long.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzav)this).zzw();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Long.valueOf(this.zzlx[v1]))) {
                System.arraycopy(this.zzlx, v1 + 1, this.zzlx, v1, this.size - v1);
                --this.size;
                ++this.modCount;
                return 1;
            }
        }

        return 0;
    }

    protected final void removeRange(int arg4, int arg5) {
        ((zzav)this).zzw();
        if(arg5 >= arg4) {
            System.arraycopy(this.zzlx, arg5, this.zzlx, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        long v0 = ((Long)arg6).longValue();
        ((zzav)this).zzw();
        this.zzg(arg5);
        long v2 = this.zzlx[arg5];
        this.zzlx[arg5] = v0;
        return Long.valueOf(v2);
    }

    public final int size() {
        return this.size;
    }

    public static zzdc zzbx() {
        return zzdc.zzlw;
    }

    private final void zzg(int arg2) {
        if(arg2 >= 0 && arg2 < this.size) {
            return;
        }

        throw new IndexOutOfBoundsException(this.zzh(arg2));
    }

    private final String zzh(int arg4) {
        int v0 = this.size;
        StringBuilder v1 = new StringBuilder(35);
        v1.append("Index:");
        v1.append(arg4);
        v1.append(", Size:");
        v1.append(v0);
        return v1.toString();
    }

    public final zzcn zzi(int arg3) {
        if(arg3 >= this.size) {
            return new zzdc(Arrays.copyOf(this.zzlx, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zzk(int arg5, long arg6) {
        ((zzav)this).zzw();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzlx.length) {
                System.arraycopy(this.zzlx, arg5, this.zzlx, arg5 + 1, this.size - arg5);
            }
            else {
                long[] v0 = new long[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzlx, 0, v0, 0, arg5);
                System.arraycopy(this.zzlx, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzlx = v0;
            }

            this.zzlx[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzh(arg5));
    }

    public final void zzm(long arg2) {
        this.zzk(this.size, arg2);
    }
}

