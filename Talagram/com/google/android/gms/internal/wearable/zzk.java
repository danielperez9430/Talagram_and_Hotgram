package com.google.android.gms.internal.wearable;

public final class zzk {
    private final byte[] buffer;
    private final int zzgr;
    private final int zzgs;
    private int zzgt;
    private int zzgu;
    private int zzgv;
    private int zzgw;
    private int zzgx;
    private int zzgy;
    private int zzgz;
    private int zzha;

    private zzk(byte[] arg2, int arg3, int arg4) {
        super();
        this.zzgx = 2147483647;
        this.zzgz = 64;
        this.zzha = 67108864;
        this.buffer = arg2;
        this.zzgr = arg3;
        arg4 += arg3;
        this.zzgt = arg4;
        this.zzgs = arg4;
        this.zzgv = arg3;
    }

    public final int getPosition() {
        return this.zzgv - this.zzgr;
    }

    public final byte[] readBytes() {
        int v0 = this.zzk();
        if(v0 >= 0) {
            if(v0 == 0) {
                return zzw.zzhy;
            }

            if(v0 <= this.zzgt - this.zzgv) {
                byte[] v1 = new byte[v0];
                System.arraycopy(this.buffer, this.zzgv, v1, 0, v0);
                this.zzgv += v0;
                return v1;
            }

            throw zzs.zzu();
        }

        throw zzs.zzv();
    }

    public final String readString() {
        int v0 = this.zzk();
        if(v0 >= 0) {
            if(v0 <= this.zzgt - this.zzgv) {
                String v1 = new String(this.buffer, this.zzgv, v0, zzr.UTF_8);
                this.zzgv += v0;
                return v1;
            }

            throw zzs.zzu();
        }

        throw zzs.zzv();
    }

    public static zzk zza(byte[] arg1, int arg2, int arg3) {
        return new zzk(arg1, 0, arg3);
    }

    public final void zza(zzt arg4) {
        int v0 = this.zzk();
        if(this.zzgy < this.zzgz) {
            v0 = this.zze(v0);
            ++this.zzgy;
            arg4.zza(this);
            this.zzc(0);
            --this.zzgy;
            this.zzf(v0);
            return;
        }

        throw new zzs("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final byte[] zzb(int arg4, int arg5) {
        if(arg5 == 0) {
            return zzw.zzhy;
        }

        byte[] v0 = new byte[arg5];
        System.arraycopy(this.buffer, this.zzgr + arg4, v0, 0, arg5);
        return v0;
    }

    public final void zzc(int arg2) {
        if(this.zzgw == arg2) {
            return;
        }

        throw new zzs("Protocol message end-group tag did not match expected tag.");
    }

    final void zzc(int arg4, int arg5) {
        if(arg4 <= this.zzgv - this.zzgr) {
            if(arg4 >= 0) {
                this.zzgv = this.zzgr + arg4;
                this.zzgw = arg5;
                return;
            }

            StringBuilder v1 = new StringBuilder(24);
            v1.append("Bad position ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        int v0 = this.zzgv - this.zzgr;
        StringBuilder v2 = new StringBuilder(50);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is beyond current ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    public final boolean zzd(int arg3) {
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

        throw new zzs("Protocol message tag had invalid wire type.");
    label_20:
        this.zzh(this.zzk());
        return 1;
    label_23:
        this.zzn();
        return 1;
    label_7:
        this.zzm();
        return 1;
    label_25:
        this.zzk();
        return 1;
        do {
        label_11:
            int v0 = this.zzj();
            if(v0 == 0) {
                break;
            }
        }
        while(this.zzd(v0));

        this.zzc(arg3 >>> 3 << 3 | 4);
        return 1;
    }

    public final int zze(int arg2) {
        if(arg2 >= 0) {
            arg2 += this.zzgv;
            int v0 = this.zzgx;
            if(arg2 <= v0) {
                this.zzgx = arg2;
                this.zzo();
                return v0;
            }

            throw zzs.zzu();
        }

        throw zzs.zzv();
    }

    public final void zzf(int arg1) {
        this.zzgx = arg1;
        this.zzo();
    }

    public final void zzg(int arg2) {
        this.zzc(arg2, this.zzgw);
    }

    private final void zzh(int arg3) {
        if(arg3 >= 0) {
            if(this.zzgv + arg3 <= this.zzgx) {
                if(arg3 <= this.zzgt - this.zzgv) {
                    this.zzgv += arg3;
                    return;
                }

                throw zzs.zzu();
            }

            this.zzh(this.zzgx - this.zzgv);
            throw zzs.zzu();
        }

        throw zzs.zzv();
    }

    public final int zzj() {
        if(this.zzgv == this.zzgt) {
            this.zzgw = 0;
            return 0;
        }

        this.zzgw = this.zzk();
        if(this.zzgw != 0) {
            return this.zzgw;
        }

        throw new zzs("Protocol message contained an invalid tag (zero).");
    }

    public final int zzk() {
        int v0 = this.zzq();
        if(v0 >= 0) {
            return v0;
        }

        v0 &= 127;
        int v1 = this.zzq();
        if(v1 >= 0) {
            v1 <<= 7;
            goto label_7;
        }
        else {
            v0 |= (v1 & 127) << 7;
            v1 = this.zzq();
            if(v1 >= 0) {
                v1 <<= 14;
                goto label_7;
            }
            else {
                v0 |= (v1 & 127) << 14;
                v1 = this.zzq();
                if(v1 >= 0) {
                    v1 <<= 21;
                label_7:
                    v0 |= v1;
                }
                else {
                    v0 |= (v1 & 127) << 21;
                    v1 = this.zzq();
                    v0 |= v1 << 28;
                    if(v1 < 0) {
                        v1 = 0;
                        while(true) {
                            if(v1 >= 5) {
                                break;
                            }
                            else if(this.zzq() >= 0) {
                                return v0;
                            }
                            else {
                                ++v1;
                                continue;
                            }

                            return v0;
                        }

                        throw zzs.zzw();
                    }
                }
            }
        }

        return v0;
    }

    public final long zzl() {
        int v0 = 0;
        long v1 = 0;
        while(v0 < 64) {
            int v3 = this.zzq();
            v1 |= (((long)(v3 & 127))) << v0;
            if((v3 & 128) == 0) {
                return v1;
            }

            v0 += 7;
        }

        throw zzs.zzw();
    }

    public final int zzm() {
        return this.zzq() & 255 | (this.zzq() & 255) << 8 | (this.zzq() & 255) << 16 | (this.zzq() & 255) << 24;
    }

    public final long zzn() {
        return ((((long)this.zzq())) & 255) << 8 | (((long)this.zzq())) & 255 | ((((long)this.zzq())) & 255) << 16 | ((((long)this.zzq())) & 255) << 24 | ((((long)this.zzq())) & 255) << 32 | ((((long)this.zzq())) & 255) << 40 | ((((long)this.zzq())) & 255) << 48 | ((((long)this.zzq())) & 255) << 56;
    }

    private final void zzo() {
        this.zzgt += this.zzgu;
        int v0 = this.zzgt;
        if(v0 > this.zzgx) {
            this.zzgu = v0 - this.zzgx;
            this.zzgt -= this.zzgu;
            return;
        }

        this.zzgu = 0;
    }

    public final int zzp() {
        if(this.zzgx == 2147483647) {
            return -1;
        }

        return this.zzgx - this.zzgv;
    }

    private final byte zzq() {
        if(this.zzgv != this.zzgt) {
            byte[] v0 = this.buffer;
            int v1 = this.zzgv;
            this.zzgv = v1 + 1;
            return v0[v1];
        }

        throw zzs.zzu();
    }
}

