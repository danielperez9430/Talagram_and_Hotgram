package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzim implements zziy {
    private final zzih zzvf;
    private final boolean zzvg;
    private final zzjq zzvp;
    private final zzgm zzvq;

    private zzim(zzjq arg1, zzgm arg2, zzih arg3) {
        super();
        this.zzvp = arg1;
        this.zzvg = arg2.zzf(arg3);
        this.zzvq = arg2;
        this.zzvf = arg3;
    }

    public final boolean equals(Object arg3, Object arg4) {
        if(!this.zzvp.zzq(arg3).equals(this.zzvp.zzq(arg4))) {
            return 0;
        }

        if(this.zzvg) {
            return this.zzvq.zzb(arg3).equals(this.zzvq.zzb(arg4));
        }

        return 1;
    }

    public final int hashCode(Object arg3) {
        int v0 = this.zzvp.zzq(arg3).hashCode();
        if(this.zzvg) {
            v0 = v0 * 53 + this.zzvq.zzb(arg3).hashCode();
        }

        return v0;
    }

    public final Object newInstance() {
        return this.zzvf.zzdr().zzdw();
    }

    static zzim zzb(zzjq arg1, zzgm arg2, zzih arg3) {
        return new zzim(arg1, arg2, arg3);
    }

    public final void zzb(Object arg11, zzix arg12, zzgl arg13) {
        boolean v4_2;
        zzjq v0 = this.zzvp;
        zzgm v1 = this.zzvq;
        Object v2 = v0.zzr(arg11);
        zzgq v3 = v1.zzc(arg11);
        try {
            do {
            label_4:
                int v5 = 2147483647;
                if(arg12.zzbg() != v5) {
                    goto label_9;
                }

                break;
            }
            while(true);
        }
        catch(Throwable v12) {
            goto label_62;
        }

        v0.zzg(arg11, v2);
        return;
        try {
        label_9:
            int v4 = arg12.getTag();
            if(v4 == 11) {
                Object v6 = null;
                zzfr v4_3 = ((zzfr)v6);
                int v7 = 0;
                do {
                    if(arg12.zzbg() == v5) {
                        break;
                    }

                    int v8 = arg12.getTag();
                    if(v8 == 16) {
                        v7 = arg12.zzbq();
                        v6 = v1.zzb(arg13, this.zzvf, v7);
                        continue;
                    }
                    else if(v8 == 26) {
                        if(v6 != null) {
                            v1.zzb(arg12, v6, arg13, v3);
                        }
                        else {
                            v4_3 = arg12.zzbp();
                        }

                        continue;
                    }
                    else if(arg12.zzbh()) {
                        continue;
                    }

                    break;
                }
                while(true);

                if(arg12.getTag() == 12) {
                    if(v4_3 == null) {
                        goto label_55;
                    }

                    if(v6 != null) {
                        v1.zzb(v4_3, v6, arg13, v3);
                        goto label_55;
                    }

                    v0.zzb(v2, v7, v4_3);
                    goto label_55;
                }

                throw zzhh.zzec();
            }
            else if((v4 & 7) == 2) {
                Object v4_1 = v1.zzb(arg13, this.zzvf, v4 >>> 3);
                if(v4_1 != null) {
                    v1.zzb(arg12, v4_1, arg13, v3);
                    goto label_55;
                }
                else {
                    v4_2 = v0.zzb(v2, arg12);
                }
            }
            else {
                v4_2 = arg12.zzbh();
            }

            goto label_56;
        }
        catch(Throwable v12) {
            goto label_62;
        }

    label_55:
        v4_2 = true;
    label_56:
        if(v4_2) {
            goto label_4;
        }

        v0.zzg(arg11, v2);
        return;
    label_62:
        v0.zzg(arg11, v2);
        throw v12;
    }

    public final void zzb(Object arg6, zzkk arg7) {
        int v2_1;
        Iterator v0 = this.zzvq.zzb(arg6).iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_28;
            }

            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            if(((zzgs)v2).zzdj() == zzkj.zzzw && !((zzgs)v2).zzdk() && !((zzgs)v2).zzdl()) {
                if((v1 instanceof zzhm)) {
                    v2_1 = ((zzgs)v2).zzap();
                    zzfr v1_1 = ((zzhm)v1).zzej().zzax();
                }
                else {
                    v2_1 = ((zzgs)v2).zzap();
                    v1 = ((Map$Entry)v1).getValue();
                }

                arg7.zzb(v2_1, v1);
                continue;
            }

            break;
        }

        throw new IllegalStateException("Found invalid MessageSet item.");
    label_28:
        this.zzvp.zzd(this.zzvp.zzq(arg6), arg7);
    }

    public final void zzd(Object arg2) {
        this.zzvp.zzd(arg2);
        this.zzvq.zzd(arg2);
    }

    public final void zzd(Object arg2, Object arg3) {
        zzja.zzb(this.zzvp, arg2, arg3);
        if(this.zzvg) {
            zzja.zzb(this.zzvq, arg2, arg3);
        }
    }

    public final int zzn(Object arg3) {
        int v0 = this.zzvp.zzs(this.zzvp.zzq(arg3));
        if(this.zzvg) {
            v0 += this.zzvq.zzb(arg3).zzdh();
        }

        return v0;
    }

    public final boolean zzo(Object arg2) {
        return this.zzvq.zzb(arg2).isInitialized();
    }
}

