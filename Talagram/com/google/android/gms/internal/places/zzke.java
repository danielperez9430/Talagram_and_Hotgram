package com.google.android.gms.internal.places;

public enum zzke {
    public static final enum zzke zzyt;
    public static final enum zzke zzyu;
    public static final enum zzke zzyv;
    public static final enum zzke zzyw;
    public static final enum zzke zzyx;
    public static final enum zzke zzyy;
    public static final enum zzke zzyz;
    public static final enum zzke zzza;
    public static final enum zzke zzzb;
    public static final enum zzke zzzc;
    public static final enum zzke zzzd;
    public static final enum zzke zzze;
    public static final enum zzke zzzf;
    public static final enum zzke zzzg;
    public static final enum zzke zzzh;
    public static final enum zzke zzzi;
    public static final enum zzke zzzj;
    public static final enum zzke zzzk;
    private final zzkj zzzl;
    private final int zzzm;

    static {
        zzke.zzyt = new zzke("DOUBLE", 0, zzkj.zzzr, 1);
        zzke.zzyu = new zzke("FLOAT", 1, zzkj.zzzq, 5);
        zzke.zzyv = new zzke("INT64", 2, zzkj.zzzp, 0);
        zzke.zzyw = new zzke("UINT64", 3, zzkj.zzzp, 0);
        zzke.zzyx = new zzke("INT32", 4, zzkj.zzzo, 0);
        zzke.zzyy = new zzke("FIXED64", 5, zzkj.zzzp, 1);
        zzke.zzyz = new zzke("FIXED32", 6, zzkj.zzzo, 5);
        zzke.zzza = new zzke("BOOL", 7, zzkj.zzzs, 0);
        zzke.zzzb = new zzkf("STRING", 8, zzkj.zzzt, 2);
        zzke.zzzc = new zzkg("GROUP", 9, zzkj.zzzw, 3);
        zzke.zzzd = new zzkh("MESSAGE", 10, zzkj.zzzw, 2);
        zzke.zzze = new zzki("BYTES", 11, zzkj.zzzu, 2);
        zzke.zzzf = new zzke("UINT32", 12, zzkj.zzzo, 0);
        zzke.zzzg = new zzke("ENUM", 13, zzkj.zzzv, 0);
        zzke.zzzh = new zzke("SFIXED32", 14, zzkj.zzzo, 5);
        zzke.zzzi = new zzke("SFIXED64", 15, zzkj.zzzp, 1);
        zzke.zzzj = new zzke("SINT32", 16, zzkj.zzzo, 0);
        zzke.zzzk = new zzke("SINT64", 17, zzkj.zzzp, 0);
        zzke.zzzn = new zzke[]{zzke.zzyt, zzke.zzyu, zzke.zzyv, zzke.zzyw, zzke.zzyx, zzke.zzyy, zzke.zzyz, zzke.zzza, zzke.zzzb, zzke.zzzc, zzke.zzzd, zzke.zzze, zzke.zzzf, zzke.zzzg, zzke.zzzh, zzke.zzzi, zzke.zzzj, zzke.zzzk};
    }

    private zzke(String arg1, int arg2, zzkj arg3, int arg4) {
        super(arg1, arg2);
        this.zzzl = arg3;
        this.zzzm = arg4;
    }

    zzke(String arg1, int arg2, zzkj arg3, int arg4, zzkd arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    public static zzke[] values() {
        // Method was not decompiled
    }

    public final zzkj zzgz() {
        return this.zzzl;
    }

    public final int zzha() {
        return this.zzzm;
    }
}

