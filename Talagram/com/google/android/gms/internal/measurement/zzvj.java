package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzvj extends zztz implements zzvs, zzxe, RandomAccess {
    private int size;
    private static final zzvj zzbyi;
    private float[] zzbyj;

    static {
        zzvj v0 = new zzvj();
        zzvj.zzbyi = v0;
        ((zztz)v0).zzsm();
    }

    zzvj() {
        this(new float[10], 0);
    }

    private zzvj(float[] arg1, int arg2) {
        super();
        this.zzbyj = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzc(arg1, ((Float)arg2).floatValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zztz)this).zztx();
        zzvo.checkNotNull(arg6);
        if(!(arg6 instanceof zzvj)) {
            return super.addAll(arg6);
        }

        if(((zzvj)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzvj)arg6).size) {
            int v0 = this.size + ((zzvj)arg6).size;
            if(v0 > this.zzbyj.length) {
                this.zzbyj = Arrays.copyOf(this.zzbyj, v0);
            }

            System.arraycopy(((zzvj)arg6).zzbyj, 0, this.zzbyj, this.size, ((zzvj)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzvj)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzvj)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzvj)arg6).size) {
            return 0;
        }

        float[] v6 = ((zzvj)arg6).zzbyj;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzbyj[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzai(arg2);
        return Float.valueOf(this.zzbyj[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + Float.floatToIntBits(this.zzbyj[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zztz)this).zztx();
        this.zzai(arg6);
        float v0 = this.zzbyj[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzbyj, arg6 + 1, this.zzbyj, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Float.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zztz)this).zztx();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Float.valueOf(this.zzbyj[v1]))) {
                System.arraycopy(this.zzbyj, v1 + 1, this.zzbyj, v1, this.size - v1);
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
            System.arraycopy(this.zzbyj, arg5, this.zzbyj, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        float v4 = ((Float)arg4).floatValue();
        ((zztz)this).zztx();
        this.zzai(arg3);
        float v0 = this.zzbyj[arg3];
        this.zzbyj[arg3] = v4;
        return Float.valueOf(v0);
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
            return new zzvj(Arrays.copyOf(this.zzbyj, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzc(float arg2) {
        this.zzc(this.size, arg2);
    }

    private final void zzc(int arg5, float arg6) {
        ((zztz)this).zztx();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzbyj.length) {
                System.arraycopy(this.zzbyj, arg5, this.zzbyj, arg5 + 1, this.size - arg5);
            }
            else {
                float[] v0 = new float[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzbyj, 0, v0, 0, arg5);
                System.arraycopy(this.zzbyj, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzbyj = v0;
            }

            this.zzbyj[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzaj(arg5));
    }
}

