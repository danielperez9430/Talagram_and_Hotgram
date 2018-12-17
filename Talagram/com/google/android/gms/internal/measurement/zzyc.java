package com.google.android.gms.internal.measurement;

import java.util.Arrays;

public final class zzyc {
    private int count;
    private boolean zzbtu;
    private int zzbyn;
    private Object[] zzcba;
    private static final zzyc zzcco;
    private int[] zzccp;

    static {
        zzyc.zzcco = new zzyc(0, new int[0], new Object[0], false);
    }

    private zzyc(int arg2, int[] arg3, Object[] arg4, boolean arg5) {
        super();
        this.zzbyn = -1;
        this.count = arg2;
        this.zzccp = arg3;
        this.zzcba = arg4;
        this.zzbtu = arg5;
    }

    private zzyc() {
        this(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v2_1;
        if(this == (((zzyc)arg9))) {
            return 1;
        }

        if(arg9 == null) {
            return 0;
        }

        if(!(arg9 instanceof zzyc)) {
            return 0;
        }

        if(this.count == ((zzyc)arg9).count) {
            int[] v2 = this.zzccp;
            int[] v3 = ((zzyc)arg9).zzccp;
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

            Object[] v2_2 = this.zzcba;
            Object[] v9 = ((zzyc)arg9).zzcba;
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
        int[] v1 = this.zzccp;
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
        Object[] v1_1 = this.zzcba;
        v2 = this.count;
        while(v3 < v2) {
            v4 = v4 * 31 + v1_1[v3].hashCode();
            ++v3;
        }

        return v0 + v4;
    }

    static zzyc zza(zzyc arg6, zzyc arg7) {
        int v0 = arg6.count + arg7.count;
        int[] v1 = Arrays.copyOf(arg6.zzccp, v0);
        System.arraycopy(arg7.zzccp, 0, v1, arg6.count, arg7.count);
        Object[] v2 = Arrays.copyOf(arg6.zzcba, v0);
        System.arraycopy(arg7.zzcba, 0, v2, arg6.count, arg7.count);
        return new zzyc(v0, v1, v2, true);
    }

    final void zza(zzyw arg4) {
        if(arg4.zzvj() == zze.zzbzf) {
            int v0;
            for(v0 = this.count - 1; v0 >= 0; --v0) {
                arg4.zza(this.zzccp[v0] >>> 3, this.zzcba[v0]);
            }

            return;
        }

        for(v0 = 0; v0 < this.count; ++v0) {
            arg4.zza(this.zzccp[v0] >>> 3, this.zzcba[v0]);
        }
    }

    final void zzb(StringBuilder arg4, int arg5) {
        int v0;
        for(v0 = 0; v0 < this.count; ++v0) {
            zzww.zzb(arg4, arg5, String.valueOf(this.zzccp[v0] >>> 3), this.zzcba[v0]);
        }
    }

    private static void zzb(int arg2, Object arg3, zzyw arg4) {
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

        throw new RuntimeException(zzvt.zzwo());
    label_20:
        arg4.zza(v0, ((zzud)arg3));
        return;
    label_22:
        arg4.zzc(v0, ((Long)arg3).longValue());
        return;
    label_25:
        arg4.zzi(v0, ((Long)arg3).longValue());
        return;
    label_9:
        if(arg4.zzvj() == zze.zzbze) {
            arg4.zzbk(v0);
            ((zzyc)arg3).zzb(arg4);
            arg4.zzbl(v0);
            return;
        }

        arg4.zzbl(v0);
        ((zzyc)arg3).zzb(arg4);
        arg4.zzbk(v0);
        return;
    label_28:
        arg4.zzg(v0, ((Integer)arg3).intValue());
    }

    public final void zzb(zzyw arg4) {
        if(this.count == 0) {
            return;
        }

        if(arg4.zzvj() == zze.zzbze) {
            int v0;
            for(v0 = 0; v0 < this.count; ++v0) {
                zzyc.zzb(this.zzccp[v0], this.zzcba[v0], arg4);
            }

            return;
        }

        for(v0 = this.count - 1; v0 >= 0; --v0) {
            zzyc.zzb(this.zzccp[v0], this.zzcba[v0], arg4);
        }
    }

    final void zzb(int arg3, Object arg4) {
        if(this.zzbtu) {
            if(this.count == this.zzccp.length) {
                int v0 = this.count < 4 ? 8 : this.count >> 1;
                int v1 = this.count + v0;
                this.zzccp = Arrays.copyOf(this.zzccp, v1);
                this.zzcba = Arrays.copyOf(this.zzcba, v1);
            }

            this.zzccp[this.count] = arg3;
            this.zzcba[this.count] = arg4;
            ++this.count;
            return;
        }

        throw new UnsupportedOperationException();
    }

    public final void zzsm() {
        this.zzbtu = false;
    }

    public final int zzvu() {
        int v0 = this.zzbyn;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            int v3 = this.zzccp[v0] >>> 3;
            int v2 = this.zzccp[v0] & 7;
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

                throw new IllegalStateException(zzvt.zzwo());
            label_35:
                v2 = zzut.zze(v3, this.zzcba[v0].longValue());
                goto label_39;
            label_19:
                v2 = (zzut.zzbb(v3) << 1) + this.zzcba[v0].zzvu();
                goto label_39;
            label_26:
                v2 = zzut.zzc(v3, this.zzcba[v0]);
                goto label_39;
            label_30:
                v2 = zzut.zzg(v3, this.zzcba[v0].longValue());
            }
            else {
                v2 = zzut.zzk(v3, this.zzcba[v0].intValue());
            }

        label_39:
            v1 += v2;
            ++v0;
        }

        this.zzbyn = v1;
        return v1;
    }

    public static zzyc zzyf() {
        return zzyc.zzcco;
    }

    static zzyc zzyg() {
        return new zzyc();
    }

    public final int zzyh() {
        int v0 = this.zzbyn;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            v1 += zzut.zzd(this.zzccp[v0] >>> 3, this.zzcba[v0]);
            ++v0;
        }

        this.zzbyn = v1;
        return v1;
    }
}

