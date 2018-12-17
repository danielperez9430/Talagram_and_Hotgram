package com.google.android.gms.internal.places;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzfr implements Serializable, Iterable {
    public static final zzfr zznt;
    private static final zzfv zznu;
    private int zznv;

    static {
        zzfz v0;
        zzfr.zznt = new zzfy(zzhb.zztl);
        zzfs v1 = null;
        if(zzfl.zzbd()) {
            v0 = new zzfz(v1);
        }
        else {
            zzft v0_1 = new zzft(v1);
        }

        zzfr.zznu = ((zzfv)v0);
    }

    zzfr() {
        super();
        this.zznv = 0;
    }

    public abstract boolean equals(Object arg1);

    public final int hashCode() {
        int v0 = this.zznv;
        if(v0 == 0) {
            v0 = this.size();
            v0 = this.zzb(v0, 0, v0);
            if(v0 == 0) {
                v0 = 1;
            }

            this.zznv = v0;
        }

        return v0;
    }

    public Iterator iterator() {
        return new zzfs(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(this.size()));
    }

    public abstract byte zzaf(int arg1);

    static zzfw zzag(int arg2) {
        return new zzfw(arg2, null);
    }

    protected abstract int zzb(int arg1, int arg2, int arg3);

    protected abstract String zzb(Charset arg1);

    abstract void zzb(zzfq arg1);

    protected abstract void zzb(byte[] arg1, int arg2, int arg3, int arg4);

    static int zzc(int arg3, int arg4, int arg5) {
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

    static zzfr zzc(byte[] arg1) {
        return new zzfy(arg1);
    }

    public static zzfr zzc(byte[] arg2, int arg3, int arg4) {
        return new zzfy(zzfr.zznu.zze(arg2, arg3, arg4));
    }

    public abstract zzfr zzc(int arg1, int arg2);

    public final String zzcd() {
        Charset v0 = zzhb.UTF_8;
        if(this.size() == 0) {
            return "";
        }

        return this.zzb(v0);
    }

    public abstract boolean zzce();

    protected final int zzcf() {
        return this.zznv;
    }

    static zzfr zzd(byte[] arg1, int arg2, int arg3) {
        return new zzfu(arg1, arg2, arg3);
    }

    public static zzfr zzj(String arg2) {
        return new zzfy(arg2.getBytes(zzhb.UTF_8));
    }
}

