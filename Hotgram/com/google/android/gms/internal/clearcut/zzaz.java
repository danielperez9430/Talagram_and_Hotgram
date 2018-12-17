package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzaz extends zzav implements zzcn, RandomAccess {
    private int size;
    private static final zzaz zzfg;
    private boolean[] zzfh;

    static {
        zzaz v0 = new zzaz();
        zzaz.zzfg = v0;
        ((zzav)v0).zzv();
    }

    zzaz() {
        this(new boolean[10], 0);
    }

    private zzaz(boolean[] arg1, int arg2) {
        super();
        this.zzfh = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zza(arg1, ((Boolean)arg2).booleanValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzav)this).zzw();
        zzci.checkNotNull(arg6);
        if(!(arg6 instanceof zzaz)) {
            return super.addAll(arg6);
        }

        if(((zzaz)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzaz)arg6).size) {
            int v0 = this.size + ((zzaz)arg6).size;
            if(v0 > this.zzfh.length) {
                this.zzfh = Arrays.copyOf(this.zzfh, v0);
            }

            System.arraycopy(((zzaz)arg6).zzfh, 0, this.zzfh, this.size, ((zzaz)arg6).size);
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
        if(this == (((zzaz)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzaz)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzaz)arg6).size) {
            return 0;
        }

        boolean[] v6 = ((zzaz)arg6).zzfh;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzfh[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzg(arg2);
        return Boolean.valueOf(this.zzfh[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzci.zzc(this.zzfh[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzav)this).zzw();
        this.zzg(arg6);
        boolean v0 = this.zzfh[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzfh, arg6 + 1, this.zzfh, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Boolean.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzav)this).zzw();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Boolean.valueOf(this.zzfh[v1]))) {
                System.arraycopy(this.zzfh, v1 + 1, this.zzfh, v1, this.size - v1);
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
            System.arraycopy(this.zzfh, arg5, this.zzfh, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        boolean v4 = ((Boolean)arg4).booleanValue();
        ((zzav)this).zzw();
        this.zzg(arg3);
        boolean v0 = this.zzfh[arg3];
        this.zzfh[arg3] = v4;
        return Boolean.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    private final void zza(int arg5, boolean arg6) {
        ((zzav)this).zzw();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzfh.length) {
                System.arraycopy(this.zzfh, arg5, this.zzfh, arg5 + 1, this.size - arg5);
            }
            else {
                boolean[] v0 = new boolean[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzfh, 0, v0, 0, arg5);
                System.arraycopy(this.zzfh, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzfh = v0;
            }

            this.zzfh[arg5] = arg6;
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
            return new zzaz(Arrays.copyOf(this.zzfh, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

