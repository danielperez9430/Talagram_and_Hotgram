package com.google.android.gms.internal.places;

import java.lang.reflect.Field;
import java.util.Arrays;

final class zziv {
    private final int flags;
    private final Object[] zzvb;
    private final int zzvc;
    private final int zzvd;
    private final int zzve;
    private final int[] zzvk;
    private final zziw zzvz;
    private Class zzwa;
    private final int zzwb;
    private final int zzwc;
    private final int zzwd;
    private final int zzwe;
    private final int zzwf;
    private final int zzwg;
    private int zzwh;
    private int zzwi;
    private int zzwj;
    private int zzwk;
    private int zzwl;
    private int zzwm;
    private int zzwn;
    private int zzwo;
    private int zzwp;
    private int zzwq;
    private int zzwr;
    private int zzws;
    private int zzwt;
    private int zzwu;
    private Field zzwv;
    private Object zzww;
    private Object zzwx;
    private Object zzwy;

    zziv(Class arg2, String arg3, Object[] arg4) {
        super();
        this.zzwj = 2147483647;
        this.zzwk = -2147483648;
        this.zzwl = 0;
        this.zzwm = 0;
        this.zzwn = 0;
        this.zzwo = 0;
        this.zzwp = 0;
        this.zzwa = arg2;
        this.zzvz = new zziw(arg3);
        this.zzvb = arg4;
        this.flags = this.zzvz.next();
        this.zzwb = this.zzvz.next();
        int[] v3 = null;
        if(this.zzwb == 0) {
            this.zzwc = 0;
            this.zzwd = 0;
            this.zzvc = 0;
            this.zzvd = 0;
            this.zzwe = 0;
            this.zzwf = 0;
            this.zzve = 0;
            this.zzwg = 0;
            this.zzvk = v3;
            return;
        }

        this.zzwc = this.zzvz.next();
        this.zzwd = this.zzvz.next();
        this.zzvc = this.zzvz.next();
        this.zzvd = this.zzvz.next();
        this.zzwf = this.zzvz.next();
        this.zzve = this.zzvz.next();
        this.zzwe = this.zzvz.next();
        this.zzwg = this.zzvz.next();
        int v2 = this.zzvz.next();
        if(v2 == 0) {
        }
        else {
            v3 = new int[v2];
        }

        this.zzvk = v3;
        this.zzwh = (this.zzwc << 1) + this.zzwd;
    }

    final boolean next() {
        int v0;
        int v1 = 0;
        if(!this.zzvz.hasNext()) {
            return 0;
        }

        this.zzwq = this.zzvz.next();
        this.zzwr = this.zzvz.next();
        this.zzws = this.zzwr & 255;
        if(this.zzwq < this.zzwj) {
            this.zzwj = this.zzwq;
        }

        if(this.zzwq > this.zzwk) {
            this.zzwk = this.zzwq;
        }

        if(this.zzws == zzgt.zzrm.id()) {
            ++this.zzwl;
        }
        else if(this.zzws >= zzgt.zzqg.id() && this.zzws <= zzgt.zzrl.id()) {
            ++this.zzwm;
        }

        ++this.zzwp;
        if(zzja.zzd(this.zzwj, this.zzwq, this.zzwp)) {
            this.zzwo = this.zzwq + 1;
            v0 = this.zzwo - this.zzwj;
        }
        else {
            v0 = this.zzwn + 1;
        }

        this.zzwn = v0;
        v0 = (this.zzwr & 1024) != 0 ? 1 : 0;
        if(v0 != 0) {
            int[] v0_1 = this.zzvk;
            int v2 = this.zzwi;
            this.zzwi = v2 + 1;
            v0_1[v2] = this.zzwq;
        }

        this.zzww = null;
        this.zzwx = null;
        this.zzwy = null;
        if(this.zzfp()) {
            this.zzwt = this.zzvz.next();
            if(this.zzws == zzgt.zzpx.id() + 51) {
                goto label_106;
            }
            else if(this.zzws == zzgt.zzqf.id() + 51) {
                goto label_106;
            }
            else if(this.zzws == zzgt.zzqa.id() + 51) {
                if(!this.zzfo()) {
                    return 1;
                }

                goto label_103;
            }
        }
        else {
            this.zzwv = zziv.zzb(this.zzwa, this.zzfm());
            if(this.zzft()) {
                this.zzwu = this.zzvz.next();
            }

            if(this.zzws != zzgt.zzpx.id()) {
                if(this.zzws == zzgt.zzqf.id()) {
                }
                else if(this.zzws == zzgt.zzqp.id() || this.zzws == zzgt.zzrl.id()) {
                label_106:
                    Object v0_2 = this.zzfm();
                    goto label_107;
                }
                else {
                    if(this.zzws != zzgt.zzqa.id() && this.zzws != zzgt.zzqs.id()) {
                        if(this.zzws == zzgt.zzrg.id()) {
                        }
                        else if(this.zzws == zzgt.zzrm.id()) {
                            this.zzwy = this.zzfm();
                            if((this.zzwr & 2048) != 0) {
                                v1 = 1;
                            }

                            if(v1 == 0) {
                                return 1;
                            }

                            goto label_103;
                        }
                        else {
                            return 1;
                        }
                    }

                    if(!this.zzfo()) {
                        return 1;
                    }

                label_103:
                    this.zzwx = this.zzfm();
                    return 1;
                }
            }

            Class v0_3 = this.zzwv.getType();
        label_107:
            this.zzww = v0_3;
        }

        return 1;
    }

    static int zzb(zziv arg0) {
        return arg0.flags;
    }

    private static Field zzb(Class arg5, String arg6) {
        try {
            return arg5.getDeclaredField(arg6);
        }
        catch(NoSuchFieldException ) {
            Field[] v0 = arg5.getDeclaredFields();
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                Field v3 = v0[v2];
                if(arg6.equals(v3.getName())) {
                    return v3;
                }
            }

            String v5 = arg5.getName();
            String v0_1 = Arrays.toString(((Object[])v0));
            StringBuilder v3_1 = new StringBuilder(String.valueOf(arg6).length() + 40 + String.valueOf(v5).length() + String.valueOf(v0_1).length());
            v3_1.append("Field ");
            v3_1.append(arg6);
            v3_1.append(" for ");
            v3_1.append(v5);
            v3_1.append(" not found. Known fields are ");
            v3_1.append(v0_1);
            throw new RuntimeException(v3_1.toString());
        }
    }

    final int zzbg() {
        return this.zzwq;
    }

    static int zzc(zziv arg0) {
        return arg0.zzvc;
    }

    static int zzd(zziv arg0) {
        return arg0.zzvd;
    }

    static int zze(zziv arg0) {
        return arg0.zzwb;
    }

    static int zzf(zziv arg0) {
        return arg0.zzwe;
    }

    private final Object zzfm() {
        Object[] v0 = this.zzvb;
        int v1 = this.zzwh;
        this.zzwh = v1 + 1;
        return v0[v1];
    }

    final int zzfn() {
        return this.zzws;
    }

    private final boolean zzfo() {
        if((this.flags & 1) == 1) {
            return 1;
        }

        return 0;
    }

    final boolean zzfp() {
        if(this.zzws > zzgt.zzrm.id()) {
            return 1;
        }

        return 0;
    }

    final Field zzfq() {
        int v0 = this.zzwt << 1;
        Object v1 = this.zzvb[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zziv.zzb(this.zzwa, ((String)v1));
        this.zzvb[v0] = v1_1;
        return v1_1;
    }

    final Field zzfr() {
        int v0 = (this.zzwt << 1) + 1;
        Object v1 = this.zzvb[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zziv.zzb(this.zzwa, ((String)v1));
        this.zzvb[v0] = v1_1;
        return v1_1;
    }

    final Field zzfs() {
        return this.zzwv;
    }

    final boolean zzft() {
        if((this.zzfo()) && this.zzws <= zzgt.zzqf.id()) {
            return 1;
        }

        return 0;
    }

    final Field zzfu() {
        int v0 = (this.zzwc << 1) + this.zzwu / 32;
        Object v1 = this.zzvb[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zziv.zzb(this.zzwa, ((String)v1));
        this.zzvb[v0] = v1_1;
        return v1_1;
    }

    final int zzfv() {
        return this.zzwu % 32;
    }

    final boolean zzfw() {
        if((this.zzwr & 256) != 0) {
            return 1;
        }

        return 0;
    }

    final boolean zzfx() {
        if((this.zzwr & 512) != 0) {
            return 1;
        }

        return 0;
    }

    final Object zzfy() {
        return this.zzww;
    }

    final Object zzfz() {
        return this.zzwx;
    }

    static int zzg(zziv arg0) {
        return arg0.zzwg;
    }

    final Object zzga() {
        return this.zzwy;
    }

    static int[] zzh(zziv arg0) {
        return arg0.zzvk;
    }

    static int zzi(zziv arg0) {
        return arg0.zzwf;
    }

    static int zzj(zziv arg0) {
        return arg0.zzve;
    }
}

