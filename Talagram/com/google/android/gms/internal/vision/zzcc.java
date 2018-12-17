package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

final class zzcc implements zzfz {
    private final zzca zzgz;

    private zzcc(zzca arg2) {
        super();
        this.zzgz = zzct.zza(arg2, "output");
        this.zzgz.zzhk = this;
    }

    public static zzcc zza(zzca arg1) {
        if(arg1.zzhk != null) {
            return arg1.zzhk;
        }

        return new zzcc(arg1);
    }

    public final void zza(int arg2, double arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zza(int arg2, float arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zza(int arg2, long arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zza(int arg2, zzbo arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zza(int arg5, zzdq arg6, Map arg7) {
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            this.zzgz.zzd(arg5, 2);
            this.zzgz.zzq(zzdp.zza(arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzdp.zza(this.zzgz, arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public final void zza(int arg2, Object arg3) {
        if((arg3 instanceof zzbo)) {
            this.zzgz.zzb(arg2, ((zzbo)arg3));
            return;
        }

        this.zzgz.zza(arg2, ((zzdx)arg3));
    }

    public final void zza(int arg2, Object arg3, zzen arg4) {
        this.zzgz.zza(arg2, ((zzdx)arg3), arg4);
    }

    public final void zza(int arg2, String arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zza(int arg5, List arg6) {
        int v1 = 0;
        if((arg6 instanceof zzdg)) {
            List v0 = arg6;
            while(v1 < arg6.size()) {
                Object v2 = ((zzdg)v0).getRaw(v1);
                if((v2 instanceof String)) {
                    this.zzgz.zza(arg5, ((String)v2));
                }
                else {
                    this.zzgz.zza(arg5, ((zzbo)v2));
                }

                ++v1;
            }

            return;
        }

        while(v1 < arg6.size()) {
            this.zzgz.zza(arg5, arg6.get(v1));
            ++v1;
        }
    }

    public final void zza(int arg3, List arg4, zzen arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zza(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zza(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzu(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzp(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzac(int arg3) {
        this.zzgz.zzd(arg3, 3);
    }

    public final void zzad(int arg3) {
        this.zzgz.zzd(arg3, 4);
    }

    public final void zzb(int arg2, long arg3) {
        this.zzgz.zzb(arg2, arg3);
    }

    public final void zzb(int arg3, Object arg4, zzen arg5) {
        zzca v0 = this.zzgz;
        v0.zzd(arg3, 3);
        arg5.zza(arg4, v0.zzhk);
        v0.zzd(arg3, 4);
    }

    public final void zzb(int arg4, List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            this.zzgz.zza(arg4, arg5.get(v0));
        }
    }

    public final void zzb(int arg3, List arg4, zzen arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zzb(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zzb(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzx(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzs(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zzh(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzb(int arg2, boolean arg3) {
        this.zzgz.zzb(arg2, arg3);
    }

    public final int zzbc() {
        return zzd.zzlj;
    }

    public final void zzc(int arg2, long arg3) {
        this.zzgz.zzc(arg2, arg3);
    }

    public final void zzc(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zze(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zzb(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzd(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zzf(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zzb(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zze(int arg2, int arg3) {
        this.zzgz.zze(arg2, arg3);
    }

    public final void zze(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zzh(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zzd(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzf(int arg2, int arg3) {
        this.zzgz.zzf(arg2, arg3);
    }

    public final void zzf(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzd(arg4.get(arg3).floatValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzc(arg4.get(v0).floatValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zza(arg3, arg4.get(v0).floatValue());
            ++v0;
        }
    }

    public final void zzg(int arg2, int arg3) {
        this.zzgz.zzg(arg2, arg3);
    }

    public final void zzg(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zzb(arg5.get(arg4).doubleValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zza(arg5.get(v0).doubleValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zza(arg4, arg5.get(v0).doubleValue());
            ++v0;
        }
    }

    public final void zzh(int arg2, int arg3) {
        this.zzgz.zzh(arg2, arg3);
    }

    public final void zzh(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzz(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzp(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzi(int arg2, long arg3) {
        this.zzgz.zza(arg2, arg3);
    }

    public final void zzi(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzb(arg4.get(arg3).booleanValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zza(arg4.get(v0).booleanValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zzb(arg3, arg4.get(v0).booleanValue());
            ++v0;
        }
    }

    public final void zzj(int arg2, long arg3) {
        this.zzgz.zzc(arg2, arg3);
    }

    public final void zzj(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzv(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzq(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zzf(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzk(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzy(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzs(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zzh(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzl(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zzi(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zzd(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzm(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzgz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzca.zzw(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzgz.zzq(v5);
            while(v0 < arg4.size()) {
                this.zzgz.zzr(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzgz.zzg(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzn(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzgz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzca.zzg(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzgz.zzq(v6);
            while(v0 < arg5.size()) {
                this.zzgz.zzc(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzgz.zzb(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzo(int arg2, int arg3) {
        this.zzgz.zzh(arg2, arg3);
    }

    public final void zzp(int arg2, int arg3) {
        this.zzgz.zze(arg2, arg3);
    }
}

