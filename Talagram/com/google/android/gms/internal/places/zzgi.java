package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzgi extends zzfk implements zzhg, RandomAccess {
    private int size;
    private static final zzgi zzoz;
    private double[] zzpa;

    static {
        zzgi v0 = new zzgi();
        zzgi.zzoz = v0;
        ((zzfk)v0).zzbb();
    }

    zzgi() {
        this(new double[10], 0);
    }

    private zzgi(double[] arg1, int arg2) {
        super();
        this.zzpa = arg1;
        this.size = arg2;
    }

    public final void add(int arg3, Object arg4) {
        this.zzd(arg3, ((Double)arg4).doubleValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzfk)this).zzbc();
        zzhb.checkNotNull(arg6);
        if(!(arg6 instanceof zzgi)) {
            return super.addAll(arg6);
        }

        if(((zzgi)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzgi)arg6).size) {
            int v0 = this.size + ((zzgi)arg6).size;
            if(v0 > this.zzpa.length) {
                this.zzpa = Arrays.copyOf(this.zzpa, v0);
            }

            System.arraycopy(((zzgi)arg6).zzpa, 0, this.zzpa, this.size, ((zzgi)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg9) {
        if(this == (((zzgi)arg9))) {
            return 1;
        }

        if(!(arg9 instanceof zzgi)) {
            return super.equals(arg9);
        }

        if(this.size != ((zzgi)arg9).size) {
            return 0;
        }

        double[] v9 = ((zzgi)arg9).zzpa;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzpa[v1] != v9[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg4) {
        this.zzac(arg4);
        return Double.valueOf(this.zzpa[arg4]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzhb.zzo(Double.doubleToLongBits(this.zzpa[v1]));
        }

        return v0;
    }

    public final Object remove(int arg7) {
        ((zzfk)this).zzbc();
        this.zzac(arg7);
        double v1 = this.zzpa[arg7];
        if(arg7 < this.size - 1) {
            System.arraycopy(this.zzpa, arg7 + 1, this.zzpa, arg7, this.size - arg7);
        }

        --this.size;
        ++this.modCount;
        return Double.valueOf(v1);
    }

    public final boolean remove(Object arg6) {
        ((zzfk)this).zzbc();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg6.equals(Double.valueOf(this.zzpa[v1]))) {
                System.arraycopy(this.zzpa, v1 + 1, this.zzpa, v1, this.size - v1);
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
            System.arraycopy(this.zzpa, arg5, this.zzpa, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg5, Object arg6) {
        double v0 = ((Double)arg6).doubleValue();
        ((zzfk)this).zzbc();
        this.zzac(arg5);
        double v2 = this.zzpa[arg5];
        this.zzpa[arg5] = v0;
        return Double.valueOf(v2);
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
            return new zzgi(Arrays.copyOf(this.zzpa, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzd(double arg2) {
        this.zzd(this.size, arg2);
    }

    private final void zzd(int arg5, double arg6) {
        ((zzfk)this).zzbc();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzpa.length) {
                System.arraycopy(this.zzpa, arg5, this.zzpa, arg5 + 1, this.size - arg5);
            }
            else {
                double[] v0 = new double[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzpa, 0, v0, 0, arg5);
                System.arraycopy(this.zzpa, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzpa = v0;
            }

            this.zzpa[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg5));
    }
}

