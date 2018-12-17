package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzha extends zzfk implements zzhe, RandomAccess {
    private int size;
    private static final zzha zztj;
    private int[] zztk;

    static {
        zzha v0 = new zzha();
        zzha.zztj = v0;
        ((zzfk)v0).zzbb();
    }

    zzha() {
        this(new int[10], 0);
    }

    private zzha(int[] arg1, int arg2) {
        super();
        this.zztk = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzq(arg1, ((Integer)arg2).intValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzfk)this).zzbc();
        zzhb.checkNotNull(arg6);
        if(!(arg6 instanceof zzha)) {
            return super.addAll(arg6);
        }

        if(((zzha)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzha)arg6).size) {
            int v0 = this.size + ((zzha)arg6).size;
            if(v0 > this.zztk.length) {
                this.zztk = Arrays.copyOf(this.zztk, v0);
            }

            System.arraycopy(((zzha)arg6).zztk, 0, this.zztk, this.size, ((zzha)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzha)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzha)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzha)arg6).size) {
            return 0;
        }

        int[] v6 = ((zzha)arg6).zztk;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zztk[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg1) {
        return Integer.valueOf(this.getInt(arg1));
    }

    public final int getInt(int arg2) {
        this.zzac(arg2);
        return this.zztk[arg2];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + this.zztk[v1];
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzfk)this).zzbc();
        this.zzac(arg6);
        int v0 = this.zztk[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zztk, arg6 + 1, this.zztk, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Integer.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzfk)this).zzbc();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Integer.valueOf(this.zztk[v1]))) {
                System.arraycopy(this.zztk, v1 + 1, this.zztk, v1, this.size - v1);
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
            System.arraycopy(this.zztk, arg5, this.zztk, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        int v4 = ((Integer)arg4).intValue();
        ((zzfk)this).zzbc();
        this.zzac(arg3);
        int v0 = this.zztk[arg3];
        this.zztk[arg3] = v4;
        return Integer.valueOf(v0);
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

    public final zzhg zzae(int arg1) {
        return this.zzbd(arg1);
    }

    public final zzhe zzbd(int arg3) {
        if(arg3 >= this.size) {
            return new zzha(Arrays.copyOf(this.zztk, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzbe(int arg2) {
        this.zzq(this.size, arg2);
    }

    public static zzha zzdy() {
        return zzha.zztj;
    }

    private final void zzq(int arg5, int arg6) {
        ((zzfk)this).zzbc();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zztk.length) {
                System.arraycopy(this.zztk, arg5, this.zztk, arg5 + 1, this.size - arg5);
            }
            else {
                int[] v0 = new int[this.size * 3 / 2 + 1];
                System.arraycopy(this.zztk, 0, v0, 0, arg5);
                System.arraycopy(this.zztk, arg5, v0, arg5 + 1, this.size - arg5);
                this.zztk = v0;
            }

            this.zztk[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg5));
    }
}

