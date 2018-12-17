package com.google.android.gms.internal.places;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfp extends zzfk implements zzhg, RandomAccess {
    private int size;
    private static final zzfp zznr;
    private boolean[] zzns;

    static {
        zzfp v0 = new zzfp();
        zzfp.zznr = v0;
        ((zzfk)v0).zzbb();
    }

    zzfp() {
        this(new boolean[10], 0);
    }

    private zzfp(boolean[] arg1, int arg2) {
        super();
        this.zzns = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zzb(arg1, ((Boolean)arg2).booleanValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zzfk)this).zzbc();
        zzhb.checkNotNull(arg6);
        if(!(arg6 instanceof zzfp)) {
            return super.addAll(arg6);
        }

        if(((zzfp)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzfp)arg6).size) {
            int v0 = this.size + ((zzfp)arg6).size;
            if(v0 > this.zzns.length) {
                this.zzns = Arrays.copyOf(this.zzns, v0);
            }

            System.arraycopy(((zzfp)arg6).zzns, 0, this.zzns, this.size, ((zzfp)arg6).size);
            this.size = v0;
            ++this.modCount;
            return 1;
        }

        throw new OutOfMemoryError();
    }

    public final void addBoolean(boolean arg2) {
        this.zzb(this.size, arg2);
    }

    public final boolean equals(Object arg6) {
        if(this == (((zzfp)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzfp)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzfp)arg6).size) {
            return 0;
        }

        boolean[] v6 = ((zzfp)arg6).zzns;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzns[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzac(arg2);
        return Boolean.valueOf(this.zzns[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzhb.zzf(this.zzns[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zzfk)this).zzbc();
        this.zzac(arg6);
        boolean v0 = this.zzns[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzns, arg6 + 1, this.zzns, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Boolean.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zzfk)this).zzbc();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Boolean.valueOf(this.zzns[v1]))) {
                System.arraycopy(this.zzns, v1 + 1, this.zzns, v1, this.size - v1);
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
            System.arraycopy(this.zzns, arg5, this.zzns, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        boolean v4 = ((Boolean)arg4).booleanValue();
        ((zzfk)this).zzbc();
        this.zzac(arg3);
        boolean v0 = this.zzns[arg3];
        this.zzns[arg3] = v4;
        return Boolean.valueOf(v0);
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
            return new zzfp(Arrays.copyOf(this.zzns, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }

    private final void zzb(int arg5, boolean arg6) {
        ((zzfk)this).zzbc();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzns.length) {
                System.arraycopy(this.zzns, arg5, this.zzns, arg5 + 1, this.size - arg5);
            }
            else {
                boolean[] v0 = new boolean[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzns, 0, v0, 0, arg5);
                System.arraycopy(this.zzns, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzns = v0;
            }

            this.zzns[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzad(arg5));
    }
}

