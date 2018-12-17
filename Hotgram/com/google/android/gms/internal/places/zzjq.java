package com.google.android.gms.internal.places;

abstract class zzjq {
    zzjq() {
        super();
    }

    abstract void zzb(Object arg1, int arg2, long arg3);

    abstract void zzb(Object arg1, int arg2, zzfr arg3);

    abstract void zzb(Object arg1, int arg2, Object arg3);

    abstract void zzb(Object arg1, zzkk arg2);

    abstract boolean zzb(zzix arg1);

    final boolean zzb(Object arg7, zzix arg8) {
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

        throw zzhh.zzed();
    label_33:
        this.zzb(arg7, v1, arg8.zzbj());
        return 1;
    label_7:
        this.zzd(arg7, v1, arg8.zzbm());
        return 1;
    label_27:
        this.zzb(arg7, v1, arg8.zzbp());
        return 1;
    label_12:
        Object v0_1 = this.zzgo();
        int v3 = v1 << 3 | 4;
        do {
            if(arg8.zzbg() == 2147483647) {
                break;
            }
        }
        while(this.zzb(v0_1, arg8));

        if(v3 == arg8.getTag()) {
            this.zzb(arg7, v1, this.zzk(v0_1));
            return 1;
        }

        throw zzhh.zzec();
    label_30:
        this.zzc(arg7, v1, arg8.zzbl());
        return 1;
    }

    abstract void zzc(Object arg1, int arg2, long arg3);

    abstract void zzd(Object arg1, int arg2, int arg3);

    abstract void zzd(Object arg1);

    abstract void zzd(Object arg1, zzkk arg2);

    abstract void zzf(Object arg1, Object arg2);

    abstract void zzg(Object arg1, Object arg2);

    abstract Object zzgo();

    abstract Object zzh(Object arg1, Object arg2);

    abstract Object zzk(Object arg1);

    abstract int zzn(Object arg1);

    abstract Object zzq(Object arg1);

    abstract Object zzr(Object arg1);

    abstract int zzs(Object arg1);
}

