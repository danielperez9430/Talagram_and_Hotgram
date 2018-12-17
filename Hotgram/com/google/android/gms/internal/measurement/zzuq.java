package com.google.android.gms.internal.measurement;

import java.util.Arrays;

final class zzuq extends zzuo {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzbum;
    private int zzbun;
    private int zzbuo;
    private int zzbup;
    private int zzbuq;

    zzuq(byte[] arg1, int arg2, int arg3, boolean arg4, zzup arg5) {
        this(arg1, arg2, arg3, arg4);
    }

    private zzuq(byte[] arg2, int arg3, int arg4, boolean arg5) {
        super(null);
        this.zzbuq = 2147483647;
        this.buffer = arg2;
        this.limit = arg4 + arg3;
        this.pos = arg3;
        this.zzbuo = this.pos;
        this.zzbum = arg5;
    }

    public final double readDouble() {
        return Double.longBitsToDouble(this.zzvb());
    }

    public final float readFloat() {
        return Float.intBitsToFloat(this.zzva());
    }

    public final String readString() {
        int v0 = this.zzuy();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            String v1 = new String(this.buffer, this.pos, v0, zzvo.UTF_8);
            this.pos += v0;
            return v1;
        }

        if(v0 == 0) {
            return "";
        }

        if(v0 < 0) {
            throw zzvt.zzwl();
        }

        throw zzvt.zzwk();
    }

    public final zzwt zza(zzxd arg4, zzuz arg5) {
        int v0 = this.zzuy();
        if(this.zzbuh < this.zzbui) {
            v0 = ((zzuo)this).zzaq(v0);
            ++this.zzbuh;
            Object v4 = arg4.zza(((zzuo)this), arg5);
            ((zzuo)this).zzan(0);
            --this.zzbuh;
            ((zzuo)this).zzar(v0);
            return ((zzwt)v4);
        }

        throw zzvt.zzwp();
    }

    public final void zzan(int arg2) {
        if(this.zzbup == arg2) {
            return;
        }

        throw zzvt.zzwn();
    }

    public final boolean zzao(int arg6) {
        int v1 = 4;
        int v2 = 0;
        switch(arg6 & 7) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_22;
            }
            case 2: {
                goto label_19;
            }
            case 3: {
                goto label_10;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw zzvt.zzwo();
    label_19:
        ((zzuo)this).zzas(this.zzuy());
        return 1;
    label_22:
        ((zzuo)this).zzas(8);
        return 1;
    label_7:
        ((zzuo)this).zzas(v1);
        return 1;
    label_25:
        int v0 = 10;
        if(this.limit - this.pos >= v0) {
            while(true) {
                if(v2 < v0) {
                    byte[] v6 = this.buffer;
                    v1 = this.pos;
                    this.pos = v1 + 1;
                    if(v6[v1] < 0) {
                        ++v2;
                        continue;
                    }
                }
                else {
                    break;
                }

                return 1;
            }

            throw zzvt.zzwm();
        }
        else {
            while(true) {
                if(v2 >= v0) {
                    break;
                }
                else if(this.zzvd() < 0) {
                    ++v2;
                    continue;
                }

                return 1;
            }

            throw zzvt.zzwm();
        }

        return 1;
        do {
        label_10:
            v0 = ((zzuo)this).zzug();
            if(v0 == 0) {
                break;
            }
        }
        while(((zzuo)this).zzao(v0));

        ((zzuo)this).zzan(arg6 >>> 3 << 3 | v1);
        return 1;
    }

    public final int zzaq(int arg2) {
        if(arg2 >= 0) {
            arg2 += ((zzuo)this).zzux();
            int v0 = this.zzbuq;
            if(arg2 <= v0) {
                this.zzbuq = arg2;
                this.zzvc();
                return v0;
            }

            throw zzvt.zzwk();
        }

        throw zzvt.zzwl();
    }

    public final void zzar(int arg1) {
        this.zzbuq = arg1;
        this.zzvc();
    }

    public final void zzas(int arg3) {
        if(arg3 >= 0 && arg3 <= this.limit - this.pos) {
            this.pos += arg3;
            return;
        }

        if(arg3 < 0) {
            throw zzvt.zzwl();
        }

        throw zzvt.zzwk();
    }

    public final int zzug() {
        if(((zzuo)this).zzuw()) {
            this.zzbup = 0;
            return 0;
        }

        this.zzbup = this.zzuy();
        if(this.zzbup >>> 3 != 0) {
            return this.zzbup;
        }

        throw new zzvt("Protocol message contained an invalid tag (zero).");
    }

    public final long zzuh() {
        return this.zzuz();
    }

    public final long zzui() {
        return this.zzuz();
    }

    public final int zzuj() {
        return this.zzuy();
    }

    public final long zzuk() {
        return this.zzvb();
    }

    public final int zzul() {
        return this.zzva();
    }

    public final boolean zzum() {
        if(this.zzuz() != 0) {
            return 1;
        }

        return 0;
    }

    public final String zzun() {
        int v0 = this.zzuy();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            String v1 = zzyj.zzh(this.buffer, this.pos, v0);
            this.pos += v0;
            return v1;
        }

        if(v0 == 0) {
            return "";
        }

        if(v0 <= 0) {
            throw zzvt.zzwl();
        }

        throw zzvt.zzwk();
    }

    public final zzud zzuo() {
        byte[] v0_1;
        int v0 = this.zzuy();
        if(v0 > 0 && v0 <= this.limit - this.pos) {
            zzud v1 = zzud.zzb(this.buffer, this.pos, v0);
            this.pos += v0;
            return v1;
        }

        if(v0 == 0) {
            return zzud.zzbtz;
        }

        if(v0 > 0 && v0 <= this.limit - this.pos) {
            int v1_1 = this.pos;
            this.pos += v0;
            v0_1 = Arrays.copyOfRange(this.buffer, v1_1, this.pos);
        }
        else if(v0 > 0) {
            goto label_36;
        }
        else if(v0 == 0) {
            v0_1 = zzvo.zzbzj;
        }
        else {
            goto label_34;
        }

        return zzud.zzi(v0_1);
    label_34:
        throw zzvt.zzwl();
    label_36:
        throw zzvt.zzwk();
    }

    public final int zzup() {
        return this.zzuy();
    }

    public final int zzuq() {
        return this.zzuy();
    }

    public final int zzur() {
        return this.zzva();
    }

    public final long zzus() {
        return this.zzvb();
    }

    public final int zzut() {
        int v0 = this.zzuy();
        return -(v0 & 1) ^ v0 >>> 1;
    }

    public final long zzuu() {
        long v0 = this.zzuz();
        return -(v0 & 1) ^ v0 >>> 1;
    }

    final long zzuv() {
        long v0 = 0;
        int v2;
        for(v2 = 0; v2 < 64; v2 += 7) {
            int v3 = this.zzvd();
            v0 |= (((long)(v3 & 127))) << v2;
            if((v3 & 128) == 0) {
                return v0;
            }
        }

        throw zzvt.zzwm();
    }

    public final boolean zzuw() {
        if(this.pos == this.limit) {
            return 1;
        }

        return 0;
    }

    public final int zzux() {
        return this.pos - this.zzbuo;
    }

    private final int zzuy() {
        int v0 = this.pos;
        if(this.limit != v0) {
            byte[] v1 = this.buffer;
            int v2 = v0 + 1;
            v0 = v1[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return v0;
            }
            else if(this.limit - v2 >= 9) {
                int v3 = v2 + 1;
                v0 ^= v1[v2] << 7;
                if(v0 < 0) {
                    v0 ^= -128;
                }
                else {
                    v2 = v3 + 1;
                    v0 ^= v1[v3] << 14;
                    if(v0 >= 0) {
                        v0 ^= 16256;
                    }
                    else {
                        v3 = v2 + 1;
                        v0 ^= v1[v2] << 21;
                        if(v0 < 0) {
                            v0 ^= -2080896;
                            goto label_58;
                        }
                        else {
                            v2 = v3 + 1;
                            v0 = v0 ^ v1[v3] << 28 ^ 266354560;
                            if(v1[v3] < 0) {
                                v3 = v2 + 1;
                                if(v1[v2] < 0) {
                                    v2 = v3 + 1;
                                    if(v1[v3] < 0) {
                                        v3 = v2 + 1;
                                        if(v1[v2] < 0) {
                                            v2 = v3 + 1;
                                            if(v1[v3] < 0) {
                                                v3 = v2 + 1;
                                                if(v1[v2] < 0) {
                                                    goto label_60;
                                                }

                                                goto label_58;
                                            }
                                        }
                                        else {
                                            goto label_58;
                                        }
                                    }
                                }
                                else {
                                    goto label_58;
                                }
                            }
                        }
                    }

                    v3 = v2;
                }

            label_58:
                this.pos = v3;
                return v0;
            }
        }

    label_60:
        return ((int)((zzuo)this).zzuv());
    }

    private final long zzuz() {
        long v0_1;
        int v6;
        long v2_1;
        long v9;
        int v0 = this.pos;
        if(this.limit != v0) {
            byte[] v1 = this.buffer;
            int v2 = v0 + 1;
            v0 = v1[v0];
            if(v0 >= 0) {
                this.pos = v2;
                return ((long)v0);
            }
            else if(this.limit - v2 >= 9) {
                int v3 = v2 + 1;
                v0 ^= v1[v2] << 7;
                if(v0 < 0) {
                    v0 ^= -128;
                    goto label_20;
                }
                else {
                    v2 = v3 + 1;
                    v0 ^= v1[v3] << 14;
                    if(v0 >= 0) {
                        v9 = ((long)(v0 ^ 16256));
                        v0 = v2;
                        goto label_23;
                    }
                    else {
                        v3 = v2 + 1;
                        v0 ^= v1[v2] << 21;
                        if(v0 < 0) {
                            v0 ^= -2080896;
                        label_20:
                            v9 = ((long)v0);
                            v0 = v3;
                        label_23:
                            v2_1 = v9;
                        }
                        else {
                            long v4 = ((long)v0);
                            v0 = v3 + 1;
                            v2_1 = (((long)v1[v3])) << 28 ^ v4;
                            v4 = 0;
                            if(v2_1 >= v4) {
                                v4 = 266354560;
                            }
                            else {
                                v6 = v0 + 1;
                                v2_1 ^= (((long)v1[v0])) << 35;
                                if(v2_1 < v4) {
                                    v0_1 = -34093383808L;
                                    goto label_63;
                                }
                                else {
                                    v0 = v6 + 1;
                                    v2_1 ^= (((long)v1[v6])) << 42;
                                    if(v2_1 >= v4) {
                                        v4 = 4363953127296L;
                                        goto label_53;
                                    }
                                    else {
                                        goto label_76;
                                    }
                                }

                                goto label_65;
                            }

                        label_53:
                            v2_1 ^= v4;
                            goto label_99;
                        label_76:
                            v6 = v0 + 1;
                            v2_1 ^= (((long)v1[v0])) << 49;
                            if(v2_1 < v4) {
                                v0_1 = -558586000294016L;
                            label_63:
                                v2_1 ^= v0_1;
                            }
                            else {
                                v0 = v6 + 1;
                                v2_1 = v2_1 ^ (((long)v1[v6])) << 56 ^ 71499008037633920L;
                                if(v2_1 < v4) {
                                    v6 = v0 + 1;
                                    if((((long)v1[v0])) >= v4) {
                                    }
                                    else {
                                        goto label_101;
                                    }
                                }
                                else {
                                    goto label_99;
                                }
                            }

                        label_65:
                            v0 = v6;
                        }
                    }
                }

            label_99:
                this.pos = v0;
                return v2_1;
            }
        }

    label_101:
        return ((zzuo)this).zzuv();
    }

    private final int zzva() {
        int v0 = this.pos;
        if(this.limit - v0 >= 4) {
            byte[] v1 = this.buffer;
            this.pos = v0 + 4;
            return (v1[v0 + 3] & 255) << 24 | (v1[v0] & 255 | (v1[v0 + 1] & 255) << 8 | (v1[v0 + 2] & 255) << 16);
        }

        throw zzvt.zzwk();
    }

    private final long zzvb() {
        int v0 = this.pos;
        int v2 = 8;
        if(this.limit - v0 >= v2) {
            byte[] v1 = this.buffer;
            this.pos = v0 + 8;
            return ((((long)v1[v0 + 7])) & 255) << 56 | ((((long)v1[v0])) & 255 | ((((long)v1[v0 + 1])) & 255) << v2 | ((((long)v1[v0 + 2])) & 255) << 16 | ((((long)v1[v0 + 3])) & 255) << 24 | ((((long)v1[v0 + 4])) & 255) << 32 | ((((long)v1[v0 + 5])) & 255) << 40 | ((((long)v1[v0 + 6])) & 255) << 48);
        }

        throw zzvt.zzwk();
    }

    private final void zzvc() {
        this.limit += this.zzbun;
        int v0 = this.limit - this.zzbuo;
        if(v0 > this.zzbuq) {
            this.zzbun = v0 - this.zzbuq;
            this.limit -= this.zzbun;
            return;
        }

        this.zzbun = 0;
    }

    private final byte zzvd() {
        if(this.pos != this.limit) {
            byte[] v0 = this.buffer;
            int v1 = this.pos;
            this.pos = v1 + 1;
            return v0[v1];
        }

        throw zzvt.zzwk();
    }
}

