package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzyx {
    private final byte[] buffer;
    private int zzbuh;
    private int zzbui;
    private int zzbuj;
    private int zzbun;
    private int zzbup;
    private int zzbuq;
    private final int zzcev;
    private final int zzcew;
    private int zzcex;
    private int zzcey;
    private zzuo zzcez;

    private zzyx(byte[] arg2, int arg3, int arg4) {
        super();
        this.zzbuq = 2147483647;
        this.zzbui = 64;
        this.zzbuj = 67108864;
        this.buffer = arg2;
        this.zzcev = arg3;
        arg4 += arg3;
        this.zzcex = arg4;
        this.zzcew = arg4;
        this.zzcey = arg3;
    }

    public final int getPosition() {
        return this.zzcey - this.zzcev;
    }

    public final String readString() {
        int v0 = this.zzuy();
        if(v0 >= 0) {
            if(v0 <= this.zzcex - this.zzcey) {
                String v1 = new String(this.buffer, this.zzcey, v0, zzze.UTF_8);
                this.zzcey += v0;
                return v1;
            }

            throw zzzf.zzyw();
        }

        throw zzzf.zzyx();
    }

    public final zzvm zza(zzxd arg6) {
        try {
            if(this.zzcez == null) {
                this.zzcez = zzuo.zzd(this.buffer, this.zzcev, this.zzcew);
            }

            int v0 = this.zzcez.zzux();
            int v1 = this.zzcey - this.zzcev;
            if(v0 <= v1) {
                this.zzcez.zzas(v1 - v0);
                this.zzcez.zzap(this.zzbui - this.zzbuh);
                zzwt v6_1 = this.zzcez.zza(arg6, zzuz.zzvp());
                this.zzao(this.zzbup);
                return ((zzvm)v6_1);
            }

            throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", Integer.valueOf(v0), Integer.valueOf(v1)));
        }
        catch(zzvt v6) {
            throw new zzzf("", ((Exception)v6));
        }
    }

    public final void zza(zzzg arg4) {
        int v0 = this.zzuy();
        if(this.zzbuh < this.zzbui) {
            v0 = this.zzaq(v0);
            ++this.zzbuh;
            arg4.zza(this);
            this.zzan(0);
            --this.zzbuh;
            this.zzar(v0);
            return;
        }

        throw zzzf.zzyz();
    }

    public final void zza(zzzg arg3, int arg4) {
        if(this.zzbuh < this.zzbui) {
            ++this.zzbuh;
            arg3.zza(this);
            this.zzan(arg4 << 3 | 4);
            --this.zzbuh;
            return;
        }

        throw zzzf.zzyz();
    }

    public final void zzan(int arg2) {
        if(this.zzbup == arg2) {
            return;
        }

        throw new zzzf("Protocol message end-group tag did not match expected tag.");
    }

    public final boolean zzao(int arg3) {
        switch(arg3 & 7) {
            case 0: {
                goto label_25;
            }
            case 1: {
                goto label_23;
            }
            case 2: {
                goto label_20;
            }
            case 3: {
                goto label_11;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw new zzzf("Protocol message tag had invalid wire type.");
    label_20:
        this.zzas(this.zzuy());
        return 1;
    label_23:
        this.zzvb();
        return 1;
    label_7:
        this.zzva();
        return 1;
    label_25:
        this.zzuy();
        return 1;
        do {
        label_11:
            int v0 = this.zzug();
            if(v0 == 0) {
                break;
            }
        }
        while(this.zzao(v0));

        this.zzan(arg3 >>> 3 << 3 | 4);
        return 1;
    }

    public final int zzaq(int arg2) {
        if(arg2 >= 0) {
            arg2 += this.zzcey;
            int v0 = this.zzbuq;
            if(arg2 <= v0) {
                this.zzbuq = arg2;
                this.zzvc();
                return v0;
            }

            throw zzzf.zzyw();
        }

        throw zzzf.zzyx();
    }

    public final void zzar(int arg1) {
        this.zzbuq = arg1;
        this.zzvc();
    }

    private final void zzas(int arg3) {
        if(arg3 >= 0) {
            if(this.zzcey + arg3 <= this.zzbuq) {
                if(arg3 <= this.zzcex - this.zzcey) {
                    this.zzcey += arg3;
                    return;
                }

                throw zzzf.zzyw();
            }

            this.zzas(this.zzbuq - this.zzcey);
            throw zzzf.zzyw();
        }

        throw zzzf.zzyx();
    }

    public final void zzby(int arg2) {
        this.zzt(arg2, this.zzbup);
    }

    public static zzyx zzj(byte[] arg1, int arg2, int arg3) {
        return new zzyx(arg1, 0, arg3);
    }

    public static zzyx zzn(byte[] arg2) {
        return zzyx.zzj(arg2, 0, arg2.length);
    }

    public final byte[] zzs(int arg4, int arg5) {
        if(arg5 == 0) {
            return zzzj.zzcfx;
        }

        byte[] v0 = new byte[arg5];
        System.arraycopy(this.buffer, this.zzcev + arg4, v0, 0, arg5);
        return v0;
    }

    final void zzt(int arg4, int arg5) {
        if(arg4 <= this.zzcey - this.zzcev) {
            if(arg4 >= 0) {
                this.zzcey = this.zzcev + arg4;
                this.zzbup = arg5;
                return;
            }

            StringBuilder v1 = new StringBuilder(24);
            v1.append("Bad position ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        int v0 = this.zzcey - this.zzcev;
        StringBuilder v2 = new StringBuilder(50);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is beyond current ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    public final int zzug() {
        if(this.zzcey == this.zzcex) {
            this.zzbup = 0;
            return 0;
        }

        this.zzbup = this.zzuy();
        if(this.zzbup != 0) {
            return this.zzbup;
        }

        throw new zzzf("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzum() {
        if(this.zzuy() != 0) {
            return 1;
        }

        return 0;
    }

    public final int zzuy() {
        int v0 = this.zzvd();
        if(v0 >= 0) {
            return v0;
        }

        v0 &= 127;
        int v1 = this.zzvd();
        if(v1 >= 0) {
            v1 <<= 7;
            goto label_7;
        }
        else {
            v0 |= (v1 & 127) << 7;
            v1 = this.zzvd();
            if(v1 >= 0) {
                v1 <<= 14;
                goto label_7;
            }
            else {
                v0 |= (v1 & 127) << 14;
                v1 = this.zzvd();
                if(v1 >= 0) {
                    v1 <<= 21;
                label_7:
                    v0 |= v1;
                }
                else {
                    v0 |= (v1 & 127) << 21;
                    v1 = this.zzvd();
                    v0 |= v1 << 28;
                    if(v1 < 0) {
                        v1 = 0;
                        while(true) {
                            if(v1 >= 5) {
                                break;
                            }
                            else if(this.zzvd() >= 0) {
                                return v0;
                            }
                            else {
                                ++v1;
                                continue;
                            }

                            return v0;
                        }

                        throw zzzf.zzyy();
                    }
                }
            }
        }

        return v0;
    }

    public final long zzuz() {
        int v0 = 0;
        long v1 = 0;
        while(v0 < 64) {
            int v3 = this.zzvd();
            v1 |= (((long)(v3 & 127))) << v0;
            if((v3 & 128) == 0) {
                return v1;
            }

            v0 += 7;
        }

        throw zzzf.zzyy();
    }

    public final int zzva() {
        return this.zzvd() & 255 | (this.zzvd() & 255) << 8 | (this.zzvd() & 255) << 16 | (this.zzvd() & 255) << 24;
    }

    public final long zzvb() {
        return ((((long)this.zzvd())) & 255) << 8 | (((long)this.zzvd())) & 255 | ((((long)this.zzvd())) & 255) << 16 | ((((long)this.zzvd())) & 255) << 24 | ((((long)this.zzvd())) & 255) << 32 | ((((long)this.zzvd())) & 255) << 40 | ((((long)this.zzvd())) & 255) << 48 | ((((long)this.zzvd())) & 255) << 56;
    }

    private final void zzvc() {
        this.zzcex += this.zzbun;
        int v0 = this.zzcex;
        if(v0 > this.zzbuq) {
            this.zzbun = v0 - this.zzbuq;
            this.zzcex -= this.zzbun;
            return;
        }

        this.zzbun = 0;
    }

    private final byte zzvd() {
        if(this.zzcey != this.zzcex) {
            byte[] v0 = this.buffer;
            int v1 = this.zzcey;
            this.zzcey = v1 + 1;
            return v0[v1];
        }

        throw zzzf.zzyw();
    }

    public final int zzyr() {
        if(this.zzbuq == 2147483647) {
            return -1;
        }

        return this.zzbuq - this.zzcey;
    }
}

