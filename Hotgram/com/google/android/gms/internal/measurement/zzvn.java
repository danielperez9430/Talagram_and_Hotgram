package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzvn extends zztz implements zzvs, zzxe, RandomAccess {
    private int size;
    private static final zzvn zzbzh;
    private int[] zzbzi;

    static {
        zzvn v0 = new zzvn();
        zzvn.zzbzh = v0;
        ((zztz)v0).zzsm();
    }

    zzvn() {
        this(new int[10], 0);
    }

    private zzvn(int[] arg1, int arg2) {
        super();
        this.zzbzi = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzp(arg1, ((Integer)arg2).intValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zztz)this).zztx();
        zzvo.checkNotNull(arg6);
        if(!(arg6 instanceof zzvn)) {
            return super.addAll(arg6);
        }

        if(((zzvn)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzvn)arg6).size) {
            int v0 = this.size + ((zzvn)arg6).size;
            if(v0 > this.zzbzi.length) {
                this.zzbzi = Arrays.copyOf(this.zzbzi, v0);
            }

            System.arraycopy(((zzvn)arg6).zzbzi, 0, this.zzbzi, this.size, ((zzvn)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzvn)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzvn)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzvn)arg6).size) {
            return 0;
        }

        int[] v6 = ((zzvn)arg6).zzbzi;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzbzi[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg1) {
        return Integer.valueOf(this.getInt(arg1));
    }

    public final int getInt(int arg2) {
        this.zzai(arg2);
        return this.zzbzi[arg2];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + this.zzbzi[v1];
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zztz)this).zztx();
        this.zzai(arg6);
        int v0 = this.zzbzi[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzbzi, arg6 + 1, this.zzbzi, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Integer.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zztz)this).zztx();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Integer.valueOf(this.zzbzi[v1]))) {
                System.arraycopy(this.zzbzi, v1 + 1, this.zzbzi, v1, this.size - v1);
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
            System.arraycopy(this.zzbzi, arg5, this.zzbzi, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        int v4 = ((Integer)arg4).intValue();
        ((zztz)this).zztx();
        this.zzai(arg3);
        int v0 = this.zzbzi[arg3];
        this.zzbzi[arg3] = v4;
        return Integer.valueOf(v0);
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
            return new zzvn(Arrays.copyOf(this.zzbzi, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    public final void zzbm(int arg2) {
        this.zzp(this.size, arg2);
    }

    private final void zzp(int arg5, int arg6) {
        ((zztz)this).zztx();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzbzi.length) {
                System.arraycopy(this.zzbzi, arg5, this.zzbzi, arg5 + 1, this.size - arg5);
            }
            else {
                int[] v0 = new int[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzbzi, 0, v0, 0, arg5);
                System.arraycopy(this.zzbzi, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzbzi = v0;
            }

            this.zzbzi[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzaj(arg5));
    }
}

