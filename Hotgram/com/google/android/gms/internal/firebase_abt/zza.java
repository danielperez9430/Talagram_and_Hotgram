package com.google.android.gms.internal.firebase_abt;

public final class zza {
    private final byte[] buffer;
    private final int zzh;
    private final int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;

    private zza(byte[] arg2, int arg3, int arg4) {
        super();
        this.zzn = 2147483647;
        this.zzp = 64;
        this.zzq = 67108864;
        this.buffer = arg2;
        this.zzh = arg3;
        arg4 += arg3;
        this.zzj = arg4;
        this.zzi = arg4;
        this.zzl = arg3;
    }

    public final int getPosition() {
        return this.zzl - this.zzh;
    }

    public final String readString() {
        int v0 = this.zzg();
        if(v0 >= 0) {
            if(v0 <= this.zzj - this.zzl) {
                String v1 = new String(this.buffer, this.zzl, v0, zzh.UTF_8);
                this.zzl += v0;
                return v1;
            }

            throw zzi.zzl();
        }

        throw zzi.zzm();
    }

    public static zza zza(byte[] arg1, int arg2, int arg3) {
        return new zza(arg1, 0, arg3);
    }

    public final void zza(int arg2) {
        if(this.zzm == arg2) {
            return;
        }

        throw new zzi("Protocol message end-group tag did not match expected tag.");
    }

    public final void zza(zzj arg4) {
        int v0 = this.zzg();
        if(this.zzo < this.zzp) {
            if(v0 >= 0) {
                v0 += this.zzl;
                int v1 = this.zzn;
                if(v0 <= v1) {
                    this.zzn = v0;
                    this.zzh();
                    ++this.zzo;
                    arg4.zza(this);
                    this.zza(0);
                    --this.zzo;
                    this.zzn = v1;
                    this.zzh();
                    return;
                }

                throw zzi.zzl();
            }

            throw zzi.zzm();
        }

        throw new zzi("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final byte[] zza(int arg4, int arg5) {
        if(arg5 == 0) {
            return zzm.zzao;
        }

        byte[] v0 = new byte[arg5];
        System.arraycopy(this.buffer, this.zzh + arg4, v0, 0, arg5);
        return v0;
    }

    final void zzb(int arg4, int arg5) {
        if(arg4 <= this.zzl - this.zzh) {
            if(arg4 >= 0) {
                this.zzl = this.zzh + arg4;
                this.zzm = 106;
                return;
            }

            StringBuilder v1 = new StringBuilder(24);
            v1.append("Bad position ");
            v1.append(arg4);
            throw new IllegalArgumentException(v1.toString());
        }

        int v0 = this.zzl - this.zzh;
        StringBuilder v2 = new StringBuilder(50);
        v2.append("Position ");
        v2.append(arg4);
        v2.append(" is beyond current ");
        v2.append(v0);
        throw new IllegalArgumentException(v2.toString());
    }

    public final boolean zzb(int arg3) {
        switch(arg3 & 7) {
            case 0: {
                goto label_35;
            }
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_23;
            }
            case 3: {
                goto label_14;
            }
            case 4: {
                return 0;
            }
            case 5: {
                goto label_7;
            }
        }

        throw new zzi("Protocol message tag had invalid wire type.");
    label_35:
        this.zzg();
        return 1;
    label_23:
        this.zzc(this.zzg());
        return 1;
    label_7:
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        return 1;
    label_26:
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        this.zzi();
        return 1;
        do {
        label_14:
            int v0 = this.zzd();
            if(v0 == 0) {
                break;
            }
        }
        while(this.zzb(v0));

        this.zza(arg3 >>> 3 << 3 | 4);
        return 1;
    }

    private final void zzc(int arg3) {
        if(arg3 >= 0) {
            if(this.zzl + arg3 <= this.zzn) {
                if(arg3 <= this.zzj - this.zzl) {
                    this.zzl += arg3;
                    return;
                }

                throw zzi.zzl();
            }

            this.zzc(this.zzn - this.zzl);
            throw zzi.zzl();
        }

        throw zzi.zzm();
    }

    public final int zzd() {
        if(this.zzl == this.zzj) {
            this.zzm = 0;
            return 0;
        }

        this.zzm = this.zzg();
        if(this.zzm != 0) {
            return this.zzm;
        }

        throw new zzi("Protocol message contained an invalid tag (zero).");
    }

    public final long zze() {
        int v0 = 0;
        long v1 = 0;
        while(v0 < 64) {
            int v3 = this.zzi();
            v1 |= (((long)(v3 & 127))) << v0;
            if((v3 & 128) == 0) {
                return v1;
            }

            v0 += 7;
        }

        throw zzi.zzn();
    }

    public final int zzf() {
        return this.zzg();
    }

    public final int zzg() {
        int v0 = this.zzi();
        if(v0 >= 0) {
            return v0;
        }

        v0 &= 127;
        int v1 = this.zzi();
        if(v1 >= 0) {
            v1 <<= 7;
            goto label_7;
        }
        else {
            v0 |= (v1 & 127) << 7;
            v1 = this.zzi();
            if(v1 >= 0) {
                v1 <<= 14;
                goto label_7;
            }
            else {
                v0 |= (v1 & 127) << 14;
                v1 = this.zzi();
                if(v1 >= 0) {
                    v1 <<= 21;
                label_7:
                    v0 |= v1;
                }
                else {
                    v0 |= (v1 & 127) << 21;
                    v1 = this.zzi();
                    v0 |= v1 << 28;
                    if(v1 < 0) {
                        v1 = 0;
                        while(true) {
                            if(v1 >= 5) {
                                break;
                            }
                            else if(this.zzi() >= 0) {
                                return v0;
                            }
                            else {
                                ++v1;
                                continue;
                            }

                            return v0;
                        }

                        throw zzi.zzn();
                    }
                }
            }
        }

        return v0;
    }

    private final void zzh() {
        this.zzj += this.zzk;
        int v0 = this.zzj;
        if(v0 > this.zzn) {
            this.zzk = v0 - this.zzn;
            this.zzj -= this.zzk;
            return;
        }

        this.zzk = 0;
    }

    private final byte zzi() {
        if(this.zzl != this.zzj) {
            byte[] v0 = this.buffer;
            int v1 = this.zzl;
            this.zzl = v1 + 1;
            return v0[v1];
        }

        throw zzi.zzl();
    }
}

