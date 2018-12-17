package com.google.android.gms.internal.vision;

import java.util.Arrays;

public final class zzfg {
    private int count;
    private boolean zzgl;
    private int zzks;
    private Object[] zznf;
    private static final zzfg zzot;
    private int[] zzou;

    static {
        zzfg.zzot = new zzfg(0, new int[0], new Object[0], false);
    }

    private zzfg(int arg2, int[] arg3, Object[] arg4, boolean arg5) {
        super();
        this.zzks = -1;
        this.count = arg2;
        this.zzou = arg3;
        this.zznf = arg4;
        this.zzgl = arg5;
    }

    private zzfg() {
        this(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v2_1;
        if(this == (((zzfg)arg9))) {
            return 1;
        }

        if(arg9 == null) {
            return 0;
        }

        if(!(arg9 instanceof zzfg)) {
            return 0;
        }

        if(this.count == ((zzfg)arg9).count) {
            int[] v2 = this.zzou;
            int[] v3 = ((zzfg)arg9).zzou;
            int v4 = this.count;
            int v5 = 0;
            while(true) {
                if(v5 >= v4) {
                    break;
                }
                else if(v2[v5] != v3[v5]) {
                    v2_1 = 0;
                }
                else {
                    ++v5;
                    continue;
                }

                goto label_25;
            }

            v2_1 = 1;
        label_25:
            if(v2_1 == 0) {
                return 0;
            }

            Object[] v2_2 = this.zznf;
            Object[] v9 = ((zzfg)arg9).zznf;
            int v3_1 = this.count;
            v4 = 0;
            while(true) {
                if(v4 >= v3_1) {
                    break;
                }
                else if(!v2_2[v4].equals(v9[v4])) {
                    v9_1 = 0;
                }
                else {
                    ++v4;
                    continue;
                }

                goto label_40;
            }

            v9_1 = 1;
        label_40:
            if(v9_1 == 0) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public final int hashCode() {
        int v0 = (this.count + 527) * 31;
        int[] v1 = this.zzou;
        int v2 = this.count;
        int v3 = 0;
        int v4 = 17;
        int v5 = 0;
        int v6 = 17;
        while(v5 < v2) {
            v6 = v6 * 31 + v1[v5];
            ++v5;
        }

        v0 = (v0 + v6) * 31;
        Object[] v1_1 = this.zznf;
        v2 = this.count;
        while(v3 < v2) {
            v4 = v4 * 31 + v1_1[v3].hashCode();
            ++v3;
        }

        return v0 + v4;
    }

    final void zza(StringBuilder arg4, int arg5) {
        int v0;
        for(v0 = 0; v0 < this.count; ++v0) {
            zzea.zza(arg4, arg5, String.valueOf(this.zzou[v0] >>> 3), this.zznf[v0]);
        }
    }

    static zzfg zza(zzfg arg6, zzfg arg7) {
        int v0 = arg6.count + arg7.count;
        int[] v1 = Arrays.copyOf(arg6.zzou, v0);
        System.arraycopy(arg7.zzou, 0, v1, arg6.count, arg7.count);
        Object[] v2 = Arrays.copyOf(arg6.zznf, v0);
        System.arraycopy(arg7.zznf, 0, v2, arg6.count, arg7.count);
        return new zzfg(v0, v1, v2, true);
    }

    final void zza(zzfz arg4) {
        if(arg4.zzbc() == zzd.zzlk) {
            int v0;
            for(v0 = this.count - 1; v0 >= 0; --v0) {
                arg4.zza(this.zzou[v0] >>> 3, this.zznf[v0]);
            }

            return;
        }

        for(v0 = 0; v0 < this.count; ++v0) {
            arg4.zza(this.zzou[v0] >>> 3, this.zznf[v0]);
        }
    }

    public final void zzao() {
        this.zzgl = false;
    }

    final void zzb(int arg3, Object arg4) {
        if(this.zzgl) {
            if(this.count == this.zzou.length) {
                int v0 = this.count < 4 ? 8 : this.count >> 1;
                int v1 = this.count + v0;
                this.zzou = Arrays.copyOf(this.zzou, v1);
                this.zznf = Arrays.copyOf(this.zznf, v1);
            }

            this.zzou[this.count] = arg3;
            this.zznf[this.count] = arg4;
            ++this.count;
            return;
        }

        throw new UnsupportedOperationException();
    }

    private static void zzb(int arg2, Object arg3, zzfz arg4) {
        int v0 = arg2 >>> 3;
        arg2 &= 7;
        if(arg2 == 5) {
            goto label_28;
        }

        switch(arg2) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_20;
            }
            case 3: {
                goto label_9;
            }
        }

        throw new RuntimeException(zzcx.zzce());
    label_20:
        arg4.zza(v0, ((zzbo)arg3));
        return;
    label_22:
        arg4.zzc(v0, ((Long)arg3).longValue());
        return;
    label_25:
        arg4.zzi(v0, ((Long)arg3).longValue());
        return;
    label_9:
        if(arg4.zzbc() == zzd.zzlj) {
            arg4.zzac(v0);
            ((zzfg)arg3).zzb(arg4);
            arg4.zzad(v0);
            return;
        }

        arg4.zzad(v0);
        ((zzfg)arg3).zzb(arg4);
        arg4.zzac(v0);
        return;
    label_28:
        arg4.zzh(v0, ((Integer)arg3).intValue());
    }

    public final void zzb(zzfz arg4) {
        if(this.count == 0) {
            return;
        }

        if(arg4.zzbc() == zzd.zzlj) {
            int v0;
            for(v0 = 0; v0 < this.count; ++v0) {
                zzfg.zzb(this.zzou[v0], this.zznf[v0], arg4);
            }

            return;
        }

        for(v0 = this.count - 1; v0 >= 0; --v0) {
            zzfg.zzb(this.zzou[v0], this.zznf[v0], arg4);
        }
    }

    public final int zzbl() {
        int v0 = this.zzks;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            int v3 = this.zzou[v0] >>> 3;
            int v2 = this.zzou[v0] & 7;
            if(v2 != 5) {
                switch(v2) {
                    case 0: {
                        goto label_35;
                    }
                    case 1: {
                        goto label_30;
                    }
                    case 2: {
                        goto label_26;
                    }
                    case 3: {
                        goto label_19;
                    }
                }

                throw new IllegalStateException(zzcx.zzce());
            label_35:
                v2 = zzca.zze(v3, this.zznf[v0].longValue());
                goto label_39;
            label_19:
                v2 = (zzca.zzt(v3) << 1) + this.zznf[v0].zzbl();
                goto label_39;
            label_26:
                v2 = zzca.zzc(v3, this.zznf[v0]);
                goto label_39;
            label_30:
                v2 = zzca.zzg(v3, this.zznf[v0].longValue());
            }
            else {
                v2 = zzca.zzl(v3, this.zznf[v0].intValue());
            }

        label_39:
            v1 += v2;
            ++v0;
        }

        this.zzks = v1;
        return v1;
    }

    public static zzfg zzdu() {
        return zzfg.zzot;
    }

    static zzfg zzdv() {
        return new zzfg();
    }

    public final int zzdw() {
        int v0 = this.zzks;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            v1 += zzca.zzd(this.zzou[v0] >>> 3, this.zznf[v0]);
            ++v0;
        }

        this.zzks = v1;
        return v1;
    }
}

