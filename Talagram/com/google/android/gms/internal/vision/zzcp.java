package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcp extends zzbi implements zzcw, zzej, RandomAccess {
    private int size;
    private static final zzcp zzko;
    private float[] zzkp;

    static {
        zzcp v0 = new zzcp();
        zzcp.zzko = v0;
        ((zzbi)v0).zzao();
    }

    zzcp() {
        this(new float[10], 0);
    }

    private zzcp(float[] arg1, int arg2) {
        super();
        this.zzkp = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzc(arg1, ((Float)arg2).floatValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzbi)this).zzap();
        zzct.checkNotNull(arg6);
        if(!(arg6 instanceof zzcp)) {
            return super.addAll(arg6);
        }

        if(((zzcp)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzcp)arg6).size) {
            int v0 = this.size + ((zzcp)arg6).size;
            if(v0 > this.zzkp.length) {
                this.zzkp = Arrays.copyOf(this.zzkp, v0);
            }

            System.arraycopy(((zzcp)arg6).zzkp, 0, this.zzkp, this.size, ((zzcp)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzcp)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzcp)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzcp)arg6).size) {
            return 0;
        }

        float[] v6 = ((zzcp)arg6).zzkp;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzkp[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzi(arg2);
        return Float.valueOf(this.zzkp[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + Float.floatToIntBits(this.zzkp[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzbi)this).zzap();
        this.zzi(arg6);
        float v0 = this.zzkp[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzkp, arg6 + 1, this.zzkp, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Float.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzbi)this).zzap();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Float.valueOf(this.zzkp[v1]))) {
                System.arraycopy(this.zzkp, v1 + 1, this.zzkp, v1, this.size - v1);
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
            System.arraycopy(this.zzkp, arg5, this.zzkp, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        float v4 = ((Float)arg4).floatValue();
        ((zzbi)this).zzap();
        this.zzi(arg3);
        float v0 = this.zzkp[arg3];
        this.zzkp[arg3] = v4;
        return Float.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    private final void zzc(int arg5, float arg6) {
        ((zzbi)this).zzap();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzkp.length) {
                System.arraycopy(this.zzkp, arg5, this.zzkp, arg5 + 1, this.size - arg5);
            }
            else {
                float[] v0 = new float[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzkp, 0, v0, 0, arg5);
                System.arraycopy(this.zzkp, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzkp = v0;
            }

            this.zzkp[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzj(arg5));
    }

    public final void zze(float arg2) {
        this.zzc(this.size, arg2);
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
            return new zzcp(Arrays.copyOf(this.zzkp, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

