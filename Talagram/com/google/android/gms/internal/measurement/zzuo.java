package com.google.android.gms.internal.measurement;

public abstract class zzuo {
    int zzbuh;
    int zzbui;
    private int zzbuj;
    zzur zzbuk;
    private boolean zzbul;

    private zzuo() {
        super();
        this.zzbui = 100;
        this.zzbuj = 2147483647;
        this.zzbul = false;
    }

    zzuo(zzup arg1) {
        this();
    }

    public abstract double readDouble();

    public abstract float readFloat();

    public abstract String readString();

    static zzuo zza(byte[] arg6, int arg7, int arg8, boolean arg9) {
        zzuq v9 = new zzuq(arg6, arg7, arg8, false, null);
        try {
            ((zzuo)v9).zzaq(arg8);
            return ((zzuo)v9);
        }
        catch(zzvt v6) {
            throw new IllegalArgumentException(((Throwable)v6));
        }
    }

    public abstract zzwt zza(zzxd arg1, zzuz arg2);

    public abstract void zzan(int arg1);

    public abstract boolean zzao(int arg1);

    public final int zzap(int arg4) {
        if(arg4 >= 0) {
            int v0 = this.zzbui;
            this.zzbui = arg4;
            return v0;
        }

        StringBuilder v2 = new StringBuilder(47);
        v2.append("Recursion limit cannot be negative: ");
        v2.append(arg4);
        throw new IllegalArgumentException(v2.toString());
    }

    public abstract int zzaq(int arg1);

    public abstract void zzar(int arg1);

    public abstract void zzas(int arg1);

    public static zzuo zzd(byte[] arg1, int arg2, int arg3) {
        return zzuo.zza(arg1, arg2, arg3, false);
    }

    public abstract int zzug();

    public abstract long zzuh();

    public abstract long zzui();

    public abstract int zzuj();

    public abstract long zzuk();

    public abstract int zzul();

    public abstract boolean zzum();

    public abstract String zzun();

    public abstract zzud zzuo();

    public abstract int zzup();

    public abstract int zzuq();

    public abstract int zzur();

    public abstract long zzus();

    public abstract int zzut();

    public abstract long zzuu();

    abstract long zzuv();

    public abstract boolean zzuw();

    public abstract int zzux();
}

