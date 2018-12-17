package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map$Entry;

final class zzdu implements zzef {
    private final zzdo zzmn;
    private final boolean zzmo;
    private final zzex zzmx;
    private final zzbu zzmy;

    private zzdu(zzex arg1, zzbu arg2, zzdo arg3) {
        super();
        this.zzmx = arg1;
        this.zzmo = arg2.zze(arg3);
        this.zzmy = arg2;
        this.zzmn = arg3;
    }

    public final boolean equals(Object arg3, Object arg4) {
        if(!this.zzmx.zzq(arg3).equals(this.zzmx.zzq(arg4))) {
            return 0;
        }

        if(this.zzmo) {
            return this.zzmy.zza(arg3).equals(this.zzmy.zza(arg4));
        }

        return 1;
    }

    public final int hashCode(Object arg3) {
        int v0 = this.zzmx.zzq(arg3).hashCode();
        if(this.zzmo) {
            v0 = v0 * 53 + this.zzmy.zza(arg3).hashCode();
        }

        return v0;
    }

    public final Object newInstance() {
        return this.zzmn.zzbd().zzbi();
    }

    static zzdu zza(zzex arg1, zzbu arg2, zzdo arg3) {
        return new zzdu(arg1, arg2, arg3);
    }

    public final void zza(Object arg6, zzfr arg7) {
        zzbb v1_1;
        int v2_1;
        Iterator v0 = this.zzmy.zza(arg6).iterator();
        while(true) {
            if(!v0.hasNext()) {
                goto label_28;
            }

            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            if(((zzca)v2).zzav() == zzfq.zzrf && !((zzca)v2).zzaw() && !((zzca)v2).zzax()) {
                if((v1 instanceof zzct)) {
                    v2_1 = ((zzca)v2).zzc();
                    v1_1 = ((zzct)v1).zzbs().zzr();
                }
                else {
                    v2_1 = ((zzca)v2).zzc();
                    v1 = ((Map$Entry)v1).getValue();
                }

                arg7.zza(v2_1, v1_1);
                continue;
            }

            break;
        }

        throw new IllegalStateException("Found invalid MessageSet item.");
    label_28:
        this.zzmx.zzc(this.zzmx.zzq(arg6), arg7);
    }

    public final void zza(Object arg7, byte[] arg8, int arg9, int arg10, zzay arg11) {
        int v1;
        int v2;
        zzey v0 = ((zzcg)arg7).zzjp;
        if(v0 == zzey.zzea()) {
            v0 = zzey.zzeb();
            ((zzcg)arg7).zzjp = v0;
        }

        zzey v7 = v0;
        while(true) {
        label_6:
            if(arg9 >= arg10) {
                goto label_49;
            }

            v2 = zzax.zza(arg8, arg9, arg11);
            int v0_1 = arg11.zzfd;
            v1 = 2;
            if(v0_1 == 11) {
                break;
            }

            if((v0_1 & 7) == v1) {
                arg9 = zzax.zza(v0_1, arg8, v2, arg10, v7, arg11);
                continue;
            }

            arg9 = zzax.zza(v0_1, arg8, v2, arg10, arg11);
        }

        arg9 = 0;
        Object v0_2 = null;
        goto label_24;
    label_49:
        if(arg9 == arg10) {
            return;
        }

        throw zzco.zzbo();
    label_24:
        while(v2 < arg10) {
            v2 = zzax.zza(arg8, v2, arg11);
            int v3 = arg11.zzfd;
            int v5 = v3 & 7;
            switch(v3 >>> 3) {
                case 2: {
                    if(v5 == 0) {
                        v2 = zzax.zza(arg8, v2, arg11);
                        arg9 = arg11.zzfd;
                        goto label_24;
                    }
                    else {
                        goto label_39;
                    }
                }
                case 3: {
                    if(v5 == v1) {
                        v2 = zzax.zze(arg8, v2, arg11);
                        v0_2 = arg11.zzff;
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

            v2 = zzax.zza(v3, arg8, v2, arg10, arg11);
        }

        if(v0_2 != null) {
            v7.zzb(arg9 << 3 | v1, v0_2);
        }

        arg9 = v2;
        goto label_6;
    }

    public final void zzc(Object arg2) {
        this.zzmx.zzc(arg2);
        this.zzmy.zzc(arg2);
    }

    public final void zzc(Object arg2, Object arg3) {
        zzeh.zza(this.zzmx, arg2, arg3);
        if(this.zzmo) {
            zzeh.zza(this.zzmy, arg2, arg3);
        }
    }

    public final int zzm(Object arg3) {
        int v0 = this.zzmx.zzr(this.zzmx.zzq(arg3));
        if(this.zzmo) {
            v0 += this.zzmy.zza(arg3).zzat();
        }

        return v0;
    }

    public final boolean zzo(Object arg2) {
        return this.zzmy.zza(arg2).isInitialized();
    }
}

