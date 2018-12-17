package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzub extends zztz implements zzvs, zzxe, RandomAccess {
    private int size;
    private static final zzub zzbtx;
    private boolean[] zzbty;

    static {
        zzub v0 = new zzub();
        zzub.zzbtx = v0;
        ((zztz)v0).zzsm();
    }

    zzub() {
        this(new boolean[10], 0);
    }

    private zzub(boolean[] arg1, int arg2) {
        super();
        this.zzbty = arg1;
        this.size = arg2;
    }

    public final void add(int arg1, Object arg2) {
        this.zza(arg1, ((Boolean)arg2).booleanValue());
    }

    public final boolean addAll(Collection arg6) {
        ((zztz)this).zztx();
        zzvo.checkNotNull(arg6);
        if(!(arg6 instanceof zzub)) {
            return super.addAll(arg6);
        }

        if(((zzub)arg6).size == 0) {
            return 0;
        }

        if(2147483647 - this.size >= ((zzub)arg6).size) {
            int v0 = this.size + ((zzub)arg6).size;
            if(v0 > this.zzbty.length) {
                this.zzbty = Arrays.copyOf(this.zzbty, v0);
            }

            System.arraycopy(((zzub)arg6).zzbty, 0, this.zzbty, this.size, ((zzub)arg6).size);
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
        if(this == (((zzub)arg6))) {
            return 1;
        }

        if(!(arg6 instanceof zzub)) {
            return super.equals(arg6);
        }

        if(this.size != ((zzub)arg6).size) {
            return 0;
        }

        boolean[] v6 = ((zzub)arg6).zzbty;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(this.zzbty[v1] != v6[v1]) {
                return 0;
            }
        }

        return 1;
    }

    public final Object get(int arg2) {
        this.zzai(arg2);
        return Boolean.valueOf(this.zzbty[arg2]);
    }

    public final int hashCode() {
        int v0 = 1;
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            v0 = v0 * 31 + zzvo.zzw(this.zzbty[v1]);
        }

        return v0;
    }

    public final Object remove(int arg6) {
        ((zztz)this).zztx();
        this.zzai(arg6);
        boolean v0 = this.zzbty[arg6];
        if(arg6 < this.size - 1) {
            System.arraycopy(this.zzbty, arg6 + 1, this.zzbty, arg6, this.size - arg6);
        }

        --this.size;
        ++this.modCount;
        return Boolean.valueOf(v0);
    }

    public final boolean remove(Object arg5) {
        ((zztz)this).zztx();
        int v1;
        for(v1 = 0; v1 < this.size; ++v1) {
            if(arg5.equals(Boolean.valueOf(this.zzbty[v1]))) {
                System.arraycopy(this.zzbty, v1 + 1, this.zzbty, v1, this.size - v1);
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
            System.arraycopy(this.zzbty, arg5, this.zzbty, arg4, this.size - arg5);
            this.size -= arg5 - arg4;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int arg3, Object arg4) {
        boolean v4 = ((Boolean)arg4).booleanValue();
        ((zztz)this).zztx();
        this.zzai(arg3);
        boolean v0 = this.zzbty[arg3];
        this.zzbty[arg3] = v4;
        return Boolean.valueOf(v0);
    }

    public final int size() {
        return this.size;
    }

    private final void zza(int arg5, boolean arg6) {
        ((zztz)this).zztx();
        if(arg5 >= 0 && arg5 <= this.size) {
            if(this.size < this.zzbty.length) {
                System.arraycopy(this.zzbty, arg5, this.zzbty, arg5 + 1, this.size - arg5);
            }
            else {
                boolean[] v0 = new boolean[this.size * 3 / 2 + 1];
                System.arraycopy(this.zzbty, 0, v0, 0, arg5);
                System.arraycopy(this.zzbty, arg5, v0, arg5 + 1, this.size - arg5);
                this.zzbty = v0;
            }

            this.zzbty[arg5] = arg6;
            ++this.size;
            ++this.modCount;
            return;
        }

        throw new IndexOutOfBoundsException(this.zzaj(arg5));
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
            return new zzub(Arrays.copyOf(this.zzbty, arg3), this.size);
        }

        throw new IllegalArgumentException();
    }
}

