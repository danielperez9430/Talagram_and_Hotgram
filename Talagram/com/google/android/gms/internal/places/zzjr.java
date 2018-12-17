package com.google.android.gms.internal.places;

import java.util.Arrays;

public final class zzjr {
    private int count;
    private boolean zznk;
    private int zzsh;
    private Object[] zzvb;
    private static final zzjr zzxr;
    private int[] zzxs;

    static {
        zzjr.zzxr = new zzjr(0, new int[0], new Object[0], false);
    }

    private zzjr(int arg2, int[] arg3, Object[] arg4, boolean arg5) {
        super();
        this.zzsh = -1;
        this.count = arg2;
        this.zzxs = arg3;
        this.zzvb = arg4;
        this.zznk = arg5;
    }

    private zzjr() {
        this(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v2_1;
        if(this == (((zzjr)arg9))) {
            return 1;
        }

        if(arg9 == null) {
            return 0;
        }

        if(!(arg9 instanceof zzjr)) {
            return 0;
        }

        if(this.count == ((zzjr)arg9).count) {
            int[] v2 = this.zzxs;
            int[] v3 = ((zzjr)arg9).zzxs;
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

            Object[] v2_2 = this.zzvb;
            Object[] v9 = ((zzjr)arg9).zzvb;
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
        int[] v1 = this.zzxs;
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
        Object[] v1_1 = this.zzvb;
        v2 = this.count;
        while(v3 < v2) {
            v4 = v4 * 31 + v1_1[v3].hashCode();
            ++v3;
        }

        return v0 + v4;
    }

    final void zzb(StringBuilder arg4, int arg5) {
        int v0;
        for(v0 = 0; v0 < this.count; ++v0) {
            zzik.zzb(arg4, arg5, String.valueOf(this.zzxs[v0] >>> 3), this.zzvb[v0]);
        }
    }

    static zzjr zzb(zzjr arg6, zzjr arg7) {
        int v0 = arg6.count + arg7.count;
        int[] v1 = Arrays.copyOf(arg6.zzxs, v0);
        System.arraycopy(arg7.zzxs, 0, v1, arg6.count, arg7.count);
        Object[] v2 = Arrays.copyOf(arg6.zzvb, v0);
        System.arraycopy(arg7.zzvb, 0, v2, arg6.count, arg7.count);
        return new zzjr(v0, v1, v2, true);
    }

    final void zzb(zzkk arg4) {
        if(arg4.zzcv() == zzh.zzth) {
            int v0;
            for(v0 = this.count - 1; v0 >= 0; --v0) {
                arg4.zzb(this.zzxs[v0] >>> 3, this.zzvb[v0]);
            }

            return;
        }

        for(v0 = 0; v0 < this.count; ++v0) {
            arg4.zzb(this.zzxs[v0] >>> 3, this.zzvb[v0]);
        }
    }

    public final void zzbb() {
        this.zznk = false;
    }

    private static void zzc(int arg2, Object arg3, zzkk arg4) {
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

        throw new RuntimeException(zzhh.zzed());
    label_20:
        arg4.zzb(v0, ((zzfr)arg3));
        return;
    label_22:
        arg4.zzd(v0, ((Long)arg3).longValue());
        return;
    label_25:
        arg4.zzj(v0, ((Long)arg3).longValue());
        return;
    label_9:
        if(arg4.zzcv() == zzh.zztg) {
            arg4.zzbb(v0);
            ((zzjr)arg3).zzc(arg4);
            arg4.zzbc(v0);
            return;
        }

        arg4.zzbc(v0);
        ((zzjr)arg3).zzc(arg4);
        arg4.zzbb(v0);
        return;
    label_28:
        arg4.zzh(v0, ((Integer)arg3).intValue());
    }

    public final void zzc(zzkk arg4) {
        if(this.count == 0) {
            return;
        }

        if(arg4.zzcv() == zzh.zztg) {
            int v0;
            for(v0 = 0; v0 < this.count; ++v0) {
                zzjr.zzc(this.zzxs[v0], this.zzvb[v0], arg4);
            }

            return;
        }

        for(v0 = this.count - 1; v0 >= 0; --v0) {
            zzjr.zzc(this.zzxs[v0], this.zzvb[v0], arg4);
        }
    }

    final void zzc(int arg3, Object arg4) {
        if(this.zznk) {
            if(this.count == this.zzxs.length) {
                int v0 = this.count < 4 ? 8 : this.count >> 1;
                int v1 = this.count + v0;
                this.zzxs = Arrays.copyOf(this.zzxs, v1);
                this.zzvb = Arrays.copyOf(this.zzvb, v1);
            }

            this.zzxs[this.count] = arg3;
            this.zzvb[this.count] = arg4;
            ++this.count;
            return;
        }

        throw new UnsupportedOperationException();
    }

    public final int zzdg() {
        int v0 = this.zzsh;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            int v3 = this.zzxs[v0] >>> 3;
            int v2 = this.zzxs[v0] & 7;
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

                throw new IllegalStateException(zzhh.zzed());
            label_35:
                v2 = zzgf.zzf(v3, this.zzvb[v0].longValue());
                goto label_39;
            label_19:
                v2 = (zzgf.zzas(v3) << 1) + this.zzvb[v0].zzdg();
                goto label_39;
            label_26:
                v2 = zzgf.zzd(v3, this.zzvb[v0]);
                goto label_39;
            label_30:
                v2 = zzgf.zzh(v3, this.zzvb[v0].longValue());
            }
            else {
                v2 = zzgf.zzl(v3, this.zzvb[v0].intValue());
            }

        label_39:
            v1 += v2;
            ++v0;
        }

        this.zzsh = v1;
        return v1;
    }

    public static zzjr zzgp() {
        return zzjr.zzxr;
    }

    static zzjr zzgq() {
        return new zzjr();
    }

    public final int zzgr() {
        int v0 = this.zzsh;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            v1 += zzgf.zze(this.zzxs[v0] >>> 3, this.zzvb[v0]);
            ++v0;
        }

        this.zzsh = v1;
        return v1;
    }
}

