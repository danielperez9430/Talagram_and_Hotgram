package com.google.android.gms.internal.clearcut;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzbb implements Serializable, Iterable {
    public static final zzbb zzfi;
    private static final zzbf zzfj;
    private int zzfk;

    static {
        zzbj v0;
        zzbb.zzfi = new zzbi(zzci.zzkt);
        zzbc v1 = null;
        if(zzaw.zzx()) {
            v0 = new zzbj(v1);
        }
        else {
            zzbd v0_1 = new zzbd(v1);
        }

        zzbb.zzfj = ((zzbf)v0);
    }

    zzbb() {
        super();
        this.zzfk = 0;
    }

    public abstract boolean equals(Object arg1);

    public final int hashCode() {
        int v0 = this.zzfk;
        if(v0 == 0) {
            v0 = this.size();
            v0 = this.zza(v0, 0, v0);
            if(v0 == 0) {
                v0 = 1;
            }

            this.zzfk = v0;
        }

        return v0;
    }

    public Iterator iterator() {
        return new zzbc(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(this.size()));
    }

    protected abstract int zza(int arg1, int arg2, int arg3);

    public abstract zzbb zza(int arg1, int arg2);

    protected abstract String zza(Charset arg1);

    abstract void zza(zzba arg1);

    public abstract boolean zzaa();

    protected final int zzab() {
        return this.zzfk;
    }

    public static zzbb zzb(byte[] arg2, int arg3, int arg4) {
        return new zzbi(zzbb.zzfj.zzc(arg2, arg3, arg4));
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

    public static zzbb zzf(String arg2) {
        return new zzbi(arg2.getBytes(zzci.UTF_8));
    }

    public abstract byte zzj(int arg1);

    static zzbg zzk(int arg2) {
        return new zzbg(arg2, null);
    }

    public final String zzz() {
        Charset v0 = zzci.UTF_8;
        if(this.size() == 0) {
            return "";
        }

        return this.zza(v0);
    }
}

