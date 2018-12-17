package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

final class zzuv implements zzyw {
    private final zzut zzbuf;

    private zzuv(zzut arg2) {
        super();
        this.zzbuf = zzvo.zza(arg2, "output");
        this.zzbuf.zzbuw = this;
    }

    public static zzuv zza(zzut arg1) {
        if(arg1.zzbuw != null) {
            return arg1.zzbuw;
        }

        return new zzuv(arg1);
    }

    public final void zza(int arg2, double arg3) {
        this.zzbuf.zza(arg2, arg3);
    }

    public final void zza(int arg2, float arg3) {
        this.zzbuf.zza(arg2, arg3);
    }

    public final void zza(int arg2, long arg3) {
        this.zzbuf.zza(arg2, arg3);
    }

    public final void zza(int arg2, zzud arg3) {
        this.zzbuf.zza(arg2, arg3);
    }

    public final void zza(int arg5, zzwm arg6, Map arg7) {
        Iterator v7 = arg7.entrySet().iterator();
        while(v7.hasNext()) {
            Object v0 = v7.next();
            this.zzbuf.zzc(arg5, 2);
            this.zzbuf.zzay(zzwl.zza(arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue()));
            zzwl.zza(this.zzbuf, arg6, ((Map$Entry)v0).getKey(), ((Map$Entry)v0).getValue());
        }
    }

    public final void zza(int arg2, Object arg3) {
        if((arg3 instanceof zzud)) {
            this.zzbuf.zzb(arg2, ((zzud)arg3));
            return;
        }

        this.zzbuf.zzb(arg2, ((zzwt)arg3));
    }

    public final void zza(int arg2, Object arg3, zzxj arg4) {
        this.zzbuf.zza(arg2, ((zzwt)arg3), arg4);
    }

    public final void zza(int arg5, List arg6) {
        int v1 = 0;
        if((arg6 instanceof zzwc)) {
            List v0 = arg6;
            while(v1 < arg6.size()) {
                Object v2 = ((zzwc)v0).getRaw(v1);
                if((v2 instanceof String)) {
                    this.zzbuf.zzb(arg5, ((String)v2));
                }
                else {
                    this.zzbuf.zza(arg5, ((zzud)v2));
                }

                ++v1;
            }

            return;
        }

        while(v1 < arg6.size()) {
            this.zzbuf.zzb(arg5, arg6.get(v1));
            ++v1;
        }
    }

    public final void zza(int arg3, List arg4, zzxj arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zza(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zza(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbc(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzax(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzd(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzb(int arg2, long arg3) {
        this.zzbuf.zzb(arg2, arg3);
    }

    public final void zzb(int arg3, Object arg4, zzxj arg5) {
        zzut v0 = this.zzbuf;
        v0.zzc(arg3, 3);
        arg5.zza(arg4, v0.zzbuw);
        v0.zzc(arg3, 4);
    }

    public final void zzb(int arg2, String arg3) {
        this.zzbuf.zzb(arg2, arg3);
    }

    public final void zzb(int arg4, List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            this.zzbuf.zza(arg4, arg5.get(v0));
        }
    }

    public final void zzb(int arg3, List arg4, zzxj arg5) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            this.zzb(arg3, arg4.get(v0), arg5);
        }
    }

    public final void zzb(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbf(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzba(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzg(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzb(int arg2, boolean arg3) {
        this.zzbuf.zzb(arg2, arg3);
    }

    public final void zzbk(int arg3) {
        this.zzbuf.zzc(arg3, 3);
    }

    public final void zzbl(int arg3) {
        this.zzbuf.zzc(arg3, 4);
    }

    public final void zzc(int arg2, long arg3) {
        this.zzbuf.zzc(arg2, arg3);
    }

    public final void zzc(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzay(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzav(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzd(int arg2, int arg3) {
        this.zzbuf.zzd(arg2, arg3);
    }

    public final void zzd(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzaz(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzav(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zza(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zze(int arg2, int arg3) {
        this.zzbuf.zze(arg2, arg3);
    }

    public final void zze(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzbb(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzax(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzf(int arg2, int arg3) {
        this.zzbuf.zzf(arg2, arg3);
    }

    public final void zzf(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzb(arg4.get(arg3).floatValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zza(arg4.get(v0).floatValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zza(arg3, arg4.get(v0).floatValue());
            ++v0;
        }
    }

    public final void zzg(int arg2, int arg3) {
        this.zzbuf.zzg(arg2, arg3);
    }

    public final void zzg(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzc(arg5.get(arg4).doubleValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzb(arg5.get(v0).doubleValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zza(arg4, arg5.get(v0).doubleValue());
            ++v0;
        }
    }

    public final void zzh(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbh(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzax(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzd(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzi(int arg2, long arg3) {
        this.zzbuf.zza(arg2, arg3);
    }

    public final void zzi(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzv(arg4.get(arg3).booleanValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzu(arg4.get(v0).booleanValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzb(arg3, arg4.get(v0).booleanValue());
            ++v0;
        }
    }

    public final void zzj(int arg2, long arg3) {
        this.zzbuf.zzc(arg2, arg3);
    }

    public final void zzj(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbd(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzay(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zze(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzk(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbg(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzba(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzg(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzl(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzbc(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzax(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zzc(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzm(int arg3, List arg4, boolean arg5) {
        int v0 = 0;
        if(arg5) {
            this.zzbuf.zzc(arg3, 2);
            arg3 = 0;
            int v5 = 0;
            while(arg3 < arg4.size()) {
                v5 += zzut.zzbe(arg4.get(arg3).intValue());
                ++arg3;
            }

            this.zzbuf.zzay(v5);
            while(v0 < arg4.size()) {
                this.zzbuf.zzaz(arg4.get(v0).intValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg4.size()) {
            this.zzbuf.zzf(arg3, arg4.get(v0).intValue());
            ++v0;
        }
    }

    public final void zzn(int arg2, int arg3) {
        this.zzbuf.zzg(arg2, arg3);
    }

    public final void zzn(int arg4, List arg5, boolean arg6) {
        int v0 = 0;
        if(arg6) {
            this.zzbuf.zzc(arg4, 2);
            arg4 = 0;
            int v6 = 0;
            while(arg4 < arg5.size()) {
                v6 += zzut.zzba(arg5.get(arg4).longValue());
                ++arg4;
            }

            this.zzbuf.zzay(v6);
            while(v0 < arg5.size()) {
                this.zzbuf.zzaw(arg5.get(v0).longValue());
                ++v0;
            }

            return;
        }

        while(v0 < arg5.size()) {
            this.zzbuf.zzb(arg4, arg5.get(v0).longValue());
            ++v0;
        }
    }

    public final void zzo(int arg2, int arg3) {
        this.zzbuf.zzd(arg2, arg3);
    }

    public final int zzvj() {
        return zze.zzbze;
    }
}

