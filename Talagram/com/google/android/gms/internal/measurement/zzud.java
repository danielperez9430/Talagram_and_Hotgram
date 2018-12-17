package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;

public abstract class zzud implements Serializable, Iterable {
    private int zzbry;
    public static final zzud zzbtz;
    private static final zzui zzbua;
    private static final Comparator zzbub;

    static {
        zzug v0_1;
        zzud.zzbtz = new zzum(zzvo.zzbzj);
        zzue v1 = null;
        if(zzua.zzty()) {
            zzun v0 = new zzun(v1);
        }
        else {
            v0_1 = new zzug(v1);
        }

        zzud.zzbua = ((zzui)v0_1);
        zzud.zzbub = new zzuf();
    }

    zzud() {
        super();
        this.zzbry = 0;
    }

    public abstract boolean equals(Object arg1);

    public final int hashCode() {
        int v0 = this.zzbry;
        if(v0 == 0) {
            v0 = this.size();
            v0 = this.zza(v0, 0, v0);
            if(v0 == 0) {
                v0 = 1;
            }

            this.zzbry = v0;
        }

        return v0;
    }

    public Iterator iterator() {
        return new zzue(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(this.size()));
    }

    private static int zza(byte arg0) {
        return arg0 & 255;
    }

    protected abstract int zza(int arg1, int arg2, int arg3);

    protected abstract String zza(Charset arg1);

    abstract void zza(zzuc arg1);

    public abstract byte zzal(int arg1);

    static zzuk zzam(int arg2) {
        return new zzuk(arg2, null);
    }

    static int zzb(byte arg0) {
        return zzud.zza(arg0);
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

    public static zzud zzb(byte[] arg2, int arg3, int arg4) {
        zzud.zzb(arg3, arg3 + arg4, arg2.length);
        return new zzum(zzud.zzbua.zzc(arg2, arg3, arg4));
    }

    public abstract zzud zzb(int arg1, int arg2);

    public static zzud zzfv(String arg2) {
        return new zzum(arg2.getBytes(zzvo.UTF_8));
    }

    static zzud zzi(byte[] arg1) {
        return new zzum(arg1);
    }

    public final String zzua() {
        Charset v0 = zzvo.UTF_8;
        if(this.size() == 0) {
            return "";
        }

        return this.zza(v0);
    }

    public abstract boolean zzub();

    protected final int zzuc() {
        return this.zzbry;
    }
}

