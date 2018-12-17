package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

final class zzbp implements zzfr {
    private final zzbn zzfo;

    private zzbp(zzbn arg2) {
        super();
        this.zzfo = zzci.zza(arg2, "output");
        this.zzfo.zzfz = this;
    }

    public static zzbp zza(zzbn arg1) {
        if(arg1.zzfz != null) {
            return arg1.zzfz;
        }

        return new zzbp(arg1);
    }

    public final void zza(int arg2, double arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zza(int arg2, float arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zza(int arg2, long arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zza(int arg2, zzbb arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zza(int arg5, zzdh arg6, Map arg7) {
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            this.zzfo.zzb(arg5, 2);
            this.zzfo.zzo(zzdg.zza(arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzdg.zza(this.zzfo, arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public final void zza(int arg2, Object arg3) {
        if((arg3 instanceof zzbb)) {
            this.zzfo.zzb(arg2, ((zzbb)arg3));
            return;
        }

        this.zzfo.zzb(arg2, ((zzdo)arg3));
    }

    public final void zza(int arg2, Object arg3, zzef arg4) {
        this.zzfo.zza(arg2, ((zzdo)arg3), arg4);
    }

    public final void zza(int arg2, String arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zza(int arg5, List arg6) {
        int v1 = 0;
        if((arg6 instanceof zzcx)) {
            List v0 = arg6;
            while(v1 < arg6.size()) {
                Object v2 = ((zzcx)v0).getRaw(v1);
                if((v2 instanceof String)) {
                    this.zzfo.zza(arg5, ((String)v2));
                }
                else {
                    this.zzfo.zza(arg5, ((zzbb)v2));
                }

                ++v1;
            }

            return;
        }

        while(v1 < arg6.size()) {
            this.zzfo.zza(arg5, arg6.get(v1));
            ++v1;
        }
    }

    public final void zza(int arg3, List arg4, zzef arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zza(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zza(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzs(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzn(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzc(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzaa(int arg3) {
        this.zzfo.zzb(arg3, 3);
    }

    public final void zzab(int arg3) {
        this.zzfo.zzb(arg3, 4);
    }

    public final int zzaj() {
        return zzg.zzko;
    }

    public final void zzb(int arg2, long arg3) {
        this.zzfo.zzb(arg2, arg3);
    }

    public final void zzb(int arg3, Object arg4, zzef arg5) {
        zzbn v0 = this.zzfo;
        v0.zzb(arg3, 3);
        arg5.zza(arg4, v0.zzfz);
        v0.zzb(arg3, 4);
    }

    public final void zzb(int arg4, List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            this.zzfo.zza(arg4, arg5.get(v0));
        }
    }

    public final void zzb(int arg3, List arg4, zzef arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zzb(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zzb(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzv(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzq(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzf(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzb(int arg2, boolean arg3) {
        this.zzfo.zzb(arg2, arg3);
    }

    public final void zzc(int arg2, int arg3) {
        this.zzfo.zzc(arg2, arg3);
    }

    public final void zzc(int arg2, long arg3) {
        this.zzfo.zzc(arg2, arg3);
    }

    public final void zzc(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zze(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zzb(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzd(int arg2, int arg3) {
        this.zzfo.zzd(arg2, arg3);
    }

    public final void zzd(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zzf(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zzb(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zze(int arg2, int arg3) {
        this.zzfo.zze(arg2, arg3);
    }

    public final void zze(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zzh(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zzd(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzf(int arg2, int arg3) {
        this.zzfo.zzf(arg2, arg3);
    }

    public final void zzf(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzb(arg4.get(arg3).floatValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zza(arg4.get(v0).floatValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zza(arg3, arg4.get(v0).floatValue());
            ++v0;
        }
    }

    public final void zzg(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zzb(arg5.get(arg4).doubleValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zza(arg5.get(v0).doubleValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zza(arg4, arg5.get(v0).doubleValue());
            ++v0;
        }
    }

    public final void zzh(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzx(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzn(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzc(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzi(int arg2, long arg3) {
        this.zzfo.zza(arg2, arg3);
    }

    public final void zzi(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzb(arg4.get(arg3).booleanValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zza(arg4.get(v0).booleanValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzb(arg3, arg4.get(v0).booleanValue());
            ++v0;
        }
    }

    public final void zzj(int arg2, long arg3) {
        this.zzfo.zzc(arg2, arg3);
    }

    public final void zzj(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzt(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzo(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzd(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzk(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzw(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzq(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zzf(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzl(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zzi(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zzd(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzm(int arg2, int arg3) {
        this.zzfo.zzf(arg2, arg3);
    }

    public final void zzm(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzfo.zzb(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzbn.zzu(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzfo.zzo(v5);
            while(v0 < arg4.size()) {
                this.zzfo.zzp(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzfo.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzn(int arg2, int arg3) {
        this.zzfo.zzc(arg2, arg3);
    }

    public final void zzn(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzfo.zzb(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzbn.zzg(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzfo.zzo(v6);
            while(v0 < arg5.size()) {
                this.zzfo.zzc(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzfo.zzb(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }
}

