package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzwh extends zztz implements zzvs, zzxe, RandomAccess {
    private int size;
    private static final zzwh zzcam;
    private long[] zzcan;

    static {
        zzwh v0 = new zzwh();
        zzwh.zzcam = v0;
        ((zztz)v0).zzsm();
    }

    zzwh() {
        this(new long[10], 0);
    }

    private zzwh(long[] arg1, int arg2) {
        super();
        this.zzcan = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzk(arg3, ((Long)arg4).longValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zztz)this).zztx();
        zzvo.checkNotNull(arg6);
        if(!(arg6 instanceof zzwh)) {
            return super.addAll(arg6);
        }

        if(((zzwh)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzwh)arg6).size) {
            int v0 = this.size + ((zzwh)arg6).size;
            if(v0 > this.zzcan.length) {
                this.zzcan = Arrays.copyOf(this.zzcan, v0);
            }

            System.arraycopy(((zzwh)arg6).zzcan, 0, this.zzcan, this.size, ((zzwh)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzwh)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzwh)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzwh)arg9).size) {
            return 0;
        }

        long[] v9 = ((zzwh)arg9).zzcan;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzcan[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg3) {
        return Long.valueOf(this.getLong(arg3));
    }

    public final long getLong(int arg4) {
        this.zzai(arg4);
        return this.zzcan[arg4];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzvo.zzbf(this.zzcan[v1]);
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zztz)this).zztx();
        this.zzai(arg7);
        long v1 = this.zzcan[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzcan, arg7 + 1, this.zzcan, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Long.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zztz)this).zztx();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Long.valueOf(this.zzcan[v1]))) {
                System.arraycopy(this.zzcan, v1 + 1, this.zzcan, v1, this.size - v1);
                --this.size;
                ++this.modCount;
                return 1;
            }
        }

        return 0;
    }

    protected final void removeRange(int arg4, int arg5) {
        ((zztz)this).zztx();
        if(arg5 >= arg4) {
            System.arraycopy(this.zzcan, arg5, this.zzcan, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        long v0 = ((Long)arg6).longValue();
        ((zztz)this).zztx();
        this.zzai(arg5);
        long v2 = this.zzcan[arg5];
        this.zzcan[arg5] = v0;
        return Long.valueOf(v2);
    }

    public final int size() {
        return this.size;
    }

    private final void zzai(int arg2) {
        if(arg2 >= 0 && arg2 < this.size) {
            return;
        }

        throw new IndexOutOfBoundsException(this.zzaj(arg2));
    }

    private final String zzaj(int arg4) {
        int v0 = this.size;
        StringBuilder v1 = new StringBuilder(35);
        v1.append("Index:");
        v1.append(arg4);
        v1.append(", Size:");
        v1.append(v0);
        return v1.toString();
    }

    public final zzvs zzak(int arg3) {
        if(arg3 >= this.size) {
            return new zzwh(Arrays.copyOf(this.zzcan, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzbg(long arg2) {
        this.zzk(this.size, arg2);
    }

    private final void zzk(int arg5, long arg6) {
        ((zztz)this).zztx();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzcan.length) {
                System.arraycopy(this.zzcan, arg5, this.zzcan, arg5 + 1, this.size - arg5);
            }
            else {
                long[] v0 = new long[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzcan, 0, v0, 0, arg5);
                System.arraycopy(this.zzcan, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzcan = v0;
            }

            this.zzcan[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzaj(arg5));
    }
}

