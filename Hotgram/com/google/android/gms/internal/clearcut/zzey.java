package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

public final class zzey {
    private int count;
    private boolean zzfa;
    private int zzjq;
    private Object[] zzmj;
    private static final zzey zzoz;
    private int[] zzpa;

    static {
        zzey.zzoz = new zzey(0, new int[0], new Object[0], false);
    }

    private zzey(int arg2, int[] arg3, Object[] arg4, boolean arg5) {
        super();
        this.zzjq = -1;
        this.count = arg2;
        this.zzpa = arg3;
        this.zzmj = arg4;
        this.zzfa = arg5;
    }

    private zzey() {
        this(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object arg9) {
        int v9_1;
        int v2_1;
        if(this == (((zzey)arg9))) {
            return 1;
        }

        if(arg9 == null) {
            return 0;
        }

        if(!(arg9 instanceof zzey)) {
            return 0;
        }

        if(this.count == ((zzey)arg9).count) {
            int[] v2 = this.zzpa;
            int[] v3 = ((zzey)arg9).zzpa;
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

            Object[] v2_2 = this.zzmj;
            Object[] v9 = ((zzey)arg9).zzmj;
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
        int[] v1 = this.zzpa;
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
        Object[] v1_1 = this.zzmj;
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
            zzdr.zza(arg4, arg5, String.valueOf(this.zzpa[v0] >>> 3), this.zzmj[v0]);
        }
    }

    static zzey zza(zzey arg6, zzey arg7) {
        int v0 = arg6.count + arg7.count;
        int[] v1 = Arrays.copyOf(arg6.zzpa, v0);
        System.arraycopy(arg7.zzpa, 0, v1, arg6.count, arg7.count);
        Object[] v2 = Arrays.copyOf(arg6.zzmj, v0);
        System.arraycopy(arg7.zzmj, 0, v2, arg6.count, arg7.count);
        return new zzey(v0, v1, v2, true);
    }

    final void zza(zzfr arg4) {
        if(arg4.zzaj() == zzg.zzkp) {
            int v0;
            for(v0 = this.count - 1; v0 >= 0; --v0) {
                arg4.zza(this.zzpa[v0] >>> 3, this.zzmj[v0]);
            }

            return;
        }

        for(v0 = 0; v0 < this.count; ++v0) {
            arg4.zza(this.zzpa[v0] >>> 3, this.zzmj[v0]);
        }
    }

    public final int zzas() {
        int v0 = this.zzjq;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            int v3 = this.zzpa[v0] >>> 3;
            int v2 = this.zzpa[v0] & 7;
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

                throw new IllegalStateException(zzco.zzbn());
            label_35:
                v2 = zzbn.zze(v3, this.zzmj[v0].longValue());
                goto label_39;
            label_19:
                v2 = (zzbn.zzr(v3) << 1) + this.zzmj[v0].zzas();
                goto label_39;
            label_26:
                v2 = zzbn.zzc(v3, this.zzmj[v0]);
                goto label_39;
            label_30:
                v2 = zzbn.zzg(v3, this.zzmj[v0].longValue());
            }
            else {
                v2 = zzbn.zzj(v3, this.zzmj[v0].intValue());
            }

        label_39:
            v1 += v2;
            ++v0;
        }

        this.zzjq = v1;
        return v1;
    }

    final void zzb(int arg3, Object arg4) {
        if(this.zzfa) {
            if(this.count == this.zzpa.length) {
                int v0 = this.count < 4 ? 8 : this.count >> 1;
                int v1 = this.count + v0;
                this.zzpa = Arrays.copyOf(this.zzpa, v1);
                this.zzmj = Arrays.copyOf(this.zzmj, v1);
            }

            this.zzpa[this.count] = arg3;
            this.zzmj[this.count] = arg4;
            ++this.count;
            return;
        }

        throw new UnsupportedOperationException();
    }

    private static void zzb(int arg2, Object arg3, zzfr arg4) {
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

        throw new RuntimeException(zzco.zzbn());
    label_20:
        arg4.zza(v0, ((zzbb)arg3));
        return;
    label_22:
        arg4.zzc(v0, ((Long)arg3).longValue());
        return;
    label_25:
        arg4.zzi(v0, ((Long)arg3).longValue());
        return;
    label_9:
        if(arg4.zzaj() == zzg.zzko) {
            arg4.zzaa(v0);
            ((zzey)arg3).zzb(arg4);
            arg4.zzab(v0);
            return;
        }

        arg4.zzab(v0);
        ((zzey)arg3).zzb(arg4);
        arg4.zzaa(v0);
        return;
    label_28:
        arg4.zzf(v0, ((Integer)arg3).intValue());
    }

    public final void zzb(zzfr arg4) {
        if(this.count == 0) {
            return;
        }

        if(arg4.zzaj() == zzg.zzko) {
            int v0;
            for(v0 = 0; v0 < this.count; ++v0) {
                zzey.zzb(this.zzpa[v0], this.zzmj[v0], arg4);
            }

            return;
        }

        for(v0 = this.count - 1; v0 >= 0; --v0) {
            zzey.zzb(this.zzpa[v0], this.zzmj[v0], arg4);
        }
    }

    public static zzey zzea() {
        return zzey.zzoz;
    }

    static zzey zzeb() {
        return new zzey();
    }

    public final int zzec() {
        int v0 = this.zzjq;
        if(v0 != -1) {
            return v0;
        }

        v0 = 0;
        int v1 = 0;
        while(v0 < this.count) {
            v1 += zzbn.zzd(this.zzpa[v0] >>> 3, this.zzmj[v0]);
            ++v0;
        }

        this.zzjq = v1;
        return v1;
    }

    public final void zzv() {
        this.zzfa = false;
    }
}

