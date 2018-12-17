package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.util.Arrays;

final class zzed {
    private final int flags;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final int[] zzms;
    private final zzee zznh;
    private Class zzni;
    private final int zznj;
    private final int zznk;
    private final int zznl;
    private final int zznm;
    private final int zznn;
    private final int zzno;
    private int zznp;
    private int zznq;
    private int zznr;
    private int zzns;
    private int zznt;
    private int zznu;
    private int zznv;
    private int zznw;
    private int zznx;
    private int zzny;
    private int zznz;
    private int zzoa;
    private int zzob;
    private int zzoc;
    private Field zzod;
    private Object zzoe;
    private Object zzof;
    private Object zzog;

    zzed(Class arg2, String arg3, Object[] arg4) {
        super();
        this.zznr = 2147483647;
        this.zzns = -2147483648;
        this.zznt = 0;
        this.zznu = 0;
        this.zznv = 0;
        this.zznw = 0;
        this.zznx = 0;
        this.zzni = arg2;
        this.zznh = new zzee(arg3);
        this.zzmj = arg4;
        this.flags = this.zznh.next();
        this.zznj = this.zznh.next();
        int[] v3 = null;
        if(this.zznj == 0) {
            this.zznk = 0;
            this.zznl = 0;
            this.zzmk = 0;
            this.zzml = 0;
            this.zznm = 0;
            this.zznn = 0;
            this.zzmm = 0;
            this.zzno = 0;
            this.zzms = v3;
            return;
        }

        this.zznk = this.zznh.next();
        this.zznl = this.zznh.next();
        this.zzmk = this.zznh.next();
        this.zzml = this.zznh.next();
        this.zznn = this.zznh.next();
        this.zzmm = this.zznh.next();
        this.zznm = this.zznh.next();
        this.zzno = this.zznh.next();
        int v2 = this.zznh.next();
        if(v2 == 0) {
        }
        else {
            v3 = new int[v2];
        }

        this.zzms = v3;
        this.zznp = (this.zznk << 1) + this.zznl;
    }

    final boolean next() {
        Object v0_2;
        int v0;
        int v1 = 0;
        if(!this.zznh.hasNext()) {
            return 0;
        }

        this.zzny = this.zznh.next();
        this.zznz = this.zznh.next();
        this.zzoa = this.zznz & 255;
        if(this.zzny < this.zznr) {
            this.zznr = this.zzny;
        }

        if(this.zzny > this.zzns) {
            this.zzns = this.zzny;
        }

        if(this.zzoa == zzcb.zziw.id()) {
            ++this.zznt;
        }
        else if(this.zzoa >= zzcb.zzhq.id() && this.zzoa <= zzcb.zziv.id()) {
            ++this.zznu;
        }

        ++this.zznx;
        if(zzeh.zzc(this.zznr, this.zzny, this.zznx)) {
            this.zznw = this.zzny + 1;
            v0 = this.zznw - this.zznr;
        }
        else {
            v0 = this.zznv + 1;
        }

        this.zznv = v0;
        v0 = (this.zznz & 1024) != 0 ? 1 : 0;
        if(v0 != 0) {
            int[] v0_1 = this.zzms;
            int v2 = this.zznq;
            this.zznq = v2 + 1;
            v0_1[v2] = this.zzny;
        }

        this.zzoe = null;
        this.zzof = null;
        this.zzog = null;
        if(this.zzda()) {
            this.zzob = this.zznh.next();
            if(this.zzoa == zzcb.zzhh.id() + 51) {
                goto label_106;
            }
            else if(this.zzoa == zzcb.zzhp.id() + 51) {
                goto label_106;
            }
            else if(this.zzoa == zzcb.zzhk.id() + 51) {
                if(!this.zzcz()) {
                    return 1;
                }

                goto label_103;
            }
        }
        else {
            this.zzod = zzed.zza(this.zzni, this.zzcw());
            if(this.zzde()) {
                this.zzoc = this.zznh.next();
            }

            if(this.zzoa != zzcb.zzhh.id()) {
                if(this.zzoa == zzcb.zzhp.id()) {
                }
                else if(this.zzoa == zzcb.zzhz.id() || this.zzoa == zzcb.zziv.id()) {
                label_106:
                    v0_2 = this.zzcw();
                    goto label_107;
                }
                else {
                    if(this.zzoa != zzcb.zzhk.id() && this.zzoa != zzcb.zzic.id()) {
                        if(this.zzoa == zzcb.zziq.id()) {
                        }
                        else if(this.zzoa == zzcb.zziw.id()) {
                            this.zzog = this.zzcw();
                            if((this.zznz & 2048) != 0) {
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

                    if(!this.zzcz()) {
                        return 1;
                    }

                label_103:
                    this.zzof = this.zzcw();
                    return 1;
                }
            }

            Class v0_3 = this.zzod.getType();
        label_107:
            this.zzoe = v0_2;
        }

        return 1;
    }

    static int zza(zzed arg0) {
        return arg0.flags;
    }

    private static Field zza(Class arg5, String arg6) {
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

    static int zzb(zzed arg0) {
        return arg0.zzmk;
    }

    static int zzc(zzed arg0) {
        return arg0.zzml;
    }

    private final Object zzcw() {
        Object[] v0 = this.zzmj;
        int v1 = this.zznp;
        this.zznp = v1 + 1;
        return v0[v1];
    }

    final int zzcx() {
        return this.zzny;
    }

    final int zzcy() {
        return this.zzoa;
    }

    private final boolean zzcz() {
        if((this.flags & 1) == 1) {
            return 1;
        }

        return 0;
    }

    static int zzd(zzed arg0) {
        return arg0.zznj;
    }

    final boolean zzda() {
        if(this.zzoa > zzcb.zziw.id()) {
            return 1;
        }

        return 0;
    }

    final Field zzdb() {
        int v0 = this.zzob << 1;
        Object v1 = this.zzmj[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zzed.zza(this.zzni, ((String)v1));
        this.zzmj[v0] = v1_1;
        return v1_1;
    }

    final Field zzdc() {
        int v0 = (this.zzob << 1) + 1;
        Object v1 = this.zzmj[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zzed.zza(this.zzni, ((String)v1));
        this.zzmj[v0] = v1_1;
        return v1_1;
    }

    final Field zzdd() {
        return this.zzod;
    }

    final boolean zzde() {
        if((this.zzcz()) && this.zzoa <= zzcb.zzhp.id()) {
            return 1;
        }

        return 0;
    }

    final Field zzdf() {
        int v0 = (this.zznk << 1) + this.zzoc / 32;
        Object v1 = this.zzmj[v0];
        if((v1 instanceof Field)) {
            return ((Field)v1);
        }

        Field v1_1 = zzed.zza(this.zzni, ((String)v1));
        this.zzmj[v0] = v1_1;
        return v1_1;
    }

    final int zzdg() {
        return this.zzoc % 32;
    }

    final boolean zzdh() {
        if((this.zznz & 256) != 0) {
            return 1;
        }

        return 0;
    }

    final boolean zzdi() {
        if((this.zznz & 512) != 0) {
            return 1;
        }

        return 0;
    }

    final Object zzdj() {
        return this.zzoe;
    }

    final Object zzdk() {
        return this.zzof;
    }

    final Object zzdl() {
        return this.zzog;
    }

    static int zze(zzed arg0) {
        return arg0.zznm;
    }

    static int zzf(zzed arg0) {
        return arg0.zzno;
    }

    static int[] zzg(zzed arg0) {
        return arg0.zzms;
    }

    static int zzh(zzed arg0) {
        return arg0.zznn;
    }

    static int zzi(zzed arg0) {
        return arg0.zzmm;
    }
}

