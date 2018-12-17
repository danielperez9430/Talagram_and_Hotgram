package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbm extends zzbi implements zzcw, zzej, RandomAccess {
    private int size;
    private static final zzbm zzgr;
    private boolean[] zzgs;

    static {
        zzbm v0 = new zzbm();
        zzbm.zzgr = v0;
        ((zzbi)v0).zzao();
    }

    zzbm() {
        this(new boolean[10], 0);
    }

    private zzbm(boolean[] arg1, int arg2) {
        super();
        this.zzgs = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zza(arg1, ((Boolean)arg2).booleanValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzbi)this).zzap();
        zzct.checkNotNull(arg6);
        if(!(arg6 instanceof zzbm)) {
            return super.addAll(arg6);
        }

        if(((zzbm)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzbm)arg6).size) {
            int v0 = this.size + ((zzbm)arg6).size;
            if(v0 > this.zzgs.length) {
                this.zzgs = Arrays.copyOf(this.zzgs, v0);
            }

            System.arraycopy(((zzbm)arg6).zzgs, 0, this.zzgs, this.size, ((zzbm)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final void addBoolean(boolean arg2) {
        this.zza(this.size, arg2);
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzbm)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzbm)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzbm)arg6).size) {
            return 0;
        }

        boolean[] v6 = ((zzbm)arg6).zzgs;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzgs[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzi(arg2);
        return Boolean.valueOf(this.zzgs[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzct.zzc(this.zzgs[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzbi)this).zzap();
        this.zzi(arg6);
        boolean v0 = this.zzgs[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzgs, arg6 + 1, this.zzgs, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Boolean.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzbi)this).zzap();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Boolean.valueOf(this.zzgs[v1]))) {
                System.arraycopy(this.zzgs, v1 + 1, this.zzgs, v1, this.size - v1);
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
            System.arraycopy(this.zzgs, arg5, this.zzgs, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        boolean v4 = ((Boolean)arg4).booleanValue();
        ((zzbi)this).zzap();
        this.zzi(arg3);
        boolean v0 = this.zzgs[arg3];
        this.zzgs[arg3] = v4;
        return Boolean.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    private final void zza(int arg5, boolean arg6) {
        ((zzbi)this).zzap();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzgs.length) {
                System.arraycopy(this.zzgs, arg5, this.zzgs, arg5 + 1, this.size - arg5);
            }
            else {
                boolean[] v0 = new boolean[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzgs, 0, v0, 0, arg5);
                System.arraycopy(this.zzgs, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzgs = v0;
            }

            this.zzgs[arg5] = arg6;
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
            return new zzbm(Arrays.copyOf(this.zzgs, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

