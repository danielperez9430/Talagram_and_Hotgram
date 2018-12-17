package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzbo implements Serializable, Iterable {
    public static final zzbo zzgt;
    private static final zzbs zzgu;
    private int zzgv;

    static {
        zzbq v0_1;
        zzbo.zzgt = new zzbv(zzct.zzlo);
        zzbp v1 = null;
        if(zzbj.zzaq()) {
            zzbw v0 = new zzbw(v1);
        }
        else {
            v0_1 = new zzbq(v1);
        }

        zzbo.zzgu = ((zzbs)v0_1);
    }

    zzbo() {
        super();
        this.zzgv = 0;
    }

    public abstract boolean equals(Object arg1);

    public final int hashCode() {
        int v0 = this.zzgv;
        if(v0 == 0) {
            v0 = this.size();
            v0 = this.zza(v0, 0, v0);
            if(v0 == 0) {
                v0 = 1;
            }

            this.zzgv = v0;
        }

        return v0;
    }

    public Iterator iterator() {
        return new zzbp(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(this.size()));
    }

    protected abstract int zza(int arg1, int arg2, int arg3);

    protected abstract String zza(Charset arg1);

    abstract void zza(zzbn arg1);

    public final String zzas() {
        Charset v0 = zzct.UTF_8;
        if(this.size() == 0) {
            return "";
        }

        return this.zza(v0);
    }

    public abstract boolean zzat();

    protected final int zzau() {
        return this.zzgv;
    }

    public static zzbo zzb(byte[] arg2, int arg3, int arg4) {
        return new zzbv(zzbo.zzgu.zzc(arg2, arg3, arg4));
    }

    static int zzb(int arg3, int arg4, int arg5) {
        StringBuilder v1;
        int v0 = arg4 - arg3;
        if((arg3 | arg4 | v0 | arg5 - arg4) < 0) {
            if(arg3 >= 0) {
                if(arg4 < arg3) {
                    v1 = new StringBuilder(66);
                    v1.append("Beginning index larger than ending index: ");
                    v1.append(arg3);
                    v1.append(", ");
                    v1.append(arg4);
                    throw new IndexOutOfBoundsException(v1.toString());
                }

                v1 = new StringBuilder(37);
                v1.append("End index: ");
                v1.append(arg4);
                v1.append(" >= ");
                v1.append(arg5);
                throw new IndexOutOfBoundsException(v1.toString());
            }

            StringBuilder v0_1 = new StringBuilder(32);
            v0_1.append("Beginning index: ");
            v0_1.append(arg3);
            v0_1.append(" < 0");
            throw new IndexOutOfBoundsException(v0_1.toString());
        }

        return v0;
    }

    public abstract zzbo zzc(int arg1, int arg2);

    public static zzbo zzg(String arg2) {
        return new zzbv(arg2.getBytes(zzct.UTF_8));
    }

    public abstract byte zzl(int arg1);

    static zzbt zzm(int arg2) {
        return new zzbt(arg2, null);
    }
}

