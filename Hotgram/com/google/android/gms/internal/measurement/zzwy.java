package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzwy implements zzxj {
    private final zzwt zzcbd;
    private final boolean zzcbe;
    private final zzyb zzcbn;
    private final zzva zzcbo;

    private zzwy(zzyb arg1, zzva arg2, zzwt arg3) {
        super();
        this.zzcbn = arg1;
        this.zzcbe = arg2.zze(arg3);
        this.zzcbo = arg2;
        this.zzcbd = arg3;
    }

    public final boolean equals(Object arg3, Object arg4) {
        if(!this.zzcbn.zzah(arg3).equals(this.zzcbn.zzah(arg4))) {
            return 0;
        }

        if(this.zzcbe) {
            return this.zzcbo.zzs(arg3).equals(this.zzcbo.zzs(arg4));
        }

        return 1;
    }

    public final int hashCode(Object arg3) {
        int v0 = this.zzcbn.zzah(arg3).hashCode();
        if(this.zzcbe) {
            v0 = v0 * 53 + this.zzcbo.zzs(arg3).hashCode();
        }

        return v0;
    }

    public final Object newInstance() {
        return this.zzcbd.zzwe().zzwi();
    }

    static zzwy zza(zzyb arg1, zzva arg2, zzwt arg3) {
        return new zzwy(arg1, arg2, arg3);
    }

    public final void zza(Object arg11, zzxi arg12, zzuz arg13) {
        boolean v4_2;
        zzyb v0 = this.zzcbn;
        zzva v1 = this.zzcbo;
        Object v2 = v0.zzai(arg11);
        zzvd v3 = v1.zzt(arg11);
        try {
            do {
            label_4:
                int v5 = 2147483647;
                if(arg12.zzve() != v5) {
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
                zzud v4_3 = ((zzud)v6);
                int v7 = 0;
                do {
                    if(arg12.zzve() == v5) {
                        break;
                    }

                    int v8 = arg12.getTag();
                    if(v8 == 16) {
                        v7 = arg12.zzup();
                        v6 = v1.zza(arg13, this.zzcbd, v7);
                        continue;
                    }
                    else if(v8 == 26) {
                        if(v6 != null) {
                            v1.zza(arg12, v6, arg13, v3);
                        }
                        else {
                            v4_3 = arg12.zzuo();
                        }

                        continue;
                    }
                    else if(arg12.zzvf()) {
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
                        v1.zza(v4_3, v6, arg13, v3);
                        goto label_55;
                    }

                    v0.zza(v2, v7, v4_3);
                    goto label_55;
                }

                throw zzvt.zzwn();
            }
            else if((v4 & 7) == 2) {
                Object v4_1 = v1.zza(arg13, this.zzcbd, v4 >>> 3);
                if(v4_1 != null) {
                    v1.zza(arg12, v4_1, arg13, v3);
                    goto label_55;
                }
                else {
                    v4_2 = v0.zza(v2, arg12);
                }
            }
            else {
                v4_2 = arg12.zzvf();
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

    public final void zza(Object arg6, zzyw arg7) {
        zzud v1_1;
        int v2_1;
        Iterator v0 = this.zzcbo.zzs(arg6).iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_28;
            }

            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            if(((zzvf)v2).zzvx() == zzyv.zzcet && !((zzvf)v2).zzvy() && !((zzvf)v2).zzvz()) {
                if((v1 instanceof zzvy)) {
                    v2_1 = ((zzvf)v2).zzc();
                    v1_1 = ((zzvy)v1).zzwu().zztt();
                }
                else {
                    v2_1 = ((zzvf)v2).zzc();
                    v1 = ((Map$Entry)v1).getValue();
                }

                arg7.zza(v2_1, v1_1);
                continue;
            }

            break;
        }

        throw new IllegalStateException("Found invalid MessageSet item.");
    label_28:
        this.zzcbn.zzc(this.zzcbn.zzah(arg6), arg7);
    }

    public final int zzae(Object arg3) {
        int v0 = this.zzcbn.zzaj(this.zzcbn.zzah(arg3));
        if(this.zzcbe) {
            v0 += this.zzcbo.zzs(arg3).zzvv();
        }

        return v0;
    }

    public final boolean zzaf(Object arg2) {
        return this.zzcbo.zzs(arg2).isInitialized();
    }

    public final void zzd(Object arg2, Object arg3) {
        zzxl.zza(this.zzcbn, arg2, arg3);
        if(this.zzcbe) {
            zzxl.zza(this.zzcbo, arg2, arg3);
        }
    }

    public final void zzu(Object arg2) {
        this.zzcbn.zzu(arg2);
        this.zzcbo.zzu(arg2);
    }
}

