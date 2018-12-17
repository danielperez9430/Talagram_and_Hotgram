package com.google.android.gms.internal.measurement;

abstract class zzyb {
    zzyb() {
        super();
    }

    abstract void zza(Object arg1, int arg2, long arg3);

    abstract void zza(Object arg1, int arg2, zzud arg3);

    abstract void zza(Object arg1, int arg2, Object arg3);

    abstract void zza(Object arg1, zzyw arg2);

    abstract boolean zza(zzxi arg1);

    final boolean zza(Object arg7, zzxi arg8) {
        int v0 = arg8.getTag();
        int v1 = v0 >>> 3;
        switch(v0 & 7) {
            case 0: {
                goto label_33;
            }
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_27;
            }
            case 3: {
                goto label_12;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw zzvt.zzwo();
    label_33:
        this.zza(arg7, v1, arg8.zzui());
        return 1;
    label_7:
        this.zzc(arg7, v1, arg8.zzul());
        return 1;
    label_27:
        this.zza(arg7, v1, arg8.zzuo());
        return 1;
    label_12:
        Object v0_1 = this.zzye();
        int v3 = v1 << 3 | 4;
        do {
            if(arg8.zzve() == 2147483647) {
                break;
            }
        }
        while(this.zza(v0_1, arg8));

        if(v3 == arg8.getTag()) {
            this.zza(arg7, v1, this.zzab(v0_1));
            return 1;
        }

        throw zzvt.zzwn();
    label_30:
        this.zzb(arg7, v1, arg8.zzuk());
        return 1;
    }

    abstract Object zzab(Object arg1);

    abstract int zzae(Object arg1);

    abstract Object zzah(Object arg1);

    abstract Object zzai(Object arg1);

    abstract int zzaj(Object arg1);

    abstract void zzb(Object arg1, int arg2, long arg3);

    abstract void zzc(Object arg1, int arg2, int arg3);

    abstract void zzc(Object arg1, zzyw arg2);

    abstract void zzf(Object arg1, Object arg2);

    abstract void zzg(Object arg1, Object arg2);

    abstract Object zzh(Object arg1, Object arg2);

    abstract void zzu(Object arg1);

    abstract Object zzye();
}

