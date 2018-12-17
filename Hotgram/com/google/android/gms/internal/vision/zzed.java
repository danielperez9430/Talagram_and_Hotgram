package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzed implements zzen {
    private final zzdx zzni;
    private final boolean zznj;
    private final zzff zzns;
    private final zzcg zznt;

    private zzed(zzff arg1, zzcg arg2, zzdx arg3) {
        super();
        this.zzns = arg1;
        this.zznj = arg2.zze(arg3);
        this.zznt = arg2;
        this.zzni = arg3;
    }

    public final boolean equals(Object arg3, Object arg4) {
        if(!this.zzns.zzr(arg3).equals(this.zzns.zzr(arg4))) {
            return 0;
        }

        if(this.zznj) {
            return this.zznt.zzb(arg3).equals(this.zznt.zzb(arg4));
        }

        return 1;
    }

    public final int hashCode(Object arg3) {
        int v0 = this.zzns.zzr(arg3).hashCode();
        if(this.zznj) {
            v0 = v0 * 53 + this.zznt.zzb(arg3).hashCode();
        }

        return v0;
    }

    public final Object newInstance() {
        return this.zzni.zzbv().zzbz();
    }

    static zzed zza(zzff arg1, zzcg arg2, zzdx arg3) {
        return new zzed(arg1, arg2, arg3);
    }

    public final void zza(Object arg6, zzfz arg7) {
        int v2_1;
        Iterator v0 = this.zznt.zzb(arg6).iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_28;
            }

            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            if(((zzcl)v2).zzbp() == zzfy.zzqz && !((zzcl)v2).zzbq() && !((zzcl)v2).zzbr()) {
                if((v1 instanceof zzdc)) {
                    v2_1 = ((zzcl)v2).zzbn();
                    zzbo v1_1 = ((zzdc)v1).zzcj().zzak();
                }
                else {
                    v2_1 = ((zzcl)v2).zzbn();
                    v1 = ((Map$Entry)v1).getValue();
                }

                arg7.zza(v2_1, v1);
                continue;
            }

            break;
        }

        throw new IllegalStateException("Found invalid MessageSet item.");
    label_28:
        this.zzns.zzc(this.zzns.zzr(arg6), arg7);
    }

    public final void zza(Object arg7, byte[] arg8, int arg9, int arg10, zzbl arg11) {
        int v1;
        int v2;
        zzfg v0 = ((zzcr)arg7).zzkr;
        if(v0 == zzfg.zzdu()) {
            v0 = zzfg.zzdv();
            ((zzcr)arg7).zzkr = v0;
        }

        zzfg v7 = v0;
        while(true) {
        label_6:
            if(arg9 >= arg10) {
                goto label_49;
            }

            v2 = zzbk.zza(arg8, arg9, arg11);
            int v0_1 = arg11.zzgo;
            v1 = 2;
            if(v0_1 == 11) {
                break;
            }

            if((v0_1 & 7) == v1) {
                arg9 = zzbk.zza(v0_1, arg8, v2, arg10, v7, arg11);
                continue;
            }

            arg9 = zzbk.zza(v0_1, arg8, v2, arg10, arg11);
        }

        arg9 = 0;
        Object v0_2 = null;
        goto label_24;
    label_49:
        if(arg9 == arg10) {
            return;
        }

        throw zzcx.zzcf();
    label_24:
        while(v2 < arg10) {
            v2 = zzbk.zza(arg8, v2, arg11);
            int v3 = arg11.zzgo;
            int v5 = v3 & 7;
            switch(v3 >>> 3) {
                case 2: {
                    if(v5 == 0) {
                        v2 = zzbk.zza(arg8, v2, arg11);
                        arg9 = arg11.zzgo;
                        goto label_24;
                    }
                    else {
                        goto label_39;
                    }
                }
                case 3: {
                    if(v5 == v1) {
                        v2 = zzbk.zze(arg8, v2, arg11);
                        v0_2 = arg11.zzgq;
                        goto label_24;
                    }
                    else {
                        goto label_39;
                    }
                }
                default: {
                    break;
                }
            }

        label_39:
            if(v3 == 12) {
                break;
            }

            v2 = zzbk.zza(v3, arg8, v2, arg10, arg11);
        }

        if(v0_2 != null) {
            v7.zzb(arg9 << 3 | v1, v0_2);
        }

        arg9 = v2;
        goto label_6;
    }

    public final void zzc(Object arg2, Object arg3) {
        zzep.zza(this.zzns, arg2, arg3);
        if(this.zznj) {
            zzep.zza(this.zznt, arg2, arg3);
        }
    }

    public final void zzd(Object arg2) {
        this.zzns.zzd(arg2);
        this.zznt.zzd(arg2);
    }

    public final int zzn(Object arg3) {
        int v0 = this.zzns.zzs(this.zzns.zzr(arg3));
        if(this.zznj) {
            v0 += this.zznt.zzb(arg3).zzbm();
        }

        return v0;
    }

    public final boolean zzp(Object arg2) {
        return this.zznt.zzb(arg2).isInitialized();
    }
}

