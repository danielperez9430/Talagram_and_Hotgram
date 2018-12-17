package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbq extends zzav implements zzcn, RandomAccess {
    private int size;
    private static final zzbq zzgj;
    private double[] zzgk;

    static {
        zzbq v0 = new zzbq();
        zzbq.zzgj = v0;
        ((zzav)v0).zzv();
    }

    zzbq() {
        this(new double[10], 0);
    }

    private zzbq(double[] arg1, int arg2) {
        super();
        this.zzgk = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzc(arg3, ((Double)arg4).doubleValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzav)this).zzw();
        zzci.checkNotNull(arg6);
        if(!(arg6 instanceof zzbq)) {
            return super.addAll(arg6);
        }

        if(((zzbq)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzbq)arg6).size) {
            int v0 = this.size + ((zzbq)arg6).size;
            if(v0 > this.zzgk.length) {
                this.zzgk = Arrays.copyOf(this.zzgk, v0);
            }

            System.arraycopy(((zzbq)arg6).zzgk, 0, this.zzgk, this.size, ((zzbq)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzbq)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzbq)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzbq)arg9).size) {
            return 0;
        }

        double[] v9 = ((zzbq)arg9).zzgk;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzgk[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg4) {
        this.zzg(arg4);
        return Double.valueOf(this.zzgk[arg4]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzci.zzl(Double.doubleToLongBits(this.zzgk[v1]));
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzav)this).zzw();
        this.zzg(arg7);
        double v1 = this.zzgk[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzgk, arg7 + 1, this.zzgk, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Double.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzav)this).zzw();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Double.valueOf(this.zzgk[v1]))) {
                System.arraycopy(this.zzgk, v1 + 1, this.zzgk, v1, this.size - v1);
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
            System.arraycopy(this.zzgk, arg5, this.zzgk, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        double v0 = ((Double)arg6).doubleValue();
        ((zzav)this).zzw();
        this.zzg(arg5);
        double v2 = this.zzgk[arg5];
        this.zzgk[arg5] = v0;
        return Double.valueOf(v2);
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double arg2) {
        this.zzc(this.size, arg2);
    }

    private final void zzc(int arg5, double arg6) {
        ((zzav)this).zzw();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzgk.length) {
                System.arraycopy(this.zzgk, arg5, this.zzgk, arg5 + 1, this.size - arg5);
            }
            else {
                double[] v0 = new double[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzgk, 0, v0, 0, arg5);
                System.arraycopy(this.zzgk, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzgk = v0;
            }

            this.zzgk[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzh(arg5));
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
            return new zzbq(Arrays.copyOf(this.zzgk, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

