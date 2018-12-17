package com.google.android.gms.measurement.internal;

final class zzaq implements Runnable {
    zzaq(zzap arg1, int arg2, String arg3, Object arg4, Object arg5, Object arg6) {
        this.zzamm = arg1;
        this.zzamh = arg2;
        this.zzami = arg3;
        this.zzamj = arg4;
        this.zzamk = arg5;
        this.zzaml = arg6;
        super();
    }

    public final void run() {
        char v2;
        zzap v1;
        zzba v0 = this.zzamm.zzadj.zzgp();
        if(!((zzcp)v0).isInitialized()) {
            this.zzamm.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }

        if(zzap.zza(this.zzamm) == 0) {
            if(this.zzamm.zzgq().zzdw()) {
                v1 = this.zzamm;
                this.zzamm.zzgr();
                v2 = 'C';
            }
            else {
                v1 = this.zzamm;
                this.zzamm.zzgr();
                v2 = 'c';
            }

            zzap.zza(v1, v2);
        }

        if(zzap.zzb(this.zzamm) < 0) {
            zzap.zza(this.zzamm, this.zzamm.zzgq().zzhc());
        }

        char v1_1 = "01VDIWEA?".charAt(this.zzamh);
        v2 = zzap.zza(this.zzamm);
        long v3 = zzap.zzb(this.zzamm);
        String v5 = zzap.zza(true, this.zzami, this.zzamj, this.zzamk, this.zzaml);
        StringBuilder v7 = new StringBuilder(String.valueOf(v5).length() + 24);
        v7.append("2");
        v7.append(v1_1);
        v7.append(v2);
        v7.append(v3);
        v7.append(":");
        v7.append(v5);
        String v1_2 = v7.toString();
        int v3_1 = 1024;
        if(v1_2.length() > v3_1) {
            v1_2 = this.zzami.substring(0, v3_1);
        }

        v0.zzand.zzc(v1_2, 1);
    }
}

