package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzce extends zzav implements zzcn, RandomAccess {
    private int size;
    private static final zzce zzjm;
    private float[] zzjn;

    static {
        zzce v0 = new zzce();
        zzce.zzjm = v0;
        ((zzav)v0).zzv();
    }

    zzce() {
        this(new float[10], 0);
    }

    private zzce(float[] arg1, int arg2) {
        super();
        this.zzjn = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzc(arg1, ((Float)arg2).floatValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzav)this).zzw();
        zzci.checkNotNull(arg6);
        if(!(arg6 instanceof zzce)) {
            return super.addAll(arg6);
        }

        if(((zzce)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzce)arg6).size) {
            int v0 = this.size + ((zzce)arg6).size;
            if(v0 > this.zzjn.length) {
                this.zzjn = Arrays.copyOf(this.zzjn, v0);
            }

            System.arraycopy(((zzce)arg6).zzjn, 0, this.zzjn, this.size, ((zzce)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzce)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzce)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzce)arg6).size) {
            return 0;
        }

        float[] v6 = ((zzce)arg6).zzjn;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzjn[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzg(arg2);
        return Float.valueOf(this.zzjn[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + Float.floatToIntBits(this.zzjn[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzav)this).zzw();
        this.zzg(arg6);
        float v0 = this.zzjn[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzjn, arg6 + 1, this.zzjn, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Float.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzav)this).zzw();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Float.valueOf(this.zzjn[v1]))) {
                System.arraycopy(this.zzjn, v1 + 1, this.zzjn, v1, this.size - v1);
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
            System.arraycopy(this.zzjn, arg5, this.zzjn, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        float v4 = ((Float)arg4).floatValue();
        ((zzav)this).zzw();
        this.zzg(arg3);
        float v0 = this.zzjn[arg3];
        this.zzjn[arg3] = v4;
        return Float.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(float arg2) {
        this.zzc(this.size, arg2);
    }

    private final void zzc(int arg5, float arg6) {
        ((zzav)this).zzw();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzjn.length) {
                System.arraycopy(this.zzjn, arg5, this.zzjn, arg5 + 1, this.size - arg5);
            }
            else {
                float[] v0 = new float[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzjn, 0, v0, 0, arg5);
                System.arraycopy(this.zzjn, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzjn = v0;
            }

            this.zzjn[arg5] = arg6;
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
            return new zzce(Arrays.copyOf(this.zzjn, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

