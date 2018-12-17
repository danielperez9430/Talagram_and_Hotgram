package com.google.android.gms.internal.places;

final class zzhw implements zziz {
    private final zzig zzuq;
    private static final zzig zzur;

    static {
        zzhw.zzur = new zzhx();
    }

    public zzhw() {
        this(new zzhy(new zzig[]{zzgy.zzdn(), zzhw.zzeo()}));
    }

    private zzhw(zzig arg2) {
        super();
        this.zzuq = zzhb.zzb(arg2, "messageInfoFactory");
    }

    private static boolean zzb(zzif arg1) {
        if(arg1.zzev() == zzh.zztd) {
            return 1;
        }

        return 0;
    }

    private static zzig zzeo() {
        try {
            return Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception ) {
            return zzhw.zzur;
        }
    }

    public final zziy zzf(Class arg9) {
        zzja.zzh(arg9);
        zzif v2 = this.zzuq.zzd(arg9);
        if(v2.zzew()) {
            if(zzgz.class.isAssignableFrom(arg9)) {
                return zzim.zzb(zzja.zzgd(), zzgp.zzdd(), v2.zzex());
            }

            return zzim.zzb(zzja.zzgb(), zzgp.zzde(), v2.zzex());
        }

        if(zzgz.class.isAssignableFrom(arg9)) {
            if(zzhw.zzb(v2)) {
                return zzil.zzb(arg9, v2, zziq.zzfa(), zzhr.zzen(), zzja.zzgd(), zzgp.zzdd(), zzie.zzet());
            }

            return zzil.zzb(arg9, v2, zziq.zzfa(), zzhr.zzen(), zzja.zzgd(), null, zzie.zzet());
        }

        if(zzhw.zzb(v2)) {
            return zzil.zzb(arg9, v2, zziq.zzez(), zzhr.zzem(), zzja.zzgb(), zzgp.zzde(), zzie.zzes());
        }

        return zzil.zzb(arg9, v2, zziq.zzez(), zzhr.zzem(), zzja.zzgc(), null, zzie.zzes());
    }
}

