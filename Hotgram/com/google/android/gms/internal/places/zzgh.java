package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

final class zzgh implements zzkk {
    private final zzgf zznz;

    private zzgh(zzgf arg2) {
        super();
        this.zznz = zzhb.zzb(arg2, "output");
        this.zznz.zzop = this;
    }

    public static zzgh zzb(zzgf arg1) {
        if(arg1.zzop != null) {
            return arg1.zzop;
        }

        return new zzgh(arg1);
    }

    public final void zzb(int arg2, double arg3) {
        this.zznz.zzb(arg2, arg3);
    }

    public final void zzb(int arg2, long arg3) {
        this.zznz.zzb(arg2, arg3);
    }

    public final void zzb(int arg2, zzfr arg3) {
        this.zznz.zzb(arg2, arg3);
    }

    public final void zzb(int arg5, zzia arg6, Map arg7) {
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            this.zznz.zzd(arg5, 2);
            this.zznz.zzap(zzhz.zzb(arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzhz.zzb(this.zznz, arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public final void zzb(int arg2, Object arg3) {
        if((arg3 instanceof zzfr)) {
            this.zznz.zzc(arg2, ((zzfr)arg3));
            return;
        }

        this.zznz.zzc(arg2, ((zzih)arg3));
    }

    public final void zzb(int arg2, Object arg3, zziy arg4) {
        this.zznz.zzb(arg2, ((zzih)arg3), arg4);
    }

    public final void zzb(int arg2, String arg3) {
        this.zznz.zzb(arg2, arg3);
    }

    public final void zzb(int arg3, List arg4, zziy arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zzb(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zzb(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzat(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzao(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzbb(int arg3) {
        this.zznz.zzd(arg3, 3);
    }

    public final void zzbc(int arg3) {
        this.zznz.zzd(arg3, 4);
    }

    public final void zzc(int arg2, float arg3) {
        this.zznz.zzc(arg2, arg3);
    }

    public final void zzc(int arg2, long arg3) {
        this.zznz.zzc(arg2, arg3);
    }

    public final void zzc(int arg3, Object arg4, zziy arg5) {
        zzgf v0 = this.zznz;
        v0.zzd(arg3, 3);
        arg5.zzb(arg4, v0.zzop);
        v0.zzd(arg3, 4);
    }

    public final void zzc(int arg5, List arg6) {
        int v1 = 0;
        if((arg6 instanceof zzhq)) {
            List v0 = arg6;
            while(v1 < arg6.size()) {
                Object v2 = ((zzhq)v0).getRaw(v1);
                if((v2 instanceof String)) {
                    this.zznz.zzb(arg5, ((String)v2));
                }
                else {
                    this.zznz.zzb(arg5, ((zzfr)v2));
                }

                ++v1;
            }

            return;
        }

        while(v1 < arg6.size()) {
            this.zznz.zzb(arg5, arg6.get(v1));
            ++v1;
        }
    }

    public final void zzc(int arg3, List arg4, zziy arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zzc(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zzc(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzaw(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzar(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzh(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzc(int arg2, boolean arg3) {
        this.zznz.zzc(arg2, arg3);
    }

    public final int zzcv() {
        return zzh.zztg;
    }

    public final void zzd(int arg2, long arg3) {
        this.zznz.zzd(arg2, arg3);
    }

    public final void zzd(int arg4, List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            this.zznz.zzb(arg4, arg5.get(v0));
        }
    }

    public final void zzd(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzh(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zze(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzb(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zze(int arg2, int arg3) {
        this.zznz.zze(arg2, arg3);
    }

    public final void zze(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzi(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zze(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzb(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzf(int arg2, int arg3) {
        this.zznz.zzf(arg2, arg3);
    }

    public final void zzf(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzk(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zzg(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzd(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzg(int arg2, int arg3) {
        this.zznz.zzg(arg2, arg3);
    }

    public final void zzg(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zze(arg4.get(arg3).floatValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzd(arg4.get(v0).floatValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzc(arg3, arg4.get(v0).floatValue());
            ++v0;
        }
    }

    public final void zzh(int arg2, int arg3) {
        this.zznz.zzh(arg2, arg3);
    }

    public final void zzh(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzc(arg5.get(arg4).doubleValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zzb(arg5.get(v0).doubleValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzb(arg4, arg5.get(v0).doubleValue());
            ++v0;
        }
    }

    public final void zzi(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzay(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzao(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzj(int arg2, long arg3) {
        this.zznz.zzb(arg2, arg3);
    }

    public final void zzj(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zze(arg4.get(arg3).booleanValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzd(arg4.get(v0).booleanValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzc(arg3, arg4.get(v0).booleanValue());
            ++v0;
        }
    }

    public final void zzk(int arg2, long arg3) {
        this.zznz.zzd(arg2, arg3);
    }

    public final void zzk(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzau(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzap(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzf(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzl(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzax(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzar(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzh(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzm(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzl(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zzg(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzd(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzn(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zznz.zzd(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzgf.zzav(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zznz.zzap(v5);
            while(v0 < arg4.size()) {
                this.zznz.zzaq(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zznz.zzg(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzo(int arg2, int arg3) {
        this.zznz.zzh(arg2, arg3);
    }

    public final void zzo(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zznz.zzd(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzgf.zzj(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zznz.zzap(v6);
            while(v0 < arg5.size()) {
                this.zznz.zzf(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zznz.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzp(int arg2, int arg3) {
        this.zznz.zze(arg2, arg3);
    }
}

