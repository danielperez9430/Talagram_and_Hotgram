package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzch extends zzav implements zzcn, RandomAccess {
    private int size;
    private static final zzch zzkr;
    private int[] zzks;

    static {
        zzch v0 = new zzch();
        zzch.zzkr = v0;
        ((zzav)v0).zzv();
    }

    zzch() {
        this(new int[10], 0);
    }

    private zzch(int[] arg1, int arg2) {
        super();
        this.zzks = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzo(arg1, ((Integer)arg2).intValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzav)this).zzw();
        zzci.checkNotNull(arg6);
        if(!(arg6 instanceof zzch)) {
            return super.addAll(arg6);
        }

        if(((zzch)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzch)arg6).size) {
            int v0 = this.size + ((zzch)arg6).size;
            if(v0 > this.zzks.length) {
                this.zzks = Arrays.copyOf(this.zzks, v0);
            }

            System.arraycopy(((zzch)arg6).zzks, 0, this.zzks, this.size, ((zzch)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzch)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzch)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzch)arg6).size) {
            return 0;
        }

        int[] v6 = ((zzch)arg6).zzks;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzks[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg1) {
        return Integer.valueOf(this.getInt(arg1));
    }

    public final int getInt(int arg2) {
        this.zzg(arg2);
        return this.zzks[arg2];
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + this.zzks[v1];
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzav)this).zzw();
        this.zzg(arg6);
        int v0 = this.zzks[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzks, arg6 + 1, this.zzks, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Integer.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzav)this).zzw();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Integer.valueOf(this.zzks[v1]))) {
                System.arraycopy(this.zzks, v1 + 1, this.zzks, v1, this.size - v1);
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
            System.arraycopy(this.zzks, arg5, this.zzks, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        int v4 = ((Integer)arg4).intValue();
        ((zzav)this).zzw();
        this.zzg(arg3);
        int v0 = this.zzks[arg3];
        this.zzks[arg3] = v4;
        return Integer.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    public final void zzac(int arg2) {
        this.zzo(this.size, arg2);
    }

    public static zzch zzbk() {
        return zzch.zzkr;
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
            return new zzch(Arrays.copyOf(this.zzks, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zzo(int arg5, int arg6) {
        ((zzav)this).zzw();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzks.length) {
                System.arraycopy(this.zzks, arg5, this.zzks, arg5 + 1, this.size - arg5);
            }
            else {
                int[] v0 = new int[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzks, 0, v0, 0, arg5);
                System.arraycopy(this.zzks, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzks = v0;
            }

            this.zzks[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzh(arg5));
    }
}

