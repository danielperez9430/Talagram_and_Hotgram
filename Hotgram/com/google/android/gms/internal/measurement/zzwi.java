package com.google.android.gms.internal.measurement;

final class zzwi implements zzxk {
    private final zzws zzcao;
    private static final zzws zzcap;

    static {
        zzwi.zzcap = new zzwj();
    }

    public zzwi() {
        this(new zzwk(new zzws[]{zzvl.zzwb(), zzwi.zzwz()}));
    }

    private zzwi(zzws arg2) {
        super();
        this.zzcao = zzvo.zza(arg2, "messageInfoFactory");
    }

    private static boolean zza(zzwr arg1) {
        if(arg1.zzxg() == zze.zzbzb) {
            return 1;
        }

        return 0;
    }

    public final zzxj zzh(Class arg9) {
        zzxl.zzj(arg9);
        zzwr v2 = this.zzcao.zzf(arg9);
        if(v2.zzxh()) {
            if(zzvm.class.isAssignableFrom(arg9)) {
                return zzwy.zza(zzxl.zzxt(), zzvc.zzvr(), v2.zzxi());
            }

            return zzwy.zza(zzxl.zzxr(), zzvc.zzvs(), v2.zzxi());
        }

        if(zzvm.class.isAssignableFrom(arg9)) {
            if(zzwi.zza(v2)) {
                return zzwx.zza(arg9, v2, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), zzvc.zzvr(), zzwq.zzxe());
            }

            return zzwx.zza(arg9, v2, zzxc.zzxl(), zzwd.zzwy(), zzxl.zzxt(), null, zzwq.zzxe());
        }

        if(zzwi.zza(v2)) {
            return zzwx.zza(arg9, v2, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxr(), zzvc.zzvs(), zzwq.zzxd());
        }

        return zzwx.zza(arg9, v2, zzxc.zzxk(), zzwd.zzwx(), zzxl.zzxs(), null, zzwq.zzxd());
    }

    private static zzws zzwz() {
        try {
            return Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance").invoke(null);
        }
        catch(Exception ) {
            return zzwi.zzcap;
        }
    }
}

