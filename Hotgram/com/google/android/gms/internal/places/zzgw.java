package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzgw extends zzfk implements zzhg, RandomAccess {
    private int size;
    private static final zzgw zzsc;
    private float[] zzsd;

    static {
        zzgw v0 = new zzgw();
        zzgw.zzsc = v0;
        ((zzfk)v0).zzbb();
    }

    zzgw() {
        this(new float[10], 0);
    }

    private zzgw(float[] arg1, int arg2) {
        super();
        this.zzsd = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zze(arg1, ((Float)arg2).floatValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzfk)this).zzbc();
        zzhb.checkNotNull(arg6);
        if(!(arg6 instanceof zzgw)) {
            return super.addAll(arg6);
        }

        if(((zzgw)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzgw)arg6).size) {
            int v0 = this.size + ((zzgw)arg6).size;
            if(v0 > this.zzsd.length) {
                this.zzsd = Arrays.copyOf(this.zzsd, v0);
            }

            System.arraycopy(((zzgw)arg6).zzsd, 0, this.zzsd, this.size, ((zzgw)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzgw)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzgw)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzgw)arg6).size) {
            return 0;
        }

        float[] v6 = ((zzgw)arg6).zzsd;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzsd[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzac(arg2);
        return Float.valueOf(this.zzsd[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + Float.floatToIntBits(this.zzsd[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzfk)this).zzbc();
        this.zzac(arg6);
        float v0 = this.zzsd[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzsd, arg6 + 1, this.zzsd, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Float.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzfk)this).zzbc();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Float.valueOf(this.zzsd[v1]))) {
                System.arraycopy(this.zzsd, v1 + 1, this.zzsd, v1, this.size - v1);
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
            System.arraycopy(this.zzsd, arg5, this.zzsd, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        float v4 = ((Float)arg4).floatValue();
        ((zzfk)this).zzbc();
        this.zzac(arg3);
        float v0 = this.zzsd[arg3];
        this.zzsd[arg3] = v4;
        return Float.valueOf(v0);
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
            return new zzgw(Arrays.copyOf(this.zzsd, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zze(int arg5, float arg6) {
        ((zzfk)this).zzbc();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzsd.length) {
                System.arraycopy(this.zzsd, arg5, this.zzsd, arg5 + 1, this.size - arg5);
            }
            else {
                float[] v0 = new float[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzsd, 0, v0, 0, arg5);
                System.arraycopy(this.zzsd, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzsd = v0;
            }

            this.zzsd[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg5));
    }

    public final void zzf(float arg2) {
        this.zze(this.size, arg2);
    }
}

