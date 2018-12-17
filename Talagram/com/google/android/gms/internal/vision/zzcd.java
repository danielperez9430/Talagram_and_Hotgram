package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcd extends zzbi implements zzcw, zzej, RandomAccess {
    private int size;
    private static final zzcd zzhl;
    private double[] zzhm;

    static {
        zzcd v0 = new zzcd();
        zzcd.zzhl = v0;
        ((zzbi)v0).zzao();
    }

    zzcd() {
        this(new double[10], 0);
    }

    private zzcd(double[] arg1, int arg2) {
        super();
        this.zzhm = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzc(arg3, ((Double)arg4).doubleValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzbi)this).zzap();
        zzct.checkNotNull(arg6);
        if(!(arg6 instanceof zzcd)) {
            return super.addAll(arg6);
        }

        if(((zzcd)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzcd)arg6).size) {
            int v0 = this.size + ((zzcd)arg6).size;
            if(v0 > this.zzhm.length) {
                this.zzhm = Arrays.copyOf(this.zzhm, v0);
            }

            System.arraycopy(((zzcd)arg6).zzhm, 0, this.zzhm, this.size, ((zzcd)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzcd)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzcd)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzcd)arg9).size) {
            return 0;
        }

        double[] v9 = ((zzcd)arg9).zzhm;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzhm[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg4) {
        this.zzi(arg4);
        return Double.valueOf(this.zzhm[arg4]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzct.zzk(Double.doubleToLongBits(this.zzhm[v1]));
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzbi)this).zzap();
        this.zzi(arg7);
        double v1 = this.zzhm[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzhm, arg7 + 1, this.zzhm, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Double.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzbi)this).zzap();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Double.valueOf(this.zzhm[v1]))) {
                System.arraycopy(this.zzhm, v1 + 1, this.zzhm, v1, this.size - v1);
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
            System.arraycopy(this.zzhm, arg5, this.zzhm, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        double v0 = ((Double)arg6).doubleValue();
        ((zzbi)this).zzap();
        this.zzi(arg5);
        double v2 = this.zzhm[arg5];
        this.zzhm[arg5] = v0;
        return Double.valueOf(v2);
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double arg2) {
        this.zzc(this.size, arg2);
    }

    private final void zzc(int arg5, double arg6) {
        ((zzbi)this).zzap();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzhm.length) {
                System.arraycopy(this.zzhm, arg5, this.zzhm, arg5 + 1, this.size - arg5);
            }
            else {
                double[] v0 = new double[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzhm, 0, v0, 0, arg5);
                System.arraycopy(this.zzhm, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzhm = v0;
            }

            this.zzhm[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzj(arg5));
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
            return new zzcd(Arrays.copyOf(this.zzhm, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

